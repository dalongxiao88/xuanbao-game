package org.come.Frame;

import org.come.until.ScrenceUntil;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.OptionsJpanel;
import javax.swing.JInternalFrame;

public class OptionsJframe extends JInternalFrame
{
    private OptionsJpanel optionsJpanel;
    
    public static OptionsJframe getOptionsJframe() {
        return (OptionsJframe)FormsManagement.getInternalForm(104).getFrame();
    }
    
    public OptionsJframe() {
        this.setContentPane(this.optionsJpanel = new OptionsJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds((ScrenceUntil.Screen_x - 534) / 2, (ScrenceUntil.Screen_y - 145) / 3, 534, 145);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public OptionsJpanel getOptionsJpanel() {
        return this.optionsJpanel;
    }
    
    public void setOptionsJpanel(OptionsJpanel optionsJpanel) {
        this.optionsJpanel = optionsJpanel;
    }
}
