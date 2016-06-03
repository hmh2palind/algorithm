package com.xxx.yyy.algorithm.inout;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadandWriteExcelFile {
	 
	public static void main(String[] args) throws Throwable {
		List<String> rows = new ArrayList<String>();
		rows.add("Hello # World");
		String delimeter = " # ";
		String fileName = "sxssf.xlsx";
		writeExcelFile(rows, delimeter, fileName, "providers");
		
		rows.add("How # are # you");
		writeExcelFile(rows, delimeter, fileName, "contents");
        
	}
	
	/**
	 * For writing/reading Excel files we will use the library Apache POI. We
	 * can read/write both xls and xlsx formats
	 * 
	 */
	public static void writeExcelFile(List<String> rows, String delimeter, String fileName, String sheetName) throws Exception {
		if (rows == null) {
			return;
		}
		
		// Using XSSF for xlsx format, for xls use HSSF
//		Workbook workbook = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
		
		SXSSFWorkbook workbook = null;
		File file = new File(fileName);

		if (file.exists()) {
			try {
				workbook = (SXSSFWorkbook) WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
		} else {
			workbook = new SXSSFWorkbook();
		}
		Sheet sheet = workbook.createSheet(sheetName);
		    
		for (int rowNum = 0; rowNum < rows.size(); rowNum++) {
			Row row = sheet.createRow(rowNum);
			
			String[] strings = rows.get(rowNum).split(delimeter);
			for (int col = 0; col < strings.length; col++) {
 				Cell cell = row.createCell(col);
				cell.setCellValue(strings[col]);
			}
		}

		FileOutputStream out = new FileOutputStream(fileName);
		workbook.write(out);
		out.close();
		
		// dispose of temporary files backing this workbook on disk
//		workbook.dispose();
		workbook.close();
	}
}
