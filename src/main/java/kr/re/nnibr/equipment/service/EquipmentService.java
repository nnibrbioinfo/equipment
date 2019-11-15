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
public interface EquipmentService {
	
	public EquipmentVO selectEquipment(EquipmentVO equipmentVO) throws Exception;
	public List<EquipmentVO> selectEquipmentList(EquipmentVO equipmentVO) throws Exception;
	public Integer selectEquipmentListCnt(EquipmentVO equipmentVO) throws Exception;
	public int insertEquipment(EquipmentVO equipmentVO) throws Exception;
	public int updateEquipment(EquipmentVO equipmentVO) throws Exception;
	public int deleteEquipment(EquipmentVO equipmentVO) throws Exception;
}
