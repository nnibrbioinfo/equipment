package kr.re.nnibr.equipment.service.impl;

import java.util.List;

import kr.re.nnibr.equipment.service.PicVO;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class PicDAO extends EgovComAbstractDAO{
	
	public List<PicVO> selectPicList(){
		
		return (List<PicVO>) list("picDAO.selectPicList");
	}

}
