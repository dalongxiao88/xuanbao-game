package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.Frame.TestpackJframe;
import org.come.entity.LotteryGood;
import org.come.Frame.OptionsJframe;
import come.tool.JDialog.TiShiUtil;
import come.tool.JDialog.TiShiChuLi;
import org.come.until.LotteryFromServerUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LotteryGoodslisten implements MouseListener
{
    private int goodPlace;
    
    public LotteryGoodslisten(int goodPlace) {
        this.goodPlace = goodPlace;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (LotteryFromServerUntil.getRecord() <= 0) {
            TiShiChuLi chuLi = (TiShiChuLi)TiShiUtil.tishiMap.get(TiShiUtil.DoLottery);
            chuLi.tipBox(true, Integer.valueOf(this.goodPlace));
        }
        else {
            int pay = 0;
            int num = LotteryFromServerUntil.getRecord();
            switch (num) {
                case 1: {
                    pay = 10;
                    break;
                }
                case 2: {
                    pay = 20;
                    break;
                }
                case 3: {
                    pay = 50;
                    break;
                }
                case 4: {
                    pay = 100;
                    break;
                }
                case 5: {
                    pay = 200;
                    break;
                }
                case 6: {
                    pay = 400;
                    break;
                }
                case 7: {
                    pay = 800;
                    break;
                }
                case 8: {
                    pay = 1600;
                    break;
                }
            }
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.DoLottery, Integer.valueOf(this.goodPlace), "#W确定要花费#G" + pay + "#W仙玉来打开打开这张卡片吗?");
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (LotteryFromServerUntil.getGoodslist() != null && LotteryFromServerUntil.getGoodslist().size() > 0) {
            LotteryGood goodstable = (LotteryGood)LotteryFromServerUntil.getGoodslist().get(this.goodPlace);
            if (goodstable != null && goodstable.getLotterystep() != 1) {
                TestpackJframe.getTestpackJframe().getJpac().PaintingText(this.goodPlace);
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
