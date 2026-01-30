package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.FactionAngelPracticeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FactionAngelPracticeJframe extends JInternalFrame implements MouseListener
{
    private FactionAngelPracticeJpanel factionAngelPracticeJpanel;
    private int first_x;
    private int first_y;
    
    public static FactionAngelPracticeJframe getFactionAngelPracticeJframe() {
        return (FactionAngelPracticeJframe)FormsManagement.getInternalForm(106).getFrame();
    }
    
    public FactionAngelPracticeJframe() throws Exception {
        this.add(this.factionAngelPracticeJpanel = new FactionAngelPracticeJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(380, 170, 330, 450);
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
                if (FactionAngelPracticeJframe.this.isVisible()) {
                    int x = e.getX() - FactionAngelPracticeJframe.this.first_x;
                    int y = e.getY() - FactionAngelPracticeJframe.this.first_y;
                    FactionAngelPracticeJframe.this.setBounds(x + FactionAngelPracticeJframe.this.getX(), y + FactionAngelPracticeJframe.this.getY(), FactionAngelPracticeJframe.this.getWidth(), FactionAngelPracticeJframe.this.getHeight());
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
            FormsManagement.HideForm(106);
        }
        else {
            FormsManagement.Switchinglevel(106);
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
    
    public FactionAngelPracticeJpanel getFactionAngelPracticeJpanel() {
        return this.factionAngelPracticeJpanel;
    }
    
    public void setFactionAngelPracticeJpanel(FactionAngelPracticeJpanel factionAngelPracticeJpanel) {
        this.factionAngelPracticeJpanel = factionAngelPracticeJpanel;
    }
}
