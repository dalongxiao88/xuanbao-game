package org.come.log;

import org.come.until.MessagrFlagUntil;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import org.come.init.CheckUpdateJPanel;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class LanderJFrame extends JFrame implements MouseListener, Runnable
{
    public LanderJPanel landerJPanel;
    private int first_x;
    private int first_y;
    CheckUpdateJPanel checkUpdateJpanel;
    public static JFrame frame;
    
    public LanderJFrame() {
        LanderJFrame.frame.setResizable(false);
        LanderJFrame.frame.setUndecorated(true);
        try {
            this.landerJPanel = new LanderJPanel(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.checkUpdateJpanel = new CheckUpdateJPanel();
        LanderJFrame.frame.setContentPane(this.landerJPanel);
        LanderJFrame.frame.setBackground(UIUtils.Color_BACK);
        Toolkit tool = LanderJFrame.frame.getToolkit();
        Image myimage = tool.getImage("img/icon/ico.png");
        LanderJFrame.frame.setIconImage(myimage);
        LanderJFrame.frame.pack();
        LanderJFrame.frame.setVisible(true);
        LanderJFrame.frame.setLocationRelativeTo(null);
        LanderJFrame.frame.setDefaultCloseOperation(3);
        LanderJFrame.frame.addMouseListener(this);
        LanderJFrame.frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (LanderJFrame.frame.isVisible()) {
                    int x = e.getX() - LanderJFrame.this.first_x;
                    int y = e.getY() - LanderJFrame.this.first_y;
                    LanderJFrame.frame.setBounds(x + LanderJFrame.frame.getX(), y + LanderJFrame.frame.getY(), LanderJFrame.frame.getWidth(), LanderJFrame.frame.getHeight());
                }
            }
        });
    }
    
    @Override
    public void run() {
        this.checkUpdateJpanel.getUpdateView().update();
    }
    
    public LanderJPanel getLanderJPanel() {
        return this.landerJPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            System.exit(0);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }
    
    static {
        LanderJFrame.frame = new JFrame("大话西游2");
    }
}
