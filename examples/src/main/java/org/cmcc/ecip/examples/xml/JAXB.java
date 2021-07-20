package org.cmcc.ecip.examples.xml;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
 
 

public class JAXB {

	static String xml="<User id='root'><name id='1'>sss</name><cdata><![CDATA[<a>s</a>]]></cdata></User>";
	
	public static void main(String a[])throws Exception {
		
		//xml 转java 对象
		// 单独声明没负值是为了更好的说明
		Class<?> clazz; //关键的参数。就是你转换完成后的对象。
		String xmlStr; // 需要转换的xml
		// 实际的需要进行复制
		clazz=User.class; //当前需要转的的是user对象
		xmlStr=xml; // 需要转的xml内容为对应的userxml
		//jaxb 的声明 需要传入需要的class
		JAXBContext context = JAXBContext.newInstance(clazz);
		//就叫他转换器吧
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//转reader
		StringReader sr = new StringReader(xmlStr);
		//进行转换  
		Object xmlObject = unmarshaller.unmarshal(sr);
		// 这里若需要具体使用某类型需要进行强转一下
		User u=(User)xmlObject;
		// 打印输出
		System.out.println(u.toString());
	}
	
 
}
