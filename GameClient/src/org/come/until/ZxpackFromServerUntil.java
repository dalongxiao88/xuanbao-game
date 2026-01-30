package org.come.until;

import java.util.Iterator;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;
import org.come.entity.Goodstable;
import java.util.List;

public class ZxpackFromServerUntil
{
    private static List<Goodstable> Goodslist;
    private static List<Goodstable> showlist;
    private static int record;
    private static final Image image;
    private static boolean hasStart;
    
    public static boolean isHasStart() {
        return ZxpackFromServerUntil.hasStart;
    }
    
    public static void setHasStart(boolean hasStart) {
        ZxpackFromServerUntil.hasStart = hasStart;
    }
    
    public static int upRecord() {
        return ++ZxpackFromServerUntil.record;
    }
    
    public static int getRecord() {
        return ZxpackFromServerUntil.record;
    }
    
    public static void setRecord(int record) {
        ZxpackFromServerUntil.record = record;
    }
    
    public static void draw(Graphics g, int x, int y) {
        for (int i = 0; i < ZxpackFromServerUntil.Goodslist.size(); ++i) {
            int row = i % 5 * 58;
            int col = i / 5 * 58;
            g.drawImage(ZxpackFromServerUntil.image, x + 20 + row + 2 + 1, y + col + 1 + 5, 56, 56, null);
            g.drawImage(CutButtonImage.getImage("img/item/" + ((Goodstable)ZxpackFromServerUntil.Goodslist.get(i)).getSkin() + ".png", -1, -1).getImage(), x + 20 + row + 2 + 1, y + col + 1 + 5, 56, 56, null);
        }
    }
    
    public static void drop() {
        ZxpackFromServerUntil.record = 0;
        setGoodslist(new ArrayList<>());
        setShowlist(new ArrayList<>());
    }
    
    public static List<Goodstable> getGoodslist() {
        return ZxpackFromServerUntil.Goodslist;
    }
    
    public static void setGoodslist(List<Goodstable> goodslist) {
        ZxpackFromServerUntil.Goodslist = goodslist;
    }
    
    public static void setGoods(List<Goodstable> goodslist) {
        ZxpackFromServerUntil.Goodslist = goodslist;
        for (Goodstable goodstable : ZxpackFromServerUntil.Goodslist) {}
    }
    
    public static List<Goodstable> getShowlist() {
        return ZxpackFromServerUntil.showlist;
    }
    
    public static void setShowlist(List<Goodstable> showlist) {
        ZxpackFromServerUntil.showlist = showlist;
    }
    
    static {
        ZxpackFromServerUntil.Goodslist = new ArrayList<>();
        ZxpackFromServerUntil.showlist = new ArrayList<>();
        ZxpackFromServerUntil.record = 0;
        image = CutButtonImage.getImage("inkImg/button1/B33.png", -1, -1).getImage();
        ZxpackFromServerUntil.hasStart = false;
    }
}
