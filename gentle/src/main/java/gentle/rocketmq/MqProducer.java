package gentle.rocketmq;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqProducer {
	private static final Logger logger = LoggerFactory.getLogger(MQProducer.class);

	@Value("${spring.rocketmq.namesrvAddr}")
	private String namesrvAddr;

	private final DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");


	/**
	 * 初始化
	 */
	@PostConstruct
	public void start() {
		try {

			producer.setNamesrvAddr(namesrvAddr);
			producer.start();
		} catch (MQClientException e) {
            logger.error("MQ：启动生产者失败：{}-{}", e.getResponseCode(), e.getErrorMessage());
			throw new RuntimeException(e.getMessage(), e);
		}

        logger.info("MQ：启动生产者完成。");
	}

	/**
	 * 发送消息
	 *
	 * @param msg	  消息内容
	 * @param topic  主题
	 * @param tags   标签
	 * @param keys   唯一主键
	 */
	public void sendMessage(String msg, String topic, String tags, String keys) {

		try {
		    // 第 4 个参数是 byte[]
			Message mqMsg = new Message(topic, tags, keys, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
			producer.send(mqMsg, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
                    logger.info("MQ: 生产者发送消息 {}", sendResult);
				}

				@Override
				public void onException(Throwable throwable) {
                    logger.error(throwable.getMessage(), throwable);
				}
			});
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
		}
	}

	@PreDestroy
	public void stop() {
		if (producer != null) {
			producer.shutdown();
            logger.info("MQ：关闭生产者");
		}
	}
}