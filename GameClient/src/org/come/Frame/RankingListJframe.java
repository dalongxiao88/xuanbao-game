package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RankingListJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RankingListJframe extends JInternalFrame implements MouseListener
{
    private RankingListJpanel rankingListJpanel;
    private int first_x;
    private int first_y;
    
    public static RankingListJframe getRankingListJframe() {
        return (RankingListJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public RankingListJframe() {
        this.rankingListJpanel = new RankingListJpanel();
        this.getContentPane().add(this.rankingListJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 616, 442);
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
                if (RankingListJframe.this.isVisible()) {
                    int x = e.getX() - RankingListJframe.this.first_x;
                    int y = e.getY() - RankingListJframe.this.first_y;
                    RankingListJframe.this.setBounds(x + RankingListJframe.this.getX(), y + RankingListJframe.this.getY(), RankingListJframe.this.getWidth(), RankingListJframe.this.getHeight());
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
            FormsManagement.HideForm(60);
        }
        else {
            FormsManagement.Switchinglevel(60);
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
    
    public RankingListJpanel getRankingListJpanel() {
        return this.rankingListJpanel;
    }
    
    public void setRankingListJpanel(RankingListJpanel rankingListJpanel) {
        this.rankingListJpanel = rankingListJpanel;
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
