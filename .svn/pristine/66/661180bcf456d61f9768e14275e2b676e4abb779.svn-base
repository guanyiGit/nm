package com.excel;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.devicemanagement.entities.ImportDevice;
import com.devicemanagement.service.DeviceInfoService;
import com.sys.controller.BaseController;
import com.utils.ExcelUtiles;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController extends BaseController {

    @Autowired
    DeviceInfoService deviceInfoService;

    @RequestMapping("export")
    public void export(HttpServletResponse response){

        //模拟从数据库获取需要导出的数据
//        List<ImportDevice> personList = new ArrayList<>();
//        ImportDevice person1 = new ImportDevice("路飞","1",new Date());
//        ImportDevice person2 = new ImportDevice("娜美","2", new Date());
//        ImportDevice person3 = new ImportDevice("索隆","1", new Date());
//        ImportDevice person4 = new ImportDevice("小狸猫","1", new Date());
//        personList.add(person1);
//        personList.add(person2);
//        personList.add(person3);
//        personList.add(person4);

        //导出操作
//        ExcelUtiles.exportExcel(personList,"花名册","草帽一伙",ImportDevice.class,"海贼王.xls",response);
    }
    @PostMapping("importDevice")
    public R importExcel(@RequestParam("file") MultipartFile file) {
        Long deptId = getUser().getDeptId();
        Long userId = getUserId();
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(0);
        importParams.setHeadRows(1);
        importParams.setSheetNum(1);
        List<ImportDevice> list = ExcelUtiles.importExcel(file, importParams, ImportDevice.class);
        //TODO 保存数据库
        list.stream().forEach(device->{
            device.setDeptId(deptId);
            device.setImportBy(userId);
        });
        if(deviceInfoService.batchInsert(list)>0){
            return R.ok();
        }
        return R.error();

    }

    public static void main(String[] args){
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(2);
        importParams.setSheetNum(1);
        List<ImportDevice> list = ExcelUtiles.importExcel("C:\\Users\\yjy-05\\Desktop\\测试.xlsx", importParams, ImportDevice.class);
        list.stream().forEach(device->{
            System.out.println(device);
        });

    }

}
 
 
 
