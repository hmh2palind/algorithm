package com.xxx.yyy.algorithm.scheduling;

import java.util.Scanner;

import com.xxx.yyy.algorithm.fundamentals.Queue;
import com.xxx.yyy.algorithm.inout.In;

import static com.xxx.yyy.algorithm.scheduling.SchedulingContants.DIR;
import static com.xxx.yyy.algorithm.scheduling.SchedulingContants.PROCESS_FILE_NAME;

/**
 * Round robin algorithm<br/>
 * <li>Each process is provided a fix time to execute called quantum.</li><br/>
 * <li>Once a process is executed for give time period. Process is preempted and
 * other process executed for given time period.</li><br/>
 * <li>Context switching is used to save states of preempted processes.</li>
 * </br>
 * 
 * @author HungHM5
 * 
 */
public class RRScheduling {
	private Queue<Process> readyList;
	private Scanner sc = new Scanner(System.in);
	private int[] bur, rem, wai, ta;
	private int size, q /* quantum time */, b = 0, t = 0, flag = 0;

	public RRScheduling() {
		readyList = new Queue<Process>();
	}

	public RRScheduling(int size) {
		this.size = size;
		bur = new int[size]; 	// burst time
		wai = new int[size]; 	// waiting time
		ta = new int[size]; 	// turn around
		rem = new int[size]; 	//
	}

	/**
	 * Enters burst time of process
	 */
	void get() {
		for (int i = 0; i < size; i++) {
			System.out.print("Enter burst time of P" + (i + 1) + ":");
			bur[i] = rem[i] = sc.nextInt();
		}
		System.out.print("Enter quantum time:");
		q = sc.nextInt();
	}

	/**
	 * Round robin algorithm
	 */
	void roundRobin() {
		do {
			flag = 0;
			for (int i = 0; i < size; i++) {// Iterator each process
				if (rem[i] >= q) {
					System.out.print("P" + (i + 1) + "\t");
					for (int j = 0; j < size; j++) {
						if (j == i)
							rem[i] = rem[i] - q;
						else if (rem[j] > 0)
							wai[j] += q;
					}
				} else if (rem[i] > 0) {
					System.out.print("P" + (i + 1) + "\t");
					for (int j = 0; j < size; j++) {
						if (j == i)
							rem[i] = 0;
						else if (rem[j] > 0)
							wai[j] += rem[i];
					}
				}
			}
			for (int i = 0; i < size; i++)
				if (rem[i] > 0)
					flag = 1;
		} while (flag == 1);

		for (int i = 0; i < size; i++)
			ta[i] = wai[i] + bur[i];
	}

	/**
	 * Displays result
	 */
	void display() {
		System.out.println("\nProcess\tBurst\tWaiting\tTurnaround");
		for (int i = 0; i < size; i++) {
			System.out.printf("\nP%d\t%d\t%d\t%d", (i + 1), bur[i], wai[i],
					ta[i]);
			// System.out.println("P" + (i + 1) + "\t" + bur[i] + "\t" + wai[i]
			// + "\t" + ta[i]);
			b += wai[i];
			t += ta[i];
		}
		System.out.println("Average waiting time:" + (b / size));
		System.out.println("Average Turnaround time:" + (t / size));
	}

	/**
	 * Test
	 */
	public static void main(String arg[]) {

		Scanner s = new Scanner(System.in);
		System.out.print("Enter the no of process:");
		int n = s.nextInt();
		RRScheduling obj = new RRScheduling(n);

		// Reading information process
		obj.setReadyList(readProcess(new In(DIR + PROCESS_FILE_NAME)));

		obj.get();
		obj.roundRobin();
		obj.display();
	}

	/**
	 * Read all process form input
	 * 
	 */
	public static Queue<Process> readProcess(In in) {
		if (in == null)
			throw new NullPointerException();

		Queue<Process> processQueue = new Queue<Process>();
		if (in.hasNextLine()) {// Read first line
			in.readLine();
		} else {
			return processQueue;
		}

		Process process;
		while (in.hasNextLine()) {
			process = new Process(in.readString(), in.readInt(), in.readInt());
			processQueue.enqueue(process);
		}

		return processQueue;
	}

	/**
	 * Sets ready list
	 */
	public void setReadyList(Queue<Process> readyList) {
		this.readyList = readyList;
	}

	/**
	 * Adds process into the ready list
	 * 
	 * @param p
	 *            The process
	 */
	public void addProcess(Process p) {
		readyList.enqueue(p);
	}

	/**
	 * Gets process from the ready list
	 * 
	 * @return process
	 */
	public Process getProcess() {
		return readyList.dequeue();
	}
}
