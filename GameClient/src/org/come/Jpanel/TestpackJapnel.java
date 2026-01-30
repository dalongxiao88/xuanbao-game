package org.come.Jpanel;

import org.come.bean.RoleShow;
import org.come.bean.LoginResult;
import com.tool.time.Limit;

import java.awt.*;

import com.tool.time.TimeLiTXT;
import org.come.until.Util;
import com.tool.tcp.SpriteFactory;
import com.tool.time.TimeLimit;
import com.tool.image.ImageMixDeal;

import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import com.tool.role.RoleProperty;
import org.come.Frame.MsgJframe;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.WLLMouslisten;
import com.tool.role.RoleData;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;

import java.util.Map;
import org.come.bean.ConfigureBean;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.equipmentSwitching.EquipmentSwitchingBtn;
import com.tool.btn.SpecificBtn;
import com.tool.tcp.NewPart;
import org.come.bean.PathPoint;
import com.tool.btn.goodbtn;
import org.come.mouslisten.GoodsMouslisten;
import org.come.mouslisten.TakeOffEquipmentMouslisten;
import javax.swing.JLabel;
import com.tool.btn.GoodAndPetLockBtn;
import com.tool.btn.MouseStyleBtn;
import com.tool.btn.GoodPanelBtn;
import javax.swing.ImageIcon;
import java.util.List;
import javax.swing.JPanel;

public class TestpackJapnel extends JPanel
{
    private List<ImageIcon> imgIconList;
    private int TeamStateS;
    private GoodPanelBtn btnchangepwd;
    private GoodPanelBtn btnunlock;
    private static MouseStyleBtn moyex;
    private static GoodPanelBtn clearAll;
    private static GoodPanelBtn clearAll1;
    private GoodPanelBtn sb520;
    private GoodAndPetLockBtn btngoodlock;
    private GoodAndPetLockBtn btngoodunlock;
    private JLabel[] choseGoodsJLabel;
    private final TakeOffEquipmentMouslisten[] choseGoodsMouslistens;
    private JLabel[] GoodsListLabel;
    private GoodsMouslisten[] goodsMouslistens;
    private boolean shoptext;
    private int goodPosition;
    private JLabel labGemIntensify;
    private static GoodPanelBtn qingli;
    private GoodPanelBtn huishou;
    private GoodPanelBtn renwuan;
    private GoodPanelBtn exchange;
    private goodbtn[] btnrights;
    private PathPoint point;
    private ImageIcon icon;
    private NewPart part;
    private JLabel labZfz;
    private GoodPanelBtn btnroleresistance;
    private SpecificBtn btnXxzb,xbBtn;
    private GoodPanelBtn xingpan;
    private SpecificBtn cqbtn;
    private static EquipmentSwitchingBtn replacement;
    private static List<EquipmentSwitchingBtn> replacementList;
    public static Integer tzIndex;
    
    public PathPoint path(int i) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int szz = 5;
        if (configure.getLzjskg() != null) {
            szz = Integer.parseInt(configure.getLzjskg());
        }
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
        if (szz == 3) {
            if (MyIsif.getStyle().equals("水墨")) {
                if (i == 1) {
                    this.point.setX(278);
                    this.point.setY(33);
                }
                else if (i == 2) {
                    this.point.setX(53);
                    this.point.setY(55);
                }
                else if (i == 3) {
                    this.point.setX(278);
                    this.point.setY(50 + i / 2 * 55);
                }
                else if (i == 5) {
                    this.point.setX(281 + i % 2 * 55);
                    this.point.setY(15 + i / 2 * 55);
                }
                else if (i == 7) {
                    this.point.setX(281 + i % 2 * 55);
                    this.point.setY(50);
                }
            }
            else if (i == 1) {
                this.point.setX(278);
                this.point.setY(33);
            }
            else if (i == 2) {
                this.point.setX(53);
                this.point.setY(55);
            }
            else if (i == 3) {
                this.point.setX(278);
                this.point.setY(50 + i / 2 * 55);
            }
            else if (i == 5) {
                this.point.setX(281 + i % 2 * 55);
                this.point.setY(15 + i / 2 * 55);
            }
            else if (i == 7) {
                this.point.setX(281 + i % 2 * 55);
                this.point.setY(50);
            }
        }
        else if (MyIsif.getStyle().equals("水墨")) {
            if (i < 6) {
                this.point.setX(50 + i % 2 * 55);
                this.point.setY(36 + i / 2 * 55);
            }
            else {
                this.point.setX(280 + (i - 6) % 2 * 55);
                this.point.setY(36 + (i - 6) / 2 * 55);
            }
        }
        else if (i < 6) {
            this.point.setX(30 + i % 2 * 56);
            this.point.setY(47 + i / 2 * 56);
        }
        else {
            this.point.setX(260 + (i - 6) % 2 * 56);
            this.point.setY(47 + (i - 6) / 2 * 56);
        }
        return this.point;
    }
    
    public static void showIsTeamBtnS(boolean type, int num) {
        if (num == 0) {
            TestpackJapnel.clearAll.setVisible(type);
            TestpackJapnel.clearAll1.setVisible(type);
            TestpackJapnel.qingli.setVisible(type);
        }
        else if (TestpackJapnel.clearAll.isVisible()) {
            TestpackJapnel.clearAll.setVisible(false);
            TestpackJapnel.clearAll1.setVisible(false);
            TestpackJapnel.qingli.setVisible(false);
        }
        else {
            TestpackJapnel.clearAll.setVisible(true);
            TestpackJapnel.clearAll1.setVisible(true);
            TestpackJapnel.qingli.setVisible(true);
        }
    }
    
    public TestpackJapnel() throws Exception {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int szz = 5;
        if (configure.getLzjskg() != null) {
            szz = Integer.parseInt(configure.getLzjskg());
        }
        if (szz == 3) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.imgIconList = null;
                this.TeamStateS = 0;
                this.choseGoodsJLabel = new JLabel[12];
                this.choseGoodsMouslistens = new TakeOffEquipmentMouslisten[12];
                this.GoodsListLabel = new JLabel[24];
                this.goodsMouslistens = new GoodsMouslisten[24];
                this.shoptext = false;
                this.icon = new ImageIcon("inkImg/background1/B104.png");
                this.sminitEquipmentSwitching();
                this.setPreferredSize(new Dimension(418, 480));
                this.setBackground(UIUtils.Color_BACK);
                this.setLayout(null);
                FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 2);
                offBtn.setBounds(371, 10, 25, 25);
                this.add(offBtn);
                this.point = new PathPoint();
                for (int i = 0; i < this.choseGoodsJLabel.length; ++i) {
                    this.choseGoodsJLabel[i] = new JLabel();
                    PathPoint pathPoint = this.path(i);
                    if (i == 0) {
                        this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 95, 95);
                    }
                    else if (i == 3) {
                        this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 95, 50);
                    }
                    else if (i == 5) {
                        this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 95, 50);
                    }
                    else {
                        this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 95, 50);
                    }
                    this.choseGoodsMouslistens[i] = new TakeOffEquipmentMouslisten(i);
                    this.choseGoodsJLabel[i].addMouseListener(this.choseGoodsMouslistens[i]);
                    this.add(this.choseGoodsJLabel[i]);
                }
                int Flag = 0;
                int count = 1;
                for (int j = 0; j < 24; ++j) {
                    this.GoodsListLabel[j] = new JLabel();
                    this.goodsMouslistens[j] = new GoodsMouslisten(j);
                    this.GoodsListLabel[j].addMouseListener(this.goodsMouslistens[j]);
                    if (Flag < 6 && count == 1) {
                        this.GoodsListLabel[j].setBounds(47 + Flag * 51, 262, 49, 49);
                        ++Flag;
                        this.add(this.GoodsListLabel[j]);
                    }
                    if (Flag < 6 && count == 2) {
                        this.GoodsListLabel[j].setBounds(47 + Flag * 51, 313, 49, 49);
                        ++Flag;
                        this.add(this.GoodsListLabel[j]);
                    }
                    if (Flag < 6 && count == 3) {
                        this.GoodsListLabel[j].setBounds(47 + Flag * 51, 364, 49, 49);
                        ++Flag;
                        this.add(this.GoodsListLabel[j]);
                    }
                    if (Flag < 6 && count == 4) {
                        this.GoodsListLabel[j].setBounds(47 + Flag * 51, 415, 49, 49);
                        ++Flag;
                        this.add(this.GoodsListLabel[j]);
                    }
                    else if (Flag == 6) {
                        Flag = 0;
                        ++count;
                    }
                }
                (this.btnchangepwd = new GoodPanelBtn("inkImg/button1/B31.png", 1, (RoleData.getRoleData().getLoginResult().getPassword() != null) ? "改" : "设", "", "")).addMouseListener(new WLLMouslisten(24));
                this.btnchangepwd.setBounds(219, 180, 18, 18);
                this.add(this.btnchangepwd);
                (this.btnunlock = new GoodPanelBtn("inkImg/button1/B31.png", 1, "解", "", "")).setBounds(241, 180, 18, 18);
                this.add(this.btnunlock);

                (this.btngoodlock = new GoodAndPetLockBtn("inkImg/button1/K156.png", 1, 1)).setBounds(175, 180, 18, 18);
                this.add(this.btngoodlock);
                (this.btngoodunlock = new GoodAndPetLockBtn("inkImg/button1/K157.png", 1, 2)).setBounds(197, 180, 18, 18);
                this.add(this.btngoodunlock);
                this.btnrights = new goodbtn[6];
                for (int j = 0; j < this.btnrights.length; ++j) {
                    (this.btnrights[j] = new goodbtn("inkImg/button1/C0" + (j + 1) + ".png", 0, this, j)).setBounds(353, 259 + j * 35, 35, 31);
                    this.add(this.btnrights[j]);
                }
                (this.labGemIntensify = new JLabel()).setBounds(165, 34, 32, 29);
                this.labGemIntensify.setIcon(GoodsListFromServerUntil.imgpath("gemIntensifyImg"));
                this.labGemIntensify.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        FormsManagement.HideForm(46);
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        PathPoint point = GameJpanel.getGameJpanel().mousepath();
                        MsgJframe.getJframe().getJapnel().zsGemIntensify(RoleProperty.getRoleProperty().getQhv(), point.getX(), point.getY());
                    }
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                });
                this.labGemIntensify.setOpaque(false);
                (TestpackJapnel.qingli = new GoodPanelBtn("inkImg/button/qingli.png", 1, "", "清理")).setBounds(15, 285, 30, 83);
                this.add(TestpackJapnel.qingli);
                (this.huishou = new GoodPanelBtn("inkImg/button/huishou.png", 1, ".")).addMouseListener(new WLLMouslisten(34));
                this.huishou.setBounds(15, 372, 30, 83);
                this.add(this.huishou);
                (TestpackJapnel.clearAll = new GoodPanelBtn("inkImg/button/49.png", 1, "当前整理", "", "")).setBounds(315, 205, 68, 18);
                TestpackJapnel.clearAll.addMouseListener(new WLLMouslisten(17));
                this.add(TestpackJapnel.clearAll);
                (TestpackJapnel.clearAll1 = new GoodPanelBtn("inkImg/button/49.png", 1, "全局整理", "", "")).addMouseListener(new WLLMouslisten(18));
                TestpackJapnel.clearAll1.setBounds(240, 205, 68, 18);
                this.add(TestpackJapnel.clearAll1);
                (this.exchange = new GoodPanelBtn("inkImg/button/2.png", 1, "勇者", "", "")).setBounds(180, 11, 34, 18);
            }
            else {
                this.imgIconList = null;
                this.TeamStateS = 0;
                this.choseGoodsJLabel = new JLabel[12];
                this.choseGoodsMouslistens = new TakeOffEquipmentMouslisten[12];
                this.GoodsListLabel = new JLabel[24];
                this.goodsMouslistens = new GoodsMouslisten[24];
                this.shoptext = false;
                this.icon = new ImageIcon("inkImg/hongmu1/B104h.png");
                this.setPreferredSize(new Dimension(395, 507));
                this.setBackground(UIUtils.Color_BACK);
                this.setLayout(null);
                this.initEquipmentSwitching();
                FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 2);
                offBtn.setBounds(338, 0, 23, 23);
                this.add(offBtn);
                this.point = new PathPoint();
                for (int i = 0; i < this.choseGoodsJLabel.length; ++i) {
                    this.choseGoodsJLabel[i] = new JLabel();
                    PathPoint pathPoint = this.path(i);
                    if (i == 0) {
                        this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 95, 95);
                    }
                    else if (i == 3) {
                        this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 95, 50);
                    }
                    else if (i == 5) {
                        this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 95, 50);
                    }
                    else {
                        this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 95, 50);
                    }
                    this.choseGoodsMouslistens[i] = new TakeOffEquipmentMouslisten(i);
                    this.choseGoodsJLabel[i].addMouseListener(this.choseGoodsMouslistens[i]);
                    this.add(this.choseGoodsJLabel[i]);
                }
                int Flag = 0;
                int count = 1;
                for (int j = 0; j < 24; ++j) {
                    this.GoodsListLabel[j] = new JLabel();
                    this.goodsMouslistens[j] = new GoodsMouslisten(j);
                    this.GoodsListLabel[j].addMouseListener(this.goodsMouslistens[j]);
                    if (Flag < 6 && count == 1) {
                        this.GoodsListLabel[j].setBounds(28 + Flag * 51, 275, 49, 49);
                        ++Flag;
                        this.add(this.GoodsListLabel[j]);
                    }
                    if (Flag < 6 && count == 2) {
                        this.GoodsListLabel[j].setBounds(28 + Flag * 51, 326, 49, 49);
                        ++Flag;
                        this.add(this.GoodsListLabel[j]);
                    }
                    if (Flag < 6 && count == 3) {
                        this.GoodsListLabel[j].setBounds(28 + Flag * 51, 377, 49, 49);
                        ++Flag;
                        this.add(this.GoodsListLabel[j]);
                    }
                    if (Flag < 6 && count == 4) {
                        this.GoodsListLabel[j].setBounds(28 + Flag * 51, 428, 49, 49);
                        ++Flag;
                        this.add(this.GoodsListLabel[j]);
                    }
                    else if (Flag == 6) {
                        Flag = 0;
                        ++count;
                    }
                }
                (this.btnchangepwd = new GoodPanelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, (RoleData.getRoleData().getLoginResult().getPassword() != null) ? "改" : "设")).addMouseListener(new WLLMouslisten(24));
                this.btnchangepwd.setBounds(295, 200, 34, 18);
                this.add(this.btnchangepwd);
                (this.exchange = new GoodPanelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "勇者")).setBounds(205, 173, 34, 18);
                if (configure.getXpgnkg().equals("开")) {
                    (this.btnunlock = new GoodPanelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "解")).setBounds(295, 221, 34, 18);
                    this.add(this.btnunlock);
                }
                else {
                    (this.btnunlock = new GoodPanelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "解")).setBounds(295, 216, 68, 26);
                    this.add(this.btnunlock);
                }
                Font font1 = new Font("楷体", 1, 14);final Color colorx1 = new Color(227, 13, 45);
                labZfz = new JLabel();
                labZfz.setText("Ctrl+右键一键使用物品");
                labZfz.setBounds(225, 12, 100, 14);
                labZfz.setForeground(colorx1);
                labZfz.setFont(font1);
                this.add(labZfz);
                (TestpackJapnel.clearAll = new GoodPanelBtn("inkImg/hongmu/a7.png", 1, "当前整理")).setBounds(188, 221, 68, 17);
                TestpackJapnel.clearAll.addMouseListener(new WLLMouslisten(17));
                this.add(TestpackJapnel.clearAll);
                (TestpackJapnel.clearAll1 = new GoodPanelBtn("inkImg/hongmu/a7.png", 1, "全局整理")).addMouseListener(new WLLMouslisten(18));
                TestpackJapnel.clearAll1.setBounds(188, 201, 68, 17);
                this.add(TestpackJapnel.clearAll1);
                (this.sb520 = new GoodPanelBtn("inkimg/hongmu/border_quac1k.png", 1, " ")).addMouseListener(new WLLMouslisten(33));
                this.sb520.setBounds(65, 201, 145, 45);
                this.add(this.sb520);
                (this.btngoodlock = new GoodAndPetLockBtn("inkImg/hongmu/s75.png", 1, 1)).addMouseListener(new WLLMouslisten(25));
                this.btngoodlock.setBounds(255, 201, 18, 18);
                this.add(this.btngoodlock);
                (this.btngoodunlock = new GoodAndPetLockBtn("inkImg/hongmu/s76.png", 1, 2)).addMouseListener(new WLLMouslisten(26));
                this.btngoodunlock.setBounds(275, 201, 18, 20);
                this.add(this.btngoodunlock);
                this.btnrights = new goodbtn[6];
                for (int k = 0; k < this.btnrights.length; ++k) {
                    (this.btnrights[k] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, k)).setBounds(333, 272 + k * 35, 24, 31);
                    this.add(this.btnrights[k]);
                }
                (TestpackJapnel.qingli = new GoodPanelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "清理")).addMouseListener(new WLLMouslisten(19));
                TestpackJapnel.qingli.setBounds(256, 221, 34, 17);
                this.add(TestpackJapnel.qingli);
                (this.huishou = new GoodPanelBtn("inkImg/hongmu/yjhs.png", 1, ".")).addMouseListener(new WLLMouslisten(34));
                this.huishou.setBounds(260, 460, 70, 29);
                this.add(this.huishou);
                (this.labGemIntensify = new JLabel()).setBounds(200, 38, 32, 29);
                this.labGemIntensify.setIcon(GoodsListFromServerUntil.imgpath("gemIntensifyImg"));
                this.labGemIntensify.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        FormsManagement.HideForm(46);
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        PathPoint point = GameJpanel.getGameJpanel().mousepath();
                        MsgJframe.getJframe().getJapnel().zsGemIntensify(RoleProperty.getRoleProperty().getQhv(), point.getX(), point.getY());
                    }
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                });
                this.labGemIntensify.setOpaque(false);
            }
        }
        else if (MyIsif.getStyle().equals("水墨")) {
            this.imgIconList = null;
            this.TeamStateS = 0;
            this.choseGoodsJLabel = new JLabel[12];
            this.choseGoodsMouslistens = new TakeOffEquipmentMouslisten[12];
            this.GoodsListLabel = new JLabel[24];
            this.goodsMouslistens = new GoodsMouslisten[24];
            this.shoptext = false;
            this.icon = new ImageIcon("inkImg/background1/B103.png");
            this.setPreferredSize(new Dimension(418, 480));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 2);
            offBtn.setBounds(371, 10, 25, 25);
            this.add(offBtn);
            this.sminitEquipmentSwitching();
            this.point = new PathPoint();
            for (int i = 0; i < this.choseGoodsJLabel.length; ++i) {
                this.choseGoodsJLabel[i] = new JLabel();
                PathPoint pathPoint = this.path(i);
                this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 50, 50);
                this.choseGoodsMouslistens[i] = new TakeOffEquipmentMouslisten(i);
                this.choseGoodsJLabel[i].addMouseListener(this.choseGoodsMouslistens[i]);
                this.add(this.choseGoodsJLabel[i]);
            }
            (TestpackJapnel.clearAll = new GoodPanelBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_WHITE1, "当前物品栏整理", null)).setBounds(240, 253, 117, 24);
            TestpackJapnel.clearAll.setVisible(false);
            TestpackJapnel.clearAll.addMouseListener(new WLLMouslisten(17));
            this.add(TestpackJapnel.clearAll);
            (TestpackJapnel.clearAll1 = new GoodPanelBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_WHITE1, "全部物品栏整理", null)).addMouseListener(new WLLMouslisten(18));
            TestpackJapnel.clearAll1.setVisible(false);
            TestpackJapnel.clearAll1.setBounds(240, 277, 117, 24);
            this.add(TestpackJapnel.clearAll1);
            (TestpackJapnel.qingli = new GoodPanelBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_WHITE1, "当前物品栏清理", null)).setVisible(false);
            TestpackJapnel.qingli.setBounds(240, 301, 117, 24);
            this.add(TestpackJapnel.qingli);
            (TestpackJapnel.moyex = new MouseStyleBtn("inkImg/button1/B20.png", 1, "整理", "整理")).setBounds(235, 230, 59, 24);
            this.add(TestpackJapnel.moyex);
            int Flag = 0;
            int count = 1;
            for (int j = 0; j < 24; ++j) {
                this.GoodsListLabel[j] = new JLabel();
                this.goodsMouslistens[j] = new GoodsMouslisten(j);
                this.GoodsListLabel[j].addMouseListener(this.goodsMouslistens[j]);
                if (Flag < 6 && count == 1) {
                    this.GoodsListLabel[j].setBounds(47 + Flag * 51, 260, 49, 49);
                    ++Flag;
                    this.add(this.GoodsListLabel[j]);
                }
                if (Flag < 6 && count == 2) {
                    this.GoodsListLabel[j].setBounds(47 + Flag * 51, 311, 49, 49);
                    ++Flag;
                    this.add(this.GoodsListLabel[j]);
                }
                if (Flag < 6 && count == 3) {
                    this.GoodsListLabel[j].setBounds(47 + Flag * 51, 362, 49, 49);
                    ++Flag;
                    this.add(this.GoodsListLabel[j]);
                }
                if (Flag < 6 && count == 4) {
                    this.GoodsListLabel[j].setBounds(47 + Flag * 51, 413, 49, 49);
                    ++Flag;
                    this.add(this.GoodsListLabel[j]);
                }
                else if (Flag == 6) {
                    Flag = 0;
                    ++count;
                }
            }
            (this.btnchangepwd = new GoodPanelBtn("inkImg/button1/B31.png", 1, (RoleData.getRoleData().getLoginResult().getPassword() != null) ? "改" : "设", "", "")).addMouseListener(new WLLMouslisten(24));
            this.btnchangepwd.setBounds(219, 180, 18, 18);
            this.add(this.btnchangepwd);
            Font font1 = new Font("楷体", 1, 14);final Color colorx1 = new Color(227, 13, 13);
            labZfz = new JLabel();
            labZfz.setText("Ctrl+右键一键使用物品");//水墨颜色
            labZfz.setBounds(140, 12, 200, 14);
            labZfz.setForeground(colorx1);
            labZfz.setFont(font1);
            this.add(labZfz);
            (this.btnroleresistance = new GoodPanelBtn("inkImg/button1/B30.png", 1, "摆摊")).setBounds(239, 203, 34, 17);
            this.add(this.btnroleresistance);
            //标记1
            (this.btnXxzb = new SpecificBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, "装扮", 1)).setBounds(293, 203, 34, 17);
            this.add(this.btnXxzb);
            (this.xbBtn = new SpecificBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, "星录", 666)).setBounds(293+37, 203, 34, 17);
            this.add(this.xbBtn);
            (this.btnunlock = new GoodPanelBtn("inkImg/button1/B31.png", 1, "解", null, "")).setBounds(241, 180, 18, 18);
            this.add(this.btnunlock);
            (this.btngoodlock = new GoodAndPetLockBtn("inkImg/button1/K156.png", 1, 1)).setBounds(175, 180, 18, 18);
            this.add(this.btngoodlock);
            (this.btngoodunlock = new GoodAndPetLockBtn("inkImg/button1/K157.png", 1, 2)).setBounds(197, 180, 18, 18);
            this.add(this.btngoodunlock);
            (this.cqbtn = new SpecificBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, "存钱", 29)).setBounds(188, 208, 34, 17);
            this.add(this.cqbtn);
            this.btnrights = new goodbtn[6];
            for (int j = 0; j < this.btnrights.length; ++j) {
                (this.btnrights[j] = new goodbtn("inkImg/button1/C0" + (j + 1) + ".png", 0, this, j)).setBounds(353, 259 + j * 35, 35, 31);
                this.add(this.btnrights[j]);
            }
            (this.labGemIntensify = new JLabel()).setBounds(165, 34, 32, 29);
            this.labGemIntensify.setIcon(GoodsListFromServerUntil.imgpath("gemIntensifyImg"));
            this.labGemIntensify.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    FormsManagement.HideForm(46);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    PathPoint point = GameJpanel.getGameJpanel().mousepath();
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.labGemIntensify.setOpaque(false);
            this.add(this.labGemIntensify);
            (this.huishou = new GoodPanelBtn("inkImg/button1/B32.png", 1, "回收师贡", null, "")).addMouseListener(new WLLMouslisten(34));
            this.huishou.setBounds(172, 232, 51, 17);
            this.add(this.huishou);
        }
        else {
            this.imgIconList = null;
            this.TeamStateS = 0;
            this.choseGoodsJLabel = new JLabel[12];
            this.choseGoodsMouslistens = new TakeOffEquipmentMouslisten[12];
            this.GoodsListLabel = new JLabel[24];
            this.goodsMouslistens = new GoodsMouslisten[24];
            this.shoptext = false;
            this.icon = new ImageIcon("inkImg/hongmu1/B103h.png");
            this.setPreferredSize(new Dimension(395, 507));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 2);
            offBtn.setBounds(370, 0, 23, 23);
            this.add(offBtn);
            this.initEquipmentSwitching();
            this.point = new PathPoint();
            for (int i = 0; i < this.choseGoodsJLabel.length; ++i) {
                this.choseGoodsJLabel[i] = new JLabel();
                PathPoint pathPoint = this.path(i);
                this.choseGoodsJLabel[i].setBounds(pathPoint.getX(), pathPoint.getY(), 50, 50);
                this.choseGoodsMouslistens[i] = new TakeOffEquipmentMouslisten(i);
                this.choseGoodsJLabel[i].addMouseListener(this.choseGoodsMouslistens[i]);
                this.add(this.choseGoodsJLabel[i]);
            }
            (TestpackJapnel.clearAll = new GoodPanelBtn("inkImg/button1/syajh.png", 1, UIUtils.COLOR_BTNPUTONG2, "当前物品栏整理", null, "")).setBounds(215, 268, 117, 24);
            TestpackJapnel.clearAll.setVisible(false);
            TestpackJapnel.clearAll.addMouseListener(new WLLMouslisten(17));
            this.add(TestpackJapnel.clearAll);
            (TestpackJapnel.clearAll1 = new GoodPanelBtn("inkImg/button1/syajh.png", 1, UIUtils.COLOR_BTNPUTONG2, "全部物品栏整理", null, "")).addMouseListener(new WLLMouslisten(18));
            TestpackJapnel.clearAll1.setVisible(false);
            TestpackJapnel.clearAll1.setBounds(215, 292, 117, 24);
            this.add(TestpackJapnel.clearAll1);
            (TestpackJapnel.qingli = new GoodPanelBtn("inkImg/button1/syajh.png", 1, UIUtils.COLOR_BTNPUTONG2, "当前物品栏清理", null, "")).setVisible(false);
            TestpackJapnel.qingli.setBounds(215, 316, 117, 24);
            this.add(TestpackJapnel.qingli);
            (TestpackJapnel.moyex = new MouseStyleBtn("inkImg/hongmu/6026.png", 1, "整理", "整理", "")).setBounds(215, 243, 60, 26);
            this.add(TestpackJapnel.moyex);
            int Flag = 0;
            int count = 1;
            for (int j = 0; j < 24; ++j) {
                this.GoodsListLabel[j] = new JLabel();
                this.goodsMouslistens[j] = new GoodsMouslisten(j);
                this.GoodsListLabel[j].addMouseListener(this.goodsMouslistens[j]);
                if (Flag < 6 && count == 1) {
                    this.GoodsListLabel[j].setBounds(28 + Flag * 51, 273, 49, 49);
                    ++Flag;
                    this.add(this.GoodsListLabel[j]);
                }
                if (Flag < 6 && count == 2) {
                    this.GoodsListLabel[j].setBounds(28 + Flag * 51, 324, 49, 49);
                    ++Flag;
                    this.add(this.GoodsListLabel[j]);
                }
                if (Flag < 6 && count == 3) {
                    this.GoodsListLabel[j].setBounds(28 + Flag * 51, 375, 49, 49);
                    ++Flag;
                    this.add(this.GoodsListLabel[j]);
                }
                if (Flag < 6 && count == 4) {
                    this.GoodsListLabel[j].setBounds(28 + Flag * 51, 426, 49, 49);
                    ++Flag;
                    this.add(this.GoodsListLabel[j]);
                }
                else if (Flag == 6) {
                    Flag = 0;
                    ++count;
                }
            }
            (this.btnchangepwd = new GoodPanelBtn("inkImg/hongmu/B31h.png", 1, (RoleData.getRoleData().getLoginResult().getPassword() != null) ? "改" : "设", 0)).addMouseListener(new WLLMouslisten(24));
            this.btnchangepwd.setBounds(199, 193, 18, 18);
            this.add(this.btnchangepwd);
            (this.btnunlock = new GoodPanelBtn("inkImg/hongmu/B31h.png", 1, "解", 0)).setBounds(221, 193, 18, 18);
            this.add(this.btnunlock);
            (this.btngoodlock = new GoodAndPetLockBtn("inkImg/hongmu/s75.png", 1, 1)).addMouseListener(new WLLMouslisten(25));
            this.btngoodlock.setBounds(155, 193, 18, 18);
            this.add(this.btngoodlock);
            (this.btngoodunlock = new GoodAndPetLockBtn("inkImg/hongmu/s76.png", 1, 2)).addMouseListener(new WLLMouslisten(26));
            this.btngoodunlock.setBounds(177, 193, 18, 18);
            this.add(this.btngoodunlock);
            (this.btnroleresistance = new GoodPanelBtn("inkImg/hongmu1/ss531.png", 1, "摆摊", 0)).setBounds(218, 218, 34, 17);
            this.add(this.btnroleresistance);
            //标记2
            (this.btnXxzb = new SpecificBtn("inkImg/hongmu1/ss531.png", 1, UIUtils.COLOR_RED_BTNTEXT, "装扮", 1)).setBounds(272, 218, 68, 17);
            this.add(this.btnXxzb);

            (this.xbBtn = new SpecificBtn("inkImg/hongmu1/ss531.png", 1, UIUtils.COLOR_RED_BTNTEXT, "星录", 666)).setBounds(275+40, 218, 37, 17);
            this.add(this.xbBtn);
//            (this.xingpan = new GoodPanelBtn("inkImg/hongmu/6026.png", 1, "星盘","","")).addMouseListener((MouseListener)new WLLMouslisten(35));
//            this.xingpan.setBounds(343, 225, 51, 17);
//            this.add((Component)this.xingpan);

            (this.cqbtn = new SpecificBtn("inkImg/hongmu/B30h.png", 1, UIUtils.COLOR_RED_BTNTEXT, "存钱", 29)).setBounds(166, 223, 34, 17);
            this.add(this.cqbtn);

            this.btnrights = new goodbtn[7];
            for (int k = 0; k < this.btnrights.length; ++k) {
                (this.btnrights[k] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, k)).setBounds(333, 272 + k * 35, 24, 31);
                this.add(this.btnrights[k]);
            }
            Font font1 = new Font("楷体", 1, 14);final Color colorx1 = new Color(144, 120, 22);
            labZfz = new JLabel();
            labZfz.setText("Ctrl+右键一键使用物品");//红木字体颜色
            labZfz.setBounds(120, 27, 200, 14);//红木位置
            labZfz.setForeground(colorx1);
            labZfz.setFont(font1);
            this.add(labZfz);
            (this.huishou = new GoodPanelBtn("inkImg/hongmu/B32h.png", 1, "回收师贡", 0)).addMouseListener(new WLLMouslisten(34));
            this.huishou.setBounds(151, 246, 51, 17);
            this.add(this.huishou);
            (this.labGemIntensify = new JLabel()).setBounds(145, 47, 32, 29);
            this.labGemIntensify.setIcon(GoodsListFromServerUntil.imgpath("gemIntensifyImg"));
            this.labGemIntensify.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    FormsManagement.HideForm(46);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    PathPoint point = GameJpanel.getGameJpanel().mousepath();
                    MsgJframe.getJframe().getJapnel().zsGemIntensify(RoleProperty.getRoleProperty().getQhv(), point.getX(), point.getY());
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.labGemIntensify.setOpaque(false);
            this.add(this.labGemIntensify);
        }
    }
    
    public void sminitEquipmentSwitching() {
        if (TestpackJapnel.replacementList != null) {
            for (EquipmentSwitchingBtn equipmentSwitchingBtn : TestpackJapnel.replacementList) {
                this.remove(equipmentSwitchingBtn);
            }
        }
        if (TestpackJapnel.replacement != null) {
            this.remove(TestpackJapnel.replacement);
        }
        (TestpackJapnel.replacement = new EquipmentSwitchingBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "换装", 998)).setBounds(318, 230, 99, 24);
        this.add(TestpackJapnel.replacement);
        String equipments = RoleData.getRoleData().getLoginResult().getEquipments();
        TestpackJapnel.replacementList = new ArrayList<>();
        if (StringUtils.isBlank(equipments)) {
            TestpackJapnel.replacement.setVisible(false);
        }
        else {
            String[] v = equipments.split("\\$");
            String[] split = v[1].split("&");
            int index = 0;
            for (String s : split) {
                String[] split2 = s.split("#");
                EquipmentSwitchingBtn tmp = new EquipmentSwitchingBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, split2[0], 1000 + index, "");
                tmp.setVisible(false);
                this.add(tmp);
                TestpackJapnel.replacementList.add(tmp);
                ++index;
            }
        }
        EquipmentSwitchingBtn replacementSettings = new EquipmentSwitchingBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, "换装设置", 999, "");
        replacementSettings.setVisible(false);
        this.add(replacementSettings);
        EquipmentSwitchingBtn replacementSettings2 = new EquipmentSwitchingBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_WHITE, "脱", 997, "");
        replacementSettings2.setVisible(false);
        this.add(replacementSettings2);
        TestpackJapnel.replacementList.add(replacementSettings2);
        TestpackJapnel.replacementList.add(replacementSettings);
    }
    
    public void initEquipmentSwitching() {
        if (TestpackJapnel.replacementList != null) {
            for (EquipmentSwitchingBtn equipmentSwitchingBtn : TestpackJapnel.replacementList) {
                this.remove(equipmentSwitchingBtn);
            }
        }
        if (TestpackJapnel.replacement != null) {
            this.remove(TestpackJapnel.replacement);
        }
        (TestpackJapnel.replacement = new EquipmentSwitchingBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_RED_BTNTEXT, "换装", 998, 0)).setBounds(287, 243, 60, 26);
        this.add(TestpackJapnel.replacement);
        String equipments = RoleData.getRoleData().getLoginResult().getEquipments();
        TestpackJapnel.replacementList = new ArrayList<>();
        if (StringUtils.isBlank(equipments)) {
            TestpackJapnel.replacement.setVisible(false);
        }
        else {
            String[] v = equipments.split("\\$");
            String[] split = v[1].split("&");
            int index = 0;
            for (String s : split) {
                String[] split2 = s.split("#");
                EquipmentSwitchingBtn tmp = new EquipmentSwitchingBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_RED_BTNTEXT, split2[0], 1000 + index, "");
                tmp.setVisible(false);
                this.add(tmp);
                TestpackJapnel.replacementList.add(tmp);
                ++index;
            }
        }
        EquipmentSwitchingBtn replacementSettings = new EquipmentSwitchingBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_RED_BTNTEXT, "换装设置", 999, "");
        replacementSettings.setVisible(false);
        this.add(replacementSettings);
        EquipmentSwitchingBtn replacementSettings2 = new EquipmentSwitchingBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_RED_BTNTEXT, "脱", 997, "");
        replacementSettings2.setVisible(false);
        this.add(replacementSettings2);
        TestpackJapnel.replacementList.add(replacementSettings);
        TestpackJapnel.replacementList.add(replacementSettings2);
    }
    
    public void isShowGemImg(int lvl) {
        this.labGemIntensify.setVisible(lvl > 0);
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
        if (this.labGemIntensify.isVisible()) {
            this.labGemIntensify.setVisible(false);
        }
        Graphics2D graphics2d = (Graphics2D)g;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int szz = 5;
        if (configure.getLzjskg() != null) {
            szz = Integer.parseInt(configure.getLzjskg());
        }
        if (szz == 3) {
            if (MyIsif.getStyle().equals("水墨")) {
                graphics2d.drawImage(this.icon.getImage(), 0, 0, 409, 480, this);
                if (ImageMixDeal.userimg != null) {
                    long se = ImageMixDeal.userimg.getRoleShow().getSpecies_id().longValue();
                    if (GoodsListFromServerUntil.getChoseGoodsList()[0] != null) {
                        long w = (long)TimeLimit.good(Integer.parseInt(GoodsListFromServerUntil.getChoseGoodsList()[0].getSkin()));
                        if (w != 0L) {
                            Limit limit = TimeLimit.getLimits().getLimit("童卡");
                            if (limit == null) {
                                limit = TimeLimit.getLimits().getLimit("变身卡");
                            }
                            if (TimeLimit.getLimits().getLimit("童卡") == null && TimeLimit.getLimits().getLimit("变身卡") == null && ((w == 1L && se == 20001L) || (w == 2L && se == 20001L) || (w == 1L && se == 20002L) || (w == 3L && se == 20002L) || (w == 4L && se == 20003L) || (w == 5L && se == 20003L) || (w == 9L && se == 20004L) || (w == 8L && se == 20004L) || (w == 10L && se == 20005L) || (w == 7L && se == 20005L) || (w == 10L && se == 20006L) || (w == 12L && se == 20006L) || (w == 1L && se == 20007L) || (w == 5L && se == 20007L) || (w == 1L && se == 20008L) || (w == 10L && se == 20008L) || (w == 2L && se == 20009L) || (w == 6L && se == 20009L) || (w == 8L && se == 20010L) || (w == 1L && se == 20010L) || (w == 12L && se == 21001L) || (w == 7L && se == 21001L) || (w == 10L && se == 21002L) || (w == 13L && se == 21002L) || (w == 10L && se == 21003L) || (w == 12L && se == 21003L) || (w == 9L && se == 21004L) || (w == 10L && se == 21004L) || (w == 7L && se == 21005L) || (w == 1L && se == 21005L) || (w == 14L && se == 21006L) || (w == 8L && se == 21006L) || (w == 12L && se == 21007L) || (w == 4L && se == 21007L) || (w == 10L && se == 21008L) || (w == 11L && se == 21008L) || (w == 10L && se == 21009L) || (w == 4L && se == 21009L) || (w == 14L && se == 21010L) || (w == 9L && se == 21010L) || (w == 12L && se == 22001L) || (w == 3L && se == 22001L) || (w == 14L && se == 22002L) || (w == 1L && se == 22002L) || (w == 7L && se == 22003L) || (w == 14L && se == 22003L) || (w == 10L && se == 22004L) || (w == 5L && se == 22004L) || (w == 7L && se == 22005L) || (w == 16L && se == 22005L) || (w == 1L && se == 22006L) || (w == 12L && se == 22006L) || (w == 12L && se == 22007L) || (w == 14L && se == 22007L) || (w == 11L && se == 22008L) || (w == 16L && se == 22008L) || (w == 1L && se == 22009L) || (w == 13L && se == 22009L) || (w == 16L && se == 22010L) || (w == 17L && se == 22010L) || (w == 1L && se == 23001L) || (w == 10L && se == 23001L) || (w == 12L && se == 23002L) || (w == 5L && se == 23002L) || (w == 13L && se == 23003L) || (w == 6L && se == 23003L) || (w == 9L && se == 23004L) || (w == 8L && se == 23004L) || (w == 17L && se == 23005L) || (w == 11L && se == 23005L) || (w == 11L && se == 23006L) || (w == 16L && se == 23006L) || (w == 1L && se == 24001L) || (w == 6L && se == 24001L) || (w == 12L && se == 24002L) || (w == 10L && se == 24002L) || (w == 18L && se == 24003L) || (w == 11L && se == 24003L) || (w == 9L && se == 24004L) || (w == 3L && se == 24004L) || (w == 18L && se == 24005L) || (w == 12L && se == 24005L) || (w == 1L && se == 24006L) || (w == 17L && se == 24006L))) {
                                w += 18L;
                            }
                            se |= w << 32;
                        }
                    }
                    if (this.part == null) {
                        this.part = SpriteFactory.createPart(se, 2, 1, null);
                    }
                    else {
                        this.part = SpriteFactory.setPart(this.part, 1, se + "");
                    }
                    this.part.draw(graphics2d, 220, 155, 4, ImageMixDeal.userimg.getTime());
                }
                if (this.shoptext) {
                    int shop_x = this.goodPosition % 6;
                    int shop_y = this.goodPosition / 6;
                    graphics2d.setColor(Color.red);
                    graphics2d.drawLine(47 + shop_x * 51, 262 + shop_y * 51, 94 + shop_x * 51, 262 + shop_y * 51);
                    graphics2d.drawLine(47 + shop_x * 51, 262 + shop_y * 51, 47 + shop_x * 51, 309 + shop_y * 51);
                    graphics2d.drawLine(94 + shop_x * 51, 262 + shop_y * 51, 94 + shop_x * 51, 309 + shop_y * 51);
                    graphics2d.drawLine(47 + shop_x * 51, 309 + shop_y * 51, 94 + shop_x * 51, 309 + shop_y * 51);
                }
                GoodsListFromServerUntil.draw(graphics2d, 47, 262);
                GoodsListFromServerUntil.drawLock(graphics2d, this);
                LoginResult login = RoleData.getRoleData().getLoginResult();
                Util.drawPrice(graphics2d, login.getGold(), 82, 222);
                Util.drawPrice(graphics2d, login.getSavegold(), 82, 246);
            }
            else {
                graphics2d.drawImage(this.icon.getImage(), 0, 0, 395, 507, this);
                if (ImageMixDeal.userimg != null) {
                    long se = ImageMixDeal.userimg.getRoleShow().getSpecies_id().longValue();
                    if (GoodsListFromServerUntil.getChoseGoodsList()[0] != null) {
                        long w = (long)TimeLimit.good(Integer.parseInt(GoodsListFromServerUntil.getChoseGoodsList()[0].getSkin()));
                        if (w != 0L) {
                            Limit limit = TimeLimit.getLimits().getLimit("童卡");
                            if (limit == null) {
                                limit = TimeLimit.getLimits().getLimit("变身卡");
                            }
                            if (TimeLimit.getLimits().getLimit("童卡") == null && TimeLimit.getLimits().getLimit("变身卡") == null && ((w == 1L && se == 20001L) || (w == 2L && se == 20001L) || (w == 1L && se == 20002L) || (w == 3L && se == 20002L) || (w == 4L && se == 20003L) || (w == 5L && se == 20003L) || (w == 9L && se == 20004L) || (w == 8L && se == 20004L) || (w == 10L && se == 20005L) || (w == 7L && se == 20005L) || (w == 10L && se == 20006L) || (w == 12L && se == 20006L) || (w == 1L && se == 20007L) || (w == 5L && se == 20007L) || (w == 1L && se == 20008L) || (w == 10L && se == 20008L) || (w == 2L && se == 20009L) || (w == 6L && se == 20009L) || (w == 8L && se == 20010L) || (w == 1L && se == 20010L) || (w == 12L && se == 21001L) || (w == 7L && se == 21001L) || (w == 10L && se == 21002L) || (w == 13L && se == 21002L) || (w == 10L && se == 21003L) || (w == 12L && se == 21003L) || (w == 9L && se == 21004L) || (w == 10L && se == 21004L) || (w == 7L && se == 21005L) || (w == 1L && se == 21005L) || (w == 14L && se == 21006L) || (w == 8L && se == 21006L) || (w == 12L && se == 21007L) || (w == 4L && se == 21007L) || (w == 10L && se == 21008L) || (w == 11L && se == 21008L) || (w == 10L && se == 21009L) || (w == 4L && se == 21009L) || (w == 14L && se == 21010L) || (w == 9L && se == 21010L) || (w == 12L && se == 22001L) || (w == 3L && se == 22001L) || (w == 14L && se == 22002L) || (w == 1L && se == 22002L) || (w == 7L && se == 22003L) || (w == 14L && se == 22003L) || (w == 10L && se == 22004L) || (w == 5L && se == 22004L) || (w == 7L && se == 22005L) || (w == 16L && se == 22005L) || (w == 1L && se == 22006L) || (w == 12L && se == 22006L) || (w == 12L && se == 22007L) || (w == 14L && se == 22007L) || (w == 11L && se == 22008L) || (w == 16L && se == 22008L) || (w == 1L && se == 22009L) || (w == 13L && se == 22009L) || (w == 16L && se == 22010L) || (w == 17L && se == 22010L) || (w == 1L && se == 23001L) || (w == 10L && se == 23001L) || (w == 12L && se == 23002L) || (w == 5L && se == 23002L) || (w == 13L && se == 23003L) || (w == 6L && se == 23003L) || (w == 9L && se == 23004L) || (w == 8L && se == 23004L) || (w == 17L && se == 23005L) || (w == 11L && se == 23005L) || (w == 11L && se == 23006L) || (w == 16L && se == 23006L) || (w == 1L && se == 24001L) || (w == 6L && se == 24001L) || (w == 12L && se == 24002L) || (w == 10L && se == 24002L) || (w == 18L && se == 24003L) || (w == 11L && se == 24003L) || (w == 9L && se == 24004L) || (w == 3L && se == 24004L) || (w == 18L && se == 24005L) || (w == 12L && se == 24005L) || (w == 1L && se == 24006L) || (w == 17L && se == 24006L))) {
                                w += 18L;
                            }
                            se |= w << 32;
                        }
                    }
                    if (this.part == null) {
                        this.part = SpriteFactory.createPart(se, 2, 1, null);
                    }
                    else {
                        this.part = SpriteFactory.setPart(this.part, 1, se + "");
                    }
                    this.part.draw(graphics2d, 200, 166, 4, ImageMixDeal.userimg.getTime());
                }
                if (this.shoptext) {
                    int shop_x = this.goodPosition % 6;
                    int shop_y = this.goodPosition / 6;
                    graphics2d.setColor(Color.red);
                    graphics2d.drawLine(28 + shop_x * 51, 275 + shop_y * 51, 75 + shop_x * 51, 275 + shop_y * 51);
                    graphics2d.drawLine(28 + shop_x * 51, 275 + shop_y * 51, 28 + shop_x * 51, 322 + shop_y * 51);
                    graphics2d.drawLine(75 + shop_x * 51, 275 + shop_y * 51, 75 + shop_x * 51, 322 + shop_y * 51);
                    graphics2d.drawLine(28 + shop_x * 51, 322 + shop_y * 51, 75 + shop_x * 51, 322 + shop_y * 51);
                }
                GoodsListFromServerUntil.draw(graphics2d, 28, 275);
                GoodsListFromServerUntil.drawLock(graphics2d, this);
                LoginResult login = RoleData.getRoleData().getLoginResult();
                Util.drawPrice(graphics2d, login.getGold(), 65, 237);
                Util.drawPrice(graphics2d, login.getSavegold(), 65, 259);
            }
        }
        else if (MyIsif.getStyle().equals("水墨")) {
            graphics2d.drawImage(this.icon.getImage(), 0, 0, 409, 480, this);
            if (ImageMixDeal.userimg != null) {
                long se = ImageMixDeal.userimg.getRoleShow().getSpecies_id().longValue();
                if (GoodsListFromServerUntil.getChoseGoodsList()[0] == null ) {
//                    if (this.part == null) {
                        this.part = SpriteFactory.createPart(se, 2, 1, null);
//                    }
                }
                else if (this.part == null) {
                    String getskin = TimeLimit.getskin(TimeLiTXT.getTimeLiTXT().getSkin(), RoleData.getRoleData().getPackRecord().getPutTX(), ImageMixDeal.userimg.getRoleShow());
                    this.changeskin(getskin);
                    this.part.setAct(7);
                }
                else {
                    this.part.setAct(7);
                }
                this.part.draw(graphics2d, 220, 155, 4, ImageMixDeal.userimg.getTime());
            }
            if (this.shoptext) {
                int shop_x = this.goodPosition % 6;
                int shop_y = this.goodPosition / 6;
                graphics2d.setColor(Color.red);
                graphics2d.drawLine(48 + shop_x * 51, 260 + shop_y * 51, 96 + shop_x * 51, 260 + shop_y * 51);
                graphics2d.drawLine(48 + shop_x * 51, 260 + shop_y * 51, 48 + shop_x * 51, 309 + shop_y * 51);
                graphics2d.drawLine(96 + shop_x * 51, 260 + shop_y * 51, 96 + shop_x * 51, 309 + shop_y * 51);
                graphics2d.drawLine(48 + shop_x * 51, 309 + shop_y * 51, 96 + shop_x * 51, 309 + shop_y * 51);
            }
            GoodsListFromServerUntil.draw(graphics2d, 47, 262);
            GoodsListFromServerUntil.drawLock(graphics2d, this);
            LoginResult login = RoleData.getRoleData().getLoginResult();
            Util.drawPrice(graphics2d, login.getGold(), 82, 222);
            Util.drawPrice(graphics2d, login.getSavegold(), 82, 246);
        }
        else {
            graphics2d.drawImage(this.icon.getImage(), 0, 0, 395, 507, this);
            if (ImageMixDeal.userimg != null) {
                long se = ImageMixDeal.userimg.getRoleShow().getSpecies_id().longValue();
                if (GoodsListFromServerUntil.getChoseGoodsList()[0] == null  ) {
//                    if (this.part == null) {
                        this.part = SpriteFactory.createPart(se, 2, 1, null);
//                    }
                }
                else if (this.part == null) {
                    String getskin = TimeLimit.getskin(TimeLiTXT.getTimeLiTXT().getSkin(), RoleData.getRoleData().getPackRecord().getPutTX(), ImageMixDeal.userimg.getRoleShow());
                    this.changeskin(getskin);
                    this.part.setAct(7);
                }
                else {
                    this.part.setAct(7);
                }
                this.part.draw(graphics2d, 200, 155, 4, ImageMixDeal.userimg.getTime());
            }
            if (this.shoptext) {
                int shop_x = this.goodPosition % 6;
                int shop_y = this.goodPosition / 6;
                graphics2d.setColor(Color.red);
                graphics2d.drawLine(28 + shop_x * 51, 275 + shop_y * 51, 75 + shop_x * 51, 275 + shop_y * 51);
                graphics2d.drawLine(28 + shop_x * 51, 275 + shop_y * 51, 28 + shop_x * 51, 322 + shop_y * 51);
                graphics2d.drawLine(75 + shop_x * 51, 275 + shop_y * 51, 75 + shop_x * 51, 322 + shop_y * 51);
                graphics2d.drawLine(28 + shop_x * 51, 322 + shop_y * 51, 75 + shop_x * 51, 322 + shop_y * 51);
            }
            GoodsListFromServerUntil.draw(graphics2d, 28, 275);
            GoodsListFromServerUntil.drawLock(graphics2d, this);
            LoginResult login = RoleData.getRoleData().getLoginResult();
            graphics2d.setFont(UIUtils.TEXT_FONT62);
            Util.drawPrice(graphics2d, login.getGold(), 62, 237);
            Util.drawPrice(graphics2d, login.getSavegold(), 62, 259);
        }
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
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
    
    public goodbtn[] getBtnrights() {
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
        return TestpackJapnel.moyex;
    }
    
    public static void setMoyex(MouseStyleBtn moyex) {
        TestpackJapnel.moyex = moyex;
    }
    
    public static List<EquipmentSwitchingBtn> getReplacementList() {
        return TestpackJapnel.replacementList;
    }
    
    public static void setReplacementList(List<EquipmentSwitchingBtn> replacementList) {
        TestpackJapnel.replacementList = replacementList;
    }
    
    public static EquipmentSwitchingBtn getReplacement() {
        return TestpackJapnel.replacement;
    }
    
    public static void setReplacement(EquipmentSwitchingBtn replacement) {
        TestpackJapnel.replacement = replacement;
    }
    
    public NewPart getPart() {
        return this.part;
    }
    
    public void setPart(NewPart part) {
        this.part = part;
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
        TestpackJapnel.tzIndex = Integer.valueOf(0);
    }
}
