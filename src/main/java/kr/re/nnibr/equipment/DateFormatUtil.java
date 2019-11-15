package kr.re.nnibr.equipment;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.time.DateFormatUtils;



public class DateFormatUtil {
	
	public static String dateFormat(String dateString) throws ParseException{
		
		SimpleDateFormat originFormat = new SimpleDateFormat("yyyy-MM-dd");
	
		
		return DateFormatUtils.format(originFormat.parse(dateString), "yyyy년 M월 d일");
		

	}
	
}
