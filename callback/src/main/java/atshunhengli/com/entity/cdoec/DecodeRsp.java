package atshunhengli.com.entity.cdoec;

import java.util.Date;

public class DecodeRsp {

    private int mid;
    private int resultCode;
    private String IMEI;
    private Date rspTime;
    private String deviceNo;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String iMEI) {
        IMEI = iMEI;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Date getRspTime() {
        return rspTime;
    }

    public void setRspTime(Date rspTime) {
        this.rspTime = rspTime;
    }

    @Override
    public String toString() {
        return "DecodeRsp [mid=" + mid + ", resultCode=" + resultCode + ", IMEI=" + IMEI + ", rspTime=" + rspTime
                + ", getIMEI()=" + getIMEI() + ", getMid()=" + getMid() + ", getResultCode()=" + getResultCode()
                + ", getRspTime()=" + getRspTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    public DecodeRsp(int mid, int resultCode, String iMEI, Date rspTime) {
        super();
        this.mid = mid;
        this.resultCode = resultCode;
        IMEI = iMEI;
        this.rspTime = rspTime;
    }

    public DecodeRsp() {
        super();
    }

}
