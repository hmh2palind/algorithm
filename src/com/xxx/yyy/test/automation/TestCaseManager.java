package com.xxx.yyy.test.automation;

import java.util.List;

public class TestCaseManager {
	private List<TestCase> testCases;
	private static TestCaseManager manager = null;
	
	private TestCaseManager() {}
	
	public static synchronized TestCaseManager getInstance() {
		if (manager == null) {
			manager = new TestCaseManager();
		}
		
		return manager;
	}

	/**
	 * @return the testCases
	 */
	public List<TestCase> getTestCases() {
		return testCases;
	}

	/**
	 * @param testCases the testCases to set
	 */
	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}
}
