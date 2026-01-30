package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PetEquipmentJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PetEquipmentJframe extends JInternalFrame implements MouseListener
{
    private PetEquipmentJpanel equipmentJpanel;
    private int first_x;
    private int first_y;
    
    public static PetEquipmentJframe getPetEquipmentJframe() {
        return (PetEquipmentJframe)FormsManagement.getInternalForm(67).getFrame();
    }
    
    public PetEquipmentJframe() {
        this.equipmentJpanel = new PetEquipmentJpanel();
        this.getContentPane().add(this.equipmentJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(570, 100, 360, 499);
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
                if (PetEquipmentJframe.this.isVisible()) {
                    int x = e.getX() - PetEquipmentJframe.this.first_x;
                    int y = e.getY() - PetEquipmentJframe.this.first_y;
                    PetEquipmentJframe.this.setBounds(x + PetEquipmentJframe.this.getX(), y + PetEquipmentJframe.this.getY(), PetEquipmentJframe.this.getWidth(), PetEquipmentJframe.this.getHeight());
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
            FormsManagement.HideForm(67);
        }
        else {
            FormsManagement.Switchinglevel(67);
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
    
    public PetEquipmentJpanel getEquipmentJpanel() {
        return this.equipmentJpanel;
    }
    
    public void setEquipmentJpanel(PetEquipmentJpanel equipmentJpanel) {
        this.equipmentJpanel = equipmentJpanel;
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
