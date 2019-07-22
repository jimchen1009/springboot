package com.ximuyi.demo.shedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzSimpleJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(QuartzSimpleJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("hello word simple quartz");
	}
}
