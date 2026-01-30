package org.come.Jpanel;

import org.come.Frame.TestpackJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.Util;
import com.tool.role.RoleProperty;
import org.come.Frame.ZhuFrame;
import org.come.until.EquipTool;
import java.awt.Graphics;
import org.come.until.Goodtype;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import com.tool.tcpimg.RichLabel;
import java.math.BigDecimal;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.tool.btn.GemRefineryBtn;
import javax.swing.JPanel;

public class GemRefineryMainJpanel extends JPanel
{
    private GemRefineryBtn btnCompound;
    private GemRefineryBtn btnDisassembly;
    private GemRefineryBtn btnTrepanning;
    private GemRefineryBtn btnCaozuo;
    private GemRefineryBtn btnLock;
    private GemRefineryBtn btnUnlock;
    private GemRefineryBtn btnLastPage;
    private GemRefineryBtn btnNextPage;
    private GemRefineryBtn btnCaozuoCompound;
    private GemRefineryBtn btnCaozuoDisassembly;
    private JLabel[] labsGoods;
    private JLabel labMaterials;
    private JLabel labProduct;
    private JTextField textNum;
    private BigDecimal money;
    private RichLabel richMsg;
    private Goodstable chooseGoodstable;
    private Goodstable twoGoodstable;
    private int gemNum;
    private int gemBackNum;
    private int lvlNum;
    private int type;
    private int page;
    private ImageIcon icon;
    private ImageIcon rightIcon;
    private ImageIcon gemBackImg;
    private ImageIcon gemImg;
    
    public GemRefineryMainJpanel() {
        this.type = -1;
        this.page = 0;
        this.setPreferredSize(new Dimension(558, 516));
        this.setLayout(null);
        this.setOpaque(false);
        this.getBtnCompound();
        this.getBtnDisassembly();
        this.getLabsGoods();
        this.getTextNum();
        this.getLabProduct();
        this.getLabMaterials();
        this.getRichMsg();
        this.getBtnTrepanning();
        this.getBtnNextPage();
        this.getBtnLastPage();
        this.getBtnLock();
        this.getBtnUnlock();
        this.getBtnCaozuoDisassembly();
        this.getBtnCaozuoCompound();
    }
    
    public void initView(int num) {
        if (num == -1) {
            if (this.type == -1) {
                this.type = 0;
            }
            this.changeMainView(true);
            return;
        }
        else {
            this.changeMainView(false);
            this.type = num;
            this.changeMainView(true);
            return;
        }
    }
    
    public void changeMainView(boolean bo) {
        if (bo) {
            this.page = 0;
            this.textNum.setText("0");
            this.chooseGoodstable = null;
            this.richMsg.setText(null);
            this.gemBackNum = 0;
            this.gemNum = 0;
            this.refreshLeftGoods();
        }
        if (this.type == 0) {
            this.changeCompoundView(bo);
        }
        else if (this.type == 1) {
            this.changeForgeView(bo);
        }
        else if (this.type == 2) {
            this.changeTrepanningView(bo);
        }
    }
    
    public void refreshLeftGoods() {
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.getChooseGoods(21) == null) {
                    if (this.btnNextPage.getBtn() != -1) {
                        this.btnNextPage.setIcon(CutButtonImage.getImage("inkImg/button/60.png", -1, -1));
                        this.btnNextPage.setBtn(-1);
                    }
                }
                else if (this.btnNextPage.getBtn() != 1) {
                    this.btnNextPage.setBtn(1);
                    this.btnNextPage.setIcons(CutButtonImage.cuts("inkImg/button/9.png"));
                }
                if (this.page <= 0) {
                    if (this.btnLastPage.getBtn() != -1) {
                        this.btnLastPage.setBtn(-1);
                        this.btnLastPage.setIcon(CutButtonImage.getImage("inkImg/button/61.png", -1, -1));
                    }
                }
                else if (this.btnLastPage.getBtn() != 1) {
                    this.btnLastPage.setBtn(1);
                    this.btnLastPage.setIcons(CutButtonImage.cuts("inkImg/button/10.png"));
                }
            }
            else {
                if (this.getChooseGoods(21) == null) {
                    if (this.btnNextPage.getBtn() != -1) {
                        this.btnNextPage.setIcon(CutButtonImage.getImage("img/xy2uiimg/36_png.button.btn_7.png", -1, -1));
                        this.btnNextPage.setBtn(-1);
                    }
                }
                else if (this.btnNextPage.getBtn() != 1) {
                    this.btnNextPage.setBtn(1);
                    this.btnNextPage.setIcons(CutButtonImage.cuts("img/xy2uiimg/36_png.button.btn_7.png"));
                }
                if (this.page <= 0) {
                    if (this.btnLastPage.getBtn() != -1) {
                        this.btnLastPage.setBtn(-1);
                        this.btnLastPage.setIcon(CutButtonImage.getImage("img/xy2uiimg/30_png.button.btn_8.png", -1, -1));
                    }
                }
                else if (this.btnLastPage.getBtn() != 1) {
                    this.btnLastPage.setBtn(1);
                    this.btnLastPage.setIcons(CutButtonImage.cuts("img/xy2uiimg/30_png.button.btn_8.png"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Goodstable getChooseGoods(int numPlace) {
        Goodstable[] goodslist = GoodsListFromServerUntil.getGoodslist();
        int num = 0;
        for (int i = 0; i < goodslist.length; ++i) {
            Goodstable goodstable = goodslist[i];
            if (goodstable != null) {
                if ((this.type == 0) ? (!Goodtype.isGemRefining((long)goodstable.getType())) : (!Goodtype.EquipGem((long)goodstable.getType()))) {}
                if (this.page * 21 > num) {
                    ++num;
                }
                else {
                    if (num - this.page * 21 == numPlace) {
                        return goodstable;
                    }
                    ++num;
                }
            }
        }
        return null;
    }
    
    public void drawGoodsImg(Graphics g) {
        Goodstable[] goodslist = GoodsListFromServerUntil.getGoodslist();
        int num = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < goodslist.length; ++i) {
                Goodstable goodstable = goodslist[i];
                if (goodstable != null) {
                    if ((this.type == 0) ? (!Goodtype.isGemRefining((long)goodstable.getType())) : (!Goodtype.EquipGem((long)goodstable.getType()))) {}
                    if (this.page * 21 > num) {
                        ++num;
                    }
                    else {
                        g.drawImage(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()).getImage(), 64 + (num - this.page * 21) % 3 * 51, 114 + (num - this.page * 21) / 3 * 51, 43, 43, null);
                        if (!EquipTool.isEquip(goodstable.getType())) {
                            g.drawString(goodstable.getUsetime().toString(), 64 + (num - this.page * 21) % 3 * 51, 124 + (num - this.page * 21) / 3 * 51);
                        }
                        if (++num >= (this.page + 1) * 21) {
                            break;
                        }
                    }
                }
            }
            if (this.chooseGoodstable != null) {
                if (this.type == 0) {
                    g.drawImage(GoodsListFromServerUntil.imgpath2(this.chooseGoodstable.getSkin()).getImage(), 352, 161, 43, 43, null);
                    g.drawString(this.chooseGoodstable.getUsetime().toString(), 350, 171);
                    g.drawImage(GoodsListFromServerUntil.imgpath2(this.chooseGoodstable.getSkin().substring(0, 4) + (this.lvlNum + 1)).getImage(), 352, 261, 43, 43, null);
                }
                else {
                    g.drawImage(GoodsListFromServerUntil.imgpath2(this.chooseGoodstable.getSkin()).getImage(), 352, 161, 43, 43, null);
                }
                if (this.twoGoodstable != null) {
                    g.drawImage(GoodsListFromServerUntil.imgpath2(this.twoGoodstable.getSkin()).getImage(), 352, 261, 43, 43, null);
                    g.drawString(this.twoGoodstable.getUsetime().toString(), 350, 271);
                }
            }
        }
        else {
            for (int i = 0; i < goodslist.length; ++i) {
                Goodstable goodstable = goodslist[i];
                if (goodstable != null) {
                    if ((this.type == 0) ? (!Goodtype.isGemRefining((long)goodstable.getType())) : (!Goodtype.EquipGem((long)goodstable.getType()))) {}
                    if (this.page * 21 > num) {
                        ++num;
                    }
                    else {
                        g.drawImage(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()).getImage(), 40 + (num - this.page * 21) % 3 * 51, 126 + (num - this.page * 21) / 3 * 51, 43, 43, null);
                        if (!EquipTool.isEquip(goodstable.getType())) {
                            g.drawString(goodstable.getUsetime().toString(), 40 + (num - this.page * 21) % 3 * 51, 136 + (num - this.page * 21) / 3 * 51);
                        }
                        if (++num >= (this.page + 1) * 21) {
                            break;
                        }
                    }
                }
            }
            if (this.chooseGoodstable != null) {
                if (this.type == 0) {
                    g.drawImage(GoodsListFromServerUntil.imgpath2(this.chooseGoodstable.getSkin()).getImage(), 328, 173, 43, 43, null);
                    g.drawString(this.chooseGoodstable.getUsetime().toString(), 325, 280);
                    g.drawImage(GoodsListFromServerUntil.imgpath2(this.chooseGoodstable.getSkin().substring(0, 4) + (this.lvlNum + 1)).getImage(), 328, 273, 43, 43, null);
                }
                else {
                    g.drawImage(GoodsListFromServerUntil.imgpath2(this.chooseGoodstable.getSkin()).getImage(), 328, 173, 43, 43, null);
                }
                if (this.twoGoodstable != null) {
                    g.drawImage(GoodsListFromServerUntil.imgpath2(this.twoGoodstable.getSkin()).getImage(), 328, 273, 43, 43, null);
                    g.drawString(this.twoGoodstable.getUsetime().toString(), 328, 283);
                }
            }
        }
    }
    
    public void changeCompoundView(boolean bo) {
        if (bo) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.rightIcon = CutButtonImage.getImage("inkImg/background1/B264.png", -1, -1);
                this.btnCaozuoCompound.setText("合成");
                this.btnCaozuoCompound.setBounds(334, 402, 80, 78);
                this.richMsg.setBounds(265, 313, 260, 20);
            }
            else {
                this.rightIcon = CutButtonImage.getImage("img/xy2uiimg/gemCom.png", -1, -1);
                this.btnCaozuoCompound.setText("合成");
                this.btnCaozuoCompound.setBounds(334, 412, 85, 78);
                this.richMsg.setBounds(240, 323, 260, 20);
            }
        }
        this.textNum.setVisible(bo);
    }
    
    public void changeForgeView(boolean bo) {
        if (bo) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.rightIcon = CutButtonImage.getImage("inkImg/background1/B263.png", -1, -1);
                this.btnCaozuoCompound.setText("镶嵌");
                this.btnCaozuoCompound.setBounds(280, 402, 80, 78);
                this.richMsg.setBounds(265, 313, 250, 20);
            }
            else {
                this.rightIcon = CutButtonImage.getImage("img/xy2uiimg/gemCaozuo.png", -1, -1);
                this.btnCaozuoCompound.setText("镶嵌");
                this.btnCaozuoCompound.setBounds(280, 422, 85, 78);
                this.richMsg.setBounds(255, 355, 250, 20);
            }
        }
        this.btnCaozuoDisassembly.setVisible(bo);
    }
    
    public void changeTrepanningView(boolean bo) {
        if (bo) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.rightIcon = CutButtonImage.getImage("inkImg/background1/B263.png", -1, -1);
                this.btnCaozuoCompound.setText("开孔");
                this.btnCaozuoCompound.setBounds(334, 402, 80, 78);
            }
            else {
                this.rightIcon = CutButtonImage.getImage("img/xy2uiimg/gemCaozuo.png", -1, -1);
                this.btnCaozuoCompound.setText("开孔");
                this.btnCaozuoCompound.setBounds(334, 422, 85, 78);
            }
        }
    }
    
    public void drawGemBackImg(Graphics g, int num, int gemNum) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.gemBackImg == null) {
                this.gemBackImg = CutButtonImage.getImage("inkImg/background/S190.png", -1, -1);
            }
            if (this.gemImg == null) {
                this.gemImg = CutButtonImage.getImage("inkImg/background/S189.png", -1, -1);
            }
            for (int i = 0; i < num; ++i) {
                g.drawImage(this.gemBackImg.getImage(), 374 - num / 2 * 13 + i * 13, 326, 10, 16, null);
                if (i < gemNum) {
                    g.drawImage(this.gemImg.getImage(), 375 - num / 2 * 13 + i * 13, 327, 8, 14, null);
                }
            }
        }
        else {
            if (this.gemBackImg == null) {
                this.gemBackImg = CutButtonImage.getImage("img/xy2uiimg/gemBackImg.png", -1, -1);
            }
            if (this.gemImg == null) {
                this.gemImg = CutButtonImage.getImage("img/xy2uiimg/gemImg.png", -1, -1);
            }
            for (int i = 0; i < num; ++i) {
                g.drawImage(this.gemBackImg.getImage(), 374 - num / 2 * 13 + i * 13, 326, 10, 16, null);
                if (i < gemNum) {
                    g.drawImage(this.gemImg.getImage(), 375 - num / 2 * 13 + i * 13, 327, 8, 14, null);
                }
            }
        }
    }
    
    public Goodstable getLvlGem(int lvlGem) {
        Goodstable[] goodslist = GoodsListFromServerUntil.getGoodslist();
        for (int i = 0, length = goodslist.length; i < length; ++i) {
            Goodstable goodstable = goodslist[i];
            if (this.type == 1) {
                int equipmentType = Goodtype.EquipmentType((long)this.chooseGoodstable.getType());
                if (equipmentType < 0) {
                    return null;
                }
                if (goodstable != null && Goodtype.isGemRefiningType((long)goodstable.getType()) == equipmentType && Integer.parseInt(goodstable.getValue().substring(3)) == lvlGem) {
                    return goodstable;
                }
            }
            else {
                if (this.type != 2) {
                    return null;
                }
                if (goodstable != null && Goodtype.isGemTapanning((long)goodstable.getType())) {
                    return goodstable;
                }
            }
        }
        return null;
    }
    
    public void addChooseGoods(Goodstable goodstable) {
        if (goodstable != null) {
            if (this.type == 0) {
                this.lvlNum = Integer.parseInt(goodstable.getValue().substring(3));
                Integer usetime = goodstable.getUsetime();
                if (this.lvlNum == 5) {
                    ZhuFrame.getZhuJpanel().addPrompt2("已经是最高级宝石了");
                    return;
                }
                int con = 10 - (this.lvlNum - 1) * 2;
                StringBuffer buffer = new StringBuffer();
                buffer.append("#G合成一个#R");
                buffer.append(this.lvlNum + 1);
                buffer.append("级#G的宝石需要#R");
                buffer.append(con);
                buffer.append("#G颗的#R");
                buffer.append(this.lvlNum);
                buffer.append("级#G的宝石");
                this.richMsg.setText(buffer.toString());
                int num = (int)usetime / con;
                this.chooseGoodstable = goodstable;
                this.textNum.setText(String.valueOf(num));
            }
            else if (this.type == 1) {
                Integer qht = goodstable.getQht();
                qht = Integer.valueOf((qht == null) ? 0 : ((int)qht));
                Integer qhv = goodstable.getQhv();
                qhv = Integer.valueOf((qhv == null) ? 0 : ((int)qhv));
                this.gemBackNum = (int)qht;
                this.gemNum = (int)qhv;
                this.chooseGoodstable = goodstable;
                this.richMsg.setText(this.getrichMsgStr(this.gemNum, this.gemBackNum));
                this.money = new BigDecimal((this.gemNum / 3 + 1) * 1000000);
            }
            else if (this.type == 2) {
                if (Goodtype.isGemTapanning((long)goodstable.getType())) {
                    if (this.chooseGoodstable != null) {
                        this.twoGoodstable = goodstable;
                    }
                    return;
                }
                else {
                    Integer qht = goodstable.getQht();
                    qht = Integer.valueOf((qht == null) ? 0 : ((int)qht));
                    Integer qhv = goodstable.getQhv();
                    qhv = Integer.valueOf((qhv == null) ? 0 : ((int)qhv));
                    if ((int)qht >= 15) {
                        ZhuFrame.getZhuJpanel().addPrompt2("开孔已经抵达上限");
                        return;
                    }
                    this.chooseGoodstable = goodstable;
                    this.gemBackNum = (int)qht;
                    this.gemNum = (int)qhv;
                    this.money = new BigDecimal(1000000);
                }
            }
            this.twoGoodstable = this.getLvlGem(this.gemNum / 3 + 1);
        }
    }
    
    public void refreshChooseGoodstable(Goodstable goodstable, int oneTwo) {
        if (oneTwo == 1) {
            if ((int)goodstable.getUsetime() <= 0) {
                this.chooseGoodstable = null;
                this.twoGoodstable = null;
                this.gemBackNum = 0;
                this.gemNum = 0;
                this.richMsg.setText(null);
                this.textNum.setText("0");
                return;
            }
            this.chooseGoodstable = goodstable;
            if (this.type == 0) {
                int num = (int)goodstable.getUsetime() / (10 - (this.lvlNum - 1) * 2);
                this.textNum.setText(String.valueOf(num));
            }
            else if (this.type == 1) {
                this.gemBackNum = ((this.chooseGoodstable.getQht() == null) ? 0 : ((int)this.chooseGoodstable.getQht()));
                this.gemNum = ((this.chooseGoodstable.getQhv() == null) ? 0 : ((int)this.chooseGoodstable.getQhv()));
                this.richMsg.setText(this.getrichMsgStr(this.gemNum, this.gemBackNum));
                this.money = new BigDecimal((this.gemNum / 3 + 1) * 1000000);
            }
            else if (this.type == 2) {
                this.gemBackNum = ((this.chooseGoodstable.getQht() == null) ? 0 : ((int)this.chooseGoodstable.getQht()));
                this.gemNum = ((this.chooseGoodstable.getQhv() == null) ? 0 : ((int)this.chooseGoodstable.getQhv()));
            }
            this.twoGoodstable = this.getLvlGem(this.gemNum / 3 + 1);
        }
        else if (oneTwo == 2) {
            if (this.chooseGoodstable != null) {
                if (this.type == 1) {
                    if (Goodtype.EquipmentType((long)this.chooseGoodstable.getType()) == Goodtype.isGemRefiningType((long)goodstable.getType()) && this.gemNum / 3 + 1 == Integer.parseInt(goodstable.getValue().substring(3))) {
                        this.twoGoodstable = goodstable;
                    }
                }
                else if (this.type == 2 && Goodtype.isGemTapanning((long)goodstable.getType())) {
                    if ((int)goodstable.getUsetime() <= 0) {
                        this.twoGoodstable = null;
                    }
                    else {
                        this.twoGoodstable = goodstable;
                    }
                }
            }
            else {
                this.twoGoodstable = null;
            }
        }
        this.refreshLeftGoods();
    }
    
    public String getrichMsgStr(int gemNum, int gemBackNum) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R基础属性+");
        buffer.append(String.format("%.2f", new Object[] { Double.valueOf(RoleProperty.getQHGemXS(gemNum)) }));
        if (gemNum >= 15) {
            buffer.append("%,已达到最高等级");
        }
        else if (gemNum >= gemBackNum) {
            buffer.append("%,开孔后可继续强化");
        }
        else {
            buffer.append("%,下一阶段属性#B+");
            buffer.append(String.format("%.2f", new Object[] { Double.valueOf(RoleProperty.getQHGemXS(gemNum + 1)) }));
            buffer.append("%");
        }
        return buffer.toString();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B262.png");
            }
            g.drawImage(this.icon.getImage(), 46, 74, this);
            if (this.rightIcon != null) {
                g.drawImage(this.rightIcon.getImage(), 230, 113, this);
            }
            if (this.type == 0) {
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 337, 377);
                }
                Util.drawMoney(g, 337, 403);
            }
            else {
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 337, 377);
                }
                Util.drawMoney(g, 337, 403);
                this.drawGemBackImg(g, this.gemBackNum, this.gemNum);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/gemMain.png");
            }
            g.drawImage(this.icon.getImage(), 22, 86, this);
            if (this.rightIcon != null) {
                g.drawImage(this.rightIcon.getImage(), 206, 122, this);
            }
            if (this.type == 0) {
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 335, 397);
                }
                Util.drawMoney(g, 335, 423);
            }
            else {
                if (this.money != null) {
                    Util.drawPrice(g, this.money, 335, 412);
                }
                Util.drawMoney(g, 335, 438);
                this.drawGemBackImg(g, this.gemBackNum, this.gemNum);
            }
        }
        this.drawGoodsImg(g);
    }
    
    public GemRefineryBtn getBtnCompound() {
        if (this.btnCompound == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnCompound = new GemRefineryBtn("inkImg/button1/K38.png", 1, 0, this)).setBounds(55, 49, 90, 24);
                this.add(this.btnCompound);
            }
            else {
                (this.btnCompound = new GemRefineryBtn("img/xy2uiimg/setsynthesis_1.png", 1, 0, this)).setBounds(31, 60, 86, 26);
                this.add(this.btnCompound);
            }
        }
        return this.btnCompound;
    }
    
    public void setBtnCompound(GemRefineryBtn btnCompound) {
        this.btnCompound = btnCompound;
    }
    
    public GemRefineryBtn getBtnDisassembly() {
        if (this.btnDisassembly == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnDisassembly = new GemRefineryBtn("inkImg/button1/K49.png", 1, 1, this)).setBounds(147, 49, 90, 24);
            }
            else {
                (this.btnDisassembly = new GemRefineryBtn("img/xy2uiimg/unGemInlay.png", 1, 1, this)).setBounds(119, 60, 86, 26);
            }
            this.add(this.btnDisassembly);
        }
        return this.btnDisassembly;
    }
    
    public void setBtnDisassembly(GemRefineryBtn btnDisassembly) {
        this.btnDisassembly = btnDisassembly;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public GemRefineryBtn getBtnCaozuo() {
        return this.btnCaozuo;
    }
    
    public void setBtnCaozuo(GemRefineryBtn btnCaozuo) {
        this.btnCaozuo = btnCaozuo;
    }
    
    public GemRefineryBtn getBtnLock() {
        if (this.btnLock == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnLock = new GemRefineryBtn("inkImg/button1/K156.png", 1, 5, this)).setBounds(72, 469, 18, 18);
            }
            else {
                (this.btnLock = new GemRefineryBtn("img/xy2uiimg/25_png.button.btn_lock.png", 1, 5, this)).setBounds(48, 481, 19, 20);
            }
            this.add(this.btnLock);
        }
        return this.btnLock;
    }
    
    public void setBtnLock(GemRefineryBtn btnLock) {
        this.btnLock = btnLock;
    }
    
    public GemRefineryBtn getBtnUnlock() {
        if (this.btnUnlock == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnUnlock = new GemRefineryBtn("inkImg/button1/K157.png", 1, 6, this)).setBounds(98, 469, 18, 18);
            }
            else {
                (this.btnUnlock = new GemRefineryBtn("img/xy2uiimg/28_png.button.btn_unlock.png", 1, 6, this)).setBounds(74, 481, 19, 20);
            }
            this.add(this.btnUnlock);
        }
        return this.btnUnlock;
    }
    
    public void setBtnUnlock(GemRefineryBtn btnUnlock) {
        this.btnUnlock = btnUnlock;
    }
    
    public GemRefineryBtn getBtnLastPage() {
        if (this.btnLastPage == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnLastPage = new GemRefineryBtn("inkImg/button/10.png", 1, 3, this)).setBounds(158, 469, 18, 18);
            }
            else {
                (this.btnLastPage = new GemRefineryBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 3, this)).setBounds(134, 481, 19, 20);
            }
            this.add(this.btnLastPage);
        }
        return this.btnLastPage;
    }
    
    public void setBtnLastPage(GemRefineryBtn btnLastPage) {
        this.btnLastPage = btnLastPage;
    }
    
    public GemRefineryBtn getBtnNextPage() {
        if (this.btnNextPage == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnNextPage = new GemRefineryBtn("inkImg/button/9.png", 1, 4, this)).setBounds(185, 469, 18, 18);
            }
            else {
                (this.btnNextPage = new GemRefineryBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 4, this)).setBounds(161, 481, 19, 20);
            }
            this.add(this.btnNextPage);
        }
        return this.btnNextPage;
    }
    
    public void setBtnNextPage(GemRefineryBtn btnNextPage) {
        this.btnNextPage = btnNextPage;
    }
    
    public JLabel[] getLabsGoods() {
        if (this.labsGoods == null) {
            this.labsGoods = new JLabel[21];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.labsGoods.length; ++i) {
                    (this.labsGoods[i] = new JLabel()).setBounds(61 + i % 3 * 51, 111 + i / 3 * 51, 50, 50);
                    this.labsGoods[i].addMouseListener(new MListener(i));
                    this.add(this.labsGoods[i]);
                }
            }
            else {
                for (int i = 0; i < this.labsGoods.length; ++i) {
                    (this.labsGoods[i] = new JLabel()).setBounds(37 + i % 3 * 51, 123 + i / 3 * 51, 50, 50);
                    this.labsGoods[i].addMouseListener(new MListener(i));
                    this.add(this.labsGoods[i]);
                }
            }
        }
        return this.labsGoods;
    }
    
    public void setLabsGoods(JLabel[] labsGoods) {
        this.labsGoods = labsGoods;
    }
    
    public JLabel getLabMaterials() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labMaterials == null) {
                (this.labMaterials = new JLabel()).setBounds(352, 161, 43, 43);
                this.labMaterials.addMouseListener(new MListener(-1));
                this.add(this.labMaterials);
            }
        }
        else if (this.labMaterials == null) {
            (this.labMaterials = new JLabel()).setBounds(328, 173, 43, 43);
            this.labMaterials.addMouseListener(new MListener(-1));
            this.add(this.labMaterials);
        }
        return this.labMaterials;
    }
    
    public void setLabMaterials(JLabel labMaterials) {
        this.labMaterials = labMaterials;
    }
    
    public JLabel getLabProduct() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labProduct == null) {
                (this.labProduct = new JLabel()).setBounds(352, 261, 43, 43);
                this.labProduct.addMouseListener(new MListener(-2));
                this.add(this.labProduct);
            }
        }
        else if (this.labProduct == null) {
            (this.labProduct = new JLabel()).setBounds(328, 273, 43, 43);
            this.labProduct.addMouseListener(new MListener(-2));
            this.add(this.labProduct);
        }
        return this.labProduct;
    }
    
    public void setLabProduct(JLabel labProduct) {
        this.labProduct = labProduct;
    }
    
    public JTextField getTextNum() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.textNum == null) {
                (this.textNum = new JTextField("0")).setBounds(337, 337, 115, 16);
                this.textNum.setFont(UIUtils.TEXT_FONT1);
                this.textNum.setOpaque(false);
                this.textNum.setBorder(BorderFactory.createEmptyBorder());
                this.textNum.setCaretColor(Color.white);
                this.textNum.setForeground(Color.white);
                this.textNum.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        int charstr = e.getKeyChar();
                        String str = GemRefineryMainJpanel.this.textNum.getText();
                        if (str.length() == 0) {
                            GemRefineryMainJpanel.this.textNum.setText("0");
                            return;
                        }
                        if (charstr < 48 || charstr > 57) {
                            e.consume();
                        }
                        if (str.length() == 1 && str.equals("0")) {
                            GemRefineryMainJpanel.this.textNum.setText("");
                        }
                        int num = 0;
                        if (GemRefineryMainJpanel.this.chooseGoodstable != null) {
                            num = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                        }
                        long parseLong = Long.parseLong(str);
                        if (str.length() > 0 && parseLong > (long)num) {
                            GemRefineryMainJpanel.this.textNum.setText(String.valueOf(num));
                        }
                    }
                    
                    @Override
                    public void keyPressed(KeyEvent e) {
                    }
                    
                    @Override
                    public void keyReleased(KeyEvent e) {
                        String str = GemRefineryMainJpanel.this.textNum.getText();
                        if (str != null && !"".equals(str)) {
                            long parseLong = Long.parseLong(str);
                            int num = 0;
                            if (GemRefineryMainJpanel.this.chooseGoodstable != null) {
                                num = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                            }
                            if (parseLong == (long)num) {
                                return;
                            }
                            if (str.length() > 0 && parseLong > (long)num) {
                                GemRefineryMainJpanel.this.textNum.setText(String.valueOf(num));
                            }
                        }
                    }
                });
                this.textNum.getDocument().addDocumentListener(new DocumentListener() {
                    BigDecimal a = new BigDecimal(0);
                    
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        String str = GemRefineryMainJpanel.this.textNum.getText();
                        if (str != null && !str.equals("")) {
                            long parseLong = Long.parseLong(str);
                            if (parseLong > 0L && GemRefineryMainJpanel.this.chooseGoodstable != null) {
                                int com = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                                if (parseLong <= (long)com) {
                                    GemRefineryMainJpanel.this.money = new BigDecimal(parseLong * 1000000L * (long)GemRefineryMainJpanel.this.lvlNum);
                                }
                                else {
                                    GemRefineryMainJpanel.this.money = new BigDecimal(com * 1000000 * GemRefineryMainJpanel.this.lvlNum);
                                }
                            }
                        }
                        else {
                            GemRefineryMainJpanel.this.money = this.a;
                        }
                    }
                    
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String str = GemRefineryMainJpanel.this.textNum.getText();
                        if (str != null && !str.equals("")) {
                            long parseLong = Long.parseLong(str);
                            if (parseLong > 0L && GemRefineryMainJpanel.this.chooseGoodstable != null) {
                                int com = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                                if (parseLong <= (long)com) {
                                    GemRefineryMainJpanel.this.money = new BigDecimal(parseLong * 1000000L * (long)GemRefineryMainJpanel.this.lvlNum);
                                }
                                else {
                                    GemRefineryMainJpanel.this.money = new BigDecimal(com * 1000000 * GemRefineryMainJpanel.this.lvlNum);
                                }
                            }
                        }
                        else {
                            GemRefineryMainJpanel.this.money = this.a;
                        }
                    }
                    
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        String str = GemRefineryMainJpanel.this.textNum.getText();
                        if (str != null && !str.equals("")) {
                            long parseLong = Long.parseLong(str);
                            if (Long.parseLong(str) > 0L && GemRefineryMainJpanel.this.chooseGoodstable != null) {
                                int com = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                                if (parseLong <= (long)com) {
                                    GemRefineryMainJpanel.this.money = new BigDecimal(parseLong * 1000000L * (long)GemRefineryMainJpanel.this.lvlNum);
                                }
                                else {
                                    GemRefineryMainJpanel.this.money = new BigDecimal(com * 1000000 * GemRefineryMainJpanel.this.lvlNum);
                                }
                            }
                        }
                        else {
                            GemRefineryMainJpanel.this.money = this.a;
                        }
                    }
                });
                this.add(this.textNum);
            }
        }
        else if (this.textNum == null) {
            (this.textNum = new JTextField("0")).setBounds(335, 357, 115, 16);
            this.textNum.setFont(UIUtils.TEXT_FONT1);
            this.textNum.setOpaque(false);
            this.textNum.setBorder(BorderFactory.createEmptyBorder());
            this.textNum.setCaretColor(Color.white);
            this.textNum.setForeground(Color.white);
            this.textNum.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = GemRefineryMainJpanel.this.textNum.getText();
                    if (str.length() == 0) {
                        GemRefineryMainJpanel.this.textNum.setText("0");
                        return;
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        GemRefineryMainJpanel.this.textNum.setText("");
                    }
                    int num = 0;
                    if (GemRefineryMainJpanel.this.chooseGoodstable != null) {
                        num = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                    }
                    long parseLong = Long.parseLong(str);
                    if (str.length() > 0 && parseLong > (long)num) {
                        GemRefineryMainJpanel.this.textNum.setText(String.valueOf(num));
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    String str = GemRefineryMainJpanel.this.textNum.getText();
                    if (str != null && !"".equals(str)) {
                        long parseLong = Long.parseLong(str);
                        int num = 0;
                        if (GemRefineryMainJpanel.this.chooseGoodstable != null) {
                            num = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                        }
                        if (parseLong == (long)num) {
                            return;
                        }
                        if (str.length() > 0 && parseLong > (long)num) {
                            GemRefineryMainJpanel.this.textNum.setText(String.valueOf(num));
                        }
                    }
                }
            });
            this.textNum.getDocument().addDocumentListener(new DocumentListener() {
                BigDecimal a = new BigDecimal(0);
                
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String str = GemRefineryMainJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        long parseLong = Long.parseLong(str);
                        if (parseLong > 0L && GemRefineryMainJpanel.this.chooseGoodstable != null) {
                            int com = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                            if (parseLong <= (long)com) {
                                GemRefineryMainJpanel.this.money = new BigDecimal(parseLong * 1000000L * (long)GemRefineryMainJpanel.this.lvlNum);
                            }
                            else {
                                GemRefineryMainJpanel.this.money = new BigDecimal(com * 1000000 * GemRefineryMainJpanel.this.lvlNum);
                            }
                        }
                    }
                    else {
                        GemRefineryMainJpanel.this.money = this.a;
                    }
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String str = GemRefineryMainJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        long parseLong = Long.parseLong(str);
                        if (parseLong > 0L && GemRefineryMainJpanel.this.chooseGoodstable != null) {
                            int com = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                            if (parseLong <= (long)com) {
                                GemRefineryMainJpanel.this.money = new BigDecimal(parseLong * 1000000L * (long)GemRefineryMainJpanel.this.lvlNum);
                            }
                            else {
                                GemRefineryMainJpanel.this.money = new BigDecimal(com * 1000000 * GemRefineryMainJpanel.this.lvlNum);
                            }
                        }
                    }
                    else {
                        GemRefineryMainJpanel.this.money = this.a;
                    }
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                    String str = GemRefineryMainJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        long parseLong = Long.parseLong(str);
                        if (Long.parseLong(str) > 0L && GemRefineryMainJpanel.this.chooseGoodstable != null) {
                            int com = (int)GemRefineryMainJpanel.this.chooseGoodstable.getUsetime() / (10 - (GemRefineryMainJpanel.this.lvlNum - 1) * 2);
                            if (parseLong <= (long)com) {
                                GemRefineryMainJpanel.this.money = new BigDecimal(parseLong * 1000000L * (long)GemRefineryMainJpanel.this.lvlNum);
                            }
                            else {
                                GemRefineryMainJpanel.this.money = new BigDecimal(com * 1000000 * GemRefineryMainJpanel.this.lvlNum);
                            }
                        }
                    }
                    else {
                        GemRefineryMainJpanel.this.money = this.a;
                    }
                }
            });
            this.add(this.textNum);
        }
        return this.textNum;
    }
    
    public void setTextNum(JTextField textNum) {
        this.textNum = textNum;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public ImageIcon getRightIcon() {
        return this.rightIcon;
    }
    
    public void setRightIcon(ImageIcon rightIcon) {
        this.rightIcon = rightIcon;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public RichLabel getRichMsg() {
        if (this.richMsg == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.richMsg = new RichLabel("#R基础属性+0%，下一阶段属性#B+3%", UIUtils.TEXT_FONT1)).setBounds(285, 335, 230, 20);
            }
            else {
                (this.richMsg = new RichLabel("#R基础属性+0%，下一阶段属性#B+3%", UIUtils.TEXT_FONT1)).setBounds(275, 355, 230, 20);
            }
            this.add(this.richMsg);
        }
        return this.richMsg;
    }
    
    public void setRichMsg(RichLabel richMsg) {
        this.richMsg = richMsg;
    }
    
    public ImageIcon getGemBackImg() {
        return this.gemBackImg;
    }
    
    public void setGemBackImg(ImageIcon gemBackImg) {
        this.gemBackImg = gemBackImg;
    }
    
    public GemRefineryBtn getBtnTrepanning() {
        if (this.btnTrepanning == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnTrepanning = new GemRefineryBtn("inkImg/button1/K51.png", 1, 2, this)).setBounds(239, 49, 90, 24);
            }
            else {
                (this.btnTrepanning = new GemRefineryBtn("img/xy2uiimg/unGemTrapper.png", 1, 2, this)).setBounds(207, 60, 86, 26);
            }
            this.add(this.btnTrepanning);
        }
        return this.btnTrepanning;
    }
    
    public void setBtnTrepanning(GemRefineryBtn btnTrepanning) {
        this.btnTrepanning = btnTrepanning;
    }
    
    public int getPage() {
        return this.page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public ImageIcon getGemImg() {
        return this.gemImg;
    }
    
    public void setGemImg(ImageIcon gemImg) {
        this.gemImg = gemImg;
    }
    
    public Goodstable getChooseGoodstable() {
        return this.chooseGoodstable;
    }
    
    public void setChooseGoodstable(Goodstable chooseGoodstable) {
        this.chooseGoodstable = chooseGoodstable;
    }
    
    public int getLvlNum() {
        return this.lvlNum;
    }
    
    public void setLvlNum(int lvlNum) {
        this.lvlNum = lvlNum;
    }
    
    public GemRefineryBtn getBtnCaozuoCompound() {
        if (this.btnCaozuoCompound == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnCaozuoCompound = new GemRefineryBtn("inkImg/button1/B21.png", 1, 7, "合成", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this)).setBounds(334, 402, 79, 24);
            }
            else {
                (this.btnCaozuoCompound = new GemRefineryBtn("img/xy2uiimg/111.png", 1, 7, "合成", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, this)).setBounds(310, 424, 85, 72);
            }
            this.add(this.btnCaozuoCompound);
        }
        return this.btnCaozuoCompound;
    }
    
    public void setBtnCaozuoCompound(GemRefineryBtn btnCaozuoCompound) {
        this.btnCaozuoCompound = btnCaozuoCompound;
    }
    
    public GemRefineryBtn getBtnCaozuoDisassembly() {
        if (this.btnCaozuoDisassembly == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnCaozuoDisassembly = new GemRefineryBtn("inkImg/button1/B21.png", 1, 8, "拆卸", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this)).setVisible(false);
                this.btnCaozuoDisassembly.setBounds(396, 429, 79, 24);
            }
            else {
                (this.btnCaozuoDisassembly = new GemRefineryBtn("img/xy2uiimg/111.png", 1, 8, "拆卸", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, this)).setVisible(false);
                this.btnCaozuoDisassembly.setBounds(372, 424, 85, 72);
            }
            this.add(this.btnCaozuoDisassembly);
        }
        return this.btnCaozuoDisassembly;
    }
    
    public void setBtnCaozuoDisassembly(GemRefineryBtn btnCaozuoDisassembly) {
        this.btnCaozuoDisassembly = btnCaozuoDisassembly;
    }
    
    public Goodstable getTwoGoodstable() {
        return this.twoGoodstable;
    }
    
    public void setTwoGoodstable(Goodstable twoGoodstable) {
        this.twoGoodstable = twoGoodstable;
    }
    
    public int getGemNum() {
        return this.gemNum;
    }
    
    public void setGemNum(int gemNum) {
        this.gemNum = gemNum;
    }
    
    public int getGemBackNum() {
        return this.gemBackNum;
    }
    
    public void setGemBackNum(int gemBackNum) {
        this.gemBackNum = gemBackNum;
    }
    
    class MListener implements MouseListener
    {
        private int numPlace;
        
        public MListener(int numPlace) {
            this.numPlace = numPlace;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (this.numPlace < 0) {
                if (this.numPlace == -1 && GemRefineryMainJpanel.this.chooseGoodstable != null) {
                    GemRefineryMainJpanel.this.chooseGoodstable = null;
                    GemRefineryMainJpanel.this.twoGoodstable = null;
                }
            }
            else {
                Goodstable goodstable = GemRefineryMainJpanel.this.getChooseGoods(this.numPlace);
                if (goodstable != null) {
                    GemRefineryMainJpanel.this.addChooseGoods(goodstable);
                }
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if (this.numPlace < 0) {
                if (this.numPlace == -1) {
                    if (GemRefineryMainJpanel.this.chooseGoodstable != null) {
                        ZhuFrame.getZhuJpanel().creatgoodtext(GemRefineryMainJpanel.this.chooseGoodstable);
                    }
                }
                else if (this.numPlace == -2 && GemRefineryMainJpanel.this.twoGoodstable != null) {
                    ZhuFrame.getZhuJpanel().creatgoodtext(GemRefineryMainJpanel.this.twoGoodstable);
                }
            }
            else {
                Goodstable goodstable = GemRefineryMainJpanel.this.getChooseGoods(this.numPlace);
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
    }
}
