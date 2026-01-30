package org.come.Jpanel;

import java.util.List;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.mouslisten.PetDepositJlistChoseMouslisten;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.DepositBtn;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class DepositListJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JList<String> listpet;
    private DefaultListModel<String> listModel;
    private DepositBtn btntop;
    private DepositBtn btnbottom;
    private DepositBtn btntopset;
    private DepositBtn btnbottomset;
    private JLabel labOpen;
    private JLabel labLocking;
    public JLabel labgundong;
    private ImageIcon icon;
    
    public DepositListJpanel() {
        (this.labgundong = new JLabel(new ImageIcon("sc/hongmu/2/gundongtiao_副本.png"))).setBounds(162, 64, 17, 169);
        this.setPreferredSize(new Dimension(293, 409));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("sc/old/2/1.png", 1, 63333);
        offBtn.setBounds(293, 5, 25, 25);
        this.add(offBtn);
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
        this.listpet.addMouseListener(new PetDepositJlistChoseMouslisten(this.listpet, this));
        this.listpet.addMouseMotionListener(new PetDepositJlistChoseMouslisten(this.listpet, this));
        (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBounds(53, 46, 194, 278);
        this.jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane);
        (this.labOpen = new JLabel()).setBounds(31, 261, 15, 15);
        this.add(this.labOpen);
        (this.labLocking = new JLabel()).setBounds(31, 282, 15, 15);
        this.add(this.labLocking);
        (this.btnbottomset = new DepositBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "取回", Integer.valueOf(2), this)).setBounds(118, 340, 59, 24);
        this.add(this.btnbottomset);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/B333.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 293, 409, this);
        this.btnbottomset.setBounds(118, 340, 59, 24);
    }
    
    public void init(List<String> list) {
        this.listModel.clear();
        for (int i = 0; i < list.size(); ++i) {
            this.listModel.addElement(list.get(i));
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
