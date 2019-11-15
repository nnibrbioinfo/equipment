package kr.re.nnibr.equipment.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import kr.re.nnibr.equipment.service.CategoryVO;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/egovframework/spring/*/*.xml","classpath:egovframework/spring/*/*/*.xml"})
public class CategoryDAOTest {
	@Autowired
	CategoryDAO categoryDAO;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void test() throws Exception {
		Gson gson = new Gson();
		List<CategoryVO> cl = categoryDAO.selectCategory();
		Map<Integer,String> pcm = new HashMap<>();
		Map<Integer,List<Integer>> clm = new HashMap<>();
		Map<Integer,String> cm = new HashMap<>();
		for(CategoryVO i : cl){
			if(i.getParentCategory() == null){
				pcm.put(i.getCategoryId(), i.getCategoryName());
			} else {
				Integer pcId = i.getParentCategory().getCategoryId();
				cm.put(i.getCategoryId(), i.getCategoryName());
				List<Integer> cil = Optional.ofNullable(clm.get(pcId)).orElse(new ArrayList<>());
				cil.add(i.getCategoryId());
				clm.put(pcId, cil);
			}
		}
		System.out.println("parant category map");
		System.out.println(gson.toJson(pcm));
		System.out.println("category list map");
		System.out.println(gson.toJson(clm));
		System.out.println("category map");
		System.out.println(gson.toJson(cm));
		
		fail("Not yet implemented");
	}

	@Test
	public void test2() throws Exception {
		Gson gson = new Gson();
		List<CategoryVO> cl = categoryDAO.selectCategory();
		
		Map<Integer,List<Integer>> clm = new HashMap<>();
		Map<Integer,String> cm = new HashMap<>();
		for(CategoryVO i : cl){

			cm.put(i.getCategoryId(), i.getCategoryName());
			
			if(i.getParentCategory() != null){
				Integer pcId = i.getParentCategory().getCategoryId();
				List<Integer> cil = Optional.ofNullable(clm.get(pcId)).orElse(new ArrayList<>());
				cil.add(i.getCategoryId());
				clm.put(pcId, cil);
			}
		}
		System.out.println("parant category map");
		System.out.println("category list map");
		System.out.println(gson.toJson(clm));
		System.out.println("category map");
		System.out.println(gson.toJson(cm));
		
		fail("Not yet implemented");
	}
}
