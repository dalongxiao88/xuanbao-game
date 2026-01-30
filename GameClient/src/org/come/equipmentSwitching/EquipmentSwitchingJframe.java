package org.come.equipmentSwitching;

import org.come.until.Music;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class EquipmentSwitchingJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private EquipmentSwitchingJpanel assistantJpanel;
    
    public static EquipmentSwitchingJframe getAssistantJframe() {
        return (EquipmentSwitchingJframe)FormsManagement.getInternalForm(100000).getFrame();
    }
    
    public EquipmentSwitchingJframe() throws Exception {
        this.assistantJpanel = new EquipmentSwitchingJpanel();
        this.getContentPane().add(this.assistantJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(300, 270, 423, 475);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (EquipmentSwitchingJframe.getAssistantJframe().isVisible()) {
                    int x = e.getX() - EquipmentSwitchingJframe.this.first_x;
                    int y = e.getY() - EquipmentSwitchingJframe.this.first_y;
                    EquipmentSwitchingJframe.getAssistantJframe().setBounds(x + EquipmentSwitchingJframe.getAssistantJframe().getX(), y + EquipmentSwitchingJframe.getAssistantJframe().getY(), EquipmentSwitchingJframe.getAssistantJframe().getWidth(), EquipmentSwitchingJframe.getAssistantJframe().getHeight());
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
            FormsManagement.HideForm(100000);
        }
        else {
            FormsManagement.Switchinglevel(100000);
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
    
    public EquipmentSwitchingJpanel getAssistantJpanel() {
        return this.assistantJpanel;
    }
    
    public void setAssistantJpanel(EquipmentSwitchingJpanel assistantJpanel) {
        this.assistantJpanel = assistantJpanel;
    }
}
