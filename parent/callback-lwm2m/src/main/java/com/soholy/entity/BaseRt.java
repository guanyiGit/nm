package com.soholy.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseRt {

    private Integer seq_id;
    private Object content;
    private LocalDateTime cretion_time;

}
