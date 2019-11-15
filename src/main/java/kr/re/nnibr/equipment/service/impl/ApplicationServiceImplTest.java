package kr.re.nnibr.equipment.service.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.mail.internet.InternetAddress;

import kr.re.nnibr.equipment.CommonUtil;
import kr.re.nnibr.equipment.DateFormatUtil;
import kr.re.nnibr.equipment.service.ApplicationFormVO;
import kr.re.nnibr.equipment.service.ApplicationService;
import kr.re.nnibr.equipment.service.AssetNoInfoVO;
import kr.re.nnibr.equipment.service.PicVO;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;


//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/egovframework/spring/*/*.xml","classpath:egovframework/spring/*/*/*.xml"})
public class ApplicationServiceImplTest {

	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	ApplicationDAO applicationDAO;
	
	@Autowired
	AssetNoInfoDAO assetNoInfoDAO; 
	
	@Autowired
	CommonUtil commonUtil;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {

//		ApplicationFormVO applicationFormVO = new ApplicationFormVO();
//		applicationFormVO.setApplicationId(30);
//
//		ApplicationFormVO qafVO = applicationFormVO = applicationService.selectApplication(applicationFormVO);
//		
//		String hpw = BCrypt.hashpw(applicationFormVO.getPasswd(), BCrypt.gensalt());
//		Boolean pchk = BCrypt.checkpw(qafVO.getPasswd(),hpw);
//		System.out.println("applicationFormVO.getPasswd()");
//		System.out.println(applicationFormVO.getPasswd());
//		System.out.println("hpw");
//		System.out.println(hpw);
//		System.out.println("qafVO.getPasswd()");
//		System.out.println(qafVO.getPasswd());
//		System.out.println(pchk);
		
//		String fpath = applicationService.makeApplicatonform(applicationFormVO);
//		System.out.println(fpath);
//		commonUtil.sendMail("bioleaf@nnibr.re.kr", "테스트", "입니다",null);
//		fail("Not yet implemented");  

	}
	
//	@Test
	public void test2() throws Exception{
		ApplicationFormVO avo = new ApplicationFormVO();
//		avo.setApplicationCodeId("NNIBREQ121");
//		avo.setEmail("bioleaf@nnibr.re.kr");
//		
//		applicationService.selectApplicationformByIdentified(avo);

//		ApplicationFormVO afvo = new ApplicationFormVO();
//		afvo.setApplicationId(31);
//		ApplicationFormVO qafvo = applicationService.selectApplication(afvo);
//		qafvo.setPasswd("123123");
//		String fpath = applicationService.makeApplicatonform(qafvo);
		
//		String body = commonUtil.makeMailContent(qafvo);
//		commonUtil.sendMail(qafvo.getEmail(), "국립낙동강생물자원관 장비예약 신청 알림", "test", null);
		List<PicVO> picList = new ArrayList<>();
		PicVO pvo1 = new PicVO();
		pvo1.setEmail("bioleaf@nnibr.re.kr");
		picList.add(pvo1);
		commonUtil.sendMailToPic(picList, "테스트", "테스트", null);
	}
	

}
