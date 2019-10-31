package com.soholy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class AepData extends Model<AepData> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "seq_id", type = IdType.AUTO)
    private Integer seqId;

    private String content;

    private LocalDateTime cretionTime;


    @Override
    protected Serializable pkVal() {
        return this.seqId;
    }

}
