package org.skill.panel;

import org.come.until.SrcollPanelUI;
import com.updateNew.MyIsif;
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
import javax.swing.JPanel;

public class SetupJpanel3 extends JPanel
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
            (this.jlist = new JList<>()).setSelectionBackground(new Color(122, 117, 112));
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
    
    public SetupJpanel3(int width, int heigth, String[] rowData) {
        this.width = width;
        this.heigth = heigth;
        this.setPreferredSize(new Dimension(width, heigth));
        this.setLayout(null);
        this.setOpaque(true);
        (SetupJpanel3.jScrollPane = new JScrollPane(this.getJlist())).setVerticalScrollBarPolicy(22);
        SetupJpanel3.jScrollPane.getVerticalScrollBar().setUI(new ScrollUIS());
        SetupJpanel3.jScrollPane.getViewport().setOpaque(false);
        SetupJpanel3.jScrollPane.setOpaque(false);
        SetupJpanel3.jScrollPane.setBounds(0, 0, width, heigth - 1);
        SetupJpanel3.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        SetupJpanel3.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(SetupJpanel3.jScrollPane);
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
        if (MyIsif.getStyle().equals("水墨")) {
            SetupJpanel3.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            SetupJpanel3.jScrollPane.getViewport().setViewSize(new Dimension(this.jlist.getWidth(), this.jlist.getHeight() + 1));
            SetupJpanel3.jScrollPane.setBounds(2, 4, this.width, this.heigth - 6);
            this.iconH = new ImageIcon("inkImg/background1/xln100x.png");
            g.drawImage(this.iconH.getImage(), 0, 0, this.width, this.heigth, this);
        }
        else {
            SetupJpanel3.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            SetupJpanel3.jScrollPane.getViewport().setViewSize(new Dimension(this.jlist.getWidth(), this.jlist.getHeight() + 1));
            SetupJpanel3.jScrollPane.setBounds(2, 4, this.width, this.heigth - 6);
            this.iconH = new ImageIcon("inkImg/hongmu1/xln100xh.png");
            g.drawImage(this.iconH.getImage(), 0, 0, this.width, this.heigth, this);
        }
    }
    
    public static JScrollPane getjScrollPane() {
        return SetupJpanel3.jScrollPane;
    }
    
    public static void setjScrollPane(JScrollPane jScrollPane) {
        SetupJpanel3.jScrollPane = jScrollPane;
    }
}
