package org.come.control;

import java.util.List;
import org.come.model.InternalForm;
import org.come.Frame.TeamJframe;
import com.tool.image.ManimgAttribute;
import java.math.BigDecimal;
import org.come.bean.RoleShow;
import org.come.Frame.TeamCreateJframe;
import org.come.Frame.ZhuFrame;
import org.come.Frame.TeamApplyJframe;
import org.come.until.FormsManagement;
import org.come.entity.TeamRole;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import org.come.until.GsonUtil;
import org.come.bean.TeamBean;
import org.come.Jpanel.ZhuJpanel;
import org.come.action.FromServerAction;

public class TeamControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (type.equals("team1")) {
            ZhuJpanel.removerTXK();
            TeamBean teamBean = (TeamBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, TeamBean.class);
            RoleData.getRoleData().setTeamBean(teamBean);
            this.SX(ImageMixDeal.userimg.getRoleShow(), teamBean);
        }
        else if (type.equals("team2")) {
            TeamRole teamRole = (TeamRole)GsonUtil.getGsonUtil().getgson().fromJson(mes, TeamRole.class);
            if (FormsManagement.getInternalForm2(30) != null && FormsManagement.getframe(30).isVisible()) {
                TeamApplyJframe.getTeamApplyJframe().getTeamApplyJpanel().showTeamRole(teamRole);
                return;
            }
            ZhuFrame.getZhuJpanel().setTeamState(1);
        }
        else if (type.equals("team3")) {
            RoleShow role = ImageMixDeal.userimg.getRoleShow();
            String name = "";
            if (role.getTeamInfo() != null) {
                name = role.getTeamInfo().split("\\|")[0];
            }
            String[] teams = mes.split("#");
            if (teams[1].split("\\|")[0].equals(role.getRolename()) || (teams.length > 2 && (teams[2].split("&")[0].equals(role.getRolename()) || teams[2].split("&")[0].equals(name)))) {
                ZhuJpanel.removerTXK();
            }
            this.team3(mes);
        }
        else if (type.equals("team4")) {
            this.team4(mes);
        }
        else if (type.equals("team6")) {
            TeamRoleList tBean = (TeamRoleList)GsonUtil.getGsonUtil().getgson().fromJson(mes, TeamRoleList.class);
            TeamApplyJframe.getTeamApplyJframe().getTeamApplyJpanel().showTeamRoleList(tBean.t);
        }
        else if (type.equals("enlist")) {
            TeanBeanList tBean2 = (TeanBeanList)GsonUtil.getGsonUtil().getgson().fromJson(mes, TeanBeanList.class);
            TeamCreateJframe.getTeamCreateJframe().getTeamCreateJpanel().initializeJlist(tBean2.t);
        }
    }
    
    public void team4(String mes) {
        TeamBean teamBean = RoleData.getRoleData().getTeamBean();
        if (teamBean == null) {
            return;
        }
        TeamRole teamRole = (TeamRole)GsonUtil.getGsonUtil().getgson().fromJson(mes, TeamRole.class);
        int type = 0;
        if (teamRole.getState() == -3) {
            type = 1;
        }
        else if (teamRole.getState() == 1 && ((TeamRole)teamBean.getTeams().get(0)).getRoleId().compareTo(teamRole.getRoleId()) != 0) {
            type = 2;
        }
        if (teamBean.upTeamRole(teamRole, type)) {}
        this.SX(ImageMixDeal.userimg.getRoleShow(), teamBean);
    }
    
    public void team3(String mes) {
        String[] v = mes.split("#");
        BigDecimal teamId = new BigDecimal(v[0]);
        if (!v[1].equals("null")) {
            String[] vs = v[1].split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                ManimgAttribute attribute = ImageMixDeal.huoquLogin(vs[i]);
                if (attribute != null) {
                    attribute.getRoleShow().setTroop_id(teamId);
                    attribute.getRoleShow().setTeamInfo(v[1]);
                    attribute.initTeam();
                }
            }
        }
        for (int j = 2; j < v.length; ++j) {
            String[] sv = v[j].split("&");
            ManimgAttribute attribute = ImageMixDeal.huoquLogin(sv[0]);
            if (attribute != null) {
                int state = Integer.parseInt(sv[1]);
                if (state == -3) {
                    attribute.getRoleShow().setTroop_id(null);
                    if (attribute == ImageMixDeal.userimg) {
                        RoleData.getRoleData().setTeamBean(null);
                        this.SX(ImageMixDeal.userimg.getRoleShow(), null);
                    }
                }
                else {
                    attribute.getRoleShow().setTroop_id(teamId);
                }
                attribute.getRoleShow().setTeamInfo(null);
                attribute.initTeam();
            }
        }
    }
    
    public void SX(RoleShow roleShow, TeamBean teamBean) {
        InternalForm internalForm = FormsManagement.getInternalForm2(13);
        if (internalForm != null && internalForm.getFrame().isVisible()) {
            if (teamBean != null) {
                TeamJframe.getTeamJframe().getTeamjpanel().show(roleShow, teamBean);
            }
            else {
                FormsManagement.HideForm(13);
            }
        }
        ZhuFrame.getZhuJpanel().SXTeam(roleShow, teamBean);
    }
    
    class TeamRoleList
    {
        private List<TeamRole> t;
    }
    
    class TeanBeanList
    {
        private List<TeamBean> t;
    }
}
