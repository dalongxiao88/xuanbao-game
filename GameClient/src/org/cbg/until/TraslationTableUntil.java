package org.cbg.until;

import org.come.until.CutButtonImage;
import org.cbg.btn.TrslationBtn;
import java.math.BigDecimal;
import org.cbg.entity.Collection;
import org.cbg.entity.Salegoods;
import java.util.List;
import javax.swing.border.Border;
import java.awt.Dimension;
import org.cbg.panel.TraslationWantBuyDaojuModelJpanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JScrollPane;
import org.cbg.panel.TraslationWantBuyDahuabiModelJpanel;
import javax.swing.JPanel;

public class TraslationTableUntil extends JPanel
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
            TraslationWantBuyDaojuModelJpanel traslationWantBuyDahuabiModelJpanel = new TraslationWantBuyDaojuModelJpanel();
            traslationWantBuyDahuabiModelJpanel.setBounds(0, i * 50, 550, 50);
            traslationWantBuyDahuabiModelJpanel.setName("第" + i + "个面板");
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
            TraslationWantBuyDaojuModelJpanel traslationWantBuyDaojuModelJpanel = new TraslationWantBuyDaojuModelJpanel((Salegoods)salegoodsList.get(i));
            traslationWantBuyDaojuModelJpanel.setBounds(0, i * 50, 550, 50);
            traslationWantBuyDaojuModelJpanel.setName("第" + i + "个新" + i + "面板");
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
            traslationWantBuyDaojuModelJpanel.addMouseListener(m);
            view.add(traslationWantBuyDaojuModelJpanel);
        }
        view.setPreferredSize(new Dimension(565, 50 * salegoodsList.size()));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
    
    public static void setShoucangTableModel(JScrollPane jScrollPane, List<Collection> collections) {
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
        for (int i = 0; i < collections.size(); ++i) {
            if (((Collection)collections.get(i)).getSalename().equals("大话币")) {
                TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel = new TraslationWantBuyDahuabiModelJpanel((Collection)collections.get(i));
                traslationWantBuyDahuabiModelJpanel.setBounds(0, i * 50, 550, 50);
                traslationWantBuyDahuabiModelJpanel.setName("第" + i + "个新" + i + "面板");
                MListener m = new MListener();
                traslationWantBuyDahuabiModelJpanel.addMouseListener(m);
                view.add(traslationWantBuyDahuabiModelJpanel);
            }
            else {
                TraslationWantBuyDaojuModelJpanel traslationWantBuyDaojuModelJpanel = new TraslationWantBuyDaojuModelJpanel((Collection)collections.get(i));
                traslationWantBuyDaojuModelJpanel.setBounds(0, i * 50, 550, 50);
                traslationWantBuyDaojuModelJpanel.setName("第" + i + "个新" + i + "面板");
                MListener m = new MListener();
                traslationWantBuyDaojuModelJpanel.addMouseListener(m);
                view.add(traslationWantBuyDaojuModelJpanel);
            }
        }
        view.setPreferredSize(new Dimension(565, 50 * collections.size()));
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
        for (int i = 0; i < salegoodsList.size(); ++i) {
            if (((Salegoods)salegoodsList.get(i)).getSalename().equals("大话币")) {
                TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel = new TraslationWantBuyDahuabiModelJpanel((Salegoods)salegoodsList.get(i), i);
                traslationWantBuyDahuabiModelJpanel.setBounds(0, i * 50, 550, 50);
                traslationWantBuyDahuabiModelJpanel.setName("第" + i + "个新" + i + "面板");
            
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
            else {
                TraslationWantBuyDaojuModelJpanel traslationWantBuyDaojuModelJpanel = new TraslationWantBuyDaojuModelJpanel((Salegoods)salegoodsList.get(i));
                traslationWantBuyDaojuModelJpanel.setBounds(0, i * 50, 550, 50);
                traslationWantBuyDaojuModelJpanel.setName("第" + i + "个新" + i + "面板");
                MListener m = new MListener();
                traslationWantBuyDaojuModelJpanel.addMouseListener(m);
                view.add(traslationWantBuyDaojuModelJpanel);
                if (bigDecimalsList != null && bigDecimalsList.contains(((Salegoods)salegoodsList.get(i)).getSaleid())) {
                    TrslationBtn component2 = (TrslationBtn)traslationWantBuyDaojuModelJpanel.getComponent(2);
                    try {
                        component2.setIcons(CutButtonImage.cuts("img/xy2uiimg/收藏w28px,h28px.png"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    traslationWantBuyDaojuModelJpanel.setStateOrNo(0);
                }
            }
        }
        view.setPreferredSize(new Dimension(565, 50 * salegoodsList.size()));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
}
