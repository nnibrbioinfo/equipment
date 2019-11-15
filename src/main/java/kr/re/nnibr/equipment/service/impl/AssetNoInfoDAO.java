/**
 * 
 */
package kr.re.nnibr.equipment.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.re.nnibr.equipment.service.ApplicationFormVO;
import kr.re.nnibr.equipment.service.AssetNoInfoVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * @author user
 * @Date : 2019. 9. 3. 
 * @author : 오정수, Oh Jeongsu
 */

@Repository("assetNoInfoDAO")
public class AssetNoInfoDAO extends EgovComAbstractDAO {
	
	
	
	public Integer selectAssetNoInfoCnt(AssetNoInfoVO assetNoInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) select("assetNoInfoDAO.selectAssetNoInfoCnt", assetNoInfoVO);
	}
}
