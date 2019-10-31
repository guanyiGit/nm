/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DogInfoServiceImpl
 * Author:   Administrator
 * Date:     2018/9/27 15:13
 * Description: 犬只管理service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.service.impl;

import com.dogmanager.VO.Antiepidemic;
import com.dogmanager.VO.DogInfoVO;
import com.dogmanager.VO.TProtectorVO;
import com.dogmanager.dao.TDogInfoMapper;
import com.dogmanager.dao.TNeckletChangeMapper;
import com.dogmanager.dao.TOwnerChangeMapper;
import com.dogmanager.enmus.DeviceStatusEnum;
import com.dogmanager.enmus.DogStatusEnum;
import com.dogmanager.service.DogInfoService;
import com.dogmanager.utils.DogResult;
import com.entities.*;
import com.epmanagement.service.ManureDisposalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.AreaInfoService;
import com.statisanalysis.wx.dao.AntieCountDao;
import com.utils.FileUpload;
import com.utils.KeyUtil;
import com.utils.OrgUtils;
import com.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈犬只管理service〉
 *
 * @author Administrator
 * @create 2018/9/27
 * @since 1.0.0
 */
@Service
public class DogInfoServiceImpl implements DogInfoService {

    private Logger logger = LoggerFactory.getLogger(DogInfoServiceImpl.class);

    @Autowired
    private TDogInfoMapper dogInfoMapper;
    @Autowired
    private TOwnerChangeMapper ownerChangeMapper;
    @Autowired
    private TNeckletChangeMapper neckletChangeMapper;
    @Autowired
    private OrgInfoDao orgInfoDao;

    @Autowired
    private AreaInfoDao areaInfoDao;

    @Autowired
    private AreaInfoService areaInfoService;

    @Autowired
    private AntieCountDao antieCountDao;

    @Autowired
    private ManureDisposalService manureDisposalService;


    @Override
    public DeviceInfo findDeviceByNo(String deviceNo) {
        return dogInfoMapper.findDeviceByNo(deviceNo);
    }

    @Override
    public boolean checkRepeated(String deviceNo) {
        TDeviceRefDog tDeviceRefDog = dogInfoMapper.checkDevice(deviceNo);
        if(tDeviceRefDog == null) {
            return false;   //false该项圈没有绑定(可以使用)
        }
        Date expireDate = tDeviceRefDog.getExpireDate();
        if(expireDate == null) {
            return true;    //true 该项圈已被使用且没有过期（没有失效时间默认没有失效）
        }
        Date today = new Date();
        if(today.before(expireDate)) {  //today < expireDate
            return true;    //true 该项圈已被使用且没有过期
        }else {
            return false;   //该项圈已经被使用过，但是已过失效时间，可重复使用
        }
    }

    /**
     * 检查设备是否已被绑定
     * @param deviceNo
     * @return
     */
    @Override
    public Boolean checkDevice(String deviceNo) {
        TDeviceRefDog deviceRefDog =  dogInfoMapper.checkDevice(deviceNo);
        if (deviceRefDog != null){
            return  true;
        }else{
            return false;
        }
    }

    /**
     * 查设备
     * */
    @Override
    public List<HashMap<String, Object>> findDevice() {
        return dogInfoMapper.findDevice();
    }
    /**
     * 设备编号查设备id
     * */
    @Override
    public Integer findDeviceIdByDeviceNo(String deviceNo) {
        return dogInfoMapper.findDeviceIdByDeviceNo(deviceNo);
    }

    //主要用于判断犬只是否存在
    @Override
    public TDogInfo selectByPrimaryKey(String traceId) {
        return dogInfoMapper.selectByPrimaryKey(traceId,dogInfoMapper.findProId(ShiroUtils.getUserId().intValue()));
    }


    @Override
    public List<HashMap<String, Object>> findProtector3() {
        List<HashMap<String, Object>> list = dogInfoMapper.findProtector3(ShiroUtils.getUser().getDeptId().intValue());
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",0);
        map.put("text","全部");
        list.add(0,map);
        return list;
    }



    @Override
    public List<TProtectorVO> findProtector2() {
        Long orgId = ShiroUtils.getUser().getDeptId();
        List<TProtectorVO > list = dogInfoMapper.findProtector2(orgId.intValue());
        TProtectorVO protector = new TProtectorVO();
        protector.setText("全部");
        protector.setUserId(0);
        protector.setId(0);//手动设置为null
        list.add(0,protector);
        return list;
    }

    /**
     * 查设备绑定
     * */
    @Override
    public TDeviceRefDog selectByTraceId(String traceId) {
        return dogInfoMapper.selectByTraceId(traceId);
    }

    /**
     * 犬只绑定设备
     * */
    @Override
    @Transactional
    public void insertDogDeviceNo(TDeviceRefDog deviceRefDog) {
        if(StringUtils.isEmpty(deviceRefDog.getDeviceNo())){
            logger.error("【犬只绑定设备】设备号为空；deviceNo:",deviceRefDog.getDeviceNo());
            throw new RuntimeException();
        }
        if(StringUtils.isEmpty(deviceRefDog.getTraceId())){
            logger.error("【犬只绑定设备】溯源号为空；traceId:",deviceRefDog.getTraceId());
            throw new RuntimeException();
        }
        String traceId = deviceRefDog.getTraceId().trim();
        String deviceNo = deviceRefDog.getDeviceNo().trim();
        TDogInfo dogInfo = dogInfoMapper.selectByPrimaryKey(traceId,dogInfoMapper.findProId(ShiroUtils.getUserId().intValue()));
        if(dogInfo==null){
            logger.error("【犬只绑定设备】犬只不存在；traceId:",traceId);
            throw new RuntimeException();
        }
        DeviceInfo deviceInfo = dogInfoMapper.findDeviceByNo(deviceNo);
        if(deviceInfo == null) {
            logger.error("【犬只绑定设备】该设备不存在；deviceNo:",deviceNo);
            throw new RuntimeException();
        }
        switch (deviceInfo.getStatus()){
            case 1:
                logger.error("【犬只绑定设备】该设备已被绑定;deviceNo:",deviceNo);
                throw new RuntimeException();
            case 2:
                logger.error("【犬只绑定设备】该设备已丢失;deviceNo:",deviceNo);
                throw new RuntimeException();
            case 3:
                logger.error("【犬只绑定设备】该设备已损坏;deviceNo:",deviceNo);
                throw new RuntimeException();
        }
        deviceRefDog.setStartDate(new Date());
        deviceRefDog.setTraceId(traceId);
        deviceRefDog.setDeviceNo(deviceNo);
        dogInfoMapper.insertDogDeviceNo(deviceRefDog);
        //激活设备
        dogInfoMapper.updateDeviceStatus(DeviceStatusEnum.DEVICE_STATUS_ACTIVATED.getCode(),deviceNo);
    }

    /**
     * 新增犬只
     * */
    @Override
    @Transactional
    public int saveDogInfo(DogInfoVO dogInfoVO) {
        String traceId = KeyUtil.getUniqueKey();
        UserDO user = ShiroUtils.getUser();
        TDogInfo dogInfo = new TDogInfo();
        BeanUtils.copyProperties(dogInfoVO,dogInfo);
        dogInfo.setOrgId(user.getDeptId().intValue());
        dogInfo.setAreaId(user.getAreaId().intValue());
        dogInfo.setCreateDate(new Date());
        dogInfo.setUpdateDate(new Date());
        dogInfo.setTraceId(traceId);
        dogInfo.setStatus(DogStatusEnum.DOG_STATUS_NORMAL.getCode());
        Integer protector = dogInfoMapper.findProId(user.getUserId().intValue());
        if(protector ==null){
            logger.error("【新增犬只】根据用户查询防疫员失败;userId:",user.getUserId());
            throw  new RuntimeException();
        }
        dogInfo.setProtector(protector);
        dogInfoMapper.insert(dogInfo);
        //如果设备号不为空，绑定设备
        if(!StringUtils.isEmpty(dogInfoVO.getDeviceNo())){
            DeviceInfo deviceInfo = dogInfoMapper.findDeviceByNo(dogInfoVO.getDeviceNo().trim());
            if(deviceInfo == null) {
                logger.error("【犬只绑定设备】该设备不存在");
                throw new RuntimeException();
            }
            switch (deviceInfo.getStatus()){
                case 1:
                    logger.error("【犬只绑定设备】该设备已被绑定");
                    throw new RuntimeException();
                case 2:
                    logger.error("【犬只绑定设备】该设备已丢失");
                    throw new RuntimeException();
                case 3:
                    logger.error("【犬只绑定设备】该设备已损坏");
                    throw new RuntimeException();
            }
            TDeviceRefDog deviceRefDog = new TDeviceRefDog();
            deviceRefDog.setDeviceNo(dogInfoVO.getDeviceNo().trim());
            deviceRefDog.setTraceId(traceId);
            deviceRefDog.setStartDate(new Date());
            dogInfoMapper.insertDogDeviceNo(deviceRefDog);
            //激活设备
            dogInfoMapper.updateDeviceStatus(DeviceStatusEnum.DEVICE_STATUS_ACTIVATED.getCode(),dogInfoVO.getDeviceNo().trim());
        }
        return dogInfo.getId();
    }

    //删除图片
    @Override
    @Transactional
    public int deletePic(Integer fid) {
        TMediaInfo mediaInfo = dogInfoMapper.findPicOne(fid);
        String url = mediaInfo.getUrl();
        String suburl = url.substring(url.indexOf("group"));
        String url_group_name = suburl.split("/")[0];
        String url_remote_filename = suburl.substring(suburl.indexOf("/")+1);
        FileUpload.delete(url_group_name, url_remote_filename);
        String thumbnailUrl = mediaInfo.getThumbnailUrl();
        String subThumbnailUrl = thumbnailUrl.substring(thumbnailUrl.indexOf("group"));
        String thu_group_name = subThumbnailUrl.split("/")[0];
        String thu_remote_filename = subThumbnailUrl.substring(subThumbnailUrl.indexOf("/")+1);
        FileUpload.delete(thu_group_name, thu_remote_filename);
        return dogInfoMapper.deletePic(fid);
    }
    @Override
    public TMediaInfo findPicOne(Integer fid) {
        return dogInfoMapper.findPicOne(fid);
    }
    @Override
    public List<HashMap<String, Object>> findPicList(Integer id, Integer type) {
        return dogInfoMapper.findPicList(id,type);
    }

    @Override
    public List<HashMap<String, Object>> findVideoList(Integer id, Integer type) {
        return dogInfoMapper.findVideoList(id,type);
    }



    /**
     * 查看详情
     * */
    @Override
    public HashMap<String,Object> findOne(String traceId) {
        if(StringUtils.isEmpty(traceId)){
            logger.error("【查看犬只详情】 溯源号为空");
            throw new RuntimeException();
        }
        Map<String,Object> param = new HashMap<>();
        param.put("traceId",traceId);
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        param.put("langType",type);
        DogInfoVO dogInfoVO = dogInfoMapper.findDogInfoByDogIdI18N(param);
        if(!StringUtils.isEmpty(dogInfoVO.getTown())){
            List<AreaInfo> AreaInfoList = areaInfoDao.getList();
            dogInfoVO.setTown(areaInfoService.findAreas(Integer.parseInt(dogInfoVO.getTown()),AreaInfoList));
        }
        List<HashMap<String, Object>> dogPicList =null;
        if(!StringUtils.isEmpty(dogInfoVO.getId())){
            dogPicList = dogInfoMapper.findPicList(dogInfoVO.getId(),0);//犬只图片
        }
        List<Antiepidemic> antiepidemicList = dogInfoMapper.findAntiepidemicList(traceId);
        List<Antiepidemic> chunfangList = new ArrayList<>();
        List<Antiepidemic> qiufangList = new ArrayList<>();
        List<Antiepidemic> yueyueList = new ArrayList<>();
        if(antiepidemicList.size()>0){
            for(Antiepidemic apc : antiepidemicList){
                if(apc.getType()==0){
                    chunfangList.add(apc);
                }else if(apc.getType()==1){
                    qiufangList.add(apc);
                }else {
                    yueyueList.add(apc);
                }
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("dogInfo",dogInfoVO);
        map.put("chunfang",chunfangList);
        map.put("qiufang",qiufangList);
        map.put("yueyue",yueyueList);
        map.put("picList",dogPicList);
        return map;
    }




    /**
     * WX查看详情
     * */
    @Override
    public DogResult WXfindOne(String traceId,String deviceNo) {
        if(!StringUtils.isEmpty(deviceNo)){
            TDeviceRefDog deviceRefDog = dogInfoMapper.checkDevice(deviceNo);
            if(deviceRefDog==null){
                logger.error("【WX查看犬只详情】 设备号查询溯源号出错");
                return DogResult.build(100,"该设备没有绑定犬只");
            }
            traceId=deviceRefDog.getTraceId();
        }
        if(StringUtils.isEmpty(traceId)){
            logger.error("【WX查看犬只详情】 溯源号为空");
            throw new RuntimeException();
        }
        Map<String,Object> param = new HashMap<>();
        param.put("traceId",traceId);
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        param.put("langType",type);
        DogInfoVO dogInfoVO = dogInfoMapper.findDogInfoByDogIdI18N(param);
        if(!StringUtils.isEmpty(dogInfoVO.getTown())){
            List<AreaInfo> AreaInfoList = areaInfoDao.getList();
            dogInfoVO.setTown(areaInfoService.findAreas(Integer.parseInt(dogInfoVO.getTown()),AreaInfoList));
        }
        List<HashMap<String, Object>> dogPicList =null;
        if(!StringUtils.isEmpty(dogInfoVO.getId())){
             dogPicList = dogInfoMapper.findPicList(dogInfoVO.getId(),0);//犬只图片
        }
        List<HashMap<String, Object>> ownerPicList =null;
        if(!StringUtils.isEmpty(dogInfoVO.getOwner())){
             ownerPicList = dogInfoMapper.findPicList(dogInfoVO.getOwner(),8);//犬主图片
        }
        List<Antiepidemic> antiepidemicList = dogInfoMapper.findAntiepidemicList(traceId);//防疫信息
        List<HashMap<String, Object>> manureList = dogInfoMapper.findManureList(traceId);//犬粪信息

        HashMap<String, Object> map = new HashMap<>();
        map.put("dogInfo",dogInfoVO);
        map.put("antiepidemic",antiepidemicList);
        map.put("dogPicList",dogPicList);
        map.put("ownerPicList",ownerPicList);
        map.put("manureList",manureList);
        return DogResult.ok(map);
    }



    /**
     * 修改犬只
     * */
    @Override
    public void updateDogInfo(TDogInfo dogInfo) {
        dogInfo.setUpdateDate(new Date());
        dogInfoMapper.updateByPrimaryKey(dogInfo);
    }

    /**
     * 删除犬只
     * */
    @Override
    public void deleteDogInfo(Integer DogId) {
        dogInfoMapper.deleteByPrimaryKey(DogId);
        //删除关联关系？
        //设备怎么办？
        //设备数据怎么办？
    }

    /**
     * 项圈更换
     * */
    @Override
    @Transactional
    public void saveNectletChange(TNeckletChange neckletChange) {
        if(StringUtils.isEmpty(neckletChange.getTraceId())){
            logger.error("【项圈更换】 溯源号为空；traceId:",neckletChange.getTraceId());
            throw  new RuntimeException();
        }
        if(StringUtils.isEmpty(neckletChange.getNewNeckletNo())){
            logger.error("【项圈更换】 新设备号为空；newNeckletNo:",neckletChange.getNewNeckletNo());
            throw  new RuntimeException();
        } if(StringUtils.isEmpty(neckletChange.getOldNeckletNo())){
            logger.error("【项圈更换】 旧设备号为空；oldNeckletNo:",neckletChange.getOldNeckletNo());
            throw  new RuntimeException();
        }
        String traceId = neckletChange.getTraceId().trim();
        String newNeckletNo = neckletChange.getNewNeckletNo().trim();
        String oldNeckletNo = neckletChange.getOldNeckletNo().trim();
        TDogInfo dogInfo = dogInfoMapper.selectByPrimaryKey(traceId,dogInfoMapper.findProId(ShiroUtils.getUserId().intValue()));
        if(dogInfo==null){
            logger.error("【项圈更换】犬只不存在；traceId:",traceId);
            throw new RuntimeException();
        }
        DeviceInfo deviceInfo = dogInfoMapper.findDeviceByNo(newNeckletNo);
        if(deviceInfo == null) {
            logger.error("【项圈更换】该设备不存在；deviceNo:",newNeckletNo);
            throw new RuntimeException();
        }
        switch (deviceInfo.getStatus()){
            case 1:
                logger.error("【项圈更换】该设备已被绑定;deviceNo:",newNeckletNo);
                throw new RuntimeException();
            case 2:
                logger.error("【项圈更换】该设备已丢失;deviceNo:",newNeckletNo);
                throw new RuntimeException();
            case 3:
                logger.error("【项圈更换】该设备已损坏;deviceNo:",newNeckletNo);
                throw new RuntimeException();
        }
        //1.添加更换记录
        neckletChange.setCreatetime(new Date());
        neckletChange.setUpdatetime(new Date());
        neckletChange.setOrgId(ShiroUtils.getUser().getDeptId().intValue());
        neckletChange.setOperator(ShiroUtils.getUserId().intValue());
        neckletChange.setOldNeckletNo(oldNeckletNo);
        neckletChange.setNewNeckletNo(newNeckletNo);
        neckletChangeMapper.insert(neckletChange);
        //2.更新犬只与设备关联表
        TDeviceRefDog deviceRefDog = new TDeviceRefDog();
        deviceRefDog.setTraceId(traceId);
        deviceRefDog.setDeviceNo(newNeckletNo);
        deviceRefDog.setStartDate(new Date());
        neckletChangeMapper.updateDogRefDevice(deviceRefDog);
        //3.激活设备
        dogInfoMapper.updateDeviceStatus(DeviceStatusEnum.DEVICE_STATUS_ACTIVATED.getCode(),newNeckletNo);
        //4.更改旧设备状态
        dogInfoMapper.updateDeviceStatus(neckletChange.getChangeReasons(),oldNeckletNo);
    }

    /**
     * 查询品种
     * */
    @Override
    public List<HashMap<String, Object>> findBreed() {
        Map<String, Object> map = new HashMap<>();
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        map.put("langType",type);
        return dogInfoMapper.findBreed(map);
    }
    /**
     * 查询电子围栏
     * */
    @Override
    public List<HashMap<String, Object>> findFence(Integer protector) {
        return dogInfoMapper.findFence(ShiroUtils.getUserId().intValue());
    }

    /**
     * 防疫员查犬主根据userId
     * */
    @Override
    public List<HashMap<String, Object>> findDogOwner(Integer protector) {
        return dogInfoMapper.findDogOwner(ShiroUtils.getUserId().intValue());
    }

    /**
     * 犬主更换
     * */
    @Override
    @Transactional
    public void saveOwnerChange(TOwnerChange ownerChange) {
        //1.添加更换记录
        ownerChange.setCreatetime(new Date());
        ownerChange.setUpdatetime(new Date());
        ownerChange.setOperator(ShiroUtils.getUserId().intValue());
        ownerChange.setOrgId(ShiroUtils.getUser().getDeptId().intValue());
        ownerChangeMapper.insert(ownerChange);
        //2.更换犬主
        ownerChangeMapper.updateDogOwner(ownerChange.getNewOwner(),ownerChange.getTraceId());
    }

    /**
     * 查看犬只列表
     * */
    @Override
    public PageInfo<DogInfoVO> findDogInfoList(HashMap<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type==2){
            Integer proId = dogInfoMapper.findProId(user.getUserId().intValue());
            if(proId==null){
                logger.error("【查看犬只列表】 该用户不是防疫员；userId:",user.getUserId());
                throw  new RuntimeException();
            }
            map.put("protector",proId);//防疫员id
        }else{
            List<OrgInfo> orgList=orgInfoDao.orgList();
            List<Integer>res=new ArrayList<>();
            if(StringUtils.isEmpty(map.get("orgId"))){
                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
            }else {
                res= OrgUtils.orgReverse(orgList, Integer.parseInt(map.get("orgId").toString()), res);
            }
            map.put("orgList",res);//组织集合
        }
        int pageNum=Integer.parseInt(map.get("pageNum").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum, pageSize);
        List<DogInfoVO> list = dogInfoMapper.findDogInfoList(map);
        List<AreaInfo> AreaInfoList = areaInfoDao.getList();
        for (DogInfoVO dv:list){
            if(!StringUtils.isEmpty(dv.getTown())){
                dv.setTown(areaInfoService.findAreas(Integer.parseInt(dv.getTown()),AreaInfoList));
            }
        }
        PageInfo<DogInfoVO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    /**
     * wx查看犬只是否防疫列表
     * */
    @Override
    public PageInfo<DogInfoVO> wxfindDogInfoListIsAnt(HashMap<String, Object> map) throws Exception {
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type==2){
            Integer proId = dogInfoMapper.findProId(user.getUserId().intValue());
            if(proId==null){
                logger.error("【查看犬只列表】 该用户不是防疫员；userId:",user.getUserId());
                throw  new RuntimeException();
            }
            map.put("protector",proId);//防疫员id
        }else{
            List<OrgInfo> orgList=orgInfoDao.orgList();
            List<Integer> res = new ArrayList<>();
            if (!StringUtils.isEmpty(map.get("areaId")) && StringUtils.isEmpty(map.get("orgId"))) {
                List<Integer>  orgIds =   antieCountDao.getOrgIdByAreaId(Integer.parseInt(map.get("areaId").toString()));
                for (Integer orgId: orgIds) {
                    OrgUtils.orgReverse(orgList, orgId, res);
                }
            }
            if(!StringUtils.isEmpty(map.get("orgId"))){
                res= OrgUtils.orgReverse(orgList, Integer.parseInt(map.get("orgId").toString()), res);
            }
            map.put("orgList",res);//组织集合
        }
        int pageNum=Integer.parseInt(map.get("pageNum").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());

        /*String nextMonth = DateUtils.getLastMonth(map.get("antiepidemicDate").toString(), 0, 1, 0);
        map.put("nextMonth",nextMonth);*/
        PageHelper.startPage(pageNum, pageSize);
        List<DogInfoVO> list = dogInfoMapper.wxfindDogInfoListIsAnt(map);
        List<AreaInfo> AreaInfoList = areaInfoDao.getList();
        for (DogInfoVO dv:list){
            if(!StringUtils.isEmpty(dv.getTown())){
                dv.setTown(areaInfoService.findAreas(Integer.parseInt(dv.getTown()),AreaInfoList));
            }
        }
        PageInfo<DogInfoVO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * wx查看犬只列表
     * */
    @Override
    public PageInfo<DogInfoVO> wxfindDogInfoList(HashMap<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type==2){
            Integer proId = dogInfoMapper.findProId(user.getUserId().intValue());
            if(proId==null){
                logger.error("【查看犬只列表】 该用户不是防疫员；userId:",user.getUserId());
                throw  new RuntimeException();
            }
            map.put("protector",proId);//防疫员id
        }else{
            List<OrgInfo> orgList=orgInfoDao.orgList();
            List<Integer>res=new ArrayList<>();
            if(StringUtils.isEmpty(map.get("orgId"))){
                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
            }else {
                res= OrgUtils.orgReverse(orgList, Integer.parseInt(map.get("orgId").toString()), res);
            }
            map.put("orgList",res);//组织集合
        }
        int pageNum=Integer.parseInt(map.get("pageNum").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum, pageSize);
        List<DogInfoVO> list = dogInfoMapper.wxfindDogInfoList(map);
        List<AreaInfo> AreaInfoList = areaInfoDao.getList();
        for (DogInfoVO dv:list){
            Date antiepidemicDate = dv.getAntiepidemicDate();
            if(antiepidemicDate!=null){
                long nowTime = new Date().getTime();
                long antiepidemicTime = antiepidemicDate.getTime();
                long day=(nowTime-antiepidemicTime)/(24*60*60*1000);
                if(day<30*Integer.parseInt(dv.getAntiepidemicPeriod())){
                    dv.setAnt(true);
                }
            }
            if(!StringUtils.isEmpty(dv.getTown())){
                dv.setTown(areaInfoService.findAreas(Integer.parseInt(dv.getTown()),AreaInfoList));
            }
        }
        PageInfo<DogInfoVO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 查看绑定项圈的犬只列表
     * */
    @Override
    public PageInfo<DogInfoVO> findBindDogInfoList(HashMap<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type==2){
            Integer proId = dogInfoMapper.findProId(user.getUserId().intValue());
            if(proId==null){
                logger.error("【查看犬只列表】 该用户不是防疫员；userId:",user.getUserId());
                throw  new RuntimeException();
            }
            map.put("protector",proId);//防疫员id
        }else{
            List<OrgInfo> orgList=orgInfoDao.orgList();
            List<Integer>res=new ArrayList<>();
            if(StringUtils.isEmpty(map.get("orgId"))){
                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
            }else {
                res= OrgUtils.orgReverse(orgList, Integer.parseInt(map.get("orgId").toString()), res);
            }
            map.put("orgList",res);//组织集合
        }
        int pageNum=Integer.parseInt(map.get("pageNum").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum, pageSize);
        List<DogInfoVO> list = dogInfoMapper.findBindDogInfoList(map);
        List<AreaInfo> AreaInfoList = areaInfoDao.getList();
        for (DogInfoVO dv:list){
            if(!StringUtils.isEmpty(dv.getTown())){
                dv.setTown(areaInfoService.findAreas(Integer.parseInt(dv.getTown()),AreaInfoList));
            }
        }
        PageInfo<DogInfoVO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Map<String,Object> getOwnerNameAndDogName(String treceId) {
        return dogInfoMapper.getOwnerNameAndDogName(treceId);
    }

    @Override
    public List<Map<String,Object>>  getTraceIdByOwnerNameOrDogName(String string,Integer protectorId) {
        return dogInfoMapper.getTraceIdByOwnerNameOrDogName(string,protectorId);
    }

}

