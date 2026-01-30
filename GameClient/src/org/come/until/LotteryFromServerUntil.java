package org.come.until;

import java.util.HashMap;
import com.tool.tcp.SpriteFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import com.tool.image.ImageMixDeal;
import com.tool.btn.LotteryNineBtn;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import java.util.Map;
import com.tool.tcp.Sprite;
import java.awt.Image;
import org.come.entity.LotteryGood;
import java.util.List;

public class LotteryFromServerUntil
{
    private static List<LotteryGood> Goodslist;
    private static List<LotteryGood> showlist;
    private static int record;
    private static Image image;
    private static Image bgimage1;
    private static Image bgimage2;
    private static Image bgimage3;
    private static Image bgimage4;
    private static boolean hasStart;
    public static Sprite path;
    public static Sprite tcp;
    public static Sprite tcp1;
    public static Map<BigDecimal, Goodstable> chatGoods;
    
    public static boolean isHasStart() {
        return LotteryFromServerUntil.hasStart;
    }
    
    public static void setHasStart(boolean hasStart) {
        LotteryFromServerUntil.hasStart = hasStart;
    }
    
    public static int getRecord() {
        return LotteryFromServerUntil.record;
    }
    
    public static int upRecord() {
        return ++LotteryFromServerUntil.record;
    }
    
    public static void setRecord(int record) {
        LotteryFromServerUntil.record = record;
    }
    
    public static void draw(Graphics g, int x, int y) {
        if (MyIsif.getStyle().equals("水墨")) {
            int i = 0;
            if (LotteryNineBtn.isXIPAI) {
                LotteryFromServerUntil.path.updateToTime(ImageMixDeal.userimg.getTime(), 1);
                LotteryFromServerUntil.path.draw(g, x, y);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000L);
                            LotteryNineBtn.isXIPAI = false;
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            else {
                while (i < 9) {
                    int row = i % 3 * 72;
                    int col = i / 3 * 72;
                    if (LotteryFromServerUntil.Goodslist != null && LotteryFromServerUntil.Goodslist.size() >= 9) {
                        if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterystep() == 1) {
                            g.drawImage(LotteryFromServerUntil.image, x - 6 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                        }
                        else {
                            if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterybg().equals("FFBG01")) {
                                g.drawImage(LotteryFromServerUntil.bgimage1, x - 6 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                            }
                            else if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterybg().equals("FFBG02")) {
                                g.drawImage(LotteryFromServerUntil.bgimage2, x - 6 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                            }
                            else if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterybg().equals("FFBG03")) {
                                g.drawImage(LotteryFromServerUntil.bgimage3, x - 6 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                            }
                            else if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterybg().equals("FFBG04")) {
                                g.drawImage(LotteryFromServerUntil.bgimage4, x - 6 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                                LotteryFromServerUntil.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                                LotteryFromServerUntil.tcp.draw(g, x - 6 + row + 1, y - 2 + col + 1 + 3);
                            }
                            g.drawImage(CutButtonImage.getImage("img/item/" + ((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getSkin() + ".png", -1, -1).getImage(), x + 2 + row + 2 + 1, y + col + 1 + 9, 53, 53, null);
                        }
                    }
                    ++i;
                }
                LotteryNineBtn.isXIPAI = false;
            }
        }
        else {
            int i = 0;
            if (LotteryNineBtn.isXIPAI) {
                while (i < 9) {
                    int row = i % 3 * 72;
                    int col = i / 3 * 72;
                    if (LotteryFromServerUntil.Goodslist != null && LotteryFromServerUntil.Goodslist.size() >= 9) {
                        LotteryFromServerUntil.path.updateToTime(ImageMixDeal.userimg.getTime(), 10);
                        LotteryFromServerUntil.path.draw(g, x - 17 + row + 2 + 1, y + col + 1 + 3);
                    }
                    ++i;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100L);
                            LotteryNineBtn.isXIPAI = false;
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            else {
                while (i < 9) {
                    int row = i % 3 * 72;
                    int col = i / 3 * 72;
                    if (LotteryFromServerUntil.Goodslist != null && LotteryFromServerUntil.Goodslist.size() >= 9) {
                        if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterystep() == 1) {
                            g.drawImage(LotteryFromServerUntil.image, x - 17 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                        }
                        else {
                            if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterybg().equals("FFBG01")) {
                                g.drawImage(LotteryFromServerUntil.bgimage1, x - 17 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                            }
                            else if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterybg().equals("FFBG02")) {
                                g.drawImage(LotteryFromServerUntil.bgimage2, x - 17 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                            }
                            else if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterybg().equals("FFBG03")) {
                                g.drawImage(LotteryFromServerUntil.bgimage3, x - 17 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                            }
                            else if (((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getLotterybg().equals("FFBG04")) {
                                g.drawImage(LotteryFromServerUntil.bgimage4, x - 17 + row + 2 + 1, y + col + 1 + 3, 70, 70, null);
                                LotteryFromServerUntil.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                                LotteryFromServerUntil.tcp.draw(g, x - 23 + row + 2 + 1, y + col + 1 - 1);
                            }
                            g.drawImage(CutButtonImage.getImage("img/item/" + ((LotteryGood)LotteryFromServerUntil.Goodslist.get(i)).getSkin() + ".png", -1, -1).getImage(), x - 10 + row + 2 + 1, y + col + 1 + 10, 53, 53, null);
                        }
                    }
                    ++i;
                }
                LotteryNineBtn.isXIPAI = false;
            }
        }
    }
    
    public static void startLottery() {
        if (!LotteryFromServerUntil.hasStart) {
            Collections.shuffle(LotteryFromServerUntil.Goodslist);
            LotteryFromServerUntil.hasStart = true;
            for (LotteryGood lotteryGood : LotteryFromServerUntil.Goodslist) {
                lotteryGood.setLotterystep(1);
            }
        }
    }
    
    public static void drop() {
        LotteryFromServerUntil.hasStart = false;
        LotteryFromServerUntil.record = 0;
        setGoodslist(new ArrayList<>());
        setShowlist(new ArrayList<>());
    }
    
    public static List<LotteryGood> getGoodslist() {
        return LotteryFromServerUntil.Goodslist;
    }
    
    public static void setGoodslist(List<LotteryGood> goodslist) {
        LotteryFromServerUntil.Goodslist = goodslist;
    }
    
    public static void setGoods(ArrayList<LotteryGood> goodslist) {
        LotteryFromServerUntil.Goodslist = goodslist;
        for (LotteryGood lotteryGood : LotteryFromServerUntil.Goodslist) {}
    }
    
    public static List<LotteryGood> getShowlist() {
        return LotteryFromServerUntil.showlist;
    }
    
    public static void setShowlist(List<LotteryGood> showlist) {
        LotteryFromServerUntil.showlist = showlist;
    }
    
    static {
        LotteryFromServerUntil.Goodslist = new ArrayList<>();
        LotteryFromServerUntil.showlist = new ArrayList<>();
        LotteryFromServerUntil.record = 0;
        LotteryFromServerUntil.image = CutButtonImage.getImage("img/item/FFBG00.png", -1, -1).getImage();
        LotteryFromServerUntil.bgimage1 = CutButtonImage.getImage("img/item/FFBG01.png", -1, -1).getImage();
        LotteryFromServerUntil.bgimage2 = CutButtonImage.getImage("img/item/FFBG02.png", -1, -1).getImage();
        LotteryFromServerUntil.bgimage3 = CutButtonImage.getImage("img/item/FFBG03.png", -1, -1).getImage();
        LotteryFromServerUntil.bgimage4 = CutButtonImage.getImage("img/item/FFBG04.png", -1, -1).getImage();
        LotteryFromServerUntil.hasStart = false;
        LotteryFromServerUntil.path = SpriteFactory.VloadSprite("resource/mouse/xipai.tcp", null);
        LotteryFromServerUntil.tcp = SpriteFactory.VloadSprite("resource/mouse/zhuan.tcp", null);
        LotteryFromServerUntil.tcp1 = SpriteFactory.VloadSprite("resource/mouse/FFLFY.tcp", null);
        LotteryFromServerUntil.chatGoods = new HashMap<>();
    }
}
