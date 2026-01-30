package org.come.lianhua;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class AttributeVo
{
    private JLabel AttributeName;
    private JTextField j1;
    private JTextField j2;
    
    public JLabel getAttributeName() {
        return this.AttributeName;
    }
    
    public void setAttributeName(JLabel attributeName) {
        this.AttributeName = attributeName;
    }
    
    public JTextField getJ1() {
        return this.j1;
    }
    
    public void setJ1(JTextField j1) {
        this.j1 = j1;
    }
    
    public JTextField getJ2() {
        return this.j2;
    }
    
    public void setJ2(JTextField j2) {
        this.j2 = j2;
    }
}
