package org.gemstone.panel;

import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import org.gemstone.mouseListener.CancelMouseListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.gemstone.btn.GemstoneOperationBtn;
import org.come.entity.Goodstable;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 宝石重铸面板
 *
 * @author Administrator
 *
 */
public class GemstoneOperationAppraisalAndRecastPanel extends JPanel
{
    private JLabel recastGemstone;// 重铸宝石
    private JLabel gemstoneName; // 第一颗宝石名
    private Goodstable reelectGoods;// 重选类型type
    private GemstoneOperationBtn reacastBtn;// 重铸按钮
    private GemstoneOperationBtn reelectBtn; // 重选类型按钮
    private JLabel[] gemstoneNumber;// 第一颗宝石,第二颗宝石
    public GemstoneOperationRecastTypePanel gemstoneOperationRecastTypePanel;
    private ImageIcon iconBack;
    private ImageIcon iconRecastGrayBtn;
    
    public GemstoneOperationAppraisalAndRecastPanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(310, 260));
            this.setOpaque(false);
            this.setLayout(null);
            this.add(this.gemstoneOperationRecastTypePanel = new GemstoneOperationRecastTypePanel());
            this.gemstoneName = new JLabel();
            this.recastGemstone = new JLabel();
            this.reacastBtn = new GemstoneOperationBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "重铸", UIUtils.TEXT_HY16, 8);
            this.reelectBtn = new GemstoneOperationBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, "重选类型", UIUtils.TEXT_FONT, 9);
            this.gemstoneNumber = new JLabel[2];
            for (int i = 0; i < this.gemstoneNumber.length; ++i) {
                (this.gemstoneNumber[i] = new JLabel()).setOpaque(false);
                this.gemstoneNumber[i].setBounds(84 + i * 79, 33, 53, 51);
                this.gemstoneNumber[i].addMouseListener(new CancelMouseListener(i));
                this.add(this.gemstoneNumber[i]);
            }
            this.reelectGoods = new Goodstable();
            this.recastGemstone.setOpaque(false);
            this.gemstoneName.setOpaque(false);
            this.gemstoneOperationRecastTypePanel.setVisible(false);
            this.gemstoneName.setForeground(Color.gray);
            this.gemstoneName.setFont(new Font("宋体", 0, 13));
            this.gemstoneName.setHorizontalAlignment(0);
            this.gemstoneName.setBounds(84, 12, 53, 13);
            this.recastGemstone.setBounds(125, 136, 53, 51);
            this.reelectBtn.setBounds(118, 116, 68, 17);
            this.reacastBtn.setBounds(121, 210, 59, 24);
            this.add(this.gemstoneName);
            this.add(this.recastGemstone);
            this.add(this.reelectBtn);
            this.add(this.reacastBtn);
        }
        else {
            this.setPreferredSize(new Dimension(310, 260));
            this.setOpaque(false);
            this.setLayout(null);
            this.add(this.gemstoneOperationRecastTypePanel = new GemstoneOperationRecastTypePanel());
            this.gemstoneName = new JLabel();
            this.recastGemstone = new JLabel();
            this.reacastBtn = new GemstoneOperationBtn("img/gemstone/重铸w60,h78.png", 1, UIUtils.COLOR_BTNPUTONG, "", UIUtils.TEXT_HY16, 8);
            this.reelectBtn = new GemstoneOperationBtn("img/gemstone/重选类型w68,h51px.png", 1, UIUtils.COLOR_BTNTEXT, "", UIUtils.TEXT_FONT, 9);
            this.gemstoneNumber = new JLabel[2];
            for (int i = 0; i < this.gemstoneNumber.length; ++i) {
                (this.gemstoneNumber[i] = new JLabel()).setOpaque(false);
                this.gemstoneNumber[i].setBounds(84 + i * 79, 33, 53, 51);
                this.gemstoneNumber[i].addMouseListener(new CancelMouseListener(i));
                this.add(this.gemstoneNumber[i]);
            }
            this.reelectGoods = new Goodstable();
            this.recastGemstone.setOpaque(false);
            this.gemstoneName.setOpaque(false);
            this.gemstoneOperationRecastTypePanel.setVisible(false);
            this.gemstoneName.setForeground(Color.gray);
            this.gemstoneName.setFont(new Font("宋体", 0, 13));
            this.gemstoneName.setHorizontalAlignment(0);
            this.gemstoneName.setBounds(84, 12, 53, 13);
            this.recastGemstone.setBounds(125, 136, 53, 51);
            this.reelectBtn.setBounds(118, 116, 68, 17);
            this.reacastBtn.setBounds(121, 210, 60, 26);
            this.add(this.gemstoneName);
            this.add(this.recastGemstone);
            this.add(this.reelectBtn);
            this.add(this.reacastBtn);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B267.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 310, 260, this);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/gemstone/宝石_鉴定&重铸w310,h260，left22，top57px.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 310, 260, this);
        }
    }
    
    public void changBtnIcon() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconRecastGrayBtn == null) {
                this.iconRecastGrayBtn = CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1);
            }
            ImageIcon[] iconsRecastBtn = null;
            try {
                iconsRecastBtn = CutButtonImage.cuts("inkImg/button/32.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            switch (GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().getTypePanel()) {
                case 2: {
                    this.reacastBtn.setTypeBtn(8);
                    this.reacastBtn.setText("重铸");
                    this.reacastBtn.setIcons(iconsRecastBtn);
                    this.reelectBtn.setVisible(true);
                    this.reacastBtn.setBtn(-1);
                    this.reelectBtn.setBtn(-1);
                    this.reacastBtn.setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", 59, 24));
                    this.reelectBtn.setIcon(CutButtonImage.getImage("inkImg/button/39.png", 68, 17));
                    this.recastGemstone.setIcon(null);
                    this.gemstoneName.setText("主宝石");
                    break;
                }
                case 3: {
                    this.reacastBtn.setTypeBtn(10);
                    this.reacastBtn.setText("鉴定");
                    this.reacastBtn.setIcons(iconsRecastBtn);
                    this.reacastBtn.setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", 59, 24));
                    this.reacastBtn.setBtn(-1);
                    this.reelectBtn.setVisible(false);
                    this.recastGemstone.setIcon(null);
                    this.gemstoneName.setText("奇异石");
                    break;
                }
            }
        }
        else {
            if (this.iconRecastGrayBtn == null) {
                this.iconRecastGrayBtn = CutButtonImage.getImage("img/xy2uiimg/112gray.png", 65, 24);
            }
            ImageIcon[] iconsRecastBtn = null;
            try {
                iconsRecastBtn = CutButtonImage.cuts("img/xy2uiimg/112.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            switch (GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().getTypePanel()) {
                case 2: {
                    this.reacastBtn.setTypeBtn(8);
                    this.reacastBtn.setText("重铸");
                    this.reacastBtn.setIcons(iconsRecastBtn);
                    this.reelectBtn.setVisible(true);
                    this.reacastBtn.setBtn(-1);
                    this.reelectBtn.setBtn(-1);
                    this.reacastBtn.setIcon(CutButtonImage.getImage("img/xy2uiimg/112gray.png", 65, 24));
                    this.reelectBtn.setIcon(CutButtonImage.getImage("img/gemstone/重选类型w68,h17px.png", 68, 17));
                    this.recastGemstone.setIcon(null);
                    this.gemstoneName.setText("主宝石");
                    break;
                }
                case 3: {
                    this.reacastBtn.setTypeBtn(10);
                    this.reacastBtn.setText("鉴定");
                    this.reacastBtn.setIcons(iconsRecastBtn);
                    this.reacastBtn.setIcon(CutButtonImage.getImage("img/xy2uiimg/112gray.png", 65, 24));
                    this.reacastBtn.setBtn(-1);
                    this.reelectBtn.setVisible(false);
                    this.recastGemstone.setIcon(null);
                    this.gemstoneName.setText("奇异石");
                    break;
                }
            }
        }
    }
    
    public JLabel getRecastGemstone() {
        return this.recastGemstone;
    }
    
    public void setRecastGemstone(JLabel recastGemstone) {
        this.recastGemstone = recastGemstone;
    }
    
    public JLabel getGemstoneName() {
        return this.gemstoneName;
    }
    
    public void setGemstoneName(JLabel gemstoneName) {
        this.gemstoneName = gemstoneName;
    }
    
    public GemstoneOperationBtn getReacastBtn() {
        return this.reacastBtn;
    }
    
    public void setReacastBtn(GemstoneOperationBtn reacastBtn) {
        this.reacastBtn = reacastBtn;
    }
    
    public GemstoneOperationBtn getReelectBtn() {
        return this.reelectBtn;
    }
    
    public void setReelectBtn(GemstoneOperationBtn reelectBtn) {
        this.reelectBtn = reelectBtn;
    }
    
    public JLabel[] getGemstoneNumber() {
        return this.gemstoneNumber;
    }
    
    public void setGemstoneNumber(JLabel[] gemstoneNumber) {
        this.gemstoneNumber = gemstoneNumber;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public ImageIcon getIconRecastGrayBtn() {
        return this.iconRecastGrayBtn;
    }
    
    public void setIconRecastGrayBtn(ImageIcon iconRecastGrayBtn) {
        this.iconRecastGrayBtn = iconRecastGrayBtn;
    }
    
    public Goodstable getReelectGoods() {
        return this.reelectGoods;
    }
    
    public void setReelectGoods(Goodstable reelectGoods) {
        this.reelectGoods = reelectGoods;
    }
    
    public GemstoneOperationRecastTypePanel getGemstoneOperationRecastTypePanel() {
        return this.gemstoneOperationRecastTypePanel;
    }
    
    public void setGemstoneOperationRecastTypePanel(GemstoneOperationRecastTypePanel gemstoneOperationRecastTypePanel) {
        this.gemstoneOperationRecastTypePanel = gemstoneOperationRecastTypePanel;
    }
}
