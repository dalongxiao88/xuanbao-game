package org.come.Jpanel;

import java.awt.Graphics;
import java.awt.Image;
import org.come.entity.Baby;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.come.mouslisten.BabyTalentMouslisten;
import javax.swing.JPanel;

public class TestChildTalentJpanel extends JPanel
{
    private BabyTalentMouslisten[] mouslistens;
    private JLabel[] jLabels;
    ImageIcon icon;
    
    public TestChildTalentJpanel() {
        this.mouslistens = new BabyTalentMouslisten[9];
        this.jLabels = new JLabel[9];
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(240, 215));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            for (int i = 0; i < 9; ++i) {
                this.jLabels[i] = new JLabel();
                this.mouslistens[i] = new BabyTalentMouslisten(i);
                this.jLabels[i].addMouseListener(this.mouslistens[i]);
                if (i < 3) {
                    this.jLabels[i].setBounds(18 + 72 * i, 28, 54, 52);
                }
                else if (i < 6) {
                    this.jLabels[i].setBounds(37 + 72 * (i - 3), 88, 20, 45);
                }
                else {
                    this.jLabels[i].setBounds(26 + 73 * (i - 6), 143, 40, 40);
                }
                this.add(this.jLabels[i]);
            }
        }
        else {
            this.setPreferredSize(new Dimension(240, 214));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            for (int i = 0; i < 9; ++i) {
                this.jLabels[i] = new JLabel();
                this.mouslistens[i] = new BabyTalentMouslisten(i);
                this.jLabels[i].addMouseListener(this.mouslistens[i]);
                if (i < 3) {
                    this.jLabels[i].setBounds(16 + 72 * i, 24, 54, 52);
                }
                else if (i < 6) {
                    this.jLabels[i].setBounds(30 + 70 * (i - 3), 88, 20, 45);
                }
                else {
                    this.jLabels[i].setBounds(25 + 70 * (i - 6), 140, 40, 40);
                }
                this.add(this.jLabels[i]);
            }
        }
    }
    
    public void showBaby(Baby baby) {
        for (int i = 0; i < 9; ++i) {
            this.jLabels[i].setIcon(null);
        }
        if (baby != null) {
            String Talents = baby.getTalents();
            if (Talents != null && !Talents.equals("")) {
                String[] v = Talents.split("\\|");
                for (int j = 0; j < v.length; ++j) {
                    String[] vs = v[j].split("=");
                    ImageIcon icon = this.getTalents(vs[0]);
                    Image img = icon.getImage().getScaledInstance(55, 55, 1);
                    this.jLabels[j].setIcon(new ImageIcon(img));
                    if (Integer.parseInt(vs[0]) > 3) {
                        img = icon.getImage().getScaledInstance(40, 40, 1);
                        this.jLabels[j + 6].setIcon(new ImageIcon(img));
                    }
                }
            }
        }
    }
    
    public ImageIcon getTalents(String id) {
        return new ImageIcon("img/skill/" + id + ".png");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B218.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 240, 215, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/121_png.xy2uiimg.tz_bg.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 240, 214, this);
        }
    }
}
