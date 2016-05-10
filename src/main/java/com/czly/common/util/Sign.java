package com.czly.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Sign {
	public static Map<String,String> signMap = new HashMap<String,String>();
	public static String TOKEN = "czly2016";
	public static String APPID = "wxd991b2c10890c424";
	public static String SECRET = "f459bbdd2139d29cedcbd83bd6ca4669";
    
    /**
     * 检查是否为合格的请求
     * @param signature
     * @param token
     * @param timestamp
     * @param nonce
     * @return true:合格<br/>
     *		false:不合格
     */
    public static boolean checkSignature(String signature, String token,String timestamp, String nonce) {
      if (signature == null || token == null || timestamp == null || nonce == null) {
        return false;
      }
      List<String> tmpArr = new ArrayList<String>();
      tmpArr.add(token);
      tmpArr.add(timestamp);
      tmpArr.add(nonce);
      Collections.sort(tmpArr);
      String tmpStr = tmpArr.get(0) + tmpArr.get(1) + tmpArr.get(2);
      try {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        md.update(tmpStr.getBytes());
        tmpStr = byteToHex(md.digest());
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      }
      if (tmpStr.equals(signature)) {
        return true;
      } else {
        return false;
      }
    }
	/**
      * 格式化
      * @param bytes
      * @return
      *//*
     public static String getFormattedText(byte[] bytes) {
         int len = bytes.length;
         StringBuilder buf = new StringBuilder(len * 2);
         for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }*/

    public static Map<String, String> sign(String apiticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();

        String[] paramArr = new String[] { "jsapi_ticket=" + apiticket,
                "timestamp=" + timestamp, "noncestr=" + nonce_str, "url=" + url };
        Arrays.sort(paramArr);
        // 将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2])
                .concat("&"+paramArr[3]);
        try {
          MessageDigest md;
          md = MessageDigest.getInstance("SHA-1");
          md.update(content.getBytes());
          content = byteToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", content);
        ret.put("appId", APPID);

        return ret;
    }
    
    public static String sign(String token,String timestamp,String nonce) {
        Map<String, String> ret = new HashMap<String, String>();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "token=" + token +
                  "&timestamp=" + timestamp +
                  "&nonce=" + nonce;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return signature;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
