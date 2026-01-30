package org.come.Frame;

import org.come.Jpanel.AlchemyCardJpanel;
import org.come.until.Music;
import javax.swing.Icon;
import org.come.entity.Goodstable;
import org.come.Jpanel.ZhuJpanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.AlchemyJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AlchemyJframe extends JInternalFrame implements MouseListener
{
    private AlchemyCardJpanel alchemyCardJpanel = new AlchemyCardJpanel();
    private int first_x;
    private int first_y;

    public static AlchemyJframe getAlchemyjframe() {
        return (AlchemyJframe)FormsManagement.getInternalForm(17).getFrame();
    }

    public AlchemyJframe() throws Exception {
        this.setContentPane(this.alchemyCardJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(300, 80, 485, 457);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                if (AlchemyJframe.this.isVisible()) {
                    int x = e.getX() - AlchemyJframe.this.first_x;
                    int y = e.getY() - AlchemyJframe.this.first_y;
                    AlchemyJframe.this.setBounds(x + AlchemyJframe.this.getX(), y + AlchemyJframe.this.getY(), AlchemyJframe.this.getWidth(), AlchemyJframe.this.getHeight());
                }

            }
        });
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            if (ZhuJpanel.getGoodstableAl() != null) {
                ZhuJpanel.setGoodstableAl((Goodstable)null);
                getAlchemyjframe().getalchemyJpanel().getLabRefined().setIcon((Icon)null);
            }

            FormsManagement.HideForm(17);
        } else {
            FormsManagement.Switchinglevel(17);
        }

        Music.addyinxiao("关闭窗口.mp3");
        this.first_x = e.getX();
        this.first_y = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

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

    public AlchemyJpanel getAlchemyjpanel() {
        return AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getAlchemyJpanel();
    }

    public void setAlchemyjpanel(AlchemyJpanel alchemyjpanel) {
    }



    public AlchemyJpanel getalchemyJpanel() {
        return AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getAlchemyJpanel();
    }

    public AlchemyCardJpanel getAlchemyCardJpanel() {
        return alchemyCardJpanel;
    }

    public void setAlchemyCardJpanel(AlchemyCardJpanel alchemyCardJpanel) {
        this.alchemyCardJpanel = alchemyCardJpanel;
    }
}
