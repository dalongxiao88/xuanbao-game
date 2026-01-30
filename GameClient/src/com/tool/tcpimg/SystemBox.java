package com.tool.tcpimg;

import java.util.ArrayList;
import com.tool.tcp.Frame;
import com.tool.image.ImageMixDeal;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import org.come.until.ScrenceUntil;
import java.awt.Graphics;
import com.tool.tcp.Sprite;
import java.util.List;

public class SystemBox
{
    private RichLabel text;
    private int length;
    private int value;
    private int height;
    private List line;
    private Sprite tcp;
    private String skin;
    
    public SystemBox(String text, String skin) {
        this.skin = skin;
        this.CZ(text);
    }
    
    public void draw(Graphics g) {
        if (this.text == null) {
            return;
        }
        ++this.value;
        if (this.value < this.length) {
            Graphics g2 = g.create(0, ScrenceUntil.Screen_y - 133, ScrenceUntil.Screen_x, 60);
            if (this.tcp == null) {
                this.tcp = SpriteFactory.Prepare(GetTcpPath.getMouseTcp(this.skin));
            }
            else {
                this.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                Frame frame = this.tcp.getCurrFrame();
                if (frame != null && frame.getImage() != null) {
                    g2.drawImage(frame.getImage(), 2 - frame.getRefPixelX(), 2 - frame.getRefPixelY(), ScrenceUntil.Screen_x, frame.getHeight(), null);
                }
            }
            g2.translate(ScrenceUntil.Screen_x - this.value, this.height);
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
            this.tcp = null;
            this.text = null;
            return;
        }
        if (this.text == null) {
            this.text = new RichLabel(text, UIUtils.TEXT_NAME_FONT, 9999);
        }
        else {
            this.text.setTextSize(text, 9999);
        }
        this.length = ScrenceUntil.Screen_x + this.text.getWidth();
        this.value = 0;
        this.height = (int)(38.0 - this.text.getSize().getHeight());
    }
}
