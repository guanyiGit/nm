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
public class TDeviceInfo extends Model<TDeviceInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String deviceNo;

    private Integer status;

    private String brand;

    private String model;

    private LocalDateTime dateOfProduction;

    private String remarks;

    private Integer type;

    private LocalDateTime startTime;

    private LocalDateTime wirteOffTime;

    private Integer orgId;

    private Integer receiveOrg;

    private Integer receiver;

    private LocalDateTime receiveTime;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer isDistribution;

    private LocalDateTime activateTime;

    private LocalDateTime freezeTime;

    private String imei;

    private String traceabilityNum;

    private String batch;

    private String psk;

    private String manufacturerNum;

    private String deviceIotId;

    /**
     * 李建
     */
    private String firm;

    /**
     * 李建
     */
    private LocalDateTime deadline;

    /**
     * 李建
     */
    private Integer importBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
