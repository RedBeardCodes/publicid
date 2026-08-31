package io.github.redbeardcodes;

import java.security.SecureRandom;
import java.util.Base64;

public class PublicId {

	private static final SecureRandom RANDOM = new SecureRandom();

	private PublicId() {}

	public static String generate() {
		byte[] bytes = new byte[10];
		RANDOM.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
}
