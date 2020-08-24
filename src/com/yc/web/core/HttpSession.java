package com.yc.web.core;

import java.util.Hashtable;
import java.util.Map;

public class HttpSession {
	public Map<String,Object> session=new Hashtable<String, Object>();
	private String jsessionid=null;
	
	public void setAttribute(String key,Object value) {
		this.session.put(key, value);
	}
	
	public Object getAttribute(String key) {
		if(!session.containsKey(key)) {
			return null;
		}
		return session.get(key);
	}
	
	public void setJSessionId(String jsession) {
		this.jsessionid=jsession;
	}
}
