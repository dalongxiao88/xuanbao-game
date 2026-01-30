package org.come.until;

import org.wing.panel.LHMainPanel;
import com.tool.btn.BaptizeBtn;
import org.come.starcard.JpanelStarTransferMain;
import org.come.starcard.JpanelStarCardMain;
import org.come.starcard.BtnStarCard;
import org.come.starcard.JframeStarTransferMain;
import org.come.starcard.JframeStarCardMain;
import org.wing.panel.WingMainPanel;
import org.wing.panel.WingMainFrame;
import org.come.Jpanel.GemRefineryMainJpanel;
import org.come.Jpanel.WorkshopRefiningCardJpanel;
import org.come.summonequip.JpanelSummonEquipMain;
import org.come.summonequip.JframeSummonEquipMain;
import com.tool.btn.goodbtn;
import org.come.Frame.PawnJfram;
import org.come.Frame.RuneOperateJframe;
import org.come.Frame.AlchemyJframe;
import org.come.Frame.PetEquipmentJframe;
import org.come.Frame.CollectionJadeJframe;
import org.come.Frame.ExchangeValueJframe;
import org.come.Frame.WorkshopRefiningJframe;
import org.come.Frame.TradeJframe;
import org.come.Frame.spot.SpotBoxJframe;
import org.come.Frame.GiveJframe;
import org.come.Frame.ForgeJframe;
import org.come.Frame.TestpackJframe;

import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.come.entity.PackRecord;

import java.util.ArrayList;

import org.come.bean.GoodTrans;
import com.tool.Stall.Commodity;
import com.tool.Stall.Stall;
import org.come.model.InternalForm;
import org.come.Frame.TransJframe;
import com.tool.Stall.StallBean;
import org.come.Frame.BoothBoxJframe;
import org.come.MountShouHu.ShouhuPackJpanel;
import org.come.MountShouHu.ShouhuPackJframe;
import com.tool.image.ImageMixDeal;
import org.come.bean.LoginResult;
import org.come.equipmentSwitching.EquipmentSwitchingJframe;
import com.tool.time.TimeLimit;
import com.tool.role.RoleProperty;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import org.come.entity.RoleSummoning;
import org.come.bean.IncludedPart;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.Jpanel.TestpackJapnel;
import org.come.bean.PathPoint;
import com.tool.tcpimg.UIUtils;
import org.come.equipmentSwitching.EquipmentSwitchingJpanel;

import java.awt.image.BufferedImage;

import come.tool.Fighting.FightingMixDeal;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Objects;
import java.awt.Color;
import java.awt.Font;

import org.come.mouslisten.YuFuGoodsMouslisten;
import org.come.mouslisten.TradeChoseGoodsMouslisten;
import org.come.mouslisten.CancelStallMouslisten;
import org.come.mouslisten.RefinersMouslisten;
import org.come.mouslisten.GiveGoodsMouslisten;
import org.come.mouslisten.TransmuteMouslisten;
import org.come.mouslisten.ChoseAlchemyGoodsMouslisten;
import org.come.mouslisten.PawnChoseMouslisten;
import org.come.mouslisten.GoodsMouslisten;

import java.awt.RenderingHints;
import java.awt.Graphics2D;

import com.updateNew.MyIsif;

import java.awt.Graphics;
import java.math.BigDecimal;
import java.util.Map;
import javax.swing.ImageIcon;
import java.util.List;

import org.come.entity.Goodstable;

import static org.come.until.Goodtype.*;

public class GoodsListFromServerUntil {
    private static Goodstable[] Goodslist;
    public static Goodstable[] choseGoodsList;
    private static List<Goodstable> wingGoodsList;
    private static List<Goodstable> starCardList;
    private static List<Goodstable> starGoods;
    private static List<Goodstable> suitGoods;
    private static ImageIcon[] goodimg;
    public static ImageIcon lockimg;
    private static final ImageIcon stallimg;
    public static int NB;
    public static List<Goodstable> petGooods;
    public static Map<BigDecimal, Goodstable> chatGoods;
    public static int Pagenumber;
    public static Map<BigDecimal, Goodstable> fushis;
    public static Map<BigDecimal, Map<String, Object>> fushisAll;
    public static List<Goodstable> goodsBackpackMissionList = new ArrayList<>();

    public static void draw(Graphics g, int x, int y) {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < 24; ++i) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                int row = i % 6 * 51;
                int col = i / 6 * 51;
                if (GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24] != null) {
                    if (GoodsListFromServerUntil.goodimg[i] != null) {
                        if (GoodsMouslisten.list.contains(i + "") || PawnChoseMouslisten.list.contains(i + "") || ChoseAlchemyGoodsMouslisten.list.contains(i + "") || TransmuteMouslisten.list.contains(i + "") || GiveGoodsMouslisten.list.contains(i + "") || RefinersMouslisten.list.contains(i + "") || CancelStallMouslisten.list.contains(i + "") || TradeChoseGoodsMouslisten.list.contains(i + "") || YuFuGoodsMouslisten.list.contains(i + "")) {
                            drawGoodsImage(g, i, x + row + 2 + 1, y + col + 1 + 2, 48, 48, null);
                        } else {
                            drawGoodsImage(g, i, x + row + 2, y + col + 2, 48, 48, null);
                        }
                    }
                    if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getType())) {
                        g.setFont(new Font("宋体", 0, 16));
                        g.setColor(new Color(188, 188, 188));
                        g.drawString("" + GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getUsetime(), x + row, y + 12 + col);
                        g.drawString("" + GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getUsetime(), x + 1 + row, y + 12 + col);
                    }
                    if (Goodtype.BS((long) GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getType())) {
                        Font font = new Font("宋体", 0, 16);
                        g.setFont(font);
                        if (GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getType().equals(Long.valueOf(744L))) {
                            int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cutting(GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24], 1)));
                            g.drawString(levelTwo + "", x + 1000 + row, y + 1200 + col);
                        } else {
                            int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cutting(GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24], 1)));
                            g.setColor(new Color(188, 188, 188));
                            g.drawString(levelTwo + "级", x + row, y + 12 + col);
                            g.drawString(levelTwo + "级", x + 1 + row, y + 12 + col);
                            g.drawString(levelTwo + "级", x + row, y + 12 + col);
                            g.drawString(levelTwo + "级", x + 1 + row, y + 12 + col);
                        }
                    }
                    if (Goodtype.ExerciseMonsterOre((long) GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getType())) {
                        Font font2 = new Font("宋体", 0, 13);
                        g.setFont(font2);
                        float levelTwo2 = Float.parseFloat((String) Objects.requireNonNull(luanyaoshi(GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24])));
                        g.setColor(new Color(255, 255, 255));
                        if (levelTwo2 >= 10.0f) {
                            g.drawString("+" + levelTwo2, x + row, y + 12 + col + 34);
                            g.drawString("+" + levelTwo2, x + 1 + row, y + 12 + col + 34);
                        } else {
                            g.drawString("+" + levelTwo2, x + row, y + 12 + col + 34);
                            g.drawString("+" + levelTwo2, x + 1 + row, y + 12 + col + 34);
                        }
                    }
                    if (GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + row + 38, y + col + 2, 10, 12, null);
                    }
                    if (GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getCommodityId() != null) {
                        g.drawImage(GoodsListFromServerUntil.stallimg.getImage(), x + row + 25, y + col + 25, 22, 21, null);
                    }
                }
            }
        } else {
            for (int i = 0; i < 24; ++i) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                int row = i % 6 * 51;
                int col = i / 6 * 51;
                if (GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24] != null) {
                    if (GoodsListFromServerUntil.goodimg[i] != null) {
                        if (GoodsMouslisten.list.contains(i + "") || PawnChoseMouslisten.list.contains(i + "") || ChoseAlchemyGoodsMouslisten.list.contains(i + "") || TransmuteMouslisten.list.contains(i + "") || GiveGoodsMouslisten.list.contains(i + "") || RefinersMouslisten.list.contains(i + "") || CancelStallMouslisten.list.contains(i + "") || TradeChoseGoodsMouslisten.list.contains(i + "") || YuFuGoodsMouslisten.list.contains(i + "")) {
                            drawGoodsImage(g, i, x + row + 2 + 1, y + col + 1 + 2, 48, 48, null);
                        } else {
                            drawGoodsImage(g, i, x + row + 2, y + col + 2, 48, 48, null);
                        }
                    }
                    if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getType())) {
                        g.setFont(new Font("宋体", 0, 16));
                        g.setColor(new Color(188, 188, 188));
                        g.drawString("" + GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getUsetime(), x + row, y + 12 + col);
                        g.drawString("" + GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getUsetime(), x + 1 + row, y + 12 + col);
                    }
                    if (Goodtype.BS((long) GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getType())) {
                        Font font = new Font("宋体", 0, 16);
                        g.setFont(font);
                        if (GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getType().equals(Long.valueOf(744L))) {
                            int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cutting(GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24], 1)));
                            g.drawString(levelTwo + "", x + 1000 + row, y + 1200 + col);
                        } else {
                            int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cutting(GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24], 1)));
                            g.setColor(new Color(188, 188, 188));
                            g.drawString(levelTwo + "级", x + row, y + 12 + col);
                            g.drawString(levelTwo + "级", x + 1 + row, y + 12 + col);
                            g.drawString(levelTwo + "级", x + row, y + 12 + col);
                            g.drawString(levelTwo + "级", x + 1 + row, y + 12 + col);
                        }
                    }
                    if (Goodtype.ExerciseMonsterOre((long) GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getType())) {
                        Font font2 = new Font("宋体", 0, 13);
                        g.setFont(font2);
                        float levelTwo2 = Float.parseFloat((String) Objects.requireNonNull(luanyaoshi(GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24])));
                        g.setColor(new Color(255, 255, 255));
                        if (levelTwo2 >= 10.0f) {
                            g.drawString("+" + levelTwo2, x + row, y + 12 + col + 34);
                            g.drawString("+" + levelTwo2, x + 1 + row, y + 12 + col + 34);
                        } else {
                            g.drawString("+" + levelTwo2, x + row, y + 12 + col + 34);
                            g.drawString("+" + levelTwo2, x + 1 + row, y + 12 + col + 34);
                        }
                    }
                    if (GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + row + 38, y + col + 2, 10, 12, null);
                    }
                    if (GoodsListFromServerUntil.Goodslist[i + GoodsListFromServerUntil.Pagenumber * 24].getCommodityId() != null) {
                        g.drawImage(GoodsListFromServerUntil.stallimg.getImage(), x + row + 25, y + col + 25, 22, 21, null);
                    }
                }
            }
        }
    }

    private static void drawGoodsImage(Graphics g, Image img, int x, int y, int width, int height, ImageObserver observer) {
        if (FightingMixDeal.State != 0) {
            drawGrayscaleImage(g, img, x, y, width, height, observer);
        } else {
            g.drawImage(img, x, y, width, height, observer);
        }
    }

    private static void drawGoodsImage(Graphics g, int index, int x, int y, int width, int height, ImageObserver observer) {
        if (FightingMixDeal.State != 0) {
            Goodstable goodstable = GoodsListFromServerUntil.Goodslist[index + GoodsListFromServerUntil.Pagenumber * 24];
            if (GoodsMouslisten.isFightingUse((long) goodstable.getType())) {
                g.drawImage(GoodsListFromServerUntil.goodimg[index].getImage(), x, y, width, height, observer);
            } else {
                drawGrayscaleImage(g, GoodsListFromServerUntil.goodimg[index].getImage(), x, y, width, height, observer);
            }
        } else {
            g.drawImage(GoodsListFromServerUntil.goodimg[index].getImage(), x, y, width, height, observer);
        }
    }

    private static void drawGrayscaleImage(Graphics g, Image image, int x, int y, int width, int height, ImageObserver observer) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < bufferedImage.getWidth(); ++i) {
            for (int j = 0; j < bufferedImage.getHeight(); ++j) {
                int rgb = bufferedImage.getRGB(i, j);
                int gray = toGrayscale(rgb);
                bufferedImage.setRGB(i, j, gray);
            }
        }
        g2d.drawImage(bufferedImage, x, y, width, height, observer);
    }

    private static int toGrayscale(int rgb) {
        int alpha = rgb >> 24 & 0xFF;
        int red = rgb >> 16 & 0xFF;
        int green = rgb >> 8 & 0xFF;
        int blue = rgb & 0xFF;
        return alpha << 24 | (int) (0.299 * (double) red + 0.587 * (double) green + 0.114 * (double) blue) << 16 | (int) (0.299 * (double) red + 0.587 * (double) green + 0.114 * (double) blue) << 8 | (int) (0.299 * (double) red + 0.587 * (double) green + 0.114 * (double) blue);
    }

    public static String cutting(Goodstable goods, int num) {
        if (goods.getType().equals(Long.valueOf(744L))) {
            return "1";
        }
        String value = goods.getValue();
        if (value == null) {
            return null;
        }
        String[] split = value.split("\\|");
        String[] split2 = split[num - 1].split("=");
        return split2[1];
    }

    public static String luanyaoshi(Goodstable goods) {
        String Bottletext = goods.getValue();
        String[] split = Bottletext.split("\\|");
        String[] kx = split[1].split("=");
        return kx[1];
    }

    public static void drawLock(Graphics g, EquipmentSwitchingJpanel testpackJapnel, Goodstable[] choseGoodsList) {
        g.setColor(UIUtils.Color_BACK);
        for (int i = 0; i < 12; ++i) {
            PathPoint pathS = testpackJapnel.pathS(i);
            PathPoint pathH = testpackJapnel.pathH(i);
            PathPoint pathD = testpackJapnel.pathD(i);
            int row = pathS.getX();
            int col = pathS.getY();
            int row2 = pathH.getX();
            int col2 = pathH.getY();
            int row3 = pathD.getX();
            int col3 = pathD.getY();
            if (choseGoodsList[i] != null) {
                if (!MyIsif.getStyle().equals("水墨")) {
                    ImageIcon pane = new ImageIcon("inkimg/hongmu/ss533.png");
                    g.drawImage(pane.getImage(), row - 13, col - 15, 50, 50, null);
                    g.drawImage(imgpath(choseGoodsList[i].getSkin()).getImage(), row - 11, col - 16, 50, 50, null);
                    g.fillRect(row, col, 49, 49);
                    if (choseGoodsList[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), row + 26, col + 21, 10, 12, null);
                    }
                } else {
                    ImageIcon pane = new ImageIcon("inkimg/background/ss500.png");
                    g.drawImage(pane.getImage(), row2 + 21, col2 - 14, 48, 48, null);
                    g.drawImage(imgpath(choseGoodsList[i].getSkin()).getImage(), row2 + 22, col2 - 14, 50, 50, null);
                    g.fillRect(row2, col2, 49, 49);
                    if (choseGoodsList[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), row2 + 60, col2 + 24, 10, 12, null);
                    }
                }
            }
        }
    }

    public static void drawLock(Graphics g, TestpackJapnel testpackJapnel) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure) item.get(new BigDecimal(1));
        int szz = 5;
        if (configure.getLzjskg() != null) {
            szz = Integer.parseInt(configure.getLzjskg());
        }
        if (szz == 3) {
            if (MyIsif.getStyle().equals("水墨")) {
                g.setColor(UIUtils.COLOR_Pack);
                for (int i = 0; i < 12; ++i) {
                    PathPoint path = testpackJapnel.path(i);
                    int row = path.getX();
                    int col = path.getY();
                    if (GoodsListFromServerUntil.choseGoodsList[i] != null) {
                        g.fillRect(row, col, 49, 49);
                        if (i == 3) {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 95, 95, null);
                        } else if (i == 0) {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 55, 55, null);
                        } else if (i == 5) {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 60, 60, null);
                        } else if (i == 2) {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 45, 45, null);
                        } else {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 45, 45, null);
                        }
                        if (GoodsListFromServerUntil.choseGoodsList[i].getGoodlock() == 1) {
                            g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), row + 38, col + 2, 10, 12, null);
                        }
                    }
                }
            } else {
                g.setColor(UIUtils.Color_BACK);
                for (int i = 0; i < 12; ++i) {
                    PathPoint path = testpackJapnel.path(i);
                    int row = path.getX();
                    int col = path.getY();
                    if (GoodsListFromServerUntil.choseGoodsList[i] != null) {
                        g.fillRect(row, col, 49, 49);
                        if (i == 3) {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 85, 85, null);
                        } else if (i == 0) {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 60, 60, null);
                        } else if (i == 5) {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 60, 60, null);
                        } else if (i == 2) {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 36, 36, null);
                        } else {
                            g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 36, 36, null);
                        }
                        if (GoodsListFromServerUntil.choseGoodsList[i].getGoodlock() == 1) {
                            g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), row + 38, col + 2, 10, 12, null);
                        }
                    }
                }
            }
        } else if (MyIsif.getStyle().equals("水墨")) {
            g.setColor(UIUtils.COLOR_Pack);
            for (int i = 0; i < 12; ++i) {
                PathPoint path = testpackJapnel.path(i);
                int row = path.getX();
                int col = path.getY();
                if (GoodsListFromServerUntil.choseGoodsList[i] != null) {
                    g.fillRect(row, col, 49, 49);
                    g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 45, 45, null);
                    if (GoodsListFromServerUntil.choseGoodsList[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), row + 38, col + 2, 10, 12, null);
                    }
                }
            }
        } else {
            g.setColor(new Color(12, 12, 12, 216));
            for (int i = 0; i < 12; ++i) {
                PathPoint path = testpackJapnel.path(i);
                int row = path.getX();
                int col = path.getY();
                if (GoodsListFromServerUntil.choseGoodsList[i] != null) {
                    g.fillRect(row - 3, col - 1, 54, 52);
                    g.drawImage(imgpath(GoodsListFromServerUntil.choseGoodsList[i].getSkin()).getImage(), row + 2, col + 2, 45, 45, null);
                    if (GoodsListFromServerUntil.choseGoodsList[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), row + 38, col + 2, 10, 12, null);
                    }
                }
            }
        }
    }

    public static void drawIdlEqu(Graphics g, int x, int y, int page, int number, int para) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (AccessSuitMsgUntil.accessIdlEqu(1) != null && AccessSuitMsgUntil.accessIdlEqu(1).size() > 0) {
                int size = (AccessSuitMsgUntil.accessIdlEqu(1).size() / number + ((AccessSuitMsgUntil.accessIdlEqu(1).size() % number == 0) ? 0 : 1)) * number;
                Goodstable[] Goodslist = new Goodstable[size];
                for (int i = 0; i < AccessSuitMsgUntil.accessIdlEqu(1).size(); ++i) {
                    Goodslist[i] = (Goodstable) AccessSuitMsgUntil.accessIdlEqu(1).get(i);
                }
                for (int i = 0; i < number; ++i) {
                    int row = i % para * 51;
                    int col = i / para * 51;
                    if (Goodslist[i + page * number] != null) {
                        g.drawImage(imgpath(Goodslist[i + page * number].getSkin()).getImage(), x + row + 2, y + col + 2, 45, 45, null);
                        if (Goodslist[i + page * number].getGoodlock() == 1) {
                            g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + row + 38, y + col + 2, 10, 12, null);
                        }
                    }
                }
            }
        } else if (AccessSuitMsgUntil.accessIdlEqu(1) != null && AccessSuitMsgUntil.accessIdlEqu(1).size() > 0) {
            int size = (AccessSuitMsgUntil.accessIdlEqu(1).size() / number + ((AccessSuitMsgUntil.accessIdlEqu(1).size() % number == 0) ? 0 : 1)) * number;
            Goodstable[] Goodslist = new Goodstable[size];
            for (int i = 0; i < AccessSuitMsgUntil.accessIdlEqu(1).size(); ++i) {
                Goodslist[i] = (Goodstable) AccessSuitMsgUntil.accessIdlEqu(1).get(i);
            }
            for (int i = 0; i < number; ++i) {
                int row = i % para * 51;
                int col = i / para * 51;
                if (Goodslist[i + page * number] != null) {
                    g.drawImage(imgpath(Goodslist[i + page * number].getSkin()).getImage(), x + row + 2, y + col + 2, 45, 45, null);
                    if (Goodslist[i + page * number].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + row + 38, y + col + 2, 10, 12, null);
                    }
                }
            }
        }
    }

    public static void drawRoleSuit(Graphics g, int x, int y, int page, int number) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (AccessSuitMsgUntil.accessIdlEqu(2) != null && AccessSuitMsgUntil.accessIdlEqu(2).size() > 0) {
                int size = (AccessSuitMsgUntil.accessIdlEqu(2).size() / number + ((AccessSuitMsgUntil.accessIdlEqu(2).size() % number == 0) ? 0 : 1)) * number;
                Goodstable[] Goodslist = new Goodstable[size];
                for (int i = 0; i < AccessSuitMsgUntil.accessIdlEqu(2).size(); ++i) {
                    Goodslist[i] = (Goodstable) AccessSuitMsgUntil.accessIdlEqu(2).get(i);
                }
                for (int i = 0; i < number; ++i) {
                    int row = i % 3 * 51;
                    int col = i / 3 * 51;
                    if (Goodslist[i + page * number] != null) {
                        g.drawImage(imgpath(Goodslist[i + page * number].getSkin()).getImage(), x + row + 2, y + col + 2, 45, 45, null);
                        if (Goodslist[i + page * number].getGoodlock() == 1) {
                            g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + row + 38, y + col + 2, 10, 12, null);
                        }
                    }
                }
            }
        } else if (AccessSuitMsgUntil.accessIdlEqu(2) != null && AccessSuitMsgUntil.accessIdlEqu(2).size() > 0) {
            int size = (AccessSuitMsgUntil.accessIdlEqu(2).size() / number + ((AccessSuitMsgUntil.accessIdlEqu(2).size() % number == 0) ? 0 : 1)) * number;
            Goodstable[] Goodslist = new Goodstable[size];
            for (int i = 0; i < AccessSuitMsgUntil.accessIdlEqu(2).size(); ++i) {
                Goodslist[i] = (Goodstable) AccessSuitMsgUntil.accessIdlEqu(2).get(i);
            }
            for (int i = 0; i < number; ++i) {
                int row = i % 3 * 51;
                int col = i / 3 * 51;
                if (Goodslist[i + page * number] != null) {
                    g.drawImage(imgpath(Goodslist[i + page * number].getSkin()).getImage(), x + row + 2, y + col + 2, 45, 45, null);
                    if (Goodslist[i + page * number].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + row + 38, y + col + 2, 10, 12, null);
                    }
                }
            }
        }
    }

    public static void shouhu(int rig) {
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getRgid().intValue() == rig) {
                GoodsListFromServerUntil.Goodslist[i] = null;
                return;
            }
        }
    }

    public static void drawIncludedSuit(Graphics g, int x, int y, int suitid) {
        if (MyIsif.getStyle().equals("水墨")) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.white);
            if (suitid <= 0) {
                return;
            }
            List<IncludedPart> parts = AccessSuitMsgUntil.getIncludedMsg(suitid);
            for (int i = 0; i < parts.size(); ++i) {
                if (i > 3) {
                    g.drawImage(imgpath("tz" + suitid + "_" + ((IncludedPart) parts.get(i)).getPartid()).getImage(), x + 18 + (i - 4) * 65, y + 71, 50, 50, null);
                    g.drawString(((IncludedPart) parts.get(i)).getNumber() + "", x + 18 + (i - 4) * 65, y + 81);
                } else {
                    g.drawImage(imgpath("tz" + suitid + "_" + ((IncludedPart) parts.get(i)).getPartid()).getImage(), x + 2 + i * 65, y, 50, 50, null);
                    g.drawString(((IncludedPart) parts.get(i)).getNumber() + "", x + 3 + i * 65, y + 10);
                }
            }
        } else {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.white);
            if (suitid <= 0) {
                return;
            }
            List<IncludedPart> parts = AccessSuitMsgUntil.getIncludedMsg(suitid);
            for (int i = 0; i < parts.size(); ++i) {
                if (i > 4) {
                    g.drawImage(imgpath("tz" + suitid + "_" + ((IncludedPart) parts.get(i)).getPartid()).getImage(), x + 23 + (i - 5) * 64, y + 78, 50, 50, null);
                    g.drawString(((IncludedPart) parts.get(i)).getNumber() + "", x + 20 + (i - 5) * 64, y + 84);
                } else {
                    g.drawImage(imgpath("tz" + suitid + "_" + ((IncludedPart) parts.get(i)).getPartid()).getImage(), x - 11 + i * 58, y + 5, 50, 50, null);
                    g.drawString(((IncludedPart) parts.get(i)).getNumber() + "", x - 7 + i * 58, y + 15);
                }
            }
        }
    }

    public static void drawGemstoneOperationSuit(Graphics g, int number) {
        if (MyIsif.getStyle().equals("水墨")) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.red);
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.BS((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                    g.drawImage(CutButtonImage.getGemstoneIcon(GoodsListFromServerUntil.Goodslist[i].getSkin(), 51, 51).getImage(), 50 + number % 6 * 51, 308 + number / 6 * 51, 48, 48, null);
                    if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i].getType())) {
                        g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 54 + number % 6 * 51, 318 + number / 6 * 51);
                    }
                    if (GoodsListFromServerUntil.Goodslist[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 88 + number % 6 * 51, number / 6 * 51 + 310, 10, 12, null);
                    }
                    if (++number >= 12) {
                        return;
                    }
                }
            }
        } else {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.red);
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.BS((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                    g.drawImage(CutButtonImage.getGemstoneIcon(GoodsListFromServerUntil.Goodslist[i].getSkin(), 51, 51).getImage(), 22 + number % 6 * 51, 320 + number / 6 * 51, 48, 48, null);
                    if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i].getType())) {
                        g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 26 + number % 6 * 51, 330 + number / 6 * 51);
                    }
                    if (GoodsListFromServerUntil.Goodslist[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 60 + number % 6 * 51, number / 6 * 51 + 322, 10, 12, null);
                    }
                    if (++number >= 12) {
                        return;
                    }
                }
            }
        }
    }

    public static void drawGemstoneOperationSuitS(Graphics g, int x, int y, int yss, int number) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        number += yss;
        yss = -yss * 12 - 1;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.BS((long) GoodsListFromServerUntil.Goodslist[i].getType()) && ++yss >= 0) {
                g.drawImage(CutButtonImage.getGemstoneIcon(GoodsListFromServerUntil.Goodslist[i].getSkin(), 51, 51).getImage(), x + yss % 6 * 51, y + yss / 6 * 51, 48, 48, null);
                if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i].getType())) {
                    Font font = new Font("宋体", 0, 16);
                    g.setFont(font);
                    g.setColor(new Color(188, 188, 188));
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 26 + yss % 6 * 51, 330 + yss / 6 * 51);
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 27 + yss % 6 * 51, 330 + yss / 6 * 51);
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 26 + yss % 6 * 51, 330 + yss / 6 * 51);
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 27 + yss % 6 * 51, 330 + yss / 6 * 51);
                }
                if (GoodsListFromServerUntil.Goodslist[i].getType().equals(Long.valueOf(744L))) {
                    Goodstable gemstoneOperationSuit = getGemstoneOperationSuit(number, yss);
                    int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cuttingString(gemstoneOperationSuit, 1)));
                    g.drawString(levelTwo + "级", 2200 + yss % 6 * 51, 332 + yss / 6 * 51);
                } else {
                    Goodstable gemstoneOperationSuit = getGemstoneOperationSuit(number, yss);
                    int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cuttingString(gemstoneOperationSuit, 1)));
                    Font font2 = new Font("宋体", 0, 16);
                    g.setFont(font2);
                    g.setColor(new Color(188, 188, 188));
                    g.drawString(levelTwo + "级", 26 + yss % 6 * 51, 332 + yss / 6 * 51);
                    g.drawString(levelTwo + "级", 27 + yss % 6 * 51, 332 + yss / 6 * 51);
                    g.drawString(levelTwo + "级", 26 + yss % 6 * 51, 332 + yss / 6 * 51);
                    g.drawString(levelTwo + "级", 27 + yss % 6 * 51, 332 + yss / 6 * 51);
                }
                if (GoodsListFromServerUntil.Goodslist[i].getGoodlock() == 1) {
                    g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 60 + yss % 6 * 51, yss / 6 * 51 + 322, 10, 12, null);
                }
                if (yss >= 11) {
                    return;
                }
            }
        }
    }

    public static void drawGemstoneOperationSuitH(Graphics g, int x, int y, int yss, int number) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        number += yss;
        yss = -yss * 12 - 1;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.BS((long) GoodsListFromServerUntil.Goodslist[i].getType()) && ++yss >= 0) {
                g.drawImage(CutButtonImage.getGemstoneIcon(GoodsListFromServerUntil.Goodslist[i].getSkin(), 51, 51).getImage(), x + yss % 6 * 51, y + yss / 6 * 51, 48, 48, null);
                if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i].getType())) {
                    Font font = new Font("宋体", 0, 16);
                    g.setFont(font);
                    g.setColor(new Color(188, 188, 188));
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 54 + yss % 6 * 51, 318 + yss / 6 * 51);
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 55 + yss % 6 * 51, 318 + yss / 6 * 51);
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 54 + yss % 6 * 51, 318 + yss / 6 * 51);
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 55 + yss % 6 * 51, 318 + yss / 6 * 51);
                }
                if (GoodsListFromServerUntil.Goodslist[i].getType().equals(Long.valueOf(744L))) {
                    Goodstable gemstoneOperationSuit = getGemstoneOperationSuit(number, yss);
                    int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cuttingString(gemstoneOperationSuit, 1)));
                    g.drawString(levelTwo + "级", 2200 + number % 6 * 51, 332 + number / 6 * 51);
                } else {
                    Goodstable gemstoneOperationSuit = getGemstoneOperationSuit(number, yss);
                    int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cuttingString(gemstoneOperationSuit, 1)));
                    Font font2 = new Font("宋体", 0, 16);
                    g.setFont(font2);
                    g.setColor(new Color(188, 188, 188));
                    g.drawString(levelTwo + "级", 50 + yss % 6 * 51, 320 + yss / 6 * 51);
                    g.drawString(levelTwo + "级", 51 + yss % 6 * 51, 320 + yss / 6 * 51);
                    g.drawString(levelTwo + "级", 50 + yss % 6 * 51, 320 + yss / 6 * 51);
                    g.drawString(levelTwo + "级", 51 + yss % 6 * 51, 320 + yss / 6 * 51);
                }
                if (GoodsListFromServerUntil.Goodslist[i].getGoodlock() == 1) {
                    g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 88 + yss % 6 * 51, yss / 6 * 51 + 310, 10, 12, null);
                }
                if (yss >= 11) {
                    return;
                }
            }
        }
    }

    public static Goodstable getGemstoneOperationSuit(int ys, int p) {
        ys = -ys * 12 - 1;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.BS((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                ++ys;
                if (p == ys) {
                    return GoodsListFromServerUntil.Goodslist[i];
                }
            }
        }
        return null;
    }

    public static void drawGemstoneSuit(Graphics g, int number, int type) {
        if (MyIsif.getStyle().equals("水墨")) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.red);
            int num = 0;
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.baoshi(type, (long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                    g.drawImage(CutButtonImage.getGemstoneIcon(GoodsListFromServerUntil.Goodslist[i].getSkin(), 48, 48).getImage(), 247 + num * 51, 284, 48, 48, null);
                    if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i].getType())) {
                        g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 251 + num * 51, 284);
                    }
                    if (GoodsListFromServerUntil.Goodslist[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 295 + num * 51, 284, 10, 12, null);
                    }
                    if (++num >= number) {
                        return;
                    }
                }
            }
        } else {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.red);
            int num = 0;
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.baoshi(type, (long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                    g.drawImage(CutButtonImage.getGemstoneIcon(GoodsListFromServerUntil.Goodslist[i].getSkin(), 48, 48).getImage(), 247 + num * 51, 284, 48, 48, null);
                    if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i].getType())) {
                        g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 251 + num * 51, 284);
                    }
                    if (GoodsListFromServerUntil.Goodslist[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 295 + num * 51, 284, 10, 12, null);
                    }
                    if (++num >= number) {
                        return;
                    }
                }
            }
        }
    }

    public static void drawGemstoneSuit(Graphics g, int yss, int number, int type) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font font = new Font("宋体", 0, 16);
        g.setFont(font);
        number += yss;
        yss = -yss * 5;
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.baoshi(type, (long) GoodsListFromServerUntil.Goodslist[i].getType()) && ++yss >= 1) {
                    g.drawImage(CutButtonImage.getGemstoneIcon(GoodsListFromServerUntil.Goodslist[i].getSkin(), 48, 48).getImage(), 194 + yss * 51, 287, 48, 48, null);
                    if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i].getType())) {
                        g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 228 + yss * 51, 295);
                    }
                    Goodstable gemstoneOperationSuit = getGemstoneSuit(number, yss - 1, type);
                    int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cuttingString(gemstoneOperationSuit, 1)));
                    g.setColor(new Color(188, 188, 188));
                    g.drawString(levelTwo + "级", 196 + yss * 51, 297);
                    g.drawString(levelTwo + "级", 197 + yss * 51, 297);
                    g.drawString(levelTwo + "级", 196 + yss * 51, 297);
                    g.drawString(levelTwo + "级", 197 + yss * 51, 297);
                    if (GoodsListFromServerUntil.Goodslist[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 235 + yss * 51, 320, 10, 12, null);
                    }
                    if (yss >= 5) {
                        return;
                    }
                }
            }
        } else {
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.baoshi(type, (long) GoodsListFromServerUntil.Goodslist[i].getType()) && ++yss >= 1) {
                    g.drawImage(CutButtonImage.getGemstoneIcon(GoodsListFromServerUntil.Goodslist[i].getSkin(), 48, 48).getImage(), 182 + yss * 54, 280, 48, 48, null);
                    if (!EquipTool.isEquip(GoodsListFromServerUntil.Goodslist[i].getType())) {
                        g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", 228 + yss * 51, 295);
                    }
                    Goodstable gemstoneOperationSuit = getGemstoneSuit(number, yss - 1, type);
                    int levelTwo = Integer.parseInt((String) Objects.requireNonNull(cuttingString(gemstoneOperationSuit, 1)));
                    g.setColor(new Color(188, 188, 188));
                    g.drawString(levelTwo + "级", 180 + yss * 54, 295);
                    g.drawString(levelTwo + "级", 180 + yss * 54, 295);
                    g.drawString(levelTwo + "级", 180 + yss * 54, 295);
                    g.drawString(levelTwo + "级", 180 + yss * 54, 295);
                    if (GoodsListFromServerUntil.Goodslist[i].getGoodlock() == 1) {
                        g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 208 + yss * 54, 330, 10, 12, null);
                    }
                    if (yss >= 5) {
                        return;
                    }
                }
            }
        }
    }

    public static String cuttingString(Goodstable goods, int num) {
        if (goods.getType().equals(Long.valueOf(744L))) {
            return "1";
        }
        String value = goods.getValue();
        if (value == null) {
            return null;
        }
        String[] split = value.split("\\|");
        String[] split2 = split[num - 1].split("=");
        return split2[1];
    }

    public static Goodstable getGemstoneSuit(int ys, int p, int type) {
        ys = -ys * 5 - 1;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.baoshi(type, (long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                ++ys;
                if (p == ys) {
                    return GoodsListFromServerUntil.Goodslist[i];
                }
            }
        }
        return null;
    }

    public static void drawCBG(Graphics g, int x, int y, int ys) {
        if (MyIsif.getStyle().equals("水墨")) {
            ys = -ys * 30 - 1;
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                Goodstable good = GoodsListFromServerUntil.Goodslist[i];
                if (good != null && good.getGoodlock() != 1 && !AnalysisString.jiaoyi((long) good.getQuality()) && EquipTool.isEquip(good.getType()) && (Goodtype.EquipmentType((long) good.getType()) == -1 || AccessSuitMsgUntil.getExtra(good.getValue(), "套装属性") == null) && ++ys >= 0) {
                    g.drawImage(imgpath(good.getSkin()).getImage(), x + ys % 6 * 51, y + ys / 6 * 51 + 2, 45, 45, null);
                    if (ys >= 29) {
                        return;
                    }
                }
            }
        } else {
            ys = -ys * 30 - 1;
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                Goodstable good = GoodsListFromServerUntil.Goodslist[i];
                if (good != null && good.getGoodlock() != 1 && !AnalysisString.jiaoyi((long) good.getQuality()) && EquipTool.isEquip(good.getType()) && (Goodtype.EquipmentType((long) good.getType()) == -1 || AccessSuitMsgUntil.getExtra(good.getValue(), "套装属性") == null) && ++ys >= 0) {
                    g.drawImage(imgpath(good.getSkin()).getImage(), x + ys % 6 * 51, y + ys / 6 * 51 + 2, 45, 45, null);
                    if (ys >= 29) {
                        return;
                    }
                }
            }
        }
    }

    public static Goodstable getCBG(int ys, int p) {
        if (MyIsif.getStyle().equals("水墨")) {
            p += ys * 30;
            ys = -ys * 30 - 1;
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                Goodstable good = GoodsListFromServerUntil.Goodslist[i];
                if (good != null && EquipTool.isEquip(good.getType()) && !isJY(good)) {
                    ++ys;
                    if (p == ys) {
                        return good;
                    }
                }
            }
        } else {
            p += ys * 30;
            ys = -ys * 30 - 1;
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                Goodstable good = GoodsListFromServerUntil.Goodslist[i];
                if (good != null && EquipTool.isEquip(good.getType()) && !isJY(good)) {
                    ++ys;
                    if (p == ys) {
                        return good;
                    }
                }
            }
        }
        return null;
    }

    public static Goodstable czGBG(BigDecimal rgid) {
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            Goodstable good = GoodsListFromServerUntil.Goodslist[i];
            if (good != null && EquipTool.isEquip(good.getType()) && !isJY(good) && good.getRgid().compareTo(rgid) == 0) {
                return good;
            }
        }
        return null;
    }

    public static void drawPetLock(Graphics g, RoleSummoning roleSummoning) {
        if (MyIsif.getStyle().equals("水墨")) {
            g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 338, 48, 10, 12, null);
        } else {
            g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), 325, 57, 10, 12, null);
        }
    }

    public static Goodstable czgood(BigDecimal rgid) {
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && rgid.compareTo(GoodsListFromServerUntil.Goodslist[i].getRgid()) == 0) {
                return GoodsListFromServerUntil.Goodslist[i];
            }
        }
        return null;
    }

    public static boolean Unloadfushi(BigDecimal id) {
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] == null) {
                GoodsListFromServerUntil.Goodslist[i] = (Goodstable) GoodsListFromServerUntil.fushis.get(id);
                GoodsListFromServerUntil.fushis.remove(id);
                PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                return true;
            }
        }
        return false;
    }

    public static ImageIcon imgpath(String skin) {
        ImageIcon img = CutButtonImage.getImage("img/item/" + skin + ".png", -1, -1);
        return img;
    }

    public static ImageIcon imgpath2(String skin) {
        ImageIcon img = CutButtonImage.getImage("img/item/" + skin + ".png", 43, 43);
        return img;
    }

    public static ImageIcon imgpathAdaptive(String skin, int width, int height) {
        ImageIcon img = CutButtonImage.getImage("img/item/" + skin + ".png", width, height);
        return img;
    }

    public static void PageNumberChange(int number) {
        GoodsListFromServerUntil.Pagenumber = number;
        for (int i = GoodsListFromServerUntil.Pagenumber * 24; i < (GoodsListFromServerUntil.Pagenumber + 1) * 24; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null) {
                GoodsListFromServerUntil.goodimg[i - GoodsListFromServerUntil.Pagenumber * 24] = imgpath(GoodsListFromServerUntil.Goodslist[i].getSkin());
            } else {
                GoodsListFromServerUntil.goodimg[i - GoodsListFromServerUntil.Pagenumber * 24] = null;
            }
        }
    }

    public static Goodstable getEquipmentByRgid(BigDecimal rid) {
        for (int i = 0; i < GoodsListFromServerUntil.suitGoods.size(); ++i) {
            if (((Goodstable) GoodsListFromServerUntil.suitGoods.get(i)).getRgid().compareTo(rid) == 0) {
                return (Goodstable) GoodsListFromServerUntil.suitGoods.get(i);
            }
        }
        return null;
    }

    public static void saveCurrentEquipmentScheme(int equipmentIndex, Goodstable goods) {
        if (StringUtils.isBlank(RoleData.getRoleData().getLoginResult().getEquipments())) {
            return;
        }
        if (equipmentIndex == -1) {
            RoleData.getRoleData().getLoginResult().equipmentsTh((int) TestpackJapnel.tzIndex);
            ZhuFrame.getZhuJpanel().addPrompt("当前套装方案保存成功#51");
        } else {
            RoleData.getRoleData().getLoginResult().equipmentsTh((int) TestpackJapnel.tzIndex, equipmentIndex, (goods != null) ? goods.getRgid() : null);
        }
        String mes = Agreement.getAgreement().rolechangeAgreement("switchEquip@" + RoleData.getRoleData().getLoginResult().getEquipments());
        SendMessageUntil.toServer(mes);
    }

    public static void mutualChange(int Equipmentid, BigDecimal rid) throws Exception {
        if ((int) RoleData.getRoleData().getLoginResult().getFly_id() != 0) {
            ZhuFrame.getZhuJpanel().addPrompt("飞行当中无法更换装备！");
            return;
        }
        if (rid != null) {
            Goodstable goodstable = getEquipmentByRgid(rid);
            if (goodstable == null) {
                return;
            }
            goodstable.setStatus(Integer.valueOf(1));
            GoodsMouslisten.gooduse(goodstable, goodstable, 0);
            GoodsListFromServerUntil.choseGoodsList[Equipmentid] = goodstable;
            GoodsListFromServerUntil.suitGoods.remove(goodstable);
        } else {
            GoodsListFromServerUntil.choseGoodsList[Equipmentid].setStatus(Integer.valueOf(0));
            GoodsMouslisten.gooduse(GoodsListFromServerUntil.choseGoodsList[Equipmentid], 0);
            GoodsListFromServerUntil.suitGoods.add(GoodsListFromServerUntil.choseGoodsList[Equipmentid]);
            GoodsListFromServerUntil.choseGoodsList[Equipmentid] = null;
        }
        RoleProperty.getRoleProperty().equipWearOff();
        Thread.sleep(5L);
        TimeLimit.getLimits().changeSkin();
    }

    public static void mutualChange(int Equipmentid, int good) throws Exception {
        if ((int) RoleData.getRoleData().getLoginResult().getFly_id() != 0) {
            ZhuFrame.getZhuJpanel().addPrompt("飞行当中无法更换装备！");
            return;
        }
        if (Equipmentid != -1 && good != -1) {

            saveCurrentEquipmentScheme(Equipmentid, GoodsListFromServerUntil.Goodslist[good]);
            Goodstable goodstable = null;
            if (GoodsListFromServerUntil.choseGoodsList[Equipmentid] != null) {
                GoodsListFromServerUntil.choseGoodsList[Equipmentid].setStatus(0);
                goodstable = GoodsListFromServerUntil.choseGoodsList[Equipmentid];
            }
            GoodsListFromServerUntil.Goodslist[good].setStatus(1);
            GoodsMouslisten.gooduse(goodstable, GoodsListFromServerUntil.Goodslist[good], 0);
            GoodsListFromServerUntil.choseGoodsList[Equipmentid] = GoodsListFromServerUntil.Goodslist[good];
            GoodsListFromServerUntil.Goodslist[good] = goodstable;
            // 图片显示切换
            if (GoodsListFromServerUntil.Goodslist[good] != null) {
                GoodsListFromServerUntil.goodimg[good - GoodsListFromServerUntil.Pagenumber * 24] = imgpath(GoodsListFromServerUntil.Goodslist[good].getSkin());
            } else {
                GoodsListFromServerUntil.goodimg[good - GoodsListFromServerUntil.Pagenumber * 24] = null;
            }
            String sendmes = Agreement.getAgreement().roleAchieveAgreement("完成功绩=第一次一件装备");
            SendMessageUntil.toServer(sendmes);

            int have = 0;


            int type4 = 0;
            int type5 = 0;
            int type6 = 0;

            int Stype4 = 0;
            int Stype5 = 0;
            int Stype6 = 0;

            int Ptype6 = 0;
            int Ptype7 = 0;
            for (int ii = 0; ii < GoodsListFromServerUntil.choseGoodsList.length; ii++) {
                Goodstable zb = GoodsListFromServerUntil.choseGoodsList[ii];
                if (zb != null) {
                    if (GodEquipment_xian(zb.getType())) {
                        if (zb.getValue() != null && zb.getValue().contains("阶数=")) {
                            String[] vv = zb.getValue().split("\\|");
                            for (int i = 0; i < vv.length; i++) {
                                if (vv[i].contains("阶数=")) {
                                    if (vv[i].split("=")[1].equals("4")) {
                                        type4++;
                                    } else if (vv[i].split("=")[1].equals("5")) {
                                        type5++;
                                    } else if (vv[i].split("=")[1].equals("6")) {
                                        type6++;
                                    }
                                }
                            }
                        }
                    } else if (GodEquipment_God(zb.getType())) {
                        if (zb.getValue() != null && zb.getValue().contains("等级=")) {
                            String[] vv = zb.getValue().split("\\|");
                            for (int i = 0; i < vv.length; i++) {
                                if (vv[i].contains("等级=")) {
                                    if (vv[i].split("=")[1].equals("4")) {
                                        Stype4++;
                                    } else if (vv[i].split("=")[1].equals("5")) {
                                        Stype5++;
                                    } else if (vv[i].split("=")[1].equals("6")) {
                                        Stype6++;
                                    }
                                }
                            }
                        }
                    } else if (Accessories(zb.getType())) {
                        if (zb.getValue() != null && zb.getValue().contains("等级=")) {
                            String[] vv = zb.getValue().split("\\|");
                            for (int i = 0; i < vv.length; i++) {
                                if (vv[i].contains("等级=")) {
                                    if (vv[i].split("=")[1].equals("6")) {
                                        Ptype6++;
                                    } else if (vv[i].split("=")[1].equals("7")) {
                                        Ptype7++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //功绩仙器神兵配饰
            if (type4 > 0) {
                String sendmes4 = Agreement.getAgreement().roleAchieveAgreement("完成功绩=穿" + hanzi(type4) + "件4介");
                SendMessageUntil.toServer(sendmes4);
            }
            if (type5 > 0) {
                String sendmes4 = Agreement.getAgreement().roleAchieveAgreement("完成功绩=穿" + hanzi(type5) + "件5介");
                SendMessageUntil.toServer(sendmes4);
            }
            if (type6 > 0) {
                String sendmes4 = Agreement.getAgreement().roleAchieveAgreement("完成功绩=穿" + hanzi(type6) + "件6介");
                SendMessageUntil.toServer(sendmes4);
            }
            if (Stype4 > 0) {
                String sendmes4 = Agreement.getAgreement().roleAchieveAgreement("完成功绩=穿" + hanzi(Stype4) + "件4级");
                SendMessageUntil.toServer(sendmes4);
            }
            if (Stype5 > 0) {
                String sendmes4 = Agreement.getAgreement().roleAchieveAgreement("完成功绩=穿" + hanzi(Stype5) + "件5级");
                SendMessageUntil.toServer(sendmes4);
            }
            if (Stype6 > 0) {
                String sendmes4 = Agreement.getAgreement().roleAchieveAgreement("完成功绩=穿" + hanzi(Stype6) + "件6级");
                SendMessageUntil.toServer(sendmes4);
            }
            if (Ptype6 > 0) {
                String sendmes4 = Agreement.getAgreement().roleAchieveAgreement("完成功绩=穿" + hanzi(Ptype6) + "件6级配饰");
                SendMessageUntil.toServer(sendmes4);
            }
            if (Ptype7 > 0) {
                String sendmes4 = Agreement.getAgreement().roleAchieveAgreement("完成功绩=穿" + hanzi(Ptype7) + "件7级配饰");
                SendMessageUntil.toServer(sendmes4);
            }
        } else if (good == -1) {
            // 脱装备
            // 判断是身上的装备脱下来
            saveCurrentEquipmentScheme(Equipmentid, null);
            boolean Full = true;
            int i = 0;
            while (i < GoodsListFromServerUntil.Goodslist.length) {
                if (GoodsListFromServerUntil.Goodslist[i] == null) {
                    GoodsListFromServerUntil.choseGoodsList[Equipmentid].setStatus(0);
                    GoodsMouslisten.gooduse(GoodsListFromServerUntil.choseGoodsList[Equipmentid], 0);
                    GoodsListFromServerUntil.Goodslist[i] = GoodsListFromServerUntil.choseGoodsList[Equipmentid];
                    if (i >= GoodsListFromServerUntil.Pagenumber * 24 && i < (GoodsListFromServerUntil.Pagenumber + 1) * 24) {// 判断背包物品位置是否需要显示
                        GoodsListFromServerUntil.goodimg[i - GoodsListFromServerUntil.Pagenumber * 24] = imgpath(GoodsListFromServerUntil.Goodslist[i].getSkin());
                    }
                    GoodsListFromServerUntil.choseGoodsList[Equipmentid] = null;// 装备图片清空
                    Full = false;
                    break;
                } else {
                    ++i;
                }
            }
            if (Full) {// 判断背包是否满了
                ZhuFrame.getZhuJpanel().addPrompt2("背包已满");
                return;
            }
        }
        RoleProperty.getRoleProperty().equipWearOff();
        Thread.sleep(5L);
        TimeLimit.getLimits().changeSkin();
        try {
            EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().showEquipment(null);
        } catch (Exception ex) {
        }
    }

    public static String hanzi(int num) {
        if (num == 1) {
            return "一";
        } else if (num == 2) {
            return "二";
        } else if (num == 3) {
            return "三";
        } else if (num == 4) {
            return "四";
        } else if (num == 5) {
            return "五";
        } else if (num == 6) {
            return "六";
        } else if (num == 7) {
            return "七";
        } else {
            return "零";
        }
    }

    public static Goodstable Getgood(int Position) {
        return GoodsListFromServerUntil.Goodslist[GoodsListFromServerUntil.Pagenumber * 24 + Position];
    }

    public static Goodstable Getgoodintotal(int Position) {
        return GoodsListFromServerUntil.Goodslist[Position];
    }

    public static void Classification(LoginResult role, List<Goodstable> goodstables, String packrecord) {
        if (goodstables.size() != 0 && ((Goodstable) goodstables.get(0)).getRole_id().longValue() != ImageMixDeal.userimg.getRoleShow().getRole_id().longValue()) {
            return;
        }
        GoodExpansion(ImageMixDeal.userimg.getRoleShow().getTurnAround(), ImageMixDeal.userimg.getRoleShow().getAttachPack());
        DDGoodUntil.ddgood.clear();
        GoodsListFromServerUntil.fushis.clear();
        GoodsListFromServerUntil.wingGoodsList.clear();
        GoodsListFromServerUntil.starCardList.clear();
        GoodsListFromServerUntil.starGoods.clear();
        goodsBackpackMissionList.clear();
        for (int i = 0; i < GoodsListFromServerUntil.choseGoodsList.length; ++i) {
            GoodsListFromServerUntil.choseGoodsList[i] = null;
        }
        int s = 0;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        List<String> packs = new ArrayList<>();
        if (DataUtil.isNotEmpty(configure.getRwType())) {
            packs = Arrays.asList(configure.getRwType().split("\\|"));
        }
        for (int j = goodstables.size() - 1; j >= 0; --j) {
            Goodstable goodstable = (Goodstable) goodstables.get(j);
            if (packs.contains(goodstable.getType() + "")) {
                goodsBackpackMissionList.add(goodstable);
                goodstables.remove(j);
                continue;
            }
            if (goodstable.getGoodsname().equals("落霞") || goodstable.getGoodsname().equals("长生") || goodstable.getGoodsname().equals("卧龙")) {
                GoodsListFromServerUntil.petGooods.add(goodstable);
            }
            if ((long) goodstable.getType() == 2255L) {
                if ((int) goodstable.getStatus() == 0) {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().add(goodstable);
                } else {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                    ShouhuPackJpanel.Eqment.add(goodstable);
                }
            }
            if ((long) goodstable.getType() == 8888L) {
                GoodsListFromServerUntil.wingGoodsList.add(goodstable);
                if ((int) goodstable.getStatus() == 1) {
                    GoodsListFromServerUntil.choseGoodsList[12] = goodstable;
                }
                goodstables.remove(j);
            } else if ((long) goodstable.getType() == 520L && ((int) goodstable.getStatus() == 4 || (int) goodstable.getStatus() == 1)) {
                if ((int) goodstable.getStatus() == 1) {
                    GoodsListFromServerUntil.choseGoodsList[13] = goodstable;
                }
                GoodsListFromServerUntil.starCardList.add(goodstable);
                goodstables.remove(j);
            } else if ((long) goodstable.getType() == 8003L) {
                GoodsListFromServerUntil.starGoods.add(goodstable);
                goodstables.remove(j);
            } else if ((int) goodstable.getStatus() == 1) {
                if ((long) goodstable.getType() == 188L || (long) goodstable.getType() == 729L || (long) goodstable.getType() == 750L || Goodtype.baoshi((long) goodstable.getType()) || ((long) goodstable.getType() >= 54L && (long) goodstable.getType() <= 61L) || Goodtype.isSummonEquip((long) goodstable.getType()) || Goodtype.isPalEquip((long) goodstable.getType()) || (long) goodstable.getType() == 2255L || (goodstable.getType() >= 10012 && goodstable.getType() <= 10018))
                {
                    GoodsListFromServerUntil.fushis.put(((Goodstable) goodstables.get(j)).getRgid(), goodstables.get(j));
                }
                else{
                    int Equipment = Goodtype.EquipmentType((long) ((Goodstable) goodstables.get(j)).getType());
                    if (Equipment != -1) {
                        if (Equipment != 10) {
                            GoodsListFromServerUntil.choseGoodsList[Equipment] = (Goodstable) goodstables.get(j);
                            if (goodstables != null && Goodtype.GodEquipment_God((long) ((Goodstable) goodstables.get(j)).getType())) {
                                String value = ((Goodstable) goodstables.get(j)).getValue();
                                if (value != null && value != "") {
                                    String[] vs = value.split("\\|");
                                    int length = vs.length;
                                    int n = 0;
                                    while (n < length) {
                                        String v = vs[n];
                                        String[] kv = v.split("=");
                                        if (kv.length == 2 && kv[0].equals("等级") && kv[1].equals("6")) {
                                            ++s;
                                            break;
                                        } else {
                                            ++n;
                                        }
                                    }
                                }
                            } else if ((goodstables != null && GodEquipment_xian((long) ((Goodstable) goodstables.get(j)).getType())) || Goodtype.GodEquipment_Ding((long) ((Goodstable) goodstables.get(j)).getType())) {
                                String value = ((Goodstable) goodstables.get(j)).getValue();
                                if (value != null && value != "") {
                                    String[] vs = value.split("\\|");
                                    int length2 = vs.length;
                                    int n2 = 0;
                                    while (n2 < length2) {
                                        String v = vs[n2];
                                        String[] kv = v.split("=");
                                        if (kv.length == 2 && kv[0].equals("阶数") && kv[1].equals("6")) {
                                            ++s;
                                            break;
                                        } else {
                                            ++n2;
                                        }
                                    }
                                }
                            }
                        } else if (Equipment == 10 && GoodsListFromServerUntil.choseGoodsList[10] == null) {
                            GoodsListFromServerUntil.choseGoodsList[Equipment] = (Goodstable) goodstables.get(j);
                        } else if (Equipment == 10 && GoodsListFromServerUntil.choseGoodsList[10] != null) {
                            GoodsListFromServerUntil.choseGoodsList[11] = (Goodstable) goodstables.get(j);
                        }
                    } else {
                        goodstable.setUsetime(Integer.valueOf(0));
                        GoodsMouslisten.gooduse(goodstable, 1);
                    }
                }
                goodstables.remove(j);
            } else if (role.isSuitEquipment(((Goodstable) goodstables.get(j)).getRgid())) {
                GoodsListFromServerUntil.suitGoods.add(goodstables.get(j));
                goodstables.remove(j);
            } else if ((int) goodstable.getStatus() == 2) {
                DDGoodUntil.addddgood((Goodstable) goodstables.get(j));
                goodstables.remove(j);
            } else if ((int) goodstable.getUsetime() <= 0) {
                goodstables.remove(j);
            }
        }
        GoodsListFromServerUntil.NB = s;
        tiankeng(goodstables);
        if (packrecord != null && !packrecord.equals("")) {
            try {
                String[] vs2 = packrecord.split("\\|");
                for (int k = 0; k < vs2.length; ++k) {
                    String[] vvs = vs2[k].split("-");
                    int p = Integer.parseInt(vvs[0]);
                    if (p < GoodsListFromServerUntil.Goodslist.length) {
                        BigDecimal rgid = new BigDecimal(vvs[1]);
                        int l = 0;
                        while (l < goodstables.size()) {
                            Goodstable good = (Goodstable) goodstables.get(l);
                            if (good.getRgid().compareTo(rgid) == 0) {
                                if (GoodsListFromServerUntil.Goodslist[p] == null) {
                                    GoodsListFromServerUntil.Goodslist[p] = good;
                                    break;
                                } else {
                                    break;
                                }
                            } else {
                                ++l;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        weidaima(goodstables);
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
    }

    public static boolean tiankeng(Goodstable good) {
        InternalForm form = FormsManagement.getInternalForm2(16);
        if (form != null) {
            Stall stall = ((BoothBoxJframe) form.getFrame()).getBoothboxjpanel().getStall2();
            if (stall != null && (stall.getState() == StallBean.PREPARE || stall.getState() == StallBean.NO)) {
                Commodity[] commodities = stall.getGoodstables();
                int i = 0;
                while (i < commodities.length) {
                    Goodstable goodstable = (commodities[i] != null) ? commodities[i].getGood() : null;
                    if (goodstable != null && good.getRgid().compareTo(goodstable.getRgid()) == 0) {
                        good.goodxh((int) goodstable.getUsetime());
                        if ((int) good.getUsetime() <= 0) {
                            return true;
                        } else {
                            break;
                        }
                    } else {
                        ++i;
                    }
                }
            }
        }
        form = FormsManagement.getInternalForm2(14);
        if (form != null) {
            GoodTrans goodTrans = ((TransJframe) form.getFrame()).getTransJpanel().getGoodTrans();
            if (goodTrans != null && goodTrans.getGoods() != null) {
                int j = 0;
                while (j < goodTrans.getGoods().size()) {
                    Goodstable goodstable2 = (Goodstable) goodTrans.getGoods().get(j);
                    if (good.getRgid().compareTo(goodstable2.getRgid()) == 0) {
                        good.goodxh((int) goodstable2.getUsetime());
                        if ((int) good.getUsetime() <= 0) {
                            return true;
                        } else {
                            break;
                        }
                    } else {
                        ++j;
                    }
                }
            }
        }
        return false;
    }

    public static void tiankeng(List<Goodstable> goods) {
        InternalForm form = FormsManagement.getInternalForm2(16);
        if (form != null) {
            Stall stall = ((BoothBoxJframe) form.getFrame()).getBoothboxjpanel().getStall2();
            if (stall != null && (stall.getState() == StallBean.PREPARE || stall.getState() == StallBean.NO)) {
                Commodity[] commodities = stall.getGoodstables();
                for (int i = 0; i < commodities.length; ++i) {
                    Goodstable goodstable = (commodities[i] != null) ? commodities[i].getGood() : null;
                    if (goodstable != null) {
                        int j = goods.size() - 1;
                        while (j >= 0) {
                            Goodstable good = (Goodstable) goods.get(j);
                            if (good.getRgid().compareTo(goodstable.getRgid()) == 0) {
                                good.goodxh((int) goodstable.getUsetime());
                                if ((int) good.getUsetime() <= 0) {
                                    goods.remove(j);
                                    break;
                                } else {
                                    break;
                                }
                            } else {
                                --j;
                            }
                        }
                    }
                }
            }
        }
        form = FormsManagement.getInternalForm2(14);
        if (form != null) {
            GoodTrans goodTrans = ((TransJframe) form.getFrame()).getTransJpanel().getGoodTrans();
            if (goodTrans != null && goodTrans.getGoods() != null) {
                for (int k = 0; k < goodTrans.getGoods().size(); ++k) {
                    Goodstable goodstable2 = (Goodstable) goodTrans.getGoods().get(k);
                    int l = goods.size() - 1;
                    while (l >= 0) {
                        Goodstable good2 = (Goodstable) goods.get(l);
                        if (good2.getRgid().compareTo(goodstable2.getRgid()) == 0) {
                            good2.goodxh((int) goodstable2.getUsetime());
                            if ((int) good2.getUsetime() <= 0) {
                                goods.remove(l);
                                break;
                            } else {
                                break;
                            }
                        } else {
                            --l;
                        }
                    }
                }
            }
        }
    }

    public static void weidaima(List<Goodstable> goods) {
        List<Integer> you = new ArrayList<>();
        List<Integer> no = new ArrayList<>();
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] == null) {
                no.add(Integer.valueOf(i));
            } else {
                you.add(Integer.valueOf(i));
            }
        }
        for (int i = goods.size() - 1; i >= 0; --i) {
            Goodstable good = (Goodstable) goods.get(i);
            int j = you.size() - 1;
            while (j >= 0) {
                Goodstable good2 = GoodsListFromServerUntil.Goodslist[(int) you.get(j)];
                if (good2.getRgid().longValue() == good.getRgid().longValue()) {
                    GoodsListFromServerUntil.Goodslist[(int) you.get(j)] = good;
                    goods.remove(i);
                    you.remove(j);
                    break;
                } else {
                    --j;
                }
            }
        }
        for (int k = you.size() - 1; k >= 0; --k) {
            no.add(you.get(k));
            GoodsListFromServerUntil.Goodslist[(int) you.get(k)] = null;
        }
        for (int size1 = no.size(), size2 = goods.size(), l = 0; l < size1 && l < size2; ++l) {
            GoodsListFromServerUntil.Goodslist[(int) no.get(l)] = (Goodstable) goods.get(l);
        }
    }

    public static void isSendPackRecord() {
        String sendmes = getPackRecord();
        PackRecord packRecord = RoleData.getRoleData().getPackRecord();
        if (packRecord.getRecord() == null || !packRecord.getRecord().equals(sendmes)) {
            packRecord.setRecord(sendmes);
            sendPackRecord(0, sendmes);
        }
    }

    public static void sendPackRecord(int type, String msg) {
        String sendmes = Agreement.getAgreement().packRecordAgreement(type + msg);
        SendMessageUntil.toServer(sendmes);
    }

    public static String getPackRecord() {
        List<Goodstable> delList = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null) {
                if (GoodsListFromServerUntil.Goodslist[i].getMinute() != null && GoodsListFromServerUntil.Goodslist[i].getCreateTime() != null) {
                    int t = (int) GoodsListFromServerUntil.Goodslist[i].getMinute() * 60000;
                    long timestamp = (long) GoodsListFromServerUntil.Goodslist[i].getCreateTime() + (long) t;
                    if (System.currentTimeMillis() > timestamp) {
                        delList.add(GoodsListFromServerUntil.Goodslist[i]);
                        continue;
                    }
                }
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(i);
                buffer.append("-");
                buffer.append(GoodsListFromServerUntil.Goodslist[i].getRgid());
            }
        }
        for (Goodstable goodstable : delList) {
            ZhuFrame.getZhuJpanel().addPrompt(goodstable.getGoodsname() + "已过使用时间,自动消失!");
            goodstable.setUsetime(Integer.valueOf(0));
            GoodsMouslisten.gooduse(goodstable, 1);
            Deletebiaoid(goodstable.getRgid());
            GoodsMouslisten.goodreplace(-1, -1);
        }
        return buffer.toString();
    }

    public static void arrange() {
        Goodstable[] gs = new Goodstable[24];
        int i = 0;
        Map<Integer, Goodstable> map = new HashMap<>();
        for (int j = GoodsListFromServerUntil.Pagenumber * 24; j < (GoodsListFromServerUntil.Pagenumber + 1) * 24; ++j) {
            Goodstable goodstable = GoodsListFromServerUntil.Goodslist[j];
            if (goodstable != null && goodstable.getGoodlock() == 1) {
                map.put(Integer.valueOf(j), goodstable);
            }
        }
        for (int j = GoodsListFromServerUntil.Pagenumber * 24; j < (GoodsListFromServerUntil.Pagenumber + 1) * 24; ++j) {
            Goodstable goodstable = GoodsListFromServerUntil.Goodslist[j];
            if (goodstable != null && goodstable.getGoodlock() == 0) {
                gs[i++] = goodstable;
            }
        }
        gs = sort(gs);
        i = 0;
        for (int j = GoodsListFromServerUntil.Pagenumber * 24; j < (GoodsListFromServerUntil.Pagenumber + 1) * 24; ++j) {
            GoodsListFromServerUntil.Goodslist[j] = gs[i++];
        }
        map.forEach((k, v)/* java.lang.Integer,org.come.entity.Goodstable, */ -> {
            Goodstable goodstable2 = GoodsListFromServerUntil.Goodslist[(int) k];
            if (goodstable2 != null) {
                int i2 = 0;
                while (i2 < GoodsListFromServerUntil.Goodslist.length) {
                    if (GoodsListFromServerUntil.Goodslist[i2] == null) {
                        GoodsListFromServerUntil.Goodslist[i2] = goodstable2;
                        GoodsListFromServerUntil.Goodslist[(int) k] = v;
                        break;
                    } else {
                        ++i2;
                    }
                }
            } else {
                GoodsListFromServerUntil.Goodslist[(int) k] = v;
            }
            return;
        });
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
    }

    public static void allArrange() {
        Goodstable[] gs = new Goodstable[GoodsListFromServerUntil.Goodslist.length];
        Map<Integer, Goodstable> map = new HashMap<>();
        int i = 0;
        for (int i2 = 0; i2 < GoodsListFromServerUntil.Goodslist.length; ++i2) {
            Goodstable goodstable = GoodsListFromServerUntil.Goodslist[i2];
            if (goodstable != null && goodstable.getGoodlock() == 1) {
                map.put(Integer.valueOf(i2), goodstable);
            }
        }
        for (Goodstable goodstable2 : GoodsListFromServerUntil.Goodslist) {
            if (goodstable2 != null && goodstable2.getGoodlock() == 0) {
                gs[i++] = goodstable2;
            }
        }
        gs = (GoodsListFromServerUntil.Goodslist = sort(gs));
        map.forEach((k, v)/* java.lang.Integer,org.come.entity.Goodstable, */ -> {
            Goodstable goodstable3 = GoodsListFromServerUntil.Goodslist[(int) k];
            if (goodstable3 != null) {
                int i3 = 0;
                while (i3 < GoodsListFromServerUntil.Goodslist.length) {
                    if (GoodsListFromServerUntil.Goodslist[i3] == null) {
                        GoodsListFromServerUntil.Goodslist[i3] = goodstable3;
                        GoodsListFromServerUntil.Goodslist[(int) k] = v;
                        break;
                    } else {
                        ++i3;
                    }
                }
            } else {
                GoodsListFromServerUntil.Goodslist[(int) k] = v;
            }
            return;
        });
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
    }

    public static int chaxunsNewindex(int goodid) {
        for (int j = 0; j < GoodsListFromServerUntil.Goodslist.length; ++j) {
            if (GoodsListFromServerUntil.Goodslist[j] != null && GoodsListFromServerUntil.Goodslist[j].getGoodsid().intValue() == goodid) {
                return j;
            }
        }
        return -1;
    }

    public static int chaxunsNewindex(BigDecimal rgid) {
        for (int j = 0; j < GoodsListFromServerUntil.Goodslist.length; ++j) {
            if (GoodsListFromServerUntil.Goodslist[j] != null && GoodsListFromServerUntil.Goodslist[j].getRgid().longValue() == rgid.longValue()) {
                return j;
            }
        }
        return -1;
    }

    public static Goodstable[] sort(Goodstable[] objects) {
        List<Goodstable> list = new ArrayList<>();
        list.addAll(Arrays.asList(objects));
        list = (List) list.stream().filter(goodstable/* org.come.entity.Goodstable, */ -> goodstable != null).sorted(Comparator.comparing(Goodstable::getTypeOrder).thenComparing(Goodstable::getGoodsid)).collect(Collectors.toList());
        return (Goodstable[]) list.toArray(new Goodstable[objects.length]);
    }

    public static void GoodExpansion(int TurnAround, int attachPack) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure) item.get(new BigDecimal(1));
        if (TurnAround >= 4) {
            TurnAround = 4;
        }
        int Total = (1 + TurnAround + attachPack) * 24;
        if (GoodsListFromServerUntil.Goodslist != null) {
            if (Total != GoodsListFromServerUntil.Goodslist.length) {
                Goodstable[] Goodslists = new Goodstable[Total];
                for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                    Goodslists[i] = GoodsListFromServerUntil.Goodslist[i];
                }
                GoodsListFromServerUntil.Goodslist = Goodslists;
            }
        } else {
            GoodsListFromServerUntil.Goodslist = new Goodstable[Total];
        }
        int keyong = Total / 24;
        goodbtn[] ds = TestpackJframe.getTestpackJframe().getJpac().getBtnrights();
        btntype(ds, keyong);
        btntype(ForgeJframe.getForgejframe().getJpanel().getBtnrights(), keyong);
        btntype(GiveJframe.getGivejframe().getGivejpanel().getBtnrights(), keyong);
        btntype(SpotBoxJframe.getSpotBoxJframe().getSpotBoxJpanel().getSpotSellBoxJpanel().getBtnRights(), keyong);
        btntype(TradeJframe.getTradejframe().getTradejpanel().getBtnrights(), keyong);
        if (!configure.getLzjskg().equals("3")) {
            btntype(WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getRefinersJpanel().getBtnrights(), keyong);
        }
        btntype(WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getBtnrights(), keyong);
        btntype(ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getBtnrights(), keyong);
        btntype(CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getBtnrights(), keyong);
        btntype(PetEquipmentJframe.getPetEquipmentJframe().getEquipmentJpanel().getBtnrights(), keyong);
        btntype(TransJframe.getTransJframe().getTransJpanel().getBtnrights(), keyong);
        btntype(AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getBtnrights(), keyong);
        btntype(RuneOperateJframe.getRuneOperateJframe().getOperateJpanel().getBtnrights(), keyong);
        btntype(PawnJfram.getPawnJfram().getPawnjpanel().getBtnrights(), keyong);
        btntype(AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel().getBtnrights(), keyong);
        if (!configure.getLzjskg().equals("3")) {
            TestpackJframe.getTestpackJframe().getJpac().getBtnrights()[GoodsListFromServerUntil.Pagenumber].dianji();
        }
    }

    public static void btntype(goodbtn[] btnrights, int path) {
        for (int i = 0; i < path; ++i) {
            btnrights[i].btntypechange(2);
        }
    }

    public static void newgood(List<Goodstable> goodstables) {
        for (int i = 0; i < goodstables.size(); ++i) {
            int j = 0;
            while (j < GoodsListFromServerUntil.Goodslist.length) {
                if (GoodsListFromServerUntil.Goodslist[j] == null) {
                    GoodsListFromServerUntil.Goodslist[j] = (Goodstable) goodstables.get(i);
                    break;
                } else {
                    ++j;
                }
            }
        }
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
    }

    public static int chongfu(Goodstable goodstable) {
        int goodid = goodstable.getGoodsid().intValue();
        if (!EquipTool.isEquip(goodstable.getType())) {
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i].getGoodsid().intValue() == goodid) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean newgood(Goodstable goodstables) {
        for (int j = 0; j < GoodsListFromServerUntil.Goodslist.length; ++j) {
            if (GoodsListFromServerUntil.Goodslist[j] == null) {
                goodstables.setStatus(Integer.valueOf(0));
                GoodsListFromServerUntil.Goodslist[j] = goodstables;
                PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                return true;
            }
        }
        return false;
    }

    public static List<Integer> chaxuns(int[] goodids) {
        List<Integer> weizhi = new ArrayList<>();
        for (int i = 0; i < goodids.length; ++i) {
            int id = goodids[i];
            int sum = 0;
            for (int j = 0; j < goodids.length; ++j) {
                if (id == goodids[j]) {
                    ++sum;
                }
            }
            int j = 0;
            while (j < GoodsListFromServerUntil.Goodslist.length) {
                if (GoodsListFromServerUntil.Goodslist[j] != null && GoodsListFromServerUntil.Goodslist[j].getGoodsid().intValue() == id && (int) GoodsListFromServerUntil.Goodslist[j].getUsetime() >= sum) {
                    weizhi.add(Integer.valueOf(j));
                    break;
                } else {
                    ++j;
                }
            }
        }
        return (weizhi.size() == goodids.length) ? weizhi : null;
    }

    public static int chaxuns(int goodid) {
        for (int j = 0; j < GoodsListFromServerUntil.Goodslist.length; ++j) {
            if (GoodsListFromServerUntil.Goodslist[j] != null && GoodsListFromServerUntil.Goodslist[j].getGoodsid().intValue() == goodid) {
                return j;
            }
        }
        return -1;
    }

    public static List<Goodstable> getGoodsByGoodsIds(List<BigDecimal> goodsIds) {
        List<Goodstable> goodsList = new ArrayList<>();
        for (int j = 0; j < GoodsListFromServerUntil.Goodslist.length; ++j) {
            if (GoodsListFromServerUntil.Goodslist[j] != null && goodsIds.contains(GoodsListFromServerUntil.Goodslist[j].getGoodsid()) && (int) GoodsListFromServerUntil.Goodslist[j].getUsetime() > 0) {
                goodsList.add(GoodsListFromServerUntil.Goodslist[j]);
            }
        }
        return goodsList;
    }

    public static boolean isContainsGoodsByGoodsIds(List<BigDecimal> goodsIds) {
        List<Goodstable> goodsList = new ArrayList<>();
        for (int j = 0; j < GoodsListFromServerUntil.Goodslist.length; ++j) {
            if (GoodsListFromServerUntil.Goodslist[j] != null && goodsIds.contains(GoodsListFromServerUntil.Goodslist[j].getGoodsid())) {
                return true;
            }
        }
        return false;
    }

    public static List<Goodstable> chaxunss(long type) {
        List<Goodstable> weizhi = new ArrayList<>();
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && (long) GoodsListFromServerUntil.Goodslist[i].getType() == type) {
                weizhi.add(GoodsListFromServerUntil.Goodslist[i]);
            }
        }
        return weizhi;
    }

    public static void Deleted(int goodid) {
        GoodsListFromServerUntil.Goodslist[goodid] = null;
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
    }

    public static void Delete(List<Integer> goodids) {
        GoodsMouslisten.goodarr.setI(1);
        List<Goodstable> list = GoodsMouslisten.goodarr.getList();
        list.clear();
        for (int i = 0; i < goodids.size(); ++i) {
            GoodsListFromServerUntil.Goodslist[(int) goodids.get(i)].goodxh(1);
            if (!list.contains(GoodsListFromServerUntil.Goodslist[(int) goodids.get(i)])) {
                list.add(GoodsListFromServerUntil.Goodslist[(int) goodids.get(i)]);
            }
        }
        String sendmes = Agreement.getAgreement().packchangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(GoodsMouslisten.goodarr));
        SendMessageUntil.toServer(sendmes);
        for (int j = 0; j < goodids.size(); ++j) {
            if (GoodsListFromServerUntil.Goodslist[(int) goodids.get(j)] != null && (int) GoodsListFromServerUntil.Goodslist[(int) goodids.get(j)].getUsetime() <= 0) {
                GoodsListFromServerUntil.Goodslist[(int) goodids.get(j)] = null;
            }
        }
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
    }

    public static List<BigDecimal> Delete2(List<Integer> goodids) {
        List<BigDecimal> list = new ArrayList<>();
        for (int i = 0; i < goodids.size(); ++i) {
            Goodstable good = GoodsListFromServerUntil.Goodslist[(int) goodids.get(i)];
            if (good != null) {
                good.goodxh(1);
                list.add(good.getRgid());
                if ((int) good.getUsetime() <= 0) {
                    GoodsListFromServerUntil.Goodslist[(int) goodids.get(i)] = null;
                }
            }
        }
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
        return list;
    }

    public static int getGoodsPlace(Goodstable goodstable) {
        if (goodstable != null && goodstable.getGoodsid() != null && GoodsListFromServerUntil.Goodslist != null && GoodsListFromServerUntil.Goodslist.length >= 0) {
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i].getGoodsid().compareTo(goodstable.getGoodsid()) == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int getGoodsPlaceById(BigDecimal goodsid) {
        if (goodsid != null && GoodsListFromServerUntil.Goodslist != null && GoodsListFromServerUntil.Goodslist.length >= 0) {
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getGoodsid() != null && GoodsListFromServerUntil.Goodslist[i].getGoodsid().compareTo(goodsid) == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static List<BigDecimal> Delete(int goodids) {
        List<BigDecimal> biaoids = new ArrayList<>();
        if (GoodsListFromServerUntil.Goodslist[goodids] == null) {
            return null;
        }
        biaoids.add(GoodsListFromServerUntil.Goodslist[goodids].getRgid());
        GoodsListFromServerUntil.Goodslist[goodids] = null;
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
        return biaoids;
    }

    public static void Deletebiaoid(BigDecimal biaoid) {
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getRgid().intValue() == biaoid.intValue() && ((int) GoodsListFromServerUntil.Goodslist[i].getUsetime() <= 0 || (int) GoodsListFromServerUntil.Goodslist[i].getStatus() != 0)) {
                GoodsListFromServerUntil.Goodslist[i] = null;
                PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                return;
            }
        }
    }

    public static Goodstable Uerbiaoid(BigDecimal biaoid) {
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null) {
                Goodstable goodstable = GoodsListFromServerUntil.Goodslist[i];
                if (goodstable.getRgid().intValue() == biaoid.intValue()) {
                    goodstable.goodxh(1);
                    if ((int) goodstable.getUsetime() <= 0) {
                        GoodsListFromServerUntil.Goodslist[i] = null;
                        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                    }
                    return goodstable;
                }
            }
        }
        return null;
    }

    public static List<Goodstable> getGoodsListByGoodsId(BigDecimal goodsId) {
        List<Goodstable> goodsList = new ArrayList<>();
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getGoodsid().compareTo(goodsId) == 0) {
                goodsList.add(GoodsListFromServerUntil.Goodslist[i]);
            }
        }
        return goodsList;
    }

    public static Goodstable getRgid(BigDecimal rgid) {
        Goodstable good = (Goodstable) GoodsListFromServerUntil.fushis.get(rgid);
        if (good != null) {
            return good;
        }
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getRgid().compareTo(rgid) == 0) {
                return GoodsListFromServerUntil.Goodslist[i];
            }
        }
        for (int i = 0; i < GoodsListFromServerUntil.choseGoodsList.length; ++i) {
            if (GoodsListFromServerUntil.choseGoodsList[i] != null && GoodsListFromServerUntil.choseGoodsList[i].getRgid().compareTo(rgid) == 0) {
                return GoodsListFromServerUntil.choseGoodsList[i];
            }
        }
        for (int i = GoodsListFromServerUntil.wingGoodsList.size() - 1; i >= 0; --i) {
            good = (Goodstable) GoodsListFromServerUntil.wingGoodsList.get(i);
            if (good.getRgid().compareTo(rgid) == 0) {
                return good;
            }
        }
        for (int i = GoodsListFromServerUntil.starCardList.size() - 1; i >= 0; --i) {
            good = (Goodstable) GoodsListFromServerUntil.starCardList.get(i);
            if (good.getRgid().compareTo(rgid) == 0) {
                return good;
            }
        }
        return null;
    }

    public static boolean isgood(int goodid, int sum) {
        int sysum = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getGoodsid().intValue() == goodid) {
                sysum += (int) GoodsListFromServerUntil.Goodslist[i].getUsetime();
                if (sysum >= sum) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean istype(int type, int sum) {
        int sysum = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getType().intValue() == type) {
                sysum += (int) GoodsListFromServerUntil.Goodslist[i].getUsetime();
                if (sysum >= sum) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean consume(int goodid, int sum) {
        int sysum = sum;
        int i = 0;
        while (i < GoodsListFromServerUntil.Goodslist.length) {
            if (sum <= 0) {
                break;
            } else {
                if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getGoodsid().intValue() == goodid) {
                    Goodstable goodstable = GoodsListFromServerUntil.Goodslist[i];
                    if ((int) goodstable.getUsetime() >= sysum) {
                        sum = 0;
                        goodstable.setUsetime(Integer.valueOf((int) goodstable.getUsetime() - sysum));
                    } else {
                        sum -= (int) goodstable.getUsetime();
                        goodstable.setUsetime(Integer.valueOf(0));
                    }
                    if ((int) goodstable.getUsetime() <= 0) {
                        GoodsListFromServerUntil.Goodslist[i] = null;
                        GoodsMouslisten.gooduse(goodstable, 1);
                    }
                    if (sum <= 0) {
                        break;
                    }
                }
                ++i;
            }
        }
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
        return false;
    }

    public static void tihuan(int one, int two) {
        Goodstable goodstable = GoodsListFromServerUntil.Goodslist[one];
        GoodsListFromServerUntil.Goodslist[one] = GoodsListFromServerUntil.Goodslist[two];
        GoodsListFromServerUntil.Goodslist[two] = goodstable;
        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
    }

    public static int Surplussum(String type, String id, int sum) {
        int size = 0;
        if (!EquipTool.isEquip(Long.valueOf(Long.parseLong(type)))) {
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] == null) {
                    return sum;
                }
                if (id.equals(GoodsListFromServerUntil.Goodslist[i].getGoodsid() + "")) {
                    return sum;
                }
            }
        } else {
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] == null && ++size >= sum) {
                    return sum;
                }
            }
        }
        return size;
    }

    public static boolean sureCanBuyOrNo(String id) {
        boolean sureCanbuOrNo = false;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] == null) {
                sureCanbuOrNo = true;
            } else if (id.equals(GoodsListFromServerUntil.Goodslist[i].getGoodsid() + "") && Surplussum(GoodsListFromServerUntil.Goodslist[i].getType() + "", id, 1) > 0) {
                sureCanbuOrNo = true;
            }
        }
        return sureCanbuOrNo;
    }

    public static void addGood(BigDecimal id, int sum) {
        Goodstable good = (Goodstable) GoodsListFromServerUntil.fushis.get(id);
        if (good != null) {
            good.setUsetime(Integer.valueOf(sum));
            if (sum == 0) {
                GoodsListFromServerUntil.fushis.remove(good);
            }
        }
        if (good != null && (long) good.getType() == 2255L) {
            if ((int) good.getStatus() == 0) {
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().add(good);
            } else {
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                ShouhuPackJpanel.Eqment.add(good);
            }
        }
        for (int i = GoodsListFromServerUntil.Goodslist.length - 1; i >= 0; --i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null) {
                good = GoodsListFromServerUntil.Goodslist[i];
                if (good.getRgid().compareTo(id) == 0) {
                    good.setUsetime(Integer.valueOf(sum));
                    if (tiankeng(good)) {
                        GoodsListFromServerUntil.Goodslist[i] = null;
                        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                    } else if ((int) good.getUsetime() <= 0) {
                        GoodsListFromServerUntil.Goodslist[i] = null;
                        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                    }
                    ZFShow(good);
                    return;
                }
            }
        }
        int i = GoodsListFromServerUntil.wingGoodsList.size() - 1;
        while (i >= 0) {
            good = (Goodstable) GoodsListFromServerUntil.wingGoodsList.get(i);
            if (good.getRgid().compareTo(id) == 0) {
                good.setUsetime(Integer.valueOf(sum));
                if ((int) good.getUsetime() <= 0) {
                    GoodsListFromServerUntil.wingGoodsList.remove(i);
                }
                return;
            } else {
                --i;
            }
        }
        i = GoodsListFromServerUntil.starCardList.size() - 1;
        while (i >= 0) {
            good = (Goodstable) GoodsListFromServerUntil.starCardList.get(i);
            if (good.getRgid().compareTo(id) == 0) {
                good.setUsetime(Integer.valueOf(sum));
                if ((int) good.getUsetime() <= 0) {
                    GoodsListFromServerUntil.starCardList.remove(i);
                }
                return;
            } else {
                --i;
            }
        }
        i = GoodsListFromServerUntil.starGoods.size() - 1;
        while (i >= 0) {
            good = (Goodstable) GoodsListFromServerUntil.starGoods.get(i);
            if (good.getRgid().compareTo(id) == 0) {
                good.setUsetime(Integer.valueOf(sum));
                if ((int) good.getUsetime() <= 0) {
                    GoodsListFromServerUntil.starGoods.remove(i);
                }
                return;
            } else {
                --i;
            }
        }
        for (int j = goodsBackpackMissionList.size() - 1; j >= 0; j--) {
            good = goodsBackpackMissionList.get(j);
            if (good.getRgid().compareTo(id) == 0) {
                good.setUsetime(sum);
                if (good.getUsetime() <= 0) {
                    goodsBackpackMissionList.remove(j);
                }
                return;
            }
        }
    }

    public static void addGood(Goodstable goodstable) {
        if ((long) goodstable.getType() == -1L) {
            RoleData.getRoleData().addTx(-goodstable.getGoodsid().longValue() + "");
            return;
        }
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        List<String> packs = Arrays.asList(configure.getRwType().split("\\|"));
        if (packs.contains(goodstable.getType() + "")) {
            if (DataUtil.isNotEmpty(goodsBackpackMissionList)) {
                boolean isHave = false;
                for (int i = 0; i < goodsBackpackMissionList.size(); i++) {
                    if (goodsBackpackMissionList.get(i).getRgid().compareTo(goodstable.getRgid()) == 0) {
                        goodsBackpackMissionList.get(i).setUsetime(goodstable.getUsetime());
                        isHave = true;
                    }
                }
                if (!isHave) {
                    goodsBackpackMissionList.add(goodstable);
                }
            } else {
                goodsBackpackMissionList.add(goodstable);
            }
            return;
        }
        if ((long) goodstable.getType() == 8888L) {
            addWingGood(goodstable);
            return;
        }
        if ((long) goodstable.getType() == 2255L) {
            if ((int) goodstable.getStatus() == 0) {
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().add(goodstable);
            } else {
                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                ShouhuPackJpanel.Eqment.add(goodstable);
            }
        }
        if ((long) goodstable.getType() == 520L && ((int) goodstable.getStatus() == 4 || (int) goodstable.getStatus() == 1)) {
            addStarCard(goodstable);
            return;
        }
        if ((long) goodstable.getType() == 8003L) {
            addStarSoul(goodstable);
            return;
        }
        if (tiankeng(goodstable)) {
            return;
        }
        if (Goodtype.isSummonEquip((long) goodstable.getType())) {
            JframeSummonEquipMain equipMain = JframeSummonEquipMain.getShowJframeSummonEquipMain();
            if (equipMain != null) {
                JpanelSummonEquipMain jpanelSummonEquipMain = equipMain.getJpanelSummonEquipMain();
                if (jpanelSummonEquipMain.getChooseEquip() != null && jpanelSummonEquipMain.getChooseEquip().compareTo(goodstable.getRgid()) == 0) {
                    jpanelSummonEquipMain.refreshEquipOneImg(goodstable);
                }
            }
        }
        GoodsListFromServerUntil.fushis.remove(goodstable.getRgid());
        if ((int) goodstable.getStatus() == 1) {
            if ((int) goodstable.getUsetime() > 0 && ((long) goodstable.getType() == 188L || (long) goodstable.getType() == 729L || (long) goodstable.getType() == 750L || Goodtype.baoshi((long) goodstable.getType()) || ((long) goodstable.getType() >= 54L && (long) goodstable.getType() <= 61L) || Goodtype.isSummonEquip((long) goodstable.getType()) || Goodtype.isPalEquip((long) goodstable.getType()) || (goodstable.getType() >= 10012 && goodstable.getType() <= 10018))) {
                GoodsListFromServerUntil.fushis.put(goodstable.getRgid(), goodstable);
            }
            int i = GoodsListFromServerUntil.Goodslist.length - 1;
            while (i >= 0) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getRgid().compareTo(goodstable.getRgid()) == 0) {
                    GoodsListFromServerUntil.Goodslist[i] = null;
                    PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                    break;
                } else {
                    --i;
                }
            }
            i = GoodsListFromServerUntil.choseGoodsList.length - 1;
            while (i >= 0) {
                if (GoodsListFromServerUntil.choseGoodsList[i] != null && GoodsListFromServerUntil.choseGoodsList[i].getRgid().compareTo(goodstable.getRgid()) == 0) {
                    GoodsListFromServerUntil.choseGoodsList[i] = goodstable;
                    break;
                } else {
                    --i;
                }
            }
            return;
        } else {
            int ky = -1;
            for (int j = GoodsListFromServerUntil.Goodslist.length - 1; j >= 0; --j) {
                if (GoodsListFromServerUntil.Goodslist[j] == null) {
                    ky = j;
                } else {
                    Goodstable good = GoodsListFromServerUntil.Goodslist[j];
                    if (good.getRgid().compareTo(goodstable.getRgid()) == 0) {
                        ky = j;
                        break;
                    }
                }
            }
            if (ky != -1) {
                String skin = (GoodsListFromServerUntil.Goodslist[ky] != null) ? GoodsListFromServerUntil.Goodslist[ky].getSkin() : null;
                if ((int) goodstable.getUsetime() > 0) {
                    GoodsListFromServerUntil.Goodslist[ky] = goodstable;
                } else {
                    GoodsListFromServerUntil.Goodslist[ky] = null;
                }
                if (GoodsListFromServerUntil.Goodslist[ky] == null || skin == null || !skin.equals(goodstable.getSkin())) {
                    PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                }
                ZFShow(goodstable);
            }
            return;
        }
    }

    public static void ZFShow(Goodstable good) {
        InternalForm form = FormsManagement.getInternalForm2(61);
        if (form == null || !form.getFrame().isVisible()) {
            return;
        }
        WorkshopRefiningCardJpanel cardJpanel = ((WorkshopRefiningJframe) form.getFrame()).getWorkshopRefiningJpanel().getCardJpanel();
        GemRefineryMainJpanel gemRefineryMainJpanel = cardJpanel.getGemRefineryMainJpanel();
        if (gemRefineryMainJpanel != null && gemRefineryMainJpanel.isVisible()) {
            if (gemRefineryMainJpanel.getChooseGoodstable() != null && gemRefineryMainJpanel.getChooseGoodstable().getRgid().compareTo(good.getRgid()) == 0) {
                gemRefineryMainJpanel.refreshChooseGoodstable(good, 1);
            } else {
                gemRefineryMainJpanel.refreshChooseGoodstable(good, 2);
            }
        } else {
            cardJpanel.getEquiJpanel().ResetGood(good);
            if (cardJpanel.getRefinersJpanel() != null) {
                cardJpanel.getRefinersJpanel().ResetGood(good);
            }
        }
    }

    public static void addWingGood(Goodstable goodstable) {
        int i = GoodsListFromServerUntil.wingGoodsList.size() - 1;
        while (i >= 0) {
            Goodstable good = (Goodstable) GoodsListFromServerUntil.wingGoodsList.get(i);
            if (good.getRgid().compareTo(goodstable.getRgid()) == 0) {
                if ((int) goodstable.getUsetime() <= 0) {
                    GoodsListFromServerUntil.wingGoodsList.remove(i);
                } else {
                    GoodsListFromServerUntil.wingGoodsList.set(i, goodstable);
                    if ((int) goodstable.getStatus() == 1) {
                        GoodsListFromServerUntil.choseGoodsList[12] = goodstable;
                        RoleProperty.getRoleProperty().equipWearOff();
                    }
                    if (FormsManagement.getframe(86).isVisible()) {
                        WingMainPanel wingMainPanel = WingMainFrame.getWingMainFrame().getWingMainPanel();
                        if (wingMainPanel.getWingGoods() != null && wingMainPanel.getWingGoods().compareTo(goodstable.getRgid()) == 0) {
                            wingMainPanel.changeChooseWingGoods(goodstable, wingMainPanel.getWingGoodsType());
                        }
                    }
                }
                return;
            } else {
                --i;
            }
        }
        GoodsListFromServerUntil.wingGoodsList.add(goodstable);
    }

    public static void addStarSoul(Goodstable goodstable) {
        int i = GoodsListFromServerUntil.starGoods.size() - 1;
        while (i >= 0) {
            Goodstable good = (Goodstable) GoodsListFromServerUntil.starGoods.get(i);
            if (good.getRgid().compareTo(goodstable.getRgid()) == 0) {
                if ((int) goodstable.getUsetime() <= 0) {
                    GoodsListFromServerUntil.starGoods.remove(i);
                } else {
                    GoodsListFromServerUntil.starGoods.set(i, goodstable);
                }
                return;
            } else {
                --i;
            }
        }
        GoodsListFromServerUntil.starGoods.add(goodstable);
    }

    public static void addStarCard(Goodstable goodstable) {
        if (goodstable.getStatus() == 1) {
            choseGoodsList[13] = goodstable;
        }
        for (int i = starCardList.size() - 1; i >= 0; i--) {
            Goodstable good = starCardList.get(i);
            if (good.getRgid().compareTo(goodstable.getRgid()) == 0) {
                if (goodstable.getUsetime() <= 0) {
                    starCardList.remove(i);
                } else {
                    starCardList.set(i, goodstable);
                }
                if (good.getStatus() == 1 && goodstable.getStatus() == 4) {
                    choseGoodsList[13] = null;
                    RoleProperty.getRoleProperty().equipWearOff();
                }
                JframeStarCardMain jframeStarCardMain = JframeStarCardMain.getJframeSummonEquipMain();
                if (jframeStarCardMain != null) {
                    JpanelStarCardMain jpanelStarCardMain = jframeStarCardMain.getJpanelStarCardMain();
                    if (jpanelStarCardMain.getChooseStarCardId() != null
                            && goodstable.getRgid().compareTo(jpanelStarCardMain.getChooseStarCardId()) == 0) {
                        if (jpanelStarCardMain.getBigType() == 2) {
                            if (jpanelStarCardMain.getSmallType() == 1 || jpanelStarCardMain.getSmallType() == 4
                                    || jpanelStarCardMain.getSmallType() == 2) {
                                jpanelStarCardMain.viewChange(goodstable.getUsetime() <= 0 ? null : goodstable);
                            }
                        } else if (jpanelStarCardMain.getBigType() == 1) {
                            jpanelStarCardMain.attributeImgShow(goodstable.getUsetime() <= 0 ? null : goodstable);
                        }
                    }
                }
                JframeStarTransferMain jframeStarTransferMain = JframeStarTransferMain.getShowJframeStarTransferMain();
                if (jframeStarTransferMain != null) {
                    JpanelStarTransferMain jpanelStarTransferMain = jframeStarTransferMain.getJpanelStarTransferMain();
                    if (jpanelStarTransferMain.getChooseOneId() != null
                            && jpanelStarTransferMain.getChooseOneId().compareTo(goodstable.getRgid()) == 0) {
                        String[] values = goodstable.getValue().split("\\|");
                        String[] split2 = values[3].split("&");
                        for (int j = 0; j < split2.length; j++) {
                            if (split2[j].startsWith("星阵属性")) {
                                String[] split = split2[j].split("=");
                                if (BtnStarCard.isfiveElements(split[1])) {
                                    jpanelStarTransferMain.showStarCardAttribute(0, split,
                                            goodstable.getUsetime() <= 0 ? null : goodstable);
                                    jpanelStarTransferMain.showStarCardAttribute(1, null, null);
                                }
                            }
                        }
                    }
                }
                return;
            }
        }
        starCardList.add(goodstable);
    }

    public static boolean ChangeParts(Goodstable good1, Goodstable good2) {
        int p = -1;
        if (good2 != null) {
            for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
                if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getRgid().compareTo(good2.getRgid()) == 0) {
                    GoodsListFromServerUntil.Goodslist[i] = null;
                    p = i;
                    break;
                }
            }
            good2.setStatus(Integer.valueOf(1));
            GoodsMouslisten.gooduse(good2, 0);
        } else {
            int i = 0;
            while (i < GoodsListFromServerUntil.Goodslist.length) {
                if (GoodsListFromServerUntil.Goodslist[i] != null) {
                    ++i;
                } else {
                    p = i;
                    break;
                }
            }
        }
        if (good1 != null) {
            if (p != -1) {
                (GoodsListFromServerUntil.Goodslist[p] = good1).setStatus(Integer.valueOf(0));
                GoodsMouslisten.gooduse(good1, 0);
            } else {
                return false;
            }
        }
        p -= GoodsListFromServerUntil.Pagenumber * 24;
        if (p >= 0 && p < 24) {
            PageNumberChange(GoodsListFromServerUntil.Pagenumber);
        }
        return true;
    }

    public static boolean isExist(Goodstable good) {
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && good == GoodsListFromServerUntil.Goodslist[i]) {
                if ((int) GoodsListFromServerUntil.Goodslist[i].getUsetime() <= 0) {
                    GoodsListFromServerUntil.Goodslist[i] = null;
                    PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                    return true;
                }
                return false;
            }
        }
        ZhuFrame.getZhuJpanel().addPrompt2("物品已经不在背包了");
        return true;
    }

    public static boolean ischoseExist(Goodstable good) {
        for (int i = 0; i < GoodsListFromServerUntil.choseGoodsList.length; ++i) {
            if (GoodsListFromServerUntil.choseGoodsList[i] != null && good == GoodsListFromServerUntil.choseGoodsList[i]) {
                return false;
            }
        }
        Goodstable goodstable = (Goodstable) GoodsListFromServerUntil.fushis.get(good.getRgid());
        if (goodstable != null && goodstable == good) {
            return false;
        }
        ZhuFrame.getZhuJpanel().addPrompt2("物品已经不在背包了");
        return true;
    }

    public static void stall1(Goodstable goodstable) {
        for (int i = GoodsListFromServerUntil.Goodslist.length - 1; i >= 0; --i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null) {
                Goodstable good = GoodsListFromServerUntil.Goodslist[i];
                if (good.getRgid().compareTo(goodstable.getRgid()) == 0) {
                    GoodsListFromServerUntil.Goodslist[i].setUsetime(Integer.valueOf((int) GoodsListFromServerUntil.Goodslist[i].getUsetime() - (int) goodstable.getUsetime()));
                    if ((int) GoodsListFromServerUntil.Goodslist[i].getUsetime() <= 0) {
                        GoodsListFromServerUntil.Goodslist[i] = null;
                        PageNumberChange(GoodsListFromServerUntil.Pagenumber);
                    }
                    return;
                }
            }
        }
    }

    public static void stall2(Goodstable goodstable) {
        int ky = -1;
        for (int i = GoodsListFromServerUntil.Goodslist.length - 1; i >= 0; --i) {
            if (GoodsListFromServerUntil.Goodslist[i] == null) {
                ky = i;
            } else {
                Goodstable good = GoodsListFromServerUntil.Goodslist[i];
                if (good.getRgid().compareTo(goodstable.getRgid()) == 0) {
                    GoodsListFromServerUntil.Goodslist[i].setUsetime(Integer.valueOf((int) GoodsListFromServerUntil.Goodslist[i].getUsetime() + (int) goodstable.getUsetime()));
                    return;
                }
            }
        }
        if (ky != -1) {
            GoodsListFromServerUntil.Goodslist[ky] = goodstable;
            PageNumberChange(GoodsListFromServerUntil.Pagenumber);
        }
    }

    public static boolean isJY(Goodstable good) {
        if (good.getGoodlock() == 1 || AnalysisString.jiaoyi((long) good.getQuality())) {
            return true;
        }
        if (Goodtype.EquipmentType((long) good.getType()) != -1) {
            if (AccessSuitMsgUntil.getExtra(good.getValue(), BaptizeBtn.Extras[3]) != null) {
                return true;
            }
            if (AccessSuitMsgUntil.getExtra(good.getValue(), BaptizeBtn.Extras[4]) != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGY(Goodstable good) {
        if (good.getGoodlock() == 1 || AnalysisString.geiyu((long) good.getType())) {
            return true;
        }
        if (Goodtype.EquipmentType((long) good.getType()) != -1) {
            if (AccessSuitMsgUntil.getExtra(good.getValue(), BaptizeBtn.Extras[3]) != null) {
                return true;
            }
            if (AccessSuitMsgUntil.getExtra(good.getValue(), BaptizeBtn.Extras[4]) != null) {
                return true;
            }
        }
        return false;
    }

    public static int getGoodNum(BigDecimal sid) {
        int size = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && GoodsListFromServerUntil.Goodslist[i].getGoodsid().compareTo(sid) == 0) {
                size += (int) GoodsListFromServerUntil.Goodslist[i].getUsetime();
            }
        }
        return size;
    }

    public static int getStarSoulNum(BigDecimal sid) {
        int size = 0;
        for (int i = 0; i < GoodsListFromServerUntil.starGoods.size(); ++i) {
            if (((Goodstable) GoodsListFromServerUntil.starGoods.get(i)).getGoodsid().compareTo(sid) == 0) {
                size += (int) ((Goodstable) GoodsListFromServerUntil.starGoods.get(i)).getUsetime();
            }
        }
        return size;
    }

    public static Goodstable getUpgradeGoods(int type, WingMainPanel wingMainPanel) {
        int j = -(wingMainPanel.getPageNumber() - 1) * 15 - 1;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && ++j >= 0 && type == j) {
                return GoodsListFromServerUntil.Goodslist[i];
            }
        }
        return null;
    }

    public static Goodstable getUpgradeGoods(int type, LHMainPanel wingMainPanel) {
        int j = -(wingMainPanel.getPageNumber() - 1) * 15 - 1;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && ++j >= 0 && type == j) {
                return GoodsListFromServerUntil.Goodslist[i];
            }
        }
        return null;
    }

    public static Goodstable getWingUpStarGoods(int type) {
        int number = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.upStarWing((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                if (number == type) {
                    return GoodsListFromServerUntil.Goodslist[i];
                }
                ++number;
            }
        }
        return null;
    }

    public static void getUpgrade(Graphics g, WingMainPanel wingMainPanel, int x, int y) {
        int j = -(wingMainPanel.getPageNumber() - 1) * 15 - 1;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && ++j >= 0) {
                ImageIcon imgpathAdaptive = imgpathAdaptive(GoodsListFromServerUntil.Goodslist[i].getSkin(), 49, 49);
                g.drawImage(imgpathAdaptive.getImage(), x + j % 5 * 51, y + j / 5 * 51, 49, 49, null);
                g.setColor(Color.red);
                g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime().toString(), x + j % 5 * 51 + 4, y + j / 5 * 51 + 10);
                if (j >= 14) {
                    return;
                }
            }
        }
    }

    public static void getGoodsUpStar(Graphics g, WingMainPanel wingMainPanel, int x, int y) {
        int j = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.upStarWing((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                g.drawImage(imgpathAdaptive(GoodsListFromServerUntil.Goodslist[i].getSkin(), 49, 49).getImage(), x + j * 51, y, 49, 49, null);
                g.setColor(Color.red);
                g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime().toString(), x + j * 51 + 4, y + 10);
                if (++j > 4) {
                    return;
                }
            }
        }
    }

    public static void getQualityGoods(Graphics g, WingMainPanel wingMainPanel, int x, int y) {
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(8890));
        if (goodstable != null) {
            g.drawImage(imgpathAdaptive(goodstable.getSkin(), 58, 56).getImage(), x, y, 58, 56, null);
        }
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && (long) GoodsListFromServerUntil.Goodslist[i].getType() == 8890L) {
                wingMainPanel.setChosegoods(GoodsListFromServerUntil.Goodslist[i]);
                g.setColor(Color.red);
                g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime().toString(), x + 2, y + 12);
                return;
            }
        }
        g.setColor(Color.red);
        g.drawString("0", x + 2, y + 12);
    }

    public static void getRecastGoods(Graphics g, WingMainPanel wingMainPanel, int x, int y) {
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(8892));
        if (goodstable != null) {
            g.drawImage(imgpathAdaptive(goodstable.getSkin(), 49, 49).getImage(), x, y, 49, 49, null);
        }
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && (long) GoodsListFromServerUntil.Goodslist[i].getType() == 8892L) {
                wingMainPanel.setChosegoods(GoodsListFromServerUntil.Goodslist[i]);
                g.setColor(Color.red);
                g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime().toString(), x + 2, y + 12);
                return;
            }
        }
        g.setColor(Color.red);
        g.drawString("0", x + 2, y + 12);
    }

    public static void getRecastGoods(Graphics g, LHMainPanel wingMainPanel, int x, int y) {
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(8892));
        if (goodstable != null) {
            g.drawImage(imgpathAdaptive(goodstable.getSkin(), 49, 49).getImage(), x, y, 49, 49, null);
        }
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && (long) GoodsListFromServerUntil.Goodslist[i].getType() == 8892L) {
                wingMainPanel.setChosegoods(GoodsListFromServerUntil.Goodslist[i]);
                g.setColor(Color.red);
                g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime().toString(), x + 2, y + 12);
                return;
            }
        }
        g.setColor(Color.red);
        g.drawString("0", x + 2, y + 12);
    }

    public static void getRefiningGoods(Graphics g, WingMainPanel wingMainPanel, int x, int y) {
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(8893));
        if (goodstable != null) {
            g.drawImage(imgpathAdaptive(goodstable.getSkin(), 58, 56).getImage(), x, y, 58, 56, null);
        }
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && (long) GoodsListFromServerUntil.Goodslist[i].getType() == 8893L) {
                wingMainPanel.setChosegoods(GoodsListFromServerUntil.Goodslist[i]);
                g.setColor(Color.red);
                g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime().toString(), x + 2, y + 12);
                return;
            }
        }
        g.setColor(Color.red);
        g.drawString("0", x + 2, y + 12);
    }

    public static void getRefiningGoods(Graphics g, LHMainPanel wingMainPanel, int x, int y) {
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(7010));
        if (goodstable != null) {
            g.drawImage(imgpathAdaptive(goodstable.getSkin(), 58, 56).getImage(), x, y, 58, 56, null);
        }
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && (long) GoodsListFromServerUntil.Goodslist[i].getType() == 7010L) {
                wingMainPanel.setChosegoods(GoodsListFromServerUntil.Goodslist[i]);
                g.setColor(Color.red);
                g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime().toString(), x + 2, y + 12);
                return;
            }
        }
        g.setColor(Color.red);
        g.drawString("0", x + 2, y + 12);
    }

    public static void drawWingImg(Graphics g, WingMainPanel wingMainPanel, int x, int y) {
        for (int i = 25 * (wingMainPanel.getPageNumber() - 1); i < GoodsListFromServerUntil.wingGoodsList.size(); ++i) {
            ImageIcon imgpathAdaptive = imgpathAdaptive(((Goodstable) GoodsListFromServerUntil.wingGoodsList.get(i)).getSkin(), 49, 49);
            g.drawImage(imgpathAdaptive.getImage(), x + i % 25 % 5 * 51, y + i % 25 / 5 * 51, 49, 49, null);
            if (i >= 25 * wingMainPanel.getPageNumber() - 1) {
                return;
            }
        }
    }

    public static void drawWingImg(Graphics g, LHMainPanel wingMainPanel, int x, int y) {
        for (int i = 25 * (wingMainPanel.getPageNumber() - 1); i < GoodsListFromServerUntil.wingGoodsList.size(); ++i) {
            ImageIcon imgpathAdaptive = imgpathAdaptive(((Goodstable) GoodsListFromServerUntil.wingGoodsList.get(i)).getSkin(), 49, 49);
            g.drawImage(imgpathAdaptive.getImage(), x + i % 25 % 5 * 51, y + i % 25 / 5 * 51, 49, 49, null);
            if (i >= 25 * wingMainPanel.getPageNumber() - 1) {
                return;
            }
        }
    }

    public static void drawSummonGoods(Graphics g, int x, int y) {
        int num = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && (Goodtype.isSummonEquip((long) GoodsListFromServerUntil.Goodslist[i].getType()) || Goodtype.isSummonGoods((long) GoodsListFromServerUntil.Goodslist[i].getType()))) {
                g.drawImage(imgpathAdaptive(GoodsListFromServerUntil.Goodslist[i].getSkin(), 50, 50).getImage(), x + num % 6 * 51, y + num / 6 * 51, 50, 50, null);
                if (Goodtype.isSummonGoods((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                    g.setColor(Color.red);
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", x + 4 + num % 6 * 51, y + 10 + num / 6 * 51);
                }
                if (++num >= 12) {
                    return;
                }
            }
        }
    }

    public static void zhengli() {
        int len = GoodsListFromServerUntil.Goodslist.length;
        if (len < 48) {
            ZhuFrame.getZhuJpanel().addPrompt("#G道具栏整理只会整理第二页及之后的道具栏，你没有可以整理的道具栏");
        } else {
            for (int i = 24; i < len - 1; ++i) {
                int tem = i;
                for (int j = i + 1; j < len; ++j) {
                    if (GoodsListFromServerUntil.Goodslist[j] != null && (GoodsListFromServerUntil.Goodslist[tem] == null || (long) GoodsListFromServerUntil.Goodslist[j].getType() < (long) GoodsListFromServerUntil.Goodslist[tem].getType())) {
                        tem = j;
                    }
                }
                Goodstable temp1 = GoodsListFromServerUntil.Goodslist[i];
                GoodsListFromServerUntil.Goodslist[i] = GoodsListFromServerUntil.Goodslist[tem];
                GoodsListFromServerUntil.Goodslist[tem] = temp1;
            }
            PageNumberChange(GoodsListFromServerUntil.Pagenumber);
        }
    }

    public static Goodstable getSummonGoods(int type) {
        int num = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && (Goodtype.isSummonEquip((long) GoodsListFromServerUntil.Goodslist[i].getType()) || Goodtype.isSummonGoods((long) GoodsListFromServerUntil.Goodslist[i].getType()))) {
                if (type == num) {
                    return GoodsListFromServerUntil.Goodslist[i];
                }
                ++num;
            }
        }
        return null;
    }

    public static void drawStarCardImg(Graphics g, int x, int y) {
        for (int i = 0; i < GoodsListFromServerUntil.starCardList.size(); ++i) {
            Goodstable goodstable = (Goodstable) GoodsListFromServerUntil.starCardList.get(i);
            g.drawImage(imgpathAdaptive(goodstable.getSkin(), 50, 50).getImage(), x + i % 4 * 51, y + i / 4 * 51, 50, 50, null);
            if ((int) goodstable.getStatus() == 1) {
                g.drawImage(CutButtonImage.getImage("img/lingbao/msg/lf_g.png", 26, 26).getImage(), x + 24 + i % 4 * 51, y + i / 4 * 51, 26, 26, null);
            }
            if (goodstable.getGoodlock() == 1) {
                g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + i % 4 * 51, y + i / 4 * 51, 10, 12, null);
            }
            if (i >= 23) {
                return;
            }
        }
    }

    public static void drawStarCardImg(Graphics g, int x, int y, ArrayList<BigDecimal> num) {
        for (int i = 0; i < GoodsListFromServerUntil.starCardList.size(); ++i) {
            Goodstable goodstable = (Goodstable) GoodsListFromServerUntil.starCardList.get(i);
            g.drawImage(imgpathAdaptive(goodstable.getSkin(), 50, 50).getImage(), x + i % 6 * 51, y + i / 6 * 51, 50, 50, null);
            if (num.contains(goodstable.getRgid())) {
                g.drawImage(CutButtonImage.getImage("img/lingbao/msg/lf_g.png", 26, 26).getImage(), x + 24 + i % 6 * 51, y + i / 6 * 51, 26, 26, null);
            }
            if (goodstable.getGoodlock() == 1) {
                g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + i % 6 * 51, y + i / 6 * 51, 10, 12, null);
            }
            if (i >= 23) {
                return;
            }
        }
    }

    public static Goodstable getStarCardGoods(int type) {
        for (int i = 0; i < GoodsListFromServerUntil.starCardList.size(); ++i) {
            if (type == i) {
                return (Goodstable) GoodsListFromServerUntil.starCardList.get(i);
            }
        }
        return null;
    }

    public static void drawStarCardCultivateMaterialImg(Graphics g, long type, int x, int y) {
        int num = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            Goodstable goodstable = GoodsListFromServerUntil.Goodslist[i];
            if (goodstable != null && (long) goodstable.getType() == type) {
                g.drawImage(imgpathAdaptive(goodstable.getSkin(), 50, 50).getImage(), x + num % 6 * 51, y + num / 6 * 51, 50, 50, null);
                g.setColor(Color.red);
                g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", x + 2 + num % 6 * 51, y + 11 + num / 6 * 51);
                if (++num >= 12) {
                    return;
                }
            }
        }
    }

    public static Goodstable getStarCardCultivateMaterialGoods(long type, int nowNum) {
        int num = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            Goodstable goodstable = GoodsListFromServerUntil.Goodslist[i];
            if (goodstable != null && (long) goodstable.getType() == type) {
                if (nowNum == num) {
                    return goodstable;
                }
                ++num;
            }
        }
        return null;
    }

    public static void drawStarArray(Graphics g, int x, int y) {
        for (int i = 0; i < GoodsListFromServerUntil.starCardList.size(); ++i) {
            Goodstable goodstable = (Goodstable) GoodsListFromServerUntil.starCardList.get(i);
            g.drawImage(imgpathAdaptive(goodstable.getSkin(), 50, 50).getImage(), x + i * 51, y, 50, 50, null);
            if (i >= 8) {
                return;
            }
        }
    }

    public static Goodstable getStarArray(int type) {
        for (int i = 0; i < GoodsListFromServerUntil.starCardList.size(); ++i) {
            if (type == i) {
                return (Goodstable) GoodsListFromServerUntil.starCardList.get(i);
            }
            if (i >= 8) {
                return null;
            }
        }
        return null;
    }

    public static void drawPalGoods(int x, int y, Graphics g) {
        int num = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.isPalGoods((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                g.drawImage(imgpath(GoodsListFromServerUntil.Goodslist[i].getSkin()).getImage(), x + num % 3 * 39, y + num / 3 * 40, 38, 39, null);
                if (!Goodtype.isPalEquip((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                    g.setColor(Color.red);
                    g.drawString(GoodsListFromServerUntil.Goodslist[i].getUsetime() + "", x + num % 3 * 39, y + 11 + num / 3 * 40);
                }
                if (++num >= 24) {
                    return;
                }
            }
        }
    }

    public static Goodstable getPalGoods(int type) {
        int num = 0;
        for (int i = 0; i < GoodsListFromServerUntil.Goodslist.length; ++i) {
            if (GoodsListFromServerUntil.Goodslist[i] != null && Goodtype.isPalGoods((long) GoodsListFromServerUntil.Goodslist[i].getType())) {
                if (num == type) {
                    return GoodsListFromServerUntil.Goodslist[i];
                }
                if (++num >= 24) {
                    return null;
                }
            }
        }
        return null;
    }

    public static Goodstable[] getGoodslist() {
        return GoodsListFromServerUntil.Goodslist;
    }

    public static Goodstable[] getChoseGoodsList() {
        return GoodsListFromServerUntil.choseGoodsList;
    }

    public static List<Goodstable> getWingGoodsList() {
        return GoodsListFromServerUntil.wingGoodsList;
    }

    public static Goodstable[] getChose1() {
        return GoodsListFromServerUntil.choseGoodsList;
    }

    public static void setWingGoodsList(List<Goodstable> wingGoodsList) {
        GoodsListFromServerUntil.wingGoodsList = wingGoodsList;
    }

    public static List<Goodstable> getStarCardList() {
        return GoodsListFromServerUntil.starCardList;
    }

    public static Goodstable chaxunsNew(int goodid) {
        for (int j = 0; j < GoodsListFromServerUntil.Goodslist.length; ++j) {
            if (GoodsListFromServerUntil.Goodslist[j] != null && GoodsListFromServerUntil.Goodslist[j].getGoodsid().intValue() == goodid) {
                return GoodsListFromServerUntil.Goodslist[j];
            }
        }
        return null;
    }

    public static void setStarCardList(List<Goodstable> starCardList) {
        GoodsListFromServerUntil.starCardList = starCardList;
    }

    static {
        GoodsListFromServerUntil.Goodslist = new Goodstable[24];
        GoodsListFromServerUntil.choseGoodsList = new Goodstable[14];
        GoodsListFromServerUntil.wingGoodsList = new ArrayList<>();
        GoodsListFromServerUntil.starCardList = new ArrayList<>();
        GoodsListFromServerUntil.starGoods = new ArrayList<>();
        GoodsListFromServerUntil.suitGoods = new ArrayList<>();
        GoodsListFromServerUntil.goodimg = new ImageIcon[24];
        GoodsListFromServerUntil.lockimg = new ImageIcon("img/xy2uiimg/goodorpet_lock.png");
        stallimg = new ImageIcon("inkImg/background/S341.png");
        GoodsListFromServerUntil.NB = 0;
        GoodsListFromServerUntil.petGooods = new ArrayList<>();
        GoodsListFromServerUntil.chatGoods = new HashMap<>();
        GoodsListFromServerUntil.Pagenumber = 0;
        GoodsListFromServerUntil.fushis = new HashMap<>();
        GoodsListFromServerUntil.fushisAll = new HashMap<>();
    }

    public static void Deleted_good(Goodstable goodstable) {
        for (int i = 0; i < Goodslist.length; ++i) {
            if (Goodslist[i] == null || Goodslist[i].getRgid().intValue() != goodstable.getRgid().intValue()) continue;
            GoodsListFromServerUntil.Goodslist[i] = null;
            GoodsListFromServerUntil.PageNumberChange(Pagenumber);
            return;
        }
    }
}
