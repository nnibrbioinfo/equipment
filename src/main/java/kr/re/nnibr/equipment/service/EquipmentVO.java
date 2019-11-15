/**
 * 
 */
package kr.re.nnibr.equipment.service;

import java.util.List;


/**
 * @author user
 * @Date : 2019. 7. 29. 
 * @author : 오정수, Oh Jeongsu
 */
public class EquipmentVO extends PageVO {
	
	private int equipId;
	private String equipName;
	private String equipNameEn;
	private String assetName;
	private String manufacturer;
	private String productName;
	private String itemName;
	private String acquisitionDate;
	private int acquisitionCost;
	private int quantity;
	private String assetNo;
	private String description;
	private String configuration;
	private String utilization;
	private int categoryId;
	private CategoryVO category;
	private Integer categoryIdSrch;
	private Integer parentCategoryIdSrch;
	private int managerId;
	private boolean isCommonEquip;
	private boolean isReservable;
	private int locationId;
	private String image;
	private List<AssetNoInfoVO> assetNoInfos;
	private List<FileInfoVO> fileInfos;
	private String location;
	private LocationVO locationVO;
	private ManagerVO managerVO;
	private List<ApplicationFormVO> applicationForms;
	private List<EquipmentVO> equipList;
	private String equipStatusType;
	
	public List<ApplicationFormVO> getApplicationForms() {
		return applicationForms;
	}
	public void setApplicationForms(List<ApplicationFormVO> applicationForms) {
		this.applicationForms = applicationForms;
	}
	public ManagerVO getManagerVO() {
		return managerVO;
	}
	public void setManagerVO(ManagerVO managerVO) {
		this.managerVO = managerVO;
	}
	public LocationVO getLocationVO() {
		return locationVO;
	}
	public void setLocationVO(LocationVO locationVO) {
		this.locationVO = locationVO;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<AssetNoInfoVO> getAssetNoInfos() {
		return assetNoInfos;
	}
	public void setAssetNoInfos(List<AssetNoInfoVO> assetNoInfos) {
		this.assetNoInfos = assetNoInfos;
	}
	
	public int getEquipId() {
		return equipId;
	}
	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getEquipNameEn() {
		return equipNameEn;
	}
	public void setEquipNameEn(String equipNameEn) {
		this.equipNameEn = equipNameEn;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getAcquisitionDate() {
		return acquisitionDate;
	}
	public void setAcquisitionDate(String acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
	public int getAcquisitionCost() {
		return acquisitionCost;
	}
	public void setAcquisitionCost(int acquisitionCost) {
		this.acquisitionCost = acquisitionCost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getUtilization() {
		return utilization;
	}
	public void setUtilization(String utilization) {
		this.utilization = utilization;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public CategoryVO getCategory() {
		return category;
	}
	public void setCategory(CategoryVO category) {
		this.category = category;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public boolean isReservable() {
		return isReservable;
	}
	public void setReservable(boolean isReservable) {
		this.isReservable = isReservable;
	}
	public boolean isCommonEquip() {
		return isCommonEquip;
	}
	public void setCommonEquip(boolean isCommonEquip) {
		this.isCommonEquip = isCommonEquip;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<EquipmentVO> getEquipList() {
		return equipList;
	}
	public void setEquipList(List<EquipmentVO> equipList) {
		this.equipList = equipList;
	}
	public List<FileInfoVO> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoVO> fileInfos) {
		this.fileInfos = fileInfos;
	}
	public Integer getCategoryIdSrch() {
		return categoryIdSrch;
	}
	public void setCategoryIdSrch(Integer categoryIdSrch) {
		this.categoryIdSrch = categoryIdSrch;
	}
	public String getEquipStatusType() {
		return equipStatusType;
	}
	public void setEquipStatusType(String equipStatusType) {
		this.equipStatusType = equipStatusType;
	}
	public Integer getParentCategoryIdSrch() {
		return parentCategoryIdSrch;
	}
	public void setParentCategoryIdSrch(Integer parentCategoryIdSrch) {
		this.parentCategoryIdSrch = parentCategoryIdSrch;
	}
}
