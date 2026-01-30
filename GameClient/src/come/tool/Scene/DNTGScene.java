package come.tool.Scene;

import org.come.until.FormsManagement;
import org.come.strength.JframeStrengthMain;
import java.util.Iterator;
import org.come.until.ScrenceUntil;
import org.come.until.Util;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import com.tool.image.ManimgAttribute;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.math.BigDecimal;

public class DNTGScene implements Scene
{
    private int camp;
    private BigDecimal DN_JB;
    private BigDecimal DN_JF;
    private String DN_TT_KJ;
    private String DN_HGS_KJ;
    private String DN_SLs;
    private CountDown countDown;
    private DNTGNV DNTGNv;
    private DNTGSG DNTGSg;
    private Image image;
    private boolean is1;
    private boolean is2;
    private Image XZ;
    private Image XY;
    
    public DNTGScene(String[] vs) {
        this.is1 = true;
        this.is2 = true;
        this.image = new ImageIcon("inkImg/background/S23.png").getImage();
        this.XZ = new ImageIcon("inkImg/button/B27.png").getImage();
        this.XY = new ImageIcon("inkImg/button/B28.png").getImage();
        this.DN_JB = new BigDecimal(0);
        this.DN_JF = new BigDecimal(0);
        this.DN_TT_KJ = "0";
        this.DN_HGS_KJ = "0";
        this.UPData(vs);
    }
    
    @Override
    public void draw(Graphics g2, long DieTime) {
        for (int i = ImageMixDeal.mapMonsterlist.size() - 1; i >= 0; --i) {
            ((ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i)).move2(DieTime);
            ((ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i)).Drawing(g2, DieTime);
        }
        for (ManimgAttribute value : ImageMixDeal.Playerimgmap.values()) {
            value.Drawing(g2, DieTime);
        }
        for (int i = ImageMixDeal.npcimglist.size() - 1; i >= 0; --i) {
            ((ManimgAttribute)ImageMixDeal.npcimglist.get(i)).Drawing(g2, DieTime);
        }
        g2.setFont(UIUtils.TEXT_MSG);
        g2.setColor(Color.CYAN);
        ImageMixDeal.userimg.Drawing(g2, DieTime);
        ImageMixDeal.userimg.draweffects(g2);
        if (this.is1) {
            g2.drawImage(this.image, 0, 120, null);
            Util.drawPrice(g2, this.DN_JB, 25, 253);
            Util.drawPrice(g2, this.DN_JF, 25, 303);
            g2.setColor(Color.white);
            g2.drawString(this.DN_HGS_KJ, 90, 377);
            g2.drawString(this.DN_TT_KJ, 90, 454);
            g2.drawImage(this.XZ, 160, 180, null);
        }
        else {
            g2.drawImage(this.XY, 0, 180, null);
        }
        if (this.countDown != null) {
            this.countDown.draw(g2);
        }
        if (this.DNTGNv != null) {
            if (this.is2) {
                this.DNTGNv.draw(g2);
                g2.drawImage(this.XY, ScrenceUntil.Screen_x - this.DNTGNv.getImage().getWidth(null) + 10, 190, null);
            }
            else {
                g2.drawImage(this.XZ, ScrenceUntil.Screen_x - 20, 190, null);
            }
        }
        else if (this.DNTGSg != null) {
            this.DNTGSg.draw(g2);
        }
    }
    
    @Override
    public boolean Monitor(int x, int y) {
        x -= Util.mapmodel.getShot_x();
        y -= Util.mapmodel.getShot_y();
        if (this.is1) {
            if (x >= 160 && x <= 180 && y >= 180 && y <= 205) {
                this.is1 = !this.is1;
                return true;
            }
        }
        else if (x >= 0 && x <= 20 && y >= 180 && y <= 205) {
            this.is1 = !this.is1;
            return true;
        }
        if (this.DNTGNv != null) {
            if (this.is2) {
                if (y >= 190 && y <= 215 && x >= ScrenceUntil.Screen_x - this.DNTGNv.getImage().getWidth(null) + 10 && x <= ScrenceUntil.Screen_x - this.DNTGNv.getImage().getWidth(null) + 30) {
                    this.is2 = !this.is2;
                    return true;
                }
            }
            else if (x >= ScrenceUntil.Screen_x - 20 && x <= ScrenceUntil.Screen_x && y >= 190 && y <= 215) {
                this.is2 = !this.is2;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void UPData(String[] vs) {
        for (int i = 1; i < vs.length; ++i) {
            if (vs[i].startsWith("M")) {
                this.DN_JB = new BigDecimal(vs[i].substring(1));
            }
            else if (vs[i].startsWith("J")) {
                this.DN_JF = new BigDecimal(vs[i].substring(1));
            }
            else if (vs[i].startsWith("K")) {
                if (vs[i].startsWith("K0")) {
                    this.DN_TT_KJ = vs[i].substring(2);
                }
                else {
                    this.DN_HGS_KJ = vs[i].substring(2);
                }
            }
            else if (vs[i].startsWith("N")) {
                if (vs[i].length() == 1) {
                    this.DNTGNv = null;
                }
                else {
                    if (this.DNTGNv == null) {
                        this.DNTGNv = new DNTGNV();
                    }
                    if (vs[i].startsWith("N0")) {
                        this.DNTGNv.upRanking(0, vs[i].substring(2));
                    }
                    else if (vs[i].startsWith("N1")) {
                        this.DNTGNv.upRanking(1, vs[i].substring(2));
                    }
                    else {
                        String[] v = vs[i].substring(2).split("\\$");
                        this.DNTGNv.upRole(this.camp, Integer.parseInt(v[0]), Integer.parseInt(v[1]));
                    }
                }
            }
            else if (vs[i].startsWith("S")) {
                if (vs[i].length() == 1) {
                    this.DNTGSg = null;
                }
                else {
                    if (this.DNTGSg == null) {
                        this.DNTGSg = new DNTGSG();
                    }
                    if (vs[i].startsWith("S0")) {
                        this.DNTGSg.upValue(0, Integer.parseInt(vs[i].substring(2)));
                    }
                    else if (vs[i].startsWith("S1")) {
                        this.DNTGSg.upValue(1, Integer.parseInt(vs[i].substring(2)));
                    }
                    else {
                        this.DNTGSg.upSG(vs[i].substring(2));
                    }
                }
            }
            else if (vs[i].startsWith("D")) {
                if (vs[i].length() == 1) {
                    this.countDown = null;
                }
                else {
                    String[] v = vs[i].substring(1).split("\\$");
                    this.countDown = new CountDown(Integer.parseInt(v[0]), Long.parseLong(v[1]));
                }
            }
            else if (vs[i].startsWith("C")) {
                this.camp = Integer.parseInt(vs[i].substring(1));
            }
            else if (vs[i].startsWith("L")) {
                this.DN_SLs = vs[i].substring(1);
                JframeStrengthMain.getJframeStrengthMain().getJpanelStrengthMain().refreshSkills(this.DN_SLs);
            }
        }
    }
    
    public void showSL() {
        JframeStrengthMain.getJframeStrengthMain().getJpanelStrengthMain().refreshSkills(this.DN_SLs);
        FormsManagement.showForm(98);
    }
    
    @Override
    public int getSceneId() {
        return 1011;
    }
    
    @Override
    public void end() {
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public BigDecimal getDN_JB() {
        return this.DN_JB;
    }
    
    public void setDN_JB(BigDecimal dN_JB) {
        this.DN_JB = dN_JB;
    }
    
    public BigDecimal getDN_JF() {
        return this.DN_JF;
    }
    
    public void setDN_JF(BigDecimal dN_JF) {
        this.DN_JF = dN_JF;
    }
}
