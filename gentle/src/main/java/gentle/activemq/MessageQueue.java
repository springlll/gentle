package gentle.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.jms.Queue;

/**
 * @author silence
 * @date 2018/8/1 10:42
 */
@Component
public class MessageQueue{

    @Bean  // 返回一个名为 jy_message 的队列,并且注册为 bean 。
    public Queue queue(){
        return new ActiveMQQueue("jy_message");
    }
}
