package com.ximuyi.demo.shedule;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * 具体的实现先从这个类入手：QuartzAutoConfiguration
 */
@Configuration
public class QuartzConfigration {

	private static final String Group = "SpringBoot";

	@Bean("simpleJobDetail")
	public JobDetailFactoryBean simpleJobDetailFactoryBean()
	{
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(QuartzSimpleJob.class);
		factory.setDurability(true);
		factory.setGroup(Group);
		factory.setName("jobDetail");
		return  factory;
	}

	@Bean("cronJobDetail")
	public JobDetailFactoryBean cronJobDetailFactoryBean()
	{
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(QuartzCronJob.class);
		factory.setDurability(true);
		factory.setName("cronDetail");
		factory.setGroup(Group);
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("key", "cron");
		factory.setJobDataMap(dataMap);
		return  factory;
	}

	@Bean(name = "simpleTrigger")
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(@Qualifier("simpleJobDetail") JobDetailFactoryBean jobDetailFactoryBean) {
        SimpleTriggerFactoryBean triggerFactoryBean = new SimpleTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        triggerFactoryBean.setRepeatInterval(30000);
        triggerFactoryBean.setGroup(Group);
		triggerFactoryBean.setName("simpleTrigger");
		triggerFactoryBean.setDescription("简单触发器");
        return triggerFactoryBean;
    }


	@Bean(name = "cronTrigger")
	public CronTriggerFactoryBean cronTriggerFactoryBean(@Qualifier("cronJobDetail") JobDetailFactoryBean jobDetailFactoryBean)
	{
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
		cronTriggerFactoryBean.setCronExpression("* 0/1 * * * ?");
		cronTriggerFactoryBean.setGroup(Group);
		cronTriggerFactoryBean.setName("cronTrigger");
		cronTriggerFactoryBean.setDescription("表达式触发器");
		return cronTriggerFactoryBean;
	}

	@Bean("codeJobDetail")
	public JobDetail jobDetail() {
		return JobBuilder.newJob(QuartzCodeJob.class)
				.withIdentity("codeDetail", Group)
				.storeDurably(true).build();
	}

	@Bean("codeTrigger")
	public Trigger trigger() {
		SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(50).repeatForever();
		return TriggerBuilder.newTrigger().forJob(jobDetail()).withIdentity("codeTrigger", Group)
				.withDescription("编码触发器").withSchedule(builder).build();
	}

	@Bean
	public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(QuartzProperties properties) {
		return (schedulerFactoryBean) -> {
			schedulerFactoryBean.setAutoStartup(false);
			schedulerFactoryBean.setStartupDelay(60);
			schedulerFactoryBean.setSchedulerName("SpringBoot");
		};
	}

	@Bean("quartzRunner")
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			SchedulerFactoryBean schedulerFactoryBean = ctx.getBean(SchedulerFactoryBean.class);
			//schedulerFactoryBean.start();
		};
	}

	/***
	 * 下面是自己实现定时调度
	 */
	//@Bean("quartzConfig")
	//public QuartzJobConfig jobConfigBean(){
	//	QuartzJobConfig jobConfigBean = new QuartzJobConfig();
	//
	//	jobConfigBean.setJobDetails(new JobDetail[]{
	//			simpleJobDetailFactoryBean().getObject(),
	//			cronJobDetailFactoryBean().getObject()
	//	});
	//	jobConfigBean.setTriggers(new Trigger[]{
	//			simpleTriggerFactoryBean(simpleJobDetailFactoryBean()).getObject(),
	//			cronTriggerFactoryBean(cronJobDetailFactoryBean()).getObject()
	//	});
	//
	//	return jobConfigBean;
	//}
	//
	//@Bean
	//public SchedulerFactoryBean schedulerFactoryBean(QuartzJobConfig quartzJobConfig, @Qualifier("quartzDataSource") DataSource source){
	//	SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
	//	/***
	//	 * org.quartz.SchedulerException: Jobs added with no trigger must be durable.
	//	 * 注释下面的代码~
	//	 */
	//	//schedulerFactoryBean.setJobDetails(quartzJobConfig.getJobDetails());
	//	schedulerFactoryBean.setTriggers(quartzJobConfig.getTriggers());
	//	schedulerFactoryBean.setStartupDelay(10);
	//	schedulerFactoryBean.setAutoStartup(false);
	//	schedulerFactoryBean.setDataSource(source);
	//	schedulerFactoryBean.setSchedulerName("");
	//	return schedulerFactoryBean;
	//}
	//
}
