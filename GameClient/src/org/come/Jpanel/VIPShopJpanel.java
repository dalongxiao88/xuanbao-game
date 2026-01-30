package org.come.Jpanel;

import java.util.ArrayList;
import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Graphics;
import org.come.bean.LoginResult;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.TaobaoCourtMainJframe;
import org.come.until.CutButtonImage;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import com.tool.time.Limit;
import com.tool.time.TimerUtil;
import com.tool.time.TimeLimit;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.bean.PayvipBean;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VIPShopJpanel extends JPanel
{
    private JLabel dayNumS;
    private List<VipGoodsJpanel> goodsList;
    private JScrollPane scrollPane;
    int vipLvl;
    private List<PayvipBean> payvipBeanList;
    private int langNum;
    private ImageIcon iconTop;
    private ImageIcon iconBack;
    private ImageIcon iconPlan;
    private List<ImageIcon> iconVip;
    
    public VIPShopJpanel() {
        this.vipLvl = 0;
        this.langNum = 0;
        this.setPreferredSize(new Dimension(656, 445));
        this.setOpaque(false);
        this.setLayout(null);
        this.getdayNumS();
        this.getScrollPane();
        this.getGoodsList();
    }
    
    public void changeTimeS() {
        Limit limit = TimeLimit.getLimits().getLimit("VIP");
        if (limit != null) {
            int residueDay = TimerUtil.residueDay(limit.getTime());
            if (residueDay >= 0) {
                this.dayNumS.setText("剩余" + residueDay + "天");
                return;
            }
        }
        this.dayNumS.setText("--");
    }
    
    public JLabel getdayNumS() {
        if (this.dayNumS == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.dayNumS = new JLabel("30", 0)).setFont(UIUtils.TEXT_FONT82);
                this.dayNumS.setForeground(Color.black);
                this.dayNumS.setBounds(500, 376, 200, 16);
                this.dayNumS.setOpaque(false);
                this.add(this.dayNumS);
            }
            else {
                (this.dayNumS = new JLabel("30", 0)).setFont(UIUtils.TEXT_FONT82);
                this.dayNumS.setForeground(Color.white);
                this.dayNumS.setBounds(500, 376, 200, 16);
                this.dayNumS.setOpaque(false);
                this.add(this.dayNumS);
            }
        }
        return this.dayNumS;
    }
    
    public void addGoods() {
        JPanel view = (JPanel)this.scrollPane.getViewport().getView();
        view.removeAll();
        view.repaint();
        int num = 0;
        for (int i = 0; i < this.goodsList.size(); ++i) {
            VipGoodsJpanel vipGoodsJpanel = (VipGoodsJpanel)this.goodsList.get(i);
            if (vipGoodsJpanel.getPayvipBean() != null) {
                vipGoodsJpanel.setBounds(0 + num % 4 * 140, num / 4 * 190, 136, 190);
                ++num;
                view.add(vipGoodsJpanel);
            }
        }
        view.setPreferredSize(new Dimension(565, 190 * ((num % 4 == 0) ? (num / 4) : (num / 4 + 1))));
        this.scrollPane.updateUI();
        this.scrollPane.invalidate();
        this.scrollPane.validate();
        this.scrollPane.repaint();
        for (int i = 0; i < this.getIconVip().size(); ++i) {
            if (this.vipLvl >= i + 1) {
                ((ImageIcon)this.iconVip.get(i)).setImage(CutButtonImage.getImage("img/item/S" + (i + 20) + ".png", 30, 25).getImage());
            }
            else {
                ((ImageIcon)this.iconVip.get(i)).setImage(CutButtonImage.getImage("img/item/S" + (i + 30) + ".png", 30, 25).getImage());
            }
        }
        TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getCardLayout().show(TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel(), "vipshop");
    }
    
    public void getShop() {
        String sendmes = Agreement.getAgreement().GetvipgradepackAgreement();
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showGoodsList(List<PayvipBean> payvipBeanList) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.payvipBeanList = payvipBeanList;
            LoginResult loginResul = RoleData.getRoleData().getLoginResult();
            BigDecimal paysum = loginResul.getPaysum();
            int maxLevel = 0;
            if (payvipBeanList != null) {
                if (payvipBeanList.size() == 0) {
                    for (int i = 0; i < this.goodsList.size(); ++i) {
                        ((VipGoodsJpanel)this.goodsList.get(i)).showGoods(null);
                    }
                }
                else {
                    String vipget = RoleData.getRoleData().getLoginResult().getVipget();
                    String[] split = null;
                    if (vipget != null) {
                        split = vipget.split("&&");
                    }
                    for (int j = 0; j < this.goodsList.size(); ++j) {
                        if (j > payvipBeanList.size()) {
                            ((VipGoodsJpanel)this.goodsList.get(j)).showGoods(null);
                        }
                        else if (j < payvipBeanList.size()) {
                            PayvipBean payvipBean = (PayvipBean)payvipBeanList.get(j);
                            if (this.goodsList.size() < payvipBeanList.size()) {
                                this.goodsList.add(new VipGoodsJpanel());
                            }
                            if (paysum.compareTo(new BigDecimal((int)((PayvipBean)payvipBeanList.get(j)).getPaynum())) >= 0) {
                                this.vipLvl = ((this.vipLvl > (int)payvipBean.getGrade()) ? this.vipLvl : ((int)payvipBean.getGrade()));
                            }
                            maxLevel = ((maxLevel > (int)payvipBean.getGrade()) ? maxLevel : ((int)payvipBean.getGrade()));
                            ((VipGoodsJpanel)this.goodsList.get(j)).showGoods(payvipBean);
                            if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "1", payvipBean.getGrade() + "")) {
                                ((VipGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setBtn(-1);
                                ((VipGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setIcon(CutButtonImage.getImage("inkImg/button/B29.png", -1, -1));
                            }
                            else {
                                ((VipGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setBtn(1);
                                try {
                                    ((VipGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setIcons(CutButtonImage.cuts("inkImg/button/B12.png"));
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    this.langNum = this.getLangNum(Integer.valueOf(maxLevel), paysum);
                }
                this.addGoods();
            }
        }
        else {
            this.payvipBeanList = payvipBeanList;
            LoginResult loginResul = RoleData.getRoleData().getLoginResult();
            BigDecimal paysum = loginResul.getPaysum();
            int maxLevel = 0;
            if (payvipBeanList != null) {
                if (payvipBeanList.size() == 0) {
                    for (int i = 0; i < this.goodsList.size(); ++i) {
                        ((VipGoodsJpanel)this.goodsList.get(i)).showGoods(null);
                    }
                }
                else {
                    String vipget = RoleData.getRoleData().getLoginResult().getVipget();
                    String[] split = null;
                    if (vipget != null) {
                        split = vipget.split("&&");
                    }
                    for (int j = 0; j < this.goodsList.size(); ++j) {
                        if (j > payvipBeanList.size()) {
                            ((VipGoodsJpanel)this.goodsList.get(j)).showGoods(null);
                        }
                        else if (j < payvipBeanList.size()) {
                            PayvipBean payvipBean = (PayvipBean)payvipBeanList.get(j);
                            if (this.goodsList.size() < payvipBeanList.size()) {
                                this.goodsList.add(new VipGoodsJpanel());
                            }
                            if (paysum.compareTo(new BigDecimal((int)((PayvipBean)payvipBeanList.get(j)).getPaynum())) >= 0) {
                                this.vipLvl = ((this.vipLvl > (int)payvipBean.getGrade()) ? this.vipLvl : ((int)payvipBean.getGrade()));
                            }
                            maxLevel = ((maxLevel > (int)payvipBean.getGrade()) ? maxLevel : ((int)payvipBean.getGrade()));
                            ((VipGoodsJpanel)this.goodsList.get(j)).showGoods(payvipBean);
                            if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "1", payvipBean.getGrade() + "")) {
                                ((VipGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setBtn(-1);
                                ((VipGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setIcon(CutButtonImage.getImage("inkImg/hongmu/yilingqu.png", -1, -1));
                            }
                            else {
                                ((VipGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setBtn(1);
                                try {
                                    ((VipGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setIcons(CutButtonImage.cuts("inkImg/hongmu/lingqu.png"));
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    this.langNum = this.getLangNum(Integer.valueOf(maxLevel), paysum);
                }
                this.addGoods();
            }
        }
    }
    
    public void refreshPanel() {
        this.showGoodsList(this.payvipBeanList);
    }
    
    public int getLangNum(Integer gradeInteger, BigDecimal paysum) {
        BigDecimal divide = this.getVipLevel(paysum).divide(new BigDecimal((int)gradeInteger), 3, 4);
        BigDecimal multiply = divide.multiply(new BigDecimal(587)).setScale(0, 1);
        return this.langNum = ((multiply.intValue() > 587) ? 587 : multiply.intValue());
    }
    
    public BigDecimal getVipLevel(BigDecimal paysum) {
        BigDecimal level = new BigDecimal(0);
        if (paysum.compareTo(new BigDecimal(30)) < 0) {
            level = new BigDecimal(0);
        }
        else if (paysum.compareTo(new BigDecimal(100)) < 0) {
            level = new BigDecimal(1);
        }
        else if (paysum.compareTo(new BigDecimal(500)) < 0) {
            level = new BigDecimal(2);
        }
        else if (paysum.compareTo(new BigDecimal(1000)) < 0) {
            level = new BigDecimal(3);
        }
        else if (paysum.compareTo(new BigDecimal(2000)) < 0) {
            level = new BigDecimal(4);
        }
        else if (paysum.compareTo(new BigDecimal(5000)) < 0) {
            level = new BigDecimal(5);
        }
        else if (paysum.compareTo(new BigDecimal(10000)) < 0) {
            level = new BigDecimal(6);
        }
        else if (paysum.compareTo(new BigDecimal(18000)) < 0) {
            level = new BigDecimal(7);
        }
        else if (paysum.compareTo(new BigDecimal(30000)) < 0) {
            level = new BigDecimal(8);
        }
        else if (paysum.compareTo(new BigDecimal(50000)) < 0) {
            level = new BigDecimal(9);
        }
        else if (paysum.compareTo(new BigDecimal(50000)) >= 0) {
            level = new BigDecimal(10);
        }
        return level;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconTop == null) {
                this.iconTop = CutButtonImage.getImage("inkImg/background/S4.png", -1, -1);
            }
            g.drawImage(this.iconTop.getImage(), 58, 0, 591, 116, this);
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/background/S5.png", -1, -1);
            }
            g.drawImage(this.iconBack.getImage(), 60, 130, 587, 7, this);
            if (this.iconPlan == null) {
                this.iconPlan = CutButtonImage.getImage("inkImg/background/S6.png", -1, -1);
            }
            g.drawImage(this.iconPlan.getImage(), 60, 131, this.langNum, 5, this);
            for (int i = 0; i < this.getIconVip().size(); ++i) {
                g.drawImage(((ImageIcon)this.getIconVip().get(i)).getImage(), 68 + 63 * i, 127, 30, 25, this);
            }
            g.setFont(UIUtils.TEXT_FONT2);
            g.setColor(Color.black);
            g.drawString("累计充值积分:", 225, 385);
            g.setColor(Color.red);
            g.drawString(RoleData.getRoleData().getLoginResult().getMoney().toString() + " 积分", 310, 385);
            g.setColor(Color.black);
            g.drawString("累计充值金额:", 225, 400);
            g.setColor(Color.red);
            g.drawString(RoleData.getRoleData().getLoginResult().getPaysum().toString() + " 元", 310, 400);
        }
        else {
            g.setFont(UIUtils.TEXT_FONT2);
            g.setColor(Color.green);
            g.drawString("累计充值积分:", 225, 385);
            g.setColor(Color.orange);
            g.drawString(RoleData.getRoleData().getLoginResult().getMoney().toString() + " 积分", 310, 385);
            g.setColor(Color.green);
            g.drawString("累计充值金额:", 225, 400);
            g.setColor(Color.orange);
            g.drawString(RoleData.getRoleData().getLoginResult().getPaysum().toString() + " 元", 310, 400);
        }
    }
    
    public JScrollPane getScrollPane() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.scrollPane == null) {
                (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
                this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.scrollPane.getVerticalScrollBar().setUnitIncrement(190);
                this.scrollPane.getViewport().setOpaque(false);
                this.scrollPane.setOpaque(false);
                this.scrollPane.setBounds(58, 150, 591, 190);
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
        }
        else if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(78, 15, 591, 340);
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
    
    public ImageIcon getIconTop() {
        return this.iconTop;
    }
    
    public void setIconTop(ImageIcon iconTop) {
        this.iconTop = iconTop;
    }
    
    public List<VipGoodsJpanel> getGoodsList() {
        if (this.goodsList == null) {
            this.goodsList = new ArrayList<>();
            for (int i = 0; i < 15; ++i) {
                VipGoodsJpanel vipGoodsJpanel = new VipGoodsJpanel();
                this.goodsList.add(vipGoodsJpanel);
            }
        }
        return this.goodsList;
    }
    
    public void setGoodsList(List<VipGoodsJpanel> goodsList) {
        this.goodsList = goodsList;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public ImageIcon getIconPlan() {
        return this.iconPlan;
    }
    
    public void setIconPlan(ImageIcon iconPlan) {
        this.iconPlan = iconPlan;
    }
    
    public List<ImageIcon> getIconVip() {
        if (this.iconVip == null) {
            this.iconVip = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                this.iconVip.add(CutButtonImage.getImage("img/item/S" + (i + 30) + ".png", 30, 25));
            }
        }
        return this.iconVip;
    }
    
    public void setIconVip(List<ImageIcon> iconVip) {
        this.iconVip = iconVip;
    }
}
