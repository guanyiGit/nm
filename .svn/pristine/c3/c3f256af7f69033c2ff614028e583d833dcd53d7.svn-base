package com.soholy.service.impl;

import com.soholy.pojo.LwM2MPayLoad;
import com.soholy.pojo.OldTupPayLoad;
import com.soholy.pojo.PayLoad;
import com.soholy.pojo.TupPayload;
import com.soholy.service.SimpleDecode;
import com.soholy.utils.ByteUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Service
public class SimpleDecodeImpl implements SimpleDecode {

    @Override
    public byte[] simpleParas(PayLoad payload) {
        try {
            byte[] input = null;
            if (payload instanceof TupPayload) {
                TupPayload ins = (TupPayload) payload;
                input = Base64.getDecoder().decode(ins.getUpdata());
            } else if (payload instanceof OldTupPayLoad) {
                OldTupPayLoad ins = (OldTupPayLoad) payload;
                String outputStr = ins.getValue();
                input= outputStr.getBytes("ISO8859-1");
            }else if (payload instanceof LwM2MPayLoad) {
                LwM2MPayLoad in = (LwM2MPayLoad) payload;
                String outputStr = LwM2MCodec(in);
                input = ByteUtils.hexTobyte(outputStr);
                System.err.println(outputStr);
            }

            //数据解析
            return input;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String simpleParasToJson(PayLoad payload) {
        try {
            if (payload instanceof LwM2MPayLoad){
                return LwM2MCodec((LwM2MPayLoad) payload);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String LwM2MCodec(LwM2MPayLoad payload) throws UnsupportedEncodingException {
        byte[] output = Base64.getDecoder().decode(payload.getAPPdata());
        return new String(output, "utf-8");
    }


}
