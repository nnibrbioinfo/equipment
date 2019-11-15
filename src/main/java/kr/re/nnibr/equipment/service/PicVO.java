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
public class PicVO{
	
	private long picId;
	private String name;
	private String phone;
	private String fax;
	private String email;
	
	public long getPicId() {
		return picId;
	}
	public void setPicId(long picId) {
		this.picId = picId;
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
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
