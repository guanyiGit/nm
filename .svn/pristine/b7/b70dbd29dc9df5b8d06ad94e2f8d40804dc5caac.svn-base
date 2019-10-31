package com.utils;

import com.entities.AreaInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class areaUtils {


    public static void main(String[] args){
        List<AreaInfo> list = new ArrayList<>();
        ArrayList<AreaInfo> lists = new ArrayList<>();
        for(int i=0;i<5;i++){
            AreaInfo inf = new AreaInfo();
            inf.setId(i);
            if(i!=0){
                inf.setPid(i-1);
            }else {
                inf.setPid(0);
            }
            inf.setName("组织名为:"+i);
            list.add(inf);
        }
//        List<String> res = s(list, "3", lists);
        List<AreaInfo> res = areaReverse(list,1, lists);
        System.out.println("res:"+res);
    }

    public static List<AreaInfo> areaReverse(List<AreaInfo> list,int areaId ,List<AreaInfo> lists){
        List<AreaInfo> res = areaR(list, areaId, lists);
        return  res.stream().sorted((a,b)->{return a.getId()-b.getId();}).collect(Collectors.toList());
    };

    public static List<AreaInfo> areaR(List<AreaInfo> list,int areaId ,List<AreaInfo> lists){
        int[] aa = new int[5];
        aa[0] = areaId;
        AreaInfo inf = list.stream().filter(area -> aa[0]==area.getId()).collect(Collectors.toList()).get(0);
        lists.add(inf);
        Integer pid = inf.getPid();
        if(areaId ==0){
            return lists;
        }
        for(AreaInfo info : list){
            if(pid==info.getId()){
                areaId = info.getId();
                areaR(list,areaId,lists);
            }
        }
        return lists;
    }
}
