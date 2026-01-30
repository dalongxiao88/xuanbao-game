package org.come.summonequip;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MouseListenerNowEquip extends MouseAdapter
{
    private int type;
    private JpanelNowEquip jpanelNowEquip;
    
    public MouseListenerNowEquip(int type, JpanelNowEquip jpanelNowEquip) {
        this.type = type;
        this.jpanelNowEquip = jpanelNowEquip;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (JframeReclaimSkillMain.getJframeReclaimSkillMain().isVisible()) {
            ZhuFrame.getZhuJpanel().addPrompt2("请先做完更换技能操作");
        }
        else {
            Goodstable goodstable = this.jpanelNowEquip.getSummonEquipOne(this.type);
            if (goodstable != null) {
                JpanelSummonEquipMain jpanelSummonEquipMain = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain();
                jpanelSummonEquipMain.chooseEquip(goodstable);
                this.jpanelNowEquip.setVisible(false);
                jpanelSummonEquipMain.getScrollPane().setVisible(false);
                if (jpanelSummonEquipMain.getSummonEquipMenuType() == 3 && jpanelSummonEquipMain.getRadioBtnType() == 1) {
                    jpanelSummonEquipMain.getAwakenSkill();
                }
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = this.jpanelNowEquip.getSummonEquipOne(this.type);
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
