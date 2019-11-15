/**
 * 
 */
package kr.re.nnibr.equipment.service;

import java.util.List;
import java.util.Map;

/**
 * @author user
 * @Date : 2019. 9. 3. 
 * @author : 오정수, Oh Jeongsu
 */
public interface ApplicationService {
	public int insertApplicaion(ApplicationFormVO applicationFormVO) throws Exception;
	
	public ApplicationFormVO selectApplication(ApplicationFormVO applicationFormVO) throws Exception;
	
	public ApplicationFormVO selectApplicationformForIms(ApplicationFormVO applicationFormVO) throws Exception;
	
	public ApplicationFormVO selectApplicationformByIdentified(ApplicationFormVO applicationFormVO) throws Exception;
	
	public List<ApplicationFormVO> selectApplicaionList(ApplicationFormVO applicationFormVO) throws Exception;
	
	public Map<Integer,List<String>> selectApplicationReserveDates(ApplicationFormVO applicationFormVO) throws Exception;
	
	public String makeApplicatonform(ApplicationFormVO applicationFormVO) throws Exception;
}
