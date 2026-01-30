package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.until.ScrenceUntil;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.NPCJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class NPCJfram extends JInternalFrame implements MouseListener
{
    private NPCJpanel npcjpanel;
    
    public static NPCJfram getNpcJfram() {
        return (NPCJfram)FormsManagement.getInternalForm(27).getFrame();
    }
    
    public NPCJfram() {
        this.npcjpanel = new NPCJpanel();
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.add(this.npcjpanel);
        this.setBounds(ScrenceUntil.Screen_x / 2 - 267, 200, 534, 208);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseListener(this);
        this.setDefaultCloseOperation(2);
    }
    
    public NPCJpanel getNpcjpanel() {
        return this.npcjpanel;
    }
    
    public void setNpcjpanel(NPCJpanel npcjpanel) {
        this.npcjpanel = npcjpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(27);
        }
        else {
            FormsManagement.Switchinglevel(27);
        }
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
}
