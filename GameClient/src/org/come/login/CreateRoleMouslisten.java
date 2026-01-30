package org.come.login;

import org.come.Jpanel.RaceChangeMainJpanel;
import org.come.until.Music;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.MouseListener;

public class CreateRoleMouslisten implements MouseListener
{
    private CreateView createView;
    private JLabel jLabel;
    
    public CreateRoleMouslisten(CreateView createView, JLabel jLabel) {
        this.createView = createView;
        this.jLabel = jLabel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.createView.clearCheckRole();
        this.jLabel.setBorder(BorderFactory.createLineBorder(Color.red));
        this.createView.setChecked(this.jLabel.getText());
        int ss = CreateView.Nbh = LoginJpanel.getSpeciesId(this.jLabel.getText()).intValue();
        Music.addyinxiao(ss + ".mp3");
        String s = (String)RaceChangeMainJpanel.introMap.get(Integer.valueOf(ss));
        this.createView.getTextArea().setText(s);
        this.createView.getTextAccount().getCaret().setVisible(true);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        this.jLabel.setBorder(BorderFactory.createLineBorder(null));
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
