package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.until.ScrollUI;
import org.come.mouslisten.TradeChoseGoodsMouslisten;
import org.come.until.SrcollPanelUI;
import com.tool.time.Limit;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.Frame.BoothBoxJframe;
import com.tool.time.TimeLimit;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.until.UserMessUntil;
import org.come.entity.RoleSummoning;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.goodbtn;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import org.come.Frame.TradeJframe;
import javax.swing.JPanel;

public class TradeJpanel extends JPanel
{
    private TradeJframe tradejframe;
    private JLabel labtradepet;
    private JList<String> listEquipment;
    private DefaultListModel<String> modelTrade;
    private DefaultListModel<String> modelname;
    private Color fontcolor;
    private JScrollPane jScrollPane1;
    private JButton btnright1;
    private JButton btnright2;
    private JButton btnright3;
    private JButton btnright4;
    private JButton btnright5;
    private goodbtn[] btnrights;
    private JLabel[] GoodsListLabel;
    ImageIcon icon;
    
    public TradeJpanel(TradeJframe tradejframe) throws Exception {
        this.GoodsListLabel = new JLabel[24];
        if (MyIsif.getStyle().equals("水墨")) {
            this.tradejframe = tradejframe;
            this.setPreferredSize(new Dimension(409, 436));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 15);
            offBtn.setBounds(372, 9, 25, 25);
            this.add(offBtn);
            (this.labtradepet = new JLabel()).setBounds(214, 27, 163, 170);
            this.add(this.labtradepet);
            this.modelname = new DefaultListModel<>();
            (this.listEquipment = new JList<>()).setOpaque(false);
            this.listEquipment.setSelectionBackground(new Color(33, 42, 52));
            this.fontcolor = Color.GREEN;
            this.listEquipment.setSelectionForeground(this.fontcolor);
            this.listEquipment.setForeground(this.fontcolor);
            this.listEquipment.setFont(new Font("微软雅黑", 1, 14));
            this.listEquipment.setBackground(UIUtils.Color_BACK);
            this.listEquipment.setModel(this.modelname);
            this.listEquipment.addMouseListener(new MouseListener() {
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
                    if (TradeJpanel.this.listEquipment.getSelectedIndex() != -1) {
                        int index = TradeJpanel.this.listEquipment.getSelectedIndex();
                        RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(index);
                        if (pet.getPetlock() == 1) {
                            ZhuFrame.getZhuJpanel().addPrompt("召唤兽" + pet.getSummoningname() + "已被加锁，不可摆摊！！");
                            return;
                        }
                        if (!pet.getQuality().equals("2")) {
                            ZhuFrame.getZhuJpanel().addPrompt("该召唤兽不可交易！！");
                            return;
                        }
                        if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && pet.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽在参战中，不可摆摊！");
                            return;
                        }
                        if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽被管制中，不可摆摊！！！");
                            return;
                        }
                        Limit limit = TimeLimit.getLimits().getLimit("枯荣丹");
                        if (limit != null && limit.getValue().equals(pet.getSid().toString())) {
                            ZhuFrame.getZhuJpanel().addPrompt2("召唤兽已使用枯荣丹，不可摆摊！！！");
                            return;
                        }
                        BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().XZBuy(null, pet);
                        PetAddPointMouslisten.showPetValue();
                    }
                }
            });
            (this.jScrollPane1 = new JScrollPane(this.listEquipment)).setVerticalScrollBarPolicy(22);
            this.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane1.getViewport().setOpaque(false);
            this.jScrollPane1.setOpaque(false);
            this.jScrollPane1.setBounds(46, 42, 153, 117);
            this.jScrollPane1.setBorder(null);
            this.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane1);
            for (int i = 0; i < 24; ++i) {
                (this.GoodsListLabel[i] = new JLabel()).addMouseListener(new TradeChoseGoodsMouslisten(i));
                this.GoodsListLabel[i].setBounds(46 + i % 6 * 51, 199 + i / 6 * 51, 49, 49);
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(353, 198 + i * 35, 35, 31);
                this.add(this.btnrights[i]);
            }
        }
        else {
            this.tradejframe = tradejframe;
            this.setPreferredSize(new Dimension(360, 452));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/s74.png", 1, 15);
            offBtn.setBounds(340, 10, 25, 25);
            this.add(offBtn);
            (this.labtradepet = new JLabel()).setBounds(189, 37, 146, 173);
            this.add(this.labtradepet);
            this.modelname = new DefaultListModel<>();
            (this.listEquipment = new JList<>()).setOpaque(false);
            this.listEquipment.setSelectionBackground(new Color(33, 42, 52));
            this.fontcolor = Color.GREEN;
            this.listEquipment.setSelectionForeground(this.fontcolor);
            this.listEquipment.setForeground(this.fontcolor);
            this.listEquipment.setFont(new Font("微软雅黑", 1, 14));
            this.listEquipment.setBackground(UIUtils.Color_BACK);
            this.listEquipment.setModel(this.modelname);
            this.listEquipment.addMouseListener(new MouseListener() {
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
                    if (TradeJpanel.this.listEquipment.getSelectedIndex() != -1) {
                        int index = TradeJpanel.this.listEquipment.getSelectedIndex();
                        RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(index);
                        if (pet.getQuality() != null && !pet.getQuality().equals("2")) {
                            ZhuFrame.getZhuJpanel().addPrompt("#R提示：#Y召唤兽#G【" + pet.getSummoningname() + "】#Y不可交易！！");
                            return;
                        }
                        if (pet.getPetlock() == 1) {
                            ZhuFrame.getZhuJpanel().addPrompt("#R提示：#Y召唤兽#G【" + pet.getSummoningname() + "】#Y已被加锁，不可摆摊！！");
                            return;
                        }
                        if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && pet.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("#R提示：#Y该召唤兽在参战中，不可摆摊！");
                            return;
                        }
                        if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("#R提示：#Y这只召唤兽被管制中，不可摆摊！！！");
                            return;
                        }
                        Limit limit = TimeLimit.getLimits().getLimit("枯荣丹");
                        if (limit != null && limit.getValue().equals(pet.getSid().toString())) {
                            ZhuFrame.getZhuJpanel().addPrompt2("召唤兽已使用枯荣丹，不可摆摊！！！");
                            return;
                        }
                        BoothBoxJframe.getBoothboxjframe().getBoothboxjpanel().XZBuy(null, pet);
                        PetAddPointMouslisten.showPetValue();
                    }
                }
            });
            (this.jScrollPane1 = new JScrollPane(this.listEquipment)).setVerticalScrollBarPolicy(22);
            this.jScrollPane1.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane1.getViewport().setOpaque(false);
            this.jScrollPane1.setOpaque(false);
            this.jScrollPane1.setBounds(23, 49, 139, 128);
            this.jScrollPane1.setBorder(null);
            this.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane1);
            for (int i = 0; i < 24; ++i) {
                (this.GoodsListLabel[i] = new JLabel()).addMouseListener(new TradeChoseGoodsMouslisten(i));
                this.GoodsListLabel[i].setBounds(19 + i % 6 * 51, 224 + i / 6 * 51, 49, 49);
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("img/xy2uiimg/44_png.button.tab_pak.png", 0, this, i)).setBounds(325, 224 + i * 51, 16, 49);
                this.add(this.btnrights[i]);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B251.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 409, 436, this);
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", 0, 12));
            Util.drawMoney(g, 86, 183);
            GoodsListFromServerUntil.draw(g, 46, 199);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/103_png.xy2uiimg.trad_pet.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 360, 452, this);
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", 0, 12));
            Util.drawMoney(g, 62, 205);
            GoodsListFromServerUntil.draw(g, 19, 224);
        }
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public TradeJframe getTradejframe() {
        return this.tradejframe;
    }
    
    public void setTradejframe(TradeJframe tradejframe) {
        this.tradejframe = tradejframe;
    }
    
    public JLabel getLabtradepet() {
        return this.labtradepet;
    }
    
    public void setLabtradepet(JLabel labtradepet) {
        this.labtradepet = labtradepet;
    }
    
    public JList<String> getListEquipment() {
        return this.listEquipment;
    }
    
    public void setListEquipment(JList<String> listEquipment) {
        this.listEquipment = listEquipment;
    }
    
    public DefaultListModel<String> getModelTrade() {
        return this.modelTrade;
    }
    
    public void setModelTrade(DefaultListModel<String> modelTrade) {
        this.modelTrade = modelTrade;
    }
    
    public DefaultListModel<String> getModelname() {
        return this.modelname;
    }
    
    public void setModelname(DefaultListModel<String> modelname) {
        this.modelname = modelname;
    }
    
    public Color getFontcolor() {
        return this.fontcolor;
    }
    
    public void setFontcolor(Color fontcolor) {
        this.fontcolor = fontcolor;
    }
    
    public JScrollPane getjScrollPane1() {
        return this.jScrollPane1;
    }
    
    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }
    
    public JButton getBtnright1() {
        return this.btnright1;
    }
    
    public void setBtnright1(JButton btnright1) {
        this.btnright1 = btnright1;
    }
    
    public JButton getBtnright2() {
        return this.btnright2;
    }
    
    public void setBtnright2(JButton btnright2) {
        this.btnright2 = btnright2;
    }
    
    public JButton getBtnright3() {
        return this.btnright3;
    }
    
    public void setBtnright3(JButton btnright3) {
        this.btnright3 = btnright3;
    }
    
    public JButton getBtnright4() {
        return this.btnright4;
    }
    
    public void setBtnright4(JButton btnright4) {
        this.btnright4 = btnright4;
    }
    
    public JButton getBtnright5() {
        return this.btnright5;
    }
    
    public void setBtnright5(JButton btnright5) {
        this.btnright5 = btnright5;
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
}
