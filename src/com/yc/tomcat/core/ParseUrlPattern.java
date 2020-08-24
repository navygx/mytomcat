package com.yc.tomcat.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseUrlPattern {
	private String basePath=TomcatConstans.BASE_PATH;
	private static Map<String,String> urlPattern=new HashMap<String,String>();
	
	public ParseUrlPattern() {
		parse();
	}

	private void parse() {
		// TODO Auto-generated method stub
		File[] files=new File(basePath).listFiles();
		if(files==null || files.length<=0) {
			return;
		}
		String projectName=null;
		File childFile=null;
		
		for(File fl : files) {
			projectName=fl.getName();
			
			if(!fl.isDirectory()) {
				continue;
			}
			
			childFile=new File(fl,"web.xml");
			if(!childFile.exists()) {
				continue;
			}
			parseXml(projectName,childFile);
		}
	}

	@SuppressWarnings("unchecked")
	private void parseXml(String projectName, File childFile) {
		// TODO Auto-generated method stub
		SAXReader read=new SAXReader();
		Document doc=null;
		
		try {
			doc=read.read(childFile);
			List<Element> mines=doc.selectNodes("//servlet");
			
			for(Element el : mines) {
				urlPattern.put("/"+projectName+el.selectSingleNode("url-pattern").getText().trim(),el.selectSingleNode("servlet-class").getText().trim());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String getClass(String url) {
		return urlPattern.getOrDefault(url, null);
	}
	
	public Map<String,String> getUrlPattern(){
		return urlPattern;
	}
	
}
