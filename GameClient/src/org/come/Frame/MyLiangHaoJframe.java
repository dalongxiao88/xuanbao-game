package org.come.Frame;

import org.come.until.Music;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.MyLiangHaoJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MyLiangHaoJframe extends JInternalFrame implements MouseListener
{
    private MyLiangHaoJpanel myLiangHaoJpanel;
    private int first_x;
    private int first_y;
    
    public static MyLiangHaoJframe getChooseLiangHaoTypeJframe() {
        return (MyLiangHaoJframe)FormsManagement.getInternalForm(640).getFrame();
    }
    
    public MyLiangHaoJframe() {
        this.myLiangHaoJpanel = new MyLiangHaoJpanel();
        this.getContentPane().add(this.myLiangHaoJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 380, 394);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (MyLiangHaoJframe.this.isVisible()) {
                    int x = e.getX() - MyLiangHaoJframe.this.first_x;
                    int y = e.getY() - MyLiangHaoJframe.this.first_y;
                    MyLiangHaoJframe.this.setBounds(x + MyLiangHaoJframe.this.getX(), y + MyLiangHaoJframe.this.getY(), MyLiangHaoJframe.this.getWidth(), MyLiangHaoJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            Music.addyinxiao("关闭窗口.mp3");
            FormsManagement.HideForm(640);
        }
        else {
            FormsManagement.Switchinglevel(640);
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
    
    public MyLiangHaoJpanel getMyLiangHaoJpanel() {
        return this.myLiangHaoJpanel;
    }
    
    public void setMyLiangHaoJpanel(MyLiangHaoJpanel myLiangHaoJpanel) {
        this.myLiangHaoJpanel = myLiangHaoJpanel;
    }
}
