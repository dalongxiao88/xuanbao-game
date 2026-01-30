package com.tool.tcpimg;

import org.come.until.CutButtonImage;
import org.come.control.TestMain;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.ScrenceUntil;

import java.awt.*;

import org.come.Jpanel.TestSetupJpanel;
import org.come.Frame.TestSetupJframe;
import com.updateNew.MyIsif;

import java.util.ArrayList;
import org.come.bean.ImgZoom;
import java.util.List;
import javax.swing.JPanel;

public class ChatBox extends JPanel
{
    private static final long serialVersionUID = 1L;
    private List<RichLabel> labels;
    private int deviation;
    private boolean Gag;
    private float Alpha;
    private boolean display;
    private int w;
    private int h;
    public static ImgZoom imgZoomS;
    public static ImgZoom imgZoomSx;
    public static ImgZoom imgZoomS1;
    public static ImgZoom imgZoomSx1;
    static boolean flag;
    private int maxSize;
    
    public ChatBox() {
        this.deviation = 0;
        this.Gag = true;
        this.Alpha = 0.0f;
        this.display = false;
        this.w = 0;
        this.h = 0;
        this.maxSize = 1000;
        this.labels = new ArrayList<>();
        this.setBackground(UIUtils.Color_BACK);
    }
    
    public InputBean isMonitor(int x, int y) {
        int History = 0;
        int hig = -this.deviation * 22;
        for (int i = 0; i < this.labels.size(); ++i) {
            RichLabel c = (RichLabel)this.labels.get(i);
            History = c.getHeight();
            hig += History;
            if (hig >= 0 && hig >= y) {
                return c.isMonitor(x, y - (hig - History));
            }
        }
        return null;
    }
    public void paintSSS11(Graphics g) {
        if (this.labels.size() != 0) {
            final Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite((Composite) AlphaComposite.getInstance(3, 1.0f));
            g2d.setColor(Color.BLACK);
            ChatBox.imgZoomSx.draw((Graphics) g2d);
            ChatBox.imgZoomSx.setMiddlew(this.getWidth() - 3 * ChatBox.imgZoomSx.getEdgew());
            ChatBox.imgZoomSx.setMiddleh(this.getHeight() - 3 * ChatBox.imgZoomSx.getEdgeh());
            int History = 0;
            int hig = 1 - this.deviation * 22;
            g2d.translate(10, hig);
            int i = 0;
            while (i < this.labels.size()) {
                final Component c = (RichLabel) this.labels.get(i);
                g2d.translate(c.getX() , History -2);
                History = c.getHeight();
                hig += History;
                if (hig >= 0) {
                    c.paint((Graphics) g2d);
                    c.paint((Graphics) g2d);
                    c.paint((Graphics) g2d);
                    c.paint((Graphics) g2d);
                }
                ++i;
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        if (this.labels.size() != 0) {
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setComposite(AlphaComposite.getInstance(3, this.Alpha));
            g2d.setColor(Color.BLACK);
            if (this.h == 0) {
                g2d.fillRoundRect(1, 1, this.getWidth() + 5, this.getHeight(), 0, 0);
                g2d.fillRoundRect(0, 0, this.getWidth() + 5, this.getHeight(), 0, 0);
            }
            else {
                g2d.fillRoundRect(1, 1, this.getWidth() + 5, this.h, 0, 0);
                g2d.fillRoundRect(0, 0, this.getWidth() + 5, this.h, 0, 0);
            }
            g2d.dispose();
            int History = 0;
            int hig = -this.deviation * 22;
            g.translate(5, hig);
            for (int i = 0; i < this.labels.size(); ++i) {
                Component c = (RichLabel)this.labels.get(i);
                g.translate(c.getX(), History);
                History = c.getHeight();
                hig += History;
                if (hig >= 0) {
                    c.paint(g);
                }
            }
        }
    }
    
    public void paintSSS1(Graphics g) {
        if (this.labels.size() != 0) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setComposite(AlphaComposite.getInstance(3, 1.0f));
            g2d.setColor(Color.BLACK);
            if (MyIsif.getStyle().equals("水墨")) {
                ChatBox.imgZoomSx.draw3(g2d);
                ChatBox.imgZoomSx.setMiddlew(this.getWidth() - 3 * ChatBox.imgZoomSx.getEdgew());
                ChatBox.imgZoomSx.setMiddleh(this.getHeight() - 3 * ChatBox.imgZoomSx.getEdgeh());
            }
            else {
                ChatBox.imgZoomSx1.draw3(g2d);
                ChatBox.imgZoomSx1.setMiddlew(this.getWidth() - 3 * ChatBox.imgZoomSx1.getEdgew());
                ChatBox.imgZoomSx1.setMiddleh(this.getHeight() - 3 * ChatBox.imgZoomSx1.getEdgeh());
            }
            int History = 0;
            int hig = 1 - this.deviation * 22;
            g2d.translate(8, hig);
            for (int i = 0; i < this.labels.size(); ++i) {
                Component c = (RichLabel)this.labels.get(i);
                g2d.translate(c.getX() - 5, History - 3);
                History = c.getHeight();
                hig += History;
                if (hig >= 0) {
                    c.paint(g2d);
                    c.paint(g2d);
                    c.paint(g2d);
                    c.paint(g2d);
                }
            }
        }
    }
    
    public void paintSSS(Graphics g) {
        if (this.labels.size() != 0) {
            Graphics2D g2d = (Graphics2D)g;
            TestSetupJpanel ttt = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
            g2d.setComposite(AlphaComposite.getInstance(3, 1.0f));
            g2d.translate(0, -1);
            g2d.setColor(Color.BLACK);
            if (MyIsif.getStyle().equals("水墨")) {
                ChatBox.imgZoomS.draw(g2d);
                ChatBox.imgZoomS.setMiddlew(this.getWidth() - 2 * ChatBox.imgZoomS.getEdgew());
                ChatBox.imgZoomS.setMiddleh(this.getHeight() - 2 * ChatBox.imgZoomS.getEdgeh());
            }
            else {
                ChatBox.imgZoomS1.draw(g2d);
                ChatBox.imgZoomS1.setMiddlew(this.getWidth() - 2 * ChatBox.imgZoomS1.getEdgew());
                ChatBox.imgZoomS1.setMiddleh(this.getHeight() - 2 * ChatBox.imgZoomS1.getEdgeh());
            }
            int History = 0;
            int hig = 1 - this.deviation * 22;
            g2d.translate(2, hig);
            for (int i = 0; i < this.labels.size(); ++i) {
                Component c = (RichLabel)this.labels.get(i);
                g2d.translate(c.getX(), History);
                History = c.getHeight();
                hig += History;
                if (hig >= 0) {
                    c.paint(g2d);
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
        if (this.ScreenFull() != 0 && ChatBox.flag) {
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
        this.update();
    }
    
    public void addtext(String text, int size) {
        boolean is = this.deviation >= this.ScreenFull();
        this.addText(text, size, UIUtils.TEXT_FONT1);
        if (is) {
            this.update();
        }
        while (this.labels.size() > this.maxSize) {
            this.labels.remove(0);
        }
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
        Dimension d = label.computeSize(size);
        label.setSize(d);
        if (label != null) {
            this.labels.add(label);
        }
        this.Formsize(size);
        return label;
    }
    public void addText(String chatText, int size,String bang) {
        RichLabel label = new RichLabel(chatText, UIUtils.TEXT_FONT1,bang);
        Dimension d = label.computeSize(size);
        label.setSize(d);
        if (label != null) {
            this.labels.add(label);
        }
        add(label);
        Formsize(size);
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
            case 8: {
                ChatBox.flag = !ChatBox.flag;
                break;
            }
        }
    }
    
    public void update() {
        if (this.ScreenFull() != 0 && ChatBox.flag) {
            while (this.deviation < this.ScreenFull()) {
                ++this.deviation;
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
    
    static {
        ChatBox.flag = true;
        ChatBox.imgZoomS = CutButtonImage.cuts("inkImg/tupiankuang/Z1007.png", 14, 7, true);
        ChatBox.imgZoomSx = CutButtonImage.cuts("inkImg/tupiankuang/img_14.png", 6, 6, true);
        ChatBox.imgZoomS1 = CutButtonImage.cuts("inkImg/tupiankuang/S145-2.png", 14, 7, true);
        ChatBox.imgZoomSx1 = CutButtonImage.cuts("inkImg/tupiankuang/img_14.png", 6, 6, true);
    }
}
