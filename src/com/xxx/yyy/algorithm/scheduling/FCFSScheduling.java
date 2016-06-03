package com.xxx.yyy.algorithm.scheduling;

import java.util.ArrayList;
import java.util.List;

public class FCFSScheduling {
	private List<Process> readyList;
	
	public FCFSScheduling() {
		readyList = new ArrayList<Process>();
	}
	public static void main(String[] args) {
		FCFSScheduling fcfsSchedule = new FCFSScheduling();
		
		new Thread(ManageCPU.getInstance()).start();
		long currentTime = System.currentTimeMillis();
		fcfsSchedule.addProcess(new Process("P0", 0, 5, currentTime));
		fcfsSchedule.addProcess(new Process("P1", 1, 3, currentTime));
		fcfsSchedule.addProcess(new Process("P2", 2, 8, currentTime));
		fcfsSchedule.addProcess(new Process("P3", 3, 6, currentTime));
		for (int i = 0; i < fcfsSchedule.getAllProcess().size(); i++) {
			new Thread(fcfsSchedule.getAllProcess().get(i)).start();
		}
		
		
//		fcfsSchedule.printAllProcess();
//		fcfsSchedule.printAllWaitTime();
//		System.out.printf("Average Wait time: %.2f", fcfsSchedule.getAverageWaitTime());
//		fcfsSchedule.printTimeLine();
	}
	
	public List<Process> getAllProcess() {
		return readyList;
	}
	/**
	 * Adds a process into ready list
	 * 
	 * @param p The process
	 */
	public void addProcess(Process p) {
		int totalServiceTime = 0;
		for (Process pro : readyList) {
			totalServiceTime += pro.getExecuteTime();
		}
		
		p.setServiceTime(totalServiceTime);
		readyList.add(p);
	}


	/**
	 * Returns wait time of a process
	 * 
	 * @param name The name of process
	 * @return If exists process then return its wait time, other
	 * return -1; 
	 */
	public int getWaitTime(String name) {
		if (readyList == null || readyList.isEmpty()) {
			return -1;
		}
		
		for (Process p : readyList) {
			if (name.equals(p.getProcessName())) {
				return p.getWaitTime();
			}
		}
		return -1;
	}
	
	/**
	 * Prints wait time of each process
	 */
	public void printAllWaitTime() {
		System.out.printf("----------------------\n");
		System.out.printf("| Process | Wait Time |\n");
		if (readyList != null) {
			for (Process pro : readyList) {
				System.out.printf("|%8s |%10d |\n", pro.getProcessName(), pro.getWaitTime());
			}
		}
		System.out.printf("----------------------\n");
	}
	
	/*
	 * Returns total execute time of all process
	 */
	private int getTotalExecuteTime() {
		int total = 0;
		if (readyList != null) {
			for (Process pro : readyList) {
				total += pro.getExecuteTime();			}
		}
		
		return total;
	}
	/**
	 * Returns average wait time of all process
	 */
	public float getAverageWaitTime() {
		if (readyList == null || readyList.isEmpty()) {
			return 0;
		}
		
		int totalTime = 0;
		for (Process pro : readyList) {
			totalTime += pro.getWaitTime();
		}
		return (float)totalTime/readyList.size();
	}
	
	/**
	 * Prints time line of process
	 */
	public void printTimeLine() {
		System.out.printf("\n--------------------------------------------------------------------------------------");
		System.out.printf("\nProcess\\Time|");	
		int totalExecuteTime = getTotalExecuteTime();
		for (int i = 0; i <= totalExecuteTime; i+=2) {
			System.out.printf("%6d",i);
		}
		
		for (Process p : readyList) {
			System.out.printf("\n     %-7s|     ", p.getProcessName());
			
			// Arrival
			for(int i = 0; i < p.getArrivalTime(); i++) {
				System.out.printf("%3s", "");
			}
			
			// Wait
			for(int i = 0; i < p.getWaitTime(); i++) {
				System.out.printf("...");
			}
			
			// Execute
			for(int i = 0; i < p.getExecuteTime(); i++) {
				System.out.printf("___");
			}
		}
		System.out.printf("\n--------------------------------------------------------------------------------------");
	}
		
	/**
	 * Prints information of each process
	 */
	public void printAllProcess() {
		System.out.printf("------------------------------------------------------\n");
		System.out.printf("| Process | Arrival Time | Execute Time | ServiceTime |\n");
		if (readyList != null) {
			for (Process pro : readyList) {
				pro.print();
			}
		}
		System.out.printf("------------------------------------------------------\n");
	}
}
