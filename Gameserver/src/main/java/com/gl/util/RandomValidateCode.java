package com.gl.util;

import java.awt.Color;
import java.awt.Graphics;
import javax.servlet.http.HttpSession;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

public class RandomValidateCode
{
    public static final String RANDOMCODEKEY = "randomcode_key";// 放到session中的key
    private Random random;
    private String randString;
    private int width;
    private int height;
    private int lineSize;
    private int stringNum;
    
    public RandomValidateCode() {
        this.random = new Random();
        this.randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串
        this.width = 80;// 图片宽
        this.height = 26;// 图片高
        this.lineSize = 40;// 干扰线数量
        this.stringNum = 4;// 随机产生字符数量
    }
    
    public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(this.width, this.height, 4);
        //产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, this.width, this.height);
        g.setFont(new Font("Times New Roman", 0, 18));
        g.setColor(this.getRandColor(160, 200));
        //绘制干扰线
        for (int i = 0; i <= this.lineSize; ++i) {
            this.drowLine(g);
        }
        //绘制随机字符
        String randomString = "";
        for (int j = 1; j <= this.stringNum; ++j) {
            randomString = this.drowString(g, randomString, j);
        }
        session.removeAttribute("randomcode_key");
        session.setAttribute("randomcode_key", randomString);
        g.dispose();
        try {
            //将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", 1, 18);
    }
    
    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + this.random.nextInt(bc - fc - 16);
        int g = fc + this.random.nextInt(bc - fc - 14);
        int b = fc + this.random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }
    /*
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(this.getFont());
        g.setColor(new Color(this.random.nextInt(101), this.random.nextInt(111), this.random.nextInt(121)));
        String rand = this.getRandomString(this.random.nextInt(this.randString.length()));
        randomString += rand;
        g.translate(this.random.nextInt(3), this.random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }
    /*
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = this.random.nextInt(this.width);
        int y = this.random.nextInt(this.height);
        int xl = this.random.nextInt(13);
        int yl = this.random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }
    /*
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(this.randString.charAt(num));
    }
}
