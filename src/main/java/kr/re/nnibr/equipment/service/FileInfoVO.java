package kr.re.nnibr.equipment.service;

public class FileInfoVO {

	private int fileInfoId;

	private String uuid;

	private String fileName;

	private String filesize;
	
	private int equipmentEquipId;

	public int getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(int fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public int getEquipmentEquipId() {
		return equipmentEquipId;
	}

	public void setEquipmentEquipId(int equipmentEquipId) {
		this.equipmentEquipId = equipmentEquipId;
	}
	
	
}
