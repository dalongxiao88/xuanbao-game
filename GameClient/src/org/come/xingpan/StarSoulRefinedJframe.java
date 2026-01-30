package org.come.xingpan;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.model.InternalForm;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class StarSoulRefinedJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private StarSoulRefinedJpane starSoulRefinedJpane;
    
    public static StarSoulRefinedJframe getStarSoulRefinedJframe() {
        return (StarSoulRefinedJframe)FormsManagement.getInternalForm(120).getFrame();
    }
    
    public static StarSoulRefinedJframe getShowJframeSummonEquipMain() {
        InternalForm form = FormsManagement.getInternalForm2(120);
        if (form == null) {
            return null;
        }
        StarSoulRefinedJframe equipMain = (StarSoulRefinedJframe)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public StarSoulRefinedJframe() {
        this.add(this.starSoulRefinedJpane = new StarSoulRefinedJpane());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(80, 200, 462, 445);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (StarSoulRefinedJframe.this.isVisible()) {
                    int x = e.getX() - StarSoulRefinedJframe.this.first_x;
                    int y = e.getY() - StarSoulRefinedJframe.this.first_y;
                    StarSoulRefinedJframe.this.setBounds(x + StarSoulRefinedJframe.this.getX(), y + StarSoulRefinedJframe.this.getY(), StarSoulRefinedJframe.this.getWidth(), StarSoulRefinedJframe.this.getHeight());
                }
            }
        });
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(120);
        }
        else {
            FormsManagement.Switchinglevel(120);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
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
    
    public StarSoulRefinedJpane getStarSoulRefinedJpane() {
        return this.starSoulRefinedJpane;
    }
}
