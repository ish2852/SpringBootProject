package com.sns.prj.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtil {

	public static String makeToken() {
		String token = null;
		try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				SecureRandom random = new SecureRandom();
				byte bytes[] = new byte[128];
				random.nextBytes(bytes);
				token = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			token = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
}