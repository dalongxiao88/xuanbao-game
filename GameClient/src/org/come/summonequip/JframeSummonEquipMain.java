package org.come.summonequip;

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

public class JframeSummonEquipMain extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private JpanelSummonEquipMain jpanelSummonEquipMain;
    
    public static JframeSummonEquipMain getJframeSummonEquipMain() {
        return (JframeSummonEquipMain)FormsManagement.getInternalForm(91).getFrame();
    }
    
    public static JframeSummonEquipMain getShowJframeSummonEquipMain() {
        InternalForm form = FormsManagement.getInternalForm2(91);
        if (form == null) {
            return null;
        }
        JframeSummonEquipMain equipMain = (JframeSummonEquipMain)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public JframeSummonEquipMain() {
        this.add(this.jpanelSummonEquipMain = new JpanelSummonEquipMain());
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
                if (JframeSummonEquipMain.getJframeSummonEquipMain().isVisible()) {
                    int x = e.getX() - JframeSummonEquipMain.this.first_x;
                    int y = e.getY() - JframeSummonEquipMain.this.first_y;
                    JframeSummonEquipMain.getJframeSummonEquipMain().setBounds(x + JframeSummonEquipMain.getJframeSummonEquipMain().getX(), y + JframeSummonEquipMain.getJframeSummonEquipMain().getY(), JframeSummonEquipMain.getJframeSummonEquipMain().getWidth(), JframeSummonEquipMain.getJframeSummonEquipMain().getHeight());
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
            FormsManagement.HideForm(91);
        }
        else {
            FormsManagement.Switchinglevel(91);
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
    
    public JpanelSummonEquipMain getJpanelSummonEquipMain() {
        return this.jpanelSummonEquipMain;
    }
    
    public void setJpanelSummonEquipMain(JpanelSummonEquipMain jpanelSummonEquipMain) {
        this.jpanelSummonEquipMain = jpanelSummonEquipMain;
    }
}
