package org.come.Jpanel;

import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import org.come.until.FormsManagement;
import org.come.Frame.EverydayRechargeJframe;
import org.come.until.CutButtonImage;
import com.tool.role.RoleData;
import java.util.ArrayList;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.btn.FormsOnOffBtn;
import com.updateNew.MyIsif;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.bean.ChongjipackBean;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JPanel;

public class EverydayRechargeJpanel extends JPanel
{
    private JList<EverydayRechargeGoodsJpanel> goodsJpanels;
    private JScrollPane paneList;
    private List<ChongjipackBean> chongjipackBeans;
    private ImageIcon iconBack;
    private ImageIcon iconTop;
    
    public EverydayRechargeJpanel() {
        this.setPreferredSize(new Dimension(682, 404));
        this.setOpaque(false);
        this.setLayout(null);
        if (MyIsif.getStyle().equals("水墨")) {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 100);
            offBtn.setBounds(655, 2, 25, 25);
            this.add(offBtn);
            this.getPaneList();
        }
        else {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 100);
            offBtn.setBounds(657, 0, 25, 25);
            this.add(offBtn);
            this.getPaneList();
        }
    }
    
    public void getGoods() {
        String sendmes = Agreement.getAgreement().PaydaygradepayAgreement();
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showGoods(List<ChongjipackBean> chongjipackBeans) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (chongjipackBeans == null) {
                chongjipackBeans = new ArrayList<>();
            }
            this.chongjipackBeans = chongjipackBeans;
            String vipget = RoleData.getRoleData().getLoginResult().getVipget();
            String[] split = null;
            if (vipget != null) {
                split = vipget.split("&&");
            }
            int componentCount = this.goodsJpanels.getComponentCount();
            for (int i = 0; i < chongjipackBeans.size() - 1; ++i) {
                for (int j = i + 1; j < chongjipackBeans.size(); ++j) {
                    ChongjipackBean chongjipackBeani = (ChongjipackBean)chongjipackBeans.get(i);
                    ChongjipackBean chongjipackBeanj = (ChongjipackBean)chongjipackBeans.get(j);
                    if ((int)chongjipackBeani.getCanpaymoney() > (int)chongjipackBeanj.getCanpaymoney()) {
                        chongjipackBeans.set(i, chongjipackBeanj);
                        chongjipackBeans.set(j, chongjipackBeani);
                    }
                }
            }
            for (int i = 0, size = chongjipackBeans.size(); i < size; ++i) {
                if (componentCount < chongjipackBeans.size()) {
                    EverydayRechargeGoodsJpanel everydayRechargeGoodsJpanel = new EverydayRechargeGoodsJpanel();
                    everydayRechargeGoodsJpanel.setBounds(0, componentCount * 60, 562, 60);
                    this.goodsJpanels.add(everydayRechargeGoodsJpanel);
                    ++componentCount;
                }
                EverydayRechargeGoodsJpanel goodsJpanel = (EverydayRechargeGoodsJpanel)this.goodsJpanels.getComponent(i);
                goodsJpanel.showGoods((ChongjipackBean)chongjipackBeans.get(i));
                if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "2", ((ChongjipackBean)chongjipackBeans.get(i)).getPackgradetype() + "")) {
                    goodsJpanel.getGetBtn().setBtn(-1);
                    goodsJpanel.getGetBtn().setIcon(CutButtonImage.getImage("inkImg/button/B29.png", -1, -1));
                }
                else {
                    goodsJpanel.getGetBtn().setBtn(1);
                    try {
                        goodsJpanel.getGetBtn().setIcons(CutButtonImage.cuts("inkImg/button/B12.png"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.goodsJpanels.setPreferredSize(new Dimension(562, 60 * chongjipackBeans.size()));
            if (!EverydayRechargeJframe.getEverydayRechargeJframe().isVisible()) {
                FormsManagement.showForm(100);
            }
        }
        else {
            if (chongjipackBeans == null) {
                chongjipackBeans = new ArrayList<>();
            }
            this.chongjipackBeans = chongjipackBeans;
            String vipget = RoleData.getRoleData().getLoginResult().getVipget();
            String[] split = null;
            if (vipget != null) {
                split = vipget.split("&&");
            }
            int componentCount = this.goodsJpanels.getComponentCount();
            for (int i = 0; i < chongjipackBeans.size() - 1; ++i) {
                for (int j = i + 1; j < chongjipackBeans.size(); ++j) {
                    ChongjipackBean chongjipackBeani = (ChongjipackBean)chongjipackBeans.get(i);
                    ChongjipackBean chongjipackBeanj = (ChongjipackBean)chongjipackBeans.get(j);
                    if ((int)chongjipackBeani.getCanpaymoney() > (int)chongjipackBeanj.getCanpaymoney()) {
                        chongjipackBeans.set(i, chongjipackBeanj);
                        chongjipackBeans.set(j, chongjipackBeani);
                    }
                }
            }
            for (int i = 0, size = chongjipackBeans.size(); i < size; ++i) {
                if (componentCount < chongjipackBeans.size()) {
                    EverydayRechargeGoodsJpanel everydayRechargeGoodsJpanel = new EverydayRechargeGoodsJpanel();
                    everydayRechargeGoodsJpanel.setBounds(0, componentCount * 60, 562, 60);
                    this.goodsJpanels.add(everydayRechargeGoodsJpanel);
                    ++componentCount;
                }
                EverydayRechargeGoodsJpanel goodsJpanel = (EverydayRechargeGoodsJpanel)this.goodsJpanels.getComponent(i);
                goodsJpanel.showGoods((ChongjipackBean)chongjipackBeans.get(i));
                if (ImpactGradeGoodsJpanel.checkYesOrNo(split, "2", ((ChongjipackBean)chongjipackBeans.get(i)).getPackgradetype() + "")) {
                    goodsJpanel.getGetBtn().setBtn(-1);
                    goodsJpanel.getGetBtn().setIcon(CutButtonImage.getImage("inkImg/hongmu/yilingqu.png", -1, -1));
                }
                else {
                    goodsJpanel.getGetBtn().setBtn(1);
                    try {
                        goodsJpanel.getGetBtn().setIcons(CutButtonImage.cuts("inkImg/hongmu/lingqu.png"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.goodsJpanels.setPreferredSize(new Dimension(562, 60 * chongjipackBeans.size()));
            if (!EverydayRechargeJframe.getEverydayRechargeJframe().isVisible()) {
                FormsManagement.showForm(100);
            }
        }
    }
    
    public void refreshPanel() {
        this.showGoods(this.chongjipackBeans);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/background/S8.png", -1, -1);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 682, 404, this);
            if (this.iconTop == null) {
                this.iconTop = CutButtonImage.getImage("inkImg/background/S4.png", -1, -1);
            }
            g.drawImage(this.iconTop.getImage(), 55, 23, 591, 116, this);
        }
        else {
            super.paintComponent(g);
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("img/xy2uiimg/S8.png", -1, -1);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 682, 404, this);
            if (this.iconTop == null) {
                this.iconTop = CutButtonImage.getImage("inkImg/background/S4.png", -1, -1);
            }
            g.drawImage(this.iconTop.getImage(), 40, 40, 591, 116, this);
        }
    }
    
    public JList<EverydayRechargeGoodsJpanel> getGoodsJpanels() {
        if (this.goodsJpanels == null) {
            (this.goodsJpanels = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
            this.goodsJpanels.setSelectionForeground(Color.white);
            this.goodsJpanels.setForeground(Color.white);
            this.goodsJpanels.setFont(UIUtils.TEXT_HY16);
            this.goodsJpanels.removeAll();
            this.goodsJpanels.setBackground(UIUtils.Color_BACK);
            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    return this;
                }
            };
            this.goodsJpanels.setCellRenderer(cellRenderer);
            this.goodsJpanels.setOpaque(false);
        }
        return this.goodsJpanels;
    }
    
    public void setGoodsJpanels(JList<EverydayRechargeGoodsJpanel> goodsJpanels) {
        this.goodsJpanels = goodsJpanels;
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
    
    public JScrollPane getPaneList() {
        if (this.paneList == null) {
            (this.paneList = new JScrollPane(this.getGoodsJpanels())).setVerticalScrollBarPolicy(22);
            this.paneList.getVerticalScrollBar().setUI(new ScrollUI());
            this.paneList.getVerticalScrollBar().setUnitIncrement(20);
            this.paneList.getViewport().setOpaque(false);
            this.paneList.setOpaque(false);
            this.paneList.setBounds(54, 174, 592, 207);
            this.paneList.setBorder(BorderFactory.createEmptyBorder());
            this.paneList.setHorizontalScrollBarPolicy(31);
            this.add(this.paneList);
        }
        return this.paneList;
    }
    
    public void setPaneList(JScrollPane paneList) {
        this.paneList = paneList;
    }
}
