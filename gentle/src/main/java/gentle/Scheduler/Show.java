package gentle.Scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 定时任务中的业务类，根据需求实现。
 * @author silence
 * @date 2018/7/17 11:37
 */
@Service("show")
public class Show implements Job {


    private static Logger _log = LoggerFactory.getLogger(Show.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        _log.info("\n\n-------------------------------\n " +
                "It is running and the time is : " + new Date()+
                "\n-------------------------------\n");
    }

}
