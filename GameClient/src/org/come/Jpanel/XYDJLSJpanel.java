package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import org.come.Frame.ImpactGradeJframe;
import org.come.bean.Goodsrecord;
import org.come.until.SrcollPaneXYDJlUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class XYDJLSJpanel extends JPanel {


    private DefaultListModel<Goodsrecord> verVectors;// 设置所有排行榜信息的集合
    private JList<Goodsrecord> verStrings;// 放置排行榜信息的集合
    // 滚动条
    private JScrollPane jScrollPane2;

    public XYDJLSJpanel() throws Exception {


        this.setPreferredSize(new Dimension(339, 153));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        // 关闭按钮事件
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/xydj-close.png", 1, 30001);
        offBtn.setBounds(339-18, 1, 17, 17);
        this.add(offBtn);


        verVectors = new DefaultListModel<Goodsrecord>();


        verStrings = new JList<Goodsrecord>(verVectors) {
            {
                this.setOpaque(false);
            }
        };


        verStrings.setCellRenderer(new MyRendererXYDJLSList());
        verStrings.setSelectionMode(0);
//        verStrings.addMouseListener(new PhListMouslisten(verStrings));
//        verStrings.addMouseMotionListener(new PhListMouslisten(verStrings));

        // 召唤兽名字列表滚动条
//            this.jScrollPane2 = new JScrollPane(this.tableMsg);
        this.jScrollPane2 = new JScrollPane(verStrings);
        jScrollPane2.getVerticalScrollBar().setUI(new SrcollPaneXYDJlUI());
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setOpaque(false);
        jScrollPane2.setBounds(2, 20, 325, 124);
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.NEVER);
        this.add(jScrollPane2);
    }

    public void showInfo(List<Goodsrecord> list){
        verVectors.clear();
        for (Goodsrecord goodsrecord : list) {
            verVectors.addElement(goodsrecord);
        }
    }

    ImageIcon icon;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        jScrollPane2.setBounds(2, 20, 344, 124);
        // 组队n
//        if (icon == null)
        icon = new ImageIcon("inkImg/background/xldjls_back.png");
        g.drawImage(icon.getImage(), 0, 0, 339, 153, this);
    }

    public DefaultListModel<Goodsrecord> getVerVectors() {
        return verVectors;
    }

    public void setVerVectors(DefaultListModel<Goodsrecord> verVectors) {
        this.verVectors = verVectors;
    }

    public JList<Goodsrecord> getVerStrings() {
        return verStrings;
    }

    public void setVerStrings(JList<Goodsrecord> verStrings) {
        this.verStrings = verStrings;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }
}
