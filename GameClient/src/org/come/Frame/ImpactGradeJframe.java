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
import org.come.Jpanel.ImpactGradeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ImpactGradeJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private ImpactGradeJpanel impactGradeJpanel;
    
    public static ImpactGradeJframe getImpactGradeJframe() {
        return (ImpactGradeJframe)FormsManagement.getInternalForm(103).getFrame();
    }
    
    public static ImpactGradeJframe getShowImpactGradeJframe() {
        InternalForm form = FormsManagement.getInternalForm2(103);
        if (form == null) {
            return null;
        }
        ImpactGradeJframe equipMain = (ImpactGradeJframe)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public ImpactGradeJframe() {
        this.add(this.impactGradeJpanel = new ImpactGradeJpanel());
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
                if (ImpactGradeJframe.getImpactGradeJframe().isVisible()) {
                    int x = e.getX() - ImpactGradeJframe.this.first_x;
                    int y = e.getY() - ImpactGradeJframe.this.first_y;
                    ImpactGradeJframe.getImpactGradeJframe().setBounds(x + ImpactGradeJframe.getImpactGradeJframe().getX(), y + ImpactGradeJframe.getImpactGradeJframe().getY(), ImpactGradeJframe.getImpactGradeJframe().getWidth(), ImpactGradeJframe.getImpactGradeJframe().getHeight());
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
            FormsManagement.HideForm(103);
        }
        else {
            FormsManagement.Switchinglevel(103);
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
    
    public ImpactGradeJpanel getImpactGradeJpanel() {
        return this.impactGradeJpanel;
    }
    
    public void setImpactGradeJpanel(ImpactGradeJpanel impactGradeJpanel) {
        this.impactGradeJpanel = impactGradeJpanel;
    }
}
