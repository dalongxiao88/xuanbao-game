package org.come.summonequip;

import javax.swing.BorderFactory;
import org.cbg.until.TraslationDemoScrollBarUI;
import org.come.daily.JpanelDailytask;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JPanel;

public class JpanelHelpMain extends JPanel
{
    private JTree jTree;
    private DefaultMutableTreeNode top;
    private JScrollPane paneLeft;
    private JScrollPane paneRight;
    private ImageIcon iconBack;
    
    public JpanelHelpMain() {
        this.setPreferredSize(new Dimension(471, 377));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 93);
        offBtn.setBounds(453, 0, 25, 25);
        this.add(offBtn);
        this.getPaneLeft();
        this.getPaneRight();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("img/xy2uiimg/炼化规则说明_底板_w471,h337.png", 417, 336);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 471, 377, this);
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JTree getjTree() {
        if (this.jTree == null) {
            (this.jTree = new JTree(this.getTop())).setOpaque(false);
            this.jTree.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
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
            cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = JpanelHelpMain.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (JpanelHelpMain.this.jTree.isExpanded(path)) {
                                JpanelHelpMain.this.jTree.collapsePath(path);
                            }
                            else {
                                JpanelHelpMain.this.jTree.expandPath(path);
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
                        return;
                    }
                    String nodeName = selectionNode.toString();
                    if (!selectionNode.isLeaf() || selectionNode.getUserObject() instanceof JpanelDailytask) {}
                }
            });
        }
        return this.jTree;
    }
    
    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }
    
    public DefaultMutableTreeNode getTop() {
        if (this.top == null) {
            this.top = new DefaultMutableTreeNode("");
        }
        return this.top;
    }
    
    public void setTop(DefaultMutableTreeNode top) {
        this.top = top;
    }
    
    public JScrollPane getPaneLeft() {
        if (this.paneLeft == null) {
            (this.paneLeft = new JScrollPane(this.getjTree())).setVerticalScrollBarPolicy(22);
            this.paneLeft.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.paneLeft));
            this.paneLeft.getViewport().setOpaque(false);
            this.paneLeft.setOpaque(false);
            this.paneLeft.setBounds(27, 73, 135, 250);
            this.paneLeft.setBorder(BorderFactory.createEmptyBorder());
            this.paneLeft.setHorizontalScrollBarPolicy(31);
            this.add(this.paneLeft);
        }
        return this.paneLeft;
    }
    
    public void setPaneLeft(JScrollPane paneLeft) {
        this.paneLeft = paneLeft;
    }
    
    public JScrollPane getPaneRight() {
        if (this.paneRight == null) {
            (this.paneRight = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.paneRight.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.paneLeft));
            this.paneRight.getViewport().setOpaque(false);
            this.paneRight.setOpaque(false);
            this.paneRight.setBounds(166, 73, 275, 250);
            this.paneRight.setBorder(BorderFactory.createEmptyBorder());
            this.paneRight.setHorizontalScrollBarPolicy(31);
            this.add(this.paneRight);
        }
        return this.paneRight;
    }
    
    public void setPaneRight(JScrollPane paneRight) {
        this.paneRight = paneRight;
    }
}
