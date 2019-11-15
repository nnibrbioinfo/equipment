package kr.re.nnibr.equipment.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovProperties;

@Controller
public class CommonController {
	
//	@RequestMapping(value="file/", method = RequestMethod.GET)
//	public void file(@RequestParam("name")String uuid )
	
	private static final String imageFilePath =  EgovProperties.getProperty("STORAGE.PATH")+"equipImage";
	
	@RequestMapping(value="/image.do")
    public void fileDownload(@RequestParam("image") String imageName,
												HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		if(StringUtils.isNotBlank(imageName)){
			imageName = imageName.replaceAll("/", "");
			imageName = imageName.replaceAll("\\\\", "");
			imageName = imageName.replaceAll("&", "");
			imageName.trim();
		} else {
//			name = ;
		}
		File reqFile = new File(imageFilePath,imageName);
		
		try {
			if(!reqFile.exists()){
				reqFile = new ClassPathResource("equip/nnibr.png").getFile();
			}
			
			InputStream in = new FileInputStream(reqFile);
			response.setContentType(selectMediaType(imageName));
			response.setHeader("Content-Disposition", "attachment; filename=" + reqFile.getName());
//			response.setHeader("Content-Length", String.valueOf(reqFile.length()));
			response.setContentLength((int)reqFile.length());
			FileCopyUtils.copy(in, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} finally {
			// TODO: handle exception
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}		
    }

	public static String selectMediaType(String fileName){
		String fileExt = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		String returnMediaType = MediaType.IMAGE_JPEG_VALUE;
		if (fileExt.equals("png")){
			returnMediaType = MediaType.IMAGE_PNG_VALUE;
		} else if (fileExt.equals("jpg")){
			returnMediaType = MediaType.IMAGE_JPEG_VALUE;
		}
		return returnMediaType;
	}
    
}
