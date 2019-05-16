package com.soholy.cb;

import com.soholy.cb.utils.ByteUtils;

public class Main {

    public static void main(String[] args) {
        byte[] output  = {0x01 ,0x02};
        byte[] toArr = new byte[output.length + 1];
        ByteUtils.copyArrays(output, 0, output.length, toArr, 1);
        ByteUtils.intTobyte1(170, toArr, 0);
        System.out.println(ByteUtils.byte2Toint(output,0));
        int i = ByteUtils.byte1Toint(toArr, 0);

        System.out.println(ByteUtils.byte1Toint(toArr,0));
    }
}
