package top.llin.dailyhealthy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.llin.dailyhealthy.entity.User;
import top.llin.dailyhealthy.mapper.UserMapper;
import top.llin.dailyhealthy.service.UserService;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void getUser() {
        User user = baseMapper.selectById(1);
        log.info("查询用户: {}", user);
    }
}
