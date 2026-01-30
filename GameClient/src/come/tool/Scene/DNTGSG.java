package come.tool.Scene;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.ScrenceUntil;
import org.come.until.Util;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Image;

public class DNTGSG
{
    private long endTime;
    private long time;
    private String msg;
    private Image image;
    private Image image2;
    private int max;
    private int value0;
    private int value1;
    private int length0;
    private int length1;
    private String msg0;
    private String msg1;
    
    public DNTGSG() {
        this.endTime = System.currentTimeMillis() + 1800000L;
        this.msg = "";
        this.image = new ImageIcon("inkImg/background/S20.png").getImage();
        this.image2 = new ImageIcon("inkImg/background/S21.png").getImage();
        this.length0 = 0;
        this.length1 = 0;
        this.msg0 = "";
        this.msg1 = "";
    }
    
    public void upSG(String msg) {
        String[] vs = msg.split("&");
        this.endTime = Long.parseLong(vs[0]);
        this.max = Integer.parseInt(vs[1]);
        this.upValue(0, this.value0);
        this.upValue(1, this.value1);
    }
    
    public void upValue(int camp, int value) {
        if (camp == 0) {
            this.value0 = value;
            this.length0 = this.image2.getWidth((ImageObserver)null) * value / this.max;
            this.msg0 = value + "/" + this.max;
        }
        else {
            this.value1 = value;
            this.length1 = this.image2.getWidth((ImageObserver)null) * value / this.max;
            this.msg1 = value + "/" + this.max;
        }
    }
    
    public void draw(Graphics g) {
        long cha = this.endTime - Util.getTime();
        if (cha > 0L) {
            int x = (ScrenceUntil.Screen_x - this.image.getWidth((ImageObserver)null)) / 2;
            g.drawImage(this.image, x, 50, (ImageObserver)null);
            cha /= 1000L;
            if (this.time != cha) {
                this.time = cha;
                this.msg = cha / 60L + ":" + cha % 60L;
            }
            g.setColor(Color.RED);
            g.setFont(UIUtils.TEXT_HY19);
            g.drawString(this.msg, x + 5, 80);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT);
            g.drawString(this.msg0, x + 160, 68);
            g.drawImage(this.image2, x + 100, 68, this.length0, 2, (ImageObserver)null);
            g.drawString(this.msg1, x + 160, 87);
            g.drawImage(this.image2, x + 100, 87, this.length1, 2, (ImageObserver)null);
        }
    }
}
