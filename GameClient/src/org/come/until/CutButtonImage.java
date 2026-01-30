package org.come.until;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.updateNew.MyIsif;
import org.come.test.Main;
import java.io.IOException;
import java.io.File;
import java.awt.Image;
import java.awt.image.ImageFilter;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.lang.ref.SoftReference;
import org.come.bean.HeadImgBean;
import org.come.bean.ImgZoom;
import javax.swing.ImageIcon;
import java.util.Map;

public class CutButtonImage
{
    static Map<String, ImageIcon[]> img2;
    static Map<String, ImgZoom> img3;
    static Map<String, HeadImgBean> img4;
    public static ImageIcon JT;
    public static Map<String, SoftReference<ImageIcon>> img;
    private static List<String> headNulls;
    
    public static ImageIcon[] cuts(byte[] srcImageFile, String fileName) throws Exception {
        if (CutButtonImage.img2.get(fileName) != null) {
            return (ImageIcon[])(ImageIcon[])CutButtonImage.img2.get(fileName);
        }
        ImageIcon[] icons = new ImageIcon[3];
        if (srcImageFile.length == 0) {
            System.out.println("没素材:" + fileName);
            icons[0] = getJT();
            icons[1] = getJT();
            icons[2] = getJT();
            return icons;
        }
        InputStream in = new ByteArrayInputStream(srcImageFile);
        BufferedImage src = ImageIO.read(in);
        int destWidth = src.getWidth();
        int destHeight = src.getHeight() / 3;
        for (int i = 0; i < 3; ++i) {
            ImageFilter cropFilter = new CropImageFilter(0, i * destHeight, destWidth, destHeight);
            icons[i] = new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(), cropFilter)));
        }
        CutButtonImage.img2.put(fileName, icons);
        return icons;
    }
    
    public static ImageIcon getImage(byte[] imageByte, String fileName, int w, int h) {
        if (w == -1) {
            SoftReference<ImageIcon> Simg = (SoftReference<ImageIcon>)CutButtonImage.img.get(fileName);
            if (Simg == null || Simg.get() == null) {
                ImageIcon imgp = new ImageIcon(imageByte);
                if (imgp.getIconHeight() == -1) {
                    if (CutButtonImage.JT == null) {
                        CutButtonImage.JT = new ImageIcon("img/xy2uiimg/小地图红点0000.png");
                    }
                    imgp = CutButtonImage.JT;
                }
                Simg = new SoftReference(imgp);
                CutButtonImage.img.put(fileName, Simg);
            }
            return (ImageIcon)Simg.get();
        }
        else {
            SoftReference<ImageIcon> Simg2 = (SoftReference<ImageIcon>)CutButtonImage.img.get(fileName);
            if (Simg2 == null || Simg2.get() == null) {
                ImageIcon imgp2 = getImage(imageByte, fileName, -1, -1);
                if (imgp2 == CutButtonImage.JT) {
                    return CutButtonImage.JT;
                }
                if (imgp2.getIconHeight() == h && imgp2.getIconWidth() == w) {
                    return imgp2;
                }
                imgp2 = new ImageIcon(imgp2.getImage().getScaledInstance(w, h, 10));
                Simg2 = new SoftReference(imgp2);
                CutButtonImage.img.put(fileName, Simg2);
            }
            return (ImageIcon)Simg2.get();
        }
    }
    
    public static Image cut(String srcImageFile, int path) throws Exception {
        BufferedImage src = ImageIO.read(new File(srcImageFile));
        int destWidth = src.getWidth();
        int destHeight = src.getHeight() / 3;
        for (int i = 0; i < 3; ++i) {
            if (i == path) {
                ImageFilter cropFilter = new CropImageFilter(0, i * destHeight, destWidth, destHeight);
                Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(), cropFilter));
                return img;
            }
        }
        return null;
    }
    
    public static ImageIcon[] cuts(String srcImageFile) {
        if (CutButtonImage.img2.get(srcImageFile) != null) {
            return (ImageIcon[])CutButtonImage.img2.get(srcImageFile);
        }
        ImageIcon[] icons = new ImageIcon[3];
        File file = new File(srcImageFile);
        if (!file.exists()) {
            icons[0] = getJT();
            icons[1] = getJT();
            icons[2] = getJT();
            return icons;
        }
        String[] vs = srcImageFile.split("/");
        boolean k = false;
        if (vs.length >= 3 && (vs[2].equals("14.png") || vs[2].equals("13.png") || vs[2].equals("S412.png") || vs[2].equals("S413.png"))) {
            k = true;
        }
        BufferedImage src = null;
        try {
            src = ImageIO.read(file);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (srcImageFile.contains("青龙") || srcImageFile.contains("玄武") || srcImageFile.contains("朱雀") || srcImageFile.contains("白虎") || srcImageFile.contains("中天")) {
            k = true;
        }
        if (vs.length >= 3 && vs[2] != null && (vs[2].equals("14.png") || vs[2].equals("13.png") || vs[2].equals("S412.png") || vs[2].equals("S413.png"))) {
            k = true;
        }
        if (srcImageFile.equals("inkImg/Client/无加号待解锁格.png") || srcImageFile.equals("inkImg/Client/待升级后解锁.png") || srcImageFile.equals("inkImg/Client/加号.png") || srcImageFile.equals("inkImg/Client/守护石.png") || srcImageFile.equals("inkImg/Client/守护石圆圈带未点亮守护石.png") || srcImageFile.equals("inkImg/Client/守护石圆圈带守护石.png")) {
            k = true;
        }
        if (k) {
            int destWidth = src.getWidth();
            int destHeight = src.getHeight();
            for (int i = 0; i < 3; ++i) {
                ImageFilter cropFilter = new CropImageFilter(0, i * destHeight, destWidth, destHeight);
                icons[i] = new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(), cropFilter)));
            }
            if (srcImageFile.equals("inkImg/Client/守护石.png")) {
                String imagePath = "inkImg/Client/守护石.png";
                File imageFile = new File(imagePath);
                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(imageFile);
                }
                catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
                int maxSize = Math.max(originalImage.getWidth(), originalImage.getHeight());
                int scaledWidth = originalImage.getWidth() * 45 / maxSize;
                int scaledHeight = originalImage.getHeight() * 45 / maxSize;
                Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, 4);
                icons[0] = new ImageIcon(scaledImage);
            }
            icons[2] = icons[0];
            icons[1] = icons[0];
            return icons;
        }
        else {
            int destWidth = src.getWidth();
            int destHeight = src.getHeight() / 3;
            for (int i = 0; i < 3; ++i) {
                ImageFilter cropFilter = new CropImageFilter(0, i * destHeight, destWidth, destHeight);
                icons[i] = new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(), cropFilter)));
            }
            CutButtonImage.img2.put(srcImageFile, icons);
            return icons;
        }
    }
    
    public static ImageIcon size(String path, int w, int h) {
        ImageIcon image = null;
        File imageFile = new File(path);
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(imageFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        int maxSize = Math.max(originalImage.getWidth(), originalImage.getHeight());
        int scaledWidth = originalImage.getWidth() * w / maxSize;
        int scaledHeight = originalImage.getHeight() * h / maxSize;
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, 4);
        image = new ImageIcon(scaledImage);
        return image;
    }

    public static ImgZoom cuts(final String srcImageFile, final int w, final int h, final boolean l) {
        if (CutButtonImage.img3.get(srcImageFile) != null) {
            return new ImgZoom(CutButtonImage.img3.get(srcImageFile));
        }
        else {
            final ImgZoom zoom = new ImgZoom();
            final Image[] icons = new Image[9];
            try {
                final BufferedImage src = ImageIO.read(new File(srcImageFile));
                zoom.setMiddlew(src.getWidth() - 2 * w);
                zoom.setMiddleh(src.getHeight() - 2 * h);
                zoom.setEdgew(w);
                zoom.setEdgeh(h);
                zoom.setImgs(icons);
                final BufferedImage image1 = new BufferedImage(w, h, 2);
                drawImg(image1, src, 0, 0);
                icons[0] = image1;
                final BufferedImage image2 = new BufferedImage(w, h, 2);
                drawImg(image2, src, w + zoom.getMiddlew(), 0);
                icons[1] = image2;
                final BufferedImage image3 = new BufferedImage(w, h, 2);
                drawImg(image3, src, 0, h + zoom.getMiddleh());
                icons[2] = image3;
                final BufferedImage image4 = new BufferedImage(w, h, 2);
                drawImg(image4, src, w + zoom.getMiddlew(), h + zoom.getMiddleh());
                icons[3] = image4;
                final BufferedImage image5 = new BufferedImage(w, zoom.getMiddleh(), 2);
                drawImg(image5, src, 0, h);
                icons[4] = image5;
                final BufferedImage image6 = new BufferedImage(w, zoom.getMiddleh(), 2);
                drawImg(image6, src, w + zoom.getMiddlew(), h);
                icons[5] = image6;
                final BufferedImage image7 = new BufferedImage(zoom.getMiddlew(), h, 2);
                drawImg(image7, src, w, 0);
                icons[6] = image7;
                final BufferedImage image8 = new BufferedImage(zoom.getMiddlew(), h, 2);
                drawImg(image8, src, w, h + zoom.getMiddleh());
                icons[7] = image8;
                if (l) {
                    final BufferedImage image9 = new BufferedImage(zoom.getMiddlew(), zoom.getMiddleh(), 2);
                    drawImg(image9, src, w, h);
                    icons[8] = image9;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            CutButtonImage.img3.put(srcImageFile, zoom);
            return zoom;
    }
    }

    public static ImgZoom cutsSmallMap(Image image, String srcImageFile, int w, int h, boolean l) {
        if (CutButtonImage.img3.get(srcImageFile) != null) {
            return new ImgZoom((ImgZoom)CutButtonImage.img3.get(srcImageFile));
        }
        ImgZoom zoom = new ImgZoom();
        Image[] icons = new Image[9];
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile));
            zoom.setMiddlew(image.getWidth(null));
            zoom.setMiddleh(src.getHeight() - 2 * h);
            zoom.setEdgew(w);
            zoom.setEdgeh(h);
            zoom.setImgs(icons);
            BufferedImage image2 = new BufferedImage(1, 1, 2);
            drawImg(image2, src, 0, 0);
            icons[0] = image2;
            BufferedImage image3 = new BufferedImage(w, h, 2);
            drawImg(image3, src, src.getWidth(null) - 83, 0);
            icons[1] = image3;
            BufferedImage image4 = new BufferedImage(w, h, 2);
            drawImg(image4, src, 0, h + zoom.getMiddleh());
            icons[2] = image4;
            BufferedImage image5 = new BufferedImage(w, h, 2);
            drawImg(image5, src, w + zoom.getMiddlew(), h + zoom.getMiddleh());
            icons[3] = image5;
            BufferedImage image6 = new BufferedImage(w, zoom.getMiddleh(), 2);
            drawImg(image6, src, 0, h);
            icons[4] = image6;
            BufferedImage image7 = new BufferedImage(w, zoom.getMiddleh(), 2);
            drawImg(image7, src, zoom.getMiddlew(), h);
            icons[5] = image7;
            BufferedImage image8 = new BufferedImage(zoom.getMiddlew(), h, 2);
            drawImg(image8, src, 0, 0);
            icons[6] = image8;
            BufferedImage image9 = new BufferedImage(zoom.getMiddlew(), h, 2);
            drawImg(image9, src, w - 10, h + zoom.getMiddleh());
            icons[7] = image9;
            if (l) {
                BufferedImage image10 = new BufferedImage(zoom.getMiddlew(), zoom.getMiddleh(), 2);
                drawImg(image10, src, w, h);
                icons[8] = image10;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        CutButtonImage.img3.put(srcImageFile, zoom);
        return zoom;
    }
    
    public static ImageIcon getJT() {
        if (CutButtonImage.JT == null) {
            CutButtonImage.JT = new ImageIcon("img/xy2uiimg/小地图红点0000.png");
        }
        return CutButtonImage.JT;
    }
    
    public static void drawImg(BufferedImage img, BufferedImage src, int w, int h) {
        int maxw = w;
        int maxh = h;
        maxw += img.getWidth();
        maxh += img.getHeight();
        if (maxw > src.getWidth()) {
            maxw = src.getWidth();
        }
        if (maxh > src.getHeight()) {
            maxh = src.getHeight();
        }
        for (int i = w; i < maxw; ++i) {
            for (int j = h; j < maxh; ++j) {
                img.setRGB(i - w, j - h, src.getRGB(i, j));
            }
        }
    }
    
    public static ImageIcon getImage(String path, int w, int h) {
        if (path.contains("inkImg/cbg")) {
            String fn = path.split("/")[2].split("\\.")[0];
            ImageIcon icon = getImage((byte[])Main.imageMap.getImgMap().get(fn), fn, w, h);
            return icon;
        }
        if (w == -1) {
            SoftReference<ImageIcon> Simg = (SoftReference<ImageIcon>)CutButtonImage.img.get(path);
            if (Simg == null || Simg.get() == null) {
                ImageIcon imgp = new ImageIcon(path);
                if (imgp.getIconHeight() == -1) {
                    if (CutButtonImage.JT == null) {
                        CutButtonImage.JT = new ImageIcon("img/xy2uiimg/小地图红点0000.png");
                    }
                    imgp = CutButtonImage.JT;
                }
                Simg = new SoftReference(imgp);
                CutButtonImage.img.put(path, Simg);
            }
            return (ImageIcon)Simg.get();
        }
        else {
            String value = w + "" + h + path;
            SoftReference<ImageIcon> Simg2 = (SoftReference<ImageIcon>)CutButtonImage.img.get(value);
            if (Simg2 == null || Simg2.get() == null) {
                ImageIcon imgp2 = getImage(path, -1, -1);
                if (imgp2 == CutButtonImage.JT) {
                    return CutButtonImage.JT;
                }
                if (imgp2.getIconHeight() != h || imgp2.getIconWidth() != w) {
                    imgp2 = new ImageIcon(imgp2.getImage().getScaledInstance(w, h, 10));
                    Simg2 = new SoftReference(imgp2);
                    CutButtonImage.img.put(value, Simg2);
                }
                else {
                    return imgp2;
                }
            }
            return (ImageIcon)Simg2.get();
        }
    }
    
    public static ImageIcon getCBG(int type, String path, int w, int h) {
        if (type == 2) {
            return getImage("img/item/8.png", w, h);
        }
        if (type == 3 || type == 5) {
            return getImage("img/item/" + path + ".png", w, h);
        }
        if (type == 4) {
            ImageIcon pet = getImage("img/head/p" + path + ".png", w, h);
            if (pet == CutButtonImage.JT) {
                pet = getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", w, h);
            }
            return pet;
        }
        else {
            if (type == 6) {
                return getImage("img/lingbao/" + path + ".png", w, h);
            }
            if (CutButtonImage.JT == null) {
                CutButtonImage.JT = new ImageIcon("img/xy2uiimg/小地图红点0000.png");
            }
            return CutButtonImage.JT;
        }
    }
    
    public static ImageIcon getGemstoneIcon(String path, int w, int h) {
        return getImage("img/item/" + path + ".png", w, h);
    }
    
    public static ImageIcon TYC(String type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type.equals("毒")) {
                return getImage("inkImg/background/66.png", -1, -1);
            }
            if (type.equals("三尸虫")) {
                return getImage("inkImg/background/63.png", -1, -1);
            }
            if (type.equals("震慑") || type.equals("盘丝") || type.equals("遗忘") || type.equals("魅惑")) {
                return getImage("inkImg/background/64.png", -1, -1);
            }
            if (type.equals("风") || type.equals("雷") || type.equals("水") || type.equals("火")) {
                return getImage("inkImg/background/67.png", -1, -1);
            }
            return getImage("inkImg/background/65.png", -1, -1);
        }
        else {
            if (type.equals("毒")) {
                return getImage("img/soaringSkill/天演策-女人w170,h310.png", -1, -1);
            }
            if (type.equals("三尸虫")) {
                return getImage("img/soaringSkill/天演策-鬼w170,h310.png", -1, -1);
            }
            if (!type.equals("震慑") && !type.equals("盘丝") && !type.equals("遗忘") && !type.equals("魅惑")) {
                return (!type.equals("风") && !type.equals("雷") && !type.equals("水") && !type.equals("火")) ? getImage("img/soaringSkill/天演策-男人w170,h310.png", -1, -1) : getImage("img/soaringSkill/天演策-仙w170,h310.png", -1, -1);
            }
            return getImage("img/soaringSkill/天演策-魔w170,h310.png", -1, -1);
        }
    }
    
    public static ImageIcon TYCSkill(int id) {
        return getImage("img/soaringSkill/40×40/" + id + ".png", -1, -1);
    }
    
    public static ImageIcon LxSkill(int id) {
        return getImage("img/skill/wxs_" + id + ".png", -1, -1);
    }
    
    public static ImageIcon shimenskll(int id) {
        return getImage("img/skill/wxs_" + id + ".png", -1, -1);
    }
    
    public static HeadImgBean getNPCHeadImg(String imgPath) {
        if (CutButtonImage.headNulls.contains(imgPath)) {
            return null;
        }
        HeadImgBean imgBean = (HeadImgBean)CutButtonImage.img4.get(imgPath);
        if (imgBean != null) {
            imgBean.setNum(4);
            return imgBean;
        }
        ImageIcon icon = new ImageIcon("img/head/N" + imgPath + ".png");
        if (icon.getIconHeight() == -1) {
            icon = new ImageIcon("img/head/N600000.png");
        }
        if (icon.getIconHeight() != -1) {
            imgBean = new HeadImgBean(4, icon);
            CutButtonImage.img4.put(imgPath, imgBean);
            return imgBean;
        }
        CutButtonImage.headNulls.add(imgPath);
        return null;
    }
    
    public static void removeNPCHeadImg() {
        Iterator<String> it = CutButtonImage.img4.keySet().iterator();
        while (it.hasNext()) {
            String key = (String)it.next();
            HeadImgBean imgBean = (HeadImgBean)CutButtonImage.img4.get(key);
            if (imgBean.is()) {
                it.remove();
            }
        }
    }
    
    public static ImageIcon STTYCSkill(int id, String value) {
        value = Race(value);
        return getImage("Client/神通天演册/40×40/" + value + "/" + id + ".png", -1, -1);
    }
    
    public static ImageIcon XZ1(String value, int index) {
        value = Race(value);
        return getImage("Client/神通天演册/" + value + "_" + index + ".png ", -1, -1);
    }
    
    public static ImageIcon XZ2(String value, int index) {
        value = Race(value);
        return getImage("Client/神通天演册/" + value + "_" + index + ".png ", -1, -1);
    }
    
    public static ImageIcon Zoom(String value, int index) {
        value = Race(value);
        return getImage("Client/神通天演册/" + value + "/" + value + "_" + index + ".png ", -1, -1);
    }
    
    public static ImageIcon ZoomJr(String value, int index) {
        value = Race(value);
        return getImage("Client/神通天演册/" + value + "/" + value + "_" + index + ".png ", -1, -1);
    }
    
    public static String Race(String value) {
        if (value.contains("魔")) {
            value = "魔";
        }
        else if (value.contains("鬼")) {
            value = "鬼";
        }
        else if (value.contains("人")) {
            value = "人";
        }
        else if (value.contains("仙")) {
            value = "仙";
        }
        else if (value.contains("龙")) {
            value = "龙";
        }
        return value;
    }
    
    public static int ZS(String value) {
        int n = -1;
        switch (value.hashCode()) {
            case 749633: {
                if (value.equals("女魔")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 969981: {
                if (value.equals("男魔")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 730054: {
                if (value.equals("女仙")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 950371: {
                if (value.equals("男人")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 950402: {
                if (value.equals("男仙")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 749609: {
                if (value.equals("女鬼")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 969957: {
                if (value.equals("男鬼")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 971074: {
                if (value.equals("男龙")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 750726: {
                if (value.equals("女龙")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return 180;
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                return 160;
            }
            case 6:
            case 7:
            case 8: {
                return 140;
            }
            default: {
                return 181;
            }
        }
    }
    
    public static ImageIcon CJ(String value) {
        return getImage("Client/神通天演册/" + value + ".png", -1, -1);
    }
    
    public static ImageIcon CJR(String value) {
        return getImage("Client/神通天演册/人/" + value + ".png", -1, -1);
    }
    
    public static ImageIcon Selected(String value) {
        return getImage("Client/神通天演册/" + value + "_1.png", -1, -1);
    }
    
    public static ImageIcon ZQ(String value) {
        if (value.equals("男魔") || value.equals("女魔")) {
            return getImage("Client/神通天演册/0413_12.png", -1, -1);
        }
        if (value.equals("男人") || value.equals("女人")) {
            return getImage("Client/神通天演册/0413_11.png", -1, -1);
        }
        if (value.equals("男仙") || value.equals("女仙")) {
            return getImage("Client/神通天演册/0413_13.png", -1, -1);
        }
        if (value.equals("男鬼") || value.equals("女鬼")) {
            return getImage("Client/神通天演册/0413_10.png", -1, -1);
        }
        if (value.equals("男龙") || value.equals("女龙")) {
            return getImage("Client/神通天演册/0413_9.png", -1, -1);
        }
        return getImage("Client/神通天演册/0413_9.png", -1, -1);
    }
    
    public static ImageIcon TYHtoppainting(String value) {
        if (value.equals("男魔") || value.equals("女魔")) {
            return getImage("Client/神通天演册/0768-F1491697.png", -1, -1);
        }
        if (value.equals("男人") || value.equals("女人")) {
            return getImage("Client/神通天演册/0700-DA236C26.png", -1, -1);
        }
        if (value.equals("男仙") || value.equals("女仙")) {
            return getImage("Client/神通天演册/0019-07052E39.png", -1, -1);
        }
        if (value.equals("男鬼") || value.equals("女鬼")) {
            return getImage("Client/神通天演册/0623-C51880CC.png", -1, -1);
        }
        if (value.equals("男龙") || value.equals("女龙")) {
            return getImage("Client/神通天演册/0021-07FF4B5C.png", -1, -1);
        }
        return getImage("Client/神通天演册/0021-07FF4B5C.png", -1, -1);
    }
    
    public static ImageIcon STTYCWAS(String value) {
        if (value.equals("男魔") || value.equals("女魔")) {
            return getImage("Client/神通天演册/0413_8.png", -1, -1);
        }
        if (value.equals("男人") || value.equals("女人")) {
            return getImage("Client/神通天演册/0413_7.png", -1, -1);
        }
        if (value.equals("男仙") || value.equals("女仙")) {
            return getImage("Client/神通天演册/0413_4.png", -1, -1);
        }
        if (value.equals("男鬼") || value.equals("女鬼")) {
            return getImage("Client/神通天演册/0413_5.png", -1, -1);
        }
        if (value.equals("男龙") || value.equals("女龙")) {
            return getImage("Client/神通天演册/0413_6.png", -1, -1);
        }
        return getImage("Client/神通天演册/0413_6.png", -1, -1);
    }
    
    static {
        CutButtonImage.img2 = new HashMap<>();
        CutButtonImage.img3 = new HashMap<>();
        CutButtonImage.img4 = new HashMap<>();
        CutButtonImage.img = new HashMap<>();
        CutButtonImage.headNulls = new ArrayList<>();
    }
}
