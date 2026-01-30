package com.tool.tcp;

public class ColorationScheme
{
    private short[][] colors;
    
    public ColorationScheme(String[] schemes) {
        this.colors = new short[3][3];
        for (int r = 0; r < schemes.length; ++r) {
            String[] colorArray = schemes[r].split(" ");
            for (int c = 0; c < colorArray.length; ++c) {
                this.colors[r][c] = Short.parseShort(colorArray[c]);
            }
        }
    }
    
    public ColorationScheme(String[] schemes, int P) {
        this.colors = new short[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.colors[i][j] = Short.parseShort(schemes[i * 3 + j + P]);
            }
        }
    }
    
    public byte[] mix(byte r, byte g, byte b) {
        int r2 = r * this.colors[0][0] + g * this.colors[0][1] + b * this.colors[0][2] >>> 8;
        int g2 = r * this.colors[1][0] + g * this.colors[1][1] + b * this.colors[1][2] >>> 8;
        int b2 = r * this.colors[2][0] + g * this.colors[2][1] + b * this.colors[2][2] >>> 8;
        r2 = Math.min(r2, 31);
        g2 = Math.min(g2, 63);
        b2 = Math.min(b2, 31);
        byte[] comps = { (byte)r2, (byte)g2, (byte)b2 };
        return comps;
    }
    
    public short mix(short color) {
        byte[] rgbs = new byte[3];
        byte r = (byte)(color >>> 11 & 0x1F);
        byte g = (byte)(color >>> 5 & 0x3F);
        byte b = (byte)(color & 0x1F);
        rgbs = this.mix(r, g, b);
        color = (short)(rgbs[0] << 11 | rgbs[1] << 5 | rgbs[2]);
        return color;
    }
}
