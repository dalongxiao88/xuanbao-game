package org.cbg.until;

import javax.swing.border.Border;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class TraslationTableRuleUntil extends JPanel
{
    public static void TableModel(JScrollPane jScrollPane, JTextArea geshu) {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(null);
        jPanel.setOpaque(false);
        jPanel.setBorder(null);
        jPanel.setLayout(null);
        jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        jScrollPane.setViewportView(jPanel);
        jScrollPane.setOpaque(false);
        jScrollPane.setBorder(null);
        jScrollPane.getViewport().setOpaque(false);
        Border js = BorderFactory.createEmptyBorder();
        jScrollPane.setBorder(js);
        jScrollPane.setVerticalScrollBarPolicy(20);
        jScrollPane.setHorizontalScrollBarPolicy(31);
        jPanel.add(geshu);
        jPanel.setPreferredSize(new Dimension(geshu.getWidth(), geshu.getHeight()));
        jScrollPane.updateUI();
        jScrollPane.invalidate();
        jScrollPane.validate();
        jScrollPane.repaint();
    }
}
