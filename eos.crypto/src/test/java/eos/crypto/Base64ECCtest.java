package eos.crypto;

import java.security.Security;
import java.util.Base64;

import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.cmcc.ecip.common.eos.crypto.ec.EosPrivateKey;
import org.cmcc.ecip.common.eos.crypto.ec.EosPublicKey;
import org.cmcc.ecip.common.eos.crypto.ec.SecpEnum;
import org.cmcc.ecip.common.eos.crypto.util.ECCUtil;
import org.cmcc.ecip.common.eos.crypto.util.HexUtils;

public class Base64ECCtest {
	static {
		try {
			Security.insertProviderAt(new BouncyCastleProvider(), 1);
		} catch (Exception e) {

			System.exit(-1);
		}
	}
//	static String base64Data = "BIJO5gZfiJQBp+ohsXY9/O2RgaETXvF1/KElTKkHsHmnKfqzEOly5zPAr/YWP4cLxi3Cs5FFBdvaNT9Twb0jLMJyoo+pUtPej5yGQsc3EfN7IUnoXJ5a+f5eqyEsYh+iVafXw3ime1ELaM94psmVZkinRd2yj/DJtOURPQTfB/CLkag1syIBkqKX6NNpfoNc7DeQ7Eerbk6+xmGQoiUxpYQZDDUavlyouDjkbIRSmSD/dPLGYdXCOtbIXAY3eEtcSSWh3O8H4iEkeu4+dOb7Y2q4l6BzNZrL523HHZuX2+2ddD4PBK9vSC+rx16DcXoofXiLm0YqKia0TY0Q0mv9Hzkfv7Q7BffnfvGjg53iTqHRJoTbXY0FSF/wm0IYgszZ4O2xz+GdUtBF1wIx9vBSEqQW6i126z7bVtZRLtZJcfRk86PU0iN7h6vMbDxYUJ07lBavVp0gxAvh3GKr4nBSS3r5NQ8W8VL0al5XQ+1hyL5hcAq0i2LfxmhVYxU7UXIzfrOFO95TpUEc09XALMfs6MYxM/r3ARwg9j0SN2nMS/bi/O2kY0k372/gKkUXzFELvSQezjQFEVP5ZTl9SiOD0kUi";
	static String receiverPrivateKey = "5KMg5NqSM6timpCMVXX2x55FW6UjAtKGZn2rwmwj6gxZWAUkXdZ";

	public static void main(String[] args) throws Exception {
		SecpEnum type = SecpEnum.SECP256K1;
//		String receiverPrivateKey = "";
		EosPrivateKey receiverECPrivateKey = new EosPrivateKey(type, receiverPrivateKey);
		ECPrivateKey pkey = receiverECPrivateKey.getECPrivateKey(type);

//EOS8g1u3ktAGHs4QsVp9aeaWNebFLtprQHwpaSjegx6iEuoTNhjXU
		EosPublicKey eospubkey = receiverECPrivateKey.getPublicKey();
		System.out.println(eospubkey.toString());
		org.bouncycastle.jce.interfaces.ECPublicKey oubk = eospubkey.getECPublicKey(type);
////
		String data = "{\"CrtT\":\"20201110112854\",\"PkgSeq\":\"351SubOrd012701\",\"UDSum\":1,\"UD\":[{\"OprNumb\":\"351migu202011101128544056\",\"IDVA\":\"13453408101\",\"Opr\":\"02\",\"BizType\":\"81\",\"BillFlg\":\"3\",\"SPID\":\"698040\",\"SPBizCode\":\"69804040011001\",\"Source\":\"11\",\"EfftT\":\"20201110112854\",\"Channel\":null,\"ChannelCode\":\"BOSS\",\"OprTime\":\"20200820120848\",\"UD1\":{\"actionReasonID\":\"1\",\"OprSource\":\"BOSS\"}}]}";
		byte[] datab = ECCUtil.publicEncrypt(data.getBytes(), oubk);
////
		System.out.println("加密后数据： " + (data = Base64.getEncoder().encodeToString(datab)));
		byte[] ss = Base64.getDecoder().decode(data.getBytes("utf-8"));

		byte[] rdatab = ECCUtil.privateDecrypt(Base64.getDecoder().decode(data.getBytes("utf-8")), pkey);

		System.out.println(new String(rdatab));

	}

}
