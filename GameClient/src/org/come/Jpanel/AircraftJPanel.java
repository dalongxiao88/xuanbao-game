package org.come.Jpanel;

import org.come.until.CutButtonImage;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import com.tool.tcp.SpriteFactory;
import com.tool.role.RoleData;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import com.tool.image.ImageMixDeal;
import org.come.until.ExpIncreaseUntil;
import org.come.entity.Fly;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import com.tool.btn.AircraftBtn;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.tool.tcpimg.RichLabel;
import com.tool.tcp.NewPart;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AircraftJPanel extends JPanel
{
    private JLabel labShowName;
    private int numExp;
    private JLabel labStage;
    private JLabel labBurn;
    private ImageIcon iconExp;
    private ImageIcon icon;
    private NewPart part;
    private JLabel labOneGoods;
    private JLabel labTwoGoods;
    private JLabel labName;
    private JLabel labflyimg;
    private JLabel labflylevel;
    private JLabel labflytili;
    private JLabel labflyexp;
    private RichLabel richPaneDetails;
    private Integer flyId;
    private JList<String> listfly;
    private DefaultListModel<String> modelfly;
    private JScrollPane paneList;
    private JScrollPane paneDetails;
    private AircraftBtn btnFight;
    private AircraftBtn btnRedo;
    private AircraftBtn btnInquire;
    private AircraftBtn btnUpgrade;
    private AircraftBtn btnAdvance;
    private AircraftBtn btnAdd;
    private String skin;
    private NewPart newPart;
    private BigDecimal se;
    
    public AircraftJPanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(513, 424));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 119);
            offBtn.setBounds(476, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            this.setPreferredSize(new Dimension(463, 448));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 119);
            offBtn.setBounds(414, 0, 23, 23);
            this.add(offBtn);
        }
        (this.labflyimg = new JLabel()).setBounds(100, 60, 200, 200);
        this.add(this.labflyimg);
        Font font = new Font("微软雅黑", 1, 12);
        this.getLabBurn();
        this.getLabExp();
        this.getLabLvl();
        this.getLabName();
        this.getLabShowName();
        this.getLabStage();
        this.getBtnAdd();
        this.getBtnAdvance();
        this.getBtnFight();
        this.getBtnInquire();
        this.getBtnRedo();
        this.getBtnUpgrade();
        this.getLabOneGoods();
        this.getLabTwoGoods();
        this.modelfly = new DefaultListModel<>();
        (this.listfly = new JList<String>() {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.listfly.setSelectionForeground(Color.GREEN);
        this.listfly.setForeground(Color.GREEN);
        this.listfly.setFont(new Font("微软雅黑", 1, 14));
        this.listfly.setBackground(UIUtils.Color_BACK);
        this.listfly.setModel(this.modelfly);
        this.listfly.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    if (AircraftJPanel.this.listfly.getSelectedIndex() >= 0) {
                        Fly fly = (Fly)ZhuJpanel.getListFly().get(AircraftJPanel.this.listfly.getSelectedIndex());
                        ExpIncreaseUntil.ShouFlyValue(fly);
                        if ((int)fly.getFlytid() == ImageMixDeal.userimg.getRoleShow().getFly_id()) {
                            AircraftJPanel.this.btnFight.setText("待机");
                        }
                        else {
                            AircraftJPanel.this.btnFight.setText("飞行");
                        }
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        (this.paneList = new JScrollPane(this.listfly)).setVerticalScrollBarPolicy(22);
        this.paneList.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.paneList.getVerticalScrollBar().setUnitIncrement(20);
        this.paneList.getViewport().setOpaque(false);
        this.paneList.setOpaque(false);
        if (MyIsif.getStyle().equals("水墨")) {
            this.paneList.setBounds(48, 320, 177, 90);
        }
        else {
            this.paneList.setBounds(20, 340, 228, 87);
        }
        this.paneList.setBorder(BorderFactory.createEmptyBorder());
        this.paneList.setHorizontalScrollBarPolicy(31);
        this.add(this.paneList);
    }
    
    public void changskin(RoleData roleData, String skin) {
        (this.part = SpriteFactory.createPart(RoleData.getRoleData().getLoginResult().getSpecies_id().longValue(), 2, 1, null)).setFly(skin, 2, Boolean.valueOf((int)roleData.getLoginResult().getFly_id() > 0));
        this.part.setFlyShadow(skin + "1", 2);
    }
    
    public void drawGoodsNum(Graphics g) {
        Goodstable[] goodslist = GoodsListFromServerUntil.getGoodslist();
        int length = goodslist.length;
        if (MyIsif.getStyle().equals("水墨")) {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.red);
            for (int i = 0; i < length; ++i) {
                if (goodslist[i] != null && (long)goodslist[i].getType() == 1102L) {
                    g.drawString(String.valueOf(goodslist[i].getUsetime()), 340, 182);
                }
                if (goodslist[i] != null && (long)goodslist[i].getType() == 1101L) {
                    g.drawString(String.valueOf(goodslist[i].getUsetime()), 406, 182);
                }
            }
        }
        else {
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.white);
            for (int i = 0; i < length; ++i) {
                if (goodslist[i] != null && (long)goodslist[i].getType() == 1102L) {
                    g.drawString(String.valueOf(goodslist[i].getUsetime()), 290, 251);
                }
                if (goodslist[i] != null && (long)goodslist[i].getType() == 1101L) {
                    g.drawString(String.valueOf(goodslist[i].getUsetime()), 371, 251);
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background1/B227.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            if (this.iconExp == null) {
                this.iconExp = CutButtonImage.getImage("inkImg/button/66.png", 108, 15);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/hongmu/H206.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            if (this.iconExp == null) {
                this.iconExp = CutButtonImage.getImage("inkImg/button/66.png", 108, 15);
            }
        }
        g.drawImage(this.iconExp.getImage(), 321, 108, this.numExp, 15, null);
        if (this.part != null) {
            this.part.draw(g, 159, 182, 0, ImageMixDeal.userimg.getTime());
        }
        if (this.listfly.getSelectedIndex() != -1) {
            int index = this.listfly.getSelectedIndex();
            if (ZhuJpanel.getListFly().size() > index) {
                Fly fly = (Fly)ZhuJpanel.getListFly().get(index);
                if (this.newPart == null) {
                    this.flyId = fly.getFlytid();
                    this.se = ImageMixDeal.userimg.getRoleShow().getSpecies_id();
                    (this.newPart = SpriteFactory.createPart(RoleData.getRoleData().getLoginResult().getSpecies_id().longValue(), 2, 1, null)).setFly(this.skin, 2, Boolean.valueOf(true));
                    this.newPart.setFlyShadow(this.skin + "1", 2);
                    this.getLabName().setText(fly.getFlyname() + "(" + getJieshu((int)fly.getFlystate()) + "阶)");
                }
                else if (this.flyId != fly.getFlytid()) {
                    this.flyId = fly.getFlytid();
                    this.se = ImageMixDeal.userimg.getRoleShow().getSpecies_id();
                    (this.newPart = SpriteFactory.createPart(RoleData.getRoleData().getLoginResult().getSpecies_id().longValue(), 2, 1, null)).setFly(this.skin, 2, Boolean.valueOf(true));
                    this.newPart.setFlyShadow(this.skin + "1", 2);
                    this.getLabName().setText(fly.getFlyname() + "(" + getJieshu((int)fly.getFlystate()) + "阶)");
                }
                if (MyIsif.getStyle().equals("水墨")) {
                    this.newPart.draw(g, 185, 165, 0, ImageMixDeal.userimg.getTime());
                }
                else {
                    this.newPart.draw(g, 130, 170, 0, ImageMixDeal.userimg.getTime());
                }
            }
        }
    }
    
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        this.drawGoodsNum(g);
    }
    
    public void setPaneList(JScrollPane paneList) {
        this.paneList = paneList;
    }
    
    public JScrollPane getPaneDetails() {
        if (this.paneDetails == null) {
            (this.paneDetails = new JScrollPane(this.getRichPaneDetails())).setVerticalScrollBarPolicy(22);
            this.paneDetails.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.paneDetails.getVerticalScrollBar().setUnitIncrement(20);
            this.paneDetails.getViewport().setOpaque(false);
            this.paneDetails.setOpaque(false);
            this.paneDetails.setBounds(239, 327, 206, 63);
            this.paneDetails.setBorder(BorderFactory.createEmptyBorder());
            this.paneDetails.setHorizontalScrollBarPolicy(31);
            this.add(this.paneDetails);
        }
        return this.paneDetails;
    }
    
    public void setPaneDetails(JScrollPane paneDetails) {
        this.paneDetails = paneDetails;
    }
    
    public JLabel getLabName() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labName == null) {
                (this.labName = new JLabel()).setBounds(324, 63, 148, 16);
                this.labName.setForeground(Color.white);
                this.labName.setFont(UIUtils.TEXT_FONT1);
                this.add(this.labName);
            }
        }
        else if (this.labName == null) {
            (this.labName = new JLabel()).setBounds(299, 58, 103, 16);
            this.labName.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
            this.labName.setFont(UIUtils.TEXT_FONTZS);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public JLabel getLabLvl() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labflylevel == null) {
                (this.labflylevel = new JLabel()).setBounds(372, 91, 70, 16);
                this.labflylevel.setForeground(Color.white);
                this.labflylevel.setFont(UIUtils.TEXT_FONT1);
                this.add(this.labflylevel);
            }
        }
        else if (this.labflylevel == null) {
            (this.labflylevel = new JLabel()).setBounds(299, 88, 103, 16);
            this.labflylevel.setForeground(Color.white);
            this.labflylevel.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labflylevel);
        }
        return this.labflylevel;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labflylevel = labLvl;
    }
    
    public JLabel getLabflytili() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labflytili == null) {
                (this.labflytili = new JLabel()).setBounds(380, 235, 75, 16);
                this.labflytili.setForeground(Color.white);
                this.labflytili.setFont(UIUtils.TEXT_FONT1);
                this.add(this.labflytili);
            }
        }
        else if (this.labflytili == null) {
            (this.labflytili = new JLabel()).setBounds(121, 269, 121, 16);
            this.labflytili.setForeground(Color.white);
            this.labflytili.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labflytili);
        }
        return this.labflytili;
    }
    
    public void setLabflytili(JLabel labflytili) {
        this.labflytili = labflytili;
    }
    
    public JLabel getLabStage() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labStage == null) {
                (this.labStage = new JLabel()).setBounds(372, 119, 70, 16);
                this.labStage.setForeground(Color.white);
                this.labStage.setFont(UIUtils.TEXT_FONT1);
                this.add(this.labStage);
            }
        }
        else if (this.labStage == null) {
            (this.labStage = new JLabel()).setBounds(299, 115, 103, 16);
            this.labStage.setForeground(Color.white);
            this.labStage.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labStage);
        }
        return this.labStage;
    }
    
    public void setLabStage(JLabel labStage) {
        this.labStage = labStage;
    }
    
    public JLabel getLabExp() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labflyexp == null) {
                (this.labflyexp = new JLabel("0")).setBounds(372, 147, 100, 16);
                this.labflyexp.setForeground(Color.white);
                this.labflyexp.setFont(UIUtils.TEXT_FONT1);
                this.add(this.labflyexp);
            }
        }
        else if (this.labflyexp == null) {
            (this.labflyexp = new JLabel("0")).setBounds(299, 145, 103, 16);
            this.labflyexp.setForeground(Color.white);
            this.labflyexp.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labflyexp);
        }
        return this.labflyexp;
    }
    
    public void setLabExp(JLabel labExp) {
        this.labflyexp = labExp;
    }
    
    public JLabel getLabBurn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labBurn == null) {
                (this.labBurn = new JLabel()).setBounds(380, 327, 75, 16);
                this.labBurn.setForeground(Color.white);
                this.labBurn.setFont(UIUtils.TEXT_FONT1);
                this.add(this.labBurn);
            }
        }
        else if (this.labBurn == null) {
            (this.labBurn = new JLabel()).setBounds(121, 269, 121, 16);
            this.labBurn.setForeground(Color.white);
            this.labBurn.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labBurn);
        }
        return this.labBurn;
    }
    
    public void setLabBurn(JLabel labBurn) {
        this.labBurn = labBurn;
    }
    
    public JLabel getLabShowName() {
        if (MyIsif.getStyle().equals("水墨") && this.labShowName == null) {
            (this.labShowName = new JLabel("飞行器", 0)).setBounds(100, 298, 60, 20);
            this.labShowName.setForeground(Color.white);
            this.labShowName.setFont(UIUtils.TEXT_HY19);
            this.add(this.labShowName);
        }
        return this.labShowName;
    }
    
    public void setLabShowName(JLabel labShowName) {
        this.labShowName = labShowName;
    }
    
    public AircraftBtn getBtnUpgrade() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnUpgrade == null) {
                (this.btnUpgrade = new AircraftBtn("inkImg/button1/B30.png", 1, "升级", UIUtils.TEXT_FONT, UIUtils.COLOR_BTNTEXT, 0, this)).setBounds(438, 91, 34, 17);
                this.add(this.btnUpgrade);
            }
        }
        else if (this.btnUpgrade == null) {
            (this.btnUpgrade = new AircraftBtn("inkImg/hongmu/B30h.png", 1, "升级", UIUtils.TEXT_FONT, UIUtils.COLOR_BTNPUTONG, 0, this)).setBounds(370, 87, 34, 17);
            this.add(this.btnUpgrade);
        }
        return this.btnUpgrade;
    }
    
    public void setBtnUpgrade(AircraftBtn btnUpgrade) {
        this.btnUpgrade = btnUpgrade;
    }
    
    public AircraftBtn getBtnAdvance() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnAdvance == null) {
                (this.btnAdvance = new AircraftBtn("inkImg/button1/B30.png", 1, "进阶", UIUtils.TEXT_FONT, UIUtils.COLOR_BTNTEXT, 1, this)).setBounds(438, 119, 34, 17);
                this.add(this.btnAdvance);
            }
        }
        else if (this.btnAdvance == null) {
            (this.btnAdvance = new AircraftBtn("inkImg/hongmu/B30h.png", 1, "进阶", UIUtils.TEXT_FONT, UIUtils.COLOR_BTNPUTONG, 1, this)).setBounds(370, 115, 34, 17);
            this.add(this.btnAdvance);
        }
        return this.btnAdvance;
    }
    
    public void setBtnAdvance(AircraftBtn btnAdvance) {
        this.btnAdvance = btnAdvance;
    }
    
    public AircraftBtn getBtnAdd() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnAdd == null) {
                (this.btnAdd = new AircraftBtn("inkImg/button/16.png", 1, 2, this)).setBounds(455, 235, 18, 18);
                this.add(this.btnAdd);
            }
        }
        else if (this.btnAdd == null) {
            (this.btnAdd = new AircraftBtn("img/xy2uiimg/55_png.button.btn_12.png", 1, 2, this)).setBounds(206, 269, 18, 18);
            this.add(this.btnAdd);
        }
        return this.btnAdd;
    }
    
    public void setBtnAdd(AircraftBtn btnAdd) {
        this.btnAdd = btnAdd;
    }
    
    public AircraftBtn getBtnFight() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnFight == null) {
                (this.btnFight = new AircraftBtn("inkImg/button1/B20.png", 1, "飞行", UIUtils.TEXT_HY16, UIUtils.COLOR_BLACK, 3, this)).setBounds(268, 380, 59, 24);
                this.add(this.btnFight);
            }
        }
        else if (this.btnFight == null) {
            (this.btnFight = new AircraftBtn("inkImg/hongmu/50x50.png", 1, "飞行", UIUtils.TEXT_HY16, UIUtils.COLOR_BTNPUTONG, 3, this)).setBounds(270, 380, 60, 26);
            this.add(this.btnFight);
        }
        return this.btnFight;
    }
    
    public void setBtnFight(AircraftBtn btnFight) {
        this.btnFight = btnFight;
    }
    
    public AircraftBtn getBtnRedo() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnRedo == null) {
                (this.btnRedo = new AircraftBtn("inkImg/button1/B20.png", 1, "重洗", UIUtils.TEXT_HY16, UIUtils.COLOR_BLACK, 4, this)).setBounds(320, 380, 59, 24);
            }
        }
        else if (this.btnRedo == null) {
            (this.btnRedo = new AircraftBtn("inkImg/hongmu/50x50.png", 1, "重洗", UIUtils.TEXT_HY16, UIUtils.COLOR_BTNPUTONG, 4, this)).setBounds(320, 397, 60, 26);
        }
        return this.btnRedo;
    }
    
    public void setBtnRedo(AircraftBtn btnRedo) {
        this.btnRedo = btnRedo;
    }
    
    public AircraftBtn getBtnInquire() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnInquire == null) {
                (this.btnInquire = new AircraftBtn("inkImg/button1/B20.png", 1, "丢弃", UIUtils.TEXT_HY16, UIUtils.COLOR_BLACK, 5, this)).setBounds(382, 380, 59, 24);
                this.add(this.btnInquire);
            }
        }
        else if (this.btnInquire == null) {
            (this.btnInquire = new AircraftBtn("inkImg/hongmu/50x50.png", 1, "丢弃", UIUtils.TEXT_HY16, UIUtils.COLOR_BTNPUTONG, 5, this)).setBounds(360, 380, 60, 26);
            this.add(this.btnInquire);
        }
        return this.btnInquire;
    }
    
    public void setBtnInquire(AircraftBtn btnInquire) {
        this.btnInquire = btnInquire;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public RichLabel getRichPaneDetails() {
        if (this.richPaneDetails == null) {
            this.richPaneDetails = new RichLabel("", UIUtils.TEXT_FONT);
        }
        return this.richPaneDetails;
    }
    
    public void setRichPaneDetails(RichLabel richPaneDetails) {
        this.richPaneDetails = richPaneDetails;
    }
    
    public Integer getFlyId() {
        return this.flyId;
    }
    
    public void setFlyId(Integer flyId) {
        this.flyId = flyId;
    }
    
    public NewPart getPart() {
        return this.part;
    }
    
    public void setPart(NewPart part) {
        this.part = part;
    }
    
    public JLabel getLabOneGoods() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labOneGoods == null) {
                (this.labOneGoods = new JLabel()).setBounds(337, 172, 55, 53);
                this.labOneGoods.setIcon(GoodsListFromServerUntil.imgpathAdaptive("99102", 53, 53));
                this.add(this.labOneGoods);
            }
        }
        else if (this.labOneGoods == null) {
            (this.labOneGoods = new JLabel()).setBounds(270, 172, 55, 53);
            this.labOneGoods.setIcon(GoodsListFromServerUntil.imgpathAdaptive("99102", 53, 53));
            this.add(this.labOneGoods);
        }
        return this.labOneGoods;
    }
    
    public void setLabOneGoods(JLabel labOneGoods) {
        this.labOneGoods = labOneGoods;
    }
    
    public JLabel getLabTwoGoods() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.labTwoGoods == null) {
                (this.labTwoGoods = new JLabel()).setBounds(402, 172, 55, 53);
                this.labTwoGoods.setIcon(GoodsListFromServerUntil.imgpathAdaptive("99101", 53, 53));
                this.add(this.labTwoGoods);
            }
        }
        else if (this.labTwoGoods == null) {
            (this.labTwoGoods = new JLabel()).setBounds(355, 172, 55, 53);
            this.labTwoGoods.setIcon(GoodsListFromServerUntil.imgpathAdaptive("99101", 53, 53));
            this.add(this.labTwoGoods);
        }
        return this.labTwoGoods;
    }
    
    public void setLabTwoGoods(JLabel labTwoGoods) {
        this.labTwoGoods = labTwoGoods;
    }
    
    public DefaultListModel<String> getmodelfly() {
        return this.modelfly;
    }
    
    public void setmodelfly(DefaultListModel<String> modelfly) {
        this.modelfly = modelfly;
    }
    
    public JList<String> getlistfly() {
        return this.listfly;
    }
    
    public void setListfly(JList<String> listfly) {
        this.listfly = listfly;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public static String getJieshu(int mun) {
        String l = "一";
        if (mun == 2) {
            l = "二";
        }
        if (mun == 3) {
            l = "三";
        }
        if (mun == 4) {
            l = "四";
        }
        if (mun == 5) {
            l = "五";
        }
        return l;
    }
}
