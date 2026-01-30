package com.tool.btn;

import org.come.until.FormsManagement;
import com.tool.role.RoleData;
import org.come.until.Util;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;

public class GoodAndPetLockBtn extends MoBanBtn
{
    private int locktype;
    
    public GoodAndPetLockBtn(String iconpath, int type, int locktype) {
        super(iconpath, type);
        this.locktype = locktype;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.locktype == 1) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE10);
        }
        else if (this.locktype == 2) {
            if (Util.canBuyOrno) {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE11);
            }
            else if (RoleData.getRoleData().getLoginResult().getPassword() == null) {
                FormsManagement.showForm(32);
            }
            else {
                FormsManagement.showForm(33);
            }
        }
        else if (this.locktype == 3) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE10);
        }
        else if (this.locktype == 4) {
            if (Util.canBuyOrno) {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE11);
            }
            else if (RoleData.getRoleData().getLoginResult().getPassword() == null) {
                FormsManagement.showForm(32);
            }
            else {
                FormsManagement.showForm(33);
            }
        }
        else if (this.locktype == 5) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE10);
        }
        else if (this.locktype == 6) {
            if (Util.canBuyOrno) {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE11);
            }
            else if (RoleData.getRoleData().getLoginResult().getPassword() == null) {
                FormsManagement.showForm(32);
            }
            else {
                FormsManagement.showForm(33);
            }
        }
    }
}
