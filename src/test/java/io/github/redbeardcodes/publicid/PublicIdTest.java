package io.github.redbeardcodes.publicid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PublicIdTest {

    @Test
    void defaultConstructorGenerates128BitIds() {
        PublicId generator = new PublicId();

        // 128 bits = 16 bytes = 22 unpadded Base64url characters
        assertEquals(22, generator.generate().length());
    }

    @Test
    void configuredBitLengthDeterminesOutputLength() {
        assertEquals(11, new PublicId(64).generate().length());
        assertEquals(16, new PublicId(96).generate().length());
        assertEquals(22, new PublicId(128).generate().length());
        assertEquals(43, new PublicId(256).generate().length());
        assertEquals(86, new PublicId(512).generate().length());
    }

    @Test
    void outputContainsOnlyUrlSafeCharacters() {
        PublicId generator = new PublicId(128);

        for (int i = 0; i < 100; i++) {
            assertTrue(generator.generate().matches("[A-Za-z0-9_-]+"));
        }
    }

    @Test
    void outputHasNoBase64Padding() {
        PublicId generator = new PublicId(128);

        for (int i = 0; i < 100; i++) {
            assertFalse(generator.generate().contains("="));
        }
    }

    @Test
    void rejectsInvalidBitLengths() {
        assertThrows(IllegalArgumentException.class,
                () -> new PublicId(0));
        assertThrows(IllegalArgumentException.class,
                () -> new PublicId(-8));
        assertThrows(IllegalArgumentException.class,
                () -> new PublicId(7));
        assertThrows(IllegalArgumentException.class,
                () -> new PublicId(520));
    }

    @Test
    void generatesDifferentIdsInRepeatedUse() {
        PublicId generator = new PublicId(128);
        String first = generator.generate();

        boolean different = false;
        for (int i = 0; i < 100; i++) {
            if (!first.equals(generator.generate())) {
                different = true;
                break;
            }
        }

        assertTrue(different);
    }
}

