package org.come.tt;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LadderJframe extends JInternalFrame implements MouseListener
{
    private LadderJpanel ladderJpanel;
    private int first_x;
    private int first_y;
    
    public static LadderJframe getLadderJframe() {
        return (LadderJframe)FormsManagement.getInternalForm(604).getFrame();
    }
    
    public LadderJframe() {
        this.ladderJpanel = new LadderJpanel();
        this.getContentPane().add(this.ladderJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(80, 50, 656, 497);
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
                if (LadderJframe.this.isVisible()) {
                    int x = e.getX() - LadderJframe.this.first_x;
                    int y = e.getY() - LadderJframe.this.first_y;
                    LadderJframe.this.setBounds(x + LadderJframe.this.getX(), y + LadderJframe.this.getY(), LadderJframe.this.getWidth(), LadderJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(604);
        }
        else {
            FormsManagement.Switchinglevel(604);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
    
    public LadderJpanel getLadderJpanel() {
        return this.ladderJpanel;
    }
    
    public void setLadderJpanel(LadderJpanel ladderJpanel) {
        this.ladderJpanel = ladderJpanel;
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
