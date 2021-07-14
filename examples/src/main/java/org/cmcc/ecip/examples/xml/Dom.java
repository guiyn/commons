package org.cmcc.ecip.examples.xml;

import org.apache.commons.io.output.DeferredFileOutputStream;
import org.cmcc.ecip.common.utils.JsonUtils;
import org.w3c.dom.*;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.Console;

import javax.xml.bind.annotation.W3CDomHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@Slf4j
public class Dom {
	// 用Element方式
	public static void element(NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);
			NodeList childNodes = element.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node node = childNodes.item(j);

				log.info(node.toString());

//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					// 获取节点
//					System.out.println("node name[" + node.getNodeName() + "] value ["
//							+ node.getFirstChild().getNodeValue() + "]");
//
//				}
			}
		}
	}

	public static void node(NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node _n = childNodes.item(j);
//				node(_n.getChildNodes());
//				log.info("{} {} {}",_n.getNodeType(),_n.getNodeName(),_n.getFirstChild().getNodeValue());
			}
		}
	}

	public static void main(String[] args) throws Exception{
		// 1.创建DocumentBuilderFactory对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2.创建DocumentBuilder对象
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document d = builder.parse(new ByteArrayInputStream(xml3.getBytes()));
			renshiNodeType(d.getChildNodes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
 
	}

	public static void renshiNodeType(NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
//			DeferredElementImpl
			Node _n = list.item(i);
			log.info("base uri "+_n.getBaseURI());
			log.info("local name "+_n.getLocalName());
			log.info("namespace uri "+_n.getNamespaceURI());
			log.info("prefix "+_n.getPrefix());
			
			log.info("node >> {} {} {} {}", _n.getNodeType(), _n.getNodeName(), _n.getNodeValue(),_n.getLocalName());
			NamedNodeMap map = _n.getAttributes();
			for (int j = 0; map != null && j < map.getLength(); j++) {
				Node an = map.item(j);
				log.info("attr >> {} {} {}", an.getNodeType(), an.getNodeName(), an.getNodeValue());
			}
			if (_n.getNodeType() == 1) {
				renshiNodeType(_n.getChildNodes());
			}
		}
	}

	

	static String xml = "<root><a id='1' cc='2' ><b>ss</b></a><c>s2</c><d><![CDATA[<e><f>f1</f></e>]]></d></root>";
	static String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!-- this is comment -->" 
			+ "<!DOCTYPE root[<!ELEMENT users (a+)>]><root><a>sss</a></root>";

	static String xml3 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + 
			"<root>" + 
			"<dd:a2 xmlns:dd=\"http://namespce.test1\">" + 
			"<a1 a3=\"a33\" xmlns:cc=\"http://namespce.test2\" cc:a4=\"a43\"/>" + 
			"</dd:a2>" + 
			"<a1><![CDATA[<s>sss</s>]]>ssss</a1>" + 
			"</root>" + 
			"";
}
