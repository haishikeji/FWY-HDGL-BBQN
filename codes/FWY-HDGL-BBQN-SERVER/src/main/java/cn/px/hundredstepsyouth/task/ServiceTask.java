package cn.px.hundredstepsyouth.task;

import cn.px.hundredstepsyouth.core.activity.service.ActivityService;
import cn.px.hundredstepsyouth.core.order.service.OrderService;
import cn.px.hundredstepsyouth.core.user.service.UserAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executors;

/**
 * @author 品讯科技
 * @description 数据统计定时任务
 * @datetime 2020-06-04 17:08
 */
@Slf4j
@Component
public class ServiceTask implements SchedulingConfigurer {


    @Resource
    private ActivityService activityService;

    @Resource
    private OrderService orderService;

    @Resource
    private UserAppService appService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //设置执行器线程池大小
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(6));
        //定时更新活动状态 每天凌晨 00:03:00更新
        scheduledTaskRegistrar.addTriggerTask(activityStatusTiming());
        //订单超时处理 每5分钟处理一次
        scheduledTaskRegistrar.addTriggerTask(updateOrderStatus());
        //每天更新会员状态 每天凌晨 00:02:00更新
        scheduledTaskRegistrar.addTriggerTask(updateUserAppMemberState());

    }

    TriggerTask activityStatusTiming() {
        return new TriggerTask(() -> {
            log.info("【定时任务-更新活动状态】-------开始");
            activityService.activityStatusTiming();
            log.info("【定时任务-更新活动状态】-------结束");
        }, triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger("0 0/5 * * * ?");
            return cronTrigger.nextExecutionTime(triggerContext);
        });
    }

    TriggerTask updateOrderStatus() {
        return new TriggerTask(() -> {
            log.info("【定时任务-更新订单状态】-------开始");
            orderService.updateOrderStatus();
            log.info("【定时任务-更新订单状态】-------结束");
        }, triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger("0 0/5 * * * ?");
            return cronTrigger.nextExecutionTime(triggerContext);
        });
    }

    TriggerTask updateUserAppMemberState() {
        return new TriggerTask(() -> {
            log.info("【定时任务-更新用户会员状态】-------开始");
            appService.updateUserAppMemberState();
            log.info("【定时任务-更新用户会员状态】-------结束");
        }, triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger("0 2 0 * * ?");
            return cronTrigger.nextExecutionTime(triggerContext);
        });
    }
}

