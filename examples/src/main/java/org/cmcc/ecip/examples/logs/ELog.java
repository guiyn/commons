package org.cmcc.ecip.examples.logs;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

public class ELog {

	public static void main(String a[]) {
		//加入自己的converter
		PatternLayout.defaultConverterMap.put("CML", LogClassOfCallerConverter.class.getName());
		//获取context 
		LoggerContext logf = (LoggerContext) LoggerFactory.getILoggerFactory();
		// 设置样式
		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setPattern("%date{dd-MM-yy HH:mm:ss.SSS}|%level|%thread|%logger|%CML|%msg%n");
		encoder.setContext(logf);
		encoder.start();
		// 再关联
		ConsoleAppender<ILoggingEvent> ca = new ConsoleAppender<>();
		ca.setEncoder(encoder);
		ca.setContext(logf);
		ca.start();
		// 使用
		Logger log = logf.getLogger("TEST");
		log.setAdditive(false);
		log.addAppender(ca);
		log.info("test ..");	
	}
}
