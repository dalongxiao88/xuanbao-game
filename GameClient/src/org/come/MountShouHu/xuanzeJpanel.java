package org.come.MountShouHu;

import org.come.entity.Mount;
import java.util.List;
import java.awt.Graphics;
import org.come.until.SrcollPanelUI;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.ListModel;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.tool.btn.MountShouhuBtn;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class xuanzeJpanel extends JPanel
{
    private ImageIcon xuanze;
    public MountShouhuBtn queren;
    private static JList<String> listmount;
    private static DefaultListModel<String> listModel;
    private JScrollPane jScrollPane;
    public static int index;
    public static int idx;
    
    public xuanzeJpanel() {
        this.xuanze = new ImageIcon("inkImg/Client/选择坐骑界面.png");
        this.setPreferredSize(new Dimension(281, 342));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        (this.queren = new MountShouhuBtn("inkImg/Client/60X26按钮.png", 1, 5, "确认", UIUtils.Black, UIUtils.TEXT_HY16, this)).setBounds(123, 298, 60, 26);
        this.add(this.queren);
        xuanzeJpanel.listModel = new DefaultListModel<>();
        (xuanzeJpanel.listmount = new JList<String>(xuanzeJpanel.listModel) {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        xuanzeJpanel.listmount.setSelectionMode(0);
        xuanzeJpanel.listmount.setBackground(UIUtils.Color_BACK);
        xuanzeJpanel.listmount.setCellRenderer(new MyMountRenderer());
        xuanzeJpanel.listmount.addMouseListener(new MountlistChoseMouslisten(xuanzeJpanel.listmount, this));
        xuanzeJpanel.listmount.addMouseMotionListener(new MountlistChoseMouslisten(xuanzeJpanel.listmount, this));
        (this.jScrollPane = new JScrollPane(xuanzeJpanel.listmount)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane.setBounds(10, 10, 250, 300);
        this.add(this.jScrollPane);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.xuanze != null) {
            g.drawImage(this.xuanze.getImage(), 0, 0, 281, 342, null);
        }
        this.jScrollPane.setBounds(54, 60, 200, 230);
    }
    
    public void shoumount(List<Mount> mountList) {
        xuanzeJpanel.listModel.removeAllElements();
        for (int i = 0; i <= mountList.size() - 1; ++i) {
            xuanzeJpanel.listModel.addElement(((Mount)mountList.get(i)).getMountname());
        }
    }
    
    static {
        xuanzeJpanel.idx = -1;
    }
}
