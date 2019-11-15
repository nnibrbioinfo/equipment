package kr.re.nnibr.equipment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import kr.re.nnibr.equipment.service.ApplicationFormVO;
import kr.re.nnibr.equipment.service.PicVO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;


@Component
public class CommonUtil {
	@Value("${Globals.mail.senderName}")
	private String sName;
	
	@Value("${Globals.mail.host}")
	private String host;

	@Value("${Globals.mail.port}")
	private String port;
	
	@Value("${Globals.mail.id}")
	private String eId;
	
	@Value("${Globals.mail.password}")
	private String ePw;
	
	
	@Value("${APPLICATION.KEY}")
	private String apKey;
	

	public static Boolean innerChk(HttpServletRequest httpServletRequest){
//		String myip = httpServletRequest.getRemoteAddr();
		String myip = ipCheck(httpServletRequest);
		
		List<String> ipList = new ArrayList<>(Arrays.asList("127.0.0.1"));

		Long ipCnt = ipList.stream().filter(ip->myip.contains(ip)).count();

		return ipCnt > 0 ? true : false;
	}

	public static String ipCheck(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public void sendMailToApplicant(String recipient, String subject, String body, String filePath) {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", host);

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un = eId;
				String pw = ePw;

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(un, pw);
				}
			});
			session.setDebug(false); // for debug

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(eId, sName));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			mimeMessage.setSubject(subject);
			// mimeMessage.setText(body);
//			mimeMessage.setContent(body, "text/html; charset=utf-8");

	        Multipart multipart = new MimeMultipart();

	        MimeBodyPart htmlBodyPart = new MimeBodyPart();
	        htmlBodyPart.setContent(body, "text/html; charset=utf-8");
	        multipart.addBodyPart(htmlBodyPart);
	        
			if(filePath != null){
		        MimeBodyPart fileBodyPart = new MimeBodyPart();
		        DataSource source = new FileDataSource(filePath);
		        fileBodyPart.setDataHandler(new DataHandler(source));
		        fileBodyPart.setFileName(MimeUtility.encodeText("NNIBR_연구장비예약_신청서.pdf", "UTF-8", null));
		        multipart.addBodyPart(fileBodyPart); 	
			}
			
	        mimeMessage.setContent(multipart);
//	        System.out.println("Sending");
			Transport.send(mimeMessage);
		} catch (Exception e) {
//			System.out.println("error");
		}
	}
	
	public void sendMailToPic(List<PicVO> picList, String subject, String body, String filePath) {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", host);

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un = eId;
				String pw = ePw;

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(un, pw);
				}
			});
			session.setDebug(false); // for debug

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(eId, sName));
			
			InternetAddress[] picArray = 
					picList.stream().map((e)->{
						InternetAddress ia = null;
						try {
							ia = new InternetAddress(e.getEmail());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return ia;
					}).toArray(InternetAddress[]::new);
			
//			Gson gson = new Gson();
//			System.out.println("gson.toJson(picArray)");
//			System.out.println(gson.toJson(picArray));
			
			
			mimeMessage.setRecipients(Message.RecipientType.TO, picArray);
			
			mimeMessage.setSubject(subject);
			// mimeMessage.setText(body);
//			mimeMessage.setContent(body, "text/html; charset=utf-8");

	        Multipart multipart = new MimeMultipart();

	        MimeBodyPart htmlBodyPart = new MimeBodyPart();
	        htmlBodyPart.setContent(body, "text/html; charset=utf-8");
	        multipart.addBodyPart(htmlBodyPart);
	        
			if(filePath != null){
		        MimeBodyPart fileBodyPart = new MimeBodyPart();
		        DataSource source = new FileDataSource(filePath);
		        fileBodyPart.setDataHandler(new DataHandler(source));
		        fileBodyPart.setFileName(MimeUtility.encodeText("NNIBR_연구장비예약_신청서.pdf", "UTF-8", null));
		        multipart.addBodyPart(fileBodyPart); 	
			}
			
	        mimeMessage.setContent(multipart);
			Transport.send(mimeMessage);
		} catch (Exception e) {
//			System.out.println("error");
		}
	}


	
	public void sendMail(String recipient, String subject, String body, String filePath) {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", host);

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un = eId;
				String pw = ePw;

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(un, pw);
				}
			});
			session.setDebug(false); // for debug

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(eId, sName));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			mimeMessage.setSubject(subject);
			// mimeMessage.setText(body);
//			mimeMessage.setContent(body, "text/html; charset=utf-8");

	        Multipart multipart = new MimeMultipart();

	        MimeBodyPart htmlBodyPart = new MimeBodyPart();
	        htmlBodyPart.setContent(body, "text/html; charset=utf-8");
	        multipart.addBodyPart(htmlBodyPart);
	        
			if(filePath != null){
		        MimeBodyPart fileBodyPart = new MimeBodyPart();
		        DataSource source = new FileDataSource(filePath);
		        fileBodyPart.setDataHandler(new DataHandler(source));
		        fileBodyPart.setFileName(MimeUtility.encodeText("NNIBR_연구장비예약_신청서.pdf", "UTF-8", null));
		        multipart.addBodyPart(fileBodyPart); 	
			}
			
	        mimeMessage.setContent(multipart);
//	        System.out.println("Sending");
			Transport.send(mimeMessage);
		} catch (Exception e) {
//			System.out.println("error");
		}
	}

	
	public List<String> toDateList(String startDate, String endDate) {
		final LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		final LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		final long days = start.until(end, ChronoUnit.DAYS);
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return LongStream.rangeClosed(0, days)
				.mapToObj(start::plusDays)
			    .map(LocalDate -> LocalDate.format(formatters))
			    .collect(Collectors.toList());
		}
	
	public String makeMailContent(ApplicationFormVO applicationFormVO){
		int mailType = applicationFormVO.getMailType();
		
	    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String schem = "https";
		String serverName = request.getServerName();
		String contextPath = request.getContextPath();
		String fullUrl = schem+"://"+serverName+contextPath;
		String infoLink = mailType == 0 ? fullUrl+"/reservation.do?email="+applicationFormVO.getEmail()+"&amp;applicationCodeId="+apKey+applicationFormVO.getApplicationId() : "https://fbp.nnibr.re.kr/equipims/manage/manageReservation" ;
		
		String content = "<xmeta charset=\"UTF-8\">" +
				"<xmeta http-equiv=\"Content-Type\" content=\"text/html;\">" +
				"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"  <tbody><tr><td>" +
				"      <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-top:4px solid #007bff;max-width:679px;margin:auto;width:100%\">" +
				"        <tbody><tr><td><a href=\""+fullUrl+"\" target=\"_blank\"><img src=\""+fullUrl+"/images/equip/nnibr_logo.png\" width=\"130\" height=\"60\" style=\"padding:30px 39px\" alt=\"nnibr_equipment\"></a></td></tr>" +
				"        <tr><td>" +
				"            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"              <tbody><tr><td width=\"39\"></td><td width=\"600\" style=\"font-size:20px;color:#666;word-break: keep-all;padding: 25px 0;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\"><span style=\"font-weight:bold\">국립낙동강생물자원관</span> 장비이용 신청이 접수되었습니다.</td><td width=\"39\"></td></tr>" +
				"            </tbody></table>" +
				"          </td></tr>" +
				"        <tr><td>" +
				"            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"              <tbody><tr><td width=\"39\"></td><td width=\"600\">" +
				"                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"                    <tbody><tr><td colspan=\"2\" style=\"padding: 25px 0px 10px 0px;font-size:15px;color:#000;font-weight: bold; font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">예약정보</td></tr>" +
				"                    <tr><td colspan=\"2\" width=\"100%\" height=\"1\" style=\"background: #000\"></td></tr>" +
				"                    <tr><td width=\"100\" style=\"font-size:13px;color:#666;padding-bottom:10px;padding-top:15px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">신청자명</td><td align=\"right\" width=\"500\" style=\"font-size:13px;color:#666;font-weight: bold;padding-bottom:10px;padding-top:15px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">"+ applicationFormVO.getApplicant() +"</td></tr>" +
				"                    <tr><td width=\"100\" style=\"font-size:13px;color:#666;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">신청장비</td><td align=\"right\" width=\"500\" style=\"font-size:13px;color:#666;font-weight: bold;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">"+ applicationFormVO.getEquipmentVO().getEquipName() +"</td></tr>" +
				"                    <tr><td width=\"100\" style=\"font-size:13px;color:#666;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">신청일자</td><td align=\"right\" width=\"500\" style=\"font-size:13px;color:#666;font-weight: bold;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">"+ applicationFormVO.getReceiptDate() +"</td></tr>" +
				"                    <tr><td width=\"100\" style=\"font-size:13px;color:#666;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">이용일자</td><td align=\"right\" width=\"500\" style=\"font-size:13px;color:#666;font-weight: bold;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">"+applicationFormVO.getStartDate() + " ~ " + applicationFormVO.getEndDate()+"</td></tr>" +
				"                    <tr><td width=\"100\" style=\"font-size:13px;color:#666;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">이용시각</td><td align=\"right\" width=\"500\" style=\"font-size:13px;color:#666;font-weight: bold;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">"+applicationFormVO.getHours()+"</td></tr>" +
				"                    <tr><td width=\"100\" style=\"font-size:13px;color:#666;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">신청번호</td><td align=\"right\" width=\"500\" style=\"font-size:13px;color:#666;font-weight: bold;padding-bottom:10px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">"+apKey+applicationFormVO.getApplicationId()+"</td></tr>";
		
		String domPass = "                    <tr><td width=\"100\" style=\"font-size:13px;color:#666;padding-bottom:15px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">비밀번호</td><td align=\"right\" width=\"500\" style=\"font-size:13px;color:#666;font-weight: bold;padding-bottom:15px;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">"+applicationFormVO.getPasswd()+"</td></tr>";
		String content2 = "                    <tr><td colspan=\"2\" width=\"100%\" height=\"1\" style=\"background: #ccc\"></td></tr>" +
				"                    <tr><td colspan=\"2\" width=\"100%\" height=\"1\" style=\"background: #ccc\"></td></tr>" +
				"                </tbody></table>" +
				"                </td><td width=\"39\"></td></tr>" +
				"            </tbody></table>" +
				"          </td></tr>" +
				"        <tr><td>" +
				"            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"              <tbody><tr><td height=\"20px\"></td></tr>" +
				"              <tr><td width=\"39\"></td><td valign=\"middle\" width=\"600\" style=\"height:42px;background: #007bff;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">" +
				"                  <a href=\""+infoLink+"\" target=\"_blank\" style=\"color:#fff;font-size:15px;text-align:center;text-decoration: none;:600px;display: block;\" rel=\"noreferrer noopener\">신청정보 확인하기</a>" +
				"                </td><td width=\"39\"></td></tr>" +
				"            </tbody></table>" +
				"          </td></tr> " +
				"        <tr><td>" +
				"            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"              <tbody><tr><td width=\"39\"></td><td width=\"600\" style=\"padding:30px 0\">" +
				"                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"                    <tbody><tr><td valign=\"middle\" style=\"font-size:15px;color:#666;line-height:15px;font-weight: bold;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">" +
				"                          유의사항" +
				"                        </td></tr><tr height=\"5\"></tr><tr><td width=\"600\" style=\"font-size:13px;color:#666;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">-  본 메일은 발신전용으로 회신하실 경우 답변을 받으실 수 없습니다.<br>- 문의사항은 \"054-530-0881\" 을 이용해 주십시오.</td></tr></tbody>" +
				"                  </table>" +
				"                </td><td width=\"39\"></td></tr>" +
				"            </tbody></table>" +
				"          </td></tr>" +
				"        <tr><td>" +
				"            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"              <tbody><tr><td width=\"39\">&nbsp;</td><td width=\"600\">" +
				"                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"                    <tbody><tr><td width=\"20\"></td><td align=\"left\" style=\"font-size:11px;color:#aaa;padding:30px 0;font-family: '나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">(우) 37242 경상북도 상주시 도남2길 137 (도남동) 국립낙동강생물자원관  054-530-0700<br>Copyright © <a href=\"http://www.nnibr.re.kr\" target=\"_blank\" style=\"color: blue;\">Nakdonggang National Institute of Biological Resources</a>.</td><td width=\"20\"></td></tr></tbody>" +
				"                  </table>" +
				"                </td><td width=\"39\">&nbsp;</td></tr>" +
				"            </tbody></table>" +
				"          </td></tr>" +
				"      </tbody></table>" +
				"    </td></tr>" +
				"</tbody></table>" +
				"</xmeta></xmeta>";
//		content.replace("${applicant}", applicationFormVO.getApplicant());
//		content.replace("${equipName}", applicationFormVO.getEquipmentVO().getEquipName());
//		content.replace("${receiptDate}", applicationFormVO.getReceiptDate());
//		content.replace("${startDate}", applicationFormVO.getStartDate());
//		content.replace("${endDate}", applicationFormVO.getEndDate());
//		content.replace("${hours}", applicationFormVO.getHours());
//		content.replace("${applicationId}", Integer.toString(applicationFormVO.getApplicationId()));
//		content.replace("${passwd}", applicationFormVO.getPasswd());
		String fContent = mailType == 0 ? content + domPass + content2 : content + content2;
		return fContent;
	}
}
