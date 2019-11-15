/**
 * 
 */
package kr.re.nnibr.equipment.service;


/**
 * @author user
 * @Date : 2019. 7. 29. 
 * @author : 오정수, Oh Jeongsu
 */
public class AssetNoInfoVO extends PageVO {

	private Long assetNoId;

	private String assetNo;

	private String rfid;
	private int equipId;
	
	public Long getAssetNoId() {
		return assetNoId;
	}
	public void setAssetNoId(Long assetNoId) {
		this.assetNoId = assetNoId;
	}
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public int getEquipId() {
		return equipId;
	}
	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}
	
	
	
}
