package org.come.Jpanel;

import org.come.entity.SellXianyu;
import org.come.bean.SearchSellXianYuResultBean;
import org.come.until.CutButtonImage;
import javax.swing.table.JTableHeader;
import org.come.until.SrcollPanelUI;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.Util;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import com.tool.btn.DianKaJiaoYiBtn;
import javax.swing.JTextField;
import java.awt.Color;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class DianKaJiaoYiGouMaiJpanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JScrollPane jScrollPane;
    private JTable tableDianKaList;
    private int tab_row;
    private DefaultTableModel tableModel;
    private Vector<Vector<String>> verVectors;
    private Color foreground;
    private Color background;
    private JTextField dianshuJTF;
    private DianKaJiaoYiBtn goumaiBtn;
    private BigDecimal Unitprice;
    private BigDecimal Totalsum;
    private BigDecimal selectSellId;
    private ImageIcon bg;
    private ImageIcon icon;
    
    public DianKaJiaoYiGouMaiJpanel() {
        this.tab_row = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(480, 520));
            this.setLayout(null);
            this.setOpaque(false);
            (this.goumaiBtn = new DianKaJiaoYiBtn("inkImg/button/18.png", 1, "确定购买", 10, this)).setBounds(195, 460, 100, 26);
            this.add(this.goumaiBtn);
            this.Unitprice = new BigDecimal("0");
            this.Totalsum = new BigDecimal("19500");
            (this.dianshuJTF = new JTextField()).setText("0");
            this.dianshuJTF.setFont(UIUtils.TEXT_FONT87);
            this.dianshuJTF.setBackground(UIUtils.Color_BACK);
            this.dianshuJTF.setBorder(BorderFactory.createEmptyBorder());
            this.dianshuJTF.setForeground(Color.white);
            this.dianshuJTF.setCaretColor(Color.white);
            this.dianshuJTF.setBounds(195, 370, 151, 16);
            this.dianshuJTF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiGouMaiJpanel.this.Unitprice.toString());
                        DianKaJiaoYiGouMaiJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiGouMaiJpanel.this.Unitprice.toString());
                        DianKaJiaoYiGouMaiJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.dianshuJTF.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText();
                    if (str.length() == 0) {
                        DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.setText("");
                    }
                    if (str.length() > 0 && str.length() > 3) {
                        e.consume();
                        DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.setText("9999");
                        int sum = Integer.parseInt(DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText());
                        long price = Long.parseLong(DianKaJiaoYiGouMaiJpanel.this.Unitprice.toString());
                        DianKaJiaoYiGouMaiJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    Util.changeTextColor(DianKaJiaoYiGouMaiJpanel.this.dianshuJTF, new BigDecimal(DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText()));
                }
            });
            this.add(this.dianshuJTF);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.tableModel.setDataVector(this.verVectors, Util.vector3);
            (this.tableDianKaList = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    DianKaJiaoYiGouMaiJpanel.this.tab_row = DianKaJiaoYiGouMaiJpanel.this.tableDianKaList.rowAtPoint(event.getPoint());
                    Object sellId = DianKaJiaoYiGouMaiJpanel.this.tableDianKaList.getModel().getValueAt(DianKaJiaoYiGouMaiJpanel.this.tab_row, 0);
                    if (sellId != null) {
                        DianKaJiaoYiGouMaiJpanel.this.selectSellId = new BigDecimal(sellId.toString());
                    }
                    else {
                        DianKaJiaoYiGouMaiJpanel.this.selectSellId = BigDecimal.ZERO;
                    }
                    Object b = DianKaJiaoYiGouMaiJpanel.this.tableDianKaList.getModel().getValueAt(DianKaJiaoYiGouMaiJpanel.this.tab_row, 1);
                    Object oUnitprice = DianKaJiaoYiGouMaiJpanel.this.tableDianKaList.getModel().getValueAt(DianKaJiaoYiGouMaiJpanel.this.tab_row, 2);
                    if (oUnitprice != null) {
                        DianKaJiaoYiGouMaiJpanel.this.Unitprice = new BigDecimal(oUnitprice.toString());
                    }
                    else {
                        DianKaJiaoYiGouMaiJpanel.this.Unitprice = BigDecimal.ZERO;
                    }
                    long price = Long.parseLong(DianKaJiaoYiGouMaiJpanel.this.Unitprice.toString());
                    String s = DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText();
                    int sum = Integer.parseInt(s);
                    DianKaJiaoYiGouMaiJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                }
            });
            this.tableDianKaList.setOpaque(false);
            this.tableDianKaList.setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                private Boolean b = Boolean.valueOf(false);
                private int column = -1;
                
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    this.column = column;
                    this.b = Boolean.valueOf(isSelected);
                    if (isSelected) {
                        this.setBackground(new Color(162, 0, 0, 150));
                    }
                    else {
                        this.setOpaque(false);
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
                
                @Override
                protected void paintComponent(Graphics g) {
                    this.setBorder(BorderFactory.createEmptyBorder());
                    if ((boolean)this.b) {
                        g.setColor(new Color(76, 164, 122, 131));
                        g.fillRect(0, 0, 150, 30);
                    }
                    FontMetrics fm = g.getFontMetrics();
                    String replace = this.getText().replace(",", "").trim();
                    int dx = fm.stringWidth(replace);
                    int i = this.getWidth() - dx;
                    if (this.column == 1) {
                        i -= 10;
                    }
                    Util.drawPrice(g, new BigDecimal(replace), i / 2, 20);
                }
            };
            renderer.setHorizontalAlignment(4);
            this.tableDianKaList.setDefaultRenderer(Object.class, renderer);
            JTableHeader header = this.tableDianKaList.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 0));
            this.tableDianKaList.getTableHeader().setBackground(new Color(0, 0, 0, 0));
            this.tableDianKaList.getTableHeader().setVisible(true);
            this.tableDianKaList.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.tableDianKaList.setSelectionBackground(new Color(0, 0, 0, 0));
            this.tableDianKaList.setSelectionForeground(Color.white);
            this.tableDianKaList.setForeground(Color.white);
            this.tableDianKaList.setFont(new Font("方正规范书宋", 0, 14));
            this.tableDianKaList.setBackground(UIUtils.Color_BACK);
            this.tableDianKaList.setModel(this.tableModel);
            this.tableDianKaList.getColumnModel().getColumn(0).setPreferredWidth(1);
            this.tableDianKaList.getColumnModel().getColumn(1).setPreferredWidth(130);
            this.tableDianKaList.getColumnModel().getColumn(2).setPreferredWidth(133);
            this.tableDianKaList.getColumnModel().getColumn(3).setPreferredWidth(133);
            this.tableDianKaList.setRowHeight(35);
            this.tableDianKaList.isCellEditable(1, 1);
            this.tableDianKaList.setEnabled(true);
            this.tableDianKaList.removeColumn(this.tableDianKaList.getColumnModel().getColumn(0));
            (this.jScrollPane = new JScrollPane(this.tableDianKaList)).setViewportView(this.tableDianKaList);
            this.jScrollPane.setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(56, 113, 399, 210);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
        }
        else {
            this.setPreferredSize(new Dimension(456, 527));
            this.setLayout(null);
            this.setOpaque(false);
            (this.goumaiBtn = new DianKaJiaoYiBtn("inkImg/hongmu/a2.png", 1, "确定购买", 10, this)).setFont(UIUtils.TEXT_HY16);
            this.goumaiBtn.setColors(UIUtils.COLOR_RED_BTNTEXT);
            this.goumaiBtn.setBounds(195, 460, 85, 24);
            this.add(this.goumaiBtn);
            this.Unitprice = new BigDecimal("0");
            this.Totalsum = new BigDecimal("0");
            (this.dianshuJTF = new JTextField()).setText("0");
            this.dianshuJTF.setFont(UIUtils.TEXT_FONT1B);
            this.dianshuJTF.setBackground(UIUtils.Color_BACK);
            this.dianshuJTF.setBorder(BorderFactory.createEmptyBorder());
            this.dianshuJTF.setForeground(Color.white);
            this.dianshuJTF.setCaretColor(Color.white);
            this.dianshuJTF.setBounds(177, 368, 151, 16);
            this.dianshuJTF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiGouMaiJpanel.this.Unitprice.toString());
                        DianKaJiaoYiGouMaiJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiGouMaiJpanel.this.Unitprice.toString());
                        DianKaJiaoYiGouMaiJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.dianshuJTF.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText();
                    if (str.length() == 0) {
                        DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.setText("");
                    }
                    if (str.length() > 0 && str.length() > 3) {
                        e.consume();
                        DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.setText("9999");
                        int sum = Integer.parseInt(DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText());
                        long price = Long.parseLong(DianKaJiaoYiGouMaiJpanel.this.Unitprice.toString());
                        DianKaJiaoYiGouMaiJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    Util.changeTextColor(DianKaJiaoYiGouMaiJpanel.this.dianshuJTF, new BigDecimal(DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText()));
                }
            });
            this.add(this.dianshuJTF);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.tableModel.setDataVector(this.verVectors, Util.vector3);
            (this.tableDianKaList = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    DianKaJiaoYiGouMaiJpanel.this.tab_row = DianKaJiaoYiGouMaiJpanel.this.tableDianKaList.rowAtPoint(event.getPoint());
                    Object sellId = DianKaJiaoYiGouMaiJpanel.this.tableDianKaList.getModel().getValueAt(DianKaJiaoYiGouMaiJpanel.this.tab_row, 0);
                    if (sellId != null) {
                        DianKaJiaoYiGouMaiJpanel.this.selectSellId = new BigDecimal(sellId.toString());
                    }
                    else {
                        DianKaJiaoYiGouMaiJpanel.this.selectSellId = BigDecimal.ZERO;
                    }
                    Object b = DianKaJiaoYiGouMaiJpanel.this.tableDianKaList.getModel().getValueAt(DianKaJiaoYiGouMaiJpanel.this.tab_row, 1);
                    Object oUnitprice = DianKaJiaoYiGouMaiJpanel.this.tableDianKaList.getModel().getValueAt(DianKaJiaoYiGouMaiJpanel.this.tab_row, 2);
                    if (oUnitprice != null) {
                        DianKaJiaoYiGouMaiJpanel.this.Unitprice = new BigDecimal(oUnitprice.toString());
                    }
                    else {
                        DianKaJiaoYiGouMaiJpanel.this.Unitprice = BigDecimal.ZERO;
                    }
                    long price = Long.parseLong(DianKaJiaoYiGouMaiJpanel.this.Unitprice.toString());
                    String s = DianKaJiaoYiGouMaiJpanel.this.dianshuJTF.getText();
                    int sum = Integer.parseInt(s);
                    DianKaJiaoYiGouMaiJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                }
            });
            this.tableDianKaList.setOpaque(false);
            this.tableDianKaList.setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                private Boolean b = Boolean.valueOf(false);
                private int column = -1;
                
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    this.column = column;
                    this.b = Boolean.valueOf(isSelected);
                    if (isSelected) {
                        this.setBackground(new Color(162, 0, 0, 150));
                    }
                    else {
                        this.setOpaque(false);
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
                
                @Override
                protected void paintComponent(Graphics g) {
                    this.setBorder(BorderFactory.createEmptyBorder());
                    if ((boolean)this.b) {
                        g.setColor(new Color(76, 164, 122, 131));
                        g.fillRect(0, 0, 150, 30);
                    }
                    FontMetrics fm = g.getFontMetrics();
                    String replace = this.getText().replace(",", "").trim();
                    int dx = fm.stringWidth(replace);
                    int i = this.getWidth() - dx;
                    if (this.column == 1) {
                        i -= 10;
                    }
                    Util.drawPrice(g, new BigDecimal(replace), i / 2, 20);
                }
            };
            renderer.setHorizontalAlignment(4);
            this.tableDianKaList.setDefaultRenderer(Object.class, renderer);
            JTableHeader header = this.tableDianKaList.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 0));
            this.tableDianKaList.getTableHeader().setBackground(new Color(0, 0, 0, 0));
            this.tableDianKaList.getTableHeader().setVisible(true);
            this.tableDianKaList.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.tableDianKaList.setSelectionBackground(new Color(0, 0, 0, 0));
            this.tableDianKaList.setSelectionForeground(Color.white);
            this.tableDianKaList.setForeground(Color.white);
            this.tableDianKaList.setFont(new Font("方正规范书宋", 0, 14));
            this.tableDianKaList.setBackground(UIUtils.Color_BACK);
            this.tableDianKaList.setModel(this.tableModel);
            this.tableDianKaList.setAutoResizeMode(0);
            this.tableDianKaList.getColumnModel().getColumn(0).setPreferredWidth(123);
            this.tableDianKaList.getColumnModel().getColumn(1).setPreferredWidth(123);
            this.tableDianKaList.getColumnModel().getColumn(2).setPreferredWidth(123);
            this.tableDianKaList.getColumnModel().getColumn(3).setPreferredWidth(123);
            this.tableDianKaList.setRowHeight(35);
            this.tableDianKaList.setAutoResizeMode(0);
            this.tableDianKaList.isCellEditable(1, 1);
            this.tableDianKaList.setEnabled(true);
            this.tableDianKaList.removeColumn(this.tableDianKaList.getColumnModel().getColumn(0));
            (this.jScrollPane = new JScrollPane(this.tableDianKaList)).setViewportView(this.tableDianKaList);
            this.jScrollPane.setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(40, 110, 390, 206);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.bg == null) {
                this.bg = CutButtonImage.getImage("Client/神通天演册/40×40/仙/dkjy-gm.png", -1, -1);
            }
            g.drawImage(this.bg.getImage(), 49, 70, this);
            this.icon = CutButtonImage.getImage("inkImg/button/dkjy-icon.png", 15, 24);
            for (int i = 0; i < ((this.verVectors.size() >= 6) ? 6 : this.verVectors.size()); ++i) {
                g.drawImage(this.icon.getImage(), 64, 118 + 35 * i, this.tableDianKaList);
            }
            Util.drawMoney(g, 335, 414);
            Util.drawPrice(g, this.Unitprice, 195, 414);
            Util.drawPrice(g, this.Totalsum, 335, 382);
        }
        else {
            super.paintComponent(g);
            this.icon = CutButtonImage.getImage("inkImg/button/dkjy-icon.png", 15, 24);
            for (int i = 0; i < ((this.verVectors.size() >= 6) ? 6 : this.verVectors.size()); ++i) {
                g.drawImage(this.icon.getImage(), 40, 114 + 35 * i, this.tableDianKaList);
            }
            Util.drawMoney(g, 315, 412);
            Util.drawPrice(g, this.Unitprice, 177, 412);
            Util.drawPrice(g, this.Totalsum, 315, 380);
        }
    }
    
    public void changeFrom(int caozuo) {
    }
    
    public JTextField getDianshuJTF() {
        return this.dianshuJTF;
    }
    
    public void setDianshuJTF(JTextField dianshuJTF) {
        this.dianshuJTF = dianshuJTF;
    }
    
    public DianKaJiaoYiBtn getGoumaiBtn() {
        return this.goumaiBtn;
    }
    
    public void setGoumaiBtn(DianKaJiaoYiBtn goumaiBtn) {
        this.goumaiBtn = goumaiBtn;
    }
    
    public BigDecimal getTotalsum() {
        return this.Totalsum;
    }
    
    public void setTotalsum(BigDecimal totalsum) {
        this.Totalsum = totalsum;
    }
    
    public JTable getTableDianKaList() {
        return this.tableDianKaList;
    }
    
    public void setTableDianKaList(JTable tableDianKaList) {
        this.tableDianKaList = tableDianKaList;
    }
    
    public BigDecimal getUnitprice() {
        return this.Unitprice;
    }
    
    public BigDecimal getSelectSellId() {
        return this.selectSellId;
    }
    
    public void resetTableData(SearchSellXianYuResultBean sa) {
        this.verVectors.clear();
        for (int i = 0; i < sa.getSellxianyus().size(); ++i) {
            Vector<String> verStrings = new Vector<>();
            verStrings.add(String.valueOf(((SellXianyu)sa.getSellxianyus().get(i)).getId()));
            verStrings.add(String.valueOf(((SellXianyu)sa.getSellxianyus().get(i)).getXianYuPoint()));
            verStrings.add(String.valueOf(((SellXianyu)sa.getSellxianyus().get(i)).getPricePoint()));
            verStrings.add(String.valueOf(((SellXianyu)sa.getSellxianyus().get(i)).getTotalPrice()));
            this.verVectors.add(verStrings);
        }
    }
}
