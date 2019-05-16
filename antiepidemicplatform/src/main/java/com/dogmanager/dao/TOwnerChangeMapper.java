package com.dogmanager.dao;

import com.dogmanager.VO.OwnerChangeVO;
import com.entities.TOwnerChange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface TOwnerChangeMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(TOwnerChange record);

    TOwnerChange selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(TOwnerChange record);

    List<OwnerChangeVO> findOwnerChangeVOList(HashMap<String,Object> map);

    //更换犬主
    int updateDogOwner(@Param("owner") Integer owner,@Param("dogSourceCard") String dogSourceCard);
}