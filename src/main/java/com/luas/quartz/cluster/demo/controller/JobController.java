package com.luas.quartz.cluster.demo.controller;

import com.luas.quartz.cluster.demo.quartz.job.UserJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private Scheduler scheduler;

    @RequestMapping("/user")
    public Object user() throws Exception {
        JobBuilder jobBuilder = JobBuilder
                .newJob(UserJob.class)
                .withIdentity(new JobKey("UserJob", "default"))
                .withDescription("a demo quartz job")
                .storeDurably();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "luas");
        jobDataMap.put("age", 18);

        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(new TriggerKey("UserJob's trigger 1", "default"))
                .withSchedule(createSimpleScheduleBuilder())
                .forJob(new JobKey("UserJob", "default"))
                .usingJobData(jobDataMap)
                .startNow();

        this.scheduler.scheduleJob(jobBuilder.build(), triggerBuilder.build());

        return LocalDateTime.now();
    }

    private ScheduleBuilder createSimpleScheduleBuilder() {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMilliseconds(10000)
                .withRepeatCount(100);

        return simpleScheduleBuilder;
    }

}
