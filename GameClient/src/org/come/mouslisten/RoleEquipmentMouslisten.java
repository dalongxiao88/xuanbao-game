package org.come.mouslisten;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.ImageIcon;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.AccessSuitMsgUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.SynthesisJpanel;
import java.awt.event.MouseListener;

public class RoleEquipmentMouslisten implements MouseListener
{
    private int index;
    private SynthesisJpanel synthesisJpanel;
    
    public RoleEquipmentMouslisten(int index, SynthesisJpanel synthesisJpanel) {
        this.index = index;
        this.synthesisJpanel = synthesisJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            List<Goodstable> listroleequi = AccessSuitMsgUntil.accessIdlEqu(1);
            if (listroleequi == null) {
                return;
            }
            if (this.index + 1 + SynthesisJpanel.page * 18 > listroleequi.size()) {
                return;
            }
            Goodstable goodstable = (Goodstable)listroleequi.get(this.index + SynthesisJpanel.page * 18);
            if (goodstable == null) {
                return;
            }
            if (goodstable.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("装备 " + goodstable.getGoodsname() + " 处于加锁状态");
                return;
            }
            SynthesisJpanel.getGoodstableBean().setGoodstable(goodstable);
            SynthesisJpanel.getLabzb().setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(50, 50, 10)));
            BigDecimal big = AccessSuitMsgUntil.returnMoney(SynthesisJpanel.getGoodstableBean(), 1);
            if (big != null) {
                SynthesisJpanel.setMoney(big);
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        List<Goodstable> listroleequi = AccessSuitMsgUntil.accessIdlEqu(1);
        if (this.index + 1 + SynthesisJpanel.page * 18 <= listroleequi.size() && listroleequi.get(this.index + SynthesisJpanel.page * 18) != null) {
            Goodstable goodstable = (Goodstable)listroleequi.get(this.index + SynthesisJpanel.page * 18);
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
