package org.come.Frame;

import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.MsgJapnel4;
import org.come.Jpanel.MsgJapnel6;
import org.come.until.FormsManagement;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MsgJframe6 extends JInternalFrame implements MouseListener {
    private MsgJapnel6 japnel6;

    public static MsgJframe6 getJframe6() {
        return (MsgJframe6) FormsManagement.getInternalForm(6333).getFrame();
    }

    public MsgJframe6() {
        this.add((Component) (this.japnel6 = new MsgJapnel6()));
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBounds(0, 0, 470, 310);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }

    public MsgJapnel6 getJapnel6() {
        return japnel6;
    }

    public void setJapnel6(MsgJapnel6 japnel6) {
        this.japnel6 = japnel6;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(6333);
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }
}
