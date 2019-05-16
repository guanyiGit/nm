package atshunhengli.com.entity.cdoec;

import java.util.List;

public class WifiUpload extends UploadBean {

    private int length;

    private List<WifiLocation> wifiLocation;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<WifiLocation> getWifiLocation() {
        return wifiLocation;
    }

    public void setWifiLocation(List<WifiLocation> wifiLocation) {
        this.wifiLocation = wifiLocation;
    }

    @Override
    public String toString() {
        return "WifiUpload [length=" + length + ", wifiLocation=" + wifiLocation + "]";
    }

}
