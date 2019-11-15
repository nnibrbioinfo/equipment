/**
 * 
 */
package kr.re.nnibr.equipment.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.re.nnibr.equipment.service.ApplicationFormVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * @author user
 * @Date : 2019. 9. 3. 
 * @author : 오정수, Oh Jeongsu
 */

@Repository("applicationDAO")
public class ApplicationDAO extends EgovComAbstractDAO {
	
	
	public int insertApplication(ApplicationFormVO applicationFormVO) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) insert("applicationDAO.insert", applicationFormVO);
	}
	
	
	public ApplicationFormVO selectApplication(ApplicationFormVO applicationFormVO) throws Exception {
		// TODO Auto-generated method stub
		return (ApplicationFormVO) select("applicationDAO.select", applicationFormVO);
	}
	
	public List<ApplicationFormVO> selectApplicationReserveDates(ApplicationFormVO applicationFormVO) throws Exception {
		// TODO Auto-generated method stub
		return (List<ApplicationFormVO>) list("applicationDAO.selectApplicationReserveDates", applicationFormVO);
	}
	
}
