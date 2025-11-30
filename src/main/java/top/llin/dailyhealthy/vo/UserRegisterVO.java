package top.llin.dailyhealthy.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 用户注册VO
 * @author llin
 */
@Data
public class UserRegisterVO {

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String phone;

}
