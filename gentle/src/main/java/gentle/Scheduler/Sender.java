package gentle.Scheduler;

import gentle.util.DateUtil;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务实现方式3  -- 简版 （ 此类单独执行一个任务 ）
 * @author silence
 * @date 2018/7/31 16:03
 */

@Component // 注册为一个bean
@EnableScheduling // 开启定时器
public class Sender {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Scheduled(fixedDelay = 2000) // 每 2s 执行1次。
    @Scheduled(fixedDelay = 900000000)
    public void send() {
        logger.info(" \n------------------------\n " +
                "定时任务内容 ：" + DateUtil.dateFormat().format(new Date()) +
                "\n------------------------\n");
    }
}
