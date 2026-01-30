package org.cbg.until;

import org.cbg.entity.Message;
import java.util.List;
import javax.swing.border.Border;
import java.awt.Dimension;
import org.cbg.panel.TraslationMyMessageModelJpanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class TraslationTableMyMessageUntil extends JPanel
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
            TraslationMyMessageModelJpanel traslationMyMessageModelJpanel = new TraslationMyMessageModelJpanel();
            traslationMyMessageModelJpanel.setBounds(0, i * 50, 550, 50);
            class MListener extends MouseAdapter
            {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(Color.gray);
                    source.setOpaque(true);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(Color.gray);
                    source.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(null);
                    source.setOpaque(false);
                }
            }
            MListener m = new MListener();
            traslationMyMessageModelJpanel.addMouseListener(m);
            jPanel.add(traslationMyMessageModelJpanel);
        }
        jPanel.setPreferredSize(new Dimension(565, 50 * geshu));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
    
    public static void TableModel(JScrollPane jScrollPane, List<Message> messageList) {
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
        for (int i = 0; i < messageList.size(); ++i) {
            TraslationMyMessageModelJpanel traslationMyMessageModelJpanel = new TraslationMyMessageModelJpanel((Message)messageList.get(i));
            traslationMyMessageModelJpanel.setBounds(0, i * 50, 550, 50);
            class MListener extends MouseAdapter
            {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(Color.gray);
                    source.setOpaque(true);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(Color.gray);
                    source.setOpaque(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    JPanel source = (JPanel)e.getSource();
                    source.setBackground(null);
                    source.setOpaque(false);
                }
            }
            MListener m = new MListener();
            traslationMyMessageModelJpanel.addMouseListener(m);
            view.add(traslationMyMessageModelJpanel);
        }
        view.setPreferredSize(new Dimension(565, 50 * messageList.size()));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
}
