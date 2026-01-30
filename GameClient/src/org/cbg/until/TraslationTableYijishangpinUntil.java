package org.cbg.until;

import org.cbg.panel.TraslationWantSentYijishangpinModelJpanel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.cbg.entity.Salegoods;
import java.util.List;
import javax.swing.border.Border;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import org.cbg.panel.TraslationWantBuyDahuabiModelJpanel;
import javax.swing.JPanel;

public class TraslationTableYijishangpinUntil extends JPanel
{
    TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel;
    
    public static JPanel TableModel(JScrollPane jScrollPane) {
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
        jPanel.setPreferredSize(new Dimension(565, 0));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
        return jPanel;
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
            TraslationWantSentYijishangpinModelJpanel traslationWantSentYijishangpinModelJpanel = new TraslationWantSentYijishangpinModelJpanel((Salegoods)salegoodsList.get(i));
            traslationWantSentYijishangpinModelJpanel.setBounds(0, i * 50, 550, 50);
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
            traslationWantSentYijishangpinModelJpanel.addMouseListener(m);
            view.add(traslationWantSentYijishangpinModelJpanel);
        }
        view.setPreferredSize(new Dimension(565, 50 * salegoodsList.size()));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
}
