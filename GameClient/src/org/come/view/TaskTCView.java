package org.come.view;

import com.tool.tcpimg.RichLabel;
import java.util.List;
import com.tool.ModerateTask.Task;
import com.tool.ModerateTask.Hero;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TaskTCView extends JPanel
{
    ImageIcon icon;
    
    public TaskTCView() {
        this.icon = null;
        this.setPreferredSize(new Dimension(534, 144));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/B234.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 534, 144, this);
        List<Task> tasks = Hero.getList();
        if (tasks != null && tasks.size() > 0) {
            Task latestTask = (Task)tasks.get(tasks.size() - 1);
            RichLabel richLabel = latestTask.getRichLabel1();
            if (richLabel != null) {
                g.translate(10, 10);
                richLabel.paint(g);
            }
        }
    }
}
