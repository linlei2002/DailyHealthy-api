package top.llin.dailyhealthy.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.llin.dailyhealthy.DailyHealthyApplication;
import top.llin.dailyhealthy.service.UserService;
import top.llin.dailyhealthy.transfer.VO.UserLoginVO;

@SpringBootTest(classes = DailyHealthyApplication.class)
@Slf4j
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    void getUser() {
    }

}