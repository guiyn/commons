package org.cmcc.ecip.examples.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
@ServletComponentScan
@SpringBootApplication
@Controller
@Slf4j
public class StartServlet {

	public static void main(String[] args) {
		new SpringApplication(StartServlet.class).run(args);
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	String isRunning() {
		log.info("......" + System.currentTimeMillis());
		return "is running " + System.currentTimeMillis();
	}
}
