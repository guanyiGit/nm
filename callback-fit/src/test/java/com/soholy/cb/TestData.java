package com.soholy.cb;

import com.alibaba.fastjson.JSON;
import com.soholy.cb.utils.ByteUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/*

{
	"notifyType":"deviceDatasChanged",
	"services":[
		{
			"serviceType":"service",
			"data":
				{
					"value":"test_value"
				},
			"eventTime":"20190517T030345Z",
			"serviceId":"service"
		}
	],
	"deviceId":"3f618136-043c-4051-abe2-b41c4f9fbabb",
	"gatewayId":"3f618136-043c-4051-abe2-b41c4f9fbabb"
}


 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestData {

    @lombok.Data
    class Input {
        private String notifyType = "deviceDatasChanged";
        private String deviceId = "3f618136-043c-4051-abe2-b41c4f9fbabb";
        private String gatewayId = "3f618136-043c-4051-abe2-b41c4f9fbabb";
        private List<service> services;
    }

    @lombok.Data
    class service {
        private String serviceId = "service";
        private String serviceType = "service";
        private String eventTime = "20190517T030345Z";
        private Data data;

    }

    @lombok.Data
    class Data {
        private String value;
    }

    public String getStr(byte[] in) throws UnsupportedEncodingException {
        service service = new service();
        Input input = new Input();
        input.setServices(Arrays.asList(service));
        Data data = new Data();
        service.setData(data);
        data.setValue(new String(in, code));
        Base64.Decoder decoder = Base64.getDecoder();
        return JSON.toJSONString(input);
    }

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }

    int verison = 170;

    String code = "ISO8859-1";

    byte[] versionBytes = new byte[1];

    {
        ByteUtils.intTobyte1(verison, versionBytes, 0);

    }

    byte[] gps = {
//            versionBytes[0],//版本 170
            0x10,//请求码
            0x08, 0x3F, 0x42, 0x2E, 0x00, 0x05, 0x5C, 0x15,//imei 0863664600059221
            0x2d, //电量 45
//            0x04, (byte) 0xB0, //电压(1200mv)
            0x66, 0x2D, 0x32, 0x31, //厂商编号 (f-21
            0x64, 0x76, 0x30, 0x31, //设备型号(dv01)
            0x5B, (byte) 0x84, 0x20, 0x00, //时间(2018/8/28 00:00:00)
            0x42, (byte) 0xF6, 0x00, (byte) 0xA1,  //经度(123.001230)
            0x41, (byte) 0xC9, 0x00, 0x01 // 纬度(25.125001)
    };

    @Test
    public void test1() throws UnsupportedEncodingException {
        String input = new String(gps, code);
        System.out.println(input);
    }

//    @Test
//    public void test2() throws UnsupportedEncodingException {
//        String in = "?B. \u0005\\\\\u0015-f-21dv01[\u0084  Bö ¡AÉ \u0001";
//        byte[] out = in.getBytes(code);
//        System.out.println(out);
//    }


    @Test
    public void testBatchgenController() throws Exception {
        String content = getStr(gps);
        mvc.perform(MockMvcRequestBuilders.get("/callback/deviceDatasChanged")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));

    }

}
