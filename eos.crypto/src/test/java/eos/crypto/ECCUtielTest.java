package eos.crypto;




import java.security.Security;
import java.util.Base64;

import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.cmcc.ecip.common.eos.crypto.ec.EosPrivateKey;
import org.cmcc.ecip.common.eos.crypto.ec.EosPublicKey;
import org.cmcc.ecip.common.eos.crypto.ec.SecpEnum;
import org.cmcc.ecip.common.eos.crypto.util.ECCUtil;
import org.cmcc.ecip.common.eos.crypto.util.HexUtils;

public class ECCUtielTest {

	static {
		try {
			Security.insertProviderAt(new BouncyCastleProvider(), 1);
		} catch (Exception e) {

			System.exit(-1);
		}
	}
	static String base64data="BM2HohhcDhC0J+577bSk2kVNX80fw+fBB4dqTn3ddKejYFKhI8nJQhpf/gol1a47N585iF5MN98S1SGCAKmmt+MzuTaz3xKhWtiIgU1h4tA0GMhumDHgFB07O9ssktY95LXL+Bot240O8YLKB69hLMrinp9jUWLyEptjEmO1+EcWpkBO7bsRNEWQF6QvND2UAWG98vdnllSCN44xLyR7eWCNg7hGMHe/M3J3azSO7DF8OHZttha7fyR++lHIMWrBaCd6f2lfQykqxdLJzv4BtupyMwW4x0rMmPLPJ4XrD6TcV8zXqLxQOdYingHRVAUDm56OOvbSQRh3KR0uwPEi2Ybz2zNMe0uUPFg3O98CzNzmq1hsr9TIDy6+gFUZJAaLUabrfv0THONjxY2JxWWx+rwiUurdPs8TD9waJt2mzodYIs85dGVWsDoBI++FjHSVbHtAZVI=";
	static String receiverPrivateKey = "5KMg5NqSM6timpCMVXX2x55FW6UjAtKGZn2rwmwj6gxZWAUkXdZ";
	public static void main(String a[]) throws Exception{
		SecpEnum type = SecpEnum.SECP256K1;
		
//	

        Security.addProvider(new BouncyCastleProvider());
        EosPublicKey eOCPublicKey = new EosPublicKey("EOS8RHgQmgXcfpnWKfCBuJswq8njBSaPb7EeMXGwXieJBjYaddkPa",type);
        byte[] params = "sssss".toString().getBytes("utf8");
        ECPublicKey ecPublicKey = eOCPublicKey.getECPublicKey(type);        
        byte[] encryptdata = ECCUtil.publicEncrypt(params,ecPublicKey); 
        System.out.println("========encryptStr without Base64：" + new String(encryptdata));    
         String encryptStr =  Base64.getEncoder().encodeToString(encryptdata);      
         System.out.println("========encryptStr:" + encryptStr);     
         
		
         
 		EosPrivateKey receiverECPrivateKey = new EosPrivateKey(type, receiverPrivateKey);
 		ECPrivateKey pkey = receiverECPrivateKey.getECPrivateKey(type);
 		byte[]  data= Base64.getDecoder().decode(encryptStr); 		
 		byte[] rdatab = ECCUtil.privateDecrypt(data, pkey);
 		System.out.println(new String(rdatab));
 		
         
	}
	
	

	
}
