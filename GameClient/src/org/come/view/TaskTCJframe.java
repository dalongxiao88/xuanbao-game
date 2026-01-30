package org.come.view;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import org.come.until.ScrenceUntil;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TaskTCJframe extends JInternalFrame implements MouseListener
{
    private TaskTCView taskTCView;
    private int first_x;
    private int first_y;
    
    public static TaskTCJframe getTaskTCJframe() {
        return (TaskTCJframe)FormsManagement.getInternalForm(642).getFrame();
    }
    
    public TaskTCJframe() throws Exception {
        this.add(this.taskTCView = new TaskTCView());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(ScrenceUntil.Screen_x / 2 - 267, (int)((double)ScrenceUntil.Screen_y / 2.8), 534, 144);
        this.taskTCView.setBounds(0, 0, 534, 144);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TaskTCJframe.this.isVisible()) {
                    int x = e.getX() - TaskTCJframe.this.first_x;
                    int y = e.getY() - TaskTCJframe.this.first_y;
                    TaskTCJframe.this.setBounds(x + TaskTCJframe.this.getX(), y + TaskTCJframe.this.getY(), TaskTCJframe.this.getWidth(), TaskTCJframe.this.getHeight());
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
            FormsManagement.HideForm(642);
        }
        else {
            FormsManagement.Switchinglevel(642);
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
    
    public TaskTCView getTaskTCView() {
        return this.taskTCView;
    }
    
    public void setTaskTCView(TaskTCView taskTCView) {
        this.taskTCView = taskTCView;
    }
}
