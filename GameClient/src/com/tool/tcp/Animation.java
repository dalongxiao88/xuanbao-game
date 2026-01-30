package com.tool.tcp;

import java.util.Iterator;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Animation
{
    private static final long serialVersionUID = 1L;
    private Object UPDATE_LOCK;
    private Vector<Frame> frames;
    private int index;
    private Frame currFrame;
    private long animTime;
    private long totalDuration;
    private int frameCount;
    
    public Animation() {
        this.UPDATE_LOCK = new Object();
        this.frames = new Vector<>();
    }
    
    public Animation(Vector<Frame> frames) {
        this.UPDATE_LOCK = new Object();
        this.frames = frames;
        this.currFrame = (Frame)frames.get(0);
        this.frameCount = frames.size();
        this.totalDuration = (long)(100 * this.frameCount);
    }
    
    public Animation(Animation anim) {
        this.UPDATE_LOCK = new Object();
        this.totalDuration = anim.totalDuration;
        this.frames = anim.frames;
        this.currFrame = anim.currFrame;
        this.frameCount = anim.getFrames().size();
    }
    
    public void addFrame(Frame frame) {
        synchronized (this.UPDATE_LOCK) {
            this.totalDuration += 100L;
            this.frames.add(frame);
            ++this.frameCount;
            this.currFrame = frame;
        }
    }
    
    public void addFrame(BufferedImage image, int centerX, int centerY) {
        synchronized (this.UPDATE_LOCK) {
            this.totalDuration += 100L;
            Frame frame = new Frame(image, centerX, centerY);
            this.frames.add(frame);
            ++this.frameCount;
            this.currFrame = frame;
        }
    }
    
    public void update(long elapsedTime) {
        this.updateToTime(this.animTime += elapsedTime);
    }
    
    public void updateToTime(long playTime) {
        synchronized (this.UPDATE_LOCK) {
            if (this.frameCount > 1) {
                this.animTime = playTime;
                this.index = (int)(this.animTime / 240L);
                this.index %= this.frameCount;
                this.currFrame = (Frame)this.frames.get(this.index);
            }
            else if (this.frameCount > 0) {
                this.currFrame = (Frame)this.frames.get(0);
            }
            this.UPDATE_LOCK.notifyAll();
        }
    }
    
    public Image getImage() {
        synchronized (this.UPDATE_LOCK) {
            return (this.currFrame == null) ? null : this.currFrame.getImage();
        }
    }
    
    public void reset() {
        synchronized (this.UPDATE_LOCK) {
            this.animTime = 0L;
            this.index = 0;
            this.currFrame = ((this.frames.size() > 0) ? ((Frame)this.frames.get(0)) : null);
        }
    }
    
    public int getWidth() {
        synchronized (this.UPDATE_LOCK) {
            return (this.currFrame == null) ? 0 : this.currFrame.getWidth();
        }
    }
    
    public int getHeight() {
        synchronized (this.UPDATE_LOCK) {
            return (this.currFrame == null) ? 0 : this.currFrame.getHeight();
        }
    }
    
    public int getRefPixelX() {
        synchronized (this.UPDATE_LOCK) {
            return (this.currFrame == null) ? 0 : this.currFrame.getRefPixelX();
        }
    }
    
    public int getRefPixelY() {
        synchronized (this.UPDATE_LOCK) {
            return (this.currFrame == null) ? 0 : this.currFrame.getRefPixelY();
        }
    }
    
    public Vector<Frame> getFrames() {
        return this.frames;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(int index) {
        this.index = index;
        this.currFrame = (Frame)this.frames.get(index);
    }
    
    public long getTotalDuration() {
        return this.totalDuration;
    }
    
    public Animation clone() {
        return new Animation(this);
    }
    
    public Frame getCurrFrame() {
        return this.currFrame;
    }
    
    public void setCurrFrame(Frame currFrame) {
        this.currFrame = currFrame;
    }
    
    public long getAnimTime() {
        return this.animTime;
    }
    
    public void setAnimTime(long animTime) {
        this.animTime = animTime;
    }
    
    public void dispose() {
        for (Frame f : this.frames) {
            f.dispose();
        }
        this.frames.clear();
    }
    
    public boolean contains(int x, int y) {
        return this.currFrame.contains(x, y);
    }
    
    public Object getUPDATE_LOCK() {
        return this.UPDATE_LOCK;
    }
    
    public void setUPDATE_LOCK(Object uPDATE_LOCK) {
        this.UPDATE_LOCK = uPDATE_LOCK;
    }
    
    public static long getSerialversionuid() {
        return 1L;
    }
    
    public void setFrames(Vector<Frame> frames) {
        this.frames = frames;
    }
    
    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }
}
