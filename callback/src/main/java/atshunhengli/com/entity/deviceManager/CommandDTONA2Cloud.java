package atshunhengli.com.entity.deviceManager;

import com.alibaba.fastjson.JSONObject;


public class CommandDTONA2Cloud {

    private CommandNA2CloudHeader header;
    private JSONObject body;
    
    public CommandNA2CloudHeader getHeader() {
        return header;
    }
    
    public void setHeader(CommandNA2CloudHeader header) {
        this.header = header;
    }
    
    public JSONObject getBody() {
        return body;
    }
    
    public void setBody(JSONObject body) {
        this.body = body;
    }
    
}
