package atshunhengli.com.entity.deviceManager;

public class CommandNA2CloudHeader {

    // 如果填写该requestId，就是用填写的requestId标识一个命令，这里的requestId不能重复，由发起端APP按照下面的规则保证其唯一性：Generate
    // seqNum：APP生成序列号seqNum，在POST请求BODY中携带，序列号规则采用：UUID_XXXX，UUID会根据JAVA自带算法生成，每次APP启动后生成一个新的值，后续使用时不变，直到下次APP重新启动再生成一个新的UUID值，XXXX的取值范围：0001-9999，达到9999后重新从0001开始，如此循环
    private String requestId;
    // Enum 是否要确认消息，不需要确认消息：NOACK ，需要确认消息：ACK，其它值无效
    private String mode;
    // 表示消息发布者的地址：
    // App发起的请求：/users/{userId}
    // NA发起的请求：/{serviceName}
    // 平台发起的请求：/cloud/{serviceName}
    private String from;
    // CLOUD、GATEWAY
    private String toType;
    // 接收者地址
    private String to;
    // 命令名称，profile命令中定义的命令名（commandName），如："DISCOVERY"
    private String method;
    // 回调的url地址。必须使用HTTPS信道回调地址，同时回调地址中必须指定回调地址的端口。(说明：HTTP信道只可用于调测)
    private String callbackURL;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

}
