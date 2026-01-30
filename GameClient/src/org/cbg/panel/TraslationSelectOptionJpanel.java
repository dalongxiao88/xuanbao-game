package org.cbg.panel;

import org.come.until.CutButtonImage;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

public class TraslationSelectOptionJpanel extends JPanel
{
    private String backPath;
    private int width;
    private int heigth;
    private JList<String> jlist;
    private DefaultListModel<String> listModel;
    private ImageIcon icon;
    
    public JList<String> getJlist() {
        if (this.jlist == null) {
            (this.jlist = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
            this.jlist.setSelectionForeground(Color.white);
            this.jlist.setForeground(Color.white);
            this.jlist.setFont(UIUtils.TEXT_HY16);
            this.jlist.setBackground(UIUtils.Color_BACK);
            this.jlist.setOpaque(false);
            this.jlist.setModel(this.getListModel());
        }
        return this.jlist;
    }
    
    public void setJlist(JList<String> jlist) {
        this.jlist = jlist;
    }
    
    public DefaultListModel<String> getListModel() {
        if (this.listModel == null) {
            this.listModel = new DefaultListModel<>();
        }
        return this.listModel;
    }
    
    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }
    
    public String getBackPath() {
        return this.backPath;
    }
    
    public void setBackPath(String backPath) {
        this.backPath = backPath;
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeigth() {
        return this.heigth;
    }
    
    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }
    
    public TraslationSelectOptionJpanel(int width, int heigth, String backPath, String[] rowData) {
        this.width = width;
        this.heigth = heigth;
        this.backPath = backPath;
        this.setPreferredSize(new Dimension(width, heigth));
        this.setLayout(null);
        this.setOpaque(true);
        JScrollPane jScrollPane = new JScrollPane(this.getJlist());
        jScrollPane.setVerticalScrollBarPolicy(22);
        jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setOpaque(false);
        jScrollPane.setBounds(0, 0, width + 2, heigth - 1);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(jScrollPane);
        for (int i = 0; i < rowData.length; ++i) {
            this.getListModel().add(i, rowData[i]);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = CutButtonImage.getImage(this.backPath, this.width, this.heigth);
        }
        g.drawImage(this.icon.getImage(), 0, 0, this.width, this.heigth, this);
    }
}
