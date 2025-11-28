package top.llin.dailyhealthy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import top.llin.dailyhealthy.security.JwtSecretGenerator;

import java.util.Date;

@Slf4j
public class TokenUtils {
    // 颁发者
    private static String ISSUER = "DailyHealthy";
    private static String secretKey = JwtSecretGenerator.generateRandomSecretKey();

    // 过期时间
    private static final long EXPIATION_TIME = 60 * 60 * 1000L;

    /**
     * 根据userId和username创建Token
     * @param userId 用户Id
     * @param username 用户名
     * @return
     */
    public static String createToken(String userId, String username) {
        try {
            // 签名算法
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            //过期时间
            Date expireAt = new Date(System.currentTimeMillis() + EXPIATION_TIME);

            // 创建token
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(expireAt)
                    .withJWTId(java.util.UUID.randomUUID().toString())

                    // 自定义声明
                    .withClaim("userId",userId)
                    .withClaim("username",username)

                    .sign(algorithm);
        } catch (Exception e) {
            log.info("创建Token失败: " + e.getMessage());
            return null;
        }
    }

    /**
     * 验证Token
     * @param token
     * @return 解析token后的DecodedJWT对象，包含所有声明
     */
    public static DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.info("验证Token失败: " + e.getMessage());
            return null;
        }
    }
}
