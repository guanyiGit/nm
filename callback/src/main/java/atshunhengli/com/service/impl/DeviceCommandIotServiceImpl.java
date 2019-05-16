package atshunhengli.com.service.impl;

import atshunhengli.com.common.ConstantIot;
import atshunhengli.com.entity.deviceManager.CommandDTOV4;
import atshunhengli.com.entity.iot.CmdType;
import atshunhengli.com.service.AuthService;
import atshunhengli.com.service.CodecService;
import atshunhengli.com.service.DeviceCommandIotService;
import atshunhengli.com.service.LogService;
import atshunhengli.com.utils.HttpClientUtil;
import atshunhengli.com.utils.HttpResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource({"classpath:config/conf.properties"})
public class DeviceCommandIotServiceImpl implements DeviceCommandIotService {

    Logger logger = LoggerFactory.getLogger(DeviceCommandIotServiceImpl.class);

    @Value("${iot.codec.server.name}")
    private String serviceId;

    @Value("${iot.codec.command.name}")
    private String commandName;

    @Value("${iot.codec.command.value}")
    private String commandValue;

    @Value("${iot.callback.host}")
    private String callbackHost;

    @Value("${iot.callback.port}")
    private String callbackPort;

    @Autowired
    private AuthService authService;

    @Autowired
    private CodecService codecService;

    @Autowired
    private LogService logService;

    /**
     * 获取回调服务器地址url
     *
     * @return
     */
    private String callbackUrl() {
        return "https://" + this.callbackHost + ":" + this.callbackPort;
    }

    @Override
    public String findCommandsByDeviceIotId(String deviceIdIot) throws Exception {
        String url = authService.iotServerBaseUrl() + ConstantIot.QUERY_DEVICE_CMD;
        if (StringUtils.isBlank(deviceIdIot)) {
            url += "?deviceId=" + deviceIdIot;
        }

        Map<String, String> headers = authService.setAuthentication();

        HttpResult response = HttpClientUtil.executeHttpParams(url, "GET", null, headers, null, null);

        if (response != null && response.getStatusCode() == 200) {
            return response.getContent();
        }
        return null;
    }

    /**
     * 生成命令的 mid
     *
     * @return
     */
    private int generateMid() {
        return (int) (Math.random() * 999);
    }

    @Override
    public JSONObject sendCommand(CmdType cmdType, Integer cmdValue, Integer mid, String deviceIotId) throws Exception {
        if (StringUtils.isNotBlank(deviceIotId)) {
            mid = mid == null ? this.generateMid() : mid;

            byte[] input = codecService.generateComanmd(cmdType, cmdValue, mid);
            String url = authService.iotServerBaseUrl() + ConstantIot.POST_ASYN_CMD;
            Map<String, String> headers = authService.setAuthentication();
            JSONObject root = new JSONObject();
            root.put("deviceId", deviceIotId);

            CommandDTOV4 dtov4 = new CommandDTOV4();
            dtov4.setServiceId(serviceId);
            dtov4.setMethod(commandName);
            Map<String, Object> paras = new HashMap<>();
            paras.put(commandValue, input);
            dtov4.setParas(paras);
            root.put("command", dtov4);
            root.put("callbackUrl", this.callbackUrl() + "/callback/cmdrsp");

            HttpResult result = HttpClientUtil.executeHttpParams(url, "POST", headers, root.toJSONString());
            if (result != null && result.getStatusCode() == 201) {
                JSONObject object = JSON.parseObject(result.getContent());
                JSONObject resultObj = new JSONObject();
                resultObj.put("commandId", object.getString("commandId"));
                resultObj.put("output", Arrays.toString(input));
                logService.printLog("cmd output:" + Arrays.toString(input));
                return resultObj;
//                return object.getString("commandId");
            }

        }
        return null;
    }


}
