package org.lottery.frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.lottery.panel.LotteryIntegralMainPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LotteryIntegralMainJframe extends JInternalFrame implements MouseListener
{
    private LotteryIntegralMainPanel lotteryIntegralMainPanel;
    private int first_x;
    private int first_y;
    
    public static LotteryIntegralMainJframe getLotteryIntegralMainJframe() {
        return (LotteryIntegralMainJframe)FormsManagement.getInternalForm(88).getFrame();
    }
    
    public LotteryIntegralMainJframe() throws Exception {
        this.lotteryIntegralMainPanel = new LotteryIntegralMainPanel();
        this.getContentPane().add(this.lotteryIntegralMainPanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 100, 589, 479);
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
                if (LotteryIntegralMainJframe.this.isVisible()) {
                    int x = e.getX() - LotteryIntegralMainJframe.this.first_x;
                    int y = e.getY() - LotteryIntegralMainJframe.this.first_y;
                    LotteryIntegralMainJframe.this.setBounds(x + LotteryIntegralMainJframe.this.getX(), y + LotteryIntegralMainJframe.this.getY(), LotteryIntegralMainJframe.this.getWidth(), LotteryIntegralMainJframe.this.getHeight());
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
            FormsManagement.HideForm(88);
        }
        else {
            FormsManagement.Switchinglevel(88);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    public LotteryIntegralMainPanel getLotteryIntegralMainPanel() {
        return this.lotteryIntegralMainPanel;
    }
    
    public void setLotteryIntegralMainPanel(LotteryIntegralMainPanel lotteryIntegralMainPanel) {
        this.lotteryIntegralMainPanel = lotteryIntegralMainPanel;
    }
}
