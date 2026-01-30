package com.tool.tcp;

import java.util.HashMap;
import java.util.ArrayList;
import com.tool.image.Creepsskin;
import org.come.bean.ConfigureBean;
import com.gl.util.Xy2Util;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.image.WritableRaster;
import com.tool.image.test.GlowFilter;
import java.util.Objects;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Iterator;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.List;

public class SpriteFactory
{
    public static final int ANIMATION_INTERVAL = 100;
    static final String WAS_FILE_TAG = "SP";
    static final String WAS_FILE_TAGTWO = "SH";
    static final int TCP_HEADER_SIZE = 12;
    static final int TYPE_ALPHA = 0;
    static final int TYPE_ALPHA_PIXEL = 32;
    static final int TYPE_ALPHA_REPEAT = 0;
    static final int TYPE_FLAG = 192;
    static final int TYPE_PIXELS = 64;
    static final int TYPE_REPEAT = 128;
    static final int TYPE_SKIP = 192;
    public static String[] ActionType;
    public static Object UPDATE_LOCK;
    public static List<Object> loads;
    public static TCPLoadThread thread;
    public static Map<Long, HHOne[]> indexMap;
    public static Map<Integer, FileRandom> indexFileMap;
    public static Sprite[] fillIns;
    public static Map<String, SoftReference<Sprite>> TcpMap;
    public static Map<Long, SoftReference<Sprite>> TcpTwoMap;
    public static Sprite shadow;
    public static final BufferedImage image;
    static String[] vvv;
    public static List<PartOne> clouds;
    
    public static HHOne[] initHHOne(long skin, SpriteHead randomIn) throws IOException {
        HHOne[] ones = new HHOne[randomIn.read()];
        for (int i = 0; i < ones.length; ++i) {
            HHOne one = new HHOne();
            one.setAct((byte)randomIn.read());
            long[] ls = new long[randomIn.read()];
            for (int j = 0; j < ls.length; ++j) {
                ls[j] = randomIn.readLong();
            }
            one.setLs(ls);
            ones[i] = one;
        }
        return ones;
    }
    
    public static void ResetAndRemove() {
        try {
            Iterator<String> it = SpriteFactory.TcpMap.keySet().iterator();
            while (it.hasNext()) {
                String key = (String)it.next();
                SoftReference<Sprite> a = (SoftReference<Sprite>)SpriteFactory.TcpMap.get(key);
                if (a == null || a.get() == null || ((Sprite)a.get()).getUse() == 0) {
                    it.remove();
                }
                else {
                    ((Sprite)a.get()).setUse(0);
                }
            }
            Iterator<Long> itLong = SpriteFactory.TcpTwoMap.keySet().iterator();
            while (itLong.hasNext()) {
                long keyLong = (long)itLong.next();
                SoftReference<Sprite> a2 = (SoftReference<Sprite>)SpriteFactory.TcpTwoMap.get(Long.valueOf(keyLong));
                if (a2 == null || a2.get() == null || ((Sprite)a2.get()).getUse() == 0) {
                    itLong.remove();
                }
                else {
                    ((Sprite)a2.get()).setUse(0);
                }
            }
            Iterator<Integer> itInt = SpriteFactory.indexFileMap.keySet().iterator();
            while (itInt.hasNext()) {
                int keyInt = (int)itInt.next();
                FileRandom fileRandom = (FileRandom)SpriteFactory.indexFileMap.get(Integer.valueOf(keyInt));
                if (fileRandom == null || fileRandom.isEnd()) {
                    itInt.remove();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized FileRandom getFileRandom(int i) {
        FileRandom fileRandom = (FileRandom)SpriteFactory.indexFileMap.get(Integer.valueOf(i));
        if (fileRandom == null) {
            try {
                fileRandom = new FileRandom("skin/" + i + ".hh");
                SpriteFactory.indexFileMap.put(Integer.valueOf(i), fileRandom);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            fileRandom.setUse(0);
        }
        return fileRandom;
    }
    
    public static Sprite Prepare(long skin, int act) {
        SoftReference<Sprite> s = (SoftReference<Sprite>)SpriteFactory.TcpTwoMap.get(Long.valueOf(skin));
        if (s == null || s.get() == null) {
            addLoad(((act == 0 || act == 1 || act == 2 || act == 10) ? TCPLoadThread.N0 : TCPLoadThread.N1) + skin);
            return null;
        }
        return (Sprite)s.get();
    }
    
    public static Sprite Prepare(String path) {
        SoftReference<Sprite> s = (SoftReference<Sprite>)SpriteFactory.TcpMap.get(path);
        if (s == null || s.get() == null) {
            addLoad(path);
            return null;
        }
        return (Sprite)s.get();
    }
    
    public static void addLoad(Object object) {
        if (object == null) {
            return;
        }
        synchronized (SpriteFactory.UPDATE_LOCK) {
            if (!SpriteFactory.loads.contains(object)) {
                SpriteFactory.loads.add(object);
                SpriteFactory.thread.setLoadTcp();
            }
        }
    }
    
    public static Object getLoad() {
        synchronized (SpriteFactory.UPDATE_LOCK) {
            return (SpriteFactory.loads.size() != 0) ? SpriteFactory.loads.get(0) : null;
        }
    }
    
    public static void clearLoad(Object load) {
        synchronized (SpriteFactory.UPDATE_LOCK) {
            SpriteFactory.loads.remove(load);
        }
    }
    
    public static Animation loadAnimation(String filename) {
        Animation animation = null;
        try {
            File file = new File(filename);
            if (file != null && file.exists()) {
                InputStream in = new FileInputStream(file);
                byte[] buf = new byte[2];
                in.read(buf);
                String fag = new String(buf);
                int version = fag.equals("SP") ? 0 : (fag.equals("SH") ? 1 : -1);
                if (version >= 0) {
                    buf = new byte[in.available()];
                    int a = 0;
                    int count = 0;
                    while (in.available() > 0) {
                        a = in.read(buf, count, in.available());
                        count += a;
                    }
                    in.close();
                    SpriteHead randomIn = new SpriteHead(buf);
                    animation = randomIn.initTwo();
                }
                else {
                    in.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (animation == null) {
            animation = new Animation();
            Frame frame = new Frame(SpriteFactory.image, 0, 0);
            animation.addFrame(frame);
        }
        return animation;
    }
    
    public static Sprite VloadSprite(long skin, boolean is, String value) {
        Sprite sprite = null;
        try {
            FileRandom fileRandom = getFileRandom((int)(skin >> 32));
            if (fileRandom != null) {
                byte[] bs = new byte[4];
                fileRandom.read(skin & 0x7FFFFFFFL, bs);
                int size = 0;
                for (int i = 0; i < bs.length; ++i) {
                    int v = bs[i] & 0xFF;
                    v <<= 8 * i;
                    size += v;
                }
                bs = new byte[size];
                fileRandom.read(skin + 4L & 0x7FFFFFFFL, bs);
                SpriteHead randomIn = new SpriteHead(bs);
                sprite = randomIn.init(value, is, 1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (sprite == null) {
            sprite = getFillIn(1);
        }
        return sprite;
    }
    
    public static Sprite VloadSprite(String filename, String value) {
        if (filename == null || filename.length() == 0) {
            return null;
        }
        Sprite sprite = null;
        try {
            File file = new File(filename);
            if (file != null && file.exists()) {
                InputStream in = new FileInputStream(file);
                byte[] buf = new byte[2];
                in.read(buf);
                String fag = new String(buf);
                int version = fag.equals("SP") ? 0 : (fag.equals("SH") ? 1 : -1);
                if (Objects.equals(fag, "SW")) {
                    version = 1;
                }
                if (version >= 0) {
                    buf = new byte[in.available()];
                    int a = 0;
                    int count = 0;
                    while (in.available() > 0) {
                        a = in.read(buf, count, in.available());
                        count += a;
                    }
                    in.close();
                    SpriteHead randomIn = new SpriteHead(buf);
                    sprite = randomIn.init(value, isV(filename), version);
                }
                else {
                    in.close();
                }
            }
            else {
                System.err.println("Warning: 找不到精灵的资源文件!" + filename);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (sprite == null && (value == null || value.length() > 0)) {
            sprite = getFillIn(getAct(filename));
        }
        return sprite;
    }
    
    public static void assignColor(short[] palette, String value) {
        try {
            String[] v = value.split("\\|");
            int sum = Integer.parseInt(v[0]);
            int start = Integer.parseInt(v[1]);
            int end = 0;
            for (int i = 1; i <= sum; ++i) {
                start = end;
                end = Integer.parseInt(v[i + 1]);
                ColorationScheme scheme = new ColorationScheme(v, sum + 2 + (i - 1) * 9);
                for (int j = start; j < end; ++j) {
                    palette[j] = scheme.mix(palette[j]);
                }
            }
        }
        catch (Exception e) {
            System.err.println("错误格式的着色方案");
        }
    }
    
    public static Sprite getFillIn(int act) {
        int value = 0;
        if (act == 1 || act == 0 || act == 10) {
            value = 1;
        }
        else if (act == 2 || act == 7) {
            value = 2;
        }
        else if (act == 4 || act == 6) {
            value = 3;
        }
        else if (act == 5 || act == 3 || act == 9 || act == 11 || act == 12) {
            value = 4;
        }
        else if (act == 8) {
            value = 5;
        }
        Sprite sprite = SpriteFactory.fillIns[value];
        if (sprite == null) {
            String path = null;
            if (value == 1) {
                path = "skin/000000/run.tcp";
            }
            else if (value == 2) {
                path = "skin/000000/stand.tcp";
            }
            else if (value == 3) {
                path = "skin/000000/hit.tcp";
            }
            else if (value == 4) {
                path = "skin/000000/magic.tcp";
            }
            else if (value == 5) {
                path = "resource/mouse/墓碑.tcp";
            }
            else {
                path = "resource/FightingSkill/10251.tcp";
            }
            sprite = VloadSprite(path, "");
            SpriteFactory.fillIns[value] = sprite;
        }
        return sprite;
    }
    /**二次加载 （图片加载）*/
    public static void VloadImg(Sprite sprite) {
        synchronized (sprite) {
            int loadDir = sprite.getLoad();
            int count = sprite.getFrameCount();
            if (loadDir == -1) {
                return;
            }
            loadDir %= sprite.getAnimationCount();

            //判断是否需要共用的方向
            boolean is = false;
            if (sprite.getAnimationCount() == 4 || sprite.getAnimationCount() == 8) {
                is = true;
                if (loadDir == 1) {
                    loadDir = 0;
                }
                else if (loadDir == 3) {
                    loadDir = 2;
                }
                else if (loadDir == 7) {
                    loadDir = 5;
                }
            }
            //避免重复加载图片
            if (sprite.getLoadFrame(loadDir).getImage() != null) {
                return;
            }
            SpriteHead in = sprite.getHead();
            if (in != null) {
                for (int i = 0; i < count; ++i) {
                    Frame frame = sprite.getFrames()[loadDir * count + i];
                    BufferedImage bufferedImage = createDraw(in, frame, sprite);
                    frame.setImage(bufferedImage);
                }
            }
            else {//使用默认的1*1图片
                for (int i = 0; i < count; ++i) {
                    sprite.getFrames()[loadDir * count + i].setImage(SpriteFactory.image);
                }
            }
            if (is) {//对象转换
                if (loadDir == 0) {
                    sprite.translate(0, 1);
                }
                else if (loadDir == 2) {
                    sprite.translate(2, 3);
                }
                else if (loadDir == 5) {
                    sprite.translate(5, 7);
                }
            }
            sprite.removeHead();//加载完毕
        }
    }
    
    public static BufferedImage createDraw(SpriteHead in, Frame frame, Sprite sprite) {
        try {
            if (frame.getPos() == 0) {
                return SpriteFactory.image;
            }
            in.seek(frame.getPos());
            int frameX = in.readInt();
            int frameY = in.readInt();
            if (sprite.getFrameCount() == 1) {
                frameX = sprite.getRefPixelX();
                frameY = sprite.getRefPixelY();
            }
            int frameWidth = in.readInt();
            int frameHeight = in.readInt();
            if (frameHeight > 1000) {
                System.err.println("报警:" + frameHeight);
                if (frameHeight >= 5000) {
                    return SpriteFactory.image;
                }
            }
            int[] lineOffsets = new int[frameHeight];
            for (int l = 0; l < frameHeight; ++l) {
                lineOffsets[l] = in.readInt();
            }
            frame.setRefPixelX(frameX);
            frame.setRefPixelY(frameY);
            if (frameWidth == 0 || frameHeight == 0) {
                return SpriteFactory.image;
            }
            short[] palette = in.getPalette();
            BufferedImage image = new BufferedImage(frameWidth, frameHeight, 2);
            WritableRaster raster = image.getRaster();
            int[] iArray = new int[4];
            for (int y = 0; y < frameHeight; ++y) {
                int x = 0;
                in.seek(lineOffsets[y] + frame.getPos());
            LOOP:
                while (x < frameWidth) {
                    int b = in.read();
                    switch (b & 0xC0) {
                        case 0: {
                            if ((b & 0x20) > 0) {
                                int index = in.read();
                                int c = palette[index];
                                iArray[0] = (c >>> 11 & 0x1F);
                                iArray[1] = (c >>> 5 & 0x3F);
                                iArray[2] = (c & 0x1F);
                                iArray[0] = (iArray[0] << 3 | (iArray[0] & 0x7));
                                iArray[1] = (iArray[1] << 2 | (iArray[1] & 0x3));
                                iArray[2] = (iArray[2] << 3 | (iArray[2] & 0x7));
                                iArray[3] = ((b & 0x1F) << 3 | (b & 0x7));
                                raster.setPixel(x++, y, iArray);
                                continue;
                            }
                            else if (b != 0) {
                                int count = b & 0x1F;
                                b = in.read();
                                int index = in.read();
                                int c = palette[index];
                                iArray[0] = (c >>> 11 & 0x1F);
                                iArray[1] = (c >>> 5 & 0x3F);
                                iArray[2] = (c & 0x1F);
                                iArray[0] = (iArray[0] << 3 | (iArray[0] & 0x7));
                                iArray[1] = (iArray[1] << 2 | (iArray[1] & 0x3));
                                iArray[2] = (iArray[2] << 3 | (iArray[2] & 0x7));
                                iArray[3] = ((b & 0x1F) << 3 | (b & 0x7));
                                int width = raster.getWidth();
                                int height = raster.getHeight();
                                for (int i = 0; i < count; ++i) {
                                    if (x < width && y < height) {
                                        raster.setPixel(x++, y, iArray);
                                    }
                                }
                                continue;
                            }
                            else if (x == 0 && y > 0) {
                                int yTwo = y - 1;
                                for (int j = x; j < frameWidth; ++j) {
                                    raster.setPixel(j, y, raster.getPixel(j, yTwo, iArray));
                                }
                                break LOOP;
                            }
                            else {
                                break LOOP;
                            }
                        }
                        case 64: {
                            for (int count = b & 0x3F, k = 0; k < count; ++k) {
                                int index = in.read();
                                if (index != -1 && x < raster.getWidth() && y < raster.getHeight()) {
                                    iArray[0] = (palette[index] >>> 11 & 0x1F);
                                    iArray[1] = (palette[index] >>> 5 & 0x3F);
                                    iArray[2] = (palette[index] & 0x1F);
                                    iArray[0] = (iArray[0] << 3 | (iArray[0] & 0x7));
                                    iArray[1] = (iArray[1] << 2 | (iArray[1] & 0x3));
                                    iArray[2] = (iArray[2] << 3 | (iArray[2] & 0x7));
                                    iArray[3] = 255;
                                    raster.setPixel(x++, y, iArray);
                                }
                            }
                            continue;
                        }
                        case 128: {
                            int count = b & 0x3F;
                            int index = in.read();
                            int c = palette[index];
                            iArray[0] = (c >>> 11 & 0x1F);
                            iArray[1] = (c >>> 5 & 0x3F);
                            iArray[2] = (c & 0x1F);
                            iArray[0] = (iArray[0] << 3 | (iArray[0] & 0x7));
                            iArray[1] = (iArray[1] << 2 | (iArray[1] & 0x3));
                            iArray[2] = (iArray[2] << 3 | (iArray[2] & 0x7));
                            iArray[3] = 255;
                            for (int k = 0; k < count; ++k) {
                                raster.setPixel(x++, y, iArray);
                            }
                            continue;
                        }
                        case 192: {
                            int count = b & 0x3F;
                            x += count;
                            continue;
                        }
                        default: {
                            break LOOP;
                        }
                    }
                }
                if (x > frameWidth) {
                    System.err.println("block end error: [" + y + "][" + x + "/" + frameWidth + "]");
                }
            }
            if ((boolean)sprite.getB()) {
                GlowFilter glowFilter = new GlowFilter();
                glowFilter.filter(image, image);
            }
            return image;
        }
        catch (Exception e) {
            e.printStackTrace();
            return SpriteFactory.image;
        }
    }
    
    public static BufferedImage createDrawTwo(SpriteHead in, Frame frame, int frameCount, int refPixelX, int refPixelY, int width, int height) {
        try {
            if (frame.getPos() == 0) {
                return SpriteFactory.image;
            }
            in.seek(frame.getPos());
            int dx = in.readInt();
            int dy = in.readInt();
            frame.setRefPixelX(dx);
            frame.setRefPixelY(dy);
            if (frameCount == 1) {
                dx = refPixelX;
                dy = refPixelY;
            }
            int frameWidth = in.readInt();
            int frameHeight = in.readInt();
            int[] lineOffsets = new int[frameHeight];
            for (int l = 0; l < frameHeight; ++l) {
                lineOffsets[l] = in.readInt();
            }
            if (frameWidth == 0 || frameHeight == 0) {
                return SpriteFactory.image;
            }
            short[] palette = in.getPalette();
            BufferedImage image = new BufferedImage(width, height, 2);
            WritableRaster raster = image.getRaster();
            dx = refPixelX - dx;
            dy = refPixelY - dy;
            int[] iArray = new int[4];
            for (int y = 0; y + dy < height && y < frameHeight; ++y) {
                int x = 0;
                in.seek(lineOffsets[y] + frame.getPos());
                LOOP:
                while (x < frameWidth) {
                    int b = in.read();
                    switch (b & 0xC0) {
                        case 0: {
                            if ((b & 0x20) > 0) {
                                int index = in.read();
                                int c = palette[index];
                                iArray[0] = (c >>> 11 & 0x1F);
                                iArray[1] = (c >>> 5 & 0x3F);
                                iArray[2] = (c & 0x1F);
                                iArray[0] = (iArray[0] << 3 | (iArray[0] & 0x7));
                                iArray[1] = (iArray[1] << 2 | (iArray[0] & 0x3));
                                iArray[2] = (iArray[2] << 3 | (iArray[0] & 0x7));
                                iArray[3] = (b & 0x1F) << 3;
                                try {
                                    raster.setPixel(x + dx, y + dy, iArray);
                                }
                                catch (Exception ex) {}
                                finally {
                                    ++x;
                                }
                                continue;
                            }
                            else if (b != 0) {
                                int count = b & 0x1F;
                                b = in.read();
                                int index = in.read();
                                int c = palette[index];
                                iArray[0] = (c >>> 11 & 0x1F);
                                iArray[1] = (c >>> 5 & 0x3F);
                                iArray[2] = (c & 0x1F);
                                iArray[0] = (iArray[0] << 3 | (iArray[0] & 0x7));
                                iArray[1] = (iArray[1] << 2 | (iArray[0] & 0x3));
                                iArray[2] = (iArray[2] << 3 | (iArray[0] & 0x7));
                                iArray[3] = (b & 0x1F) << 3;
                                for (int i = 0; i < count; ++i) {
                                    try {
                                        raster.setPixel(x + dx, y + dy, iArray);
                                    }
                                    catch (Exception ex2) {}
                                    finally {
                                        ++x;
                                    }
                                }
                                continue;
                            }
                            else if (x == 0 && y > 0) {
                                int yTwo = y - 1;
                                for (int j = x; j < frameWidth; ++j) {
                                    raster.setPixel(j, y, raster.getPixel(j, yTwo, iArray));
                                }
                                break LOOP;
                            }
                            else {
                                break LOOP;
                            }
                        }
                        case 64: {
                            for (int count = b & 0x3F, i = 0; i < count; ++i) {
                                int index = in.read();
                                iArray[0] = (palette[index] >>> 11 & 0x1F);
                                iArray[1] = (palette[index] >>> 5 & 0x3F);
                                iArray[2] = (palette[index] & 0x1F);
                                iArray[0] = (iArray[0] << 3 | (iArray[0] & 0x7));
                                iArray[1] = (iArray[1] << 2 | (iArray[0] & 0x3));
                                iArray[2] = (iArray[2] << 3 | (iArray[0] & 0x7));
                                iArray[3] = 248;
                                try {
                                    raster.setPixel(x + dx, y + dy, iArray);
                                }
                                catch (Exception ex3) {}
                                finally {
                                    ++x;
                                }
                            }
                            continue;
                        }
                        case 128: {
                            int count = b & 0x3F;
                            int index = in.read();
                            int c = palette[index];
                            iArray[0] = (c >>> 11 & 0x1F);
                            iArray[1] = (c >>> 5 & 0x3F);
                            iArray[2] = (c & 0x1F);
                            iArray[0] = (iArray[0] << 3 | (iArray[0] & 0x7));
                            iArray[1] = (iArray[1] << 2 | (iArray[0] & 0x3));
                            iArray[2] = (iArray[2] << 3 | (iArray[0] & 0x7));
                            iArray[3] = 248;
                            for (int i = 0; i < count; ++i) {
                                try {
                                    raster.setPixel(x + dx, y + dy, iArray);
                                }
                                catch (Exception ex4) {}
                                finally {
                                    ++x;
                                }
                            }
                            continue;
                        }
                        case 192: {
                            int count = b & 0x3F;
                            x += count;
                            continue;
                        }
                        default: {
                            break LOOP;
                        }
                    }
                }
                if (x > frameWidth) {
                    System.err.println("block end error: [" + y + "][" + x + "/" + frameWidth + "]");
                }
            }
            return image;
        }
        catch (Exception e) {
            e.printStackTrace();
            return SpriteFactory.image;
        }
    }
    
    public static boolean isV(String filename) {
        int i = 0;
        LOOP:
        while (i < 11) {
            if (filename.endsWith(SpriteFactory.vvv[i])) {
                int j = 11;
                while (j < SpriteFactory.vvv.length) {
                    if (filename.indexOf(SpriteFactory.vvv[j]) != -1) {
                        break LOOP;
                    }
                    else {
                        ++j;
                    }
                }
                return true;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public static int getAct(String filename) {
        if (filename.length() <= 4) {
            return -1;
        }
        filename = filename.substring(0, filename.length() - 4);
        for (int i = 0; i < SpriteFactory.ActionType.length; ++i) {
            if (filename.endsWith(SpriteFactory.ActionType[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public static int changdir(int dir, int size) {
        if (size == 2) {
            return (dir != 3) ? 1 : 0;
        }
        switch (dir) {
            case 0: {
                dir = 6;
                break;
            }
            case 1: {
                dir = 3;
                break;
            }
            case 2: {
                dir = 7;
                break;
            }
            case 3: {
                dir = 0;
                break;
            }
            case 4: {
                dir = 4;
                break;
            }
            case 5: {
                dir = 1;
                break;
            }
            case 6: {
                dir = 5;
                break;
            }
            case 7: {
                dir = 2;
                break;
            }
        }
        return dir;
    }
    
    public static String getActionType(int type) {
        return SpriteFactory.ActionType[type];
    }
    
    public static HHOne[] getHHOne(long skin) {
        HHOne[] ones = (HHOne[])SpriteFactory.indexMap.get(Long.valueOf(skin));
        if (ones == null && skin > 2147483647L && skin >> 32 > 18L) {
            ones = (HHOne[])SpriteFactory.indexMap.get(Long.valueOf((skin >> 32) - 18L << 32 | (long)(int)skin));
        }
        return ones;
    }
    //添加光武素材位置
    public static NewPart createPart(String skin, int act, int lvl, String color) {
//        System.err.println(skin);
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();//这里断点看角色素材id
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            if (skin.equals("6936372203050")) {
                skin = "81604398634";
            }
            if (skin.equals("26225070329377")) {
                skin = "85899365921";
            }
            if (skin.equals("26276609938932")) {
                skin = "120259106292";
            }
            if (skin.equals("128849039887")) {
                skin = "210070012";
            }
            if (skin.equals("26276609937936")) {
                skin = "120259105296";
            }
            if (skin.equals("26276609937937")) {
                skin = "120259105297";
            }
            if (skin.equals("26199300526605")) {
                skin = "81604399629";
            }
            if (skin.equals("26199300527609")) {
                skin = "81604400633";
            }
            if (skin.equals("26225070329385")) {
                skin = "85899365929";
            }
            if (skin.equals("26199300528601")) {
                skin = "81604401625";
            }
            if (skin.equals("26289494841821")) {
                skin = "150323878365";
            }
            if (skin.equals("26199300529601")) {
                skin = "81604402625";
            }
            if (skin.equals("26216480398789")) {
                skin = "128849042885";
            }
            if ("90194333218".equals(skin)) {
                skin = "600034";
            }
            if (skin.equals("146028910069")) {
                skin = "600032";
            }
            if (skin.equals("107374204405")) {
                skin = "600032";
            }
            if (skin.equals("30064793077")) {
                skin = "220050007";
            }
            if (skin.equals("90194337220")) {
                skin = "240040301";
            }
//            if (skin.equals("51539631554")) {//long 2
//                skin = "5008000";
//            }

        }
        if (color != null) {
            color = TCPLoadThread.addMap(color);
        }
        NewPart part = null;
        if (!nao.equals("新")) {
            if (Xy2Util.MODEL.containsKey(skin)) {
                part = new PartOne((String)Xy2Util.MODEL.get(skin), act, lvl, color);
            }
            else if (act != -2 && act != -1 && Xy2Util.isNumeric(skin)) {
                try {
                    long skinInt = Long.parseLong(skin);
                    HHOne[] ones = getHHOne(skinInt);
                    if (ones != null) {
                        part = new PartTwo(skinInt, ones, act, lvl, color);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (Xy2Util.NEWMODEL.containsKey(skin)) {
                part = new PartOne(Xy2Util.NEWMODEL.get(skin), act, lvl, color);
            } else if (act != -2 && act != -1 && Xy2Util.isNumeric(skin)) {
                long skinInt = Long.parseLong(skin);
                HHOne[] ones = getHHOne(skinInt);
                if (ones != null) {
                    part = new PartTwo(skinInt, ones, act, lvl, color);
                }
            }
        }
        if (part == null) {
            part = new PartOne(skin, act, lvl, color);
        }
        return part;
    }

    public static NewPart createPart(long skin, int act, int lvl, String color) {
        if (color != null) {
            color = TCPLoadThread.addMap(color);
        }
        NewPart part = null;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
//        if (skin==51539631554)) {
//            skin = 5008000;
//        }
        if (!nao.equals("新")) {
            if (Xy2Util.MODEL.containsKey(skin + "")) {
                part = new PartOne((String)Xy2Util.MODEL.get(skin + ""), act, lvl, color);
            }
            else if (act != -2 && act != -1) {
                try {
                    HHOne[] ones = getHHOne(skin);
                    if (ones != null) {
                        part = new PartTwo(skin, ones, act, lvl, color);
                        long mid = (long)Long.valueOf(skin) >> 40;
                        if (mid >= 1L && mid <= 7L) {
                            long sid = ((long)Long.valueOf(skin) - (mid << 40)) / 100L * 10L;
                            if (mid == 7L && Creepsskin.getRace(new BigDecimal(sid)) == 10001 && Creepsskin.getSex(new BigDecimal(sid)) == 0) {
                                part.setFly("shadow/" + sid + "/" + mid + "2", act, null);
                            }
                            else {
                                part.setFly("shadow/" + sid + "/" + mid, act, null);
                            }
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (skin == 1099511647787L || skin == 2199023275563L || skin == 3298534903339L || skin == 4398046531115L || skin == 5497558158891L || skin == 6597069786667L || skin == 7696581414443L || skin == 7696581414444L || skin == 6597069786668L || skin == 5497558158892L || skin == 4398046531116L || skin == 3298534903340L || skin == 2199023275564L || skin == 1099511647788L || skin == 7696581415443L || skin == 6597069787667L || skin == 5497558159891L || skin == 4398046532115L || skin == 3298534904339L || skin == 2199023276563L || skin == 1099511648787L || skin == 7696581415444L || skin == 6597069787668L || skin == 5497558159892L || skin == 4398046532116L || skin == 3298534904340L || skin == 2199023276564L || skin == 1099511648788L || skin == 7696581416443L || skin == 6597069788667L || skin == 5497558160891L || skin == 4398046533115L || skin == 3298534905339L || skin == 1099511649787L || skin == 2199023277563L || skin == 7696581416444L || skin == 6597069788668L || skin == 5497558160892L || skin == 4398046533116L || skin == 3298534905340L || skin == 2199023277564L || skin == 1099511649788L || skin == 1099511650783L || skin == 2199023278559L || skin == 3298534906335L || skin == 4398046534111L || skin == 5497558161887L || skin == 6597069789663L || skin == 7696581417439L || skin == 1099511650784L || skin == 2199023278560L || skin == 3298534906336L || skin == 4398046534112L || skin == 5497558161888L || skin == 6597069789664L || skin == 7696581417440L || skin == 1099511651783L || skin == 2199023279559L || skin == 3298534907335L || skin == 4398046535111L || skin == 5497558162887L || skin == 6597069790663L || skin == 7696581418439L || skin == 1099511651784L || skin == 2199023279560L || skin == 3298534907336L || skin == 4398046535112L || skin == 5497558162888L || skin == 6597069790664L || skin == 7696581418440L) {
            part = new PartOne(skin + "", act, lvl, color);
        }
        else if (act != -2 && act != -1) {
            try {
                HHOne[] ones = getHHOne(skin);
                if (ones != null) {
                    part = new PartTwo(skin, ones, act, lvl, color);
                    long mid = (long)Long.valueOf(skin) >> 40;
                    if (mid >= 1L && mid <= 7L) {
                        long sid = ((long)Long.valueOf(skin) - (mid << 40)) / 100L * 10L;
                        if (mid == 7L && Creepsskin.getRace(new BigDecimal(sid)) == 10001 && Creepsskin.getSex(new BigDecimal(sid)) == 0) {
                            part.setFly("shadow/" + sid + "/" + mid + "2", act, null);
                        }
                        else {
                            part.setFly("shadow/" + sid + "/" + mid, act, null);
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (part == null) {
            part = new PartOne(skin + "", act, lvl, color);
        }
        return part;
    }
    
    public static NewPart setPart(NewPart newPart, int lvl, String skin) {
        try {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            if (!nao.equals("新")) {
                if (Long.parseLong(skin) >= 20001L && Long.parseLong(skin) <= 22012L) {
                    newPart.setAct(2);
                }
                else if (Long.parseLong(skin) >= 23001L && Long.parseLong(skin) <= 23008L) {
                    newPart.setAct(2);
                }
                else if (Long.parseLong(skin) >= 24001L && Long.parseLong(skin) <= 24008L) {
                    newPart.setAct(2);
                }
                else {
                    newPart.setAct(7);
                }
                if (Xy2Util.MODEL.containsKey(skin + "")) {
                    return newPart.setPart(lvl, (String)Xy2Util.MODEL.get(skin + ""));
                }
                long skinLong = Long.parseLong(skin);
                HHOne[] ones = getHHOne(skinLong);
                if (ones != null) {
                    return newPart.setPart(lvl, skinLong, ones);
                }
            }
            else {
                long skinLong = Long.parseLong(skin);
                HHOne[] ones = getHHOne(skinLong);
                if (ones != null) {
                    return newPart.setPart(lvl, skinLong, ones);
                }
            }
        }
        catch (Exception ex) {}
        return newPart.setPart(lvl, skin);
    }
    
    public static List<PartOne> getClouds() {
        return SpriteFactory.clouds;
    }
    
    public static void setClouds(List<PartOne> clouds) {
        SpriteFactory.clouds = clouds;
    }
    
    public static Sprite VloadSprite1(String filename, InputStream in, String value) {
        if (filename == null || filename.length() == 0) {
            return null;
        }
        Sprite sprite = null;
        try {
            if (in != null) {
                byte[] buf = new byte[2];
                in.read(buf);
                String fag = new String(buf);
                int version = fag.equals("SP") ? 0 : (fag.equals("SH") ? 1 : -1);
                if (Objects.equals(fag, "SW")) {
                    version = 1;
                }
                if (version >= 0) {
                    buf = new byte[in.available()];
                    int a = 0;
                    int count = 0;
                    while (in.available() > 0) {
                        a = in.read(buf, count, in.available());
                        count += a;
                    }
                    in.close();
                    SpriteHead randomIn = new SpriteHead(buf);
                    sprite = randomIn.init(value, isV(filename), version);
                }
                else {
                    in.close();
                }
            }
            else {
                System.err.println("Warning: 找不到精灵的资源文件!" + filename);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (sprite == null && (value == null || value.length() > 0)) {
            sprite = getFillIn(getAct(filename));
        }
        return sprite;
    }
    
    static {
        SpriteFactory.ActionType = new String[14];
        SpriteFactory.UPDATE_LOCK = new Object();
        SpriteFactory.loads = new ArrayList<>();
        SpriteFactory.fillIns = new Sprite[6];
        SpriteFactory.TcpMap = new HashMap<>();
        SpriteFactory.TcpTwoMap = new HashMap<>();
        image = new BufferedImage(1, 1, 2);
        SpriteFactory.vvv = new String[] { "hit.tcp", "magic.tcp", "defend.tcp", "guard.tcp", "die.tcp", "attack.tcp", "t2.tcp", "4001.tcp", "4002.tcp", "4003.tcp", "4004.tcp", "400509", "400535", "400314", "1237.tcp", "1298.tcp", "4101.tcp", "4102.tcp", "4103.tcp" };
        SpriteFactory.clouds = new ArrayList<>();
        SpriteFactory.ActionType[0] = "walk";
        SpriteFactory.ActionType[1] = "run";
        SpriteFactory.ActionType[2] = "stand";
        SpriteFactory.ActionType[3] = "t1";
        SpriteFactory.ActionType[4] = "hit";
        SpriteFactory.ActionType[5] = "magic";
        SpriteFactory.ActionType[6] = "defend";
        SpriteFactory.ActionType[7] = "guard";
        SpriteFactory.ActionType[8] = "die";
        SpriteFactory.ActionType[9] = "attack";
        SpriteFactory.ActionType[10] = "attackrun";
        SpriteFactory.ActionType[11] = "t2";
        SpriteFactory.ActionType[12] = "t3";
        SpriteFactory.ActionType[13] = "t4";
        SpriteFactory.indexFileMap = new HashMap<>();
        SpriteFactory.indexMap = new HashMap<>();
        try {
            File file = new File("skin/hh.init");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[fis.available()];
                fis.read(data);
                SpriteHead randomIn = new SpriteHead(data);
                randomIn.seek(2);
                while (randomIn.available() > 0) {
                    long skin = randomIn.readLong();
                    SpriteFactory.indexMap.put(Long.valueOf(skin), initHHOne(skin, randomIn));
                }
                fis.close();
                randomIn.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        (SpriteFactory.thread = new TCPLoadThread()).start();
        (SpriteFactory.shadow = VloadSprite("skin/YZ.tcp", null)).updateToTime(0L, 0);
    }
}
