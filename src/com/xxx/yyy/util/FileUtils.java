package com.xxx.yyy.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public final class FileUtils {
	private final static Logger logger = LoggerFactory
			.getLogger(FileUtils.class);

	private static String FILE_TIMESTAMP_FORMAT = "-yyyyMMdd_HHmmss_SSS";
	private static String FILENAME_EXT = ".xml";
	private static String primaryNamePrefix = "prefix";
	private static String deltaFileNamePrefix = "prefix";

	public static Comparator<File> fileNameComparator = new Comparator<File>() {
		public int compare(File file1, File file2) {
			String fn1 = file1.getName().replaceAll("\\D", "");
			if (fn1.contains("restart"))
				fn1 = fn1.replace("restart", "");

			String fn2 = file2.getName().replaceAll("\\D", "");
			if (fn2.contains("restart"))
				fn2 = fn2.replaceAll("restart", "");

			return fn2.compareTo(fn1);
		}
	}; // fileNameComparator

	public static long getRuleFileTimestamp(String fileName) {
		long timestamp = -1;
		if (fileName == null) {
			return timestamp;
		}
		if (!fileName.toLowerCase().endsWith(FILENAME_EXT.toLowerCase())) {
			return timestamp;
		}
		if (!fileName.startsWith(primaryNamePrefix)
				&& !fileName.startsWith(deltaFileNamePrefix)) {
			return timestamp;
		}
		int ind = fileName.length() - FILENAME_EXT.length()
				- FILE_TIMESTAMP_FORMAT.length();
		if (ind < 0) {
			return timestamp;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(FILE_TIMESTAMP_FORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			sdf.setLenient(false);

			timestamp = sdf.parse(fileName.substring(ind)).getTime();
		} catch (ParseException e) {

		}

		return timestamp;
	}

	/**
	 * The method will append a object to file. Using BufferWriter
	 * 
	 * @param number
	 * @param file
	 * @return The method returns true if append success else false
	 * @throws IOException
	 */
	public static boolean appendToFile(Number number, File file)
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		writer.append(String.valueOf(number));
		writer.newLine();
		writer.close();

		return true;
	}

	/**
	 * This method read the no empty line before the end of file. Using
	 * bufferdReader
	 * 
	 * @return
	 */
	public static String getLineBeforeEndOfFile(File file) throws IOException {
		String lastLine = null, currentLine = null;

		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));

		while ((currentLine = buffReader.readLine()) != null) {
			if (StringUtils.isNotBlank(currentLine)) {
				lastLine = currentLine;
			}
		}
		buffReader.close();

		return lastLine;
	}

	/**
	 * This method write a string to file
	 * 
	 * @param destinationFileName
	 * @param str
	 * @throws IOException
	 */
	public static void writeToFile(String destinationFileName, String str)
			throws IOException {
		File destinationFile = new File(destinationFileName);

		if (!destinationFile.exists()) {
			destinationFile.createNewFile();
		} else {
			destinationFile.delete();
			destinationFile.createNewFile();
		}

		PrintWriter pw = new PrintWriter(new FileWriter(destinationFileName));
		pw.println(str);
		pw.close();
	}

	/**
	 * The getLineSet function read data from file into a Set
	 * 
	 * @param file
	 * @return The function returns {Set} if file is read, and {null} otherwise
	 */
	public static Set<String> getLineSet(File file)
			throws FileNotFoundException {
		Set<String> lineSet = new HashSet<String>();

		Scanner scanner = new Scanner(file);

		String lineText;
		while (scanner.hasNextLine()) {
			lineText = scanner.nextLine();
			if (lineText != null && !lineText.trim().isEmpty()) {
				lineSet.add(lineText.trim());
			}
		}
		scanner.close();

		return lineSet;
	}

	public static InputSource getInputsource(String dirFile) {
		InputSource is = null;
		try {
			InputStream inputStream = new FileInputStream(new File(dirFile));
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			is = new InputSource(reader);
			is.setEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.debug("Unsupported Encoding Exception {}", e);
		} catch (FileNotFoundException e) {
			logger.debug("File Not Found Exception {}", e);
		}
		return is;
	}

	public static InputSource getInputsource(String str, boolean bool) {
		Reader reader = new StringReader(str);
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");
		return is;
	}

	public static void main(String[] args) {
		String sFileName = "C:\\Users\\hunghm5\\Desktop\\dates.txt";
		generateCsvFile(sFileName, false);
	}
	/**
	 * CSV is stand for Comma-separated values, CSV is a delimited data format
	 * that has fields/columns separated by the comma character and records/rows
	 * separated by newlines Export data to CSV file
	 * 
	 * @param sFileName
	 */
	public static void generateCsvFile(String sFileName, boolean append) {
		try {
			FileWriter writer = new FileWriter(sFileName, append);

			writer.append("DisplayName");
			writer.append(',');
			writer.append("Age");
			writer.append('\n');

			writer.append("MKYONG");
			writer.append(',');
			writer.append("26");
			writer.append('\n');

			writer.append("YOUR NAME");
			writer.append(',');
			writer.append("29");
			writer.append('\n');

			// generate whatever data you want

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
