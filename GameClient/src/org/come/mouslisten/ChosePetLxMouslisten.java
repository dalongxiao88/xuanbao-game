package org.come.mouslisten;

import org.skill.panel.LxPanel;
import org.come.until.FormsManagement;
import org.come.until.UserMessUntil;
import org.come.Frame.PetLxJframe;

public class ChosePetLxMouslisten
{
    public static void refreshPetSkills() {
        LxPanel petSkillsJpanel = PetLxJframe.getPetLxJframe().getLxPanel();
        if (UserMessUntil.getChosePetMes() != null) {
            UserMessUntil.getChosePetMes();
            if (UserMessUntil.getChosePetMes().getTurnRount() >= 1) {
                petSkillsJpanel.init();
                return;
            }
        }
        FormsManagement.HideForm(601);
    }
}
