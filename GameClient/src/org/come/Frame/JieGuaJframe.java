package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import com.tool.tcpimg.UIUtils;
import javax.swing.BorderFactory;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import org.come.Jpanel.JieGuaJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class JieGuaJframe extends JInternalFrame implements MouseListener
{
    private JieGuaJpanel jieGuaJpanel;
    private int first_x;
    private int first_y;
    
    public static void open() {
        FormsManagement.showForm(712);
        SendMessageUntil.toServer(Agreement.getAgreement().JieGuaAgreement("1"));
    }
    
    public static void close() {
        FormsManagement.HideForm(712);
        SendMessageUntil.toServer(Agreement.getAgreement().JieGuaAgreement("0"));
    }
    
    public static JieGuaJframe getJieGuaJframe() {
        return (JieGuaJframe)FormsManagement.getInternalForm(712).getFrame();
    }
    
    public JieGuaJframe() {
        this.jieGuaJpanel = new JieGuaJpanel();
        this.getContentPane().add(this.jieGuaJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(UIUtils.Color_BACK);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(200, 80, 641, 464);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (JieGuaJframe.this.isVisible()) {
                    int x = e.getX() - JieGuaJframe.this.first_x;
                    int y = e.getY() - JieGuaJframe.this.first_y;
                    JieGuaJframe.this.setBounds(x + JieGuaJframe.this.getX(), y + JieGuaJframe.this.getY(), JieGuaJframe.this.getWidth(), JieGuaJframe.this.getHeight());
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
            close();
        }
        else {
            FormsManagement.Switchinglevel(712);
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
    
    public JieGuaJpanel getJieGuaJpanel() {
        return this.jieGuaJpanel;
    }
}
