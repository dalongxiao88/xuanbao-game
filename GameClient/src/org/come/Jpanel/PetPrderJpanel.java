package org.come.Jpanel;

import java.util.List;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.JLabel;
import com.tool.btn.PetOrderBtn;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class PetPrderJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JList<String> listpet;
    private DefaultListModel<String> listModel;
    private PetOrderBtn btntop;
    private PetOrderBtn btnbottom;
    private PetOrderBtn btntopset;
    private PetOrderBtn btnbottomset;
    private JLabel labOpen;
    private JLabel labLocking;
    public JLabel labgundong;
    FormsOnOffBtn offBtn;
    private ImageIcon icon;
    
    public PetPrderJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(293, 409));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 621)).setBounds(256, 10, 25, 25);
            this.add(this.offBtn);
        }
        else {
            this.setPreferredSize(new Dimension(276, 441));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 621)).setBounds(276, 5, 25, 25);
            this.add(this.offBtn);
        }
        this.listModel = new DefaultListModel<>();
        (this.listpet = new JList<String>() {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.listpet.setFont(new Font("微软雅黑", 1, 14));
        this.listpet.setBackground(UIUtils.Color_BACK);
        this.listpet.setForeground(Color.green);
        this.listpet.setSelectionForeground(Color.green);
        this.listpet.setModel(this.listModel);
        (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        if (MyIsif.getStyle().equals("水墨")) {
            this.jScrollPane.setBounds(50, 45, 198, 320);
        }
        else {
            this.jScrollPane.setBounds(30, 46, 191, 274);
            this.jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
        }
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane);
        if (MyIsif.getStyle().equals("水墨")) {
            (this.btntop = new PetOrderBtn("inkImg/button/B42.png", 1, 0, this)).setBounds(258, 118, 17, 17);
            this.add(this.btntop);
            (this.btnbottom = new PetOrderBtn("inkImg/button/B43.png", 1, 1, this)).setBounds(258, 147, 17, 17);
            this.add(this.btnbottom);
            (this.btntopset = new PetOrderBtn("inkImg/button/19.png", 1, 2, "顶", this)).setBounds(258, 201, 18, 18);
            this.add(this.btntopset);
            (this.btnbottomset = new PetOrderBtn("inkImg/button/19.png", 1, 3, "底", this)).setBounds(258, 230, 18, 18);
            this.add(this.btnbottomset);
        }
        else {
            (this.labOpen = new JLabel()).setBounds(31, 261, 15, 15);
            this.add(this.labOpen);
            (this.labLocking = new JLabel()).setBounds(31, 282, 15, 15);
            this.add(this.labLocking);
            (this.btntop = new PetOrderBtn("img/xy2uiimg/42_png.button.btn_1.png", 1, 0, this)).setBounds(238, 118, 20, 20);
            this.add(this.btntop);
            (this.btnbottom = new PetOrderBtn("img/xy2uiimg/34_png.button.xy_vscroll$down.png", 1, 1, this)).setBounds(238, 147, 20, 20);
            this.add(this.btnbottom);
            (this.btntopset = new PetOrderBtn("img/xy2uiimg/topset.png", 1, 2, this)).setBounds(238, 201, 17, 17);
            this.add(this.btntopset);
            (this.btnbottomset = new PetOrderBtn("img/xy2uiimg/bottomset.png", 1, 3, this)).setBounds(238, 230, 17, 17);
            this.add(this.btnbottomset);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B332.png");
            g.drawImage(this.icon.getImage(), 0, 0, 293, 409, this);
        }
        else {
            this.icon = new ImageIcon("inkImg/hongmu/Bpx.png");
            g.drawImage(this.icon.getImage(), 0, 0, this);
        }
    }
    
    public void init(List<String> list) {
        int selectedIndex = this.listpet.getSelectedIndex();
        this.init(list, selectedIndex);
    }
    
    public void init(List<String> list, int index) {
        this.listModel.clear();
        for (int i = 0; i < list.size(); ++i) {
            this.listModel.addElement(list.get(i));
        }
        if (index != -1) {
            this.listpet.setSelectedIndex(index);
        }
    }
    
    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }
    
    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }
    
    public JList<String> getListpet() {
        return this.listpet;
    }
    
    public void setListpet(JList<String> listpet) {
        this.listpet = listpet;
    }
}
