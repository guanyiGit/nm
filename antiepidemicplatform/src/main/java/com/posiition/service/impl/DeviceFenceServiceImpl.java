package com.posiition.service.impl;

import com.dogmanager.dao.TDogInfoMapper;
import com.entities.OrgInfo;
import com.entities.TFenceDef;
import com.entities.TFenceDetail;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.posiition.VO.FcnceRefFenceDetail;
import com.posiition.VO.FcnceRefFenceDetailChile;
import com.posiition.VO.fenceDetailVO;
import com.posiition.mapper.DeviceFenceMapper;
import com.posiition.service.BizService;
import com.posiition.service.DeviceFenceService;
import com.utils.Converter;
import com.utils.OrgUtils;
import com.utils.ReqPage;
import com.utils.ShiroUtils;
import com.utils.pojo.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceFenceServiceImpl implements DeviceFenceService {


    @Autowired
    private DeviceFenceMapper deviceFenceMapper;

    @Autowired
    private BizService bizService;

    @Autowired
    private TDogInfoMapper dogInfoMapper;

    @Autowired
    private OrgInfoService orgInfoService;

    private Logger logger = LoggerFactory.getLogger(DeviceFenceServiceImpl.class);
    @Override
    public PageInfo<TFenceDef> list(Integer userId, ReqPage page) {
        List<Integer> orgIds = bizService.orgIds(userId);
        PageHelper.offsetPage(page.getPageNum(), page.getPageNum());
        List<TFenceDef> tFenceDefs = deviceFenceMapper.findFencesByUserId(userId, orgIds);
        return new PageInfo<TFenceDef>(tFenceDefs);
    }

    @Override
    public boolean delete(Integer id) {
        return deviceFenceMapper.removeById(id);
    }

    @Override
    public boolean modifyByid(TFenceDef tFenceDef) {
        return deviceFenceMapper.modifyByid(tFenceDef) == 1;
    }

    @Override
    public boolean saves(List<TFenceDef> tFenceDefs) {
        tFenceDefs = tFenceDefs.stream()
                .filter(x -> !(x.getFence_type() == null || x.getCreate_by() == null || x.getStatus() == null || x.getArea_id() == null))
                .map(x -> {
                    x.setCreate_date(new Date());
                    return x;
                })
                .collect(Collectors.toList());
        if (tFenceDefs != null && tFenceDefs.size() > 0)
            return deviceFenceMapper.saves(tFenceDefs) == tFenceDefs.size();
        throw new RuntimeException();
    }

    @Override
    public List<FcnceRefFenceDetail> listDetail(Integer userId) {
        return deviceFenceMapper.findFencesInfoList(userId, bizService.orgIds(userId));
    }

    @Override
    public boolean deleteDetail(Integer id) {
        List<TFenceDetail> tFenceDetails = deviceFenceMapper.findDetailsByid(id);
        if (tFenceDetails != null && tFenceDetails.size() > 0) {
            List<Integer> ids = tFenceDetails.stream()
                    .map(x -> x.getId())
                    .collect(Collectors.toList());
            return deviceFenceMapper.removeDetailById(id) == tFenceDetails.size();
        }
        throw new RuntimeException();
    }

    @Override
    public boolean saveDetails(List<FcnceRefFenceDetail> tFenceDetails) {
        if (tFenceDetails != null && tFenceDetails.size() > 0) {
            //保存围栏信息
            boolean bool = this.saves(
                    tFenceDetails.stream()
                            .map(x -> x.gettFenceDef())
                            .collect(Collectors.toList())
            );
            if (bool) {
                tFenceDetails.stream()
                        .map(x -> x.gettFenceDetail())
                        .forEach(y -> {
                            List<TFenceDetail> list = y.stream()
                                    .map(x -> {//时间处理
                                        x.setCreateDate(new Date());
                                        return x;
                                    }).collect(Collectors.toList());
                            //保存围栏详情
                            if (deviceFenceMapper.saveDetails(list) != list.size()) {
                                //保存失败
                                throw new RuntimeException();
                            }
                        });
                return true;
            }
        }
        throw new RuntimeException();
    }

    @Override
    public boolean modefyDetail(TFenceDetail tFenceDetail) {
        return deviceFenceMapper.modefyDetail(tFenceDetail) == 1;
    }

    public PageInfo<FcnceRefFenceDetail> searchFenceList(String keyWord, Integer userId, ReqPage reqPage) {
        List<Integer> orgIds = bizService.orgIds(userId);
        PageHelper.offsetPage(reqPage.getPageNum(), reqPage.getPageSize());
        List<FcnceRefFenceDetail> fcnceRefFenceDetails = deviceFenceMapper.findFcnceRefFenceDetailByUserId(keyWord, userId, orgIds);

        //TODO
        return new PageInfo<FcnceRefFenceDetail>(fcnceRefFenceDetails);
    }

    @Override
    public FcnceRefFenceDetail findfenRefDetailsByid(Integer fenceId) {
        return deviceFenceMapper.findfenRefDetailsByid(fenceId);
    }


    private void tree(List<FcnceRefFenceDetail> list){
        //结果
        FcnceRefFenceDetailChile result = new FcnceRefFenceDetailChile();

        long count = list.stream()
                .filter(x -> x.getOrgInfo().getType() == 0)//省级
                .count();
        if(count<1){
            count = list.stream()
                    .filter(x->x.getOrgInfo().getType()==1)//州级
            .count();
            if(count<1){
                count = list.stream()
                        .filter(x->x.getOrgInfo().getType()==2)//县级
                .count();
                if(count<1){
                    list.stream()
                            .filter(x->x.getOrgInfo().getType()==3);
                }
            }
        }else{
//            result.setFcnceRefFenceDetails();
        }

    }

    @Override
    public List<fenceDetailVO> findFenceList(HashMap<String, Object> params) {
        //查询列表数据
        UserDO user = ShiroUtils.getUser();
        if(user.getRoles().get(0).getType()==2){
            params.put("operator",user.getUserId().intValue());
        }else{
            List<OrgInfo> orgList=orgInfoService.orgList();
            List<Integer> res=new ArrayList<>();
            if(StringUtils.isEmpty(params.get("orgId"))) {
                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
            }else{
                res= OrgUtils.orgReverse(orgList, Integer.parseInt(params.get("orgId").toString()), res);
            }
            params.put("orgList",res);
        }
        List<fenceDetailVO> fenceList = deviceFenceMapper.findFenceList(params);
        return fenceList;
    }

    /**
     * 根据犬id查找电子围栏
     */
	@Override
	public fenceDetailVO findFenceByDogId(Integer id) {
		return deviceFenceMapper.findFenceByDogId(id);
	}

    

}
