package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import java.util.List;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import org.come.until.AccessSuitMsgUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.TransferJpanel;
import java.awt.event.MouseListener;

public class TransferEquiMouslisten implements MouseListener
{
    private int index;
    private TransferJpanel transferJpanel;
    private int type;
    
    public TransferEquiMouslisten(int index, TransferJpanel transferJpanel, int type) {
        this.index = index;
        this.transferJpanel = transferJpanel;
        this.type = type;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        List<Goodstable> goodstables = AccessSuitMsgUntil.accessIdlEqu(this.type);
        if (goodstables == null) {
            return;
        }
        Goodstable goodstable = null;
        if (e.getButton() == 3) {
            if (this.type == 1) {
                if (this.index + 1 + TransferJpanel.page2 * 9 > goodstables.size()) {
                    return;
                }
                if (goodstables.get(this.index + TransferJpanel.page2 * 9) != null) {
                    goodstable = (Goodstable)goodstables.get(this.index + TransferJpanel.page2 * 9);
                }
                if (goodstable == null) {
                    return;
                }
                TransferJpanel.getLabzb().setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(50, 50, 10)));
                TransferJpanel.setGoodstable(goodstable);
                if (TransferJpanel.getGoodstableBean().getGoodstable() != null) {
                    TransferJpanel.setMoney(new BigDecimal(100000000));
                    TransferJpanel.setValue(new BigDecimal(AccessSuitMsgUntil.getSxlxz(TransferJpanel.getGoodstableBean().getGoodstable().getValue())));
                    TransferJpanel.getWorkshopBtn().setText("转移");
                }
            }
            else if (this.type == 2) {
                if (this.index + 1 + TransferJpanel.page * 9 > goodstables.size()) {
                    return;
                }
                if (goodstables.get(this.index + TransferJpanel.page * 9) != null) {
                    goodstable = (Goodstable)goodstables.get(this.index + TransferJpanel.page * 9);
                }
                TransferJpanel.getLabtz().setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(50, 50, 10)));
                TransferJpanel.getGoodstableBean().setGoodstable(goodstable);
                if (TransferJpanel.getGoodstable() != null) {
                    TransferJpanel.setMoney(new BigDecimal(100000000));
                    TransferJpanel.setValue(new BigDecimal(AccessSuitMsgUntil.getSxlxz(goodstable.getValue())));
                    TransferJpanel.getWorkshopBtn().setText("转移");
                }
                else {
                    TransferJpanel.setMoney(new BigDecimal(1000000));
                    TransferJpanel.setValue(null);
                    TransferJpanel.getWorkshopBtn().setText("拆解");
                }
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
        List<Goodstable> listroleequi = AccessSuitMsgUntil.accessIdlEqu(this.type);
        Goodstable goodstable = null;
        if (this.type == 1) {
            if (this.index + 1 + TransferJpanel.page2 * 9 > listroleequi.size()) {
                return;
            }
            if (listroleequi.get(this.index + TransferJpanel.page2 * 9) != null) {
                goodstable = (Goodstable)listroleequi.get(this.index + TransferJpanel.page2 * 9);
            }
        }
        else if (this.type == 2) {
            if (this.index + 1 + TransferJpanel.page * 9 > listroleequi.size()) {
                return;
            }
            if (listroleequi.get(this.index + TransferJpanel.page * 9) != null) {
                goodstable = (Goodstable)listroleequi.get(this.index + TransferJpanel.page * 9);
            }
        }
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
