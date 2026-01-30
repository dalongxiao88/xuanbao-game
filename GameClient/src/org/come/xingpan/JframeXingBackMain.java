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

public class JframeXingBackMain extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private JpanelXingBackMain JpanelXingBackMain;
    
    public static JframeXingBackMain getJframeSummonEquipMain() {
        return (JframeXingBackMain)FormsManagement.getInternalForm(121).getFrame();
    }
    
    public static JframeXingBackMain getShowJframeSummonEquipMain() {
        InternalForm form = FormsManagement.getInternalForm2(121);
        if (form == null) {
            return null;
        }
        JframeXingBackMain equipMain = (JframeXingBackMain)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public JframeXingBackMain() {
        this.add(this.JpanelXingBackMain = new JpanelXingBackMain());
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
                if (JframeXingBackMain.getJframeSummonEquipMain().isVisible()) {
                    int x = e.getX() - JframeXingBackMain.this.first_x;
                    int y = e.getY() - JframeXingBackMain.this.first_y;
                    JframeXingBackMain.getJframeSummonEquipMain().setBounds(x + JframeXingBackMain.getJframeSummonEquipMain().getX(), y + JframeXingBackMain.getJframeSummonEquipMain().getY(), JframeXingBackMain.getJframeSummonEquipMain().getWidth(), JframeXingBackMain.getJframeSummonEquipMain().getHeight());
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
            FormsManagement.HideForm(121);
        }
        else {
            FormsManagement.Switchinglevel(121);
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
    
    public JpanelXingBackMain getJpanelXingBackMain() {
        return this.JpanelXingBackMain;
    }
    
    public void setJpanelXingBackMain(JpanelXingBackMain JpanelXingBackMain) {
        this.JpanelXingBackMain = JpanelXingBackMain;
    }
}
