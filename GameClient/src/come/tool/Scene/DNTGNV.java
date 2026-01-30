package come.tool.Scene;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.image.ImageObserver;
import org.come.until.ScrenceUntil;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

public class DNTGNV
{
    private int num;
    private int ci;
    private String msg1;
    private String msg2;
    private DNTGNVRole[] TTNV;
    private DNTGNVRole[] HGSNV;
    private Image image;
    
    public DNTGNV() {
        this.num = 0;
        this.ci = 0;
        this.msg1 = "";
        this.msg2 = "";
        this.TTNV = new DNTGNVRole[5];
        this.HGSNV = new DNTGNVRole[5];
        this.image = new ImageIcon("inkImg/background/S19.png").getImage();
    }
    
    public void upRole(int camp, int num, int ci) {
        this.num = num;
        this.ci = ci;
        if (ci == 0) {
            this.msg1 = ((camp == 0) ? "天庭" : "花果山");
        }
        else {
            this.msg1 = ((camp == 0) ? ("天庭   第" + ci + "名") : ("花果山   第" + ci + "名"));
        }
        this.msg2 = String.valueOf(num);
    }
    
    public void upRanking(int camp, String msg) {
        DNTGNVRole[] roles = (camp == 0) ? this.TTNV : this.HGSNV;
        String[] vs = msg.split("&");
        for (int i = 0; i < vs.length && i < 5; ++i) {
            String[] v = vs[i].split("\\$");
            if (roles[i] == null) {
                roles[i] = new DNTGNVRole(v[0], v[1]);
            }
            else {
                roles[i].upRole(v[0], v[1]);
            }
        }
        for (int i = vs.length; i < 5; ++i) {
            roles[i] = null;
        }
    }
    
    public void draw(Graphics g) {
        int x = ScrenceUntil.Screen_x - this.image.getWidth((ImageObserver)null);
        g.drawImage(this.image, x, 130, (ImageObserver)null);
        g.setColor(Color.white);
        g.setFont(UIUtils.TEXT_FONT2);
        x += 40;
        g.drawString(this.msg1, x, 230);
        g.drawString(this.msg2, x + 108, 230);
        int i = 0;
        while (i < this.TTNV.length) {
            DNTGNVRole role = this.TTNV[i];
            if (role == null) {
                break;
            }
            else {
                g.drawString(role.getName(), x, 258 + 21 * i);
                g.drawString(role.getSize(), x + 108, 258 + 21 * i);
                ++i;
            }
        }
        i = 0;
        while (i < this.HGSNV.length) {
            DNTGNVRole role = this.HGSNV[i];
            if (role == null) {
                break;
            }
            else {
                g.drawString(role.getName(), x, 381 + 21 * i);
                g.drawString(role.getSize(), x + 108, 381 + 21 * i);
                ++i;
            }
        }
    }
    
    public int getNum() {
        return this.num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public int getCi() {
        return this.ci;
    }
    
    public void setCi(int ci) {
        this.ci = ci;
    }
    
    public DNTGNVRole[] getTTNV() {
        return this.TTNV;
    }
    
    public void setTTNV(DNTGNVRole[] tTNV) {
        this.TTNV = tTNV;
    }
    
    public DNTGNVRole[] getHGSNV() {
        return this.HGSNV;
    }
    
    public void setHGSNV(DNTGNVRole[] hGSNV) {
        this.HGSNV = hGSNV;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
}
