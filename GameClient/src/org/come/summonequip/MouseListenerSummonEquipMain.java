package org.come.summonequip;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.Goodtype;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MouseListenerSummonEquipMain extends MouseAdapter
{
    private int i;
    private JpanelSummonEquipMain jpanelSummonEquipMain;
    
    public MouseListenerSummonEquipMain(int i, JpanelSummonEquipMain jpanelSummonEquipMain) {
        this.i = i;
        this.jpanelSummonEquipMain = jpanelSummonEquipMain;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            Goodstable goodstable = GoodsListFromServerUntil.getSummonGoods(this.i);
            if (goodstable == null) {
                return;
            }
            if (Goodtype.isSummonEquip((long)goodstable.getType())) {
                if (JframeReclaimSkillMain.getJframeReclaimSkillMain().isVisible()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先做完更换技能操作");
                    return;
                }
                this.jpanelSummonEquipMain.chooseEquip(goodstable);
                this.jpanelSummonEquipMain.getAwakenSkill();
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 1 && this.jpanelSummonEquipMain.getRadioBtnType() == 1) {
                this.cultivate(goodstable);
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 1 && this.jpanelSummonEquipMain.getRadioBtnType() == 2) {
                ZhuFrame.getZhuJpanel().addPrompt2("选中的物品必须为召唤兽装备");
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 2 && this.jpanelSummonEquipMain.getRadioBtnType() == 1) {
                this.washSkill(goodstable);
            }
            else if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 2 && this.jpanelSummonEquipMain.getRadioBtnType() == 2) {
                this.anewSkill(goodstable);
            }
            else {
                if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 3 && this.jpanelSummonEquipMain.getRadioBtnType() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择召唤兽装备");
                    return;
                }
                if (this.jpanelSummonEquipMain.getSummonEquipMenuType() == 3 && this.jpanelSummonEquipMain.getRadioBtnType() == 2) {
                    this.openSkill(goodstable);
                }
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getSummonGoods(this.i);
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public void chooseGoods(Goodstable goodstable) {
        this.jpanelSummonEquipMain.setChooseGoods(goodstable.getRgid());
        this.jpanelSummonEquipMain.getEquipImgTwo().setText("");
        this.jpanelSummonEquipMain.getEquipImgTwo().setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 55, 53));
    }
    
    public void cultivate(Goodstable goodstable) {
        if ((long)goodstable.getType() == 513L) {
            this.chooseGoods(goodstable);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("选中的物品必须是玄铁晶石或者召唤兽装备");
        }
    }
    
    public void washSkill(Goodstable goodstable) {
        if ((long)goodstable.getType() == 513L) {
            this.getWashGoodsLabsGoods(goodstable, 0);
        }
        else if ((long)goodstable.getType() == 497L) {
            this.getWashGoodsLabsGoods(goodstable, 1);
        }
        else if ((long)goodstable.getType() == 498L) {
            this.getWashGoodsLabsGoods(goodstable, 2);
        }
    }
    
    public void getWashGoodsLabsGoods(Goodstable goodstable, int i) {
        if (i > 1) {
            int num = 0;
            for (int j = 2; j < this.jpanelSummonEquipMain.getChooseWashGoods().length; ++j) {
                this.jpanelSummonEquipMain.recoverWashGoodsLabs(j);
            }
            for (int j = 2; j < this.jpanelSummonEquipMain.getChooseWashGoods().length; ++j) {
                if (this.jpanelSummonEquipMain.getChooseWashGoods()[j] == null && (int)goodstable.getUsetime() > num) {
                    this.jpanelSummonEquipMain.getWashGoodsLabs()[j].setText("");
                    this.jpanelSummonEquipMain.getChooseWashGoods()[j] = goodstable;
                    this.jpanelSummonEquipMain.getWashGoodsLabs()[j].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 39, 39));
                    ++num;
                }
            }
        }
        else {
            this.jpanelSummonEquipMain.getWashGoodsLabs()[i].setText("");
            this.jpanelSummonEquipMain.getChooseWashGoods()[i] = goodstable;
            this.jpanelSummonEquipMain.getWashGoodsLabs()[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 39, 39));
        }
    }
    
    public void anewSkill(Goodstable goodstable) {
        if (515L == (long)goodstable.getType()) {
            this.chooseGoods(goodstable);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("选中的物品必须是隐月神石或者召唤兽装备");
        }
    }
    
    public void openSkill(Goodstable goodstable) {
        if (514L == (long)goodstable.getType()) {
            this.chooseGoods(goodstable);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("选中的物品必须是千年魂石或者召唤兽装备");
        }
    }
}
