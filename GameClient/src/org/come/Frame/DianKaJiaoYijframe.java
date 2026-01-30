package org.come.Frame;

import org.come.until.Music;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.DianKaJiaoYiJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class DianKaJiaoYijframe extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = 1L;
    private DianKaJiaoYiJpanel dianKaJiaoYiJpanel;
    private int first_x;
    private int first_y;
    
    public static DianKaJiaoYijframe getDianKaJiaoYijframe() {
        return (DianKaJiaoYijframe)FormsManagement.getInternalForm(114).getFrame();
    }
    
    public DianKaJiaoYijframe() {
        this.dianKaJiaoYiJpanel = new DianKaJiaoYiJpanel();
        this.getContentPane().add(this.dianKaJiaoYiJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(100, 50, 480, 520);
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
                if (DianKaJiaoYijframe.this.isVisible()) {
                    int x = e.getX() - DianKaJiaoYijframe.this.first_x;
                    int y = e.getY() - DianKaJiaoYijframe.this.first_y;
                    DianKaJiaoYijframe.this.setBounds(x + DianKaJiaoYijframe.this.getX(), y + DianKaJiaoYijframe.this.getY(), DianKaJiaoYijframe.this.getWidth(), DianKaJiaoYijframe.this.getHeight());
                }
            }
        });
        String mes2 = Agreement.getAgreement().sellxianyuAgreement("ALLLIST|1");
        SendMessageUntil.toServer(mes2);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(114);
        }
        else {
            FormsManagement.Switchinglevel(114);
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
    
    public DianKaJiaoYiJpanel getDianKaJiaoYiJpanel() {
        return this.dianKaJiaoYiJpanel;
    }
    
    public void setDianKaJiaoYiJpanel(DianKaJiaoYiJpanel dianKaJiaoYiJpanel) {
        this.dianKaJiaoYiJpanel = dianKaJiaoYiJpanel;
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
