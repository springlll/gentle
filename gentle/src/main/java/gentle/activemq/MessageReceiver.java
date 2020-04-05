package gentle.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者、消费者
 * @author silence
 * @date 2018/7/31 16:03
 */

@Component
public class MessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 使用 @JmsListener 注解来监听指定的某个队列内的消息。
     * 有新消息时，则取出队列内消息并进行处理。
     **/
    @JmsListener(destination="jy_message")
    public void removeMessage(String msg){

        // 输出队列内的消息
        logger.info("\n -------------------\n " +
                " 接收到的消息是: " + msg
                + "\n -------------------\n");
    }

}
