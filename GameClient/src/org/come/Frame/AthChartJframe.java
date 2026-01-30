package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.AthChartJPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AthChartJframe extends JInternalFrame implements MouseListener
{
    private static AthChartJPanel athChartJPanel;
    private int first_x;
    private int first_y;
    
    public static AthChartJframe getRankingListJframe() {
        return (AthChartJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public AthChartJframe() {
        AthChartJframe.athChartJPanel = new AthChartJPanel();
        this.getContentPane().add(AthChartJframe.athChartJPanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(170, 90, 636, 437);
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
                if (AthChartJframe.this.isVisible()) {
                    int x = e.getX() - AthChartJframe.this.first_x;
                    int y = e.getY() - AthChartJframe.this.first_y;
                    AthChartJframe.this.setBounds(x + AthChartJframe.this.getX(), y + AthChartJframe.this.getY(), AthChartJframe.this.getWidth(), AthChartJframe.this.getHeight());
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
            ZhuFrame.getZhuJpanel().addPrompt2("关闭后将消失，请勿关闭！");
        }
        else {
            FormsManagement.Switchinglevel(3001);
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
    
    public static AthChartJPanel getAthChartJPanel() {
        return AthChartJframe.athChartJPanel;
    }
    
    public static void setAthChartJPanel(AthChartJPanel athChartJPanel) {
        AthChartJframe.athChartJPanel = athChartJPanel;
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
