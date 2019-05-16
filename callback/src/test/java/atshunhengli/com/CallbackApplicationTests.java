package atshunhengli.com;

import atshunhengli.com.entity.iot.CmdType;
import atshunhengli.com.service.AppService;
import atshunhengli.com.service.DeviceCommandIotService;
import atshunhengli.com.service.DeviceManagerService;
import atshunhengli.com.service.RegisterCallBack;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallbackApplicationTests {

    org.slf4j.Logger logger = LoggerFactory.getLogger(CallbackApplicationTests.class);

    @Autowired
    private DeviceManagerService deviceManagerService;


    @Autowired
    private RegisterCallBack registerCallBack;


    @Autowired
    private AppService appService;

    @Autowired
    private DeviceCommandIotService deviceCommandIotService;


    @Test
    public void unsub() throws Exception {
        boolean flag = appService.deleteSubs();
        System.out.println(flag);
    }

    @Test
    public void sub() throws Exception {
        registerCallBack.register();
        System.out.println("ok");
    }

    @Test
    public void suendcmd() throws Exception {
        String deviceIdIot = "a0a61c82-7147-4132-9c78-6cee75282e11";
        JSONObject cmdJson = deviceCommandIotService.sendCommand(CmdType.INTERVALTIME, 300, 11, deviceIdIot);
        System.out.println(cmdJson);
    }

    @Test
    public void test2() {
        logger.debug("0000");
    }

    public static void main(String[] args) throws Exception {
        byte[] data = {
                0x05,
                0x08, 0x3F, 0x42, 0x2E, 0x00, 0x05, 0x5C, 0x15,
                0x50,
                0x66, 0x2D, 0x32, 0x31,
                0x64, 0x76, 0x30, 0x31,
                0x5B, (byte) 0x84, 0x20, 0x00
        };
        String string = new String(data, "ISO8859-1");

        System.out.println(string);
    }

    @Test
    public void findSubs() throws Exception {
        String str = appService.findSubs();
        System.out.println(str);
    }


    @Test
    public void testToJSON(){
        String jsonStr = "{'notifyType':'deviceInfoChanged','deviceId':'045cbe31-8893-4b07-b1ed-a4d6946cc0d4','gatewayId':'045cbe31-8893-4b07-b1ed-a4d6946cc0d4','deviceInfo':{'nodeId':null,'name':null,'description':null,'manufacturerId':null,'manufacturerName':null,'mac':null,'location':null,'deviceType':null,'model':null,'swVersion':null,'fwVersion':null,'hwVersion':null,'protocolType':null,'bridgeId':null,'status':'ONLINE','statusDetail':'NONE','mute':null,'supportedSecurity':null,'isSecurity':null,'signalStrength':null,'sigVersion':null,'serialNumber':null,'batteryLevel':null}}";
        JSONObject json = JSON.parseObject(jsonStr);
        System.out.println(json);
    }


}
