package org.come.summonequip;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.cbg.until.TraslationDemoScrollBarUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Component;
import java.awt.Font;
import org.come.Frame.TestpackJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.tcpimg.UIUtils;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.Frame.ZhuFrame;
import java.util.List;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import java.awt.Color;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.CutButtonImage;
import com.tool.role.RoleData;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpanelSummonEquipMain extends JPanel
{
    private JpanelNowEquip jpanelNowEquip;
    private JLabel radioBtnOne;
    private JLabel radioBtnTwo;
    private JLabel radioLabOne;
    private JLabel radioLabTwo;
    private JLabel equipBackImgOne;
    private JLabel equipBackImgTwo;
    private JLabel equipImgOne;
    private JLabel equipImgTwo;
    private JLabel textLabOne;
    private JLabel textLabTwo;
    private JLabel nowNumLab;
    private JLabel upgradeLab;
    private JLabel awakenName;
    private JLabel levelLab;
    private JLabel expLab;
    private JLabel expLabImg;
    private JLabel expBackLabImg;
    private JTextField numText;
    private JLabel[] goodsLabs;
    private JLabel[] washGoodsLabs;
    private Integer type;
    private BtnSummonEquipMain nowEquipBtn;
    private BtnSummonEquipMain ackBtn;
    private BtnSummonEquipMain cashRewardsBtn;
    private BtnSummonEquipMain cultivateMenuBtn;
    private BtnSummonEquipMain recastMenuBtn;
    private BtnSummonEquipMain awakenMenuBtn;
    private BtnSummonEquipMain maxNumBtn;
    private JList<String> summonNameJlist;
    private DefaultListModel<String> summonNamesModel;
    private JScrollPane scrollPane;
    private int radioBtnType;
    private int summonEquipMenuType;
    private BigDecimal chooseEquip;
    private BigDecimal chooseGoods;
    private Goodstable[] chooseWashGoods;
    private BigDecimal money;
    private ArrayList<BigDecimal> summonList;
    private BigDecimal scoretype;
    private ImageIcon iconBack;
    private ImageIcon textLabImg;
    private ImageIcon recastWashImg;
    private ImageIcon awakenUpLvlImg;
    
    public JpanelSummonEquipMain() {
        this.radioBtnType = 1;
        this.summonEquipMenuType = -1;
        this.money = new BigDecimal(1000000);
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(382, 455));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 91);
            offBtn.setBounds(345, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            this.setPreferredSize(new Dimension(356, 477));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 91);
            offBtn.setBounds(331, 0, 25, 25);
            this.add(offBtn);
        }
        this.getSummonList();
        this.getScrollPane();
        this.getJpanelNowEquip();
        this.getRadioBtnOne();
        this.getRadioBtnTwo();
        this.getRadioLabOne();
        this.getRadioLabTwo();
        this.getNowEquipBtn();
        this.getEquipImgOne();
        this.getEquipImgTwo();
        this.getEquipBackImgOne();
        this.getEquipBackImgTwo();
        this.getTextLabOne();
        this.getTextLabTwo();
        this.getGoodsLabs();
        this.getAckBtn();
        this.getCashRewardsBtn();
        this.getCultivateMenuBtn();
        this.getRecastMenuBtn();
        this.getAwakenMenuBtn();
        this.getMaxNumBtn();
        this.getAwakenName();
        this.getLevelLab();
        this.getExpLab();
        this.getExpLabImg();
        this.getExpBackLabImg();
        this.getUpgradeLab();
        this.getNowNumLab();
        this.getWashGoodsLabs();
        this.getNumText();
        this.scoretype = RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章");
    }
    
    public void changeMenuView(int type) {
        if (type == -1) {
            if (this.summonEquipMenuType == -1) {
                type = 1;
            }
            else {
                return;
            }
        }
        this.type = Integer.valueOf(type);
        this.refreshView(this.summonEquipMenuType, this.radioBtnType, false);
        this.summonEquipMenuType = type;
        this.changeViewChooseRadioBtn();
        this.refreshView(this.summonEquipMenuType, this.radioBtnType, true);
    }
    
    public void refreshView(int menuType, int btnType, boolean type) {
        if (menuType == 1 && btnType == 1) {
            this.changeCultivateViewCultivateBtn(type);
        }
        else if (menuType == 1 && btnType == 2) {
            this.changeCultivateViewResolveBtn(type);
        }
        else if (menuType == 2 && btnType == 1) {
            this.changeRecastViewAttributeBtn(type);
        }
        else if (menuType == 2 && btnType == 2) {
            this.changeRecastViewSkillBtn(type);
        }
        else if (menuType == 3 && btnType == 1) {
            this.changeAwakenViewUpgradeBtn(type);
        }
        else if (menuType == 3 && btnType == 2) {
            this.changeAwakenViewOpenawakenBtn(type);
        }
    }
    
    public void changeCultivateViewCultivateBtn(boolean type) {
        try {
            this.radioBtnOne.setVisible(type);
            this.radioBtnTwo.setVisible(type);
            this.radioLabOne.setVisible(type);
            this.radioLabTwo.setVisible(type);
            this.nowEquipBtn.setVisible(type);
            this.equipBackImgOne.setVisible(type);
            this.equipBackImgTwo.setVisible(type);
            this.equipImgOne.setVisible(type);
            this.equipImgTwo.setVisible(type);
            this.textLabOne.setVisible(type);
            this.textLabTwo.setVisible(type);
            if (type) {
                this.concealNowEquip();
                this.recoverEquipImgTwo();
                this.money = new BigDecimal(1000000);
                if (MyIsif.getStyle().equals("水墨")) {
                    this.nowEquipBtn.setBounds(109, 108, 68, 17);
                    this.radioBtnOne.setBounds(107, 65, 18, 18);
                    this.radioBtnTwo.setBounds(201, 65, 18, 18);
                    this.radioLabOne.setBounds(128, 65, 60, 20);
                    this.radioLabTwo.setBounds(222, 65, 60, 20);
                    this.equipBackImgOne.setBounds(111, 126, 59, 57);
                    this.equipBackImgTwo.setBounds(227, 126, 59, 57);
                    this.equipImgOne.setBounds(113, 128, 55, 53);
                    this.equipImgTwo.setBounds(229, 128, 55, 53);
                    this.textLabOne.setBounds(102, 216, 60, 18);
                    this.textLabTwo.setBounds(102, 240, 60, 18);
                    this.radioLabOne.setText("培养");
                    this.radioLabTwo.setText("分解");
                    this.ackBtn.setText("培养");
                }
                else {
                    this.nowEquipBtn.setBounds(89, 120, 60, 17);
                    this.radioBtnOne.setBounds(87, 77, 19, 19);
                    this.radioBtnTwo.setBounds(181, 77, 19, 19);
                    this.radioLabOne.setBounds(108, 77, 60, 20);
                    this.radioLabTwo.setBounds(202, 77, 60, 20);
                    this.equipBackImgOne.setBounds(91, 138, 59, 57);
                    this.equipBackImgTwo.setBounds(207, 138, 59, 57);
                    this.equipImgOne.setBounds(93, 140, 55, 53);
                    this.equipImgTwo.setBounds(209, 140, 55, 53);
                    this.textLabOne.setBounds(82, 228, 60, 18);
                    this.textLabTwo.setBounds(82, 252, 60, 18);
                    this.radioLabOne.setText("培养");
                    this.radioLabTwo.setText("分解");
                    this.ackBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/按钮_培养_w60,h78.png"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeCultivateViewResolveBtn(boolean type) {
        try {
            this.radioBtnOne.setVisible(type);
            this.radioBtnTwo.setVisible(type);
            this.equipBackImgOne.setVisible(type);
            this.radioLabOne.setVisible(type);
            this.radioLabTwo.setVisible(type);
            this.equipImgOne.setVisible(type);
            if (type) {
                this.concealNowEquip();
                if (MyIsif.getStyle().equals("水墨")) {
                    this.textLabOne.setBounds(102, 216, 60, 18);
                    this.textLabTwo.setBounds(102, 240, 60, 18);
                    this.radioLabOne.setBounds(128, 65, 60, 20);
                    this.radioLabTwo.setBounds(222, 65, 60, 20);
                    this.equipBackImgOne.setBounds(164, 119, 59, 57);
                    this.equipImgOne.setBounds(166, 121, 55, 53);
                    this.radioLabOne.setText("培养");
                    this.radioLabTwo.setText("分解");
                    this.ackBtn.setText("分解");
                }
                else {
                    this.textLabOne.setBounds(82, 228, 60, 18);
                    this.textLabTwo.setBounds(82, 252, 60, 18);
                    this.radioLabOne.setBounds(108, 77, 60, 20);
                    this.radioLabTwo.setBounds(202, 77, 60, 20);
                    this.equipBackImgOne.setBounds(144, 131, 59, 57);
                    this.equipImgOne.setBounds(146, 133, 55, 53);
                    this.radioLabOne.setText("培养");
                    this.radioLabTwo.setText("分解");
                    this.ackBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/按钮_分解_w60,h78.png"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeRecastViewAttributeBtn(boolean type) {
        try {
            this.radioBtnOne.setVisible(type);
            this.radioBtnTwo.setVisible(type);
            this.radioLabOne.setVisible(type);
            this.radioLabTwo.setVisible(type);
            this.textLabOne.setVisible(type);
            this.textLabTwo.setVisible(type);
            this.nowEquipBtn.setVisible(type);
            this.equipBackImgOne.setVisible(type);
            this.equipImgOne.setVisible(type);
            for (int i = 0; i < this.washGoodsLabs.length; ++i) {
                this.washGoodsLabs[i].setVisible(type);
            }
            if (type) {
                this.concealNowEquip();
                this.recoverWashGoodsLabs(-1);
                this.money = new BigDecimal(50000);
                if (MyIsif.getStyle().equals("水墨")) {
                    this.textLabOne.setBounds(102, 228, 60, 18);
                    this.textLabTwo.setBounds(102, 252, 60, 18);
                    this.nowEquipBtn.setBounds(159, 95, 68, 17);
                    this.equipBackImgOne.setBounds(161, 113, 59, 57);
                    this.equipImgOne.setBounds(163, 115, 55, 53);
                    this.radioLabOne.setText("重洗属性");
                    this.radioLabTwo.setText("重悟技能");
                    this.ackBtn.setText("重洗");
                }
                else {
                    this.textLabOne.setBounds(82, 240, 60, 18);
                    this.textLabTwo.setBounds(82, 264, 60, 18);
                    this.nowEquipBtn.setBounds(139, 107, 60, 17);
                    this.equipBackImgOne.setBounds(141, 125, 59, 57);
                    this.equipImgOne.setBounds(143, 127, 55, 53);
                    this.radioLabOne.setText("重洗属性");
                    this.radioLabTwo.setText("重悟技能");
                    this.ackBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/按钮_重洗_w60,h78.png"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeRecastViewSkillBtn(boolean type) {
        try {
            this.radioBtnOne.setVisible(type);
            this.radioBtnTwo.setVisible(type);
            this.radioLabOne.setVisible(type);
            this.radioLabTwo.setVisible(type);
            this.equipImgOne.setVisible(type);
            this.equipImgTwo.setVisible(type);
            this.nowEquipBtn.setVisible(type);
            this.equipBackImgOne.setVisible(type);
            this.equipBackImgTwo.setVisible(type);
            this.textLabOne.setVisible(type);
            this.textLabTwo.setVisible(type);
            if (JframeReclaimSkillMain.getJframeReclaimSkillMain().isVisible()) {
                JframeReclaimSkillMain.getJframeReclaimSkillMain().setVisible(false);
            }
            if (type) {
                this.concealNowEquip();
                this.recoverEquipImgTwo();
                this.money = new BigDecimal(50000);
                if (MyIsif.getStyle().equals("水墨")) {
                    this.textLabOne.setBounds(100, 199, 60, 18);
                    this.textLabTwo.setBounds(100, 223, 60, 18);
                    this.equipBackImgOne.setBounds(111, 126, 59, 57);
                    this.equipBackImgTwo.setBounds(227, 126, 59, 57);
                    this.equipImgOne.setBounds(113, 128, 55, 53);
                    this.equipImgTwo.setBounds(229, 128, 55, 53);
                    this.radioLabOne.setText("重洗属性");
                    this.radioLabTwo.setText("重悟技能");
                    this.nowEquipBtn.setBounds(109, 108, 68, 17);
                    this.ackBtn.setText("重悟");
                }
                else {
                    this.textLabOne.setBounds(80, 211, 60, 18);
                    this.textLabTwo.setBounds(80, 235, 60, 18);
                    this.equipBackImgOne.setBounds(91, 138, 59, 57);
                    this.equipBackImgTwo.setBounds(207, 138, 59, 57);
                    this.equipImgOne.setBounds(93, 140, 55, 53);
                    this.equipImgTwo.setBounds(209, 140, 55, 53);
                    this.radioLabOne.setText("重洗属性");
                    this.radioLabTwo.setText("重悟技能");
                    this.nowEquipBtn.setBounds(89, 120, 60, 17);
                    this.ackBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/按钮_重悟_w60,h78.png"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeAwakenViewUpgradeBtn(boolean type) {
        try {
            this.radioBtnOne.setVisible(type);
            this.radioBtnTwo.setVisible(type);
            this.radioLabOne.setVisible(type);
            this.radioLabTwo.setVisible(type);
            this.equipBackImgOne.setVisible(type);
            this.equipImgOne.setVisible(type);
            this.nowEquipBtn.setVisible(type);
            this.textLabOne.setVisible(type);
            this.textLabTwo.setVisible(type);
            this.maxNumBtn.setVisible(type);
            this.upgradeLab.setVisible(type);
            this.nowNumLab.setVisible(type);
            this.numText.setVisible(type);
            this.recoverSkillMessage();
            if (type) {
                this.concealNowEquip();
                this.money = new BigDecimal(6000);
                this.numText.setText("0");
                this.nowNumLab.setText("<html><body>(拥有:<font color=#FFFF00>" + RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章").toString() + "</font>)</body><html>");
                if (MyIsif.getStyle().equals("水墨")) {
                    this.equipBackImgOne.setBounds(87, 108, 59, 57);
                    this.equipImgOne.setBounds(89, 110, 55, 53);
                    this.nowEquipBtn.setBounds(85, 90, 68, 17);
                    this.radioBtnOne.setBounds(107, 65, 18, 18);
                    this.radioLabOne.setBounds(128, 65, 60, 20);
                    this.radioBtnTwo.setBounds(201, 65, 18, 18);
                    this.radioLabTwo.setBounds(222, 65, 60, 20);
                    this.textLabOne.setBounds(98, 235, 60, 18);
                    this.textLabTwo.setBounds(98, 259, 60, 18);
                    this.radioLabOne.setText("提升等级");
                    this.radioLabTwo.setText("开启觉醒");
                    this.ackBtn.setText("提升");
                }
                else {
                    this.equipBackImgOne.setBounds(67, 120, 59, 57);
                    this.equipImgOne.setBounds(69, 122, 55, 53);
                    this.nowEquipBtn.setBounds(65, 102, 60, 17);
                    this.radioBtnOne.setBounds(87, 77, 19, 19);
                    this.radioLabOne.setBounds(108, 77, 60, 20);
                    this.radioBtnTwo.setBounds(181, 77, 19, 19);
                    this.radioLabTwo.setBounds(202, 77, 60, 20);
                    this.textLabOne.setBounds(78, 247, 60, 18);
                    this.textLabTwo.setBounds(78, 271, 60, 18);
                    this.radioLabOne.setText("提升等级");
                    this.radioLabTwo.setText("开启觉醒");
                    this.ackBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/按钮_提升_w60,h78.png"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeAwakenViewOpenawakenBtn(boolean type) {
        try {
            this.radioBtnOne.setVisible(type);
            this.radioBtnTwo.setVisible(type);
            this.radioLabOne.setVisible(type);
            this.radioLabTwo.setVisible(type);
            this.equipImgOne.setVisible(type);
            this.equipImgTwo.setVisible(type);
            this.nowEquipBtn.setVisible(type);
            this.equipBackImgOne.setVisible(type);
            this.equipBackImgTwo.setVisible(type);
            this.textLabOne.setVisible(type);
            this.textLabTwo.setVisible(type);
            if (type) {
                this.concealNowEquip();
                this.money = new BigDecimal(10000000);
                if (MyIsif.getStyle().equals("水墨")) {
                    this.textLabOne.setBounds(100, 199, 60, 18);
                    this.textLabTwo.setBounds(100, 223, 60, 18);
                    this.equipBackImgOne.setBounds(111, 126, 59, 57);
                    this.equipBackImgTwo.setBounds(227, 126, 59, 57);
                    this.equipImgOne.setBounds(113, 128, 55, 53);
                    this.equipImgTwo.setBounds(229, 128, 55, 53);
                    this.radioLabOne.setText("提升等级");
                    this.radioLabTwo.setText("开启觉醒");
                    this.recoverEquipImgTwo();
                    this.nowEquipBtn.setBounds(109, 108, 68, 17);
                    this.ackBtn.setText("开启");
                }
                else {
                    this.textLabOne.setBounds(80, 211, 60, 18);
                    this.textLabTwo.setBounds(80, 235, 60, 18);
                    this.equipBackImgOne.setBounds(91, 138, 59, 57);
                    this.equipBackImgTwo.setBounds(207, 138, 59, 57);
                    this.equipImgOne.setBounds(93, 140, 55, 53);
                    this.equipImgTwo.setBounds(209, 140, 55, 53);
                    this.radioLabOne.setText("提升等级");
                    this.radioLabTwo.setText("开启觉醒");
                    this.recoverEquipImgTwo();
                    this.nowEquipBtn.setBounds(89, 120, 60, 17);
                    this.ackBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/按钮_开启_w60,h78.png"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void concealNowEquip() {
        if (this.jpanelNowEquip.isVisible()) {
            this.jpanelNowEquip.setVisible(false);
        }
        if (this.scrollPane.isVisible()) {
            this.scrollPane.setVisible(false);
        }
    }
    
    public void chooseRadioBtn(int type) {
        if (this.radioBtnType != type) {
            this.recoverEquipImgOne();
            this.refreshView(this.summonEquipMenuType, this.radioBtnType, false);
            this.radioBtnType = type;
            this.refreshView(this.summonEquipMenuType, this.radioBtnType, true);
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.radioBtnType == 1) {
                    this.radioBtnOne.setIcon(CutButtonImage.getImage("inkImg/button/B214.png", 18, 18));
                    this.radioBtnTwo.setIcon(CutButtonImage.getImage("inkImg/button/B213.png", 18, 18));
                }
                else {
                    this.radioBtnOne.setIcon(CutButtonImage.getImage("inkImg/button/B213.png", 18, 18));
                    this.radioBtnTwo.setIcon(CutButtonImage.getImage("inkImg/button/B214.png", 18, 18));
                }
            }
            else if (this.radioBtnType == 1) {
                this.radioBtnOne.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_选中_w19,h19.png", 19, 19));
                this.radioBtnTwo.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_未选中_w19,h19.png", 19, 19));
            }
            else {
                this.radioBtnOne.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_未选中_w19,h19.png", 19, 19));
                this.radioBtnTwo.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_选中_w19,h19.png", 19, 19));
            }
        }
    }
    
    public void changeViewChooseRadioBtn() {
        this.radioBtnType = 1;
        this.concealNowEquip();
        this.recoverEquipImgOne();
        if (MyIsif.getStyle().equals("水墨")) {
            this.radioBtnOne.setIcon(CutButtonImage.getImage("inkImg/button/B214.png", 18, 18));
            this.radioBtnTwo.setIcon(CutButtonImage.getImage("inkImg/button/B213.png", 18, 18));
            try {
                if (this.summonEquipMenuType == 1) {
                    this.cultivateMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B218.png"));
                    this.recastMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B199.png"));
                    this.awakenMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B215.png"));
                }
                else if (this.summonEquipMenuType == 2) {
                    this.cultivateMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B217.png"));
                    this.recastMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B200.png"));
                    this.awakenMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B215.png"));
                }
                else if (this.summonEquipMenuType == 3) {
                    this.cultivateMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B217.png"));
                    this.recastMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B199.png"));
                    this.awakenMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button/B216.png"));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            this.radioBtnOne.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_选中_w19,h19.png", 19, 19));
            this.radioBtnTwo.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_未选中_w19,h19.png", 19, 19));
            try {
                if (this.summonEquipMenuType == 1) {
                    this.cultivateMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_培养_选中_w63,h78.png"));
                    this.recastMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_重铸_未选中_w63,h78.png"));
                    this.awakenMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_觉醒_未选中_w63,h78.png"));
                }
                else if (this.summonEquipMenuType == 2) {
                    this.cultivateMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_培养_未选中_w63,h78.png"));
                    this.recastMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_重铸_选中_w63,h78.png"));
                    this.awakenMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_觉醒_未选中_w63,h78.png"));
                }
                else if (this.summonEquipMenuType == 3) {
                    this.cultivateMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_培养_未选中_w63,h78.png"));
                    this.recastMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_重铸_未选中_w63,h78.png"));
                    this.awakenMenuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选项卡_觉醒_选中_w63,h78.png"));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void recoverEquipImgTwo() {
        if (this.summonEquipMenuType == 1 && this.radioBtnType == 1) {
            this.equipImgTwo.setText("玄铁晶石");
        }
        else if (this.summonEquipMenuType == 2 && this.radioBtnType == 2) {
            this.equipImgTwo.setText("隐月神石");
        }
        else if (this.summonEquipMenuType == 3 && this.radioBtnType == 2) {
            this.equipImgTwo.setText("千年魂石");
        }
        this.equipImgTwo.setIcon(null);
        this.chooseGoods = null;
    }
    
    public void recoverEquipImgOne() {
        this.equipImgOne.setText("装备");
        this.equipImgOne.setIcon(null);
        this.recoverSkillMessage();
        this.chooseEquip = null;
    }
    
    public void refreshEquipOneImg(Goodstable goodstable) {
        if (goodstable != null) {
            this.equipImgOne.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 55, 53));
        }
        else {
            this.recoverEquipImgOne();
        }
    }
    
    public void recoverSkillMessage() {
        this.upgradeLab.setForeground(Color.gray);
        this.upgradeLab.setText("请放入已有觉醒技的装备");
        this.awakenName.setVisible(false);
        this.levelLab.setVisible(false);
        this.expBackLabImg.setVisible(false);
        this.expLab.setVisible(false);
        this.expLabImg.setVisible(false);
    }
    
    public void recoverWashGoodsLabs(int num) {
        if (this.chooseWashGoods == null) {
            this.getChooseWashGoods();
        }
        if (num < 0) {
            for (int i = 0; i < 5; ++i) {
                this.chooseWashGoods[i] = null;
                if (i == 0) {
                    this.washGoodsLabs[i].setText("晶石");
                }
                else if (i == 1) {
                    this.washGoodsLabs[i].setText("精华");
                }
                else {
                    this.washGoodsLabs[i].setText("九彩");
                }
                this.washGoodsLabs[i].setIcon(null);
            }
        }
        else {
            this.chooseWashGoods[num] = null;
            if (num == 0) {
                this.washGoodsLabs[num].setText("晶石");
            }
            else if (num == 1) {
                this.washGoodsLabs[num].setText("精华");
            }
            else {
                this.washGoodsLabs[num].setText("九彩");
            }
            this.washGoodsLabs[num].setIcon(null);
        }
    }
    
    public String getValuesMessage(String[] values, String strType) {
        int i = 0;
        while (i < values.length) {
            if (values[i].startsWith(strType)) {
                if ("觉醒技".equals(strType)) {
                    return values[i];
                }
                return values[i].split("=")[1];
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    public static long expChangeLevel(long exp) {
        for (int i = 1; i <= 20; ++i) {
            long num = (long)((i * 250 + 50 + 300) * i / 2);
            if (num > exp) {
                return (long)i;
            }
        }
        return 20L;
    }
    
    public static long getMaxExp(long level) {
        return level * 250L + 50L;
    }
    
    public static long getNowExp(long exp) {
        long maxExp = expChangeLevel(exp);
        long num = ((maxExp - 1L) * 250L + 50L + 300L) * (maxExp - 1L) / 2L;
        return exp - num;
    }
    
    public void getpaneMessage() {
        this.summonNamesModel.clear();
        this.summonList.clear();
        this.jpanelNowEquip.getSummonEquipList().clear();
        int maxNum = 0;
        List<RoleSummoning> petListTable = UserMessUntil.getPetListTable();
        for (int i = 0; i < petListTable.size(); ++i) {
            RoleSummoning roleSummoning = (RoleSummoning)petListTable.get(i);
            String stye = roleSummoning.getStye();
            if (stye != null && stye.length() > 1) {
                String[] v = stye.split("\\|");
                ArrayList<Goodstable> summonEquips = this.getSummonEquips(v);
                if (summonEquips.size() != 0) {
                    maxNum += summonEquips.size();
                    this.summonList.add(roleSummoning.getSid());
                    this.summonNamesModel.addElement(roleSummoning.getSummoningname() + "(" + summonEquips.size() + ")");
                }
            }
        }
        this.summonNamesModel.add(0, "全部(" + maxNum + ")");
        this.jpanelNowEquip.getShowName().setText("全部(" + maxNum + ")");
    }
    
    public ArrayList<Goodstable> getSummonEquips(String[] v) {
        ArrayList<Goodstable> goodstables = new ArrayList<>();
        for (int i = 1; i < v.length; ++i) {
            String[] vs = v[i].split("-");
            if (vs.length >= 2 && !vs[0].equals("3")) {
                Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vs[1]));
                if (goodstable != null) {
                    this.jpanelNowEquip.getSummonEquipList().add(goodstable.getRgid());
                    goodstables.add(goodstable);
                }
            }
        }
        return goodstables;
    }
    
    public RoleSummoning getSummonOne(BigDecimal type) {
        List<RoleSummoning> petListTable = UserMessUntil.getPetListTable();
        for (int i = 0; i < petListTable.size(); ++i) {
            RoleSummoning roleSummoning = (RoleSummoning)petListTable.get(i);
            if (roleSummoning.getSid().compareTo(type) == 0) {
                return roleSummoning;
            }
        }
        return null;
    }
    
    public void chooseEquip(Goodstable goodstable) {
        this.chooseEquip = goodstable.getRgid();
        this.equipImgOne.setText("");
        this.equipImgOne.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 55, 53));
    }
    
    public void skillMessage(String[] awakenSkill) {
        this.awakenName.setText("觉醒技:" + awakenSkill[0]);
        this.levelLab.setText("等  级:" + expChangeLevel(Long.parseLong(awakenSkill[3])));
        long nowExp = getNowExp(Long.parseLong(awakenSkill[3]));
        long maxExp = getMaxExp(expChangeLevel(Long.parseLong(awakenSkill[3])));
        this.expLab.setText("经  验:" + nowExp + "/" + maxExp);
        int divide = new BigDecimal(nowExp).multiply(new BigDecimal(102)).divide(new BigDecimal(maxExp), 2, 4).intValue();
        if (MyIsif.getStyle().equals("水墨")) {
            this.expLabImg.setIcon(CutButtonImage.getImage("inkImg/button/26.png", (divide == 0) ? 1 : divide, 12));
        }
        else {
            this.expLabImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_条_102,h8.png", (divide == 0) ? 1 : divide, 12));
        }
    }
    
    public void getAwakenSkill() {
        if (this.getSummonEquipMenuType() == 3 && this.getRadioBtnType() == 1) {
            if (this.getChooseEquip() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择召唤兽装备");
                return;
            }
            Goodstable chooseEquip = GoodsListFromServerUntil.getRgid(this.getChooseEquip());
            if (chooseEquip == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择召唤兽装备");
                return;
            }
            String[] values = chooseEquip.getValue().split("\\|");
            String valuesMessage = this.getValuesMessage(values, "觉醒技");
            if (valuesMessage == null) {
                this.getUpgradeLab().setForeground(Color.red);
                this.getUpgradeLab().setText("该装备还没有觉醒技");
                this.getUpgradeLab().setVisible(true);
                this.getExpLabImg().setVisible(false);
                this.getAwakenName().setVisible(false);
                this.getLevelLab().setVisible(false);
                this.getExpBackLabImg().setVisible(false);
                this.getExpLab().setVisible(false);
            }
            else {
                String[] awakenSkill = valuesMessage.split("&");
                this.skillMessage(awakenSkill);
                this.getUpgradeLab().setVisible(false);
                this.getExpLabImg().setVisible(true);
                this.getAwakenName().setVisible(true);
                this.getLevelLab().setVisible(true);
                this.getExpBackLabImg().setVisible(true);
                this.getExpLab().setVisible(true);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/background1/B297.png", 382, 455);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 382, 455, this);
            if (this.textLabImg == null) {
                this.textLabImg = CutButtonImage.getImage("inkImg/background/S128.png", 124, 42);
            }
            if (this.recastWashImg == null) {
                this.recastWashImg = CutButtonImage.getImage("inkImg/background/S129.png", 250, 50);
            }
            if (this.awakenUpLvlImg == null) {
                this.awakenUpLvlImg = CutButtonImage.getImage("inkImg/background/S130.png", 165, 56);
            }
            GoodsListFromServerUntil.drawSummonGoods(g, 46, 315);
            if (this.summonEquipMenuType == 1 && this.radioBtnType == 1) {
                g.drawImage(this.textLabImg.getImage(), 166, 217, 124, 42, this);
                Util.drawMoney(g, 168, 255);
                Util.drawPrice(g, this.money, 168, 231);
            }
            else if (this.summonEquipMenuType == 2 && this.radioBtnType == 1) {
                g.drawImage(this.textLabImg.getImage(), 166, 229, 124, 42, this);
                g.drawImage(this.recastWashImg.getImage(), 68, 175, this.recastWashImg.getIconWidth(), this.recastWashImg.getIconHeight(), this);
                Util.drawMoney(g, 168, 267);
                Util.drawPrice(g, this.money, 168, 243);
            }
            else if (this.summonEquipMenuType == 2 && this.radioBtnType == 2) {
                g.drawImage(this.textLabImg.getImage(), 164, 200, 124, 42, this);
                Util.drawMoney(g, 166, 238);
                Util.drawPrice(g, this.money, 166, 214);
            }
            else if (this.summonEquipMenuType == 3 && this.radioBtnType == 1) {
                g.drawImage(this.textLabImg.getImage(), 162, 170, 124, 42, this);
                g.drawImage(this.textLabImg.getImage(), 162, 235, 124, 42, this);
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(Color.white);
                g.drawString("消耗物品", 98, 185);
                g.drawString("比斗奖章", 164, 185);
                g.drawString("消耗奖章", 98, 209);
                g.drawImage(this.awakenUpLvlImg.getImage(), 150, 108, 165, 56, this);
                Util.drawMoney(g, 164, 274);
                Util.drawPrice(g, this.money, 164, 250);
            }
            else if (this.summonEquipMenuType == 3 && this.radioBtnType == 2) {
                g.drawImage(this.textLabImg.getImage(), 164, 200, 124, 45, this);
                Util.drawMoney(g, 166, 238);
                Util.drawPrice(g, this.money, 166, 214);
            }
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("img/xy2uiimg/装备炼化_底板_w356,h477.png", 356, 477);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 356, 477, this);
            if (this.textLabImg == null) {
                this.textLabImg = CutButtonImage.getImage("img/xy2uiimg/输入框_双框_w124,h45.png", 124, 45);
            }
            if (this.recastWashImg == null) {
                this.recastWashImg = CutButtonImage.getImage("img/xy2uiimg/重铸_重洗框_w250,h50.png", 250, 50);
            }
            if (this.awakenUpLvlImg == null) {
                this.awakenUpLvlImg = CutButtonImage.getImage("img/xy2uiimg/框_觉醒技能装备_w165,h56.png", 165, 56);
            }
            GoodsListFromServerUntil.drawSummonGoods(g, 26, 327);
            if (this.summonEquipMenuType == 1 && this.radioBtnType == 1) {
                g.drawImage(this.textLabImg.getImage(), 146, 229, 124, 45, this);
                Util.drawMoney(g, 148, 267);
                Util.drawPrice(g, this.money, 148, 243);
            }
            else if (this.summonEquipMenuType == 2 && this.radioBtnType == 1) {
                g.drawImage(this.textLabImg.getImage(), 146, 241, 124, 45, this);
                g.drawImage(this.recastWashImg.getImage(), 48, 187, this.recastWashImg.getIconWidth(), this.recastWashImg.getIconHeight(), this);
                Util.drawMoney(g, 148, 279);
                Util.drawPrice(g, this.money, 148, 255);
            }
            else if (this.summonEquipMenuType == 2 && this.radioBtnType == 2) {
                g.drawImage(this.textLabImg.getImage(), 144, 212, 124, 45, this);
                Util.drawMoney(g, 146, 250);
                Util.drawPrice(g, this.money, 146, 226);
            }
            else if (this.summonEquipMenuType == 3 && this.radioBtnType == 1) {
                g.drawImage(this.textLabImg.getImage(), 142, 182, 124, 45, this);
                g.drawImage(this.textLabImg.getImage(), 142, 247, 124, 45, this);
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(Color.white);
                g.drawString("消耗物品", 78, 197);
                g.drawString("比斗奖章", 144, 197);
                g.drawString("消耗奖章", 78, 221);
                g.drawImage(this.awakenUpLvlImg.getImage(), 130, 120, 165, 56, this);
                Util.drawMoney(g, 144, 286);
                Util.drawPrice(g, this.money, 144, 262);
            }
            else if (this.summonEquipMenuType == 3 && this.radioBtnType == 2) {
                g.drawImage(this.textLabImg.getImage(), 144, 212, 124, 45, this);
                Util.drawMoney(g, 146, 250);
                Util.drawPrice(g, this.money, 146, 226);
            }
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public ImageIcon getTextLabImg() {
        return this.textLabImg;
    }
    
    public void setTextLabImg(ImageIcon textLabImg) {
        this.textLabImg = textLabImg;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public ImageIcon getRecastWashImg() {
        return this.recastWashImg;
    }
    
    public void setRecastWashImg(ImageIcon recastWashImg) {
        this.recastWashImg = recastWashImg;
    }
    
    public ImageIcon getAwakenUpLvlImg() {
        return this.awakenUpLvlImg;
    }
    
    public void setAwakenUpLvlImg(ImageIcon awakenUpLvlImg) {
        this.awakenUpLvlImg = awakenUpLvlImg;
    }
    
    public JLabel getRadioBtnOne() {
        if (this.radioBtnOne == null) {
            this.radioBtnOne = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.radioBtnOne.setIcon(CutButtonImage.getImage("inkImg/button/B214.png", 18, 18));
                this.radioBtnOne.setBounds(107, 65, 18, 18);
            }
            else {
                this.radioBtnOne.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_选中_w19,h19.png", 19, 19));
                this.radioBtnOne.setBounds(87, 77, 19, 19);
            }
            this.radioBtnOne.setOpaque(false);
            this.radioBtnOne.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JpanelSummonEquipMain.this.chooseRadioBtn(1);
                }
            });
            this.add(this.radioBtnOne);
        }
        return this.radioBtnOne;
    }
    
    public void setRadioBtnOne(JLabel radioBtnOne) {
        this.radioBtnOne = radioBtnOne;
    }
    
    public JLabel getRadioBtnTwo() {
        if (this.radioBtnTwo == null) {
            this.radioBtnTwo = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.radioBtnTwo.setIcon(CutButtonImage.getImage("inkImg/button/B213.png", 18, 18));
                this.radioBtnTwo.setBounds(201, 65, 18, 18);
            }
            else {
                this.radioBtnTwo.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_未选中_w19,h19.png", 18, 18));
                this.radioBtnTwo.setBounds(181, 77, 19, 19);
            }
            this.radioBtnTwo.setOpaque(false);
            this.radioBtnTwo.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JpanelSummonEquipMain.this.chooseRadioBtn(2);
                }
            });
            this.add(this.radioBtnTwo);
        }
        return this.radioBtnTwo;
    }
    
    public void setRadioBtnTwo(JLabel radioBtnTwo) {
        this.radioBtnTwo = radioBtnTwo;
    }
    
    public JLabel getRadioLabOne() {
        if (this.radioLabOne == null) {
            (this.radioLabOne = new JLabel("洗练属性")).setForeground(Color.white);
            this.radioLabOne.setFont(UIUtils.TEXT_FONT);
            this.radioLabOne.setBounds(108, 77, 60, 20);
            this.radioLabOne.setOpaque(false);
            this.add(this.radioLabOne);
        }
        return this.radioLabOne;
    }
    
    public void setRadioLabOne(JLabel radioLabOne) {
        this.radioLabOne = radioLabOne;
    }
    
    public JLabel getRadioLabTwo() {
        if (this.radioLabTwo == null) {
            (this.radioLabTwo = new JLabel("重悟技能")).setForeground(Color.white);
            this.radioLabTwo.setFont(UIUtils.TEXT_FONT);
            this.radioLabTwo.setBounds(202, 77, 60, 20);
            this.radioLabTwo.setOpaque(false);
            this.add(this.radioLabTwo);
        }
        return this.radioLabTwo;
    }
    
    public void setRadioLabTwo(JLabel radioLabTwo) {
        this.radioLabTwo = radioLabTwo;
    }
    
    public int getRadioBtnType() {
        return this.radioBtnType;
    }
    
    public void setRadioBtnType(int radioBtnType) {
        this.radioBtnType = radioBtnType;
    }
    
    public BtnSummonEquipMain getNowEquipBtn() {
        if (this.nowEquipBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.nowEquipBtn = new BtnSummonEquipMain("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "已穿装备", 11, this)).setBounds(109, 108, 68, 17);
            }
            else {
                (this.nowEquipBtn = new BtnSummonEquipMain("img/xy2uiimg/文字按钮_已穿装备_w60,h51.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "", 11, this)).setBounds(89, 120, 60, 17);
            }
            this.add(this.nowEquipBtn);
        }
        return this.nowEquipBtn;
    }
    
    public void setNowEquipBtn(BtnSummonEquipMain nowEquipBtn) {
        this.nowEquipBtn = nowEquipBtn;
    }
    
    public int getSummonEquipMenuType() {
        return this.summonEquipMenuType;
    }
    
    public void setSummonEquipMenuType(int summonEquipMenuType) {
        this.summonEquipMenuType = summonEquipMenuType;
    }
    
    public JLabel getEquipImgOne() {
        if (this.equipImgOne == null) {
            this.equipImgOne = new JLabel("装备", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.equipImgOne.setBounds(113, 128, 55, 53);
            }
            else {
                this.equipImgOne.setBounds(93, 140, 55, 53);
            }
            this.equipImgOne.setForeground(Color.gray);
            this.equipImgOne.setFont(UIUtils.TEXT_FONT);
            this.equipImgOne.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 3) {
                        JpanelSummonEquipMain.this.recoverEquipImgOne();
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (JpanelSummonEquipMain.this.chooseEquip != null) {
                        Goodstable goodstable = GoodsListFromServerUntil.getRgid(JpanelSummonEquipMain.this.chooseEquip);
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
            });
            this.add(this.equipImgOne);
        }
        return this.equipImgOne;
    }
    
    public void setEquipImgOne(JLabel equipImgOne) {
        this.equipImgOne = equipImgOne;
    }
    
    public JLabel getEquipImgTwo() {
        if (this.equipImgTwo == null) {
            this.equipImgTwo = new JLabel("玄铁晶石", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.equipImgTwo.setBounds(229, 128, 55, 53);
            }
            else {
                this.equipImgTwo.setBounds(209, 140, 55, 53);
            }
            this.equipImgTwo.setForeground(Color.gray);
            this.equipImgTwo.setFont(UIUtils.TEXT_FONT);
            this.equipImgTwo.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 3) {
                        JpanelSummonEquipMain.this.recoverEquipImgTwo();
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (JpanelSummonEquipMain.this.chooseGoods != null) {
                        Goodstable goodstable = GoodsListFromServerUntil.getRgid(JpanelSummonEquipMain.this.chooseGoods);
                        if (goodstable == null) {
                            return;
                        }
                        ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    TestpackJframe.getTestpackJframe().getJpac().ClearText();
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
            });
            this.add(this.equipImgTwo);
        }
        return this.equipImgTwo;
    }
    
    public void setEquipImgTwo(JLabel equipImgTwo) {
        this.equipImgTwo = equipImgTwo;
    }
    
    public JLabel getEquipBackImgOne() {
        if (this.equipBackImgOne == null) {
            this.equipBackImgOne = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.equipBackImgOne.setIcon(CutButtonImage.getImage("inkImg/background/S131.png", 59, 57));
                this.equipBackImgOne.setBounds(111, 126, 59, 57);
            }
            else {
                this.equipBackImgOne.setIcon(CutButtonImage.getImage("img/xy2uiimg/物品框_w59,h57.png", 59, 57));
                this.equipBackImgOne.setBounds(91, 138, 59, 57);
            }
            this.add(this.equipBackImgOne);
        }
        return this.equipBackImgOne;
    }
    
    public void setEquipBackImgOne(JLabel equipBackImgOne) {
        this.equipBackImgOne = equipBackImgOne;
    }
    
    public JLabel getEquipBackImgTwo() {
        if (this.equipBackImgTwo == null) {
            this.equipBackImgTwo = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.equipBackImgTwo.setIcon(CutButtonImage.getImage("inkImg/background/S131.png", 59, 57));
                this.equipBackImgTwo.setBounds(227, 126, 59, 57);
            }
            else {
                this.equipBackImgTwo.setIcon(CutButtonImage.getImage("img/xy2uiimg/物品框_w59,h57.png", 59, 57));
                this.equipBackImgTwo.setBounds(207, 138, 59, 57);
            }
            this.add(this.equipBackImgTwo);
        }
        return this.equipBackImgTwo;
    }
    
    public void setEquipBackImgTwo(JLabel equipBackImgTwo) {
        this.equipBackImgTwo = equipBackImgTwo;
    }
    
    public JLabel getTextLabOne() {
        if (this.textLabOne == null) {
            this.textLabOne = new JLabel("消耗金钱");
            if (MyIsif.getStyle().equals("水墨")) {
                this.textLabOne.setBounds(102, 216, 60, 18);
            }
            else {
                this.textLabOne.setBounds(82, 228, 60, 18);
            }
            this.textLabOne.setForeground(Color.white);
            this.textLabOne.setFont(UIUtils.TEXT_FONT);
            this.add(this.textLabOne);
        }
        return this.textLabOne;
    }
    
    public void setTextLabOne(JLabel textLabOne) {
        this.textLabOne = textLabOne;
    }
    
    public JLabel getTextLabTwo() {
        if (this.textLabTwo == null) {
            this.textLabTwo = new JLabel("拥有金钱");
            if (MyIsif.getStyle().equals("水墨")) {
                this.textLabTwo.setBounds(102, 240, 60, 18);
            }
            else {
                this.textLabTwo.setBounds(82, 252, 60, 18);
            }
            this.textLabTwo.setForeground(Color.white);
            this.textLabTwo.setFont(UIUtils.TEXT_FONT);
            this.add(this.textLabTwo);
        }
        return this.textLabTwo;
    }
    
    public void setTextLabTwo(JLabel textLabTwo) {
        this.textLabTwo = textLabTwo;
    }
    
    public JLabel[] getGoodsLabs() {
        if (this.goodsLabs == null) {
            this.goodsLabs = new JLabel[12];
            for (int i = 0; i < this.goodsLabs.length; ++i) {
                this.goodsLabs[i] = new JLabel();
                if (MyIsif.getStyle().equals("水墨")) {
                    this.goodsLabs[i].setBounds(46 + i % 6 * 51, 315 + i / 6 * 51, 50, 50);
                }
                else {
                    this.goodsLabs[i].setBounds(26 + i % 6 * 51, 327 + i / 6 * 51, 50, 50);
                }
                this.goodsLabs[i].addMouseListener(new MouseListenerSummonEquipMain(i, this));
                this.add(this.goodsLabs[i]);
            }
        }
        return this.goodsLabs;
    }
    
    public void setGoodsLabs(JLabel[] goodsLabs) {
        this.goodsLabs = goodsLabs;
    }
    
    public BtnSummonEquipMain getAckBtn() {
        if (this.ackBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.ackBtn = new BtnSummonEquipMain("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "培养", 12, this)).setBounds(164, 283, 59, 24);
            }
            else {
                (this.ackBtn = new BtnSummonEquipMain("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "", 12, this)).setBounds(144, 295, 60, 26);
            }
            this.add(this.ackBtn);
        }
        return this.ackBtn;
    }
    
    public void setAckBtn(BtnSummonEquipMain ackBtn) {
        this.ackBtn = ackBtn;
    }
    
    public BtnSummonEquipMain getCashRewardsBtn() {
        if (this.cashRewardsBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.cashRewardsBtn = new BtnSummonEquipMain("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "兑换奖励", 13, this)).setBounds(43, 421, 68, 17);
            }
            else {
                (this.cashRewardsBtn = new BtnSummonEquipMain("img/xy2uiimg/文字按钮_兑换奖励_w68,h51.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "", 13, this)).setBounds(23, 433, 68, 17);
            }
            this.add(this.cashRewardsBtn);
        }
        return this.cashRewardsBtn;
    }
    
    public void setCashRewardsBtn(BtnSummonEquipMain cashRewardsBtn) {
        this.cashRewardsBtn = cashRewardsBtn;
    }
    
    public JpanelNowEquip getJpanelNowEquip() {
        if (this.jpanelNowEquip == null) {
            this.jpanelNowEquip = new JpanelNowEquip(this);
            if (MyIsif.getStyle().equals("水墨")) {
                this.jpanelNowEquip.setBounds(30, 128, 330, 150);
            }
            else {
                this.jpanelNowEquip.setBounds(10, 140, 330, 150);
            }
            this.add(this.jpanelNowEquip);
        }
        return this.jpanelNowEquip;
    }
    
    public void setJpanelNowEquip(JpanelNowEquip jpanelNowEquip) {
        this.jpanelNowEquip = jpanelNowEquip;
    }
    
    public JList<String> getSummonNameJlist() {
        if (this.summonNameJlist == null) {
            (this.summonNameJlist = new JList<>()).setSelectionBackground(new Color(33, 42, 52));
            Color fontcolor = Color.GREEN;
            this.summonNameJlist.setSelectionForeground(fontcolor);
            this.summonNameJlist.setForeground(fontcolor);
            this.summonNameJlist.setFont(new Font("微软雅黑", 0, 14));
            this.summonNameJlist.setBackground(UIUtils.Color_BACK);
            this.summonNameJlist.setOpaque(false);
            this.summonNameJlist.setModel(this.getSummonNamesModel());
            this.summonNameJlist.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int selectedIndex = JpanelSummonEquipMain.this.summonNameJlist.getSelectedIndex();
                    if (selectedIndex != -1) {
                        JpanelSummonEquipMain.this.jpanelNowEquip.getSummonEquipList().clear();
                        if (selectedIndex == 0) {
                            for (int i = 0; i < JpanelSummonEquipMain.this.summonList.size(); ++i) {
                                String[] v = JpanelSummonEquipMain.this.getSummonOne((BigDecimal)JpanelSummonEquipMain.this.summonList.get(i)).getStye().split("\\|");
                                JpanelSummonEquipMain.this.getSummonEquips(v);
                            }
                        }
                        else {
                            String[] v2 = JpanelSummonEquipMain.this.getSummonOne((BigDecimal)JpanelSummonEquipMain.this.summonList.get(selectedIndex - 1)).getStye().split("\\|");
                            JpanelSummonEquipMain.this.getSummonEquips(v2);
                        }
                        JpanelSummonEquipMain.this.scrollPane.setVisible(false);
                    }
                    JpanelSummonEquipMain.this.jpanelNowEquip.getShowName().setText((String)JpanelSummonEquipMain.this.summonNameJlist.getSelectedValue());
                    JpanelSummonEquipMain.this.jpanelNowEquip.setNowPage(1);
                }
            });
        }
        return this.summonNameJlist;
    }
    
    public void setSummonNameJlist(JList<String> summonNameJlist) {
        this.summonNameJlist = summonNameJlist;
    }
    
    public DefaultListModel<String> getSummonNamesModel() {
        if (this.summonNamesModel == null) {
            this.summonNamesModel = new DefaultListModel<>();
        }
        return this.summonNamesModel;
    }
    
    public void setSummonNamesModel(DefaultListModel<String> summonNamesModel) {
        this.summonNamesModel = summonNamesModel;
    }
    
    public JScrollPane getScrollPane() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.scrollPane == null) {
                (this.scrollPane = new JScrollPane(this.getSummonNameJlist()) {
                    private ImageIcon iconBack = new ImageIcon("inkImg/background/S132.png");
                    
                    {
                        this.setOpaque(false);
                    }
                    
                    @Override
                    public void paint(Graphics g) {
                        g.drawImage(this.iconBack.getImage(), -2, 0, this);
                        super.paint(g);
                    }
                }).setVerticalScrollBarPolicy(22);
                this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.scrollPane.getVerticalScrollBar().setUnitIncrement(30);
                this.scrollPane.getViewport().setOpaque(false);
                this.scrollPane.setOpaque(false);
                this.scrollPane.setBounds(42, 268, 148, 120);
                this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
                this.scrollPane.setHorizontalScrollBarPolicy(31);
                this.add(this.scrollPane);
            }
        }
        else if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane(this.getSummonNameJlist()) {
                private ImageIcon iconBack = new ImageIcon("img/xy2uiimg/已穿装备_底板_下拉框w147,h120.png");
                
                {
                    this.setOpaque(false);
                }
                
                @Override
                public void paint(Graphics g) {
                    g.drawImage(this.iconBack.getImage(), 0, 0, this);
                    super.paint(g);
                }
            }).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.scrollPane));
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(22, 280, 143, 120);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public BtnSummonEquipMain getCultivateMenuBtn() {
        if (this.cultivateMenuBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.cultivateMenuBtn = new BtnSummonEquipMain("inkImg/button/B218.png", 1, "", 1, this)).setBounds(54, 28, 63, 26);
            }
            else {
                (this.cultivateMenuBtn = new BtnSummonEquipMain("img/xy2uiimg/小选项卡_培养_选中_w63,h78.png", 1, "", 1, this)).setBounds(30, 40, 63, 26);
            }
            this.add(this.cultivateMenuBtn);
        }
        return this.cultivateMenuBtn;
    }
    
    public void setCultivateMenuBtn(BtnSummonEquipMain cultivateMenuBtn) {
        this.cultivateMenuBtn = cultivateMenuBtn;
    }
    
    public BtnSummonEquipMain getRecastMenuBtn() {
        if (this.recastMenuBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.recastMenuBtn = new BtnSummonEquipMain("inkImg/button/B199.png", 1, "", 2, this)).setBounds(119, 28, 63, 26);
            }
            else {
                (this.recastMenuBtn = new BtnSummonEquipMain("img/xy2uiimg/小选项卡_重铸_未选中_w63,h78.png", 1, "", 2, this)).setBounds(95, 40, 63, 26);
            }
            this.add(this.recastMenuBtn);
        }
        return this.recastMenuBtn;
    }
    
    public void setRecastMenuBtn(BtnSummonEquipMain recastMenuBtn) {
        this.recastMenuBtn = recastMenuBtn;
    }
    
    public BtnSummonEquipMain getAwakenMenuBtn() {
        if (this.awakenMenuBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.awakenMenuBtn = new BtnSummonEquipMain("inkImg/button/B215.png", 1, "", 3, this)).setBounds(184, 28, 63, 26);
            }
            else {
                (this.awakenMenuBtn = new BtnSummonEquipMain("img/xy2uiimg/小选项卡_觉醒_未选中_w63,h78.png", 1, "", 3, this)).setBounds(160, 40, 63, 26);
            }
            this.add(this.awakenMenuBtn);
        }
        return this.awakenMenuBtn;
    }
    
    public void setAwakenMenuBtn(BtnSummonEquipMain awakenMenuBtn) {
        this.awakenMenuBtn = awakenMenuBtn;
    }
    
    public BtnSummonEquipMain getMaxNumBtn() {
        if (this.maxNumBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.maxNumBtn = new BtnSummonEquipMain("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "最大", 14, this)).setBounds(250, 194, 34, 17);
            }
            else {
                (this.maxNumBtn = new BtnSummonEquipMain("img/xy2uiimg/文字按钮_最大_w34,h51.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "最大", 14, this)).setBounds(230, 206, 34, 17);
            }
            this.maxNumBtn.setVisible(false);
            this.add(this.maxNumBtn);
        }
        return this.maxNumBtn;
    }
    
    public void setMaxNumBtn(BtnSummonEquipMain maxNumBtn) {
        this.maxNumBtn = maxNumBtn;
    }
    
    public JLabel getUpgradeLab() {
        if (this.upgradeLab == null) {
            this.upgradeLab = new JLabel("觉醒技:大圣附体\r\n等级:19\r\n经验:1501/4800", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.upgradeLab.setBounds(152, 110, 159, 50);
            }
            else {
                this.upgradeLab.setBounds(132, 122, 159, 50);
            }
            this.upgradeLab.setFont(UIUtils.TEXT_FONT);
            this.upgradeLab.setForeground(Color.gray);
            this.upgradeLab.setVisible(false);
            this.add(this.upgradeLab);
        }
        return this.upgradeLab;
    }
    
    public void setUpgradeLab(JLabel upgradeLab) {
        this.upgradeLab = upgradeLab;
    }
    
    public JLabel getNowNumLab() {
        if (this.nowNumLab == null) {
            this.nowNumLab = new JLabel("<html><body>(拥有:<font color=#FFFF00>" + RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章").toString() + "</font>)</body><html>");
            if (MyIsif.getStyle().equals("水墨")) {
                this.nowNumLab.setBounds(160, 215, 160, 17);
            }
            else {
                this.nowNumLab.setBounds(140, 227, 160, 17);
            }
            this.nowNumLab.setFont(UIUtils.TEXT_FONT);
            this.nowNumLab.setForeground(Color.white);
            this.nowNumLab.setVisible(false);
            this.add(this.nowNumLab);
        }
        return this.nowNumLab;
    }
    
    public void setNowNumLab(JLabel nowNumLab) {
        this.nowNumLab = nowNumLab;
    }
    
    public JLabel[] getWashGoodsLabs() {
    	 class MListener extends MouseAdapter
         {
             private int i;
             
             public MListener(int i) {
             	this.i = i;
             }
             
             @Override
             public void mousePressed(MouseEvent e) {
                 if (e.getButton() == 3) {
                     JpanelSummonEquipMain.this.recoverWashGoodsLabs(this.i);
                 }
             }
             
             @Override
             public void mouseEntered(MouseEvent e) {
                 Goodstable goodstable = JpanelSummonEquipMain.this.chooseWashGoods[this.i];
                 if (goodstable != null) {
                     ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                 }
             }
             
             @Override
             public void mouseExited(MouseEvent e) {
                 TestpackJframe.getTestpackJframe().getJpac().ClearText();
                 ZhuFrame.getZhuJpanel().cleargoodtext();
             }
         }
        if (this.washGoodsLabs == null) {
            this.washGoodsLabs = new JLabel[5];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.washGoodsLabs.length; ++i) {
                    (this.washGoodsLabs[i] = new JLabel("", 0)).setBounds(76 + i * 49, 180, 39, 39);
                    if (i == 0) {
                        this.washGoodsLabs[i].setText("晶石");
                    }
                    else if (i == 1) {
                        this.washGoodsLabs[i].setText("精华");
                    }
                    else {
                        this.washGoodsLabs[i].setText("九彩");
                    }
                    this.washGoodsLabs[i].setFont(UIUtils.TEXT_FONT);
                    this.washGoodsLabs[i].setForeground(Color.gray);
                    this.washGoodsLabs[i].setVisible(false);
                   
                    this.washGoodsLabs[i].addMouseListener(new MListener(i));
                    this.add(this.washGoodsLabs[i]);
                }
            }
            else {
                for (int i = 0; i < this.washGoodsLabs.length; ++i) {
                    (this.washGoodsLabs[i] = new JLabel("", 0)).setBounds(56 + i * 49, 192, 39, 39);
                    if (i == 0) {
                        this.washGoodsLabs[i].setText("晶石");
                    }
                    else if (i == 1) {
                        this.washGoodsLabs[i].setText("精华");
                    }
                    else {
                        this.washGoodsLabs[i].setText("九彩");
                    }
                    this.washGoodsLabs[i].setFont(UIUtils.TEXT_FONT);
                    this.washGoodsLabs[i].setForeground(Color.gray);
                    this.washGoodsLabs[i].setVisible(false);
                    this.washGoodsLabs[i].addMouseListener(new MListener(i));
                    this.add(this.washGoodsLabs[i]);
                }
            }
        }
        return this.washGoodsLabs;
    }
    
    public void setWashGoodsLabs(JLabel[] washGoodsLabs) {
        this.washGoodsLabs = washGoodsLabs;
    }
    
    public BigDecimal getChooseEquip() {
        return this.chooseEquip;
    }
    
    public void setChooseEquip(BigDecimal chooseEquip) {
        this.chooseEquip = chooseEquip;
    }
    
    public BigDecimal getChooseGoods() {
        return this.chooseGoods;
    }
    
    public void setChooseGoods(BigDecimal chooseGoods) {
        this.chooseGoods = chooseGoods;
    }
    
    public Goodstable[] getChooseWashGoods() {
        if (this.chooseWashGoods == null) {
            this.chooseWashGoods = new Goodstable[5];
        }
        return this.chooseWashGoods;
    }
    
    public void setChooseWashGoods(Goodstable[] chooseWashGoods) {
        this.chooseWashGoods = chooseWashGoods;
    }
    
    public JTextField getNumText() {
        if (this.numText == null) {
            (this.numText = new JTextField()).setForeground(Color.white);
            this.numText.setFont(UIUtils.TEXT_FONT1);
            if (MyIsif.getStyle().equals("水墨")) {
                this.numText.setBounds(164, 195, 87, 16);
            }
            else {
                this.numText.setBounds(144, 207, 87, 16);
            }
            this.numText.setOpaque(false);
            this.numText.setCaretColor(Color.white);
            this.numText.setBorder(null);
            this.numText.setVisible(false);
            this.add(this.numText);
            this.numText.getDocument().addDocumentListener(new DocumentListener() {
                BigDecimal gold = new BigDecimal(6000);
                
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = JpanelSummonEquipMain.this.numText.getText();
                    try {
                        JpanelSummonEquipMain.this.money = this.gold.multiply(new BigDecimal(s));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = JpanelSummonEquipMain.this.numText.getText();
                    try {
                        JpanelSummonEquipMain.this.money = this.gold.multiply(new BigDecimal(s));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.numText.addKeyListener(new KeyListener() {
                boolean type = true;
                
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = JpanelSummonEquipMain.this.numText.getText();
                    if (str.isEmpty()) {
                        JpanelSummonEquipMain.this.numText.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        JpanelSummonEquipMain.this.numText.setText("");
                    }
                    if (!str.isEmpty()) {
                        if (JpanelSummonEquipMain.this.scoretype.compareTo(new BigDecimal(str)) <= 0) {
                            this.type = false;
                            e.consume();
                            JpanelSummonEquipMain.this.numText.setText(JpanelSummonEquipMain.this.scoretype.toString());
                        }
                        else {
                            this.type = true;
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (this.type) {
                        String str = JpanelSummonEquipMain.this.numText.getText();
                        if (JpanelSummonEquipMain.this.scoretype.compareTo(new BigDecimal(str)) <= 0) {
                            e.consume();
                            JpanelSummonEquipMain.this.numText.setText(JpanelSummonEquipMain.this.scoretype.toString());
                        }
                    }
                }
            });
        }
        return this.numText;
    }
    
    public void setNumText(JTextField numText) {
        this.numText = numText;
    }
    
    public JLabel getAwakenName() {
        if (this.awakenName == null) {
            (this.awakenName = new JLabel("觉醒技:  大圣附体")).setForeground(Color.white);
            this.awakenName.setFont(UIUtils.TEXT_FONT);
            if (MyIsif.getStyle().equals("水墨")) {
                this.awakenName.setBounds(160, 109, 151, 16);
            }
            else {
                this.awakenName.setBounds(140, 121, 151, 16);
            }
            this.awakenName.setVisible(false);
            this.add(this.awakenName);
        }
        return this.awakenName;
    }
    
    public void setAwakenName(JLabel awakenName) {
        this.awakenName = awakenName;
    }
    
    public JLabel getLevelLab() {
        if (this.levelLab == null) {
            (this.levelLab = new JLabel("等  级:  19")).setForeground(Color.white);
            this.levelLab.setFont(UIUtils.TEXT_FONT);
            if (MyIsif.getStyle().equals("水墨")) {
                this.levelLab.setBounds(160, 125, 151, 16);
            }
            else {
                this.levelLab.setBounds(140, 137, 151, 16);
            }
            this.levelLab.setVisible(false);
            this.add(this.levelLab);
        }
        return this.levelLab;
    }
    
    public void setLevelLab(JLabel levelLab) {
        this.levelLab = levelLab;
    }
    
    public JLabel getExpLab() {
        if (this.expLab == null) {
            (this.expLab = new JLabel("经  验:  1501/4800")).setForeground(Color.white);
            this.expLab.setFont(UIUtils.TEXT_FONT);
            if (MyIsif.getStyle().equals("水墨")) {
                this.expLab.setBounds(160, 141, 151, 16);
            }
            else {
                this.expLab.setBounds(140, 153, 151, 16);
            }
            this.expLab.setVisible(false);
            this.add(this.expLab);
        }
        return this.expLab;
    }
    
    public void setExpLab(JLabel expLab) {
        this.expLab = expLab;
    }
    
    public JLabel getExpLabImg() {
        if (this.expLabImg == null) {
            this.expLabImg = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.expLabImg.setBounds(204, 146, 110, 8);
                this.expLabImg.setIcon(CutButtonImage.getImage("inkImg/button/26.png", 102, 8));
            }
            else {
                this.expLabImg.setBounds(184, 158, 110, 8);
                this.expLabImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_条_102,h8.png", 102, 8));
            }
            this.expLabImg.setVisible(false);
            this.add(this.expLabImg);
        }
        return this.expLabImg;
    }
    
    public void setExpLabImg(JLabel expLabImg) {
        this.expLabImg = expLabImg;
    }
    
    public JLabel getExpBackLabImg() {
        if (this.expBackLabImg == null) {
            this.expBackLabImg = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.expBackLabImg.setBounds(201, 141, 110, 18);
                this.expBackLabImg.setIcon(CutButtonImage.getImage("inkImg/background/S134.png", 110, 19));
            }
            else {
                this.expBackLabImg.setBounds(181, 154, 110, 18);
                this.expBackLabImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/经验_外框_110,h18.png", 110, 19));
            }
            this.expBackLabImg.setVisible(false);
            this.add(this.expBackLabImg);
        }
        return this.expBackLabImg;
    }
    
    public void setExpBackLabImg(JLabel expBackLabImg) {
        this.expBackLabImg = expBackLabImg;
    }
    
    public ArrayList<BigDecimal> getSummonList() {
        if (this.summonList == null) {
            this.summonList = new ArrayList<>();
        }
        return this.summonList;
    }
    
    public void setSummonList(ArrayList<BigDecimal> summonList) {
        this.summonList = summonList;
    }
    
    public BigDecimal getScoretype() {
        return this.scoretype;
    }
    
    public void setScoretype(BigDecimal scoretype) {
        this.scoretype = scoretype;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
