package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.model.InternalForm;
import org.come.until.FormsManagement;
import org.come.Jpanel.DreamlandTrialMainJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class DreamlandTrialMainJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private DreamlandTrialMainJpanel dreamlandTrialMainJpanel;
    
    public static DreamlandTrialMainJframe getDreamlandTrialMainJframe() {
        return (DreamlandTrialMainJframe)FormsManagement.getInternalForm(111).getFrame();
    }
    
    public static DreamlandTrialMainJframe getShowDreamlandTrialMainJframe() {
        InternalForm form = FormsManagement.getInternalForm2(111);
        if (form == null) {
            return null;
        }
        DreamlandTrialMainJframe equipMain = (DreamlandTrialMainJframe)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public DreamlandTrialMainJframe() {
        this.add(this.dreamlandTrialMainJpanel = new DreamlandTrialMainJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(200, 60, 472, 403);
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
                if (DreamlandTrialMainJframe.getDreamlandTrialMainJframe().isVisible()) {
                    int x = e.getX() - DreamlandTrialMainJframe.this.first_x;
                    int y = e.getY() - DreamlandTrialMainJframe.this.first_y;
                    DreamlandTrialMainJframe.getDreamlandTrialMainJframe().setBounds(x + DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getX(), y + DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getY(), DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getWidth(), DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getHeight());
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
            FormsManagement.HideForm(111);
        }
        else {
            FormsManagement.Switchinglevel(111);
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
    
    public DreamlandTrialMainJpanel getDreamlandTrialMainJpanel() {
        return this.dreamlandTrialMainJpanel;
    }
    
    public void setDreamlandTrialMainJpanel(DreamlandTrialMainJpanel dreamlandTrialMainJpanel) {
        this.dreamlandTrialMainJpanel = dreamlandTrialMainJpanel;
    }
}
