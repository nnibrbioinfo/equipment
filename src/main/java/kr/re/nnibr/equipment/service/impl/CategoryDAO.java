package kr.re.nnibr.equipment.service.impl;

import java.util.List;

import kr.re.nnibr.equipment.service.CategoryVO;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;


@Repository("categoryDAO")
public class CategoryDAO extends EgovComAbstractDAO{
	
	public List<CategoryVO> selectCategoryList() throws Exception{
		return (List<CategoryVO>) list("categoryDAO.selectCategoryList");
	}
	
	public List<CategoryVO> selectCategory() throws Exception{
		return (List<CategoryVO>) list("categoryDAO.selectCategory");
	}

}
