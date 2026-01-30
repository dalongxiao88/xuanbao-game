package com.tool.tcp;

import com.tool.image.ManimgAttribute;
import java.awt.Graphics;

public class PartOne implements NewPart
{
    public String skin;
    private String color;
    private int act;
    private int lvl;
    private Sprite tcp;
    private NewPart flyPart;
    private NewPart flyShadowPart;
    private NewPart part;
    private Boolean isFly;
    Boolean gl;
    
    @Override
    public Sprite getTCP() {
        return this.tcp;
    }
    
    @Override
    public void setGl(Boolean gl) {
        this.gl = gl;
    }
    
    public PartOne(String skin, int act, int lvl, String color) {
        this.gl = Boolean.valueOf(false);
        this.skin = skin;
        this.act = act;
        this.lvl = lvl;
        this.color = color;
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int dir, long time) {
        if (this.flyPart != null) {
            this.flyPart.drawFly(g, x, y, dir, time, 1.0f);
        }
        if (this.flyShadowPart != null) {
            this.flyShadowPart.setGl(this.gl);
            this.flyShadowPart.draw(g, x, y + 100, dir, time, 1.0f);
        }
        if (this.tcp == null) {
            this.loadTcp();
        }
        synchronized (this) {
            if (this.tcp != null) {
                this.tcp.setisHigh1((boolean)this.gl);
                this.tcp.updateToTime(time, dir);
                this.tcp.draw(g, x, y);
            }
        }
        if (this.part != null) {
            this.part.setGl(this.gl);
            this.part.draw(g, x, y, dir, time);
        }
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int dir, long time, float alpha) {
        if (this.flyPart != null) {
            this.flyPart.drawFly(g, x, y, dir, time, alpha);
        }
        if (this.flyShadowPart != null) {
            this.flyShadowPart.setGl(this.gl);
            this.flyShadowPart.draw(g, x, y + 100, dir, time, 1.0f);
        }
        if (this.tcp == null) {
            this.loadTcp();
        }
        synchronized (this) {
            if (this.tcp != null) {
                this.tcp.setisHigh1((boolean)this.gl);
                this.tcp.updateToTime(time, dir);
                this.tcp.draw(g, x, y, alpha);
            }
        }
        if (this.part != null) {
            this.part.setGl(this.gl);
            this.part.draw(g, x, y, dir, time, alpha);
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
            if (this.tcp == null) {
                this.loadTcp();
            }
            synchronized (this) {
                if (this.tcp != null) {
                    this.tcp.updateToTime((long)(this.tcp.getTime() - 1), dir);
                    this.tcp.draw(g, x, y, alpha);
                }
            }
            return;
        }
    }
    
    @Override
    public void drawBattle(Graphics g, int x, int y, int dir, long time, float alpha) {
        if (this.tcp == null) {
            this.loadTcp();
        }
        synchronized (this) {
            if (this.tcp != null) {
                this.tcp.setisHigh1((boolean)this.gl);
                this.tcp.updateToTime(time, SpriteFactory.changdir(dir, this.tcp.getAnimationCount()));
                this.tcp.draw(g, x, y, alpha);
            }
        }
        if (this.part != null) {
            this.part.setGl(this.gl);
            this.part.drawBattle(g, x, y, dir, time, alpha);
        }
    }
    
    @Override
    public void drawFly(Graphics g, int x, int y, int dir, long time, float alpha) {
        if (this.tcp == null) {
            this.loadTcp();
        }
        synchronized (this) {
            if (this.tcp != null) {
                this.tcp.setisHigh1((boolean)this.gl);
                this.tcp.updateToTime(time, dir);
                this.tcp.draw(g, x, y, alpha);
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
        return rSkin.equals(this.skin);
    }
    
    @Override
    public boolean contains(int x, int y) {
        if (this.lvl == 1) {
            synchronized (this) {
                if (this.tcp != null) {
                    return this.tcp.contains(x, y);
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
            this.tcp = null;
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
            if (this.tcp != null) {
                return this.tcp.getTime();
            }
        }
        this.loadTcp();
        return 1200;
    }
    
    @Override
    public void loadTcp() {
        synchronized (this) {
            if (this.act == -2) {
                this.tcp = SpriteFactory.Prepare(GetTcpPath.getMouseTcp(this.skin));
            }
            else if (this.act == -1) {
                this.tcp = SpriteFactory.Prepare(GetTcpPath.getSkillTcp(this.skin));
            }
            else if (this.flyPart != null) {
                this.tcp = SpriteFactory.Prepare(GetTcpPath.getRoleTcp(this.skin, 2, this.color));
            }
            else {
                this.tcp = SpriteFactory.Prepare(GetTcpPath.getRoleTcp(this.skin, this.act, this.color));
            }
        }
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
        if (this.act >= 0 && this.act != Act) {
            this.act = Act;
            synchronized (this) {
                this.tcp = null;
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
    
    @Override
    public NewPart setPart(int lvl, String skin) {
        if (this.lvl == lvl) {
            this.skin = skin;
            synchronized (this) {
                this.tcp = null;
            }
            return this;
        }
        else {
            if (this.part != null) {
                return this.part.setPart(lvl, skin);
            }
            return this;
        }
    }
    
    @Override
    public NewPart setPart(int lvl, long skin, HHOne[] ones) {
        if (this.lvl == lvl) {
            NewPart newPart = new PartTwo(skin, ones, this.act, lvl, this.color);
            newPart.setPart(this.part);
            return newPart;
        }
        if (this.part != null) {
            return this.part.setPart(lvl, skin, ones);
        }
        return this;
    }
    
    @Override
    public int getPy() {
        if (this.lvl == 1) {
            synchronized (this) {
                if (this.tcp != null) {
                    int py = this.tcp.getHightMax();
                    py = ((py > 100) ? 100 : py);
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
        NewPart newPart = new PartOne(this.skin, this.act, this.lvl, this.color);
        if (this.part != null) {
            newPart.setPart(this.part.clonePart());
        }
        return newPart;
    }
    
    @Override
    public int getAnimationCount() {
        if (this.lvl == 1) {
            synchronized (this) {
                if (this.tcp != null) {
                    return this.tcp.getAnimationCount();
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
                this.flyShadowPart = null;
                this.isFly = null;
            }
            else {
                this.flyPart = SpriteFactory.createPart(skin, act, 1, null);
                this.flyShadowPart = SpriteFactory.createPart(skin, act, 1, null);
                this.isFly = isFly;
            }
            return;
        }
        else {
            if (this.part != null) {
                this.part.setFly(skin, act, isFly);
                this.part.setFlyShadow(skin, act);
            }
            return;
        }
    }
    
    @Override
    public void setFlyShadow(String skin, int act) {
        if (this.lvl == 1) {
            this.flyShadowPart = SpriteFactory.createPart(skin, act, 1, null);
            return;
        }
        if (this.part != null) {
            this.part.setFlyShadow(skin, act);
        }
    }
    
    @Override
    public void drawFlyShadow(Graphics g, int x, int y, int dir, long time, float alpha) {
        if (this.tcp == null) {
            this.loadTcp();
        }
        synchronized (this) {
            if (this.tcp != null) {
                this.tcp.setisHigh1((boolean)this.gl);
                this.tcp.updateToTime(time, dir);
                if (ManimgAttribute.lx == 0 && ManimgAttribute.yys > 0) {
                    this.tcp.draw(g, x, y + ManimgAttribute.yys, alpha);
                }
                else {
                    this.tcp.draw(g, x, y + 140, alpha);
                }
            }
        }
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
}
