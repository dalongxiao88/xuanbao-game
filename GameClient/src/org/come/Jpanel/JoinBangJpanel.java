package org.come.Jpanel;

import java.awt.Graphics;
import javax.swing.table.JTableHeader;
import org.come.until.ScrollUI;
import org.come.until.SrcollPanelUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import org.come.until.Util;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.CreatBangBtn;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import org.come.entity.Gang;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;

public class JoinBangJpanel extends JPanel
{
    private Vector<Vector<String>> verVectors;
    private Vector<String> veStrings;
    private List<Gang> gangs;
    private JTable tableMsg;
    private DefaultTableModel tableModel;
    private JTextArea jTextArea;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private CreatBangBtn btnbang;
    private ImageIcon icon;
    
    public JoinBangJpanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(371, 420));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            (this.veStrings = new Vector<>()).add("adscf");
            this.veStrings.add("adscf");
            this.veStrings.add("adscf");
            this.veStrings.add("adscf");
            this.verVectors.add(this.veStrings);
            this.tableModel.setDataVector(this.verVectors, Util.vector3);
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
            this.tableMsg.getTableHeader().setBackground(UIUtils.Color_BACK);
            this.tableMsg.getTableHeader().setVisible(true);
            this.tableMsg.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.tableMsg.setSelectionBackground(UIUtils.Color_BACK);
            this.tableMsg.setSelectionForeground(Color.red);
            this.tableMsg.setForeground(Color.yellow);
            this.tableMsg.setFont(new Font("微软雅黑", 0, 16));
            this.tableMsg.setBackground(UIUtils.Color_BACK);
            this.tableMsg.setModel(this.tableModel);
            this.tableMsg.getColumnModel().getColumn(0).setPreferredWidth(88);
            this.tableMsg.getColumnModel().getColumn(1).setPreferredWidth(60);
            this.tableMsg.getColumnModel().getColumn(2).setPreferredWidth(57);
            this.tableMsg.getColumnModel().getColumn(3).setPreferredWidth(48);
            this.tableMsg.setAutoResizeMode(0);
            this.tableMsg.isCellEditable(1, 1);
            this.tableMsg.setEnabled(true);
            this.tableMsg.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    int index = JoinBangJpanel.this.tableMsg.getSelectedRow();
                    JoinBangJpanel.this.jTextArea.setText(((Gang)JoinBangJpanel.this.gangs.get(index)).getIntroduction());
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            (this.jScrollPane1 = new JScrollPane()).setViewportView(this.tableMsg);
            this.jScrollPane1.setVerticalScrollBarPolicy(22);
            this.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane1.getViewport().setOpaque(false);
            this.jScrollPane1.setOpaque(false);
            this.jScrollPane1.setBounds(51, 52, 298, 145);
            this.jScrollPane1.setBackground(UIUtils.Color_BACK);
            this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane1);
            (this.jTextArea = new JTextArea() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionColor(new Color(55, 23, 31));
            this.jTextArea.setForeground(Color.WHITE);
            this.jTextArea.setSelectedTextColor(Color.WHITE);
            this.jTextArea.setLineWrap(true);
            this.jTextArea.setFont(new Font("微软雅黑", 1, 14));
            this.jTextArea.setBackground(UIUtils.Color_BACK);
            (this.jScrollPane2 = new JScrollPane(this.jTextArea)).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(51, 224, 298, 145);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
            (this.btnbang = new CreatBangBtn("inkImg/button/B60.png", 1, "加入帮派", this, null)).setBounds(140, 380, 92, 26);
            this.add(this.btnbang);
        }
        else {
            this.setPreferredSize(new Dimension(333, 435));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            (this.veStrings = new Vector<>()).add("adscf");
            this.veStrings.add("adscf");
            this.veStrings.add("adscf");
            this.veStrings.add("adscf");
            this.verVectors.add(this.veStrings);
            this.tableModel.setDataVector(this.verVectors, Util.vector3);
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
            this.tableMsg.getTableHeader().setBackground(UIUtils.Color_BACK);
            this.tableMsg.getTableHeader().setVisible(true);
            this.tableMsg.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.tableMsg.setSelectionBackground(UIUtils.Color_BACK);
            this.tableMsg.setSelectionForeground(Color.red);
            this.tableMsg.setForeground(Color.yellow);
            this.tableMsg.setFont(new Font("微软雅黑", 0, 16));
            this.tableMsg.setBackground(UIUtils.Color_BACK);
            this.tableMsg.setModel(this.tableModel);
            this.tableMsg.getColumnModel().getColumn(0).setPreferredWidth(88);
            this.tableMsg.getColumnModel().getColumn(1).setPreferredWidth(60);
            this.tableMsg.getColumnModel().getColumn(2).setPreferredWidth(57);
            this.tableMsg.getColumnModel().getColumn(3).setPreferredWidth(48);
            this.tableMsg.setAutoResizeMode(0);
            this.tableMsg.isCellEditable(1, 1);
            this.tableMsg.setEnabled(true);
            this.tableMsg.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    int index = JoinBangJpanel.this.tableMsg.getSelectedRow();
                    JoinBangJpanel.this.jTextArea.setText(((Gang)JoinBangJpanel.this.gangs.get(index)).getIntroduction());
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            (this.jScrollPane1 = new JScrollPane()).setViewportView(this.tableMsg);
            this.jScrollPane1.setVerticalScrollBarPolicy(22);
            this.jScrollPane1.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane1.getViewport().setOpaque(false);
            this.jScrollPane1.setOpaque(false);
            this.jScrollPane1.setBounds(35, 58, 265, 145);
            this.jScrollPane1.setBackground(UIUtils.Color_BACK);
            this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane1.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane1);
            (this.jTextArea = new JTextArea() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionColor(new Color(55, 23, 31));
            this.jTextArea.setForeground(Color.WHITE);
            this.jTextArea.setSelectedTextColor(Color.WHITE);
            this.jTextArea.setLineWrap(true);
            this.jTextArea.setFont(new Font("微软雅黑", 1, 14));
            this.jTextArea.setBackground(UIUtils.Color_BACK);
            (this.jScrollPane2 = new JScrollPane(this.jTextArea)).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(35, 230, 265, 145);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
            (this.btnbang = new CreatBangBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "加入帮派", this)).setBounds(140, 390, 80, 26);
            this.add(this.btnbang);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S61.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 371, 420, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/58_png.xy2uiimg.quergroup.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 333, 435, this);
        }
    }
    
    public JTable getTableMsg() {
        return this.tableMsg;
    }
    
    public void setTableMsg(JTable tableMsg) {
        this.tableMsg = tableMsg;
    }
    
    public JTextArea getjTextArea() {
        return this.jTextArea;
    }
    
    public void setjTextArea(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }
    
    public List<Gang> getGangs() {
        return this.gangs;
    }
    
    public void setGangs(List<Gang> gangs) {
        this.gangs = gangs;
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
    
    public Vector<String> getVeStrings() {
        return this.veStrings;
    }
    
    public void setVeStrings(Vector<String> veStrings) {
        this.veStrings = veStrings;
    }
}
