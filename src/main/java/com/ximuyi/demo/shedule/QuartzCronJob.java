package com.ximuyi.demo.shedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.stream.Collectors;

public class QuartzCronJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(QuartzCronJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		List<String> stringList = context.getMergedJobDataMap().entrySet()
				.stream().map(entry -> String.format("{%s:%s}", entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
		logger.info("hello word cron quartz, {}", stringList);
	}
}
