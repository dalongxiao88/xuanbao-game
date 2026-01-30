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
import org.come.Jpanel.GetLiangHaoJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GetLiangHaoJframe extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = 1L;
    private GetLiangHaoJpanel getLiangHaoJpanel;
    private int first_x;
    private int first_y;
    
    public static GetLiangHaoJframe getGetLiangHaoJframe() {
        return (GetLiangHaoJframe)FormsManagement.getInternalForm(641).getFrame();
    }
    
    public GetLiangHaoJframe() {
        this.getLiangHaoJpanel = new GetLiangHaoJpanel();
        this.getContentPane().add(this.getLiangHaoJpanel);
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
                if (GetLiangHaoJframe.this.isVisible()) {
                    int x = e.getX() - GetLiangHaoJframe.this.first_x;
                    int y = e.getY() - GetLiangHaoJframe.this.first_y;
                    GetLiangHaoJframe.this.setBounds(x + GetLiangHaoJframe.this.getX(), y + GetLiangHaoJframe.this.getY(), GetLiangHaoJframe.this.getWidth(), GetLiangHaoJframe.this.getHeight());
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
            FormsManagement.HideForm(641);
        }
        else {
            FormsManagement.Switchinglevel(641);
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
    
    public GetLiangHaoJpanel getGetLiangHaoJpanel() {
        return this.getLiangHaoJpanel;
    }
    
    public void setGetLiangHaoJpanel(GetLiangHaoJpanel getLiangHaoJpanel) {
        this.getLiangHaoJpanel = getLiangHaoJpanel;
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
