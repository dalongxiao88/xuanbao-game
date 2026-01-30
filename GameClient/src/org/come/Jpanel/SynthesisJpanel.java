package org.come.Jpanel;

import org.come.until.Util;
import org.come.until.GoodsListFromServerUntil;
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
import java.awt.Color;
import com.tool.role.RoleData;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import org.come.until.AccessSuitMsgUntil;
import org.come.entity.Goodstable;
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
import org.come.mouslisten.RoleEquipmentMouslisten;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import java.math.BigDecimal;
import com.tool.btn.SwitchPageBtn;
import com.tool.btn.WorkshopBtn;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class SynthesisJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane2;
    private JTree jTree;
    private WorkshopBtn workshopBtn;
    private SwitchPageBtn leftpageBtn;
    private SwitchPageBtn rightpageBtn;
    private static BigDecimal money;
    private static JLabel labzb;
    private static JLabel labyf;
    private static JLabel labShowAll;
    private static DefaultMutableTreeNode top;
    public static boolean showAll;
    public static JLabel[] GoodsListLabel;
    private static RoleEquipmentMouslisten[] goodsMouslistens;
    public static int page;
    private static JadeorGoodstableBean goodstableBean;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public SynthesisJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (SynthesisJpanel.labShowAll = new JLabel("", 0)).setBounds(11, 230, 17, 17);
            SynthesisJpanel.labShowAll.addMouseListener(new ShowAllSuitMouslisten(null, null, this, null));
            this.add(SynthesisJpanel.labShowAll);
            (this.workshopBtn = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "合 成", 2)).setBounds(60, 374, 59, 24);
            this.add(this.workshopBtn);
            (this.leftpageBtn = new SwitchPageBtn("inkImg/button/10.png", 1, 1)).setBounds(422, 408, 18, 18);
            this.add(this.leftpageBtn);
            (this.rightpageBtn = new SwitchPageBtn("inkImg/button/9.png", 1, 2)).setBounds(452, 408, 18, 18);
            this.add(this.rightpageBtn);
            (SynthesisJpanel.labyf = new JLabel("", 0)).setBounds(21, 263, 53, 51);
            SynthesisJpanel.labyf.addMouseListener(new MouseListener() {
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
                    int index = SynthesisJpanel.goodstableBean.getType() - 1;
                    if (SynthesisJpanel.goodstableBean.getPartJade() != null && index >= 0 && index < 5) {
                        StorageJadeMouslisten.showMsg(index);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(SynthesisJpanel.labyf);
            (SynthesisJpanel.labzb = new JLabel("", 0)).setBounds(100, 263, 53, 51);
            SynthesisJpanel.labzb.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = SynthesisJpanel.goodstableBean.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(SynthesisJpanel.labzb);
            for (int i = 0; i < 18; ++i) {
                SynthesisJpanel.GoodsListLabel[i] = new JLabel();
                int row = i % 6;
                int col = i / 6;
                SynthesisJpanel.GoodsListLabel[i].setBounds(173 + row * 51, 255 + col * 51, 49, 49);
                SynthesisJpanel.goodsMouslistens[i] = new RoleEquipmentMouslisten(i, this);
                SynthesisJpanel.GoodsListLabel[i].addMouseListener(SynthesisJpanel.goodsMouslistens[i]);
                this.add(SynthesisJpanel.GoodsListLabel[i]);
            }
            AccessSuitMsgUntil.showSuitMethod(SynthesisJpanel.top = new DefaultMutableTreeNode(""), SynthesisJpanel.showAll);
            (this.jTree = new JTree(SynthesisJpanel.top)).setOpaque(false);
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
                        TreePath path = SynthesisJpanel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (SynthesisJpanel.this.jTree.isExpanded(path)) {
                                SynthesisJpanel.this.jTree.collapsePath(path);
                            }
                            else {
                                SynthesisJpanel.this.jTree.expandPath(path);
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
                        SynthesisJpanel.this.jScrollPane2.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(selectionNode.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(selectionNode.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        StorageJadeJpanel3 jadeJpanel = new StorageJadeJpanel3();
                        StorageJadeJpanel3.partJade = jade;
                        SynthesisJpanel.this.jScrollPane2.setViewportView(jadeJpanel);
                    }
                    else {
                        int key = (int)AccessSuitMsgUntil.returnSuitID(nodeName);
                        RichLabel label = new RichLabel(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(key))).getIntroduce(), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(275);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        SynthesisJpanel.this.jScrollPane2.setViewportView(label);
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(18, 29, 149, 198);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.jScrollPane2 = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(185, 55, 297, 173);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
        }
        else {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (SynthesisJpanel.labShowAll = new JLabel("", 0)).setBounds(11, 230, 17, 17);
            SynthesisJpanel.labShowAll.addMouseListener(new ShowAllSuitMouslisten(null, null, this, null));
            this.add(SynthesisJpanel.labShowAll);
            (this.workshopBtn = new WorkshopBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "合成", 2)).setBounds(47, 374, 60, 26);
            this.add(this.workshopBtn);
            (this.leftpageBtn = new SwitchPageBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 1)).setBounds(422, 408, 18, 18);
            this.add(this.leftpageBtn);
            (this.rightpageBtn = new SwitchPageBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 2)).setBounds(452, 408, 18, 18);
            this.add(this.rightpageBtn);
            (SynthesisJpanel.labyf = new JLabel("", 0)).setBounds(21, 263, 53, 51);
            SynthesisJpanel.labyf.addMouseListener(new MouseListener() {
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
                    int index = SynthesisJpanel.goodstableBean.getType() - 1;
                    if (SynthesisJpanel.goodstableBean.getPartJade() != null && index >= 0 && index < 5) {
                        StorageJadeMouslisten.showMsg(index);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(SynthesisJpanel.labyf);
            (SynthesisJpanel.labzb = new JLabel("", 0)).setBounds(100, 263, 53, 51);
            SynthesisJpanel.labzb.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = SynthesisJpanel.goodstableBean.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(SynthesisJpanel.labzb);
            for (int i = 0; i < 18; ++i) {
                SynthesisJpanel.GoodsListLabel[i] = new JLabel();
                int row = i % 6;
                int col = i / 6;
                SynthesisJpanel.GoodsListLabel[i].setBounds(173 + row * 51, 255 + col * 51, 49, 49);
                SynthesisJpanel.goodsMouslistens[i] = new RoleEquipmentMouslisten(i, this);
                SynthesisJpanel.GoodsListLabel[i].addMouseListener(SynthesisJpanel.goodsMouslistens[i]);
                this.add(SynthesisJpanel.GoodsListLabel[i]);
            }
            AccessSuitMsgUntil.showSuitMethod(SynthesisJpanel.top = new DefaultMutableTreeNode(""), SynthesisJpanel.showAll);
            (this.jTree = new JTree(SynthesisJpanel.top)).setOpaque(false);
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
                        TreePath path = SynthesisJpanel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (SynthesisJpanel.this.jTree.isExpanded(path)) {
                                SynthesisJpanel.this.jTree.collapsePath(path);
                            }
                            else {
                                SynthesisJpanel.this.jTree.expandPath(path);
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
                        SynthesisJpanel.this.jScrollPane2.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(selectionNode.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(selectionNode.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        StorageJadeJpanel3 jadeJpanel = new StorageJadeJpanel3();
                        StorageJadeJpanel3.partJade = jade;
                        SynthesisJpanel.this.jScrollPane2.setViewportView(jadeJpanel);
                    }
                    else {
                        int key = (int)AccessSuitMsgUntil.returnSuitID(nodeName);
                        RichLabel label = new RichLabel(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(key))).getIntroduce(), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(275);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        SynthesisJpanel.this.jScrollPane2.setViewportView(label);
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
    
    public static void clearInterface() {
        SynthesisJpanel.money = null;
        SynthesisJpanel.goodstableBean.setGoodstable(null);
        SynthesisJpanel.goodstableBean.setPartJade(null);
        SynthesisJpanel.goodstableBean.setType(0);
        SynthesisJpanel.labzb.setIcon(null);
        SynthesisJpanel.labyf.setIcon(null);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B242.png");
            }
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/button/13.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            GoodsListFromServerUntil.drawIdlEqu(g, 173, 255, SynthesisJpanel.page, 18, 6);
            if (SynthesisJpanel.money != null) {
                Util.drawPrice(g, SynthesisJpanel.money, 87, 346);
            }
            if (!SynthesisJpanel.showAll) {
                g.drawImage(this.icon1.getImage(), 12, 231, 15, 15, this);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/setsynthesis_hc.png");
            }
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/showjadesuit.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            GoodsListFromServerUntil.drawIdlEqu(g, 173, 255, SynthesisJpanel.page, 18, 6);
            if (SynthesisJpanel.money != null) {
                Util.drawPrice(g, SynthesisJpanel.money, 87, 346);
            }
            if (!SynthesisJpanel.showAll) {
                g.drawImage(this.icon1.getImage(), 11, 231, 15, 15, this);
            }
        }
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
        return SynthesisJpanel.money;
    }
    
    public static void setMoney(BigDecimal money) {
        SynthesisJpanel.money = money;
    }
    
    public static JLabel getLabzb() {
        return SynthesisJpanel.labzb;
    }
    
    public static void setLabzb(JLabel labzb) {
        SynthesisJpanel.labzb = labzb;
    }
    
    public static JLabel getLabyf() {
        return SynthesisJpanel.labyf;
    }
    
    public static void setLabyf(JLabel labyf) {
        SynthesisJpanel.labyf = labyf;
    }
    
    public static DefaultMutableTreeNode getTop() {
        return SynthesisJpanel.top;
    }
    
    public static void setTop(DefaultMutableTreeNode top) {
        SynthesisJpanel.top = top;
    }
    
    public static boolean isShowAll() {
        return SynthesisJpanel.showAll;
    }
    
    public static void setShowAll(boolean showAll) {
        SynthesisJpanel.showAll = showAll;
    }
    
    public static JLabel[] getGoodsListLabel() {
        return SynthesisJpanel.GoodsListLabel;
    }
    
    public static void setGoodsListLabel(JLabel[] goodsListLabel) {
        SynthesisJpanel.GoodsListLabel = goodsListLabel;
    }
    
    public static RoleEquipmentMouslisten[] getGoodsMouslistens() {
        return SynthesisJpanel.goodsMouslistens;
    }
    
    public static void setGoodsMouslistens(RoleEquipmentMouslisten[] goodsMouslistens) {
        SynthesisJpanel.goodsMouslistens = goodsMouslistens;
    }
    
    public static JadeorGoodstableBean getGoodstableBean() {
        return SynthesisJpanel.goodstableBean;
    }
    
    public static void setGoodstableBean(JadeorGoodstableBean goodstableBean) {
        SynthesisJpanel.goodstableBean = goodstableBean;
    }
    
    static {
        SynthesisJpanel.showAll = true;
        SynthesisJpanel.GoodsListLabel = new JLabel[18];
        SynthesisJpanel.goodsMouslistens = new RoleEquipmentMouslisten[18];
        SynthesisJpanel.page = 0;
        SynthesisJpanel.goodstableBean = new JadeorGoodstableBean();
    }
}
