package com.xxx.yyy.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;

public class CollectionUtils {
	
	public static Set<String> difference(Set<String>set1, Set<String>set2) {
        if (isEmpty(set1)) {
            return Sets.newHashSet();
        } 
        if (isEmpty(set2)) {
            return set1;
        }

        return Sets.difference(set1, set2);
    }

    public static Set<String> intersection(Set<String>set1, Set<String>set2) {
        if (isEmpty(set1) || isEmpty(set2)) {
            return Sets.newHashSet();
        }

        return Sets.intersection(set1, set2);
    }

    public static Set<String> union(Set<String>set1, Set<String>set2) {
        if (isEmpty(set1)) {
            return set2;
        }
        if (isEmpty(set2)) {
            return set1;
        }

        return Sets.union(set1, set2);
    }
    
	/**
	 * {@code col} is empty if it is {@code null} or {@code col.isEmpty}
	 * returns true. 
	 * 
	 * @param col
	 * @return
	 */
    public static boolean isEmpty(float[] col) {
		return col == null || col.length == 0;
	}
    
    public static boolean isEmpty(int[] col) {
		return col == null || col.length == 0;
	}
    
	public static boolean isEmpty(short[] col) {
		return col == null || col.length == 0;
	}
	
	public static <T> boolean isEmpty(T[] col) {
		return col == null || col.length == 0;
	}

	public static <T> boolean isEmpty(Collection<T> col) {
		return col == null || col.isEmpty();
	}

	public static <K, V> boolean isEmpty(Map<K, V> col) {
		return col == null || col.isEmpty();
	}
	
	public static boolean isEmpty(CharSequence s) {
		return s == null || s.length() == 0;
	}
}
