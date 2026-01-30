package org.cbg.panel;

import java.awt.Graphics;
import org.cbg.mouslisten.CBGmoveMouslisten;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.cbg.frame.TraslationCommodityJFrame;
import org.cbg.frame.TrslationMainJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.cbg.entity.Collection;
import org.cbg.entity.Salegoods;
import org.cbg.btn.CBGDahuabiBuyBtn;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationWantBuyDaojuModelJpanel extends JPanel
{
    private JLabel zhanshidikuang;
    private JLabel tupian;
    private JLabel dahuabixia;
    private JLabel danjia;
    private JLabel price;
    private TrslationBtn shoucang;
    private CBGDahuabiBuyBtn buy;
    private int stateOrNo;
    private int gongshiqiBiaoshi;
    private Salegoods salegoods;
    private Collection collection;
    private ImageIcon icon1;
    
    public TraslationWantBuyDaojuModelJpanel() {
        this.stateOrNo = 1;
        this.gongshiqiBiaoshi = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel("四千八百万");
            this.danjia = new JLabel("48.6万两/元");
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel("￥100.0");
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.shoucang.setBounds(5, 5, 28, 28);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabixia.setBounds(100, 5, 150, 24);
            this.price.setBounds(280, 0, 125, 40);
            this.danjia.setBounds(380, 0, 125, 40);
            (this.buy = new CBGDahuabiBuyBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, 2, "预购", this)).setBounds(470, 10, 60, 26);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1) {
                        try {
                            if (TraslationWantBuyDaojuModelJpanel.this.stateOrNo == 1) {
                                TraslationWantBuyDaojuModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                TraslationWantBuyDaojuModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            });
        }
        else {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel("四千八百万");
            this.danjia = new JLabel("48.6万两/元");
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel("￥100.0");
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.shoucang.setBounds(5, 5, 28, 28);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabixia.setBounds(100, 5, 150, 24);
            this.price.setBounds(280, 0, 125, 40);
            this.danjia.setBounds(380, 0, 125, 40);
            (this.buy = new CBGDahuabiBuyBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, 2, "预购", this)).setBounds(470, 10, 60, 26);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1) {
                        try {
                            if (TraslationWantBuyDaojuModelJpanel.this.stateOrNo == 1) {
                                TraslationWantBuyDaojuModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                TraslationWantBuyDaojuModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            });
        }
    }
    
    public TraslationWantBuyDaojuModelJpanel(Salegoods salegoods) {
        this.stateOrNo = 1;
        this.gongshiqiBiaoshi = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.salegoods = salegoods;
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel(salegoods.getSalename());
            String remainingTime = TrslationMainJframe.remainingTime(salegoods.getUptime(), Integer.valueOf(1));
            this.gongshiqiBiaoshi = 0;
            this.buy = new CBGDahuabiBuyBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, 2, "预购", this);
            if (remainingTime.equals("已下架")) {
                this.buy.setText("购买");
                remainingTime = TrslationMainJframe.remainingTime(salegoods.getUptime(), Integer.valueOf(168));
                this.gongshiqiBiaoshi = 1;
            }
            this.danjia = new JLabel(remainingTime);
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            if (salegoods.getSaleprice() != null) {
                this.price.setText(salegoods.getSaleprice() + "");
            }
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            if (salegoods.getSaleskin() != null) {
                ImageIcon cbg = CutButtonImage.getCBG((int)salegoods.getSaletype(), salegoods.getSaleskin(), 39, 39);
                this.tupian.setIcon(cbg);
            }
            this.shoucang.setBounds(5, 5, 28, 28);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabixia.setBounds(100, 5, 150, 24);
            this.price.setBounds(280, 0, 150, 40);
            this.danjia.setBounds(350, 0, 150, 40);
            if (salegoods.getRoleid() != null) {
                this.price.setName(salegoods.getRoleid().toString());
            }
            if (salegoods.getSaleid() != null) {
                this.tupian.setName(salegoods.getSaleid().toString());
            }
            if (salegoods.getOtherid() != null) {
                this.dahuabixia.setName(salegoods.getOtherid().toString());
            }
            this.buy.setName(salegoods.getSaletype().toString());
            if (salegoods.getBuyrole() != null) {
                this.danjia.setName(salegoods.getBuyrole().toString());
            }
            this.buy.setBounds(470, 10, 60, 26);
            this.buy.setBorder(null);
            this.buy.setOpaque(false);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TrslationMainJframe.getTrslationMainJframe().setShoucangBtn(e.getSource());
                    TrslationBtn sss = (TrslationBtn)TrslationMainJframe.getTrslationMainJframe().getShoucangBtn();
                    if (e.getButton() == 1) {
                        try {
                            if (TraslationWantBuyDaojuModelJpanel.this.stateOrNo == 1) {
                                sss.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                sss.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (TraslationWantBuyDaojuModelJpanel.this.tupian.getName().equals(TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getName())) {
                            TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucang().setIcons(TraslationWantBuyDaojuModelJpanel.this.shoucang.getIcons());
                        }
                    }
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationWantBuyDaojuModelJpanel.this.tupian.getName());
                    SendMessageUntil.toServer(sendmes);
                    if (TrslationMainJframe.getTrslationMainJframe().getShoucangWupinList().contains(new BigDecimal(TraslationWantBuyDaojuModelJpanel.this.tupian.getName()))) {
                        TrslationMainJframe.getTrslationMainJframe().getShoucangWupinList().remove(new BigDecimal(TraslationWantBuyDaojuModelJpanel.this.tupian.getName()));
                    }
                    else {
                        TrslationMainJframe.getTrslationMainJframe().getShoucangWupinList().add(new BigDecimal(TraslationWantBuyDaojuModelJpanel.this.tupian.getName()));
                    }
                }
            });
            this.tupian.addMouseListener(new CBGmoveMouslisten(salegoods.getSaletype(), salegoods.getOtherid()));
        }
        else {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.salegoods = salegoods;
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel(salegoods.getSalename());
            String remainingTime = TrslationMainJframe.remainingTime(salegoods.getUptime(), Integer.valueOf(1));
            this.gongshiqiBiaoshi = 0;
            this.buy = new CBGDahuabiBuyBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, 2, "预购", this);
            if (remainingTime.equals("已下架")) {
                this.buy.setText("购买");
                remainingTime = TrslationMainJframe.remainingTime(salegoods.getUptime(), Integer.valueOf(168));
                this.gongshiqiBiaoshi = 1;
            }
            this.danjia = new JLabel(remainingTime);
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            if (salegoods.getSaleprice() != null) {
                this.price.setText(salegoods.getSaleprice() + "");
            }
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            if (salegoods.getSaleskin() != null) {
                ImageIcon cbg = CutButtonImage.getCBG((int)salegoods.getSaletype(), salegoods.getSaleskin(), 39, 39);
                this.tupian.setIcon(cbg);
            }
            this.shoucang.setBounds(5, 5, 28, 28);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabixia.setBounds(100, 5, 150, 24);
            this.price.setBounds(280, 0, 150, 40);
            this.danjia.setBounds(350, 0, 150, 40);
            if (salegoods.getRoleid() != null) {
                this.price.setName(salegoods.getRoleid().toString());
            }
            if (salegoods.getSaleid() != null) {
                this.tupian.setName(salegoods.getSaleid().toString());
            }
            if (salegoods.getOtherid() != null) {
                this.dahuabixia.setName(salegoods.getOtherid().toString());
            }
            this.buy.setName(salegoods.getSaletype().toString());
            if (salegoods.getBuyrole() != null) {
                this.danjia.setName(salegoods.getBuyrole().toString());
            }
            this.buy.setBounds(470, 10, 60, 26);
            this.buy.setBorder(null);
            this.buy.setOpaque(false);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TrslationMainJframe.getTrslationMainJframe().setShoucangBtn(e.getSource());
                    TrslationBtn sss = (TrslationBtn)TrslationMainJframe.getTrslationMainJframe().getShoucangBtn();
                    if (e.getButton() == 1) {
                        try {
                            if (TraslationWantBuyDaojuModelJpanel.this.stateOrNo == 1) {
                                sss.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                sss.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (TraslationWantBuyDaojuModelJpanel.this.tupian.getName().equals(TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getName())) {
                            TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucang().setIcons(TraslationWantBuyDaojuModelJpanel.this.shoucang.getIcons());
                        }
                    }
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationWantBuyDaojuModelJpanel.this.tupian.getName());
                    SendMessageUntil.toServer(sendmes);
                    if (TrslationMainJframe.getTrslationMainJframe().getShoucangWupinList().contains(new BigDecimal(TraslationWantBuyDaojuModelJpanel.this.tupian.getName()))) {
                        TrslationMainJframe.getTrslationMainJframe().getShoucangWupinList().remove(new BigDecimal(TraslationWantBuyDaojuModelJpanel.this.tupian.getName()));
                    }
                    else {
                        TrslationMainJframe.getTrslationMainJframe().getShoucangWupinList().add(new BigDecimal(TraslationWantBuyDaojuModelJpanel.this.tupian.getName()));
                    }
                }
            });
            this.tupian.addMouseListener(new CBGmoveMouslisten(salegoods.getSaletype(), salegoods.getOtherid()));
        }
    }
    
    public TraslationWantBuyDaojuModelJpanel(Collection collection) {
        this.stateOrNo = 1;
        this.gongshiqiBiaoshi = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.collection = collection;
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel(collection.getSalename());
            String remainingTime = TrslationMainJframe.remainingTime(collection.getUptime(), Integer.valueOf(1));
            this.gongshiqiBiaoshi = 0;
            this.buy = new CBGDahuabiBuyBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, 2, "预购", this);
            if (remainingTime.equals("已下架")) {
                this.buy.setText("购买");
                remainingTime = TrslationMainJframe.remainingTime(collection.getUptime(), Integer.valueOf(168));
                this.gongshiqiBiaoshi = 1;
            }
            this.danjia = new JLabel(remainingTime);
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            if (collection.getSaleprice() != null) {
                this.price.setText(collection.getSaleprice() + "");
            }
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            if (collection.getSaleskin() != null) {
                ImageIcon cbg = CutButtonImage.getCBG((int)collection.getSaletype(), collection.getSaleskin(), 39, 39);
                this.tupian.setIcon(cbg);
            }
            try {
                this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            this.stateOrNo = 0;
            this.shoucang.setBounds(5, 5, 28, 28);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabixia.setBounds(100, 5, 150, 24);
            this.price.setBounds(280, 0, 150, 40);
            this.danjia.setBounds(350, 0, 150, 40);
            if (collection.getRoleid() != null) {
                this.price.setName(collection.getRoleid().toString());
            }
            if (collection.getSaleid() != null) {
                this.tupian.setName(collection.getSaleid().toString());
            }
            if (collection.getColid() != null) {
                this.dahuabixia.setName(collection.getColid().toString());
            }
            this.buy.setName(collection.getColid() + "");
            this.buy.setBounds(470, 10, 65, 31);
            this.buy.setBorder(null);
            this.buy.setOpaque(false);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TrslationMainJframe.getTrslationMainJframe().setShoucangBtn(e.getSource());
                    TrslationBtn sss = (TrslationBtn)TrslationMainJframe.getTrslationMainJframe().getShoucangBtn();
                    if (e.getButton() == 1) {
                        try {
                            if (TraslationWantBuyDaojuModelJpanel.this.stateOrNo == 1) {
                                sss.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                sss.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (TraslationWantBuyDaojuModelJpanel.this.tupian.getName().equals(TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getName())) {
                            TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucang().setIcons(TraslationWantBuyDaojuModelJpanel.this.shoucang.getIcons());
                        }
                    }
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationWantBuyDaojuModelJpanel.this.tupian.getName());
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.tupian.addMouseListener(new CBGmoveMouslisten(collection.getSaletype(), collection.getOtherid()));
        }
        else {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.collection = collection;
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel(collection.getSalename());
            String remainingTime = TrslationMainJframe.remainingTime(collection.getUptime(), Integer.valueOf(1));
            this.gongshiqiBiaoshi = 0;
            this.buy = new CBGDahuabiBuyBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, 2, "预购", this);
            if (remainingTime.equals("已下架")) {
                this.buy.setText("购买");
                remainingTime = TrslationMainJframe.remainingTime(collection.getUptime(), Integer.valueOf(168));
                this.gongshiqiBiaoshi = 1;
            }
            this.danjia = new JLabel(remainingTime);
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            if (collection.getSaleprice() != null) {
                this.price.setText(collection.getSaleprice() + "");
            }
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            if (collection.getSaleskin() != null) {
                ImageIcon cbg = CutButtonImage.getCBG((int)collection.getSaletype(), collection.getSaleskin(), 39, 39);
                this.tupian.setIcon(cbg);
            }
            try {
                this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            this.stateOrNo = 0;
            this.shoucang.setBounds(5, 5, 28, 28);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabixia.setBounds(100, 5, 150, 24);
            this.price.setBounds(280, 0, 150, 40);
            this.danjia.setBounds(350, 0, 150, 40);
            if (collection.getRoleid() != null) {
                this.price.setName(collection.getRoleid().toString());
            }
            if (collection.getSaleid() != null) {
                this.tupian.setName(collection.getSaleid().toString());
            }
            if (collection.getColid() != null) {
                this.dahuabixia.setName(collection.getColid().toString());
            }
            this.buy.setName(collection.getColid() + "");
            this.buy.setBounds(470, 10, 65, 31);
            this.buy.setBorder(null);
            this.buy.setOpaque(false);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TrslationMainJframe.getTrslationMainJframe().setShoucangBtn(e.getSource());
                    TrslationBtn sss = (TrslationBtn)TrslationMainJframe.getTrslationMainJframe().getShoucangBtn();
                    if (e.getButton() == 1) {
                        try {
                            if (TraslationWantBuyDaojuModelJpanel.this.stateOrNo == 1) {
                                sss.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                sss.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDaojuModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (TraslationWantBuyDaojuModelJpanel.this.tupian.getName().equals(TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getName())) {
                            TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucang().setIcons(TraslationWantBuyDaojuModelJpanel.this.shoucang.getIcons());
                        }
                    }
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationWantBuyDaojuModelJpanel.this.tupian.getName());
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.tupian.addMouseListener(new CBGmoveMouslisten(collection.getSaletype(), collection.getOtherid()));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon1 == null) {
            this.icon1 = new ImageIcon("inkImg/background/12.png");
        }
        g.drawImage(this.icon1.getImage(), 1, 48, 548, 2, this);
    }
    
    public TrslationBtn getShoucang() {
        return this.shoucang;
    }
    
    public void setShoucang(TrslationBtn shoucang) {
        this.shoucang = shoucang;
    }
    
    public JLabel getZhanshidikuang() {
        return this.zhanshidikuang;
    }
    
    public void setZhanshidikuang(JLabel zhanshidikuang) {
        this.zhanshidikuang = zhanshidikuang;
    }
    
    public JLabel getTupian() {
        return this.tupian;
    }
    
    public void setTupian(JLabel tupian) {
        this.tupian = tupian;
    }
    
    public JLabel getDahuabixia() {
        return this.dahuabixia;
    }
    
    public void setDahuabixia(JLabel dahuabixia) {
        this.dahuabixia = dahuabixia;
    }
    
    public JLabel getDanjia() {
        return this.danjia;
    }
    
    public void setDanjia(JLabel danjia) {
        this.danjia = danjia;
    }
    
    public JLabel getPrice() {
        return this.price;
    }
    
    public void setPrice(JLabel price) {
        this.price = price;
    }
    
    public int getStateOrNo() {
        return this.stateOrNo;
    }
    
    public void setStateOrNo(int stateOrNo) {
        this.stateOrNo = stateOrNo;
    }
    
    public CBGDahuabiBuyBtn getBuy() {
        return this.buy;
    }
    
    public void setBuy(CBGDahuabiBuyBtn buy) {
        this.buy = buy;
    }
    
    public int getGongshiqiBiaoshi() {
        return this.gongshiqiBiaoshi;
    }
    
    public void setGongshiqiBiaoshi(int gongshiqiBiaoshi) {
        this.gongshiqiBiaoshi = gongshiqiBiaoshi;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
    
    public Salegoods getSalegoods() {
        return this.salegoods;
    }
    
    public void setSalegoods(Salegoods salegoods) {
        this.salegoods = salegoods;
    }
    
    public Collection getCollection() {
        return this.collection;
    }
    
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
