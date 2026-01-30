package org.come.tool;

import org.come.server.GameServer;
import java.util.Iterator;
import redis.clients.jedis.Jedis;
import java.util.Map;
import org.come.redis.RedisParameterUtil;
import org.come.redis.RedisPoolUntil;
import org.come.until.GsonUtil;
import org.come.action.monitor.TBean;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;

public class WriteOut
{
    public static String PATH;
    public static String PATHTwo;
    public static String PATHThere;
    static int a;
    static int b;
    static int c;
    public static StringBuffer buffer;
    static StringBuffer buffer2;
    
    public static synchronized void addtxt(String newStr, long time) {
        if (time < 10L) {
            return;
        }
        if (WriteOut.buffer == null) {
            WriteOut.buffer = new StringBuffer();
        }
        ++WriteOut.a;
        WriteOut.buffer.append("\r\n");
        WriteOut.buffer.append(newStr);
        WriteOut.buffer.append(":");
        WriteOut.buffer.append(time);
        if (WriteOut.a >= 100) {
            writeTxtFile(WriteOut.buffer.toString());
            WriteOut.buffer = null;
            WriteOut.a = 0;
        }
    }
    
    public static synchronized void addtxt() {
        if (WriteOut.buffer != null) {
            writeTxtFile(WriteOut.buffer.toString());
            WriteOut.buffer = null;
            WriteOut.a = 0;
        }
    }
    
    public static void writeTxtFile(String txt) {
        try {
            try {
                ++WriteOut.b;
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(new File(WriteOut.PATH + WriteOut.b + ".txt")), "UTF-8");
                BufferedWriter writer = new BufferedWriter(write);
                writer.write(txt);
                writer.close();
                write.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void writeTxtFile(String txt, String path) {
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(new File(WriteOut.PATH + path + ".txt")), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(txt);
            writer.close();
            write.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static <T> void writeTxtTBean(TBean<T> t) {
        try {
            try {
                ++WriteOut.c;
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(new File(WriteOut.PATHThere + WriteOut.c + ".txt")), "UTF-8");
                BufferedWriter writer = new BufferedWriter(write);
                writer.write(GsonUtil.getGsonUtil().getgson().toJson(t));
                writer.close();
                write.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void TB() {
        Jedis jedis = RedisPoolUntil.getJedis();
        Map<String, String> redisChangeMap = jedis.hgetAll(RedisParameterUtil.ROLE_CONTROL);
        RedisPoolUntil.returnResource(jedis);
        StringBuffer buffer = new StringBuffer();
        int size = 0;
        int a = 0;
        for (Map.Entry<String, String> entry : redisChangeMap.entrySet()) {
            String v = (String)entry.getKey() + ":" + (String)entry.getValue();
            buffer.append(v);
            buffer.append("  ");
            if (size % 10 == 9) {
                buffer.append("\r\n");
            }
            if (++size >= 1000) {
                size = 0;
                ++a;
                TBFile(buffer.toString(), a);
                buffer = new StringBuffer();
            }
        }
        if (size != 0) {
            ++a;
            TBFile(buffer.toString(), a);
        }
    }
    
    public static void TBFile(String txt, int a) {
        try {
            try {
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(new File(WriteOut.PATH + "TB" + a + ".txt")), "UTF-8");
                BufferedWriter writer = new BufferedWriter(write);
                writer.write(txt);
                writer.close();
                write.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        WriteOut.PATH = GameServer.getTXTPATH() + "/log";
        WriteOut.PATHTwo = GameServer.getTXTPATH() + "/RZ";
        WriteOut.PATHThere = GameServer.getTXTPATH() + "/Task";
    }
}
