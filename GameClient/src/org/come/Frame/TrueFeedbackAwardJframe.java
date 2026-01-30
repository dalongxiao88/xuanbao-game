package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TrueFeedbackAwardJPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TrueFeedbackAwardJframe extends JInternalFrame implements MouseListener
{
    private TrueFeedbackAwardJPanel trueFeedbackAwardJPanel;
    private int first_x;
    private int first_y;
    
    public static TrueFeedbackAwardJframe getTrueFeedbackAwardJframe() {
        return (TrueFeedbackAwardJframe)FormsManagement.getInternalForm(110).getFrame();
    }
    
    public TrueFeedbackAwardJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.add(this.trueFeedbackAwardJPanel = new TrueFeedbackAwardJPanel());
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
                if (TrueFeedbackAwardJframe.this.isVisible()) {
                    int x = e.getX() - TrueFeedbackAwardJframe.this.first_x;
                    int y = e.getY() - TrueFeedbackAwardJframe.this.first_y;
                    TrueFeedbackAwardJframe.this.setBounds(x + TrueFeedbackAwardJframe.this.getX(), y + TrueFeedbackAwardJframe.this.getY(), TrueFeedbackAwardJframe.this.getWidth(), TrueFeedbackAwardJframe.this.getHeight());
                }
            }
        });
    }
    
    public TrueFeedbackAwardJPanel getTrueFeedbackAwardJPanel() {
        return this.trueFeedbackAwardJPanel;
    }
    
    public void setTrueFeedbackAwardJPanel(TrueFeedbackAwardJPanel trueFeedbackAwardJPanel) {
        this.trueFeedbackAwardJPanel = trueFeedbackAwardJPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(110);
        }
        else {
            FormsManagement.Switchinglevel(110);
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
