package top.llin.dailyhealthy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.llin.dailyhealthy.entity.User;

public interface UserService extends IService<User> {
    void getUser();
}
