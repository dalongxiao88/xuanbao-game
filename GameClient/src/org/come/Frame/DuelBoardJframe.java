package org.come.Frame;

import org.come.until.ScrenceUntil;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.DuelBoardJpanel;
import javax.swing.JInternalFrame;

public class DuelBoardJframe extends JInternalFrame
{
    private DuelBoardJpanel duelBoardJpanel;
    private int first_x;
    private int first_y;
    
    public static DuelBoardJframe getDuelBoardJframe() {
        return (DuelBoardJframe)FormsManagement.getInternalForm(9).getFrame();
    }
    
    public DuelBoardJframe() throws Exception {
        this.duelBoardJpanel = new DuelBoardJpanel(this);
        this.getContentPane().add(this.duelBoardJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(ScrenceUntil.Screen_x - 210, 215, 500, 500);
        this.pack();
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
    
    public DuelBoardJpanel getDuelBoardJpanel() {
        return this.duelBoardJpanel;
    }
    
    public void setDuelBoardJpanel(DuelBoardJpanel duelBoardJpanel) {
        this.duelBoardJpanel = duelBoardJpanel;
    }
}
