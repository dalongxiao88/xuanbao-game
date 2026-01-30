package com.tool.tab;

import java.awt.event.ActionEvent;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.AbstractButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class ButtonTabComponent extends JPanel
{
    public ButtonTabComponent(JTabbedPane pane, boolean close) {
    }
    
    private class TabButton extends JButton implements ActionListener
    {
        private final MouseListener mouseListener;
        
        public TabButton() {
            this.mouseListener = new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent event) {
                    Component c = event.getComponent();
                    if (c instanceof AbstractButton) {
                        ((AbstractButton)c).setBorderPainted(true);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent event) {
                    Component c = event.getComponent();
                    if (c instanceof AbstractButton) {
                        ((AbstractButton)c).setBorderPainted(false);
                    }
                }
            };
            int size = 17;
            this.setPreferredSize(new Dimension(17, 17));
            this.setToolTipText("关闭窗口");
            this.setUI(new BasicButtonUI());
            this.setContentAreaFilled(false);
            this.setFocusable(false);
            this.setBorder(BorderFactory.createEtchedBorder());
            this.setBorderPainted(false);
            this.addActionListener(this);
            this.addMouseListener(this.mouseListener);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
        }
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
        }
    }
}
