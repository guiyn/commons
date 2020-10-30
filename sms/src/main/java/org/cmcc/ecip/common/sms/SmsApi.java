package org.cmcc.ecip.common.sms;

import java.net.URL;

/**
 * 短信告警 API
 * 
 * @author guiyn
 *
 */
public class SmsApi {

	public static String SMS_ACTION_DEFAULT = "00";

	private static String _url;
	private static String _pass;
	private static String _tager;
	private static String _action;

	public static void buildService(String url, String pass, String tager, String action) {
		_url = url;
		_pass = pass;
		_tager = tager;
		_action = action;

	}

	/**
	 * 
	 * @param smsBodyXML
	 * @param url
	 * @param pass
	 * @param tager
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public static String sendSms(String smsBodyXML, String url, String pass, String tager, String action)
			throws Exception {
		buildService(url, pass, tager, action);
		ISmsWebServiceService service = new ISmsWebServiceService(new URL(url));
		ISmsWebService clientService = service.getISmsWebServicePort();
		String md5_info = SlpMD5Util.generateCheckCode(pass + action + smsBodyXML + tager);
		return clientService.smsService(tager, action, smsBodyXML, md5_info);
	}

	public static String sendSms(String smsBodyXML) throws Exception {
		ISmsWebServiceService service = new ISmsWebServiceService(new URL(_url));
		ISmsWebService clientService = service.getISmsWebServicePort();
		String md5_info = SlpMD5Util.generateCheckCode(_pass + _action + smsBodyXML + _tager);
		return clientService.smsService(_tager, _action, smsBodyXML, md5_info);
	}

	public static String buildSmsMessage(String mobiles, String message) throws Exception {

		String sendxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<SmsServiceReq>" + "<SmsList>" + "<Mobile>"
				+ mobiles + "</Mobile>" + "<Contents>" + "<![CDATA[" + message + "]]>" + "</Contents>" + "</SmsList>"
				+ "</SmsServiceReq>";
		return sendxml;
	}

	public static String buildSmsMessageAndSend(String mobiles, String message) throws Exception {

		String sendxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<SmsServiceReq>" + "<SmsList>" + "<Mobile>"
				+ mobiles + "</Mobile>" + "<Contents>" + "<![CDATA[" + message + "]]>" + "</Contents>" + "</SmsList>"
				+ "</SmsServiceReq>";
		return sendSms(sendxml);
	}

}
