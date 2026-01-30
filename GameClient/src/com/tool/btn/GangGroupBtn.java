package com.tool.btn;

import org.come.until.FormsManagement;
import org.come.bean.PathPoint;
import org.come.Frame.MsgJframe;
import org.come.Jpanel.GameJpanel;
import com.tool.imagemonitor.NpcMonitor;
import java.awt.event.MouseEvent;

public class GangGroupBtn extends MoBanBtn
{
    private int npcType;
    private int lvl;
    
    public GangGroupBtn(String iconpath, int type, int npcType) {
        super(iconpath, type);
        this.npcType = npcType;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.npcType != 0) {
            NpcMonitor.npc(this.npcType);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (this.npcType == 2021 || this.npcType == 2022 || this.npcType == 2023) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().ewts("等级  " + this.lvl, point.getX(), point.getY());
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (this.npcType == 2021 || this.npcType == 2022 || this.npcType == 2023) {
            FormsManagement.HideForm(46);
        }
    }
    
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
