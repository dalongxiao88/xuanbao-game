package org.come.starcard;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.model.InternalForm;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class JframeStarCardMain extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private JpanelStarCardMain jpanelStarCardMain;
    
    public static JframeStarCardMain getJframeSummonEquipMain() {
        return (JframeStarCardMain)FormsManagement.getInternalForm(95).getFrame();
    }
    
    public static JframeStarCardMain getShowJframeSummonEquipMain() {
        InternalForm form = FormsManagement.getInternalForm2(95);
        if (form == null) {
            return null;
        }
        JframeStarCardMain equipMain = (JframeStarCardMain)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public JframeStarCardMain() {
        this.add(this.jpanelStarCardMain = new JpanelStarCardMain());
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
                if (JframeStarCardMain.getJframeSummonEquipMain().isVisible()) {
                    int x = e.getX() - JframeStarCardMain.this.first_x;
                    int y = e.getY() - JframeStarCardMain.this.first_y;
                    JframeStarCardMain.getJframeSummonEquipMain().setBounds(x + JframeStarCardMain.getJframeSummonEquipMain().getX(), y + JframeStarCardMain.getJframeSummonEquipMain().getY(), JframeStarCardMain.getJframeSummonEquipMain().getWidth(), JframeStarCardMain.getJframeSummonEquipMain().getHeight());
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
            FormsManagement.HideForm(95);
        }
        else {
            FormsManagement.Switchinglevel(95);
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
    
    public JpanelStarCardMain getJpanelStarCardMain() {
        return this.jpanelStarCardMain;
    }
    
    public void setJpanelStarCardMain(JpanelStarCardMain jpanelStarCardMain) {
        this.jpanelStarCardMain = jpanelStarCardMain;
    }
}
