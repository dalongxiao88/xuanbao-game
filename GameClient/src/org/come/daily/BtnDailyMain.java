package org.come.daily;

import org.come.bean.RoleShow;
import com.tool.ModerateTask.TaskData;
import org.come.model.EventModel;
import org.come.Frame.ZhuFrame;
import com.tool.btn.MyAWTEventListener;
import org.come.until.GsonUtil;
import com.tool.tcpimg.InputBean;
import com.tool.image.ImageMixDeal;
import org.come.until.UserMessUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.MoBanBtn;

public class BtnDailyMain extends MoBanBtn
{
    private int caozuo;
    private JpanelDailyMain jpanelDailyMain;
    
    public BtnDailyMain(String iconpath, int type, int caozuo, JpanelDailyMain jpanelDailyMain) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.jpanelDailyMain = jpanelDailyMain;
    }
    
    public BtnDailyMain(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelDailyMain jpanelDailyMain) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.jpanelDailyMain = jpanelDailyMain;
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo <= 10) {
            this.jpanelDailyMain.changeMenuIcon(this.caozuo);
        }
        else if (this.caozuo == 11) {
            EventModel eventModel = this.jpanelDailyMain.getEventModel();
            if (eventModel == null) {
                return;
            }
            String sendmes = Agreement.getFiveMsgAgreement("EG" + eventModel.getgId());
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.caozuo == 12) {
            EventModel eventModel = this.jpanelDailyMain.getEventModel();
            if (eventModel == null) {
                return;
            }
            TaskData taskData = UserMessUntil.getTaskData(eventModel.getTaskId());
            if (taskData == null) {
                return;
            }
            RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            if (ImageMixDeal.userimg.getTeams() != null) {
                if (roleShow.getTroop_id() != null) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("邀请各路英雄一起完成#Y");
                    buffer.append(taskData.getTaskName());
                    buffer.append("#V");
                    InputBean bean = new InputBean(11, roleShow.getRole_id(), "[申请入队]", "G");
                    buffer.append(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    buffer.append("#L");
                    MyAWTEventListener.Mesage(buffer.toString(), "世界");
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("各位大佬带我完成#Y");
                    buffer.append(taskData.getTaskName());
                    buffer.append("#V");
                    InputBean bean = new InputBean(12, roleShow.getRole_id(), "[拉我入队]", "G");
                    buffer.append(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    buffer.append("#L");
                    MyAWTEventListener.Mesage(buffer.toString(), "世界");
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("你是队员无法使用该功能");
            }
        }
        else if (this.caozuo == 13) {
            ZhuFrame.getZhuJpanel().addPrompt2("暂未开放");
        }
    }
}
