package org.come.strength;

import org.come.bean.Skill;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import come.tool.Scene.DNTGScene;
import com.tool.image.ImageMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.MoBanBtn;

public class BtnStrengthMain extends MoBanBtn
{
    private int caozuo;
    private JpanelStrengthMain.SkillLabel skillLabel;
    
    public BtnStrengthMain(String iconpath, int type, Color[] colors, Font font, int caozuo, String text, JpanelStrengthMain.SkillLabel skillLabel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.skillLabel = skillLabel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 0 && ImageMixDeal.scene != null && ImageMixDeal.scene.getSceneId() == 1011) {
            DNTGScene scene = (DNTGScene)ImageMixDeal.scene;
            int dn_JB = scene.getDN_JB().intValue();
            Skill skill = this.skillLabel.getSkill();
            BigDecimal multiply = new BigDecimal(this.skillLabel.getLevel() + 1).multiply(new BigDecimal(skill.getDielectric()));
            if (multiply.compareTo(new BigDecimal(dn_JB)) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("金币不足，无法升级");
                return;
            }
            String sendmes = Agreement.getAgreement().sceneAgreement("1011|L" + skill.getSkillid());
            SendMessageUntil.toServer(sendmes);
        }
    }
}
