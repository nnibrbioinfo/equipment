/**
 * 
 */
package kr.re.nnibr.equipment.service;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * @Date : 2019. 7. 30. 
 * @author : 오정수, Oh Jeongsu
 */
public class ApplicationFormVO extends PageVO {
	
	private int applicationId;
	private String applicant;
	private String affiliation;
	private String zipcode;
	private String detailAddress;
	private String address;
	private String phone;
	private String email;
	private String passwd;
	private String receiptDate;
	private String startDate;
	private String endDate;
	private String hours;
	private String purpose;
	private Boolean isAgreePrivacyInfo;
	private Boolean isPermission;
	private String applicationDate;
	private String signature;
	private int equipId;
	private EquipmentVO equipmentVO;
	private int assetNoId;
	private AssetNoInfoVO assetNoInfoVO;
	private String applicationCodeId;
	private String errMsg;
	private String denyComment;
	
	@JsonIgnore
	private int mailType;
	
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Boolean getIsAgreePrivacyInfo() {
		return isAgreePrivacyInfo;
	}
	public void setIsAgreePrivacyInfo(Boolean isAgreePrivacyInfo) {
		this.isAgreePrivacyInfo = isAgreePrivacyInfo;
	}
	public Boolean getIsPermission() {
		return isPermission;
	}
	public void setIsPermission(Boolean isPermission) {
		this.isPermission = isPermission;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getEquipId() {
		return equipId;
	}
	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}
	public EquipmentVO getEquipmentVO() {
		return equipmentVO;
	}
	public void setEquipmentVO(EquipmentVO equipmentVO) {
		this.equipmentVO = equipmentVO;
	}
	public int getAssetNoId() {
		return assetNoId;
	}
	public void setAssetNoId(int assetNoId) {
		this.assetNoId = assetNoId;
	}
	public AssetNoInfoVO getAssetNoInfoVO() {
		return assetNoInfoVO;
	}
	public void setAssetNoInfoVO(AssetNoInfoVO assetNoInfoVO) {
		this.assetNoInfoVO = assetNoInfoVO;
	}
	public String getApplicationCodeId() {
		return applicationCodeId;
	}
	public void setApplicationCodeId(String applicationCodeId) {
		this.applicationCodeId = applicationCodeId;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getDenyComment() {
		return denyComment;
	}
	public void setDenyComment(String denyComment) {
		this.denyComment = denyComment;
	}
	public int getMailType() {
		return mailType;
	}
	public void setMailType(int mailType) {
		this.mailType = mailType;
	}
}
