package org.come.Frame;

import java.awt.Color;
import org.come.Jpanel.ExitGameTipJpanel;
import javax.swing.JFrame;

public class ExitGameTipJframe extends JFrame
{
    private ExitGameTipJpanel exitGameTipJpanel;
    
    public ExitGameTipJframe() {
        this.add(this.exitGameTipJpanel = new ExitGameTipJpanel());
        this.setBackground((Color)null);
        this.setUndecorated(true);
        this.setFocusable(true);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }
    
    public ExitGameTipJpanel getExitGameTipJpanel() {
        return this.exitGameTipJpanel;
    }
    
    public void setExitGameTipJpanel(ExitGameTipJpanel exitGameTipJpanel) {
        this.exitGameTipJpanel = exitGameTipJpanel;
    }
}
