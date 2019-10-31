package atshunhengli.com.entity.iot;

import com.alibaba.fastjson.JSON;

/**
 * Title: 统一响应结构 
 * { "meta": { "success": true, "message": "ok" }, "data": ... }
 */
public class GlobalResult {

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";

    private Meta meta; // 元数据
    private Object data; // 响应内容

    public GlobalResult success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public String successJSON() {
        this.meta = new Meta(true, OK);
        return JSON.toJSONString(this);
    }

    public String boolResultJSON(boolean isOk) {
        if (isOk) {
            this.meta = new Meta(true, OK);
        } else {
            this.meta = new Meta(false, ERROR);
        }
        return JSON.toJSONString(this);
    }

    /**
     * 
     * @Description (空值返回err )
     * @param data
     * @return
     */
    public String objResultJSON(Object... data) {
        if (data != null) {
            this.meta = new Meta(true, OK);
        } else {
            this.meta = new Meta(false, ERROR);
            this.data = data;
        }
        return JSON.toJSONString(this);
    }

    public GlobalResult success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public String successJSON(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return JSON.toJSONString(this);
    }

    public String successJSON(Object... data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return JSON.toJSONString(this);
    }

    public GlobalResult failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public String failureJSON() {
        this.meta = new Meta(false, ERROR);
        return JSON.toJSONString(this);
    }

    public GlobalResult failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public String failureJSON(String message) {
        this.meta = new Meta(false, message);
        return JSON.toJSONString(this);
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    /**
     * Title: 请求元数据
     */
    public class Meta {

        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
