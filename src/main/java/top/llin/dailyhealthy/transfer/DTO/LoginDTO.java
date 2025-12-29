package top.llin.dailyhealthy.transfer.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 登录DTO
 * @author llin
 */
@Data
public class LoginDTO {

    @Schema(description = "用户名")
    @NotNull
    private String username;

    @NotNull
    @Schema(description = "密码")
    private String password;
}
