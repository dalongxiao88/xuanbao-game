package org.come.login;

import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Graphics;
import org.come.until.Util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class ValidCode extends JComponent implements MouseListener
{
    private String code;
    private int width;
    private int height;
    private int codeLength;
    
    public ValidCode() {
        this.width = 35;
        this.height = 30;
        this.codeLength = 4;
        this.width = this.codeLength * 16 + (this.codeLength - 1) * 10;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setSize(this.width, this.height);
        this.addMouseListener(this);
        this.setToolTipText("点击可以更换验证码");
    }
    
    public int getCodeLength() {
        return this.codeLength;
    }
    
    public void setCodeLength(int codeLength) {
        if (codeLength < 4) {
            this.codeLength = 4;
        }
        else {
            this.codeLength = codeLength;
        }
    }
    
    public String getCode() {
        return this.code;
    }
    
    public Color getRandColor(int min, int max) {
        if (min > 255) {
            min = 255;
        }
        if (max > 255) {
            max = 255;
        }
        int red = Util.random.nextInt(max - min) + min;
        int green = Util.random.nextInt(max - min) + min;
        int blue = Util.random.nextInt(max - min) + min;
        return new Color(red, green, blue);
    }
    
    protected String generateCode() {
        char[] codes = new char[this.codeLength];
        for (int i = 0, len = codes.length; i < len; ++i) {
            if (Util.random.nextBoolean()) {
                codes[i] = (char)(Util.random.nextInt(26) + 65);
            }
            else {
                codes[i] = (char)(Util.random.nextInt(26) + 97);
            }
        }
        return this.code = new String(codes);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.code == null || this.code.length() != this.codeLength) {
            this.code = this.generateCode();
        }
        super.setSize(this.width = this.codeLength * 16 + (this.codeLength - 1) * 10, this.height);
        super.setPreferredSize(new Dimension(this.width, this.height));
        Font mFont = new Font("Arial", 3, 23);
        g.setFont(mFont);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.getRandColor(200, 250));
        g2d.fillRect(0, 0, this.width, this.height);
        g2d.setColor(this.getRandColor(180, 200));
        g2d.drawRect(0, 0, this.width - 1, this.height - 1);
        for (int i = 0, len = 150; i < len; ++i) {
            int x = Util.random.nextInt(this.width - 1);
            int y = Util.random.nextInt(this.height - 1);
            int x2 = Util.random.nextInt(this.width - 10) + 10;
            int y2 = Util.random.nextInt(this.height - 4) + 4;
            g2d.setColor(Color.CYAN);
            g2d.drawLine(x, y, x2, y2);
        }
        int i = 0;
        int len = this.codeLength;
        FontMetrics fm = g2d.getFontMetrics();
        int base = (this.height - fm.getHeight()) / 2 + fm.getAscent();
        while (i < len) {
            int b = Util.random.nextBoolean() ? 1 : -1;
            g2d.rotate((double)Util.random.nextInt(10) * 0.01 * (double)b);
            g2d.setColor(this.getRandColor(20, 130));
            g2d.drawString(this.code.charAt(i) + "", 16 * i + 10, base);
            ++i;
        }
    }
    
    public void nextCode() {
        this.generateCode();
        this.repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.nextCode();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
