package org.come.lianhua;

import org.come.until.SrcollPanelUI;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.until.ScrollUIS;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.come.view.View;

public class AutoMaticRefiningView extends View
{
    private String backPath;
    private int width;
    private int heigth;
    private JList<String> jlist;
    private DefaultListModel<String> listModel;
    public static JScrollPane jScrollPane;
    private ImageIcon iconS;
    private ImageIcon iconH;
    private ImageIcon iconD;
    
    public JList<String> getJlist() {
        if (this.jlist == null) {
            (this.jlist = new JList<>()).setSelectionBackground(new Color(28, 22, 15, 226));
            this.jlist.setSelectionForeground(Color.white);
            this.jlist.setForeground(Color.white);
            this.jlist.setFont(UIUtils.TEXT_FONT1);
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
    
    public AutoMaticRefiningView(int width, int heigth, String[] rowData) {
        this.width = width;
        this.heigth = heigth;
        this.setPreferredSize(new Dimension(width, heigth));
        this.setLayout(null);
        this.setOpaque(true);
        (AutoMaticRefiningView.jScrollPane = new JScrollPane(this.getJlist())).setVerticalScrollBarPolicy(22);
        AutoMaticRefiningView.jScrollPane.getVerticalScrollBar().setUI(new ScrollUIS());
        AutoMaticRefiningView.jScrollPane.getViewport().setOpaque(false);
        AutoMaticRefiningView.jScrollPane.setOpaque(false);
        AutoMaticRefiningView.jScrollPane.setBounds(0, 0, width, heigth - 1);
        AutoMaticRefiningView.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        AutoMaticRefiningView.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(AutoMaticRefiningView.jScrollPane);
        if (rowData != null) {
            for (int i = 0; i < rowData.length; ++i) {
                this.getListModel().add(i, rowData[i]);
            }
        }
    }
    
    public void clearModelContent(String[] rowData) {
        this.getListModel().clear();
        if (rowData != null) {
            for (int i = 0; i < rowData.length; ++i) {
                this.getListModel().add(i, rowData[i]);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        AutoMaticRefiningView.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        AutoMaticRefiningView.jScrollPane.getViewport().setViewSize(new Dimension(this.jlist.getWidth(), this.jlist.getHeight() + 1));
        AutoMaticRefiningView.jScrollPane.setBounds(2, 4, this.width, this.heigth - 6);
        if (this.iconH == null) {
            this.iconH = new ImageIcon("img/background/zutoBox.png");
        }
        g.drawImage(this.iconH.getImage(), 0, 0, this.width, this.heigth, this);
    }
    
    public static JScrollPane getjScrollPane() {
        return AutoMaticRefiningView.jScrollPane;
    }
}
