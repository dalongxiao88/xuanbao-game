package org.come.Frame;

import java.awt.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
//import java.awt.Point;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import com.tool.tcpimg.UIUtils;
import org.come.until.Music;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.mouslisten.Mouselistener;
import javax.swing.JInternalFrame;

public class FrameMessageFrame extends JInternalFrame
{
    private Mouselistener mouselistener;
    private static FrameMessageChangeJpanel changeJpanel;
    private static FrameMessageFrame frameMessage;
    
    public static FrameMessageFrame getFrameMessage() {
        if (FrameMessageFrame.frameMessage == null) {
            try {
                FrameMessageFrame.frameMessage = new FrameMessageFrame();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return FrameMessageFrame.frameMessage;
    }
    
    public FrameMessageFrame() throws Exception {
        Music.beijing(false);
        this.setBackground(UIUtils.Color_BACK);
        Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(0, 0, new int[0], 0, 0));
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), null));
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.addMouseListener(this.mouselistener = new Mouselistener());
        this.addMouseMotionListener(this.mouselistener);
        this.add(FrameMessageFrame.changeJpanel = new FrameMessageChangeJpanel());
        this.pack();
        this.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public Mouselistener getMouselistener() {
        return this.mouselistener;
    }
    
    public void setMouselistener(Mouselistener mouselistener) {
        this.mouselistener = mouselistener;
    }
    
    public static FrameMessageChangeJpanel getChangeJpanel() {
        return FrameMessageFrame.changeJpanel;
    }
    
    public static void setChangeJpanel(FrameMessageChangeJpanel changeJpanel) {
        FrameMessageFrame.changeJpanel = changeJpanel;
    }
}
