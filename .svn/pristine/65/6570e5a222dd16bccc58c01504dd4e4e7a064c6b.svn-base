package com.soholy.service.codec.impl;

import com.alibaba.fastjson.JSON;
import com.soholy.mapper.AepDataMapper;
import com.soholy.pojo.aep.command.rq.BaseCommandAepRq;
import com.soholy.pojo.aep.em.CmdType_lwM2M;
import com.soholy.service.codec.CodecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;

@Service
public class CodecServiceImpl implements CodecService {

    @Autowired
    private AepDataMapper aepDataMapper;

    @Override
    public byte[] generateComanmd_lwM2M(String imei, CmdType_lwM2M cmdType, String cmdValue, Long mid) {
        BaseCommandAepRq rq = new BaseCommandAepRq();
        rq.setVersion("1.0");
        rq.setImei(imei);
        BaseCommandAepRq.CommandAepRqMode mode = new BaseCommandAepRq.CommandAepRqMode();
        mode.setMid(mid.toString());
        mode.setType(String.valueOf(cmdType.getType()));
        mode.setVal(cmdValue);
        mode.setTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        rq.setModes(Arrays.asList(mode));

        aepDataMapper.saves(Arrays.asList(JSON.toJSONString(rq)));
        System.err.println(JSON.toJSONString(rq));
        if (CmdType_lwM2M.PATTERN_CHCKD.equals(cmdType)) {//模式切換
            try {
                return JSON.toJSONString(rq).getBytes("UTF-8");
            } catch (UnsupportedEncodingException e1) {
            }
        } else if (CmdType_lwM2M.STARTING_UP.equals(cmdType)) { //开机激活
            try {
                return JSON.toJSONString(rq).getBytes("UTF-8");
            } catch (UnsupportedEncodingException e1) {
            }
        } else if (CmdType_lwM2M.SET_INTERVAL.equals(cmdType)) { //间隔时间设置
            try {
                return JSON.toJSONString(rq).getBytes("UTF-8");
            } catch (UnsupportedEncodingException e1) {
            }
        }

        return null;
    }
}
