package top.llin.dailyhealthy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @Schema(description = "用户id")
    private String id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    @JsonIgnore
    private String password;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "删除标志;0-未删除;1-已删除")
    private Integer deleteFlag;

}
