package com.soholy.cb.dao;

import com.soholy.cb.entity.cdoec.T_iot_data;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IotDataDb_dev {

    @Insert({"<script>",
            "INSERT INTO t_iot_data (source,`full`,codec,type,local_Time) VALUES ",
            "<foreach collection='datas' item='item' separator='),(' open='(' close=')'>",
            "   #{item.source},#{item.full},#{item.codec},#{item.type},#{item.local_Time}",
            "</foreach>",
            "</script>"})
    int saves(@Param("datas") List<T_iot_data> paramList);

    @Results({
            @Result(property = "seq", column = "seq", id = true),
            @Result(property = "source", column = "source"),
            @Result(property = "full", column = "full"),
            @Result(property = "codec", column = "codec"),
            @Result(property = "type", column = "type"),
            @Result(property = "local_Time", column = "local_Time")})
    @Select({"<script>", "SELECT * FROM t_iot_data AS iot",
            "<if test='imei != null and imei != \"\"'>",
            "    WHERE iot.codec LIKE CONCAT('%',#{imei},'%')",
            "</if>", "ORDER BY iot.local_Time DESC LIMIT #{pageNo},#{pageSize}",
            "</script>"})
    List<T_iot_data> find(@Param("imei") String paramString, @Param("pageNo") int paramInt1, @Param("pageSize") int paramInt2);
}
