package org.come.tt;

import javax.swing.JLabel;

public class EventSelecton
{
    private JLabel[] JLabel;
    private Integer index;
    
    public EventSelecton(JLabel jLabel0, JLabel jLabel1) {
        (this.JLabel = new JLabel[2])[0] = jLabel0;
        this.JLabel[1] = jLabel1;
        this.index = Integer.valueOf(0);
    }
    
    public JLabel[] getJLabel() {
        return this.JLabel;
    }
    
    public void setJLabel(JLabel[] JLabel) {
        this.JLabel = JLabel;
    }
    
    public Integer getIndex() {
        return this.index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }
}
