package org.come.lianhua;

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

public class AutoMaticRefiningJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private AutoMaticRefiningJpanel assistantJpanel;
    
    public static AutoMaticRefiningJframe getAssistantJframe() {
        return (AutoMaticRefiningJframe)FormsManagement.getInternalForm(1119).getFrame();
    }
    
    public AutoMaticRefiningJframe() throws Exception {
        this.assistantJpanel = new AutoMaticRefiningJpanel();
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
                if (AutoMaticRefiningJframe.getAssistantJframe().isVisible()) {
                    int x = e.getX() - AutoMaticRefiningJframe.this.first_x;
                    int y = e.getY() - AutoMaticRefiningJframe.this.first_y;
                    AutoMaticRefiningJframe.getAssistantJframe().setBounds(x + AutoMaticRefiningJframe.getAssistantJframe().getX(), y + AutoMaticRefiningJframe.getAssistantJframe().getY(), AutoMaticRefiningJframe.getAssistantJframe().getWidth(), AutoMaticRefiningJframe.getAssistantJframe().getHeight());
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
            FormsManagement.HideForm(1119);
            FormsManagement.disposeForm(1119);
        }
        else {
            FormsManagement.Switchinglevel(1119);
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
    
    public AutoMaticRefiningJpanel getAssistantJpanel() {
        return this.assistantJpanel;
    }
    
    public void setAssistantJpanel(AutoMaticRefiningJpanel assistantJpanel) {
        this.assistantJpanel = assistantJpanel;
    }
}
