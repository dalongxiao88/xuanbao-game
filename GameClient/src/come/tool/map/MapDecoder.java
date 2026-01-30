package come.tool.map;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.come.model.Door;
import java.util.List;
import java.io.File;

public class MapDecoder
{
    private int width;
    private int height;
    private int[][] segmentsOffset;
    private Object[][] jpegDatas;
    private String filename;
    private MyRandomAccessFile mapFile;
    private int horSegmentCount;
    private int verSegmentCount;
    public boolean isNew;
    public MaskHead maskhead;
    public MaskUnit[] maskunit;
    
    public MyRandomAccessFile getMapFile() {
        return this.mapFile;
    }
    
    public void setMapFile(MyRandomAccessFile mapFile) {
        this.mapFile = mapFile;
    }
    
    public MapDecoder(String filename) throws Exception {
        this(new File(filename));
    }
    
    public MapDecoder(File file) throws Exception {
        this.filename = file.getName();
        this.mapFile = new MyRandomAccessFile(file, "r");
    }
    
    public byte[][] loadHeader(List<Door> doors) {
        byte[][] bs = (byte[][])null;
        if (!this.isValidMapFile()) {
            throw new IllegalArgumentException("非梦幻地图格式文件!");
        }
        try {
            this.width = this.mapFile.readInt2();
            this.height = this.mapFile.readInt2();
            this.horSegmentCount = (int)Math.ceil((double)this.width / 320.0);
            this.verSegmentCount = (int)Math.ceil((double)this.height / 240.0);
            this.segmentsOffset = new int[this.horSegmentCount][this.verSegmentCount];
            this.jpegDatas = new Object[this.horSegmentCount][this.verSegmentCount];
            for (int v = 0; v < this.verSegmentCount; ++v) {
                for (int h = 0; h < this.horSegmentCount; ++h) {
                    this.segmentsOffset[h][v] = this.mapFile.readInt2();
                }
            }
            if (this.isNew) {
                this.maskhead = new MaskHead();
                this.maskhead.unknown = this.mapFile.readInt2();
                this.maskhead.maskNum = this.mapFile.readInt2();
                this.maskhead.maskList = new int[this.maskhead.maskNum];
                this.maskunit = new MaskUnit[this.maskhead.maskNum];
                for (int i = 0; i < this.maskhead.maskNum; ++i) {
                    this.maskhead.maskList[i] = this.mapFile.readInt2();
                }
                for (int i = 0; i < this.maskhead.maskNum; ++i) {
                    this.ReadMaskUint(i);
                }
            }
            bs = new byte[this.verSegmentCount * 20][this.horSegmentCount * 20];
            for (int v = 0; v < this.verSegmentCount; ++v) {
                for (int h = 0; h < this.horSegmentCount; ++h) {
                    this.getJpegData(h, v, bs);
                }
            }
            if (this.isNew) {
                for (MaskUnit mu : this.maskunit) {
                    mu.getMsk(bs);
                }
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("地图解码失败:" + e.getMessage());
        }
        return bs;
    }
    
    private void ReadMaskUint(int index) throws IOException {
        int startaddress = this.maskhead.maskList[index];
        this.maskunit[index] = new MaskUnit();
        this.mapFile.seek((long)startaddress);
        this.maskunit[index].startX = this.mapFile.readInt2();
        this.maskunit[index].startY = this.mapFile.readInt2();
        this.maskunit[index].width = this.mapFile.readInt2();
        this.maskunit[index].height = this.mapFile.readInt2();
        this.maskunit[index].maskN = this.mapFile.readInt2();
        this.maskunit[index].maskData = new byte[this.maskunit[index].maskN];
    }
    
    public void cell(int h, int v, byte[][] bs, byte[] ccles) {
        int gao = 0;
        for (int i = 0; i < ccles.length; ++i) {
            bs[v * 12 + gao][h * 16 + i % 16] = ccles[i];
            if (i % 16 == 15) {
                ++gao;
            }
        }
    }
    
    public void cell2(int h, int v, byte[][] bs, byte[] ccles) {
        int gao = 0;
        for (int i = 0; i < ccles.length; ++i) {
            bs[v * 12 + gao][h * 16 + i % 16] = (byte)(byte)((ccles[i] != 1) ? 1 : 0);
            if (i % 16 == 15) {
                ++gao;
            }
        }
    }
    
    public void getJpegData(int h, int v, byte[][] bs) {
        try {
            int len = 0;
            byte[] jpegBuf = null;
            byte[] jpegBuf2 = null;
            this.mapFile.seek((long)this.segmentsOffset[h][v]);
            if (this.isNew) {
                if (this.isJPEGData2()) {
                    len = this.mapFile.readInt2();
                    jpegBuf = new byte[len];
                    this.mapFile.readFully(jpegBuf);
                    this.jpegDatas[h][v] = jpegBuf;
                }
                if (this.isLLECData()) {
                    jpegBuf2 = this.data();
                    this.cell2(h, v, bs, jpegBuf2);
                }
            }
            else {
                if (this.isJPEGData()) {
                    len = this.mapFile.readInt2();
                    jpegBuf = new byte[len];
                    this.mapFile.readFully(jpegBuf);
                    this.jpegDatas[h][v] = jpegBuf;
                }
                if (this.isLLECData()) {
                    jpegBuf2 = this.data();
                    this.cell(h, v, bs, jpegBuf2);
                }
            }
        }
        catch (Exception e) {
            System.err.println("获取JPEG 数据块失败：" + e.getMessage());
        }
    }
    
    public byte[] getJpegData(int h, int v) {
        byte[] bs = (byte[])(byte[])this.jpegDatas[h][v];
        return bs;
    }
    
    public byte[] getJpegData2(int h, int v) {
        try {
            byte[] jpegBuf = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream(4096);
            boolean isFilled = false;
            bos.reset();
            jpegBuf = (byte[])(byte[])this.jpegDatas[h][v];
            bos.write(jpegBuf, 0, 2);
            isFilled = false;
            int p = 4;
            int start = 4;
            while (p < jpegBuf.length - 2) {
                if (!isFilled && jpegBuf[p] == -1 && jpegBuf[++p] == -38) {
                    isFilled = true;
                    jpegBuf[p + 2] = 12;
                    bos.write(jpegBuf, start, p + 10 - start);
                    bos.write(0);
                    bos.write(63);
                    bos.write(0);
                    start = p + 10;
                    p += 9;
                }
                if (isFilled && jpegBuf[p] == -1) {
                    bos.write(jpegBuf, start, p + 1 - start);
                    bos.write(0);
                    start = p + 1;
                }
                ++p;
            }
            bos.write(jpegBuf, start, jpegBuf.length - start);
            this.jpegDatas[h][v] = bos.toByteArray();
        }
        catch (Exception e) {
            System.err.println("获取JPEG 数据块失败：" + e.getMessage());
        }
        return (byte[])(byte[])this.jpegDatas[h][v];
    }
    
    private boolean isJPEGData2() {
        byte[] buf = new byte[4];
        try {
            int len = this.mapFile.read();
            this.mapFile.skipBytes(3 + len * 4);
            this.mapFile.read(buf);
            String str = new String(buf);
            return str.equals("GEPJ");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void removedata() {
        try {
            this.mapFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public byte[] data() {
        try {
            int e = this.mapFile.readInt2();
            Object jpegBuf = null;
            byte[] jpegBuf2 = new byte[e];
            this.mapFile.readFully(jpegBuf2);
            return jpegBuf2;
        }
        catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }
    
    private boolean isJPEGData() {
        byte[] buf = new byte[4];
        try {
            this.mapFile.read();
            this.mapFile.read(buf);
            String ex = new String(buf);
            return ex.equals("GEPJ");
        }
        catch (IOException var3) {
            var3.printStackTrace();
            return false;
        }
    }
    
    private boolean isLLECData() {
        byte[] buf = new byte[4];
        try {
            this.mapFile.read(buf);
            String ex = new String(buf);
            return ex.equals("LLEC");
        }
        catch (IOException var3) {
            var3.printStackTrace();
            return false;
        }
    }
    
    private boolean isValidMapFile() {
        byte[] buf = new byte[4];
        try {
            this.mapFile.read(buf);
            String ex = new String(buf);
            if (ex.equals("HHYZ")) {
                this.isNew = false;
                return true;
            }
            if (ex.endsWith("ABCD")) {
                return this.isNew = true;
            }
        }
        catch (IOException var3) {
            var3.printStackTrace();
        }
        return false;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHorSegmentCount() {
        return this.horSegmentCount;
    }
    
    public int getVerSegmentCount() {
        return this.verSegmentCount;
    }
    
    class MyRandomAccessFile extends RandomAccessFile
    {
        public MyRandomAccessFile(String name, String mode) throws FileNotFoundException {
            super(name, mode);
        }
        
        public MyRandomAccessFile(File file, String mode) throws FileNotFoundException {
            super(file, mode);
        }
        
        public int readInt2() throws IOException {
            int ch1 = this.read();
            int ch2 = this.read();
            int ch3 = this.read();
            int ch4 = this.read();
            if ((ch1 | ch2 | ch3 | ch4) < 0) {
                throw new EOFException();
            }
            return (ch1 << 0) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24);
        }
    }
}
