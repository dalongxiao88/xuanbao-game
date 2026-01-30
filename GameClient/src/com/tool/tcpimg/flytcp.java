package com.tool.tcpimg;

import org.come.bean.PathPoint;
import com.tool.tcp.Sprite;
import org.come.until.Util;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import java.awt.Graphics;
import come.tool.Fighting.sidian;

public class flytcp
{
    private String skin;
    private int jg;
    private sidian[] sidians;
    
    public flytcp(String skin) {
        this.skin = skin;
        this.sidians = new sidian[5];
        for (int i = 0; i < this.sidians.length; ++i) {
            this.sidians[i] = new sidian(-1, 0, 0, 0);
        }
        this.jg = 30;
    }
    
    public void draw(Graphics g, long time) {
        time = (long)((double)time / 5.0);
        Sprite sprite = null;
        for (int i = 0; i < this.sidians.length; ++i) {
            if (i == 0) {
                sprite = SpriteFactory.Prepare(GetTcpPath.getflytcp(this.skin));
            }
            if (this.sidians[i].getX() != -1) {
                this.sidians[i].setX((int)((long)this.sidians[i].getX() + time));
                if (sprite != null) {
                    if (this.sidians[i].getX() <= sprite.getTime()) {
                        PathPoint pathPoint = Util.mapmodel.path(this.sidians[i].getZ(), this.sidians[i].getW());
                        if (pathPoint != null) {
                            sprite.updateToTime((long)this.sidians[i].getX(), this.sidians[i].getY());
                            sprite.drawfly(g, pathPoint.getX(), pathPoint.getY());
                        }
                    }
                    else {
                        this.sidians[i].setX(-1);
                    }
                }
            }
        }
    }
    
    public void addfly(int x, int y, int dir) {
        if (Util.mapmodel.path(x, y) == null) {
            return;
        }
        int i = 0;
        while (i < this.sidians.length) {
            if (this.sidians[i].getX() == -1) {
                --this.jg;
                if (this.jg < 0) {
                    this.jg = 30;
                    this.sidians[i].setsidian(0, dir, x, y);
                }
                return;
            }
            else {
                ++i;
            }
        }
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
}
