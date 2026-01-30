package org.come.Jpanel;

import java.awt.event.MouseEvent;
import org.come.until.DDGoodUntil;
import java.awt.Graphics;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.mouslisten.TakeBackGoodMouslisten;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class TakeBackPawnGoodsJpanel2 extends JPanel implements MouseListener
{
    private JLabel[] labgoods;
    private FormsOnOffBtn orderflag;
    private TakeBackGoodMouslisten[] backGoodMouslistens;
    private JLabel[] jabs;
    private ImageIcon icon;
    
    public TakeBackPawnGoodsJpanel2() throws Exception {
        this.labgoods = new JLabel[42];
        this.backGoodMouslistens = new TakeBackGoodMouslisten[42];
        this.jabs = new JLabel[7];
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(386, 440));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 1001);
            offBtn.setBounds(343, 10, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < 42; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.labgoods[i] = new JLabel()).setBounds(50 + row * 51, 33 + col * 51, 50, 50);
                this.add(this.labgoods[i]);
                this.backGoodMouslistens[i] = new TakeBackGoodMouslisten(i);
                this.labgoods[i].addMouseListener(this.backGoodMouslistens[i]);
            }
            ImageIcon imageIcon = new ImageIcon("inkImg/button/B162.png");
            for (int j = 0; j < this.jabs.length; ++j) {
                (this.jabs[j] = new JLabel()).setBounds(47 + 41 * j, 396, 39, 23);
                this.jabs[j].setIcon(imageIcon);
                this.jabs[j].setBackground(UIUtils.Color_BACK);
                this.jabs[j].setText(j + 1 + "");
                this.jabs[j].addMouseListener(new fy(j + 1));
                this.jabs[j].setHorizontalTextPosition(0);
                this.jabs[j].setFont(UIUtils.TEXT_FONT1);
                this.jabs[j].setForeground(Color.WHITE);
                this.add(this.jabs[j]);
            }
            (this.orderflag = new FormsOnOffBtn("inkImg/button/19.png", 1, UIUtils.COLOR_BLACK, "整", 805)).setBounds(340, 398, 19, 19);
            this.add(this.orderflag);
        }
        else {
            this.setPreferredSize(new Dimension(380, 439));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 1001);
            offBtn.setBounds(331, 0, 23, 23);
            this.add(offBtn);
            for (int i = 0; i < 42; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.labgoods[i] = new JLabel()).setBounds(20 + row * 51, 45 + col * 51, 50, 50);
                this.add(this.labgoods[i]);
                this.backGoodMouslistens[i] = new TakeBackGoodMouslisten(i);
                this.labgoods[i].addMouseListener(this.backGoodMouslistens[i]);
            }
            ImageIcon imageIcon = new ImageIcon("img/xy2uiimg/93_png.xy2uiimg.label_chat.png");
            for (int j = 0; j < this.jabs.length; ++j) {
                (this.jabs[j] = new JLabel()).setBounds(20 + 43 * j, 405, 41, 25);
                this.jabs[j].setIcon(imageIcon);
                this.jabs[j].setBackground(UIUtils.Color_BACK);
                this.jabs[j].setText(String.valueOf(j + 1));
                this.jabs[j].addMouseListener(new fy(j + 1));
                this.jabs[j].setHorizontalTextPosition(0);
                this.jabs[j].setFont((j == 0) ? UIUtils.TEXT_FONT1B : UIUtils.TEXT_FONT1);
                this.jabs[j].setForeground((j == 0) ? Color.YELLOW : Color.WHITE);
                this.add(this.jabs[j]);
            }
            (this.orderflag = new FormsOnOffBtn("inkImg/hongmu/a3.png", 1, UIUtils.COLOR_BTNPUTONG, "整", 805)).setBounds(320, 390, 19, 19);
            this.add(this.orderflag);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B308.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 386, 440, this);
            DDGoodUntil.draw(g, 50, 33);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/30_png.xy2uiimg.depot.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 355, 436, this);
            DDGoodUntil.draw(g, 20, 45);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    class fy implements MouseListener
    {
        private int y;
        
        public fy(int y) {
            this.y = y;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < TakeBackPawnGoodsJpanel2.this.jabs.length; ++i) {
                if (this.y - 1 == i) {
                    TakeBackPawnGoodsJpanel2.this.jabs[i].setForeground(Color.YELLOW);
                    TakeBackPawnGoodsJpanel2.this.jabs[i].setFont(UIUtils.TEXT_FONT1B);
                }
                else {
                    TakeBackPawnGoodsJpanel2.this.jabs[i].setForeground(Color.WHITE);
                    TakeBackPawnGoodsJpanel2.this.jabs[i].setFont(UIUtils.TEXT_FONT1);
                }
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            DDGoodUntil.ys = this.y;
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
