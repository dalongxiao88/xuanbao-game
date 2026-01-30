package come.tool.Fighting;

import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import com.tool.tcp.GetTcpPath;
import java.awt.Color;

public class DataDisplay
{
    static int TILIU;
    private String tcp;
    private Color tsc;
    private String ts;
    private String hpbig;
    private String hpsmall;
    private Color hpc;
    private String mpbig;
    private String mpsmall;
    private Color mpc;
    private long time;
    public String ZM;
    
    public DataDisplay() {
        this.ZM = "致命";
    }
    
    public DataDisplay(String ts, int hp, int mp) {
        this.ZM = "致命";
        if (ts != null) {
            if (ts.equals("致命")) {
                this.tcp = GetTcpPath.getMouseTcp("ztzm");
            }
            else if (ts.equals("怨气")) {
                this.tcp = GetTcpPath.getMouseTcp("ztyq");
            }
            else if (ts.equals("吸收")) {
                this.tcp = GetTcpPath.getMouseTcp("ztxs");
            }
            else if (ts.startsWith("吸收")) {
                this.tcp = GetTcpPath.getMouseTcp("ztxs");
                this.ts = "吸收";
                String str = ts.substring(2).trim();
                this.hpc = new Color(8, 170, 148);
                if (str.length() >= 6) {
                    this.hpbig = str.substring(0, str.length() - 4);
                    this.hpsmall = str.substring(str.length() - 4, str.length());
                }
                else {
                    this.hpsmall = str;
                }
            }
            else if (ts.equals("怒气")) {
                this.tcp = GetTcpPath.getMouseTcp("ztnq");
            }
            else if (ts.equals("至圣")) {
                this.tcp = GetTcpPath.getMouseTcp("zssh");
            }
            else if (ts.equals("狂暴")) {
                this.tcp = GetTcpPath.getMouseTcp("ztkb");
            }
            else if (ts.equals("4倍")) {
                this.tcp = GetTcpPath.getMouseTcp("zt4b");
            }
            else if (ts.equals("3倍")) {
                this.tcp = GetTcpPath.getMouseTcp("zt3b");
            }
            else {
                this.ts = ts;
                this.tsc = Color.YELLOW;
            }
        }
        if (hp != 0) {
            String values = null;
            if (hp > 0) {
                values = "+" + hp;
                this.hpc = Color.GREEN;
            }
            else {
                values = hp + "";
                this.hpc = UIUtils.COLOR_HURTR1;
                if (ts != null && ts.equals("至圣")) {
                    this.hpc = new Color(249, 255, 240);
                }
            }
            if (values.length() >= 6) {
                this.hpbig = values.substring(0, values.length() - 4);
                this.hpsmall = values.substring(values.length() - 4, values.length());
            }
            else {
                this.hpsmall = values;
            }
        }
        if (mp != 0) {
            String values = null;
            if (mp > 0) {
                values = "+" + mp;
            }
            else {
                values = mp + "";
            }
            this.mpc = new Color(255, 176, 30);
            if (values.length() >= 6) {
                this.mpbig = values.substring(0, values.length() - 4);
                this.mpsmall = values.substring(values.length() - 4, values.length());
            }
            else {
                this.mpsmall = values;
            }
        }
        this.time = 0L;
    }
    
    public boolean draw(Graphics g, int x, int y, int camp, long dietime) {
        this.time += dietime;
        if (this.time > (long)DataDisplay.TILIU) {
            return true;
        }
        int sum = (int)(this.time / 15L);
        y -= sum;
        if (this.tsc != null) {
            int row = x - this.ts.length() * 16 - 5;
            g.setFont(UIUtils.nameFont);
            g.setColor(UIUtils.COLOR_HURTB2);
            g.drawString(this.ts, row - 1, y);
            g.drawString(this.ts, row + 1, y);
            g.drawString(this.ts, row, y - 1);
            g.drawString(this.ts, row, y + 1);
            g.setColor(this.tsc);
            g.drawString(this.ts, row, y);
        }
        if (this.tcp != null) {
            Sprite sprite = SpriteFactory.Prepare(this.tcp);
            if (sprite != null) {
                sprite.updateToTime(0L, 0);
                sprite.draw(g, x - 40, y - 15);
            }
        }
        if (this.hpc != null) {
            int row2 = x;
            if (this.hpbig != null) {
                g.setFont(UIUtils.TEXT_FONT22);
                g.setColor(Color.BLACK);
                g.drawString(this.hpbig, x - 1, y);
                g.drawString(this.hpbig, x + 1, y);
                g.drawString(this.hpbig, x, y - 1);
                g.drawString(this.hpbig, x, y + 1);
                g.setColor(this.hpc);
                g.drawString(this.hpbig, x, y);
                row2 += this.hpbig.length() * 10;
            }
            g.setFont(UIUtils.TEXT_HURT2);
            g.setColor(Color.BLACK);
            g.drawString(this.hpsmall, row2 - 1, y);
            g.drawString(this.hpsmall, row2 + 1, y);
            g.drawString(this.hpsmall, row2, y - 1);
            g.drawString(this.hpsmall, row2, y + 1);
            g.setColor(this.hpc);
            g.drawString(this.hpsmall, row2, y);
        }
        y += 15;
        if (this.mpc != null) {
            int row2 = x;
            if (this.mpbig != null) {
                g.setFont(UIUtils.TEXT_FONT22);
                g.setColor(Color.BLACK);
                g.drawString(this.mpbig, x - 1, y);
                g.drawString(this.mpbig, x + 1, y);
                g.drawString(this.mpbig, x, y - 1);
                g.drawString(this.mpbig, x, y + 1);
                g.setColor(this.mpc);
                g.drawString(this.mpbig, x, y);
                row2 += this.mpbig.length() * 10;
            }
            g.setFont(UIUtils.TEXT_HURT2);
            g.setColor(Color.BLACK);
            g.drawString(this.mpsmall, row2 - 1, y);
            g.drawString(this.mpsmall, row2 + 1, y);
            g.drawString(this.mpsmall, row2, y - 1);
            g.drawString(this.mpsmall, row2, y + 1);
            g.setColor(this.mpc);
            g.drawString(this.mpsmall, row2, y);
        }
        return false;
    }
    
    public String getTs() {
        return this.ts;
    }
    
    public void setTs(String ts) {
        this.ts = ts;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    static {
        DataDisplay.TILIU = 1300;
    }
}
