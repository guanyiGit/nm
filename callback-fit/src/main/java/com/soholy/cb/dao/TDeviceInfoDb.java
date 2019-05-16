package com.soholy.cb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soholy.cb.entity.TDeviceInfoEntity;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface TDeviceInfoDb extends BaseMapper<TDeviceInfoEntity> {

    @Select({
            "<script>",
            "SELECT own.`name` ownName, dog.dog_name dogName, ref.trace_id traceId",
            "FROM t_device_ref_dog ref",
            "LEFT JOIN t_dog_info dog ON ref.trace_id = dog.trace_id",
            "LEFT JOIN t_dog_owner own ON own.id = dog.`owner`",
            "WHERE",
            "       ref.device_no = #{deviceNo}",
            "</script>"})
    Map<String, Object> selectInfosByDeviceNo(String paramString);

    @Select({"<script>",
            "SELECT ref.trace_id traceId from t_device_ref_dog ref where ref.device_no=#{deviceNo}",
            "</script>"})
    String selectTraceIdByDeviceNo(String paramString);
}
