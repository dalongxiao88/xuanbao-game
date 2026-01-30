package org.come.equipmentSwitching;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TakeOffEquipmentSwitchMouslisten implements MouseListener
{
    private int goodPlace;
    
    public TakeOffEquipmentSwitchMouslisten(int goodPlace) {
        this.goodPlace = goodPlace;
    }
    
    public TakeOffEquipmentSwitchMouslisten() {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().getGoodstables()[this.goodPlace];
        if (goodstable != null) {
            if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
            }
            EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().PaintingTextx(this.goodPlace);
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        }
        EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().ClearTextx(this.goodPlace);
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
