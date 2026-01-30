package org.come.Jpanel;

import java.util.List;
import org.come.bean.LoginResult;
import org.come.until.CutButtonImage;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.ChooseLhBtn;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class ChooseLiangHaoTypeJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JList<Integer> listLh;
    private DefaultListModel<Integer> listModel;
    private ChooseLhBtn btntop;
    private ChooseLhBtn btnbottom;
    private ChooseLhBtn btntopset;
    private ChooseLhBtn btnbottomset;
    private ChooseLhBtn lhBtn;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public ChooseLiangHaoTypeJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(293, 409));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 638);
            offBtn.setBounds(256, 11, 25, 25);
            this.add(offBtn);
            this.listModel = new DefaultListModel<>();
            (this.listLh = new JList<Integer>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(0, 0, 0, 0));
            this.listLh.setFont(new Font("微软雅黑", 1, 14));
            this.listLh.setBackground(UIUtils.Color_BACK);
            this.listLh.setSelectionForeground(Color.white);
            this.listLh.setForeground(Color.white);
            this.listLh.setModel(this.listModel);
            (this.jScrollPane = new JScrollPane(this.listLh)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(55, 45, 194, 320);
            this.jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.btnbottomset = new ChooseLhBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "确定", Integer.valueOf(2), this)).setBounds(118, 370, 59, 24);
            this.add(this.btnbottomset);
            (this.lhBtn = new ChooseLhBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "炼化", Integer.valueOf(3), this)).setBounds(50, 370, 59, 24);
            this.add(this.lhBtn);
        }
        else {
            this.setPreferredSize(new Dimension(290, 408));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 638);
            offBtn.setBounds(268, 0, 25, 25);
            this.add(offBtn);
            this.listModel = new DefaultListModel<>();
            (this.listLh = new JList<Integer>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(0, 0, 0, 0));
            this.listLh.setFont(new Font("微软雅黑", 1, 14));
            this.listLh.setBackground(UIUtils.Color_BACK);
            this.listLh.setSelectionForeground(Color.white);
            this.listLh.setForeground(Color.white);
            this.listLh.setModel(this.listModel);
            (this.jScrollPane = new JScrollPane(this.listLh)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(45, 58, 180, 260);
            this.jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.btnbottomset = new ChooseLhBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_HY16, "确定", Integer.valueOf(2), this)).setBounds(118, 350, 60, 25);
            this.add(this.btnbottomset);
            (this.lhBtn = new ChooseLhBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_HY16, "炼化", Integer.valueOf(3), this)).setBounds(50, 350, 60, 25);
            this.add(this.lhBtn);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/lianghao.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 293, 409, this);
            LoginResult login = RoleData.getRoleData().getLoginResult();
            String num = login.getLiangHao();
            Integer type = Integer.valueOf((login.getLianghaotype() == null) ? 0 : ((int)login.getLianghaotype()));
            if (StringUtils.isNotEmpty(num)) {
                String[] nums = num.split("");
                for (int i = 0; i < this.listLh.getModel().getSize(); ++i) {
                    for (int j = 0; j < nums.length; ++j) {
                        int xnum = 70 + 20 * j;
                        int ynum = 48 + 21 * i;
                        g.drawImage(CutButtonImage.getImage("inkImg/number/" + String.valueOf(i) + nums[j] + ".png", 11, 15).getImage(), xnum, ynum, 11, 15, this);
                    }
                }
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu/lianghaoh.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 290, 408, this);
            LoginResult login = RoleData.getRoleData().getLoginResult();
            String num = login.getLiangHao();
            Integer type = Integer.valueOf((login.getLianghaotype() == null) ? 0 : ((int)login.getLianghaotype()));
            if (StringUtils.isNotEmpty(num)) {
                String[] nums = num.split("");
                for (int i = 0; i < this.listLh.getModel().getSize(); ++i) {
                    for (int j = 0; j < nums.length; ++j) {
                        int xnum = 70 + 20 * j;
                        int ynum = 60 + 21 * i;
                        g.drawImage(CutButtonImage.getImage("inkImg/number/" + String.valueOf(i) + nums[j] + ".png", 11, 15).getImage(), xnum, ynum, 11, 15, this);
                    }
                }
            }
        }
    }
    
    public void init(List<Integer> list) {
        this.listModel.clear();
        for (int i = 0; i < list.size(); ++i) {
            this.listModel.addElement(list.get(i));
        }
    }
    
    public JList<Integer> getListLh() {
        return this.listLh;
    }
    
    public void setListLh(JList<Integer> listLh) {
        this.listLh = listLh;
    }
    
    public DefaultListModel<Integer> getListModel() {
        return this.listModel;
    }
    
    public void setListModel(DefaultListModel<Integer> listModel) {
        this.listModel = listModel;
    }
}
