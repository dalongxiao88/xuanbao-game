package org.cbg.until;

import org.come.until.CutButtonImage;
import org.cbg.btn.TrslationBtn;
import java.math.BigDecimal;
import org.cbg.entity.Salegoods;
import java.util.List;
import javax.swing.border.Border;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JScrollPane;
import org.cbg.panel.TraslationWantBuyDahuabiModelJpanel;
import javax.swing.JPanel;

public class TraslationTableDahuabiUntil extends JPanel
{
    TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel;
    
    public static void TableModel(JScrollPane jScrollPane, int geshu) {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(null);
        jPanel.setOpaque(false);
        jPanel.setBorder(null);
        jPanel.setLayout(null);
        jScrollPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(jScrollPane));
        jScrollPane.setViewportView(jPanel);
        jScrollPane.setOpaque(false);
        jScrollPane.setBorder(null);
        jScrollPane.getViewport().setOpaque(false);
        Border js = BorderFactory.createEmptyBorder();
        jScrollPane.setBorder(js);
        jScrollPane.setVerticalScrollBarPolicy(20);
        jScrollPane.setHorizontalScrollBarPolicy(31);
        for (int i = 0; i < geshu; ++i) {
            TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel = new TraslationWantBuyDahuabiModelJpanel();
            traslationWantBuyDahuabiModelJpanel.setBounds(0, i * 50, 550, 50);
            traslationWantBuyDahuabiModelJpanel.setName("第" + i + "个面板");
            class MListener extends MouseAdapter
            {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JPanel btn = (JPanel)e.getSource();
                    StringBuilder sb = new StringBuilder();
                    sb.append("当前进入的按钮是：\n").append(btn.getName()).append("\n").append("正在进行演示自定义Tooltip！\n").append("请自行查看源码");
                    btn.setBackground(Color.gray);
                    btn.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    JPanel btn = (JPanel)e.getSource();
                    btn.setBackground(null);
                    btn.setOpaque(false);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            }
            MListener m = new MListener();
            traslationWantBuyDahuabiModelJpanel.addMouseListener(m);
            jPanel.add(traslationWantBuyDahuabiModelJpanel);
        }
        jPanel.setPreferredSize(new Dimension(565, 50 * geshu));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
    
    public static void setTableModel(JScrollPane jScrollPane, List<Salegoods> salegoodsList) {
        JPanel view = (JPanel)jScrollPane.getViewport().getView();
        view.removeAll();
        view.repaint();
        jScrollPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(jScrollPane));
        jScrollPane.setViewportView(view);
        jScrollPane.setOpaque(false);
        jScrollPane.setBorder(null);
        jScrollPane.getViewport().setOpaque(false);
        Border js = BorderFactory.createEmptyBorder();
        jScrollPane.setBorder(js);
        jScrollPane.setVerticalScrollBarPolicy(20);
        jScrollPane.setHorizontalScrollBarPolicy(31);
        for (int i = 0; i < salegoodsList.size(); ++i) {
            TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel = new TraslationWantBuyDahuabiModelJpanel((Salegoods)salegoodsList.get(i));
            traslationWantBuyDahuabiModelJpanel.setBounds(0, i * 50, 550, 50);
            traslationWantBuyDahuabiModelJpanel.setName("第" + i + "个新" + i + "面板");
            class MListener extends MouseAdapter
            {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JPanel btn = (JPanel)e.getSource();
                    StringBuilder sb = new StringBuilder();
                    sb.append("当前进入的按钮是：\n").append(btn.getName()).append("\n").append("正在进行演示自定义Tooltip！\n").append("请自行查看源码");
                    btn.setBackground(Color.gray);
                    btn.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    JPanel btn = (JPanel)e.getSource();
                    btn.setBackground(null);
                    btn.setOpaque(false);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            }
            MListener m = new MListener();
            traslationWantBuyDahuabiModelJpanel.addMouseListener(m);
            view.add(traslationWantBuyDahuabiModelJpanel);
        }
        view.setPreferredSize(new Dimension(565, 50 * salegoodsList.size()));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
    
    public static void setTableModel(JScrollPane jScrollPane, List<Salegoods> salegoodsList, List<BigDecimal> bigDecimalsList) {
        JPanel view = (JPanel)jScrollPane.getViewport().getView();
        view.removeAll();
        view.repaint();
        jScrollPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(jScrollPane));
        jScrollPane.setViewportView(view);
        jScrollPane.setOpaque(false);
        jScrollPane.setBorder(null);
        jScrollPane.getViewport().setOpaque(false);
        Border js = BorderFactory.createEmptyBorder();
        jScrollPane.setBorder(js);
        jScrollPane.setVerticalScrollBarPolicy(20);
        jScrollPane.setHorizontalScrollBarPolicy(31);
        for (int i = 0; i < salegoodsList.size(); ++i) {
            TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel = new TraslationWantBuyDahuabiModelJpanel((Salegoods)salegoodsList.get(i));
            traslationWantBuyDahuabiModelJpanel.setBounds(0, i * 50, 550, 50);
            traslationWantBuyDahuabiModelJpanel.setName("第" + i + "个新" + i + "面板");
            class MListener extends MouseAdapter
            {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JPanel btn = (JPanel)e.getSource();
                    btn.setBackground(Color.gray);
                    btn.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    JPanel btn = (JPanel)e.getSource();
                    btn.setBackground(null);
                    btn.setOpaque(false);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            }
            MListener m = new MListener();
            traslationWantBuyDahuabiModelJpanel.addMouseListener(m);
            view.add(traslationWantBuyDahuabiModelJpanel);
            if (bigDecimalsList.contains(((Salegoods)salegoodsList.get(i)).getSaleid())) {
                TrslationBtn component2 = (TrslationBtn)traslationWantBuyDahuabiModelJpanel.getComponent(2);
                try {
                    component2.setIcons(CutButtonImage.cuts("img/xy2uiimg/收藏w28px,h28px.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                traslationWantBuyDahuabiModelJpanel.setStateOrNo(0);
            }
        }
        view.setPreferredSize(new Dimension(565, 50 * salegoodsList.size()));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
}
