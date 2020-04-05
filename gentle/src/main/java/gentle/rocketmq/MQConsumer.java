package gentle.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Component
public class MQConsumer implements MessageListenerConcurrently {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    // ChatRocketMQConsumer 是所在组的组名
    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ChatRocketMQConsumer");

    /**
     * 初始化
     *
     * @throws MQClientException
     */
    @PostConstruct
    public void start() {
        try {

            // 设置 MQ 服务所在服务器 IP 地址
            consumer.setNamesrvAddr(namesrvAddr);
            // 从消息队列头开始消费 （ Offset : 偏移量 ）
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            // 集群消费模式
//            consumer.setMessageModel(MessageModel.CLUSTERING);
            // 订阅主题为“FZW_JY”， * 代表全部消息（subscribe 意为订阅）
            consumer.subscribe("FZW_JY", "*");
            // 注册消息监听器
            consumer.registerMessageListener(this);
            // 启动消费端
            consumer.start();
        } catch (MQClientException e) {
            logger.error("MQ：启动消费者失败：{}-{}", e.getResponseCode(), e.getErrorMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

        logger.info("MQ：启动消费者完成。");
    }

    /**
     * 消费消息
     * 消费时默认每次推送的是一条消息。
     * @param msgs
     * @param context
     * @return
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

        logger.info("收到消息总条数为：" + msgs.size());
        for (MessageExt msg : msgs) {
            try {
                String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                logger.info("MQ：消费者接收新信息: {} {} {} {} {}",
                        msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(), messageBody);

            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
            }
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @PreDestroy
    public void stop() {
        if (consumer != null) {
            consumer.shutdown();
            logger.error("MQ：关闭消费者");
        }
    }

}
