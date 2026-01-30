package org.come.starcard;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerStarTransferMain implements MouseListener
{
    private int i;
    private JpanelStarTransferMain jpanelStarTransferMain;
    
    public MouseListenerStarTransferMain(int i, JpanelStarTransferMain jpanelStarTransferMain) {
        this.i = i;
        this.jpanelStarTransferMain = jpanelStarTransferMain;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getStarArray(this.i);
        if (goodstable != null) {
            if (this.jpanelStarTransferMain.getChooseOneId() != null && goodstable.getRgid().compareTo(this.jpanelStarTransferMain.getChooseOneId()) != 0) {
                String[] split = goodstable.getValue().split("\\|")[3].split("&");
                if (split.length >= 5) {
                    String[] split2 = split[4].split("=");
                    if (BtnStarCard.isfiveElements(split2[1])) {
                        this.jpanelStarTransferMain.showStarCardAttribute(1, split[4].split("="), goodstable);
                        return;
                    }
                }
                ZhuFrame.getZhuJpanel().addPrompt2("该星卡没有四神兽星阵");
                return;
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("不能选择主卡");
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getStarArray(this.i);
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
