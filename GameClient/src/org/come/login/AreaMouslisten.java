package org.come.login;

import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.LoginUserBean;
import org.come.action.MapAction;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.until.Util;
import org.come.socket.DownLoadTxt;
import org.come.socket.SendMessageUntil;
import org.come.socket.GameClient;
import org.come.test.Main;
import java.awt.event.MouseEvent;
import org.come.entity.RegionResult;
import org.come.bean.LoginRoleInfo;
import java.math.BigDecimal;
import java.awt.event.MouseListener;

public class AreaMouslisten implements MouseListener
{
    private int caozuo;
    private BigDecimal i;
    private SpriteBtn btn;
    private LoginJpanel loginJpanel;
    private LoginRoleInfo roleInfo;
    private RegionResult area;
    private boolean isClicked;
    
    public AreaMouslisten(BigDecimal i, SpriteBtn btn, LoginJpanel loginJpanel, RegionResult area) {
        this.isClicked = false;
        this.i = i;
        this.btn = btn;
        this.loginJpanel = loginJpanel;
        this.area = area;
    }
    
    public AreaMouslisten(SpriteBtn btn, LoginJpanel loginJpanel, LoginRoleInfo roleInfo, int type) {
        this.isClicked = false;
        this.btn = btn;
        this.loginJpanel = loginJpanel;
        this.roleInfo = roleInfo;
        this.caozuo = type;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.btn != null) {
            if (this.btn.isChoose()) {
                if (this.btn.getZhen() != 2) {
                    this.btn.btn(2);
                }
            }
            else {
                this.btn.btn(2);
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!this.isClicked && this.btn != null) {
            if (!this.btn.isChoose()) {
                this.btn.btn(0);
                this.dianJi();
            }
            this.isClicked = true;
        }
        else if (!this.isClicked) {
            this.dianJi();
            this.isClicked = true;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.btn != null) {
            if (this.btn.getZhen() != 2) {
                this.btn.btn(1);
            }
            else {
                this.btn.btn(0);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.btn != null) {
            if (this.btn.getZhen() != 2) {
                this.btn.btn(0);
            }
            else {
                this.btn.btn(1);
            }
        }
    }
    
    private void dianJi() {
        try {
            if (this.area != null) {
                Main.frame.getLoginJpanel().getAreaView().getLabMsgTip().setText("");
                GameClient.BT = this.area.getRA_NAME();
                Main.frame.getLoginJpanel().setRa_id(this.area.getRA_ID() + "");
                GameClient.tempreId = this.area.getRA_ID();
                Main.frame.getLoginJpanel().framechange(0, null, null);
                SendMessageUntil.getClientUntil(this.area.getIp(), this.area.getPort());
                DownLoadTxt.ip = this.area.getIp();
                DownLoadTxt.port = Integer.parseInt(this.area.getDowport());
                Main.frame.getRoleInfo().setAreaId(this.area.getRA_ID());
                Main.frame.getRoleInfo().setAreaName(this.area.getRA_NAME());
            }
            else if (this.caozuo == 0) {
                try {
                    RegionResult area = Util.getRegionResultByName(this.roleInfo.getAreaName());
                    if (area != null) {
                        Main.frame.getLoginJpanel().getAreaView().getLabMsgTip().setText("");
                        GameClient.BT = area.getRA_NAME();
                        Main.frame.getLoginJpanel().setRa_id(area.getRA_ID() + "");
                        GameClient.tempreId = area.getRA_ID();
                        SendMessageUntil.getClientUntil(area.getIp(), area.getPort());
                        DownLoadTxt.ip = area.getIp();
                        DownLoadTxt.port = Integer.parseInt(area.getDowport());
                        Main.frame.getRoleInfo().setAtId(area.getRA_ID().toString());
                    }
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
                Main.frame.setRoleInfo(this.roleInfo);
                RoleLoginThread roleLoginThread = new RoleLoginThread(this.roleInfo);
                Thread t1 = new Thread(roleLoginThread);
                t1.start();
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                Util.zzs = Integer.parseInt(configure.getLzjskg());
            }
            else {
                this.loginJpanel.getAreaView().delCommonlyUsedRoles(this.loginJpanel, this.roleInfo);
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    private final class RoleLoginThread implements Runnable
    {
        private LoginRoleInfo roleInfo;
        
        public RoleLoginThread(LoginRoleInfo loginRoleInfo) {
            this.roleInfo = loginRoleInfo;
            Main.frame.setRoleInfo(loginRoleInfo);
        }
        
        @Override
        public void run() {
            Boolean is;
            do {
                is = (Boolean)MapAction.flagAction.get(SendMessageUntil.gameServerKey);
            } while (is == null || !(boolean)is);
            LoginUserBean loginUserBean = new LoginUserBean();
            loginUserBean.setUsername(this.roleInfo.getAccount());
            loginUserBean.setPassword(this.roleInfo.getPassword());
            this.roleInfo.setAccount(this.roleInfo.getAccount());
            this.roleInfo.setPassword(this.roleInfo.getPassword());
            String mesage = Agreement.getAgreement().LoginAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginUserBean));
            SendMessageUntil.toServer(mesage);
            AreaMouslisten.this.loginJpanel.setRoleInfo(this.roleInfo);
            GameClient.username = this.roleInfo.getAccount();
            GameClient.userpasw = this.roleInfo.getPassword();
        }
    }
}
