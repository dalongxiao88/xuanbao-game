package com.tool.btn;

import org.come.summonequip.JframeSummonEquipMain;
import org.come.until.FormsManagement;
import org.come.until.Util;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;

public class PetEquipmentBtn extends MoBanBtn
{
    private int caozuo;
    
    public PetEquipmentBtn(String iconpath, int type, String text, int caozuo) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
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
        if (this.caozuo == 1) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            FormsManagement.HideForm(67);
            JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().changeMenuView(-1);
            FormsManagement.showForm(91);
        }
    }
}
