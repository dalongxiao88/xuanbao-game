package com.tool.btn;

import come.tool.handle.HandleState;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.ActivityJframe;
import org.come.entity.Goodstable;
import org.come.until.FormsManagement;
import org.come.Frame.MsgJframe;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import org.come.bean.RoleShow;
import com.tool.imagemonitor.ScriptOpen;
import come.tool.map.XLPath;
import org.come.bean.Coordinates;
import org.come.thread.TimeControlRunnable;
import com.tool.imagemonitor.ScriptTask;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import come.tool.Fighting.FightingMixDeal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseEvent;

import org.come.Jpanel.ActivityModelJpanel;
import org.come.Jpanel.ActivityJpanel;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;

import javax.swing.*;

public class ActivityBtn extends MoBanBtn
{
    private int caozuo;
    private ActivityJpanel activityJpanel;
    private ActivityModelJpanel activityModelJpanel;
    private int subscript;
    private String itemUrl;
    public ActivityBtn(String iconpath, int type, int caozuo, int subscript, ActivityJpanel activityJpanel, String itemUrl) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.subscript = subscript;
        this.activityJpanel = activityJpanel;
        this.itemUrl = itemUrl;
    }
    public ActivityBtn(String iconpath, int type, int caozuo, int subscript, ActivityJpanel activityJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.subscript = subscript;
        this.activityJpanel = activityJpanel;
    }
    
    public ActivityBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, ActivityModelJpanel activityModelJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.setFont(font);
        this.caozuo = caozuo;
        this.activityModelJpanel = activityModelJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (getText().equals("抽奖")) {
//            ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().getDayDraw();
            String[] is = ActivityJframe.getActivityJframe().getActivityJpanel().getIscj();
            boolean isd = false;
            for (String v : is) {
                if (StringUtils.isNotEmpty(v)) {
                    if (v.split("=")[0].equals(ActivityJpanel.CJTYPENUM)) {
                        isd = v.split("=")[1].equals("1") ? true : false;
                    }
                }
            }
            if (!isd) {
                ZhuFrame.getZhuJpanel().addPrompt2("不满足抽奖条件！");
                return;
//                impactGradeJpanel.getDayDraw();
            } else {
                if(ActivityJpanel.start){
                    ZhuFrame.getZhuJpanel().addPrompt2("正在抽奖中！");
                    return;
                }
                ActivityJframe.getActivityJframe().getActivityJpanel().cj();

            }
        } else if (caozuo == 1) {
            String sendmes = Agreement.getAgreement().TaskNAgreement("R2=" + subscript);
            SendMessageUntil.toServer(sendmes);
        } else if (caozuo == 2) {
            if (FightingMixDeal.State != HandleState.USUAL) {
                return;
            }
            if (ImageMixDeal.userimg.getTeams() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你是队员无法操作");
                return;
            }
            String guide = activityModelJpanel.getActiveBase().getGuide();
            String[] v = guide.split("-");
            if (v.length == 5) {
                TimeControlRunnable.addTask(new ScriptTask(v, activityModelJpanel.getActiveBase().getSid()), false);
                return;
            }
            RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            Coordinates coordinates = new Coordinates(Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]));
            List<Object> list = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(), coordinates.getX(), coordinates.getY(), coordinates.getMapID());
            if (list == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
                return;
            }
            ScriptOpen open = new ScriptOpen(1);
            open.setNpc(Integer.parseInt(v[3]));
            list.add(0, open);
            TimeControlRunnable.addScript(list);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (caozuo == 1) {
            String name = this.getName();
            String vitality = activityJpanel.getLabVitality()[subscript].getText();
            MsgJframe.getJframe().getJapnel().TYC(vitality + "活跃奖励", name);
        } else if (caozuo == 2) {

        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (this.caozuo == 1) {
            FormsManagement.HideForm(46);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getText().equals("前往") || getText().equals("抽奖"))
            super.paintComponent(g);
        else {
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(new Color(7, 6, 6, 147));
            graphics2D.fillOval(0, 0, 50, 50);
            Goodstable goodstable = UserMessUntil.getGoodsBean().getAllGoodsMap().get(new BigDecimal(itemUrl));
            ImageIcon img = GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 50, 50);
            g.drawImage(img.getImage(), 0, 0, null);
        }

    }
}
