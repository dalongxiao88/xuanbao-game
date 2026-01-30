package com.tool.tcp;

import java.util.HashMap;
import java.lang.ref.SoftReference;
import java.util.Map;

public class TCPLoadThread extends Thread
{
    private static long colorID;
    private static Map<String, String> colorMap;
    public static String N;
    public static String N0;
    public static String N1;
    private static String HX;
    boolean is;
    private int size;
    
    public static synchronized String addMap(String value) {
        String color = (String)TCPLoadThread.colorMap.get(value);
        if (color != null) {
            return color;
        }
        color = TCPLoadThread.colorID++ + "";
        TCPLoadThread.colorMap.put(color, value);
        TCPLoadThread.colorMap.put(value, color);
        return color;
    }
    
    private static String getMap(String id) {
        return (String)TCPLoadThread.colorMap.get(id);
    }
    
    public TCPLoadThread() {
        this.setDaemon(this.is = true);
    }
    
    public void setLoadTcp() {
        synchronized (this) {
            if (this.is) {
                return;
            }
            this.is = true;
            this.notifyAll();
        }
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Object object = SpriteFactory.getLoad();
                if (object == null) {
                    synchronized(this) {
                     try {
                        this.is = false;
                        this.wait();
                        continue;
                    }
                    finally {}
                    }

                }
                else {
                    if (object instanceof Sprite) {
                        if (this.size++ % 4 == 0) {
                            Thread.sleep(1L);
                        }
                        SpriteFactory.VloadImg((Sprite)object);
                    }
                    else if (object instanceof String) {
                        String TCPPath = (String)object;
                        if (TCPPath.startsWith(TCPLoadThread.N)) {
                            long l = Long.parseLong(TCPPath.substring(2));
                            SoftReference<Sprite> s = (SoftReference<Sprite>)SpriteFactory.TcpTwoMap.get(Long.valueOf(l));
                            if (s == null || s.get() == null) {
                                long color = l >> 40;
                                Sprite sprite = SpriteFactory.VloadSprite(l & 0x7FFFFFFFFFL, TCPPath.startsWith(TCPLoadThread.N1), (color != 0L) ? getMap(color + "") : null);
                                if (sprite != null) {
                                    SpriteFactory.TcpTwoMap.put(Long.valueOf(l), new SoftReference(sprite));
                                    if (sprite.getAnimationCount() == 1) {
                                        sprite.setLoad(0);
                                        SpriteFactory.VloadImg(sprite);
                                    }
                                }
                            }
                        }
                        else {
                            SoftReference<Sprite> s2 = (SoftReference<Sprite>)SpriteFactory.TcpMap.get(TCPPath);
                            if (s2 == null || s2.get() == null) {
                                Sprite sprite2 = null;
                                int index = TCPPath.indexOf(TCPLoadThread.HX);
                                if (index == -1) {
                                    sprite2 = SpriteFactory.VloadSprite(TCPPath, null);
                                }
                                else {
                                    sprite2 = SpriteFactory.VloadSprite(TCPPath.substring(0, index), getMap(TCPPath.substring(index + 1)));
                                }
                                if (sprite2 != null) {
                                    SpriteFactory.TcpMap.put(TCPPath, new SoftReference(sprite2));
                                    if (sprite2.getAnimationCount() == 1) {
                                        sprite2.setLoad(0);
                                        SpriteFactory.VloadImg(sprite2);
                                    }
                                }
                            }
                        }
                    }
                    SpriteFactory.clearLoad(object);
                    continue;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                synchronized (this) {
                    try {
                        Thread.sleep(5000L);
                        this.is = true;
                        this.notifyAll();
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
    
    static {
        TCPLoadThread.colorID = 0L;
        TCPLoadThread.colorMap = new HashMap<>();
        TCPLoadThread.N = "N";
        TCPLoadThread.N0 = "N0";
        TCPLoadThread.N1 = "N1";
        TCPLoadThread.HX = "_";
    }
}
