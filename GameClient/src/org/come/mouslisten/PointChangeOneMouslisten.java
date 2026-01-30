package org.come.mouslisten;

import org.come.Jpanel.RoleToggleJpanel;
import org.come.Frame.ZhuFrame;
import org.come.Frame.RoleToggleJframe;
import org.come.bean.LoginResult;
import org.come.bean.RoleAttribute;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import org.come.until.FormsManagement;
import org.come.bean.PathPoint;
import org.come.Frame.MsgJframe;
import org.come.Jpanel.GameJpanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointChangeOneMouslisten implements MouseListener
{
    private int type;
    
    public PointChangeOneMouslisten(int type) {
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
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            RoleAttribute roleAttribute = RoleData.getRoleData().getRoleAttribute();
            int lvl = AnalysisString.lvlint((int)loginResult.getGrade());
            if (roleAttribute == null) {
                roleAttribute = new RoleAttribute();
                if (roleAttribute.getBONEONE() == null) {
                    roleAttribute.setBONEONE(Integer.valueOf(lvl));
                    roleAttribute.setSPIRONE(Integer.valueOf(lvl));
                    roleAttribute.setPOWERONE(Integer.valueOf(lvl));
                    roleAttribute.setSPEEDONE(Integer.valueOf(lvl));
                    if (loginResult.getTurnAround() >= 4) {
                        roleAttribute.setCALMONE(Integer.valueOf(lvl));
                    }
                }
                if (roleAttribute.getBONETWO() == null) {
                    roleAttribute.setBONETWO(Integer.valueOf(lvl));
                    roleAttribute.setSPIRTWO(Integer.valueOf(lvl));
                    roleAttribute.setPOWERTWO(Integer.valueOf(lvl));
                    roleAttribute.setSPEEDTWO(Integer.valueOf(lvl));
                    if (loginResult.getTurnAround() >= 4) {
                        roleAttribute.setCALMTWO(Integer.valueOf(lvl));
                    }
                }
            }
            this.rolepoint(roleAttribute, type, point);
        }
    }
    
    public void rolepoint(RoleAttribute roleAttribute, int type, int point) {
        type /= 2;
        RoleToggleJpanel jpanel = RoleToggleJframe.getRoleToggleJframe().getRoleToggleJpanel();
        point = this.addpoint(this.getdz1(type, roleAttribute), jpanel.getdian1(type), jpanel.getdian1(5), point);
        if (point == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("已无法改变点数");
        }
        else {
            jpanel.adddian1(type, point);
        }
    }
    
    public PointChangeOneMouslisten() {
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
    
    public int getdz1(int type, RoleAttribute roleAttribute) {
        if (roleAttribute != null) {
            if (type == 0) {
                type = (int)roleAttribute.getBONEONE();
            }
            else if (type == 1) {
                type = (int)roleAttribute.getSPIRONE();
            }
            else if (type == 2) {
                type = (int)roleAttribute.getPOWERONE();
            }
            else if (type == 3) {
                type = (int)roleAttribute.getSPEEDONE();
            }
            else if (type == 4) {
                type = (int)roleAttribute.getCALMONE();
            }
        }
        return type;
    }
}
