package org.come.control;

import org.come.Jpanel.TeamArenaMainJpanel;
import org.come.entity.TeamRole;
import com.google.gson.reflect.TypeToken;
import org.come.until.GsonUtil;
import java.util.List;
import org.come.until.FormsManagement;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.Frame.ZhuFrame;
import org.come.Frame.TeamArenaMainJframe;
import org.come.action.FromServerAction;

public class TeamArenaControl implements FromServerAction
{
    private String type;
    
    public TeamArenaControl(String type) {
        this.type = type;
    }
    
    @Override
    public void controlMessFromServer(String mes, String type) {
        Integer typesOfEvents = Integer.valueOf(1);
        if ("laddArena".equals(type)) {
            typesOfEvents = Integer.valueOf(2);
        }
        TeamArenaMainJpanel teamArenaMainJpanel = TeamArenaMainJframe.getTeamArenaMainJframe().getTeamArenaMainJpanel();
        if (mes.startsWith("D")) {
            if (mes.length() > 1) {
                ZhuFrame.getZhuJpanel().addPrompt2(mes.substring(1));
            }
            teamArenaMainJpanel.hideShow();
        }
        else if (mes.startsWith("A")) {
            String listStr = mes.substring(1);
            BigDecimal bigDecimal = new BigDecimal(listStr);
            if (bigDecimal.compareTo(RoleData.getRoleData().getLoginResult().getRole_id()) == 0) {
                teamArenaMainJpanel.btnShow(2, typesOfEvents);
            }
            teamArenaMainJpanel.addCount(bigDecimal);
        }
        else if (mes.startsWith("O")) {
            FormsManagement.showForm(108);
            String listStr = mes.substring(1);
            List<TeamRole> teamRoles = (List)GsonUtil.getGsonUtil().getgson().fromJson(listStr, new TypeToken<List<TeamRole>>() {}.getType());
            teamArenaMainJpanel.showMine(teamRoles, 1);
            teamArenaMainJpanel.btnShow(0, typesOfEvents);
        }
        else if (mes.startsWith("E")) {
            String listStr = mes.substring(1);
            List<TeamRole> teamRoles = (List)GsonUtil.getGsonUtil().getgson().fromJson(listStr, new TypeToken<List<TeamRole>>() {}.getType());
            teamArenaMainJpanel.showMine(teamRoles, 0);
        }
    }
}
