package com.statisanalysis.serivce.impl;

import com.entities.AreaInfo;
import com.entities.TDogInfo;
import com.statisanalysis.dao.ComprehensiveReportDao;
import com.statisanalysis.dao.DogAmountDao;
import com.statisanalysis.entity.CompreReportVO;
import com.statisanalysis.entity.DogVO;
import com.statisanalysis.serivce.IComprehensiveReportService;
import com.statisanalysis.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/11 10:38
 * @Version 1.0
 */
@Service
public class ComprehensiveReportServiceImpl implements IComprehensiveReportService{
    @Autowired
    private ComprehensiveReportDao reportDao;
    @Autowired
    private DogAmountDao dogAmountDao;

    public List<Map<String,Object>> getMonth(Map<String,Object> map) {
        return reportDao.getMonth(map);
    }

    @Override
    public Integer getMonthTotal(Map<String, Object> map) {
        return reportDao.getMonthTotal(map);
    }

    @Override
    public List<Map<String,Object>> getCounties(Map<String, Object> map) {
        return reportDao.getCounties(map);
    }

    @Override
    public List<Map<String, Object>> getCountiesInfo(Integer orgId) {
        return reportDao.getCountiesInfo(orgId);
    }

    @Override
    public List<Integer> getTownId(Integer orgId) {
        return reportDao.getTownId(orgId);
    }

    @Override
    public List<TDogInfo> getDogsByTownId(Integer orgId) {
        return reportDao.getDogsByTownId(orgId);
    }

    /**
     * 统计某月的防疫情况
     *
     * @param map
     * @return
     */
    @Override
    public List<CompreReportVO> getAntiReport(Map<String, Object> params) throws Exception {
        Integer cljqNumCount = 0;           //存栏家犬合计(万条)
        Integer clllqNumCount = 0;          //存栏流浪犬合计（万条）
        Integer monthQuJiaNumCount = 0;     //本月-驱虫-家犬合计（条）
        Integer monthQuLiuNumCount = 0;     //本月-驱虫-流浪犬合计（条）
        Double monthFenJianNumCount = 0.0;  //本月-粪便-检测数合计
        Double monthFenYangNumCount = 0.0;  //本月-粪便-阳性数合计
        Double monthPouJianNumCount = 0.0;        //本月-剖检-检测数合计
        Double monthPouYangNumCount = 0.0;        //本月-剖检-阳性数合计
        Integer monthLiuNumCount = 0;           //本月-流浪犬处理数量合计（条）

        Integer yearQuJiaNumCount = 0;          //全年-驱虫数量-家犬合计（条）
        Integer yearQuLiuNumCount = 0;          //全年-驱虫数量-流浪犬合计（条）
        Double yearFenJianNumCount = 0.0;         //全年-粪便-检测数合计
        Double yearFenYangNumCount = 0.0;         //全年-粪便-阳性数合计
        Double yearPouJianNumCount = 0.0;         //全年-剖检-检测数合计
        Double yearPouYangNumCount = 0.0;         //全年-剖检-阳性数合计
        Integer yearLiuNumCount = 0;            //全年-流浪犬处理数量合计（条）
        List<CompreReportVO> result = new ArrayList<>();
        //查询本州组织下所有县组织所在的区域
        List<AreaInfo> areaList = dogAmountDao.getAreaInfoByOrgId(params);
        String perMonth = params.get("perMonth").toString();
        String[] split = perMonth.split("-");
        String nextMonth = DateUtils.getLastMonth(perMonth, 0, 1, 0);
        Map<String,Object> map = new HashMap<>();
        map.put("nextMonth",nextMonth); //yyyy-MM 下一月
        map.put("year",split[0]);       //yyyy     当前年
        //统计每个区域（县）的防治情况
        for (AreaInfo area:
             areaList) {
            CompreReportVO reportVO = new CompreReportVO();
            reportVO.setAreaName(area.getName());
            map.put("areaId",area.getId());
            List<DogVO> dogVOList = reportDao.getDogsByAreaId(map);
            //统计家犬存栏的数量
            Integer liveNum = countLiveNum(perMonth, dogVOList);
            cljqNumCount += liveNum;    //统计存栏家犬
            reportVO.setCljqNum(convertUnit(String.valueOf(liveNum)));  //将单位转成（万条）
            //统计本月累计流浪狗处理数量
            List<Date> strayDog = reportDao.getStrayDog(map);
            int strayNumMonth = countStrayNumMonth(perMonth,strayDog);  //从本年处理的流浪狗中统计属于本月处理的流浪狗数量
            //统计本年累计流浪狗处理数量
            int strayNumYear = strayDog.size();
            monthLiuNumCount += strayNumMonth;
            yearLiuNumCount += strayNumYear;
            reportVO.setMonthLiuNum(strayNumMonth);
            reportVO.setYearLiuNum(strayNumYear);
            //统计本年犬粪抗原检测数量（检测数、阳性数）
            Map<String, Object> fenMountYear = reportDao.getFenMount(map);
            Double[] doubles = countJianYangNum(fenMountYear);
            yearFenJianNumCount += doubles[0];
            yearFenYangNumCount += doubles[1];
            reportVO.setYearFenJianNum(doubles[0]);
            reportVO.setYearFenYangNum(doubles[1]);
            //统计本年剖检法检测数量（检测数、阳性数）
            Map<String, Object> pouMountYear = reportDao.getPouMount(map);
            Double[] doubles2 = countJianYangNum(pouMountYear);
            yearPouJianNumCount += doubles2[0];
            yearPouYangNumCount += doubles2[1];
            reportVO.setYearPouJianNum(doubles2[0]);
            reportVO.setYearPouYangNum(doubles2[1]);

            //统计本月犬粪抗原检测数量（检测数、阳性数）
            map.remove("year");     //移除年份条件
            map.put("perMonth",perMonth);   //添加月份条件
            Map<String, Object> fenMountMonth = reportDao.getFenMount(map);
            Double[] doubles1 = countJianYangNum(fenMountMonth);
            monthFenJianNumCount += doubles1[0];
            monthFenYangNumCount += doubles1[1];
            reportVO.setMonthFenJianNum(doubles1[0]);
            reportVO.setMonthFenYangNum(doubles1[1]);
            //统计本月剖检法检测数量（检测数、阳性数）
            Map<String, Object> pouMountMonth = reportDao.getPouMount(map);
            Double[] doubles3 = countJianYangNum(pouMountMonth);
            monthPouJianNumCount += doubles3[0];
            monthPouYangNumCount += doubles3[1];
            reportVO.setMonthPouJianNum(doubles3[0]);
            reportVO.setMonthPouYangNum(doubles3[1]);
            result.add(reportVO);
        }
        //添加合计内容到最后
        CompreReportVO reportVO = new CompreReportVO();
        reportVO.setAreaName("合计");         //此处严格来说是不应该这样的，但是为了技术的实现方便
        reportVO.setCljqNum(convertUnit(String.valueOf(cljqNumCount)));
        //流浪犬合计todo
        //驱虫合计todo
        DecimalFormat df = new DecimalFormat("#.00");
        reportVO.setMonthFenJianNum(Double.parseDouble(df.format(monthFenJianNumCount)));
        reportVO.setMonthFenYangNum(Double.parseDouble(df.format(monthFenYangNumCount)));
        reportVO.setMonthPouJianNum(Double.parseDouble(df.format(monthPouJianNumCount)));
        reportVO.setMonthPouYangNum(Double.parseDouble(df.format(monthPouYangNumCount)));
        reportVO.setMonthLiuNum(monthLiuNumCount);
        reportVO.setYearFenJianNum(Double.parseDouble(df.format(yearFenJianNumCount)));
        reportVO.setYearFenYangNum(Double.parseDouble(df.format(yearFenYangNumCount)));
        reportVO.setYearPouJianNum(Double.parseDouble(df.format(yearPouJianNumCount)));
        reportVO.setYearPouYangNum(Double.parseDouble(df.format(yearPouYangNumCount)));
        reportVO.setYearLiuNum(yearLiuNumCount);
        result.add(reportVO);
        return result;
    }

    public Double[] countJianYangNum(Map<String, Object> map) {
        Double[] res = new Double[2];
        if(map != null) {
            Object jianNum = map.get("jianNum");
            if(jianNum != null) {
                res[0] = Double.parseDouble(jianNum.toString());
            }else {
                res[0] = 0.00;
            }
            Object yangNum = map.get("yangNum");
            if(yangNum != null) {
                res[1] = Double.parseDouble(yangNum.toString());
            }else {
                res[1] = 0.00;
            }
        }else{
            res[0] = 0.00;
            res[1] = 0.00;
        }
        return res;
    }


    public Integer countLiveNum(String month,List<DogVO> dogList) {
        Integer num = 0;
        int res1 = DateUtils.getNumOfDate(month);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (DogVO dog:
             dogList) {
            Date dealTime = dog.getDealTime();
            if(dealTime != null) {
                String format = sdf.format(dealTime);
                int res2 = DateUtils.getNumOfDate(format);
                if(res2 > res1) {
                    num++;
                }
            }else {
                num++;
            }
        }
        return num;
    }

    //
    public Integer countStrayNumMonth(String str,List<Date> dateList) {
        int num = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (Date date:
             dateList) {
            if(date != null) {
                String format = sdf.format(date);
                if(format.contains(str)) {
                    num++;
                }
            }
        }
        return num;
    }

    public String convertUnit(String liveNum) {
        BigDecimal bigDecimal = new BigDecimal(liveNum);
        // 转换为万条（除以10000）
        BigDecimal decimal = bigDecimal.divide(new BigDecimal("10000"));
        // 保留四位小数
        DecimalFormat formater = new DecimalFormat("0.0000");
        // 四舍五入
        formater.setRoundingMode(RoundingMode.HALF_UP);    // 5000008.89
//        formater.setRoundingMode(RoundingMode.HALF_DOWN);// 5000008.89
//        formater.setRoundingMode(RoundingMode.HALF_EVEN);

        // 格式化完成之后得出结果
        String formatNum = formater.format(decimal);
//        System.out.println(formatNum);
        return formatNum;
    }

}





