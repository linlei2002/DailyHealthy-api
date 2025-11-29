package top.llin.dailyhealthy.config.authentication;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 手机验证码发送
 */
@Configuration
public class SendVerificationCodeConfig {

    // 模板id
    private final String TEMPLATE_ID = "1";
    // 设置过期时间为5分钟
    private final String EXPIRE_MINUTES = "5分钟";

    @Value("${rlyun.serverIp}")
    private String serverIp;

    @Value("${rlyun.serverPort}")
    private String serverPort;

    @Value("${rlyun.accountSID}")
    private String accountSid;

    @Value("${rlyun.authToken}")
    private String authToken;

    @Value("${rlyun.appID}")
    private String appId;


    /**
     * 发送验证码
     * @param phone 手机号
     * @return
     */
    public String sendVerificationCode(String phone) {
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSid, authToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String code = generateVerificationCode();
        String[] templateParams = {code, EXPIRE_MINUTES};
        sdk.sendTemplateSMS(phone, TEMPLATE_ID, templateParams);
        return code;
    }

    /**
     * 生成6位验证码
     * @return
     */
    public String generateVerificationCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}
