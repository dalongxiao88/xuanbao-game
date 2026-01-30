package org.come.init;

import java.awt.Dimension;
import javax.swing.JPanel;

public class CheckUpdateJPanel extends JPanel
{
    UpdateView updateView;
    
    public CheckUpdateJPanel() {
        this.setLayout(null);
        this.add(this.updateView = new UpdateView());
        this.setPreferredSize(new Dimension(800, 600));
    }
    
    public UpdateView getUpdateView() {
        return this.updateView;
    }
}
