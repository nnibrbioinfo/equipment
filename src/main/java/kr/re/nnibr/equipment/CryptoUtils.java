/**
 * 
 */
package kr.re.nnibr.equipment;


import org.apache.commons.lang3.StringUtils;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import egovframework.rte.fdl.cryptography.EgovARIACryptoService;
import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;
import egovframework.rte.fdl.cryptography.impl.EgovARIACryptoServiceImpl;


/**
 * @author user
 * @Date : 2019. 9. 3. 
 * @author : 오정수, Oh Jeongsu
 */

@Component("cryptoUtils")
public class CryptoUtils {
	@Value("${CRYPTO.KEY}")
	private String CRYPTO_KEY;
	
	private EgovPasswordEncoder passwordEncoder;
	private EgovARIACryptoService aRIACryptoService;
	
	private void init(String key){
		passwordEncoder = new EgovPasswordEncoder();
		aRIACryptoService = new EgovARIACryptoServiceImpl();
		
		String hasedPassword = passwordEncoder.encryptPassword(key);
		passwordEncoder.setHashedPassword(hasedPassword);
		passwordEncoder.setAlgorithm("SHA-256");
		aRIACryptoService.setPasswordEncoder(passwordEncoder);
		aRIACryptoService.setBlockSize(1025);
	}
	
	public byte[] decrypted(String value, String key){
		
		init(key);
		
		value = StringUtils.trim(value);
		
		byte[] decrypted = aRIACryptoService.decrypt(Base64.decodeBase64(value.getBytes()), key);
		
		return decrypted;
		
	}
	
	public String decryptedString(String value, String key){
		
		key = StringUtils.trim(CRYPTO_KEY+key);
		
		value = StringUtils.trim(value);
		
		return new String(decrypted(value, key));
	}
	
	public byte[] encrypted(String value, String key){
		
		init(key);
		
		value = StringUtils.trim(value);
		
		byte[] encrypted = aRIACryptoService.encrypt(value.getBytes(), key);
		
		return encrypted;
		
	}
	
	public String encryptedString(String value, String key){
		
		key = StringUtils.trim(CRYPTO_KEY+key);
		
		return new String(Base64.encodeBase64(encrypted(value, key)));
	}
	
}
