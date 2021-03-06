package org.quickMap.Utils;

/*
 * @(#) EncrypAES.java
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/***
 * des加密/解密工具
 */
public class EncryptDesUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private SecretKeyFactory keyFactory;
    // 生成加密密钥
    private DESKeySpec keySpec;

    private SecretKey secretKey;

    private Cipher cipher;

    public EncryptDesUtil(String key) {
        try {
            //KeySpec组成加密密钥的密钥内容的（透明）规范
            keySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
            keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            secretKey = keyFactory.generateSecret(keySpec);
            cipher = Cipher.getInstance("DES");
        }catch (Exception e){
            logger.error("初始化加密工具异常",e);
            throw new IllegalArgumentException("加密异常",e);
        }
    }

    /**
     * 字符串加密
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String encrypt(String str) {

        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] src = str.getBytes(StandardCharsets.UTF_8);
            // 加密，结果保存进cipherByte
            byte[] encryByte = cipher.doFinal(src);
            String encryStr = parseByte2HexStr(encryByte);
            return encryStr;
        } catch (Exception e) {
            logger.error("加密异常",e);
            throw new IllegalArgumentException("加密异常");
        }
    }

    /**
     * 字符串解密
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String decrypt(String str) {
        try {
            // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
            byte[] encryByte = parseHexStr2Byte(str);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(encryByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("解密异常",e);
            throw new IllegalArgumentException("解密异常");
        }
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}