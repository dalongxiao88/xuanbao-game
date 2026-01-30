package org.come.Frame;

import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.come.Jpanel.ZhuJpanel;
import come.tool.Fighting.FightingMixDeal;
import org.come.until.Music;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.MsgJapnel5;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MsgJframe5 extends JInternalFrame implements MouseListener
{
    private MsgJapnel5 japnel5;
    private int first_x;
    private int first_y;
    
    public static MsgJframe5 getMsJframe5() {
        return (MsgJframe5)FormsManagement.getInternalForm(634).getFrame();
    }
    
    public MsgJframe5() {
        this.add(this.japnel5 = new MsgJapnel5());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(450, 520, 150, 94);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (MsgJframe5.this.isVisible()) {
                    int x = e.getX() - MsgJframe5.this.first_x;
                    int y = e.getY() - MsgJframe5.this.first_y;
                    MsgJframe5.this.setBounds(x + MsgJframe5.this.getX(), y + MsgJframe5.this.getY(), MsgJframe5.this.getWidth(), MsgJframe5.this.getHeight());
                }
            }
        });
    }
    
    public MsgJapnel5 getJapnel5() {
        return this.japnel5;
    }
    
    public void setJapnel5(MsgJapnel5 japnel5) {
        this.japnel5 = japnel5;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(289);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FightingMixDeal.zdhh = 0;
            System.out.println(FightingMixDeal.zdhh);
            FormsManagement.HideForm(634);
            ZhuFrame.getZhuJpanel().setCaozuo(0);
            ZhuFrame.getZhuJpanel();
            ZhuJpanel.getZidong().setText("自动");
            try {
                if (MyIsif.getStyle().equals("水墨")) {
                    ZhuJpanel.getZidong().setIcons(CutButtonImage.cuts("inkImg/button1/aj1.png"));
                }
                else {
                    ZhuJpanel.getZidong().setIcons(CutButtonImage.cuts("inkImg/hongmu/6026.png"));
                }
            }
            catch (Exception var3) {
                var3.printStackTrace();
            }
        }
        else {
            FormsManagement.Switchinglevel(634);
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
}
