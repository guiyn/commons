package org.cmcc.ecip.examples.logs;

import ch.qos.logback.classic.pattern.NamedConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogClassOfCallerConverter  extends NamedConverter {

	
	  protected String getFullyQualifiedName(ILoggingEvent event) {
		  
	        StackTraceElement[] cda = event.getCallerData();
	        if (cda != null && cda.length > 1) {
	            return cda[1].getClassName()+"|"+cda[1].getMethodName()+"|"+Integer.toString(cda[1].getLineNumber());
	        } 
	        else if (cda != null && cda.length == 1)
	        {
	        	return cda[0].getClassName()+"|"+cda[0].getMethodName()+"|"+Integer.toString(cda[0].getLineNumber());
	        }
	        else {
	            return CallerData.NA;
	        }
	    }
}
