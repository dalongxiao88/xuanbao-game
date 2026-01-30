package org.lottery.frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.lottery.panel.LotteryMainPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LotteryMainFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private LotteryMainPanel lotteryMainPanel;
    
    public static LotteryMainFrame getLotteryMainFrame() {
        return (LotteryMainFrame)FormsManagement.getInternalForm(87).getFrame();
    }
    
    public LotteryMainFrame() {
        this.add(this.lotteryMainPanel = new LotteryMainPanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(200, 60, 561, 537);
        this.setOpaque(false);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (LotteryMainFrame.this.isVisible()) {
                    int x = e.getX() - LotteryMainFrame.this.first_x;
                    int y = e.getY() - LotteryMainFrame.this.first_y;
                    LotteryMainFrame.this.setBounds(x + LotteryMainFrame.this.getX(), y + LotteryMainFrame.this.getY(), LotteryMainFrame.this.getWidth(), LotteryMainFrame.this.getHeight());
                }
            }
        });
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.disposeForm(87);
        }
        else {
            FormsManagement.Switchinglevel(87);
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
    
    public LotteryMainPanel getLotteryMainPanel() {
        return this.lotteryMainPanel;
    }
    
    public void setLotteryMainPanel(LotteryMainPanel lotteryMainPanel) {
        this.lotteryMainPanel = lotteryMainPanel;
    }
}
