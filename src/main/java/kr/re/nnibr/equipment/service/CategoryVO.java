/**
 * 
 */
package kr.re.nnibr.equipment.service;

import java.util.List;
import java.util.Map;


/**
 * @author user
 * @Date : 2019. 7. 29. 
 * @author : 오정수, Oh Jeongsu
 */
public class CategoryVO extends PageVO {
	
	private int categoryId;
	private String categoryName;
	private String categoryNameEn;
	private int parentCategoryId;
	private CategoryVO parentCategory;
	private List<EquipmentVO> equipments;
	
	
	private Map<Integer,String> parentCategoryMap;
	private Map<Integer,String> categoryMap;
	private Map<Integer,List<Integer>> categoryTree;
	
	
	public List<EquipmentVO> getEquipments() {
		return equipments;
	}
	public void setEquipments(List<EquipmentVO> equipments) {
		this.equipments = equipments;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryNameEn() {
		return categoryNameEn;
	}
	public void setCategoryNameEn(String categoryNameEn) {
		this.categoryNameEn = categoryNameEn;
	}
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public CategoryVO getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(CategoryVO parentCategory) {
		this.parentCategory = parentCategory;
	}
	public Map<Integer, String> getCategoryMap() {
		return categoryMap;
	}
	public void setCategoryMap(Map<Integer, String> categoryMap) {
		this.categoryMap = categoryMap;
	}
	public Map<Integer, List<Integer>> getCategoryTree() {
		return categoryTree;
	}
	public void setCategoryTree(Map<Integer, List<Integer>> categoryTree) {
		this.categoryTree = categoryTree;
	}
	public Map<Integer, String> getParentCategoryMap() {
		return parentCategoryMap;
	}
	public void setParentCategoryMap(Map<Integer, String> parentCategoryMap) {
		this.parentCategoryMap = parentCategoryMap;
	}
}
