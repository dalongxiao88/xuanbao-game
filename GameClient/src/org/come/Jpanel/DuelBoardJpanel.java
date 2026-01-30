package org.come.Jpanel;

import java.awt.Graphics;
import javax.swing.table.JTableHeader;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.Frame.DuelBoardJframe;
import com.tool.btn.DuelBoardBtn;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JPanel;

public class DuelBoardJpanel extends JPanel
{
    private JTable tablePankList;
    private DefaultTableModel tableModel;
    private Vector<Vector<String>> verVectors;
    private Vector<String> verStrings;
    private DuelBoardBtn showBtn;
    private DuelBoardJframe duelBoardJframe;
    private boolean showType;
    private ImageIcon icon;
    
    public DuelBoardJpanel(DuelBoardJframe duelBoardJframe) {
        this.showType = true;
        this.setPreferredSize(new Dimension(210, 188));
        this.setLayout(null);
        this.setOpaque(false);
        this.duelBoardJframe = duelBoardJframe;
        this.tableModel = new DefaultTableModel();
        this.verVectors = new Vector<>();
        this.verStrings = new Vector<>();
        this.verVectors.add(this.verStrings);
        Vector<String> vector3 = new Vector<>();
        vector3.add("");
        vector3.add("");
        vector3.add("");
        this.tableModel.setDataVector(this.verVectors, vector3);
        (this.tablePankList = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }).setShowGrid(false);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                this.setForeground(Color.white);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        renderer.setHorizontalAlignment(0);
        this.tablePankList.setDefaultRenderer(Object.class, renderer);
        JTableHeader header = this.tablePankList.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 0));
        this.tablePankList.getTableHeader().setBackground(UIUtils.Color_BACK);
        this.tablePankList.getTableHeader().setVisible(true);
        this.tablePankList.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        this.tablePankList.setSelectionBackground(UIUtils.Color_BACK);
        this.tablePankList.setSelectionForeground(Color.white);
        this.tablePankList.setForeground(Color.white);
        this.tablePankList.setFont(UIUtils.TEXT_FONT);
        this.tablePankList.setBackground(UIUtils.Color_BACK);
        this.tablePankList.setModel(this.tableModel);
        this.tablePankList.getColumnModel().getColumn(0).setPreferredWidth(90);
        this.tablePankList.getColumnModel().getColumn(1).setPreferredWidth(60);
        this.tablePankList.getColumnModel().getColumn(2).setPreferredWidth(60);
        this.tablePankList.setRowHeight(25);
        this.tablePankList.setAutoResizeMode(0);
        this.tablePankList.isCellEditable(1, 1);
        this.tablePankList.setEnabled(true);
        this.tablePankList.setBounds(0, 53, 210, 135);
        this.add(this.tablePankList);
        this.getShowBtn();
    }
    
    public void getBoardData(String boardData) {
        this.tableModel.getDataVector().clear();
        if (boardData == null || boardData.equals("")) {
            return;
        }
        String[] dataList = boardData.split("\\|");
        for (int i = 0; i < dataList.length; ++i) {
            String[] data = dataList[i].split("&");
            Vector<String> vector = new Vector<>();
            for (int j = 0; j < data.length; ++j) {
                vector.add(data[j]);
            }
            this.tableModel.addRow(vector);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S146.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 210, 188, this);
    }
    
    public JTable getTablePankList() {
        return this.tablePankList;
    }
    
    public void setTablePankList(JTable tablePankList) {
        this.tablePankList = tablePankList;
    }
    
    public DefaultTableModel getTableModel() {
        return this.tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public Vector<Vector<String>> getVerVectors() {
        return this.verVectors;
    }
    
    public void setVerVectors(Vector<Vector<String>> verVectors) {
        this.verVectors = verVectors;
    }
    
    public Vector<String> getVerStrings() {
        return this.verStrings;
    }
    
    public void setVerStrings(Vector<String> verStrings) {
        this.verStrings = verStrings;
    }
    
    public DuelBoardBtn getShowBtn() {
        if (this.showBtn == null) {
            (this.showBtn = new DuelBoardBtn("inkImg/button/9.png", 1, "", 1, this)).setBounds(192, 0, 18, 18);
            this.add(this.showBtn);
        }
        return this.showBtn;
    }
    
    public void setShowBtn(DuelBoardBtn showBtn) {
        this.showBtn = showBtn;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public DuelBoardJframe getDuelBoardJframe() {
        return this.duelBoardJframe;
    }
    
    public void setDuelBoardJframe(DuelBoardJframe duelBoardJframe) {
        this.duelBoardJframe = duelBoardJframe;
    }
    
    public boolean isShowType() {
        return this.showType;
    }
    
    public void setShowType(boolean showType) {
        this.showType = showType;
    }
}
