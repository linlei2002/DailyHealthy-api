package top.llin.dailyhealthy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import top.llin.dailyhealthy.config.authentication.SendEmailCodeConfig;
import top.llin.dailyhealthy.entity.User;
import top.llin.dailyhealthy.mapper.UserMapper;
import top.llin.dailyhealthy.service.UserService;
import top.llin.dailyhealthy.config.authentication.SendVerificationCodeConfig;
import top.llin.dailyhealthy.transfer.DTO.LoginDTO;
import top.llin.dailyhealthy.utils.TokenUtils;
import top.llin.dailyhealthy.utils.cache.RedisUtils;
import top.llin.dailyhealthy.transfer.VO.UserLoginVO;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final SendVerificationCodeConfig sendVerificationCodeConfig;
    private final SendEmailCodeConfig sendEmailCodeConfig;
    private final RedisUtils redisUtils;

    private final AuthenticationManager authentication;

    /**
     * 登录
     * @param loginDTO 封装用户登录信息
     * @return token
     */
    @Override
    public UserLoginVO login(LoginDTO loginDTO) {
        Authentication authenticate = authentication.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        UserDetails userDetail = (UserDetails)authenticate.getPrincipal();

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userDetail.getUsername());
        User user = baseMapper.selectOne(wrapper);

        if (user != null){
            String token = TokenUtils.createToken(user.getId(), user.getUserName());
            redisUtils.saveToken(user.getId(), token);
            return UserLoginVO.builder().id(user.getId()).token(token).build();
        } else {
            return null;
        }
    }

}
