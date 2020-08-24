package com.yc.web.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.tomcat.core.TomcatConstans;

public class HttpServletRequest implements ServletRequest{
	private String method;
	private Map<String,String> parameter=new HashMap<String,String>();
	private String url;
	private String protocalVersion;
	private InputStream is;
	private BufferedReader read;
	
	private volatile HttpSession session=new HttpSession();
	private boolean checkJSessionId=false;
	private String jsessionid;
	private Cookie[] cookies;
	
	public HttpServletRequest(InputStream is) {
		// TODO Auto-generated constructor stub
		this.is=is;
	}
	
	@Override
	public void parse() {
		// TODO Auto-generated method stub
		try {
			read=new BufferedReader(new InputStreamReader(is));
			String line=null;
			List<String> headStrs=new ArrayList<String>();
			while((line=read.readLine())!=null && !"".equals(line)) {
				headStrs.add(line);
			}
			parseFirstLine(headStrs.get(0));
			parseParameter(headStrs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fdfd");
		
	}

	private void parseParameter(List<String> headStrs) {
		// TODO Auto-generated method stub
		String str=headStrs.get(0).split(" ")[1];
		if(str.contains("?")) {
			str=str.substring(str.indexOf("?")+1);
			String[] params=str.split("&");
			String[] temp;
			for(String param : params) {
				temp=param.split("=");
				this.parameter.put(temp[0], temp[1]);
			}
		}
		
		if(TomcatConstans.REQUEST_METHOD_POST.equals(this.method)) {
			int len=0;
			for(String head : headStrs) {
				if(head.contains("Content-Length:")) {
					len=Integer.parseInt(head.substring(head.indexOf(":")+1).trim());
					break;
				}
			}
			
			if(len<=0) {
				return;
			}
			
			try {
				char[] ch=new char[1024*10];
				int count=0,total=0;
				StringBuffer sbf=new StringBuffer(1024*10);
				while((count=read.read(ch))>0) {
					sbf.append(ch,0,count);
					total+=count;
					if(total>=len) {
						break;
					}
				}
				str=URLDecoder.decode(read.readLine(),"utf-8");
				str=str.substring(str.indexOf("?")+1);
				String[] params=str.split("&");
				String[] temp;
				for(String param : params) {
					temp=param.split("=");
					this.parameter.put(temp[0], temp[1]);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private void parseFirstLine(String str) {
		// TODO Auto-generated method stub
		String[] strs=str.split(" ");
		this.method=strs[0];
		if(strs[1].contains("?")) {
			this.url=strs[1].substring(0,strs[1].indexOf("?"));
		}else {
			this.url=strs[1];
		}
		this.protocalVersion=strs[2];
	}

	@Override
	public String getParameter(String key) {
		// TODO Auto-generated method stub
		return this.parameter.getOrDefault(key, null);
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie[] getCookie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkJSessionId() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getJSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

}
