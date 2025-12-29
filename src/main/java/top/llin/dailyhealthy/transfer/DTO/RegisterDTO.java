package top.llin.dailyhealthy.transfer.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 注册DTO
 * @author llin
 */
@Data
public class RegisterDTO {

    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String phone;

    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "^\\d{4}$", message = "验证码格式错误")
    private String verificationCode;
}
