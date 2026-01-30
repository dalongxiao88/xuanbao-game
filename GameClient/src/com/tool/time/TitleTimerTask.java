package com.tool.time;

import org.come.bean.LoginResult;
import com.tool.role.RoleTX;
import com.tool.role.RoleProperty;
import com.tool.image.ImageMixDeal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import java.util.TimerTask;

public class TitleTimerTask extends TimerTask
{
    private long midTime;
    private String titleName;
    private String text;
    
    public TitleTimerTask(long midTime, String titleName) {
        this.midTime = midTime;
        this.titleName = titleName;
    }
    
    public String getText() {
        return this.text;
    }
    
    @Override
    public void run() {
        for (long i = this.midTime - 1L; i >= 0L; --i) {
            long dd = (long)Math.floor((double)(i / 60L / 60L / 24L));
            long hh = i / 60L / 60L % 24L;
            long mm = i / 60L % 60L;
            long ss = i % 60L;
            this.text = "还剩" + dd + "天" + hh + "小时" + mm + "分钟" + ss + "秒";
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = "已经过期";
        LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();
        String currentTitle = loginResult2.getTitle();
        if (currentTitle != null && currentTitle.equals(this.titleName)) {
            String sendmes = Agreement.getAgreement().TitleExpireAgreement(currentTitle);
            SendMessageUntil.toServer(sendmes);
            ImageMixDeal.userimg.getRoleShow().setTitle("");
            RoleData.getRoleData().getLoginResult().setTitle("");
            RoleProperty.ResetEw();
            RoleTX.getRoleTX().skin();
            loginResult2.setTitle("");
        }
    }
}
