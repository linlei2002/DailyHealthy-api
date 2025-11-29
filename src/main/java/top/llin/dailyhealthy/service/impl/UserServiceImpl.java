package top.llin.dailyhealthy.service.impl;

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

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final SendVerificationCodeConfig sendVerificationCodeConfig;
    private final SendEmailCodeConfig sendEmailCodeConfig;

    @Override
    public void getUser() throws MessagingException {
        User user = baseMapper.selectById(1);
        String code = sendVerificationCodeConfig.sendVerificationCode(user.getPhone());
        String codeMail = sendEmailCodeConfig.sendVerificationCode(user.getEmail());
        log.info("验证码为: {}", code);
        log.info("验证码为: {}", codeMail);
    }
}
