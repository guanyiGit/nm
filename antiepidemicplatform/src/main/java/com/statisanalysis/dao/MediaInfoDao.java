package com.statisanalysis.dao;

import com.entities.TMediaInfo;
import com.entities.TMediaRef;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/17 16:58
 * @Version 1.0
 */
@Mapper
public interface MediaInfoDao {
    /**
     * 批量插入媒体表
     * @param list
     * @return
     */
    int insertMediaBatch(List<TMediaInfo> list);

    /**
     * 批量插入媒体关联表
     * @param list
     * @return
     */
    int insertMediaRefBatch(List<TMediaRef> list);

}
