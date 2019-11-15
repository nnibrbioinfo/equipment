/**
 * 
 */
package kr.re.nnibr.equipment.service;

import java.util.List;


/**
 * @Date : 2019. 7. 29. 
 * @author : 오정수, Oh Jeongsu
 */
public class ManagerVO extends PageVO {
	
	private int managerId;
	private String name;
	private String phone;
	private String email;
	private String department;
	private List<EquipmentVO> equipments;
	
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public List<EquipmentVO> getEquipments() {
		return equipments;
	}
	public void setEquipments(List<EquipmentVO> equipments) {
		this.equipments = equipments;
	}
	
	
}
