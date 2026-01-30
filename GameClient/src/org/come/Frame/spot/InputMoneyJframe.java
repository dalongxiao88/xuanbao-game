package org.come.Frame.spot;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.Jpanel.spot.stall.SpotStallBaseJpanel;
import org.come.until.FormsManagement;
import org.come.Jpanel.spot.InputMoneyJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class InputMoneyJframe extends JInternalFrame implements MouseListener
{
    private InputMoneyJpanel inputMoneyJpanel;
    private int first_x;
    private int first_y;
    
    public static InputMoneyJframe getInputMoneyJframe() {
        return (InputMoneyJframe)FormsManagement.getInternalForm(802).getFrame();
    }
    
    public static void open(SpotStallBaseJpanel spotStallBaseJpanel) {
        getInputMoneyJframe().inputMoneyJpanel.init(spotStallBaseJpanel);
        FormsManagement.showForm(802);
    }
    
    public InputMoneyJframe() throws Exception {
        this.setContentPane(this.inputMoneyJpanel = new InputMoneyJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(500, 400, 296, 126);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (InputMoneyJframe.this.isVisible()) {
                    int x = e.getX() - InputMoneyJframe.this.first_x;
                    int y = e.getY() - InputMoneyJframe.this.first_y;
                    InputMoneyJframe.this.setBounds(x + InputMoneyJframe.this.getX(), y + InputMoneyJframe.this.getY(), InputMoneyJframe.this.getWidth(), InputMoneyJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(802);
        }
        else {
            FormsManagement.Switchinglevel(802);
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
    }
}
