package com.yc.web.core;

public class Cookie {
	private String name;
	private String value;
	private long maxAge;
	
	public Cookie(String name, String value, long maxAge) {
		super();
		this.name = name;
		this.value = value;
		this.maxAge = maxAge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(long maxAge) {
		this.maxAge = maxAge;
	}
	
	
}
