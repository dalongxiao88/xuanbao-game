package org.lottery.panel;

import com.tool.tcpimg.UIUtils;
import java.awt.Component;
import org.come.model.Shop;
import java.util.List;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.cbg.until.TraslationDemoScrollBarUI;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LotteryIntegralMainPanel extends JPanel
{
    private JLabel integralTypeLab;
    private JScrollPane scrollPane;
    private JPanel jPanel;
    private ImageIcon iconBack;
    
    public LotteryIntegralMainPanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(571, 501));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 88);
            offBtn.setBounds(548, 0, 23, 23);
            this.add(offBtn);
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.scrollPane));
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(22, 109, 526, 359);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            (this.jPanel = new JPanel()).setOpaque(false);
            this.jPanel.setLayout(null);
            this.scrollPane.setViewportView(this.jPanel);
            this.add(this.scrollPane);
            this.scrollPane.updateUI();
            this.scrollPane.invalidate();
            this.scrollPane.validate();
            this.scrollPane.repaint();
            this.getIntegralTypeLab();
        }
        else {
            this.setPreferredSize(new Dimension(571, 501));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 88);
            offBtn.setBounds(548, 0, 23, 23);
            this.add(offBtn);
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.scrollPane));
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(22, 109, 526, 359);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            (this.jPanel = new JPanel()).setOpaque(false);
            this.jPanel.setLayout(null);
            this.scrollPane.setViewportView(this.jPanel);
            this.add(this.scrollPane);
            this.scrollPane.updateUI();
            this.scrollPane.invalidate();
            this.scrollPane.validate();
            this.scrollPane.repaint();
            this.getIntegralTypeLab();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/cjjfdh.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 571, 501, this);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/积分兑换_w571,h501.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 571, 501, this);
        }
    }
    
    public void getShopIntegralData(List<Shop> shopList, String integralType, String integralNow) {
        Component[] components = this.jPanel.getComponents();
        if (components.length >= shopList.size()) {
            for (int i = 0; i < shopList.size(); ++i) {
                LotteryIntegralGoodsJpanel component2 = (LotteryIntegralGoodsJpanel)this.jPanel.getComponent(i);
                component2.getShopData((Shop)shopList.get(i));
            }
            for (int i = shopList.size(); i < components.length; ++i) {
                LotteryIntegralGoodsJpanel component2 = (LotteryIntegralGoodsJpanel)this.jPanel.getComponent(i);
                component2.clearWindow();
            }
            this.jPanel.setPreferredSize(new Dimension(656, 65 * ((shopList.size() % 2 == 0) ? (shopList.size() / 2) : (shopList.size() / 2 + 1))));
        }
        else if (components.length < shopList.size()) {
            for (int i = 0; i < components.length; ++i) {
                LotteryIntegralGoodsJpanel component2 = (LotteryIntegralGoodsJpanel)this.jPanel.getComponent(i);
                component2.getShopData((Shop)shopList.get(i));
            }
            for (int i = components.length; i < shopList.size(); ++i) {
                int row = i / 2;
                int rank = i % 2;
                LotteryIntegralGoodsJpanel dailyLoginGoodsJpanel = new LotteryIntegralGoodsJpanel((Shop)shopList.get(i));
                dailyLoginGoodsJpanel.setBounds(rank * 259 + 0, row * 65 + 0, 250, 65);
                this.jPanel.add(dailyLoginGoodsJpanel);
            }
            this.jPanel.setPreferredSize(new Dimension(656, 65 * ((shopList.size() % 2 == 0) ? (shopList.size() / 2) : (shopList.size() / 2 + 1))));
        }
        this.scrollPane.updateUI();
        this.scrollPane.invalidate();
        this.scrollPane.validate();
        this.scrollPane.repaint();
        this.integralTypeLab.setText(integralType + ":" + integralNow);
    }
    
    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel getIntegralTypeLab() {
        if (this.integralTypeLab == null) {
            (this.integralTypeLab = new JLabel("积分类型：当前积分数")).setBounds(-20, 68, 550, 20);
            this.integralTypeLab.setHorizontalAlignment(4);
            this.integralTypeLab.setFont(UIUtils.TEXT_FONT);
            this.integralTypeLab.setOpaque(false);
            this.integralTypeLab.setForeground(UIUtils.COLOR_HURTB3);
            this.add(this.integralTypeLab);
        }
        return this.integralTypeLab;
    }
    
    public void setIntegralTypeLab(JLabel integralTypeLab) {
        this.integralTypeLab = integralTypeLab;
    }
}
