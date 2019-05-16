package atshunhengli.com.service.impl;

import atshunhengli.com.entity.callback.cmd.CmdmandRes;
import atshunhengli.com.entity.iot.CmdType;
import atshunhengli.com.mapper.TDeviceCommandMapper;
import atshunhengli.com.service.DeviceCommandService;
import com.entities.TDeviceCommand;
import com.entities.TDeviceCommandExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@PropertySource({"classpath:config/conf.properties"})
public class DeviceCommandServiceImpl implements DeviceCommandService {

    @Autowired
    private TDeviceCommandMapper tDeviceCommandMapper;

    @Value("${iot.codec.command.value}")
    private String cmdValue;

    @Override
    public TDeviceCommand findDeviceCmdDataByMid(int mid) {
        System.err.println(cmdValue);
        TDeviceCommandExample example = new TDeviceCommandExample();
        example.createCriteria().andCmdMidEqualTo(mid);
        List<TDeviceCommand> tDeviceCommands = tDeviceCommandMapper.selectByExample(example);
        if (tDeviceCommands != null && tDeviceCommands.size() == 1) {
            return tDeviceCommands.get(0);
        }
        return null;
    }

    @Override
    public boolean modifyDeviceCmdByMidAndDeviceImei(int mid, int resultCode, String imei, Date rspTime) {
        return tDeviceCommandMapper.modifyDeviceCmdByMidAndDeviceImei(mid, resultCode, imei, rspTime) == 1;
    }

    @Override
    public boolean saveCommand(Long deviceNo, CmdType cmdType, int cmdValue, long operatorId) throws Exception {
        if (deviceNo == null) {
            return false;
        }
        int mid = (int) (Math.random() * 99999);

        int cmdTypeInt = -1;
        if (cmdType.equals(CmdType.INTERVALTIME)) {// 设置时间间隔
            cmdTypeInt = 1;
        } else if (cmdType.equals(CmdType.WORKPATTERN)) {// 设置工作模式
            cmdTypeInt = 0;
        }
        if (cmdTypeInt != -1) {// 保存命令信息
            TDeviceCommand tDeviceCommand = new TDeviceCommand();
            tDeviceCommand.setCmdMid(mid);
            tDeviceCommand.setCmdStatus(0);
            tDeviceCommand.setCmdType(cmdTypeInt);
            tDeviceCommand.setCmdValue(cmdValue);
            tDeviceCommand.setCreationTime(new Date());
            tDeviceCommand.setDeviceNo(deviceNo);
            tDeviceCommand.setOperatorId(operatorId);
            return tDeviceCommandMapper.insertSelective(tDeviceCommand) == 1;
        }

        return false;
    }

    @Override
    public boolean modefyCommandStatusByIotCmdId(CmdmandRes cmdmandRes) {
        if (cmdmandRes != null && cmdmandRes.getResult() != null) {
            if ("SENT".equalsIgnoreCase(cmdmandRes.getResult().getResultCode())) {// 平台已下发
                TDeviceCommand record = new TDeviceCommand();
                record.setCmdStatus(1);
                TDeviceCommandExample example = new TDeviceCommandExample();
                example.createCriteria().andIotCommandIdEqualTo(cmdmandRes.getCommandId());
                return tDeviceCommandMapper.updateByExampleSelective(record, example) == 1;
            }
        }
        return false;
    }

}
