package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import org.come.until.ScrenceUntil;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.WorldMapJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class WorldMapJframe extends JInternalFrame implements MouseListener
{
    private static WorldMapJpanel worldMapJpanel;
    private int first_x;
    private int first_y;
    
    public static WorldMapJframe getRankingListJframe() {
        return (WorldMapJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public WorldMapJframe() {
        WorldMapJframe.worldMapJpanel = new WorldMapJpanel();
        this.getContentPane().add(WorldMapJframe.worldMapJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(ScrenceUntil.Screen_x / 2 - 476, ScrenceUntil.Screen_y / 2 - 250, 953, 501);
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
                if (WorldMapJframe.this.isVisible()) {
                    int x = e.getX() - WorldMapJframe.this.first_x;
                    int y = e.getY() - WorldMapJframe.this.first_y;
                    WorldMapJframe.this.setBounds(x + WorldMapJframe.this.getX(), y + WorldMapJframe.this.getY(), WorldMapJframe.this.getWidth(), WorldMapJframe.this.getHeight());
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
            FormsManagement.HideForm(1102);
            WorldMapJpanel.showIsTeamBtnSx(false, 0);
            WorldMapJpanel.showduyu(false, 0);
            WorldMapJpanel.showdayanta(false, 0);
            WorldMapJpanel.showfengcao(false, 0);
            WorldMapJpanel.showlongku(false, 0);
        }
        else {
            FormsManagement.Switchinglevel(1102);
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
    
    public static WorldMapJpanel getWorldMapJpanel() {
        return WorldMapJframe.worldMapJpanel;
    }
    
    public static void setWorldMapJpanel(WorldMapJpanel worldMapJpanel) {
        WorldMapJframe.worldMapJpanel = worldMapJpanel;
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
