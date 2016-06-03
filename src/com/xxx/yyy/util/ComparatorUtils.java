package com.xxx.yyy.util;

import java.util.Comparator;

public final class ComparatorUtils {
	protected static String ruleFullFileNamePrefix = "full_file";

	private ComparatorUtils() {
		
	}
	
    public static final Comparator<String> fileNameComparator = new Comparator<String>() {
        public int compare(String fn1, String fn2) {
            // Full files should appear first, and be in descending order (latest full file becomes the first one).
            // Delta files should follow full files, and appear in ascending order (from older to newer).
            if (fn1.startsWith(ruleFullFileNamePrefix ) && !fn2.startsWith(ruleFullFileNamePrefix)) {
                return -1;
            }
            if (!fn1.startsWith(ruleFullFileNamePrefix) && fn2.startsWith(ruleFullFileNamePrefix)) {
                return 1;
            }
            if (fn1.startsWith(ruleFullFileNamePrefix) && fn2.startsWith(ruleFullFileNamePrefix)) {
                return fn2.compareTo(fn1); // sort full files in descending order
            }
            
            return fn1.compareTo(fn2); // sort delta files in ascending order
        }
    };
}
