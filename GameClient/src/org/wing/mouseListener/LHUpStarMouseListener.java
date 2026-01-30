package org.wing.mouseListener;

import java.math.BigDecimal;
import org.come.Frame.TestpackJframe;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.wing.panel.LHMainPanel;
import java.awt.event.MouseAdapter;

public class LHUpStarMouseListener extends MouseAdapter
{
    private int type;
    private LHMainPanel LHMainPanel;
    
    public LHUpStarMouseListener(int type, LHMainPanel LHMainPanel) {
        this.type = type;
        this.LHMainPanel = LHMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Goodstable wingUpStarGoods = GoodsListFromServerUntil.getWingUpStarGoods(this.type);
        if (wingUpStarGoods == null) {
            return;
        }
        if (this.LHMainPanel.getWingGoods() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选中翅膀");
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.LHMainPanel.getWingGoods());
        if (goodstable == null) {
            return;
        }
        String[] split = goodstable.getValue().split("\\|");
        Integer goodsValue = Integer.valueOf(Integer.parseInt(this.LHMainPanel.getGoodsValue(split, "星级")));
        this.LHMainPanel.setMoney(getGoodsTrue(wingUpStarGoods, goodsValue));
        if (this.LHMainPanel.getMoney() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("不符合该星级");
            return;
        }
        String goodsQuality = this.LHMainPanel.getGoodsValue(split, "品质");
        String qualityNumber = getGoodsQuality(goodsQuality);
        if (qualityNumber == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("不符合该品质");
            return;
        }
        this.LHMainPanel.setChosegoods(wingUpStarGoods);
        this.LHMainPanel.getChooseGoodsImg().setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.LHMainPanel.getChosegoods().getSkin(), 58, 56));
        this.LHMainPanel.getNumberLab().setText(qualityNumber);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.getWingUpStarGoods(this.type);
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public static BigDecimal getGoodsTrue(Goodstable wingUpStarGoods, Integer goodsValue) {
        if (goodsValue == null) {
            return null;
        }
        String value = wingUpStarGoods.getValue().split("=")[1];
        if ((int)goodsValue <= 2) {
            if (value.equals("1")) {
                return new BigDecimal("10000000");
            }
        }
        else if ((int)goodsValue <= 5) {
            if (value.equals("2")) {
                return new BigDecimal("20000000");
            }
        }
        else if ((int)goodsValue <= 8) {
            if (value.equals("3")) {
                return new BigDecimal("30000000");
            }
        }
        else if ((int)goodsValue <= 11) {
            if (value.equals("4")) {
                return new BigDecimal("40000000");
            }
        }
        else if ((int)goodsValue <= 14 && value.equals("5")) {
            return new BigDecimal("50000000");
        }
        return null;
    }
    
    public static String getGoodsQuality(String quality) {
        int n = -1;
        switch (quality.hashCode()) {
            case 811615: {
                if (quality.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (quality.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (quality.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (quality.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (quality.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (quality.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return "1";
            }
            case 1: {
                return "2";
            }
            case 2: {
                return "3";
            }
            case 3: {
                return "4";
            }
            case 4: {
                return "5";
            }
            case 5: {
                return "6";
            }
            default: {
                return null;
            }
        }
    }
}
