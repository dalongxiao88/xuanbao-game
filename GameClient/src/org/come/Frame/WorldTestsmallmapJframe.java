package org.come.Frame;

import java.util.List;
import java.io.IOException;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseMotionListener;
import org.come.until.Music;
import org.come.Jpanel.WorldMapJpanel;
import com.tool.image.ImageMixDeal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.WorldTestsmallmapJpanel;
import javax.swing.JInternalFrame;

public class WorldTestsmallmapJframe extends JInternalFrame
{
    private static WorldTestsmallmapJpanel worldTestsmallmapJpanel;
    private int first_x;
    private int first_y;
    private int cs_x;
    private int cs_y;
    private double smalx;
    private double smaly;
    
    public static WorldTestsmallmapJframe getWorldTestsmallmapJframe() {
        return (WorldTestsmallmapJframe)FormsManagement.getInternalForm(1103).getFrame();
    }
    
    public WorldTestsmallmapJframe() throws IOException {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setContentPane(WorldTestsmallmapJframe.worldTestsmallmapJpanel = new WorldTestsmallmapJpanel(this));
        this.setLocation(150, 170);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.setBackground(new Color(0, 0, 0, 0));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 1) {
                    WorldTestsmallmapJframe this$0 = WorldTestsmallmapJframe.this;
                    WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().pathPoints = ImageMixDeal.userimg.getRoleShow().getPlayer_Paths();
                    int ditubianma = WorldMapJpanel.ditubianma;
                    WorldMapJpanel.iWantToFly(ditubianma + ",1," + (int)WorldTestsmallmapJframe.this.smalx * 20 + "," + (int)WorldTestsmallmapJframe.this.smaly * 20);
                    FormsManagement.getframe(1103).requestFocus();
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isMetaDown()) {
                    FormsManagement.HideForm(1103);
                    FormsManagement.HideForm(633);
                }
                else {
                    MsgJframe4.getJframe4().getJapnel4().xy((int)WorldTestsmallmapJframe.this.smalx, (int)WorldTestsmallmapJframe.this.smaly);
                    FormsManagement.Switchinglevel(1103);
                }
                Music.addyinxiao("关闭窗口.mp3");
                WorldTestsmallmapJframe.this.first_x = e.getX();
                WorldTestsmallmapJframe.this.first_y = e.getY();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                FormsManagement.HideForm(633);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
                WorldTestsmallmapJframe.this.smalx = (double)e.getX() * WorldMapJpanel.mapmodel.getBili_x();
                WorldTestsmallmapJframe.this.smaly = (double)(e.getY() - 35) * WorldMapJpanel.mapmodel.getBili_y();
                if (WorldTestsmallmapJframe.this.smalx >= 0.0 && WorldTestsmallmapJframe.this.smaly >= 0.0 && WorldTestsmallmapJframe.this.smalx <= (double)WorldMapJpanel.mapmodel.getW() && WorldTestsmallmapJframe.this.smaly <= (double)WorldMapJpanel.mapmodel.getH()) {
                    WorldTestsmallmapJframe this$0 = WorldTestsmallmapJframe.this;
                    WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().setDx(e.getX());
                    WorldTestsmallmapJframe this$2 = WorldTestsmallmapJframe.this;
                    WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().setDy(e.getY());
                    int x = (int)WorldTestsmallmapJframe.getWorldTestsmallmapJframe().smalx;
                    int y = (int)WorldTestsmallmapJframe.getWorldTestsmallmapJframe().smaly;
                    if (x > 0 && x < WorldMapJpanel.mapmodel.getW() && y > 0 && y < WorldMapJpanel.mapmodel.getH()) {
                        MsgJframe4.getJframe4().getJapnel4().xy((int)WorldTestsmallmapJframe.this.smalx, (int)WorldTestsmallmapJframe.this.smaly);
                    }
                }
                else {
                    WorldTestsmallmapJframe this$3 = WorldTestsmallmapJframe.this;
                    WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().setDx(2000);
                    WorldTestsmallmapJframe this$4 = WorldTestsmallmapJframe.this;
                    WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().setDy(2000);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (WorldTestsmallmapJframe.this.isVisible()) {
                    int x = e.getX() - WorldTestsmallmapJframe.this.first_x;
                    int y = e.getY() - WorldTestsmallmapJframe.this.first_y;
                    WorldTestsmallmapJframe.this.setBounds(x + WorldTestsmallmapJframe.this.getX(), y + WorldTestsmallmapJframe.this.getY(), WorldTestsmallmapJframe.this.getWidth(), WorldTestsmallmapJframe.this.getHeight());
                }
            }
        });
    }
    
    public static WorldTestsmallmapJpanel getWorldTestsmallmapJpanel() {
        return WorldTestsmallmapJframe.worldTestsmallmapJpanel;
    }
    
    public void setWorldTestsmallmapJpanel(WorldTestsmallmapJpanel worldTestsmallmapJpanel) {
        WorldTestsmallmapJframe.worldTestsmallmapJpanel = worldTestsmallmapJpanel;
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
    
    public int getCs_x() {
        return this.cs_x;
    }
    
    public void setCs_x(int cs_x) {
        this.cs_x = cs_x;
    }
    
    public int getCs_y() {
        return this.cs_y;
    }
    
    public void setCs_y(int cs_y) {
        this.cs_y = cs_y;
    }
}
