package org.come.login;

import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Component;
import org.come.until.Util;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.List;
import org.come.view.View;

public class DownView extends View
{
    public List<JLabel> jLabels;
    public List<JLabel> jLabels1;
    private ImageIcon icon;
    
    public DownView(LoginJpanel loginJpanel) {
        this.icon = new ImageIcon("img/xy2uiimg/窗口_下拉框0000_旋转.png");
        this.setPreferredSize(new Dimension(213, 122));
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setOpaque(false);
        this.cshuser(Util.readUserPwd().split("\\|"), loginJpanel);
    }
    
    public void cshuser(String[] v, LoginJpanel loginJpanel) {
        if (this.jLabels != null) {
            for (int i = 0; i < this.jLabels.size(); ++i) {
                this.remove((Component)this.jLabels.get(i));
                this.remove((Component)this.jLabels1.get(i));
            }
        }
        this.jLabels = new ArrayList<>();
        this.jLabels1 = new ArrayList<>();
        if (!v[0].equals("")) {
            for (int i = 0; i < v.length; ++i) {
                JLabel jLabel = new JLabel();
                jLabel.setText(v[i].split("=")[0]);
                jLabel.setFont(new Font("宋体", 0, 14));
                jLabel.setBounds(5, 3 + i * 20, 193, 20);
                jLabel.setForeground(Color.WHITE);
                jLabel.addMouseListener(new DownMouslisten(loginJpanel, v[i], jLabel, 0));
                this.add(jLabel);
                this.jLabels.add(jLabel);
                JLabel jLabel2 = new JLabel();
                jLabel2.setIcon(new ImageIcon("inkImg/background/X.png"));
                jLabel2.setBounds(198, 5 + i * 20, 17, 16);
                jLabel2.addMouseListener(new DownMouslisten(loginJpanel, v[i], jLabel2, 1));
                this.add(jLabel2);
                this.jLabels1.add(jLabel2);
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(this.icon.getImage(), 0, 0, this.icon.getIconWidth(), this.icon.getIconHeight(), null);
        super.paint(g);
    }
}
