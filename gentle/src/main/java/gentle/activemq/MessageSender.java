package gentle.activemq;

import gentle.util.DateUtil;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.Date;

/**
 * 消息发送者、生产者
 *
 * @author silence
 * @date 2018/7/31 16:03
 */

@Component // 注册为一个bean
@EnableScheduling // 开启定时器
public class MessageSender {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate; // 使用JmsMessagingTemplate将消息放入队列

    @Resource
    private Queue queue;

    //    @Scheduled(fixedDelay = 3000) // 每 3s 执行1次,将消息放入队列内
    @Scheduled(cron = "* * 2 * * ?") // 每 2s 执行1次,将消息放入队列内
    public void send() {
        this.jmsMessagingTemplate.convertAndSend(this.queue, " 消息内容 ：" + DateUtil.dateFormat().format(new Date()));
    }
}
