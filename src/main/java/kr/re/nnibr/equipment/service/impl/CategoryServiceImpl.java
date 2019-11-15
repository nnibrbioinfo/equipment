package kr.re.nnibr.equipment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import kr.re.nnibr.equipment.service.CategoryService;
import kr.re.nnibr.equipment.service.CategoryVO;

import org.springframework.stereotype.Service;
import org.stringtemplate.v4.compiler.CodeGenerator.primary_return;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Resource(name ="categoryDAO")
	private CategoryDAO categoryDAO;  

	@Override
	public List<CategoryVO> selectCategoryList() throws Exception {
		// TODO Auto-generated method stub
		return categoryDAO.selectCategoryList();
	}

	@Override
	public CategoryVO selectCategoryMap() throws Exception {
		// TODO Auto-generated method stub
		List<CategoryVO> cl = categoryDAO.selectCategory();

		Map<Integer,String> pcm = new HashMap<>();
		Map<Integer,List<Integer>> clm = new HashMap<>();
		Map<Integer,String> cm = new HashMap<>();
		for(CategoryVO i : cl){

			cm.put(i.getCategoryId(), i.getCategoryName());
			if(i.getParentCategory() == null){
				pcm.put(i.getCategoryId(), i.getCategoryName());
			} else if(i.getParentCategory() != null){
				Integer pcId = i.getParentCategory().getCategoryId();
				List<Integer> cil = Optional.ofNullable(clm.get(pcId)).orElse(new ArrayList<>());
				cil.add(i.getCategoryId());
				clm.put(pcId, cil);
			} else {
				
			}
		}
		CategoryVO cvo = new CategoryVO();
		cvo.setParentCategoryMap(pcm);
		cvo.setCategoryTree(clm);
		cvo.setCategoryMap(cm);
		return cvo;
	}

}
