package org.come.summonequip;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;

import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

public class JpanelNowEquip extends JPanel
{
    private JLabel[] equipLabs;
    private JLabel showName;
    private BtnSummonEquipMain downBtn;
    private BtnSummonEquipMain leftBtn;
    private BtnSummonEquipMain rightBtn;
    private JpanelSummonEquipMain jpanelSummonEquipMain;
    private List<BigDecimal> summonEquipList;
    private int nowPage;
    private ImageIcon iconBack;
    
    public JpanelNowEquip(JpanelSummonEquipMain jpanelSummonEquipMain) {
        this.nowPage = 1;
        this.jpanelSummonEquipMain = jpanelSummonEquipMain;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(330, 149));
        }
        else {
            this.setPreferredSize(new Dimension(330, 150));
        }
        class MListener
        implements MouseListener {
            private JpanelNowEquip jpanelNowEquip;

            public MListener(JpanelNowEquip jpanelNowEquip) {
                this.jpanelNowEquip = jpanelNowEquip;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 3) {
                    this.jpanelNowEquip.setVisible(false);
                    this.jpanelNowEquip.getJpanelSummonEquipMain().getScrollPane().setVisible(false);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        }
        this.setOpaque(false);
        this.setLayout(null);
        this.addMouseListener(new MListener(this));
        this.getSummonEquipList();
        this.getEquipLabs();
        this.getDownBtn();
        this.getShowName();
        this.getRightBtn();
        this.getLeftBtn();
    }
    
    public void drawSummonEquips(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = (this.nowPage - 1) * 12; i < this.summonEquipList.size(); ++i) {
                if (GoodsListFromServerUntil.fushis.get(this.summonEquipList.get(i)) == null) {
                    this.summonEquipList.remove(i);
                    --i;
                }
                else {
                    g.drawImage(GoodsListFromServerUntil.imgpathAdaptive(((Goodstable)GoodsListFromServerUntil.fushis.get(this.summonEquipList.get(i))).getSkin(), 50, 50).getImage(), 11 + (i - (this.nowPage - 1) * 12) % 6 * 51, 15 + (i - (this.nowPage - 1) * 12) / 6 * 51, 50, 50, null);
                    if (i >= 11 + (this.nowPage - 1) * 12) {
                        return;
                    }
                }
            }
        }
        else {
            for (int i = (this.nowPage - 1) * 12; i < this.summonEquipList.size(); ++i) {
                if (GoodsListFromServerUntil.fushis.get(this.summonEquipList.get(i)) == null) {
                    this.summonEquipList.remove(i);
                    --i;
                }
                else {
                    g.drawImage(GoodsListFromServerUntil.imgpathAdaptive(((Goodstable)GoodsListFromServerUntil.fushis.get(this.summonEquipList.get(i))).getSkin(), 50, 50).getImage(), 11 + (i - (this.nowPage - 1) * 12) % 6 * 51, 15 + (i - (this.nowPage - 1) * 12) / 6 * 51, 50, 50, null);
                    if (i >= 11 + (this.nowPage - 1) * 12) {
                        return;
                    }
                }
            }
        }
    }
    
    public Goodstable getSummonEquipOne(int type) {
        type += (this.nowPage - 1) * 12;
        for (int i = (this.nowPage - 1) * 12; i < this.summonEquipList.size(); ++i) {
            if (GoodsListFromServerUntil.fushis.get(this.summonEquipList.get(i)) != null && i == type) {
                return (Goodstable)GoodsListFromServerUntil.fushis.get(this.summonEquipList.get(i));
            }
        }
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/background1/B298.png", 330, 149);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 330, 149, this);
            this.drawSummonEquips(g);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("img/xy2uiimg/已穿装备_底板_w330,h150.png", 330, 150);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 330, 149, this);
            this.drawSummonEquips(g);
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel[] getEquipLabs() {
        if (this.equipLabs == null) {
            this.equipLabs = new JLabel[12];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.equipLabs.length; ++i) {
                    (this.equipLabs[i] = new JLabel()).setBounds(11 + i % 6 * 51, 15 + i / 6 * 51, 50, 50);
                    this.equipLabs[i].addMouseListener(new MouseListenerNowEquip(i, this));
                    this.add(this.equipLabs[i]);
                }
            }
            else {
                for (int i = 0; i < this.equipLabs.length; ++i) {
                    (this.equipLabs[i] = new JLabel()).setBounds(11 + i % 6 * 51, 15 + i / 6 * 51, 50, 50);
                    this.equipLabs[i].addMouseListener(new MouseListenerNowEquip(i, this));
                    this.add(this.equipLabs[i]);
                }
            }
        }
        return this.equipLabs;
    }
    
    public void setEquipLabs(JLabel[] equipLabs) {
        this.equipLabs = equipLabs;
    }
    
    public JLabel getShowName() {
        if (this.showName == null) {
            (this.showName = new JLabel()).setBounds(12, 124, 144, 14);
            this.showName.setForeground(Color.white);
            this.showName.setFont(UIUtils.TEXT_FONT1);
            this.add(this.showName);
        }
        return this.showName;
    }
    
    public void setShowName(JLabel showName) {
        this.showName = showName;
    }
    
    public BtnSummonEquipMain getDownBtn() {
        if (this.downBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.downBtn = new BtnSummonEquipMain("inkImg/button/8.png", 1, "", 20, this.jpanelSummonEquipMain)).setBounds(139, 123, 18, 18);
            }
            else {
                (this.downBtn = new BtnSummonEquipMain("img/xy2uiimg/34_png.button.xy_vscroll$down.png", 1, "", 20, this.jpanelSummonEquipMain)).setBounds(141, 123, 19, 20);
            }
            this.add(this.downBtn);
        }
        return this.downBtn;
    }
    
    public void setDownBtn(BtnSummonEquipMain downBtn) {
        this.downBtn = downBtn;
    }
    
    public JpanelSummonEquipMain getJpanelSummonEquipMain() {
        return this.jpanelSummonEquipMain;
    }
    
    public void setJpanelSummonEquipMain(JpanelSummonEquipMain jpanelSummonEquipMain) {
        this.jpanelSummonEquipMain = jpanelSummonEquipMain;
    }
    
    public List<BigDecimal> getSummonEquipList() {
        if (this.summonEquipList == null) {
            this.summonEquipList = new ArrayList<>();
        }
        return this.summonEquipList;
    }
    
    public void setSummonEquipList(List<BigDecimal> summonEquipList) {
        this.summonEquipList = summonEquipList;
    }
    
    public BtnSummonEquipMain getLeftBtn() {
        if (this.leftBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.leftBtn = new BtnSummonEquipMain("inkImg/button/10.png", 1, "", 21, this)).setBounds(270, 125, 18, 18);
            }
            else {
                (this.leftBtn = new BtnSummonEquipMain("img/xy2uiimg/29_png.button.btn_8.png", 1, "", 21, this)).setBounds(270, 125, 19, 20);
            }
            this.add(this.leftBtn);
        }
        return this.leftBtn;
    }
    
    public void setLeftBtn(BtnSummonEquipMain leftBtn) {
        this.leftBtn = leftBtn;
    }
    
    public BtnSummonEquipMain getRightBtn() {
        if (this.rightBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.rightBtn = new BtnSummonEquipMain("inkImg/button/9.png", 1, "", 22, this)).setBounds(295, 125, 18, 18);
            }
            else {
                (this.rightBtn = new BtnSummonEquipMain("img/xy2uiimg/36_png.button.btn_7.png", 1, "", 22, this)).setBounds(295, 125, 18, 18);
            }
            this.add(this.rightBtn);
        }
        return this.rightBtn;
    }
    
    public void setRightBtn(BtnSummonEquipMain rightBtn) {
        this.rightBtn = rightBtn;
    }
    
    public int getNowPage() {
        return this.nowPage;
    }
    
    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }
}
