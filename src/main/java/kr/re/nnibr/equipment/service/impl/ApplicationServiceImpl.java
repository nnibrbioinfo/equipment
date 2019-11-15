/**
 * 
 */
package kr.re.nnibr.equipment.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import kr.re.nnibr.equipment.CommonUtil;
import kr.re.nnibr.equipment.CryptoUtils;
import kr.re.nnibr.equipment.DateFormatUtil;
import kr.re.nnibr.equipment.service.ApplicationFormVO;
import kr.re.nnibr.equipment.service.ApplicationService;
import kr.re.nnibr.equipment.service.EquipmentVO;
import kr.re.nnibr.equipment.service.ManagerVO;
import kr.re.nnibr.equipment.service.PicVO;

/**
 * @author user
 * @Date : 2019. 9. 3. 
 * @author : 오정수, Oh Jeongsu
 */
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {
	
	@Value("${STORAGE.APPLICANT.PDF}")
	private String pdfPath; 
	
	@Value("${APPLICATION.KEY}")
	private String applicationKey;
	
	@Resource(name = "applicationDAO")
	private ApplicationDAO applicationDAO;
	
	@Autowired
	private PicDAO picDAO;
	
	@Resource(name = "cryptoUtils")
	private CryptoUtils cryptoUtils;
	
	@Autowired
	private CommonUtil commonUtil;
	
	/* (non-Javadoc)
	 * @see kr.re.nnibr.equipment.service.ApplicationService#insertApplicaion(kr.re.nnibr.equipment.service.ApplicationFormVO)
	 */
	@Override
	public int insertApplicaion(ApplicationFormVO applicationFormVO)throws Exception {
		// TODO Auto-generated method stub
		int equipId= applicationFormVO.getEquipId();
		String passwd = applicationFormVO.getPasswd();
		applicationFormVO.setApplicant(cryptoUtils.encryptedString(applicationFormVO.getApplicant(), Integer.toString(equipId)));
		applicationFormVO.setPhone(cryptoUtils.encryptedString(applicationFormVO.getPhone(), Integer.toString(equipId)));
		applicationFormVO.setEmail(cryptoUtils.encryptedString(StringUtils.trim(applicationFormVO.getEmail()), Integer.toString(equipId)));
		applicationFormVO.setSignature(cryptoUtils.encryptedString(applicationFormVO.getSignature(), Integer.toString(equipId)));
		applicationFormVO.setPasswd(BCrypt.hashpw(applicationFormVO.getPasswd(),BCrypt.gensalt()));
		Integer id = applicationDAO.insertApplication(applicationFormVO);
		List<PicVO> picList = picDAO.selectPicList();
		
		if(id != null) {
			ApplicationFormVO afvo = new ApplicationFormVO();
			afvo.setApplicationId(id);
			ApplicationFormVO qafvo = selectApplication(afvo);
			qafvo.setPasswd(passwd);
			qafvo.setMailType(0);
			String fpath = makeApplicatonform(qafvo);
			String body = commonUtil.makeMailContent(qafvo);
			
//			new ArrayList<String>(Arrays.asList(qafvo.getEmail()));
			commonUtil.sendMailToApplicant(qafvo.getEmail(), "국립낙동강생물자원관 장비예약 신청 알림" ,body, fpath);

			qafvo.setMailType(1);
			String body2 = commonUtil.makeMailContent(qafvo);
			commonUtil.sendMailToPic(picList, "국립낙동강생물자원관 장비예약 신청 알림", body2, fpath);
			qafvo.setMailType(1);
			
			File pdfFile = new File(fpath);
			pdfFile.delete();
		}
		
		return id;
	}

	@Override
	public ApplicationFormVO selectApplication(ApplicationFormVO applicationFormVO) throws Exception {
		// TODO Auto-generated method stub
		applicationFormVO = applicationDAO.selectApplication(applicationFormVO);
		return decryptApplicationInfo(applicationFormVO);
	}


	@Override
	public ApplicationFormVO selectApplicationformForIms(ApplicationFormVO applicationFormVO) throws Exception {
		// TODO Auto-generated method stub
		ApplicationFormVO qafVO = applicationDAO.selectApplication(applicationFormVO);
		
		boolean pwChk = BCrypt.checkpw(qafVO.getPasswd(),applicationFormVO.getPasswd());
		return pwChk ? decryptApplicationInfo(qafVO) : null;
	}
	
	@Override
	public ApplicationFormVO selectApplicationformByIdentified(ApplicationFormVO applicationFormVO) throws Exception {
		// TODO Auto-generated method stub
		
		String apCode = applicationFormVO.getApplicationCodeId().toUpperCase();
		if(apCode.startsWith(applicationKey)){
			int applicationId = Integer.parseInt(apCode.substring(7));
			applicationFormVO.setApplicationId(applicationId);
			ApplicationFormVO avo = decryptApplicationInfo(applicationDAO.selectApplication(applicationFormVO));
			
			boolean emailChk = avo.getEmail().equalsIgnoreCase(applicationFormVO.getEmail());
			boolean pwChk = BCrypt.checkpw(applicationFormVO.getPasswd(),avo.getPasswd());
			
			if(emailChk && pwChk){
				return avo;
			};
		};
		return null;
	}
	
	@Override
	public List<ApplicationFormVO> selectApplicaionList(ApplicationFormVO applicationFormVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public String makeApplicatonform(ApplicationFormVO applicationFormVO) throws ParseException, IOException{
		// TODO Auto-generated method stub
		File folder = new File(pdfPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		
	    String pdfFile = "";
	    PdfDocument pdfDoc = null;
	    Document document = null;
		
		org.springframework.core.io.Resource resource = new ClassPathResource("equip/applcationForm.pdf");
	    org.springframework.core.io.Resource fontResource = new ClassPathResource("equip/ARIALUNI.TTF");
		
	    try {
			File applcationFile = resource.getFile();
			
			 //temp 폴터 패스 가져오기
//		    String tempFolder = System.getProperty("java.io.tmpdir");
//		    System.out.println(tempFolder);
		    
		    //sign 이미지 파일 패스
		    String signFile = pdfPath+"sign_"+System.currentTimeMillis()+".png";
		    
		    //pdf 파일 패스
		    pdfFile = pdfPath+"application_"+System.currentTimeMillis()+".pdf";
		   
		    FileUtils.writeByteArrayToFile(new File(signFile), Base64.decodeBase64(applicationFormVO.getSignature().split(",")[1]));

	 	    pdfDoc = new PdfDocument(new PdfReader(applcationFile.getAbsolutePath()), new PdfWriter(pdfFile));
		    
		    PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
	        form.setGenerateAppearance(true);

	        PdfFont font = PdfFontFactory.createFont(fontResource.getFile().getAbsolutePath(), PdfEncodings.IDENTITY_H);
	        
	        //접수번호
	        form.getField("APPLICATION_ID").setValue(applicationKey+Integer.toString(applicationFormVO.getApplicationId()), font, 12f);
	        
	        //접수일자
	        String receiptDate = DateFormatUtil.dateFormat(applicationFormVO.getReceiptDate());
	        form.getField("RECEIPT_DATE").setValue(receiptDate, font, 12f);
	        
	        //신청자 정보 시작
	        //성명
	        form.getField("APPLICANT").setValue(applicationFormVO.getApplicant(), font, 12f);
	        
	        //전화번호
	        form.getField("PHONE").setValue(applicationFormVO.getPhone(), font, 12f);
	        
	        //소속
	        form.getField("AFFILIATION").setValue(applicationFormVO.getAffiliation(), font, 12f);
	        
	        //전자우편
	        form.getField("EMAIL").setValue(applicationFormVO.getEmail(), font, 12f);
	        //주소
	        String address = "("+applicationFormVO.getZipcode()+") "+ applicationFormVO.getAddress() + "  " + applicationFormVO.getDetailAddress();
	        form.getField("ADDRESS").setValue(address, font, 12f);

	        //연구장비정보 시작
	        EquipmentVO equipmentVO = applicationFormVO.getEquipmentVO();

	        //명칭
	        form.getField("EQUIP_NAME").setValue(equipmentVO.getEquipName(), font, 12f);

	        //자산번호
//	        if(equipmentVO.getAssetNoInfos()!=null&&equipmentVO.getAssetNoInfos().size()>0){
//		        form.getField("ASSET_NO").setValue(equipmentVO.getAssetNoInfos().get(0).getAssetNo(), font, 12f);
//	        } else {
//		        form.getField("ASSET_NO").setValue("승인대기중", font, 12f);
//	        }
	        if(applicationFormVO.getAssetNoInfoVO()!=null){
		        form.getField("ASSET_NO").setValue(applicationFormVO.getAssetNoInfoVO().getAssetNo(), font, 12f);
	        } else {
		        form.getField("ASSET_NO").setValue("승인 후 지정", font, 12f);
	        }

	        //보유부서
	        ManagerVO managerVO = equipmentVO.getManagerVO();
	        form.getField("DEPARTMENT").setValue(managerVO.getDepartment(), font, 12f);

	        //가격
	        form.getField("ACQUISITION_COST").setValue("비공개", font, 12f);
//	        form.getField("ACQUISITION_COST").setValue(Integer.toString(equipmentVO.getAcquisitionCost()), font, 12f);

	        //담당자
	        form.getField("NAME").setValue(managerVO.getName(), font, 12f);
	        
	        //이용일자
	        //시작
	 	    String startDate = DateFormatUtil.dateFormat(applicationFormVO.getStartDate());
	 	    form.getField("START_DATE").setValue(startDate, font, 12f);
	 	    // 끝
	 	    String endDate = DateFormatUtil.dateFormat(applicationFormVO.getEndDate());
	 	    form.getField("END_DATE").setValue(endDate, font, 12f);

	 	    //이용시간
	 	    form.getField("HOURS").setValue(applicationFormVO.getHours(), font, 12f);

	 	    //이용목적
	 	    form.getField("PURPOSE").setValue(applicationFormVO.getPurpose(), font, 12f);

	 	    //신청인
	 	    form.getField("APPLICANT_2").setValue(applicationFormVO.getApplicant(), font, 12f);
	 	    ImageData imageData = ImageDataFactory.create(signFile);

	 	    Image image = new Image(imageData).scaleAbsolute(160,60).setFixedPosition(390, 120);

	 	    document = new Document(pdfDoc);
	 	    
	 	    document.add(image);
	 	    
	 	    document.close();
	        pdfDoc.close();
	        
	        File signPng = new File(signFile);
	        if(signPng.exists())signPng.delete();
	 	    
	    }finally{
			document.close();
			pdfDoc.close();
		}

	   return pdfFile;
	}

	@Override
	public Map<Integer,List<String>> selectApplicationReserveDates(ApplicationFormVO applicationFormVO) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer,List<String>> dMap = new HashMap<>();
		Map<Integer,List<String>> dMap2 = new HashMap<>();
		
		List<ApplicationFormVO> avL = applicationDAO.selectApplicationReserveDates(applicationFormVO);
		
		for(ApplicationFormVO i : avL){
			List<String> dL = Optional.ofNullable((List<String>)dMap.get(i.getAssetNoId())).orElse(new ArrayList<String>());
			List<String> dL2 = commonUtil.toDateList(i.getStartDate(), i.getEndDate());
			dL.addAll(dL2);
			dMap.put(i.getAssetNoId(),dL);
		}
		int dMapCnt = 0;
		List<String> retailDates = new ArrayList<>();
		for(Integer i : dMap.keySet()){
			dMapCnt++;
			List<String> sdL = new ArrayList<String>(new HashSet<String>(dMap.get(i)));;
			Collections.sort(sdL, new Comparator<String>() {
                @Override
                public int compare(String object1, String object2) {
                    return object1.compareTo(object2);
                }
            });
			dMap2.put(i, sdL);
			dMap2.put(0, retailDates);
			if(dMapCnt == 1){
				retailDates = new ArrayList<>(sdL);
			} else {
				retailDates.retainAll(sdL);
			}
		}
		dMap2.put(0, retailDates);
		return dMap2;
	}

	public ApplicationFormVO decryptApplicationInfo(ApplicationFormVO applicationFormVO) throws Exception{
		int equipId= applicationFormVO.getEquipId();
		applicationFormVO.setApplicant(cryptoUtils.decryptedString(applicationFormVO.getApplicant(), Integer.toString(equipId)));
		applicationFormVO.setPhone(cryptoUtils.decryptedString(applicationFormVO.getPhone(), Integer.toString(equipId)));
		applicationFormVO.setEmail(cryptoUtils.decryptedString(applicationFormVO.getEmail(), Integer.toString(equipId)));
		applicationFormVO.setSignature(cryptoUtils.decryptedString(applicationFormVO.getSignature(), Integer.toString(equipId)));
		return applicationFormVO;
	}
}
