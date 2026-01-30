package org.come.Frame;

import org.come.until.FormsManagement;
import org.come.until.Music;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class Insideforms extends JInternalFrame implements MouseListener
{
    private int formsid;
    Insideforms insideforms;
    private JPanel jPanel;
    int movex;
    int movey;
    
    public Insideforms(int formsid, JPanel jPanel, int x, int y) throws Exception {
        this.insideforms = this;
        this.formsid = formsid;
        this.jPanel = jPanel;
        this.setBackground(new Color(0, 0, 0, 0));
        this.add(jPanel);
        this.setBounds(x, y, jPanel.getWidth(), jPanel.getHeight());
        this.pack();
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (Insideforms.this.insideforms.isVisible()) {
                    int x = e.getX() - Insideforms.this.movex;
                    int y = e.getY() - Insideforms.this.movey;
                    Insideforms.this.insideforms.setBounds(x + Insideforms.this.insideforms.getX(), y + Insideforms.this.insideforms.getY(), Insideforms.this.insideforms.getWidth(), Insideforms.this.insideforms.getHeight());
                }
            }
        });
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        this.movex = e.getX();
        this.movey = e.getY();
        if (e.isMetaDown()) {
            if (this.formsid == 57) {
                FormsManagement.disposeForm(this.formsid);
            }
            else {
                FormsManagement.HideForm(this.formsid);
            }
        }
        else {
            FormsManagement.Switchinglevel(this.formsid);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public int getFormsid() {
        return this.formsid;
    }
    
    public void setFormsid(int formsid) {
        this.formsid = formsid;
    }
    
    public JPanel getjPanel() {
        return this.jPanel;
    }
    
    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }
}
