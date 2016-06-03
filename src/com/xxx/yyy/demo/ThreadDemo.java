package com.xxx.yyy.demo;

/**
 * This example shows the usage of {java.lang.Thread.interrupted} method.
 * 
 * @author HungHM5
 */
public class ThreadDemo implements Runnable {
	Thread t;

	ThreadDemo() throws InterruptedException {
		t = new Thread(this);
		System.out.println("Executing " + t.getName());
		// this will call run() fucntion
		t.start();

		// interrupt the threads
		if (!Thread.interrupted()) {
			Thread.sleep(10000);
			t.interrupt();
		}
		// block until other threads finish
		try {
			t.join();
		} catch (InterruptedException e) {
			System.out.print(t.getName() + " interrupted exception:");
			System.out.println(e.toString());
		}
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
//				new ThreadDemo();
			}
		} catch (InterruptedException e) {
			System.out.print(t.getName() + " interrupted:");
			System.out.println(e.toString());
		}
	}

	public static void main(String args[]) throws InterruptedException {
		new ThreadDemo();
		new ThreadDemo();
		new ThreadDemo();
	}
}