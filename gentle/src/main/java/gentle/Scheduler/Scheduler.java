package gentle.Scheduler;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 定时任务实现方式1 （ 由 main 方法直接可运行，业务实现在 Show 中。）
 * @author silence
 * @date 2018/7/17 11:36
 */
@Service("scheduler")
public class Scheduler {


    private static Logger _log = LoggerFactory.getLogger(org.quartz.Scheduler.class);

   /* public static void main(String[] args) {
        addJob(); // 此种方法调用要在方法中开启第6点：启动 调度器
    }*/

    public static Object addJob() {

        try {

            //1.创建Scheduler的工厂
            SchedulerFactory sf = new StdSchedulerFactory();
            //2.从工厂中获取调度器实例
            org.quartz.Scheduler scheduler = sf.getScheduler();

            //3.创建JobDetail
            JobDetail jb = JobBuilder.newJob(Show.class) // Show 为一个job,是要执行的一个任务( 即定时任务真正要运行的业务内容 )。
                    .withDescription("这是我的测试定时任务。") //job的描述
                    .withIdentity("jy2Job", "jy2Group") //job 的name和group
                    .build();

            //任务运行的时间，SimpleSchedle类型触发器有效
            long time = System.currentTimeMillis() + 3 * 1000L; // 3秒后启动任务
            Date statTime = new Date(time);

            //4.创建Trigger
            //使用SimpleScheduleBuilder或者CronScheduleBuilder
            Trigger t = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("jyTrigger", "jyTriggerGroup")
                    //.withSchedule(SimpleScheduleBuilder.simpleSchedule())
                    .startAt(statTime)  //默认当前时间启动 ,也可以写为：.startNow();
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")) //两秒执行一次
                    .build();

            //5.注册任务和定时器
            scheduler.scheduleJob(jb, t);

            //6.启动 调度器
//            scheduler.start();
            _log.info("启动时间 ： " + new Date());

        } catch (Exception e) {
            _log.info("定时任务出现异常 ： " + e);
        }
        return true;
    }
}
