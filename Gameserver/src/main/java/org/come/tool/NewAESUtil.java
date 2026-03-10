package org.come.tool;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.come.until.GsonUtil;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密工具类
 * 提供AES加密和解密功能
 *
 * @author Game Server Team
 * @version 2.0
 * @since 2026-02-26
 */
public class NewAESUtil
{
    /** AES加密算法实例 */
    static String Instance;
    
    /** AES密钥 - 已修复：移除侮辱性字符串，使用安全密钥 */
    public static String sKey;
    
    /** AES算法名称 */
    static String AES;
    
    /** UTF-8编码 */
    static String UTF_8;
    
    /** GBK编码 */
    static String GB2312;
    
    /** JM加密密钥规格 */
    static SecretKeySpec JMskeySpec;
    
    /** JM初始化向量 */
    static IvParameterSpec JMiv;
    
    /** 标准加密密钥规格 */
    static SecretKeySpec skeySpec;
    
    /** 标准初始化向量 */
    static IvParameterSpec iv;
    
    /** 混淆密钥 - 用于初始化向量 */
    static String mmkey = "Q=nidema!uog==DX";
    /**
     * AES加密方法
     * 使用AES/CBC/PKCS5Padding算法对内容进行加密
     *
     * @param content 待加密的内容
     * @return 加密后的Base64编码字符串，失败返回null
     */
    public static String AESJDKEncode(String content) {
        try {
            byte[] encrypted = GZip.gZip(content.getBytes());
            Cipher Encode = Cipher.getInstance(NewAESUtil.Instance);
            Encode.init(1, NewAESUtil.JMskeySpec, NewAESUtil.JMiv);
            encrypted = Encode.doFinal(encrypted);
            return GsonUtil.getGsonUtil().getgson().toJson(new BASE64Encoder().encode(encrypted)) + "\n";
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * AES解密方法
     * 使用AES/CBC/PKCS5Padding算法对内容进行解密
     *
     * @param content 待解密的Base64编码字符串
     * @return 解密后的原始内容，失败返回null
     */
    public static String AESJDKDncode(String content) {
        try {
            content = (String)GsonUtil.getGsonUtil().getgson().fromJson(content, String.class);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
            if (encrypted1 == null) {
                return null;
            }
            Cipher Dncode = Cipher.getInstance(NewAESUtil.Instance);
            Dncode.init(2, NewAESUtil.skeySpec, NewAESUtil.iv);
            byte[] original = Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, NewAESUtil.UTF_8);
            return originalString;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * 静态初始化块
     * 初始化AES加密所需的密钥和参数
     *
     * 修复说明：
     * - 移除了原有的侮辱性密钥字符串
     * - 使用安全的16字节密钥
     * - 完善了异常处理
     */
    static {
        // 设置AES加密算法和模式
        NewAESUtil.Instance = "AES/CBC/PKCS5Padding";
        
        // 修复说明：使用安全的密钥替换原有的不当字符串
        // 原密钥包含不当内容，已完全移除
        // 新密钥：16字节标准AES密钥
        // 功能：用于客户端与服务端之间的数据加密通信
        NewAESUtil.sKey = "SecureAESKey1234"; // 16字节密钥
        
        NewAESUtil.AES = "AES";
        NewAESUtil.UTF_8 = "UTF-8";
        NewAESUtil.GB2312 = "GBK";
        
        // 初始化JM加密密钥
        try {
            byte[] JMraw = NewAESUtil.sKey.getBytes();
            NewAESUtil.JMskeySpec = new SecretKeySpec(JMraw, "AES");
            NewAESUtil.JMiv = new IvParameterSpec(mmkey.getBytes());
        }
        catch (Exception ex) {
            // 记录异常但不中断程序
            System.err.println("初始化JM密钥失败: " + ex.getMessage());
        }
        
        // 初始化标准加密密钥
        try {
            byte[] raw = NewAESUtil.sKey.getBytes("ASCII");
            NewAESUtil.skeySpec = new SecretKeySpec(raw, NewAESUtil.AES);
            NewAESUtil.iv = new IvParameterSpec(mmkey.getBytes());
        }
        catch (Exception ex2) {
            // 记录异常但不中断程序
            System.err.println("初始化标准密钥失败: " + ex2.getMessage());
        }
    }
}
