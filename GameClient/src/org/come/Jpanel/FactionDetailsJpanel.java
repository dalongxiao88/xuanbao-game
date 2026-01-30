package org.come.Jpanel;

import org.come.until.ScrollUI;
import java.util.Vector;
import javax.swing.table.JTableHeader;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import com.updateNew.MyIsif;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.FactionBtn;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class FactionDetailsJpanel extends JPanel
{
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private FactionBtn btnExpel;
    private FactionBtn btnAddFriend;
    private ImageIcon icon;
    
    public FactionDetailsJpanel() {
        this.setPreferredSize(new Dimension(662, 475));
        this.setOpaque(false);
        this.setLayout(null);
        if (MyIsif.getStyle().equals("水墨")) {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 49);
            offBtn.setBounds(625, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/s74.png", 1, 49);
            offBtn.setBounds(637, 1, 25, 25);
            this.add(offBtn);
        }
        this.getScrollPane();
        this.getBtnExpel();
        this.getBtnAddFriend();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S171.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 662, 475, null);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S171.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 662, 475, null);
        }
    }
    
    public JTable getTable() {
        if (this.table == null) {
            (this.table = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).setShowGrid(false);
            this.table.setOpaque(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    this.setForeground(Color.white);
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };
            renderer.setHorizontalAlignment(0);
            this.table.setDefaultRenderer(Object.class, renderer);
            JTableHeader header = this.table.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 0));
            this.table.getTableHeader().setBackground(UIUtils.Color_BACK);
            this.table.getTableHeader().setVisible(true);
            this.table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.table.setSelectionBackground(UIUtils.Color_BACK);
            this.table.setSelectionForeground(Color.white);
            this.table.setForeground(Color.white);
            this.table.setFont(UIUtils.TEXT_FONT);
            this.table.setBackground(UIUtils.Color_BACK);
            this.table.setModel(this.getTableModel());
            this.table.getColumnModel().getColumn(0).setPreferredWidth(70);
            this.table.getColumnModel().getColumn(1).setPreferredWidth(80);
            this.table.getColumnModel().getColumn(2).setPreferredWidth(80);
            this.table.getColumnModel().getColumn(3).setPreferredWidth(70);
            this.table.getColumnModel().getColumn(4).setPreferredWidth(70);
            this.table.getColumnModel().getColumn(5).setPreferredWidth(60);
            this.table.getColumnModel().getColumn(6).setPreferredWidth(60);
            this.table.getColumnModel().getColumn(7).setPreferredWidth(80);
            this.table.setRowHeight(25);
            this.table.setAutoResizeMode(0);
            this.table.isCellEditable(1, 1);
            this.table.setEnabled(true);
        }
        return this.table;
    }
    
    public void setTable(JTable table) {
        this.table = table;
    }
    
    public DefaultTableModel getTableModel() {
        if (this.tableModel == null) {
            this.tableModel = new DefaultTableModel();
            Vector<Vector<String>> verVectors = new Vector<>();
            for (int i = 0; i < 50; ++i) {
                Vector<String> vector = new Vector<>();
                for (int j = 0; j < 8; ++j) {
                    vector.add(String.valueOf(j + 1));
                }
                verVectors.add(vector);
            }
            Vector<String> vector2 = new Vector<>();
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            this.tableModel.setDataVector(verVectors, vector2);
        }
        return this.tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane(this.getTable())).setBounds(51, 51, 590, 372);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setVerticalScrollBarPolicy(22);
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.scrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.getViewport().setOpaque(false);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public FactionBtn getBtnExpel() {
        if (this.btnExpel == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnExpel = new FactionBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "逐出", UIUtils.TEXT_HY16, 12, this)).setBounds(440, 432, 59, 24);
            }
            else {
                (this.btnExpel = new FactionBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "逐出", UIUtils.TEXT_HY88, 12, this)).setBounds(440, 432, 60, 26);
            }
            this.add(this.btnExpel);
        }
        return this.btnExpel;
    }
    
    public void setBtnExpel(FactionBtn btnExpel) {
        this.btnExpel = btnExpel;
    }
    
    public FactionBtn getBtnAddFriend() {
        if (this.btnAddFriend == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnAddFriend = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "加为好友", UIUtils.TEXT_HY16, 14, this)).setBounds(513, 432, 99, 24);
            }
            else {
                (this.btnAddFriend = new FactionBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "加为好友", UIUtils.TEXT_HY88, 14, this)).setBounds(513, 432, 80, 26);
            }
            this.add(this.btnAddFriend);
        }
        return this.btnAddFriend;
    }
    
    public void setBtnAddFriend(FactionBtn btnAddFriend) {
        this.btnAddFriend = btnAddFriend;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
