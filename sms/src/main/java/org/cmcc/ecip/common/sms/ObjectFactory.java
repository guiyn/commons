
package org.cmcc.ecip.common.sms;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.test.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SmsServiceResponse_QNAME = new QName("http://www.sms.upbms.hp.com", "smsServiceResponse");
    private final static QName _SmsService_QNAME = new QName("http://www.sms.upbms.hp.com", "smsService");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.test.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SmsServiceResponse }
     * 
     */
    public SmsServiceResponse createSmsServiceResponse() {
        return new SmsServiceResponse();
    }

    /**
     * Create an instance of {@link SmsService }
     * 
     */
    public SmsService createSmsService() {
        return new SmsService();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmsServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sms.upbms.hp.com", name = "smsServiceResponse")
    public JAXBElement<SmsServiceResponse> createSmsServiceResponse(SmsServiceResponse value) {
        return new JAXBElement<SmsServiceResponse>(_SmsServiceResponse_QNAME, SmsServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmsService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sms.upbms.hp.com", name = "smsService")
    public JAXBElement<SmsService> createSmsService(SmsService value) {
        return new JAXBElement<SmsService>(_SmsService_QNAME, SmsService.class, null, value);
    }

}
