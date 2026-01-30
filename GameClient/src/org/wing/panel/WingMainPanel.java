package org.wing.panel;

import org.wing.mouseListener.ChooseRefiningCheckboxMouseListener;
import org.wing.mouseListener.WingGoodsListMouseLitener;
import org.wing.mouseListener.WingUpStarMouseListener;
import org.wing.mouseListener.UpgradeGoodsMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.wing.mouseListener.ChooseGoodsMouseListener;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.wing.mouseListener.ChooseWingGoodsMouseListener;
import com.tool.tcp.Sprite;
import com.tool.image.ImageMixDeal;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import org.come.until.Util;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import org.come.until.AccessSuitMsgUntil;
import java.awt.Color;
import org.come.until.GoodsListFromServerUntil;
import com.tool.tcpimg.UIUtils;
import com.tool.tcpimg.RichLabel;
import org.come.until.CutButtonImage;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import org.wing.btn.WingBtn;
import javax.swing.JPanel;

public class WingMainPanel extends JPanel
{
    private WingBtn wingMenuBtn;
    private WingBtn upgradeBtn;
    private WingBtn amtyuranusBtn;
    private WingBtn qualityBtn;
    private WingBtn recastMenuBtn;
    private WingBtn refineryMenuBtn;
    private WingBtn recastBtn;
    private JLabel wingMinImg;
    private JLabel chooseWingLab;
    private WingBtn leftBtn;
    private WingBtn rightBtn;
    private JLabel wingLevel;
    private JLabel wingQuality;
    private JLabel wingStarLevel;
    private JLabel experience;
    private JLabel[] starLab;
    private JLabel[] nowAttributeLab;
    private JScrollPane ruleWingScrollPane;
    private JLabel chooseGoodsImg;
    private WingBtn cultivateBtn;
    private WingBtn wingNextPageBtn;
    private WingBtn wingLastPageBtn;
    private JTextField textNumber;
    private JLabel numberLab;
    private JLabel[] goodsLab;
    private BigDecimal money;
    private JLabel[] attributeLab;
    private JLabel[] upStarMaterials;
    private JLabel[] qualityLab;
    private JLabel[] wingLab;
    private JLabel[] recastAttrLab;
    private JLabel[] qualityNowLab;
    private JLabel[] wingRefining;
    private JLabel recastNowAttribute;
    private int pageNumber;
    private BigDecimal wingGoods;
    private Goodstable chosegoods;
    private int wingGoodsType;
    private boolean[] wingRefiningType;
    private JLabel refiningTitle;
    private WingBtn refineryBtn;
    private JLabel qualityHint;
    private ImageIcon iconBack;
    private ImageIcon iconBackLeft;
    private int leftType;
    private ImageIcon iconBackRight;
    private int rightType;
    
    public WingMainPanel() {
        this.money = new BigDecimal(0);
        this.pageNumber = 1;
        this.leftType = -1;
        this.rightType = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(675, 538));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 86);
            offBtn.setBounds(638, 10, 25, 25);
            this.add(offBtn);
            this.add(this.getWingMenuBtn());
            this.add(this.getUpgradeBtn());
            this.add(this.getAmtyuranusBtn());
            this.add(this.getRecastMenuBtn());
            this.add(this.getRefineryMenuBtn());
            this.add(this.getQualityBtn());
            this.add(this.getWingMinImg());
            this.add(this.getLeftBtn());
            this.add(this.getRightBtn());
            this.add(this.getWingLevel());
            this.add(this.getWingQuality());
            this.add(this.getWingStarLevel());
            this.add(this.getExperience());
            this.add(this.getRuleWingScrollPane());
            this.add(this.getTextNumber());
            this.add(this.getCultivateBtn());
            this.add(this.getChooseGoodsImg());
            this.getNowAttributeLab();
            this.getWingNextPageBtn();
            this.getWingLastPageBtn();
            this.getGoodsLab();
            this.getAttributeLab();
            this.getUpStarMaterials();
            this.getStarLab();
            this.getQualityLab();
            this.getWingLab();
            this.getRecastAttrLab();
            this.getRecastNowAttribute();
            this.getRecastBtn();
            this.getRefineryBtn();
            this.getNumberLab();
            this.getQualityNowLab();
            this.getWingRefining();
            this.getWingRefiningType();
            this.getRefiningTitle();
        }
        else {
            this.setPreferredSize(new Dimension(561, 537));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 86);
            offBtn.setBounds(538, 0, 23, 23);
            this.add(offBtn);
            this.add(this.getWingMenuBtn());
            this.add(this.getUpgradeBtn());
            this.add(this.getAmtyuranusBtn());
            this.add(this.getRecastMenuBtn());
            this.add(this.getRefineryMenuBtn());
            this.add(this.getQualityBtn());
            this.add(this.getChooseWingLab());
            this.add(this.getWingMinImg());
            this.add(this.getLeftBtn());
            this.add(this.getRightBtn());
            this.add(this.getWingLevel());
            this.add(this.getWingQuality());
            this.add(this.getWingStarLevel());
            this.add(this.getExperience());
            this.add(this.getRuleWingScrollPane());
            this.add(this.getTextNumber());
            this.add(this.getCultivateBtn());
            this.add(this.getChooseGoodsImg());
            this.getNowAttributeLab();
            this.getWingNextPageBtn();
            this.getWingLastPageBtn();
            this.getGoodsLab();
            this.getAttributeLab();
            this.getUpStarMaterials();
            this.getStarLab();
            this.getQualityLab();
            this.getWingLab();
            this.getRecastAttrLab();
            this.getRecastNowAttribute();
            this.getRecastBtn();
            this.getRefineryBtn();
            this.getNumberLab();
            this.getQualityNowLab();
            this.getWingRefining();
            this.getWingRefiningType();
            this.getRefiningTitle();
        }
    }
    
    public void changLeftType(int type) {
        if (type == -1) {
            if (this.leftType == -1) {
                type = 1;
            }
            else {
                return;
            }
        }
        switch (this.leftType = type) {
            case 1: {
                this.changRightType(5);
                break;
            }
            case 2: {
                this.changRightType(2);
                break;
            }
            case 3: {
                this.changRightType(3);
                break;
            }
            case 4: {
                this.changRightType(4);
                break;
            }
            case 5: {
                this.changRightType(6);
                break;
            }
            case 6: {
                this.changRightType(7);
                break;
            }
        }
    }
    
    public void changRightType(int type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type == this.rightType) {
                if (type != 2 || this.rightType != 2) {
                    if (type == 3 && this.rightType == 3) {
                        this.getWingAttribute(1);
                    }
                    else if (type == 4 && this.rightType == 4) {
                        this.getGoodsConsumeNumber();
                        this.getWingAttribute(2);
                    }
                    else if (type != 5 || this.rightType != 5) {
                        if (type == 6 && this.rightType == 6) {
                            this.getWingRecastAttribute();
                        }
                        else if (type == 7 && this.rightType == 7) {
                            this.getRefiningAttr();
                            this.refreshwingRefiningLab();
                        }
                    }
                }
                return;
            }
            else {
                switch (this.rightType = type) {
                    case 2: {
                        this.iconBackRight = CutButtonImage.getImage("inkImg/background1/B274.png", -1, -1);
                        this.upSatrView(false);
                        this.qualityViewControl(false);
                        this.wingViewControl(false);
                        this.recastViewControl(false);
                        this.refineryViewControl(false);
                        this.goodsCultivateView(true);
                        break;
                    }
                    case 3: {
                        this.iconBackRight = CutButtonImage.getImage("inkImg/background1/B275.png", -1, -1);
                        this.goodsCultivateView(false);
                        this.wingViewControl(false);
                        this.qualityViewControl(false);
                        this.recastViewControl(false);
                        this.refineryViewControl(false);
                        this.upSatrView(true);
                        break;
                    }
                    case 4: {
                        this.iconBackRight = CutButtonImage.getImage("inkImg/background1/B276.png", -1, -1);
                        this.goodsCultivateView(false);
                        this.upSatrView(false);
                        this.wingViewControl(false);
                        this.recastViewControl(false);
                        this.refineryViewControl(false);
                        this.qualityViewControl(true);
                        break;
                    }
                    case 5: {
                        this.iconBackRight = CutButtonImage.getImage("inkImg/background1/B273.png", -1, -1);
                        this.goodsCultivateView(false);
                        this.upSatrView(false);
                        this.qualityViewControl(false);
                        this.recastViewControl(false);
                        this.refineryViewControl(false);
                        this.wingViewControl(true);
                        break;
                    }
                    case 6: {
                        this.iconBackRight = CutButtonImage.getImage("inkImg/background1/B277.png", -1, -1);
                        this.goodsCultivateView(false);
                        this.upSatrView(false);
                        this.qualityViewControl(false);
                        this.wingViewControl(false);
                        this.refineryViewControl(false);
                        this.recastViewControl(true);
                        break;
                    }
                    case 7: {
                        this.iconBackRight = CutButtonImage.getImage("inkImg/background1/B278.png", -1, -1);
                        this.goodsCultivateView(false);
                        this.upSatrView(false);
                        this.qualityViewControl(false);
                        this.wingViewControl(false);
                        this.recastViewControl(false);
                        this.refineryViewControl(true);
                        break;
                    }
                }
            }
        }
        else if (type == this.rightType) {
            if (type != 2 || this.rightType != 2) {
                if (type == 3 && this.rightType == 3) {
                    this.getWingAttribute(1);
                }
                else if (type == 4 && this.rightType == 4) {
                    this.getGoodsConsumeNumber();
                    this.getWingAttribute(2);
                }
                else if (type != 5 || this.rightType != 5) {
                    if (type == 6 && this.rightType == 6) {
                        this.getWingRecastAttribute();
                    }
                    else if (type == 7 && this.rightType == 7) {
                        this.getRefiningAttr();
                        this.refreshwingRefiningLab();
                    }
                }
            }
            return;
        }
        else {
            switch (this.rightType = type) {
                case 2: {
                    this.iconBackRight = CutButtonImage.getImage("img/wing/翅膀_升级_物品培养_w278,h431.png", 278, 431);
                    this.upSatrView(false);
                    this.qualityViewControl(false);
                    this.wingViewControl(false);
                    this.recastViewControl(false);
                    this.refineryViewControl(false);
                    this.goodsCultivateView(true);
                    break;
                }
                case 3: {
                    this.iconBackRight = CutButtonImage.getImage("img/wing/翅膀_升级_属性_w278,h431.png", 278, 431);
                    this.goodsCultivateView(false);
                    this.wingViewControl(false);
                    this.qualityViewControl(false);
                    this.recastViewControl(false);
                    this.refineryViewControl(false);
                    this.upSatrView(true);
                    break;
                }
                case 4: {
                    this.iconBackRight = CutButtonImage.getImage("img/wing/翅膀_品质_属性_w278,h431.png", 278, 431);
                    this.goodsCultivateView(false);
                    this.upSatrView(false);
                    this.wingViewControl(false);
                    this.recastViewControl(false);
                    this.refineryViewControl(false);
                    this.qualityViewControl(true);
                    break;
                }
                case 5: {
                    this.iconBackRight = CutButtonImage.getImage("img/wing/翅膀_翅膀_w278,h431.png", 278, 431);
                    this.goodsCultivateView(false);
                    this.upSatrView(false);
                    this.qualityViewControl(false);
                    this.recastViewControl(false);
                    this.refineryViewControl(false);
                    this.wingViewControl(true);
                    break;
                }
                case 6: {
                    this.iconBackRight = CutButtonImage.getImage("img/wing/翅膀_重铸_w278,h431.png", 278, 431);
                    this.goodsCultivateView(false);
                    this.upSatrView(false);
                    this.qualityViewControl(false);
                    this.wingViewControl(false);
                    this.refineryViewControl(false);
                    this.recastViewControl(true);
                    break;
                }
                case 7: {
                    this.iconBackRight = CutButtonImage.getImage("img/wing/翅膀_炼化_w278,h431.png", 278, 431);
                    this.goodsCultivateView(false);
                    this.upSatrView(false);
                    this.qualityViewControl(false);
                    this.wingViewControl(false);
                    this.recastViewControl(false);
                    this.refineryViewControl(true);
                    break;
                }
            }
        }
    }
    
    public void changeRule() {
        this.wingRefiningType = new boolean[4];
        for (int i = 0; i < this.wingRefiningType.length; ++i) {
            this.wingRefining[i].setIcon(null);
            this.wingRefiningType[i] = false;
        }
        RichLabel view = (RichLabel)this.ruleWingScrollPane.getViewport().getView();
        StringBuffer buffer = new StringBuffer();
        if (this.rightType == 2) {
            buffer.append("#Y翅膀升级培养功能玩法明细#r1，#Y翅膀经验影响翅膀加成属性值，等级越高属性增加越多。#r2，#R全局所有物品#Y均可作为培养材料，每次培养费用为1000万。#r3，#R普通物品培养经验为1点，#Y稀有掉落，商城内物资培养值2-10点浮动。#r4，#R唯一性翅膀培养材料，#Y新副本掉落，商城购买，及向玩家手里收购。#r");
        }
        else if (this.rightType == 3) {
            buffer.append("#Y翅膀升星玩法说明#r1，#G使用唯一材料#R秘羽灵石#Y对翅膀进行升星#r2，#R秘羽灵石#Y共分5级，每个等级对应3颗星级，例（1-3级需要1级秘羽灵石，4-6需要2级，每三级递增一个秘羽灵石）。#r3，#R秘羽灵石#Y合成公式，打开套装面板，点击炼化配饰，#G每4个相同等级秘羽灵石，合成下一个等级得秘羽灵石。");
        }
        else if (this.rightType == 4) {
            buffer.append("#Y品质提升玩法说明#r1，#Y使用唯一材料#R翅膀品质石#Y对翅膀进行品质提升#r2，#Y每个品质对应不同外观，品质越高翅膀外观越炫酷，#R增加四围成长越高，（初始翅膀四围成长为1）。#r3，#Y每个级别提升品质得概率均为20%，翅膀品质提升必须满足当前星级已满，品质提后成功后星级清空。#r4，#Y翅膀提升品质时，不考虑等级，不管等级多少都可以对翅膀进行品质提醒，前提是要满星噢！");
        }
        else if (this.rightType == 5) {
            buffer.append("#Y翅膀功能简介 #r1，#Y翅膀等级，#G（全局物品可以培养）。#r2，#Y翅膀升星，#G（提升当前属性成长，提升品质前置需求）。#r3，#Y翅膀品质，#G（提升增加四围对人物得成长比）（例1点成长为1点属性）。#r4，#Y翅膀重铸，#G（对应当前属性进行重铸，获得新得属性分配）#r5，#Y翅膀炼化，#G（对应当前翅膀，进行翅膀炼化，炼化数值参照炼器数值）#r");
        }
        else if (this.rightType == 6) {
            buffer.append("#Y翅膀重铸玩法说明#r1，#Y对当前翅膀产生翅膀重铸功能，对当前属性做出洗练#r2，#R翅膀重铸石，#Y初始最大属性为属性当前等级10点，（1级最大属性获得是4项总和10点）。");
        }
        else if (this.rightType == 7) {
            buffer.append("#Y翅膀炼化玩法说明#r1，#Y对当前装备进行炼化，增强翅膀属性。#r2，#R翅膀炼化石#Y通过新副本掉落，商城购买，每次使用消耗3个。");
        }
        view = new RichLabel(buffer.toString(), UIUtils.TEXT_FONT2);
        Dimension d = view.computeSize(130);
        view.setSize(d);
        view.setPreferredSize(d);
        this.ruleWingScrollPane.setViewportView(view);
    }
    
    public void wingViewControl(boolean type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type) {
                this.ruleWingScrollPane.setBounds(354, 378, 279, 125);
                this.pageNumber = 1;
                this.wingNextPageBtn.setBounds(614, 308, 18, 18);
                this.wingLastPageBtn.setBounds(614, 283, 18, 18);
                this.chosegoods = null;
                this.chooseGoodsImg.setIcon(null);
                this.changeRule();
            }
            for (int i = 0; i < this.wingLab.length; ++i) {
                this.wingLab[i].setVisible(type);
            }
            this.wingLastPageBtn.setVisible(type);
            this.wingNextPageBtn.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
        }
        else {
            if (type) {
                this.ruleWingScrollPane.setBounds(253, 387, 274, 125);
                this.pageNumber = 1;
                this.wingNextPageBtn.setBounds(520, 308, 19, 20);
                this.wingLastPageBtn.setBounds(520, 283, 19, 20);
                this.chosegoods = null;
                this.chooseGoodsImg.setIcon(null);
                this.changeRule();
            }
            for (int i = 0; i < this.wingLab.length; ++i) {
                this.wingLab[i].setVisible(type);
            }
            this.wingLastPageBtn.setVisible(type);
            this.wingNextPageBtn.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
        }
    }
    
    public void goodsCultivateView(boolean type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type) {
                this.chooseGoodsImg.setBounds(368, 290, 50, 50);
                this.textNumber.setBounds(520, 327, this.textNumber.getWidth(), this.textNumber.getHeight());
                this.cultivateBtn.setText("培养");
                this.cultivateBtn.setBounds(580, 325, this.cultivateBtn.getWidth(), this.cultivateBtn.getHeight());
                this.ruleWingScrollPane.setBounds(354, 378, 279, 125);
                this.pageNumber = 1;
                this.wingNextPageBtn.setBounds(614, 243, 18, 18);
                this.wingLastPageBtn.setBounds(614, 210, 18, 18);
                this.chosegoods = null;
                this.chooseGoodsImg.setIcon(null);
                this.money = new BigDecimal(0);
                this.changeRule();
            }
            this.textNumber.setText("0");
            this.wingNextPageBtn.setVisible(type);
            this.wingLastPageBtn.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
            this.chooseGoodsImg.setVisible(type);
            this.cultivateBtn.setVisible(type);
            this.textNumber.setVisible(type);
            for (int i = 0; i < this.goodsLab.length; ++i) {
                this.goodsLab[i].setVisible(type);
            }
        }
        else if (type) {
            this.chooseGoodsImg.setBounds(253, 275, 58, 56);
            this.textNumber.setBounds(this.textNumber.getX(), 315, this.textNumber.getWidth(), this.textNumber.getHeight());
            this.cultivateBtn.setBounds(this.cultivateBtn.getX(), 315, this.cultivateBtn.getWidth(), this.cultivateBtn.getHeight());
            this.ruleWingScrollPane.setBounds(253, 104, 274, 160);
            this.pageNumber = 1;
            this.wingNextPageBtn.setBounds(508, 493, 19, 20);
            this.wingLastPageBtn.setBounds(508, 460, 19, 20);
            this.chosegoods = null;
            this.chooseGoodsImg.setIcon(null);
            this.money = new BigDecimal(0);
            this.changeRule();
        }
        this.textNumber.setText("0");
        this.wingNextPageBtn.setVisible(type);
        this.wingLastPageBtn.setVisible(type);
        this.ruleWingScrollPane.setVisible(type);
        this.chooseGoodsImg.setVisible(type);
        this.cultivateBtn.setVisible(type);
        this.textNumber.setVisible(type);
        for (int i = 0; i < this.goodsLab.length; ++i) {
            this.goodsLab[i].setVisible(type);
        }
    }
    
    public void upSatrView(boolean type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type) {
                for (int i = 0; i < this.attributeLab.length; ++i) {
                    this.attributeLab[i].setBounds(399 + i % 2 * 156, 113 + i / 2 * 22, 64, 14);
                }
                for (int i = 0; i < this.upStarMaterials.length; ++i) {
                    this.upStarMaterials[i].setBounds(357 + i % 5 * 54, 317, 50, 50);
                }
                this.chooseGoodsImg.setBounds(365, 225, 58, 56);
                this.numberLab.setText("0");
                this.numberLab.setBounds(520, 261, this.numberLab.getWidth(), this.numberLab.getHeight());
                this.cultivateBtn.setText("培养");
                this.cultivateBtn.setBounds(580, 261, this.cultivateBtn.getWidth(), this.cultivateBtn.getHeight());
                this.ruleWingScrollPane.setBounds(352, 396, 280, 107);
                this.money = new BigDecimal(0);
                this.chooseGoodsImg.setIcon(null);
                this.chosegoods = null;
                this.getWingAttribute(1);
                this.changeRule();
            }
            this.money = new BigDecimal(0);
            this.chooseGoodsImg.setVisible(type);
            this.cultivateBtn.setVisible(type);
            this.numberLab.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
            for (int i = 0; i < this.attributeLab.length; ++i) {
                this.attributeLab[i].setVisible(type);
            }
            for (int i = 0; i < this.upStarMaterials.length; ++i) {
                this.upStarMaterials[i].setVisible(type);
            }
        }
        else {
            for (int i = 0; i < this.attributeLab.length; ++i) {
                this.attributeLab[i].setVisible(type);
                if (type) {
                    this.attributeLab[i].setBounds(275 + i % 2 * 156 + 21, 45 + i / 2 * 22 + 70, 64, 14);
                }
            }
            for (int i = 0; i < this.upStarMaterials.length; ++i) {
                this.upStarMaterials[i].setVisible(type);
                if (type) {
                    this.upStarMaterials[i].setBounds(232 + i % 5 * 51 + 21, 308, 50, 50);
                }
            }
            this.ruleWingScrollPane.setVisible(type);
            if (type) {
                this.chooseGoodsImg.setBounds(253, 221, 58, 56);
                this.numberLab.setBounds(412, 260, this.numberLab.getWidth(), this.numberLab.getHeight());
                this.cultivateBtn.setBounds(this.cultivateBtn.getX(), 261, this.cultivateBtn.getWidth(), this.cultivateBtn.getHeight());
                this.ruleWingScrollPane.setBounds(253, 387, 274, 125);
                this.numberLab.setText("0");
                this.money = new BigDecimal(0);
                this.chooseGoodsImg.setIcon(null);
                this.chosegoods = null;
                this.getWingAttribute(1);
                this.changeRule();
            }
            this.money = new BigDecimal(0);
            this.chooseGoodsImg.setVisible(type);
            this.cultivateBtn.setVisible(type);
            this.numberLab.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
        }
    }
    
    public void qualityViewControl(boolean type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type) {
                for (int i = 0; i < this.attributeLab.length; ++i) {
                    this.attributeLab[i].setBounds(399 + i % 2 * 156, 174 + i / 2 * 22, 64, 14);
                }
                for (int i = 0; i < this.qualityLab.length; ++i) {
                    this.qualityLab[i].setBounds(383 + i * 156, 111, 55, 53);
                }
                this.chooseGoodsImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive("cbpzs", 58, 56));
                this.chooseGoodsImg.setBounds(365, 287, 58, 56);
                this.numberLab.setText("0");
                this.numberLab.setBounds(520, 325, this.numberLab.getWidth(), this.numberLab.getHeight());
                this.cultivateBtn.setText("培养");
                this.cultivateBtn.setBounds(580, 324, this.cultivateBtn.getWidth(), this.cultivateBtn.getHeight());
                this.ruleWingScrollPane.setBounds(351, 396, 282, 107);
                this.money = new BigDecimal(0);
                this.chosegoods = null;
                this.getGoodsConsumeNumber();
                this.getWingAttribute(2);
                this.changeRule();
            }
            for (int i = 0; i < this.attributeLab.length; ++i) {
                this.attributeLab[i].setVisible(type);
            }
            for (int i = 0; i < this.qualityLab.length; ++i) {
                this.qualityLab[i].setVisible(type);
            }
            for (int i = 0; i < this.qualityNowLab.length; ++i) {
                this.qualityNowLab[i].setVisible(type);
            }
            this.chooseGoodsImg.setVisible(type);
            this.cultivateBtn.setVisible(type);
            this.numberLab.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
        }
        else {
            if (type) {
                this.chooseGoodsImg.setBounds(253, 284, 58, 56);
                this.numberLab.setBounds(412, 323, this.numberLab.getWidth(), this.numberLab.getHeight());
                this.cultivateBtn.setBounds(this.cultivateBtn.getX(), 324, this.cultivateBtn.getWidth(), this.cultivateBtn.getHeight());
                this.ruleWingScrollPane.setBounds(253, 387, 274, 125);
                this.money = new BigDecimal(0);
                this.chooseGoodsImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive("cbpzs", 58, 56));
                this.chosegoods = null;
                this.numberLab.setText("0");
                this.getGoodsConsumeNumber();
                this.getWingAttribute(2);
                this.changeRule();
            }
            for (int i = 0; i < this.attributeLab.length; ++i) {
                this.attributeLab[i].setVisible(type);
                if (type) {
                    this.attributeLab[i].setBounds(275 + i % 2 * 156 + 21, 180 + i / 2 * 22, 64, 14);
                }
            }
            for (int i = 0; i < this.qualityLab.length; ++i) {
                this.qualityLab[i].setVisible(type);
                if (type) {
                    this.qualityLab[i].setBounds(262 + i * 156, 119, 55, 53);
                }
            }
            for (int i = 0; i < this.qualityNowLab.length; ++i) {
                this.qualityNowLab[i].setVisible(type);
            }
            this.chooseGoodsImg.setVisible(type);
            this.cultivateBtn.setVisible(type);
            this.numberLab.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
        }
    }
    
    public String getGoodsValue(String[] values, String type) {
        for (int i = 0; i < values.length; ++i) {
            if (values[i].startsWith(type)) {
                return values[i].split("=")[1];
            }
        }
        return null;
    }
    
    public void recastViewControl(boolean type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type) {
                for (int i = 0; i < this.recastAttrLab.length; ++i) {
                    this.recastAttrLab[i].setBounds(460, 173 + i * 22, 64, 14);
                }
                this.ruleWingScrollPane.setBounds(351, 396, 281, 107);
                this.chooseGoodsImg.setBounds(367, 295, 49, 49);
                this.chooseGoodsImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive("cbczs", 49, 49));
                this.money = new BigDecimal(10000000);
                this.qualityLab[0].setBounds(459, 112, 55, 53);
                this.changeRule();
                this.getWingRecastAttribute();
            }
            for (int i = 0; i < this.recastAttrLab.length; ++i) {
                this.recastAttrLab[i].setVisible(type);
            }
            this.qualityLab[0].setVisible(type);
            this.recastBtn.setVisible(type);
            this.recastNowAttribute.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
            this.chooseGoodsImg.setVisible(type);
        }
        else {
            if (type) {
                this.ruleWingScrollPane.setBounds(253, 339, 274, 173);
                this.chooseGoodsImg.setBounds(253, 229, 55, 53);
                this.chooseGoodsImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive("cbczs", 58, 56));
                this.money = new BigDecimal(10000000);
                this.qualityLab[0].setBounds(262, 121, 55, 53);
                this.changeRule();
                this.getWingRecastAttribute();
            }
            for (int i = 0; i < this.recastAttrLab.length; ++i) {
                this.recastAttrLab[i].setVisible(type);
                if (type) {
                    this.recastAttrLab[i].setBounds(368, 121 + i * 22, 64, 14);
                }
            }
            this.qualityLab[0].setVisible(type);
            this.recastBtn.setVisible(type);
            this.recastNowAttribute.setVisible(type);
            this.ruleWingScrollPane.setVisible(type);
            this.chooseGoodsImg.setVisible(type);
        }
    }
    
    public void refineryViewControl(boolean type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type) {
                this.ruleWingScrollPane.setBounds(352, 358, 280, 146);
                this.numberLab.setBounds(517, 300, this.numberLab.getWidth(), this.numberLab.getHeight());
                this.money = new BigDecimal(500000);
                this.numberLab.setText("3");
                this.chooseGoodsImg.setBounds(368, 262, 49, 49);
                this.chooseGoodsImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive("cblhs", 49, 49));
                this.changeRule();
                this.getRefiningAttr();
                this.refreshwingRefiningLab();
            }
            for (int i = 0; i < this.nowAttributeLab.length; ++i) {
                this.nowAttributeLab[i].setVisible(type);
            }
            for (int i = 0; i < this.wingRefining.length; ++i) {
                this.wingRefining[i].setVisible(type);
            }
            this.ruleWingScrollPane.setVisible(type);
            this.refineryBtn.setVisible(type);
            this.numberLab.setVisible(type);
            this.chooseGoodsImg.setVisible(type);
            this.refiningTitle.setVisible(type);
        }
        else {
            if (type) {
                this.ruleWingScrollPane.setBounds(253, 349, 274, 163);
                this.numberLab.setBounds(412, 301, this.numberLab.getWidth(), this.numberLab.getHeight());
                this.money = new BigDecimal(500000);
                this.numberLab.setText("3");
                this.chooseGoodsImg.setBounds(253, 262, 58, 56);
                this.chooseGoodsImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive("cblhs", 58, 56));
                this.changeRule();
                this.getRefiningAttr();
                this.refreshwingRefiningLab();
            }
            for (int i = 0; i < this.nowAttributeLab.length; ++i) {
                this.nowAttributeLab[i].setVisible(type);
            }
            this.ruleWingScrollPane.setVisible(type);
            this.refineryBtn.setVisible(type);
            this.numberLab.setVisible(type);
            this.chooseGoodsImg.setVisible(type);
            this.refiningTitle.setVisible(type);
        }
    }
    
    public void upStarViewControl(boolean type) {
        for (int i = 0; i < this.starLab.length; ++i) {
            this.starLab[i].setVisible(type);
        }
    }
    
    public static int getUpStarData(String DataStr, int starLvl, String qualityAttr) {
        double parseDouble = Double.parseDouble(DataStr);
        if (qualityAttr == null) {
            return 0;
        }
        return (int)(parseDouble * (1.0 + (double)starLvl * 0.1) * (1.0 + getQualityAttr(qualityAttr)));
    }
    
    public static double getQualityAttr(String qualityAttr) {
        int n = -1;
        switch (qualityAttr.hashCode()) {
            case 811615: {
                if (qualityAttr.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (qualityAttr.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (qualityAttr.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (qualityAttr.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (qualityAttr.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (qualityAttr.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return 0.0;
            }
            case 1: {
                return 0.2;
            }
            case 2: {
                return 0.4;
            }
            case 3: {
                return 0.8;
            }
            case 4: {
                return 1.6;
            }
            case 5: {
                return 3.2;
            }
            default: {
                return -1.0;
            }
        }
    }
    
    public void getGoodsConsumeNumber() {
        if (this.wingGoods == null) {
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingGoods);
        if (goodstable == null) {
            return;
        }
        String[] split = goodstable.getValue().split("\\|");
        String goodsQuality = this.getGoodsValue(split, "品质");
        int n = -1;
        switch (goodsQuality.hashCode()) {
            case 811615: {
                if (goodsQuality.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (goodsQuality.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (goodsQuality.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (goodsQuality.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (goodsQuality.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (goodsQuality.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                this.numberLab.setText("1");
                this.qualityNowLab[0].setForeground(this.getQualityColor(goodsQuality));
                this.qualityNowLab[1].setForeground(this.getQualityColor("贴身"));
                this.qualityNowLab[0].setText(goodsQuality);
                this.qualityNowLab[1].setText("贴身");
                break;
            }
            case 1: {
                this.numberLab.setText("2");
                this.qualityNowLab[0].setForeground(this.getQualityColor(goodsQuality));
                this.qualityNowLab[1].setForeground(this.getQualityColor("珍藏"));
                this.qualityNowLab[0].setText(goodsQuality);
                this.qualityNowLab[1].setText("珍藏");
                break;
            }
            case 2: {
                this.numberLab.setText("4");
                this.qualityNowLab[0].setForeground(this.getQualityColor(goodsQuality));
                this.qualityNowLab[1].setForeground(this.getQualityColor("无价"));
                this.qualityNowLab[0].setText(goodsQuality);
                this.qualityNowLab[1].setText("无价");
                break;
            }
            case 3: {
                this.qualityNowLab[0].setForeground(this.getQualityColor(goodsQuality));
                this.qualityNowLab[1].setForeground(this.getQualityColor("传世"));
                this.numberLab.setText("6");
                this.qualityNowLab[0].setText(goodsQuality);
                this.qualityNowLab[1].setText("传世");
                break;
            }
            case 4: {
                this.qualityNowLab[0].setForeground(this.getQualityColor(goodsQuality));
                this.qualityNowLab[1].setForeground(this.getQualityColor("神迹"));
                this.numberLab.setText("10");
                this.qualityNowLab[0].setText(goodsQuality);
                this.qualityNowLab[1].setText("神迹");
                break;
            }
            case 5: {
                this.numberLab.setText("0");
                this.qualityNowLab[0].setForeground(this.getQualityColor(goodsQuality));
                this.qualityNowLab[0].setText(goodsQuality);
                this.qualityNowLab[1].setText("");
                break;
            }
        }
        this.money = new BigDecimal(10000000);
    }
    
    public String getQualityName(String name) {
        int n = -1;
        switch (name.hashCode()) {
            case 811615: {
                if (name.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (name.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (name.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (name.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (name.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (name.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return "贴身";
            }
            case 1: {
                return "珍藏";
            }
            case 2: {
                return "无价";
            }
            case 3: {
                return "传世";
            }
            case 4: {
                return "神迹";
            }
            case 5: {
                return null;
            }
            default: {
                return null;
            }
        }
    }
    
    public String getWingSkin(String name) {
        if (this.wingGoods == null) {
            return null;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingGoods);
        if (goodstable == null) {
            return null;
        }
        String substring = goodstable.getSkin().substring(0, goodstable.getSkin().length() - 1);
        int n = -1;
        switch (name.hashCode()) {
            case 811615: {
                if (name.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (name.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (name.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (name.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (name.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (name.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return substring + 1;
            }
            case 1: {
                return substring + 2;
            }
            case 2: {
                return substring + 3;
            }
            case 3: {
                return substring + 4;
            }
            case 4: {
                return substring + 5;
            }
            case 5: {
                return substring + 6;
            }
            default: {
                return null;
            }
        }
    }
    
    public static int getWingLevel(String exp) {
        int parseInt;
        int i;
        for (parseInt = Integer.parseInt(exp), i = 1; parseInt >= i * i * 100; ++i) {}
        return i - 1;
    }
    
    public static BigDecimal getLevelNowExp(int level) {
        return new BigDecimal(level * level * 100);
    }
    
    public static BigDecimal getLevelLastExp(String exp) {
        return new BigDecimal(exp).subtract(getLevelNowExp(getWingLevel(exp)));
    }
    
    public void getWingAttribute(int type) {
        if (this.wingGoods == null) {
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingGoods);
        if (goodstable == null) {
            return;
        }
        String[] split = goodstable.getValue().split("\\|");
        int goodsStar = Integer.parseInt(this.getGoodsValue(split, "星级"));
        int goodsStarUp = 0;
        String goodsQualityUp = null;
        String goodsQuality = this.getGoodsValue(split, "品质");
        if (type == 1) {
            goodsStarUp = goodsStar + 1;
            goodsQualityUp = goodsQuality;
        }
        else if (type == 2) {
            goodsStarUp = goodsStar;
            goodsQualityUp = this.getQualityName(goodsQuality);
            if (this.getWingSkin(goodsQuality) != null) {
                this.qualityLab[0].setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.getWingSkin(goodsQuality), 55, 53));
            }
            if (goodsQualityUp != null && this.getWingSkin(goodsQualityUp) != null) {
                this.qualityLab[1].setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.getWingSkin(goodsQualityUp), 55, 53));
            }
            else {
                this.qualityLab[1].setIcon(null);
            }
        }
        String intelligence = this.getGoodsValue(split, "灵性");
        String bone = this.getGoodsValue(split, "根骨");
        String strength = this.getGoodsValue(split, "力量");
        String agility = this.getGoodsValue(split, "敏捷");
        this.attributeLab[2].setText((intelligence == null) ? "0" : ("" + getUpStarData(intelligence, goodsStar, goodsQuality)));
        this.attributeLab[0].setText((bone == null) ? "0" : ("" + getUpStarData(bone, goodsStar, goodsQuality)));
        this.attributeLab[4].setText((strength == null) ? "0" : ("" + getUpStarData(strength, goodsStar, goodsQuality)));
        this.attributeLab[6].setText((agility == null) ? "0" : ("" + getUpStarData(agility, goodsStar, goodsQuality)));
        if (goodsStarUp > 15 || goodsQualityUp == null) {
            this.attributeLab[1].setText("");
            this.attributeLab[3].setText("");
            this.attributeLab[5].setText("");
            this.attributeLab[7].setText("");
        }
        else {
            this.attributeLab[3].setText((intelligence == null) ? "0" : ("" + getUpStarData(intelligence, goodsStarUp, goodsQualityUp)));
            this.attributeLab[1].setText((bone == null) ? "0" : ("" + getUpStarData(bone, goodsStarUp, goodsQualityUp)));
            this.attributeLab[5].setText((strength == null) ? "0" : ("" + getUpStarData(strength, goodsStarUp, goodsQualityUp)));
            this.attributeLab[7].setText((agility == null) ? "0" : ("" + getUpStarData(agility, goodsStarUp, goodsQualityUp)));
        }
    }
    
    public void getWingRecastAttribute() {
        if (this.wingGoods == null) {
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingGoods);
        if (goodstable == null) {
            return;
        }
        String[] split = goodstable.getValue().split("\\|");
        String intelligence = this.getGoodsValue(split, "灵性");
        int goodsStar = Integer.parseInt(this.getGoodsValue(split, "星级"));
        String goodsQuality = this.getGoodsValue(split, "品质");
        this.qualityLab[0].setIcon(GoodsListFromServerUntil.imgpathAdaptive(this.getWingSkin(goodsQuality), 55, 53));
        this.recastAttrLab[1].setText((intelligence == null) ? "0" : ("" + getUpStarData(intelligence, goodsStar, goodsQuality)));
        String bone = this.getGoodsValue(split, "根骨");
        this.recastAttrLab[0].setText((bone == null) ? "0" : ("" + getUpStarData(bone, goodsStar, goodsQuality)));
        String strength = this.getGoodsValue(split, "力量");
        this.recastAttrLab[2].setText((strength == null) ? "0" : ("" + getUpStarData(strength, goodsStar, goodsQuality)));
        String agility = this.getGoodsValue(split, "敏捷");
        this.recastAttrLab[3].setText((agility == null) ? "0" : ("" + getUpStarData(agility, goodsStar, goodsQuality)));
    }
    
    public Color getQualityColor(String qualityName) {
        int n = -1;
        switch (qualityName.hashCode()) {
            case 811615: {
                if (qualityName.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (qualityName.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (qualityName.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (qualityName.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (qualityName.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (qualityName.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return UIUtils.COLOR_PLAY;
            }
            case 1: {
                return UIUtils.COLOR_PERSONAL;
            }
            case 2: {
                return UIUtils.COLOR_TREASURE;
            }
            case 3: {
                return UIUtils.COLOR_NONVALENT;
            }
            case 4: {
                return UIUtils.COLOR_CHENS;
            }
            case 5: {
                return UIUtils.COLOR_SIGN;
            }
            default: {
                return null;
            }
        }
    }
    
    public static String getQualityColorOx(String qualityName) {
        int n = -1;
        switch (qualityName.hashCode()) {
            case 811615: {
                if (qualityName.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (qualityName.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (qualityName.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (qualityName.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (qualityName.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (qualityName.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return "#c29BF6B";
            }
            case 1: {
                return "#c01FBF9";
            }
            case 2: {
                return "#cEFEE0C";
            }
            case 3: {
                return "#cDB0ACD";
            }
            case 4: {
                return "#cF35E01";
            }
            case 5: {
                return "#cFB0001";
            }
            default: {
                return null;
            }
        }
    }
    
    public void changeChooseWingGoods(Goodstable goodstable, int type) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.wingGoods = goodstable.getRgid();
            this.changRightType(this.rightType);
            this.wingGoodsType = type;
            if (this.wingGoods == null) {
                return;
            }
            this.wingMinImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 60, 60));
            String[] values = goodstable.getValue().split("\\|");
            for (int i = 0; i < values.length; ++i) {
                String[] split = values[i].split("=");
                String s = split[0];
                int n = -1;
                switch (s.hashCode()) {
                    case 708743: {
                        if (s.equals("品质")) {
                            n = 0;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 842856: {
                        if (s.equals("星级")) {
                            n = 1;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1045917: {
                        if (s.equals("经验")) {
                            n = 2;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                switch (n) {
                    case 0: {
                        this.wingQuality.setForeground(this.getQualityColor(split[1]));
                        this.wingQuality.setText(split[0] + ":" + split[1]);
                        break;
                    }
                    case 1: {
                        this.wingStarLevel.setText(split[0] + ":" + split[1] + "/15");
                        for (int j = 0; j < this.starLab.length; ++j) {
                            if (j <= Integer.parseInt(split[1]) - 1) {
                                this.starLab[j].setIcon(CutButtonImage.getImage("img/xy2uiimg/收藏w28px,h28px.png", 20, 20));
                            }
                            else {
                                this.starLab[j].setIcon(CutButtonImage.getImage("img/xy2uiimg/收藏(未)w28px,h28px.png", 20, 20));
                            }
                        }
                        break;
                    }
                    case 2: {
                        this.wingLevel.setText("等级:" + getWingLevel(split[1]));
                        int divide = getLevelLastExp(split[1]).multiply(new BigDecimal(106)).divide(getLevelNowExp(getWingLevel(split[1]) + 1).subtract(getLevelNowExp(getWingLevel(split[1]))), 2, 4).intValue();
                        this.experience.setIcon(CutButtonImage.getImage("inkImg/background/S22.png", (divide == 0) ? 1 : divide, 10));
                        break;
                    }
                }
            }
        }
        else {
            this.wingGoods = goodstable.getRgid();
            this.changRightType(this.rightType);
            this.wingGoodsType = type;
            if (this.wingGoods == null) {
                return;
            }
            this.wingMinImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 60, 60));
            String[] values = goodstable.getValue().split("\\|");
            for (int i = 0; i < values.length; ++i) {
                String[] split = values[i].split("=");
                String s2 = split[0];
                int n2 = -1;
                switch (s2.hashCode()) {
                    case 708743: {
                        if (s2.equals("品质")) {
                            n2 = 0;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 842856: {
                        if (s2.equals("星级")) {
                            n2 = 1;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1045917: {
                        if (s2.equals("经验")) {
                            n2 = 2;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                switch (n2) {
                    case 0: {
                        this.wingQuality.setForeground(this.getQualityColor(split[1]));
                        this.wingQuality.setText(split[0] + ":" + split[1]);
                        break;
                    }
                    case 1: {
                        this.wingStarLevel.setText(split[0] + ":" + split[1] + "/15");
                        for (int j = 0; j < this.starLab.length; ++j) {
                            if (j <= Integer.parseInt(split[1]) - 1) {
                                this.starLab[j].setIcon(CutButtonImage.getImage("img/xy2uiimg/收藏w28px,h28px.png", 20, 20));
                            }
                            else {
                                this.starLab[j].setIcon(CutButtonImage.getImage("img/xy2uiimg/收藏(未)w28px,h28px.png", 20, 20));
                            }
                        }
                        break;
                    }
                    case 2: {
                        this.wingLevel.setText("等级:" + getWingLevel(split[1]));
                        int divide = getLevelLastExp(split[1]).multiply(new BigDecimal(106)).divide(getLevelNowExp(getWingLevel(split[1]) + 1).subtract(getLevelNowExp(getWingLevel(split[1]))), 2, 4).intValue();
                        this.experience.setIcon(CutButtonImage.getImage("img/wing/翅膀_经验条_w106,h10.png", (divide == 0) ? 1 : divide, 10));
                        break;
                    }
                }
            }
        }
    }
    
    public void getRefiningAttr() {
        if (this.wingGoods == null) {
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingGoods);
        if (goodstable == null) {
            return;
        }
        String value = AccessSuitMsgUntil.getExtra(goodstable.getValue(), "炼化属性");
        if (value == null || value.equals("")) {
            return;
        }
        String[] split = value.split("&");
        int num = 1;
        for (int i = 0; i < this.nowAttributeLab.length; i += 2) {
            if (split.length - 1 >= num) {
                if (!split[num].startsWith("特技")) {
                    this.nowAttributeLab[i].setText(split[num].split("=")[0]);
                    this.nowAttributeLab[i + 1].setText(split[num].split("=")[1]);
                    ++num;
                }
            }
            else {
                this.nowAttributeLab[i].setText("");
                this.nowAttributeLab[i + 1].setText("");
            }
        }
    }
    
    public void drawChooseImg(int num, Graphics g, int x, int y) {
        for (int i = 0; i < num; ++i) {
            g.drawImage(CutButtonImage.getImage("img/xy2uiimg/待选.png", 15, 15).getImage(), x, y + i * 27, 15, 15, (ImageObserver)null);
        }
    }
    
    public int getlock() {
        int lock = 0;
        int size = 0;
        for (int i = 0; i < this.wingRefiningType.length; ++i) {
            if (this.wingRefiningType[i]) {
                lock = (int)((double)lock + Math.pow(10.0, (double)i));
                ++size;
            }
        }
        return lock * 10 + size;
    }
    
    public int getRefiningMoney(int num) {
        switch (num) {
            case 1: {
                return 10;
            }
            case 2: {
                return 50;
            }
            case 3: {
                return 100;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void refreshwingRefiningLab() {
        for (int i = 0; i < this.wingRefiningType.length; ++i) {
            if (this.wingRefiningType[i]) {
                for (int j = 0; j < i; ++j) {
                    if (!this.wingRefiningType[j]) {
                        this.wingRefiningType[j] = this.wingRefiningType[i];
                        this.wingRefining[j].setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", 15, 15));
                        this.wingRefining[i].setIcon(null);
                        this.wingRefiningType[i] = false;
                    }
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B271.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 675, 538, this);
            if (this.iconBackLeft == null) {
                this.iconBackLeft = new ImageIcon("inkImg/background1/B272.png");
            }
            g.drawImage(this.iconBackLeft.getImage(), 57, 85, this.iconBackLeft.getIconWidth(), this.iconBackLeft.getIconHeight(), this);
            if (this.iconBackRight != null) {
                g.drawImage(this.iconBackRight.getImage(), 350, 85, 281, 420, this);
            }
            if (this.rightType == 2) {
                Util.drawMoney(g, 520, 299);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 520, 320);
                }
                GoodsListFromServerUntil.getUpgrade(g, this, 356, 112);
            }
            else if (this.rightType == 3) {
                Util.drawMoney(g, 520, 234);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 520, 255);
                }
                GoodsListFromServerUntil.getGoodsUpStar(g, this, 357, 317);
            }
            else if (this.rightType == 4) {
                Util.drawMoney(g, 520, 297);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 520, 317);
                }
                GoodsListFromServerUntil.getQualityGoods(g, this, 366, 288);
            }
            else if (this.rightType == 5) {
                GoodsListFromServerUntil.drawWingImg(g, this, 360, 92);
            }
            else if (this.rightType == 6) {
                Util.drawMoney(g, 517, 307);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 517, 327);
                }
                GoodsListFromServerUntil.getRecastGoods(g, this, 367, 295);
            }
            else if (this.rightType == 7) {
                Util.drawMoney(g, 517, 271);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 517, 291);
                }
                GoodsListFromServerUntil.getRefiningGoods(g, this, 365, 262);
            }
            if (this.wingGoods != null) {
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingGoods);
                if (goodstable == null) {
                    return;
                }
                Sprite sprite = SpriteFactory.Prepare(GetTcpPath.getMouseTcp("tx/" + goodstable.getSkin() + "1"));
                if (sprite != null) {
                    sprite.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                    sprite.draw(g, 131, 390);
                }
            }
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/wing/翅膀底板_w561,h537.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 561, 537, this);
            if (this.iconBackLeft == null) {
                this.iconBackLeft = CutButtonImage.getImage("img/wing/翅膀_品质&升星_展示框w218,h429.png", 218, 429);
            }
            g.drawImage(this.iconBackLeft.getImage(), 23, 85, this.iconBackLeft.getIconWidth(), this.iconBackLeft.getIconHeight(), this);
            if (this.iconBackRight != null) {
                g.drawImage(this.iconBackRight.getImage(), 251, 85, 278, 431, this);
            }
            if (this.rightType == 2) {
                Util.drawMoney(g, 413, 288);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 413, 308);
                }
                GoodsListFromServerUntil.getUpgrade(g, this, 253, 360);
            }
            else if (this.rightType == 3) {
                Util.drawMoney(g, 413, 233);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 413, 254);
                }
                GoodsListFromServerUntil.getGoodsUpStar(g, this, 253, 308);
            }
            else if (this.rightType == 4) {
                Util.drawMoney(g, 413, 297);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 413, 317);
                }
                GoodsListFromServerUntil.getQualityGoods(g, this, 253, 284);
            }
            else if (this.rightType == 5) {
                GoodsListFromServerUntil.drawWingImg(g, this, 263, 97);
            }
            else if (this.rightType == 6) {
                Util.drawMoney(g, 396, 251);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 396, 271);
                }
                GoodsListFromServerUntil.getRecastGoods(g, this, 253, 229);
            }
            else if (this.rightType == 7) {
                Util.drawMoney(g, 416, 274);
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 416, 294);
                }
                GoodsListFromServerUntil.getRefiningGoods(g, this, 253, 262);
                this.drawChooseImg(4, g, 382, 120);
            }
            if (this.wingGoods != null) {
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.wingGoods);
                if (goodstable == null) {
                    return;
                }
                Sprite sprite = SpriteFactory.Prepare(GetTcpPath.getMouseTcp("tx/" + goodstable.getSkin() + "1"));
                if (sprite != null) {
                    sprite.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                    sprite.draw(g, 131, 390);
                }
            }
        }
    }
    
    public WingBtn getWingMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.wingMenuBtn == null) {
                (this.wingMenuBtn = new WingBtn("inkImg/button1/K64.png", 1, "", 1, this)).setOpaque(false);
                this.wingMenuBtn.setBounds(78, 24, 75, 33);
            }
        }
        else if (this.wingMenuBtn == null) {
            (this.wingMenuBtn = new WingBtn("img/wing/一级选项卡_翅膀_选中_w80,h120.png", 1, "", 1, this)).setOpaque(false);
            this.wingMenuBtn.setBounds(27, 30, 80, 40);
        }
        return this.wingMenuBtn;
    }
    
    public void setWingMenuBtn(WingBtn wingMenuBtn) {
        this.wingMenuBtn = wingMenuBtn;
    }
    
    public WingBtn getUpgradeBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.upgradeBtn == null) {
                (this.upgradeBtn = new WingBtn("inkImg/button1/K65.png", 1, "", 2, this)).setOpaque(false);
                this.upgradeBtn.setBounds(156, 24, 75, 33);
            }
        }
        else if (this.upgradeBtn == null) {
            (this.upgradeBtn = new WingBtn("img/wing/一级选项卡_升级_未选中_w80,h120.png", 1, "", 2, this)).setOpaque(false);
            this.upgradeBtn.setBounds(109, 30, 80, 40);
        }
        return this.upgradeBtn;
    }
    
    public void setUpgradeBtn(WingBtn upgradeBtn) {
        this.upgradeBtn = upgradeBtn;
    }
    
    public WingBtn getAmtyuranusBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.amtyuranusBtn == null) {
                (this.amtyuranusBtn = new WingBtn("inkImg/button1/K67.png", 1, "", 3, this)).setOpaque(false);
                this.amtyuranusBtn.setBounds(234, 24, 75, 33);
            }
        }
        else if (this.amtyuranusBtn == null) {
            (this.amtyuranusBtn = new WingBtn("img/wing/一级选项卡_升星_未选中_w80,h120.png", 1, "", 3, this)).setOpaque(false);
            this.amtyuranusBtn.setBounds(191, 30, 80, 40);
        }
        return this.amtyuranusBtn;
    }
    
    public void setAmtyuranusBtn(WingBtn amtyuranusBtn) {
        this.amtyuranusBtn = amtyuranusBtn;
    }
    
    public WingBtn getQualityBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.qualityBtn == null) {
                (this.qualityBtn = new WingBtn("inkImg/button1/K69.png", 1, "", 4, this)).setOpaque(false);
                this.qualityBtn.setBounds(312, 24, 75, 33);
            }
        }
        else if (this.qualityBtn == null) {
            (this.qualityBtn = new WingBtn("img/wing/一级选项卡_品质_未选中_w80,h120.png", 1, "", 4, this)).setOpaque(false);
            this.qualityBtn.setBounds(273, 30, 80, 40);
        }
        return this.qualityBtn;
    }
    
    public void setQualityBtn(WingBtn qualityBtn) {
        this.qualityBtn = qualityBtn;
    }
    
    public WingBtn getRecastMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.recastMenuBtn == null) {
                (this.recastMenuBtn = new WingBtn("inkImg/button1/K55.png", 1, "", 5, this)).setOpaque(false);
                this.recastMenuBtn.setBounds(390, 24, 75, 33);
            }
        }
        else if (this.recastMenuBtn == null) {
            (this.recastMenuBtn = new WingBtn("img/wing/一级选项卡_重铸_未选中_w80,h120.png", 1, "", 5, this)).setOpaque(false);
            this.recastMenuBtn.setBounds(355, 30, 80, 40);
        }
        return this.recastMenuBtn;
    }
    
    public void setRecastMenuBtn(WingBtn recastBtn) {
        this.recastMenuBtn = recastBtn;
    }
    
    public WingBtn getRefineryMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.refineryMenuBtn == null) {
                (this.refineryMenuBtn = new WingBtn("inkImg/button1/K71.png", 1, "", 6, this)).setOpaque(false);
                this.refineryMenuBtn.setBounds(468, 24, 75, 33);
            }
        }
        else if (this.refineryMenuBtn == null) {
            (this.refineryMenuBtn = new WingBtn("img/wing/一级选项卡_炼化_未选中_w80,h120.png", 1, "", 6, this)).setOpaque(false);
            this.refineryMenuBtn.setBounds(437, 30, 80, 40);
        }
        return this.refineryMenuBtn;
    }
    
    public void setRefineryMenuBtn(WingBtn refineryMenuBtn) {
        this.refineryMenuBtn = refineryMenuBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel getWingMinImg() {
        if (this.wingMinImg == null) {
            (this.wingMinImg = new JLabel()).setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.wingMinImg.setBounds(109, 140, 60, 60);
            }
            else {
                this.wingMinImg.setBounds(61, 142, 60, 60);
            }
            this.wingMinImg.addMouseListener(new ChooseWingGoodsMouseListener(this));
        }
        return this.wingMinImg;
    }
    
    public void setWingMinImg(JLabel wingMinImg) {
        this.wingMinImg = wingMinImg;
    }
    
    public WingBtn getLeftBtn() {
        if (this.leftBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.leftBtn = new WingBtn("inkImg/button/B175.png", 1, "", 10, this)).setBounds(71, 130, 16, 82);
            }
            else {
                (this.leftBtn = new WingBtn("img/wing/向左切换_w16,h246.png", 1, "", 10, this)).setBounds(37, 130, 16, 82);
            }
        }
        return this.leftBtn;
    }
    
    public void setLeftBtn(WingBtn leftBtn) {
        this.leftBtn = leftBtn;
    }
    
    public WingBtn getRightBtn() {
        if (this.rightBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.rightBtn = new WingBtn("inkImg/button/B176.png", 1, "", 11, this)).setBounds(298, 130, 16, 82);
            }
            else {
                (this.rightBtn = new WingBtn("img/wing/向右切换_w16,h246.png", 1, "", 11, this)).setBounds(211, 130, 16, 82);
            }
        }
        return this.rightBtn;
    }
    
    public void setRightBtn(WingBtn rightBtn) {
        this.rightBtn = rightBtn;
    }
    
    public JLabel getWingLevel() {
        if (this.wingLevel == null) {
            (this.wingLevel = new JLabel("等级:0")).setFont(UIUtils.TEXT_FONT1);
            this.wingLevel.setForeground(Color.white);
            if (MyIsif.getStyle().equals("水墨")) {
                this.wingLevel.setBounds(180, 140, 80, 20);
            }
            else {
                this.wingLevel.setBounds(126, 142, 80, 20);
            }
            this.wingLevel.setHorizontalAlignment(0);
        }
        return this.wingLevel;
    }
    
    public void setWingLevel(JLabel wingLevel) {
        this.wingLevel = wingLevel;
    }
    
    public JLabel getWingQuality() {
        if (this.wingQuality == null) {
            (this.wingQuality = new JLabel("品质:把玩")).setFont(UIUtils.TEXT_FONT1);
            this.wingQuality.setForeground(UIUtils.COLOR_PLAY);
            if (MyIsif.getStyle().equals("水墨")) {
                this.wingQuality.setBounds(180, 162, 80, 20);
            }
            else {
                this.wingQuality.setBounds(126, 164, 80, 20);
            }
            this.wingQuality.setHorizontalAlignment(0);
        }
        return this.wingQuality;
    }
    
    public JLabel getChooseWingLab() {
        if (this.chooseWingLab == null) {
            (this.chooseWingLab = new JLabel("选择翅膀")).setFont(UIUtils.TEXT_HY16);
            this.chooseWingLab.setForeground(UIUtils.COLOR_Wing1);
            this.chooseWingLab.setBounds(86, 107, 100, 18);
            this.chooseWingLab.setHorizontalAlignment(0);
        }
        return this.chooseWingLab;
    }
    
    public void setWingQuality(JLabel wingQuality) {
        this.wingQuality = wingQuality;
    }
    
    public JLabel getWingStarLevel() {
        if (this.wingStarLevel == null) {
            (this.wingStarLevel = new JLabel("星级:0/15")).setFont(UIUtils.TEXT_FONT1);
            this.wingStarLevel.setForeground(Color.yellow);
            if (MyIsif.getStyle().equals("水墨")) {
                this.wingStarLevel.setBounds(180, 184, 80, 20);
            }
            else {
                this.wingStarLevel.setBounds(126, 186, 80, 20);
            }
            this.wingStarLevel.setHorizontalAlignment(0);
        }
        return this.wingStarLevel;
    }
    
    public void setWingStarLevel(JLabel wingStarLevel) {
        this.wingStarLevel = wingStarLevel;
    }
    
    public JLabel getExperience() {
        if (this.experience == null) {
            this.experience = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.experience.setBounds(168, 216, 98, 14);
            }
            else {
                this.experience.setBounds(98, 215, 110, 14);
            }
            this.experience.setOpaque(false);
        }
        return this.experience;
    }
    
    public void setExperience(JLabel experience) {
        this.experience = experience;
    }
    
    public ImageIcon getIconBackLeft() {
        return this.iconBackLeft;
    }
    
    public void setIconBackLeft(ImageIcon iconBackLeft) {
        this.iconBackLeft = iconBackLeft;
    }
    
    public int getLeftType() {
        return this.leftType;
    }
    
    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }
    
    public JLabel[] getStarLab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starLab == null) {
                this.starLab = new JLabel[15];
                for (int i = 0; i < this.starLab.length; ++i) {
                    (this.starLab[i] = new JLabel()).setBounds(130 + i % 5 * 25, 395 + i / 5 * 25, 20, 20);
                    this.starLab[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/收藏(未)w28px,h28px.png", 20, 20));
                    this.add(this.starLab[i]);
                }
            }
        }
        else if (this.starLab == null) {
            this.starLab = new JLabel[15];
            for (int i = 0; i < this.starLab.length; ++i) {
                (this.starLab[i] = new JLabel()).setBounds(72 + i % 5 * 25, 423 + i / 5 * 25, 20, 20);
                this.starLab[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/收藏(未)w28px,h28px.png", 20, 20));
                this.add(this.starLab[i]);
            }
        }
        return this.starLab;
    }
    
    public void setStarLab(JLabel[] starLab) {
        this.starLab = starLab;
    }
    
    public ImageIcon getIconBackRight() {
        return this.iconBackRight;
    }
    
    public void setIconBackRight(ImageIcon iconBackRight) {
        this.iconBackRight = iconBackRight;
    }
    
    public JLabel[] getNowAttributeLab() {
        if (this.nowAttributeLab == null) {
            this.nowAttributeLab = new JLabel[8];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.nowAttributeLab.length; ++i) {
                    (this.nowAttributeLab[i] = new JLabel()).setBounds(366 + i % 2 * 158, 112 + i / 2 * 22, (i % 2 == 0) ? 127 : 65, 20);
                    this.nowAttributeLab[i].setForeground(Color.white);
                    this.nowAttributeLab[i].setFont(UIUtils.TEXT_FONT1);
                    this.add(this.nowAttributeLab[i]);
                }
            }
            else {
                for (int i = 0; i < this.nowAttributeLab.length; ++i) {
                    (this.nowAttributeLab[i] = new JLabel()).setBounds(269 + i % 2 * 135, 117 + i / 2 * 27, 129 - i % 2 * 66, 21);
                    this.nowAttributeLab[i].setForeground(Color.white);
                    this.nowAttributeLab[i].setFont(UIUtils.TEXT_FONT1);
                    this.add(this.nowAttributeLab[i]);
                }
            }
        }
        return this.nowAttributeLab;
    }
    
    public void setNowAttributeLab(JLabel[] nowAttributeLab) {
        this.nowAttributeLab = nowAttributeLab;
    }
    
    public JScrollPane getRuleWingScrollPane() {
        if (this.ruleWingScrollPane == null) {
            this.ruleWingScrollPane = new JScrollPane();
            this.ruleWingScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.ruleWingScrollPane.setOpaque(false);
            this.ruleWingScrollPane.getViewport().setOpaque(false);
            this.ruleWingScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.ruleWingScrollPane.setVerticalScrollBarPolicy(22);
            this.ruleWingScrollPane.setHorizontalScrollBarPolicy(31);
            if (MyIsif.getStyle().equals("水墨")) {
                this.ruleWingScrollPane.setBounds(253, 360, 274, 153);
            }
            else {
                this.ruleWingScrollPane.setBounds(253, 360, 274, 153);
            }
        }
        return this.ruleWingScrollPane;
    }
    
    public void setRuleWingScrollPane(JScrollPane ruleWingScrollPane) {
        this.ruleWingScrollPane = ruleWingScrollPane;
    }
    
    public int getRightType() {
        return this.rightType;
    }
    
    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
    
    public JLabel getChooseGoodsImg() {
        if (this.chooseGoodsImg == null) {
            (this.chooseGoodsImg = new JLabel()).setBounds(253, 275, 58, 56);
            this.chooseGoodsImg.addMouseListener(new ChooseGoodsMouseListener(this));
        }
        return this.chooseGoodsImg;
    }
    
    public void setChooseGoodsImg(JLabel chooseGoodsImg) {
        this.chooseGoodsImg = chooseGoodsImg;
    }
    
    public JTextField getTextNumber() {
        if (this.textNumber == null) {
            (this.textNumber = new JTextField("0")).setFont(UIUtils.TEXT_FONT1);
            this.textNumber.setOpaque(false);
            this.textNumber.setBorder(null);
            this.textNumber.setCaretColor(Color.white);
            this.textNumber.setForeground(Color.white);
            this.textNumber.setBounds(413, 315, 35, 17);
            this.textNumber.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = WingMainPanel.this.textNumber.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong("1000000");
                        WingMainPanel.this.money = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = WingMainPanel.this.textNumber.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong("10000000");
                        WingMainPanel.this.money = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.textNumber.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = WingMainPanel.this.textNumber.getText();
                    if (str.length() == 0) {
                        WingMainPanel.this.textNumber.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        WingMainPanel.this.textNumber.setText("");
                    }
                    if (str.length() > 0 && str.length() > 1 && str.length() > 2) {
                        e.consume();
                        WingMainPanel.this.textNumber.setText("999");
                        int sum = Integer.parseInt(WingMainPanel.this.textNumber.getText());
                        long price = Long.parseLong("10000000");
                        WingMainPanel.this.money = new BigDecimal(price * (long)sum);
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
        return this.textNumber;
    }
    
    public void setTextNumber(JTextField textNumber) {
        this.textNumber = textNumber;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public WingBtn getCultivateBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.cultivateBtn == null) {
                (this.cultivateBtn = new WingBtn("inkImg/button1/B20.png", 1, "培养", 18, this, "")).setBounds(580, 345, 59, 24);
                this.cultivateBtn.setOpaque(false);
            }
            return this.cultivateBtn;
        }
        else {
            if (this.cultivateBtn == null) {
                (this.cultivateBtn = new WingBtn("img/wing/按钮_培养_w71,h72.png", 1, "", 18, this)).setBounds(458, 315, 71, 24);
                this.cultivateBtn.setOpaque(false);
            }
            return this.cultivateBtn;
        }
    }
    
    public void setCultivateBtn(WingBtn cultivateBtn) {
        this.cultivateBtn = cultivateBtn;
    }
    
    public JLabel[] getGoodsLab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.goodsLab == null) {
                this.goodsLab = new JLabel[15];
                for (int i = 0; i < this.goodsLab.length; ++i) {
                    (this.goodsLab[i] = new JLabel()).setBounds(358 + i % 5 * 52, 112 + i / 5 * 52, 52, 52);
                    this.goodsLab[i].addMouseListener(new UpgradeGoodsMouseListener(i, this));
                    this.add(this.goodsLab[i]);
                }
            }
            return this.goodsLab;
        }
        else {
            if (this.goodsLab == null) {
                this.goodsLab = new JLabel[15];
                for (int i = 0; i < this.goodsLab.length; ++i) {
                    (this.goodsLab[i] = new JLabel()).setBounds(253 + i % 5 * 51, 360 + i / 5 * 51, 50, 50);
                    this.goodsLab[i].addMouseListener(new UpgradeGoodsMouseListener(i, this));
                    this.add(this.goodsLab[i]);
                }
            }
            return this.goodsLab;
        }
    }
    
    public void setGoodsLab(JLabel[] goodsLab) {
        this.goodsLab = goodsLab;
    }
    
    public JLabel[] getAttributeLab() {
        if (this.attributeLab == null) {
            this.attributeLab = new JLabel[8];
            for (int i = 0; i < this.attributeLab.length; ++i) {
                (this.attributeLab[i] = new JLabel()).setBounds(275 + i % 2 * 156 + 21, 45 + i / 2 * 22 + 70, 64, 14);
                this.attributeLab[i].setOpaque(false);
                this.attributeLab[i].setHorizontalAlignment(4);
                this.attributeLab[i].setFont(UIUtils.TEXT_FONT1);
                this.attributeLab[i].setForeground(Color.white);
                this.add(this.attributeLab[i]);
            }
        }
        return this.attributeLab;
    }
    
    public void setAttributeLab(JLabel[] attributeLab) {
        this.attributeLab = attributeLab;
    }
    
    public JLabel[] getUpStarMaterials() {
        if (this.upStarMaterials == null) {
            this.upStarMaterials = new JLabel[5];
            for (int i = 0; i < this.upStarMaterials.length; ++i) {
                (this.upStarMaterials[i] = new JLabel()).setOpaque(false);
                this.upStarMaterials[i].setBounds(253 + i % 5 * 51, 308, 50, 50);
                this.upStarMaterials[i].addMouseListener(new WingUpStarMouseListener(i, this));
                this.add(this.upStarMaterials[i]);
            }
        }
        return this.upStarMaterials;
    }
    
    public void setUpStarMaterials(JLabel[] upStarMaterials) {
        this.upStarMaterials = upStarMaterials;
    }
    
    public JLabel[] getQualityLab() {
        if (this.qualityLab == null) {
            this.qualityLab = new JLabel[2];
            for (int i = 0; i < this.qualityLab.length; ++i) {
                (this.qualityLab[i] = new JLabel()).setBounds(262 + i * 156, 119, 55, 53);
                this.qualityLab[i].setOpaque(false);
                this.qualityLab[i].setVisible(false);
                this.add(this.qualityLab[i]);
            }
        }
        return this.qualityLab;
    }
    
    public void setQualityLab(JLabel[] qualityLab) {
        this.qualityLab = qualityLab;
    }
    
    public JLabel[] getWingLab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.wingLab == null) {
                this.wingLab = new JLabel[25];
                for (int i = 0; i < this.wingLab.length; ++i) {
                    (this.wingLab[i] = new JLabel()).setBounds(360 + i % 5 * 51, 92 + i / 5 * 51, 50, 50);
                    this.wingLab[i].addMouseListener(new WingGoodsListMouseLitener(i, this));
                    this.add(this.wingLab[i]);
                }
            }
            return this.wingLab;
        }
        else {
            if (this.wingLab == null) {
                this.wingLab = new JLabel[25];
                for (int i = 0; i < this.wingLab.length; ++i) {
                    (this.wingLab[i] = new JLabel()).setBounds(263 + i % 5 * 51, 97 + i / 5 * 51, 50, 50);
                    this.wingLab[i].addMouseListener(new WingGoodsListMouseLitener(i, this));
                    this.add(this.wingLab[i]);
                }
            }
            return this.wingLab;
        }
    }
    
    public void setWingLab(JLabel[] wingLab) {
        this.wingLab = wingLab;
    }
    
    public JLabel[] getRecastAttrLab() {
        if (this.recastAttrLab == null) {
            this.recastAttrLab = new JLabel[4];
            for (int i = 0; i < this.recastAttrLab.length; ++i) {
                (this.recastAttrLab[i] = new JLabel()).setBounds(368, 121 + i * 22, 64, 14);
                this.recastAttrLab[i].setOpaque(false);
                this.recastAttrLab[i].setFont(UIUtils.TEXT_FONT1);
                this.recastAttrLab[i].setForeground(Color.white);
                this.recastAttrLab[i].setHorizontalAlignment(4);
                this.add(this.recastAttrLab[i]);
            }
        }
        return this.recastAttrLab;
    }
    
    public void setRecastAttrLab(JLabel[] recastAttrLab) {
        this.recastAttrLab = recastAttrLab;
    }
    
    public JLabel getRecastNowAttribute() {
        if (this.recastNowAttribute == null) {
            (this.recastNowAttribute = new JLabel()).setBounds(262, 121, 55, 53);
            this.recastNowAttribute.setOpaque(false);
            this.add(this.recastNowAttribute);
        }
        return this.recastNowAttribute;
    }
    
    public void setRecastNowAttribute(JLabel recastNowAttribute) {
        this.recastNowAttribute = recastNowAttribute;
    }
    
    public WingBtn getRecastBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.recastBtn == null) {
                (this.recastBtn = new WingBtn("inkImg/button1/B20.png", 1, "重铸", 19, this, "")).setBounds(450, 335, 59, 24);
                this.add(this.recastBtn);
            }
            return this.recastBtn;
        }
        else {
            if (this.recastBtn == null) {
                (this.recastBtn = new WingBtn("img/wing/按钮_重铸_w71,h72.png", 1, "", 19, this)).setBounds(416, 283, 71, 24);
                this.add(this.recastBtn);
            }
            return this.recastBtn;
        }
    }
    
    public WingBtn getWingNextPageBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.wingNextPageBtn == null) {
                (this.wingNextPageBtn = new WingBtn("inkImg/button/8.png", 1, "", 21, this)).setBounds(520, 308, 18, 18);
                this.add(this.wingNextPageBtn);
            }
        }
        else if (this.wingNextPageBtn == null) {
            (this.wingNextPageBtn = new WingBtn("img/xy2uiimg/34_png.button.xy_vscroll$down.png", 1, "", 21, this)).setBounds(520, 308, 19, 20);
            this.add(this.wingNextPageBtn);
        }
        return this.wingNextPageBtn;
    }
    
    public void setWingNextPageBtn(WingBtn wingNextPageBtn) {
        this.wingNextPageBtn = wingNextPageBtn;
    }
    
    public WingBtn getWingLastPageBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.wingLastPageBtn == null) {
                (this.wingLastPageBtn = new WingBtn("inkImg/button/7.png", 1, "", 22, this)).setBounds(520, 283, 18, 18);
                this.add(this.wingLastPageBtn);
            }
        }
        else if (this.wingLastPageBtn == null) {
            (this.wingLastPageBtn = new WingBtn("img/xy2uiimg/42_png.button.btn_1.png", 1, "", 22, this)).setBounds(520, 283, 19, 19);
            this.add(this.wingLastPageBtn);
        }
        return this.wingLastPageBtn;
    }
    
    public void setWingLastPageBtn(WingBtn wingLastPageBtn) {
        this.wingLastPageBtn = wingLastPageBtn;
    }
    
    public WingBtn getRefineryBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.refineryBtn == null) {
                (this.refineryBtn = new WingBtn("inkImg/button1/B20.png", 1, "炼化", 20, this, "")).setBounds(575, 297, 59, 24);
                this.add(this.refineryBtn);
            }
            return this.refineryBtn;
        }
        else {
            if (this.refineryBtn == null) {
                (this.refineryBtn = new WingBtn("img/wing/按钮_炼化_w71,h72.png", 1, "", 20, this)).setBounds(455, 300, 71, 24);
                this.add(this.refineryBtn);
            }
            return this.refineryBtn;
        }
    }
    
    public int getPageNumber() {
        return this.pageNumber;
    }
    
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    public JLabel getNumberLab() {
        if (this.numberLab == null) {
            (this.numberLab = new JLabel()).setBounds(412, 315, 36, 16);
            this.numberLab.setForeground(Color.white);
            this.numberLab.setVisible(false);
            this.add(this.numberLab);
        }
        return this.numberLab;
    }
    
    public void setNumberLab(JLabel numberLab) {
        this.numberLab = numberLab;
    }
    
    public BigDecimal getWingGoods() {
        return this.wingGoods;
    }
    
    public void setWingGoods(BigDecimal wingGoods) {
        this.wingGoods = wingGoods;
    }
    
    public Goodstable getChosegoods() {
        return this.chosegoods;
    }
    
    public void setChosegoods(Goodstable chosegoods) {
        this.chosegoods = chosegoods;
    }
    
    public JLabel[] getQualityNowLab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.qualityNowLab == null) {
                this.qualityNowLab = new JLabel[2];
                for (int i = 0; i < this.qualityNowLab.length; ++i) {
                    (this.qualityNowLab[i] = new JLabel()).setBounds(432 + i * 159, 119, 46, 18);
                    this.qualityNowLab[i].setFont(UIUtils.TEXT_FONT1);
                    this.qualityNowLab[i].setForeground(Color.white);
                    this.qualityNowLab[i].setHorizontalAlignment(0);
                    this.add(this.qualityNowLab[i]);
                }
            }
            return this.qualityNowLab;
        }
        else {
            if (this.qualityNowLab == null) {
                this.qualityNowLab = new JLabel[2];
                for (int i = 0; i < this.qualityNowLab.length; ++i) {
                    (this.qualityNowLab[i] = new JLabel()).setBounds(320 + i * 156, 119, 46, 18);
                    this.qualityNowLab[i].setFont(UIUtils.TEXT_FONT1);
                    this.qualityNowLab[i].setForeground(Color.white);
                    this.qualityNowLab[i].setHorizontalAlignment(0);
                    this.add(this.qualityNowLab[i]);
                }
            }
            return this.qualityNowLab;
        }
    }
    
    public void setQualityNowLab(JLabel[] qualityNowLab) {
        this.qualityNowLab = qualityNowLab;
    }
    
    public int getWingGoodsType() {
        return this.wingGoodsType;
    }
    
    public void setWingGoodsType(int wingGoodsType) {
        this.wingGoodsType = wingGoodsType;
    }
    
    public JLabel[] getWingRefining() {
        if (this.wingRefining == null) {
            this.wingRefining = new JLabel[4];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.wingRefining.length; ++i) {
                    (this.wingRefining[i] = new JLabel()).setBounds(497, 115 + i * 22, 15, 15);
                    this.wingRefining[i].addMouseListener(new ChooseRefiningCheckboxMouseListener(i, this));
                    this.add(this.wingRefining[i]);
                }
            }
            else {
                for (int i = 0; i < this.wingRefining.length; ++i) {
                    (this.wingRefining[i] = new JLabel()).setBounds(382, 120 + i * 27, 15, 15);
                    this.wingRefining[i].addMouseListener(new ChooseRefiningCheckboxMouseListener(i, this));
                    this.add(this.wingRefining[i]);
                }
            }
        }
        return this.wingRefining;
    }
    
    public void setWingRefining(JLabel[] wingRefining) {
        this.wingRefining = wingRefining;
    }
    
    public boolean[] getWingRefiningType() {
        if (this.wingRefiningType == null) {
            this.wingRefiningType = new boolean[4];
            for (int i = 0; i < this.wingRefiningType.length; ++i) {
                this.wingRefiningType[i] = false;
            }
        }
        return this.wingRefiningType;
    }
    
    public void setWingRefiningType(boolean[] wingRefiningType) {
        this.wingRefiningType = wingRefiningType;
    }
    
    public JLabel getRefiningTitle() {
        if (this.refiningTitle == null) {
            this.refiningTitle = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.refiningTitle.setBounds(361, 210, 278, 20);
            }
            else {
                this.refiningTitle.setBounds(251, 235, 278, 20);
            }
            this.refiningTitle.setFont(UIUtils.TEXT_FONT1);
            this.refiningTitle.setForeground(Color.yellow);
            this.add(this.refiningTitle);
        }
        return this.refiningTitle;
    }
    
    public void setRefiningTitle(JLabel refiningTitle) {
        this.refiningTitle = refiningTitle;
    }
}
