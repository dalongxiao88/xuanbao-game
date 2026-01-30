package come.tool.Scene;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.ScrenceUntil;
import org.come.until.Util;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

public class CountDown
{
    private int type;
    private long endTime;
    private long time;
    private String msg;
    private Image image;
    
    public CountDown(int type, long endTime) {
        this.type = type;
        this.endTime = endTime;
        if (type == 0) {
            this.image = new ImageIcon("inkImg/background/S16.png").getImage();
        }
        else if (type == 1) {
            this.image = new ImageIcon("inkImg/background/S17.png").getImage();
        }
        this.msg = "";
    }
    
    public void draw(Graphics g) {
        long cha = this.endTime - Util.getTime();
        if (cha > 0L) {
            g.drawImage(this.image, 0, ScrenceUntil.Screen_y - 130, null);
            cha /= 1000L;
            if (this.time != cha) {
                this.time = cha;
                this.msg = cha / 60L + ":" + cha % 60L;
            }
            g.setColor(Color.YELLOW);
            g.setFont(UIUtils.TEXT_FONT41);
            g.drawString(this.msg, 30, ScrenceUntil.Screen_y - 65);
        }
    }
    
    public void toString(StringBuffer buffer) {
        buffer.append(this.type);
        buffer.append("$");
        buffer.append(this.endTime);
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public long getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
