package org.come.mouslisten;

import java.util.ArrayList;
import org.come.Frame.ZhuFrame;
import org.come.until.SynthesisFormulation;
import org.come.Frame.ForgeJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.util.List;
import org.come.Jpanel.RuneOperateJpanel;
import org.come.entity.Goodstable;
import org.come.Jpanel.ForgeJpanel;
import java.awt.event.MouseListener;

public class TransmuteMouslisten implements MouseListener
{
    private int goodPlace;
    private ForgeJpanel forgeJpanel;
    public static Goodstable[] goods2;
    public static Goodstable[] goods3;
    public static Goodstable[] goods4;
    private RuneOperateJpanel runeOperateJpanel;
    public static List<String> list;
    
    public TransmuteMouslisten(int goodPlace, ForgeJpanel forgeJpanel) {
        this.goodPlace = goodPlace;
        this.forgeJpanel = forgeJpanel;
    }
    
    public TransmuteMouslisten(int goodPlace, RuneOperateJpanel runeOperateJpanel) {
        this.goodPlace = goodPlace;
        this.runeOperateJpanel = runeOperateJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        TransmuteMouslisten.list.add(this.goodPlace + "");
        Music.addyinxiao("关闭窗口.mp3");
        if (this.goodPlace > 23 || GoodsListFromServerUntil.getGoodslist()[this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24] != null) {
            if (this.forgeJpanel != null) {
                SynthesisFormulation.type(ForgeJframe.getForgejframe().getForgejpanel().getType(), this);
            }
            else if (this.runeOperateJpanel != null && this.runeOperateJpanel.getRuneType() == 1) {
                SynthesisFormulation.type("我要洗练符石", this);
            }
            else if (this.runeOperateJpanel != null && this.runeOperateJpanel.getRuneType() == 2) {
                SynthesisFormulation.type("我要合成符石", this);
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        TransmuteMouslisten.list.remove(this.goodPlace + "");
        if (this.goodPlace < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24];
            if (goodstable != null) {
                ForgeJframe.getForgejframe().getForgejpanel().PaintingText(this.goodPlace);
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
        else if (this.forgeJpanel != null && TransmuteMouslisten.goods2[this.goodPlace - 24] != null) {
            Goodstable goodstable = TransmuteMouslisten.goods2[this.goodPlace - 24];
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
        else if (this.runeOperateJpanel != null && this.runeOperateJpanel.getRuneType() == 1 && this.goodPlace < 26 && TransmuteMouslisten.goods3[this.goodPlace - 24] != null) {
            Goodstable goodstable = TransmuteMouslisten.goods3[this.goodPlace - 24];
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
        else if (this.runeOperateJpanel != null && this.runeOperateJpanel.getRuneType() == 2 && this.goodPlace > 25 && TransmuteMouslisten.goods4[this.goodPlace - 26] != null) {
            Goodstable goodstable = TransmuteMouslisten.goods4[this.goodPlace - 26];
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ForgeJframe.getForgejframe().getForgejpanel().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public int getGoodPlace() {
        return this.goodPlace;
    }
    
    public void setGoodPlace(int goodPlace) {
        this.goodPlace = goodPlace;
    }
    
    public ForgeJpanel getForgeJpanel() {
        return this.forgeJpanel;
    }
    
    public void setForgeJpanel(ForgeJpanel forgeJpanel) {
        this.forgeJpanel = forgeJpanel;
    }
    
    public RuneOperateJpanel getRuneOperateJpanel() {
        return this.runeOperateJpanel;
    }
    
    public void setRuneOperateJpanel(RuneOperateJpanel runeOperateJpanel) {
        this.runeOperateJpanel = runeOperateJpanel;
    }
    
    static {
        TransmuteMouslisten.goods2 = new Goodstable[2];
        TransmuteMouslisten.goods3 = new Goodstable[2];
        TransmuteMouslisten.goods4 = new Goodstable[5];
        TransmuteMouslisten.list = new ArrayList<>();
    }
}
