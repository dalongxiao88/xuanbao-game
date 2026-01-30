package org.come.entity;

import java.util.Map;
import org.come.bean.ConfigureBean;
import javax.swing.ImageIcon;
import com.updateNew.MyIsif;
import java.awt.Color;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.Graphics;
import org.come.socket.GameClient;
import org.come.Jpanel.NewRefiningJpanel;
import java.awt.Image;

public class RefiningValue
{
    private static Image icon;
    private static Image icon2;
    private NewRefiningJpanel NrJpanel;
    private int x;
    private int y;
    private String v1;
    private String v2;
    private boolean is;
    
    public RefiningValue(int x, int y, String v1, String v2, boolean is, NewRefiningJpanel nrJpanel) {
        this.x = x;
        this.y = y;
        this.v1 = v1;
        this.v2 = v2;
        if (GameClient.lianhua == 0) {
            this.is = is;
        }
        this.NrJpanel = nrJpanel;
    }
    
    public void draw(Graphics g) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        g.setColor(Color.GREEN);
        if (MyIsif.getStyle().equals("水墨")) {
            RefiningValue.icon = new ImageIcon("inkImg/background1/B282.png").getImage();
            RefiningValue.icon2 = new ImageIcon("inkImg/button/14.png").getImage();
            g.drawImage(RefiningValue.icon, this.x, this.y, null);
            if (this.is && configure.getLxsdkg().equals("开")) {
                g.drawImage(RefiningValue.icon2, this.x + 145, this.y + 4, null);
            }
            if (this.v2.contains("|制作人%")) {
                this.v2 = this.v2.split("\\|")[0];
            }
            if (this.v2.contains("|制作人")) {
                this.v2 = this.v2.split("\\|")[0];
            }
            if (this.v1.startsWith("特技")) {
                g.setColor(new Color(160, 152, 200));
            }
            g.drawString(this.v1, this.x + 10, this.y + 19);
            g.drawString(this.v2, this.x + 172, this.y + 19);
        }
        else {
            if (this.v2.contains("|制作人%")) {
                this.v2 = this.v2.split("\\|")[0];
            }
            if (this.v2.contains("|制作人")) {
                this.v2 = this.v2.split("\\|")[0];
            }
            RefiningValue.icon = new ImageIcon("inkImg/hongmu/S67.png").getImage();
            RefiningValue.icon2 = new ImageIcon("inkImg/button/14.png").getImage();
            g.drawImage(RefiningValue.icon, this.x, this.y, null);
            if (this.is) {
                g.drawImage(RefiningValue.icon2, this.x + 112, this.y + 5, null);
            }
            if (this.v1.startsWith("特技")) {
                g.setColor(new Color(160, 152, 200));
            }
            g.drawString(this.v1, this.x + 10, this.y + 20);
            g.drawString(this.v2, this.x + 150, this.y + 20);
        }
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
    
    public String getV1() {
        return this.v1;
    }
    
    public void setV1(String v1) {
        this.v1 = v1;
    }
    
    public String getV2() {
        return this.v2;
    }
    
    public void setV2(String v2) {
        this.v2 = v2;
    }
    
    static {
        RefiningValue.icon = new ImageIcon("inkImg/hongmu/S67.png").getImage();
        RefiningValue.icon2 = new ImageIcon("inkImg/button/14.png").getImage();
    }
}
