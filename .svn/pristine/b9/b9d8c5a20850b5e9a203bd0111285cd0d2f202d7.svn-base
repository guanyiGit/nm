package com.statisanalysis.serivce.impl;

import com.entities.OrgInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.statisanalysis.dao.DogCountDao;
import com.statisanalysis.entity.Data;
import com.statisanalysis.serivce.IDogCountService;
import com.utils.ShiroUtils;
import com.utils.OrgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: linchong
 * @Date: 2018/9/29 12:28
 * @Version 1.0
 */
@Service
public class DogCountServiceImpl implements IDogCountService {
    @Autowired
    private DogCountDao dogCountDao;
    @Autowired
    private OrgInfoDao orgInfoDao;
    private static Logger logger = LoggerFactory.getLogger(DogCountServiceImpl.class);

    @Override
    public List<Data> getDogBreed(Map<String,Object> map) {
        List<Data> dogBreed = dogCountDao.getDogBreed(map);
        logger.info("DogCountServiceImpl.findDogBreed()|dogBreed={}",dogBreed);
        return dogBreed;

    }

    @Override
    public List<Data> getDogSex(Map<String,Object> map) {
        return dogCountDao.getDogSex(map);
    }

    @Override
    public List<Data>  getDogAge(Map<String,Object> map) {
        List<Double> dogAge = dogCountDao.getDogAge(map);
        logger.info("DogCountServiceImpl.getDogAge|dogAge={}",dogAge);
        int[] ageArr = new int[6];
        for (Double age:
             dogAge) {
            if(age != null) {
                if(age < 3) {
                    ageArr[0]++;
                }else if(age >= 3 && age < 5) {
                    ageArr[1]++;
                }else if(age >=5 && age < 8) {
                    ageArr[2]++;
                }else if(age >= 8 && age<10) {
                    ageArr[3]++;
                }else if(age >= 10 && age < 15) {
                    ageArr[4]++;
                }else {
                    ageArr[5]++;
                }
            }
        }
        List<Data>  list = new ArrayList<>();
        Object o = ShiroUtils.getSubjct().getSession().getAttribute("type");
        int type = 1;
        if(o != null) {
            type = Integer.parseInt(o.toString());
        }
        multilangAge(list,ageArr,type);

        return list;
    }

    private void multilangAge(List<Data> list, int[] ageArr, int type) {
        if(type == 1) {
            if(ageArr[0] != 0) {
                list.add(new Data("3岁以下",ageArr[0]));
            }
            if(ageArr[1] != 0) {
                list.add(new Data("3~5岁",ageArr[1]));
            }
            if(ageArr[2] != 0) {
                list.add(new Data("5~8岁",ageArr[2]));
            }
            if(ageArr[3] != 0) {
                list.add(new Data("8~10岁",ageArr[3]));
            }
            if(ageArr[4] != 0) {
                list.add(new Data("10~15岁",ageArr[4]));
            }
            if(ageArr[5] != 0) {
                list.add(new Data("15岁以上",ageArr[5]));
            }
        }else if(type == 2) {
            if(ageArr[0] != 0) {
                list.add(new Data("ལོ་༣གྱི་མན། ",ageArr[0]));
            }
            if(ageArr[1] != 0) {
                list.add(new Data("ལོ་༣ནས་༥བར།  ",ageArr[1]));
            }
            if(ageArr[2] != 0) {
                list.add(new Data("ལོ་༥ནས་༨བར། ",ageArr[2]));
            }
            if(ageArr[3] != 0) {
                list.add(new Data("ལོ་༨ནས་༡༠བར། ",ageArr[3]));
            }
            if(ageArr[4] != 0) {
                list.add(new Data("ལོ་༡༠ནས་༡༥བར། ",ageArr[4]));
            }
            if(ageArr[5] != 0) {
                list.add(new Data("ལོ་༡༥ཡི་ཡན། ",ageArr[5]));
            }
        }else if(type == 3) {
            //TODO 蒙语为翻译
            if(ageArr[0] != 0) {
                list.add(new Data("3岁以下",ageArr[0]));
            }
            if(ageArr[1] != 0) {
                list.add(new Data("3~5岁",ageArr[1]));
            }
            if(ageArr[2] != 0) {
                list.add(new Data("5~8岁",ageArr[2]));
            }
            if(ageArr[3] != 0) {
                list.add(new Data("8~10岁",ageArr[3]));
            }
            if(ageArr[4] != 0) {
                list.add(new Data("10~15岁",ageArr[4]));
            }
            if(ageArr[5] != 0) {
                list.add(new Data("15岁以上",ageArr[5]));
            }
        }

    }

    @Override
    public List<Data> getDogAreaInfo(Map<String, Object> map) {
        //角色判断
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        List<OrgInfo> orgInfos = orgInfoDao.orgList();
        Long orgId = user.getDeptId();
        List<Integer> subOrgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(orgId.toString()), new ArrayList<>());
        List<Data> dogAreaInfo1 = new ArrayList<>();
        if(type == 5 || type == 9) {
        //如果是县级角色
            map.put("orgId",user.getDeptId());
            dogAreaInfo1 = dogCountDao.getDogAreaInfo1(map);
        }else if(type == 6 || type == 10) {
            map.put("lists",subOrgList);
            dogAreaInfo1 =  dogCountDao.getDogAreaInfo2(map);
        }else {
            return null;
        }

        return dogAreaInfo1;
    }

}
