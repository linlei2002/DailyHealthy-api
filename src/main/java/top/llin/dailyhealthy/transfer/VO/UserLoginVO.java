package top.llin.dailyhealthy.transfer.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 用户登录VO,向前端返回数据
 * @author llin
 */
@Data
@Builder
public class UserLoginVO {

    // 用户id
    @Schema(description = "用户id")
    private String id;

    // token
    @Schema(description = "token")
    private String token;

}
