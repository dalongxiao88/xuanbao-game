package org.come.daily;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.model.EventModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpanelDailytask extends JPanel
{
    private JLabel taskName;
    private JLabel taskTime;
    private JLabel taskType;
    private JLabel taskCompletion;
    private JLabel[] taskDifficulty;
    private EventModel eventModel;
    private ImageIcon iconBack;
    
    public JpanelDailytask() {
        this.setPreferredSize(new Dimension(348, 59));
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        this.getTaskName();
        this.getTaskTime();
        this.getTaskType();
        this.getTaskCompletion();
        this.getTaskDifficulty();
    }
    
    public JpanelDailytask(EventModel eventModel) {
        this.setPreferredSize(new Dimension(348, 59));
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        this.getTaskName();
        this.getTaskTime();
        this.getTaskType();
        this.getTaskCompletion();
        this.getTaskDifficulty();
        this.changeView(eventModel);
    }
    
    public void changeView(EventModel eventModel) {
        this.eventModel = eventModel;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/background/S143.png", 348, 59);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 348, 59, this);
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel getTaskName() {
        if (this.taskName == null) {
            (this.taskName = new JLabel()).setBounds(12, 10, 120, 18);
            this.taskName.setForeground(Color.white);
            this.taskName.setFont(UIUtils.TEXT_FONT1);
            this.add(this.taskName);
        }
        return this.taskName;
    }
    
    public void setTaskName(JLabel taskName) {
        this.taskName = taskName;
    }
    
    public JLabel getTaskTime() {
        if (this.taskTime == null) {
            (this.taskTime = new JLabel()).setBounds(135, 10, 110, 18);
            this.taskTime.setForeground(Color.white);
            this.taskTime.setHorizontalAlignment(0);
            this.taskTime.setFont(UIUtils.TEXT_FONT);
            this.add(this.taskTime);
        }
        return this.taskTime;
    }
    
    public void setTaskTime(JLabel taskTime) {
        this.taskTime = taskTime;
    }
    
    public JLabel getTaskType() {
        if (this.taskType == null) {
            (this.taskType = new JLabel()).setBounds(16, 32, 120, 18);
            this.taskType.setForeground(Color.white);
            this.taskType.setFont(UIUtils.TEXT_FONT);
            this.add(this.taskType);
        }
        return this.taskType;
    }
    
    public void setTaskType(JLabel taskType) {
        this.taskType = taskType;
    }
    
    public JLabel getTaskCompletion() {
        if (this.taskCompletion == null) {
            (this.taskCompletion = new JLabel()).setBounds(214, 32, 120, 18);
            this.taskCompletion.setForeground(Color.white);
            this.taskCompletion.setHorizontalAlignment(4);
            this.taskCompletion.setFont(UIUtils.TEXT_FONT);
            this.add(this.taskCompletion);
        }
        return this.taskCompletion;
    }
    
    public void setTaskCompletion(JLabel taskCompletion) {
        this.taskCompletion = taskCompletion;
    }
    
    public JLabel[] getTaskDifficulty() {
        if (this.taskDifficulty == null) {
            this.taskDifficulty = new JLabel[5];
            for (int i = 0; i < this.taskDifficulty.length; ++i) {
                (this.taskDifficulty[i] = new JLabel()).setBounds(334 - 13 * (i + 1), 10, 13, 13);
                this.add(this.taskDifficulty[i]);
            }
        }
        return this.taskDifficulty;
    }
    
    public void setTaskDifficulty(JLabel[] taskDifficulty) {
        this.taskDifficulty = taskDifficulty;
    }
    
    public EventModel getEventModel() {
        return this.eventModel;
    }
    
    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }
}
