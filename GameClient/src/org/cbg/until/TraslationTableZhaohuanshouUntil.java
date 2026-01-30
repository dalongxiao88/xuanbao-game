package org.cbg.until;

import javax.swing.border.Border;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class TraslationTableZhaohuanshouUntil extends JPanel
{
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
}
