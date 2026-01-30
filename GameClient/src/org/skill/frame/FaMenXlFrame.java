package org.skill.frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.skill.panel.FaMenXlPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FaMenXlFrame extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = 6487538265898556693L;
    private int first_x;
    private int first_y;
    private FaMenXlPanel faMenXlPanel;
    
    public static FaMenXlFrame getInstance() {
        return (FaMenXlFrame)FormsManagement.getInternalForm(6022).getFrame();
    }
    
    public FaMenXlFrame() {
        this.faMenXlPanel = new FaMenXlPanel();
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(200, 100, 388, 374);
        this.setBackground(UIUtils.Color_BACK);
        this.add(this.faMenXlPanel);
        this.pack();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (FaMenXlFrame.this.isVisible()) {
                    int x = e.getX() - FaMenXlFrame.this.first_x;
                    int y = e.getY() - FaMenXlFrame.this.first_y;
                    FaMenXlFrame.this.setBounds(x + FaMenXlFrame.this.getX(), y + FaMenXlFrame.this.getY(), FaMenXlFrame.this.getWidth(), FaMenXlFrame.this.getHeight());
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
            FormsManagement.HideForm(6022);
        }
        else {
            FormsManagement.Switchinglevel(6022);
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
    
    public FaMenXlPanel getFaMenXlPanel() {
        return this.faMenXlPanel;
    }
    
    public void setFaMenXlPanel(FaMenXlPanel faMenXlPanel) {
        this.faMenXlPanel = faMenXlPanel;
    }
}
