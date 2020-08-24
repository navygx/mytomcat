package com.yc.web.core;

import java.io.IOException;
import java.io.PrintWriter;

public interface ServletResponse {
	public PrintWriter getWriter();
	
	public void sendRedirect(String url);
}
