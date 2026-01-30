package org.come.mouslisten;

import org.come.bean.Skill;
import come.tool.Fighting.MousePosUtil;
import org.come.bean.Coordinates;
import com.tool.imagemonitor.ScriptOpen;
import come.tool.map.XLPath;
import org.come.until.UserStallUntil;
import com.tool.imagemonitor.PlayerMonitor;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import java.util.List;
import org.come.bean.RoleShow;
import org.come.bean.Path;
import org.come.bean.PathPoint;
import java.util.ArrayList;
import com.tool.image.ManimgAttribute;
import come.tool.BangBattle.BangFight;
import org.come.socket.SendMessageUntil;
import org.come.thread.TimeControlRunnable;
import org.come.Jpanel.GameJpanel;
import org.come.Frame.ZhuFrame;
import com.tool.imagemonitor.FightingMonitor;
import org.come.until.FormsManagement;
import org.come.Frame.RoleMsgJframe;
import org.come.until.GoodsListFromServerUntil;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import com.tool.image.ImageMixDeal;
import org.come.until.MessagrFlagUntil;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.ScrenceUntil;
import org.come.until.Util;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.InputBean;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouselistener implements MouseMotionListener, MouseListener
{
    int xd;
    int yd;
    public boolean yidong;
    private InputBean inputBean;
    
    public Mouselistener() {
        this.xd = 0;
        this.yd = 0;
        this.yidong = false;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.inputBean != null) {
            DJInputBean(this.inputBean);
            this.inputBean = null;
        }
        else if (FightingMixDeal.State == 0 && e.getButton() == 3) {
            if (this.yidong) {
                Util.ZOU = false;
                int x = e.getX();
                int y = e.getY();
                int xdd = (Util.mapmodel.getShot_x() + x) / 20;
                int ydd = (Util.mapmodel.getShot_y() + y) / 20;
                if (this.xd != xdd || this.yd != ydd) {
                    this.xd = xdd;
                    this.yd = ydd;
                    Pathfinding(this.xd, this.yd);
                }
            }
        }
        else if (FightingMixDeal.State != 0 || e.getButton() == 1) {}
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (Util.isM()) {
            return;
        }
        int x = e.getX();
        int y = e.getY();
        if (e.getButton() == 1) {
            if (x > ScrenceUntil.Screen_x) {
                x -= ScrenceUntil.Screen_x;
                x -= 3;
                if (y <= ScrenceUntil.ChatFram_y / 10 * 5 - 20) {
                    this.inputBean = FrameMessageChangeJpanel.chatbox1.isMonitor(x, y);
                    if (this.inputBean != null) {
                        this.inputBean.setM(true);
                    }
                }
                else if (y >= ScrenceUntil.ChatFram_y / 10 * 5) {
                    y -= ScrenceUntil.ChatFram_y / 10 * 5;
                    this.inputBean = FrameMessageChangeJpanel.chatbox2.isMonitor(x, y);
                    if (this.inputBean != null) {
                        this.inputBean.setM(true);
                    }
                }
                return;
            }
            else if (FrameMessageChangeJpanel.chatbox.isDisplay()) {
                int w = FrameMessageChangeJpanel.chatbox.getW();
                int h = FrameMessageChangeJpanel.chatbox.getH();
                if (x <= w && y >= ScrenceUntil.Screen_y - 45 - h && y < ScrenceUntil.Screen_y - 45) {
                    this.inputBean = FrameMessageChangeJpanel.chatbox.isMonitor(x, y - (ScrenceUntil.Screen_y - 45 - h));
                    if (this.inputBean != null) {
                        this.inputBean.setM(true);
                    }
                }
            }
        }
        if (FightingMixDeal.State == 0) {
            if (GoodsMouslisten.replacepath == -1) {
                if (e.getButton() == 3) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    this.yidong = true;
                }
                else if (e.getButton() == 1) {
                    ImageMixDeal.MonitorTrigger(Util.mapmodel.getShot_x() + x, Util.mapmodel.getShot_y() + y);
                }
            }
            else if (e.getButton() == 3) {
                GoodsMouslisten.goodreplace(-1, -1);
            }
            else if (e.getButton() == 1) {
                Util.dj = true;
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.GoodDiscatd, null, "#W确定要将#G" + GoodsListFromServerUntil.Getgood(GoodsMouslisten.replacepath).getGoodsname() + "#W物品丢弃吗?");
            }
            if (FightingMixDeal.State == 0) {
                if (GoodsMouslisten.replacepath == -1) {
                    if (e.getButton() == 3) {
                        if ("解锁".equals(RoleMsgJframe.getRoleMsgJframe().getJpanel().getLbasuod().getText())) {
                            return;
                        }
                        FormsManagement.HideForm(77);
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                        this.yidong = true;
                    }
                    else if (e.getButton() == 1) {}
                }
                else if (e.getButton() == 3) {
                    GoodsMouslisten.goodreplace(-1, -1);
                }
                else if (e.getButton() == 1) {
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.GoodDiscatd, null, "#W确定要将#G" + GoodsListFromServerUntil.Getgood(GoodsMouslisten.replacepath).getGoodsname() + "#W物品丢弃吗?");
                }
            }
        }
        else if (e.getButton() == 3) {
            if (FightingMonitor.mousestate != 0) {
                if (FightingMixDeal.State == 1) {
                    ZhuFrame.getZhuJpanel().ShowManBtn(FightingMixDeal.isLL());
                }
                else if (FightingMixDeal.State == 2) {
                    ZhuFrame.getZhuJpanel().ShowBeastBtn();
                }
            }
            FightingMonitor.mousesname = "普通攻击";
            FightingMonitor.mousestate = 0;
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        }
        else if (e.getButton() == 1) {
            FightingMixDeal.MonitorTrigger(e.getX(), e.getY());
        }
        GameJpanel.skill = null;
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    public static void Pathfinding(int x, int y) {
        TimeControlRunnable.removeScript(true);
        XL(x, y);
    }
    
    public static void XL(int x, int y) {
        if (!SendMessageUntil.CanDoConnectOrNo) {
            FrameMessageChangeJpanel.addtext(6, "重连中,时间若太长重上", null, null);
            return;
        }
        if (!BangFight.getBangFight().isChao()) {
            ZhuFrame.getZhuJpanel().addPrompt2("您当前处于开炮、控塔、挑战状态，无法进行其他操作");
            return;
        }
        if (x > 2 && x < Util.mapmodel.getW() - 2 && y > 2 && y < Util.mapmodel.getH() - 2) {
            ManimgAttribute.addEffects("点击", x * 20, y * 20);
        }
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getFighting() != 0) {
            return;
        }
        if (roleShow.getBooth_id() != null) {
            return;
        }
        if (ImageMixDeal.userimg.getTeams() == null) {
            return;
        }
        boolean i = true;
        if (roleShow.getCar_id() != 0 || roleShow.getFly_id() != 0) {
            i = false;
        }
        if (roleShow.getMount_id() < 8 || roleShow.getFly_id() == 0 || Util.ditubianma == 3320 || Util.ditubianma == 1732 || Util.ditubianma == 1761 || (ImageMixDeal.scene != null && ImageMixDeal.scene.getSceneId() == 1011)) {
            if (i) {
                int rx = roleShow.getX() / 20;
                int ry = roleShow.getY() / 20;
                if (rx < 0 || ry < 0 || rx >= Util.mapmodel.getjMap().getMaprules()[0].length || ry >= Util.mapmodel.getjMap().getMaprules().length || Util.mapmodel.mskpanduan(rx, ry)) {
                    List<PathPoint> points = new ArrayList<>();
                    points.add(new PathPoint(roleShow.getX(), roleShow.getY()));
                    points.add(new PathPoint(x * 20, y * 20));
                    Path.sendXandYToServer(points);
                }
                else {
                    Path.findPath(Util.mapmodel.getNodes(), rx, ry, x, y);
                }
            }
            else {
                List<PathPoint> points2 = new ArrayList<>();
                points2.add(new PathPoint(roleShow.getX(), roleShow.getY()));
                points2.add(new PathPoint(x * 20, y * 20));
                Path.sendXandYToServer(points2);
            }
        }
        else {
            List<PathPoint> points2 = new ArrayList<>();
            points2.add(new PathPoint(roleShow.getX(), roleShow.getY()));
            points2.add(new PathPoint(x * 20, y * 20));
            Path.sendXandYToServer(points2);
        }
    }
    
    public static void DJInputBean(InputBean inputBean) {
        inputBean.setM(false);
        if (inputBean.getType() == 2 || inputBean.getType() == 3 || inputBean.getType() == 4) {
            String sendmes = Agreement.getAgreement().richMAgreement(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
            SendMessageUntil.toServer(sendmes);
        }
        else if (inputBean.getType() == 1) {
            String name = "";
            if (inputBean.getName().endsWith("]")) {
                name = inputBean.getName().substring(1, inputBean.getName().length() - 1);
            }
            else {
                name = inputBean.getName().substring(1, inputBean.getName().length());
            }
            ZhuFrame.getZhuJpanel().creatroletext(inputBean.getId(), name);
        }
        else if (inputBean.getType() == 11 || inputBean.getType() == 12) {
            ManimgAttribute attribute = ImageMixDeal.huoquLogin(inputBean.getId());
            if (attribute != null && attribute.getLeixing() == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("不能对自己进行操作");
                return;
            }
            if (inputBean.getType() == 11) {
                PlayerMonitor.teamApply(inputBean.getId());
            }
            else if (inputBean.getType() == 12) {}
        }
        else if (inputBean.getType() == 13) {
            UserStallUntil.showBuyStall(inputBean.getId().intValue());
        }
        else if (inputBean.getType() == 10 || inputBean.getType() == 20 || inputBean.getType() == 21 || inputBean.getType() == 22 || inputBean.getType() == 23 || inputBean.getType() == 24 || inputBean.getType() == 25) {
            if (FightingMixDeal.State != 0) {
                return;
            }
            Coordinates zb = inputBean.getZb();
            if (zb != null) {
                RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                List<Object> list = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(), zb.getX(), zb.getY(), zb.getMapID());
                if (list == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
                    return;
                }
                if (inputBean.getType() == 21 || inputBean.getType() == 22 || inputBean.getType() == 23 || inputBean.getType() == 25) {
                    int type = inputBean.getType() - 20;
                    ScriptOpen open = new ScriptOpen((type == 1) ? 2 : 1);
                    open.setNpc(inputBean.getId().intValue());
                    list.add(0, open);
                }
                TimeControlRunnable.addScript(list);
            }
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x > ScrenceUntil.Screen_x) {
            x -= ScrenceUntil.Screen_x;
            x -= 3;
        }
        if (ImageMixDeal.userimg.getRoleShow().getFighting() != 0) {
            MousePosUtil.mouse_x = e.getX();
            MousePosUtil.mouse_y = e.getY();
            FightingMixDeal.nameColor(e.getX(), e.getY());
        }
        else {
            ImageMixDeal.colorName(Util.mapmodel.getShot_x() + x, Util.mapmodel.getShot_y() + y);
        }
        FightingMixDeal.mouseEnteredMonitor(e.getX(), e.getY());
    }
}
