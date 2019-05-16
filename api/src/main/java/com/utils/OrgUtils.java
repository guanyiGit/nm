package com.utils;

import com.entities.OrgInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrgUtils {

    public static void main(String[] args){
//        ArrayList<OrgInfo> list = new ArrayList<>();
//        ArrayList<Integer> res = new ArrayList<>();
//        list.add(new OrgInfo(0,-1,0,"a"));
//        list.add(new OrgInfo(1,0,1,"b"));
//        list.add(new OrgInfo(2,1,4,"c"));
//        list.add(new OrgInfo(3,1,4,"d"));
//        list.add(new OrgInfo(4,1,4,"e"));
//        list.add(new OrgInfo(5,1,4,"f"));
//
//        list.add(new OrgInfo(6,0,1,"g"));
//        list.add(new OrgInfo(7,6,4,"h"));
//        list.add(new OrgInfo(8,6,4,"i"));
//
//
//        Scanner scan = new Scanner(System.in);
//        while (true){
//            res.clear();
//            System.out.println("请输入一个组织id");
//            Integer orgId = scan.nextInt();
//            if(orgId==100){
//                break;
//            }
//            List<Integer> integers = orgReverse(list, orgId, res);
//            System.out.println(integers);
//        }
//        scan.close();

        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("7899");
        System.out.println(String.join(",",list.toString()));

    }

    public static String getOrgIds(List<OrgInfo> list ,int orgId,List<String> res){

        List<String> ids = orgReverseTwo(list, orgId, res);
        String join = String.join(",", ids);
        return join;
    }
    public static List<String> orgReverseTwo(List<OrgInfo> list ,int orgId,List<String> res){

        /**
         * 查找出所有类型为3的组织的组织id
         */
        String[] args = new String[5];
        args[0] = orgId+"";

        OrgInfo orgInfo = list.stream().filter(org -> { return args[0].equals(org.getId()+"") ; }).collect(Collectors.toList()).get(0);
        int type = orgInfo.getType();
        orgId = orgInfo.getId();
        if(type==3){
            res.add(orgId+"");
            return res;
        }
        List<OrgInfo> collect = list.stream().filter(inf -> {return args[0].equals(inf.getPid()+"") ;}).collect(Collectors.toList());
        collect.stream().forEach(org ->{Integer orgId1 = org.getId();orgReverseTwo(list,orgId1,res); });
        return  res;
    };

    public static List<Integer> orgReverse(List<OrgInfo> list ,int orgId,List<Integer> res){

        /**
         * 查找出所有类型为3的组织的组织id
         */
        int[] args = new int[5];
        args[0] = orgId;

        OrgInfo orgInfo = list.stream().filter(org -> { return args[0] == org.getId(); }).collect(Collectors.toList()).get(0);
            int type = orgInfo.getType();
            orgId = orgInfo.getId();
            if(type==3){
                res.add(orgId);
                return res;
            }
        List<OrgInfo> collect = list.stream().filter(inf -> {return args[0] == inf.getPid();}).collect(Collectors.toList());
            collect.stream().forEach(org ->{Integer orgId1 = org.getId();orgReverse(list,orgId1,res); });
        return  res;
    };





}
