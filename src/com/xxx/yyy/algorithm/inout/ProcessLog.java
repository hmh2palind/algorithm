package com.xxx.yyy.algorithm.inout;

import static com.xxx.yyy.algorithm.inout.InOutConstant.CHANNEL;
import static com.xxx.yyy.algorithm.inout.InOutConstant.CONTENT;
import static com.xxx.yyy.algorithm.inout.InOutConstant.IN;
import static com.xxx.yyy.algorithm.inout.InOutConstant.MATERIAL;
import static com.xxx.yyy.algorithm.inout.InOutConstant.OUT;
import static com.xxx.yyy.algorithm.inout.InOutConstant.PROVIDER;
import static com.xxx.yyy.algorithm.inout.InOutConstant.SCHEDULE;

public class ProcessLog {
	private static final String FILE_NAME = "- FILE_NAME : ";
	private static final String ADDED = "- ADDED : ";
	public static final String DETERMINE= "#";
	private In in;
	private Out out;
	
	private Out channel = new Out(CHANNEL);
	private Out provider = new Out(PROVIDER);
	private Out content = new Out(CONTENT);
	private Out material = new Out(MATERIAL);
	private Out schedule = new Out(SCHEDULE);
	
	public ProcessLog(In in, Out out) {
		this.in = in;
		this.out = out;
	}
	
	public static void main(String[] args) {
		In in = new In(IN);
		Out out = new Out(OUT);
		
		ProcessLog processer = new ProcessLog(in, out);
		processer.process();
		in.close();
		out.close();
		
	}

	public void process() {
		String fileName = null;
		String context = null;
		
		int count = 0;
		while (in.hasNextLine()) {
			String line = in.readLine();
			if (line.startsWith(FILE_NAME)) {
				count = 0;
				fileName = line.substring(FILE_NAME.length());
			} else if (line.startsWith(ADDED)) {
				count ++;
				context = line.substring(ADDED.length()); 
			}
			
			if (line.startsWith("- Channel#")) {
				channel.println(line);
			} else if (line.startsWith("- Provider#")) {
				provider.println(line);
			} else if (line.startsWith("- ContentDetail#")) {
				content.println(line);
			} else if (line.startsWith("- Material#")) {
				material.println(line);
			} else if (line.startsWith("- Schedule#")) {
				schedule.println(line);
			}
			
			if (count == 1) {
				count = 0;
				out.print(context);
				out.print(DETERMINE);
				out.print(fileName);
				out.println();
				
				if (context.startsWith("Channel"))
					channel.println(context + DETERMINE + fileName);
				else if (context.startsWith("Provider")) {
					provider.println(context + DETERMINE + fileName);
				} else if (context.startsWith("ContentDetail")) {
					content.println(context + DETERMINE + fileName);
				} else if (context.startsWith("Material")) {
					material.println(context + DETERMINE + fileName);
				} else if (context.startsWith("Schedule")) {
					schedule.println(context + DETERMINE + fileName);
				}
			}
		}
	}
}
