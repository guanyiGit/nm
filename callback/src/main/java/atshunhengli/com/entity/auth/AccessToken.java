package atshunhengli.com.entity.auth;

import java.io.Serializable;

public class AccessToken implements Serializable {

    /**
     * @Field @serialVersionUID : TODO(explain)
     */
    private static final long serialVersionUID = -7929395629918359231L;
    // 范围，默认值default
    private String scope;
    // 鉴权token类型，默认值bearer
    private String tokenType;
    // 平台生成并返回accessToken的有效时间，单位秒
    private Integer expiresIn;
    // Oauth 2.0 鉴权参数
    private String accessToken;
    // Oauth 2.0 鉴权参数，用来刷新accessToken。（1个月的有效期）
    private String refreshToken;

    @Override
    public String toString() {
        return "AccessToken [scope=" + scope + ", tokenType=" + tokenType + ", expiresIn=" + expiresIn
                + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken + "]";
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
