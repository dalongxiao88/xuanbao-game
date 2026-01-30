package org.come.Frame;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import org.come.Jpanel.BuffstateJpanel;
import javax.swing.JInternalFrame;

public class Buffstatejframe extends JInternalFrame
{
    private BuffstateJpanel buffstatejpanel;
    private int first_x;
    private int first_y;
    
    public static Buffstatejframe getBuffstatejframe() {
        return (Buffstatejframe)FormsManagement.getInternalForm(635).getFrame();
    }
    
    public Buffstatejframe() throws Exception {
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.buffstatejpanel = new BuffstateJpanel(this);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.buffstatejpanel);
        this.pack();
        this.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public BuffstateJpanel getBuffstateJpanel() {
        return this.buffstatejpanel;
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
