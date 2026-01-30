package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import org.come.until.ScrollUI;
import org.come.until.SrcollPanelUI;
import com.tool.tcpimg.RichLabel;
import org.come.until.UserMessUntil;
import org.come.bean.RoleSuitBean;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import org.come.until.CutButtonImage;
import org.come.entity.PartJade;
import com.tool.role.RoleData;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import org.come.until.AccessSuitMsgUntil;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import org.come.mouslisten.ShowAllSuitMouslisten;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.goodbtn;
import org.come.bean.JadeorGoodstableBean;
import org.come.mouslisten.YuFuGoodsMouslisten;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.math.BigDecimal;
import javax.swing.JLabel;
import com.tool.btn.WorkshopBtn;
import javax.swing.JPanel;

public class ExchangeValueJpanel extends JPanel
{
    private WorkshopBtn workshopBtn;
    private JLabel labTzyf;
    private JLabel labShowAll;
    public static BigDecimal khdlxz;
    private JTextField textNum;
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane2;
    private JTree jTree;
    private DefaultMutableTreeNode top;
    public static boolean showAll;
    private JLabel[] GoodsListLabel;
    private YuFuGoodsMouslisten[] goodsMouslistens;
    private boolean shoptext;
    private int goodPosition;
    private JadeorGoodstableBean jadeorGoodstableBean;
    private goodbtn[] btnrights;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public ExchangeValueJpanel() {
        this.GoodsListLabel = new JLabel[24];
        this.goodsMouslistens = new YuFuGoodsMouslisten[24];
        this.shoptext = false;
        this.jadeorGoodstableBean = new JadeorGoodstableBean();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(560, 495));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 63);
            offBtn.setBounds(515, 10, 25, 25);
            this.add(offBtn);
            (this.workshopBtn = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "兑换", 7)).setBounds(95, 454, 59, 24);
            this.add(this.workshopBtn);
            (this.labTzyf = new JLabel("", 0)).setBounds(100, 279, 54, 51);
            this.add(this.labTzyf);
            (this.labShowAll = new JLabel("", 0)).setBounds(47, 256, 15, 15);
            this.labShowAll.addMouseListener(new ShowAllSuitMouslisten(this, null, null, null));
            this.add(this.labShowAll);
            (this.textNum = new JTextField()).setBounds(57, 389, 141, 16);
            this.textNum.setFont(UIUtils.TEXT_FONT1);
            this.textNum.setOpaque(false);
            this.textNum.setBorder(BorderFactory.createEmptyBorder());
            this.textNum.setCaretColor(Color.white);
            this.textNum.setForeground(Color.white);
            this.textNum.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                        return;
                    }
                    String str = ExchangeValueJpanel.this.textNum.getText();
                    if (str.length() == 0) {
                        ExchangeValueJpanel.this.textNum.setText("");
                    }
                    if (str.length() == 1 && Long.parseLong(str) == 0L) {
                        ExchangeValueJpanel.this.textNum.setText("");
                    }
                    if (str.length() >= 9) {
                        e.consume();
                        return;
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.textNum.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String str = ExchangeValueJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() == 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() == null && ExchangeValueJpanel.this.jadeorGoodstableBean.getGoodstable() == null) {
                                return;
                            }
                            int price = 3;
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() != 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() != null) {
                                price = AccessSuitMsgUntil.returnExcNum(ExchangeValueJpanel.this.jadeorGoodstableBean.getType());
                            }
                            ExchangeValueJpanel.khdlxz = new BigDecimal(Long.parseLong(str) * (long)price);
                        }
                    }
                    else {
                        ExchangeValueJpanel.khdlxz = null;
                    }
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String str = ExchangeValueJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() == 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() == null && ExchangeValueJpanel.this.jadeorGoodstableBean.getGoodstable() == null) {
                                return;
                            }
                            int price = 3;
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() != 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() != null) {
                                price = AccessSuitMsgUntil.returnExcNum(ExchangeValueJpanel.this.jadeorGoodstableBean.getType());
                            }
                            ExchangeValueJpanel.khdlxz = new BigDecimal(Long.parseLong(str) * (long)price);
                        }
                    }
                    else {
                        ExchangeValueJpanel.khdlxz = null;
                    }
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                    String str = ExchangeValueJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() == 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() == null && ExchangeValueJpanel.this.jadeorGoodstableBean.getGoodstable() == null) {
                                return;
                            }
                            int price = 3;
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() != 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() != null) {
                                price = AccessSuitMsgUntil.returnExcNum(ExchangeValueJpanel.this.jadeorGoodstableBean.getType());
                            }
                            ExchangeValueJpanel.khdlxz = new BigDecimal(Long.parseLong(str) * (long)price);
                        }
                    }
                    else {
                        ExchangeValueJpanel.khdlxz = null;
                    }
                }
            });
            this.add(this.textNum);
            AccessSuitMsgUntil.showSuitMethod(this.top = new DefaultMutableTreeNode(""), ExchangeValueJpanel.showAll);
            (this.jTree = new JTree(this.top)).setOpaque(false);
            this.jTree.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                    if (node.getLevel() == 2) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(node.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(node.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        if (jade.isJade()) {
                            this.setForeground(new Color(102, 102, 102));
                        }
                        else {
                            this.setForeground(new Color(213, 210, 209));
                        }
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null);
            try {
                cellRenderer.setOpenIcon(CutButtonImage.cuts("inkImg/button/B108.png")[0]);
                cellRenderer.setClosedIcon(CutButtonImage.cuts("inkImg/button/B109.png")[0]);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            cellRenderer.setFont(new Font("宋体", 0, 14));
            cellRenderer.setBackgroundNonSelectionColor(UIUtils.Color_BACK);
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(UIUtils.Color_BACK);
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.setRowHeight(20);
            this.jTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = ExchangeValueJpanel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (ExchangeValueJpanel.this.jTree.isExpanded(path)) {
                                ExchangeValueJpanel.this.jTree.collapsePath(path);
                            }
                            else {
                                ExchangeValueJpanel.this.jTree.expandPath(path);
                            }
                        }
                    }
                }
            });
            this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    JTree tree = (JTree)e.getSource();
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                    if (selectionNode == null) {
                        ExchangeValueJpanel.this.jScrollPane2.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(selectionNode.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(selectionNode.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        StorageJadeJpanel jadeJpanel = new StorageJadeJpanel();
                        StorageJadeJpanel.partJade = jade;
                        ExchangeValueJpanel.this.jScrollPane2.setViewportView(jadeJpanel);
                    }
                    else {
                        int key = (int)AccessSuitMsgUntil.returnSuitID(nodeName);
                        RichLabel label = new RichLabel(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(key))).getIntroduce(), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(270);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        ExchangeValueJpanel.this.jScrollPane2.setViewportView(label);
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(48, 63, 166, 188);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.jScrollPane2 = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(217, 46, 309, 206);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
            for (int i = 0; i < 24; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).setBounds(217 + row * 51, 262 + col * 51, 49, 49);
                this.goodsMouslistens[i] = new YuFuGoodsMouslisten(i, this);
                this.GoodsListLabel[i].addMouseListener(this.goodsMouslistens[i]);
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(523, 266 + i * 35, 35, 31);
                this.add(this.btnrights[i]);
            }
        }
        else {
            this.setPreferredSize(new Dimension(526, 517));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 63);
            offBtn.setBounds(500, 0, 25, 25);
            this.add(offBtn);
            (this.workshopBtn = new WorkshopBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "兑换", 7)).setBounds(70, 464, 60, 26);
            this.add(this.workshopBtn);
            (this.labTzyf = new JLabel("", 0)).setBounds(74, 290, 54, 51);
            this.add(this.labTzyf);
            (this.labShowAll = new JLabel("", 0)).setBounds(22, 270, 15, 15);
            this.labShowAll.addMouseListener(new ShowAllSuitMouslisten(this, null, null, null));
            this.add(this.labShowAll);
            (this.textNum = new JTextField()).setBounds(34, 401, 136, 16);
            this.textNum.setFont(UIUtils.TEXT_FONT1);
            this.textNum.setOpaque(false);
            this.textNum.setBorder(BorderFactory.createEmptyBorder());
            this.textNum.setCaretColor(Color.white);
            this.textNum.setForeground(Color.white);
            this.textNum.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                        return;
                    }
                    String str = ExchangeValueJpanel.this.textNum.getText();
                    if (str.length() == 0) {
                        ExchangeValueJpanel.this.textNum.setText("");
                    }
                    if (str.length() == 1 && Long.parseLong(str) == 0L) {
                        ExchangeValueJpanel.this.textNum.setText("");
                    }
                    if (str.length() >= 8) {
                        e.consume();
                        return;
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.textNum.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String str = ExchangeValueJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() == 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() == null && ExchangeValueJpanel.this.jadeorGoodstableBean.getGoodstable() == null) {
                                return;
                            }
                            int price = 3;
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() != 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() != null) {
                                price = AccessSuitMsgUntil.returnExcNum(ExchangeValueJpanel.this.jadeorGoodstableBean.getType());
                            }
                            ExchangeValueJpanel.khdlxz = new BigDecimal(Long.parseLong(str) * (long)price);
                        }
                    }
                    else {
                        ExchangeValueJpanel.khdlxz = null;
                    }
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String str = ExchangeValueJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() == 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() == null && ExchangeValueJpanel.this.jadeorGoodstableBean.getGoodstable() == null) {
                                return;
                            }
                            int price = 3;
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() != 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() != null) {
                                price = AccessSuitMsgUntil.returnExcNum(ExchangeValueJpanel.this.jadeorGoodstableBean.getType());
                            }
                            ExchangeValueJpanel.khdlxz = new BigDecimal(Long.parseLong(str) * (long)price);
                        }
                    }
                    else {
                        ExchangeValueJpanel.khdlxz = null;
                    }
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                    String str = ExchangeValueJpanel.this.textNum.getText();
                    if (str != null && !str.equals("")) {
                        if (Long.parseLong(str) > 0L) {
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() == 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() == null && ExchangeValueJpanel.this.jadeorGoodstableBean.getGoodstable() == null) {
                                return;
                            }
                            int price = 3;
                            if (ExchangeValueJpanel.this.jadeorGoodstableBean.getType() != 0 && ExchangeValueJpanel.this.jadeorGoodstableBean.getPartJade() != null) {
                                price = AccessSuitMsgUntil.returnExcNum(ExchangeValueJpanel.this.jadeorGoodstableBean.getType());
                            }
                            ExchangeValueJpanel.khdlxz = new BigDecimal(Long.parseLong(str) * (long)price);
                        }
                    }
                    else {
                        ExchangeValueJpanel.khdlxz = null;
                    }
                }
            });
            this.add(this.textNum);
            AccessSuitMsgUntil.showSuitMethod(this.top = new DefaultMutableTreeNode(""), ExchangeValueJpanel.showAll);
            (this.jTree = new JTree(this.top)).setOpaque(false);
            this.jTree.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                    if (node.getLevel() == 2) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(node.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(node.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        if (jade.isJade()) {
                            this.setForeground(new Color(102, 102, 102));
                        }
                        else {
                            this.setForeground(new Color(213, 210, 209));
                        }
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null);
            try {
                cellRenderer.setOpenIcon(CutButtonImage.cuts("inkImg/button/B108.png")[0]);
                cellRenderer.setClosedIcon(CutButtonImage.cuts("inkImg/button/B109.png")[0]);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            cellRenderer.setFont(new Font("宋体", 0, 14));
            cellRenderer.setBackgroundNonSelectionColor(UIUtils.Color_BACK);
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(UIUtils.Color_BACK);
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.setRowHeight(20);
            this.jTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = ExchangeValueJpanel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (ExchangeValueJpanel.this.jTree.isExpanded(path)) {
                                ExchangeValueJpanel.this.jTree.collapsePath(path);
                            }
                            else {
                                ExchangeValueJpanel.this.jTree.expandPath(path);
                            }
                        }
                    }
                }
            });
            this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    JTree tree = (JTree)e.getSource();
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                    if (selectionNode == null) {
                        ExchangeValueJpanel.this.jScrollPane2.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(selectionNode.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(selectionNode.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        StorageJadeJpanel jadeJpanel = new StorageJadeJpanel();
                        StorageJadeJpanel.partJade = jade;
                        ExchangeValueJpanel.this.jScrollPane2.setViewportView(jadeJpanel);
                    }
                    else {
                        int key = (int)AccessSuitMsgUntil.returnSuitID(nodeName);
                        RichLabel label = new RichLabel(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(key))).getIntroduce(), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(270);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        ExchangeValueJpanel.this.jScrollPane2.setViewportView(label);
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(29, 77, 154, 185);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.jScrollPane2 = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(203, 59, 291, 203);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
            for (int i = 0; i < 24; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).setBounds(193 + row * 51, 274 + col * 51, 49, 49);
                this.goodsMouslistens[i] = new YuFuGoodsMouslisten(i, this);
                this.GoodsListLabel[i].addMouseListener(this.goodsMouslistens[i]);
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, i)).setBounds(498, 270 + i * 33, 24, 33);
                this.add(this.btnrights[i]);
            }
        }
    }
    
    public void btntype(int path) {
        for (int i = 0; i < path; ++i) {
            this.btnrights[i].btntypechange(2);
        }
    }
    
    public void PaintingText(int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }
    
    public void ClearText() {
        this.shoptext = false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S100.png");
            }
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/button/B88.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 552, 495, this);
            GoodsListFromServerUntil.draw(g, 217, 262);
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.white);
            if (ExchangeValueJpanel.khdlxz != null) {
                g.drawString(ExchangeValueJpanel.khdlxz.toString(), 57, 437);
            }
            if (!ExchangeValueJpanel.showAll) {
                g.drawImage(this.icon1.getImage(), 47, 256, 15, 15, this);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/exchangevalue.png");
            }
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/showjadesuit.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 526, 517, this);
            GoodsListFromServerUntil.draw(g, 193, 274);
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.white);
            if (ExchangeValueJpanel.khdlxz != null) {
                g.drawString(ExchangeValueJpanel.khdlxz.toString(), 36, 449);
            }
            if (!ExchangeValueJpanel.showAll) {
                g.drawImage(this.icon1.getImage(), 23, 268, 15, 15, this);
            }
        }
    }
    
    public void clearInterface() {
        this.labTzyf.setIcon(null);
        this.textNum.setText("");
        ExchangeValueJpanel.khdlxz = null;
        this.jadeorGoodstableBean.setGoodstable(null);
        this.jadeorGoodstableBean.setPartJade(null);
        this.jadeorGoodstableBean.setType(0);
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public WorkshopBtn getWorkshopBtn() {
        return this.workshopBtn;
    }
    
    public void setWorkshopBtn(WorkshopBtn workshopBtn) {
        this.workshopBtn = workshopBtn;
    }
    
    public JLabel getLabTzyf() {
        return this.labTzyf;
    }
    
    public void setLabTzyf(JLabel labTzyf) {
        this.labTzyf = labTzyf;
    }
    
    public BigDecimal getKhdlxz() {
        return ExchangeValueJpanel.khdlxz;
    }
    
    public void setKhdlxz(BigDecimal khdlxz) {
        ExchangeValueJpanel.khdlxz = khdlxz;
    }
    
    public JTree getjTree() {
        return this.jTree;
    }
    
    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }
    
    public YuFuGoodsMouslisten[] getGoodsMouslistens() {
        return this.goodsMouslistens;
    }
    
    public void setGoodsMouslistens(YuFuGoodsMouslisten[] goodsMouslistens) {
        this.goodsMouslistens = goodsMouslistens;
    }
    
    public JTextField getTextNum() {
        return this.textNum;
    }
    
    public void setTextNum(JTextField textNum) {
        this.textNum = textNum;
    }
    
    public DefaultMutableTreeNode getTop() {
        return this.top;
    }
    
    public void setTop(DefaultMutableTreeNode top) {
        this.top = top;
    }
    
    public JadeorGoodstableBean getJadeorGoodstableBean() {
        return this.jadeorGoodstableBean;
    }
    
    public void setJadeorGoodstableBean(JadeorGoodstableBean jadeorGoodstableBean) {
        this.jadeorGoodstableBean = jadeorGoodstableBean;
    }
    
    static {
        ExchangeValueJpanel.showAll = true;
    }
}
