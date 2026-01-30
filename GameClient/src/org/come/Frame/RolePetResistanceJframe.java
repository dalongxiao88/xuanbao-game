package org.come.Frame;

import org.come.model.InternalForm;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import com.tool.tcpimg.UIUtils;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RolePetResistanceJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

import static org.come.until.FormsManagement.HidePrivilege;
import static org.come.until.FormsManagement.getInternalForm2;

public class RolePetResistanceJframe extends JInternalFrame implements MouseListener,MouseMotionListener
{
    private RolePetResistanceJpanel resistancejpanel;
    private int first_x;
    private int first_y;
    int showType;

    public RolePetResistanceJpanel getResistancejpanel() {
        return this.resistancejpanel;
    }

    public void setResistancejpanel(RolePetResistanceJpanel resistancejpanel) {
        this.resistancejpanel = resistancejpanel;
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

    public static RolePetResistanceJframe getResistancejframe() {
        return (RolePetResistanceJframe)FormsManagement.getInternalForm(58).getFrame();
    }

    public static RolePetResistanceJframe getBaoResistancejframe() {
        return (RolePetResistanceJframe)FormsManagement.getInternalForm(711).getFrame();
    }

    public RolePetResistanceJframe(int showType) throws Exception {
        this.showType = 0;
        this.showType = showType;
        this.resistancejpanel = new RolePetResistanceJpanel(showType);
        this.getContentPane().add(this.resistancejpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(UIUtils.Color_BACK);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(400, 50, 290, 450);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (RolePetResistanceJframe.this.isVisible()) {
                    int x = e.getX() - RolePetResistanceJframe.this.first_x;
                    int y = e.getY() - RolePetResistanceJframe.this.first_y;
                    RolePetResistanceJframe.this.setBounds(x + RolePetResistanceJframe.this.getX(), y + RolePetResistanceJframe.this.getY(), RolePetResistanceJframe.this.getWidth(), RolePetResistanceJframe.this.getHeight());
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
//        if (this.showType == 0) {
//            if (e.isMetaDown()) {
//                FormsManagement.HideForm(58);
//            }
//            else {
//                FormsManagement.Switchinglevel(58);
//            }
//        }
//        else if (e.isMetaDown()) {
//            FormsManagement.HideForm(711);
//        }
//        else {
//            FormsManagement.Switchinglevel(711);
//        }

        InternalForm form = getInternalForm2(711);
        if (form != null && form.HideForm()) {
            if (e.isMetaDown()) {
                FormsManagement.HideForm(711);
            } else {
                FormsManagement.Switchinglevel(711);
            }
        }else {
            if (e.isMetaDown()) {
                FormsManagement.HideForm(58);
            }
            else {
                FormsManagement.Switchinglevel(58);
            }
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
    @Override
    public void mouseDragged(MouseEvent e) {
        if (RolePetResistanceJframe.this.isVisible()) {
            int x = e.getX() - RolePetResistanceJframe.this.first_x;
            int y = e.getY() - RolePetResistanceJframe.this.first_y;
            RolePetResistanceJframe.this.setBounds(x + RolePetResistanceJframe.this.getX(), y + RolePetResistanceJframe.this.getY(), RolePetResistanceJframe.this.getWidth(), RolePetResistanceJframe.this.getHeight());
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
