package org.come.bean;

import org.come.entity.Car;
import org.come.entity.Mount;
import java.util.List;

public class RoleTransBean
{
    private LoginResult loginResult;
    private PrivateData privateData;
    private RoleAttribute roleAttribute;
    private List<Mount> mounts;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    private List<Car> cars;

    public LoginResult getLoginResult() {
        return this.loginResult;
    }
    
    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }
    
    public PrivateData getPrivateData() {
        return this.privateData;
    }
    
    public void setPrivateData(PrivateData privateData) {
        this.privateData = privateData;
    }
    
    public List<Mount> getMounts() {
        return this.mounts;
    }

    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }
    
    public RoleAttribute getRoleAttribute() {
        return this.roleAttribute;
    }
    
    public void setRoleAttribute(RoleAttribute roleAttribute) {
        this.roleAttribute = roleAttribute;
    }
}
