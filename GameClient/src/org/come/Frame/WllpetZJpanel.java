package org.come.Frame;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WllpetZJpanel extends JPanel
{
    private JLabel toubbmp;
    private JLabel toubbhp;
    private JLabel toubbexe;
    
    public WllpetZJpanel() throws Exception {
        this.add(this.toubbhp = new JLabel());
        this.add(this.toubbmp = new JLabel());
        this.add(this.toubbexe = new JLabel());
    }
    
    public JLabel gettoubbhp() {
        return this.toubbhp;
    }
    
    public void settoubbhp(JLabel toubbhp) {
        this.toubbhp = toubbhp;
    }
    
    public JLabel gettoubbmp() {
        return this.toubbmp;
    }
    
    public void settoubbmp(JLabel toubbmp) {
        this.toubbmp = toubbmp;
    }
    
    public JLabel gettoubbexe() {
        return this.toubbexe;
    }
    
    public void setToubbexe(JLabel toubbexe) {
        this.toubbexe = toubbexe;
    }
    
    public void intoFighting() {
        this.toubbexe.setVisible(true);
        this.toubbhp.setVisible(true);
        this.toubbmp.setVisible(true);
    }
    
    public void outFighting() {
        this.toubbexe.setVisible(false);
        this.toubbhp.setVisible(false);
        this.toubbmp.setVisible(false);
    }
}
