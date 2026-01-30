package org.come.model;

import org.come.Jpanel.GameJpanel;
import javax.swing.JInternalFrame;

public class InternalForm
{
    private int formid;
    private String formname;
    private int formlvl;
    private JInternalFrame frame;
    
    public InternalForm(int formid, JInternalFrame frame, int formlvl) {
        this.formid = formid;
        this.frame = frame;
        this.formlvl = formlvl;
    }
    
    public boolean HideForm() {
        GameJpanel.getGameJpanel().remove(this.frame);
        this.frame.setVisible(false);
        if (this.formlvl == 6) {
            this.formlvl = 2;
            return true;
        }
        return this.formlvl == 3;
    }
    
    public String getFormname() {
        return this.formname;
    }
    
    public void setFormname(String formname) {
        this.formname = formname;
    }
    
    public JInternalFrame getFrame() {
        return this.frame;
    }
    
    public void setFrame(JInternalFrame frame) {
        this.frame = frame;
    }
    
    public int getFormid() {
        return this.formid;
    }
    
    public void setFormid(int formid) {
        this.formid = formid;
    }
    
    public int getFormlvl() {
        return this.formlvl;
    }
    
    public void setFormlvl(int formlvl) {
        this.formlvl = formlvl;
    }
}
