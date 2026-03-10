package org.come.bean;

import org.come.entity.Gangapplytable;
import java.util.List;
import org.come.entity.GangGroup;
import org.come.entity.Gang;

/**
 * 帮派详情与申请列表返回对象。
 */
public class GangResultBean
{
    private Gang gang;
    private GangGroup gangGroup;
    private List<LoginResult> roleTables;
    private List<Gangapplytable> gangapplytables;
    
    public Gang getGang() {
        return this.gang;
    }
    
    public void setGang(Gang gang) {
        this.gang = gang;
    }
    
    public GangGroup getGangGroup() {
        return this.gangGroup;
    }
    
    public void setGangGroup(GangGroup gangGroup) {
        this.gangGroup = gangGroup;
    }
    
    public List<LoginResult> getRoleTables() {
        return this.roleTables;
    }

    /** 语义化别名：帮派成员列表。 */
    public List<LoginResult> getRoleTableList() {
        return this.roleTables;
    }
    
    public void setRoleTables(List<LoginResult> roleTables) {
        this.roleTables = roleTables;
    }

    public void setRoleTableList(List<LoginResult> roleTableList) {
        this.roleTables = roleTableList;
    }
    
    public List<Gangapplytable> getGangapplytables() {
        return this.gangapplytables;
    }

    /** 语义化别名：帮派申请列表。 */
    public List<Gangapplytable> getGangApplyTables() {
        return this.gangapplytables;
    }
    
    public void setGangapplytables(List<Gangapplytable> gangapplytables) {
        this.gangapplytables = gangapplytables;
    }

    public void setGangApplyTables(List<Gangapplytable> gangApplyTables) {
        this.gangapplytables = gangApplyTables;
    }
}
