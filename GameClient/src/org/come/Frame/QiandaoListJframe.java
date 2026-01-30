package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.QiandaoListJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class QiandaoListJframe extends JInternalFrame implements MouseListener
{
    private static QiandaoListJpanel qiandaoListJpanel;
    private int first_x;
    private int first_y;
    
    public static QiandaoListJframe getRankingListJframe() {
        return (QiandaoListJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public QiandaoListJframe() {
        QiandaoListJframe.qiandaoListJpanel = new QiandaoListJpanel();
        this.getContentPane().add(QiandaoListJframe.qiandaoListJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(150, 100, 800, 700);
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
                if (QiandaoListJframe.this.isVisible()) {
                    int x = e.getX() - QiandaoListJframe.this.first_x;
                    int y = e.getY() - QiandaoListJframe.this.first_y;
                    QiandaoListJframe.this.setBounds(x + QiandaoListJframe.this.getX(), y + QiandaoListJframe.this.getY(), QiandaoListJframe.this.getWidth(), QiandaoListJframe.this.getHeight());
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
            FormsManagement.HideForm(1100);
        }
        else {
            FormsManagement.Switchinglevel(1100);
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
    
    public static QiandaoListJpanel getQiandaoListJpanel() {
        return QiandaoListJframe.qiandaoListJpanel;
    }
    
    public void setQiandaoListJpanel(QiandaoListJpanel qiandaoListJpanel) {
        QiandaoListJframe.qiandaoListJpanel = qiandaoListJpanel;
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
