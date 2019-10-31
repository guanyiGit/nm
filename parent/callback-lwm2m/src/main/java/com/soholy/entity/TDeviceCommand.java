package com.soholy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author GuanY
 * @since 2019-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDeviceCommand extends Model<TDeviceCommand> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "device_command_id", type = IdType.AUTO)
    private Long deviceCommandId;

    private Long deviceNo;

    /**
     * 0模式设置，1间隔时间设置
     */
    private Integer cmdType;

    /**
     * -1正常,-2省电，其他为实际设置
     */
    private Integer cmdValue;

    private Long operatorId;

    private LocalDateTime cmdIssuedTime;

    private Integer cmdMid;

    /**
     * 0待发送，1平台已下发，2iot已下发，3命令已送达，4发送失败，5成功响应，6失败响应
     */
    private Integer cmdStatus;

    private LocalDateTime cmdRspTime;

    private LocalDateTime creationTime;

    private String iotCommandId;


    @Override
    protected Serializable pkVal() {
        return this.deviceCommandId;
    }

}
