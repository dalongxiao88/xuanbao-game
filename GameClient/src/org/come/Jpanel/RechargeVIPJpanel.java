package org.come.Jpanel;

import org.come.until.UserMessUntil;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.mouslisten.RechargeVIPGoodsMouslisten;
import java.util.ArrayList;
import org.come.bean.LoginResult;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import java.awt.Color;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.tool.btn.FormsOnOffBtn;
import org.come.until.CutButtonImage;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.come.bean.PayvipBean;
import javax.swing.JScrollPane;
import org.come.entity.Goodstable;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RechargeVIPJpanel extends JPanel
{
    private JLabel labtext1;
    private JLabel labtext2;
    private Map<String, String> map;
    public static List<Goodstable> GoodstableList;
    private static Goodstable goodstable;
    private List<RechargeVIPGoodsJpanel> goodsList;
    private JScrollPane scrollPane;
    int vipLvl;
    private List<PayvipBean> payvipBeanList;
    private int langNum;
    private ImageIcon iconTop;
    private ImageIcon iconBack;
    private ImageIcon iconPlan;
    private List<ImageIcon> iconVip;
    private static ImageIcon iconVipk;
    private int xz;
    private int sxz;
    private static RechargeVIPGoodsJpanel.Mlistener mlistener;
    private JLabel laba;
    private JLabel labb;
    private JLabel labc;
    private JLabel labd;
    private JLabel[] GoodsListLabel;
    private ImageIcon icon;
    private ImageIcon icon1;
    private ImageIcon icon3;
    
    public RechargeVIPJpanel() {
        this.map = new HashMap<>();
        this.vipLvl = 0;
        this.langNum = 0;
        this.xz = -1;
        this.sxz = -1;
        this.GoodsListLabel = new JLabel[7];
        this.icon = CutButtonImage.getImage("inkImg/background/首冲底图.png", 574, 356);
        this.icon1 = CutButtonImage.getImage("inkImg/background/日历底图1.png", 474, 319);
        this.icon3 = CutButtonImage.getImage("inkImg/background/gift-item-border.png", 67, 52);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/background/gift-close-btn.png", 1, 3002);
        offBtn.setBounds(546, 5, 18, 18);
        this.add(offBtn);
        this.setPreferredSize(new Dimension(574, 356));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setOpaque(false);
        this.getScrollPane();
        this.getGoodsList();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.icon = CutButtonImage.getImage("inkImg/background/首冲底图.png", 574, 356);
        g.drawImage(this.icon.getImage(), 0, 0, 574, 356, this);
        g.setFont(UIUtils.TEXT_FONT14);
        g.setColor(Color.black);
        g.drawString("可使用积分:", 65, 338);
        g.setColor(Color.red);
        g.drawString(RoleData.getRoleData().getLoginResult().getMoney().toString() + " 积分", 145, 338);
        g.setColor(Color.black);
        g.drawString("累计获得:", 295, 338);
        g.setColor(Color.red);
        g.drawString(RoleData.getRoleData().getLoginResult().getPaysum().toString() + " 积分", 365, 338);
        if (RechargeVIPJpanel.GoodstableList != null && RechargeVIPJpanel.GoodstableList.size() > 0) {
            for (int i = 0; i < RechargeVIPJpanel.GoodstableList.size(); ++i) {
                if (RechargeVIPJpanel.GoodstableList.get(i) != null) {
                    g.drawImage(this.icon3.getImage(), 40 + i * 65, 240, 60, 60, this);
                    this.icon1 = CutButtonImage.getImage("img/item/" + ((Goodstable)RechargeVIPJpanel.GoodstableList.get(i)).getSkin() + ".png", -1, -1);
                    g.drawImage(this.icon1.getImage(), 40 + i * 65, 240, 60, 60, this);
                }
            }
        }
    }
    
    public void refreshPanel() {
        this.showGoodsList(this.payvipBeanList);
    }
    
    public void getShop() {
        String sendmes = Agreement.getAgreement().GetvipgradepackAgreement();
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showGoodsList(List<PayvipBean> payvipBeanList) {
        this.payvipBeanList = payvipBeanList;
        LoginResult loginResul = RoleData.getRoleData().getLoginResult();
        BigDecimal paysum = loginResul.getPaysum();
        int maxLevel = 0;
        if (payvipBeanList.size() == 0) {
            for (int i = 0; i < this.goodsList.size(); ++i) {
                ((RechargeVIPGoodsJpanel)this.goodsList.get(i)).showGoods(null);
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
                    ((RechargeVIPGoodsJpanel)this.goodsList.get(j)).showGoods(null);
                }
                else if (j < payvipBeanList.size()) {
                    PayvipBean payvipBean = (PayvipBean)payvipBeanList.get(j);
                    if (this.goodsList.size() < payvipBeanList.size()) {
                        this.goodsList.add(new RechargeVIPGoodsJpanel());
                    }
                    if (paysum.compareTo(new BigDecimal((int)((PayvipBean)payvipBeanList.get(j)).getPaynum())) >= 0) {
                        this.vipLvl = ((this.vipLvl > (int)payvipBean.getGrade()) ? this.vipLvl : ((int)payvipBean.getGrade()));
                    }
                    maxLevel = ((maxLevel > (int)payvipBean.getGrade()) ? maxLevel : ((int)payvipBean.getGrade()));
                    ((RechargeVIPGoodsJpanel)this.goodsList.get(j)).showGoods(payvipBean);
                    if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "1", payvipBean.getGrade() + "")) {
                        ((RechargeVIPGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setBtn(-1);
                        ((RechargeVIPGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setIcon(CutButtonImage.getImage("inkImg/background/gift-received-btn.png", -1, -1));
                    }
                    else {
                        ((RechargeVIPGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setBtn(1);
                        try {
                            ((RechargeVIPGoodsJpanel)this.goodsList.get(j)).getVipShopBtn().setIcons(CutButtonImage.cuts("inkImg/background/gift-receive-btn.png"));
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
    
    public void addGoods() {
        JPanel view = (JPanel)this.scrollPane.getViewport().getView();
        view.removeAll();
        view.repaint();
        int num = 0;
        for (int i = 0; i < this.goodsList.size(); ++i) {
            RechargeVIPGoodsJpanel rechargeVIPGoodsJpanel = (RechargeVIPGoodsJpanel)this.goodsList.get(i);
            if (rechargeVIPGoodsJpanel.getPayvipBean() != null) {
                rechargeVIPGoodsJpanel.setBounds(0 + num % 4 * 115, num / 4 * 155, 136, 155);
                ++num;
                view.add(rechargeVIPGoodsJpanel);
            }
        }
        view.setPreferredSize(new Dimension(565, 155 * ((num % 4 == 0) ? (num / 4) : (num / 4 + 1))));
        this.scrollPane.updateUI();
        this.scrollPane.invalidate();
        this.scrollPane.validate();
        this.scrollPane.repaint();
    }
    
    public List<RechargeVIPGoodsJpanel> getGoodsList() {
        if (this.goodsList == null) {
            this.goodsList = new ArrayList<>();
            for (int i = 0; i < 15; ++i) {
                RechargeVIPGoodsJpanel rechargeVIPGoodsJpanel = new RechargeVIPGoodsJpanel();
                this.goodsList.add(rechargeVIPGoodsJpanel);
            }
            for (int i = 0; i < 7; ++i) {
                (this.GoodsListLabel[i] = new JLabel()).addMouseListener(new RechargeVIPGoodsMouslisten(i));
                this.GoodsListLabel[i].setBounds(40 + i * 65, 240, 60, 60);
                this.add(this.GoodsListLabel[i]);
            }
        }
        return this.goodsList;
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(155);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(58, 55, 490, 155);
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
    
    public static void paintPeopleGoods(PayvipBean payvipBean) {
        RechargeVIPJpanel.GoodstableList = new ArrayList<>();
        String str = payvipBean.getGivegoods();
        String[] goods = str.split("&");
        if (goods.length > 0) {
            for (int i = 0; i < goods.length - 1; ++i) {
                String goodId = goods[i + 1].split("\\$")[0];
                Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(goodId));
                showGoods(goodstable);
                RechargeVIPJpanel.GoodstableList.add(goodstable);
            }
        }
    }
    
    public static void showGoods(Goodstable goodstables) {
        RechargeVIPJpanel.goodstable = goodstables;
        if (RechargeVIPJpanel.goodstable != null) {
            getIconVipK().setImage(CutButtonImage.getImage("img/item/" + goodstables.getSkin() + ".png", -1, -1).getImage());
        }
    }
    
    public void upSXz(int v) {
        this.sxz = v;
    }
    
    public void upXz(int v) {
        this.xz = v;
    }
    
    public Map<String, String> getMap() {
        return this.map;
    }
    
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    
    public static ImageIcon getIconVipK() {
        if (RechargeVIPJpanel.iconVipk == null) {
            RechargeVIPJpanel.iconVipk = new ImageIcon();
        }
        return RechargeVIPJpanel.iconVipk;
    }
    
    public void setIconVip(ImageIcon iconVipk) {
        RechargeVIPJpanel.iconVipk = iconVipk;
    }
    
    public RechargeVIPGoodsJpanel.Mlistener getMlistener() {
        return RechargeVIPJpanel.mlistener;
    }
    
    public void setMlistener(RechargeVIPGoodsJpanel.Mlistener mlistener) {
        RechargeVIPJpanel.mlistener = mlistener;
    }
    
    static {
        RechargeVIPJpanel.GoodstableList = new ArrayList<>();
    }
}
