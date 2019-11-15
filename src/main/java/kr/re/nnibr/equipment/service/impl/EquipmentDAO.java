/**
 * 
 */
package kr.re.nnibr.equipment.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.re.nnibr.equipment.service.ApplicationFormVO;
import kr.re.nnibr.equipment.service.EquipmentVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * @author user
 * @Date : 2019. 9. 3. 
 * @author : 오정수, Oh Jeongsu
 */

@Repository("equipmentDAO")
public class EquipmentDAO extends EgovComAbstractDAO {
	
	
	public int insertEquipment(EquipmentVO equipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) insert("equipmentDAO.insert", equipmentVO);
	}
	
	public EquipmentVO selectEquipment(EquipmentVO equipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return (EquipmentVO) select("equipmentDAO.selectEquipment", equipmentVO);
	}
	
	public List<EquipmentVO> selectEquipmentList(EquipmentVO equipmentVO) throws Exception {
		return (List<EquipmentVO>) list("equipmentDAO.selectEquipmentList",equipmentVO);
	}
	
	public Integer selectEquipmentListCnt(EquipmentVO equipmentVO) throws Exception {
		return (Integer) select("equipmentDAO.selectEquipmentListCnt", equipmentVO);
	}
	
	
	
}
