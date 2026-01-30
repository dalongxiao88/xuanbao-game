package org.come.summonequip;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class JframeCashRewardsMain extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private JpanelCashRewardsMain jpanelCashRewardsMain;
    
    public static JframeCashRewardsMain getJframeCashRewardsMain() {
        return (JframeCashRewardsMain)FormsManagement.getInternalForm(92).getFrame();
    }
    
    public JframeCashRewardsMain() {
        this.add(this.jpanelCashRewardsMain = new JpanelCashRewardsMain());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(500, 110, 561, 537);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (JframeCashRewardsMain.getJframeCashRewardsMain().isVisible()) {
                    int x = e.getX() - JframeCashRewardsMain.this.first_x;
                    int y = e.getY() - JframeCashRewardsMain.this.first_y;
                    JframeCashRewardsMain.getJframeCashRewardsMain().setBounds(x + JframeCashRewardsMain.getJframeCashRewardsMain().getX(), y + JframeCashRewardsMain.getJframeCashRewardsMain().getY(), JframeCashRewardsMain.getJframeCashRewardsMain().getWidth(), JframeCashRewardsMain.getJframeCashRewardsMain().getHeight());
                }
            }
        });
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(92);
        }
        else {
            FormsManagement.Switchinglevel(92);
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
    
    public JpanelCashRewardsMain getJpanelCashRewardsMain() {
        return this.jpanelCashRewardsMain;
    }
    
    public void setJpanelCashRewardsMain(JpanelCashRewardsMain jpanelCashRewardsMain) {
        this.jpanelCashRewardsMain = jpanelCashRewardsMain;
    }
}
