package org.come.mouslisten;

import org.come.Jpanel.RaceChangeMainJpanel;
import org.come.until.ControlNpcXmlUntil;
import com.tool.tcp.SpriteFactory;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.come.Frame.RaceChangeMainJframe;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.awt.event.MouseListener;

public class ChoseRoleChangeMouslisten implements MouseListener
{
    private int index;
    private BigDecimal species_id;
    private String species_intro;
    
    public ChoseRoleChangeMouslisten(int index) {
        this.index = index;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        RaceChangeMainJpanel roleMetempsychosisJpanel = RaceChangeMainJframe.getRaceChangeMainJframe().getRaceChangeMainJpanel();
        for (int i = 0; i < roleMetempsychosisJpanel.getChangeLabelKuang().length; ++i) {
            if (i == this.index) {
                roleMetempsychosisJpanel.getChangeLabelKuang()[i].setBorder(BorderFactory.createLineBorder(Color.red));
                roleMetempsychosisJpanel.setSpecies_id(this.species_id);
                roleMetempsychosisJpanel.textArea.setText(this.species_intro);
                roleMetempsychosisJpanel.setRolePart1(SpriteFactory.createPart(this.species_id.toString(), 9, 1, (String)null));
                roleMetempsychosisJpanel.setRolePart2(SpriteFactory.createPart(this.species_id.toString(), 5, 1, (String)null));
            }
            else {
                roleMetempsychosisJpanel.getChangeLabelKuang()[i].setBorder(BorderFactory.createEmptyBorder());
            }
        }
        ControlNpcXmlUntil.setSpeciesId("" + this.species_id);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public BigDecimal getSpecies_id() {
        return this.species_id;
    }
    
    public void setSpecies_id(BigDecimal species_id) {
        this.species_id = species_id;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public String getSpecies_intro() {
        return this.species_intro;
    }
    
    public void setSpecies_intro(String species_intro) {
        this.species_intro = species_intro;
    }
}
