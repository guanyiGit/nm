package com.soholy.service.aep;

import com.alibaba.fastjson.JSONObject;
import com.soholy.pojo.vo.AddDevice;
import com.soholy.pojo.vo.CreateSub;

public interface AepService {
    /**
     * 命令下发
     *
     * @param bytes
     * @param deviceIdIot
     * @return result
     * commandId
     * code
     */
       /*
     {
        "code": 0,
        "msg": "ok",
        "result": {
                "tenantId": "10007905",
                "taskId": 219245,//指令下发的指令Id
                "deviceId": "521c8e53fea14a958d0de24e57273c7e",
                "controlType": null,
                "deviceSn": null,
                "productId": 10000741,
                "payloadType": 0,
                "serviceId": "8002",
                "serviceName": "bgbg",
                "content": "{\"isTr\":1,\"datasetId\":\"8002\",\"value\":{\"gfg\":2},\"taskId\":3}",
                "status": 1,
                "ttl": 7200,
                "level": 0,
                "deviceTaskId": 3,
                "imei": "123098978676612",//imei号
                "isTr": null,
                "dataType": null,
                "createTime": 1542875084068,
                "finishTime": null,
                "resultCode": null,
                "resultPayload": null,
                "operator": "string",
                "productProtocol": 3,
                "type": 6
        }
    }

     */
    JSONObject sendComand(byte[] bytes, String deviceIdIot);

    /**
     * 批量获取设备信息
     *
     * @return
     */
    String findDevies();

    /**
     * 批量设备信息
     *
     * @return
     */
    String findDevie(String deviceIdIot);

    /**
     * 批量删除设备
     *
     * @return {
     * "code": 0,
     * "msg": "string",
     * "result": {}
     * }
     */
    String delDevices(String[] deviceIds);

    /*
        "code": 0,
        "msg": "ok",
        "result": {
            "deviceId": "b0850dda049b4117a3c6a9466b78c297",
            "deviceName": "151964040592564",
            "dmpId": null,
            "tenantId": "10176562",
            "productId": 10007954,
            "imei": "151964040592564",
            "imsi": null,
            "firmwareVersion": null,
            "deviceStatus": 0,
            "deviceStatusStr": null,
            "token": null,
            "secret": null,
            "autoObserver": 0,
            "lastTime": null,
            "createTime": null,
            "createBy": "GUANYI",
            "updateTime": null,
            "updateBy": null,
            "isDelete": null,
            "apn": null,
            "iotVer": null,
            "mvendor": null,
            "mver": null,
            "netStatus": null,
            "onlineAt": null,
            "offlineAt": null,
            "tupVendorId": null,
            "tupDeviceModel": null,
            "tupDeviceType": null,
            "tupIsProfile": 1,
            "psk": null,
            "nbDeviceResourceInfos": null
        }
     */

    /**
     * 增加设备
     *
     * @return
     */
    String createDevice(AddDevice addDevice);

    /**
     * 查看订阅信息
     *
     * @return
     */
    String querySubs();

    /**
     * 创建设备北向订阅
     * subTypes  subUrl
     *
     * @return
     */
    String createSub(CreateSub createSub);


    /**
     * 删除设备北向订阅信息
     *
     * @param subId
     * @return
     */
    String delSub(String subId);

    /**
     * 查询单个指令详情
     *
     * @param taskId
     * @return
     */
    String queryCommand(String taskId);

}
