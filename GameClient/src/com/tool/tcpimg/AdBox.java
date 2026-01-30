package com.tool.tcpimg;

import java.util.ArrayList;
import org.come.until.ScrenceUntil;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.List;

public class AdBox
{
    private RichLabel text;
    private int length;
    private int value;
    private List line;
    private Image image;
    
    public AdBox(String text) {
        this.image = new ImageIcon("inkImg/button/B31.png").getImage();
        this.CZ(text);
    }
    
    public void draw(Graphics g) {
        if (this.text == null) {
            return;
        }
        ++this.value;
        if (this.value < this.length) {
            Graphics g2 = g.create(0, ScrenceUntil.Screen_y - 75, ScrenceUntil.Screen_x, 24);
            g2.drawImage(this.image, 0, 0, ScrenceUntil.Screen_x, 24, null);
            g2.translate(ScrenceUntil.Screen_x - this.value, 0);
            this.text.paint(g2);
            g2.dispose();
        }
        else {
            this.CZ(null);
        }
    }
    
    public void addText(String text) {
        if (this.text == null) {
            this.CZ(text);
        }
        else {
            if (this.line == null) {
                this.line = new ArrayList<>();
            }
            this.line.add(text);
        }
    }
    
    public void CZ(String text) {
        if (text == null) {
            if (this.line != null && this.line.size() != 0) {
                text = (String)this.line.remove(0);
            }
        }
        else if (this.line != null) {
            this.line.remove(text);
        }
        if (text == null) {
            this.text = null;
            return;
        }
        if (this.text == null) {
            this.text = new RichLabel(text, UIUtils.TEXT_NAME_FONT, 9999);
        }
        else {
            this.text.setText(text);
        }
        this.length = ScrenceUntil.Screen_x + this.text.getWidth();
        this.value = 0;
    }
    
    public RichLabel getText() {
        return this.text;
    }
}
