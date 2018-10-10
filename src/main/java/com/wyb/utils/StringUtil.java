package com.wyb.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static final String BLANK = "";
	public static final String NULL = "null";
	

    /**
     * 去除所有的换行、制表符
     */
    public static String replaceNewline(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 对数值型字符串进行四舍五入
	 * @param s
	 * @return
	 */
	public static String fomatFloat(String s, int scael){
		BigDecimal decimal = new BigDecimal(s);
		BigDecimal a = decimal.setScale(scael, RoundingMode.HALF_EVEN);
		return String.valueOf(a);
	}


    public static String getRandomStr(int length){
        String str="abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sf=new StringBuffer();
        for(int i=0;i<length;i++)
        {
            int number=random.nextInt(62);//0~61
            sf.append(str.charAt(number));
        }
        return sf.toString();
    }
    

    /**
     * 将数据库的名称：MY_NAME转换成java对应的model的名称：myName
     */
    public static String convertDbColumnToJava(String str){
        StringBuilder result = new StringBuilder();
        char[] arr = str.toCharArray();
        boolean preCharIsLine = false;
        for(char ch : arr){
            if(ch == '_'){
                preCharIsLine = true;
                continue;
            }
            if(preCharIsLine){//下划线的后一个字符转换为大写
                if(ch >= 'a' && ch <= 'z'){
                    ch -= ch;
                }
            }else if(ch >= 'A' && ch <= 'Z'){//其余情况大写都要转小写
                ch += 32;
            }
            preCharIsLine = false;
            result.append(ch);
        }
        return result.toString();
    }
	/**
	 * 通过SecureRandom获取随机生成数
	 * 使用更安全的随机数生成器，如java.security.SecureRandom 类。
	 * @param num 随机数
	 * @return
	 */
	public static int getSecureRandomNumber(int num){
		SecureRandom number = null;
		try {
			number = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("通过SecureRandom获取随机生成数失败！"+e);
			return 0;
		}
		return number.nextInt(num);
	}
	public static void main(String[] args){
	}
}
