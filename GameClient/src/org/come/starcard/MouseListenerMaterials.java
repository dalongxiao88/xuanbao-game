package org.come.starcard;

import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MouseListenerMaterials extends MouseAdapter
{
    private int i;
    private JpanelStarCardMain jpanelStarCardMain;
    
    public MouseListenerMaterials(int i, JpanelStarCardMain jpanelStarCardMain) {
        this.i = i;
        this.jpanelStarCardMain = jpanelStarCardMain;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Goodstable goodstable = this.getGoodstable();
        if (goodstable != null) {
            if (this.jpanelStarCardMain.getSmallType() == 2) {
                int materialsLvl = Integer.parseInt(goodstable.getValue().split("=")[1]);
                if (materialsLvl != 11) {
                    ZhuFrame.getZhuJpanel().addPrompt2("物品不是11级矿石");
                    return;
                }
            }
            this.jpanelStarCardMain.chooseMaterialsImg(goodstable);
            if (this.jpanelStarCardMain.getSmallType() == 4) {
                this.jpanelStarCardMain.viewChange(goodstable);
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = this.getGoodstable();
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public Goodstable getGoodstable() {
        int smallType = this.jpanelStarCardMain.getSmallType();
        int type = 0;
        if (smallType == 1) {
            type = 522;
        }
        else if (smallType == 2) {
            type = 500;
        }
        else if (smallType == 3) {
            int radioType = this.jpanelStarCardMain.getRadioType();
            if (radioType == 1) {
                type = 524;
            }
            else if (radioType == 2) {
                type = 523;
            }
        }
        else if (smallType == 4) {
            type = 521;
        }
        return GoodsListFromServerUntil.getStarCardCultivateMaterialGoods((long)type, this.i);
    }
}
