/**
 * 
 */
package kr.re.nnibr.equipment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.re.nnibr.equipment.CryptoUtils;
import kr.re.nnibr.equipment.service.EquipmentService;
import kr.re.nnibr.equipment.service.EquipmentVO;

/**
 * @author user
 * @Date : 2019. 9. 6. 
 * @author : 오정수, Oh Jeongsu
 */

@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {
	
	
	@Resource(name = "equipmentDAO")
	private EquipmentDAO equipmentDAO;
	
	/* (non-Javadoc)
	 * @see kr.re.nnibr.equipment.service.EquipmentService#selectEquipment(kr.re.nnibr.equipment.service.EquipmentVO)
	 */
	@Override
	public EquipmentVO selectEquipment(EquipmentVO equipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return equipmentDAO.selectEquipment(equipmentVO);
	}

	/* (non-Javadoc)
	 * @see kr.re.nnibr.equipment.service.EquipmentService#selectEquipmentList(kr.re.nnibr.equipment.service.EquipmentVO)
	 */
	@Override
	public List<EquipmentVO> selectEquipmentList(EquipmentVO equipmentVO)
			throws Exception {
		// TODO Auto-generated method stub
		return equipmentDAO.selectEquipmentList(equipmentVO);
	}

	@Override
	public Integer selectEquipmentListCnt(EquipmentVO equipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return equipmentDAO.selectEquipmentListCnt(equipmentVO);
	}


	/* (non-Javadoc)
	 * @see kr.re.nnibr.equipment.service.EquipmentService#insertEquipment(kr.re.nnibr.equipment.service.EquipmentVO)
	 */
	@Override
	public int insertEquipment(EquipmentVO equipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see kr.re.nnibr.equipment.service.EquipmentService#updateEquipment(kr.re.nnibr.equipment.service.EquipmentVO)
	 */
	@Override
	public int updateEquipment(EquipmentVO equipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see kr.re.nnibr.equipment.service.EquipmentService#deleteEquipment(kr.re.nnibr.equipment.service.EquipmentVO)
	 */
	@Override
	public int deleteEquipment(EquipmentVO equipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
