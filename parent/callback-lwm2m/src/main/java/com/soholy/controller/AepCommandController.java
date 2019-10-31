package com.soholy.controller;

import com.soholy.pojo.aep.em.CmdType_lwM2M;
import com.soholy.service.command.DeviceCommandIotService;
import com.soholy.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lwm2m")
public class AepCommandController {


    @Autowired
    DeviceCommandIotService deviceCommandIotService;

    //http://119.147.209.163:8081/nm/lwm2m/command/5005fd3312394dd69275763273c531ad?mode=1&mVal=1
    //http://119.147.209.163:8081/nm/lwm2m/command/5005fd3312394dd69275763273c531ad?mode=2&mVal=300
    /**
     * mode:指令类型,非空，int32
     * 		0:开机回复
     * 		1:模式切换
     * 		2:设备当前模式的上传间隔调整
     * mVal:指令值,非空,int32
     * 		当type = 0时，模式切换指令，val可选值为：
     * 			1:成功激活
     * 			2:未激活
     * 		当type = 1时，模式切换指令，val可选值为：
     * 			1:普通模式
     * 			2:省电模式
     * 		当type = 2时，间隔设置指令，val为普通数值，单位秒
     * @param imei
     * @param mode
     * @param mVal
     * @return
     */
    @RequestMapping("/command/{imei}")
    public R sender(@PathVariable String imei,
                    @RequestParam Integer mode,
                    @RequestParam Integer mVal) {

        if (StringUtils.isNotBlank(imei)) {
            //  1模式切换 2其他
            // （0健康管理模式 1追踪模式）
            CmdType_lwM2M cmdType = mode == 1 ? CmdType_lwM2M.PATTERN_CHCKD : CmdType_lwM2M.SET_INTERVAL;
            return R.ok(deviceCommandIotService.sendCommand_v2_lwM2M(cmdType, "" + mVal, null, imei));
        }
        return R.error();
    }
}
