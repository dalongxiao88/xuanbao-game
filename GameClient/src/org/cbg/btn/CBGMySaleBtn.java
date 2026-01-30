package org.cbg.btn;

import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.cbg.panel.TraslationWantSentYijishangpinModelJpanel;
import org.cbg.panel.TraslationWantSentYijishangpinJpanel;
import com.tool.btn.MoBanBtn;

public class CBGMySaleBtn extends MoBanBtn
{
    private int leixing;
    private TraslationWantSentYijishangpinJpanel jpanel1;
    private TraslationWantSentYijishangpinModelJpanel jpanel2;
    
    public CBGMySaleBtn(String iconpath, int type, int leixing, TraslationWantSentYijishangpinJpanel jpanel) {
        super(iconpath, type);
        this.leixing = leixing;
        this.jpanel1 = jpanel;
    }
    
    public CBGMySaleBtn(String iconpath, int type, Color[] colors, Font font, int leixing, String text, TraslationWantSentYijishangpinJpanel jpanel) {
        super(iconpath, type, colors);
        this.leixing = leixing;
        this.jpanel1 = jpanel;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public CBGMySaleBtn(String iconpath, int type, Color[] colors, Font font, int leixing, String text, TraslationWantSentYijishangpinModelJpanel jpanel) {
        super(iconpath, type, colors);
        this.leixing = leixing;
        this.jpanel2 = jpanel;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.leixing == 0) {
            this.jpanel1.xl();
        }
        else if (this.leixing >= 1 && this.leixing <= 4) {
            String value = this.jpanel1.YSqh(this.leixing);
            if (value != null) {
                ZhuFrame.getZhuJpanel().addPrompt2(value);
            }
        }
        else if (this.leixing == 5) {
            this.jpanel2.caozuo(this.getName());
        }
    }
}
