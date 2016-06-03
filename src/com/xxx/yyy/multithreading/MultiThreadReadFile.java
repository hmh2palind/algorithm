package com.xxx.yyy.multithreading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MultiThreadReadFile {

	/**Java multithreaded reading large files
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1=new Thread(new MultiThread(),"A");
		t1.setDaemon(true);
		Thread t2=new Thread(new MultiThread(),"B");
		t1.start();
		t2.start();
	}

}


 class MultiThread implements Runnable{	
	 Locale locale;
	private static BufferedReader br = null;
	private List<String> list;
	
	static{
		try {
			br = new BufferedReader(new FileReader("D://ThreadDemo.txt"),10);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public void run() {
		String line = null;
		int count = 0;
		while(true) {
			this.list = new ArrayList<String>();
			synchronized(br) {
				try {
					while((line = br.readLine()) != null) {
						if(count<15) {
							list.add(line);
							count++;
						}else {
							list.add(line);
							count = 0;
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1);
				display(this.list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(line == null)
				break;
		}
		
		
	}
	
	public void display(List<String> list) {
		for(String str:list) {
			System.out.println(str);
		}
		System.out.println(list.size());
	}
	
}