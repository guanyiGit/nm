package com.soholy.dogmanager.utils.httpclient;

import org.apache.http.Header;

import java.io.Serializable;

/**
 * Http请求返回的结果对象信�?
 * 
 * @author comven
 * @date 2016/10/22 11:29
 */
public class HttpResult implements Serializable {

    /**
     * @Field @serialVersionUID : TODO(explain)
     */
    private static final long serialVersionUID = 1L;
    private Integer statusCode;
    private String content;
    private Long contentLength;
    private Header contentEncoding;
    private Header contentType;

    public HttpResult() {
        super();
    }

    public HttpResult(Integer statusCode, String content, Long contentLength, Header contentEncoding, Header contentType) {
        super();
        this.statusCode = statusCode;
        this.content = content;
        this.contentLength = contentLength;
        this.contentEncoding = contentEncoding;
        this.contentType = contentType;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public Header getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(Header contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public Header getContentType() {
        return contentType;
    }

    public void setContentType(Header contentType) {
        this.contentType = contentType;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HttpResult [statusCode=");
        builder.append(statusCode);
        builder.append(", content=");
        builder.append(content);
        builder.append(", contentLength=");
        builder.append(contentLength);
        builder.append(", contentEncoding=");
        builder.append(contentEncoding);
        builder.append(", contentType=");
        builder.append(contentType);
        builder.append("]");
        return builder.toString();
    }

}
