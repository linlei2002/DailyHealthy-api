package top.llin.dailyhealthy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.mail.MessagingException;
import top.llin.dailyhealthy.entity.User;

public interface UserService extends IService<User> {
    void getUser() throws MessagingException;
}
