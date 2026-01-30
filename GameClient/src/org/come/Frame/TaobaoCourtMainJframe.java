package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TaobaoCourtMainJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TaobaoCourtMainJframe extends JInternalFrame implements MouseListener
{
    private TaobaoCourtMainJpanel taobaoCourtMainJpanel;
    private int first_x;
    private int first_y;
    
    public static TaobaoCourtMainJframe getTaobaoCourtJframe() {
        return (TaobaoCourtMainJframe)FormsManagement.getInternalForm(39).getFrame();
    }
    
    public TaobaoCourtMainJframe() throws Exception {
        this.taobaoCourtMainJpanel = new TaobaoCourtMainJpanel();
        this.getContentPane().add(this.taobaoCourtMainJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(115, 115, 656, 497);
        this.setBackground(UIUtils.Color_BACK);
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
                if (TaobaoCourtMainJframe.this.isVisible()) {
                    int x = e.getX() - TaobaoCourtMainJframe.this.first_x;
                    int y = e.getY() - TaobaoCourtMainJframe.this.first_y;
                    TaobaoCourtMainJframe.this.setBounds(x + TaobaoCourtMainJframe.this.getX(), y + TaobaoCourtMainJframe.this.getY(), TaobaoCourtMainJframe.this.getWidth(), TaobaoCourtMainJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(39);
        }
        else {
            FormsManagement.Switchinglevel(39);
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
    
    public int getFirst_x() {
        return this.first_x;
    }
    
    public void setFirst_x(int first_x) {
        this.first_x = first_x;
    }
    
    public int getFirst_y() {
        return this.first_y;
    }
    
    public void setFirst_y(int first_y) {
        this.first_y = first_y;
    }
    
    public TaobaoCourtMainJpanel getTaobaoCourtMainJpanel() {
        return this.taobaoCourtMainJpanel;
    }
    
    public void setTaobaoCourtMainJpanel(TaobaoCourtMainJpanel taobaoCourtMainJpanel) {
        this.taobaoCourtMainJpanel = taobaoCourtMainJpanel;
    }
}
