package com.statisanalysis.wx.utils;

import com.statisanalysis.wx.vo.CountVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/22 17:38
 * @Version 1.0
 */
public class HandleDataUtils {
    /**
     * 添加州、县的统计数据（州级角色）
     * @param list
     * @return
     */
//    public static List<CountVO> handleData(List<CountVO> list) {
//        List<CountVO> addList = new ArrayList<>();
//        if(list == null || list.size() < 1) {
//            return list;
//        }
//        Set<Integer> countyIdSet = new HashSet<>();
//        for (CountVO vo:
//                list) {
//            if(vo.getCountyId() != null) {
//                countyIdSet.add(vo.getCountyId());
//            }
//        }
//        //统计每个县，乡不限
//        if(countyIdSet.size() > 0) {
//            for (Integer id: countyIdSet) {
//                CountVO addVO = new CountVO();
//                Integer count = 0;
//                String countyName = "";
//                String stateName = list.get(0).getStateName();
//                Integer stateId = list.get(0).getStateId();
//                for (CountVO vo: list) {
//                    Integer countyId = vo.getCountyId();
//                    if(countyId != null && countyId.intValue() == id.intValue()) {
//                        count += vo.getCount().intValue();
//                        countyName = vo.getCountyName();
//                    }
//                }
//                addVO.setStateId(stateId);
//                addVO.setStateName(stateName);
//                addVO.setCountyId(id);
//                addVO.setCountyName(countyName);
//                addVO.setVillageName("");
//                addVO.setCount(count);
//                addList.add(addVO);
//            }
//        }
//        //统计本州，县不限
//        int count = 0;
//        CountVO countVO = new CountVO();
//        Integer stateId = list.get(0).getStateId();
//        String stateName = list.get(0).getStateName();
//        for (CountVO vo:
//             list) {
//            if(vo.getCount() != null) {
//                count += vo.getCount().intValue();
//            }
//        }
//        countVO.setStateId(stateId);
//        countVO.setStateName(stateName);
//        countVO.setCountyName("");
//        countVO.setVillageName("");
//        countVO.setCount(count);
//        addList.add(countVO);
////        将新增的数据添加到原来的list中
//        if(addList.size() > 0) {
//            for (CountVO c:
//                    addList) {
//                list.add(0,c);
//            }
//        }
//        return list;
//    }

    /**
     *
     * @param list
     * @return
     */
    public static List<CountVO> handleData(List<CountVO> list) {
        List<CountVO> addList = new ArrayList<>();
        if(list == null || list.size() < 1) {
            return list;
        }
        Set<Integer> countyIdSet = new HashSet<>();
        for (CountVO vo:
                list) {
            if(vo.getCountyId() != null) {
                countyIdSet.add(vo.getCountyId());
            }
        }
        //统计每个县，乡不限
        if(countyIdSet.size() > 0) {
            for (Integer id: countyIdSet) {
                CountVO addVO = new CountVO();
                Integer count = 0;
                String countyName = "";
                String stateName = list.get(0).getStateName();
                Integer stateId = list.get(0).getStateId();
                for (CountVO vo: list) {
                    Integer countyId = vo.getCountyId();
                    if(countyId != null && countyId.intValue() == id.intValue()) {
                        count += vo.getCount().intValue();
                        countyName = vo.getCountyName();
                    }
                }
                addVO.setStateId(stateId);
                addVO.setStateName(stateName);
                addVO.setCountyId(id);
                addVO.setCountyName(countyName);
                addVO.setVillageName("");
                addVO.setCount(count);
                addList.add(addVO);
            }
        }
        //统计本州，县不限
        int count = 0;
        CountVO countVO = new CountVO();
        Integer stateId = list.get(0).getStateId();
        String stateName = list.get(0).getStateName();
        for (CountVO vo:
                list) {
            if(vo.getCount() != null) {
                count += vo.getCount().intValue();
            }
        }
        countVO.setStateId(stateId);
        countVO.setStateName(stateName);
        countVO.setCountyName("");
        countVO.setVillageName("");
        countVO.setCount(count);
        addList.add(countVO);
//        将新增的数据添加到原来的list中
        if(addList.size() > 0) {
            for (CountVO c:
                    addList) {
                list.add(0,c);
            }
        }
        //-----------------重排序，将县的统计信息和其乡的信息放在一起--------------
        if(countyIdSet.size() < 1) {
            return list;    //没有县的信息需要整合
        }
        List list2 = new ArrayList();
        list2.add(list.get(0));     //将州级的统计信息先放入
        for (Integer countyId:
             countyIdSet) {
            for (CountVO vo :
                 list) {
                //先加入县的统计信息
                if(vo.getCountyId() != null && vo.getCountyId().intValue() == countyId.intValue() && vo.getVillageId() == null) {
                    list2.add(vo);
                }
                //再加入该县所有乡的统计信息
                if(vo.getCountyId() != null && vo.getCountyId().intValue() == countyId.intValue() && vo.getVillageId() != null) {
                    list2.add(vo);
                }
            }
        }
        list.clear();
        list.addAll(list2);
        return list;
    }

    /**
     * 添加县的统计数据（州级角色）
     * @param list
     * @return
     */
    public static List<CountVO> handleData1(List<CountVO> list){
        List<CountVO> addList = new ArrayList<>();
        if(list == null || list.size() < 1) {
            return list;
        }
        Set<Integer> countyIdSet = new HashSet<>();
        for (CountVO vo:
             list) {
            if(vo.getCountyId() != null) {
                countyIdSet.add(vo.getCountyId());
            }
        }
        //统计每个县，乡不限
        if(countyIdSet.size() > 0) {
            for (Integer id: countyIdSet) {
                CountVO addVO = new CountVO();
                Integer count = 0;
                String countyName = "";
                String stateName = list.get(0).getStateName();
                Integer stateId = list.get(0).getStateId();
                for (CountVO vo: list) {
                    Integer countyId = vo.getCountyId();
                    if(countyId != null && countyId.intValue() == id.intValue()) {
                        count += vo.getCount().intValue();
                        countyName = vo.getCountyName();
                    }
                }
                addVO.setStateId(stateId);
                addVO.setStateName(stateName);
                addVO.setCountyId(id);
                addVO.setCountyName(countyName);
                addVO.setVillageName("");
                addVO.setCount(count);
                addList.add(addVO);
            }
        }
        //统计本州，县不限
//        int count = 0;
//        CountVO countVO = new CountVO();
//        Integer stateId = list.get(0).getStateId();
//        String stateName = list.get(0).getStateName();
//        for (CountVO vo:
//             list) {
//            if(vo.getCount() != null) {
//                count += vo.getCount().intValue();
//            }
//        }
//        countVO.setStateId(stateId);
//        countVO.setStateName(stateName);
//        countVO.setCountyName("");
//        countVO.setVillageName("");
//        countVO.setCount(count);
//        addList.add(countVO);
        //将新增的数据添加到原来的list中
        if(addList.size() > 0) {
            for (CountVO c:
                    addList) {
                list.add(0,c);
            }
        }
        return list;
    }

    /**
     * 添加州的统计数据（州级角色）
     * @param list
     * @return
     */
    public static List<CountVO> handleData2(List<CountVO> list) {
        if(list == null || list.size() < 1) {
            return list;
        }
        //统计本州，县不限
        int count = 0;
        CountVO countVO = new CountVO();
        Integer stateId = list.get(0).getStateId();
        String stateName = list.get(0).getStateName();
        for (CountVO vo:
                list) {
            if(vo.getCount() != null) {
                count += vo.getCount().intValue();
            }
        }
        countVO.setStateId(stateId);
        countVO.setStateName(stateName);
        countVO.setCountyName("");
        countVO.setVillageName("");
        countVO.setCount(count);
        list.add(0,countVO);    //将州的信息添加到第一条
        return list;
    }

    /**
     * 县统计数据（县级管理员）
     * @param list
     * @return
     */
    public static List<CountVO> handleData3(List<CountVO> list) {
        if(list == null || list.size() < 1) {
            return list;
        }
        //统计本县，乡不限
        int count = 0;
        CountVO countVO = new CountVO();
        Integer countyId = list.get(0).getCountyId();
        String countyName = list.get(0).getCountyName();
        for (CountVO vo:
                list) {
            if(vo.getCount() != null) {
                count += vo.getCount().intValue();
            }
        }
        countVO.setCountyId(countyId);
        countVO.setCountyName(countyName);
        countVO.setVillageName("");
        countVO.setCount(count);
        list.add(0,countVO);    //将县的信息添加到第一条
        return list;
    }

    /**
     * 统计本乡（乡级管理员）
     * @param list
     * @return
     */
    public static List<CountVO> handleData4(List<CountVO> list) {
        if(list == null || list.size() < 1) {
            return list;
        }
        //统计本乡，防疫员不限
        int count = 0;
        CountVO countVO = new CountVO();
        Integer villageId = list.get(0).getVillageId();
        String villageName = list.get(0).getVillageName();
        for (CountVO vo:
             list) {
            if(vo.getCount() != null) {
                count += vo.getCount().intValue();
            }
        }
        countVO.setVillageId(villageId);
        countVO.setVillageName(villageName);
        countVO.setProtectorName("");
        countVO.setCount(count);
        list.add(0,countVO);
        return list;
    }

    /**
     * 统计本乡（乡级管理员）  --针对流浪狗处理
     * @param list
     * @return
     */
    public static List<CountVO> handleData5(List<CountVO> list,Integer count) {
        if(list == null || list.size() < 1) {
            return list;
        }
//        int count = 0;
        CountVO countVO = new CountVO();
        Integer villageId = list.get(0).getVillageId();
        String villageName = list.get(0).getVillageName();
//        for (CountVO vo:
//                list) {
//            if(vo.getCount() != null) {
//                count += vo.getCount().intValue();
//            }
//        }
        countVO.setVillageId(villageId);
        countVO.setVillageName(villageName);
        countVO.setProtectorName("");
        countVO.setCount(count);
        list.add(0,countVO);
        return list;
    }

    /**
     * 州级管理员（犬粪抗原、犬只解剖、牛羊抗体检测、牛羊脏器处理）
     * @param list
     * @param stateCount
     * @return
     */
    public static List<CountVO> handleData6(List<CountVO> list,Integer stateCount) {
        if(list == null || list.size() < 1) {
            return list;
        }
        int count = 0;
        CountVO countVO = new CountVO();
        Integer stateId = list.get(0).getStateId();
        String stateName = list.get(0).getStateName();
        for (CountVO vo:
                list) {
            if(vo.getCount() != null) {
                count += vo.getCount().intValue();
            }
        }
        count += stateCount;
        countVO.setStateId(stateId);
        countVO.setStateName(stateName);
        countVO.setCountyName("");
        countVO.setCount(count);
        list.add(0,countVO);
        return list;
    }

//    public static void main(String[] args) {
//        List<CountVO> list = new ArrayList<>();
//        CountVO v1 =new CountVO(32,33,31,null,"演示州","演示县33","演示乡31","",100);
//        CountVO v2 =new CountVO(32,33,34,null,"演示州","演示县33","演示乡34","",100);
//        CountVO v3 =new CountVO(32,35,37,null,"演示州","演示县35","演示乡37","",100);
//        CountVO v4 =new CountVO(32,35,38,null,"演示州","演示县35","演示乡38","",100);
//        CountVO v5 =new CountVO(32,36,39,null,"演示州","演示县36","演示乡39","",100);
//        CountVO v6 =new CountVO(32,36,40,null,"演示州","演示县36","演示乡40","",100);
//        list.add(v1);
//        list.add(v2);
//        list.add(v3);
//        list.add(v4);
//        list.add(v5);
//        list.add(v6);
//        List<CountVO> countVOS = handleDataState(list);
//        System.out.println(countVOS);
//    }
}
