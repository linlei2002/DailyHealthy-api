package top.llin.dailyhealthy.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.llin.dailyhealthy.service.UserService;
import top.llin.dailyhealthy.vo.UserLoginVO;

/**
 * 用户控制器
 */
@RestController
@Tag(name = "用户控制器")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @RequestMapping("/login")
    public String login(UserLoginVO userLoginVO) {
        return userService.login(userLoginVO);
    }
}
