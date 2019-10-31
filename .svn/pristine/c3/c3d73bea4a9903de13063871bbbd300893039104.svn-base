package atshunhengli.com.controller;

import atshunhengli.com.entity.iot.CallbackType;
import atshunhengli.com.service.IotCallbackService;
import atshunhengli.com.utils.GlobalResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guanY
 * @version 1.0.0
 * @ClassName CallbackControlller
 * @Description (电信平台回调)
 * @Date 2018年4月12日 下午2:36:20
 */
@RestController
@RequestMapping(value = "/callback")
public class IotCallbackController {

    public static final String NONE = "none";

    private static final Logger logger = Logger.getLogger(IotCallbackController.class);

    @Autowired
    private IotCallbackService iotCallbackService;

    @RequestMapping("/test")
    public String test() {
        return new GlobalResult().successJSON();
    }

    /**
     *
     {
     "notifyType":"deviceDatasChanged",
     "requestId":"requestId",
     "deviceIdIot":"88888888",
     "gatewayId":"gatewayId",
     "service":"\u0005\b?B.\u0000\u0005\\\u0015Pf-21dv01[ \u0000"
     }
     */


    /**
     * @param body
     * @throws Exception
     * @Description (批量设备数据)
     */
    @RequestMapping(value = "/deviceDatasChanged")
    public String deviceDatasChanged(@RequestBody String body) throws Exception {
        logger.info(body);
        JSONObject json = JSON.parseObject(body);
        String notifyType = json.getString("notifyType");
        if (!CallbackType.deviceDatasChanged.toString().equals(notifyType)) {
            logger.warn("电信凭条参数错误！");
        }
        iotCallbackService.deviceDatasChangedMsg(json);
        return NONE;
    }

    /**
     * @param body
     * @throws Exception
     * @Description (绑定设备通知 - - 首次登录时)
     */
    @RequestMapping(value = "/bindDevice")
    public String bindDevice(@RequestBody String body) throws Exception {
        logger.info(body);
        JSONObject json = JSON.parseObject(body);
        String notifyType = json.getString("notifyType");
        if (CallbackType.bindDevice.equals(notifyType)) {
            logger.warn("电信凭条参数错误！");
        }
        iotCallbackService.bindDevicetMsg(json);
        return NONE;
    }

    /**
     * @param body
     * @throws Exception
     * @Description (设备事件通知)
     */
    @RequestMapping(value = "/deviceEvent")
    public void deviceEvent(@RequestBody String body) throws Exception {
        logger.info(body);
        JSONObject json = JSON.parseObject(body);
        String notifyType = json.getString("notifyType");
        if (CallbackType.deviceEvent.equals(notifyType)) {
            logger.warn("电信凭条参数错误！");
            return;
        }
        iotCallbackService.deviceEventMsg(json);
    }

    /**
     * @param body
     * @throws Exception
     * @Description (设备响应命令通知)
     */
    @RequestMapping(value = "/commandRsp")
    public String commandRsp(@RequestBody String body) throws Exception {
        logger.info(body);
        JSONObject json = JSON.parseObject(body);
        String notifyType = json.getString("notifyType");
        if (CallbackType.commandRsp.equals(notifyType)) {
            logger.warn("电信凭条参数错误！");
        }
        iotCallbackService.commandRspMsg(json);
        return NONE;
    }

    /**
     * @param body
     * @throws Exception
     * @Description (命令下发成功通知 - - 至网关)
     */
    @RequestMapping(value = "/messageConfirm")
    public String messageConfirm(@RequestBody String body) throws Exception {
        logger.info(body);
        JSONObject json = JSON.parseObject(body);
        String notifyType = json.getString("notifyType");
        if (CallbackType.messageConfirm.equals(notifyType)) {
            logger.warn("电信凭条参数错误！");
        }
        iotCallbackService.messageConfirmMsg(json);
        return NONE;
    }

    /**
     * @param body
     * @throws Exception
     * @Description (设备删除)
     */
    @RequestMapping(value = "/deviceDeleted")
    public String deviceDeleted(@RequestBody String body) throws Exception {
        logger.info(body);
        JSONObject json = JSON.parseObject(body);
        String notifyType = json.getString("notifyType");
        if (CallbackType.deviceDeleted.equals(notifyType)) {
            logger.warn("电信凭条参数错误！");
            return notifyType;
        }
        iotCallbackService.deviceDeletedMsg(json);
        return NONE;
    }

    /**
     * @param body
     * @throws Exception
     * @Description (设备信息变化)
     */
    @RequestMapping(value = "/deviceInfoChanged")
    public String deviceInfoChanged(@RequestBody String body) throws Exception {
        try {
            //TODO 这里异常了
            logger.info("设备上线： " + body);
            JSONObject req = JSON.parseObject(body);
            String notifyType = req.getString("notifyType");
            if (CallbackType.deviceInfoChanged.equals(notifyType)) {
                logger.warn("电信凭条参数错误！");
            }
            iotCallbackService.deviceInfoChangedMsg(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }

    /**
     * @param body
     * @throws Exception
     * @Description (添加型设备)
     */
    @RequestMapping(value = "/deviceAdded")
    public String deviceAdded(@RequestBody String body) {
        try {
            //TODO 这里异常了
            logger.info(body);
            JSONObject req = JSON.parseObject(body);
            String notifyType = req.getString("notifyType");
            if (CallbackType.deviceAdded.equals(notifyType)) {
                logger.warn("电信凭条参数错误！");
            }
            iotCallbackService.deviceAddedMeg(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }

    /**
     * @param body
     * @throws Exception
     * @Description (数据变化推送)
     */
    @RequestMapping(value = "/deviceDataChanged")
    public String deviceDataChanged(@RequestBody String body) throws Exception {
        logger.info(body);
        JSONObject req = JSON.parseObject(body);
        String notifyType = req.getString("notifyType");
        if (CallbackType.deviceDataChanged.equals(notifyType)) {
            logger.warn("电信凭条参数错误！");
        }
        iotCallbackService.deviceDataChangedMeg(req);
        return NONE;
    }


}
