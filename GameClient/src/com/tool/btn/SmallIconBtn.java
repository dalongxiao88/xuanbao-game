package com.tool.btn;

import org.come.until.MessagrFlagUntil;
import org.come.Frame.ChatHistoryJframe;
import org.come.until.FormsManagement;
import come.tool.Fighting.FightingMixDeal;
import org.come.Jpanel.FrameMessageChangeJpanel;
import java.awt.event.MouseEvent;
import org.come.Jpanel.ZhuJpanel;

public class SmallIconBtn extends MoBanBtn
{
    private ZhuJpanel zhuJpanel;
    private String text;
    private int caozuo;
    
    public SmallIconBtn(String iconpath, int type, int caozuo, String text, ZhuJpanel zhuJpanel) {
        super(iconpath, type);
        this.zhuJpanel = zhuJpanel;
        this.text = text;
        this.caozuo = caozuo;
    }
    
    public SmallIconBtn(String iconpath, int type, int caozuo, String text, int num, ZhuJpanel zhuJpanel) {
        super(iconpath, type);
        this.zhuJpanel = zhuJpanel;
        this.text = text;
        this.caozuo = caozuo;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.text.equals("向左")) {
            FrameMessageChangeJpanel.chatbox.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("向上")) {
            FrameMessageChangeJpanel.chatbox.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("向下")) {
            FrameMessageChangeJpanel.chatbox.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("向上1")) {
            FrameMessageChangeJpanel.chatbox1.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("向下1")) {
            FrameMessageChangeJpanel.chatbox1.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("向上2")) {
            FrameMessageChangeJpanel.chatbox2.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("向下2")) {
            FrameMessageChangeJpanel.chatbox2.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("星星")) {
            FrameMessageChangeJpanel.chatbox.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("加号")) {
            FrameMessageChangeJpanel.chatbox.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("停止")) {
            FrameMessageChangeJpanel.chatbox.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("减号")) {
            FrameMessageChangeJpanel.chatbox.AddAndSubtract(this.caozuo);
        }
        else if (this.text.equals("添加") && this.caozuo == 100 && FightingMixDeal.State == 0) {
            this.addFriend();
        }
        else if (!this.text.equals("删除") || this.caozuo != 100 || FightingMixDeal.State != 0) {
            if (this.text.equals("查找") && this.caozuo == 101 && FightingMixDeal.State == 0) {
                FormsManagement.showForm(75);
            }
            else if (this.text.equals("清屏2") || this.text.equals("清屏1")) {
                FrameMessageChangeJpanel.background();
            }
            else if (this.text.equals("记录")) {
                ChatHistoryJframe.getChatHistoryJframe().getChatHistoryJpanel().updateNew();
                FormsManagement.showForm(708);
            }
            else if (this.text.equals("开关")) {
                ChatHistoryJframe.getChatHistoryJframe().getChatHistoryJpanel().updateNew();
                FormsManagement.showForm(709);
            }
        }
    }
    
    public void addFriend() {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE4);
    }
}
