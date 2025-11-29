package top.llin.dailyhealthy.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.llin.dailyhealthy.config.authentication.SendEmailCodeConfig;
import top.llin.dailyhealthy.entity.User;
import top.llin.dailyhealthy.mapper.UserMapper;
import top.llin.dailyhealthy.service.UserService;
import top.llin.dailyhealthy.config.authentication.SendVerificationCodeConfig;
import top.llin.dailyhealthy.utils.TokenUtils;
import top.llin.dailyhealthy.utils.cache.RedisUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final SendVerificationCodeConfig sendVerificationCodeConfig;
    private final SendEmailCodeConfig sendEmailCodeConfig;
    private final RedisUtils redisUtils;

    @Override
    public void getUser() throws MessagingException {
        User user = baseMapper.selectById(1);
        String token = TokenUtils.createToken(user.getId(), user.getUserName());
        String codeMail = sendEmailCodeConfig.sendVerificationCode(user.getEmail());
        log.info("验证码为: {}", codeMail);
        redisUtils.saveVerificationCode(user.getEmail(), codeMail);
        redisUtils.saveToken(user.getId(), token);
        log.info("token为: {}", token);
        DecodedJWT decodedJWT = TokenUtils.verifyToken(redisUtils.getToken(user.getId()));
        if (decodedJWT != null) {
            String username = decodedJWT.getClaim("username").asString();
            String userId = decodedJWT.getClaim("userId").asString();
            log.info("根据redis获取用户名: {}, 用户Id: {}", username, userId);
        }
    }
}
