package org.come.mouslisten;

import java.math.BigDecimal;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.until.Util;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import org.lottery.panel.LotteryIntegralGoodsJpanel;
import org.come.Jpanel.PetsMsgJpanel;
import org.come.Jpanel.PetEquipmentJpanel;
import java.awt.event.MouseListener;

public class PetEquipMouslisten implements MouseListener
{
    private PetEquipmentJpanel jpanel;
    private PetsMsgJpanel jpanel1;
    private LotteryIntegralGoodsJpanel jpanel2;
    private int type;
    
    public PetEquipMouslisten(PetEquipmentJpanel jpanel, int type) {
        this.jpanel = jpanel;
        this.type = type;
    }
    
    public PetEquipMouslisten(PetsMsgJpanel jpanel1, int type) {
        this.jpanel1 = jpanel1;
        this.type = type;
    }
    
    public PetEquipMouslisten(LotteryIntegralGoodsJpanel jpanel2) {
        this.jpanel2 = jpanel2;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (pet != null) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            this.jpanel.Equip(pet, null, this.type);
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
        RoleSummoning roleSummoning = UserMessUntil.getChosePetMes();
        Goodstable goodstable = null;
        if (roleSummoning != null) {
            BigDecimal goodsId = UserMessUntil.getChosePetMes().getGoodId(this.type);
            goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(goodsId);
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
