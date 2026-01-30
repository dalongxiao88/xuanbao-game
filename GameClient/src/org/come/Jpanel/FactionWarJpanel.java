package org.come.Jpanel;

import org.come.until.SrcollPanelUI;
import java.util.Vector;
import javax.swing.table.JTableHeader;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.tcpimg.RichLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.tool.btn.FactionBtn;
import javax.swing.JPanel;

public class FactionWarJpanel extends JPanel
{
    private FactionBtn btnDetails;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private RichLabel richLabPandectMsg;
    private RichLabel richLabLastMsg;
    private RichLabel richLabMyMsg;
    private ImageIcon icon;
    
    public FactionWarJpanel() {
        this.setPreferredSize(new Dimension(662, 475));
        this.setOpaque(false);
        this.setLayout(null);
        this.getScrollPane();
        this.getBtnDetails();
        this.getRichLabLastMsg();
        this.getRichLabMyMsg();
        this.getRichLabPandectMsg();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S168.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 662, 475, null);
    }
    
    public FactionBtn getBtnDetails() {
        if (this.btnDetails == null) {
            (this.btnDetails = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BTNPUTONG, "战报详情", UIUtils.TEXT_HY16, 15, this)).setBounds(286, 420, 99, 24);
            this.add(this.btnDetails);
        }
        return this.btnDetails;
    }
    
    public void setBtnDetails(FactionBtn btnDetails) {
        this.btnDetails = btnDetails;
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
            this.table.getColumnModel().getColumn(0).setPreferredWidth(75);
            this.table.getColumnModel().getColumn(1).setPreferredWidth(90);
            this.table.getColumnModel().getColumn(2).setPreferredWidth(75);
            this.table.getColumnModel().getColumn(3).setPreferredWidth(85);
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
                for (int j = 0; j < 4; ++j) {
                    vector.add(String.valueOf(j + 1));
                }
                verVectors.add(vector);
            }
            Vector<String> vector2 = new Vector<>();
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
            (this.scrollPane = new JScrollPane(this.getTable())).setBounds(51, 87, 342, 320);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setVerticalScrollBarPolicy(22);
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
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
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public RichLabel getRichLabPandectMsg() {
        if (this.richLabPandectMsg == null) {
            (this.richLabPandectMsg = new RichLabel("", UIUtils.TEXT_FONT1)).setBounds(404, 91, 204, 70);
            this.add(this.richLabPandectMsg);
        }
        return this.richLabPandectMsg;
    }
    
    public void setRichLabPandectMsg(RichLabel richLabPandectMsg) {
        this.richLabPandectMsg = richLabPandectMsg;
    }
    
    public RichLabel getRichLabLastMsg() {
        if (this.richLabLastMsg == null) {
            (this.richLabLastMsg = new RichLabel("", UIUtils.TEXT_FONT1)).setBounds(404, 181, 204, 70);
            this.add(this.richLabLastMsg);
        }
        return this.richLabLastMsg;
    }
    
    public void setRichLabLastMsg(RichLabel richLabLastMsg) {
        this.richLabLastMsg = richLabLastMsg;
    }
    
    public RichLabel getRichLabMyMsg() {
        if (this.richLabMyMsg == null) {
            (this.richLabMyMsg = new RichLabel("", UIUtils.TEXT_FONT1)).setBounds(404, 273, 204, 154);
            this.add(this.richLabMyMsg);
        }
        return this.richLabMyMsg;
    }
    
    public void setRichLabMyMsg(RichLabel richLabMyMsg) {
        this.richLabMyMsg = richLabMyMsg;
    }
}
