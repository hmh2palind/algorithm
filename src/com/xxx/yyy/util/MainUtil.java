package com.xxx.yyy.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class MainUtil {
	private static final String INPUT_DATA = "D:\\jboss-4.2.2.GA\\server\\default\\log\\edmcontentsearch.log";
	private static final String MATERIAL_OUTPUT = "D:\\jboss-4.2.2.GA\\server\\default\\log\\edmcontentsearch.txt";
	private static final String CONTENT_OUTPUT = "D:\\jboss-4.2.2.GA\\server\\default\\log\\edmcontentsearch_content.txt";
	private static final String SCHEDULE_OUTPUT = "D:\\jboss-4.2.2.GA\\server\\default\\log\\edmcontentsearch_schedule.txt";
	private static final String ADD_MATERIAL = "- ADDED : Material";
	private static final String ADD_CONTENT = "- ADDED: ContentDetailData";
	private static final String ADD_SCHEDULE = "- ADDED: Schedule";

	private static final String NEW = "C:\\Users\\hunghm5\\Desktop\\new.txt";
	private static final String OLD = "C:\\Users\\hunghm5\\Desktop\\20160126.txt";
	private static final String HUNGHM= "C:\\Users\\hunghm5\\Desktop\\myIds.txt";
	private static final String ANHLD = "C:\\Users\\hunghm5\\Desktop\\anhld4.txt";
	
	public static void main(String[] args) throws IOException {
		File newFile = new File(NEW);
		File oldFile = new File(OLD);
		File myFile = new File(HUNGHM);
		File anhld = new File(ANHLD);
		
		// Don't Fix
		Set<String> beginSet = FileUtils.getLineSet(newFile);
		// Fix
		Set<String> processSet = FileUtils.getLineSet(oldFile);
		// HungHM5
		Set<String> mySet = FileUtils.getLineSet(myFile);
		// ANHLD4
		Set<String> anhldSet = FileUtils.getLineSet(anhld);

		System.out.println("Don't Fix : " + beginSet.size());
		System.out.println(beginSet);
		System.out.println();
		System.out.println("Fixed: " + processSet.size());
		System.out.println(processSet);
		System.out.println();
		
		SetView<String> view = Sets.difference(beginSet, processSet);
		System.out.println("I Fixed : " + view.size());
		System.out.println(view);
		System.out.println();
		
		view = Sets.difference(processSet, beginSet);
		System.out.println("I Create Bugs : " + view.size());
		System.out.println(view);
		System.out.println();
			
		System.out.println("I Have : " + mySet.size());
		System.out.println(mySet);
		System.out.println();
		
		view = Sets.difference(mySet, processSet);
		System.out.println("I Fixed my bugs: " + view.size());
		System.out.println(view);
		
		view = Sets.intersection(mySet, processSet);
		System.out.println("After fix bugs, i have: " + view.size());
		System.out.println(view);
		
		System.out.println("AnhLD has: " + anhldSet.size());
		System.out.println(anhldSet);
		System.out.println();
		
		view = Sets.difference(anhldSet, processSet);
		System.out.println("AndLD fixed my bugs: " + view.size());
		System.out.println(view);
		
		view = Sets.intersection(anhldSet, processSet);
		System.out.println("After fix bugs, AnhLD has: " + view.size());
		System.out.println(view);
		
	}
	
	public static void _main(String[] args) throws IOException {
		// Read file
		File inputFile = new File(INPUT_DATA);
		File materialFile = new File(MATERIAL_OUTPUT);
		File contentFile = new File(CONTENT_OUTPUT);
		File scheduleFile  = new File(SCHEDULE_OUTPUT);
			
		Scanner scanner = new Scanner(inputFile);
		String lineText;
		while (scanner.hasNextLine())
		{
			lineText = scanner.nextLine();

			if (lineText.startsWith(ADD_MATERIAL))
				appendToFile(lineText, materialFile);
			if (lineText.startsWith(ADD_CONTENT))
				appendToFile(lineText, contentFile);
			if (lineText.startsWith(ADD_SCHEDULE))
				appendToFile(lineText, scheduleFile);
		}
		scanner.close();
		
		// Write to file
	}

	private static void appendToFile(String lineText, File outputFile) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
		writer.append(String.valueOf(lineText));
		writer.newLine();
		writer.close();
	}
}
