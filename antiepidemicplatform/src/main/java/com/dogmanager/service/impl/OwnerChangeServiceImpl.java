/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OwnerChangeServiceImpl
 * Author:   Administrator
 * Date:     2018/9/28 16:33
 * Description: 犬主更换service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.service.impl;

import com.dogmanager.VO.OwnerChangeVO;
import com.dogmanager.dao.TDogInfoMapper;
import com.dogmanager.dao.TOwnerChangeMapper;
import com.dogmanager.service.OwnerChangeService;
import com.entities.OrgInfo;
import com.entities.TOwnerChange;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.ShiroUtils;
import com.utils.OrgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈犬主更换service〉
 *
 * @author Administrator
 * @create 2018/9/28
 * @since 1.0.0
 */
@Service
public class OwnerChangeServiceImpl implements OwnerChangeService {

    @Autowired
    private TOwnerChangeMapper ownerChangeMapper;
    @Autowired
    private TDogInfoMapper dogInfoMapper;
    @Autowired
    private OrgInfoDao orgInfoDao;

    /**
     * 列表
     * */
    @Override
    public PageInfo<OwnerChangeVO> findOwnerChangeVOList(@RequestParam HashMap<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        int type=user.getRoles().get(0).getType();
        if(type==2){
            map.put("operator",user.getUserId().intValue());
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
        List<OwnerChangeVO> ownerChangeVOList = ownerChangeMapper.findOwnerChangeVOList(map);
        PageInfo<OwnerChangeVO> pageInfo = new PageInfo<>(ownerChangeVOList);
        return pageInfo;
    }

    /**
     * 详情
     * */
    @Override
    public TOwnerChange findOwnerChangeVOById(Integer Id) {
        TOwnerChange tOwnerChange = ownerChangeMapper.selectByPrimaryKey(Id);
        return tOwnerChange;
    }
}
