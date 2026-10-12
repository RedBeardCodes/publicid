# PublicId

Generates URL-safe random identifiers suitable for public-facing resource identifiers with a configurable bit-length.

The configured bit length specifies the entropy of the random input, not the encoded string length.

## Examples

```java
PublicId generator = new PublicId(); // default bit length is 128 bits
String id = generator.generate();
```

Uniqueness is not guaranteed. Please ensure that proper constraints are placed on the database and that errors/collisions are properly handled.

The following table shows the relationship between the requested entropy (passed to the constructor), random bytes, and the number of Base64 characters returned.

| Entropy Bits | Random Bytes | Base64 Characters |
|---|---|---|
| 64 | 8 | 11 |
| 96 | 12 | 16 |
| 128 | 16 | 22 |
| 192 | 24 | 32 |
| 256 | 32 | 43 |
| 384 | 48 | 64 |
| 512 | 64 | 86 |

All bit lengths passed to the constructor *must* be multiples of 8.

```java
PublicId generator = new PublicId(256);
String id = generator.generate();
```

