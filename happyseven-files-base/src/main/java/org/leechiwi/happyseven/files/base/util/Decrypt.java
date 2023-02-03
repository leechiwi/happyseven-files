package org.leechiwi.happyseven.files.base.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.apache.commons.io.IOUtils;
import org.leechiwi.happyseven.files.base.ap.AbstractAspose;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
public class Decrypt {
    /**
     * 16字节
     */
    //private static final String ENCODE_KEY = "1234567812345678";
    //private static final String IV_KEY = "0000000000000000";

    public static void main(String[] args) {
        InputStream resourceStream = AbstractAspose.class.getResourceAsStream("/aspose/license2.txt");
        String text="";
        try {
            text = IOUtils.toString(resourceStream, "UTF-8").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encrypt = encrypt("sl@39y^*U!5g8Ebe", "0000000000000000", text);
        //String encryptData = encryptFromString("zdm321123.", Mode.CBC, Padding.NoPadding);
        System.out.println("加密：" + encrypt);
        String base64Key = "";
        try {
            base64Key=Base64.encodeBase64("sl@39y^*U!5g8Ebe".getBytes("utf-8"));
            System.out.println("base64 key："+base64Key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String decrypt = decrypt(base64Key, "0000000000000000", encrypt);
        //String decryptData = decryptFromString(encryptData, Mode.CBC, Padding.NoPadding);
        System.out.println("解密：" + decrypt);
    }
    public static String encrypt(String key,String ivKey,String data){
        return encryptFromString(key,ivKey,data, Mode.CBC,Padding.ZeroPadding);
    }
    public static String encryptFromString(String key,String ivKey,String data, Mode mode, Padding padding) {
        AES aes;
        SecretKeySpec aesSpec = new SecretKeySpec(key.getBytes(), "AES");
        if (Mode.CBC == mode) {
            aes = new AES(mode, padding, aesSpec, new IvParameterSpec(ivKey.getBytes()));
        } else {
            aes = new AES(mode, padding, aesSpec);
        }
        return aes.encryptBase64(data, StandardCharsets.UTF_8);
    }
    public static String decrypt(String key,String ivKey,String data){
        return decryptFromString(Base64.decodeBase64(key),ivKey,data, Mode.CBC,Padding.ZeroPadding);
    }
    public static String decryptFromString(byte[] key,String ivKey,String data, Mode mode, Padding padding) {
        AES aes;
        SecretKeySpec aesSpec= new SecretKeySpec(key, "AES");
        if (Mode.CBC == mode) {
            aes = new AES(mode, padding, aesSpec, new IvParameterSpec(ivKey.getBytes()));
        } else {
            aes = new AES(mode, padding, aesSpec);
        }
        byte[] decryptDataBase64 = aes.decrypt(data);
        return new String(decryptDataBase64, StandardCharsets.UTF_8);
    }
}
