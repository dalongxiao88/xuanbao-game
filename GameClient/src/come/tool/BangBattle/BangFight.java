package come.tool.BangBattle;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.control.BangBattleControl;
import javax.swing.ImageIcon;
import org.come.bean.PathPoint;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import org.come.until.Util;
import com.tool.image.ImageMixDeal;
import org.come.until.ScrenceUntil;
import java.awt.Graphics;
import com.tool.tcp.Sprite;
import java.awt.Image;

public class BangFight
{
    public static int MINSUM;
    private static BangFight bangFight;
    private Image t_z;
    private Image t_xg;
    private Image t_xr;
    private Sprite Bg_Xt_Z;
    public BangTZ tz;
    public int state;
    public int manstate;
    private Build[] builds;
    Sprite Huo;
    
    public static BangFight getBangFight() {
        if (BangFight.bangFight == null) {
            BangFight.bangFight = new BangFight();
        }
        return BangFight.bangFight;
    }
    
    public BangFight() {
        this.builds = new Build[11];
    }
    
    public void HZBZJD(Graphics g) {
        if (this.t_xg == null) {
            return;
        }
        g.drawImage(this.t_z, ScrenceUntil.Screen_x / 2 - 274, 40, null);
        this.Bg_Xt_Z.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        this.Bg_Xt_Z.draw(g, ScrenceUntil.Screen_x / 2, 70);
        Build build = this.builds[1];
        double xc = (double)build.getHp() / 6000.0;
        int cd = (int)(xc * 226.0);
        if (xc < 0.3) {
            g.drawImage(this.t_xr, ScrenceUntil.Screen_x / 2 - 43 - cd, 61, cd, 13, null);
        }
        else {
            g.drawImage(this.t_xg, ScrenceUntil.Screen_x / 2 - 43 - cd, 61, cd, 13, null);
        }
        build = this.builds[6];
        xc = (double)build.getHp() / 6000.0;
        cd = (int)(xc * 226.0);
        if (xc < 0.3) {
            g.drawImage(this.t_xr, ScrenceUntil.Screen_x / 2 + 43, 61, cd, 13, null);
        }
        else {
            g.drawImage(this.t_xg, ScrenceUntil.Screen_x / 2 + 43, 61, cd, 13, null);
        }
    }
    
    public void draw(Graphics g) {
        if (this.state == 1 && Util.ditubianma == 3315) {
            for (int i = 0; i < this.builds.length; ++i) {
                if (this.builds[i] != null) {
                    this.builds[i].draw(g);
                }
            }
            if (this.Huo == null) {
                this.Huo = SpriteFactory.Prepare(GetTcpPath.getMouseTcp("40"));
            }
            else {
                this.Huo.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                PathPoint point = Util.mapmodel.path(336, 466);
                if (point != null) {
                    this.Huo.draw(g, point.getX(), point.getY());
                }
                point = Util.mapmodel.path(11, 306);
                if (point != null) {
                    this.Huo.draw(g, point.getX(), point.getY());
                }
                point = Util.mapmodel.path(350, 135);
                if (point != null) {
                    this.Huo.draw(g, point.getX(), point.getY());
                }
                point = Util.mapmodel.path(684, 292);
                if (point != null) {
                    this.Huo.draw(g, point.getX(), point.getY());
                }
            }
            this.HZBZJD(g);
            if (this.tz != null && this.tz.draw(g)) {
                this.tz = null;
            }
        }
    }
    
    public void init(String msg) {
        if (this.t_xg == null) {
            this.t_xg = new ImageIcon("img/xy2uiimg/bang_t_xg.png").getImage();
            this.t_xr = new ImageIcon("img/xy2uiimg/bang_t_xr.png").getImage();
            this.t_z = new ImageIcon("img/xy2uiimg/bang_t_z.png").getImage();
            this.Bg_Xt_Z = SpriteFactory.VloadSprite("resource/bang/BgXtZ.tcp", null);
        }
        if (msg != null) {
            String[] v = msg.split("\\|");
            int value = Integer.parseInt(v[0]);
            if (value == 0) {
                this.state = Integer.parseInt(v[1]);
                this.manstate = Integer.parseInt(v[2]);
            }
            for (int i = (value == 1) ? 1 : 3; i < v.length; ++i) {
                String[] vs = v[i].split("=");
                int bh = Integer.parseInt(vs[0]);
                int wei = (bh > 5) ? (bh - 5) : bh;
                if (this.builds[wei] == null) {
                    this.builds[wei] = new Build(bh, Integer.parseInt(vs[1]), Integer.parseInt(vs[2]), Integer.parseInt(vs[3]));
                }
                else {
                    this.builds[wei].setState(Integer.parseInt(vs[2]));
                    this.builds[wei].setHp(Integer.parseInt(vs[3]));
                }
            }
        }
        else {
            this.state = 0;
        }
    }
    
    public boolean Monitor(int x, int y) {
        if (this.state == 1) {
            for (int i = 0; i < this.builds.length; ++i) {
                if (this.builds[i] != null && this.builds[i].getImage() != null && ImageMixDeal.toBufferedImage(this.builds[i].getImage(), x - this.builds[i].getX() + this.builds[i].getPx(), y - this.builds[i].getY() + this.builds[i].getPy())) {
                    this.BuildMonitor(this.builds[i]);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void BuildMonitor(Build build) {
        BangBattleControl.build = build;
        String sendmes = null;
        try {
            sendmes = Agreement.getAgreement().gangmonitor(build.getBh() + "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        SendMessageUntil.toServer(sendmes);
    }
    
    public boolean isChao() {
        return this.state != 1 || Util.ditubianma != 3315 || (this.manstate == 0 || this.manstate == 1 || this.manstate == -1);
    }
    
    static {
        BangFight.MINSUM = 3;
    }
}
