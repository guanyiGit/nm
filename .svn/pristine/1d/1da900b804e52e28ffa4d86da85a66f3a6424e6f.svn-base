package com.soholy.dogmanager.config;


import com.soholy.dogmanager.utils.mail.MailUtils;
import com.soholy.dogmanager.utils.randCode.CTCCRandcode;
import com.soholy.dogmanager.utils.sms.CTCCUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {


    /**
     * 密码短信模板
     * @return
     */
    @Bean
    public CTCCUtils passWordSMS(){
        CTCCUtils ctccUtils = new CTCCUtils();
        ctccUtils.setAPP_ID("236452020000275228");
        ctccUtils.setAPP_SECRET("e0262a99a3dd245c43e06e1f00205bfc");
        ctccUtils.setTEMPLATE_ID("91554444");
        return  ctccUtils;
    }

    /**
     * 验证码
     * @return
     */
    @Bean
    public CTCCRandcode ctccRandcode(){
        CTCCRandcode ctccRandcode = new CTCCRandcode();
        ctccRandcode.setAPP_ID("236452020000275228");
        ctccRandcode.setAPP_SECRET("e0262a99a3dd245c43e06e1f00205bfc");
        ctccRandcode.setExpTime(2);
        return  ctccRandcode;
    }


    /**
     * 犬只围栏黑名单告警模板
     * @return
     */
    @Bean
    public CTCCUtils forbidSMS(){
        CTCCUtils forbidSMS = new CTCCUtils();
        forbidSMS.setAPP_ID("236452020000275228");
        forbidSMS.setAPP_SECRET("e0262a99a3dd245c43e06e1f00205bfc");
        forbidSMS.setTEMPLATE_ID("91554444");
        return  forbidSMS;
    }
    /**
     * 电量不足短信模板
     * @return
     */
    @Bean
    public CTCCUtils batterySMS(){
        CTCCUtils batterySMS = new CTCCUtils();
        batterySMS.setAPP_ID("236452020000275228");
        batterySMS.setAPP_SECRET("e0262a99a3dd245c43e06e1f00205bfc");
        batterySMS.setTEMPLATE_ID("91554444");
        return  batterySMS;
    }


    /**
     * 黑名单短信模板
     * @return
     */
    @Bean
    public CTCCUtils blacklistSMS(){
        CTCCUtils batterySMS = new CTCCUtils();
        batterySMS.setAPP_ID("236452020000275228");
        batterySMS.setAPP_SECRET("e0262a99a3dd245c43e06e1f00205bfc");
        batterySMS.setTEMPLATE_ID("91554444");
        return  batterySMS;
    }



    /**
     * 犬只围栏黑名单短信模板
     * @return
     */
    @Bean
    public CTCCUtils whitelistSMS(){
        CTCCUtils batterySMS = new CTCCUtils();
        batterySMS.setAPP_ID("236452020000275228");
        batterySMS.setAPP_SECRET("e0262a99a3dd245c43e06e1f00205bfc");
        batterySMS.setTEMPLATE_ID("91554444");
        return  batterySMS;
    }


    /**
     * 犬只违规短信模板
     * @return
     */
    @Bean
    public CTCCUtils violationSMS(){
        CTCCUtils batterySMS = new CTCCUtils();
        batterySMS.setAPP_ID("236452020000275228");
        batterySMS.setAPP_SECRET("e0262a99a3dd245c43e06e1f00205bfc");
        batterySMS.setTEMPLATE_ID("91554444");
        return  batterySMS;
    }




    /**
     * 邮件参数
     * @return
     */
    @Bean
    public MailUtils mailUtils(){
        MailUtils mailUtils = new MailUtils();
        mailUtils.setSMTP("smtp.163.com");
        mailUtils.setSenderName("彭雨");
        mailUtils.setSenderAddress("pengyu928548515@163.com");
        return  mailUtils;
    }
}
