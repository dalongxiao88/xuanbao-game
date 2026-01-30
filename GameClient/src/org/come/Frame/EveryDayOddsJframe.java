package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.model.InternalForm;
import org.come.until.FormsManagement;
import org.come.Jpanel.EveryDayOddsJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class EveryDayOddsJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private EveryDayOddsJpanel everyDayOddsJpanel;
    
    public static EveryDayOddsJframe getEveryDayOddsJframe() {
        return (EveryDayOddsJframe)FormsManagement.getInternalForm(102).getFrame();
    }
    
    public static EveryDayOddsJframe getShowEveryDayOddsJframe() {
        InternalForm form = FormsManagement.getInternalForm2(102);
        if (form == null) {
            return null;
        }
        EveryDayOddsJframe equipMain = (EveryDayOddsJframe)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public EveryDayOddsJframe() {
        this.add(this.everyDayOddsJpanel = new EveryDayOddsJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(200, 60, 561, 537);
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
                if (EveryDayOddsJframe.getEveryDayOddsJframe().isVisible()) {
                    int x = e.getX() - EveryDayOddsJframe.this.first_x;
                    int y = e.getY() - EveryDayOddsJframe.this.first_y;
                    EveryDayOddsJframe.getEveryDayOddsJframe().setBounds(x + EveryDayOddsJframe.getEveryDayOddsJframe().getX(), y + EveryDayOddsJframe.getEveryDayOddsJframe().getY(), EveryDayOddsJframe.getEveryDayOddsJframe().getWidth(), EveryDayOddsJframe.getEveryDayOddsJframe().getHeight());
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
            FormsManagement.HideForm(102);
        }
        else {
            FormsManagement.Switchinglevel(102);
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
    
    public int getFirst_x() {
        return this.first_x;
    }
    
    public void setFirst_x(int first_x) {
        this.first_x = first_x;
    }
    
    public int getFirst_y() {
        return this.first_y;
    }
    
    public void setFirst_y(int first_y) {
        this.first_y = first_y;
    }
    
    public EveryDayOddsJpanel getEveryDayOddsJpanel() {
        return this.everyDayOddsJpanel;
    }
    
    public void setEveryDayOddsJpanel(EveryDayOddsJpanel everyDayOddsJpanel) {
        this.everyDayOddsJpanel = everyDayOddsJpanel;
    }
}
