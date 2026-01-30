package org.come.Frame;

import org.come.until.ScrenceUntil;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.Jpanel.FormMaskJpanel;
import javax.swing.JInternalFrame;

public class FormMaskJFrame extends JInternalFrame
{
    private FormMaskJpanel formMaskJpanel;
    
    public FormMaskJFrame() {
        this.add(this.formMaskJpanel = new FormMaskJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.pack();
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(0, 0, ScrenceUntil.ChatFram_X + ScrenceUntil.Screen_x, ScrenceUntil.Screen_y);
    }
    
    public void change(int w, int h) {
        this.setBounds(0, 0, w, h);
        this.formMaskJpanel.changge(w, h);
    }
}
