package come.tool.BangBattle;

import com.tool.tcp.Frame;
import org.come.bean.PathPoint;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import org.come.until.Util;
import java.awt.Graphics;
import com.tool.tcp.Sprite;
import java.awt.Image;

public class Build
{
    public static int MAN;
    public static int CAN;
    public static int PO;
    public static int LZ1;
    public static int LZ2;
    public static int LZ3;
    public static int LY1;
    public static int LY2;
    public static int LY3;
    public static int TOWER_DOOR;
    public static int TOWER_FIRE;
    public static int TOWER_ICE;
    public static int TOWER_LONG;
    public static int TOWER_HUO;
    private int bh;
    private int type;
    private int state;
    private int hp;
    private int x;
    private int y;
    private Image image;
    private Sprite sprite;
    private Sprite s1;
    private Sprite s2;
    private Sprite s3;
    private int v;
    private int px;
    private int py;
    private int time;
    
    public void draw(Graphics g) {
        if (this.type == Build.TOWER_DOOR && this.state == Build.PO) {
            this.image = null;
        }
        else {
            this.time += 9;
            if (this.state >= Build.LZ1) {
                if (this.sprite != null && this.time > this.sprite.getTime()) {
                    if (this.state != Build.LY3 && this.state != Build.LZ3) {
                        ++this.state;
                    }
                    else {
                        this.state = Build.MAN;
                    }
                    this.setState(this.state);
                }
            }
            else {
                this.time += 9;
            }
            PathPoint point = Util.mapmodel.path(this.x, this.y);
            if (this.sprite == null) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(GetTcpPath.MOUSE);
                buffer.append((this.type == Build.TOWER_ICE) ? Build.TOWER_FIRE : this.type);
                if (this.state == Build.LZ3) {
                    buffer.append(Build.LZ1);
                }
                else if (this.state == Build.LY3) {
                    buffer.append(Build.LY1);
                }
                else {
                    buffer.append(this.state);
                }
                buffer.append(GetTcpPath.WEI);
                this.sprite = SpriteFactory.Prepare(buffer.toString());
            }
            if (this.sprite == null) {
                this.image = null;
            }
            else if (point == null) {
                this.image = null;
            }
            else {
                if (this.type == Build.TOWER_FIRE || this.type == Build.TOWER_ICE) {
                    if (this.s2 == null) {
                        if (this.type == Build.TOWER_FIRE) {
                            this.s2 = SpriteFactory.Prepare("resource/bang/BgTDbR.tcp");
                        }
                        else {
                            this.s2 = SpriteFactory.Prepare("resource/bang/BgTDbB.tcp");
                        }
                    }
                    else {
                        this.s2.updateToTime((long)this.time, 0);
                        this.s2.draw(g, point.getX(), point.getY());
                    }
                }
                if (this.state != Build.LZ3 && this.state != Build.LY3) {
                    this.sprite.updateToTime((long)this.time, 0);
                }
                else {
                    this.sprite.updateToTime((long)(this.sprite.getTime() - this.time), 0);
                }
                this.sprite.draw(g, point.getX(), point.getY());
                Frame frame = this.sprite.getCurrFrame();
                this.px = frame.getRefPixelX();
                this.py = frame.getRefPixelY();
                this.image = frame.getImage();
                if (this.v != 0) {
                    if (this.s3 == null) {
                        if (this.v == 1) {
                            if (this.type == Build.TOWER_DOOR) {
                                this.s3 = SpriteFactory.Prepare("resource/bang/BgTHs.tcp");
                            }
                            else {
                                this.s3 = SpriteFactory.Prepare("resource/bang/BgTDj.tcp");
                            }
                        }
                        else if (this.type == Build.TOWER_FIRE) {
                            this.s3 = SpriteFactory.Prepare("resource/bang/BgTQR.tcp");
                        }
                        else {
                            this.s3 = SpriteFactory.Prepare("resource/bang/BgTQB.tcp");
                        }
                    }
                    else {
                        this.s3.updateToTime((long)this.time, 0);
                        this.s3.draw(g, point.getX(), point.getY());
                        if (this.time > this.s3.getTime()) {
                            this.v = 0;
                            this.s3 = null;
                        }
                    }
                }
            }
        }
    }
    
    public Build(int bh, int type, int state, int hp) {
        this.bh = bh;
        this.type = type;
        this.state = state;
        this.hp = hp;
        switch (bh) {
            case 0: {
                this.x = 307;
                this.y = 360;
                break;
            }
            case 1: {
                this.x = 340;
                this.y = 1593;
                break;
            }
            case 2: {
                this.x = 841;
                this.y = 1113;
                break;
            }
            case 3: {
                this.x = 1118;
                this.y = 1287;
                break;
            }
            case 4: {
                this.x = 556;
                this.y = 1228;
                break;
            }
            case 5: {
                this.x = 882;
                this.y = 1414;
                break;
            }
            case 11: {
                this.x = 2149;
                this.y = 552;
                break;
            }
            case 12: {
                this.x = 1465;
                this.y = 781;
                break;
            }
            case 13: {
                this.x = 1767;
                this.y = 956;
                break;
            }
            case 14: {
                this.x = 1701;
                this.y = 648;
                break;
            }
            case 15: {
                this.x = 2001;
                this.y = 826;
                break;
            }
        }
    }
    
    public int getBh() {
        return this.bh;
    }
    
    public void setBh(int bh) {
        this.bh = bh;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.sprite = null;
        this.image = null;
        this.time = 1;
        this.state = state;
    }
    
    public String getName() {
        switch (this.type) {
            case 0: {
                return "城门";
            }
            case 1: {
                return "火塔";
            }
            case 2: {
                return "冰塔";
            }
            case 3: {
                return "龙神大炮";
            }
            default: {
                return "";
            }
        }
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void setImage(Image image) {
        this.image = image;
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
    
    public int getPx() {
        return this.px;
    }
    
    public void setPx(int px) {
        this.px = px;
    }
    
    public int getPy() {
        return this.py;
    }
    
    public void setPy(int py) {
        this.py = py;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public void setHp(int hp) {
        if (this.hp == hp) {
            if (this.type == Build.TOWER_FIRE || this.type == Build.TOWER_ICE) {
                this.time = 1;
                this.v = 2;
            }
        }
        else {
            if (this.type == Build.TOWER_DOOR || this.type == Build.TOWER_FIRE || this.type == Build.TOWER_ICE) {
                this.time = 1;
                this.v = 1;
            }
            this.hp = hp;
        }
    }
    
    static {
        Build.MAN = 0;
        Build.CAN = 1;
        Build.PO = 2;
        Build.LZ1 = 3;
        Build.LZ2 = 4;
        Build.LZ3 = 5;
        Build.LY1 = 6;
        Build.LY2 = 7;
        Build.LY3 = 8;
        Build.TOWER_DOOR = 0;
        Build.TOWER_FIRE = 1;
        Build.TOWER_ICE = 2;
        Build.TOWER_LONG = 3;
        Build.TOWER_HUO = 4;
    }
}
