package com.tool.tcp;

import java.awt.Graphics;
import org.apache.commons.lang.StringUtils;

public class PartTwo implements NewPart
{
    private long skin;
    private HHOne[] ones;
    private long[] ls;
    private Sprite[] tcps;
    private int act;
    private int lvl;
    private long colorId;
    private NewPart flyPart;
    private NewPart flyShadowPart;
    private NewPart part;
    private Boolean isFly;
    Boolean gl;
    private Sprite tcps11;
    private String color;
    
    @Override
    public void setGl(Boolean gl) {
        this.gl = gl;
        if ((boolean)gl) {
            this.tcps11 = null;
            if (StringUtils.isBlank(this.color)) {
                this.color += "#gl";
            }
            else if (StringUtils.isNotBlank(this.color) && !this.color.contains("#gl")) {
                this.color += "#gl";
            }
        }
        else {
            this.tcps11 = null;
            if (StringUtils.isNotBlank(this.color) && this.color.contains("#")) {
                String[] v = this.color.split("#");
                this.color = v[0];
            }
            else if (!StringUtils.isNotBlank(this.color)) {
                this.color = "";
            }
        }
    }
    
    @Override
    public Sprite getTCP() {
        return null;
    }
    
    public PartTwo(long skin, HHOne[] ones, int act, int lvl, String color) {
        this.gl = Boolean.valueOf(false);
        this.skin = skin;
        this.ones = ones;
        this.ls = ones[0].getLs();
        this.act = act;
        this.lvl = lvl;
        if (color != null && !color.equals("")) {
            try {
                this.color = color;
                this.colorId = Long.parseLong(color);
            }
            catch (Exception ex) {}
        }
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int dir, long time) {
        synchronized (this) {
            if (this.tcps == null) {
                this.resetHH();
            }
            if (this.flyPart != null) {
                this.flyPart.setGl(this.gl);
                this.flyPart.drawFly(g, x, y, dir, time, 1.0f);
                if (this.flyShadowPart != null) {
                    this.flyShadowPart.setGl(this.gl);
                    this.flyShadowPart.draw(g, x, y+100, dir, time, 1.0f);
                }
            }
            else {
                SpriteFactory.shadow.draw(g, x, y);
            }
            for (int i = 0; i < this.tcps.length; ++i) {
                if (this.tcps[i] == null) {
                    this.loadTcp(i);
                }
                if (this.tcps[i] != null) {
                    (this.tcps11 = this.tcps[i]).setisHigh((boolean)this.gl);
                    this.tcps[i].updateToTime(time, dir);
                    this.tcps[i].draw(g, x, y);
                }
            }
        }
        if (this.part != null) {
            this.part.setGl(this.gl);
            if (this.flyPart != null) {
                this.part.setAct(2);
            }
            this.part.draw(g, x, y, dir, time);
        }
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int dir, long time, float alpha) {
        synchronized (this) {
            if (this.flyPart != null) {
                this.flyPart.drawFly(g, x, y, dir, time, alpha);
                this.flyPart.setGl(this.gl);
                if (this.flyShadowPart != null) {
                    this.flyShadowPart.setGl(this.gl);
                    this.flyShadowPart.drawFlyShadow(g, x, y+100, dir, time, alpha);
                }
            }
            else {
                SpriteFactory.shadow.draw(g, x, y);
            }
            if (this.tcps == null) {
                this.resetHH();
            }
            for (int i = 0; i < this.tcps.length; ++i) {
                if (this.tcps[i] == null) {
                    this.loadTcp(i);
                }
                if (this.tcps[i] != null) {
                    (this.tcps11 = this.tcps[i]).setisHigh((boolean)this.gl);
                    this.tcps[i].updateToTime(time, dir);
                    this.tcps[i].draw(g, x, y, alpha);
                }
            }
        }
        if (this.part != null) {
            this.part.setGl(this.gl);
            this.part.draw(g, x, y, dir, time);
        }
    }
    
    @Override
    public void drawEnd(Graphics g, int x, int y, int dir, float alpha) {
        if (this.lvl != 1) {
            if (this.part != null) {
                this.part.drawEnd(g, x, y, dir, alpha);
            }
            return;
        }
        else {
            synchronized (this) {
                if (this.tcps == null) {
                    this.resetHH();
                }
                SpriteFactory.shadow.draw(g, x, y);
                for (int i = 0; i < this.tcps.length; ++i) {
                    if (this.tcps[i] == null) {
                        this.loadTcp(i);
                    }
                    if (this.tcps[i] != null) {
                        (this.tcps11 = this.tcps[i]).setisHigh((boolean)this.gl);
                        this.tcps[i].updateToTime((long)(this.tcps[i].getTime() - 1), dir);
                        this.tcps[i].draw(g, x, y, alpha);
                    }
                }
            }
            return;
        }
    }
    
    @Override
    public void drawBattle(Graphics g, int x, int y, int dir, long time, float alpha) {
        synchronized (this) {
            if (this.tcps == null) {
                this.resetHH();
            }
            SpriteFactory.shadow.draw(g, x, y);
            for (int i = 0; i < this.tcps.length; ++i) {
                if (this.tcps[i] == null) {
                    this.loadTcp(i);
                }
                if (this.tcps[i] != null) {
                    (this.tcps11 = this.tcps[i]).setisHigh((boolean)this.gl);
                    this.tcps[i].updateToTime(time, SpriteFactory.changdir(dir, this.tcps[i].getAnimationCount()));
                    this.tcps[i].draw(g, x, y, alpha);
                }
            }
        }
        if (this.part != null) {
            this.part.setGl(this.gl);
            this.part.drawBattle(g, x, y, dir, time, alpha);
        }
    }
    
    @Override
    public void drawFly(Graphics g, int x, int y, int dir, long time, float alpha) {
        synchronized (this) {
            if (this.tcps == null) {
                this.resetHH();
            }
            for (int i = 0; i < this.tcps.length; ++i) {
                if (this.tcps[i] == null) {
                    this.loadTcp(i);
                }
                if (this.tcps[i] != null) {
                    (this.tcps11 = this.tcps[i]).setisHigh((boolean)this.gl);
                    this.tcps[i].updateToTime(time, dir);
                    this.tcps[i].draw(g, x, y);
                }
            }
        }
    }
    
    @Override
    public NewPart addPart(NewPart newPart) {
        if (newPart == null) {
            return this;
        }
        if (newPart.getLvl() < this.lvl) {
            newPart.setPart(this);
            return newPart;
        }
        if (this.part == null) {
            this.part = newPart;
            return this;
        }
        this.part = this.part.addPart(newPart);
        return this;
    }
    
    @Override
    public NewPart removePart(String rSkin) {
        if (this.isD(rSkin)) {
            return this.part;
        }
        if (this.part != null) {
            this.part = this.part.removePart(rSkin);
        }
        return this;
    }
    
    private boolean isD(String rSkin) {
        return rSkin.equals(this.skin + "");
    }
    
    @Override
    public boolean contains(int x, int y) {
        if (this.lvl == 1) {
            synchronized (this) {
                if (this.tcps != null) {
                    for (int i = 0; i < this.tcps.length; ++i) {
                        if (this.tcps[i] != null) {
                            (this.tcps11 = this.tcps[i]).setisHigh((boolean)this.gl);
                            boolean is = this.tcps[i].contains(x, y);
                            if (is) {
                                return is;
                            }
                        }
                    }
                }
            }
        }
        else if (this.part != null) {
            return this.part.contains(x, y);
        }
        return false;
    }
    
    @Override
    public void recycle() {
        synchronized (this) {
            if (this.tcps != null) {
                for (int i = 0; i < this.tcps.length; ++i) {
                    this.tcps[i] = null;
                }
            }
        }
        if (this.part != null) {
            this.part.recycle();
        }
    }
    
    @Override
    public int getTime() {
        if (this.lvl != 1 && this.part != null) {
            return this.part.getTime();
        }
        synchronized (this) {
            if (this.tcps == null) {
                this.resetHH();
            }
            if (this.tcps.length > 1) {
                if (this.tcps[1] != null) {
                    return this.tcps[1].getTime();
                }
                this.loadTcp(1);
            }
            else {
                if (this.tcps[0] != null) {
                    return this.tcps[0].getTime();
                }
                this.loadTcp(0);
            }
        }
        return 1200;
    }
    
    @Override
    public void loadTcp() {
    }
    
    public void loadTcp(int i) {
        this.tcps[i] = SpriteFactory.Prepare(this.colorId << 40 | this.ls[i], this.act);
    }
    
    @Override
    public int getAct() {
        if (this.lvl != 1 && this.part != null) {
            return this.part.getAct();
        }
        return this.act;
    }
    
    @Override
    public void setAct(int Act) {
        if (this.act != Act) {
            this.act = Act;
            synchronized (this) {
                this.resetHH();
            }
        }
        if (this.part != null) {
            this.part.setAct(Act);
        }
    }
    
    @Override
    public int getLvl() {
        return this.lvl;
    }
    
    @Override
    public NewPart getPart() {
        return this.part;
    }
    
    @Override
    public void setPart(NewPart part) {
        this.part = part;
    }
    
    @Override
    public void clearGwPart(NewPart part) {
        if (this.getPart() instanceof PartOne) {
            PartOne part2 = (PartOne)this.getPart();
            if (part2.skin.contains("tx")) {
                if (part != null) {
                    part.setPart(null);
                }
                else if (this.part != null) {
                    this.setPart(null);
                }
            }
            else if (part2.getPart() != null) {
                part2.clearGwPart(part2);
            }
        }
    }
    
    public void resetHH() {
        this.ls = this.ones[0].getLs();
        if (this.flyPart != null) {
            this.flyPart.setAct(this.act);
        }
        for (int i = 0; i < this.ones.length; ++i) {
            if (this.flyPart != null && this.isFly != null && (boolean)this.isFly) {
                if (this.ones[i].getAct() == 2) {
                    this.ls = this.ones[i].getLs();
                    break;
                }
            }
            else if (this.ones[i].getAct() == this.act) {
                this.ls = this.ones[i].getLs();
                break;
            }
        }
        if (this.tcps == null || this.tcps.length != this.ls.length) {
            this.tcps = new Sprite[this.ls.length];
        }
        else {
            for (int i = 0; i < this.tcps.length; ++i) {
                this.tcps[i] = null;
            }
        }
    }
    
    @Override
    public NewPart setPart(int lvl, String skin) {
        if (this.lvl == lvl) {
            NewPart newPart = new PartOne(skin, this.act, lvl, this.colorId + "");
            newPart.setPart(this.part);
            return newPart;
        }
        if (this.part != null) {
            return this.part.setPart(lvl, skin);
        }
        return this;
    }
    
    @Override
    public NewPart setPart(int lvl, long skin, HHOne[] ones) {
        if (this.lvl == lvl) {
            if (this.skin == skin) {
                return this;
            }
            synchronized (this) {
                this.skin = skin;
                this.ones = ones;
                this.resetHH();
            }
            return this;
        }
        else {
            if (this.part != null) {
                return this.part.setPart(lvl, skin, ones);
            }
            return this;
        }
    }
    
    @Override
    public int getPy() {
        if (this.lvl == 1) {
            synchronized (this) {
                if (this.tcps != null) {
                    int py = 0;
                    for (int i = 0; i < this.tcps.length; ++i) {
                        if (this.tcps[i] != null && this.tcps[i].getHightMax() > py) {
                            py = this.tcps[i].getHightMax();
                        }
                    }
                    if (py > 150) {
                        py = 150;
                    }
                    return py;
                }
            }
        }
        else if (this.part != null) {
            return this.part.getPy();
        }
        return -1;
    }
    
    @Override
    public NewPart clonePart() {
        NewPart newPart = new PartTwo(this.skin, this.ones, this.act, this.lvl, this.colorId + "");
        if (this.part != null) {
            newPart.setPart(this.part.clonePart());
        }
        return newPart;
    }
    
    @Override
    public int getAnimationCount() {
        if (this.lvl == 1) {
            synchronized (this) {
                if (this.tcps != null) {
                    for (int i = 0; i < this.tcps.length; ++i) {
                        if (this.tcps[i] != null) {
                            (this.tcps11 = this.tcps[i]).setisHigh((boolean)this.gl);
                            return this.tcps[i].getAnimationCount();
                        }
                    }
                }
            }
        }
        else if (this.part != null) {
            return this.part.getAnimationCount();
        }
        return 2;
    }
    
    @Override
    public void setFly(String skin, int act, Boolean isFly) {
        if (this.lvl == 1) {
            if (skin == null) {
                this.flyPart = null;
            }
            else {
                this.flyPart = SpriteFactory.createPart(skin, act, 1, null);
                this.isFly = isFly;
            }
            return;
        }
        else {
            if (this.part != null) {
                this.part.setFly(skin, act, isFly);
            }
            return;
        }
    }
    
    @Override
    public void setFlyShadow(String skin, int act) {
        if (this.lvl == 1) {
            if (skin == null) {
                this.flyShadowPart = null;
            }
            else {
                this.flyShadowPart = SpriteFactory.createPart(skin, act, 1, null);
            }
            return;
        }
        else {
            if (this.part != null) {
                this.part.setFlyShadow(skin, act);
            }
            return;
        }
    }
    
    @Override
    public void drawFlyShadow(Graphics g, int x, int y, int dir, long time, float alpha) {
        for (int i = 0; i < this.tcps.length; ++i) {
            if (this.tcps[i] == null) {
                this.loadTcp(i);
            }
            if (this.tcps[i] != null) {
                (this.tcps11 = this.tcps[i]).setisHigh((boolean)this.gl);
                this.tcps[i].updateToTime(time, dir);
                this.tcps[i].draw(g, x, y);
            }
        }
    }
}
