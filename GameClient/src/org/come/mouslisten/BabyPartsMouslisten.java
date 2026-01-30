package org.come.mouslisten;

import org.come.entity.Baby;
import org.come.good.BabyGood;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.Frame.ZhuFrame;
import org.come.until.UserMessUntil;
import org.come.Frame.TestChildJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BabyPartsMouslisten implements MouseListener
{
    private int type;
    
    public BabyPartsMouslisten(int type) {
        this.type = type;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Baby baby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
        if (baby == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的孩子");
        }
        else {
            BigDecimal id = baby.ChangePart(new BigDecimal(-1), this.type);
            Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(id);
            if (good != null) {
                if (TestChildJframe.getTestChildJframe().getTestChildJpanel().ChangeParts(baby, good, (Goodstable)null, this.type)) {
                    BabyGood.UpdaBaby(baby);
                }
                else {
                    baby.ChangePart(good.getRgid(), this.type);
                    ZhuFrame.getZhuJpanel().addPrompt2("背包已满");
                }
            }
        }
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
