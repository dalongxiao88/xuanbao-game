package com.tool.Stall;

import com.tool.tcp.Sprite;
import org.come.bean.PathPoint;
import com.tool.image.ImageMixDeal;
import com.tool.tcp.SpriteFactory;
import java.awt.Color;
import org.come.until.UserStallUntil;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.come.until.Util;
import java.awt.Graphics;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import org.come.bean.ImgZoom;

public class StallBean
{
    public static int PREPARE;
    public static int NO;
    public static int MANAGE;
    public static int OFF;
    private static int size;
    private static ImgZoom imgZoom;
    private static ImageIcon imageIcon1;
    public static String path;
    private int id;
    private int mapid;
    private String role;
    private BigDecimal roleid;
    private String stall;
    private int state;
    private int x;
    private int y;
    private boolean isPurchase;
    
    public StallBean() {
        this.stall = "商店";
    }
    
    public StallBean(int id) {
        this.stall = "商店";
        this.id = id;
    }
    
    public StallBean(Stall stall) {
        this.stall = "商店";
        this.id = stall.getId();
        this.mapid = stall.getMapid();
        this.role = stall.getRole();
        this.roleid = stall.getRoleid();
        this.stall = stall.getStall();
        this.state = stall.getState();
    }
    
    public void draw(Graphics g) {
        PathPoint point = Util.mapmodel.path(this.x, this.y);
        if (point != null) {
            g = g.create();
            g.translate(point.getX(), point.getY());
            if (StallBean.imgZoom == null) {
                if (MyIsif.getStyle().equals("水墨")) {
                    StallBean.imgZoom = CutButtonImage.cuts("inkImg/background/S148.png", 7, 9, true);
                }
                else {
                    StallBean.imgZoom = CutButtonImage.cuts("img/xy2uiimg/92_png.xy2uiimg.boothtitle.png", 7, 9, true);
                }
            }
            StallBean.size = g.getFontMetrics().stringWidth(this.stall);
            if (StallBean.size < 96) {
                StallBean.imgZoom.setMiddlew(96);
                StallBean.size = 48 - StallBean.size / 2 + 10;
            }
            else {
                StallBean.imgZoom.setMiddlew(StallBean.size);
                StallBean.size = 8;
            }
            StallBean.imgZoom.setMiddleh(g.getFontMetrics().getHeight() - 8);
            StallBean.imgZoom.draw(g);
            if (this.isPurchase) {
                if (StallBean.imageIcon1 == null) {
                    if (MyIsif.getStyle().equals("水墨")) {
                        StallBean.imageIcon1 = CutButtonImage.getImage("inkImg/background/S343.png", 22, 28);
                    }
                    else {
                        StallBean.imageIcon1 = CutButtonImage.getImage("inkImg/background/S343.png", 22, 28);
                    }
                }
                g.drawImage(StallBean.imageIcon1.getImage(), StallBean.imgZoom.getEdgew() * 2 + StallBean.imgZoom.getMiddlew(), -2, null);
            }
            if (UserStallUntil.isFollow(Integer.valueOf(this.id))) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(new Color(199, 174, 92));
            }
            g.drawString(this.stall, StallBean.size, 18);
            if (this.state == StallBean.MANAGE) {
                Sprite role = SpriteFactory.Prepare(StallBean.path);
                if (role != null) {
                    role.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                    role.draw(g, 55, 125);
                }
            }
            g.dispose();
        }
    }
    
    public boolean isDJ(int dx, int dy) {
        dx -= this.x;
        dy -= this.y;
        int size = 0;
        if (this.isPurchase && StallBean.imageIcon1 != null) {
            size = StallBean.imageIcon1.getIconWidth();
        }
        return StallBean.imgZoom != null && dx >= 0 && dx < StallBean.imgZoom.getEdgew() * 2 + StallBean.imgZoom.getMiddlew() + size && dy >= 0 && dy < StallBean.imgZoom.getEdgeh() * 2 + StallBean.imgZoom.getMiddleh() + size;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getMapid() {
        return this.mapid;
    }
    
    public void setMapid(int mapid) {
        this.mapid = mapid;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getStall() {
        return this.stall;
    }
    
    public void setStall(String stall) {
        this.stall = stall;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
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
    
    public boolean isPurchase() {
        return this.isPurchase;
    }
    
    public void setPurchase(boolean purchase) {
        this.isPurchase = purchase;
    }
    
    static {
        StallBean.PREPARE = 0;
        StallBean.NO = 1;
        StallBean.MANAGE = 2;
        StallBean.OFF = 3;
        StallBean.size = 0;
        StallBean.path = "skin/300040/stand.tcp";
    }
}
