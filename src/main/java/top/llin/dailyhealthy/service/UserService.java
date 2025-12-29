package top.llin.dailyhealthy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.llin.dailyhealthy.entity.User;
import top.llin.dailyhealthy.transfer.DTO.LoginDTO;
import top.llin.dailyhealthy.transfer.VO.UserLoginVO;

public interface UserService extends IService<User> {
//    void getUser() throws MessagingException;

    UserLoginVO login(LoginDTO loginDTO);
}
