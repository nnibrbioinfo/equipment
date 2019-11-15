/**
 * 
 */
package kr.re.nnibr.equipment.service.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import kr.re.nnibr.equipment.CryptoUtils;
import kr.re.nnibr.equipment.DateFormatUtil;
import kr.re.nnibr.equipment.service.ApplicationFormVO;
import kr.re.nnibr.equipment.service.EquipmentVO;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author user
 * @Date : 2019. 9. 3. 
 * @author : 오정수, Oh Jeongsu
 */

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/egovframework/spring/*/*.xml","classpath:egovframework/spring/*/*/*.xml"})
public class ApplicationDAOTest {

	private ApplicationFormVO applicationFormVO;
	private EquipmentVO equipmentVO;
	
	@Resource(name = "applicationDAO")
	private ApplicationDAO applicationDAO;
	
	@Resource(name = "equipmentDAO")
	private EquipmentDAO equipmentDAO;
	
	@Resource(name = "cryptoUtils")
	private CryptoUtils cryptoUtils;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		equipmentVO = new EquipmentVO();
		equipmentVO.setEquipName("현미경");
		equipmentVO.setEquipNameEn("Microscope");
		equipmentVO.setAssetName("현미경");
		equipmentVO.setManufacturer("Nikkon");
		equipmentVO.setProductName("현미경");
		equipmentVO.setItemName("현미경 아이템");
		equipmentVO.setAcquisitionDate("2019-09-09");
		equipmentVO.setAcquisitionCost(1000);
		equipmentVO.setQuantity(1);
		equipmentVO.setAssetNo("1234567890");
		equipmentVO.setDescription("현미경 장비");
		equipmentVO.setConfiguration("구성 및 성능");
		equipmentVO.setUtilization("활용");
		equipmentVO.setCommonEquip(true);
		equipmentVO.setImage("test.jpg");
		equipmentVO.setCategoryId(1);
		equipmentVO.setManagerId(1);
		equipmentVO.setLocationId(1);
		
		
		applicationFormVO = new ApplicationFormVO();
		applicationFormVO.setApplicant("홍길동");
		applicationFormVO.setAffiliation("경북대학교");
		applicationFormVO.setAddress("경북 상주시");
		applicationFormVO.setPhone("010-000-0000");
		applicationFormVO.setEmail("aaa@aaa.com");
		applicationFormVO.setReceiptDate("2019-09-19");
		applicationFormVO.setStartDate("2019-09-19");
		applicationFormVO.setEndDate("2019-09-19");
		applicationFormVO.setHours("12");
		applicationFormVO.setPurpose("전기영동");
//		applicationFormVO.setAgreePrivacyInfo(true);
		applicationFormVO.setSignature("iVBORw0KGgoAAAANSUhEUgAAB0QAAAHRCAYAAAD69Ap1AAAgAElEQVR4XuzdCbhkZX0n"
				+ "/u+p7kZ2FFTccEFUUAHjggtugCu4oUYnmpg4mWgmiyP0vd1oZiadfyZC33sbjKOZmMVGHeMOooLSYOISY1yiiDiiYh"
				+ "RRccHILtB96/yft6tamuY2Xaeq7lJ1P+d56qlL9/t7l885mDTfft9TxUWAAAECBAgQIECAAAECBAgQIECAAAECBAgQ"
				+ "IECAAIExFajGdF2WRYAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQIAAgQhEPQQECBAgQIAAAQIECBAgQIAAAQIECB"
				+ "AgQIAAAQIECIytgEB0bG+thREgQIAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgIBD1DBAgQIAAAQIECBAgQIAAAQIEC"
				+ "BAgQIAAAQIECBAgMLYCAtGxvbUWRoAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQICAQNQzQIAAAQIECBAgQIAAAQIEC"
				+ "BAgQIAAAQIECBAgQIDA2AoIRMf21loYAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQIAAAQICUc8AAQIECBAgQIAAAQIECB"
				+ "AgQIAAAQIECBAgQIAAAQJjKyAQHdtba2EECBAgQIAAAQIECBAgQIAAAQIECBAgQIAAAQIECAhEPQMECBAgQIAAAQIECBAg"
				+ "QIAAAQIECBAgQIAAAQIECIytgEB0bG+thREgQIAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgIBD1DBAgQIAAAQIECBAgQIA"
				+ "AAQIECBAgQIAAAQIECBAgMLYCAtGxvbUWRoAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQICAQNQzQIAAAQIECBAgQIAAAQI"
				+ "ECBAgQIAAAQIECBAgQIDA2AoIRMf21loYAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQIAAAQICUc8AAQIECBAgQIAAAQIECBAg");
		applicationFormVO.setEquipId(1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEncrypt(){
		String signature = "iVBORw0KGgoAAAANSUhEUgAAB0QAAAHRCAYAAAD69Ap1AAAgAElEQVR4XuzdCbhkZX0n"
				+ "/u+p7kZ2FFTccEFUUAHjggtugCu4oUYnmpg4mWgmiyP0vd1oZiadfyZC33sbjKOZmMVGHeMOooLSYOISY1yiiDiiYh"
				+ "RRccHILtB96/yft6tamuY2Xaeq7lJ1P+d56qlL9/t7l885mDTfft9TxUWAAAECBAgQIECAAAECBAgQIECAAAECBAgQ"
				+ "IECAAIExFajGdF2WRYAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQIAAgQhEPQQECBAgQIAAAQIECBAgQIAAAQIECB"
				+ "AgQIAAAQIECIytgEB0bG+thREgQIAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgIBD1DBAgQIAAAQIECBAgQIAAAQIEC"
				+ "BAgQIAAAQIECBAgMLYCAtGxvbUWRoAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQICAQNQzQIAAAQIECBAgQIAAAQIEC"
				+ "BAgQIAAAQIECBAgQIDA2AoIRMf21loYAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQIAAAQICUc8AAQIECBAgQIAAAQIECB"
				+ "AgQIAAAQIECBAgQIAAAQJjKyAQHdtba2EECBAgQIAAAQIECBAgQIAAAQIECBAgQIAAAQIECAhEPQMECBAgQIAAAQIECBAg"
				+ "QIAAAQIECBAgQIAAAQIECIytgEB0bG+thREgQIAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgIBD1DBAgQIAAAQIECBAgQIA"
				+ "AAQIECBAgQIAAAQIECBAgMLYCAtGxvbUWRoAAAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQICAQNQzQIAAAQIECBAgQIAAAQI"
				+ "ECBAgQIAAAQIECBAgQIDA2AoIRMf21loYAQIECBAgQIAAAQIECBAgQIAAAQIECBAgQIAAAQICUc8AAQIECBAgQIAAAQIECBAg";
				
		String encode = cryptoUtils.encryptedString(signature, "1");
		String decode = cryptoUtils.decryptedString(encode, "1");
		
		assertEquals(signature, decode);
		
		
		
	}
	
	/**
	 * Test method for {@link kr.re.nnibr.equipment.service.impl.ApplicationDAO#insertApplication(kr.re.nnibr.equipment.service.ApplicationFormVO)}.
	 * @throws Exception 
	 */
	@Test
	public void testInsertApplicaion() throws Exception {
		
//		applicationFormVO.setEquipId(equipmentDAO.insertEquipment(equipmentVO));
		
//		int id = applicationDAO.insertApplicaion(applicationFormVO);

	}
	
	@Test
	public void testSelectApplicaion() throws Exception {
		
		applicationFormVO.setEquipId(1);
		applicationFormVO.setApplicationId(19);
//		applicationFormVO.setEquipId(equipmentDAO.insertEquipment(equipmentVO));
		ApplicationFormVO result = applicationDAO.selectApplication(applicationFormVO);
//		int id = applicationDAO.insertApplicaion(applicationFormVO);
		
		 //접수일자
 	    String receiptDate = DateFormatUtil.dateFormat(result.getReceiptDate());
 	    //이용일자 시작
 	    String startDate = DateFormatUtil.dateFormat(result.getStartDate());
 	    //이용일자 끝
 	    String endDate = DateFormatUtil.dateFormat(result.getEndDate());
 	    
 	    
		System.out.println(result.getHours() + " : " +result.getStartDate() + " : " +startDate);
		System.out.println(result.getApplicationId() + " : " +result.getEndDate() + " : " +endDate);
	}

}
