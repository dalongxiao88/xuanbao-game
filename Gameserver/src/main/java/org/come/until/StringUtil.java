package org.come.until;

import java.util.UUID;
import org.come.server.GameServer;
import java.security.MessageDigest;
import java.math.BigDecimal;

public class StringUtil
{
    public static String str;
    public static final String EMPTY_STRING = "";
    public static String[] nameCondition;
    private static final String[] hexDigits;
    private static final BigDecimal b1;
    private static final BigDecimal b2;
    private static final BigDecimal b3;
    private static final BigDecimal b4;
    private static final BigDecimal b5;
    private static final BigDecimal b6;
    
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return StringUtil.hexDigits[d1] + StringUtil.hexDigits[d2];
    }
    
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex) {}
        return resultString;
    }
    
    public static String toMoneyString(BigDecimal money) {
        StringBuffer buffer = new StringBuffer(money.longValue() + "");
        for (int index = buffer.length() - 3; index > 0; index -= 3) {
            buffer.insert(index, ',');
        }
        if (money.compareTo(StringUtil.b1) < 0) {
            buffer.insert(0, "#W");
        }
        else if (money.compareTo(StringUtil.b2) < 0) {
            buffer.insert(0, "#c24DB76");
        }
        else if (money.compareTo(StringUtil.b3) < 0) {
            buffer.insert(0, "#cFD44DD");
        }
        else if (money.compareTo(StringUtil.b4) < 0) {
            buffer.insert(0, "#cFBD932");
        }
        else if (money.compareTo(StringUtil.b5) < 0) {
            buffer.insert(0, "#c00EFEF");
        }
        else if (money.compareTo(StringUtil.b6) < 0) {
            buffer.insert(0, "#G");
        }
        else {
            buffer.insert(0, "#R");
        }
        return buffer.toString();
    }
    
    public static String generateUniqueString(int length, int numberSize, int index) {
        length -= numberSize;
        int beginIndex = GameServer.random.nextInt(32 - length);
        int endIndex = beginIndex + length;
        String uuid = intToString(index, numberSize) + UUID.randomUUID().toString().replaceAll("-", "").substring(beginIndex, endIndex);
        return uuid;
    }
    
    private static String intToString(int i, int length) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(i);
        length -= buffer.length();
        for (int j = 0; j < length; ++j) {
            buffer.insert(0, "0");
        }
        return buffer.toString();
    }
    
    static {
        StringUtil.nameCondition = new String[] { "管理", "GM", "gm", "Gm", "gM", "卖", "物集", "菜", "新开", "群", "号", "币", "艹", "贱", "系统", "当前", "传音", "世界", "帮派", "队伍" };
        hexDigits = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        b1 = new BigDecimal(10000);
        b2 = new BigDecimal(100000);
        b3 = new BigDecimal(1000000);
        b4 = new BigDecimal(10000000);
        b5 = new BigDecimal(100000000);
        b6 = new BigDecimal(1000000000);
    }
}
