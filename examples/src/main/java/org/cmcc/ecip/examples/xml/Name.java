package org.cmcc.ecip.examples.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Name {
	String id;
	String value;

	@XmlAttribute(name = "id")
	public void setId(String id) {
		this.id = id;
	}

	@XmlValue
	public void setValue(String value) {
		this.value = value;
	}

}
