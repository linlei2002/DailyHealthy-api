package top.llin.dailyhealthy.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import top.llin.dailyhealthy.vo.UserLoginVO;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final SendVerificationCodeConfig sendVerificationCodeConfig;
    private final SendEmailCodeConfig sendEmailCodeConfig;
    private final RedisUtils redisUtils;

    /**
     * 登录
     * @param userLoginVO 封装用户登录信息
     * @return token
     */
    @Override
    public String login(UserLoginVO userLoginVO) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getUserName, userLoginVO.getUsername())
                    .eq(User::getPassword, userLoginVO.getPassword());
            User user = baseMapper.selectOne(wrapper);
            if (user != null) {
                String token = TokenUtils.createToken(user.getId(), user.getUserName());
                redisUtils.saveToken(user.getId(), token);
                return token;
            } else {
                return "登录失败";
            }
    }

}
