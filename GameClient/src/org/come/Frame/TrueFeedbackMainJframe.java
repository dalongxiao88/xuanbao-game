package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TrueFeedbackMainJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TrueFeedbackMainJframe extends JInternalFrame implements MouseListener
{
    private TrueFeedbackMainJpanel trueFeedbackMainJpanel;
    private int first_x;
    private int first_y;
    
    public static TrueFeedbackMainJframe getTrueFeedbackMainJframe() {
        return (TrueFeedbackMainJframe)FormsManagement.getInternalForm(109).getFrame();
    }
    
    public TrueFeedbackMainJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.add(this.trueFeedbackMainJpanel = new TrueFeedbackMainJpanel());
        this.setBounds(50, 50, 400, 300);
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
                if (TrueFeedbackMainJframe.this.isVisible()) {
                    int x = e.getX() - TrueFeedbackMainJframe.this.first_x;
                    int y = e.getY() - TrueFeedbackMainJframe.this.first_y;
                    TrueFeedbackMainJframe.this.setBounds(x + TrueFeedbackMainJframe.this.getX(), y + TrueFeedbackMainJframe.this.getY(), TrueFeedbackMainJframe.this.getWidth(), TrueFeedbackMainJframe.this.getHeight());
                }
            }
        });
    }
    
    public TrueFeedbackMainJpanel getTrueFeedbackMainJpanel() {
        return this.trueFeedbackMainJpanel;
    }
    
    public void setTrueFeedbackMainJpanel(TrueFeedbackMainJpanel trueFeedbackMainJpanel) {
        this.trueFeedbackMainJpanel = trueFeedbackMainJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(109);
        }
        else {
            FormsManagement.Switchinglevel(109);
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
}
