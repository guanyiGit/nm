package com.soholy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soholy.entity.TDeviceInfo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuanY
 * @since 2019-06-26
 */
public interface TDeviceInfoMapper extends BaseMapper<TDeviceInfo> {

    @Select({"<script>",
            "SELECT ref.trace_id traceId from t_device_ref_dog ref where ref.device_no=#{deviceNo}",
            "</script>"})
    String selectTraceIdByDeviceNo(String paramString);

}
