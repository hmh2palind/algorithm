package com.xxx.yyy.algorithm.scheduling;

public class Process implements Runnable{
	private String processName;
	private int arrivalTime;
	private int executeTime;
	private int serviceTime;
	private long startTime;
	
	public Process(String processName, int arrivalTime, int executeTime) {
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.executeTime = executeTime;
	}
	
	public Process(String processName, int arrivalTime, int executeTime, long time) {
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.executeTime = executeTime;
		this.startTime = time;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(arrivalTime * 1000);
			System.out.printf("\n%s arrival time %d", processName, arrivalTime);
			
			// CPU execute process
			ManageCPU.getInstance().register(this);
//			CPU.getInstance().executeProcess(this);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public long getStartTime() {
		return startTime;
	}
	
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	/**
	 * Returns wait time of process
	 *
	 */
	public int getWaitTime() {
		return serviceTime - arrivalTime;
	}

	public String getProcessName() {
		return processName;
	}

	public int getExecuteTime() {
		return executeTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void print() {
		System.out.printf("|%8s |%13d |%13d |%12d |\n",processName, arrivalTime, executeTime, serviceTime);
	}

	
}
