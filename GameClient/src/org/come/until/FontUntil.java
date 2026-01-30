package org.come.until;

import java.io.FileInputStream;
import java.io.File;
import java.awt.Font;

public class FontUntil
{
    public static Font loadFont(String fontFileName, float fontSize) {
        try {
            File file = new File(fontFileName);
            FileInputStream aixing = new FileInputStream(file);
            Font dynamicFont = Font.createFont(0, aixing);
            Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
            aixing.close();
            return dynamicFontPt;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Font("仿宋", 0, 14);
        }
    }
    
    public static Font getHanYiFont(int fontSize) {
        Font font = loadFont("font/HYF2GJM_0.TTF", (float)fontSize);
        font = font.deriveFont(1);
        return font;
    }

    public static Font getHYKTJFont1(int fontSize) {
        Font font = loadFont("font/hyhy.ttf", fontSize);
        return font.deriveFont(1);
    }
    public static Font getHanYiFont1(int fontSize) {
        Font font = loadFont("font/hy.ttf", fontSize);
        return font.deriveFont(1);
    }
    public static Font getHYKTJFont(int fontSize) {
        Font font = loadFont("font/HYC1GJM.TTF", (float)fontSize);
        font = font.deriveFont(1);
        return font;
    }
    
    public static Font getHYLSFFont(int fontSize) {
        Font font = loadFont("font/篆书字体.TTF", (float)fontSize);
        font = font.deriveFont(1);
        return font;
    }
    
    public static Font loadFont(String fontFileName) {
        try {
            File file = new File(fontFileName);
            FileInputStream aixing = new FileInputStream(file);
            Font dynamicFont = Font.createFont(0, aixing);
            return dynamicFont;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Font("仿宋", 1, 14);
        }
    }
}
