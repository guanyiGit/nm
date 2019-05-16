package atshunhengli.com.utils;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @ClassName HttpClientUtil
 * @Description (apache httpclient工具类)
 * @author guanY
 * @Date 2018年6月15日 上午11:21:08
 * @version 1.0.0
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final String DEFAULT_CHARSET = "UTF-8";// 默认请求编码
    private static final int DEFAULT_SOCKET_TIMEOUT = 5000;// 默认等待响应时间(毫秒)
    private static final int DEFAULT_RETRY_TIMES = 0;// 默认执行重试的次数

    private static void setHttpClientConnectionPool(PoolingHttpClientConnectionManager cm) {
        if (cm != null) {
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 执行HttpGet请求
     *
     * @param httpClient
     *            HttpClient客户端实例，传入null会自动创建一个
     * @param url
     *            请求的远程地址
     * @param charset
     *            请求编码，默认UTF8
     * @param isCloseHttpClien
     *            执行请求结束后是否关闭HttpClient客户端实例
     * @param headers
     *            (可设置[referer:连接地址]和[Cookie：cookie信息]) 请求头信息
     * @param paramsObj
     *            (可是是String类型和Map类型) 请求参数
     * @return HttpResult(状态码，结果)
     * @throws IOException
     * @throws URISyntaxException
     */
    public static HttpResult executeGetParams(CloseableHttpClient httpClient, String url, String charset,
            Boolean isCloseHttpClien, Map<String, String> headers, Map<String, String> paramsObj)
            throws IOException, URISyntaxException {
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = executeGetResponse(httpClient, url, headers, paramsObj, charset);
            // Http请求状态码和结果
            return getResult(httpResponse, charset);
        } finally {
            CloseHttpClien(httpClient, isCloseHttpClien, httpResponse);
        }
    }

    /**
     * 执行HttpPost请求
     *
     * @param httpClient
     *            HttpClient客户端实例，传入null会自动创建一个
     * @param url
     *            请求的远程地址
     * @param charset
     *            请求编码，默认UTF8
     * @param isCloseHttpClien
     *            执行请求结束后是否关闭HttpClient客户端实例
     * @param headers
     *            (可设置[referer:连接地址]和[Cookie：cookie信息]) 请求头信息
     * @param paramsObj
     *            (可是是String类型和Map<String,String>类型) 请求参数
     * @return HttpResult(状态码，结果)
     * @throws ParseException
     * @throws IOException
     */
    public static HttpResult executePostParams(CloseableHttpClient httpClient, String url, String charset,
            Boolean isCloseHttpClien, Map<String, String> headers, Object paramsObj)
            throws ParseException, IOException {
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = executePostResponse(httpClient, url, headers, paramsObj, charset);
            // Http请求状态码和结果
            return getResult(httpResponse, charset);
        } finally {
            CloseHttpClien(httpClient, isCloseHttpClien, httpResponse);
        }
    }

    /**
     * 执行HttpGet请求
     *
     * @param httpClient
     *            HttpClient客户端实例，传入null会自动创建一个
     * @param url
     *            请求的远程地址
     * @param charset
     *            请求编码，默认UTF8
     * @param isCloseHttpClien
     *            执行请求结束后是否关闭HttpClient客户端实例
     * @return HttpResult(状态码，结果)
     * @throws URISyntaxException
     * @throws IOException
     */
    public static HttpResult executeBaseGet(CloseableHttpClient httpClient, String url, String charset,
            Boolean isCloseHttpClien) throws IOException, URISyntaxException {
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = executeGetResponse(httpClient, url, null, null, charset);
            // Http请求状态码和结果
            return getResult(httpResponse, charset);
        } finally {
            CloseHttpClien(httpClient, isCloseHttpClien, httpResponse);
        }
    }

    /**
     * 执行HttpPost请求
     *
     * @param httpClient
     *            HttpClient客户端实例，传入null会自动创建一个
     * @param url
     *            请求的远程地址
     * @param charset
     *            请求编码，默认UTF8
     * @param isCloseHttpClien
     *            执行请求结束后是否关闭HttpClient客户端实例
     * @return HttpResult(状态码，结果)
     * @throws ParseException
     * @throws IOException
     */
    public static HttpResult executeBasePost(CloseableHttpClient httpClient, String url, String charset,
            Boolean isCloseHttpClien) throws ParseException, IOException {
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = executePostResponse(httpClient, url, null, null, charset);
            // Http请求状态码和结果
            return getResult(httpResponse, charset);
        } finally {
            CloseHttpClien(httpClient, isCloseHttpClien, httpResponse);
        }
    }

    /**
     * @Description (执行http请求)
     * @param url
     *            ：请求路径
     * @param charset
     *            ：编码格式
     * @param isCloseHttpClien
     *            ：连接后时候关闭
     * @param methodName
     *            ：请求方法名称
     * @return HttpResult：请求结果
     * @throws ParseException
     * @throws IOException
     * @throws URISyntaxException
     */
    public static HttpResult executeBaseHttp(String url, String methodName, Boolean isCloseHttpClien, String charset)
            throws ParseException, IOException, URISyntaxException {

        CloseableHttpResponse httpResponse = null;
        // 创建一个CloseableHttpClient
        CloseableHttpClient httpClient = createHttpClient();
        try {
            httpResponse = executeHttpResponse(url, methodName, null, null, charset, httpClient);
            // Http请求状态码和结果
            return getResult(httpResponse, charset);
        } finally {
            CloseHttpClien(httpClient, isCloseHttpClien, httpResponse);
        }
    }

    /**
     * @Description (执行http请求)
     * @param url 请求路径
     * @param methodName 请求方法名称
     * @param headers 请求参数头
     * @param paramsObj 请求参数体
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws URISyntaxException
     */
    public static HttpResult executeHttpParams(String url, String methodName, Map<String, String> headers,
            Object paramsObj) throws ParseException, IOException, URISyntaxException {
        return executeHttpParams(url, methodName, null, headers, paramsObj, null);
    }

    /**
     * @Description (执行http请求)
     * @param url 请求路径
     * @param methodName 请求方法名称
     * @param isCloseHttpClien 连接后时候关闭
     * @param headers 请求参数头
     * @param paramsObj 请求参数体
     * @param charset 编码格式(面膜人UTF-8)
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws URISyntaxException
     */
    public static HttpResult executeHttpParams(String url, String methodName, Boolean isCloseHttpClien,
            Map<String, String> headers, Object paramsObj, String charset)
            throws ParseException, IOException, URISyntaxException {

        if (null == charset || "".equals(charset)) {
            charset = DEFAULT_CHARSET;
        }

        CloseableHttpResponse httpResponse = null;
        // 创建一个CloseableHttpClient
        CloseableHttpClient httpClient = createHttpClient();
        try {
            httpResponse = executeHttpResponse(url, methodName, headers, paramsObj, charset, httpClient);
            // Http请求状态码和结果
            return getResult(httpResponse, charset);
        } finally {
            CloseHttpClien(httpClient, isCloseHttpClien, httpResponse);
        }
    }

    private static CloseableHttpResponse executeHttpResponse(String url, String methodName, Map<String, String> headers,
            Object paramsObj, String charset, CloseableHttpClient httpClient)
            throws ClientProtocolException, IOException, URISyntaxException {
        logger.info("execute Http requst ...");
        if (methodName == null || methodName == "") {
            methodName = "GET";
        }

        if (httpClient == null) {
            httpClient = createHttpClient();
        }
        URIBuilder ub = new URIBuilder(url);
        // urlEncode转码,ub.setPath(url);

        HttpRequestBase httpRequestBase = null;
        if (methodName.equalsIgnoreCase("GET")) {
            httpRequestBase = new HttpGet();
        } else if (methodName.equalsIgnoreCase("POST")) {
            httpRequestBase = new HttpPost();
        } else if (methodName.equalsIgnoreCase("PUT")) {
            httpRequestBase = new HttpPut();
        } else if (methodName.equalsIgnoreCase("DELETE")) {
            httpRequestBase = new HttpDelete();
        } else if (methodName.equalsIgnoreCase("PATCH")) {
            httpRequestBase = new HttpPatch();
        } else {
            logger.error("request mehtod name error!");
            return null;
        }

        if (headers != null) {
            setHeaders(headers, httpRequestBase);
        }

        // 设置参数
        if (paramsObj != null) {
            if (httpRequestBase instanceof HttpEntityEnclosingRequestBase) {
                HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase) httpRequestBase;
                HttpEntity httpEntity = getEntity(paramsObj, charset);
                httpEntityEnclosingRequestBase.setEntity(httpEntity);
            } else {// get delete方法时
                if (Map.class.isInstance(paramsObj)) {
                    @SuppressWarnings("unchecked")
                    Map<String, String> paramsMap = (Map<String, String>) paramsObj;
                    ArrayList<NameValuePair> pairs = covertParams2NVPS(paramsMap);
                    ub.setParameters(pairs);
                } else if (String.class.isInstance(paramsObj)) {
                    String paramStr = (String) paramsObj;
                    ub.setParameter(paramStr, null);
                } else {
                    logger.error("request paramsObj type error!");
                }
            }
        }

        URI uri = ub.build();
        httpRequestBase.setURI(uri);
        return httpClient.execute(httpRequestBase);
    }

    /**
     * 创建一个默认的可关闭的HttpClient
     * 
     * @return
     */
    public static CloseableHttpClient createHttpClient() {
        return createHttpClient(DEFAULT_RETRY_TIMES, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * 创建一个可关闭的HttpClient
     *
     * @param socketTimeout
     *            请求获取数据的超时时间
     * @return
     */
    public static CloseableHttpClient createHttpClient(int socketTimeout) {
        return createHttpClient(DEFAULT_RETRY_TIMES, socketTimeout);
    }

    /**
     * 创建一个可关闭的HttpClient
     *
     * @param retryTimes
     *            重试次数，小于等于0表示不重试
     * @param socketTimeout
     *            请求获取数据的超时时间
     * @return
     */
    public static CloseableHttpClient createHttpClient(int retryTimes, int socketTimeout) {
        Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(5000);// 设置连接超时时间，单位毫秒
        builder.setConnectionRequestTimeout(1000);// 设置从connect Manager获取Connection
                                                  // 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
        if (socketTimeout >= 0) {
            builder.setSocketTimeout(socketTimeout);// 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
        }
        RequestConfig defaultRequestConfig = builder.setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        // 开启HTTPS支持
        enableSSL();
        // 创建可用Scheme
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
        // 创建ConnectionManager，添加Connection配置信息
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);

        // 设置连接池信息
        setHttpClientConnectionPool(connectionManager);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        if (retryTimes > 0) {
            setRetryHandler(httpClientBuilder, retryTimes);
        }
        CloseableHttpClient httpClient = httpClientBuilder.setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();
        return httpClient;
    }

    /**
     * 开启SSL支持
     */
    private static void enableSSL() {
        try {
            // String demo_base_Path = System.getProperty("user.dir");
            String selfcertpath = "/cert/outgoing.CertwithKey.pkcs12";
            String trustcapath = "/cert/ca.jks";

            // 导入客户端证书到证书库
            KeyStore selfCert = KeyStore.getInstance("pkcs12");
            selfCert.load(HttpClientUtil.class.getResourceAsStream(selfcertpath), "IoM@1234".toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
            kmf.init(selfCert, "IoM@1234".toCharArray());

            // 导入服务器的CA证书，添加信任
            KeyStore caCert = KeyStore.getInstance("jks");
            caCert.load(HttpClientUtil.class.getResourceAsStream(trustcapath), "Huawei@123".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
            tmf.init(caCert);

            SSLContext ssl = SSLContext.getInstance("TLS");
            // 自己的证书，服务器证书，加密随机数
            ssl.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            // ssl.init(null, new TrustManager[] {manager }, null);

            socketFactory = new SSLConnectionSocketFactory(ssl, NoopHostnameVerifier.INSTANCE);// 设置域名不检验
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SSLConnectionSocketFactory socketFactory;

    // HTTPS网站一般情况下使用了安全系数较低的SHA-1签名，因此首先我们在调用SSL之前需要重写验证方法，取消检测SSL。
    @SuppressWarnings("unused")
    private static TrustManager manager = new X509TrustManager() {

        @Override
        // 返回受信任的X509证书数组
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        // 检查服务器端证书
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        // 检查客户端证书
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    };

    /**
     * 为httpclient设置重试信息
     *
     * @param httpClientBuilder
     * @param retryTimes
     */
    private static void setRetryHandler(HttpClientBuilder httpClientBuilder, final int retryTimes) {
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= retryTimes) {
                    // Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    // Timeout
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    // Unknown host
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    // Connection refused
                    return false;
                }
                if (exception instanceof SSLException) {
                    // SSL handshake exception
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    // 如果请求被认为是幂等的，那么就重试
                    // Retry if the request is considered idempotent
                    return true;
                }
                return false;
            }
        };
        httpClientBuilder.setRetryHandler(myRetryHandler);
    }

    /**
     * @param httpClient
     *            httpclient对象
     * @param url
     *            执行GET的URL地址
     * @param headers
     *            (可设置[referer:连接地址]和[Cookie：cookie信息]) 请求头信息
     * @param paramsObj
     *            (可是是String类型和Map类型) 请求参数
     * @param charset
     *            编码信息
     * @throws IOException
     * @throws URISyntaxException
     */
    private static CloseableHttpResponse executeGetResponse(CloseableHttpClient httpClient, String url,
            Map<String, String> headers, Map<String, String> paramsObj, String charset)
            throws IOException, URISyntaxException {
        logger.info("execute get requst ...");
        charset = getCharset(charset);
        if (httpClient == null) {
            httpClient = createHttpClient();
        }
        HttpGet httpGet = null;
        // 创建uri
        URIBuilder builder = new URIBuilder(url);

        if (paramsObj != null) {
            ArrayList<NameValuePair> pairs = covertParams2NVPS(paramsObj);
            builder.setParameters(pairs);
        }
        URI uri = builder.build();

        httpGet = new HttpGet(uri);
        if (headers != null) {
            setHeaders(headers, httpGet);
        }
        return httpClient.execute(httpGet);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, String> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Entry<String, String> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return pairs;
    }

    /**
     * @param httpClient
     *            httpclient对象
     * @param url
     *            执行POST的URL地址
     * @param headers
     *            (可设置[referer:连接地址]和[Cookie：cookie信息]) 请求头信息
     * @param paramsObj
     *            请求参数
     * @param charset
     *            编码信息
     * @return CloseableHttpResponse
     * @throws ClientProtocolException
     * @throws IOException
     */
    private static CloseableHttpResponse executePostResponse(CloseableHttpClient httpClient, String url,
            Map<String, String> headers, Object paramsObj, String charset) throws ClientProtocolException, IOException {
        logger.info("execute post requst ...");
        if (httpClient == null) {
            httpClient = createHttpClient();
        }
        HttpPost httpPost = new HttpPost(url);
        if (headers != null) {
            setHeaders(headers, httpPost);
        }
        // 设置参数
        HttpEntity httpEntity = getEntity(paramsObj, charset);
        if (httpEntity != null) {
            httpPost.setEntity(httpEntity);
        }
        for (Header header : httpPost.getAllHeaders()) {
            logger.info("httpPost Headers:--->" + header);
        }
        logger.info("httpPost body:--->" + httpPost.getEntity());

        return httpClient.execute(httpPost);
    }

    private static void setHeaders(Map<String, String> headers, HttpRequestBase httpRequestBase) {
        for (Iterator<Entry<String, String>> it = headers.entrySet().iterator(); it.hasNext();) {
            Entry<String, String> entry = it.next();
            if (entry != null) {
                httpRequestBase.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 根据参数获取请求的Entity
     *
     * @param paramsObj
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    private static HttpEntity getEntity(Object paramsObj, String charset) throws UnsupportedEncodingException {
        if (paramsObj == null) {
            logger.info("当前未传入参数信息，无法生成HttpEntity");
            return null;
        }
        if (Map.class.isInstance(paramsObj)) {// 当前是map数据
            @SuppressWarnings("unchecked")
            Map<String, String> paramsMap = (Map<String, String>) paramsObj;
            List<NameValuePair> list = getNameValuePairs(paramsMap);
            UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(list, charset);
            httpEntity.setContentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
            return httpEntity;
        } else if (String.class.isInstance(paramsObj)) {// 当前是string对象，可能是
            String paramsStr = (String) paramsObj;
            StringEntity httpEntity = new StringEntity(paramsStr, charset);
            if (paramsStr.startsWith("{")) {
                httpEntity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            } else if (paramsStr.startsWith("<")) {
                httpEntity.setContentType(ContentType.APPLICATION_XML.getMimeType());
            } else {
                httpEntity.setContentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
            }
            return httpEntity;
        } else {
            logger.info("当前传入参数不能识别类型，无法生成HttpEntity");
        }
        return null;
    }

    /**
     * 将map类型参数转化为NameValuePair集合方式
     *
     * @param paramsMap
     * @return
     */
    private static List<NameValuePair> getNameValuePairs(Map<String, String> paramsMap) {
        List<NameValuePair> list = new ArrayList<>();
        if (paramsMap == null || paramsMap.isEmpty()) {
            return list;
        }
        for (Entry<String, String> entry : paramsMap.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    /**
     * @Description (关闭CloseHttpClien)
     * @param httpClient
     * @param isCloseHttpClien
     * @param httpResponse
     */
    private static void CloseHttpClien(CloseableHttpClient httpClient, Boolean isCloseHttpClien,
            CloseableHttpResponse httpResponse) {
        if (httpResponse != null) {
            try {
                httpResponse.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isCloseHttpClien != null && isCloseHttpClien && httpClient != null) {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从结果中获取出String数据
     *
     * @param httpResponse
     *            http结果对象
     * @param charset
     *            编码信息
     * @return String
     * @throws ParseException
     * @throws IOException
     */
    private static HttpResult getResult(CloseableHttpResponse httpResponse, String charset)
            throws ParseException, IOException {
        String result = null;
        if (httpResponse == null) {
            return null;
        }
        HttpResult httpResult = new HttpResult();
        httpResult.setStatusCode(httpResponse.getStatusLine().getStatusCode());

        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity, charset);
            httpResult.setContentType(entity.getContentType());
            httpResult.setContent(result);
            httpResult.setContentEncoding(entity.getContentEncoding());
            httpResult.setContentLength(entity.getContentLength());
        }
        EntityUtils.consume(entity);// 关闭应该关闭的资源，适当的释放资源 ;也可以把底层的流给关闭了
        return httpResult;
    }

    /**
     * 转化请求编码
     *
     * @param charset
     *            编码信息
     * @return String
     */
    private static String getCharset(String charset) {
        return charset == null ? DEFAULT_CHARSET : charset;
    }
}
