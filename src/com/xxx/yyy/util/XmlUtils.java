package com.xxx.yyy.util;

import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class XmlUtils {
	private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);
	
	public static SAXParser getSAXParser() {
		try {
			return SAXParserFactory.newInstance().newSAXParser();
		} catch (ParserConfigurationException e) {
			logger.debug("Parser Configuration Exception: {}", e);
		} catch (SAXException e) {
			logger.debug("SAX Exception: {}", e);
		}
		return null;
	}
	
	public static String getCollectionAtIndex(Set<String> set, int index) {
		if (CollectionUtils.isEmpty(set) || index >= set.size() || index < 0)
			return null;
		
		int i = 0;
		String tmsId = "";
		Iterator<String> it = set.iterator();
		boolean found = false;
		while (it.hasNext() && !found) {
			tmsId = it.next();
			if (i++ == index) {
				found = true;
			}
		}
		
		if (found)
			return tmsId;
		else
			return null;
	}
}
