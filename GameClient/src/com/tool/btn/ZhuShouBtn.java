package com.tool.btn;

import org.come.thread.TimeControlRunnable;
import org.come.until.CutButtonImage;
import java.awt.event.MouseEvent;
import org.come.Jpanel.AutoTaskJpanel;

public class ZhuShouBtn extends MoBanBtn
{
    private boolean isStart;
    private final AutoTaskJpanel autoTaskJpanel;
    private final int caozuo;
    
    public ZhuShouBtn(String iconpath, int type, int caozuo, AutoTaskJpanel autoTaskJpanel) {
        super(iconpath, type);
        this.isStart = false;
        this.caozuo = caozuo;
        this.autoTaskJpanel = autoTaskJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 0) {
            this.isStart = !this.isStart;
            try {
                if (this.isStart) {
                    this.setIcons(CutButtonImage.cuts("Inkimg/hongmu1/B459.png"));
                    TimeControlRunnable.addAllTask(this.autoTaskJpanel.getAllActive());
                }
                else {
                    this.setIcons(CutButtonImage.cuts("Inkimg/hongmu1/B458.png"));
                    TimeControlRunnable.removeScript(true);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setStart(boolean isStart) {
        this.isStart = isStart;
        try {
            if (isStart) {
                this.setIcons(CutButtonImage.cuts("Inkimg/hongmu1/B459.png"));
            }
            else {
                this.setIcons(CutButtonImage.cuts("Inkimg/hongmu1/B458.png"));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
