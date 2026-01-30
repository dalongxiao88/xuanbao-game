package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ExchangeAwardJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ExchangeAwardJframe extends JInternalFrame implements MouseListener
{
    private ExchangeAwardJpanel awardJpanel;
    private int first_x;
    private int first_y;
    
    public static ExchangeAwardJframe getExchangeAwardJframe() {
        return (ExchangeAwardJframe)FormsManagement.getInternalForm(73).getFrame();
    }
    
    public ExchangeAwardJframe() {
        this.awardJpanel = new ExchangeAwardJpanel();
        this.getContentPane().add(this.awardJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(200, 120, 536, 542);
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
                if (ExchangeAwardJframe.this.isVisible()) {
                    int x = e.getX() - ExchangeAwardJframe.this.first_x;
                    int y = e.getY() - ExchangeAwardJframe.this.first_y;
                    ExchangeAwardJframe.this.setBounds(x + ExchangeAwardJframe.this.getX(), y + ExchangeAwardJframe.this.getY(), ExchangeAwardJframe.this.getWidth(), ExchangeAwardJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //关闭按钮
        //开启窗口音效
        Music.addyinxiao("关闭窗口.mp3");
        //打开了窗体
        if (e.isMetaDown()) {
            //检测鼠标右键单击
            FormsManagement.HideForm(73);
        }
        else {
            FormsManagement.Switchinglevel(73);
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
    
    public ExchangeAwardJpanel getAwardJpanel() {
        return this.awardJpanel;
    }
    
    public void setAwardJpanel(ExchangeAwardJpanel awardJpanel) {
        this.awardJpanel = awardJpanel;
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
