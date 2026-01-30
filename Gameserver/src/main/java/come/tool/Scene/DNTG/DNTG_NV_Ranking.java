package come.tool.Scene.DNTG;

import java.util.ArrayList;
import java.util.List;

public class DNTG_NV_Ranking
{
    private int camp;
    private int size;
    private List<DNTGRole> roles;
    private int min;
    private String rankingSting;
    
    public DNTG_NV_Ranking(int camp) {
        this.camp = camp;
        this.roles = new ArrayList<>();
    }
    
    public synchronized boolean upRanking(DNTGRole role) {
        role.setNVNum(role.getNVNum() + 1);
        ++this.size;
        if (this.min >= role.getNVNum()) {
            return false;
        }
        this.roles.remove(role);
        boolean is = true;
        boolean is2 = false;
        int i = 0;
        while (i < this.roles.size()) {
            DNTGRole dntgRole = (DNTGRole)this.roles.get(i);
            if (dntgRole.getNVNum() < role.getNVNum()) {
                is = false;
                this.roles.add(i, role);
                is2 = true;
                break;
            }
            else {
                ++i;
            }
        }
        if (is) {
            if (this.roles.size() < 5) {
                this.roles.add(role);
                is2 = true;
            }
        }
        else if (this.roles.size() > 5) {
            for (i = this.roles.size() - 1; i >= 5; --i) {
                this.roles.remove(i);
                is2 = true;
            }
        }
        if (is2) {
            this.min = ((DNTGRole)this.roles.get(this.roles.size() - 1)).getNVNum();
            StringBuffer buffer = new StringBuffer();
            buffer.append("N");
            buffer.append(this.camp);
            for (int j = 0; j < this.roles.size(); ++j) {
                DNTGRole dntgRole2 = (DNTGRole)this.roles.get(j);
                if (j != 0) {
                    buffer.append("&");
                }
                buffer.append(dntgRole2.getRoleName());
                buffer.append("$");
                buffer.append(dntgRole2.getNVNum());
            }
            this.rankingSting = buffer.toString();
        }
        return is2;
    }
    
    public int getPlace(DNTGRole role) {
        return this.roles.indexOf(role) + 1;
    }
    
    public DNTGRole getOne() {
        if (this.roles.size() != 0) {
            return (DNTGRole)this.roles.get(0);
        }
        return null;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public List<DNTGRole> getRoles() {
        return this.roles;
    }
    
    public void setRoles(List<DNTGRole> roles) {
        this.roles = roles;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public void setMin(int min) {
        this.min = min;
    }
    
    public String getRankingSting() {
        return this.rankingSting;
    }
    
    public void setRankingSting(String rankingSting) {
        this.rankingSting = rankingSting;
    }
}
