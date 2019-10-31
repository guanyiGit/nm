package com.soholy.dogmanager.utils.mail;

import com.soholy.dogmanager.pojo.Result;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.util.ByteArrayDataSource;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailUtils {
	
	private static Logger logger = Logger.getLogger(MailUtils.class);
	
	
	public MailUtils() {
		super();
	}

	public MailUtils(String sMTP, String senderName, String senderAddress, String authorizedPassword) {
		super();
		SMTP = sMTP;
		SenderName = senderName;
		SenderAddress = senderAddress;
		AuthorizedPassword = authorizedPassword;
	}
	
	/**
	* @Description: 发送带图片邮件
	* @Title: sendPicMail  
	* @param  recipients
	* @param  subject
	* @param  context
	* @param  pics    
	 */
	public Result sendPicMail(String recipients, String subject, String context, String pics){
		Result result = this.sendMail(recipients, subject, context, pics, null);
		return result;
	}
	
	/**
	* @Description: 发送带附件邮件
	* @Title: sendAttaMail  
	* @param  recipients
	* @param  subject
	* @param  context
	* @param  fileDataSource
	 */
	public Result sendAttaMail(String recipients, String subject, String context, String fileDataSource){
		Result result = this.sendMail(recipients, subject, context, null, fileDataSource);
		return result;
	}
	
	/**
	* @Description: 发送带附件和图片的邮件
	* @Title: sendMail  
	* @param @param recipients
	* @param @param subject
	* @param @param context
	* @param @param pics
	* @param @param fileDataSource
	* @param @return    
	* @return Result     
	* @throws
	 */
	public Result sendMail(String recipients, String subject, String context, String pics, String fileDataSource)
		 {
		try {
			Properties props = new Properties(); // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
			props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
			props.setProperty("mail.smtp.host", SMTP); // 发件人的邮箱的 SMTP
			props.setProperty("mail.smtp.auth", "true");
			// 使用JavaMail发送邮件的5个步骤
			// 1、创建session
			Session session = Session.getDefaultInstance(props);
			// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
			//session.setDebug(true);
			// 3.通过session得到transport对象
			Transport transport = session.getTransport();
			transport.connect(SenderAddress, AuthorizedPassword);
			// 4.创建一封邮件
			MimeMessage message = createMixedMail(session, recipients, subject, context, pics, fileDataSource);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return Result.ok();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(400, "邮件发送失败！！请稍后再试");
		}
		
	}

	/**
	 * @throws Exception
	 * @Description: 创建邮件 
	 * @Title: createMixedMail
	 *  @param @param session 
	 *  @param @param recipients 格式<收件人姓名/收件人邮箱,收件人姓名/收件人邮箱 > 
	 *  @param @param subject 
	 *  @param @param context 
	 *  @throws
	 */
	public MimeMessage createMixedMail(Session session, String recipients, String subject, String context,
			String pics, String fileDataSource)
			throws Exception {

		// 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(
					new InternetAddress("\"" + MimeUtility.encodeText(SenderName) + "\" <" + SenderAddress + ">"));
			// 鉴别发送者
			message.setReplyTo(new Address[] { new InternetAddress(SenderAddress) });
			// 设置收件人
			// 友好名称和邮箱地址之间不要忘了有一空格，邮箱地址之间用逗号分隔
			// 如：张益达sohu <zyh5540@sohu.com>,张益达qq <554077931@qq.com>,张益达163
			// <zyh5540@163.com>
			// 收件人集合
			String addresslist = getRecipients(recipients);
			message.setRecipients(RecipientType.TO, InternetAddress.parse(addresslist));
			message.setSubject(subject);
			/*邮件消息内容设置，包括附件，图片，正文*/
			Multipart msgPart = new MimeMultipart();//默认mixed类型
			message.setContent(msgPart);
			
			
			MimeBodyPart body = new MimeBodyPart();//表示正文
			msgPart.addBodyPart(body);             //将正文添加到消息内容中
			/*以下为设置正文*/
			/*正文是文字和图片混合的*/
			Multipart contentPart = new MimeMultipart("related");
			body.setContent(contentPart);
			//文字部分
			MimeBodyPart content = new MimeBodyPart();
			
			//获取图片地址集合
			if (pics!=null&&!pics.equals("")) {
				String[] picAddress = this.getPicAddress(pics);
				for (String pic : picAddress) {
					MimeBodyPart img = new MimeBodyPart();     //图片
					
					DataSource fileds = new  ByteArrayDataSource(new FileInputStream(pic), "image/jpeg");
					DataHandler imgdataHandler = new DataHandler(fileds);
					img.setDataHandler(imgdataHandler);
					//获取图片名称
					String ImgName = pic.substring(pic.lastIndexOf("/")+1);
					System.err.println(ImgName);
					img.setHeader("Content-ID", ImgName);
					img.setFileName(MimeUtility.encodeText(ImgName));
					//将图片信息添加到正文对象中
					contentPart.addBodyPart(img);
				}
			}
			//设置正文(html格式)()
			content.setContent(ImgUrlChange(context),"text/html;charset=utf-8");
			contentPart.addBodyPart(content);
			//设置附件
			if (fileDataSource!=null&&!fileDataSource.equals("")) {
				String[] files = fileDataSource.split(",");
				for (String file : files) {
					MimeBodyPart filePort = new  MimeBodyPart();//附件
					filePort.setDataHandler(new DataHandler(new FileDataSource(file)));
					String filename = file.substring(file.lastIndexOf("/"));
					filePort.setFileName(filename);
					msgPart.addBodyPart(filePort);
				}
			}
			message.saveChanges();
			
			return message;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception("邮件信息出错！！！");
		}

	}
	
	public String[]	getPicAddress(String pics){
		String[] picAddresses = pics.split(",");
		return picAddresses;
	}
	
	
	/**
	 * @throws UnsupportedEncodingException
	 * @Description: 获取收件人地址
	 *  @Title: getRecipients 
	 *  @param recipients 
	 *  @throws
	 */
	public String getRecipients(String recipients) throws UnsupportedEncodingException {
		String addresslist = "";
		// 根据“，”将收件人分开
		String[] recipient = recipients.split(",");
		for (String person : recipient) {
			String address = "";
			// 获取单个收件人信息
			String[] split = person.split("/");
			for (int i = 0; i < split.length; i++) {
				if (i == 0) {
					address += MimeUtility.encodeText(split[0]) + " ";
				} else {
					address += "<" + split[1] + ">";
				}
			}
			// 将单个人信息添加到收件人集合中
			addresslist += address + ",";
		}
		addresslist = addresslist.substring(0, addresslist.length() - 1);
		return addresslist;
	}
	
	/**
	* @Description: 替换图片地址为cid:url
	* @Title: StringUtils  
	* @param @param context
	* @param @return    
	* @return String     
	* @throws
	 */
	public String ImgUrlChange(String context){
		String[] ImageUrlsFromHtml = returnImageUrlsFromHtml(context);
		if (ImageUrlsFromHtml!=null&&!ImageUrlsFromHtml.equals("")) {
			for (String url : ImageUrlsFromHtml) {
				String newUrl = url.substring(url.lastIndexOf("/")+1);
				newUrl = "cid:"+newUrl;
				context = context.replace(url, newUrl);
			}
		}
		return context;
	}
	
	
	/**
     * 获取html中的所有图片
     *
     * @param html
     * @return
     */
    public static String[] returnImageUrlsFromHtml(String html) {
        List<String> imageSrcList = new ArrayList<String>();
        String htmlCode = html;
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList == null || imageSrcList.size() == 0) {
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }
	
	
	// 发件人的邮箱SMTP
	private String SMTP;

	// 发件人姓名
	private String SenderName;

	// 发件人地址
	private String SenderAddress;

	// 授权密码
	private String AuthorizedPassword;

	public String getSMTP() {
		return SMTP;
	}

	public void setSMTP(String sMTP) {
		SMTP = sMTP;
	}

	public String getSenderName() {
		return SenderName;
	}

	public void setSenderName(String senderName) {
		SenderName = senderName;
	}

	public String getSenderAddress() {
		return SenderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		SenderAddress = senderAddress;
	}

	public String getAuthorizedPassword() {
		return AuthorizedPassword;
	}

	public void setAuthorizedPassword(String authorizedPassword) {
		AuthorizedPassword = authorizedPassword;
	}


}
