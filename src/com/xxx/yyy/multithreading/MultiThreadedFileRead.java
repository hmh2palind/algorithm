package com.xxx.yyy.multithreading;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MultiThreadedFileRead extends Thread
{
	private InputStream in;

	public MultiThreadedFileRead(String fname) throws Exception {
		in = new FileInputStream(fname);
		this.start();
	}

	public void run() {
		int i = 0;
		while (i != -1) {
			try {
				i = in.read();
				System.out.print((char) i);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		try {
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
    public static void main(String arguments[]) throws Exception
    {
		int n = 2;
		System.out.print("Enter the number of files : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			n = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println(e);
		}

		MultiThreadedFileRead fr[] = new MultiThreadedFileRead[n];
		long tim = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			fr[i] = new MultiThreadedFileRead(arguments[i]);
		}
		
		// Join thread
		for (int i = 0; i < n; i++) {
			try {
				fr[i].join();// Waits for thread to die
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
        System.out.println("Time Required : "+(System.currentTimeMillis()-tim)+" miliseconds.");
    }
}


