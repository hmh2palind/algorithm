package com.xxx.yyy.multithreading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MultiThreadWriteFile {
	public static void main(String[] args) {
		File file=new File("D:"+File.separator+"ThreadDemo.txt");
		try {
			FileOutputStream out=new FileOutputStream(file, true);
			ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<String>();
			
			for (int i = 0; i < 10; i++) {
				// multi thread into the queue data
				new Thread(new MyThread(queue, "Thread " + i + " ")).start();
			}
			// The thread of monitoring, continuously from the queue read and
			// write data to a file
			new Thread(new DealFile(out, queue)).start();
			try {
				Thread.sleep(3000);
				if (!Thread.currentThread().isAlive()) {
					System.out.println("The thread has finished");
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}

/**
 * Will be written to the file data into the queue
 * 
 * @author Administrator
 * 
 */
class MyThread implements Runnable {
	private ConcurrentLinkedQueue<String> queue;
	private String contents;

	public MyThread() {
	}

	public MyThread(ConcurrentLinkedQueue<String> queue, String contents) {
		this.queue = queue;
		this.contents = contents;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		queue.add(contents);
	}
}

/**
 * Write data to a file in the queue
 * 
 * @author Administrator
 * 
 */
class DealFile implements Runnable {
	private FileOutputStream out;
	private ConcurrentLinkedQueue<String> queue;

	public DealFile() {
	}

	public DealFile(FileOutputStream out, ConcurrentLinkedQueue<String> queue) {
		this.out = out;
		this.queue = queue;
	}

	@Override
	public void run() {
		synchronized (queue) {
			while (true) {
				if (!queue.isEmpty()) {
					try {
						out.write(queue.poll().getBytes("UTF-8"));
					} catch (IOException e) {
						System.out.println(e);
					}
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}