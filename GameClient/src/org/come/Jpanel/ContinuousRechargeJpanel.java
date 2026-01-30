package org.come.Jpanel;

import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Graphics;
import org.come.until.FormsManagement;
import org.come.Frame.ContinuousRechargeJframe;
import org.come.until.CutButtonImage;
import com.tool.role.RoleData;
import java.util.ArrayList;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.bean.ChongjipackBean;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.JPanel;

public class ContinuousRechargeJpanel extends JPanel
{
    private List<ContinuousRechargeGoodsJpanel> goodsJpanelList;
    private JScrollPane scrollPane;
    private List<ChongjipackBean> chongjipackBeans;
    private ImageIcon iconBack;
    private ImageIcon iconTop;
    
    public ContinuousRechargeJpanel() {
        this.setPreferredSize(new Dimension(682, 479));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 101);
        offBtn.setBounds(657, 0, 25, 25);
        this.add(offBtn);
        this.getScrollPane();
        this.getGoodsJpanelList();
    }
    
    public void getGoods() {
        String sendmes = Agreement.getAgreement().DayforweekgradegetAgreement();
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showGoods(List<ChongjipackBean> chongjipackBeans) {
        if (chongjipackBeans == null) {
            chongjipackBeans = new ArrayList<>();
        }
        this.chongjipackBeans = chongjipackBeans;
        String vipget = RoleData.getRoleData().getLoginResult().getVipget();
        String[] split = null;
        if (vipget != null) {
            split = vipget.split("&&");
        }
        for (int i = 0; i < this.goodsJpanelList.size(); ++i) {
            if (i < chongjipackBeans.size()) {
                if (this.goodsJpanelList.size() < chongjipackBeans.size()) {
                    this.goodsJpanelList.add(new ContinuousRechargeGoodsJpanel());
                }
                if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "3", ((ChongjipackBean)chongjipackBeans.get(i)).getPackgradetype() + "")) {
                    ((ContinuousRechargeGoodsJpanel)this.goodsJpanelList.get(i)).getGetBtn().setBtn(-1);
                    ((ContinuousRechargeGoodsJpanel)this.goodsJpanelList.get(i)).getGetBtn().setIcon(CutButtonImage.getImage("inkImg/hongmu/yilingqu.png", -1, -1));
                }
                else {
                    ((ContinuousRechargeGoodsJpanel)this.goodsJpanelList.get(i)).getGetBtn().setBtn(1);
                    try {
                        ((ContinuousRechargeGoodsJpanel)this.goodsJpanelList.get(i)).getGetBtn().setIcons(CutButtonImage.cuts("inkImg/hongmu/lingqu.png"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ((ContinuousRechargeGoodsJpanel)this.goodsJpanelList.get(i)).showGoods((ChongjipackBean)chongjipackBeans.get(i));
            }
            else {
                ((ContinuousRechargeGoodsJpanel)this.goodsJpanelList.get(i)).showGoods(null);
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
        for (int i = 0; i < this.goodsJpanelList.size(); ++i) {
            ContinuousRechargeGoodsJpanel vipGoodsJpanel = (ContinuousRechargeGoodsJpanel)this.goodsJpanelList.get(i);
            if (vipGoodsJpanel.getChongjipackBean() != null) {
                vipGoodsJpanel.setBounds(10, num * 62, 562, 60);
                ++num;
                view.add(vipGoodsJpanel);
            }
        }
        view.setPreferredSize(new Dimension(590, 62 * num));
        this.scrollPane.updateUI();
        this.scrollPane.invalidate();
        this.scrollPane.validate();
        this.scrollPane.repaint();
        if (!ContinuousRechargeJframe.getContinuousRechargeJframe().isVisible()) {
            FormsManagement.showForm(101);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/background/S9.png", -1, -1);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 682, 479, this);
    }
    
    public List<ContinuousRechargeGoodsJpanel> getGoodsJpanelList() {
        if (this.goodsJpanelList == null) {
            this.goodsJpanelList = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                this.goodsJpanelList.add(new ContinuousRechargeGoodsJpanel());
            }
        }
        return this.goodsJpanelList;
    }
    
    public void setGoodsJpanelList(List<ContinuousRechargeGoodsJpanel> goodsJpanelList) {
        this.goodsJpanelList = goodsJpanelList;
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(55, 176, 590, 281);
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
}
