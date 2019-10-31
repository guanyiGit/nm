package com.basicInfo.dao;

import com.basicInfo.vo.DogOwnerVO;
import com.dogmanager.VO.DogInfoVO;
import com.entities.TDogOwner;
import com.entities.TDogOwnerExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface TDogOwnerMapper {
    int countByExample(TDogOwnerExample example);

    int deleteByExample(TDogOwnerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDogOwner record);

    int insertSelective(TDogOwner record);

    List<TDogOwner> selectByExample(TDogOwnerExample example);

    TDogOwner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDogOwner record, @Param("example") TDogOwnerExample example);

    int updateByExample(@Param("record") TDogOwner record, @Param("example") TDogOwnerExample example);

    int updateByPrimaryKeySelective(TDogOwner record);

    int updateByPrimaryKey(TDogOwner record);

    /**
     * 查看所有犬主信息
     * @param
     * @return
     */
	List<DogOwnerVO> findDogOwnerList(Map<String, Object>map);
	//犬主详情
	DogOwnerVO findDogOwnerDetail(@Param("id") Integer id,@Param("type") Object type);
	//犬主id查所属犬只
	List<DogInfoVO> findDogList(Integer id);
	//总记录数
	int findDogOwnerCount(Map<String, Object> map);
	
}