package com.tool.time;

import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.role.RoleProperty;
import com.tool.role.RoleTX;
import com.tool.tcpimg.RichLabel;
import org.come.bean.LoginResult;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;

import java.awt.*;
import java.util.TimerTask;

public class XYTimerTask extends TimerTask {

    private long midTime;

    private String text;
    private RichLabel richLabel;

    public XYTimerTask(long midTime, RichLabel richLabel) {
        this.midTime = midTime;
        this.richLabel = richLabel;
    }

    public long getMidTime() {
        return midTime;
    }

    public void setMidTime(long midTime) {
        this.midTime = midTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RichLabel getRichLabel() {
        return richLabel;
    }

    public void setRichLabel(RichLabel richLabel) {
        this.richLabel = richLabel;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void run() {

        if(midTime<0){
            richLabel.clearAddText("#Y活动已经结束", new Font("楷体", Font.BOLD, 18));
        }else {
            for (long i = midTime - 1; i >= 0; i--) {
                long dd = (long) Math.floor(i / 60 / 60 / 24);
                long hh = i / 60 / 60 % 24;
                long mm = i / 60 % 60;
                long ss = i % 60;
                text = "活动时间：仅剩#Y" + dd + "#W天#Y" + hh + "#W小时#Y" + mm + "#W分钟#Y" + ss + "#W秒";
                richLabel.clearAddText(text, new Font("楷体", Font.BOLD, 18));
                try {
                    // 倒计时每秒
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
