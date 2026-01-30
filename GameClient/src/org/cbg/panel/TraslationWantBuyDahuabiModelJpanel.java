package org.cbg.panel;

import java.awt.Graphics;
import org.cbg.mouslisten.CBGmoveMouslisten;
import org.cbg.frame.TraslationCommodityJFrame;
import org.cbg.frame.TrslationMainJframe;
import java.math.RoundingMode;
import org.come.until.UserData;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class TraslationWantBuyDahuabiModelJpanel extends JPanel
{
    private JLabel zhanshidikuang;
    private JLabel tupian;
    private JLabel dahuabishang;
    private JLabel dahuabixia;
    private JLabel danjia;
    private JLabel price;
    private TrslationBtn shoucang;
    private CBGDahuabiBuyBtn buy;
    private int stateOrNo;
    private int gongshiqiBiaoshi;
    private Salegoods salegoods;
    private Collection collection;
    private ImageIcon icon;
    
    public TraslationWantBuyDahuabiModelJpanel() {
        this.stateOrNo = 1;
        this.gongshiqiBiaoshi = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabishang = new JLabel("大话币");
            this.dahuabixia = new JLabel("三亿九千九百九十九万");
            this.danjia = new JLabel("48.6万两/元");
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel("￥100000.0");
            this.dahuabishang.setFont(UIUtils.TEXT_FONT);
            this.dahuabishang.setForeground(Color.white);
            this.dahuabixia.setFont(UIUtils.TEXT_FONT);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT);
            this.danjia.setForeground(Color.white);
            this.danjia.setHorizontalAlignment(0);
            this.price.setFont(UIUtils.TEXT_FONT);
            this.price.setForeground(Color.white);
            this.price.setHorizontalAlignment(0);
            this.shoucang.setBounds(5, 5, 29, 29);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabishang.setBounds(155, -5, 100, 24);
            this.dahuabixia.setBounds(100, 15, 150, 24);
            this.price.setBounds(230, 0, 125, 40);
            this.danjia.setBounds(350, 0, 125, 40);
            (this.buy = new CBGDahuabiBuyBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, 2, "购买", this)).setBounds(470, 10, 60, 26);
            this.buy.setBorder(null);
            this.buy.setOpaque(false);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabishang);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1) {
                        JPanel source = (JPanel)e.getSource();
                        try {
                            if (TraslationWantBuyDahuabiModelJpanel.this.stateOrNo == 1) {
                                TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    Collection collection = new Collection();
                    collection.setRoleid(new BigDecimal(TraslationWantBuyDahuabiModelJpanel.this.buy.getName()));
                    collection.setSaleid(new BigDecimal(TraslationWantBuyDahuabiModelJpanel.this.tupian.getName()));
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(GsonUtil.getGsonUtil().getgson().toJson(collection));
                    SendMessageUntil.toServer(sendmes);
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
            this.dahuabishang = new JLabel("大话币");
            this.dahuabixia = new JLabel("三亿九千九百九十九万");
            this.danjia = new JLabel("48.6万两/元");
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel("￥100000.0");
            this.dahuabishang.setFont(UIUtils.TEXT_FONT);
            this.dahuabishang.setForeground(Color.white);
            this.dahuabixia.setFont(UIUtils.TEXT_FONT);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT);
            this.danjia.setForeground(Color.white);
            this.danjia.setHorizontalAlignment(0);
            this.price.setFont(UIUtils.TEXT_FONT);
            this.price.setForeground(Color.white);
            this.price.setHorizontalAlignment(0);
            this.shoucang.setBounds(5, 5, 29, 29);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabishang.setBounds(155, -5, 100, 24);
            this.dahuabixia.setBounds(100, 15, 150, 24);
            this.price.setBounds(230, 0, 125, 40);
            this.danjia.setBounds(350, 0, 125, 40);
            (this.buy = new CBGDahuabiBuyBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, 2, "购买", this)).setBounds(470, 10, 60, 26);
            this.buy.setBorder(null);
            this.buy.setOpaque(false);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabishang);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1) {
                        JPanel source = (JPanel)e.getSource();
                        try {
                            if (TraslationWantBuyDahuabiModelJpanel.this.stateOrNo == 1) {
                                TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    Collection collection = new Collection();
                    collection.setRoleid(new BigDecimal(TraslationWantBuyDahuabiModelJpanel.this.buy.getName()));
                    collection.setSaleid(new BigDecimal(TraslationWantBuyDahuabiModelJpanel.this.tupian.getName()));
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(GsonUtil.getGsonUtil().getgson().toJson(collection));
                    SendMessageUntil.toServer(sendmes);
                }
            });
        }
    }
    
    public TraslationWantBuyDahuabiModelJpanel(Salegoods salegoods) {
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
            this.dahuabishang = new JLabel("大话币");
            this.dahuabixia = new JLabel(UserData.getMS(salegoods.getOtherid()));
            this.danjia = new JLabel();
            if (salegoods.getSaleprice() != null && salegoods.getSalename() != null) {
                this.danjia.setText(salegoods.getOtherid().divide(salegoods.getSaleprice().multiply(new BigDecimal(10000)), 2, RoundingMode.HALF_UP) + "万两/仙玉");
            }
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            if (salegoods.getSaleprice() != null) {
                this.price.setText(salegoods.getSaleprice() + "");
            }
            this.dahuabishang.setFont(UIUtils.TEXT_FONT2);
            this.dahuabishang.setForeground(Color.white);
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.danjia.setHorizontalAlignment(0);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.price.setHorizontalAlignment(0);
            if (salegoods.getRoleid() != null) {
                this.price.setName(salegoods.getRoleid().toString());
            }
            if (salegoods.getBuyrole() != null) {
                this.danjia.setName(salegoods.getBuyrole().toString());
            }
            if (salegoods.getSaleid() != null) {
                this.tupian.setName(salegoods.getSaleid().toString());
            }
            if (salegoods.getOtherid() != null) {
                this.dahuabixia.setName(salegoods.getOtherid().toString());
            }
            this.buy = new CBGDahuabiBuyBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, 1, "购买", this);
            this.gongshiqiBiaoshi = 1;
            if (salegoods.getUptime() != null) {
                String remainingTime = TrslationMainJframe.remainingTime(salegoods.getUptime(), Integer.valueOf(168));
                this.dahuabishang.setName(remainingTime);
            }
            if (salegoods.getSaletype() != null) {
                this.buy.setName(salegoods.getSaletype().toString());
            }
            try {
                this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            this.stateOrNo = 1;
            if (salegoods.getSaleskin() != null) {
                this.tupian.setIcon(CutButtonImage.getImage("img/item/" + salegoods.getSaleskin() + ".png", 39, 39));
            }
            this.shoucang.setBounds(5, 5, 28, 28);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabishang.setBounds(165, 0, 100, 24);
            this.dahuabixia.setBounds(100, 20, 150, 24);
            this.price.setBounds(230, 0, 125, 40);
            this.danjia.setBounds(350, 0, 125, 40);
            this.buy.setBounds(470, 10, 60, 26);
            this.buy.setBorder(null);
            this.buy.setOpaque(false);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabishang);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1) {
                        try {
                            if (TraslationWantBuyDahuabiModelJpanel.this.stateOrNo == 1) {
                                TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (TraslationWantBuyDahuabiModelJpanel.this.tupian.getName().equals(TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getName())) {
                            TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucang().setIcons(TraslationWantBuyDahuabiModelJpanel.this.shoucang.getIcons());
                        }
                        String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationWantBuyDahuabiModelJpanel.this.tupian.getName());
                        SendMessageUntil.toServer(sendmes);
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
            this.dahuabishang = new JLabel("大话币");
            this.dahuabixia = new JLabel(UserData.getMS(salegoods.getOtherid()));
            this.danjia = new JLabel();
            if (salegoods.getSaleprice() != null && salegoods.getSalename() != null) {
                this.danjia.setText(salegoods.getOtherid().divide(salegoods.getSaleprice().multiply(new BigDecimal(10000)), 2, RoundingMode.HALF_UP) + "万两/仙玉");
            }
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            if (salegoods.getSaleprice() != null) {
                this.price.setText(salegoods.getSaleprice() + "");
            }
            this.dahuabishang.setFont(UIUtils.TEXT_FONT2);
            this.dahuabishang.setForeground(Color.white);
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.danjia.setHorizontalAlignment(0);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.price.setHorizontalAlignment(0);
            if (salegoods.getRoleid() != null) {
                this.price.setName(salegoods.getRoleid().toString());
            }
            if (salegoods.getBuyrole() != null) {
                this.danjia.setName(salegoods.getBuyrole().toString());
            }
            if (salegoods.getSaleid() != null) {
                this.tupian.setName(salegoods.getSaleid().toString());
            }
            if (salegoods.getOtherid() != null) {
                this.dahuabixia.setName(salegoods.getOtherid().toString());
            }
            this.buy = new CBGDahuabiBuyBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, 1, "购买", this);
            this.gongshiqiBiaoshi = 1;
            if (salegoods.getUptime() != null) {
                String remainingTime = TrslationMainJframe.remainingTime(salegoods.getUptime(), Integer.valueOf(168));
                this.dahuabishang.setName(remainingTime);
            }
            if (salegoods.getSaletype() != null) {
                this.buy.setName(salegoods.getSaletype().toString());
            }
            try {
                this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            this.stateOrNo = 1;
            if (salegoods.getSaleskin() != null) {
                this.tupian.setIcon(CutButtonImage.getImage("img/item/" + salegoods.getSaleskin() + ".png", 39, 39));
            }
            this.shoucang.setBounds(5, 5, 28, 28);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabishang.setBounds(165, 0, 100, 24);
            this.dahuabixia.setBounds(100, 20, 150, 24);
            this.price.setBounds(230, 0, 125, 40);
            this.danjia.setBounds(350, 0, 125, 40);
            this.buy.setBounds(470, 10, 60, 26);
            this.buy.setBorder(null);
            this.buy.setOpaque(false);
            this.add(this.buy);
            this.add(this.shoucang);
            this.add(this.tupian);
            this.add(this.dahuabishang);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.shoucang.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1) {
                        try {
                            if (TraslationWantBuyDahuabiModelJpanel.this.stateOrNo == 1) {
                                TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                                TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 0;
                            }
                            else {
                                TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                                TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 1;
                            }
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (TraslationWantBuyDahuabiModelJpanel.this.tupian.getName().equals(TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getName())) {
                            TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucang().setIcons(TraslationWantBuyDahuabiModelJpanel.this.shoucang.getIcons());
                        }
                        String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationWantBuyDahuabiModelJpanel.this.tupian.getName());
                        SendMessageUntil.toServer(sendmes);
                    }
                }
            });
            this.tupian.addMouseListener(new CBGmoveMouslisten(salegoods.getSaletype(), salegoods.getOtherid()));
        }
    }
    
    public TraslationWantBuyDahuabiModelJpanel(Salegoods salegoods, int i) {
        this.stateOrNo = 1;
        this.gongshiqiBiaoshi = 0;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(550, 50));
        this.setOpaque(false);
        this.salegoods = salegoods;
        this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
        this.tupian = new JLabel();
        this.zhanshidikuang = new JLabel();
        this.dahuabishang = new JLabel("大话币");
        this.dahuabixia = new JLabel(UserData.getMS(salegoods.getOtherid()));
        this.danjia = new JLabel();
        this.zhanshidikuang.setBorder(null);
        this.zhanshidikuang.setBounds(40, 5, 39, 39);
        this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
        this.zhanshidikuang.setOpaque(false);
        this.add(this.zhanshidikuang);
        this.price = new JLabel();
        if (salegoods.getSaleprice() != null) {
            this.price.setText(salegoods.getSaleprice() + "");
        }
        this.dahuabishang.setFont(UIUtils.TEXT_FONT2);
        this.dahuabishang.setForeground(Color.white);
        this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
        this.dahuabixia.setForeground(Color.white);
        this.dahuabixia.setHorizontalAlignment(0);
        this.danjia.setFont(UIUtils.TEXT_FONT2);
        this.danjia.setForeground(Color.white);
        this.price.setFont(UIUtils.TEXT_FONT2);
        this.price.setForeground(Color.white);
        if (salegoods.getRoleid() != null) {
            this.price.setName(salegoods.getRoleid().toString());
        }
        if (salegoods.getBuyrole() != null) {
            this.danjia.setName(salegoods.getBuyrole().toString());
        }
        if (salegoods.getSaleid() != null) {
            this.tupian.setName(salegoods.getSaleid().toString());
        }
        if (salegoods.getOtherid() != null) {
            this.dahuabixia.setName(salegoods.getOtherid().toString());
        }
        this.buy = new CBGDahuabiBuyBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, 1, "购 买", this);
        this.gongshiqiBiaoshi = 1;
        if (salegoods.getUptime() != null) {
            String remainingTime = TrslationMainJframe.remainingTime(salegoods.getUptime(), Integer.valueOf(168));
            this.dahuabishang.setName(remainingTime);
            this.danjia.setText(remainingTime);
        }
        if (salegoods.getSaletype() != null) {
            this.buy.setName(salegoods.getSaletype().toString());
        }
        try {
            this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        this.stateOrNo = 1;
        if (salegoods.getSaleskin() != null) {
            this.tupian.setIcon(CutButtonImage.getCBG((int)salegoods.getSaletype(), salegoods.getSaleskin(), 39, 39));
        }
        this.shoucang.setBounds(5, 5, 28, 28);
        this.tupian.setBounds(40, 5, 39, 39);
        this.dahuabishang.setBounds(165, 0, 100, 24);
        this.dahuabixia.setBounds(100, 20, 150, 24);
        this.price.setBounds(280, 0, 150, 40);
        this.danjia.setBounds(350, 0, 150, 40);
        this.buy.setBounds(470, 10, 60, 26);
        this.buy.setBorder(null);
        this.buy.setOpaque(false);
        this.add(this.buy);
        this.add(this.shoucang);
        this.add(this.tupian);
        this.add(this.dahuabishang);
        this.add(this.dahuabixia);
        this.add(this.danjia);
        this.add(this.price);
        this.shoucang.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    try {
                        if (TraslationWantBuyDahuabiModelJpanel.this.stateOrNo == 1) {
                            TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                            TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 0;
                        }
                        else {
                            TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                            TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 1;
                        }
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (TraslationWantBuyDahuabiModelJpanel.this.tupian.getName().equals(TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getName())) {
                        TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucang().setIcons(TraslationWantBuyDahuabiModelJpanel.this.shoucang.getIcons());
                    }
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationWantBuyDahuabiModelJpanel.this.tupian.getName());
                    SendMessageUntil.toServer(sendmes);
                }
            }
        });
        this.tupian.addMouseListener(new CBGmoveMouslisten(salegoods.getSaletype(), salegoods.getOtherid()));
    }
    
    public TraslationWantBuyDahuabiModelJpanel(Collection salegoods) {
        this.stateOrNo = 1;
        this.gongshiqiBiaoshi = 0;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(550, 50));
        this.setOpaque(false);
        this.collection = salegoods;
        this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
        this.tupian = new JLabel();
        this.zhanshidikuang = new JLabel();
        this.dahuabishang = new JLabel("大话币");
        this.dahuabixia = new JLabel(UserData.getMS(salegoods.getOtherid()));
        this.danjia = new JLabel();
        this.zhanshidikuang.setBorder(null);
        this.zhanshidikuang.setBounds(40, 5, 39, 39);
        this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
        this.zhanshidikuang.setOpaque(false);
        this.add(this.zhanshidikuang);
        this.price = new JLabel();
        if (salegoods.getSaleprice() != null) {
            this.price.setText(salegoods.getSaleprice() + "");
        }
        this.dahuabishang.setFont(UIUtils.TEXT_FONT2);
        this.dahuabishang.setForeground(Color.white);
        this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
        this.dahuabixia.setForeground(Color.white);
        this.dahuabixia.setHorizontalAlignment(0);
        this.danjia.setFont(UIUtils.TEXT_FONT2);
        this.danjia.setForeground(Color.white);
        this.price.setFont(UIUtils.TEXT_FONT2);
        this.price.setForeground(Color.white);
        if (salegoods.getRoleid() != null) {
            this.price.setName(salegoods.getRoleid().toString());
        }
        if (salegoods.getBuyrole() != null) {
            this.danjia.setName(salegoods.getBuyrole().toString());
        }
        if (salegoods.getSaleid() != null) {
            this.tupian.setName(salegoods.getSaleid().toString());
        }
        this.buy = new CBGDahuabiBuyBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, 1, "购 买", this);
        this.gongshiqiBiaoshi = 1;
        if (salegoods.getUptime() != null) {
            String remainingTime = TrslationMainJframe.remainingTime(salegoods.getUptime(), Integer.valueOf(168));
            this.dahuabishang.setName(remainingTime);
            this.danjia.setText(remainingTime);
        }
        try {
            this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        this.stateOrNo = 0;
        if (salegoods.getSaleskin() != null) {
            this.tupian.setIcon(CutButtonImage.getCBG((int)salegoods.getSaletype(), salegoods.getSaleskin(), 39, 39));
        }
        this.shoucang.setBounds(5, 5, 28, 28);
        this.tupian.setBounds(40, 5, 39, 39);
        this.dahuabishang.setBounds(165, 0, 100, 24);
        this.dahuabixia.setBounds(100, 20, 150, 24);
        this.price.setBounds(280, 0, 150, 40);
        this.danjia.setBounds(350, 0, 150, 40);
        this.buy.setBounds(470, 10, 60, 26);
        this.buy.setBorder(null);
        this.buy.setOpaque(false);
        this.add(this.buy);
        this.add(this.shoucang);
        this.add(this.tupian);
        this.add(this.dahuabishang);
        this.add(this.dahuabixia);
        this.add(this.danjia);
        this.add(this.price);
        this.shoucang.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    try {
                        if (TraslationWantBuyDahuabiModelJpanel.this.stateOrNo == 1) {
                            TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                            TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 0;
                        }
                        else {
                            TraslationWantBuyDahuabiModelJpanel.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                            TraslationWantBuyDahuabiModelJpanel.this.stateOrNo = 1;
                        }
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (TraslationWantBuyDahuabiModelJpanel.this.tupian.getName().equals(TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getName())) {
                        TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane().getShoucang().setIcons(TraslationWantBuyDahuabiModelJpanel.this.shoucang.getIcons());
                    }
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationWantBuyDahuabiModelJpanel.this.tupian.getName());
                    SendMessageUntil.toServer(sendmes);
                }
            }
        });
        this.tupian.addMouseListener(new CBGmoveMouslisten(salegoods.getSaletype(), salegoods.getOtherid()));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(245, 245, 245));
        if (this.icon == null) {
            this.icon = CutButtonImage.getImage("inkImg/background/12.png", 548, 2);
        }
        g.drawImage(this.icon.getImage(), 0, 48, 548, 2, this);
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
    
    public JLabel getDahuabishang() {
        return this.dahuabishang;
    }
    
    public void setDahuabishang(JLabel dahuabishang) {
        this.dahuabishang = dahuabishang;
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
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public int getStateOrNo() {
        return this.stateOrNo;
    }
    
    public void setStateOrNo(int stateOrNo) {
        this.stateOrNo = stateOrNo;
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
