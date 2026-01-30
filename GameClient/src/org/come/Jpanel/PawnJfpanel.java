package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import org.come.until.EquipTool;
import org.come.mouslisten.SurePawnMouslisten;
import org.come.entity.Goodstable;
import javax.swing.BorderFactory;
import java.awt.Font;
import org.come.mouslisten.PawnChoseMouslisten;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.goodbtn;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import com.tool.btn.RoleOperationPanelBtn;
import javax.swing.JPanel;

public class PawnJfpanel extends JPanel
{
    private RoleOperationPanelBtn SurePawn;
    private JTextArea goodsCount;
    private JTextArea goodsSum;
    private JLabel[] goodsJlabel;
    private int goodPosition;
    private goodbtn[] btnrights;
    private int xz;
    private ImageIcon icon;
    
    public PawnJfpanel() throws Exception {
        this.goodsJlabel = new JLabel[24];
        this.xz = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBackground(UIUtils.Color_BACK);
            this.setPreferredSize(new Dimension(418, 379));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 29);
            offBtn.setBounds(381, 10, 25, 25);
            this.add(offBtn);
            (this.SurePawn = new RoleOperationPanelBtn("inkImg/button1/B22.png", 1, "典 当", null, null)).setBounds(143, 325, 99, 24);
            this.add(this.SurePawn);
            (this.goodsCount = new JTextArea("0")).setOpaque(false);
            this.goodsCount.setBounds(143, 264, 140, 18);
            this.goodsCount.setForeground(Color.yellow);
            this.goodsCount.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = PawnJfpanel.this.goodsCount.getText();
                    if (str.length() == 0) {
                        PawnJfpanel.this.goodsCount.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.add(this.goodsCount);
            (this.goodsSum = new JTextArea("0")).setBounds(143, 289, 140, 18);
            this.goodsSum.setOpaque(false);
            this.goodsSum.setForeground(Color.yellow);
            this.add(this.goodsSum);
            int a = 0;
            int b = -1;
            for (int i = 0; i < 24; ++i) {
                if (i > 0 && i % 6 == 0) {
                    ++a;
                    b = -1;
                }
                ++b;
                (this.goodsJlabel[i] = new JLabel()).setBounds(49 + b * 51, 33 + a * 51, 50, 50);
                this.goodsJlabel[i].addMouseListener(new PawnChoseMouslisten(i, this));
                this.add(this.goodsJlabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(356, 33 + i * 35, 35, 31);
                this.add(this.btnrights[i]);
            }
        }
        else {
            this.setBackground(UIUtils.Color_BACK);
            this.setPreferredSize(new Dimension(345, 370));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 29);
            offBtn.setBounds(325, 0, 23, 23);
            this.add(offBtn);
            (this.SurePawn = new RoleOperationPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "典 当", null)).setBounds(143, 325, 84, 24);
            this.add(this.SurePawn);
            (this.goodsCount = new JTextArea("0")).setOpaque(false);
            this.goodsCount.setBounds(143, 268, 140, 18);
            this.goodsCount.setForeground(Color.WHITE);
            this.goodsCount.setFont(new Font("宋体", 0, 14));
            this.goodsCount.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = PawnJfpanel.this.goodsCount.getText();
                    if (str.length() == 0) {
                        PawnJfpanel.this.goodsCount.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.add(this.goodsCount);
            (this.goodsSum = new JTextArea("0")).setBounds(142, 296, 140, 18);
            this.goodsSum.setOpaque(false);
            this.goodsSum.setForeground(Color.WHITE);
            this.goodsSum.setFont(new Font("宋体", 0, 14));
            this.add(this.goodsSum);
            int a = 0;
            int b = -1;
            for (int i = 0; i < 24; ++i) {
                if (i > 0 && i % 6 == 0) {
                    ++a;
                    b = -1;
                }
                ++b;
                (this.goodsJlabel[i] = new JLabel()).setBounds(17 + b * 51, 45 + a * 51, 50, 50);
                this.goodsJlabel[i].addMouseListener(new PawnChoseMouslisten(i, this));
                this.add(this.goodsJlabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, i)).setBounds(326, 43 + i * 33, 14, 33);
                this.add(this.btnrights[i]);
            }
        }
    }
    
    public void qingchu() {
        this.xuanzhong(null, -1, false);
    }
    
    public void PaintingText(int shopPlace) {
        this.goodsJlabel[shopPlace].setBorder(BorderFactory.createLineBorder(Color.red));
    }
    
    public void ClearText(int shopPlace) {
        if (this.xz != shopPlace) {
            this.goodsJlabel[shopPlace].setBorder(null);
        }
    }
    
    public void xuanzhong(Goodstable goodstable, int shopPlace, boolean isAll) {
        if (goodstable == null) {
            if (this.xz != -1) {
                this.goodsJlabel[this.xz].setBorder(null);
            }
            shopPlace = this.xz;
            this.xz = -1;
            this.ClearText(shopPlace);
            this.goodsCount.setText("0");
            SurePawnMouslisten.setGoodstable(null);
        }
        else if (!EquipTool.isEquip(goodstable.getType()) && SurePawnMouslisten.getGoodstable() != null && goodstable.getGoodsid().intValue() == SurePawnMouslisten.getGoodstable().getGoodsid().intValue()) {
            if (isAll) {
                this.goodsCount.setText(goodstable.getUsetime() + "");
            }
            else {
                int sum = Integer.parseInt(this.goodsCount.getText());
                if ((int)goodstable.getUsetime() > sum) {
                    ++sum;
                }
                else {
                    sum = (int)goodstable.getUsetime();
                }
                this.goodsCount.setText(sum + "");
            }
        }
        else {
            if (this.xz != -1) {
                this.goodsJlabel[this.xz].setBorder(null);
            }
            this.xz = shopPlace;
            this.goodsJlabel[this.xz].setBorder(BorderFactory.createLineBorder(Color.red));
            SurePawnMouslisten.setGoodstable(goodstable);
            this.goodsCount.setText("1");
        }
        if (this.xz != -1) {
            this.goodsJlabel[this.xz].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B307.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 418, 379, this);
            GoodsListFromServerUntil.draw(g, 49, 33);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/20_png.xy2uiimg.push.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 345, 370, this);
            GoodsListFromServerUntil.draw(g, 17, 45);
        }
    }
    
    public RoleOperationPanelBtn getSurePawn() {
        return this.SurePawn;
    }
    
    public void setSurePawn(RoleOperationPanelBtn surePawn) {
        this.SurePawn = surePawn;
    }
    
    public JTextArea getGoodsCount() {
        return this.goodsCount;
    }
    
    public void setGoodsCount(JTextArea goodsCount) {
        this.goodsCount = goodsCount;
    }
    
    public JTextArea getGoodsSum() {
        return this.goodsSum;
    }
    
    public void setGoodsSum(JTextArea goodsSum) {
        this.goodsSum = goodsSum;
    }
    
    public JLabel[] getGoodsJlabel() {
        return this.goodsJlabel;
    }
    
    public void setGoodsJlabel(JLabel[] goodsJlabel) {
        this.goodsJlabel = goodsJlabel;
    }
    
    public int getGoodPosition() {
        return this.goodPosition;
    }
    
    public void setGoodPosition(int goodPosition) {
        this.goodPosition = goodPosition;
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
}
