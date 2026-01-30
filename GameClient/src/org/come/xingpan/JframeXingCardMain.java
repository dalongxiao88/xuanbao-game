package org.come.xingpan;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.model.InternalForm;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class JframeXingCardMain extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private JpanelXingCardMain JpanelXingCardMain;
    
    public static JframeXingCardMain getJframeSummonEquipMain() {
        return (JframeXingCardMain)FormsManagement.getInternalForm(122).getFrame();
    }
    
    public static JframeXingCardMain getShowJframeSummonEquipMain() {
        InternalForm form = FormsManagement.getInternalForm2(122);
        if (form == null) {
            return null;
        }
        JframeXingCardMain equipMain = (JframeXingCardMain)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public JframeXingCardMain() {
        this.add(this.JpanelXingCardMain = new JpanelXingCardMain());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(200, 120, 646, 464);
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
                if (JframeXingCardMain.getJframeSummonEquipMain().isVisible()) {
                    int x = e.getX() - JframeXingCardMain.this.first_x;
                    int y = e.getY() - JframeXingCardMain.this.first_y;
                    JframeXingCardMain.getJframeSummonEquipMain().setBounds(x + JframeXingCardMain.getJframeSummonEquipMain().getX(), y + JframeXingCardMain.getJframeSummonEquipMain().getY(), JframeXingCardMain.getJframeSummonEquipMain().getWidth(), JframeXingCardMain.getJframeSummonEquipMain().getHeight());
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
            FormsManagement.HideForm(122);
        }
        else {
            FormsManagement.Switchinglevel(122);
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
    
    public JpanelXingCardMain getJpanelXingCardMain() {
        return this.JpanelXingCardMain;
    }
    
    public void setJpanelXingCardMain(JpanelXingCardMain JpanelXingCardMain) {
        this.JpanelXingCardMain = JpanelXingCardMain;
    }
}
