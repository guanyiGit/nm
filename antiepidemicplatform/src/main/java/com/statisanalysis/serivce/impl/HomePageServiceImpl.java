package com.statisanalysis.serivce.impl;

import com.entities.Antiepidemic;
import com.entities.AreaInfo;
import com.entities.OrgInfo;
import com.entities.TMsg;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.statisanalysis.dao.DogAmountDao;
import com.statisanalysis.dao.HomePageDao;
import com.statisanalysis.entity.Data;
import com.statisanalysis.entity.DogVO;
import com.statisanalysis.serivce.IHomePageService;
import com.statisanalysis.utils.DateUtils;
import com.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/5 17:24
 * @Version 1.0
 */
@Service
public class HomePageServiceImpl implements IHomePageService {
    @Autowired
    private HomePageDao homePageDao;
    @Autowired
    private DogAmountDao dogAmountDao;
    @Autowired
    private OrgInfoDao orgInfoDao;
    @Autowired
    private AreaInfoDao areaInfoDao;
    @Autowired
    private OrgInfoDao sysDeptMapper;
    @Autowired
    private MessageSource messageSource;
    private Logger logger = LoggerFactory.getLogger(HomePageServiceImpl.class);

    @Override
    public List<TMsg> getAllMsgs(Map<String, Object> map) {
        logger.info("HomePageServiceImpl.getAllInfo()|map = {}",map);
        List<TMsg> msgs = homePageDao.findAllMsgs(map);
        logger.info("HomePageServiceImpl.getAllInfo()|msgs={}",msgs);
        return msgs;
    }

    @Override
    public Integer getAntiepidemicTimes(Map<String, Object> map) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String currMonth = sdf.format(date);
        String lastMonth = DateUtils.getLastMonth(currMonth, 0, -1, 0);
        map.put("currMonth",currMonth);
        map.put("lastMonth",lastMonth);
        logger.info("HomePageServiceImpl.getAntiepidemicTimes|map={}",map);
        //TODO 获取用户的角色roleName
        //获取当前用户org_id
//        Integer orgId = 1;  //从登陆信息中获取orgId
//        List<OrgInfo> orgInfos = orgInfoDao.orgList();
//        int i = Integer.parseInt(ShiroUtils.getUserId().toString());
//        List<Integer> lists = orgUtils.orgReverse(orgInfos, Integer.parseInt(ShiroUtils.getUser().getDeptId().toString()), new ArrayList<>());
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            map.put("userId",user.getUserId());
        }else {
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(ShiroUtils.getUser().getDeptId().toString()), new ArrayList<>());
            map.put("lists",lists);
        }
        Integer times = homePageDao.findAntiepidmicTimes(map);
        return times;
    }

    @Override
    public Integer getAddedDogs(Map<String, Object> map) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String currMonth = sdf.format(date);
        String lastMonth = DateUtils.getLastMonth(currMonth, 0, -1, 0);
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            map.put("userId",user.getUserId());
        }else {
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(ShiroUtils.getUser().getDeptId().toString()), new ArrayList<>());
            map.put("lists",lists);
        }

        map.put("currMonth",currMonth);
        map.put("lastMonth",lastMonth);
        Integer dogsNum = homePageDao.findAddedDogs(map);
        return dogsNum;
    }

    @Override
    public Map<String,List<Object>> getToptenDrug(Map<String, Object> map) throws Exception {
        Map<String,List<Object>> result = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String currMonth = sdf.format(date);
        String lastMonth = DateUtils.getLastMonth(currMonth, 0, -1, 0);
        map.put("currMonth",currMonth);
        map.put("lastMonth",lastMonth);
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            map.put("userId",user.getUserId());
        }else {
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(ShiroUtils.getUser().getDeptId().toString()), new ArrayList<>());
            map.put("lists",lists);
        }

        logger.info("HomePageServiceImpl.getAddedDogs|map={}",map);
        List<Data> topTenDrug = homePageDao.findTopTenDrug(map);
        List<Object> drugName = new ArrayList<>();
        List<Object> times = new ArrayList<>();
        for (Data data:
        topTenDrug) {
            drugName.add(data.getName());
            times.add(data.getValue());
        }
        result.put("drugName",drugName);
        result.put("times",times);
        logger.info("HomePageServiceImpl.getAddedDogs|result={}",result);
        return result;
    }

    @Override
    public Map<String,List<String>> getPerMonthTimes(Map<String, Object> map) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String currMonth = sdf.format(date);
        //当前月份往后一个月
        String endTime = DateUtils.getLastMonth(currMonth, 0, 1, 0);
        //一年前的月份
        String startTime = DateUtils.getLastMonth(currMonth, 0, -11, 0);
        List<String> monthBetween = DateUtils.getMonthBetween(startTime, currMonth);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        logger.info("HomePageServiceImpl.getPerMonthTimes()|map={}",map);
//        //角色为防疫员
//        //Integer protectorId = 1;
//        //角色为其他
//        List<Integer> lists = new ArrayList<>();
//        lists.add(20);
//        lists.add(21);
//        lists.add(22);
//        map.put("lists",lists);
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            map.put("userId",user.getUserId());
        }else {
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(ShiroUtils.getUser().getDeptId().toString()), new ArrayList<>());
            map.put("lists",lists);
        }

        List<Antiepidemic> antiepidemicList = homePageDao.findPerMonthTimes(map);

        logger.info("HomePageServiceImpl.getPerMonthTimes()|antiepidemicList={}",antiepidemicList);
//        List<Data> list = new ArrayList<>();
        Map<String,List<String>> result = new HashMap<>();
        result.put("monthBetween",monthBetween);
        List<String> list = new ArrayList<>();
        for (String perMonth:
             monthBetween) {
            int res1 = DateUtils.getNumOfDate(perMonth);
            int num = 0;
            for (Antiepidemic antiepidemic:
                 antiepidemicList) {
                Date antiepidemicDate = antiepidemic.getAntiepidemicDate();
                String format = sdf.format(antiepidemicDate);
                int res2 = DateUtils.getNumOfDate(format);
                if(res1 == res2) {   //这次免疫属于当前年月
                    num++;
                }
            }
            list.add(String.valueOf(num));
        }
        result.put("nums",list);
        return result;
    }

    @Override
    public Map<String,List<String>> getChargeDogs(Map<String, Object> map) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String currMonth = sdf.format(date);
        //当前月份往后一个月
        String endTime = DateUtils.getLastMonth(currMonth, 0, 1, 0);
        //一年前的月份
        String startTime = DateUtils.getLastMonth(currMonth, 0, -11, 0);
        List<String> monthBetween = DateUtils.getMonthBetween(startTime, currMonth);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            map.put("userId",user.getUserId());
        }else {
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(ShiroUtils.getUser().getDeptId().toString()), new ArrayList<>());
            map.put("lists",lists);
        }
        List<DogVO> dogVOList = dogAmountDao.getDogsByTime(map);

        Map<String,List<String>> result = new HashMap<>();
        List<String> list = new ArrayList<>();
        result.put("monthBetween",monthBetween);
        for (String perMonth:
                monthBetween) {
            int res1 = DateUtils.getNumOfDate(perMonth);
            int num = 0;
            for(DogVO dog : dogVOList) {
                Date createDate = dog.getCreateDate();
                int res2 = DateUtils.getNumOfDate(sdf.format(createDate));
                Date handleTime = null; //可能是死亡时间、注销时间
                if(dog.getDealTime() != null) {
                    handleTime = dog.getDealTime();
                }
                if (dog.getCancelTime() != null) {
                    handleTime = dog.getCancelTime();
                }
                int res3 = Integer.MAX_VALUE;       //默认设置为最大，没有死亡时间
                if(handleTime != null) {
                    res3 = DateUtils.getNumOfDate(sdf.format(handleTime));
                }
                if(res2 <= res1 && res1 < res3) {
                    num++;
                }
            }
            list.add(String.valueOf(num));
        }
        result.put("nums",list);
        return result;
    }

    @Override
    public Integer getUnreadedTotal(Map<String, Object> map) {
        Long userId = ShiroUtils.getUserId();
        map.put("id",userId);
        return homePageDao.findUnreadTotal(map);
    }

    @Override
    public List<TMsg> getUnreadedMsgs(Map<String, Object> map) {
        Long userId = ShiroUtils.getUserId();
        map.put("id",userId);
        List<TMsg> msgs = homePageDao.findUnreadMsgs(map);
//        Locale locale = (Locale)ShiroUtils.getSubjct().getSession().getAttribute("localLang");
        Locale locale = (Locale)ShiroUtils.getSubjct().getSession().getAttribute("localLang");
        for (TMsg m:msgs){
            if(m.getType()==0){
                String title = messageSource.getMessage("primaryPage.outFenceWarn", null, locale);
                String content = messageSource.getMessage("primaryPage.dogOutFence", null, locale);
                m.setTitle(title);
                m.setContent(content);
            }
            if(m.getType()==1){//低电
                String title = messageSource.getMessage("primaryPage.lowPowerWarn", null, locale);

                String neckLetLowPower = messageSource.getMessage("primaryPage.neckLetLowPower", null, locale);
                String warnningTime = messageSource.getMessage("primaryPage.warnningTime", null, locale);
                String lowPower = messageSource.getMessage("primaryPage.lowPower", null, locale);
                String traceId = messageSource.getMessage("dogManage.traceId", null, locale);
                String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
                String dog = messageSource.getMessage("dogManage.dogName", null, locale);
                String dbContent = m.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",neckLetLowPower);
                dbContent =StringUtils.replaceOnce(dbContent,"$",warnningTime);
                dbContent =StringUtils.replaceOnce(dbContent,"$",lowPower);
                dbContent =StringUtils.replaceOnce(dbContent,"$",traceId);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
                m.setTitle(title);
                m.setContent(dbContent);
            }
            if(m.getType()==2){//春防
                String title = messageSource.getMessage("dogManage.springAntiepidemic", null, locale);
                String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
                String dog = messageSource.getMessage("dogManage.dogName", null, locale);
                String springAntiepidemicTime = messageSource.getMessage("primaryPage.springAntiepidemicTime", null, locale);
                String dbContent = m.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
                dbContent =StringUtils.replaceOnce(dbContent,"$",springAntiepidemicTime);
                m.setTitle(title);
                m.setContent(dbContent);
            }
            if(m.getType()==3){//秋防
                String title = messageSource.getMessage("dogManage.autmnAntiepidemic", null, locale);
                String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
                String dog = messageSource.getMessage("dogManage.dogName", null, locale);
                String autumnAntiepidemicTime = messageSource.getMessage("primaryPage.autumnAntiepidemicTime", null, locale);
                String dbContent = m.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
                dbContent =StringUtils.replaceOnce(dbContent,"$",autumnAntiepidemicTime);
                m.setTitle(title);
                m.setContent(dbContent);
            }
            if(m.getType()==4){//月月投
                String title = messageSource.getMessage("dogManage.monthAntiepidemic", null, locale);
                String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
                String dog = messageSource.getMessage("dogManage.dogName", null, locale);
                String monthAntiepidemicTime = messageSource.getMessage("primaryPage.monthAntiepidemicTime", null, locale);
                String dbContent = m.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
                dbContent =StringUtils.replaceOnce(dbContent,"$",monthAntiepidemicTime);
                m.setTitle(title);
                m.setContent(dbContent);
            }
        }
        return msgs;
    }

    @Override
    public Integer getReadedTotal(Map<String, Object> map) {
        Long userId = ShiroUtils.getUserId();
        map.put("id",userId);
        return homePageDao.findReadedTotal(map);
    }

    @Override
    public List<TMsg> getReadedMsgs(Map<String, Object> map) {
        Long userId = ShiroUtils.getUserId();
        map.put("id",userId);
        List<TMsg> msgs = homePageDao.findReadMsgs(map);
        Locale locale = (Locale)ShiroUtils.getSubjct().getSession().getAttribute("localLang");
        for (TMsg m:msgs){
            if(m.getType()==0){
                String title = messageSource.getMessage("primaryPage.outFenceWarn", null, locale);
                String content = messageSource.getMessage("primaryPage.dogOutFence", null, locale);
                m.setTitle(title);
                m.setContent(content);
            }
            if(m.getType()==1){//低电
                String title = messageSource.getMessage("primaryPage.lowPowerWarn", null, locale);

                String neckLetLowPower = messageSource.getMessage("primaryPage.neckLetLowPower", null, locale);
                String warnningTime = messageSource.getMessage("primaryPage.warnningTime", null, locale);
                String lowPower = messageSource.getMessage("primaryPage.lowPower", null, locale);
                String traceId = messageSource.getMessage("dogManage.traceId", null, locale);
                String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
                String dog = messageSource.getMessage("dogManage.dogName", null, locale);
                String dbContent = m.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",neckLetLowPower);
                dbContent =StringUtils.replaceOnce(dbContent,"$",warnningTime);
                dbContent =StringUtils.replaceOnce(dbContent,"$",lowPower);
                dbContent =StringUtils.replaceOnce(dbContent,"$",traceId);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
                m.setTitle(title);
                m.setContent(dbContent);
            }
            if(m.getType()==2){//春防
                String title = messageSource.getMessage("dogManage.springAntiepidemic", null, locale);
                String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
                String dog = messageSource.getMessage("dogManage.dogName", null, locale);
                String springAntiepidemicTime = messageSource.getMessage("primaryPage.springAntiepidemicTime", null, locale);
                String dbContent = m.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
                dbContent =StringUtils.replaceOnce(dbContent,"$",springAntiepidemicTime);
                m.setTitle(title);
                m.setContent(dbContent);
            }
            if(m.getType()==3){//秋防
                String title = messageSource.getMessage("dogManage.autmnAntiepidemic", null, locale);
                String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
                String dog = messageSource.getMessage("dogManage.dogName", null, locale);
                String autumnAntiepidemicTime = messageSource.getMessage("primaryPage.autumnAntiepidemicTime", null, locale);
                String dbContent = m.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
                dbContent =StringUtils.replaceOnce(dbContent,"$",autumnAntiepidemicTime);
                m.setTitle(title);
                m.setContent(dbContent);
            }
            if(m.getType()==4){//月月投
                String title = messageSource.getMessage("dogManage.monthAntiepidemic", null, locale);
                String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
                String dog = messageSource.getMessage("dogManage.dogName", null, locale);
                String monthAntiepidemicTime = messageSource.getMessage("primaryPage.monthAntiepidemicTime", null, locale);
                String dbContent = m.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
                dbContent =StringUtils.replaceOnce(dbContent,"$",monthAntiepidemicTime);
                m.setTitle(title);
                m.setContent(dbContent);
            }
        }
        return msgs;
    }

    @Override
    public List<TMsg> getHomePageUnreadedMsg(Map<String, Object> map) {
        Long userId = ShiroUtils.getUserId();
        map.put("userId",userId);
        List<TMsg> msgs = homePageDao.findHomePageUnreadMsgs(map);
        Locale locale = (Locale)ShiroUtils.getSubjct().getSession().getAttribute("localLang");
            for (TMsg m:msgs){
                if(m.getType()==0){
                    String title = messageSource.getMessage("primaryPage.outFenceWarn", null, locale);
                    m.setTitle(title);
                }
                if(m.getType()==1){
                    String title = messageSource.getMessage("primaryPage.lowPowerWarn", null, locale);
                    m.setTitle(title);
                }
                if(m.getType()==2){
                    String title = messageSource.getMessage("dogManage.springAntiepidemic", null, locale);
                    m.setTitle(title);
                }
                if(m.getType()==3){
                    String title = messageSource.getMessage("dogManage.autmnAntiepidemic", null, locale);
                    m.setTitle(title);
                }
                if(m.getType()==4){
                    String title = messageSource.getMessage("dogManage.monthAntiepidemic", null, locale);
                    m.setTitle(title);
                }
            }
        return msgs;
    }

    @Override
    public TMsg findMsgById(String id) {
        TMsg msg = homePageDao.findMsgById(id);
        Locale locale = (Locale)ShiroUtils.getSubjct().getSession().getAttribute("localLang");


        if(msg.getType()==0){//出栏
            String title = messageSource.getMessage("primaryPage.outFenceWarn", null, locale);
            String content = messageSource.getMessage("primaryPage.dogOutFence", null, locale);
            msg.setTitle(title);
            msg.setContent(content);
        }
        if(msg.getType()==1){//低电
            String title = messageSource.getMessage("primaryPage.lowPowerWarn", null, locale);

            String neckLetLowPower = messageSource.getMessage("primaryPage.neckLetLowPower", null, locale);
            String warnningTime = messageSource.getMessage("primaryPage.warnningTime", null, locale);
            String lowPower = messageSource.getMessage("primaryPage.lowPower", null, locale);
            String traceId = messageSource.getMessage("dogManage.traceId", null, locale);
            String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
            String dog = messageSource.getMessage("dogManage.dogName", null, locale);
            String dbContent = msg.getContent();
                dbContent =StringUtils.replaceOnce(dbContent,"$",neckLetLowPower);
                dbContent =StringUtils.replaceOnce(dbContent,"$",warnningTime);
                dbContent =StringUtils.replaceOnce(dbContent,"$",lowPower);
                dbContent =StringUtils.replaceOnce(dbContent,"$",traceId);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
                dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
            msg.setTitle(title);
            msg.setContent(dbContent);
        }
        if(msg.getType()==2){//春防
            String title = messageSource.getMessage("dogManage.springAntiepidemic", null, locale);
            String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
            String dog = messageSource.getMessage("dogManage.dogName", null, locale);
            String springAntiepidemicTime = messageSource.getMessage("primaryPage.springAntiepidemicTime", null, locale);
            String dbContent = msg.getContent();
            dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
            dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
            dbContent =StringUtils.replaceOnce(dbContent,"$",springAntiepidemicTime);
            msg.setTitle(title);
            msg.setContent(dbContent);
        }
        if(msg.getType()==3){//秋防
            String title = messageSource.getMessage("dogManage.autmnAntiepidemic", null, locale);
            String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
            String dog = messageSource.getMessage("dogManage.dogName", null, locale);
            String autumnAntiepidemicTime = messageSource.getMessage("primaryPage.autumnAntiepidemicTime", null, locale);
            String dbContent = msg.getContent();
            dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
            dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
            dbContent =StringUtils.replaceOnce(dbContent,"$",autumnAntiepidemicTime);
            msg.setTitle(title);
            msg.setContent(dbContent);
        }
        if(msg.getType()==4){//月月投
            String title = messageSource.getMessage("dogManage.monthAntiepidemic", null, locale);
            String dogOwner = messageSource.getMessage("dogManage.ownerName", null, locale);
            String dog = messageSource.getMessage("dogManage.dogName", null, locale);
            String monthAntiepidemicTime = messageSource.getMessage("primaryPage.monthAntiepidemicTime", null, locale);
            String dbContent = msg.getContent();
            dbContent =StringUtils.replaceOnce(dbContent,"$",dogOwner);
            dbContent =StringUtils.replaceOnce(dbContent,"$",dog);
            dbContent =StringUtils.replaceOnce(dbContent,"$",monthAntiepidemicTime);
            msg.setTitle(title);
            msg.setContent(dbContent);
        }

        return msg;
    }

    @Override
    public int updateMsgStatus(String id) {
        return homePageDao.updateMsgStatus(id);
    }

    @Override
    public List<OrgInfo> getAllOrgInfo() {
        return homePageDao.orgInfoList();
    }



    @Override
    public List<TMsg> getMsgList(Map<String, Object> map) {
        return homePageDao.getMsgList(map);
    }

    @Override
    public PageUtils dogList(Map<String, Object> map) throws ParseException {
        Map<String,Object> query = new HashMap<>();
        Object type = map.get("type");
        String myOrgId = map.get("myOrgId").toString();
        String userId = map.get("userId").toString();
        int role = Integer.parseInt(map.get("role").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        String orgId = map.get("orgId").toString();

        if(type == null) {
            //类型不确定，抛出异常
            throw new RuntimeException("类型不能为空");
        }
        List<String> antiTraceIdList = new ArrayList<>();
        List<String> traceIdList = new ArrayList<>();
        if(role == 2) {
            //防疫员
            query.put("userId",userId);
        }else if(role == 3 || role == 8) {
            //乡级
            String protector = map.get("protector").toString();
            if("".equals(protector)) {
                List<String> lists = new ArrayList<>();
                lists.add(myOrgId);
                //查询本组织的所有防疫员的
                query.put("lists",lists);
            }else {
                //查找特定防疫员的犬只
                String pUserId = homePageDao.findUserId(protector.toString());
                query.put("userId",pUserId);
            }
        }else {
            if(!"".equals(orgId)) {
                myOrgId = orgId;
            }
            List<OrgInfo> orgInfos = sysDeptMapper.orgList();
            List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(myOrgId.toString()), new ArrayList<>());
            query.put("lists",lists);
        }
        //1、获取所管犬只的traceId
        traceIdList = homePageDao.getTraceIdList(query);
        if(traceIdList == null || traceIdList.isEmpty()) {
            //没有所管的犬只
            PageUtils res = new PageUtils(new ArrayList<>(),0);
            return res;
        }
        query.put("traceIdList",traceIdList);
        //2、查询所管犬只的防疫记录
        List<Map<String, Object>> maps = homePageDao.countAntiAmount(query);
        //3、过滤在防疫期的犬只
        antiTraceIdList = retAntiTraceIdList(maps);

        int total = 0;
        if("1".equals(type.toString())) {
            //查询已防疫犬只
            if(antiTraceIdList == null || antiTraceIdList.isEmpty()) {
                PageUtils res = new PageUtils(new ArrayList<>(),0);
                return res;
            }
            total = antiTraceIdList.size();
            //获取分页的traceIdList
            antiTraceIdList = compute(pageNum,pageSize,antiTraceIdList);
            query.put("antiTraceIdList",antiTraceIdList);
        }else {
            //查询未防疫犬只
            rmAntiTraceId(traceIdList,antiTraceIdList);
            total = traceIdList.size();
            //获取分页的traceIdList
            traceIdList = compute(pageNum,pageSize,traceIdList);
            query.put("antiTraceIdList",traceIdList);
        }

        //4、查询traceIdList的犬只
        if(traceIdList == null || traceIdList.isEmpty()) {
            return new PageUtils(new ArrayList<>(),0);
        }
        List<Map<String, Object>> dogList = homePageDao.dogList(query);
        PageUtils res = new PageUtils(addDetailTown(dogList),total);
        return res;
    }

    public List<String> rmAntiTraceId(List<String> traceIdList,List<String> antiTraceIdList){
        if(antiTraceIdList == null || antiTraceIdList.isEmpty()) {
            return traceIdList;
        }
        for (String str:
                antiTraceIdList) {
            traceIdList.remove(str);
        }
        return traceIdList;
    }

    public List<String> compute(int pageNum,int pageSize,List<String> antiTraceIdList) {
        if(pageNum * pageSize < antiTraceIdList.size()) {
            return antiTraceIdList.subList((pageNum-1)*pageSize,pageNum*pageSize);
        }else {
            return antiTraceIdList.subList((pageNum-1)*pageSize,antiTraceIdList.size());
        }
    }

    //添加详细地址
    List<Map<String, Object>> addDetailTown(List<Map<String, Object>> list) {
        if(list != null && list.size() > 0) {
            List<AreaInfo> areaList = areaInfoDao.getList();
            for (Map<String,Object> m:
                    list) {
                Integer areaId = null;
                if(m.get("areaId") != null) {
                    areaId = Integer.parseInt(m.get("areaId").toString());
                }
                String areaName = "";
                if(areaId != null) {
                    List<AreaInfo> areaInfos = areaUtils.areaReverse(areaList, areaId, new ArrayList<>());
                    //组装州县乡
                    StringBuilder sb = new StringBuilder();
                    if(areaInfos != null && areaInfos.size() > 0) {
                        for (int i = 1; i< areaInfos.size(); i++) {
                            sb.append(areaInfos.get(i).getName());
                        }
                    }
                    areaName = sb.toString();
                }
                m.put("town",areaName);
            }
        }
        return list;
    }

    @Override
    public Map<String, Object> countDogNumber(Map<String, Object> map) throws Exception {
        Map<String,Object> res = new HashMap<>();
        Integer addNumber = 0;
        Integer cancelNumber = 0;
        Integer dealNumber = 0;
        int type = Integer.parseInt(map.get("type").toString());
        List<Integer> lists = new ArrayList<>();
        int orgId = Integer.parseInt(map.get("orgId").toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String currentDate = sdf.format(new Date());
        String lastMonth = DateUtils.getLastMonth(currentDate,0,-1,0);
        map.put("lastMonth",lastMonth);
        if(type == 3 ||type == 8) {
            //乡级组织
            lists.add(orgId);
            map.put("lists",lists);
            map.remove("userId");   //不根据用户id查
        }else if(type != 2) {
            //乡级以上的组织
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            lists = orgtills.getChildren(orgInfos, orgId, new ArrayList<>()).stream().map(org -> org.getId()).collect(Collectors.toList());
            map.put("lists",lists);
            map.remove("userId");   //不根据用户id查
        }
        addNumber = homePageDao.findAddedDogs(map);
        cancelNumber = homePageDao.findCancelDogs(map);
        dealNumber = homePageDao.findDeadDogs(map);
        res.put("addNumber",addNumber);
        res.put("cancelNumber",cancelNumber);
        res.put("dealNumber",dealNumber);
        return res;
    }

    @Override
    public Map<String, Object> countAntiAmount(Map<String, Object> map) throws ParseException {
        Map<String,Object> res = new HashMap<>();
        Integer totalAmount = 0;
        Integer antiAmount = 0;
        Integer unAntiAmount = 0;
        int type = Integer.parseInt(map.get("type").toString());
        List<Integer> lists = new ArrayList<>();
        int orgId = Integer.parseInt(map.get("orgId").toString());
        if(type == 3 ||type == 8) {
            //乡级组织
            lists.add(orgId);
            map.put("lists",lists);
            map.remove("userId");   //不根据用户id查
        }else if(type != 2) {
            //乡级以上的组织
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            lists = orgtills.getChildren(orgInfos, orgId, new ArrayList<>()).stream().map(org -> org.getId()).collect(Collectors.toList());
            map.put("lists",lists);
            map.remove("userId");   //不根据用户id查
        }
        //查询犬只总数
        List<String> traceIdList = homePageDao.getTraceIdList(map);
        if(traceIdList != null) {
            totalAmount = traceIdList.size();
        }
        map.put("traceIdList",traceIdList);
        //统计已防疫犬只、未防疫犬只数
        List<Map<String, Object>> list = homePageDao.countAntiAmount(map);
        List<Integer> antiList = countAntiAmount(list, traceIdList);//已防疫的犬只数
        antiAmount = antiList.get(0);
        unAntiAmount = antiList.get(1);

        res.put("totalAmount",totalAmount);
        res.put("antiAmount",antiAmount);
        res.put("unAntiAmount",unAntiAmount);
        return res;
    }

    List<String> retAntiTraceIdList(List<Map<String, Object>> list) throws ParseException {
        List<String> res = new ArrayList<>();
        if(list == null || list.isEmpty()) {
            return res;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(new Date());
        for (Map<String,Object> map: list) {
            String traceId = map.get("traceId").toString();
            String antieDate = map.get("antieDate").toString();
            String period = map.get("period").toString();
            if(antieDate == null || period == null) {   //异常情况不计入统计
                continue;
            }
            long days = DateUtils.daysOfTwo_1(antieDate, nowDate);
            long dayPeriod = Long.parseLong(period) * 30;
            if(days < dayPeriod) {
                //时间间隔 < 防疫周期   --> 处于已防疫状态
                res.add(traceId);
            }
        }
        return res;
    }

    //计算已防疫犬只数量、未防疫犬只数量
    List<Integer> countAntiAmount(List<Map<String, Object>> list,List<String> traceIdList) throws ParseException {
        List<Integer> res = new ArrayList<>();
        int antiAmount = 0;
        int unAntiAmount = 0;
        if(traceIdList == null || traceIdList.isEmpty()) {  //没有所管的犬只
            res.add(antiAmount);
            res.add(unAntiAmount);
            return res;
        }
        if(list == null || list.isEmpty()) {        //没有犬只有防疫记录
            unAntiAmount = traceIdList.size();
            res.add(antiAmount);
            res.add(unAntiAmount);
            return res;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(new Date());
        for (Map<String,Object> map: list) {
            String traceId = map.get("traceId").toString();
            String antieDate = map.get("antieDate").toString();
            String period = map.get("period").toString();
            if(antieDate == null || period == null || traceId == null) {   //异常情况不计入统计
                continue;
            }
            traceIdList.remove(traceId);    //去掉有防疫记录的犬只
            long days = DateUtils.daysOfTwo_1(antieDate, nowDate);
            long dayPeriod = Long.parseLong(period) * 30;
            if(days < dayPeriod) {
                //时间间隔 < 防疫周期   --> 处于已防疫状态
                antiAmount++;
            }else {
                //超出防疫周期
                unAntiAmount++;
            }
        }
        unAntiAmount += traceIdList.size(); //未防疫的犬只总数= 没有防疫记录的犬只 + 防疫超期的犬只
        res.add(antiAmount);
        res.add(unAntiAmount);
        return res;
    }


}
