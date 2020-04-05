package gentle.Scheduler;

import gentle.util.DateUtil;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务实现方式2 （ 此类和配置文件一起工作，由配置文件调用。 ）
 * @author silence
 * @date 2018/7/17 11:36
 */
public class JyTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    Show show;

    public void doScheduler() {

        logger.info("\n\n 定时JY--开始，当前时间： " + DateUtil.dateFormat().format(new Date()));
        try {
            show.execute(null); // 调用业务实现类 show ,执行定时任务的具体内容。
        } catch (JobExecutionException e) {
            e.printStackTrace();
        }
        logger.info("\n\n 定时JY--结束，当前时间：" + DateUtil.dateFormat().format(new Date()));
    }

}
