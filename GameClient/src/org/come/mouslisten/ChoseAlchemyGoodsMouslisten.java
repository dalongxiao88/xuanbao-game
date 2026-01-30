package org.come.mouslisten;

import java.util.ArrayList;
import org.come.Frame.TestpackJframe;
import org.come.Frame.ZhuFrame;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.AlchemyJframe;
import org.come.until.SplitStringTool;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.AlchemyJpanel;
import java.util.List;
import java.awt.event.MouseListener;

public class ChoseAlchemyGoodsMouslisten implements MouseListener
{
    private int index;
    public static List<String> list;
    private AlchemyJpanel alchemyJpanel;
    
    public ChoseAlchemyGoodsMouslisten(int index) {
        this.index = index;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.index < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.index);
            if (goodstable == null) {
                return;
            }
            List<String> strings = SplitStringTool.splitString("701-711|713|714|722|723");
            if (strings.contains(goodstable.getType().toString())) {
                ImageIcon img = GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49);
                AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getLabRefined().setIcon(img);
                ZhuJpanel.setGoodstableAl(goodstable);
            }
        }
        else if (this.index == 24) {
            AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getLabRefined().setIcon(null);
            ZhuJpanel.setGoodstableAl(null);
        }
        if (this.index == 24) {
            return;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        ChoseAlchemyGoodsMouslisten.list.remove(this.index + "");
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.index < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.index);
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
        else {
            Goodstable goodstable = ZhuJpanel.getGoodstableAl();
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    static {
        ChoseAlchemyGoodsMouslisten.list = new ArrayList<>();
    }
}
