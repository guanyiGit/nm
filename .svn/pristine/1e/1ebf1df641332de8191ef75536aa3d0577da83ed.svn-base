package atshunhengli.com.service.impl;

import atshunhengli.com.common.ConstantIot;
import atshunhengli.com.entity.auth.AccessToken;
import atshunhengli.com.service.AuthService;
import atshunhengli.com.utils.HttpClientUtil;
import atshunhengli.com.utils.HttpResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName TokenServiceImpl
 * @Description (鉴权相关)
 * @author guanY
 * @Date 2018年7月26日 上午9:59:22
 * @version 1.0.0
 */
@PropertySource({"classpath:config/conf.properties"})
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = Logger.getLogger(AuthServiceImpl.class);

    @Value("${iot.application.appid}")
    private String appid;

    @Value("${iot.application.secret}")
    private String secret;

    @Value("${iot.server.address.host}")
    private String iotServerHost;

    @Value("${iot.server.address.post}")
    private String iotServerPost;

    /**
     * 获取iot服务器地址url
     *
     * @return
     */
    @Override
    public String iotServerBaseUrl() {
        return "https://" + this.iotServerHost + ":" + this.iotServerPost;
    }


    @Override
    public Map<String, String> setAuthentication() throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("app_key", this.appid);
        headers.put("Authorization", "Bearer " + this.getAccessToken().getAccessToken());
        headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        return headers;
    }

    public AccessToken getAccessToken() throws Exception {
        String url = this.iotServerBaseUrl() + ConstantIot.APP_AUTH;
        Map<String, String> paramsObj = new HashMap<String, String>();
        paramsObj.put("appId", this.appid);
        paramsObj.put("secret", this.secret);
        HttpResult resp = HttpClientUtil.executePostParams(null, url, null, null, null, paramsObj);
        String content = resp.getContent();
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("getAccessToken error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(content, AccessToken.class);
    }

    @Override
    public AccessToken refreshToken() throws Exception {
        String url = this.iotServerBaseUrl() + ConstantIot.REFRESH_TOKEN;
        Map<String, String> paramsObj = new HashMap<String, String>();
        paramsObj.put("appId", this.appid);
        paramsObj.put("secret", this.secret);
        paramsObj.put("refreshToken", this.getAccessToken().getRefreshToken());

        HttpResult resp = HttpClientUtil.executePostParams(null, url, null, null, null,
                JSONObject.toJSONString(paramsObj));
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("getAccessToken error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        String content = resp.getContent();
        return JSONObject.parseObject(content, AccessToken.class);
    }

    public boolean logoutAuth() throws Exception {
        String url = this.iotServerBaseUrl() + ConstantIot.APP_AUTH_LOGOUT;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", this.getAccessToken());
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, null, jsonObject.toJSONString(), null);
        if (resp == null || resp.getStatusCode() != 204) {
            logger.error("logoutAuth error ,result statusCode:" + resp.getStatusCode());
            return false;
        }
        return true;
    }

}
