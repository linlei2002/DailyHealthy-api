package top.llin.dailyhealthy.config.authentication;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 发送邮件验证码
 */
@Configuration
public class SendEmailCodeConfig {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 生成6位验证码
     * @return
     */
    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    /**
     * 生成html内容，用于发送邮件
     * @param code
     * @return
     */
    private String buildHtmlContent(String code){
        return "<html>"
                + "<body style='font-family: Arial, sans-serif; padding: 20px;'>"
                + "  <div style='max-width: 600px; margin: auto; border: 1px solid #ddd; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>"
                + "    <h2 style='color: #007bff;'>用户注册/登录验证</h2>"
                + "    <p>您好，</p>"
                + "    <p>您的验证码是：</p>"
                + "    <div style='background-color: #f4f4f4; padding: 15px; text-align: center; border-radius: 5px; margin: 20px 0;'>"
                + "      <strong style='font-size: 32px; color: #dc3545; letter-spacing: 5px;'>" + code + "</strong>"
                + "    </div>"
                + "    <p>请在 **" + "10分钟** 内使用该验证码进行验证。</p>"
                + "    <p style='margin-top: 30px; font-size: 12px; color: #999;'>[系统邮件，请勿回复]</p>"
                + "  </div>"
                + "</body>"
                + "</html>";
    }

    /**
     * 邮箱发送验证码
     * @param to 收件人邮箱
     * @return
     */
    public String sendVerificationCode(String to) throws MessagingException {
        String code = generateVerificationCode();
        String htmlContent = buildHtmlContent(code);

        String subject = "用户注册/登录验证";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
        return code;
    }
}
