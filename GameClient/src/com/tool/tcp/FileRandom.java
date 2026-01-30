package com.tool.tcp;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileRandom
{
    public RandomAccessFile randomFile;
    private int use;
    
    public FileRandom(String fileName) {
        try {
            this.randomFile = new RandomAccessFile(fileName, "r");
        }
        catch (Exception ex) {}
    }
    
    public void read(long pos, byte[] bs) throws IOException {
        synchronized (this) {
            this.use = 0;
            this.randomFile.seek(pos);
            this.randomFile.read(bs);
        }
    }
    
    public boolean isEnd() {
        synchronized (this) {
            if (this.use++ > 7) {
                try {
                    this.randomFile.close();
                    this.randomFile = null;
                }
                catch (Exception var3) {
                    var3.printStackTrace();
                }
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    public int getUse() {
        return this.use;
    }
    
    public void setUse(int use) {
        this.use = use;
    }
}
