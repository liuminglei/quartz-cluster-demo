package com.luas.quartz.cluster.demo.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class UserJob extends QuartzJobBean {

    @Value("${server.port}")
    private String port;

    private String name;

    private Integer age;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println(String.format("the user job is execute on port %s. it's name is %s, age is %s", port, name, age));
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
