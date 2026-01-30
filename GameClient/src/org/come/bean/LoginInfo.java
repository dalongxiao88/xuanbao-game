package org.come.bean;

import org.apache.commons.lang.StringUtils;
import org.come.until.Util;
import org.come.until.GsonUtil;
import org.come.test.Main;
import java.util.ArrayList;
import java.util.List;

public class LoginInfo
{
    private List<LoginRoleInfo> loginRoleInfos;
    
    public void updateLoginRole(LoginRoleInfo roleInfo) {
        if (this.loginRoleInfos == null) {
            this.loginRoleInfos = new ArrayList<>();
        }
        boolean isAdd = true;
        int i = 0;
        while (i < this.loginRoleInfos.size()) {
            if (((LoginRoleInfo)this.loginRoleInfos.get(i)).contrast(Main.frame.getRoleInfo())) {
                this.loginRoleInfos.set(i, roleInfo);
                isAdd = false;
                break;
            }
            else {
                ++i;
            }
        }
        if (isAdd) {
            this.loginRoleInfos.add(0, Main.frame.getRoleInfo());
        }
        i = this.loginRoleInfos.size() - 1;
        if (i > 5) {
            return;
        }
        Util.writeUserInfo(GsonUtil.getGsonUtil().getgson().toJson(this));
    }
    
    public void delCommonlyUsedRoles(LoginRoleInfo loginRoleInfo) {
        for (int i = this.loginRoleInfos.size() - 1; i >= 0; --i) {
            LoginRoleInfo roleInfo = (LoginRoleInfo)this.loginRoleInfos.get(i);
            if (StringUtils.isBlank(roleInfo.getRoleName())) {
                this.loginRoleInfos.remove(i);
            }
            else if (roleInfo.getAccount().equals(loginRoleInfo.getAccount()) && roleInfo.getPassword().equals(loginRoleInfo.getPassword()) && roleInfo.getRoleId().compareTo(loginRoleInfo.getRoleId()) == 0) {
                this.loginRoleInfos.remove(i);
            }
        }
        Util.writeUserInfo(GsonUtil.getGsonUtil().getgson().toJson(this));
    }
    
    public List<LoginRoleInfo> getLoginRoleInfos() {
        return this.loginRoleInfos;
    }
    
    public void setLoginRoleInfos(List<LoginRoleInfo> loginRoleInfos) {
        this.loginRoleInfos = loginRoleInfos;
    }
}
