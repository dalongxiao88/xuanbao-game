package org.cbg.until;

import org.cbg.entity.Roleorder;
import java.util.List;
import javax.swing.border.Border;
import java.awt.Dimension;
import org.cbg.panel.TraslationMyMainMygoodsModelJpanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class TraslationTableMygoodsUntil extends JPanel
{
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
            TraslationMyMainMygoodsModelJpanel traslationMyMainMygoodsModelJpanel = new TraslationMyMainMygoodsModelJpanel();
            traslationMyMainMygoodsModelJpanel.setBounds(0, i * 50, 550, 50);
            class MListener implements MouseListener
            {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(null);
                    source.setOpaque(false);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(Color.gray);
                    source.setOpaque(true);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            }
            MListener mListener = new MListener();
            traslationMyMainMygoodsModelJpanel.addMouseListener(mListener);
            jPanel.add(traslationMyMainMygoodsModelJpanel);
        }
        jPanel.setPreferredSize(new Dimension(565, 50 * geshu));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
    
    public static void setTableModel(JScrollPane jScrollPane, List<Roleorder> salegoods) {
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
        for (int i = 0; i < salegoods.size(); ++i) {
            TraslationMyMainMygoodsModelJpanel traslationMyMainMygoodsModelJpanel = new TraslationMyMainMygoodsModelJpanel((Roleorder)salegoods.get(i));
            traslationMyMainMygoodsModelJpanel.setBounds(0, i * 50, 550, 50);
            class MListener implements MouseListener
            {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(null);
                    source.setOpaque(false);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(Color.gray);
                    source.setOpaque(true);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            }
            MListener mListener = new MListener();
            traslationMyMainMygoodsModelJpanel.addMouseListener(mListener);
            view.add(traslationMyMainMygoodsModelJpanel);
        }
        view.setPreferredSize(new Dimension(565, 50 * salegoods.size()));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
}
