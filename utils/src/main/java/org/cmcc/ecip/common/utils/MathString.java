package org.cmcc.ecip.common.utils;

import java.math.BigInteger;

/**
 * 任意进制数值之间的转换
 * 
 * @author guiyn
 *
 */
public class MathString {

	public static BigInteger toDecimalBy36(String input) {
		return toDecimal(input, 36);
	}

	// 任意进制转换为10进制
	public static BigInteger toDecimal(String input, int base) {
		BigInteger bigtemp = BigInteger.ZERO , temp = BigInteger.ONE;
		int len = input.length();
		for (int i = len - 1; i >= 0; i--) {
			if (i != len - 1) {
				temp = temp.multiply(BigInteger.valueOf(base));
			}
			int num = changeDec(input.charAt(i));
			bigtemp = bigtemp.add(temp.multiply(BigInteger.valueOf(num)));
		}
		return bigtemp;
	}

	// 十进制转换为任意进制
	public static String toAnyConversion(BigInteger Bigtemp, BigInteger base) {
		String ans = "";
		while (Bigtemp.compareTo(BigInteger.ZERO) !=0) {
			System.out.println(""+Bigtemp);
			BigInteger temp = Bigtemp.mod(base);
			Bigtemp = Bigtemp.divide(base);
			char ch = changToNum(temp);
			ans = ch + ans;
		}
		return ans;
	}

	public static String toConversion36(BigInteger bigtemp) {
		return toAnyConversion(bigtemp, BigInteger.valueOf(36));
	}

	// 十进制转换中把字符转换为数
	public static int changeDec(char ch) {
		int num = 0;
		if (ch >= 'A' && ch <= 'Z') {
			num = ch - 'A' + 10;
		} else if (ch >= 'a' && ch <= 'z') {
			num = ch - 'a' + 36;
		} else {
			num = ch - '0';
		}
		return num;
	}

	// 数字转换为字符
	static char changToNum(BigInteger temp) {
		int n = temp.intValue();

		if (n >= 10 && n <= 35) {
			return (char) (n - 10 + 'A');
		} else if (n >= 36 && n <= 61) {
			return (char) (n - 36 + 'a');
		} else {
			return (char) (n + '0');
		}
	}

	public static void main(String[] args) {
System.out.println(Long.toBinaryString(90));
		char a=36;
		for(int i=0;i<100;i++)
		{
			System.out.println(i+"   "+(char)i);
		}
		
//		
//		long a=Long.MAX_VALUE;
//		   for(int i = 63; i >=0; i--) {
//			            if( ( (1L << i) & 1 ) != 0 ){
//			                System.out.print("1");
//			              } else {
//			                 System.out.print("0");
//			            }
//			          System.out.println();
//			        }

		
//		BigInteger bi = toDecimal("1", 36);
//		long i=1231231231;
//		
//		System.out.println(Long.toString(30, 36) );
//		System.out.println(Long.MAX_VALUE);
//		System.out.println(bi.toString());
//		System.out.println(Long.valueOf(bi.toString()));
//		System.out.println(bi.compareTo(BigInteger.ZERO) );
//		System.out.println( toAnyConversion(bi, BigInteger.valueOf(36)));
		
//		for (int i = 0; i < 100; i++) {
//			String ss = toAnyConversion(BigInteger.valueOf(i), BigInteger.valueOf(38));
//			System.out.println(ss);
//		}

	}
}
