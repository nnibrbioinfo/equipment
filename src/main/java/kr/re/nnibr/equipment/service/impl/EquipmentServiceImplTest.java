package kr.re.nnibr.equipment.service.impl;

import static org.junit.Assert.*;
import kr.re.nnibr.equipment.CryptoUtils;
import kr.re.nnibr.equipment.service.ApplicationFormVO;
import kr.re.nnibr.equipment.service.ApplicationService;
import kr.re.nnibr.equipment.service.EquipmentService;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/egovframework/spring/*/*.xml","classpath:egovframework/spring/*/*/*.xml"})
public class EquipmentServiceImplTest {
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	CryptoUtils cryptoUtils;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		ApplicationFormVO applicationFormVO = new ApplicationFormVO();
		applicationFormVO.setApplicationId(4);

		applicationFormVO = applicationService.selectApplication(applicationFormVO);

			if(applicationFormVO != null){
			
				String pdfFilepath = applicationService.makeApplicatonform(applicationFormVO);
		}
	}

}
