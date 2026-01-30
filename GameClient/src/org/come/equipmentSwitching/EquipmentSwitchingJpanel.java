package org.come.equipmentSwitching;

import org.come.bean.RoleShow;
import org.come.Jpanel.TestpackJapnel;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.time.TimeLiTXT;
import com.tool.tcp.SpriteFactory;
import com.tool.time.TimeLimit;
import com.tool.image.ImageMixDeal;
import javax.swing.BorderFactory;
import java.awt.FontMetrics;
import sun.awt.SunHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.come.until.GoodsListFromServerUntil;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import java.util.Iterator;
import org.come.until.CutButtonImage;
import java.awt.Font;
import com.updateNew.MyIsif;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.entity.Goodstable;
import com.tool.tcp.Sprite;
import com.tool.tcp.NewPart;
import org.come.bean.PathPoint;
import com.tool.btn.goodbtn;
import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.JpanelOnJalbelBtn;
import org.come.mouslisten.GoodsMouslisten;
import com.tool.btn.GoodAndPetLockBtn;
import com.tool.btn.MouseStyleBtn;
import com.tool.btn.SpecificBtn;
import com.tool.btn.GoodPanelBtn;
import javax.swing.ImageIcon;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EquipmentSwitchingJpanel extends JPanel
{
    private EquipmentSwitchingJpanel equipmentSwitchingJpanel;
    private static JLabel effect;
    private List<ImageIcon> imgIconList;
    private int TeamStateS;
    private GoodPanelBtn btnchangepwd;
    private GoodPanelBtn btnunlock;
    private GoodPanelBtn btnlock;
    private SpecificBtn btnXxzb;
    private static MouseStyleBtn moyex;
    private static GoodPanelBtn clearAll;
    private static GoodPanelBtn xinghun;
    private static GoodPanelBtn yongzhejifen;
    private static GoodPanelBtn recovery;
    private static GoodPanelBtn clearAll1;
    private static EquipmentSwitchingBtn replacement;
    private static List<EquipmentSwitchingBtn> replacementList;
    private GoodAndPetLockBtn btngoodlock;
    private GoodAndPetLockBtn btngoodunlock;
    private JLabel[] choseGoodsJLabel;
    private TakeOffEquipmentSwitchMouslisten[] choseGoodsMouslistens;
    private static JLabel[] GoodsListLabel;
    private GoodsMouslisten[] goodsMouslistens;
    private boolean shoptext;
    private int goodPosition;
    private JLabel labGemIntensify;
    private JLabel hunyinzhi;
    private GoodPanelBtn qingli;
    private GoodPanelBtn hunyin;
    private MouseStyleBtn exchange;
    private JpanelOnJalbelBtn savetheset;
    private FormsOnOffBtn offBtn;
    private JpanelOnJalbelBtn btnstall;
    private int caozuo;
    public goodbtn[] btnrights;
    private static goodbtn spackage;
    private PathPoint pointS;
    private PathPoint pointH;
    private PathPoint pointD;
    private PathPoint point;
    private static int xz;
    private static ImageIcon icon;
    private static ImageIcon iconS;
    private static ImageIcon iconD;
    private static ImageIcon iconx2;
    private static ImageIcon iconx3;
    private static ImageIcon iconx;
    private static ImageIcon iconB;
    private static ImageIcon iconE;
    private static ImageIcon iconW;
    private NewPart part;
    static Sprite map;
    private List<EquipmentSwitchingBtn> EquipmentSwitchingMenus;
    private Goodstable[] goodstables;
    private EquipmentSwitchingBtn saveBtn;
    private EquipmentSwitchingBtn clearBtn;
    private EquipmentSwitchingBtn renameBtn;
    private int selectIndex;
    private String menu1;
    private String menu2;
    
    public EquipmentSwitchingJpanel() throws Exception {
        this.selectIndex = 0;
        this.imgIconList = null;
        this.TeamStateS = 0;
        this.choseGoodsJLabel = new JLabel[12];
        this.choseGoodsMouslistens = new TakeOffEquipmentSwitchMouslisten[12];
        this.goodstables = new Goodstable[12];
        this.goodsMouslistens = new GoodsMouslisten[24];
        this.shoptext = false;
        this.caozuo = 0;
        this.setPreferredSize(new Dimension(EquipmentSwitchingJpanel.iconD.getIconWidth(), 561));
        this.setBackground(UIUtils.Color_BACK);
        this.setLayout(null);
        (this.offBtn = new FormsOnOffBtn("inkimg/hongmu/s74.png", 1, 100000)).setBounds(436, 0, 25, 25);
        this.add(this.offBtn);
        this.redis();
        this.getBtnrights();
    }
    
    public void PaintingTextx(int goodPosition) {
        PathPoint pathPoint = this.pathD(goodPosition);
        PathPoint pathPointS = this.pathS(goodPosition);
        PathPoint pathPointH = this.pathH(goodPosition);
        PathPoint pathPointB = this.sanzhiye(goodPosition);
        if (!MyIsif.getStyle().equals("水墨")) {
            this.choseGoodsJLabel[goodPosition].setIcon(EquipmentSwitchingJpanel.iconx2);
            this.choseGoodsJLabel[goodPosition].setBounds(pathPointS.getX() - 14, pathPointS.getY() - 17, 52, 52);
        }
        else {
            this.choseGoodsJLabel[goodPosition].setIcon(EquipmentSwitchingJpanel.iconx2);
            this.choseGoodsJLabel[goodPosition].setBounds(pathPointH.getX() + 22, pathPointH.getY() - 16, 52, 52);
        }
    }
    
    public void redis() {
        this.refMenu();
        this.pointD = new PathPoint();
        this.pointS = new PathPoint();
        this.pointH = new PathPoint();
        this.point = new PathPoint();
        for (int i = 0; i < this.choseGoodsJLabel.length; ++i) {
            this.choseGoodsJLabel[i] = new JLabel();
            PathPoint pathPoint = this.pathH(i);
            this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 50, 50);
            this.choseGoodsMouslistens[i] = new TakeOffEquipmentSwitchMouslisten(i);
            this.choseGoodsJLabel[i].addMouseListener(this.choseGoodsMouslistens[i]);
            this.add(this.choseGoodsJLabel[i]);
        }
        (this.saveBtn = new EquipmentSwitchingBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_RED_BTNTEXT, "保存装备", 2001, new Font("微软雅体", 1, 18), this, Boolean.valueOf(false))).setBounds(35, 270, 100, 26);
        (this.clearBtn = new EquipmentSwitchingBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_RED_BTNTEXT, "清空套装", 2002, new Font("微软雅体", 1, 18), this, Boolean.valueOf(false))).setBounds(155, 270, 100, 26);
        (this.renameBtn = new EquipmentSwitchingBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_RED_BTNTEXT, "改", 2003, new Font("微软雅体", 1, 18), this, Boolean.valueOf(false))).setBounds(200, 150, 100, 26);
        this.add(this.renameBtn);
    }
    
    public String getMenu1() {
        return this.menu1;
    }
    
    public void setMenu1(String menu1) {
        this.menu1 = menu1;
    }
    
    public String getMenu2() {
        return this.menu2;
    }
    
    public void setMenu2(String menu2) {
        this.menu2 = menu2;
    }
    
    public void smEquipmentSwint() {
        EquipmentSwitchingJpanel equipmentSwitchingJpanel = EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel();
        equipmentSwitchingJpanel.setMenu1("inkImg/button1/5-1.png");
        equipmentSwitchingJpanel.setMenu2("inkImg/button1/6-1.png");
        equipmentSwitchingJpanel.getOffBtn().setIcons(CutButtonImage.cuts("inkImg/button/1.png"));
        equipmentSwitchingJpanel.getOffBtn().setBounds(435, 5, 25, 25);
        int k = 0;
        for (EquipmentSwitchingBtn equipmentSwitchingMenu : equipmentSwitchingJpanel.getEquipmentSwitchingMenus()) {
            if (equipmentSwitchingJpanel.getSelectIndex() == k) {
                equipmentSwitchingMenu.setBounds(100 + k * 70, 31, 75, 33);
                equipmentSwitchingMenu.setIcons(CutButtonImage.cuts(equipmentSwitchingJpanel.getMenu2()));
            }
            else {
                equipmentSwitchingMenu.setBounds(100 + k * 70, 31, 75, 33);
                equipmentSwitchingMenu.setIcons(CutButtonImage.cuts(equipmentSwitchingJpanel.getMenu1()));
            }
            equipmentSwitchingMenu.setColors(UIUtils.COLOR_BLACK);
            equipmentSwitchingMenu.setFont(UIUtils.TXT_lianss);
            ++k;
        }
        EquipmentSwitchingBtn saveBtn = equipmentSwitchingJpanel.getSaveBtn();
        EquipmentSwitchingBtn clearBtn = equipmentSwitchingJpanel.getClearBtn();
        EquipmentSwitchingBtn renameBtn = equipmentSwitchingJpanel.getRenameBtn();
        ImageIcon[] cuts = CutButtonImage.cuts("inkImg/button1/B31.png");
        saveBtn.setBounds(35, 285, 99, 24);
        saveBtn.setIcons(cuts);
        clearBtn.setBounds(155, 285, 99, 24);
        clearBtn.setIcons(cuts);
        renameBtn.setBounds(269, 86, 18, 18);
        renameBtn.setIcons(cuts);
        clearBtn.setColors(UIUtils.COLOR_BLACK);
        renameBtn.setColors(UIUtils.COLOR_WHITE);
        saveBtn.setColors(UIUtils.COLOR_BLACK);
        clearBtn.setFont(UIUtils.TEXT_HY88);
        renameBtn.setFont(UIUtils.TEXT_FONT82);
        saveBtn.setFont(UIUtils.TEXT_HY88);
    }
    
    public void hmEquipmentSwint() {
        EquipmentSwitchingJpanel equipmentSwitchingJpanel = EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel();
        equipmentSwitchingJpanel.setMenu1("inkImg/hongmu/tzMenu2.png");
        equipmentSwitchingJpanel.setMenu2("inkImg/hongmu/tzMenu1.png");
        equipmentSwitchingJpanel.getOffBtn().setIcons(CutButtonImage.cuts("inkimg/hongmu/s74.png"));
        equipmentSwitchingJpanel.getOffBtn().setBounds(410, 2, 23, 23);
        int k = 0;
        for (EquipmentSwitchingBtn equipmentSwitchingMenu : equipmentSwitchingJpanel.getEquipmentSwitchingMenus()) {
            if (equipmentSwitchingJpanel.getSelectIndex() == k) {
                equipmentSwitchingMenu.setBounds(50 + k * 80, 27, 80, 40);
                equipmentSwitchingMenu.setIcons(CutButtonImage.cuts(equipmentSwitchingJpanel.getMenu2()));
            }
            else {
                equipmentSwitchingMenu.setBounds(50 + k * 80, 27, 80, 40);
                equipmentSwitchingMenu.setIcons(CutButtonImage.cuts(equipmentSwitchingJpanel.getMenu1()));
            }
            equipmentSwitchingMenu.setColors(UIUtils.COLOR_BTNPUTONG);
            ++k;
        }
        EquipmentSwitchingBtn saveBtn = equipmentSwitchingJpanel.getSaveBtn();
        EquipmentSwitchingBtn clearBtn = equipmentSwitchingJpanel.getClearBtn();
        EquipmentSwitchingBtn renameBtn = equipmentSwitchingJpanel.getRenameBtn();
        ImageIcon[] cuts = CutButtonImage.cuts("inkImg/hongmu/B31h.png");
        saveBtn.setBounds(35, 285, 100, 26);
        saveBtn.setIcons(cuts);
        clearBtn.setBounds(155, 285, 100, 26);
        clearBtn.setIcons(cuts);
        renameBtn.setBounds(244, 74, 100, 26);
        renameBtn.setIcons(cuts);
        clearBtn.setColors(UIUtils.COLOR_BTNPUTONG);
        renameBtn.setColors(UIUtils.COLOR_BTNPUTONG);
        saveBtn.setColors(UIUtils.COLOR_BTNPUTONG);
        clearBtn.setFont(UIUtils.TEXT_FONT82);
        renameBtn.setFont(UIUtils.TEXT_FONT82);
        saveBtn.setFont(UIUtils.TEXT_FONT82);
    }
    
    public void showEquipment(Integer index) {
        if (index == null) {
            index = Integer.valueOf(this.selectIndex);
        }
        this.goodstables = new Goodstable[12];
        String equipments = RoleData.getRoleData().getLoginResult().getEquipments();
        String[] v = equipments.split("\\$");
        String[] split = v[1].split("&");
        int i = 0;
        while (i < this.EquipmentSwitchingMenus.size()) {
            if (i == (int)index) {
                this.selectIndex = (int)index;
                String[] split2 = split[i].split("#");
                if (split2.length == 2) {
                    String[] split3 = split2[1].split("\\|");
                    for (int j = 0; j < 12; ++j) {
                        String[] split4 = split3[j].split("=");
                        if (split4.length == 2) {
                            Goodstable goods = GoodsListFromServerUntil.getEquipmentByRgid(new BigDecimal(split4[1]));
                            if (goods == null) {
                                goods = GoodsListFromServerUntil.choseGoodsList[Integer.parseInt(split4[0])];
                            }
                            this.goodstables[Integer.parseInt(split4[0])] = goods;
                        }
                    }
                    break;
                }
                else {
                    break;
                }
            }
            else {
                ++i;
            }
        }
        if (!MyIsif.getStyle().equals("水墨")) {
            this.hmEquipmentSwint();
        }
        else {
            this.smEquipmentSwint();
        }
    }
    
    public void refMenu() {
        if (this.EquipmentSwitchingMenus != null) {
            for (EquipmentSwitchingBtn equipmentSwitchingMenu : this.EquipmentSwitchingMenus) {
                this.remove(equipmentSwitchingMenu);
            }
        }
        String equipments = RoleData.getRoleData().getLoginResult().getEquipments();
        if (StringUtils.isNotBlank(equipments)) {
            String[] v = equipments.split("\\$");
            String[] split = v[1].split("&");
            this.EquipmentSwitchingMenus = new ArrayList<>();
            for (int k = 0; k < split.length; ++k) {
                EquipmentSwitchingBtn replacementMenu;
                if (k == 0) {
                    String[] split2 = split[k].split("#");
                    replacementMenu = new EquipmentSwitchingBtn("inkImg/hongmu/tzMenu1.png", 1, UIUtils.COLOR_BTNPUTONG, split2[0], k, new Font("微软雅体", 1, 18), this) {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2d = (Graphics2D)g.create();
                            g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, SunHints.VALUE_TEXT_ANTIALIAS_ON);
                            g2d.setFont(new Font("微软雅体", 1, 18));
                            FontMetrics fm = g2d.getFontMetrics();
                            int dx = fm.stringWidth(this.getTzName());
                            int width = this.getWidth();
                            if (!MyIsif.getStyle().equals("水墨")) {
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 33);
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 33);
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 33);
                            }
                            else {
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 28);
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 28);
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 28);
                            }
                        }
                    };
                    replacementMenu.setBounds(25 + k * 80, 30, 80, 39);
                    if (split2.length == 2 && split2[1] != null) {
                        String[] split3 = split2[1].split("\\|");
                        for (int i = 0; i < 12; ++i) {
                            String[] split4 = split3[i].split("=");
                            if (split4.length == 2) {
                                Goodstable rgid = GoodsListFromServerUntil.getRgid(new BigDecimal(split4[1]));
                                this.goodstables[Integer.parseInt(split4[0])] = rgid;
                            }
                        }
                    }
                }
                else {
                    String s = split[k].split("#")[0];
                    replacementMenu = new EquipmentSwitchingBtn("inkImg/hongmu/tzMenu2.png", 1, UIUtils.COLOR_BTNPUTONG, s, k, new Font("微软雅体", 1, 18), this) {
                        String text = s;
                        
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2d = (Graphics2D)g.create();
                            g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, SunHints.VALUE_TEXT_ANTIALIAS_ON);
                            g2d.setFont(new Font("微软雅体", 1, 18));
                            FontMetrics fm = g2d.getFontMetrics();
                            int dx = fm.stringWidth(this.getTzName());
                            int width = this.getWidth();
                            if (!MyIsif.getStyle().equals("水墨")) {
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 33);
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 33);
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 33);
                            }
                            else {
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 28);
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 28);
                                g2d.drawString(this.getTzName(), (width - dx) / 2, 28);
                            }
                        }
                    };
                    replacementMenu.setBounds(25 + k * 80, 30, 80, 36);
                }
                this.add(replacementMenu);
                this.EquipmentSwitchingMenus.add(replacementMenu);
            }
        }
    }
    
    public void xuanzhong(Goodstable goodstable, int shopPlace) {
        if (goodstable == null) {
            if (EquipmentSwitchingJpanel.xz != -1) {
                EquipmentSwitchingJpanel.GoodsListLabel[EquipmentSwitchingJpanel.xz].setBorder(null);
            }
            EquipmentSwitchingJpanel.xz = -1;
        }
        else {
            if (EquipmentSwitchingJpanel.xz != -1) {
                EquipmentSwitchingJpanel.GoodsListLabel[EquipmentSwitchingJpanel.xz].setBorder(null);
            }
            EquipmentSwitchingJpanel.xz = shopPlace;
            EquipmentSwitchingJpanel.GoodsListLabel[EquipmentSwitchingJpanel.xz].setBorder(BorderFactory.createLineBorder(Color.red));
        }
    }
    
    public void ClearTextx(int goodPosition) {
        this.choseGoodsJLabel[goodPosition].setIcon(null);
    }
    
    public void PaintingText(int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }
    
    public void ClearText() {
        this.shoptext = false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long se = ImageMixDeal.userimg.getRoleShow().getSpecies_id().longValue();
        if (!MyIsif.getStyle().equals("水墨")) {
            g.drawImage(EquipmentSwitchingJpanel.iconS.getImage(), 0, 0, EquipmentSwitchingJpanel.iconS.getIconWidth(), EquipmentSwitchingJpanel.iconS.getIconHeight(), this);
            if (ImageMixDeal.userimg != null) {
                if (this.goodstables[0] != null) {
                    long w = (long)TimeLimit.good(Integer.parseInt(this.goodstables[0].getSkin()));
                    se |= w << 32;
                    this.part = SpriteFactory.createPart(se, 7, 1, null);
                }
                else {
                    this.part = SpriteFactory.createPart(se, 2, 1, null);
                }
                this.part.draw(g, 200, 210, 4, ImageMixDeal.userimg.getTime());
            }
        }
        else {
            g.drawImage(EquipmentSwitchingJpanel.iconD.getImage(), 0, 0, EquipmentSwitchingJpanel.iconD.getIconWidth(), EquipmentSwitchingJpanel.iconD.getIconHeight(), this);
            if (ImageMixDeal.userimg != null) {
                if (GoodsListFromServerUntil.getChoseGoodsList()[0] == null && this.part == null) {
                    if (this.part == null) {
                        this.part = SpriteFactory.createPart(se, 2, 1, null);
                    }
                }
                else if (this.part == null) {
                    String getskin = TimeLimit.getskin(TimeLiTXT.getTimeLiTXT().getSkin(), RoleData.getRoleData().getPackRecord().getPutTX(), ImageMixDeal.userimg.getRoleShow());
                    this.changeskin(getskin);
                    this.part.setAct(7);
                }
                else {
                    this.part.setAct(7);
                }
                this.part.draw(g, 236, 230, 4, ImageMixDeal.userimg.getTime());
            }
            GoodsListFromServerUntil.drawLock(g, this, this.goodstables);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 6;
                int shop_y = this.goodPosition / 6;
                g.drawImage(EquipmentSwitchingJpanel.iconx2.getImage(), 22 + shop_x * 51, 274 + shop_y * 51, this);
            }
        }
    }
    
    public int getTeamStateS() {
        return this.TeamStateS;
    }
    
    public void setTeamStatS(int teamStateS) {
        this.TeamStateS = teamStateS;
    }
    
    public List<ImageIcon> getImgIconList() {
        return this.imgIconList;
    }
    
    public void setImgIconList(List<ImageIcon> imgIconList) {
        this.imgIconList = imgIconList;
    }
    
    public static JLabel[] getGoodsListLabel() {
        return EquipmentSwitchingJpanel.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        EquipmentSwitchingJpanel.GoodsListLabel = goodsListLabel;
    }
    
    public GoodsMouslisten[] getGoodsMouslistens() {
        return this.goodsMouslistens;
    }
    
    public void setGoodsMouslistens(GoodsMouslisten[] goodsMouslistens) {
        this.goodsMouslistens = goodsMouslistens;
    }
    
    public boolean isShoptext() {
        return this.shoptext;
    }
    
    public void setShoptext(boolean shoptext) {
        this.shoptext = shoptext;
    }
    
    public int getGoodPosition() {
        return this.goodPosition;
    }
    
    public void setGoodPosition(int goodPosition) {
        this.goodPosition = goodPosition;
    }
    
    public GoodPanelBtn getBtnchangepwd() {
        return this.btnchangepwd;
    }
    
    public void setBtnchangepwd(GoodPanelBtn btnchangepwd) {
        this.btnchangepwd = btnchangepwd;
    }
    
    public GoodPanelBtn getBtnunlock() {
        return this.btnunlock;
    }
    
    public void setBtnunlock(GoodPanelBtn btnunlock) {
        this.btnunlock = btnunlock;
    }
    
    public GoodPanelBtn getBtnlock() {
        return this.btnlock;
    }
    
    public void setBtnlock(GoodPanelBtn btnlock) {
        this.btnlock = btnlock;
    }
    
    public goodbtn[] getBtnrights() {
        if (this.btnrights == null) {
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/hongmu/ss530.png", 0, this, i)).setBounds(329, 273 + i * 35, 24, 34);
                this.btnrights[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        EquipmentSwitchingJpanel.getEffect().setBounds(10000, 10000, 52, 52);
                        super.mouseClicked(e);
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        FormsManagement.showForm(289);
                    }
                });
                this.add(this.btnrights[i]);
            }
        }
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public JLabel getLabGemIntensify() {
        return this.labGemIntensify;
    }
    
    public void setLabGemIntensify(JLabel labGemIntensify) {
        this.labGemIntensify = labGemIntensify;
    }
    
    public static MouseStyleBtn getMoyex() {
        return EquipmentSwitchingJpanel.moyex;
    }
    
    public static void setMoyex(MouseStyleBtn moyex) {
        EquipmentSwitchingJpanel.moyex = moyex;
    }
    
    public static JLabel getEffect() {
        return EquipmentSwitchingJpanel.effect;
    }
    
    public void setEffect(JLabel effect) {
        EquipmentSwitchingJpanel.effect = effect;
    }
    
    public SpecificBtn getBtnXxzb() {
        return this.btnXxzb;
    }
    
    public void setBtnXxzb(SpecificBtn btnXxzb) {
        this.btnXxzb = btnXxzb;
    }
    
    public JLabel[] getChoseGoodsJLabel() {
        return this.choseGoodsJLabel;
    }
    
    public void setChoseGoodsJLabel(JLabel[] choseGoodsJLabel) {
        this.choseGoodsJLabel = choseGoodsJLabel;
    }
    
    public FormsOnOffBtn getOffBtn() {
        return this.offBtn;
    }
    
    public void setOffBtn(FormsOnOffBtn offBtn) {
        this.offBtn = offBtn;
    }
    
    public static int getXz() {
        return EquipmentSwitchingJpanel.xz;
    }
    
    public static void setXz(int xz) {
        EquipmentSwitchingJpanel.xz = xz;
    }
    
    public void setTeamStateS(int teamStateS) {
        this.TeamStateS = teamStateS;
    }
    
    public static GoodPanelBtn getClearAll() {
        return EquipmentSwitchingJpanel.clearAll;
    }
    
    public static void setClearAll(GoodPanelBtn clearAll) {
        EquipmentSwitchingJpanel.clearAll = clearAll;
    }
    
    public static GoodPanelBtn getXinghun() {
        return EquipmentSwitchingJpanel.xinghun;
    }
    
    public static void setXinghun(GoodPanelBtn xinghun) {
        EquipmentSwitchingJpanel.xinghun = xinghun;
    }
    
    public static GoodPanelBtn getYongzhejifen() {
        return EquipmentSwitchingJpanel.yongzhejifen;
    }
    
    public static void setYongzhejifen(GoodPanelBtn yongzhejifen) {
        EquipmentSwitchingJpanel.yongzhejifen = yongzhejifen;
    }
    
    public static GoodPanelBtn getClearAll1() {
        return EquipmentSwitchingJpanel.clearAll1;
    }
    
    public static void setClearAll1(GoodPanelBtn clearAll1) {
        EquipmentSwitchingJpanel.clearAll1 = clearAll1;
    }
    
    public GoodAndPetLockBtn getBtngoodlock() {
        return this.btngoodlock;
    }
    
    public void setBtngoodlock(GoodAndPetLockBtn btngoodlock) {
        this.btngoodlock = btngoodlock;
    }
    
    public GoodAndPetLockBtn getBtngoodunlock() {
        return this.btngoodunlock;
    }
    
    public void setBtngoodunlock(GoodAndPetLockBtn btngoodunlock) {
        this.btngoodunlock = btngoodunlock;
    }
    
    public GoodPanelBtn getQingli() {
        return this.qingli;
    }
    
    public void setQingli(GoodPanelBtn qingli) {
        this.qingli = qingli;
    }
    
    public MouseStyleBtn getExchange() {
        return this.exchange;
    }
    
    public void setExchange(MouseStyleBtn exchange) {
        this.exchange = exchange;
    }
    
    public ImageIcon getIcon() {
        return EquipmentSwitchingJpanel.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        EquipmentSwitchingJpanel.icon = icon;
    }
    
    public ImageIcon getIconS() {
        return EquipmentSwitchingJpanel.iconS;
    }
    
    public void setIconS(ImageIcon iconS) {
        EquipmentSwitchingJpanel.iconS = iconS;
    }
    
    public ImageIcon getIconD() {
        return EquipmentSwitchingJpanel.iconD;
    }
    
    public void setIconD(ImageIcon iconD) {
        EquipmentSwitchingJpanel.iconD = iconD;
    }
    
    public ImageIcon getIconx2() {
        return EquipmentSwitchingJpanel.iconx2;
    }
    
    public void setIconx2(ImageIcon iconx2) {
        EquipmentSwitchingJpanel.iconx2 = iconx2;
    }
    
    public static ImageIcon getIconx() {
        return EquipmentSwitchingJpanel.iconx;
    }
    
    public static void setIconx(ImageIcon iconx) {
        EquipmentSwitchingJpanel.iconx = iconx;
    }
    
    public NewPart getPart() {
        return this.part;
    }
    
    public void setPart(NewPart part) {
        this.part = part;
    }
    
    public static GoodPanelBtn getRecovery() {
        return EquipmentSwitchingJpanel.recovery;
    }
    
    public static void setRecovery(GoodPanelBtn recovery) {
        EquipmentSwitchingJpanel.recovery = recovery;
    }
    
    public EquipmentSwitchingJpanel getEquipmentSwitchingJpanel() {
        return this.equipmentSwitchingJpanel;
    }
    
    public void setEquipmentSwitchingJpanel(EquipmentSwitchingJpanel equipmentSwitchingJpanel) {
        this.equipmentSwitchingJpanel = equipmentSwitchingJpanel;
    }
    
    public JpanelOnJalbelBtn getBtnstall() {
        return this.btnstall;
    }
    
    public void setBtnstall(JpanelOnJalbelBtn btnstall) {
        this.btnstall = btnstall;
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
    
    public JpanelOnJalbelBtn getSavetheset() {
        return this.savetheset;
    }
    
    public void setSavetheset(JpanelOnJalbelBtn savetheset) {
        this.savetheset = savetheset;
    }
    
    public static goodbtn getSpackage() {
        return EquipmentSwitchingJpanel.spackage;
    }
    
    public static void setSpackage(goodbtn spackage) {
        EquipmentSwitchingJpanel.spackage = spackage;
    }
    
    public GoodPanelBtn getHunyin() {
        return this.hunyin;
    }
    
    public void setHunyin(GoodPanelBtn hunyin) {
        this.hunyin = hunyin;
    }
    
    public static EquipmentSwitchingBtn getReplacement() {
        return EquipmentSwitchingJpanel.replacement;
    }
    
    public static void setReplacement(EquipmentSwitchingBtn replacement) {
        EquipmentSwitchingJpanel.replacement = replacement;
    }
    
    public static List<EquipmentSwitchingBtn> getReplacementList() {
        return EquipmentSwitchingJpanel.replacementList;
    }
    
    public static void setReplacementList(List<EquipmentSwitchingBtn> replacementList) {
        EquipmentSwitchingJpanel.replacementList = replacementList;
    }
    
    public List<EquipmentSwitchingBtn> getEquipmentSwitchingMenus() {
        return this.EquipmentSwitchingMenus;
    }
    
    public void setEquipmentSwitchingMenus(List<EquipmentSwitchingBtn> equipmentSwitchingMenus) {
        this.EquipmentSwitchingMenus = equipmentSwitchingMenus;
    }
    
    public EquipmentSwitchingBtn getSaveBtn() {
        return this.saveBtn;
    }
    
    public void setSaveBtn(EquipmentSwitchingBtn saveBtn) {
        this.saveBtn = saveBtn;
    }
    
    public EquipmentSwitchingBtn getClearBtn() {
        return this.clearBtn;
    }
    
    public void setClearBtn(EquipmentSwitchingBtn clearBtn) {
        this.clearBtn = clearBtn;
    }
    
    public EquipmentSwitchingBtn getRenameBtn() {
        return this.renameBtn;
    }
    
    public void setRenameBtn(EquipmentSwitchingBtn renameBtn) {
        this.renameBtn = renameBtn;
    }
    
    public PathPoint sanzhiye(int i) {
        switch (i) {
            case 0: {
                i = 2;
                break;
            }
            case 1: {
                i = 1;
                break;
            }
            case 2: {
                i = 7;
                break;
            }
            case 3: {
                i = 3;
                break;
            }
            case 4: {
                i = 11;
                break;
            }
            case 5: {
                i = 5;
                break;
            }
            case 6: {
                i = 6;
                break;
            }
            case 7: {
                i = 10;
                break;
            }
            case 8: {
                i = 0;
                break;
            }
            case 9: {
                i = 4;
                break;
            }
            case 10: {
                i = 8;
                break;
            }
            case 11: {
                i = 9;
                break;
            }
        }
        if (i == 1) {
            this.point.setX(33);
            this.point.setY(45);
        }
        else if (i == 3) {
            this.point.setX(36);
            this.point.setY(98);
        }
        else if (i == 2) {
            this.point.setX(158);
            this.point.setY(45);
        }
        else if (i == 5) {
            this.point.setX(158);
            this.point.setY(140);
        }
        else if (i == 7) {
            this.point.setX(90);
            this.point.setY(45);
        }
        else if (i == 10) {
            this.point.setX(300);
            this.point.setY(45);
        }
        return this.point;
    }
    
    public PathPoint pathS(int i) {
        switch (i) {
            case 0: {
                i = 2;
                break;
            }
            case 1: {
                i = 1;
                break;
            }
            case 2: {
                i = 7;
                break;
            }
            case 3: {
                i = 3;
                break;
            }
            case 4: {
                i = 11;
                break;
            }
            case 5: {
                i = 5;
                break;
            }
            case 6: {
                i = 6;
                break;
            }
            case 7: {
                i = 10;
                break;
            }
            case 8: {
                i = 0;
                break;
            }
            case 9: {
                i = 4;
                break;
            }
            case 10: {
                i = 8;
                break;
            }
            case 11: {
                i = 9;
                break;
            }
        }
        if (i < 6) {
            this.pointS.setX(45 + i % 2 * 58);
            this.pointS.setY(95 + i / 2 * 55);
        }
        else {
            this.pointS.setX(274 + (i - 6) % 2 * 57);
            this.pointS.setY(94 + (i - 6) / 2 * 56);
        }
        return this.pointS;
    }
    
    public PathPoint pathH(int i) {
        switch (i) {
            case 0: {
                i = 2;
                break;
            }
            case 1: {
                i = 1;
                break;
            }
            case 2: {
                i = 7;
                break;
            }
            case 3: {
                i = 3;
                break;
            }
            case 4: {
                i = 11;
                break;
            }
            case 5: {
                i = 5;
                break;
            }
            case 6: {
                i = 6;
                break;
            }
            case 7: {
                i = 10;
                break;
            }
            case 8: {
                i = 0;
                break;
            }
            case 9: {
                i = 4;
                break;
            }
            case 10: {
                i = 8;
                break;
            }
            case 11: {
                i = 9;
                break;
            }
        }
        if (i < 6) {
            this.pointH.setX(33 + i % 2 * 57);
            this.pointH.setY(95 + i / 2 * 57);
        }
        else {
            this.pointH.setX(299 + (i - 6) % 2 * 57);
            this.pointH.setY(95 + (i - 6) / 2 * 57);
        }
        return this.pointH;
    }
    
    public PathPoint pathD(int i) {
        switch (i) {
            case 0: {
                i = 2;
                break;
            }
            case 1: {
                i = 1;
                break;
            }
            case 2: {
                i = 7;
                break;
            }
            case 3: {
                i = 3;
                break;
            }
            case 4: {
                i = 11;
                break;
            }
            case 5: {
                i = 5;
                break;
            }
            case 6: {
                i = 6;
                break;
            }
            case 7: {
                i = 10;
                break;
            }
            case 8: {
                i = 0;
                break;
            }
            case 9: {
                i = 4;
                break;
            }
            case 10: {
                i = 8;
                break;
            }
            case 11: {
                i = 9;
                break;
            }
        }
        if (i < 6) {
            this.pointD.setX(25 + i % 2 * 58);
            this.pointD.setY(48 + i / 2 * 56);
        }
        else {
            this.pointD.setX(242 + (i - 6) % 2 * 58);
            this.pointD.setY(47 + (i - 6) / 2 * 56);
        }
        return this.pointD;
    }
    
    public void updateGoodstables(Goodstable goods, int index) {
        if ((int)TestpackJapnel.tzIndex == this.selectIndex) {
            this.goodstables[index] = goods;
        }
    }
    
    public Goodstable[] getGoodstables() {
        return this.goodstables;
    }
    
    public void setGoodstables(Goodstable[] goodstables) {
        this.goodstables = goodstables;
    }
    
    public int getSelectIndex() {
        return this.selectIndex;
    }
    
    public void changeskin(String skin) {
        boolean isZJ = false;
        this.part = null;
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow != null && skin != null && !skin.equals("")) {
            String[] vs = skin.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (!vs[i].startsWith("GW") && !vs[i].startsWith("SGW")) {
                    if (vs[i].startsWith("X") || vs[i].startsWith("P")) {
                        String[] ts = vs[i].substring(1).split("_");
                        NewPart newPart = SpriteFactory.createPart("tx/tx" + ts[0], -2, Integer.parseInt(ts[1]), null);
                        if (this.part == null) {
                            this.part = newPart;
                        }
                        else {
                            this.part = this.part.addPart(newPart);
                        }
                        continue;
                    }
                    else if (vs[i].startsWith("S")) {
                        skin = vs[i].substring(1);
                        continue;
                    }
                    else if (vs[i].startsWith("B")) {
                        String cb = vs[i].substring(1);
                        NewPart newPart = SpriteFactory.createPart("tx/" + cb + "0", -2, -5, null);
                        if (this.part == null) {
                            this.part = newPart;
                        }
                        else {
                            this.part = this.part.addPart(newPart);
                        }
                        newPart = SpriteFactory.createPart("tx/" + cb + "1", -2, 5, null);
                        this.part = this.part.addPart(newPart);
                        continue;
                    }
                    else {
                        continue;
                    }
                }
                try {
                    String[] ts = vs[i].substring(vs[i].startsWith("GW") ? 2 : 3).split("_");
                    if (ts != null) {
                        String color = null;
                        if (ts[1].contains("#")) {
                            String[] v = ts[1].split("#");
                            ts[1] = v[0];
                            color = v[1].replace("^", "|");
                        }
                        NewPart newPart2 = SpriteFactory.createPart(Long.parseLong(ts[1]), 2, 2, color);
                        if (this.part == null) {
                            this.part = newPart2;
                        }
                        else {
                            this.part = this.part.addPart(newPart2);
                        }
                    }
                }
                catch (Exception e) {}
            }
        }
        if (skin != null && !skin.equals("")) {
            if (roleShow != null) {
                NewPart newPart3 = SpriteFactory.createPart(skin, 2, 1, null);
                if (this.part == null) {
                    this.part = newPart3;
                }
                else {
                    this.part = this.part.addPart(newPart3);
                }
            }
        }
        else {
            NewPart newPart3 = SpriteFactory.createPart(roleShow.getSpecies_id().longValue(), 2, 1, null);
            if (this.part == null) {
                this.part = newPart3;
            }
            else {
                this.part = this.part.addPart(newPart3);
            }
        }
    }
    
    static {
        EquipmentSwitchingJpanel.effect = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(EquipmentSwitchingJpanel.iconx.getImage(), 0, 0, 50, 50, this);
            }
        };
        EquipmentSwitchingJpanel.GoodsListLabel = new JLabel[24];
        EquipmentSwitchingJpanel.xz = -1;
        EquipmentSwitchingJpanel.icon = new ImageIcon("inkImg/danxin/SS40.png");
        EquipmentSwitchingJpanel.iconD = new ImageIcon("inkImg/background/tz.png");
        EquipmentSwitchingJpanel.iconS = new ImageIcon("inkImg/hongmu1/honghz.png");
        EquipmentSwitchingJpanel.iconx2 = new ImageIcon("inkimg/danxin/border_quack.png");
        EquipmentSwitchingJpanel.iconx3 = new ImageIcon("inkimg/hongmu/ss710.png");
        EquipmentSwitchingJpanel.iconx = new ImageIcon("inkimg/danxin/ss231.png");
        EquipmentSwitchingJpanel.iconB = new ImageIcon("inkImg/hongmu/无配置包裹.png");
        EquipmentSwitchingJpanel.iconE = new ImageIcon("inkImg/background/ss537.png");
        EquipmentSwitchingJpanel.iconW = new ImageIcon("inkImg/danxin/SS538.png");
        EquipmentSwitchingJpanel.map = SpriteFactory.VloadSprite("gires/arms.was", null);
    }
}
