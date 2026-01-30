package come.tool.Role;

import org.come.entity.PayvipBean;
import org.come.server.GameServer;
import org.come.bean.UseCardBean;
import java.util.concurrent.ConcurrentHashMap;

public class PrivateData
{
    private String taskComplete;
    private String taskData;
    private String Skills;
    private String born;
    private String born1;
    private String TimingGood;
    private transient Integer DBExp;
    
    public ConcurrentHashMap<String, UseCardBean> initLimit(long money) {
        ConcurrentHashMap<String, UseCardBean> map = new ConcurrentHashMap<>();
        if (this.TimingGood != null && !this.TimingGood.equals("")) {
            String[] v = this.TimingGood.split("\\^");
            for (int i = 0; i < v.length; ++i) {
                String[] vs = v[i].split("#");
                if (vs.length < 2 || !vs[1].startsWith("单人竞技场")) {
                    map.put(vs[1], new UseCardBean(vs[0], vs[1], vs[2], Long.parseLong(vs[3]) * 60000L, (vs.length > 4) ? vs[4] : null));
                }
            }
        }
        if (money != 0L) {
            PayvipBean vipBean = GameServer.getVIP(money);
            if (vipBean != null) {
                UseCardBean limit = new UseCardBean("VIP" + vipBean.getGrade(), "SVIP", "S" + (19 + (int)vipBean.getGrade()), 0L, vipBean.getIncreationtext());
                map.put(limit.getType(), limit);
                if (this.TimingGood != null && !this.TimingGood.equals("")) {
                    this.TimingGood = this.TimingGood + "^" + limit.getName() + "#" + limit.getType() + "#" + limit.getSkin() + "#" + limit.getTime() + "#" + limit.getValue();
                }
                else {
                    this.TimingGood = limit.getName() + "#" + limit.getType() + "#" + limit.getSkin() + "#" + limit.getTime() + "#" + limit.getValue();
                }
            }
        }
        return map;
    }
    
    public String getTaskComplete() {
        if (this.taskComplete == null) {
            return "";
        }
        return this.taskComplete;
    }
    
    public void setTaskComplete(String taskComplete) {
        this.taskComplete = taskComplete;
    }
    
    public String getTaskData() {
        return this.taskData;
    }
    
    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }
    
    public Integer getDBExp() {
        if (this.DBExp == null) {
            return Integer.valueOf(0);
        }
        return this.DBExp;
    }
    
    public void setDBExp(Integer dBExp) {
        this.DBExp = dBExp;
    }
    
    public String getSkills() {
        if (this.Skills == null) {
            return "";
        }
        return this.Skills;
    }
    
    public void setSkills(String skills) {
        this.Skills = skills;
    }
    
    public String getBorn() {
        if (this.born == null) {
            return "";
        }
        return this.born;
    }
    
    public void setBorn(String born) {
        this.born = born;
    }
    
    public String getTimingGood() {
        return this.TimingGood;
    }
    
    public void setTimingGood(String timingGood) {
        this.TimingGood = timingGood;
    }
    
    public String[] getSkill(String type) {
        if (this.Skills == null || this.Skills.equals("")) {
            return null;
        }
        String[] vs = this.Skills.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].startsWith(type)) {
                return vs[i].substring(1).split("#");
            }
        }
        return null;
    }
    
    public boolean isSkill(String type) {
        if (this.Skills == null || this.Skills.equals("")) {
            return false;
        }
        String[] vs = this.Skills.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].startsWith(type)) {
                return true;
            }
        }
        return false;
    }
    
    public void setSkills(String type, String skill) {
        if (this.Skills == null || this.Skills.equals("")) {
            if (skill != null && !skill.equals("")) {
                this.Skills = type + skill;
            }
            return;
        }
        else {
            StringBuffer buffer = new StringBuffer();
            String[] vs = this.Skills.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (!vs[i].startsWith(type)) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(vs[i]);
                }
            }
            if (skill != null && !skill.equals("")) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(type);
                buffer.append(skill);
            }
            this.Skills = buffer.toString();
            return;
        }
    }
    
    public String getBorn1() {
        return this.born1;
    }
    
    public void setBorn1(String born1) {
        this.born1 = born1;
    }
}
