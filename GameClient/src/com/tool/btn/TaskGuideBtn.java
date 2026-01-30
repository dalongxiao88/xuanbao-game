package com.tool.btn;

import java.awt.event.MouseEvent;
import org.come.view.TaskGuideView;

public class TaskGuideBtn extends MoBanBtn
{
    private int caozuo;
    private TaskGuideView taskGuideView;
    
    public TaskGuideBtn(String iconpath, int type, int caozuo, TaskGuideView taskGuideView) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.taskGuideView = taskGuideView;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        this.taskGuideView.guideShow(this.caozuo);
        if (this.caozuo == 1) {
            this.taskGuideView.DJ(e.getX(), e.getY());
        }
    }
}
