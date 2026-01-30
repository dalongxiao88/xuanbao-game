package org.come.starcard;

import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.mouslisten.GoodsMouslisten;
import org.come.Frame.ZhuFrame;
import org.come.until.EquipTool;
import org.come.until.MessagrFlagUntil;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MouseListenerStarCardMain extends MouseAdapter
{
    private int i;
    private JpanelStarCardMain jpanelStarCardMain;
    private JpanelSoulBackMain jpanelSoulBackMain;
    
    public MouseListenerStarCardMain(int i, JpanelStarCardMain jpanelStarCardMain) {
        this.i = i;
        this.jpanelStarCardMain = jpanelStarCardMain;
    }
    
    public MouseListenerStarCardMain(int i, JpanelSoulBackMain jpanelSoulBackMain) {
        this.i = i;
        this.jpanelSoulBackMain = jpanelSoulBackMain;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Goodstable starCardGoods = GoodsListFromServerUntil.getStarCardGoods(this.i);
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
            if (starCardGoods != null) {
                if (EquipTool.isEquip(starCardGoods.getType())) {
                    if (starCardGoods.getGoodlock() == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt("该物品已加锁");
                    }
                    else {
                        starCardGoods.setGoodlock(1);
                        GoodsMouslisten.gooduse(starCardGoods, 0);
                        ZhuFrame.getZhuJpanel().addPrompt("加锁成功");
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt("该类型物品不可加锁");
                }
            }
        }
        else if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11)) {
            if (starCardGoods != null && starCardGoods.getGoodlock() == 1) {
                starCardGoods.setGoodlock(0);
                GoodsMouslisten.gooduse(starCardGoods, 0);
                ZhuFrame.getZhuJpanel().addPrompt("解锁成功");
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
            }
        }
        else if (starCardGoods != null && this.jpanelStarCardMain != null) {
            if (this.jpanelStarCardMain.getBigType() == 1) {
                this.jpanelStarCardMain.attributeImgShow(starCardGoods);
            }
            else if (this.jpanelStarCardMain.getBigType() == 2) {
                this.jpanelStarCardMain.chooseStarCardImg(starCardGoods);
                if (this.jpanelStarCardMain.getSmallType() != 3) {
                    this.jpanelStarCardMain.viewChange(starCardGoods);
                }
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getStarCardGoods(this.i);
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
