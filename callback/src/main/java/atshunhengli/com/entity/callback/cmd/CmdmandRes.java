package atshunhengli.com.entity.callback.cmd;

/**
 * 
 * @ClassName CommandRes
 * @Description (iot命令响应)
 * @author guanY
 * @Date 2018年9月10日 下午1:25:17
 * @version 1.0.0
 */
public class CmdmandRes {

    private String deviceId;
    private String commandId;
    private Result result;

    public class Result {

        private String resultCode;
        private String resultDetail;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultDetail() {
            return resultDetail;
        }

        public void setResultDetail(String resultDetail) {
            this.resultDetail = resultDetail;
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
