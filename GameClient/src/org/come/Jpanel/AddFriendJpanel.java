package org.come.Jpanel;

import java.awt.Graphics;
import org.come.until.CutButtonImage;
import javax.swing.table.JTableHeader;
import org.come.until.ScrollUI;
import org.come.until.SrcollPanelUI;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import org.come.until.Util;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.until.AnalysisString;
import javax.swing.ImageIcon;
import org.come.bean.Role_bean;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JPanel;

public class AddFriendJpanel extends JPanel
{
    private RoleCaoZuoBtn labId;
    private RoleCaoZuoBtn labName;
    private JTextField field;
    private RoleCaoZuoBtn zuoBtn;
    private RoleCaoZuoBtn zuoBtn2;
    private RoleCaoZuoBtn zuoBtn3;
    private JScrollPane jScrollPane;
    private JTable tableMsg;
    private DefaultTableModel tableModel;
    private Vector<Vector<String>> verVectors;
    private Vector<String> verStrings;
    private String tj;
    private Role_bean role;
    private ImageIcon icon;
    
    public void CF(Role_bean role_bean) {
        this.verVectors.clear();
        this.verStrings.clear();
        this.role = role_bean;
        if (this.role != null) {
            this.verStrings.add(0, this.role.getRolename());
            this.verStrings.add(1, AnalysisString.lvl((int)this.role.getGrade()));
            this.verStrings.add(2, this.role.getRace_name());
            this.verStrings.add(3, "不愿透露");
            this.verVectors.add(0, this.verStrings);
        }
    }
    
    public AddFriendJpanel() {
        this.tj = "数字ID";
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(402, 359));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 75);
            offBtn.setBounds(365, 10, 25, 25);
            this.add(offBtn);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.verStrings = new Vector<>();
            this.verVectors.add(this.verStrings);
            this.tableModel.setDataVector(this.verVectors, Util.vector2);
            (this.tableMsg = new JTable() {
                {
                    this.setOpaque(false);
                }
                
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(0);
            this.tableMsg.setDefaultRenderer(Object.class, renderer);
            JTableHeader header = this.tableMsg.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 0));
            this.tableMsg.getTableHeader().setBackground(new Color(0, 0, 0, 0));
            this.tableMsg.getTableHeader().setVisible(true);
            this.tableMsg.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.tableMsg.setSelectionBackground(new Color(0, 0, 0, 0));
            this.tableMsg.setSelectionForeground(Color.red);
            this.tableMsg.setForeground(Color.yellow);
            this.tableMsg.setFont(new Font("微软雅黑", 1, 13));
            this.tableMsg.setBackground(UIUtils.Color_BACK);
            this.tableMsg.setModel(this.tableModel);
            this.tableMsg.getColumnModel().getColumn(0).setPreferredWidth(100);
            this.tableMsg.getColumnModel().getColumn(1).setPreferredWidth(70);
            this.tableMsg.getColumnModel().getColumn(2).setPreferredWidth(60);
            this.tableMsg.getColumnModel().getColumn(3).setPreferredWidth(60);
            this.tableMsg.setAutoResizeMode(0);
            this.tableMsg.isCellEditable(1, 1);
            this.tableMsg.setEnabled(true);
            (this.jScrollPane = new JScrollPane()).setViewportView(this.tableMsg);
            this.jScrollPane.setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(49, 123, 311, 158);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.field = new JTextField()).setBounds(121, 70, 97, 16);
            this.field.setBackground(UIUtils.Color_BACK);
            this.field.setBorder(BorderFactory.createEmptyBorder());
            this.field.setFont(UIUtils.TEXT_FONT1);
            this.field.setCaretColor(Color.white);
            this.field.setForeground(Color.white);
            this.add(this.field);
            (this.zuoBtn2 = new RoleCaoZuoBtn("inkImg/button1/B30.png", 1, "搜索", 7, UIUtils.COLOR_BTNTEXT)).setBounds(218, 70, 34, 17);
            this.add(this.zuoBtn2);
            (this.zuoBtn = new RoleCaoZuoBtn("inkImg/button1/B22.png", 1, "加为好友", 6, UIUtils.COLOR_BLACK)).setBounds(83, 292, 99, 24);
            this.add(this.zuoBtn);
            (this.zuoBtn3 = new RoleCaoZuoBtn("inkImg/button1/B22.png", 1, "申请入队", 6, UIUtils.COLOR_BLACK)).setBounds(245, 292, 99, 24);
            this.add(this.zuoBtn3);
            (this.labId = new RoleCaoZuoBtn("inkImg/button/B242.png", 1, 11, this)).setBounds(56, 25, 100, 35);
            this.add(this.labId);
            (this.labName = new RoleCaoZuoBtn("inkImg/button/B245.png", 1, 12, this)).setBounds(158, 25, 100, 35);
            this.add(this.labName);
        }
        else {
            this.setPreferredSize(new Dimension(380, 303));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 75);
            offBtn.setBounds(354, 0, 23, 23);
            this.add(offBtn);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.verStrings = new Vector<>();
            this.verVectors.add(this.verStrings);
            this.tableModel.setDataVector(this.verVectors, Util.vector2);
            (this.tableMsg = new JTable() {
                {
                    this.setOpaque(false);
                }
                
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(0);
            this.tableMsg.setDefaultRenderer(Object.class, renderer);
            JTableHeader header = this.tableMsg.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 0));
            this.tableMsg.getTableHeader().setBackground(new Color(0, 0, 0, 0));
            this.tableMsg.getTableHeader().setVisible(true);
            this.tableMsg.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.tableMsg.setSelectionBackground(new Color(0, 0, 0, 0));
            this.tableMsg.setSelectionForeground(Color.red);
            this.tableMsg.setForeground(Color.yellow);
            this.tableMsg.setFont(new Font("微软雅黑", 1, 13));
            this.tableMsg.setBackground(UIUtils.Color_BACK);
            this.tableMsg.setModel(this.tableModel);
            this.tableMsg.getColumnModel().getColumn(0).setPreferredWidth(85);
            this.tableMsg.getColumnModel().getColumn(1).setPreferredWidth(65);
            this.tableMsg.getColumnModel().getColumn(2).setPreferredWidth(55);
            this.tableMsg.getColumnModel().getColumn(3).setPreferredWidth(55);
            this.tableMsg.setAutoResizeMode(0);
            this.tableMsg.isCellEditable(1, 1);
            this.tableMsg.setEnabled(true);
            (this.jScrollPane = new JScrollPane()).setViewportView(this.tableMsg);
            this.jScrollPane.setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(49, 142, 282, 104);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.field = new JTextField()).setBounds(101, 89, 125, 16);
            this.field.setBackground(UIUtils.Color_BACK);
            this.field.setBorder(BorderFactory.createEmptyBorder());
            this.field.setFont(UIUtils.TEXT_FONT1);
            this.field.setCaretColor(Color.white);
            this.field.setForeground(Color.white);
            this.add(this.field);
            (this.zuoBtn2 = new RoleCaoZuoBtn("img/xy2uiimg/112.png", 1, "搜索", 7, UIUtils.COLOR_BTNPUTONG)).setBounds(238, 85, 65, 24);
            this.add(this.zuoBtn2);
            (this.zuoBtn = new RoleCaoZuoBtn("img/xy2uiimg/111.png", 1, "加为好友", 6, UIUtils.COLOR_BTNPUTONG)).setBounds(66, 262, 85, 24);
            this.add(this.zuoBtn);
            (this.zuoBtn3 = new RoleCaoZuoBtn("img/xy2uiimg/111.png", 1, "申请入队", 6, UIUtils.COLOR_BTNPUTONG)).setBounds(210, 262, 85, 24);
            this.add(this.zuoBtn3);
            (this.labId = new RoleCaoZuoBtn("inkImg/hongmu/IDchazhao.png", 1, 11, this)).setBounds(56, 33, 100, 35);
            this.add(this.labId);
            (this.labName = new RoleCaoZuoBtn("inkImg/hongmu/nchen.png", 1, 12, this)).setBounds(158, 33, 100, 35);
            this.add(this.labName);
        }
    }
    
    public void labMenuChange(int type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type == 11) {
                try {
                    this.labId.setIcons(CutButtonImage.cuts("inkImg/button/B242.png"));
                    this.labName.setIcons(CutButtonImage.cuts("inkImg/button/B245.png"));
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.tj = "数字ID";
                this.field.setText("");
                this.CF(null);
            }
            else if (type == 12) {
                try {
                    this.labId.setIcons(CutButtonImage.cuts("inkImg/button/B244.png"));
                    this.labName.setIcons(CutButtonImage.cuts("inkImg/button/B243.png"));
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.tj = "昵称";
                this.field.setText("");
                this.CF(null);
            }
        }
        else if (type == 11) {
            try {
                this.labId.setIcons(CutButtonImage.cuts("inkImg/hongmu/idchazhao1.png"));
                this.labName.setIcons(CutButtonImage.cuts("inkImg/hongmu/nichen1.png"));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            this.tj = "数字ID";
            this.field.setText("");
            this.CF(null);
        }
        else if (type == 12) {
            try {
                this.labId.setIcons(CutButtonImage.cuts("inkImg/hongmu/IDchazhao.png"));
                this.labName.setIcons(CutButtonImage.cuts("inkImg/hongmu/nchen.png"));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            this.tj = "昵称";
            this.field.setText("");
            this.CF(null);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B309.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 402, 359, this);
            g.setColor(UIUtils.COLOR_NAME8);
            g.setFont(UIUtils.TEXT_FONT21);
            g.drawString(this.tj, 50, 85);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/searchaddfriend.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 380, 303, this);
            g.setColor(UIUtils.COLOR_HURTB3);
            g.setFont(UIUtils.TEXT_FONT78);
            g.drawString(this.tj, 44, 103);
        }
    }
    
    public Role_bean getRole() {
        return this.role;
    }
    
    public void setRole(Role_bean role) {
        this.role = role;
    }
    
    public String getFieldText() {
        return this.field.getText();
    }
    
    public String getTj() {
        return this.tj;
    }
}
