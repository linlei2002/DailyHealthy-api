package top.llin.dailyhealthy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private String id;
    private String userName;
    private String password;
    private Integer deleteFlag;
}
