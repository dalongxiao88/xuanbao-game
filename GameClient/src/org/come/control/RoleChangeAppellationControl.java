package org.come.control;

import com.tool.image.ManimgAttribute;
import com.tool.image.ImageMixDeal;
import org.come.bean.RoleShow;
import org.come.bean.LoginResult;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import com.tool.ModerateTask.TaskRoleData;
import org.come.entity.Titletable;
import org.come.Frame.Change_titleJframe;
import com.tool.role.RoleData;
import org.come.Jpanel.Change_titleJpanel;
import org.come.until.GsonUtil;
import org.come.bean.TitleBean;
import org.come.action.FromServerAction;

public class RoleChangeAppellationControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        int n = -1;
        switch (type.hashCode()) {
            case -2135038218: {
                if (type.equals("titlelist")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 963515400: {
                if (type.equals("titlechange")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                try {
                    this.titleList(mes);
                    break;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            case 1: {
                this.titleChange(mes);
                break;
            }
        }
    }
    
    public void titleList(String mes) throws Exception {
        TitleBean titleBean = Change_titleJpanel.titleBean = (TitleBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, TitleBean.class);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        Change_titleJpanel titleJpanel = Change_titleJframe.getChange_titlejframe().getChange_titlejpanel();
        titleJpanel.getLabnamech().setText(loginResult.getTitle());
        if (titleJpanel.getModelname() != null) {
            titleJpanel.getModelname().clear();
        }
        if (titleBean.getTitletables() != null) {
            for (int i = 0; i < titleBean.getTitletables().size(); ++i) {
                titleJpanel.getModelname().addElement(((Titletable)titleBean.getTitletables().get(i)).getTitlename());
            }
        }
        if (loginResult.getGang_id() != null && loginResult.getGangname() != null && loginResult.getGangpost() != null) {
            titleJpanel.getModelname().addElement(loginResult.getGangname() + loginResult.getGangpost());
        }
        if (loginResult.getMarryObject() != null && !loginResult.getMarryObject().equals("")) {
            if (loginResult.getSex().equals("男")) {
                titleJpanel.getModelname().addElement(loginResult.getMarryObject() + "的老公");
            }
            else if (loginResult.getSex().equals("女")) {
                titleJpanel.getModelname().addElement(loginResult.getMarryObject() + "的老婆");
            }
        }
        double max = loginResult.getKilltype("击杀煞星");
        if (max >= 7.0) {
            titleJpanel.getModelname().addElement("地煞斗士");
        }
        else if (max >= 11.0) {
            titleJpanel.getModelname().addElement("地煞克星");
        }
        else if (max >= 15.0) {
            titleJpanel.getModelname().addElement("地煞战神");
        }
        int jqId = TaskRoleData.SumReceive(110, 3);
        int nowTitle = this.getNowTitle(jqId);
        BigDecimal race_id = loginResult.getRace_id();
        String[] NumTitle;
        if (race_id.compareTo(new BigDecimal(10001)) == 0) {
            NumTitle = new String[] { "武林新丁", "江湖小虾", "后起之秀", "武林高手", "风尘奇侠", "无双隐士", "世外高人", "江湖侠隐", "无敌圣者", "三界贤君" };
        }
        else if (race_id.compareTo(new BigDecimal(10002)) == 0) {
            NumTitle = new String[] { "古灵精怪", "魅力精灵", "魔幻使者", "妖之奇葩", "天神煞星", "万兽妖灵", "混世魔王", "三界妖仙", "魔神至尊", "齐天妖王" };
        }
        else if (race_id.compareTo(new BigDecimal(10003)) == 0) {
            NumTitle = new String[] { "仙灵小童", "逍遥之仙", "陆地飞仙", "无极天师", "神机真人", "降魔金仙", "金身尊者", "太外飞仙", "万圣天尊", "九天圣佛" };
        }
        else {
            NumTitle = new String[] { "阴曹小鬼", "飘渺之魂", "幽冥鬼士", "勾魂使者", "催命判官", "阴阳无常", "阎罗鬼王", "冥灵鬼仙", "九幽神君", "阴都大帝" };
        }
        for (int j = 0; j < nowTitle; ++j) {
            titleJpanel.getModelname().addElement(NumTitle[j]);
        }
        titleJpanel.getModelname().addElement("御武盟小虾米");
        FormsManagement.showForm(10);
    }
    
    public int getNowTitle(int jqId) {
        if (jqId >= 3265) {
            return 9;
        }
        if (jqId >= 3253) {
            return 8;
        }
        if (jqId >= 3239) {
            return 7;
        }
        if (jqId >= 3227) {
            return 6;
        }
        if (jqId >= 3215) {
            return 5;
        }
        if (jqId >= 3204) {
            return 4;
        }
        if (jqId >= 3187) {
            return 3;
        }
        if (jqId >= 3178) {
            return 2;
        }
        if (jqId >= 3166) {
            return 1;
        }
        return 0;
    }
    
    public void titleChange(String mes) {
        RoleShow roleShow = (RoleShow)GsonUtil.getGsonUtil().getgson().fromJson(mes, RoleShow.class);
        if (ImageMixDeal.userimg.getRoleShow().getRole_id().compareTo(roleShow.getRole_id()) == 0) {
            ImageMixDeal.userimg.getRoleShow().setTitle(roleShow.getTitle());
            ImageMixDeal.userimg.setAppellation(roleShow.getTitle());
        }
        else {
            ((ManimgAttribute)ImageMixDeal.Playerimgmap.get(roleShow.getRolename())).getRoleShow().setTitle(roleShow.getTitle());
            ((ManimgAttribute)ImageMixDeal.Playerimgmap.get(roleShow.getRolename())).setAppellation(roleShow.getTitle());
        }
        FormsManagement.HideForm(10);
    }
}
