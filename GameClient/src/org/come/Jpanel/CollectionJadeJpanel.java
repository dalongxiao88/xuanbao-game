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
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.CutButtonImage;
import org.come.entity.PartJade;
import java.awt.Color;
import com.tool.role.RoleData;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import org.come.until.AccessSuitMsgUntil;
import org.come.mouslisten.ShowAllSuitMouslisten;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.bean.JadeorGoodstableBean;
import com.tool.btn.goodbtn;
import org.come.mouslisten.YuFuGoodsMouslisten;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import java.math.BigDecimal;
import javax.swing.JLabel;
import com.tool.btn.WorkshopBtn;
import javax.swing.JPanel;

public class CollectionJadeJpanel extends JPanel
{
    private WorkshopBtn workshopBtn;
    private WorkshopBtn workshopBtn2;
    private JLabel labTzyf;
    private JLabel labShowAll;
    public static BigDecimal money;
    public static BigDecimal sxlxz;
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane2;
    private JTree jTree;
    private DefaultMutableTreeNode top;
    public static boolean showAll;
    private JLabel[] GoodsListLabel;
    private YuFuGoodsMouslisten[] goodsMouslistens;
    private goodbtn[] btnrights;
    private boolean shoptext;
    private int goodPosition;
    private JadeorGoodstableBean goodstableBean;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public CollectionJadeJpanel() {
        this.GoodsListLabel = new JLabel[24];
        this.goodsMouslistens = new YuFuGoodsMouslisten[24];
        this.shoptext = false;
        this.goodstableBean = new JadeorGoodstableBean();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(558, 495));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 64);
            offBtn.setBounds(515, 10, 25, 25);
            this.add(offBtn);
            (this.workshopBtn = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "收录", 8)).setBounds(93, 446, 59, 24);
            this.add(this.workshopBtn);
            (this.workshopBtn2 = new WorkshopBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "获得", 11)).setBounds(163, 422, 34, 17);
            this.add(this.workshopBtn2);
            (this.labTzyf = new JLabel()).setBounds(98, 277, 54, 51);
            this.add(this.labTzyf);
            (this.labShowAll = new JLabel("", 0)).setBounds(48, 257, 15, 15);
            this.labShowAll.addMouseListener(new ShowAllSuitMouslisten(null, this, null, null));
            this.add(this.labShowAll);
            AccessSuitMsgUntil.showSuitMethod(this.top = new DefaultMutableTreeNode(""), CollectionJadeJpanel.showAll);
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
            cellRenderer.setFont(UIUtils.TEXT_FONT1);
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
                        TreePath path = CollectionJadeJpanel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (CollectionJadeJpanel.this.jTree.isExpanded(path)) {
                                CollectionJadeJpanel.this.jTree.collapsePath(path);
                            }
                            else {
                                CollectionJadeJpanel.this.jTree.expandPath(path);
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
                        CollectionJadeJpanel.this.jScrollPane2.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(selectionNode.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(selectionNode.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        StorageJadeJpanel2 jadeJpanel = new StorageJadeJpanel2();
                        StorageJadeJpanel2.partJade = jade;
                        CollectionJadeJpanel.this.jScrollPane2.setViewportView(jadeJpanel);
                    }
                    else {
                        int key = (int)AccessSuitMsgUntil.returnSuitID(nodeName);
                        RichLabel label = new RichLabel(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(key))).getIntroduce(), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(270);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        CollectionJadeJpanel.this.jScrollPane2.setViewportView(label);
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(51, 63, 163, 188);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.jScrollPane2 = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane2.getViewport().setOpaque(false);
            this.jScrollPane2.setOpaque(false);
            this.jScrollPane2.setBounds(219, 45, 306, 206);
            this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane2.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane2);
            for (int i = 0; i < 24; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).setBounds(216 + row * 51, 261 + col * 51, 49, 49);
                this.goodsMouslistens[i] = new YuFuGoodsMouslisten(i, null);
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
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 64);
            offBtn.setBounds(500, 0, 25, 25);
            this.add(offBtn);
            (this.workshopBtn = new WorkshopBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "收录", 8)).setBounds(70, 460, 60, 26);
            this.add(this.workshopBtn);
            (this.workshopBtn2 = new WorkshopBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "获得", 11)).setBounds(138, 436, 34, 17);
            this.add(this.workshopBtn2);
            (this.labTzyf = new JLabel()).setBounds(74, 290, 54, 51);
            this.add(this.labTzyf);
            (this.labShowAll = new JLabel("", 0)).setBounds(22, 270, 15, 15);
            this.labShowAll.addMouseListener(new ShowAllSuitMouslisten(null, this, null, null));
            this.add(this.labShowAll);
            AccessSuitMsgUntil.showSuitMethod(this.top = new DefaultMutableTreeNode(""), CollectionJadeJpanel.showAll);
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
            cellRenderer.setFont(UIUtils.TEXT_FONT1);
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
                        TreePath path = CollectionJadeJpanel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (CollectionJadeJpanel.this.jTree.isExpanded(path)) {
                                CollectionJadeJpanel.this.jTree.collapsePath(path);
                            }
                            else {
                                CollectionJadeJpanel.this.jTree.expandPath(path);
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
                        CollectionJadeJpanel.this.jScrollPane2.setViewportView(null);
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (selectionNode.isLeaf()) {
                        int suitid = (int)AccessSuitMsgUntil.returnSuitID(selectionNode.getParent().toString());
                        int partId = (int)AccessSuitMsgUntil.returnPartsID(selectionNode.getUserObject().toString());
                        PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId);
                        StorageJadeJpanel2 jadeJpanel = new StorageJadeJpanel2();
                        StorageJadeJpanel2.partJade = jade;
                        CollectionJadeJpanel.this.jScrollPane2.setViewportView(jadeJpanel);
                    }
                    else {
                        int key = (int)AccessSuitMsgUntil.returnSuitID(nodeName);
                        RichLabel label = new RichLabel(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(key))).getIntroduce(), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(270);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        CollectionJadeJpanel.this.jScrollPane2.setViewportView(label);
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
                this.goodsMouslistens[i] = new YuFuGoodsMouslisten(i, null);
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
                this.icon = new ImageIcon("inkImg/background/S98.png");
            }
            if (this.icon1 == null) {
                this.icon1 = CutButtonImage.getImage("inkImg/button/B88.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, 552, 495, this);
            GoodsListFromServerUntil.draw(g, 216, 261);
            if (CollectionJadeJpanel.money != null) {
                Util.drawPrice(g, CollectionJadeJpanel.money, 56, 365);
            }
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.white);
            if (CollectionJadeJpanel.sxlxz != null) {
                g.drawString(CollectionJadeJpanel.sxlxz.toString(), 56, 401);
            }
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值") != null) {
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("灵修值") + "", 56, 436);
            }
            if (!CollectionJadeJpanel.showAll) {
                g.drawImage(this.icon1.getImage(), 48, 257, 15, 15, this);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/collectionjade.png");
            }
            if (this.icon1 == null) {
                this.icon1 = CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, 526, 517, this);
            GoodsListFromServerUntil.draw(g, 193, 274);
            if (CollectionJadeJpanel.money != null) {
                Util.drawPrice(g, CollectionJadeJpanel.money, 36, 378);
            }
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.white);
            if (CollectionJadeJpanel.sxlxz != null) {
                g.drawString(CollectionJadeJpanel.sxlxz.toString(), 36, 414);
            }
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值") != null) {
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("灵修值") + "", 36, 449);
            }
            if (!CollectionJadeJpanel.showAll) {
                g.drawImage(this.icon1.getImage(), 23, 268, 15, 15, this);
            }
        }
    }
    
    public void clearInterface() {
        this.labTzyf.setIcon(null);
        CollectionJadeJpanel.sxlxz = null;
        CollectionJadeJpanel.money = null;
        this.goodstableBean.setPartJade(null);
        this.goodstableBean.setType(0);
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
    
    public WorkshopBtn getWorkshopBtn2() {
        return this.workshopBtn2;
    }
    
    public void setWorkshopBtn2(WorkshopBtn workshopBtn2) {
        this.workshopBtn2 = workshopBtn2;
    }
    
    public JLabel getLabTzyf() {
        return this.labTzyf;
    }
    
    public void setLabTzyf(JLabel labTzyf) {
        this.labTzyf = labTzyf;
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
    
    public DefaultMutableTreeNode getTop() {
        return this.top;
    }
    
    public void setTop(DefaultMutableTreeNode top) {
        this.top = top;
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public YuFuGoodsMouslisten[] getGoodsMouslistens() {
        return this.goodsMouslistens;
    }
    
    public void setGoodsMouslistens(YuFuGoodsMouslisten[] goodsMouslistens) {
        this.goodsMouslistens = goodsMouslistens;
    }
    
    public JadeorGoodstableBean getGoodstableBean() {
        return this.goodstableBean;
    }
    
    public void setGoodstableBean(JadeorGoodstableBean goodstableBean) {
        this.goodstableBean = goodstableBean;
    }
    
    static {
        CollectionJadeJpanel.showAll = true;
    }
}
