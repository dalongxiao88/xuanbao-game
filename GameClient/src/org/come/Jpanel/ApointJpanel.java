package org.come.Jpanel;

import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JPanel;

public class ApointJpanel extends JPanel
{
    private RoleCaoZuoBtn btnqueren;
    private RoleCaoZuoBtn btnquxiao;
    private JLabel labname;
    private JLabel labRace;
    private JLabel labLevel;
    private JList<String> listposition;
    private DefaultListModel<String> listModel;
    private Color fontcolor;
    private JScrollPane jScrollPane;
    public static int index;
    private ImageIcon icon;
    
    public ApointJpanel() throws Exception {
        this.setPreferredSize(new Dimension(294, 344));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 37);
        offBtn.setBounds(274, 0, 25, 25);
        this.add(offBtn);
        (this.listModel = new DefaultListModel<>()).add(0, "护法");
        this.listModel.add(1, "长老");
        this.listModel.add(2, "堂主");
        this.listModel.add(3, "香主");
        this.listModel.add(4, "精英");
        this.listModel.add(5, "帮众");
        (this.labname = new JLabel()).setBounds(96, 45, 150, 16);
        this.labname.setForeground(Color.WHITE);
        this.labname.setFont(new Font("微软雅黑", 0, 14));
        this.add(this.labname);
        (this.labRace = new JLabel()).setBounds(96, 77, 150, 16);
        this.labRace.setForeground(Color.WHITE);
        this.labRace.setFont(new Font("微软雅黑", 0, 14));
        this.add(this.labRace);
        (this.labLevel = new JLabel()).setBounds(96, 108, 150, 16);
        this.labLevel.setForeground(Color.WHITE);
        this.labLevel.setFont(new Font("微软雅黑", 0, 14));
        this.add(this.labLevel);
        (this.btnqueren = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "确认", 0, UIUtils.COLOR_BTNPUTONG)).setBounds(48, 307, 68, 26);
        this.add(this.btnqueren);
        (this.btnquxiao = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "取消", 1, UIUtils.COLOR_BTNPUTONG)).setBounds(153, 307, 68, 26);
        this.add(this.btnquxiao);
        (this.listposition = new JList<String>() {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.fontcolor = Color.GREEN;
        this.listposition.setSelectionForeground(this.fontcolor);
        this.listposition.setForeground(this.fontcolor);
        this.listposition.setFont(new Font("楷体", 1, 16));
        this.listposition.setBackground(UIUtils.Color_BACK);
        this.listposition.setModel(this.listModel);
        (this.jScrollPane = new JScrollPane(this.listposition)).setVerticalScrollBarPolicy(21);
        this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBounds(37, 163, 216, 129);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xy2uiimg/21_png.xy2uiimg.grouptitle.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 294, 344, this);
    }
    
    public RoleCaoZuoBtn getBtnqueren() {
        return this.btnqueren;
    }
    
    public void setBtnqueren(RoleCaoZuoBtn btnqueren) {
        this.btnqueren = btnqueren;
    }
    
    public RoleCaoZuoBtn getBtnquxiao() {
        return this.btnquxiao;
    }
    
    public void setBtnquxiao(RoleCaoZuoBtn btnquxiao) {
        this.btnquxiao = btnquxiao;
    }
    
    public JList<String> getListposition() {
        return this.listposition;
    }
    
    public void setListposition(JList<String> listposition) {
        this.listposition = listposition;
    }
    
    public JList<String> getListpet() {
        return this.listposition;
    }
    
    public void setListpet(JList<String> listpet) {
    }
    
    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }
    
    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }
    
    public Color getFontcolor() {
        return this.fontcolor;
    }
    
    public void setFontcolor(Color fontcolor) {
        this.fontcolor = fontcolor;
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public JLabel getLabname() {
        return this.labname;
    }
    
    public void setLabname(JLabel labname) {
        this.labname = labname;
    }
    
    public JLabel getLabRace() {
        return this.labRace;
    }
    
    public void setLabRace(JLabel labRace) {
        this.labRace = labRace;
    }
    
    public JLabel getLabLevel() {
        return this.labLevel;
    }
    
    public void setLabLevel(JLabel labLevel) {
        this.labLevel = labLevel;
    }
    
    static {
        ApointJpanel.index = 0;
    }
}
