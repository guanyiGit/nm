package com.soholy.service.aep.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctg.ag.sdk.biz.AepDeviceCommandClient;
import com.ctg.ag.sdk.biz.AepDeviceManagementClient;
import com.ctg.ag.sdk.biz.AepSubscribeNorthClient;
import com.ctg.ag.sdk.biz.aep_device_command.CreateCommandRequest;
import com.ctg.ag.sdk.biz.aep_device_command.CreateCommandResponse;
import com.ctg.ag.sdk.biz.aep_device_command.QueryCommandRequest;
import com.ctg.ag.sdk.biz.aep_device_command.QueryCommandResponse;
import com.ctg.ag.sdk.biz.aep_device_management.*;
import com.ctg.ag.sdk.biz.aep_subscribe_north.*;
import com.ctg.ag.sdk.core.constant.Scheme;
import com.soholy.pojo.vo.*;
import com.soholy.service.aep.AepService;
import com.soholy.utils.ByteUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class AepServiceImpl implements AepService {
    private static final Logger logger = LoggerFactory.getLogger(AepServiceImpl.class);

    @Value("${aep.nb.subUrl}")
    private String subUrl;

    @Value("${aep.nb.appKey}")
    private String appKey;

    @Value("${aep.nb.appSecret}")
    private String appSecret;

    @Value("${aep.nb.MasterKey}")
    private String MasterKey;

    @Value("${aep.nb.productId}")
    private Integer productId;

    private final String orName = System.getenv().getOrDefault("COMPUTERNAME", "root");

    @Override
    public JSONObject sendComand(byte[] input, String deviceIdIot) {
        AepDeviceCommandClient client = null;
        try {
            client = AepDeviceCommandClient
                    .newClient()
                    .scheme(Scheme.HTTPS)
                    .appKey(appKey)
                    .appSecret(appSecret)
                    .build();

            CreateCommandRequest request = new CreateCommandRequest();

            // set your request params here
            // request.setParamMasterKey(MasterKey);	// single value
            // request.addParamMasterKey(MasterKey);	// or multi values
            // request.setBody([BINARY DATA])
            request.setParamMasterKey(MasterKey);
            ComandSend csend = new ComandSend();
            csend.setDeviceId(deviceIdIot);
            csend.setOperator(orName);
            csend.setProductId(productId);

            int cmdType = 2;
            if (0 == cmdType) {//0、nb网关传透 新设备 或者
                Content nbNewC = new Content();
                nbNewC.setPayload(ByteUtils.byteTohex(input));
                csend.setContent(nbNewC);
            } else if (1 == cmdType) {//nb设备（老版本） perofile版本

            } else if (2 == cmdType) {//lwM2M透传新设备
                M2mContent m2mContent = new M2mContent();
                m2mContent.setPayload(ByteUtils.byteTohex(input));
                csend.setContent(m2mContent);
            }

            request.setBody(JSON.toJSONString(csend).getBytes("UTF-8"));

            CreateCommandResponse response = client.CreateCommand(request);
            if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                JSONObject returnJson = new JSONObject();

                JSONObject resultJson = JSON.parseObject(new String(response.getBody(), "UTF-8"));
                CmdResult resultCmd = resultJson.getObject("result", CmdResult.class);

                returnJson.put("commandId", resultCmd.getTaskId());
                returnJson.put("result", resultJson);
                returnJson.put("code", resultJson.getIntValue("code"));

                return returnJson;
            }
            logger.info("send command content:" + ReflectionToStringBuilder.toString(response, ToStringStyle.MULTI_LINE_STYLE));

            // more request
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("命令下发失败", e);
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }

        return null;
    }

    @Override
    public String findDevies() {
        AepDeviceManagementClient client = null;
        try {
            client = AepDeviceManagementClient
                    .newClient()
                    .scheme(Scheme.HTTPS)
                    .appKey(appKey)
                    .appSecret(appSecret)
                    .build();

            QueryDeviceListRequest request = new QueryDeviceListRequest();
            request.addParamMasterKey(MasterKey);
            request.addParamProductId(productId);

            // request.setParam..  	// set your request params here
            QueryDeviceListResponse response = client.QueryDeviceList(request);
            System.out.println(ReflectionToStringBuilder.toString(response, ToStringStyle.MULTI_LINE_STYLE));
            System.out.println(new String(response.getBody(), "utf-8"));

            if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                return new String(response.getBody(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;

    }

    @Override
    public String findDevie(String deviceIdIot) {
        if (StringUtils.isNotBlank(deviceIdIot)) {
            AepDeviceManagementClient client = null;
            try {
                client = AepDeviceManagementClient
                        .newClient()
                        .scheme(Scheme.HTTPS)
                        .appKey(appKey)
                        .appSecret(appSecret)
                        .build();
                QueryDeviceRequest request = new QueryDeviceRequest();
                request.addParamMasterKey(MasterKey);
                request.addParamProductId(productId);
                request.setParamDeviceId(deviceIdIot);

                QueryDeviceResponse response = client.QueryDevice(request);
                System.out.println(ReflectionToStringBuilder.toString(response, ToStringStyle.MULTI_LINE_STYLE));
                System.out.println(new String(response.getBody(), "utf-8"));

                if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                    return new String(response.getBody(), "utf-8");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (client != null) {
                    client.shutdown();
                }
            }
        }
        return null;
    }

    @Override
    public String delDevices(String[] deviceIds) {
        AepDeviceManagementClient client = null;
        try {
            client = AepDeviceManagementClient
                    .newClient()
                    .scheme(Scheme.HTTPS)
                    .appKey(appKey)
                    .appSecret(appSecret)
                    .build();

            DeleteDeviceRequest request = new DeleteDeviceRequest();
            request.addParamMasterKey(MasterKey);
            request.addParamProductId(productId);

            if (deviceIds != null && deviceIds.length > 0) {
                StringBuffer deviceIdsSB = new StringBuffer();
                for (int i = 0; i < deviceIds.length; i++) {
                    String temp = i == 0 ? deviceIds[i] : "," + deviceIds[i];
                    deviceIdsSB.append(temp);
                }
                request.addParamDeviceIds(deviceIdsSB.toString());
            }

            // request.setParam..  	// set your request params here
            DeleteDeviceResponse response = client.DeleteDevice(request);
            System.err.println(new String(response.getBody(), "utf-8"));

            if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                return new String(response.getBody(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;
    }

    @Override
    public String createDevice(AddDevice addDevice) {
        AepDeviceManagementClient client = null;
        try {
            client = AepDeviceManagementClient
                    .newClient()
                    .scheme(Scheme.HTTPS)
                    .appKey(appKey)
                    .appSecret(appSecret)
                    .build();

            CreateDeviceRequest request = new CreateDeviceRequest();
            request.addParamMasterKey(MasterKey);

            addDevice.setOperator(orName);
            addDevice.setProductId(productId);

            request.setBody(JSON.toJSONString(addDevice).getBytes("UTF-8"));

            // request.setParam..  	// set your request params here
            CreateDeviceResponse response = client.CreateDevice(request);

            if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                return new String(response.getBody(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;
    }

    @Override
    public String querySubs() {
        AepSubscribeNorthClient client = null;
        try {
            client = AepSubscribeNorthClient
                    .newClient()
                    .scheme(Scheme.HTTPS)
                    .appKey(appKey)
                    .appSecret(appSecret)
                    .build();

            GetSubscriptionsListRequest request = new GetSubscriptionsListRequest();
            request.setParamMasterKey(MasterKey);
            request.setParamProductId(productId);
            request.setParamPageNow(1);
            request.setParamPageSize(50);
            request.setParamSubType(1);

            GetSubscriptionsListResponse response = client.GetSubscriptionsList(request);
            if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                return new String(response.getBody(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e.toString());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;
    }

    @Override
    public String createSub(CreateSub createSub) {
        AepSubscribeNorthClient client = null;
        try {
            client = AepSubscribeNorthClient
                    .newClient()
                    .scheme(Scheme.HTTPS)
                    .appKey(appKey)
                    .appSecret(appSecret)
                    .build();

            CreateSubscriptionRequest request = new CreateSubscriptionRequest();
            request.addParamMasterKey(MasterKey);

            createSub.setOperator(orName);
            createSub.setProductId(productId);
            createSub.setSubLevel(1);
            request.setBody(JSON.toJSONString(createSub).getBytes("UTF-8"));

            // set your request params here
            // request.setParamMasterKey("e75da91530544d248cfbc179ba0e4256");	// single value
            // request.addParamMasterKey("e75da91530544d248cfbc179ba0e4256");	// or multi values
            // request.setBody([BINARY DATA])
            CreateSubscriptionResponse response = client.CreateSubscription(request);
            System.out.println(response);
            if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                return new String(response.getBody(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.shutdown();
        }
        return null;
    }

    @Override
    public String delSub(String subId) {
        AepSubscribeNorthClient client = null;
        try {
            client = AepSubscribeNorthClient
                    .newClient()
                    .scheme(Scheme.HTTPS)
                    .appKey(appKey)
                    .appSecret(appSecret)
                    .build();

            DeleteSubscriptionRequest request = new DeleteSubscriptionRequest();
            request.addParamMasterKey(MasterKey);
            request.addParamSubId(subId);
            request.addParamProductId(productId);
            request.addParamSubLevel(1);
            DeleteSubscriptionResponse response = client.DeleteSubscription(request);
            System.out.println(response);
            if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                return new String(response.getBody(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;
    }

    @Override
    public String queryCommand(String taskId) {
        AepDeviceCommandClient client = null;
        try {
            client = AepDeviceCommandClient
                    .newClient()
                    .scheme(Scheme.HTTPS)
                    .appKey(appKey)
                    .appSecret(appSecret)
                    .build();

            QueryCommandRequest request = new QueryCommandRequest();
            request.setParamProductId(productId);
            request.setParamTaskId(taskId);

            QueryCommandResponse response = client.QueryCommand(request);
            System.out.println(response);
            if (response != null && 200 == response.getStatusCode() && response.getBody() != null) {
                return new String(response.getBody(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null)
                client.shutdown();
        }
        return null;
    }

    @PostConstruct
    public void initSuns() {
        //其他订阅
        CreateSub otherSub = new CreateSub();
        //2表示设备响应命令通知、3表示设备事件上报、4表示设备上下线通知、9表示TUP合并数据上报
        List<Integer> list = Arrays.asList(2, 3, 4, 9);
        otherSub.setSubTypes(list.toArray(new Integer[list.size()]));
        otherSub.setSubUrl(subUrl + "/callback/other_v2");
        this.createSub(otherSub);

        //数据订阅
        CreateSub dataSub = new CreateSub();
        //2表示设备响应命令通知、3表示设备事件上报、4表示设备上下线通知、9表示TUP合并数据上报
        list = Arrays.asList(1);
        dataSub.setSubTypes(list.toArray(new Integer[list.size()]));
//        dataSub.setSubUrl(subUrl + "/callback/deviceDatasChanged_v2");
        dataSub.setSubUrl(subUrl + "/callback/lwm2m");
        this.createSub(dataSub);
    }

}
