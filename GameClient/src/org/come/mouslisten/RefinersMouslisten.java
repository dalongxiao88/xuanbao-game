package org.come.mouslisten;

import java.util.ArrayList;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.util.List;
import org.come.Jpanel.DianJpanel;
import org.come.Jpanel.RefiningEquiJpanel;
import org.come.Jpanel.RefinersJpanel;
import java.awt.event.MouseListener;

public class RefinersMouslisten implements MouseListener
{
    private RefinersJpanel jpanel;
    private RefiningEquiJpanel Ejpanel;
    private DianJpanel Ejpanel1;
    private int path;
    public static List<String> list;
    
    public RefinersMouslisten(RefinersJpanel jpanel, int path) {
        this.jpanel = jpanel;
        this.path = path;
    }
    
    public RefinersMouslisten(DianJpanel jpanel, int path) {
        this.Ejpanel1 = jpanel;
        this.path = path;
    }
    
    public RefinersMouslisten(RefiningEquiJpanel jpanel, int path) {
        this.Ejpanel = jpanel;
        this.path = path;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        RefinersMouslisten.list.add(this.path + "");
        Music.addyinxiao("关闭窗口.mp3");
        Goodstable goodstable = null;
        if (this.path < 24) {
            goodstable = GoodsListFromServerUntil.Getgood(this.path);
        }
        else if (this.jpanel != null) {
            goodstable = this.jpanel.goods[this.path - 24];
        }
        else if (this.Ejpanel != null) {
            goodstable = this.Ejpanel.goods[this.path - 24];
        }
        if (goodstable != null) {
            if (this.jpanel != null) {
                this.jpanel.ClickGood(goodstable, this.path);
            }
            else if (this.Ejpanel != null) {
                this.Ejpanel.ClickGood(goodstable, this.path);
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        RefinersMouslisten.list.remove(this.path + "");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = null;
        if (this.path < 24) {
            goodstable = GoodsListFromServerUntil.Getgood(this.path);
        }
        else if (this.jpanel != null) {
            goodstable = this.jpanel.goods[this.path - 24];
        }
        else if (this.Ejpanel != null) {
            goodstable = this.Ejpanel.goods[this.path - 24];
        }
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    static {
        RefinersMouslisten.list = new ArrayList<>();
    }
}
