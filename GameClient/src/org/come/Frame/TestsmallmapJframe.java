package org.come.Frame;

import java.util.List;
import java.io.IOException;
import org.come.until.Util;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseMotionListener;
import org.come.until.Music;
import com.tool.image.ImageMixDeal;
import org.come.mouslisten.Mouselistener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TestsmallmapJpanel;
import javax.swing.JInternalFrame;

public class TestsmallmapJframe extends JInternalFrame
{
    private TestsmallmapJpanel testsmallmapJpanel;
    private int first_x;
    private int first_y;
    private double smalx;
    private double smaly;
    
    public static TestsmallmapJframe getTestsmallmapJframe() {
        return (TestsmallmapJframe)FormsManagement.getInternalForm(22).getFrame();
    }
    
    public TestsmallmapJframe() throws IOException {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setContentPane(this.testsmallmapJpanel = new TestsmallmapJpanel(this));
        this.setLocation(150, 170);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.setBackground(new Color(0, 0, 0, 0));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 1) {
                    Mouselistener.Pathfinding((int)TestsmallmapJframe.this.smalx, (int)TestsmallmapJframe.this.smaly);
                    TestsmallmapJframe.this.getTestsmallmapJpanel().pathPoints = ImageMixDeal.userimg.getRoleShow().getPlayer_Paths();
                    FormsManagement.getframe(22).requestFocus();
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isMetaDown()) {
                    FormsManagement.HideForm(22);
                    FormsManagement.HideForm(633);
                }
                else {
                    MsgJframe4.getJframe4().getJapnel4().xy((int)TestsmallmapJframe.this.smalx, (int)TestsmallmapJframe.this.smaly);
                    FormsManagement.Switchinglevel(22);
                }
                Music.addyinxiao("关闭窗口.mp3");
                TestsmallmapJframe.this.first_x = e.getX();
                TestsmallmapJframe.this.first_y = e.getY();
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
                TestsmallmapJframe.this.smalx = (double)e.getX() * Util.mapmodel.getBili_x();
                TestsmallmapJframe.this.smaly = (double)(e.getY() - 35) * Util.mapmodel.getBili_y();
                if (TestsmallmapJframe.this.smalx >= 0.0 && TestsmallmapJframe.this.smaly >= 0.0 && TestsmallmapJframe.this.smalx <= (double)Util.mapmodel.getW() && TestsmallmapJframe.this.smaly <= (double)Util.mapmodel.getH()) {
                    TestsmallmapJframe.this.getTestsmallmapJpanel().setDx(e.getX());
                    TestsmallmapJframe.this.getTestsmallmapJpanel().setDy(e.getY());
                    int x = (int)TestsmallmapJframe.getTestsmallmapJframe().smalx;
                    int y = (int)TestsmallmapJframe.getTestsmallmapJframe().smaly;
                    if (x > 0 && x < Util.mapmodel.getW() && y > 0 && y < Util.mapmodel.getH()) {
                        MsgJframe4.getJframe4().getJapnel4().xy((int)TestsmallmapJframe.this.smalx, (int)TestsmallmapJframe.this.smaly);
                    }
                }
                else {
                    TestsmallmapJframe.this.getTestsmallmapJpanel().setDx(2000);
                    TestsmallmapJframe.this.getTestsmallmapJpanel().setDy(2000);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TestsmallmapJframe.this.isVisible()) {
                    int x = e.getX() - TestsmallmapJframe.this.first_x;
                    int y = e.getY() - TestsmallmapJframe.this.first_y;
                    TestsmallmapJframe.this.setBounds(x + TestsmallmapJframe.this.getX(), y + TestsmallmapJframe.this.getY(), TestsmallmapJframe.this.getWidth(), TestsmallmapJframe.this.getHeight());
                }
            }
        });
    }
    
    public TestsmallmapJpanel getTestsmallmapJpanel() {
        return this.testsmallmapJpanel;
    }
    
    public void setTestsmallmapJpanel(TestsmallmapJpanel testsmallmapJpanel) {
        this.testsmallmapJpanel = testsmallmapJpanel;
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
