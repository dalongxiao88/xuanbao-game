package org.come.mouslisten;

import com.updateNew.MyIsif;
import org.come.Frame.AlchemyJframe;
import org.come.Jpanel.SpiritualJpanel;
import org.come.bean.Skill;
import org.come.until.MessagrFlagUntil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SpiritualPetSkillsMouslisten implements MouseListener {
    private int skillwl;
    private Skill skill;
    private int index;
    private SpiritualJpanel spiritualJpanel;
    private int[] xy;

    public SpiritualPetSkillsMouslisten(final int index, final SpiritualJpanel spiritualJpanel, final int[] xy) {
        this.spiritualJpanel = spiritualJpanel;
        this.xy = xy;
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (this.skill != null) {
            AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel().setPetskillNO(this.index);
            AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel().setPetskillID(this.skill.getSkillid());
        }
        if (!"水墨".equals(MyIsif.getStyle())) {
            SpiritualJpanel.getEffectx().setBounds(this.xy[0] - 2, this.xy[1] - 1, 34, 34);
        } else {
            SpiritualJpanel.getEffectx().setBounds(this.xy[0] + 20, this.xy[1] - 14, 34, 34);
        }

    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
        }
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        }
    }

    public Skill getSkill() {
        return this.skill;
    }

    public void setSkill(final Skill skill) {
        this.skill = skill;
    }

    public int getSkillwl() {
        return this.skillwl;
    }

    public void setSkillwl(final int skillwl) {
        this.skillwl = skillwl;
    }
}
