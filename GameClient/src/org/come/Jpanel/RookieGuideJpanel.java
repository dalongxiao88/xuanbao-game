package org.come.Jpanel;

import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import com.tool.tcpimg.RichLabel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.CutButtonImage;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import org.come.until.RookieGuideMatterUntil;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class RookieGuideJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane2;
    private JTree jTree;
    private DefaultMutableTreeNode top;
    private ImageIcon icon;
    
    public RookieGuideJpanel() {
        this.icon = new ImageIcon("img/xy2uiimg/rookieguide.png");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(579, 409));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 68);
            offBtn.setBounds(542, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            this.setPreferredSize(new Dimension(555, 407));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 68);
            offBtn.setBounds(529, 0, 23, 23);
            this.add(offBtn);
        }
        RookieGuideMatterUntil.showGuideMethod(this.top = new DefaultMutableTreeNode(""));
        (this.jTree = new JTree(this.top)).setOpaque(false);
        this.jTree.putClientProperty("JTree.lineStyle", "None");
        ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
        DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
            Color color = new Color(237, 234, 0);
            Font font = new Font("微软雅黑", 1, 15);
            
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                if (node.getLevel() == 1) {
                    this.setForeground(this.color);
                    this.setFont(this.font);
                }
                if (node.getLevel() == 2) {
                    this.setFont(UIUtils.TEXT_FONT1);
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
        cellRenderer.setBackgroundNonSelectionColor(UIUtils.Color_BACK);
        cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
        cellRenderer.setBorderSelectionColor(UIUtils.Color_BACK);
        cellRenderer.setTextNonSelectionColor(Color.white);
        cellRenderer.setTextSelectionColor(Color.white);
        this.jTree.setCellRenderer(cellRenderer);
        this.jTree.setRootVisible(false);
        this.jTree.setRowHeight(25);
        this.jTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    TreePath path = RookieGuideJpanel.this.jTree.getSelectionPath();
                    if (path != null) {
                        if (RookieGuideJpanel.this.jTree.isExpanded(path)) {
                            RookieGuideJpanel.this.jTree.collapsePath(path);
                        }
                        else {
                            RookieGuideJpanel.this.jTree.expandPath(path);
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
                    RookieGuideJpanel.this.jScrollPane2.setViewportView(null);
                    return;
                }
                String nodeName = selectionNode.toString();
                if (nodeName.equals("游戏介绍") || nodeName.equals("充值介绍") || nodeName.equals("版本特色") || nodeName.equals("功能介绍")) {
                    RookieGuideJpanel.this.jScrollPane2.setViewportView(null);
                    if (RookieGuideMatterUntil.returnContent(nodeName) == null) {
                        return;
                    }
                    RichLabel label = new RichLabel(RookieGuideMatterUntil.returnContent(nodeName), UIUtils.TEXT_FONT);
                    Dimension d = label.computeSize(250);
                    label.setSize(d);
                    label.setPreferredSize(d);
                    RookieGuideJpanel.this.jScrollPane2.setViewportView(label);
                    return;
                }
                else {
                    if (selectionNode.isLeaf()) {
                        RookieGuideJpanel.this.jScrollPane2.setViewportView(null);
                        if (RookieGuideMatterUntil.returnChildMsg(nodeName) == null) {
                            return;
                        }
                        RichLabel label = new RichLabel(RookieGuideMatterUntil.returnChildMsg(nodeName), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(250);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        RookieGuideJpanel.this.jScrollPane2.setViewportView(label);
                    }
                    else {
                        RookieGuideJpanel.this.jScrollPane2.setViewportView(null);
                        if (RookieGuideMatterUntil.returnContent(nodeName) == null) {
                            return;
                        }
                        RichLabel label = new RichLabel(RookieGuideMatterUntil.returnContent(nodeName), UIUtils.TEXT_FONT);
                        Dimension d = label.computeSize(250);
                        label.setSize(d);
                        label.setPreferredSize(d);
                        RookieGuideJpanel.this.jScrollPane2.setViewportView(label);
                    }
                    return;
                }
            }
        });
        (this.jScrollPane = new JScrollPane(this.jTree)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        if (MyIsif.getStyle().equals("水墨")) {
            this.jScrollPane.setBounds(48, 79, 217, 290);
        }
        else {
            this.jScrollPane.setBounds(26, 60, 209, 315);
        }
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane);
        (this.jScrollPane2 = new JScrollPane()).setVerticalScrollBarPolicy(22);
        this.jScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(50);
        this.jScrollPane2.getViewport().setOpaque(false);
        this.jScrollPane2.setOpaque(false);
        if (MyIsif.getStyle().equals("水墨")) {
            this.jScrollPane2.setBounds(272, 79, 288, 290);
        }
        else {
            this.jScrollPane2.setBounds(255, 60, 275, 315);
        }
        this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane2.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B231.png");
            g.drawImage(this.icon.getImage(), 0, 0, 579, 409, this);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/rookieguide.png");
            g.drawImage(this.icon.getImage(), 0, 0, 555, 407, this);
        }
    }
}
