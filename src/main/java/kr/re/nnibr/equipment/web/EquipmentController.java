/**
 * 
 */
package kr.re.nnibr.equipment.web;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.re.nnibr.equipment.CommonUtil;
import kr.re.nnibr.equipment.service.ApplicationFormVO;
import kr.re.nnibr.equipment.service.ApplicationService;
import kr.re.nnibr.equipment.service.AssetNoInfoService;
import kr.re.nnibr.equipment.service.AssetNoInfoVO;
import kr.re.nnibr.equipment.service.CategoryService;
import kr.re.nnibr.equipment.service.CategoryVO;
import kr.re.nnibr.equipment.service.EquipmentService;
import kr.re.nnibr.equipment.service.EquipmentVO;
import kr.re.nnibr.equipment.service.impl.AssetNoInfoDAO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @author user
 * @Date : 2019. 9. 4.
 * @author : 오정수, Oh Jeongsu
 */
@Controller
public class EquipmentController {

	
	private static final String applicationKey =  EgovProperties.getProperty("APPLICATION.KEY");

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AssetNoInfoService assetNoInfoService;

	@RequestMapping(value = {"/","/equipment/list.do"})
	public String list(@ModelAttribute EquipmentVO equipmentVO,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			ModelMap modelMap) throws Exception {

		pageIndex = Optional.ofNullable(pageIndex).orElse(1);
		pageIndex = pageIndex < 1 ? 1 : pageIndex;
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setPageSize(10);
		pageInfo.setRecordCountPerPage(10);
		pageInfo.setCurrentPageNo(pageIndex);

		equipmentVO.setCategoryId(Optional.ofNullable(equipmentVO.getCategoryId()).orElse(0));
		equipmentVO.setFirstIndex(pageInfo.getFirstRecordIndex());
		equipmentVO.setRecordCountPerPage(pageInfo.getRecordCountPerPage());

		List<CategoryVO> cateList = categoryService.selectCategoryList();
		CategoryVO categoryTree = categoryService.selectCategoryMap();
		List<EquipmentVO> equipList = equipmentService.selectEquipmentList(equipmentVO);
		equipmentVO.setEquipList(equipList);

		Integer equipCnt = equipmentService.selectEquipmentListCnt(equipmentVO);
		pageInfo.setTotalRecordCount(equipCnt);

		modelMap.addAttribute("pageInfo", pageInfo);
		modelMap.addAttribute("categoryList", cateList);
		modelMap.addAttribute("equipList", equipList);
		modelMap.addAttribute("categoryTree", categoryTree);
		modelMap.addAttribute("equipmentVO", equipmentVO);

		return "/equipment/equipList";
	}
	
	@RequestMapping(value = "/equipment/apply.do")
	public String apply(
			@RequestParam int id,
			@ModelAttribute("applicationForm") ApplicationFormVO applicationFormVO,
			ModelMap model) throws Exception {

		EquipmentVO equipmentVO = new EquipmentVO();
		equipmentVO.setEquipId(id);

		equipmentVO = equipmentService.selectEquipment(equipmentVO);
		if (equipmentVO == null) {
			return "redirect:/equipment/list.do";
		}
		
		applicationFormVO.setEquipId(id);
		Map<Integer,List<String>> dateMap = applicationService.selectApplicationReserveDates(applicationFormVO);
		
		AssetNoInfoVO assetNoInfoVO = new AssetNoInfoVO();
		assetNoInfoVO.setEquipId(id);
		
		int assetNoInfoCnt = assetNoInfoService.selectAssetNoInfoCnt(assetNoInfoVO);
		
		model.addAttribute("equipmentVO", equipmentVO);
		model.addAttribute("dateMap", dateMap);
		model.addAttribute("assetNoInfoCnt", assetNoInfoCnt);

		return "equipment/application";
	}

	@RequestMapping(value = "/equipment/insert.do")
	public String insert(
			@ModelAttribute("applicationForm") ApplicationFormVO applicationFormVO,
			RedirectAttributes redirectAttributes) throws Exception {
		

		int start = (int)(Math.random()*27);
		String passwd = UUID.randomUUID().toString().replace("-", "").substring(start, start+6);
		
		applicationFormVO.setPasswd(passwd);
		
		ApplicationFormVO apvo = new ApplicationFormVO();
		apvo.setEmail(applicationFormVO.getEmail());
		apvo.setPasswd(applicationFormVO.getPasswd());
		
		int applicationId = applicationService.insertApplicaion(applicationFormVO);
		
		apvo.setApplicationCodeId(applicationKey+applicationId);
		
		redirectAttributes.addFlashAttribute("applicationFormVO", apvo);

		return "redirect:/reservation/detail.do";
	}

	@RequestMapping(value = "/equipment/detail.do")
	public String detail(
			@RequestParam(value = "id", required = false) Integer equipId,
			ModelMap modelMap) throws Exception {

		EquipmentVO equipmentVO = new EquipmentVO();
		equipmentVO.setEquipId(equipId);
		EquipmentVO equipVO = equipmentService.selectEquipment(equipmentVO);
		if(equipVO == null) return "redirect:/equipment/list.do";
		modelMap.addAttribute("equip", equipVO);
		return "/equipment/detail";
	}

	@RequestMapping(value="/reservation.do")
	public String reservation(ApplicationFormVO applicationFormVO, ModelMap model){
		return "/equipment/reserveLogin";
	}
	
	@RequestMapping(value="/reservation/detail.do")
	public String reservationDetail(ApplicationFormVO applicationFormVO, ModelMap model, RedirectAttributes redirectAttributes){
		String errMsg = null;
		try {
			if(applicationFormVO.getEmail()!=null && applicationFormVO.getApplicationCodeId()!=null && applicationFormVO.getPasswd() != null){
				ApplicationFormVO savedApplicationFormVO = applicationService.selectApplicationformByIdentified(applicationFormVO);
				if(savedApplicationFormVO != null){
					model.addAttribute("reservInfo", savedApplicationFormVO);
					return "/equipment/reserveDetail";	
				} else {
					errMsg = "일치하는 접수 정보가 없습니다";
				}
			}
		} catch (NumberFormatException e) {
			errMsg = "일치하는 접수 정보가 없습니다";
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		ApplicationFormVO avo = new ApplicationFormVO();
		avo.setErrMsg(errMsg);
//		model.addAttribute("applicationFormVO", avo);
		redirectAttributes.addFlashAttribute("applicationFormVO", avo);
		return "redirect:/reservation.do";
	}

	@RequestMapping(value = "/reservation/document.do", method=RequestMethod.POST)
	public void document(ApplicationFormVO applicationFormVO,@RequestParam(required=false)Integer ims, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		boolean innerChk = CommonUtil.innerChk(request);
		
		ApplicationFormVO qaVO = null;
		if(ims != null && ims == 1 && innerChk){
			qaVO = applicationService.selectApplicationformForIms(applicationFormVO);
			
		} else {
			qaVO = applicationService.selectApplicationformByIdentified(applicationFormVO);
		}

		if (qaVO != null) {
			String pdfFilepath = applicationService.makeApplicatonform(qaVO);

			File pdfFile = new File(pdfFilepath);
			int fSize = (int) pdfFile.length();
			if (pdfFile.exists()) {
				try {
					response.setContentType("application/pdf");
//					response.addHeader("Content-Disposition", "attachment; filename=" + new String("신청서.pdf".getBytes("UTF-8"), "ISO-8859-1"));

					String fileName = "nnibr_장비예약신청서.pdf";
//				    System.out.println(header);

					String header = request.getHeader("User-Agent");					
					if (header.contains("MSIE") || header.contains("Trident")) {
				       String docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
				       response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
					} else {
				       String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				       response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
					}
					
					response.setContentLength(fSize);

					FileUtils.copyFile(pdfFile, response.getOutputStream());

					response.getOutputStream().flush();
					response.getOutputStream().close();
					pdfFile.delete();
				} finally {
					response.getOutputStream().flush();
					response.getOutputStream().close();
				}
			}
		}
	}
	
}
