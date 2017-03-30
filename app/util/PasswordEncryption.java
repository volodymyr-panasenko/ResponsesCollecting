package util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Class-utility that contains the method to encrypt 
 * the administrator password by dint of algorithm MD5
 */

public class PasswordEncryption {
	
	public static String encrypt(String password) {
		return DigestUtils.md5Hex(password);
	}
	
}
