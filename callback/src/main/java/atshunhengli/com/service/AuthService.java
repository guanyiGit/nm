package atshunhengli.com.service;

import atshunhengli.com.entity.auth.AccessToken;

import java.util.Map;

public interface AuthService {

    /**
     * 获取iot服务器地址url
     *
     * @return
     */
    public String iotServerBaseUrl();

    /**
     * @return
     * @throws Exception
     * @Description (获取AccessToken)
     */
    public abstract AccessToken getAccessToken() throws Exception;

    /**
     * @return
     * @throws Exception
     * @Description (刷新 token)
     */
    public abstract AccessToken refreshToken() throws Exception;

    /**
     * @return
     * @throws Exception
     * @Description (注销鉴权信息)
     */
    public boolean logoutAuth() throws Exception;

    /**
     * @return
     * @throws Exception
     * @Description (设置请求头)
     */
    Map<String, String> setAuthentication() throws Exception;
}
