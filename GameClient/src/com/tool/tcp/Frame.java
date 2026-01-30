package com.tool.tcp;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class Frame
{
    private static final long serialVersionUID = 2191491798990378838L;
    private Image image;
    private int pos;
    private int refPixelX;
    private int refPixelY;
    
    public Frame(int pos) {
        this.pos = pos;
    }
    
    public BufferedImage HighTcp(Image img) {
        BufferedImage image = (BufferedImage)img;
        BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), 2);
        image2.setData(image.getData());
        int width = image2.getWidth();
        int height = image2.getHeight();
        float brightnessValue = 50.0f;
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Color color = new Color(image2.getRGB(x, y), true);
                int red = (int)((double)color.getRed() * 1.4);
                int green = (int)((double)color.getGreen() * 1.4);
                int blue = (int)((double)color.getBlue() * 1.4);
                int Alpha = Math.max(color.getAlpha(), 0);
                if (red > 255) {
                    red = 255;
                }
                if (green > 255) {
                    green = 255;
                }
                if (blue > 255) {
                    blue = 255;
                }
                if (red < 256 && green < 256 && blue < 256 && red >= 0 && green >= 0 && blue >= 0) {
                    Color adjustedColor = new Color(red, green, blue, Alpha);
                    image2.setRGB(x, y, adjustedColor.getRGB());
                }
            }
        }
        return image2;
    }
    
    public Frame(BufferedImage image, int centerX, int centerY) {
        this.image = image;
        this.refPixelX = centerX;
        this.refPixelY = centerY;
    }
    
    public Frame(Image image, int centerX, int centerY) {
        this.image = image;
        this.refPixelX = centerX;
        this.refPixelY = centerY;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void dispose() {
        this.image = null;
    }
    
    protected void doDraw(Graphics2D g2, int x, int y, int width, int height) {
        int x2 = x - this.refPixelX;
        int y2 = y - this.refPixelY;
        g2.drawImage(this.image, x2, y2, x2 + width, y2 + height, 0, 0, width, height, null);
    }
    
    public int getRefPixelX() {
        return this.refPixelX;
    }
    
    public void setRefPixelX(int centerX) {
        this.refPixelX = centerX;
    }
    
    public int getRefPixelY() {
        return this.refPixelY;
    }
    
    public void setRefPixelY(int centerY) {
        this.refPixelY = centerY;
    }
    
    public void setImage(BufferedImage image) {
        this.pos = 0;
        this.image = image;
    }
    
    public void setImage(Image image, int x, int y) {
        this.pos = 1;
        this.refPixelX = x;
        this.refPixelY = y;
        this.image = image;
    }
    
    public boolean contains(int x, int y) {
        return false;
    }
    
    public int getWidth() {
        return this.image.getWidth(null);
    }
    
    public int getHeight() {
        return this.image.getHeight(null);
    }
    
    public int getPos() {
        return this.pos;
    }
    
    public void setPos(int pos) {
        this.pos = pos;
    }
}
