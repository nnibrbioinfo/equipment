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
public class LocationVO extends PageVO {
	
	private int locationId;
	private String locationName;
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	

}
