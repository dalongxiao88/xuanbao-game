package org.come.bean;

import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.until.ScrenceUntil;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import org.come.model.GamemapWorld;
import come.tool.map.JMap;
import org.come.model.Door;
import java.util.List;

public class Mapmodelworld
{
    private int w;
    private int h;
    private int bottom;
    private int right;
    private String mappath;
    private List<Door> doors;
    private int min_x;
    private int min_y;
    private double bili_x;
    private double bili_y;
    private JMap jMap;
    private byte[][] Nodes;
    private GamemapWorld gamemap;
    private ImgZoom zoom;
    PathPoint point;
    private int shot_x;
    private int shot_y;
    private static int LIMIT_X;
    private static int LIMIT_Y;
    private int history_x;
    private int history_y;
    
    public Mapmodelworld() {
        this.doors = new ArrayList<>();
        this.point = new PathPoint(0, 0);
    }
    
    public void MiniMap(Image image) {
        this.getZoom().middleimg(image);
        this.min_x = image.getWidth(null) - 85;
        this.min_y = image.getHeight(null) - 60;
        this.bili_x = (double)this.w / (double)this.min_x;
        this.bili_y = (double)this.h / (double)this.min_y;
    }
    
    public void Mapsize() {
        Dimension mapsize = this.jMap.getMapSize();
        this.right = mapsize.width;
        this.bottom = mapsize.height;
        this.w = this.right / 20;
        this.h = this.bottom / 20;
    }
    
    public Mapmodelworld(double map_Screen_x, double map_Screen_y, JMap jMap, GamemapWorld gamemap) {
        this.doors = new ArrayList<>();
        this.point = new PathPoint(0, 0);
        this.gamemap = gamemap;
        this.jMap = jMap;
        Dimension mapsize = jMap.getMapSize();
        this.right = mapsize.width;
        this.bottom = mapsize.height;
        this.w = this.right / 20;
        this.h = this.bottom / 20;
        this.Nodes = new byte[this.w][this.h];
    }
    
    public boolean zhezhao(int x, int y) {
        x = (x + this.shot_x - ScrenceUntil.Screen_x / 2) / 20;
        y = (y + this.shot_y - ScrenceUntil.Screen_y / 2) / 20;
        return this.jMap.getMaprules()[y][x] == 2;
    }
    
    public Door tp(int x, int y) {
        if (this.jMap.getMaprules()[y / 20][x / 20] == 3) {
            Door door = this.Seekdoor(x, y);
            return door;
        }
        return null;
    }
    
    public void drawmap(Graphics g) {
        if (this.jMap != null) {
            this.jMap.draw(g, this.shot_x - ScrenceUntil.Screen_x / 2, this.shot_y - ScrenceUntil.Screen_y / 2);
        }
    }
    
    public boolean mskpanduan(int x, int y) {
        return this.jMap.getMaprules()[y][x] == 0;
    }
    
    public Door Seekdoor(int x, int y) {
        Door door = null;
        int p = 1000;
        for (int i = 0; i < this.doors.size(); ++i) {
            int a = this.matchingdoor((Door)this.doors.get(i), x, y);
            if (a < p && a < 250) {
                p = a;
                door = (Door)this.doors.get(i);
            }
        }
        return door;
    }
    
    public int matchingdoor(Door door, int x, int y) {
        if (door == null) {
            return 1001;
        }
        int[] rect = door.getRects();
        if (rect == null) {
            return 1001;
        }
        return (Math.abs(Math.abs((rect[0] + rect[1]) / 2) - x) + Math.abs(Math.abs((rect[2] + rect[3]) / 2) - y)) / 2;
    }
    
    public PathPoint path(int x, int y) {
        if (Math.abs(x - this.shot_x) < ScrenceUntil.Screen_x / 2 && Math.abs(y - this.shot_y) < ScrenceUntil.Screen_y / 2) {
            this.point.setX(x - this.shot_x + ScrenceUntil.Screen_x / 2);
            this.point.setY(y - this.shot_y + ScrenceUntil.Screen_y / 2);
            return this.point;
        }
        return null;
    }
    
    public int getW() {
        return this.w;
    }
    
    public void setW(int w) {
        this.w = w;
    }
    
    public int getH() {
        return this.h;
    }
    
    public void setH(int h) {
        this.h = h;
    }
    
    public int getBottom() {
        return this.bottom;
    }
    
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
    
    public int getRight() {
        return this.right;
    }
    
    public void setRight(int right) {
        this.right = right;
    }
    
    public String getMappath() {
        return this.mappath;
    }
    
    public void setMappath(String mappath) {
        this.mappath = mappath;
    }
    
    public List<Door> getDoors() {
        return this.doors;
    }
    
    public void setDoors(List<Door> doors) {
        this.doors = null;
        this.doors = doors;
    }
    
    public int getMin_x() {
        return this.min_x;
    }
    
    public void setMin_x(int min_x) {
        this.min_x = min_x;
    }
    
    public int getMin_y() {
        return this.min_y;
    }
    
    public void setMin_y(int min_y) {
        this.min_y = min_y;
    }
    
    public double getBili_x() {
        return this.bili_x;
    }
    
    public void setBili_x(double bili_x) {
        this.bili_x = bili_x;
    }
    
    public double getBili_y() {
        return this.bili_y;
    }
    
    public void setBili_y(double bili_y) {
        this.bili_y = bili_y;
    }
    
    public GamemapWorld getGamemapWorld() {
        return this.gamemap;
    }
    
    public void setGamemapWorld(GamemapWorld gamemap) {
        this.gamemap = gamemap;
    }
    
    public byte[][] getNodes() {
        if (this.Nodes == null || this.Nodes.length != this.jMap.getMaprules().length || this.Nodes[0].length != this.jMap.getMaprules()[0].length) {
            this.Nodes = new byte[this.jMap.getMaprules()[0].length][this.jMap.getMaprules().length];
        }
        else {
            for (int i = 0; i < this.Nodes.length; ++i) {
                for (int k = 0; k < this.Nodes[0].length; ++k) {
                    this.Nodes[k][i] = 0;
                }
            }
        }
        return this.Nodes;
    }
    
    public void setNodes(byte[][] nodes) {
        this.Nodes = nodes;
    }
    
    public JMap getjMap() {
        if (this.jMap == null) {
            this.jMap = new JMap();
        }
        return this.jMap;
    }
    
    public void setjMap(JMap jMap) {
        this.jMap = jMap;
    }
    
    public void ShotMove(int x, int y) {
        this.ShotMove_X(x);
        this.ShotMove_Y(y);
        this.history_x = x;
        this.history_y = y;
    }
    
    public void ShotMove_X(int x) {
        if (x == this.history_x && x == this.shot_x) {
            return;
        }
        if (x != this.history_x) {
            this.setShot_x(x);
        }
    }
    
    public void ShotMove_Y(int y) {
        if (y == this.history_y && y == this.shot_y) {
            return;
        }
        if (y != this.history_y) {
            this.setShot_y(y);
        }
    }
    
    public void setShot_x(int shot_x) {
        int ban = ScrenceUntil.Screen_x / 2;
        if (Math.abs(this.shot_x - shot_x) > Mapmodelworld.LIMIT_X) {
            this.shot_x = shot_x + ((shot_x - this.shot_x < 0) ? Mapmodelworld.LIMIT_X : (-Mapmodelworld.LIMIT_X));
        }
        if (this.shot_x > this.jMap.getMapWidth() - ban) {
            this.shot_x = this.jMap.getMapWidth() - ban;
        }
        if (this.shot_x < ban) {
            this.shot_x = ban;
        }
    }
    
    public void setShot_y(int shot_y) {
        int ban = ScrenceUntil.Screen_y / 2;
        if (Math.abs(this.shot_y - shot_y) > Mapmodelworld.LIMIT_Y) {
            this.shot_y = shot_y + ((shot_y - this.shot_y < 0) ? Mapmodelworld.LIMIT_Y : (-Mapmodelworld.LIMIT_Y));
        }
        if (this.shot_y > this.jMap.getMapHeight() - ban) {
            this.shot_y = this.jMap.getMapHeight() - ban;
        }
        if (this.shot_y < ban) {
            this.shot_y = ban;
        }
    }
    
    public void ShotMiddlex(int x) {
        if (x == this.history_x && x == this.shot_x) {
            return;
        }
        this.shot_x += ((x - this.shot_x > 0) ? 1 : -1) * ((Math.abs(x - this.shot_x) > 2) ? 2 : 1);
        int banx = ScrenceUntil.Screen_x / 2;
        if (this.shot_x > this.jMap.getMapWidth() - banx) {
            this.shot_x = this.jMap.getMapWidth() - banx;
        }
        if (this.shot_x < banx) {
            this.shot_x = banx;
        }
        this.history_x = x;
    }
    
    public void ShotMiddley(int y) {
        if (y == this.history_y && y == this.shot_y) {
            return;
        }
        this.shot_y += ((y - this.shot_y > 0) ? 1 : -1) * ((Math.abs(y - this.shot_y) > 2) ? 2 : 1);
        int bany = ScrenceUntil.Screen_y / 2;
        if (this.shot_y > this.jMap.getMapHeight() - bany) {
            this.shot_y = this.jMap.getMapHeight() - bany;
        }
        if (this.shot_y < bany) {
            this.shot_y = bany;
        }
        this.history_y = y;
    }
    
    public void force(int x, int y) {
        this.shot_x = x;
        this.history_x = x;
        this.shot_y = y;
        this.history_y = y;
        int banx = ScrenceUntil.Screen_x / 2;
        if (this.shot_x > this.jMap.getMapWidth() - banx) {
            this.shot_x = this.jMap.getMapWidth() - banx;
        }
        if (this.shot_x < banx) {
            this.shot_x = banx;
        }
        int bany = ScrenceUntil.Screen_y / 2;
        if (this.shot_y > this.jMap.getMapHeight() - bany) {
            this.shot_y = this.jMap.getMapHeight() - bany;
        }
        if (this.shot_y < bany) {
            this.shot_y = bany;
        }
    }
    
    public int getShot_x() {
        return this.shot_x - ScrenceUntil.Screen_x / 2;
    }
    
    public int getShot_y() {
        return this.shot_y - ScrenceUntil.Screen_y / 2;
    }
    
    public ImgZoom getZoom() {
        if (this.zoom == null) {
            this.zoom = CutButtonImage.cuts("inkImg/background/49.png", 1, 1, false);
        }
        return this.zoom;
    }
    
    public void setZoom(ImgZoom zoom) {
        this.zoom = zoom;
    }
    
    static {
        Mapmodelworld.LIMIT_X = 0;
        Mapmodelworld.LIMIT_Y = 0;
    }
}
