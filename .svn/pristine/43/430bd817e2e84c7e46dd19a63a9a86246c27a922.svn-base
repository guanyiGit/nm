package com.devicemanagement.entities;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@Accessors(chain = true)
@Data
@ExcelTarget("importDevice")
public class ImportDevice  {
    @Excel(name = "IMEI",orderNum="0" )
    private  String imei;
    @Excel(name = "溯源Id",orderNum="1" )
    private  String device_no;
    @Excel(name = "品牌",orderNum="2" )
    private  String brand;
    @Excel(name = "型号",orderNum="3" )
    private  String model;
    @Excel(name = "类型",orderNum="4" )
    private int type;
    @Excel(name = "批次",orderNum="5" )
    private  String batch;
    @Excel(name = "生产厂商",orderNum="6" )
    private  String firm;
    @Excel(name = "出厂日期",exportFormat = "yyyy/MM/dd",orderNum="7" )
    private  Date dateOfProduction;
    @Excel(name = "失效日期",exportFormat = "yyyy/MM/dd",orderNum="8" )
    private  Date deadline;

    private  Long deptId;
    private  Long importBy;
}
