package org.come.Frame;

import org.come.until.Music;
import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.QZCQJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class QZCQJfram extends JInternalFrame implements MouseListener
{
    private QZCQJpanel qzcqJpanel;
    private int first_x;
    private int first_y;
    
    public QZCQJpanel getQZCQJpanel() {
        return this.qzcqJpanel;
    }
    
    public void setQZCQJpanel(QZCQJpanel qzcqJpanel) {
        this.qzcqJpanel = qzcqJpanel;
    }
    
    public static QZCQJfram getqzcqJfram() {
        return (QZCQJfram)FormsManagement.getInternalForm(911).getFrame();
    }
    
    public QZCQJfram() throws Exception {
        this.qzcqJpanel = new QZCQJpanel();
        this.getContentPane().add(this.qzcqJpanel);
        this.setBounds(300, 200, 268, 309);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (QZCQJfram.this.isVisible()) {
                    int x = e.getX() - QZCQJfram.this.first_x;
                    int y = e.getY() - QZCQJfram.this.first_y;
                    QZCQJfram.this.setBounds(x + QZCQJfram.this.getX(), y + QZCQJfram.this.getY(), QZCQJfram.this.getWidth(), QZCQJfram.this.getHeight());
                }
            }
        });
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(2);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(911);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
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
