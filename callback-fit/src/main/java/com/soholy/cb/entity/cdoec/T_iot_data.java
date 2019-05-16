package com.soholy.cb.entity.cdoec;

import java.time.LocalDateTime;

public class T_iot_data {
    private Integer seq;

    private String source;

    private String full;

    private String codec;

    private Integer type;

    private LocalDateTime local_Time;

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setLocal_Time(LocalDateTime local_Time) {
        this.local_Time = local_Time;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof T_iot_data))
            return false;
        T_iot_data other = (T_iot_data) o;
        if (!other.canEqual(this))
            return false;
        Object this$seq = getSeq(), other$seq = other.getSeq();
        if ((this$seq == null) ? (other$seq != null) : !this$seq.equals(other$seq))
            return false;
        Object this$source = getSource(), other$source = other.getSource();
        if ((this$source == null) ? (other$source != null) : !this$source.equals(other$source))
            return false;
        Object this$full = getFull(), other$full = other.getFull();
        if ((this$full == null) ? (other$full != null) : !this$full.equals(other$full))
            return false;
        Object this$codec = getCodec(), other$codec = other.getCodec();
        if ((this$codec == null) ? (other$codec != null) : !this$codec.equals(other$codec))
            return false;
        Object this$type = getType(), other$type = other.getType();
        if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
            return false;
        Object this$local_Time = getLocal_Time(), other$local_Time = other.getLocal_Time();
        return ((this$local_Time == null) ? (other$local_Time != null) : !this$local_Time.equals(other$local_Time)) ? false : true;
    }

    protected boolean canEqual(Object other) {
        return other instanceof T_iot_data;
    }


    public String toString() {
        return "T_iot_data(seq=" + getSeq() + ", source=" + getSource() + ", full=" + getFull() + ", codec=" + getCodec() + ", type=" + getType() + ", local_Time=" + getLocal_Time() + ")";
    }

    public T_iot_data() {
    }

    public T_iot_data(Integer seq, String source, String full, String codec, Integer type, LocalDateTime local_Time) {
        this.seq = seq;
        this.source = source;
        this.full = full;
        this.codec = codec;
        this.type = type;
        this.local_Time = local_Time;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public String getSource() {
        return this.source;
    }

    public String getFull() {
        return this.full;
    }

    public String getCodec() {
        return this.codec;
    }

    public Integer getType() {
        return this.type;
    }

    public LocalDateTime getLocal_Time() {
        return this.local_Time;
    }
}
