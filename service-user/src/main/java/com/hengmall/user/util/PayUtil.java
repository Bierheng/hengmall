package com.server.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.Element;

public class PayUtil {

	/**
	 * xml转map
	 */
	public static Map<String, String> xmlToMap(String strxml) throws Exception {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		Map<String, String> map = new HashMap<>();
		SAXReader reader = new SAXReader();

		InputStream ins = new ByteArrayInputStream(strxml.getBytes("UTF-8"));

		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		Map m = new HashMap();

		Document document = reader.read(ins);

		Element root = document.getRootElement();

		List<Element> list = root.elements();

		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}

		ins.close();

		return map;
	}

	/**
	 *
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 * @param outputStr
	 *            参数
	 * @return
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		// 创建SSLContext
		StringBuffer buffer = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			// 往服务器端写内容
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}
			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}