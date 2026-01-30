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

public class LadderLotteryJframe extends JInternalFrame implements MouseListener
{
    private LadderLotteryJpanel ladderLotteryJpanel;
    private int first_x;
    private int first_y;
    
    public static LadderLotteryJframe getLadderLotteryJframe() {
        return (LadderLotteryJframe)FormsManagement.getInternalForm(606).getFrame();
    }
    
    public LadderLotteryJframe() {
        this.ladderLotteryJpanel = new LadderLotteryJpanel();
        this.getContentPane().add(this.ladderLotteryJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(80, 50, 652, 446);
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
                if (LadderLotteryJframe.this.isVisible()) {
                    int x = e.getX() - LadderLotteryJframe.this.first_x;
                    int y = e.getY() - LadderLotteryJframe.this.first_y;
                    LadderLotteryJframe.this.setBounds(x + LadderLotteryJframe.this.getX(), y + LadderLotteryJframe.this.getY(), LadderLotteryJframe.this.getWidth(), LadderLotteryJframe.this.getHeight());
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
            FormsManagement.HideForm(606);
        }
        else {
            FormsManagement.Switchinglevel(606);
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
    
    public LadderLotteryJpanel getLadderLotteryJpanel() {
        return this.ladderLotteryJpanel;
    }
    
    public void setLadderLotteryJpanel(LadderLotteryJpanel ladderLotteryJpanel) {
        this.ladderLotteryJpanel = ladderLotteryJpanel;
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
