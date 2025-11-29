package top.llin.dailyhealthy.utils.cache;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.llin.dailyhealthy.utils.TokenUtils;

/**
 * Redis工具类
 * @author llin
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认过期时长为24小时，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L;
    /**
     * 过期时长为1小时，单位：秒
     */
    public final static long HOUR_ONE_EXPIRE = 60 * 60L;
    /**
     * 过期时长为6小时，单位：秒
     */
    public final static long HOUR_SIX_EXPIRE = 60 * 60 * 6L;

    /**
     *
     */
    public final static long FIVE_MINUTES_EXPIRE = 60 * 5L;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1L;

    /**
     * 保存Token
     * @param UserId 通过userId保存Token
     * @param token  Token
     */
    public void saveToken(String UserId, String token) {
        String key = "sys:token" + UserId;
        redisTemplate.opsForValue().set(key, token);
    }

    /**
     * 获取Token
     * @param UserId 通过userId获取Token
     * @return  Token
     */
    public String getToken(String UserId) {
        String key = "sys:token" + UserId;
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除Token
     * @param UserId 通过userId删除Token
     */
    public void deleteToken(String UserId) {
        String key = "sys:token" + UserId;
        redisTemplate.delete(key);
    }

    /**
     * 保存验证码
     * @param contact 联系方式：phone/email
     * @param code 验证码
     *             验证码有效期5分钟
     */
    public void saveVerificationCode(String contact, String code) {
        String key = "sys:verificationCode" + contact;
        redisTemplate.opsForValue().set(key, code,FIVE_MINUTES_EXPIRE);
    }

    /**
     * 获取验证码
     * @param contact 联系方式：phone/email
     * @return 验证码
     */
    public String getVerificationCode(String contact) {
        String key = "sys:verificationCode" + contact;
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除验证码
     * @param contact 联系方式：phone/email
     */
    public void deleteVerificationCode(String contact) {
        String key = "sys:verificationCode" + contact;
        redisTemplate.delete(key);
    }

}
