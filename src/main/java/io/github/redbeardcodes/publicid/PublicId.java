package io.github.redbeardcodes.publicid;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Generates URL-safe random identifiers suitable for public-facing resource identifiers.
 * The configured bit length specifies the entropy of the random input,
 * not the encoded string length.
 */
public final class PublicId {

	private final int byteLength;

	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * @param bits number of bits of entropy to use for generating IDs, in multiples of 8
	 */
	public PublicId() {
		this(128);
	}

	/**
	 * @param bits number of bits of entropy to use for generating IDs, in multiples of 8
	 */
	public PublicId(int bits) {
		if (bits <= 0 || bits > 512 || bits % 8 != 0)
			throw new IllegalArgumentException("bits must be a positive multiple of 8, and less than or equal to 512");
		this.byteLength = bits / 8;
	}

	/**
	 * Generates an unpadded, URL-safe Base64 identifier.
	 * Uniqueness is not guaranteed.
	 *
	 * @return a random identifier
	 */
	public String generate() {
		byte[] bytes = new byte[byteLength];
		RANDOM.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
}
