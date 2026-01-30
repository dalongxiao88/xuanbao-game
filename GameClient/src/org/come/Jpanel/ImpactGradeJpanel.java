package org.come.Jpanel;

import javax.swing.*;

import com.tool.btn.XyCjBtn;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import com.tool.time.XYTimerTask;
import org.come.bean.QuackGameBean;
import org.come.entity.Goodstable;
import org.come.mouslisten.VipGoodsMouselistener;
import org.come.until.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

import org.come.Frame.ImpactGradeJframe;
import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;

import org.come.bean.ConfigureBean;
import com.tool.btn.FormsOnOffBtn;

import java.math.BigDecimal;
import java.util.List;
import java.util.Timer;

import org.come.model.Configure;
import org.come.bean.ChongjipackBean;

import com.tool.btn.VipShopBtn;

public class ImpactGradeJpanel extends JPanel
{
    private VipShopBtn freeMenuBtn;
    private VipShopBtn smallMenuBtn;
    private VipShopBtn luxuryMenuBtn;
    private VipShopBtn meiRBtn;
    private VipShopBtn LXBtn;
    private VipShopBtn LJBtn;
    private VipShopBtn xycjBtn;
    private XyCjBtn xycj_1, xycj_10, xycjLs;
    private List<ImpactGradeGoodsJpanel> goodsJpanels;
    private JScrollPane scrollPane;
    private List<ChongjipackBean> chongjipackBeans;
    private int typeMenu;
    private ImageIcon iconBack;
    private ImageIcon iconTop;
    private ImageIcon iconshj;

    public ImpactGradeJpanel() {
        this.typeMenu = -1;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(7));
        this.setPreferredSize(new Dimension(735, 451));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 103);
        offBtn.setBounds(708, 25, 25, 25);
        this.add(offBtn);
        this.getFreeMenuBtn();
        this.getSmallMenuBtn();
        this.getLuxuryMenuBtn();
        this.getMeiRiBtn();
        this.getLXBtn();
        this.getLJBtn();
        this.getScrollPane();
        this.getGoodsJpanels();
        this.getXycjBtn();
        this.typeMenu = 1;
        this.getGoods();
        this.getGoodsImgs();
    }
    
    public void changeMenuBtn(int type) {
        if (type == -1) {
            type = 1;
        }
        showXYDJ(false);
        this.typeMenu = type;
        scrollPane.setVisible(true);
        try {
            if (this.typeMenu == 1) {
                this.freeMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K88.png"));
                this.smallMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K90.png"));
                this.luxuryMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K92.png"));
                this.meiRBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K93.png"));
                this.LXBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K95.png"));
                this.LJBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K96.png"));
                this.freeMenuBtn.imgchange(2);
                this.smallMenuBtn.imgchange(0);
                this.luxuryMenuBtn.imgchange(0);
                this.meiRBtn.imgchange(0);
                this.LXBtn.imgchange(0);
                this.LJBtn.imgchange(0);
                xycjBtn.imgchange(0);
            }
            else if (this.typeMenu == 2) {
                this.freeMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K88.png"));
                this.smallMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K90.png"));
                this.luxuryMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K92.png"));
                this.meiRBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K93.png"));
                this.LXBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K95.png"));
                this.freeMenuBtn.imgchange(0);
                this.smallMenuBtn.imgchange(2);
                this.luxuryMenuBtn.imgchange(0);
                this.meiRBtn.imgchange(0);
                this.LXBtn.imgchange(0);
                this.LJBtn.imgchange(0);
                xycjBtn.imgchange(0);
            }
            else if (this.typeMenu == 3) {
                this.freeMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K88.png"));
                this.smallMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K90.png"));
                this.luxuryMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K92.png"));
                this.meiRBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K93.png"));
                this.LXBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K95.png"));
                this.freeMenuBtn.imgchange(0);
                this.smallMenuBtn.imgchange(0);
                this.luxuryMenuBtn.imgchange(2);
                this.meiRBtn.imgchange(0);
                this.LXBtn.imgchange(0);
                this.LJBtn.imgchange(0);
                xycjBtn.imgchange(0);
            }
            else if (this.typeMenu == 4) {
                this.freeMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K88.png"));
                this.smallMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K90.png"));
                this.luxuryMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K92.png"));
                this.meiRBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K93.png"));
                this.LXBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K95.png"));
                this.LJBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K96.png"));
                this.freeMenuBtn.imgchange(0);
                this.smallMenuBtn.imgchange(0);
                this.luxuryMenuBtn.imgchange(0);
                this.meiRBtn.imgchange(2);
                this.LXBtn.imgchange(0);
                this.LJBtn.imgchange(0);
                xycjBtn.imgchange(0);
            }
            else if (this.typeMenu == 5) {
                this.freeMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K88.png"));
                this.smallMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K90.png"));
                this.luxuryMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K92.png"));
                this.meiRBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K93.png"));
                this.LXBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K95.png"));
                this.LJBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K96.png"));
                this.freeMenuBtn.imgchange(0);
                this.smallMenuBtn.imgchange(0);
                this.luxuryMenuBtn.imgchange(0);
                this.meiRBtn.imgchange(0);
                this.LXBtn.imgchange(2);
                this.LJBtn.imgchange(0);
                xycjBtn.imgchange(0);
            }
            else if (this.typeMenu == 7) {
                this.freeMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K88.png"));
                this.smallMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K90.png"));
                this.luxuryMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K92.png"));
                this.meiRBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K93.png"));
                this.LXBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K95.png"));
                this.LJBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K96.png"));
                this.freeMenuBtn.imgchange(0);
                this.smallMenuBtn.imgchange(0);
                this.luxuryMenuBtn.imgchange(0);
                this.meiRBtn.imgchange(0);
                this.LXBtn.imgchange(0);
                this.LJBtn.imgchange(2);
                xycjBtn.imgchange(0);
            }
            else if (this.typeMenu == 9) {
                scrollPane.setVisible(false);
                String sendMes = Agreement.getFiveMsgAgreement("CC" + 10);
                SendMessageUntil.toServer(sendMes);
                showXYDJ(true);
                this.freeMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K88.png"));
                this.smallMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K90.png"));
                this.luxuryMenuBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K92.png"));
                this.meiRBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K93.png"));
                this.LXBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K95.png"));
                this.LJBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K96.png"));
                this.freeMenuBtn.imgchange(0);
                this.smallMenuBtn.imgchange(0);
                this.luxuryMenuBtn.imgchange(0);
                this.meiRBtn.imgchange(0);
                this.LXBtn.imgchange(0);
                this.LJBtn.imgchange(0);
                xycjBtn.imgchange(2);
            }
            this.scrollPane.getVerticalScrollBar().setValue(0);
            this.getGoods();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getGoods() {
        if (this.typeMenu == -1 || typeMenu == 9) {
            return;
        }
        String sendmes = Agreement.getAgreement().chongjipackgetAgreement(this.typeMenu + "");
        SendMessageUntil.toServer(sendmes);
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
    
    public void showGoods(List<ChongjipackBean> chongjipackBeans) {
        if (chongjipackBeans == null) {
            chongjipackBeans = new ArrayList<>();
        }
        this.goodsJpanels.clear();
        this.chongjipackBeans = chongjipackBeans;
        String vipget = RoleData.getRoleData().getLoginResult().getVipget();
        String[] split = null;
        if (vipget != null) {
            split = vipget.split("&&");
        }
        for (int i = 0; i < chongjipackBeans.size(); ++i) {
            if (i < chongjipackBeans.size()) {
                if (this.goodsJpanels.size() < chongjipackBeans.size()) {
                    this.goodsJpanels.add(new ImpactGradeGoodsJpanel());
                }
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                int needlv = AnalysisString.lvldirection1(((ChongjipackBean)chongjipackBeans.get(i)).getPackgrade());
                int level = (int)loginResult.getGrade();
                BigDecimal daypaysum = loginResult.getDaypaysum();
                BigDecimal paysum = loginResult.getPaysum();
                BigDecimal dayandpayorno = loginResult.getDayandpayorno();
                if ((int)((ChongjipackBean)chongjipackBeans.get(i)).getPacktype() == 1 || (int)((ChongjipackBean)chongjipackBeans.get(i)).getPacktype() == 2 || (int)((ChongjipackBean)chongjipackBeans.get(i)).getPacktype() == 3) {
                    if (ImpactGradeGoodsJpanel.checkYesOrNo(split, this.getLiBaoType((ChongjipackBean)chongjipackBeans.get(i)), ((ChongjipackBean)chongjipackBeans.get(i)).getPackgradetype() + "")) {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getNameRich().setText(((ChongjipackBean)chongjipackBeans.get(i)).getPackgrade() + "级");
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(-1);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(false);
                    }
                    else if (level < needlv) {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getNameRich().setText(((ChongjipackBean)chongjipackBeans.get(i)).getPackgrade() + "级");
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(-1);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(true);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setIcon(CutButtonImage.getImage("inkImg/button1/99.png", -1, -1));
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBounds(420, 3, 85, 32);
                    }
                    else {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(1);
                        try {
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getNameRich().setText(((ChongjipackBean)chongjipackBeans.get(i)).getPackgrade() + "级");
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(true);
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setIcons(CutButtonImage.cuts("inkImg/button1/1911.png"));
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBounds(378, 12, 91, 32);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if ((int)((ChongjipackBean)chongjipackBeans.get(i)).getPacktype() == 4) {
                    if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "2", ((ChongjipackBean)chongjipackBeans.get(i)).getPackgradetype() + "")) {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(-1);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(false);
                    }
                    else if (daypaysum.doubleValue() < (double)(int)((ChongjipackBean)chongjipackBeans.get(i)).getCanpaymoney()) {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(-1);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(true);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setIcon(CutButtonImage.getImage("inkImg/button1/99.png", -1, -1));
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBounds(420, 3, 85, 32);
                    }
                    else {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(1);
                        try {
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(true);
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setIcons(CutButtonImage.cuts("inkImg/button1/1911.png"));
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBounds(378, 12, 91, 32);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if ((int)((ChongjipackBean)chongjipackBeans.get(i)).getPacktype() == 5) {
                    if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "3", ((ChongjipackBean)chongjipackBeans.get(i)).getPackgradetype() + "")) {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(-1);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(false);
                    }
                    else if (dayandpayorno.compareTo(BigDecimal.valueOf((long)(int)((ChongjipackBean)chongjipackBeans.get(i)).getPackgradetype())) < 0) {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(-1);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(true);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setIcon(CutButtonImage.getImage("inkImg/button1/99.png", -1, -1));
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBounds(420, 3, 85, 32);
                    }
                    else {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(1);
                        try {
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(true);
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setIcons(CutButtonImage.cuts("inkImg/button1/1911.png"));
                            ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBounds(378, 12, 91, 32);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "7", ((ChongjipackBean)chongjipackBeans.get(i)).getPackgradetype() + "")) {
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(-1);
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(false);
                }
                else if (paysum.doubleValue() < (double)(int)((ChongjipackBean)chongjipackBeans.get(i)).getCanpaymoney()) {
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(-1);
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(true);
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setIcon(CutButtonImage.getImage("inkImg/button1/99.png", -1, -1));
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBounds(420, 3, 85, 32);
                }
                else {
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBtn(1);
                    try {
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setVisible(true);
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setIcons(CutButtonImage.cuts("inkImg/button1/1911.png"));//领取按钮
                        ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).getGetBtn().setBounds(378, 12, 91, 32);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if ((int)((ChongjipackBean)chongjipackBeans.get(i)).getPacktype() == 1 || (int)((ChongjipackBean)chongjipackBeans.get(i)).getPacktype() == 2 || (int)((ChongjipackBean)chongjipackBeans.get(i)).getPacktype() == 3) {
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).showGoods((ChongjipackBean)chongjipackBeans.get(i));
                }
                else {
                    ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).showGoods1((ChongjipackBean)chongjipackBeans.get(i));
                }
            }
            else {
                ((ImpactGradeGoodsJpanel)this.goodsJpanels.get(i)).showGoods(null);
            }
        }
        this.addGoods();
    }
    
    public void refreshPanel() {
        this.showGoods(this.chongjipackBeans);
    }
    
    public void addGoods() {
        JPanel view = (JPanel)this.scrollPane.getViewport().getView();
        view.removeAll();
        view.repaint();
        int num = 0;
        for (int i = 0; i < this.goodsJpanels.size(); ++i) {
            ImpactGradeGoodsJpanel vipGoodsJpanel = (ImpactGradeGoodsJpanel)this.goodsJpanels.get(i);
            if (vipGoodsJpanel.getChongjipackBean() != null) {
                vipGoodsJpanel.setBounds(10, num * 62, 477, 57);
                ++num;
                view.add(vipGoodsJpanel);
            }
        }
        view.setPreferredSize(new Dimension(477, 62 * num));
        this.scrollPane.updateUI();
        this.scrollPane.invalidate();
        this.scrollPane.validate();
        this.scrollPane.repaint();
        if (!ImpactGradeJframe.getImpactGradeJframe().isVisible()) {
            FormsManagement.showForm(103);
        }
    }
    private ImageIcon xycjBack;
    //调整开服狂欢位置
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (typeMenu == 9) {
            if (xycjBack == null) {
                xycjBack = new ImageIcon("inkImg/background/xycj-back.png");
            }
            g.drawImage(xycjBack.getImage(), 0, 0, 735, 451, this);
            g.setColor(new Color(110, 61, 44));
            g.drawString("消耗10积分", 437, 420);
            g.drawString("消耗100积分", 547, 420);
            g.drawString(RoleData.getRoleData().getLoginResult().getMoney().toString(), 305, 415);
            g.drawString(RoleData.getRoleData().getLoginResult().getPaysum().toString(), 305, 392);
            g2d.setColor(new Color(173, 121, 43, 138));
            RoundRectangle2D roundedRect = new RoundRectangle2D.Float(210, 122, 480, 20, 0, 0);
            g2d.fill(roundedRect);
            if (gameBean != null) {
                g.setFont(new Font("宋体", 1, 15));
                g.setColor(Color.RED);
                g.drawString(gameBean.getG1(), 385, 270);
                g.drawString(gameBean.getG2(), 495, 270);
            }
        }else {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/background1/B325.png", -1, -1);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 735, 451, this);
            this.scrollPane.setBounds(198, 178, 483, 248);
        }
    }
    
    public VipShopBtn getFreeMenuBtn() {
        if (this.freeMenuBtn == null) {
            (this.freeMenuBtn = new VipShopBtn("inkImg/button1/K88.png", 1, 5, this)).setBounds(88, 70, 104, 28);//(30, 70, 104, 28);
            this.add(this.freeMenuBtn);
            this.add(this.freeMenuBtn);
        }//等级礼包
        return this.freeMenuBtn;
    }
    
    public void setFreeMenuBtn(VipShopBtn freeMenuBtn) {
        this.freeMenuBtn = freeMenuBtn;
    }
    
    public VipShopBtn getSmallMenuBtn() {
        if (this.smallMenuBtn == null) {
            (this.smallMenuBtn = new VipShopBtn("inkImg/button1/K90.png", 1, 6, this)).setBounds(90, 70+35, 104, 28);//(30, 110, 104, 28);
            this.add(this.smallMenuBtn);
        }//成长基金
        return this.smallMenuBtn;
    }
    
    public void setSmallMenuBtn(VipShopBtn smallMenuBtn) {
        this.smallMenuBtn = smallMenuBtn;
    }
    
    public VipShopBtn getLuxuryMenuBtn() {
        if (this.luxuryMenuBtn == null) {
            (this.luxuryMenuBtn = new VipShopBtn("inkImg/button1/K92.png", 1, 7, this)).setBounds(30, 150, 104, 28);
        }
        return this.luxuryMenuBtn;
    }
    
    public void setLuxuryMenuBtn(VipShopBtn luxuryMenuBtn) {
        this.luxuryMenuBtn = luxuryMenuBtn;
    }
    
    public VipShopBtn getMeiRiBtn() {
        if (this.meiRBtn == null) {
            (this.meiRBtn = new VipShopBtn("inkImg/button1/K93.png", 1, 9, this)).setBounds(30, 190, 104, 28);
        }
        return this.meiRBtn;
    }
    
    public void setMeiRBtn(VipShopBtn meiRBtn) {
        this.meiRBtn = meiRBtn;
    }
    
    public VipShopBtn getLXBtn() {
        if (this.LXBtn == null) {
            (this.LXBtn = new VipShopBtn("inkImg/button1/K95.png", 1, 10, this)).setBounds(30, 230, 104, 28);
        }
        return this.LXBtn;
    }
    
    public void setLXBtn(VipShopBtn LXBtn) {
        this.LXBtn = LXBtn;
    }
    
    public VipShopBtn getLJBtn() {
        if (this.LJBtn == null) {
            (this.LJBtn = new VipShopBtn("inkImg/button1/K96.png", 1, 12, this)).setBounds(90, 70+35*2, 104, 28);//(30, 150, 104, 28);
            this.add(this.LJBtn);
        }//累计回馈
        return this.LJBtn;
    }
    public VipShopBtn getXycjBtn() {//幸运抽奖
        if (xycjBtn == null) {
            xycjBtn = new VipShopBtn("inkImg/button1/153.png", 1, 13, this);
            xycjBtn.setText("幸运大奖");
            xycjBtn.setFont(UIUtils.TEXT_FONT_17);
            xycjBtn.setColors(UIUtils.COLOR_HONG);
            xycjBtn.setVerticalTextPosition(SwingConstants.CENTER);
            xycjBtn.setHorizontalTextPosition(SwingConstants.CENTER);
            xycjBtn.setBounds(90, 70 + 35 * 3, 102, 28);
            this.add(xycjBtn);
            getXycj_1();
            getXycj_10();
            getXycjLs();
        }
        return xycjBtn;
    }
    public void setLJBtn(VipShopBtn LJBtn) {
        this.LJBtn = LJBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public ImageIcon getIconTop() {
        return this.iconTop;
    }
    
    public void setIconTop(ImageIcon iconTop) {
        this.iconTop = iconTop;
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUINew());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(197, 177, 503, 250);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            JPanel jPanel = new JPanel();
            jPanel.setBackground(null);
            jPanel.setOpaque(false);
            jPanel.setBorder(null);
            jPanel.setLayout(null);
            this.scrollPane.setViewportView(jPanel);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public List<ImpactGradeGoodsJpanel> getGoodsJpanels() {
        if (this.goodsJpanels == null) {
            this.goodsJpanels = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                this.goodsJpanels.add(new ImpactGradeGoodsJpanel());
            }
        }
        return this.goodsJpanels;
    }
    
    public void setGoodsJpanels(List<ImpactGradeGoodsJpanel> goodsJpanels) {
        this.goodsJpanels = goodsJpanels;
    }
    
    public int getTypeMenu() {
        return this.typeMenu;
    }
    
    public void setTypeMenu(int typeMenu) {
        this.typeMenu = typeMenu;
    }

    public XyCjBtn getXycj_1() {
        if (xycj_1 == null) {
            xycj_1 = new XyCjBtn("inkImg/button/cjbtn.png", 1, "单抽一次", "单抽一次");
            xycj_1.setBounds(425, 375, 89, 34);
            this.add(xycj_1);
        }
        return xycj_1;
    }

    public void setXycj_1(XyCjBtn xycj_1) {
        this.xycj_1 = xycj_1;
    }

    public XyCjBtn getXycj_10() {
        if (xycj_10 == null) {
            xycj_10 = new XyCjBtn("inkImg/button/cjbtn.png", 1, "连抽十次", "连抽十次");
            xycj_10.setBounds(540, 375, 89, 34);
            this.add(xycj_10);
        }
        return xycj_10;
    }

    public XyCjBtn getXycjLs() {
        if (xycjLs == null) {
            xycjLs = new XyCjBtn("inkImg/button/cjjl.png", 1, "", "");
            xycjLs.setBounds(640, 378, 63, 41);
            this.add(xycjLs);
        }
        return xycjLs;
    }
    private XYTimerTask xyTimerTask;
    private Timer timer;
    private RichLabel richLabel = new RichLabel();
    private QuackGameBean gameBean;
    private JLabel[] goodsImgs;
    private VipGoodsMouselistener[] mouselistener;

    public void XYDJInit(String[] drawGoods, String endTime1, QuackGameBean gameBean) {
        this.gameBean = gameBean;
        if (timer != null) {
            timer.cancel();
            timer.purge(); // 可选，清除已取消的任;
        }
//        String targetDateString = endTime1;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        Date data = new Date(endTime1);
        long endTime = data.getTime();


//        //开始时间
        long startTime = Util.getTime();
        long midTime = (endTime - startTime) / 1000;
//        targetDateTime.
        // 计算时间差
        if (timer == null) {
            timer = new Timer();
            xyTimerTask = new XYTimerTask(midTime, richLabel);
            timer.schedule(xyTimerTask, 0);
        } else {
            xyTimerTask.setMidTime(midTime);
            xyTimerTask.setRichLabel(richLabel);
        }

        List<String> arawInfo = new ArrayList<>();

        for (String drawGood : drawGoods) {
            if (drawGood.startsWith("N") || drawGood.startsWith("Y")) {
                continue;
            }
            arawInfo.add(drawGood);
        }
        for (int i = 0; i < arawInfo.size(); i++) {
            if (i > 6) {
                break;
            }
            arawInfo.get(i);
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(arawInfo.get(i)));
            if (goodstable != null) {


                if (i == 0) {
                    goodsImgs[i].setIcon(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()));
                    mouselistener[i].setGoodstable(goodstable);
                    goodsImgs[i].setBounds(377 + i * 42, 204, 49, 49);
                } else if (i == 1) {
                    goodsImgs[i].setIcon(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()));
                    mouselistener[i].setGoodstable(goodstable);
                    goodsImgs[i].setBounds(377 + i * 110, 204, 49, 49);
                } else {
                    goodsImgs[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 35, 35));
                    goodsImgs[i].setBounds(298 + (i - 2) * 73 - i * 2, 313, 35, 35);
                    mouselistener[i].setGoodstable(goodstable);
                }

            }
        }

    }
    public void showXYDJ(Boolean b) {
        for (JLabel goodsImg : goodsImgs) {
            goodsImg.setVisible(b);
        }
        xycj_1.setVisible(b);
        xycj_10.setVisible(b);
        richLabel.setVisible(b);
        xycjLs.setVisible(b);
    }
    public JLabel[] getGoodsImgs() {
        if (goodsImgs == null) {
            goodsImgs = new JLabel[7];
            mouselistener = new VipGoodsMouselistener[7];
            for (int i = 0; i < goodsImgs.length; i++) {
                goodsImgs[i] = new JLabel();
                mouselistener[i] = new VipGoodsMouselistener();
                goodsImgs[i].setBounds(146 + i * 42, 8, 40, 40);
                goodsImgs[i].addMouseListener(mouselistener[i]);
                this.add(goodsImgs[i]);
            }
            richLabel = new RichLabel() {
                @Override
                public void paint(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2d.setColor(Color.white);
                    super.paint(g2d);
                }
            };
            this.richLabel.setBounds(285, 120, 500, 30);
//            richLabel.setFont(new Font("楷体", Font.BOLD, 18));
            richLabel.setBorder(BorderFactory.createLineBorder(Color.red));
            this.add(richLabel);
        }
        return goodsImgs;
    }
}
