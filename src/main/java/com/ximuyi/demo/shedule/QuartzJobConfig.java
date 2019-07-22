package com.ximuyi.demo.shedule;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public class QuartzJobConfig {

	private JobDetail[] jobDetails;

	private Trigger[] triggers;

	public JobDetail[] getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(JobDetail[] jobDetails) {
		this.jobDetails = jobDetails;
	}

	public Trigger[] getTriggers() {
		return triggers;
	}

	public void setTriggers(Trigger[] triggers) {
		this.triggers = triggers;
	}
}
