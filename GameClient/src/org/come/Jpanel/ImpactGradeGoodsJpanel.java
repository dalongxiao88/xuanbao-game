package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.math.BigDecimal;
import org.come.Frame.ZhuFrame;
import org.come.bean.LoginResult;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ImpactGradeJframe;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import org.come.entity.Goodstable;
import java.util.Iterator;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.EquipTool;
import org.come.until.UserMessUntil;
import org.come.bean.XXGDBean;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.bean.ChongjipackBean;
import com.tool.tcpimg.RichLabel;
import com.tool.btn.ImpactGradeGoodsBtn;
import com.tool.btn.VipShopBtn;
import org.come.mouslisten.VipGoodsMouselistener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImpactGradeGoodsJpanel extends JPanel
{
    private JLabel[] goodsImgs;
    private JLabel[] goodsNums;
    private JLabel[] goodsNums1;
    private VipGoodsMouselistener[] mouselistener;
    private VipShopBtn getBtn;
    private ImpactGradeGoodsBtn b1;
    private ImpactGradeGoodsBtn b2;
    private RichLabel nameRich;
    private RichLabel moneyRich;
    private RichLabel moneyRichLQ;
    private ChongjipackBean chongjipackBean;
    private int numMax;
    private ImageIcon iconBack;
    
    public ImpactGradeGoodsJpanel() {
        this.setPreferredSize(new Dimension(477, 57));
        this.setOpaque(false);
        this.setLayout(null);
        (this.b1 = new ImpactGradeGoodsBtn("inkImg/button1/156.png", 1, 1, "", this)).setBounds(104, 15, 18, 25);
        this.b1.setVisible(false);
        this.add(this.b1);
        (this.b2 = new ImpactGradeGoodsBtn("inkImg/button1/155.png", 1, 2, "", this)).setBounds(375, 15, 18, 25);
        this.b2.setVisible(false);
        this.add(this.b2);
        this.getGetBtn();
        this.getNameRich();
        this.getMoneyRich();
        this.getMoneyRichLQ();
        this.getGoodsNums();
        this.getGoodsImgs();
        this.getGoodsNums1();
    }
    
    public void exchangeGoods() {
        if (this.chongjipackBean == null) {
            return;
        }
        List<XXGDBean> goods = ChongjipackBean.getGoodsImpactGrade(this.chongjipackBean.getPackgoods());
        int x = 0;
        for (XXGDBean good : goods) {
            Goodstable goodstable = UserMessUntil.getgoodstable(good.getId());
            if (goodstable == null) {
                continue;
            }
            else if (!EquipTool.isEquip(goodstable.getType())) {
                ++x;
            }
            else {
                x += good.getSum();
            }
        }
        String sendmes = "";
        if ((int)this.chongjipackBean.getPacktype() == 4) {
            sendmes = Agreement.getAgreement().PaydaygradesureAgreement("v" + this.chongjipackBean.getPackgradetype());
        }
        else if ((int)this.chongjipackBean.getPacktype() == 5) {
            sendmes = Agreement.getAgreement().DayforweekgradesureAgreement("v" + this.chongjipackBean.getPackgradetype());
        }
        else if ((int)this.chongjipackBean.getPacktype() == 7) {
            sendmes = Agreement.getAgreement().PayLJgradesureAgreement("v" + this.chongjipackBean.getPackgradetype());
        }
        else {
            sendmes = Agreement.getAgreement().ChongjipacksureAgreement(this.chongjipackBean.getPacktype() + "|" + this.chongjipackBean.getPackgradetype());
        }
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showGoods(ChongjipackBean bean) {
        this.chongjipackBean = bean;
        this.b1.setVisible(false);
        this.b2.setVisible(false);
        if (bean != null) {
            List<XXGDBean> goods = ChongjipackBean.getGoodsImpactGrade(bean.getPackgoods());
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            int needlv = AnalysisString.lvldirection1(bean.getPackgrade());
            int level = (int)loginResult.getGrade();
            int typeMenu = ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getTypeMenu();
            if (level >= needlv) {
                int lvl = -1;
                if (typeMenu == 1) {
                    lvl = 4;
                }
                else if (typeMenu - 1 == loginResult.getLowOrHihtpack()) {
                    lvl = 5;
                }
                if (lvl != -1) {
                    String[] split = null;
                    if (loginResult.getVipget() != null) {
                        split = loginResult.getVipget().split("&&");
                    }
                    if (checkYesOrNo(split, lvl + "", bean.getPackgradetype() + "")) {
                        this.moneyRichLQ.setText("#c784023  已领取");
                        this.moneyRichLQ.setVisible(true);
                        this.moneyRich.setVisible(false);
                    }
                    else {
                        this.moneyRichLQ.setText("");
                        this.moneyRich.setVisible(false);
                    }
                }
                else {
                    this.moneyRich.setText("#c784023暂不可领取");
                    this.moneyRich.setVisible(false);
                }
            }
            else {
                this.moneyRich.setText("#c784023等级未达到");
                this.moneyRich.setVisible(true);
            }
            this.goodsImgsClear();
            this.numMax = 0;
            int size = 0;
            if (goods.size() > 6) {
                size = 6;
                this.b2.setVisible(true);
            }
            else {
                size = goods.size();
            }
            for (int i = 0; i < size; ++i) {
                Goodstable goodstable = UserMessUntil.getgoodstable(((XXGDBean)goods.get(i)).getId());
                this.goodsNums[i].setVisible(true);
                this.goodsNums[i].setText(((XXGDBean)goods.get(i)).getSum() + "");
                this.goodsImgs[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 40, 40));
                this.mouselistener[i].setGoodstable(goodstable);
                this.numMax += ((XXGDBean)goods.get(i)).getSum();
                this.goodsNums1[i].setVisible(true);
                this.goodsNums1[i].setText(((XXGDBean)goods.get(i)).getSum() + "");
            }
            this.changeName("#c580404" + bean.getPackgrade() + "级");
        }
    }
    
    public void refreshItem(int caozuo) {
        this.b1.setVisible(false);
        ChongjipackBean bean = this.chongjipackBean;
        if (bean != null) {
            List<XXGDBean> goods = ChongjipackBean.getGoodsImpactGrade(bean.getPackgoods());
            int index = this.getB2().getI();
            if (caozuo == 1) {
                if (--index < 0) {
                    index = 0;
                }
            }
            else if (caozuo == 2) {
                ++index;
            }
            if (index != 0) {
                this.b1.setVisible(true);
            }
            if (index + 6 > goods.size()) {
                ZhuFrame.getZhuJpanel().addPrompt("没有更多了#76");
                return;
            }
            this.getB2().setI(index);
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            int needlv = AnalysisString.lvldirection1(bean.getPackgrade());
            int level = (int)loginResult.getGrade();
            int typeMenu = ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getTypeMenu();
            if (level >= needlv) {
                int lvl = -1;
                if (typeMenu == 1) {
                    lvl = 4;
                }
                else if (typeMenu - 1 == loginResult.getLowOrHihtpack()) {
                    lvl = 5;
                }
                if (lvl != -1) {
                    String[] split = null;
                    if (loginResult.getVipget() != null) {
                        split = loginResult.getVipget().split("&&");
                    }
                    if (checkYesOrNo(split, lvl + "", bean.getPackgradetype() + "")) {
                        this.moneyRichLQ.setText("#c784023  已领取");
                        this.moneyRichLQ.setVisible(true);
                        this.moneyRich.setVisible(false);
                    }
                    else {
                        this.moneyRichLQ.setText("");
                    }
                }
                else {
                    this.moneyRich.setText("#c784023暂不可领取");
                }
            }
            else {
                this.moneyRich.setText("#c784023等级未达到");
                this.moneyRich.setVisible(true);
            }
            this.goodsImgsClear();
            this.numMax = 0;
            int size = 0;
            if (goods.size() > 6) {
                size = 6;
                this.b2.setVisible(true);
            }
            else {
                size = goods.size();
            }
            int j = 0;
            for (int i = 0; i < this.goodsNums.length; ++i) {
                this.goodsNums[i].setVisible(false);
                this.goodsImgs[i].setVisible(false);
                this.goodsNums1[i].setVisible(false);
            }
            for (int i = index; i < index + 6; ++i) {
                Goodstable goodstable = UserMessUntil.getgoodstable(((XXGDBean)goods.get(i)).getId());
                this.goodsNums[j].setVisible(true);
                this.goodsNums[j].setText(((XXGDBean)goods.get(i)).getSum() + "");
                this.goodsImgs[j].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 40, 40));
                this.mouselistener[j].setGoodstable(goodstable);
                ++this.numMax;
                this.goodsNums1[j].setVisible(true);
                this.goodsNums1[j].setText(((XXGDBean)goods.get(i)).getSum() + "");
                this.goodsImgs[j].setVisible(true);
                ++j;
            }
            this.changeName("#c580404" + bean.getPackgrade() + "级");
        }
    }
    
    private String getLiBaoType(ChongjipackBean chongjipackBean) {
        if ((int)chongjipackBean.getPacktype() == 1) {
            return "4";
        }
        if ((int)chongjipackBean.getPacktype() == 2 || (int)chongjipackBean.getPacktype() == 3) {
            return "5";
        }
        if ((int)chongjipackBean.getPacktype() == 4) {
            return "2";
        }
        if ((int)chongjipackBean.getPacktype() == 5) {
            return "3";
        }
        if ((int)chongjipackBean.getPacktype() == 7) {
            return "7";
        }
        return "";
    }
    
    public void showGoods1(ChongjipackBean bean) {
        LoginResult loginResult1 = RoleData.getRoleData().getLoginResult();
        this.chongjipackBean = bean;
        this.b1.setVisible(false);
        this.b2.setVisible(false);
        if (bean != null) {
            List<XXGDBean> goods = ChongjipackBean.getGoodsImpactGrade(bean.getPackgoods());
            LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();
            String[] split = null;
            if (loginResult2.getVipget() != null) {
                split = loginResult2.getVipget().split("&&");
            }
            BigDecimal daypaysum = loginResult2.getDaypaysum();
            BigDecimal dayandpayorno = loginResult2.getDayandpayorno();
            BigDecimal paysum = loginResult2.getPaysum();
            if ((int)bean.getPacktype() == 4) {
                if (daypaysum.doubleValue() > (double)(int)this.chongjipackBean.getCanpaymoney()) {
                    if (checkYesOrNo(split, "2", bean.getPackgradetype() + "")) {
                        this.moneyRichLQ.setText("#c784023  已领取");
                        this.moneyRichLQ.setVisible(true);
                        this.moneyRich.setVisible(false);
                    }
                    else {
                        this.moneyRichLQ.setText("");
                    }
                }
                else {
                    this.moneyRich.setText("#c784023金额未达到");
                    this.moneyRich.setVisible(true);
                }
            }
            else if ((int)bean.getPacktype() == 5) {
                if (dayandpayorno.compareTo(BigDecimal.valueOf((long)(int)this.chongjipackBean.getPackgradetype())) >= 0) {
                    if (checkYesOrNo(split, "3", bean.getPackgradetype() + "")) {
                        this.moneyRichLQ.setText("#c784023  已领取");
                        this.moneyRichLQ.setVisible(true);
                        this.moneyRich.setVisible(false);
                    }
                    else {
                        this.moneyRichLQ.setText("");
                    }
                }
                else {
                    this.moneyRich.setText("#c784023天数未达到");
                    this.moneyRich.setVisible(true);
                }
            }
            else if ((int)bean.getPacktype() == 7) {
                if (paysum.doubleValue() > (double)(int)this.chongjipackBean.getCanpaymoney()) {
                    if (checkYesOrNo(split, "7", bean.getPackgradetype() + "")) {
                        this.moneyRichLQ.setText("#c784023  已领取");
                        this.moneyRichLQ.setVisible(true);
                        this.moneyRich.setVisible(false);
                    }
                    else {
                        this.moneyRichLQ.setText("");
                    }
                }
                else {
                    this.moneyRich.setText("#c784023金额未达到");
                    this.moneyRich.setVisible(true);
                }
            }
            this.goodsImgsClear();
            this.numMax = 0;
            int size = 0;
            if (goods.size() > 6) {
                size = 6;
                this.b2.setVisible(true);
            }
            else {
                size = goods.size();
            }
            for (int i = 0; i < size; ++i) {
                Goodstable goodstable = UserMessUntil.getgoodstable(((XXGDBean)goods.get(i)).getId());
                if (goodstable != null) {
                    this.goodsNums[i].setVisible(true);
                    this.goodsNums[i].setText(((XXGDBean)goods.get(i)).getSum() + "");
                    this.goodsImgs[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 40, 40));
                    this.mouselistener[i].setGoodstable(goodstable);
                    ++this.numMax;
                    this.goodsNums1[i].setVisible(true);
                    this.goodsNums1[i].setText(((XXGDBean)goods.get(i)).getSum() + "");
                }
            }
            if ((int)bean.getPacktype() == 4) {
                this.changeName("#c580404累计" + bean.getCanpaymoney() + "元");
            }
            else if ((int)bean.getPacktype() == 5) {
                this.changeName("#c580404连冲" + bean.getPackgradetype() + "天");
            }
            else if ((int)bean.getPacktype() == 7) {
                this.changeName("#c580404累计" + bean.getCanpaymoney() + "元");
            }
        }
    }
    
    public void refreshItem1(int caozuo) {
        this.b1.setVisible(false);
        ChongjipackBean bean = this.chongjipackBean;
        if (bean != null) {
            List<XXGDBean> goods = ChongjipackBean.getGoodsImpactGrade(bean.getPackgoods());
            int index = this.getB2().getI();
            if (caozuo == 1) {
                if (--index < 0) {
                    index = 0;
                }
            }
            else if (caozuo == 2) {
                ++index;
            }
            if (index != 0) {
                this.b1.setVisible(true);
            }
            if (index + 6 > goods.size()) {
                ZhuFrame.getZhuJpanel().addPrompt("没有更多了#76");
                return;
            }
            this.getB2().setI(index);
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            String[] split = null;
            if (loginResult.getVipget() != null) {
                split = loginResult.getVipget().split("&&");
            }
            BigDecimal daypaysum = loginResult.getDaypaysum();
            BigDecimal dayandpayorno = loginResult.getDayandpayorno();
            BigDecimal paysum = loginResult.getPaysum();
            if ((int)bean.getPacktype() == 4) {
                if (daypaysum.doubleValue() > (double)(int)this.chongjipackBean.getCanpaymoney()) {
                    if (checkYesOrNo(split, "2", bean.getPackgradetype() + "")) {
                        this.moneyRichLQ.setText("#c784023  已领取");
                        this.moneyRichLQ.setVisible(true);
                        this.moneyRich.setVisible(false);
                    }
                }
                else {
                    this.moneyRich.setText("#c784023金额未达到");
                    this.moneyRich.setVisible(true);
                }
            }
            else if ((int)bean.getPacktype() == 5) {
                if (dayandpayorno.compareTo(BigDecimal.valueOf((long)(int)this.chongjipackBean.getPackgradetype())) >= 0) {
                    if (checkYesOrNo(split, "3", bean.getPackgradetype() + "")) {
                        this.moneyRichLQ.setText("#c784023  已领取");
                        this.moneyRichLQ.setVisible(true);
                        this.moneyRich.setVisible(false);
                    }
                }
                else {
                    this.moneyRich.setText("#c784023天数未达到");
                    this.moneyRich.setVisible(true);
                }
            }
            else if ((int)bean.getPacktype() == 7) {
                if (paysum.doubleValue() > (double)(int)this.chongjipackBean.getCanpaymoney()) {
                    if (checkYesOrNo(split, "7", bean.getPackgradetype() + "")) {
                        this.moneyRichLQ.setText("#c784023  已领取");
                        this.moneyRichLQ.setVisible(true);
                        this.moneyRich.setVisible(false);
                    }
                    else {
                        this.moneyRichLQ.setText("");
                    }
                }
                else {
                    this.moneyRich.setText("#c784023金额未达到");
                    this.moneyRich.setVisible(true);
                }
            }
            this.goodsImgsClear();
            this.numMax = 0;
            int size = 0;
            if (goods.size() > 6) {
                size = 6;
                this.b2.setVisible(true);
            }
            else {
                size = goods.size();
            }
            int j = 0;
            for (int i = 0; i < this.goodsNums.length; ++i) {
                this.goodsNums[i].setVisible(false);
                this.goodsImgs[i].setVisible(false);
                this.goodsNums1[i].setVisible(false);
            }
            for (int i = index; i < index + 6; ++i) {
                Goodstable goodstable = UserMessUntil.getgoodstable(((XXGDBean)goods.get(i)).getId());
                this.goodsNums[j].setVisible(true);
                this.goodsNums[j].setText(((XXGDBean)goods.get(i)).getSum() + "");
                this.goodsImgs[j].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 40, 40));
                this.mouselistener[j].setGoodstable(goodstable);
                ++this.numMax;
                this.goodsNums1[j].setVisible(true);
                this.goodsNums1[j].setText(((XXGDBean)goods.get(i)).getSum() + "");
                this.goodsImgs[j].setVisible(true);
                ++j;
            }
            if ((int)bean.getPacktype() == 4) {
                this.changeName("#c580404累计" + bean.getCanpaymoney() + "元");
            }
            else if ((int)bean.getPacktype() == 5) {
                this.changeName("#c580404连冲" + bean.getPackgradetype() + "天");
            }
            else if ((int)bean.getPacktype() == 7) {
                this.changeName("#c580404累计" + bean.getCanpaymoney() + "元");
            }
        }
    }
    
    public void goodsImgsClear() {
        for (int i = 0; i < this.goodsImgs.length; ++i) {
            this.goodsNums[i].setText("");
            this.goodsNums[i].setVisible(false);
            this.goodsImgs[i].setIcon(null);
            this.goodsNums1[i].setText("");
            this.goodsNums1[i].setVisible(false);
        }
    }
    
    public static boolean checkYesOrNo(String[] infoArr, String action, String grade) {
        if (infoArr == null) {
            return false;
        }
        action += "=";
        for (int i = 0; i < infoArr.length; ++i) {
            if (infoArr[i].startsWith(action)) {
                String[] arr = infoArr[i].split("=");
                if (arr.length == 1) {
                    return false;
                }
                String[] gradeArr = arr[1].split("\\|");
                for (int j = 0; j < gradeArr.length; ++j) {
                    if (gradeArr[j].equals(grade)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static int getRoleGradeReward(int grade) {
        if (grade >= 439) {
            return 15;
        }
        if (grade >= 379) {
            return 14;
        }
        if (grade >= 336) {
            return 13;
        }
        if (grade >= 296) {
            return 12;
        }
        if (grade >= 210) {
            return 11;
        }
        if (grade >= 188) {
            return 10;
        }
        if (grade >= 158) {
            return 9;
        }
        if (grade >= 103) {
            return 8;
        }
        if (grade >= 100) {
            return 7;
        }
        if (grade >= 90) {
            return 6;
        }
        if (grade >= 80) {
            return 5;
        }
        if (grade >= 70) {
            return 4;
        }
        if (grade >= 50) {
            return 3;
        }
        if (grade >= 30) {
            return 2;
        }
        if (grade >= 0) {
            return 1;
        }
        return 0;
    }
    
    public void changeName(String text) {
        this.nameRich.setText(text);
        Dimension d = this.nameRich.computeSize(136);
        this.nameRich.setSize(d);
        this.nameRich.setPreferredSize(d);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/background1/B327.png", -1, -1);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 477, 57, this);
    }
    
    public JLabel[] getGoodsImgs() {
        if (this.goodsImgs == null) {
            this.goodsImgs = new JLabel[9];
            this.mouselistener = new VipGoodsMouselistener[9];
            for (int i = 0; i < this.goodsImgs.length; ++i) {
                this.goodsImgs[i] = new JLabel();
                this.mouselistener[i] = new VipGoodsMouselistener();
                this.goodsImgs[i].setBounds(124 + i * 42, 8, 40, 40);
                this.goodsImgs[i].addMouseListener(this.mouselistener[i]);
                this.add(this.goodsImgs[i]);
            }
        }
        return this.goodsImgs;
    }
    
    public void setGoodsImgs(JLabel[] goodsImgs) {
        this.goodsImgs = goodsImgs;
    }
    
    public JLabel[] getGoodsNums1() {
        if (this.goodsNums1 == null) {
            this.goodsNums1 = new JLabel[9];
            for (int i = 0; i < this.goodsNums1.length; ++i) {
                (this.goodsNums1[i] = new JLabel()).setBounds(124 + i * 42, 8, 40, 40);
                this.goodsNums1[i].setIcon(CutButtonImage.getImage("inkImg/background1/B326.png", -1, -1));
                this.add(this.goodsNums1[i]);
            }
        }
        return this.goodsNums1;
    }
    
    public void setGoodsNums1(JLabel[] goodsNums1) {
        this.goodsNums1 = goodsNums1;
    }
    
    public JLabel[] getGoodsNums() {
        if (this.goodsNums == null) {
            this.goodsNums = new JLabel[9];
            for (int i = 0; i < this.goodsNums.length; ++i) {
                (this.goodsNums[i] = new JLabel()).setBounds(124 + i * 42, 6, 40, 16);
                this.goodsNums[i].setVerticalTextPosition(0);
                this.goodsNums[i].setHorizontalTextPosition(0);
                this.goodsNums[i].setForeground(Color.white);
                this.goodsNums[i].setFont(UIUtils.TEXT_FONT11);
                this.add(this.goodsNums[i]);
            }
        }
        return this.goodsNums;
    }
    
    public void setGoodsNums(JLabel[] goodsNums) {
        this.goodsNums = goodsNums;
    }
    
    public VipShopBtn getGetBtn() {
        if (this.getBtn == null) {
            (this.getBtn = new VipShopBtn("inkImg/button1/K93.png", 1, 8, this)).setBounds(380, 5, 85, 32);
            this.getBtn.setVisible(false);
            this.add(this.getBtn);
        }
        return this.getBtn;
    }
    
    public void setGetBtn(VipShopBtn getBtn) {
        this.getBtn = getBtn;
    }
    
    public RichLabel getNameRich() {
        if (this.nameRich == null) {
            this.nameRich = new RichLabel("1级奖励", UIUtils.TEXT_FONT15);
            Dimension d = this.nameRich.computeSize(136);
            this.nameRich.setSize(d);
            this.nameRich.setPreferredSize(d);
            this.nameRich.setBounds(30, 16, 136, 20);
            this.add(this.nameRich);
        }
        return this.nameRich;
    }
    
    public void setNameRich(RichLabel nameRich) {
        this.nameRich = nameRich;
    }
    
    public RichLabel getMoneyRich() {
        if (this.moneyRich == null) {
            this.moneyRich = new RichLabel("", UIUtils.TEXT_FONT15);
            Dimension d = this.moneyRich.computeSize(136);
            this.moneyRich.setSize(d);
            this.moneyRich.setPreferredSize(d);
            this.moneyRich.setBounds(379, 29, 136, 30);
            this.moneyRich.setVisible(false);
            this.add(this.moneyRich);
        }
        return this.moneyRich;
    }
    
    public void setMoneyRich(RichLabel moneyRich) {
        this.moneyRich = moneyRich;
    }
    
    public RichLabel getMoneyRichLQ() {
        if (this.moneyRichLQ == null) {
            this.moneyRichLQ = new RichLabel("", UIUtils.TEXT_FONT15);
            Dimension d = this.moneyRichLQ.computeSize(136);
            this.moneyRichLQ.setSize(d);
            this.moneyRichLQ.setPreferredSize(d);
            this.moneyRichLQ.setBounds(390, 13, 136, 30);
            this.moneyRichLQ.setVisible(false);
            this.add(this.moneyRichLQ);
        }
        return this.moneyRichLQ;
    }
    
    public void setMoneyRichLQ(RichLabel moneyRich) {
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public ChongjipackBean getChongjipackBean() {
        return this.chongjipackBean;
    }
    
    public void setChongjipackBean(ChongjipackBean chongjipackBean) {
        this.chongjipackBean = chongjipackBean;
    }
    
    public VipGoodsMouselistener[] getMouselistener() {
        return this.mouselistener;
    }
    
    public void setMouselistener(VipGoodsMouselistener[] mouselistener) {
        this.mouselistener = mouselistener;
    }
    
    public int getNumMax() {
        return this.numMax;
    }
    
    public void setNumMax(int numMax) {
        this.numMax = numMax;
    }
    
    public ImpactGradeGoodsBtn getB1() {
        return this.b1;
    }
    
    public void setB1(ImpactGradeGoodsBtn b1) {
        this.b1 = b1;
    }
    
    public ImpactGradeGoodsBtn getB2() {
        return this.b2;
    }
    
    public void setB2(ImpactGradeGoodsBtn b2) {
        this.b2 = b2;
    }
}
