package org.come.Jpanel;

import java.awt.Graphics;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

public class WuXingShuXingJpanel extends JPanel
{
    private DefaultListModel<String> dlm;
    private DefaultListModel<String> dlm1;
    private JLabel AreaTestJlabel;
    private JList<String> listNo1;
    private JList<String> listNo2;
    private ImageIcon icon;
    
    public DefaultListModel<String> getDlm() {
        return this.dlm;
    }
    
    public void setDlm(DefaultListModel<String> dlm) {
        this.dlm = dlm;
    }
    
    public DefaultListModel<String> getDlm1() {
        return this.dlm1;
    }
    
    public void setDlm1(DefaultListModel<String> dlm1) {
        this.dlm1 = dlm1;
    }
    
    public JLabel getAreaTestJlabel() {
        return this.AreaTestJlabel;
    }
    
    public void setAreaTestJlabel(JLabel areaTestJlabel) {
        this.AreaTestJlabel = areaTestJlabel;
    }
    
    public JList<String> getListNo1() {
        return this.listNo1;
    }
    
    public void setListNo1(JList<String> listNo1) {
        this.listNo1 = listNo1;
    }
    
    public JList<String> getListNo2() {
        return this.listNo2;
    }
    
    public void setListNo2(JList<String> listNo2) {
        this.listNo2 = listNo2;
    }
    
    public WuXingShuXingJpanel() {
        this.icon = new ImageIcon("img/xy2uiimg/kx_wx.png");
        this.setBackground(new Color(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(290, 100));
        this.setLayout(null);
        this.dlm = new DefaultListModel<>();
        this.dlm1 = new DefaultListModel<>();
        (this.listNo1 = new JList<>()).setBounds(20, 26, 130, 300);
        this.listNo1.setForeground(Color.white);
        this.listNo1.setFont(UIUtils.TEXT_FONT);
        this.listNo1.setBackground(UIUtils.Color_BACK);
        this.listNo1.setModel(this.dlm);
        this.listNo1.addMouseListener(new MouseListener() {
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
                if (e.isMetaDown()) {
                    FormsManagement.HideForm(8);
                }
            }
        });
        this.add(this.listNo1);
        (this.listNo2 = new JList<>()).setBounds(150, 26, 130, 300);
        this.listNo2.setForeground(Color.white);
        this.listNo2.setFont(UIUtils.TEXT_FONT);
        this.listNo2.setBackground(UIUtils.Color_BACK);
        this.listNo2.setModel(this.dlm1);
        this.listNo2.addMouseListener(new MouseListener() {
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
                if (e.isMetaDown()) {
                    FormsManagement.HideForm(8);
                }
            }
        });
        this.add(this.listNo2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.icon.getImage(), 0, 0, 290, 24, this);
    }
}
