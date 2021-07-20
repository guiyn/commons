package org.cmcc.ecip.examples.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.xml.internal.bind.v2.runtime.MarshallerImpl;
import com.sun.xml.internal.bind.v2.runtime.XMLSerializer;

import lombok.extern.slf4j.Slf4j;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

@Slf4j
public class JAXB2 {

	static String xml = "<User><name>sss</name></User>";

	@SuppressWarnings("restriction")
	public static void main(String a[]) throws Exception {

		// java 对象 转 xml
		// 创建一个数据对象
		User u = new User();
		u.settId("uid");
		u.setCdata("data value");
		Name n = new Name();
		n.setId("nid");
		n.setValue("张三");
		u.setN(n);

		write2Str(u);
	}

	public static void showxml(User u) throws Exception {
		// 根据数据对象构造一个jaxb对象
		JAXBContext context = JAXBContext.newInstance(User.class);
		// 创建输出流
		ByteArrayOutputStream op = new ByteArrayOutputStream();
		// xml的输出工厂
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		// 创建xml的输出流
//		com.sun.xml.internal.stream.writers.XMLStreamWriterImpl
		
		XMLStreamWriter streamWriter = xof.createXMLStreamWriter(op);
		// 创建一个转换器
		Marshaller mar = context.createMarshaller();
		// 设置一下
		
		
		

 
		mar.marshal(u, streamWriter);
		// 输出
		streamWriter.flush();
		// 关闭
		streamWriter.close();
		// 打印

		// marshal using the Apache XMLSerializer

		System.out.println(op.toString("UTF-8"));
	}

	public static void write2Str(User u) throws Exception {

		JAXBContext context = JAXBContext.newInstance(User.class);
		Marshaller marshaller = context.createMarshaller();

		// xml格式
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// 不进行转义字符的处理
		marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
			public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer) throws IOException {
				writer.write(ch, start, length);
			}
		});
		StringWriter sw = new StringWriter();
		marshaller.marshal(u, sw);
		sw.flush();
		sw.close();
		System.out.println(sw.toString());

	}
}
