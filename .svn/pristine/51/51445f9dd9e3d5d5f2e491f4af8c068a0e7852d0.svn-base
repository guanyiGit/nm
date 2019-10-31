package com.soholy.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.soholy.entity.TDeviceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TDeviceInfoMapperTest {

    @Autowired
    private TDeviceInfoMapper tDeviceInfoMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void getLearn() {
        int count = tDeviceInfoMapper.update(null,
                Wrappers.
                        <TDeviceInfo>lambdaUpdate()
                        .eq(TDeviceInfo::getId, 134)
                        .set(TDeviceInfo::getModel, "lw2m2测试modify"));
        System.out.println(count);
    }

    @Test
    public void testRestTemplate() {
        String url = "http://localhost:10086/convert/wifi/?bssid={BSSID}&rssi={RSSI}";

        Map<String, String> params = new HashMap<String, String>();
        String bssid = "f1:43:47:f6:c6:e1";
        float rssi = 475f;
        params.put("BSSID", bssid);
        params.put("RSSI", String.valueOf(rssi));
        ResponseEntity<JSONObject> entity = restTemplate.exchange(url, HttpMethod.GET, null, JSONObject.class, params);
        System.out.println(entity);
        System.out.println(entity.getBody());

    }
}