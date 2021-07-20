package org.cmcc.ecip.examples.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@XmlRootElement(name = "User")
public class User {
	String id;
	Name n;
	String cdata;

	@XmlElement(name = "name")
	public void setN(Name n) {
		this.n = n;
	}

	@XmlAttribute(name = "id")
	public void  settId(String id) {
		this.id = id;
	}

	
	@XmlElement(name = "cdata")
	@XmlJavaTypeAdapter(CDataAdapter.class)
	public void setCdata(String cdata) {
		this.cdata = cdata;
	}
}
