/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: NectletChangeServiceImpl
 * Author:   Administrator
 * Date:     2018/9/28 17:48
 * Description: 项圈变更service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.service.impl;

import com.dogmanager.VO.NectletChangeVO;
import com.dogmanager.dao.TNeckletChangeMapper;
import com.dogmanager.service.NectletChangeService;
import com.entities.AreaInfo;
import com.entities.OrgInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.AreaInfoService;
import com.utils.OrgUtils;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈项圈变更service〉
 *
 * @author Administrator
 * @create 2018/9/28
 * @since 1.0.0
 */
@Service
public class NectletChangeServiceImpl implements NectletChangeService{

    @Autowired
    private TNeckletChangeMapper neckletChangeMapper;
    @Autowired
    private OrgInfoDao orgInfoDao;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private AreaInfoDao areaInfoDao;

    /**
     * 列表
     * */
    @Override
    public PageInfo<NectletChangeVO> findTNeckletChangeList(HashMap<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        int type=user.getRoles().get(0).getType();
        if(type==2){
            map.put("operator",ShiroUtils.getUserId());
        }else{
            List<OrgInfo> orgList=orgInfoDao.orgList();
            List<Integer> res=new ArrayList<>();
            if(StringUtils.isEmpty(map.get("orgId"))){
                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
            }else {
                res= OrgUtils.orgReverse(orgList, Integer.parseInt(map.get("orgId").toString()), res);
            }
            map.put("orgList",res);//组织集合
        }
        int pageNum=Integer.parseInt(map.get("pageNum").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        Object langType = ShiroUtils.getSubjct().getSession().getAttribute("type");
        map.put("langType",langType);
        List<NectletChangeVO> tNeckletChangeList = neckletChangeMapper.findTNeckletChangeList(map);
        List<AreaInfo> AreaInfoList = areaInfoDao.getList();
        for(NectletChangeVO tc:tNeckletChangeList){
            if(!StringUtils.isEmpty(tc.getTown())){
                tc.setTown(areaInfoService.findAreas(Integer.parseInt(tc.getTown()),AreaInfoList));
            }
        }
        PageInfo<NectletChangeVO> pageInfo = new PageInfo<>(tNeckletChangeList);
        return pageInfo;
    }

    /**
     * 详情
     * */
    @Override
    public NectletChangeVO findOne(Integer Id) {
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        Map<String,Object> map =new HashMap<>();
        map.put("langType",type);
        map.put("id",Id);
//        NectletChangeVO neckletChange = neckletChangeMapper.selectByPrimaryKey(Id);
        NectletChangeVO neckletChange = neckletChangeMapper.selectByPrimaryKeyI18N(map);
        if(!StringUtils.isEmpty(neckletChange.getTown())){
            List<AreaInfo> AreaInfoList = areaInfoDao.getList();
            neckletChange.setTown(areaInfoService.findAreas(Integer.parseInt(neckletChange.getTown()),AreaInfoList));
        }
        return neckletChange;
    }
}
