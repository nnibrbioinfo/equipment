package kr.re.nnibr.equipment.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import kr.re.nnibr.equipment.service.CategoryService;
import kr.re.nnibr.equipment.service.CategoryVO;
import kr.re.nnibr.equipment.service.EquipmentVO;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.stringtemplate.v4.compiler.CodeGenerator.primary_return;

import com.google.gson.Gson;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/egovframework/spring/*/*.xml","classpath:egovframework/spring/*/*/*.xml"})
public class EquipmentDAOTest extends EquipmentDAO {

	
	@Resource(name = "equipmentDAO")
	private EquipmentDAO equipmentDAO;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		EquipmentVO vo = new EquipmentVO();
//		
//		List<EquipmentVO> voList = equipmentDAO.selectEquipmentList(vo);
//		System.out.println(voList.size());

		Gson gson = new Gson();
//		EquipmentVO vo = new EquipmentVO();
//		
//		List<EquipmentVO> evoList = equipmentDAO.selectEquipmentList(vo);
//		System.out.println(evoList.size());
//		System.out.println("====================== evoList ===================");
//		for (EquipmentVO i : evoList){
//			System.out.println(gson.toJson(i));
//		}
		vo.setEquipId(1);
		vo.setReservable(true);
		List<EquipmentVO> qVO = equipmentDAO.selectEquipmentList(vo);
		System.out.println(gson.toJson(qVO));
//		System.out.println(equipmentDAO.selectEquipmentListCnt(vo));
//
//		System.out.println("====================== voList ===================");
//		List<CategoryVO> voList = categoryService.selectCategoryList();
//		for(CategoryVO i : voList){
//			System.out.println(gson.toJson(i));
//		}
		
//		PaginationInfo page = new PaginationInfo();
//		page.setCurrentPageNo(12);
//		page.setTotalRecordCount(1701);
//		page.setPageSize(10);
//		page.setRecordCountPerPage(10);
//		System.out.println(gson.toJson(page.toString()));
//		System.out.printf("first Page : %s \n",page.getFirstPageNo());
//		System.out.printf("last Page : %s \n",page.getLastPageNo());
//		System.out.printf("first on Page : %s \n",page.getFirstPageNoOnPageList());
//		System.out.printf("last on Page : %s \n",page.getLastPageNoOnPageList());
//		System.out.printf("first record index : %s \n",page.getFirstRecordIndex());
//		System.out.printf("last record index : %s \n",page.getLastRecordIndex());
//		System.out.printf("first Page : %s \n",page.get);
		
//		page.get
		
//		System.out.println(gson.toJson(page));
		
		
		
		fail("Not yet implemented");
	}

}
