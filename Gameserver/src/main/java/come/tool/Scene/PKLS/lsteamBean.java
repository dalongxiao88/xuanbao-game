package come.tool.Scene.PKLS;

import org.come.entity.Baby;
import org.come.entity.Fly;
import org.come.entity.Mount;
import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import org.come.entity.UserTable;
import org.come.bean.LoginResult;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import come.tool.Scene.LTS.UserData;
import java.util.List;

public class lsteamBean
{
    private List<LSTeam> LSTeams;
    private List<UserData> userDatas;
    private String teams;
    
    public void initUserData() {
        System.out.println("读取玩家报名数据");
        this.userDatas = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.LSTeams.size(); ++i) {
            if (buffer.length() != 0) {
                buffer.append("\n");
            }
            LSTeam lsTeam = (LSTeam)this.LSTeams.get(i);
            for (int j = 0; j < lsTeam.getRoleids().length; ++j) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
                if (j != 0) {
                    buffer.append("|");
                }
                BigDecimal role_id = lsTeam.getRoleids()[j];
                LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleID(role_id);
                buffer.append(loginResult.getRolename());
                UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(loginResult.getUser_id());
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex2) {}
                List<Goodstable> goodstables = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(role_id);
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex3) {}
                List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(role_id);
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex4) {}
                List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectLingbaoByRoleID(role_id);
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex5) {}
                List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(role_id);
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex6) {}
                List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(role_id);
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex7) {}
                List<Baby> babys = AllServiceUtil.getBabyService().selectBabyByRolename(role_id);
                UserData userData = new UserData(userTable, loginResult, goodstables, pets, mounts, lingbaos, babys, flys);
                this.userDatas.add(userData);
            }
        }
        this.teams = buffer.toString();
    }
    
    public List<LSTeam> getLSTeams() {
        return this.LSTeams;
    }
    
    public void setLSTeams(List<LSTeam> lSTeams) {
        this.LSTeams = lSTeams;
    }
    
    public List<UserData> getUserDatas() {
        return this.userDatas;
    }
    
    public void setUserDatas(List<UserData> userDatas) {
        this.userDatas = userDatas;
    }
    
    public String getTeams() {
        return this.teams;
    }
    
    public void setTeams(String teams) {
        this.teams = teams;
    }
}
