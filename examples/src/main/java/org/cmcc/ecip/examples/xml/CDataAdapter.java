package org.cmcc.ecip.examples.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

  public class CDataAdapter extends XmlAdapter<String, String> {

	@Override
	public String unmarshal(String v) throws Exception {
		return v;
	}

	@Override
	public String marshal(String v) throws Exception {
		return new StringBuilder("<![CDATA[").append(v).append("]]>").toString();
	}
}