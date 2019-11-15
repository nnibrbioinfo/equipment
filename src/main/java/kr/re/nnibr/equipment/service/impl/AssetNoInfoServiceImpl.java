/**
 * 
 */
package kr.re.nnibr.equipment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.re.nnibr.equipment.service.AssetNoInfoService;
import kr.re.nnibr.equipment.service.AssetNoInfoVO;


/**
 * @author user
 * @Date : 2019. 9. 3. 
 * @author : 오정수, Oh Jeongsu
 */
@Service("assetNoInfoService")
public class AssetNoInfoServiceImpl implements AssetNoInfoService {

	@Autowired
	AssetNoInfoDAO assetNoInfoDAO;
	@Override
	public int selectAssetNoInfoCnt(AssetNoInfoVO assetNoInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return assetNoInfoDAO.selectAssetNoInfoCnt(assetNoInfoVO);
	}

}
