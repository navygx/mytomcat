package com.yc.web.core;

public interface ServletRequest {
	public void parse();
	
	public String getParameter(String key);
	
	public String getUrl();
	
	public String getMethod();
	
	public HttpSession getSession();
	
	public Cookie[] getCookie();
	
	public boolean checkJSessionId();
	
	public String getJSessionId();
}
