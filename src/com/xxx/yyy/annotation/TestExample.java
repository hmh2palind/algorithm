package com.xxx.yyy.annotation;
import com.xxx.yyy.annotation.TesterInfo.Priority;

@TesterInfo(
		priority = Priority.HIGH, 
		createdBy = "mkyong.com", 
		tags = {"sales", "test" }
)
public class TestExample {

	@Test
	void testA() {
		if (true) 
			throw new RuntimeException("This test always failed");
	}

	@Test(enabled = false)
	void testB() {
		if (false) {
			throw new RuntimeException("This test always passed");
		}
	}

	@Test(enabled = true)
	void testC() {
		if (10 > 1) {
			// do nothing, this test always passed.
		}
	}

}