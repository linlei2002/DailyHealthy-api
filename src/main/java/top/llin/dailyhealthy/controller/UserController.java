package top.llin.dailyhealthy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.llin.dailyhealthy.common.result.Result;
import top.llin.dailyhealthy.service.UserService;
import top.llin.dailyhealthy.transfer.DTO.LoginDTO;
import top.llin.dailyhealthy.transfer.VO.UserLoginVO;

/**
 * 用户控制器
 */
@RestController
@Tag(name = "用户控制器")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<UserLoginVO> login(@RequestBody @Validated LoginDTO loginDTO) {
        return Result.ok(userService.login(loginDTO));
    }
}
