package com.tool.tcp;

import java.awt.image.BufferedImage;
import java.util.Vector;
import java.io.IOException;
import java.io.ByteArrayInputStream;

public class SpriteHead extends ByteArrayInputStream
{
    private short[] palette;
    
    public SpriteHead(byte[] buf) {
        super(buf);
    }
    
    public Sprite init(String value, boolean is, int version) throws IOException {
        short headerSize = this.readUnsignedShort();
        short animCount = this.readUnsignedShort();
        short frameCount = this.readUnsignedShort();
        short width = this.readUnsignedShort();
        short height = this.readUnsignedShort();
        short refPixelX = this.readUnsignedShort();
        short refPixelY = this.readUnsignedShort();
        int len = headerSize - 12;
        if (len < 0) {
            throw new IllegalStateException("帧延时信息错误: " + len);
        }
        if (len != 0) {
            this.seek((int)(this.getPosition() + (long)len));
        }
        headerSize += 2;
        this.seek((int)headerSize);
        this.palette = new short[256];
        for (int i = 0; i < 256; ++i) {
            this.palette[i] = this.readUnsignedShort();
        }
        if (value != null && value.length() != 0) {
            SpriteFactory.assignColor(this.palette, value);
        }
        this.seek(headerSize + 512);
        Frame[] frames = null;
        if (is && animCount == 4) {
            frames = new Frame[2 * frameCount];
        }
        else {
            frames = new Frame[animCount * frameCount];
        }
        for (int j = 0; j < frames.length; ++j) {
            if (is && animCount == 4 && j == frameCount) {
                this.seek((int)(this.getPosition() + (long)(frameCount * 4)));
            }
            int pos = this.readInt();
            if (pos != 0) {
                pos += headerSize;
            }
            frames[j] = new Frame(pos);
        }
        return new Sprite(frames, (is && animCount == 4) ? 2 : animCount, (int)frameCount, this, (int)refPixelX, (int)refPixelY, Boolean.valueOf("gl".equals(value)));
    }
    
    public Animation initTwo() throws IOException {
        short headerSize = this.readUnsignedShort();
        short animCount = this.readUnsignedShort();
        short frameCount = this.readUnsignedShort();
        short width = this.readUnsignedShort();
        short height = this.readUnsignedShort();
        short refPixelX = this.readUnsignedShort();
        short refPixelY = this.readUnsignedShort();
        int len = headerSize - 12;
        if (len < 0) {
            throw new IllegalStateException("帧延时信息错误: " + len);
        }
        if (len != 0) {
            this.seek((int)(this.getPosition() + (long)len));
        }
        headerSize += 2;
        this.seek((int)headerSize);
        this.palette = new short[256];
        for (int i = 0; i < 256; ++i) {
            this.palette[i] = this.readUnsignedShort();
        }
        this.seek(headerSize + 512);
        Vector<Frame> frames = new Vector<>();
        for (int j = 0; j < frameCount; ++j) {
            int pos = this.readInt();
            if (pos != 0) {
                pos += headerSize;
            }
            Frame frame = new Frame(pos);
            frames.add(frame);
        }
        for (int j = 0; j < frameCount; ++j) {
            BufferedImage image = SpriteFactory.createDrawTwo(this, (Frame)frames.get(j), (int)frameCount, (int)refPixelX, (int)refPixelY, (int)width, (int)height);
            ((Frame)frames.get(j)).setImage(image);
        }
        this.remve();
        return new Animation(frames);
    }
    
    public void seek(int pos) {
        if (pos >= 0 && pos <= this.count) {
            this.pos = pos;
            return;
        }
        throw new IndexOutOfBoundsException(pos + ":" + this.count);
    }
    
    public long getPosition() {
        return (long)this.pos;
    }
    
    public long readLong() throws IOException {
        int ch1 = this.read();
        int ch2 = this.read();
        int ch3 = this.read();
        int ch4 = this.read();
        long ch5 = (long)this.read();
        long ch6 = (long)this.read();
        long ch7 = (long)this.read();
        long ch8 = (long)this.read();
        return (long)(ch1 + (ch2 << 8) + (ch3 << 16) + (ch4 << 24)) + (ch5 << 32) + (ch6 << 40) + (ch7 << 48) + (ch8 << 56);
    }
    
    public int readInt() throws IOException {
        int ch1 = this.read();
        int ch2 = this.read();
        int ch3 = this.read();
        int ch4 = this.read();
        return ch1 + (ch2 << 8) + (ch3 << 16) + (ch4 << 24);
    }
    
    public short readUnsignedShort() throws IOException {
        int ch1 = this.read();
        int ch2 = this.read();
        return (short)((ch2 << 8) + ch1);
    }
    
    public boolean readFully(byte[] buf) throws IOException {
        this.read(buf);
        return false;
    }
    
    public short[] getPalette() {
        return this.palette;
    }
    
    public void remve() {
        try {
            this.close();
            this.buf = null;
            this.count = 0;
        }
        catch (Exception ex) {}
    }
}
