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
import org.come.Jpanel.EverydayRechargeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class EverydayRechargeJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private EverydayRechargeJpanel everydayRechargeJpanel;
    
    public static EverydayRechargeJframe getEverydayRechargeJframe() {
        return (EverydayRechargeJframe)FormsManagement.getInternalForm(100).getFrame();
    }
    
    public static EverydayRechargeJframe getShowEverydayRechargeJframe() {
        InternalForm form = FormsManagement.getInternalForm2(100);
        if (form == null) {
            return null;
        }
        EverydayRechargeJframe equipMain = (EverydayRechargeJframe)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public EverydayRechargeJframe() {
        this.add(this.everydayRechargeJpanel = new EverydayRechargeJpanel());
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
                if (EverydayRechargeJframe.getEverydayRechargeJframe().isVisible()) {
                    int x = e.getX() - EverydayRechargeJframe.this.first_x;
                    int y = e.getY() - EverydayRechargeJframe.this.first_y;
                    EverydayRechargeJframe.getEverydayRechargeJframe().setBounds(x + EverydayRechargeJframe.getEverydayRechargeJframe().getX(), y + EverydayRechargeJframe.getEverydayRechargeJframe().getY(), EverydayRechargeJframe.getEverydayRechargeJframe().getWidth(), EverydayRechargeJframe.getEverydayRechargeJframe().getHeight());
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
            FormsManagement.HideForm(100);
        }
        else {
            FormsManagement.Switchinglevel(100);
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
    
    public EverydayRechargeJpanel getEverydayRechargeJpanel() {
        return this.everydayRechargeJpanel;
    }
    
    public void setEverydayRechargeJpanel(EverydayRechargeJpanel everydayRechargeJpanel) {
        this.everydayRechargeJpanel = everydayRechargeJpanel;
    }
}
