package com.task;


import com.epmanagement.service.AntiepidemicService;
import com.statisanalysis.entity.Message;
import com.statisanalysis.entity.MessageParm;
import com.statisanalysis.serivce.InsertMessage;
import com.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;
import java.util.*;


@Component
@Configurable
@EnableScheduling
public class testTask {

    @Autowired
    private AntiepidemicService antiepidemicService;
    @Autowired
    private InsertMessage insertMessage;
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void test1(){
//        System.out.println("test");
//    }

    /**
     * 防疫到期时间通知
     */
    @Scheduled(cron = "0 0 2 * * ?")//每天凌晨2点执行一次
//    @Scheduled(cron = "0/5 * * * * ?")
    public void epidemicTask(){
        List<Map<String, Object>> list = antiepidemicService.list(new HashMap<String, Object>());
        list.stream().forEach(ac ->{
            String period = ac.get("period").toString();//防疫周期
            String date = ac.get("date").toString();//防疫时间
            Date fyDate = DateUtils.stringToDate(date, null);
            int protector = Integer.parseInt(ac.get("protectBy").toString());
            int type = Integer.parseInt(ac.get("type").toString());
            //犬主名
            String ownerName = ac.get("ownerName").toString();
            String dogName = ac.get("dogName").toString();
            //犬只名

            //计算到期日期
            Date endDate = DateUtils.addMonth(fyDate, Integer.parseInt(period));
            String endTime = DateUtils.format(endDate);

            // 计算当前日期到到期日期的 天数
            Long days = DateUtils.getTimeBefore(endDate);
            //如果天数小于7则自动报警
            if(days<=7&&days>0){//protector
                ArrayList<Integer> receiver = new ArrayList<>();
                receiver.add(protector);
                MessageParm msg = new MessageParm();
                Message message = new Message();
                String title =   "春防消息";
                String content = "有春防时间快到期了";
                StringBuffer buffer = new StringBuffer();
                int msgType = 2;//默认为春防
                if(type==0) {
                    title = "春防消息";
                    buffer.append("$:"+ownerName+" $:"+dogName);
//                    content = " $有春防时间快到期了";
//                    buffer.append(content);
                    buffer.append(" $ :"+endTime);
                    msgType = 2;
                }else if(type==1){
                    title = "秋防消息";
                    buffer.append("$:"+ownerName+" $:"+dogName);
//                    content = " $有秋防防时间快到期了";
//                    buffer.append(content);
                    buffer.append(" $ :"+endTime);
                    msgType = 3;
                }else if (type==2){
                    title = "月月投药消息";
                    buffer.append("$:"+ownerName+" $:"+endTime);
//                    content = " $有月月投药时间快到期了";
//                    buffer.append(content);
                    buffer.append(" $ :"+endTime);
                    msgType = 4;
                }
                message.setStatus(0);
                message.setTitle(title);
                message.setType(msgType);
                message.setContent(buffer.toString());
                message.setCreateDate(new Date());
                message.setUpdateDate(new Date());

                msg.setMessage(message);
                msg.setList(receiver);
                try {
                    insertMessage.insertMessage(msg);
                } catch (ServerException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
