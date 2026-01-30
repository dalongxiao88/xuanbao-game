package org.come.Jpanel;

import java.awt.image.ColorConvertOp;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import org.come.Frame.FriendMsgJframe;
import java.math.BigDecimal;
import org.come.until.FormsManagement;
import org.come.until.MessagrFlagUntil;
import org.come.Frame.FriendChatMessageJframe;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.entity.Friendtable;
import org.come.until.UserMessUntil;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.basic.BasicTreeUI;
import org.come.until.JTreeData;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import com.tool.btn.SmallIconBtn;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class TestfriendlistJapnel extends JPanel
{
    private JScrollPane jScrollPane;
    private SmallIconBtn btnAdd;
    private SmallIconBtn btnCha;
    private JTree jTree;
    private DefaultMutableTreeNode top;
    private ImageIcon icon;
    
    public TestfriendlistJapnel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(158, 498));
            this.setBackground(Color.BLACK);
            this.setLayout(null);
            JTreeData.ShowFriendMsg(this.top = new DefaultMutableTreeNode(""), this.jTree);
            (this.jTree = new JTree(this.top)).setOpaque(false);
            this.jTree.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                    if (node.getLevel() == 2) {
                        JTreeData jtd = (JTreeData)node.getUserObject();
                        this.setIcon(CutButtonImage.getImage(jtd.getPath(), 20, 20));
                        this.setText(jtd.getString());
                        this.setFont(UIUtils.TEXT_FONT);
                        this.setForeground(Color.green);
                        if (UserMessUntil.getFriendtables() != null && UserMessUntil.getFriendtables().size() > 0) {
                            for (int i = 0; i < UserMessUntil.getFriendtables().size(); ++i) {
                                Friendtable friend = (Friendtable)UserMessUntil.getFriendtables().get(i);
                                if (friend.getRolename().equals(jtd.getString())) {
                                    if (friend.getOnlineState() == 1) {
                                        this.setForeground(Color.green);
                                    }
                                    else {
                                        this.setForeground(Color.gray);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (node.getUserObject().equals("大话精灵")) {
                            this.setForeground(Color.GREEN);
                            this.setIcon(CutButtonImage.getImage("img/head/999999.png", 25, 25));
                        }
                        else {
                            this.setForeground(Color.WHITE);
                        }
                        this.setFont(UIUtils.TEXT_HURT2);
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null);
            cellRenderer.setOpenIcon(new ImageIcon("img/xy2uiimg/expand.png"));
            cellRenderer.setClosedIcon(new ImageIcon("img/xy2uiimg/not_expanded.png"));
            cellRenderer.setFont(UIUtils.TEXT_HURT2);
            cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.setRowHeight(30);
            this.jTree.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)TestfriendlistJapnel.this.jTree.getLastSelectedPathComponent();
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = TestfriendlistJapnel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (TestfriendlistJapnel.this.jTree.isExpanded(path)) {
                                TestfriendlistJapnel.this.jTree.collapsePath(path);
                            }
                            else {
                                TestfriendlistJapnel.this.jTree.expandPath(path);
                            }
                        }
                        if (e.getClickCount() == 2) {
                            if (selectionNode.getLevel() == 2) {
                                JTreeData jtd = (JTreeData)selectionNode.getUserObject();
                                int i = 0;
                                while (i < UserMessUntil.getFriendtables().size()) {
                                    Friendtable friend = (Friendtable)UserMessUntil.getFriendtables().get(i);
                                    if (friend.getRolename().equals(jtd.getString())) {
                                        FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend, MessagrFlagUntil.getRichLabel(jtd.getString()));
                                        FormsManagement.showForm(56);
                                        break;
                                    }
                                    else {
                                        ++i;
                                    }
                                }
                            }
                            else {
                                String nodeName = selectionNode.toString();
                                JTreeData jtd2 = new JTreeData(nodeName, "img/head/999999.png");
                                Friendtable friend = new Friendtable();
                                friend.setRole_id(new BigDecimal(99999));
                                friend.setRolename(nodeName);
                                friend.setOnlineState(1);
                                friend.setGrade(new BigDecimal(1));
                                friend.setSpecies_id(new BigDecimal(999999));
                                if (nodeName.equals("大话精灵")) {
                                    FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend, MessagrFlagUntil.getRichLabel(jtd2.getString()));
                                    FormsManagement.showForm(56);
                                }
                            }
                        }
                    }
                    else if (SwingUtilities.isRightMouseButton(e) && selectionNode.getLevel() == 2) {
                        JTreeData jtd3 = (JTreeData)selectionNode.getUserObject();
                        if (UserMessUntil.getFriendtables() == null) {
                            return;
                        }
                        int j = 0;
                        while (j < UserMessUntil.getFriendtables().size()) {
                            Friendtable friend2 = (Friendtable)UserMessUntil.getFriendtables().get(j);
                            if (friend2.getRolename().equals(jtd3.getString())) {
                                FriendMsgJframe.getFriendMsgJframe().getMsgJpanel().show(friend2);
                                FormsManagement.showForm(76);
                                break;
                            }
                            else {
                                ++j;
                            }
                        }
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
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
            this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    JTree tree = (JTree)e.getSource();
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                }
            });
            this.jScrollPane = new JScrollPane(22, 31);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setViewportView(this.jTree);
            this.jScrollPane.setBounds(5, 74, 155, 395);
            this.add(this.jScrollPane);
            (this.btnAdd = new SmallIconBtn("inkImg/button/B35.png", 1, 100, "添加", null)).setBounds(5, 475, 19, 20);
            this.add(this.btnAdd);
            (this.btnCha = new SmallIconBtn("inkImg/button/B40.png", 1, 101, "查找", null)).setBounds(25, 475, 19, 20);
            this.add(this.btnCha);
        }
        else {
            this.setPreferredSize(new Dimension(158, 498));
            this.setBackground(Color.BLACK);
            this.setLayout(null);
            JTreeData.ShowFriendMsg(this.top = new DefaultMutableTreeNode(""), this.jTree);
            (this.jTree = new JTree(this.top)).setOpaque(false);
            this.jTree.putClientProperty("JTree.lineStyle", "None");
            ((BasicTreeUI)this.jTree.getUI()).setLeftChildIndent(0);
            DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
                @Override
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
                    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                    if (node.getLevel() == 2) {
                        JTreeData jtd = (JTreeData)node.getUserObject();
                        this.setIcon(CutButtonImage.getImage(jtd.getPath(), 20, 20));
                        this.setText(jtd.getString());
                        this.setFont(UIUtils.TEXT_FONT);
                        this.setForeground(Color.green);
                        if (UserMessUntil.getFriendtables() != null && UserMessUntil.getFriendtables().size() > 0) {
                            for (int i = 0; i < UserMessUntil.getFriendtables().size(); ++i) {
                                Friendtable friend = (Friendtable)UserMessUntil.getFriendtables().get(i);
                                if (friend.getRolename().equals(jtd.getString())) {
                                    if (friend.getOnlineState() == 1) {
                                        this.setForeground(Color.green);
                                    }
                                    else {
                                        this.setForeground(Color.gray);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (node.getUserObject().equals("大话精灵")) {
                            this.setForeground(Color.GREEN);
                            this.setIcon(CutButtonImage.getImage("img/head/999999.png", 25, 25));
                        }
                        else {
                            this.setForeground(Color.WHITE);
                        }
                        this.setFont(UIUtils.TEXT_HURT2);
                    }
                    return this;
                }
            };
            cellRenderer.setLeafIcon(null);
            cellRenderer.setOpenIcon(new ImageIcon("img/xy2uiimg/expand.png"));
            cellRenderer.setClosedIcon(new ImageIcon("img/xy2uiimg/not_expanded.png"));
            cellRenderer.setFont(UIUtils.TEXT_HURT2);
            cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setBackgroundSelectionColor(new Color(99, 93, 90));
            cellRenderer.setBorderSelectionColor(new Color(0, 0, 0, 0));
            cellRenderer.setTextNonSelectionColor(Color.white);
            cellRenderer.setTextSelectionColor(Color.white);
            this.jTree.setCellRenderer(cellRenderer);
            this.jTree.setRootVisible(false);
            this.jTree.setRowHeight(30);
            this.jTree.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)TestfriendlistJapnel.this.jTree.getLastSelectedPathComponent();
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        TreePath path = TestfriendlistJapnel.this.jTree.getSelectionPath();
                        if (path != null) {
                            if (TestfriendlistJapnel.this.jTree.isExpanded(path)) {
                                TestfriendlistJapnel.this.jTree.collapsePath(path);
                            }
                            else {
                                TestfriendlistJapnel.this.jTree.expandPath(path);
                            }
                        }
                        if (e.getClickCount() == 2) {
                            if (selectionNode.getLevel() == 2) {
                                JTreeData jtd = (JTreeData)selectionNode.getUserObject();
                                int i = 0;
                                while (i < UserMessUntil.getFriendtables().size()) {
                                    Friendtable friend = (Friendtable)UserMessUntil.getFriendtables().get(i);
                                    if (friend.getRolename().equals(jtd.getString())) {
                                        FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend, MessagrFlagUntil.getRichLabel(jtd.getString()));
                                        FormsManagement.showForm(56);
                                        break;
                                    }
                                    else {
                                        ++i;
                                    }
                                }
                            }
                            else {
                                String nodeName = selectionNode.toString();
                                JTreeData jtd2 = new JTreeData(nodeName, "img/head/999999.png");
                                Friendtable friend = new Friendtable();
                                friend.setRole_id(new BigDecimal(99999));
                                friend.setRolename(nodeName);
                                friend.setOnlineState(1);
                                friend.setGrade(new BigDecimal(1));
                                friend.setSpecies_id(new BigDecimal(999999));
                                if (nodeName.equals("大话精灵")) {
                                    FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend, MessagrFlagUntil.getRichLabel(jtd2.getString()));
                                    FormsManagement.showForm(56);
                                }
                            }
                        }
                    }
                    else if (SwingUtilities.isRightMouseButton(e) && selectionNode.getLevel() == 2) {
                        JTreeData jtd3 = (JTreeData)selectionNode.getUserObject();
                        if (UserMessUntil.getFriendtables() == null) {
                            return;
                        }
                        int j = 0;
                        while (j < UserMessUntil.getFriendtables().size()) {
                            Friendtable friend2 = (Friendtable)UserMessUntil.getFriendtables().get(j);
                            if (friend2.getRolename().equals(jtd3.getString())) {
                                FriendMsgJframe.getFriendMsgJframe().getMsgJpanel().show(friend2);
                                FormsManagement.showForm(76);
                                break;
                            }
                            else {
                                ++j;
                            }
                        }
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
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
            this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    JTree tree = (JTree)e.getSource();
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                }
            });
            this.jScrollPane = new JScrollPane(22, 31);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setViewportView(this.jTree);
            this.jScrollPane.setBounds(5, 74, 155, 395);
            this.add(this.jScrollPane);
            (this.btnAdd = new SmallIconBtn("img/xy2uiimg/55_png.button.btn_12.png", 1, 100, "添加", (ZhuJpanel)null)).setBounds(5, 473, 19, 20);
            this.add(this.btnAdd);
            (this.btnCha = new SmallIconBtn("img/xy2uiimg/icon_search.png", 1, 101, "查找", (ZhuJpanel)null)).setBounds(30, 474, 19, 20);
            this.add(this.btnCha);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B215.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 158, 498, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu1/B215h.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 158, 498, this);
        }
    }
    
    public static BufferedImage getGrayImage(BufferedImage originalImage) {
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();
        BufferedImage grayImage = new BufferedImage(imageWidth, imageHeight, 5);
        ColorConvertOp cco = new ColorConvertOp(ColorSpace.getInstance(1003), null);
        cco.filter(originalImage, grayImage);
        return grayImage;
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public DefaultMutableTreeNode getTop() {
        return this.top;
    }
    
    public void setTop(DefaultMutableTreeNode top) {
        this.top = top;
    }
    
    public JTree getjTree() {
        return this.jTree;
    }
    
    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }
}
