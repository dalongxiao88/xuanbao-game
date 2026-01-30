package org.come.Jpanel;

import org.come.entity.RoleSummoning;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import come.tool.Fighting.FightingMixDeal;
import org.come.until.UserMessUntil;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MsgJapnel5 extends JPanel
{
    public String sublime;
    public String subsumpet;
    private ImageIcon iconS;
    
    public MsgJapnel5() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(150, 94));
        this.setBackground(new Color(0, 0, 0, 0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconS == null) {
            this.iconS = new ImageIcon("Img/icon/ZD.png");
        }
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (pet == null) {
            this.subsumpet = "";
        }
        g.drawImage(this.iconS.getImage(), 0, 0, 150, 94, this);
        if (FightingMixDeal.zdhh != 0) {
            g.setColor(Color.white);
            g.drawString("自动还剩余", 15, 23);
            g.setColor(Color.red);
            g.setFont(UIUtils.TEXT_FONT2);
            g.drawString(FightingMixDeal.zdhh + "", 78, 23);
            g.drawString(FightingMixDeal.zdhh + "", 79, 23);
            g.setColor(Color.white);
            g.setFont(new Font("宋体", 0, 12));
            g.drawString("回合", 112, 23);
            g.drawString("人 物", 15, 42);
            g.drawString("召唤兽", 15, 60);
            g.setColor(new Color(255, 251, 153));
            if (this.sublime == null && this.subsumpet == null) {
                this.sublime = "普通攻击";
                this.subsumpet = "普通攻击";
            }
            if (this.sublime != null) {
                g.drawString(this.sublime, 79, 42);
            }
            if (this.subsumpet != null) {
                g.drawString(this.subsumpet, 79, 60);
            }
            g.setColor(Color.green);
            g.drawString("右键关闭自动", 35, 82);
        }
    }
}
