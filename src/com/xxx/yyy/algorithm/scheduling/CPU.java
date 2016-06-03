package com.xxx.yyy.algorithm.scheduling;

public class CPU {
	private static CPU instance;
	
	private CPU() {}
	
	public synchronized static CPU getInstance() {
		if (instance == null) {
			instance = new CPU();
		}
		
		return instance;
	}

	/**
	 * CPU execute process
	 */
	public void executeProcess(Process process) {
		try {
			System.out.printf("\n%s execute time %d", process.getProcessName(), process.getExecuteTime());
			Thread.sleep(process.getExecuteTime() * 1000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
	}
}
