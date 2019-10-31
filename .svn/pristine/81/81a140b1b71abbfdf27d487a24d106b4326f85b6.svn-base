package com.soholy.service.command.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.soholy.entity.TDeviceCommand;
import com.soholy.entity.TDeviceInfo;
import com.soholy.mapper.TDeviceCommandMapper;
import com.soholy.mapper.TDeviceInfoMapper;
import com.soholy.pojo.aep.em.CmdType_lwM2M;
import com.soholy.service.aep.AepService;
import com.soholy.service.codec.CodecService;
import com.soholy.service.command.DeviceCommandIotService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceCommandIotServiceV2Impl implements DeviceCommandIotService {

    Logger logger = LoggerFactory.getLogger(DeviceCommandIotServiceV2Impl.class);

    @Autowired
    private CodecService codecService;

    @Autowired
    private AepService aepService;

    @Autowired
    private TDeviceCommandMapper tDeviceCommandMapper;

    @Autowired
    private TDeviceInfoMapper tDeviceInfoMapper;


    @Override
    public JSONObject sendCommand_v2_lwM2M(CmdType_lwM2M cmdType, String cmdValue, Long mid, String imei) {
        if (StringUtils.isNotBlank(imei)) {
            List<TDeviceInfo> devices = tDeviceInfoMapper.selectList(Wrappers.<TDeviceInfo>lambdaQuery()
                    .eq(TDeviceInfo::getImei, imei));
            if (devices != null && devices.size() > 0) {
                mid = mid == null ? (int) (Math.random() * Integer.MAX_VALUE) : mid;
                TDeviceInfo tDeviceInfo = devices.get(0);
                byte[] input = codecService.generateComanmd_lwM2M(tDeviceInfo.getImei(), cmdType, cmdValue, mid);
                try {
                    String rest = new String(input, "utf-8");
                } catch (UnsupportedEncodingException e) {
                }
                return aepService.sendComand(input, tDeviceInfo.getDeviceIotId());
            }
        }
        return null;
    }

    public long generateMid() {
        return (int) (Math.random() * 9999999);
    }

    @Override
    public boolean depositoryCommandCheckAndSend(String imei, String deviceIdIot) {
        List<TDeviceCommand> cmds = tDeviceCommandMapper.selectList(Wrappers.<TDeviceCommand>lambdaQuery()
                .eq(TDeviceCommand::getDeviceNo, imei)
                .eq(TDeviceCommand::getCmdStatus, 0));
        if (cmds != null) {
            Long count = cmds.stream()
                    .filter(x -> x != null)
                    .filter(x -> x.getCmdType() != null && x.getCmdValue() != null)
                    .map(x -> {
                        //命令类型 1模式切换 2上传间隔调整
                        Integer type = x.getCmdType();
                        //命令值（1普通模式 2省电模式）
                        Integer cmdValue = x.getCmdValue();
                        CmdType_lwM2M cmdType = type == 1 ? CmdType_lwM2M.PATTERN_CHCKD : CmdType_lwM2M.PATTERN_CHCKD;

                        JSONObject rtJson = sendCommand_v2_lwM2M(cmdType, "" + cmdValue, x.getDeviceCommandId(), deviceIdIot);
                        return 0 == rtJson.getIntValue("code");
                    })
                    .filter(x -> x)
                    .collect(Collectors.counting());
            if (count != null && count > 0) {
                logger.warn(count + "条数据保存失败");
            }
            return true;
        }
        return false;
    }
}
