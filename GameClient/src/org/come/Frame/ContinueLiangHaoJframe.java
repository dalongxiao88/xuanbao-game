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
import org.come.Jpanel.ContinueLiangHaoJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ContinueLiangHaoJframe extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = 1L;
    private ContinueLiangHaoJpanel continueLiangHaoJpanel;
    private int first_x;
    private int first_y;
    
    public static ContinueLiangHaoJframe getContinueLiangHaoJframe() {
        return (ContinueLiangHaoJframe)FormsManagement.getInternalForm(706).getFrame();
    }
    
    public ContinueLiangHaoJframe() {
        this.continueLiangHaoJpanel = new ContinueLiangHaoJpanel();
        this.getContentPane().add(this.continueLiangHaoJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(100, 50, 430, 470);
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
                if (ContinueLiangHaoJframe.this.isVisible()) {
                    int x = e.getX() - ContinueLiangHaoJframe.this.first_x;
                    int y = e.getY() - ContinueLiangHaoJframe.this.first_y;
                    ContinueLiangHaoJframe.this.setBounds(x + ContinueLiangHaoJframe.this.getX(), y + ContinueLiangHaoJframe.this.getY(), ContinueLiangHaoJframe.this.getWidth(), ContinueLiangHaoJframe.this.getHeight());
                }
            }
        });
        String mes2 = Agreement.getAgreement().selllianghaoAgreement("SELLLIST");
        SendMessageUntil.toServer(mes2);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(706);
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
