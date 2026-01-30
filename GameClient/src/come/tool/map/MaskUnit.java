package come.tool.map;

public class MaskUnit
{
    public int startX;
    public int startY;
    public int width;
    public int height;
    public int maskN;
    public byte[] maskData;
    
    public int getStartX() {
        return this.startX;
    }
    
    public void setStartX(int startX) {
        this.startX = startX;
    }
    
    public int getStartY() {
        return this.startY;
    }
    
    public void setStartY(int startY) {
        this.startY = startY;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getMaskN() {
        return this.maskN;
    }
    
    public void setMaskN(int maskN) {
        this.maskN = maskN;
    }
    
    public byte[] getMaskData() {
        return this.maskData;
    }
    
    public void setMaskData(byte[] maskData) {
        this.maskData = maskData;
    }
    
    public void getMsk(byte[][] msk) {
        for (int x = this.startX / 20; x < (this.startX + this.width) / 20; ++x) {
            for (int y = this.startY / 20; y < (this.startY + this.height) / 20; ++y) {
                if (msk[y][x] != 0) {
                    msk[y][x] = 2;
                }
            }
        }
    }
}
