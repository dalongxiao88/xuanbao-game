package com.tool.btn;

import org.come.until.MessagrFlagUntil;
import org.come.Frame.FriendChatMessageJframe;
import com.tool.imagemonitor.PlayerMonitor;
import org.come.entity.TeamRole;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.TeamCreateModelJpanel;
import org.come.Jpanel.TeamCreateJpanel;

public class TeamCreateBtn extends MoBanBtn
{
    private TeamCreateJpanel teamCreateJpanel;
    private TeamCreateModelJpanel teamCreateModelJpanel;
    private int caozuo;
    
    public TeamCreateBtn(String iconpath, int type, int caozuo, TeamCreateJpanel teamCreateJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.teamCreateJpanel = teamCreateJpanel;
    }
    
    public TeamCreateBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, TeamCreateModelJpanel teamCreateModelJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.teamCreateModelJpanel = teamCreateModelJpanel;
    }
    
    public TeamCreateBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, TeamCreateJpanel teamCreateJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.teamCreateJpanel = teamCreateJpanel;
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
            if (ImageMixDeal.userimg.getRoleShow().getCaptian() != 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("只有队长才能发布队伍信息");
                return;
            }
            FormsManagement.HiddenDisplay(19);
        }
        else if (this.caozuo != 1 && this.caozuo == 2) {
            TeamRole teamRole = (TeamRole)this.teamCreateModelJpanel.getTeamBean().getTeams().get(0);
            if (this.getText().equals("加入")) {
                PlayerMonitor.teamApply(teamRole.getRoleId());
            }
            else if (this.getText().equals("交谈")) {
                if (ImageMixDeal.userimg.getRoleShow().getRole_id().compareTo(teamRole.getRoleId()) == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("不能和自己说话");
                    return;
                }
                FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(teamRole, MessagrFlagUntil.getRichLabel(teamRole.getName()));
                FormsManagement.showForm(56);
            }
        }
    }
}
