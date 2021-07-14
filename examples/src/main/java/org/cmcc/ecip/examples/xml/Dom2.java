package org.cmcc.ecip.examples.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Text;

public class Dom2 {
	public static void main(String a[]) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		// 去掉头部声明 standalone="no"
		document.setXmlStandalone(true);
		Element rootNode = document.createElement("root");
		Element aa1 = document.createElement("aa1");
		
		Attr a3 = document.createAttribute("a3");
		a3.setNodeValue("a31");
		a3.setTextContent("a32");
		a3.setValue("a33");
		Attr a4 = document.createAttributeNS("http://namespce.test2","cc:a4");
		a4.setNodeValue("a41");
		a4.setTextContent("a42");
		a4.setValue("a43");
		aa1.setAttributeNode(a3);
		aa1.setAttributeNode(a4);
		Element a2 = document.createElementNS("http://namespce.test1", "dd:a2");
		Text a2_v=document.createTextNode("a2 value");
		a2.appendChild(a2_v);
		aa1.appendChild(a2);
		rootNode.appendChild(aa1);
		CDATASection a5 =document.createCDATASection("<s>sss</s>");
		EntityReference a6=	document.createEntityReference("sdddd");
		Text a7=document.createTextNode("test hello..");
		Element aa2 = document.createElement("aa2");
		aa2.appendChild(a5);
		aa2.appendChild(a6);
		aa2.appendChild(a7);
		rootNode.appendChild(aa2);
		document.appendChild(rootNode);
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		tf.transform(new DOMSource(document), new StreamResult(System.out));

	}
}
