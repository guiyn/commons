package org.cmcc.ecip.examples.logs;

 

import ch.qos.logback.classic.PatternLayout;


public class LogLayout extends PatternLayout{

	
	public LogLayout() {
		super();
	    defaultConverterMap.put("CML", LogClassOfCallerConverter.class.getName());
	}
}
