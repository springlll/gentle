package gentle.util.mail;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

/**
 * 发送邮件
 * @author silence
 * @date 2018/9/10 15:34
 */
public class Mail {

    // 更多：http://www.simplejavamail.org/#/features
    public static void main(String[] args) {
        sendMail();
    }

    private static void sendMail(){

        Email email = EmailBuilder.startingBlank()
                .from("樊宝宝", "xxxxx@163.com") // 发件人名字、邮箱
                .to("微风轻许", "xxxxx@qq.com") // 收件人名字、邮箱
                // .to("其它收件人", "其它收件人邮箱地址")   // 抄送
                .withSubject("我是邮件主题")
                .withPlainText("我是邮件正文 ...")
                .buildEmail();

        MailerBuilder  //　邮箱服务、端口、发件人邮箱账号、发件人邮箱密码
                .withSMTPServer("smtp.163.com", 25, "xxxxx@163.com", "xxxx")
                .buildMailer()
                .sendMail(email);

    }

}
