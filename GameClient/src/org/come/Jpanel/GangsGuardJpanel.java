package org.come.Jpanel;

import java.awt.Graphics;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.mouslisten.GangsGuardMouslisten;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GangsGuardJpanel extends JPanel
{
    private JLabel[] labResistance;
    private GangsGuardMouslisten[] guardMouslistens;
    private JLabel labCrap;
    private JLabel labPassby;
    public static ImageIcon icon;
    
    public GangsGuardJpanel() {
        this.labResistance = new JLabel[11];
        this.guardMouslistens = new GangsGuardMouslisten[11];
        this.setPreferredSize(new Dimension(600, 200));
        this.setLayout(null);
        for (int i = 0; i < 11; ++i) {
            int row = i % 5;
            int col = i / 5;
            (this.labResistance[i] = new JLabel()).setForeground(Color.green);
            this.labResistance[i].setBounds(20 + row * 116, 60 + col * 25, 70, 18);
            this.labResistance[i].setFont(UIUtils.TEXT_FONT);
            this.guardMouslistens[i] = new GangsGuardMouslisten(i);
            this.labResistance[i].addMouseListener(this.guardMouslistens[i]);
            this.add(this.labResistance[i]);
        }
        (this.labCrap = new JLabel()).setFont(new Font("宋体", 0, 14));
        this.labCrap.setBounds(11, 10, 340, 25);
        this.labCrap.setForeground(Color.white);
        this.labCrap.setText("我是帮派守护非常荣幸给您服务你想要什么样的服务。");
        this.add(this.labCrap);
        (this.labPassby = new JLabel()).setFont(UIUtils.TEXT_FONT);
        this.labPassby.setBounds(16, 155, 72, 18);
        this.labPassby.setForeground(Color.green);
        this.labPassby.setText("我只是路过。");
        this.labPassby.addMouseListener(new MouseListener() {
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
                FormsManagement.HideForm(53);
            }
        });
        this.add(this.labPassby);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (GangsGuardJpanel.icon == null) {
            GangsGuardJpanel.icon = new ImageIcon("img/xy2uiimg/95_png.xy2uiimg.npctalk.png");
        }
        g.drawImage(GangsGuardJpanel.icon.getImage(), 0, 0, 600, 200, this);
    }
    
    public JLabel[] getLabResistance() {
        return this.labResistance;
    }
    
    public void setLabResistance(JLabel[] labResistance) {
        this.labResistance = labResistance;
    }
    
    public JLabel getLabCrap() {
        return this.labCrap;
    }
    
    public void setLabCrap(JLabel labCrap) {
        this.labCrap = labCrap;
    }
    
    public JLabel getLabPassby() {
        return this.labPassby;
    }
    
    public void setLabPassby(JLabel labPassby) {
        this.labPassby = labPassby;
    }
}
