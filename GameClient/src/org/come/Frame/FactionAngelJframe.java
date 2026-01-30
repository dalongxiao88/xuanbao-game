package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.FactionAngelJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FactionAngelJframe extends JInternalFrame implements MouseListener
{
    private FactionAngelJpanel factionAngelJpanel;
    private int first_x;
    private int first_y;
    
    public static FactionAngelJframe getFactionAngelJframe() {
        return (FactionAngelJframe)FormsManagement.getInternalForm(54).getFrame();
    }
    
    public FactionAngelJframe() throws Exception {
        this.add(this.factionAngelJpanel = new FactionAngelJpanel());
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
                if (FactionAngelJframe.this.isVisible()) {
                    int x = e.getX() - FactionAngelJframe.this.first_x;
                    int y = e.getY() - FactionAngelJframe.this.first_y;
                    FactionAngelJframe.this.setBounds(x + FactionAngelJframe.this.getX(), y + FactionAngelJframe.this.getY(), FactionAngelJframe.this.getWidth(), FactionAngelJframe.this.getHeight());
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
            FormsManagement.HideForm(54);
            FormsManagement.disposeForm(54);
        }
        else {
            FormsManagement.Switchinglevel(54);
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
    
    public FactionAngelJpanel getFactionAngelJpanel() {
        return this.factionAngelJpanel;
    }
    
    public void setFactionAngelJpanel(FactionAngelJpanel factionAngelJpanel) {
        this.factionAngelJpanel = factionAngelJpanel;
    }
}
