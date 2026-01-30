package org.come.equipmentSwitching;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class EquipmenSwitchingIputJframe extends JInternalFrame implements MouseListener
{
    private EquipmentSwitchingInputJpanel equipmentSwitchingInputJpanel;
    private int first_x;
    private int first_y;
    
    public static EquipmenSwitchingIputJframe getExchangeAwardJframe() {
        return (EquipmenSwitchingIputJframe)FormsManagement.getInternalForm(100001).getFrame();
    }
    
    public EquipmenSwitchingIputJframe() {
        this.equipmentSwitchingInputJpanel = new EquipmentSwitchingInputJpanel();
        this.getContentPane().add(this.equipmentSwitchingInputJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(200, 120, 250, 170);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (EquipmenSwitchingIputJframe.this.isVisible()) {
                    int x = e.getX() - EquipmenSwitchingIputJframe.this.first_x;
                    int y = e.getY() - EquipmenSwitchingIputJframe.this.first_y;
                    EquipmenSwitchingIputJframe.this.setBounds(x + EquipmenSwitchingIputJframe.this.getX(), y + EquipmenSwitchingIputJframe.this.getY(), EquipmenSwitchingIputJframe.this.getWidth(), EquipmenSwitchingIputJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(100001);
        }
        else {
            FormsManagement.Switchinglevel(100001);
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
    
    public EquipmentSwitchingInputJpanel getEquipmentSwitchingInputJpanel() {
        return this.equipmentSwitchingInputJpanel;
    }
    
    public void setEquipmentSwitchingInputJpanel(EquipmentSwitchingInputJpanel equipmentSwitchingInputJpanel) {
        this.equipmentSwitchingInputJpanel = equipmentSwitchingInputJpanel;
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
}
