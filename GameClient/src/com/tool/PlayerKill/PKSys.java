package com.tool.PlayerKill;

import org.come.bean.LoginResult;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.Middle;
import org.come.Frame.ZhuFrame;
import org.come.until.Util;
import com.tool.role.RoleData;

public class PKSys
{
    private static PKSys pkSys;
    private int pk1;
    private int pk2;
    private int pk3;
    private int pk4;
    private long JailTime;
    
    public PKSys() {
        this.pk1 = 0;
        this.pk2 = 0;
        this.pk3 = 0;
        this.pk4 = 0;
    }
    
    public static PKSys getPkSys() {
        if (PKSys.pkSys == null) {
            initial();
        }
        return PKSys.pkSys;
    }
    
    public void upPK(String taskDaily) {
        try {
            RoleData.getRoleData().getLoginResult().setTaskDaily(taskDaily);
            String[] v = RoleData.getRoleData().getLoginResult().getTaskDaily().split("\\|");
            this.pk1 = Integer.parseInt(v[0]);
            this.pk2 = Integer.parseInt(v[1]);
            this.pk3 = Integer.parseInt(v[2]);
            this.pk4 = Integer.parseInt(v[3]);
        }
        catch (Exception ex) {}
    }
    
    public static void initial() {
        PKSys.pkSys = new PKSys();
        try {
            String[] v = RoleData.getRoleData().getLoginResult().getTaskDaily().split("\\|");
            PKSys.pkSys.pk1 = Integer.parseInt(v[0]);
            PKSys.pkSys.pk2 = Integer.parseInt(v[1]);
            PKSys.pkSys.pk3 = Integer.parseInt(v[2]);
            PKSys.pkSys.pk4 = Integer.parseInt(v[3]);
        }
        catch (Exception ex) {}
    }
    
    public void isexpiation() {
        if (this.pk1 <= 0) {
            return;
        }
        this.JailTime += 75000L;
        if (this.pk2 != 0) {
            if (PKMixdeal.getJailTime(this.pk4) < this.JailTime) {
                this.expiation();
            }
        }
        else if (Util.CREEPSMAP && PKMixdeal.YW < this.JailTime) {
            this.expiation();
        }
    }
    
    public void expiation() {
        this.JailTime = 0L;
        --this.pk1;
        ZhuFrame.getZhuJpanel().addPrompt2("你减少了一点pk标识你还有#G " + this.pk1 + " #Y点PK标志");
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        loginResult.setTaskDaily(this.splicing());
        Middle middle = new Middle();
        middle.setRolename(loginResult.getRolename());
        middle.setTaskDaily(loginResult.getTaskDaily());
        String mes = Agreement.getAgreement().MiddleAgreement(GsonUtil.getGsonUtil().getgson().toJson(middle));
        SendMessageUntil.toServer(mes);
    }
    
    public String splicing() {
        return PKSys.pkSys.pk1 + "|" + PKSys.pkSys.pk2 + "|" + PKSys.pkSys.pk3 + "|" + PKSys.pkSys.pk4;
    }
    
    public int getPk1() {
        return this.pk1;
    }
    
    public void setPk1(int pk1) {
        this.pk1 = pk1;
    }
    
    public int getPk2() {
        return this.pk2;
    }
    
    public void setPk2(int pk2) {
        this.pk2 = pk2;
    }
    
    public int getPk3() {
        return this.pk3;
    }
    
    public void setPk3(int pk3) {
        this.pk3 = pk3;
    }
    
    public int getPk4() {
        return this.pk4;
    }
    
    public void setPk4(int pk4) {
        this.pk4 = pk4;
    }
}
