package top.llin.dailyhealthy.service.Impl;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.llin.dailyhealthy.DailyHealthyApplication;
import top.llin.dailyhealthy.service.UserService;

@SpringBootTest(classes = DailyHealthyApplication.class)
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    void getUser() throws MessagingException {
        userService.getUser();
    }

}