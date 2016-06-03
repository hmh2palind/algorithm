package com.xxx.yyy.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Date;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import statements excluded for brevity
public class MyJob implements InterruptableJob {
	private static Logger    LOG              = LoggerFactory.getLogger(MyJob.class);
	private volatile boolean isJobInterrupted = false;
	private JobKey jobKey = null;

	private volatile Thread thisThread;

	public MyJob() {
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		thisThread = Thread.currentThread();
		LOG.info("Thread name of the current job: " + thisThread.getName());

		jobKey = context.getJobDetail().getKey();
		LOG.info("Job " + jobKey + " executing at " + new Date());

		try {
			String fileUrl = "http://mirrors.viethosting.vn/apache/logging/log4j/1.2.17/log4j-1.2.17.tar.gz"; // 59
			String localFile = "log4j-1.2.17.tar.gz"; // MB
			download(fileUrl, localFile);
		} catch (ClosedByInterruptException e) {
			LOG.info("Caught ClosedByInterruptException... exiting job.");
		} catch (IOException e) {
			LOG.info("Caught IOException... exiting job.", e);
		} finally {
			if (isJobInterrupted) {
				LOG.info("Job " + jobKey + " did not complete");
			} else {
				LOG.info("Job " + jobKey + " completed at " + new Date());
			}
		}
	}

	// this method is called by the scheduler
	public void interrupt() throws UnableToInterruptJobException {
		LOG.info("Job " + jobKey + "  -- INTERRUPTING --");
		isJobInterrupted = true;
		if (thisThread != null) {
			// this call causes the ClosedByInterruptException to happen
			thisThread.interrupt();
		}
	}

	private void download(String address, String localFileName)
			throws ClosedByInterruptException, IOException {
		URL url = new URL(address);
		ReadableByteChannel src = Channels.newChannel(url.openStream());
		@SuppressWarnings("resource")
		WritableByteChannel dest = new FileOutputStream(new File(localFileName)).getChannel();
		try {
			System.out.println("Downloading " + address + " to " + new File(localFileName).getCanonicalPath());
			int size = fastChannelCopy(src, dest);
			System.out.println("Download completed! " + (size / 1024 / 1024) + " MB");
		} finally {
			src.close();
			dest.close();
		}
	}

	// Code copied from
	// http://thomaswabner.wordpress.com/2007/10/09/fast-stream-copy-using-javanio-channels/
	private static int fastChannelCopy(final ReadableByteChannel src,
			final WritableByteChannel dest) throws IOException {
		final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		int count = 0;
		int total = 0;
		while ((count = src.read(buffer)) != -1) {
			total += count;
			// prepare the buffer to be drained
			buffer.flip();
			// write to the channel, may block
			dest.write(buffer);
			// If partial transfer, shift remainder down
			// If buffer is empty, same as doing clear()
			buffer.compact();
		}
		// EOF will leave buffer in fill state
		buffer.flip();
		// make sure the buffer is fully drained.
		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
		return total;
	}
}