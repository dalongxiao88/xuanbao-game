package com.tool.tcpimg;

import org.come.control.TestMain;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.ScrenceUntil;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import com.updateNew.MyIsif;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import org.come.until.CutButtonImage;
import org.come.bean.ImgZoom;
import javax.swing.ImageIcon;
import java.util.List;
import javax.swing.JPanel;

public class WlinBox extends JPanel
{
    private static final long serialVersionUID = 1L;
    private List<RichLabel> labels;
    private int deviation;
    private boolean Gag;
    private float Alpha;
    private boolean display;
    private int w;
    private int h;
    public static ImageIcon icon;
    private ImgZoom imgZoomS;
    private ImgZoom imgZoomSx;
    
    public InputBean isMonitor(int x, int y) {
        int Historys = 0;
        int higs = -this.deviation * 22;
        for (int i = 0; i < this.labels.size(); ++i) {
            RichLabel c = (RichLabel)this.labels.get(i);
            Historys = c.getHeight();
            higs += Historys;
            if (higs >= 0 && higs >= y) {
                return c.isMonitor(x, y - (higs - Historys));
            }
        }
        return null;
    }
    
    public WlinBox() {
        this.deviation = 0;
        this.Gag = true;
        this.Alpha = 0.0f;
        this.display = false;
        this.w = 0;
        this.h = 0;
        this.deviation = 0;
        this.Gag = true;
        this.Alpha = 0.0f;
        this.display = false;
        this.w = 0;
        this.h = 0;
        this.imgZoomS = CutButtonImage.cuts("inkImg/tupiankuang/Z1007.png", 14, 7, true);
        this.imgZoomSx = CutButtonImage.cuts("inkImg/tupiankuang/S145-2.png", 6, 6, true);
        this.labels = new ArrayList<>();
        this.setBackground(UIUtils.Color_BACK);
    }
    
    @Override
    public void paint(Graphics g) {
        if (this.labels.size() != 0) {
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setComposite(AlphaComposite.getInstance(3, 1.0f));
            g2d.translate(0, 1);
            g2d.setColor(Color.BLACK);
            if (MyIsif.getStyle().equals("水墨")) {
                this.imgZoomS.draw(g2d);
                this.imgZoomS.setMiddlew(this.getWidth() - 2 * this.imgZoomS.getEdgew());
                this.imgZoomS.setMiddleh(this.getHeight() - 2 * this.imgZoomS.getEdgeh());
            }
            else {
                this.imgZoomSx.draw(g2d);
                this.imgZoomSx.setMiddlew(this.getWidth() - 2 * this.imgZoomSx.getEdgew());
                this.imgZoomSx.setMiddleh(this.getHeight() - 2 * this.imgZoomSx.getEdgeh());
            }
            int History = 0;
            int hig = -this.deviation * 22;
            g2d.translate(75, hig);
            for (int i = 0; i < this.labels.size(); ++i) {
                Component c = (RichLabel)this.labels.get(i);
                g2d.translate(c.getX() - 5, History + 1);
                History = c.getHeight();
                hig += History;
                if (hig >= 0) {
                    c.paint(g2d);
                    c.paint(g2d);
                    c.paint(g2d);
                }
            }
        }
    }
    
    public void addtext(RichLabel label, int size) {
        if (this.labels.size() > 100) {
            this.AddAndSubtract(2);
        }
        if (label != null) {
            this.labels.add(label);
        }
        this.Formsize(size);
        if (this.ScreenFull() != 0) {
            while (this.deviation < this.ScreenFull()) {
                ++this.deviation;
            }
        }
    }
    
    public void addtext(String text, int size, Font font) {
        if (this.labels.size() > 100) {
            this.AddAndSubtract(2);
        }
        this.addText(text, size, font);
        if (this.ScreenFull() != 0) {
            while (this.deviation < this.ScreenFull()) {
                ++this.deviation;
            }
        }
    }
    
    public void addTexts(String chatTexts, int sizes) {
        RichLabel labels = new RichLabel(chatTexts, null);
        Dimension d = labels.computeSize(sizes);
        labels.setSize(d);
        if (labels != null) {
            this.labels.add(labels);
        }
        this.add(labels);
        this.Formsize(sizes);
    }
    
    public void addText(String chatText, int size) {
        RichLabel label = new RichLabel(chatText, null);
        Dimension d = label.computeSize(size);
        label.setSize(d);
        if (label != null) {
            this.labels.add(label);
        }
        this.add(label);
        this.Formsize(size);
    }
    
    public RichLabel addText(String chatText, int size, Font font) {
        RichLabel label = new RichLabel(chatText, font);
        Dimension d = label.computeSize(size - 75);
        label.setSize(d);
        if (label != null) {
            this.labels.add(label);
        }
        this.Formsize(size);
        return label;
    }
    
    public RichLabel removeAddText(String chatText, int size, Font font) {
        this.removeAll();
        RichLabel label = (this.labels.size() != 0) ? ((RichLabel)this.labels.get(0)) : null;
        this.labels.clear();
        if (label == null) {
            label = new RichLabel(chatText, font);
            Dimension d = label.computeSize(size);
            label.setSize(d);
        }
        else {
            label.setFont(font);
            label.setTextSize(chatText, size);
        }
        this.labels.add(label);
        this.Formsize(size);
        return label;
    }
    
    public void Formsize(int size) {
        int h = 6;
        for (int i = 0; i < this.labels.size(); ++i) {
            h = (int)((double)h + ((RichLabel)this.labels.get(i)).getSize().getHeight());
        }
        this.setSize(size, h);
    }
    
    public void AddAndSubtract(int type) {
        switch (type) {
            case 0: {
                if (this.ScreenFull() != 0 && this.deviation > 0) {
                    --this.deviation;
                    break;
                }
                else {
                    break;
                }
            }
            case 1: {
                if (this.ScreenFull() != 0 && this.deviation < this.ScreenFull()) {
                    ++this.deviation;
                    break;
                }
                else {
                    break;
                }
            }
            case 2: {
                this.removemsg();
                this.deviation = 0;
                this.Formsize(280);
                break;
            }
            case 3: {
                this.Gag = !this.Gag;
                break;
            }
            case 4: {
                this.RemoveForms();
                break;
            }
            case 5: {
                if (this.h < ScrenceUntil.Screen_y - 300) {
                    this.h += 20;
                    break;
                }
                else {
                    break;
                }
            }
            case 6: {
                if (this.h > 80) {
                    this.h -= 20;
                    break;
                }
                else {
                    break;
                }
            }
            case 7: {
                if (this.display) {
                    this.Alpha -= 0.2f;
                    if (this.Alpha <= 0.0f) {
                        this.Alpha = 0.6f;
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
        }
    }
    
    public int ScreenFull() {
        int size = 0;
        for (int i = 0; i < this.labels.size(); ++i) {
            size = (int)((double)size + ((RichLabel)this.labels.get(i)).getSize().getHeight());
        }
        if (size - 22 >= this.h) {
            return (size - this.h) / 22 + 2;
        }
        return 0;
    }
    
    public void RemoveForms() {
        if (!this.display) {
            this.Alpha = 0.6f;
            this.w = 300;
            this.h = 200;
            FrameMessageChangeJpanel.chatbox1.removemsg();
            FrameMessageChangeJpanel.chatbox2.removemsg();
            ZhuFrame.getzhuframe().getChangeJpanel().setVisible(false);
            TestMain.gameJframe.setSize(ScrenceUntil.Screen_x + 6, ScrenceUntil.Screen_y + 28);
        }
        else {
            FrameMessageChangeJpanel.chatbox.removemsg();
            this.Alpha = 0.0f;
            this.w = 300;
            this.h = ScrenceUntil.Screen_y / 2 - 20;
            ZhuFrame.getzhuframe().getChangeJpanel().setVisible(true);
            TestMain.gameJframe.setSize(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X + 6, ScrenceUntil.Screen_y + 28);
        }
        this.display = !this.display;
    }
    
    public void removemsg() {
        this.removeAll();
        this.labels.clear();
    }
    
    public void removemsgs() {
        this.removeAll();
        this.labels.clear();
    }
    
    public List<RichLabel> getLabels() {
        return this.labels;
    }
    
    public void setLabels(List<RichLabel> labels) {
        this.labels = labels;
    }
    
    public int getDeviation() {
        return this.deviation;
    }
    
    public void setDeviation(int deviation) {
        this.deviation = deviation;
    }
    
    public boolean isGag() {
        return this.Gag;
    }
    
    public void setGag(boolean gag) {
        this.Gag = gag;
    }
    
    public float getAlpha() {
        return this.Alpha;
    }
    
    public void setAlpha(float alpha) {
        this.Alpha = alpha;
    }
    
    public boolean isDisplay() {
        return this.display;
    }
    
    public void setDisplay(boolean display) {
        this.display = display;
    }
    
    public int getW() {
        return this.w;
    }
    
    public void setW(int w) {
        this.w = w;
    }
    
    public int getH() {
        return this.h;
    }
    
    public void setH(int h) {
        this.h = h;
    }
}
