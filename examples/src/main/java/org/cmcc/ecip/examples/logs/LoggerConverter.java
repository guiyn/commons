package org.cmcc.ecip.examples.logs;

import ch.qos.logback.classic.pattern.NamedConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LoggerConverter extends NamedConverter {

    protected String getFullyQualifiedName(ILoggingEvent event) {
    	try {
    		Class.forName(event.getLoggerName());
    		return "CLASS";
    	}catch(Exception e)
    	{
    		return event.getLoggerName();
    	}
    }
}