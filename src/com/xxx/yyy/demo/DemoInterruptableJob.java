package com.xxx.yyy.demo;

import java.util.Calendar;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoInterruptableJob implements InterruptableJob {
	// logging services
	private static Logger LOGGER = LoggerFactory .getLogger(DemoInterruptableJob.class);

	// job name
	private String jobName = "";
	private boolean isInterrupted = false;

	public DemoInterruptableJob() {
	}

	/**
	 * Called by the {@link org.quartz.Scheduler} when a
	 * {@link org.quartz.Trigger} fires that is associated with the Job.
	 * 
	 * @throws JobExecutionException
	 *             if there is an exception while executing the job.
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int i = 0;
		try {
			// jobName = context.getJobDetail().getFullName();
			jobName = context.getJobDetail().getDescription();
			LOGGER.info("---- " + jobName + " executing at " + Calendar.getInstance().getTime());

			while (i < 1000000000L) {
				if (isInterrupted) {
					LOGGER.info("- inside isInterrupted -");
					throw new InterruptedException();
				} else {
					if (i % 100000000L == 0) {
						LOGGER.info("---- i:: " + i);
					}
					i++;
				}
			}
			LOGGER.info("---- " + jobName + " completing at " + Calendar.getInstance().getTime());
		} catch (InterruptedException e) {
			LOGGER.info("- isInterrupted at --- i:: " + i);
			return;
		}
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		LOGGER.info("--INTERUPTING-- ");
		isInterrupted = true;

	}
}
