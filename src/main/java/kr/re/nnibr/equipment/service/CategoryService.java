package kr.re.nnibr.equipment.service;

import java.util.List;


public interface CategoryService {
	public List<CategoryVO> selectCategoryList() throws Exception;
	public CategoryVO selectCategoryMap() throws Exception;
}
