package org.come.Jpanel;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import org.come.entity.SellXianyu;
import java.text.SimpleDateFormat;
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

public class DianKaJiaoYiJiShouJpanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JScrollPane jScrollPane;
    private JTable tableDianKaList;
    private DefaultTableModel tableModel;
    private Vector<Vector<String>> verVectors;
    private Color foreground;
    private Color background;
    private JTextField jishoudianshuJTF;
    private JTextField shoujiaJTF;
    private DianKaJiaoYiBtn jishouBtn;
    private DianKaJiaoYiBtn xiajiaBtn;
    private DianKaJiaoYiBtn kmcz1;
    private int tab_row;
    private BigDecimal selectSellId;
    private BigDecimal Totalsum;
    private BigDecimal yajin;
    private BigDecimal myDianShu;
    private double yajinxishu;
    private ImageIcon bg;
    private ImageIcon icon;
    
    public DianKaJiaoYiJiShouJpanel() {
        this.tab_row = -1;
        this.yajinxishu = 0.0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(480, 520));
            this.setLayout(null);
            this.setOpaque(false);
            (this.jishouBtn = new DianKaJiaoYiBtn("inkImg/button/18.png", 1, "确定寄售", 11, this)).setBounds(195, 460, 100, 26);
            this.add(this.jishouBtn);
            (this.xiajiaBtn = new DianKaJiaoYiBtn("inkImg/button1/B30.png", 1, "下架", 12, this)).setBounds(368, 330, 34, 17);
            this.add(this.xiajiaBtn);
            (this.kmcz1 = new DianKaJiaoYiBtn("inkImg/button1/B32.png", 1, "购卡充值", 61, this)).setBounds(65, 425, 51, 17);
            this.add(this.kmcz1);
            this.Totalsum = new BigDecimal("0");
            this.myDianShu = new BigDecimal("100000");
            this.yajin = new BigDecimal("0");
            (this.jishoudianshuJTF = new JTextField()).setText("0");
            this.jishoudianshuJTF.setFont(UIUtils.TEXT_FONT1B);
            this.jishoudianshuJTF.setBackground(UIUtils.Color_BACK);
            this.jishoudianshuJTF.setBorder(BorderFactory.createEmptyBorder());
            this.jishoudianshuJTF.setForeground(Color.white);
            this.jishoudianshuJTF.setCaretColor(Color.white);
            this.jishoudianshuJTF.setBounds(192, 368, 151, 16);
            this.jishoudianshuJTF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.jishoudianshuJTF.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    if (str.length() == 0) {
                        DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.setText("");
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    String str = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    if (str.length() > 0 && Integer.parseInt(str) > Integer.parseInt(String.format("%.0f", new Object[] { DianKaJiaoYiJiShouJpanel.this.myDianShu }))) {
                        e.consume();
                        DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.setText(DianKaJiaoYiJiShouJpanel.this.myDianShu + "");
                        int sum = Integer.parseInt(DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText());
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    Util.changeTextColor(DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF, new BigDecimal(DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText()));
                }
            });
            this.add(this.jishoudianshuJTF);
            (this.shoujiaJTF = new JTextField()).setText("0");
            this.shoujiaJTF.setFont(UIUtils.TEXT_FONT1B);
            this.shoujiaJTF.setBackground(UIUtils.Color_BACK);
            this.shoujiaJTF.setBorder(BorderFactory.createEmptyBorder());
            this.shoujiaJTF.setForeground(Color.white);
            this.shoujiaJTF.setCaretColor(Color.white);
            this.shoujiaJTF.setBounds(192, 396, 151, 16);
            this.shoujiaJTF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.shoujiaJTF.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText();
                    if (str.length() == 0) {
                        DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.setText("");
                    }
                    if (str.length() > 0 && str.length() > 8) {
                        e.consume();
                        DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.setText("9999999");
                        int sum = Integer.parseInt(DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText());
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    Util.changeTextColor(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF, new BigDecimal(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText()));
                }
            });
            this.add(this.shoujiaJTF);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.tableModel.setDataVector(this.verVectors, Util.vector4);
            (this.tableDianKaList = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    DianKaJiaoYiJiShouJpanel.this.tab_row = DianKaJiaoYiJiShouJpanel.this.tableDianKaList.rowAtPoint(event.getPoint());
                    Object sellId = DianKaJiaoYiJiShouJpanel.this.tableDianKaList.getModel().getValueAt(DianKaJiaoYiJiShouJpanel.this.tab_row, 0);
                    if (sellId != null) {
                        DianKaJiaoYiJiShouJpanel.this.selectSellId = new BigDecimal(sellId.toString());
                    }
                    else {
                        DianKaJiaoYiJiShouJpanel.this.selectSellId = BigDecimal.ZERO;
                    }
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
                    if (this.column == 0) {
                        return;
                    }
                    FontMetrics fm = g.getFontMetrics();
                    String replace = this.getText().replace(",", "").trim();
                    int dx = fm.stringWidth(replace);
                    int i = this.getWidth() - dx;
                    if (this.column == 1) {
                        i -= 10;
                    }
                    if (this.column != 4) {
                        Util.drawPrice(g, new BigDecimal(replace), i / 2, 20);
                    }
                    else {
                        g.drawString(replace, i / 2 + 5, 20);
                    }
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
            this.tableDianKaList.getColumnModel().getColumn(0).setPreferredWidth(10);
            this.tableDianKaList.getColumnModel().getColumn(1).setPreferredWidth(90);
            this.tableDianKaList.getColumnModel().getColumn(2).setPreferredWidth(100);
            this.tableDianKaList.getColumnModel().getColumn(3).setPreferredWidth(130);
            this.tableDianKaList.getColumnModel().getColumn(4).setPreferredWidth(100);
            this.tableDianKaList.setRowHeight(35);
            this.tableDianKaList.isCellEditable(1, 1);
            this.tableDianKaList.setEnabled(true);
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
            (this.jishouBtn = new DianKaJiaoYiBtn("inkImg/hongmu/a2.png", 1, "确定寄售", 11, this)).setFont(UIUtils.TEXT_HY16);
            this.jishouBtn.setColors(UIUtils.COLOR_RED_BTNTEXT);
            this.jishouBtn.setBounds(195, 460, 85, 24);
            this.add(this.jishouBtn);
            (this.xiajiaBtn = new DianKaJiaoYiBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "下架", 12, this)).setColors(UIUtils.COLOR_RED_BTNTEXT);
            this.xiajiaBtn.setBounds(328, 327, 34, 17);
            this.add(this.xiajiaBtn);
            (this.kmcz1 = new DianKaJiaoYiBtn("inkImg/hongmu/B32h.png", 1, "购卡充值", 61, this)).setColors(UIUtils.COLOR_RED_BTNTEXT);
            this.kmcz1.setBounds(43, 424, 51, 17);
            this.add(this.kmcz1);
            this.Totalsum = new BigDecimal("0");
            this.myDianShu = new BigDecimal("100000");
            this.yajin = new BigDecimal("0");
            (this.jishoudianshuJTF = new JTextField()).setText("0");
            this.jishoudianshuJTF.setFont(UIUtils.TEXT_FONT1B);
            this.jishoudianshuJTF.setBackground(UIUtils.Color_BACK);
            this.jishoudianshuJTF.setBorder(BorderFactory.createEmptyBorder());
            this.jishoudianshuJTF.setForeground(Color.white);
            this.jishoudianshuJTF.setCaretColor(Color.white);
            this.jishoudianshuJTF.setBounds(177, 368, 151, 16);
            this.jishoudianshuJTF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.jishoudianshuJTF.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    if (str.length() == 0) {
                        DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.setText("");
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    String str = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    if (str.length() > 0 && Integer.parseInt(str) > Integer.parseInt(String.format("%.0f", new Object[] { DianKaJiaoYiJiShouJpanel.this.myDianShu }))) {
                        e.consume();
                        DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.setText(DianKaJiaoYiJiShouJpanel.this.myDianShu + "");
                        int sum = Integer.parseInt(DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText());
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    Util.changeTextColor(DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF, new BigDecimal(DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText()));
                }
            });
            this.add(this.jishoudianshuJTF);
            (this.shoujiaJTF = new JTextField()).setText("0");
            this.shoujiaJTF.setFont(UIUtils.TEXT_FONT1B);
            this.shoujiaJTF.setBackground(UIUtils.Color_BACK);
            this.shoujiaJTF.setBorder(BorderFactory.createEmptyBorder());
            this.shoujiaJTF.setForeground(Color.white);
            this.shoujiaJTF.setCaretColor(Color.white);
            this.shoujiaJTF.setBounds(177, 396, 151, 16);
            this.shoujiaJTF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.shoujiaJTF.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText();
                    if (str.length() == 0) {
                        DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.setText("");
                    }
                    if (str.length() > 0 && str.length() > 5) {
                        e.consume();
                        DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.setText("9999999");
                        int sum = Integer.parseInt(DianKaJiaoYiJiShouJpanel.this.jishoudianshuJTF.getText());
                        long price = Long.parseLong(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText());
                        DianKaJiaoYiJiShouJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                        DianKaJiaoYiJiShouJpanel.this.yajin = new BigDecimal(String.format("%.0f", new Object[] { Double.valueOf((double)(price * (long)sum) * DianKaJiaoYiJiShouJpanel.this.yajinxishu) }));
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    Util.changeTextColor(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF, new BigDecimal(DianKaJiaoYiJiShouJpanel.this.shoujiaJTF.getText()));
                }
            });
            this.add(this.shoujiaJTF);
            this.tableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.tableModel.setDataVector(this.verVectors, Util.vector4);
            (this.tableDianKaList = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    DianKaJiaoYiJiShouJpanel.this.tab_row = DianKaJiaoYiJiShouJpanel.this.tableDianKaList.rowAtPoint(event.getPoint());
                    Object sellId = DianKaJiaoYiJiShouJpanel.this.tableDianKaList.getModel().getValueAt(DianKaJiaoYiJiShouJpanel.this.tab_row, 0);
                    if (sellId != null) {
                        DianKaJiaoYiJiShouJpanel.this.selectSellId = new BigDecimal(sellId.toString());
                    }
                    else {
                        DianKaJiaoYiJiShouJpanel.this.selectSellId = BigDecimal.ZERO;
                    }
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
                    if (this.column == 0) {
                        return;
                    }
                    FontMetrics fm = g.getFontMetrics();
                    String replace = this.getText().replace(",", "").trim();
                    int dx = fm.stringWidth(replace);
                    int i = this.getWidth() - dx;
                    if (this.column == 1) {
                        i -= 10;
                    }
                    if (this.column != 4) {
                        Util.drawPrice(g, new BigDecimal(replace), i / 2, 20);
                    }
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
            this.tableDianKaList.getColumnModel().getColumn(0).setPreferredWidth(0);
            this.tableDianKaList.getColumnModel().getColumn(1).setPreferredWidth(75);
            this.tableDianKaList.getColumnModel().getColumn(2).setPreferredWidth(150);
            this.tableDianKaList.getColumnModel().getColumn(3).setPreferredWidth(140);
            this.tableDianKaList.getColumnModel().getColumn(4).setPreferredWidth(50);
            this.tableDianKaList.setRowHeight(35);
            this.tableDianKaList.isCellEditable(1, 1);
            this.tableDianKaList.setEnabled(true);
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
    
    public JTable getTableDianKaList() {
        return this.tableDianKaList;
    }
    
    public void setTableDianKaList(JTable tableDianKaList) {
        this.tableDianKaList = tableDianKaList;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.bg == null) {
                this.bg = CutButtonImage.getImage("Client/神通天演册/40×40/仙/dkjy-js.png", -1, -1);
            }
            g.drawImage(this.bg.getImage(), 49, 70, this);
            this.icon = CutButtonImage.getImage("inkImg/button/dkjy-icon.png", 15, 24);
            for (int i = 0; i < ((this.verVectors.size() >= 6) ? 6 : this.verVectors.size()); ++i) {
                g.drawImage(this.icon.getImage(), 64, 118 + 35 * i, this);
            }
            Util.drawTotalXianYu(g, 192, 439);
            Util.drawPrice(g, this.Totalsum, 333, 380);
            Util.drawPrice(g, this.yajin, 333, 408);
            Util.drawMoney(g, 333, 438);
        }
        else {
            super.paintComponent(g);
            if (this.bg == null) {
                this.bg = CutButtonImage.getImage("Client/神通天演册/40×40/仙/仙玉寄售2.png", -1, -1);
            }
            g.drawImage(this.bg.getImage(), 0, 0, this);
            this.icon = CutButtonImage.getImage("inkImg/button/dkjy-icon.png", 15, 24);
            for (int i = 0; i < ((this.verVectors.size() >= 6) ? 6 : this.verVectors.size()); ++i) {
                g.drawImage(this.icon.getImage(), 40, 114 + 35 * i, this);
            }
            Util.drawTotalXianYu(g, 176, 438);
            Util.drawPrice(g, this.Totalsum, 317, 380);
            Util.drawPrice(g, this.yajin, 317, 408);
            Util.drawMoney(g, 316, 438);
        }
    }
    
    public void changeFrom(int caozuo) {
    }
    
    public JTextField getJishoudianshuJTF() {
        return this.jishoudianshuJTF;
    }
    
    public void setJishoudianshuJTF(JTextField jishoudianshuJTF) {
        this.jishoudianshuJTF = jishoudianshuJTF;
    }
    
    public JTextField getShoujiaJTF() {
        return this.shoujiaJTF;
    }
    
    public void setShoujiaJTF(JTextField shoujiaJTF) {
        this.shoujiaJTF = shoujiaJTF;
    }
    
    public BigDecimal getTotalsum() {
        return this.Totalsum;
    }
    
    public void setTotalsum(BigDecimal totalsum) {
        this.Totalsum = totalsum;
    }
    
    public BigDecimal getMyDianShu() {
        return this.myDianShu;
    }
    
    public void setMyDianShu(BigDecimal myDianShu) {
        this.myDianShu = myDianShu;
    }
    
    public BigDecimal getYajin() {
        return this.yajin;
    }
    
    public void setYajin(BigDecimal yajin) {
        this.yajin = yajin;
    }
    
    public int getTab_row() {
        return this.tab_row;
    }
    
    public void setTab_row(int tab_row) {
        this.tab_row = tab_row;
    }
    
    public BigDecimal getSelectSellId() {
        return this.selectSellId;
    }
    
    public void resetTableData(SearchSellXianYuResultBean sa) {
        this.verVectors.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        for (int i = 0; i < sa.getSellxianyus().size(); ++i) {
            Vector<String> verStrings = new Vector<>();
            verStrings.add(String.valueOf(((SellXianyu)sa.getSellxianyus().get(i)).getId()));
            verStrings.add(String.valueOf(((SellXianyu)sa.getSellxianyus().get(i)).getXianYuPoint()));
            verStrings.add(String.valueOf(((SellXianyu)sa.getSellxianyus().get(i)).getPricePoint()));
            verStrings.add(String.valueOf(((SellXianyu)sa.getSellxianyus().get(i)).getTotalPrice()));
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date expireTime = dateFormat.parse(((SellXianyu)sa.getSellxianyus().get(i)).getExpireTime());
                verStrings.add(sdf.format(expireTime));
            }
            catch (ParseException e) {
                throw new RuntimeException(e);
            }
            this.verVectors.add(verStrings);
        }
        this.tableModel.fireTableDataChanged();
    }
}
