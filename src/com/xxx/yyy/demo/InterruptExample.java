package com.xxx.yyy.demo;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// other imports excluded for brevity

public class InterruptExample {
	Boolean bb;

	public void run() throws Exception {
		final Logger log = LoggerFactory.getLogger(InterruptExample.class);
		log.info("------- Initializing ----------------------");

		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		log.info("------- Initialization Complete -----------");
		log.info("------- Scheduling Jobs -------------------");

		// get a "nice round" time a few seconds in the future...
		Date startTime = nextGivenSecondDate(null, 1);

		JobDetail job = newJob(MyJob.class)
				.withIdentity("myJob", "group1")
				.build();

		SimpleTrigger trigger = newTrigger()
				.withIdentity("trigger1", "group1")
				.startAt(startTime)
				.withSchedule(simpleSchedule())
				.build();

		sched.scheduleJob(job, trigger);

		// start up the scheduler (jobs do not start to fire until
		// the scheduler has been started)
		sched.start();
		log.info("Scheduler thread's name: " + Thread.currentThread().getName());
		log.info("------- Started Scheduler -----------------");

		try {
			// if you want to see the job to finish successfully, sleep for
			// about 40 seconds
			Thread.sleep(5 * 1000L);
			// tell the scheduler to interrupt our job
			sched.interrupt(job.getKey());
			Thread.sleep(3 * 1000L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
	}

	public static void main(String[] args) throws Exception {
		InterruptExample example = new InterruptExample();
		example.run();
	}
}