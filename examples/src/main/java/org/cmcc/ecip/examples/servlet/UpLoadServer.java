package org.cmcc.ecip.examples.servlet;

import java.io.IOException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = "/upload")
public class UpLoadServer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MultipartResolver resolver = new CommonsMultipartResolver(req.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(req);

		Map<String, String> header =	getHeaderMap(req);
		
		Enumeration<String> names = multipartRequest.getParameterNames();
		while (names.hasMoreElements()) {
			log.info("get Parameter  >>>>>>>>>>>>>>>>>  [" + names.nextElement() + "]");;
		}

		String attr = multipartRequest.getParameter("attr");
		log.info("Parameter  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + attr);

		MultipartFile file = multipartRequest.getFile("attr_file");
		byte[] attachfile = null;
		if (file != null) {
			attachfile = file.getBytes();
			String base64file = Base64.getEncoder().encodeToString(attachfile);
			log.info("attr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ");
			log.info(base64file);
			log.info("attr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ");
		}

	}
	
	
	HashMap<String, String> getHeaderMap(HttpServletRequest servletRequest) {
		HashMap<String, String> map = new HashMap<String, String>();
		Enumeration<String> ns = servletRequest.getHeaderNames();
		while (ns.hasMoreElements()) {
			String name = ns.nextElement();
			String value = servletRequest.getHeader(name);
			 
			map.put(name, value);
		}
		return map;
	}
}
