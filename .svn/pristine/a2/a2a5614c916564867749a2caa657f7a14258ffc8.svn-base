package com.statisanalysis.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**外部接口
 * @Description
 * @Author: linchong
 * @Date: 2018/10/12 17:51
 * @Version 1.0
 */
@Mapper
public interface OuterDao {
    //获取犬只存栏数量
    Integer getLiveDog(Map<String,Object> map);
    //获取犬只新增数量
    Integer getNewDogNum(Map<String,Object> map);
    //获取犬只注销数量
    Integer getCancelDogNum(Map<String,Object> map);
    //获取犬主新增数量
    Integer getOwnerNewNum(Map<String,Object> map);
    //获取防疫次数
    Integer getAntiedemicTimes(Map<String,Object> map);
    //获取犬尸处理数量
    Integer getDogCorpseNum(Map<String,Object> map);
    //获取粪便处理次数
    Integer getManureDealTimes(Map<String,Object> map);
    //获取流浪犬处理次数
    Integer getStrayDogTimes(Map<String,Object> map);
    //獲取項圈更換次數
    Integer getNectletTimes(Map<String,Object> map);

}
