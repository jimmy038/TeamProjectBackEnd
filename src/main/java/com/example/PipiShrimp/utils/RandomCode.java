package com.example.PipiShrimp.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomCode {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int CODE_LENGTH = 20;

	public static String generateVerificationCode() {
		Random random = new SecureRandom();
		StringBuilder codeBuilder = new StringBuilder();

		for (int i = 0; i < CODE_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			codeBuilder.append(randomChar);
		}

		return codeBuilder.toString();
	}
}
