package org.come.Jpanel;

import org.come.until.Util;
import java.awt.Graphics;
import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import com.tool.tcpimg.RichLabel;
import org.come.until.UserMessUntil;
import org.come.bean.RoleSuitBean;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import org.come.until.CutButtonImage;
import org.come.entity.PartJade;
import com.tool.role.RoleData;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import org.come.until.AccessSuitMsgUntil;
import java.awt.Color;
import org.come.mouslisten.StorageJadeMouslisten;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.mouslisten.ShowAllSuitMouslisten;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.bean.JadeorGoodstableBean;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import java.math.BigDecimal;
import com.tool.btn.WorkshopBtn;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class JadeUpJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane2;
    private JTree jTree;
    private WorkshopBtn workshopBtn;
    private static BigDecimal money;
    private static int number;
    private static JLabel labyf;
    private static JLabel labpz;
    private static JLabel labgs;
    private static JLabel labShowAll;
    private static DefaultMutableTreeNode top;
    public static boolean showAll;
    private static JadeorGoodstableBean goodstableBean;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public JadeUpJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (JadeUpJpanel.labShowAll = new JLabel("", 0)).setBounds(11, 230, 17, 17);
            JadeUpJpanel.labShowAll.addMouseListener(new ShowAllSuitMouslisten(null, null, null, this));
            this.add(JadeUpJpanel.labShowAll);
            (JadeUpJpanel.labyf = new JLabel("", 0)).setBounds(123, 275, 54, 51);
            JadeUpJpanel.labyf.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    int index = JadeUpJpanel.goodstableBean.getType() - 1;
                    if (JadeUpJpanel.goodstableBean.getPartJade() != null && index >= 0 && index < 5) {
                        StorageJadeMouslisten.showMsg(index);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(JadeUpJpanel.labyf);
            (JadeUpJpanel.labpz = new JLabel("", 0)).setBounds(235, 279, 58, 16);
            JadeUpJpanel.labpz.setFont(UIUtils.TEXT_FONT2);
            JadeUpJpanel.labpz.setForeground(Color.white);
            this.add(JadeUpJpanel.labpz);
            (JadeUpJpanel.labgs = new JLabel("", 0)).setBounds(235, 306, 58, 16);
            JadeUpJpanel.labgs.setFont(UIUtils.TEXT_FONT1);
            JadeUpJpanel.labgs.setForeground(Color.white);
            this.add(JadeUpJpanel.labgs);
            (this.workshopBtn = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "升级", 5)).setBounds(314, 359, 59, 24);
            this.add(this.workshopBtn);
            AccessSuitMsgUntil.showSuitMethod(JadeUpJpanel.top = new DefaultMutableTreeNode(""), JadeUpJpanel.showAll);
            (this.jTree = new JTree(JadeUpJpanel.top)).setOpaque(false);
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
            cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.setRowHeight(20);
            this.jTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = JadeUpJpanel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (JadeUpJpanel.this.jTree.isExpanded(path)) {
                                JadeUpJpanel.this.jTree.collapsePath(path);
                            }
                            else {
                                JadeUpJpanel.this.jTree.expandPath(path);
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
                        JadeUpJpanel.this.jScrollPane2.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(selectionNode.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(selectionNode.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        StorageJadeJpanel4 jadeJpanel = new StorageJadeJpanel4();
                        StorageJadeJpanel4.partJade = jade;
                        JadeUpJpanel.this.jScrollPane2.setViewportView(jadeJpanel);
                    }
                    else {
                        int key = (int)AccessSuitMsgUntil.returnSuitID(nodeName);
                        RichLabel label = new RichLabel(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(key))).getIntroduce(), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(275);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        JadeUpJpanel.this.jScrollPane2.setViewportView(label);
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(18, 29, 148, 198);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.jScrollPane2 = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(185, 52, 295, 176);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
        }
        else {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (JadeUpJpanel.labShowAll = new JLabel("", 0)).setBounds(11, 230, 17, 17);
            JadeUpJpanel.labShowAll.addMouseListener(new ShowAllSuitMouslisten(null, null, null, this));
            this.add(JadeUpJpanel.labShowAll);
            (JadeUpJpanel.labyf = new JLabel("", 0)).setBounds(123, 275, 54, 51);
            JadeUpJpanel.labyf.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    int index = JadeUpJpanel.goodstableBean.getType() - 1;
                    if (JadeUpJpanel.goodstableBean.getPartJade() != null && index >= 0 && index < 5) {
                        StorageJadeMouslisten.showMsg(index);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(JadeUpJpanel.labyf);
            (JadeUpJpanel.labpz = new JLabel("", 0)).setBounds(235, 279, 58, 16);
            JadeUpJpanel.labpz.setFont(UIUtils.TEXT_FONT2);
            JadeUpJpanel.labpz.setForeground(Color.white);
            this.add(JadeUpJpanel.labpz);
            (JadeUpJpanel.labgs = new JLabel("", 0)).setBounds(235, 306, 58, 16);
            JadeUpJpanel.labgs.setFont(UIUtils.TEXT_FONT1);
            JadeUpJpanel.labgs.setForeground(Color.white);
            this.add(JadeUpJpanel.labgs);
            (this.workshopBtn = new WorkshopBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "升级", 5)).setBounds(314, 359, 60, 26);
            this.add(this.workshopBtn);
            AccessSuitMsgUntil.showSuitMethod(JadeUpJpanel.top = new DefaultMutableTreeNode(""), JadeUpJpanel.showAll);
            (this.jTree = new JTree(JadeUpJpanel.top)).setOpaque(false);
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
            cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.setRowHeight(20);
            this.jTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = JadeUpJpanel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (JadeUpJpanel.this.jTree.isExpanded(path)) {
                                JadeUpJpanel.this.jTree.collapsePath(path);
                            }
                            else {
                                JadeUpJpanel.this.jTree.expandPath(path);
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
                        JadeUpJpanel.this.jScrollPane2.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(selectionNode.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(selectionNode.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        StorageJadeJpanel4 jadeJpanel = new StorageJadeJpanel4();
                        StorageJadeJpanel4.partJade = jade;
                        JadeUpJpanel.this.jScrollPane2.setViewportView(jadeJpanel);
                    }
                    else {
                        int key = (int)AccessSuitMsgUntil.returnSuitID(nodeName);
                        RichLabel label = new RichLabel(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(key))).getIntroduce(), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(275);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        JadeUpJpanel.this.jScrollPane2.setViewportView(label);
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(18, 29, 142, 194);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.jScrollPane2 = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(185, 55, 290, 170);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B245.png");
            }
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/button/B88.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            Util.drawMoney(g, 194, 389);
            if (JadeUpJpanel.money != null) {
                Util.drawPrice(g, JadeUpJpanel.money, 194, 363);
            }
            if (JadeUpJpanel.number >= 0) {
                g.setColor(Color.red);
                g.drawString(JadeUpJpanel.number + "", 350, 317);
            }
            if (!JadeUpJpanel.showAll) {
                g.drawImage(this.icon1.getImage(), 12, 231, 15, 15, this);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/setsynthesis_yfsj.png");
            }
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/showjadesuit.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            Util.drawMoney(g, 194, 389);
            if (JadeUpJpanel.money != null) {
                Util.drawPrice(g, JadeUpJpanel.money, 194, 363);
            }
            if (JadeUpJpanel.number >= 0) {
                g.drawString(String.valueOf(JadeUpJpanel.number), 350, 317);
            }
            if (!JadeUpJpanel.showAll) {
                g.drawImage(this.icon1.getImage(), 12, 231, 15, 15, this);
            }
        }
    }
    
    public static void clearInterface() {
        JadeUpJpanel.money = null;
        JadeUpJpanel.number = -1;
        JadeUpJpanel.goodstableBean.setPartJade(null);
        JadeUpJpanel.goodstableBean.setType(0);
        JadeUpJpanel.labyf.setIcon(null);
        JadeUpJpanel.labgs.setText("");
        JadeUpJpanel.labpz.setText("");
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public JScrollPane getjScrollPane2() {
        return this.jScrollPane2;
    }
    
    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }
    
    public JTree getjTree() {
        return this.jTree;
    }
    
    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }
    
    public WorkshopBtn getWorkshopBtn() {
        return this.workshopBtn;
    }
    
    public void setWorkshopBtn(WorkshopBtn workshopBtn) {
        this.workshopBtn = workshopBtn;
    }
    
    public static BigDecimal getMoney() {
        return JadeUpJpanel.money;
    }
    
    public static void setMoney(BigDecimal money) {
        JadeUpJpanel.money = money;
    }
    
    public static JLabel getLabyf() {
        return JadeUpJpanel.labyf;
    }
    
    public static void setLabyf(JLabel labyf) {
        JadeUpJpanel.labyf = labyf;
    }
    
    public static JLabel getLabpz() {
        return JadeUpJpanel.labpz;
    }
    
    public static void setLabpz(JLabel labpz) {
        JadeUpJpanel.labpz = labpz;
    }
    
    public static JLabel getLabgs() {
        return JadeUpJpanel.labgs;
    }
    
    public static void setLabgs(JLabel labgs) {
        JadeUpJpanel.labgs = labgs;
    }
    
    public static JLabel getLabShowAll() {
        return JadeUpJpanel.labShowAll;
    }
    
    public static void setLabShowAll(JLabel labShowAll) {
        JadeUpJpanel.labShowAll = labShowAll;
    }
    
    public static DefaultMutableTreeNode getTop() {
        return JadeUpJpanel.top;
    }
    
    public static void setTop(DefaultMutableTreeNode top) {
        JadeUpJpanel.top = top;
    }
    
    public static boolean isShowAll() {
        return JadeUpJpanel.showAll;
    }
    
    public static void setShowAll(boolean showAll) {
        JadeUpJpanel.showAll = showAll;
    }
    
    public static JadeorGoodstableBean getGoodstableBean() {
        return JadeUpJpanel.goodstableBean;
    }
    
    public static void setGoodstableBean(JadeorGoodstableBean goodstableBean) {
        JadeUpJpanel.goodstableBean = goodstableBean;
    }
    
    public static int getNumber() {
        return JadeUpJpanel.number;
    }
    
    public static void setNumber(int number) {
        JadeUpJpanel.number = number;
    }
    
    static {
        JadeUpJpanel.number = -1;
        JadeUpJpanel.showAll = true;
        JadeUpJpanel.goodstableBean = new JadeorGoodstableBean();
    }
}
