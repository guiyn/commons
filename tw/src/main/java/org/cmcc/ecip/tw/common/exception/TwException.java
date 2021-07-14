package org.cmcc.ecip.tw.common.exception;

/**
 * @ProjectName R-twcmcc
 * @PackageName com.cmcc.boss.security
 * @ClassName TwException
 * @Description
 * @author guiyn
 * @date 2018年12月10日 下午3:30:24
 * @version 2018年12月10日 下午3:30:24
 * 
 */
public class TwException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public final static String error_node_id_null="01";
	
	public final static String error_key_null="02";
	public final static String error_key_length_not32="03";
	public final static String error_pin_null="04";
	public final static String error_pin_length_less48="05";
	public final static String error_pin_decrypt_result_null="06";
	public final static String error_connection_server="07";
	public final static String error_validate_digest="08";
	public final static String error_cipher_execute="09";
	public final static String error_not_fand_by_nodeid="10";
	public final static String error_cert_null="11";
	public final static String error_private_key ="12";
	public final static String error_unknow="99";
	
	public final static String START_ERROR_CODE="00020";
	public final static String REPORT_ERROR_CODE="00021";
	public final static String LOAD_ERROR_CODE="00022";
	
	private String code;

	public TwException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public TwException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}

