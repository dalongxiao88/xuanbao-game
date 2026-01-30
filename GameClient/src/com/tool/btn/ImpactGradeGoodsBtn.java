package com.tool.btn;

import org.come.Frame.ImpactGradeJframe;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.ImpactGradeGoodsJpanel;

public class ImpactGradeGoodsBtn extends MoBanBtn
{
    private int caozuo;
    private ImpactGradeGoodsJpanel impactGradeGoodsJpanel;
    private int i;
    
    public ImpactGradeGoodsBtn(String iconpath, int type, int caozuo, String text, ImpactGradeGoodsJpanel impactGradeGoodsJpanel) {
        super(iconpath, type);
        this.i = 0;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.yellow);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.impactGradeGoodsJpanel = impactGradeGoodsJpanel;
        this.caozuo = caozuo;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getTypeMenu() == 4) {
            this.impactGradeGoodsJpanel.refreshItem(this.caozuo);
        }
        else {
            this.impactGradeGoodsJpanel.refreshItem1(this.caozuo);
        }
    }
    
    public int getI() {
        return this.i;
    }
    
    public void setI(int i) {
        this.i = i;
    }
}
