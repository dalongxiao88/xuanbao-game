package org.come.mouslisten;

import org.come.Frame.AlchemyJframe;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.SpiritualJpanel;
import org.come.Jpanel.ZhuJpanel;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.MessagrFlagUntil;
import org.come.until.Music;
import org.come.until.SplitStringTool;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class SpiritualMouslisten implements MouseListener
{
    private int index;
    private int[] xy;
    public static List<String> list;
    private SpiritualJpanel spiritual;

    public SpiritualMouslisten(final int index, final SpiritualJpanel spiritual, final int[] xy) {
        this.xy = xy;
        this.index = index;
        this.spiritual = spiritual;
    }

    public SpiritualMouslisten(final int index) {
        this.index = index;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (this.index < 24) {
            final Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.index);
            if (goodstable == null) {
                return;
            }
            else {
                final List<String> strings1 = SplitStringTool.splitString("66690");
                if (strings1.contains(goodstable.getType().toString())) {
                    final ImageIcon img = GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49);
                    AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel().getLabRefined().setIcon((Icon)img);
                    ZhuJpanel.setGoodstableAlf(goodstable);
                }
            }
        }
        else {
            if (this.index == 24) {
                AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel().getLabRefined().setIcon(null);
                ZhuJpanel.setGoodstableAlf(null);
            }
        }
        if (this.index == 24) {
            Music.addyinxiao("关闭窗口.mp3");
            return;
        }
        else {
            SpiritualMouslisten.list.add(this.index + "");
            Music.addyinxiao("关闭窗口.mp3");
            this.spiritual.xuanzhong(GoodsListFromServerUntil.getGoodslist()[this.index + GoodsListFromServerUntil.Pagenumber * 24], this.index);
            return;
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        SpiritualMouslisten.list.remove(this.index + "");
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
        }
        if (this.index < 24) {
            final Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.index);
            if (goodstable != null) {
                AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel().PaintingText(this.index);
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
        else {
            final Goodstable goodstable = ZhuJpanel.getGoodstableAlf();
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        }
        AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    static {
        list = new ArrayList();
    }
}
