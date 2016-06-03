package com.xxx.yyy.algorithm.scheduling;

import com.xxx.yyy.algorithm.fundamentals.Queue;

public class ManageCPU implements Runnable{
	private static ManageCPU instance;
	private Queue<Process> queue = new Queue<Process>();
	
	public synchronized static ManageCPU getInstance() {
		if (instance == null) {
			instance = new ManageCPU();
		}
		
		return instance;
	}
	
	/**
	 * Adds a process into the manage CPU
	 * @param process The process
	 */
	public void register(Process process) {
		queue.enqueue(process);
	}

	@Override
	public void run() {
		Process process;
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			
			if (!queue.isEmpty()) {
				process = queue.dequeue();
				CPU.getInstance().executeProcess(process);
			}
		}
	}
}
