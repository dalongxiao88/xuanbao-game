package com.tool.fuben;

import com.tool.btn.MoBanBtn;
import com.tool.imagemonitor.NpcMonitor;
import org.come.bean.NpcInfoBean;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.UserMessUntil;

import java.awt.*;
import java.awt.event.MouseEvent;

public class fubenBtn extends MoBanBtn {
    private int caozuo;
    private FuBenJpanel fubenJpanel;


    public fubenBtn(String iconpath, int type, Color[] colors, Font font, String text,FuBenJpanel fubenJpanel) {
        super(iconpath, type, colors);
        this.type=type;
        this.fubenJpanel = fubenJpanel;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }

    public void chooseyes() {
    }

    public void chooseno() {
    }

    public void nochoose(MouseEvent e) {
        TaskList taskList = fubenJpanel.getTaskLists().get(FuBenJpanel.index);
        if (taskList == null) {
            return;
        }
        if (this.getText().equals("领取任务")) {//领取任务
            NpcInfoBean infoBean = UserMessUntil.getnpc(taskList.getNPCID());
            NpcMonitor.npc(infoBean);
        }else if (this.getText().equals("领取奖励")) {//领取奖励

            String mes = Agreement.getAgreement().fubenAgreement(taskList.getTaskID()+"");
            SendMessageUntil.toServer(mes);
        }
    }
}
