package com.soholy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soholy.entity.AepData;
import com.soholy.entity.BaseRt;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author GuanY
 * @since 2019-06-26
 */
public interface AepDataMapper extends BaseMapper<AepData> {

    @Insert(value = {"<script>",
            "INSERT INTO aep_data (content) VALUES ",
            "<foreach collection='datas' item='item' separator='),(' open='(' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    int saves(@Param("datas") List<String> datas);

    @Results({
            @Result(property = "seq_id", column = "seq_id", id = true),
            @Result(property = "content", column = "content"),
            @Result(property = "cretion_time", column = "cretion_time")
    })
    @Select(value = {
            "<script>",
            "SELECT * FROM aep_data AS aep ",
            "<if test='imei != null and imei != \"\"'>",
            "WHERE aep.content LIKE CONCAT('%',#{imei},'%')",
            "</if>",
            "ORDER BY aep.cretion_time DESC LIMIT #{pageNo},#{pageSize}",
            "</script>"
    })
    List<BaseRt> findDatas(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize, @Param("imei") String imei);

}
