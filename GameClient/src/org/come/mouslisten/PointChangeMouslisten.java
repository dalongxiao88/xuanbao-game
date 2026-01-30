package org.come.mouslisten;

import com.tool.role.RoleProperty;
import org.come.Jpanel.TestPetJpanel;
import org.come.Frame.TestPetJframe;
import org.come.Jpanel.TeststateJpanel;
import org.come.Frame.ZhuFrame;
import org.come.entity.RoleSummoning;
import org.come.Frame.Teststatejframe;
import org.come.bean.LoginResult;
import org.come.until.UserMessUntil;
import com.tool.role.RoleData;
import org.come.until.FormsManagement;
import org.come.bean.PathPoint;
import org.come.Frame.MsgJframe;
import org.come.Jpanel.GameJpanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointChangeMouslisten implements MouseListener
{
    private int type;
    
    public PointChangeMouslisten(int type) {
        this.type = type;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        this.point(this.type, (e.isShiftDown() ? 10 : (1 + (e.isControlDown() ? 2000 : 0))) * ((this.type % 2 == 0) ? -1 : 1));
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        PathPoint point = GameJpanel.getGameJpanel().mousepath();
        MsgJframe.getJframe().getJapnel().ewts("【按Shift键加减10点】【按Ctrl键加减全部点】", point.getX(), point.getY());
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm(46);
    }
    
    public void point(int type, int point) {
        if (type < 10) {
            this.rolepoint(RoleData.getRoleData().getLoginResult(), type, point);
        }
        else {
            this.petpoint(UserMessUntil.getChosePetMes(), type, point);
        }
    }
    
    public void rolepoint(LoginResult loginResult, int type, int point) {
        type /= 2;
        TeststateJpanel jpanel = Teststatejframe.getTeststatejframe().getTeststateJpanel();
        point = this.addpoint(this.getdz(type, loginResult, (RoleSummoning)null), jpanel.getdian(type), jpanel.getdian(5), point);
        if (point == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("已无法改变点数");
        }
        else {
            jpanel.adddian(type, point);
        }
    }
    
    public void petpoint(RoleSummoning pet, int type, int point) {
        if (pet == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选中的召唤兽");
        }
        else {
            type = type / 2 - 5;
            TestPetJpanel jpanel = TestPetJframe.getTestPetJframe().getTestPetJpanel();
            point = this.addpoint(this.getdz(type, (LoginResult)null, pet), jpanel.getdian(type), jpanel.getdian(5), point);
            if (point == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("已无法改变点数");
            }
            else {
                jpanel.adddian(type, point);
            }
        }
    }
    
    public PointChangeMouslisten() {
    }
    
    public int addpoint(int d, int z, int f, int point) {
        if (d > z + point) {
            point = d - z;
        }
        if (f - point < 0) {
            point = f;
        }
        return point;
    }
    
    public int getdz(int type, LoginResult loginResult, RoleSummoning pet) {
        if (loginResult != null) {
            if (type == 0) {
                type = RoleProperty.getBone(loginResult);
            }
            else if (type == 1) {
                type = RoleProperty.getSpir(loginResult);
            }
            else if (type == 2) {
                type = RoleProperty.getPower(loginResult);
            }
            else if (type == 3) {
                type = RoleProperty.getSpeed(loginResult);
            }
            else if (type == 4) {
                type = RoleProperty.getCalm(loginResult);
            }
        }
        else if (pet != null) {
            if (type == 0) {
                type = (int)pet.getZBone();
            }
            else if (type == 1) {
                type = (int)pet.getZSpir();
            }
            else if (type == 2) {
                type = (int)pet.getZPower();
            }
            else if (type == 3) {
                type = (int)pet.getZSpeed();
            }
            else if (type == 4) {
                type = (int)pet.getZCalm();
            }
        }
        return type;
    }
}
