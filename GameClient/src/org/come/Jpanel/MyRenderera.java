package org.come.Jpanel;

import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
//import org.come.bean.AllAlchemyMaxBean;
import come.tool.Fighting.FightingMixDeal;
import org.come.until.UserMessUntil;
import org.come.until.Util;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Arrays;
import javax.swing.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.tool.role.RoleProperty.xianMap;

class MyRenderera extends JPanel implements ListCellRenderer {
    private int index;
    private Object value;
    public static Map<String, Double> cache;

    public MyRenderera() {
    }

    public MyRenderera(final int index, final Object value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), 20);
    }

    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        final MyRenderera mr = new MyRenderera(index, value);
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

    public static DecimalFormat df = new DecimalFormat("#0.0");

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        String raceSting = Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id());

//        AllAlchemyMaxBean allAlchemyMaxBean = UserMessUntil.getAllAlchemyMaxBean();
//        ConcurrentHashMap<String, Map<String, Double>> alchemyMax = allAlchemyMaxBean.getAllAlchemyMax();

        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        final Font font = new Font("宋体", 0, 13);
        final String dvd = this.value.toString();
        final String res = dvd.replaceAll("\\[|\\]", "");
        final String[] v = res.split("[,]");
        final String[] vv = Arrays.toString((Object[]) v).split("\\:");
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
        if (xianMap != null) {
            max = xianMap.get(name);
            if (max != null) {
                if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0 || FightingMixDeal.camp == -1) {
                    max = Double.valueOf(df.format(max));
                } else {
                    try{
                        if (resxs.matches("-?\\d+(\\.\\d+)?")) {
                            max = Double.valueOf(df.format(Double.valueOf(resxs)));
                        }else {
                            max = Double.valueOf(df.format(max));
                        }
                    } catch (NumberFormatException e) {
                        max = Double.valueOf(df.format(max));
                    }
                }



            }
        }
        if (max == null)
            max = -1d;
        double d = Double.parseDouble(resxs);
        MyRenderera.cache.put(name, Double.valueOf(d));
        d = ((Double) MyRenderera.cache.get(name)).doubleValue();
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
            g2d.drawString(d + "", 134 - i, 12);
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
