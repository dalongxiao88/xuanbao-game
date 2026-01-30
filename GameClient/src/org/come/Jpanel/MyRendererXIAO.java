package org.come.Jpanel;

import org.come.until.Util;
import org.come.entity.Mount;
import java.awt.Font;
import javax.swing.ImageIcon;
import com.updateNew.MyIsif;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import java.util.Map;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

class MyRendererXIAO extends JPanel implements ListCellRenderer
{
    private int index;
    public static String vs;
    private Object value;
    private Map<String, Integer> map;
    private boolean isSelected;
    private static DefaultListModel<String> listModel;
    
    public MyRendererXIAO() {
    }
    
    public MyRendererXIAO(int index, Object value, boolean isSelected) {
        this.index = index;
        this.isSelected = isSelected;
        this.value = value;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), 24);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        MyRendererXIAO mr = new MyRendererXIAO(index, value, isSelected);
        mr.removeAll();
        if (mountJPanel.idx2 == index || isSelected) {
            mr.setBackground(Color.GRAY);
        }
        else {
            mr.setOpaque(false);
        }
        mr.setLayout(new FlowLayout(0));
        Box box = Box.createHorizontalBox();
        mr.add(box);
        return (Component)mr;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if (MyIsif.getStyle().equals("水墨")) {
            g2d.setColor(new Color(250, 250, 250));
            ImageIcon peiimg = new ImageIcon("inkImg/button1/K158.png");
            g.drawImage(peiimg.getImage(), 1, 3, 34, 17, null);
        }
        else {
            g2d.setColor(new Color(187, 165, 75));
            ImageIcon peiimg = new ImageIcon("inkImg/hongmu/ss661.png");
            g.drawImage(peiimg.getImage(), 1, 3, 34, 17, null);
        }
        g2d.setFont(new Font("宋体", 0, 12));
        int index1 = (int)((Mount)ZhuJpanel.getListMount().get(this.index)).getMountid();
        g2d.drawString(Util.number2Chinese(index1) + "座", 5, 15);
        g2d.setFont(new Font("宋体", 0, 12));
        if (this.isSelected) {
            g2d.setColor(Color.green);
            g2d.drawString(this.value + "", 40, 16);
        }
        else {
            g2d.setColor(Color.white);
            g2d.drawString(this.value + "", 40, 16);
        }
    }
}
