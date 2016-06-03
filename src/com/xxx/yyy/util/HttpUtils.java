package com.xxx.yyy.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.xxx.yyy.test.automation.InfomationServer;
import com.xxx.yyy.test.automation.TestCaseManager;

public final class HttpUtils {
	private static final String CHARSET = "UTF-8";
	private HttpUtils () {
		
	}
	
	/**
	 * Get HTTP response from httpConnection
	 * @param httpUrl
	 * @return
	 * @throws IOException
	 */
	public static String getHttpResponse(HttpURLConnection httpUrl) throws IOException {
        InputStream is = null;
        if (httpUrl.getResponseCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
            is = httpUrl.getErrorStream();
        } else {
            is = httpUrl.getInputStream();
        }

        InputStreamReader isr = new InputStreamReader(is, CHARSET);
        char[] buf = new char[10000];
        StringBuilder sb = new StringBuilder();
        while (true) {
            int len = isr.read(buf);
            if (len < 0) {
                break;
            }
            sb.append(buf, 0, len);
        }
        isr.close();
        return sb.toString();
	}
	
	public static void main(String[] args) {
		// TODO Read configure server
		InfomationServer infoServer = new InfomationServer();
		
		// TODO Read TestCases
		
		// TODO Verify TestCases
		String req = "/SmartSearchWS/rest/search15?searchText=sport&output=xml";
		String xpath = "//contents";
		String requests[] = new String[]{req};
		String xpaths[] = new String[]{xpath};
		int count_request = requests.length;
		boolean result[] = new boolean[count_request];
		String response;
		for (int i = 0; i < count_request; i++) { 
			response = callWebPage(infoServer, requests[i]);
			System.out.println(response);
			//TODO process response with XPATH or JPATH
			result[i] = verifyRespone(response, xpaths[i]);
		}
		
		List<com.xxx.yyy.test.automation.TestCase> testCases = TestCaseManager.getInstance().getTestCases();
		for (com.xxx.yyy.test.automation.TestCase cases : testCases) {
			response = callWebPage(infoServer, cases.getReqest());
			System.out.println(response);
			//TODO process response with XPATH or JPATH
			verifyRespone(response, cases);
		}
		
		// TODO Report Test Result
		System.out.println(result);
		
	}
	
	private static void verifyRespone(String xml, com.xxx.yyy.test.automation.TestCase cases) {
		Document xmlDocument = null;
		String string = null;
		Node node = null;
		NodeList nodeList = null;
		
		String expression = cases.getXpath();
		try {
			// Create XML document
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		
			// Create xPath object
			XPath xPath =  XPathFactory.newInstance().newXPath();
			//read a string value
			string = xPath.compile(expression).evaluate(xmlDocument);
			//read an XML node using xPath
			node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
			//read a nodeList using xPath
			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//TODO Verify expected
		if (string.isEmpty() || node == null || nodeList.getLength() == 0) {
			cases.setStatus(false);
		}
		
		cases.setStatus(true);
	}

	/**
	 * XPath validation XML
	 * 
	 * @param xml
	 * @exception expression
	 *                xPath expression
	 */
	private static boolean verifyRespone(String xml, String expression) {
		Document xmlDocument = null;
		String string = null;
		Node node = null;
		NodeList nodeList = null;
		try {
			// Create XML document
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		
			// Create xPath object
			XPath xPath =  XPathFactory.newInstance().newXPath();
			//read a string value
			string = xPath.compile(expression).evaluate(xmlDocument);
			//read an XML node using xPath
			node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
			//read a nodeList using xPath
			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (string.isEmpty() || node == null || nodeList.getLength() == 0) {
			return false;
		}
		
		return true;
	}

	/**
	 * The method executes a request then returns response 
	 */
	public static String callWebPage(InfomationServer infoServer, String request) {
		String response = "";
        String url = infoServer.getURL() + request;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(infoServer.getTimeOut());
            connection.setReadTimeout(infoServer.getTimeOut());
            connection.setRequestMethod(infoServer.getDefaultRequestMethod());
            response = getHttpResponse(connection);
            int status = connection.getResponseCode();

            if (status != 200) {
                response = "";
            }
        } catch (IOException ex) {
        	System.out.println(ex);
        } finally {
            connection.disconnect();
        }
        return response;
    }
}
