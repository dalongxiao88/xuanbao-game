package org.come.mouslisten;

import javax.swing.JTextField;
import org.come.entity.Goodstable;
import org.come.until.MessagrFlagUntil;
import come.tool.Fighting.FightingMixDeal;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import com.tool.Document.RichDocument;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TakeOffEquipmentMouslisten implements MouseListener
{
    private int goodPlace;
    
    public TakeOffEquipmentMouslisten(int goodPlace) {
        this.goodPlace = goodPlace;
    }
    
    public TakeOffEquipmentMouslisten() {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (!Util.isCanBuyOrno()) {
            if (e.isShiftDown() && e.getButton() == 1) {
                Goodstable goodstable = GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace];
                if (goodstable != null) {
                    try {
                        JTextField SendMes = ZhuFrame.getZhuJpanel().getSendMes();
                        ((RichDocument)SendMes.getDocument()).insertRich(SendMes.getCaretPosition(), 2, goodstable.getRgid(), "[" + goodstable.getGoodsname() + "]", "G", (AttributeSet)null);
                    }
                    catch (BadLocationException var4) {
                        var4.printStackTrace();
                    }
                }
            }
            else {
                if (e.getButton() == 3 && FightingMixDeal.State == 0) {
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10) || MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11)) {
                        return;
                    }
                    if (GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace] != null) {
                        try {
                            GoodsListFromServerUntil.mutualChange(this.goodPlace, -1);
                        }
                        catch (Exception var5) {
                            var5.printStackTrace();
                        }
                    }
                }
                if (e.getButton() == 1 && FightingMixDeal.State == 0 && GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace] != null) {
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10)) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                        if (GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace].getGoodlock() == 1) {
                            ZhuFrame.getZhuJpanel().addPrompt("该物品已加锁");
                            return;
                        }
                        GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace].setGoodlock(1);
                        GoodsMouslisten.gooduse(GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace], 0);
                        ZhuFrame.getZhuJpanel().addPrompt("加锁成功");
                    }
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11) && GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace].getGoodlock() == 1) {
                        GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace].setGoodlock(0);
                        GoodsMouslisten.gooduse(GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace], 0);
                        ZhuFrame.getZhuJpanel().addPrompt("解锁成功");
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    }
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getChoseGoodsList()[this.goodPlace];
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public void takeOffHelmet() {
    }
    
    public void takeOffNecklaces() {
    }
    
    public void takeOffHCloths() {
    }
    
    public void takeOffamulet() {
    }
    
    public void takeOffweapons() {
    }
    
    public void takeOffShooes() {
    }
    
    public void takeOffmasks() {
    }
    
    public void takeOffRing() {
    }
    
    public void takeOffBelts() {
    }
    
    public void takeOffCapes() {
    }
    
    public void takeOffPendant() {
    }
}
