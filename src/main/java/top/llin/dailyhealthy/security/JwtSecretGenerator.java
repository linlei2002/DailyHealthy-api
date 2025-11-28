package top.llin.dailyhealthy.security;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * 随机生成token的密钥
 */
public class JwtSecretGenerator {
    private static final int SECRET_KEY_LENGTH = 32;

    public static String generateRandomSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[SECRET_KEY_LENGTH];
        secureRandom.nextBytes(keyBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);
    }
}
