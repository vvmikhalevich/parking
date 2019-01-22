package com.itacademy.jd2.vvm.parking.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class SaltedMD5Example {
	public static String main(String passwordToHash) throws NoSuchAlgorithmException, NoSuchProviderException {

		byte[] salt = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

		String securePassword = getSecurePassword(passwordToHash, salt);
		System.out.println(securePassword); // Prints 83ee5baeea20b6c21635e4ea67847f66

		String regeneratedPassowrdToVerify = getSecurePassword(passwordToHash, salt);
		System.out.println(regeneratedPassowrdToVerify); // Prints 83ee5baeea20b6c21635e4ea67847f66
		return regeneratedPassowrdToVerify;
	}

	private static String getSecurePassword(String passwordToHash, byte[] salt) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(salt);
			// Get the hash's bytes
			byte[] bytes = md.digest(passwordToHash.getBytes());
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

}