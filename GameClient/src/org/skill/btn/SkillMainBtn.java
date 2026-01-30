package org.skill.btn;

import java.awt.event.MouseEvent;
import org.skill.panel.SkillMainPanel;
import com.tool.btn.MoBanBtn;

public class SkillMainBtn extends MoBanBtn
{
    private int typeBtn;
    private SkillMainPanel mainPanel;
    
    public SkillMainBtn(String iconpath, int type, int typeBtn, SkillMainPanel mainPanel) {
        super(iconpath, type);
        this.typeBtn = typeBtn;
        this.mainPanel = mainPanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        this.mainPanel.changeBtnPanel(this.typeBtn);
    }
}
