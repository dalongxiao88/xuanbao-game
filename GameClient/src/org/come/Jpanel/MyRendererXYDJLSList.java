package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import org.come.Frame.RankingListJframe;
import org.come.bean.Goodsrecord;
import org.come.bean.LoginResult;
import org.come.until.AnalysisString;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Map;

class MyRendererXYDJLSList extends JPanel implements ListCellRenderer {
    private int index;
    public static String vs;
    private Object value;
    private Map<String, Integer> map;
    private boolean isSelected;
    private static DefaultListModel<String> listModel;

    public MyRendererXYDJLSList() {
    }

    public MyRendererXYDJLSList(final int index, final Object value, final boolean isSelected) {
        this.index = index;
        this.isSelected = isSelected;
        this.value = value;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), 28);
    }

    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        final MyRendererXYDJLSList mr = new MyRendererXYDJLSList(index, value, isSelected);
        mr.removeAll();
        RankingListJpanel rankingListJpanel = RankingListJframe.getRankingListJframe().getRankingListJpanel();
        if (isSelected) {
            mr.setBackground(new Color(173, 173, 173, 150));
        } else if (rankingListJpanel.idx2 == index) {
            mr.setBackground(new Color(234, 217, 154, 150));
        } else {
            mr.setOpaque(false);
        }
//        System.out.println(index);
        mr.setLayout(new FlowLayout(0));

        final Box box = Box.createHorizontalBox();
        mr.add((Component) box);
        return (Component) mr;
    }

    private ImageIcon mountBox;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        Goodsrecord l = (Goodsrecord) value;
        g2d.setFont(UIUtils.TEXT_FONT);
        g2d.setColor(UIUtils.COLOR_CBG1);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int w = fontMetrics.stringWidth(l.getRolename());
        g2d.drawString(l.getRolename(), w / 2, 20);
        w = fontMetrics.stringWidth(l.getGoods());
        g2d.drawString(l.getGoods(), 133 - w / 2, 20);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(l.getRecordtime());
        w = fontMetrics.stringWidth(formattedDate);
        g2d.drawString(formattedDate, 255 - w / 2, 20);
    }

}
