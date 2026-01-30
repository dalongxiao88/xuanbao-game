package org.come.Frame;

//import java.awt.Image;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
//import javax.swing.BorderFactory;
import java.awt.Point;
import java.awt.image.MemoryImageSource;
//import java.awt.Toolkit;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.mouslisten.Mouselistener;
import org.come.Jpanel.ZhuJpanel;
//import javax.swing.JInternalFrame;

public class ZhuFrame extends JInternalFrame
{
    private static ZhuJpanel zhuJpanel;
    private Mouselistener mouselistener;
    private FrameMessageChangeJpanel changeJpanel;
    private GoodsMsgJframe goodsMsgJframe;
    private static ZhuFrame zhuFrame;
    
    public static ZhuFrame getzhuframe() {
        if (ZhuFrame.zhuFrame == null) {
            try {
                ZhuFrame.zhuFrame = new ZhuFrame();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ZhuFrame.zhuFrame;
    }
    
    public static ZhuFrame getzhuframefg() {
        try {
            ZhuFrame.zhuFrame = new ZhuFrame();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ZhuFrame.zhuFrame;
    }
    
    public ZhuFrame() throws Exception {
        this.setBackground(UIUtils.Color_BACK);
        Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(0, 0, new int[0], 0, 0));
      //  this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), null));
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.addMouseListener(this.mouselistener = new Mouselistener());
        this.addMouseMotionListener(this.mouselistener);
        this.changeJpanel = new FrameMessageChangeJpanel();
      //  this.add(ZhuFrame.zhuJpanel = new ZhuJpanel(), "West");
        ZhuFrame.zhuJpanel = new ZhuJpanel();
        this.add(zhuJpanel, "West");
        this.add(this.changeJpanel, "East");
        this.pack();
        this.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public static ZhuJpanel getZhuJpanel() {
        return ZhuFrame.zhuJpanel;
    }
    
    public static void setZhuJpanel(ZhuJpanel zhuJpanel) {
        ZhuFrame.zhuJpanel = zhuJpanel;
    }
    
    public Mouselistener getMouselistener() {
        return this.mouselistener;
    }
    
    public void setMouselistener(Mouselistener mouselistener) {
        this.mouselistener = mouselistener;
    }
    
    public FrameMessageChangeJpanel getChangeJpanel() {
        return this.changeJpanel;
    }
    
    public void setChangeJpanel(FrameMessageChangeJpanel changeJpanel) {
        this.changeJpanel = changeJpanel;
    }
    
    public GoodsMsgJframe getGoodsMsgJframe() {
        return this.goodsMsgJframe;
    }
    
    public void setGoodsMsgJframe(GoodsMsgJframe goodsMsgJframe) {
        this.goodsMsgJframe = goodsMsgJframe;
    }
}
