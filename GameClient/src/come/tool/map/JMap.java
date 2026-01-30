package come.tool.map;

import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Dimension;
import org.come.Jpanel.GameJpanel;
import org.come.model.Door;
import java.util.List;
import java.util.Iterator;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import java.util.Vector;
import java.util.LinkedList;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class JMap extends JComponent
{
    private static final long serialVersionUID = 1L;
    private MapDecoder decoder;
    private Rectangle maxVisibleRect;
    private LinkedList<MapImage> loadedImages;
    private MapImage[][] visibleImages;
    private byte[][] maprules;
    private int xOff;
    private int yOff;
    private int xOff2;
    private int yOff2;
    Vector<ImageLoadThread> threadBuffer;
    
    public JMap(File file) {
        this.maxVisibleRect = new Rectangle();
        this.loadedImages = new LinkedList<>();
        this.threadBuffer = new Vector(10);
        this.loadMap(file, null, true);
    }
    
    public JMap() {
        this.maxVisibleRect = new Rectangle();
        this.loadedImages = new LinkedList<>();
        this.threadBuffer = new Vector(10);
    }
    
    @Override
    public void paint(Graphics g) {
        this.paintComponent(g);
    }
    
    public void draw(Graphics g, int map_x, int map_y) {
        this.xOff = -map_x % 320;
        this.yOff = -map_y % 240;
        g.translate(this.xOff, this.yOff);
        map_x /= 320;
        map_y /= 240;
        for (int h = 0; h < 6; ++h) {
            this.xOff2 = h * 320;
            this.yOff2 = h + map_x;
            for (int v = 0; v < 6; ++v) {
                Image image = this.getimage(this.yOff2, v + map_y);
                if (image != null) {
                    g.drawImage(image, this.xOff2, v * 240, this);
                }
            }
        }
        g.translate(-this.xOff, -this.yOff);
    }
    
    public Image getimage(int x, int y) {
        if (x < 0 || x >= this.visibleImages.length || y < 0 || y >= this.visibleImages[0].length) {
            return null;
        }
        MapImage mapImage = this.visibleImages[x][y];
        if (mapImage == null) {
            mapImage = new MapImage(null, x, y);
            this.visibleImages[x][y] = mapImage;
            byte[] imageData = this.decoder.isNew ? this.decoder.getJpegData2(x, y) : this.decoder.getJpegData(x, y);
            mapImage.image = Toolkit.getDefaultToolkit().createImage(imageData);
        }
        return mapImage.image;
    }
    
    public void paintComponent(Graphics g, int map_x, int map_y) {
        if (this.decoder == null) {
            return;
        }
        Rectangle clipRect = this.getVisibleRect();
        if (!this.maxVisibleRect.contains(clipRect)) {
            this.loadRectMapData(clipRect);
        }
        this.drawMap(g, map_x, map_y);
    }
    
    public void drawMap(Graphics g, int map_x, int map_y) {
        g = g.create();
        int xOffset = -map_x % 320;
        int yOffset = -map_y % 240;
        g.translate(xOffset, yOffset);
        map_x /= 320;
        map_y /= 240;
        for (int h = 0; h < 6; ++h) {
            for (int v = 0; v < 6; ++v) {
                if (h + map_x < this.visibleImages.length && v + map_y < this.visibleImages[0].length) {
                    g.drawImage(this.visibleImages[h + map_x][v + map_y].image, h * 320, v * 240, this);
                }
            }
        }
        g.dispose();
    }
    
    protected void drawCell(Graphics g) {
    }
    
    public void drawRect(Graphics g, Rectangle clipRect) {
        if (this.decoder == null) {
            return;
        }
        if (!this.maxVisibleRect.contains(clipRect)) {
            this.loadRectMapData(clipRect);
        }
        g = g.create();
        MapImage img0 = this.visibleImages[0][0];
        int xOffset = img0.h * 320 - clipRect.x;
        int yOffset = img0.v * 240 - clipRect.y;
        g.translate(xOffset, yOffset);
        for (int h = 0; h < this.visibleImages.length; ++h) {
            for (int v = 0; v < this.visibleImages[0].length; ++v) {
                g.drawImage(this.visibleImages[h][v].image, h * 320, v * 240, this);
            }
        }
        g.dispose();
    }
    
    private void loadRectMapData(Rectangle clipRect) {
        MapArea mapArea = this.getMapArea(clipRect);
        int rows = mapArea.right - mapArea.left + 1;
        int cols = mapArea.bottom - mapArea.top + 1;
        this.visibleImages = new MapImage[rows][cols];
        Vector<ImageLoadThread> threads = new Vector<>();
        for (int h = mapArea.left; h <= mapArea.right; ++h) {
            for (int v = mapArea.top; v <= mapArea.bottom; ++v) {
                MapImage mapimage = this.removeMapImage(h, v);
                if (mapimage == null) {
                    byte[] imageData = this.decoder.getJpegData(h, v);
                    Image image = Toolkit.getDefaultToolkit().createImage(imageData);
                    mapimage = new MapImage(image, h, v);
                    ImageLoadThread thread = this.getLoadThread();
                    thread.setLoadingImage(image);
                    threads.add(thread);
                }
                this.loadedImages.add(0, mapimage);
                if (this.loadedImages.size() > 16) {
                    this.loadedImages.removeLast();
                }
                this.visibleImages[h - mapArea.left][v - mapArea.top] = mapimage;
            }
        }
        for (ImageLoadThread thread2 : threads) {
            synchronized (thread2) {
                while (!thread2.isFinished()) {
                    try {
                        thread2.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.releaseLoadThread(thread2);
        }
        this.maxVisibleRect.x = mapArea.left * 320;
        this.maxVisibleRect.y = mapArea.top * 240;
        this.maxVisibleRect.width = (mapArea.right - mapArea.left + 1) * 320;
        this.maxVisibleRect.height = (mapArea.bottom - mapArea.top + 1) * 240;
        this.decoder.removedata();
    }
    
    private void releaseImage(MapImage img) {
        for (int w = 0; w < this.visibleImages.length; ++w) {
            int h = 0;
            while (h < this.visibleImages[w].length) {
                if (this.visibleImages[w][h] == img) {
                    this.visibleImages[w][h] = null;
                    break;
                }
                else {
                    ++h;
                }
            }
        }
        System.gc();
    }
    
    private void releaseLoadThread(ImageLoadThread thread) {
        this.threadBuffer.add(thread);
    }
    
    private ImageLoadThread getLoadThread() {
        if (this.threadBuffer.size() > 0) {
            return (ImageLoadThread)this.threadBuffer.remove(this.threadBuffer.size() - 1);
        }
        ImageLoadThread thread = new ImageLoadThread();
        thread.start();
        return thread;
    }
    
    private MapArea getMapArea(Rectangle clipRect) {
        MapArea mapArea = new MapArea();
        mapArea.left = (int)Math.ceil((double)clipRect.x / 320.0) - 1;
        mapArea.top = (int)Math.ceil((double)clipRect.y / 240.0) - 1;
        mapArea.right = (int)Math.ceil((double)(clipRect.x + clipRect.width) / 320.0) - 1;
        mapArea.bottom = (int)Math.ceil((double)(clipRect.y + clipRect.height) / 240.0) - 1;
        if (mapArea.left < 0) {
            mapArea.left = 0;
        }
        if (mapArea.top < 0) {
            mapArea.top = 0;
        }
        return mapArea;
    }
    
    private MapImage removeMapImage(int h, int v) {
        int length = this.loadedImages.size();
        MapImage mapImage = null;
        int index = -1;
        int i = 0;
        while (i < length) {
            mapImage = (MapImage)this.loadedImages.get(i);
            if (mapImage.h == h && mapImage.v == v) {
                index = i;
                break;
            }
            else {
                ++i;
            }
        }
        return (index == -1) ? null : ((MapImage)this.loadedImages.remove(index));
    }
    
    public boolean loadMap(File file, List<Door> doors, boolean useAlpha) {
        if (file == null) {
            return false;
        }
        try {
            if (useAlpha) {
                GameJpanel.alpha = 0.1f;
            }
            this.decoder = new MapDecoder(file);
            this.maprules = this.decoder.loadHeader(doors);
            this.setSize(this.decoder.getWidth(), this.decoder.getHeight());
            this.setPreferredSize(new Dimension(this.decoder.getWidth(), this.decoder.getHeight()));
            if (useAlpha) {
                GameJpanel.alpha = 0.35f;
            }
            this.loadedImages = new LinkedList<>();
            this.maxVisibleRect.height = 0;
            this.maxVisibleRect.width = 0;
            this.visibleImages = new MapImage[(int)Math.ceil((double)this.decoder.getWidth() / 320.0)][(int)Math.ceil((double)this.decoder.getHeight() / 240.0)];
            this.decoder.removedata();
        }
        catch (Exception e) {
            System.err.println("打开地图文件出错:" + file.getName());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public int getMapWidth() {
        return this.decoder.getWidth();
    }
    
    public int getMapHeight() {
        return this.decoder.getHeight();
    }
    
    public Dimension getMapSize() {
        return new Dimension(this.decoder.getWidth(), this.decoder.getHeight());
    }
    
    public Dimension getSegments() {
        return new Dimension(this.decoder.getHorSegmentCount(), this.decoder.getVerSegmentCount());
    }
    
    public MapDecoder getDecoder() {
        return this.decoder;
    }
    
    public byte[][] getMaprules() {
        return this.maprules;
    }
    
    public void setMaprules(byte[][] maprules) {
        this.maprules = maprules;
    }
    
    class MapArea
    {
        public int left;
        public int right;
        public int top;
        public int bottom;
        
        @Override
        public String toString() {
            return "[" + this.left + "," + this.top + "," + this.right + "," + this.bottom + "]";
        }
    }
    
    class MapImage
    {
        public Image image;
        public int h;
        public int v;
        
        public MapImage(Image image, int h, int v) {
            this.image = image;
            this.h = h;
            this.v = v;
        }
    }
    
    public class ImageLoadThread extends Thread
    {
        private Image image;
        protected Component component;
        protected MediaTracker tracker;
        private int mediaTrackerID;
        private boolean isFinished;
        private boolean isCompleted;
        
        private int getNextID() {
            return ++this.mediaTrackerID;
        }
        
        public ImageLoadThread() {
            this.component = new Component() {};
            this.tracker = new MediaTracker(this.component);
            this.setDaemon(true);
        }
        
        public void setLoadingImage(Image image) {
            this.image = image;
            synchronized (this) {
                this.notifyAll();
            }
        }
        
        @Override
        public void run() {
            synchronized (this) {
                this.isFinished = false;
                this.isCompleted = false;
                int id = this.getNextID();
                this.tracker.addImage(this.image, id);
                try {
                    this.tracker.waitForID(id, 0L);
                    this.isCompleted = true;
                }
                catch (InterruptedException e) {
                    System.out.println("INTERRUPTED while loading Image");
                }
                this.tracker.removeImage(this.image, id);
                this.isFinished = true;
                this.image = null;
                this.notifyAll();
                return;
            }
        }
        
        public boolean isCompleted() {
            return this.isCompleted;
        }
        
        public boolean isFinished() {
            return this.isFinished;
        }
    }
}
