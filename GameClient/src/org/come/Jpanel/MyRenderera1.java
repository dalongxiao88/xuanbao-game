package org.come.Jpanel;

import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import org.come.until.UserMessUntil;
import org.come.until.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class MyRenderera1 extends JPanel implements ListCellRenderer {
    private int index;
    private Object value;
    public static Map<String, Double> cache;

    public MyRenderera1() {
    }

    public MyRenderera1(final int index, final Object value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), 20);
    }

    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        final MyRenderera1 mr = new MyRenderera1(index, value);
        mr.removeAll();
        if (FaShuKangXingJpanel.idx == index || isSelected) {
            mr.setBackground(UIUtils.Color_BACK);
        } else {
            mr.setOpaque(false);
        }
        mr.setLayout(new FlowLayout(0, 0, 0));
        final Box box = Box.createHorizontalBox();
        return (Component) mr;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        String raceSting = "召唤兽";
//        AllAlchemyMaxBean allAlchemyMaxBean = UserMessUntil.getAllAlchemyMaxBean();
//        ConcurrentHashMap<String, Map<String, Double>> alchemyMax = allAlchemyMaxBean.getAllAlchemyMax();

        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        final Font font = new Font("宋体", 0, 13);
        final String dvd = this.value.toString();
        final String res = dvd.replaceAll("\\[|\\]", "");
        final String[] v = res.split("[,]");
        final String[] vv = Arrays.toString((Object[]) v).split("\\:");
        if (vv.length>1) {
            final String resx = vv[1].replaceAll("\\[|\\]", "");
            final String resxs = resx.replaceAll("\\%", "");
            final String name = vv[0].replaceAll("\\[|\\]", "");
            Double max = -1d;
//        if (alchemyMax != null) {
//            Map<String, Double> stringDoubleMap = alchemyMax.get(raceSting);
//            if (stringDoubleMap != null) {
//                max = stringDoubleMap.get(name);
//            }
//        }
            double d = Double.parseDouble(resxs);
            MyRenderera1.cache.put(name, Double.valueOf(d));
            d = ((Double) MyRenderera1.cache.get(name)).doubleValue();
//        if (v[0].startsWith("抗混乱") && d >= 550.0 || v[0].startsWith("抗风") && d >= 95.0 || v[0].startsWith("抗火") && d >= 95.0 || v[0].startsWith("抗雷") && d >= 95.0 || v[0].startsWith("抗水") && d >= 95.0 || v[0].startsWith("抗鬼火") && d >= 100.0
//                || v[0].startsWith("抗封印") && d >= 550.0 || v[0].startsWith("抗昏睡") && d >= 550.0 || v[0].startsWith("抗遗忘") && d >= 550.0 || v[0].startsWith("强力克") && d >= 1000.0 || v[0].startsWith("伤害加深") && d >= 95.0 || v[0].startsWith("伤害减免") && d >= 95.0
//                || v[0].startsWith("抗物理") && d >= 95.0 || v[0].startsWith("躲闪") && d >= 95.0 || v[0].startsWith("风法狂暴几率") && d >= 100.0 || v[0].startsWith("火法狂暴几率") && d >= 100.0 || v[0].startsWith("雷法狂暴几率") && d >= 100.0 || v[0].startsWith("水法狂暴几率") && d >= 100.0
//                || v[0].startsWith("鬼火狂暴几率") && d >= 100.0 || v[0].startsWith("鬼火狂暴程度") && d >= 1000.0 || v[0].startsWith("风法狂暴程度") && d >= 1000.0 || v[0].startsWith("火法狂暴程度") && d >= 1000.0
//                || v[0].startsWith("雷法狂暴程度") && d >= 1000.0 || v[0].startsWith("水法狂暴程度") && d >= 1000.0) {
            if (max == -1 || d <= max) {
//            if (v[0].startsWith("抗三尸")) {
                g2d.setFont(font);
                FontMetrics fm = g.getFontMetrics();
                int i = fm.stringWidth(d + "");
                g2d.setColor(Color.white);
                g2d.drawString(d + "", 114 - i, 12);
                g2d.setColor(Color.white);
                g2d.drawString(name, 0, 12);
            } else {
                g2d.setFont(font);
                FontMetrics fm = g.getFontMetrics();
                int i = fm.stringWidth(max + "");
                g2d.setColor(Color.red);
                g2d.drawString(max + "", 134 - i, 12);
                g2d.setColor(Color.white);
                g2d.drawString(name, 0, 12);
            }
        }else {
            g2d.setFont(font);
            g2d.setColor(Color.white);
            g2d.drawString(vv[0], 0, 12);
        }

//        } else {
//            if (v[0].startsWith("抗浩然正气") || v[0].startsWith("风法伤害") || v[0].startsWith("火法伤害") || v[0].startsWith("雷法伤害") || v[0].startsWith("电法伤害") || v[0].startsWith("水法伤害") || v[0].startsWith("强力") || v[0].startsWith("连击次数")) {
//                g2d.setFont(font);
//                FontMetrics fm = g.getFontMetrics();
//                int i = fm.stringWidth(d + "");
//                g2d.setColor(Color.white);
//                g2d.drawString(d + " ", 134 - i, 12);
//                g2d.setColor(Color.white);
//                g2d.drawString(name, 0, 12);
//            } else {
//                if (v[0].startsWith("亲密") || v[0].startsWith("气血") || v[0].startsWith("法力") || v[0].startsWith("攻击") || v[0].startsWith("速度")) {
//                    g2d.setFont(font);
//                    FontMetrics fm = g.getFontMetrics();
//                    int i = fm.stringWidth(d + "");
//                    g2d.setColor(Color.white);
//                    g2d.drawString(name, 0, 12);
//                    g2d.drawString(d + "", 134 - i, 12);
//                } else {
//                    g2d.setFont(font);
//                    FontMetrics fm = g.getFontMetrics();
//                    int i = fm.stringWidth(d + "");
//                    g2d.setColor(Color.white);
//                    g2d.drawString(name, 0, 12);
//                    g2d.drawString(d + "", 134 - i, 12);
//                }
//            }
//        }
    }

    static {
        cache = new HashMap();
    }
}
