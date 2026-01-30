package org.come.Jpanel;

import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import org.come.entity.GangGroup;
import org.come.entity.Gang;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import com.tool.btn.GangGroupBtn;
import com.tool.btn.FactionBtn;
import javax.swing.JPanel;

public class FactionPandectJpanel extends JPanel
{
    private FactionBtn btnReward;
    private FactionBtn btnWarehouse;
    private FactionBtn juanqian;
    private GangGroupBtn btnFourTiger;
    private GangGroupBtn btnFourDragon;
    private GangGroupBtn btnFourRosefinch;
    private GangGroupBtn btnFourTortoise;
    private GangGroupBtn btnDoor;
    private GangGroupBtn btnDye;
    private GangGroupBtn btnShop;
    private GangGroupBtn btnTownHall;
    private GangGroupBtn btnBase;
    private GangGroupBtn btnTechfun;
    private GangGroupBtn btnForge;
    private GangGroupBtn btnWare;
    private GangGroupBtn btnKylin;
    private GangGroupBtn btnTwoTiger;
    private GangGroupBtn btnTwoDragon;
    private GangGroupBtn[] btnArrTower;
    private JLabel labFactionName;
    private JLabel labFactionLvl;
    private JLabel labFactionMoney;
    private JLabel labFactionNum;
    private JLabel labFactionWang;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private FactionCardJpanel factionCardJpanel;
    private ImageIcon icon;
    
    public FactionPandectJpanel(FactionCardJpanel factionCardJpanel) {
        this.factionCardJpanel = factionCardJpanel;
        this.setPreferredSize(new Dimension(662, 475));
        this.setOpaque(false);
        this.setLayout(null);
        this.getLabFactionName();
        this.getLabFactionLvl();
        this.getLabFactionMoney();
        this.getLabFactionNum();
        this.getLabFactionWang();
        this.getScrollPane();
        this.getBtnReward();
        this.getJuanqian();
        this.getBtnWarehouse();
        this.getBtnArrTower();
        this.getBtnTechfun();
        this.getBtnTwoDragon();
        this.getBtnTwoTiger();
        this.getBtnBase();
        this.getBtnDoor();
        this.getBtnDye();
        this.getBtnForge();
        this.getBtnKylin();
        this.getBtnFourDragon();
        this.getBtnFourRosefinch();
        this.getBtnFourTiger();
        this.getBtnFourTortoise();
        this.getBtnShop();
        this.getBtnTownHall();
        this.getBtnWare();
    }
    
    public void showFactionMessage(Gang gang, GangGroup gangGroup) {
        this.labFactionName.setText(gang.getGangname());
        this.labFactionLvl.setText(gang.getGanggrade().toString());
        this.labFactionMoney.setText(gang.getBuilder().toString());
        this.labFactionNum.setText(gang.getGangnumber().toString());
        this.labFactionWang.setText(gang.getGangbelong());
        this.textArea.setText(gang.getIntroduction());
        this.btnBase.setLvl(gang.getGanggrade().intValue());
        if (gangGroup != null) {
            this.btnDye.setLvl(gangGroup.getXy());
            this.btnTechfun.setLvl(gangGroup.getKj());
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B222.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 662, 475, null);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S166.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 662, 475, null);
        }
    }
    
    public JLabel getLabFactionName() {
        if (this.labFactionName == null) {
            this.labFactionName = new JLabel("帮派名称");
            if (MyIsif.getStyle().equals("水墨")) {
                this.labFactionName.setBounds(541, 94, 101, 16);
            }
            else {
                this.labFactionName.setBounds(550, 104, 101, 16);
            }
            this.labFactionName.setFont(UIUtils.TEXT_HY14);
            this.labFactionName.setForeground(Color.white);
            this.add(this.labFactionName);
        }
        return this.labFactionName;
    }
    
    public void setLabFactionName(JLabel labFactionName) {
        this.labFactionName = labFactionName;
    }
    
    public JLabel getLabFactionLvl() {
        if (this.labFactionLvl == null) {
            this.labFactionLvl = new JLabel("帮派名称");
            if (MyIsif.getStyle().equals("水墨")) {
                this.labFactionLvl.setBounds(541, 136, 101, 16);
            }
            else {
                this.labFactionLvl.setBounds(550, 145, 101, 16);
            }
            this.labFactionLvl.setFont(UIUtils.TEXT_HY14);
            this.labFactionLvl.setForeground(Color.white);
            this.add(this.labFactionLvl);
        }
        return this.labFactionLvl;
    }
    
    public void setLabFactionLvl(JLabel labFactionLvl) {
        this.labFactionLvl = labFactionLvl;
    }
    
    public JLabel getLabFactionMoney() {
        if (this.labFactionMoney == null) {
            this.labFactionMoney = new JLabel("帮派名称");
            if (MyIsif.getStyle().equals("水墨")) {
                this.labFactionMoney.setBounds(541, 178, 101, 16);
            }
            else {
                this.labFactionMoney.setBounds(550, 187, 101, 16);
            }
            this.labFactionMoney.setFont(UIUtils.TEXT_HY14);
            this.labFactionMoney.setForeground(Color.white);
            this.add(this.labFactionMoney);
        }
        return this.labFactionMoney;
    }
    
    public void setLabFactionMoney(JLabel labFactionMoney) {
        this.labFactionMoney = labFactionMoney;
    }
    
    public JLabel getLabFactionNum() {
        if (this.labFactionNum == null) {
            (this.labFactionNum = new JLabel("帮派名称")).setFont(UIUtils.TEXT_HY14);
            this.labFactionNum.setForeground(Color.white);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labFactionNum.setBounds(541, 220, 101, 16);
            }
            else {
                this.labFactionNum.setBounds(550, 229, 101, 16);
            }
            this.add(this.labFactionNum);
        }
        return this.labFactionNum;
    }
    
    public void setLabFactionNum(JLabel labFactionNum) {
        this.labFactionNum = labFactionNum;
    }
    
    public JLabel getLabFactionWang() {
        if (this.labFactionWang == null) {
            this.labFactionWang = new JLabel("帮派名称");
            if (MyIsif.getStyle().equals("水墨")) {
                this.labFactionWang.setBounds(541, 262, 101, 16);
            }
            else {
                this.labFactionWang.setBounds(550, 271, 101, 16);
            }
            this.labFactionWang.setFont(UIUtils.TEXT_HY14);
            this.labFactionWang.setForeground(Color.white);
            this.add(this.labFactionWang);
        }
        return this.labFactionWang;
    }
    
    public void setLabFactionWang(JLabel labFactionWang) {
        this.labFactionWang = labFactionWang;
    }
    
    public JTextArea getTextArea() {
        if (this.textArea == null) {
            (this.textArea = new JTextArea()).setOpaque(false);
            this.textArea.setForeground(Color.white);
            this.textArea.setFont(UIUtils.TEXT_HY14);
            this.textArea.setLineWrap(true);
        }
        return this.textArea;
    }
    
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane(this.getTextArea())).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.scrollPane.setBounds(50, 352, 490, 82);
            }
            else {
                this.scrollPane.setBounds(56, 362, 490, 88);
            }
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public FactionBtn getBtnReward() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnReward == null) {
                (this.btnReward = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "赏功堂", UIUtils.TEXT_HY16, 8, this)).setBounds(545, 343, 99, 24);
                this.add(this.btnReward);
            }
        }
        else if (this.btnReward == null) {
            (this.btnReward = new FactionBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "赏功堂", UIUtils.TEXT_HY88, 8, this)).setBounds(550, 303, 80, 26);
            this.add(this.btnReward);
        }
        return this.btnReward;
    }
    
    public void setBtnReward(FactionBtn btnReward) {
        this.btnReward = btnReward;
    }
    
    public FactionBtn getJuanqian() {
        if (this.juanqian == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.juanqian = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "帮派捐钱", UIUtils.TEXT_HY88, 25, this)).setBounds(545, 403, 99, 24);
            }
            else {
                (this.juanqian = new FactionBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "帮派捐钱", UIUtils.TEXT_HY88, 25, this)).setBounds(550, 363, 80, 26);
            }
            this.add(this.juanqian);
        }
        return this.juanqian;
    }
    
    public void setJuanqian(FactionBtn juanqian) {
        this.juanqian = juanqian;
    }
    
    public FactionBtn getBtnWarehouse() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnWarehouse == null) {
                (this.btnWarehouse = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "帮派仓库", UIUtils.TEXT_HY16, 9, this)).setBounds(545, 373, 99, 24);
                this.add(this.btnWarehouse);
            }
        }
        else if (this.btnWarehouse == null) {
            (this.btnWarehouse = new FactionBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "帮派仓库", UIUtils.TEXT_HY88, 9, this)).setBounds(550, 333, 80, 26);
            this.add(this.btnWarehouse);
        }
        return this.btnWarehouse;
    }
    
    public void setBtnWarehouse(FactionBtn btnWarehouse) {
        this.btnWarehouse = btnWarehouse;
    }
    
    public FactionCardJpanel getFactionCardJpanel() {
        return this.factionCardJpanel;
    }
    
    public void setFactionCardJpanel(FactionCardJpanel factionCardJpanel) {
        this.factionCardJpanel = factionCardJpanel;
    }
    
    public GangGroupBtn getBtnFourTiger() {
        if (this.btnFourTiger == null) {
            (this.btnFourTiger = new GangGroupBtn("inkImg/button/B292.png", 1, 0)).setBounds(250, 180, 19, 18);
            this.add(this.btnFourTiger);
        }
        return this.btnFourTiger;
    }
    
    public GangGroupBtn getBtnFourDragon() {
        if (this.btnFourDragon == null) {
            (this.btnFourDragon = new GangGroupBtn("inkImg/button/B294.png", 1, 0)).setBounds(203, 202, 22, 19);
            this.add(this.btnFourDragon);
        }
        return this.btnFourDragon;
    }
    
    public GangGroupBtn getBtnFourRosefinch() {
        if (this.btnFourRosefinch == null) {
            (this.btnFourRosefinch = new GangGroupBtn("inkImg/button/B293.png", 1, 0)).setBounds(293, 204, 20, 22);
            this.add(this.btnFourRosefinch);
        }
        return this.btnFourRosefinch;
    }
    
    public GangGroupBtn getBtnFourTortoise() {
        if (this.btnFourTortoise == null) {
            (this.btnFourTortoise = new GangGroupBtn("inkImg/button/B295.png", 1, 0)).setBounds(244, 224, 16, 22);
            this.add(this.btnFourTortoise);
        }
        return this.btnFourTortoise;
    }
    
    public GangGroupBtn getBtnDoor() {
        if (this.btnDoor == null) {
            (this.btnDoor = new GangGroupBtn("inkImg/button/B300.png", 1, 0)).setBounds(93, 231, 78, 59);
            this.add(this.btnDoor);
        }
        return this.btnDoor;
    }
    
    public GangGroupBtn getBtnDye() {
        if (this.btnDye == null) {
            (this.btnDye = new GangGroupBtn("inkImg/button/B301.png", 1, 2023)).setBounds(427, 160, 102, 70);
            this.add(this.btnDye);
        }
        return this.btnDye;
    }
    
    public GangGroupBtn getBtnShop() {
        if (this.btnShop == null) {
            (this.btnShop = new GangGroupBtn("inkImg/button/B302.png", 1, 0)).setBounds(50, 175, 57, 61);
            this.add(this.btnShop);
        }
        return this.btnShop;
    }
    
    public GangGroupBtn getBtnTownHall() {
        if (this.btnTownHall == null) {
            (this.btnTownHall = new GangGroupBtn("inkImg/button/B304.png", 1, 0)).setBounds(438, 62, 87, 71);
            this.add(this.btnTownHall);
        }
        return this.btnTownHall;
    }
    
    public GangGroupBtn getBtnBase() {
        if (this.btnBase == null) {
            (this.btnBase = new GangGroupBtn("inkImg/button/B305.png", 1, 2021)).setBounds(330, 93, 130, 88);
            this.add(this.btnBase);
        }
        return this.btnBase;
    }
    
    public GangGroupBtn getBtnTechfun() {
        if (this.btnTechfun == null) {
            (this.btnTechfun = new GangGroupBtn("inkImg/button/B299.png", 1, 2022)).setBounds(385, 200, 108, 85);
            this.add(this.btnTechfun);
        }
        return this.btnTechfun;
    }
    
    public GangGroupBtn getBtnForge() {
        if (this.btnForge == null) {
            (this.btnForge = new GangGroupBtn("inkImg/button/B298.png", 1, 0)).setBounds(305, 256, 86, 52);
            this.add(this.btnForge);
        }
        return this.btnForge;
    }
    
    public GangGroupBtn getBtnWare() {
        if (this.btnWare == null) {
            (this.btnWare = new GangGroupBtn("inkImg/button/B297.png", 1, 0)).setBounds(279, 75, 99, 67);
            this.add(this.btnWare);
        }
        return this.btnWare;
    }
    
    public GangGroupBtn getBtnKylin() {
        if (this.btnKylin == null) {
            (this.btnKylin = new GangGroupBtn("inkImg/button/B296.png", 1, 2026)).setBounds(241, 179, 38, 47);
            this.add(this.btnKylin);
        }
        return this.btnKylin;
    }
    
    public GangGroupBtn getBtnTwoTiger() {
        if (this.btnTwoTiger == null) {
            (this.btnTwoTiger = new GangGroupBtn("inkImg/button/B290.png", 1, 0)).setBounds(326, 134, 21, 32);
            this.add(this.btnTwoTiger);
        }
        return this.btnTwoTiger;
    }
    
    public GangGroupBtn getBtnTwoDragon() {
        if (this.btnTwoDragon == null) {
            (this.btnTwoDragon = new GangGroupBtn("inkImg/button/B291.png", 1, 0)).setBounds(370, 160, 21, 27);
            this.add(this.btnTwoDragon);
        }
        return this.btnTwoDragon;
    }
    
    public GangGroupBtn[] getBtnArrTower() {
        if (this.btnArrTower == null) {
            this.btnArrTower = new GangGroupBtn[8];
            for (int i = 0; i < this.btnArrTower.length; ++i) {
                this.add(this.btnArrTower[i] = new GangGroupBtn("inkImg/button/B303.png", 1, 0));
            }
            this.btnArrTower[0].setBounds(106, 234, 24, 42);
            this.btnArrTower[1].setBounds(152, 257, 24, 42);
            this.btnArrTower[2].setBounds(179, 202, 24, 42);
            this.btnArrTower[3].setBounds(210, 222, 24, 42);
            this.btnArrTower[4].setBounds(279, 148, 24, 42);
            this.btnArrTower[5].setBounds(312, 168, 24, 42);
            this.btnArrTower[6].setBounds(423, 58, 24, 42);
            this.btnArrTower[7].setBounds(496, 100, 24, 42);
        }
        return this.btnArrTower;
    }
}
