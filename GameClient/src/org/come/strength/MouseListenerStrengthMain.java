package org.come.strength;

import org.come.until.FormsManagement;
import org.come.bean.Skill;
import org.come.Jpanel.MsgJapnel;
import org.come.Frame.MsgJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerStrengthMain implements MouseListener
{
    private JpanelStrengthMain.SkillLabel skillLabel;
    
    public MouseListenerStrengthMain(JpanelStrengthMain.SkillLabel skillLabel) {
        this.skillLabel = skillLabel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Skill skill = this.skillLabel.getSkill();
        if (skill != null) {
            MsgJframe.getJframe().getJapnel().TYC(skill.getSkillname(), MsgJapnel.StrengthChange(skill.getRemark(), skill, (double)this.skillLabel.getLevel()));
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm(46);
    }
}
