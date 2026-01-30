package com.tool.time;

import javax.swing.JLabel;
import java.util.TimerTask;

public class UpdataLimitTimerTask extends TimerTask
{
    private boolean isrun;
    private JLabel residueTime;
    private TitleTimerTask titleTimerTask;
    
    public UpdataLimitTimerTask(JLabel residueTime, TitleTimerTask titleTimerTask) {
        this.isrun = true;
        this.residueTime = residueTime;
        this.titleTimerTask = titleTimerTask;
    }
    
    public void setTitleTimerTask(TitleTimerTask titleTimerTask) {
        this.titleTimerTask = titleTimerTask;
    }
    
    public void setIsrun(boolean isrun) {
        this.isrun = isrun;
    }
    
    @Override
    public void run() {
        while (true) {
            if (this.isrun) {
                String text = this.titleTimerTask.getText();
                this.residueTime.setText(text);
                this.residueTime.updateUI();
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                this.residueTime.setText("");
                this.residueTime.updateUI();
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
