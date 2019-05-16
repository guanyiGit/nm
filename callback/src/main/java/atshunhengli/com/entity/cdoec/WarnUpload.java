package atshunhengli.com.entity.cdoec;

import java.util.Date;

public class WarnUpload extends UploadBean {

    private Date warnTime;

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }


    public WarnUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WarnUpload(Date warnTime) {
        super();
        this.warnTime = warnTime;
    }

}
