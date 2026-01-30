package come.tool.Fighting;

import org.come.until.UserMessUntil;
import java.util.List;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import org.come.bean.Skill;

public class SkillTx
{
    private int id;
    private Skill skill;
    private int x;
    private int y;
    private boolean is;
    private ImageIcon icon;
    private static Image image;
    
    public static Image getImage() {
        if (SkillTx.image == null) {
            SkillTx.image = new ImageIcon("img/xy2uiimg/技能框_w36,h36.png").getImage();
        }
        return SkillTx.image;
    }
    
    public SkillTx() {
        if (SkillTx.image == null) {
            SkillTx.image = new ImageIcon("img/xy2uiimg/技能框_w36,h36.png").getImage();
        }
    }
    
    public void draw(Graphics g) {
        if (this.icon == null) {
            this.icon = CutButtonImage.TYCSkill(this.id);
        }
        g.drawImage(SkillTx.image, this.x - 3, this.y - 3, 36, 36, null);
        g.drawImage(this.icon.getImage(), this.x, this.y, 30, 30, null);
        Fightingimage fightingimage = FightingMixDeal.getdata(0);
        List<TypeState> list = fightingimage.getFightingManData().cxxx("技能");
        int i = 0;
        while (i < list.size()) {
            TypeState typeState = (TypeState)list.get(i);
            if (typeState.getType().equals(this.skill.getSkillname()) && typeState.getState() == 2) {
                g.setColor(new Color(255, 0, 0, 120));
                g.fillRect(this.x, this.y, 30, 30);
                break;
            }
            else {
                ++i;
            }
        }
    }
    
    public void SReset(String skillName, int x, int y) {
        this.is = false;
        this.x = x;
        this.y = y;
        if (this.skill == null || !this.skill.getSkillname().equals(skillName)) {
            this.skill = UserMessUntil.getskill1(skillName);
            this.id = Integer.parseInt(this.skill.getSkillid());
            this.icon = null;
        }
    }
    
    public boolean isMonitor(int Mx, int My) {
        return this.x <= Mx && this.x + 35 >= Mx && this.y <= My && this.y + 35 >= My;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Skill getSkill() {
        return this.skill;
    }
    
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public boolean isIs() {
        return this.is;
    }
    
    public void setIs(boolean is) {
        this.is = is;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
