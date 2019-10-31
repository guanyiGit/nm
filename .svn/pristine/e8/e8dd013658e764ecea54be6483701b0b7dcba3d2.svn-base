package com.soholy.pojo.aep.rt;


import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class BaseRt {

    private Integer seq_id;
    private Object content;
    private Date cretion_time;

    public String getCretion_time() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(this.cretion_time);
    }
}
