package com.sns.prj.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TokenUtil {

	public static String makeToken() {
		SecureRandom secureRandom;
		StringBuffer sb = new StringBuffer();
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			secureRandom.setSeed(secureRandom.generateSeed(128));

			byte byteData[] = digest.digest((secureRandom.nextLong() + "").getBytes());
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			sb = null;
		}
		return sb.toString();
	}
}
