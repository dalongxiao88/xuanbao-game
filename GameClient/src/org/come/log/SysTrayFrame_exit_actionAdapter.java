package org.come.log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SysTrayFrame_exit_actionAdapter implements ActionListener
{
    private SysTrayFrame adaptee;
    
    SysTrayFrame_exit_actionAdapter(SysTrayFrame adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.adaptee.exit_actionPerformed(e);
    }
}
