package com.posiition.VO;

import java.io.Serializable;
import java.util.List;

public class FcnceRefFenceDetailChile implements Serializable {

    private List<FcnceRefFenceDetail> fcnceRefFenceDetails;

    private FcnceRefFenceDetailChile fcnceRefFenceDetailChile;

    public List<FcnceRefFenceDetail> getFcnceRefFenceDetails() {
        return fcnceRefFenceDetails;
    }

    public void setFcnceRefFenceDetails(List<FcnceRefFenceDetail> fcnceRefFenceDetails) {
        this.fcnceRefFenceDetails = fcnceRefFenceDetails;
    }

    public FcnceRefFenceDetailChile getFcnceRefFenceDetailChile() {
        return fcnceRefFenceDetailChile;
    }

    public void setFcnceRefFenceDetailChile(FcnceRefFenceDetailChile fcnceRefFenceDetailChile) {
        this.fcnceRefFenceDetailChile = fcnceRefFenceDetailChile;
    }
}
