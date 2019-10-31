package com.soholy.cb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.soholy.cb.dao.TDeviceCommandDb;
import com.soholy.cb.entity.TDeviceCommandEntity;
import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.cdoec.CallBackData;
import com.soholy.cb.entity.cdoec.DecodeRsp;
import com.soholy.cb.enums.CmdType;
import com.soholy.cb.service.TDeviceCommandService;
import com.soholy.cb.service.app.CmdService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TDeviceCommandServiceImpl implements TDeviceCommandService {
    private static final Logger log = Logger.getLogger(TDeviceCommandServiceImpl.class.getName());

    @Autowired
    private TDeviceCommandDb tDeviceCommandMapper;

    @Autowired
    private CmdService cmdService;

    public List<TDeviceCommandEntity> findCmdByNoAndStatus(String deviceNo, Integer status) {
        LambdaQueryWrapper<TDeviceCommandEntity> mrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(deviceNo))
            mrapper.eq(TDeviceCommandEntity::getDeviceNo, deviceNo);
        if (status != null)
            mrapper.eq(TDeviceCommandEntity::getCmdStatus, status);
        return this.tDeviceCommandMapper.selectList(mrapper);
    }

    public boolean updateById(TDeviceCommandEntity tdevCmd) {
        return (this.tDeviceCommandMapper.updateById(tdevCmd) == 1);
    }

    public void resStart(CallBackData data, TDeviceInfoEntity device) {
        if (data != null && data.getDataType().intValue() == 4) {
            int resultCode = 0;
            if (device != null && device.getStatus() != null && device.getStatus().intValue() == 1)
                resultCode = 1;
            try {
                this.cmdService.sendCommand(CmdType.STARTING_UP, Integer.valueOf(resultCode), Integer.valueOf(this.cmdService.generateMid()), device.getDeviceIotId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean cmdResHandle(DecodeRsp decodeRsp) {
        if (decodeRsp != null) {
            int resMid = decodeRsp.getMid();
            TDeviceCommandEntity command = new TDeviceCommandEntity();
            Instant instant = decodeRsp.getRspTime().toInstant();
            command.setCmdRspTime(instant.atZone(ZoneId.systemDefault()).toLocalDateTime());
            String deviceNo = decodeRsp.getIMEI();
            int code = 6;
            if (1 == decodeRsp.getResultCode())
                code = 5;
            command.setCmdStatus(Integer.valueOf(code));
            Integer cmdStatus = Integer.valueOf(0);
            return (1 == this.tDeviceCommandMapper.update(command, Wrappers.<TDeviceCommandEntity>lambdaUpdate()
                    .eq(TDeviceCommandEntity::getCmdMid, Integer.valueOf(resMid))
                    .eq(TDeviceCommandEntity::getDeviceNo, deviceNo)
                    .notIn(TDeviceCommandEntity::getCmdStatus, Arrays.asList(cmdStatus))));
        }
        return false;
    }
}
