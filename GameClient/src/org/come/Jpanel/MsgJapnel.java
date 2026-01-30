package org.come.Jpanel;

import java.util.ArrayList;
import java.util.HashMap;
import org.come.until.CustomFunction;
import org.come.Frame.MsgJframe;
import java.util.Iterator;
import org.apache.commons.lang.math.NumberUtils;
import come.tool.Fighting.FightingManData;
import org.come.model.Lingbao;
import org.come.until.UserData;
import com.tool.role.RoleLingFa;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import java.math.BigDecimal;
import come.tool.FightingData.FBUtil;
import org.come.until.Arith;
import org.come.bean.BabyResult;
import org.come.bean.Talent;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import com.tool.tcpimg.UIUtils;
import java.awt.Point;
import java.awt.Dimension;
import org.come.Frame.ZhuFrame;
import com.tool.time.TimerUtil;
import com.tool.time.Limit;
import org.come.entity.Goodstable;
import org.come.bean.Skill;
import org.come.until.UserMessUntil;
import org.come.until.FormsManagement;
import org.come.until.ScrenceUntil;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import com.tool.tcp.Sprite;
import com.tool.tcpimg.ChatBox;
import javax.swing.JPanel;

public class MsgJapnel extends JPanel
{
    private ChatBox box;
    private int boxx;
    private int boxy;
    private int boxw;
    private int boxh;
    private long time;
    private Sprite tx;
    public static Map<String, String> lingxiState;
    static Map<Integer, List<Double>> listMap;
    
    public MsgJapnel() {
        this.time = 0L;
        (this.box = new ChatBox()).setAlpha(0.6f);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.tx != null) {
            this.time += 80L;
            this.tx.updateToTime(this.time, 0);
            this.tx.draw(g, ScrenceUntil.Screen_x / 2, ScrenceUntil.Screen_y / 2);
            if ((long)this.tx.getTime() < this.time) {
                this.tx = null;
                FormsManagement.HideForm(46);
            }
        }
        else {
            Graphics g2 = g.create(0, 0, this.boxw, this.boxh);
            this.box.paintSSS(g2);
            g2.dispose();
        }
    }
    
    public void zsskill(String v, int x, int y) {
        this.box.removemsg();
        String[] vs = v.split("=");
        Skill skill = UserMessUntil.getskill1(vs[0]);
        this.box.addText("#W" + vs[0], 250);
        this.box.addText("#Y【组合技能】: " + vs[1] + "人合技", 250);
        if (skill.getSkilltype().equals("0")) {
            this.box.addText("#Y【组合类型】: 攻击类", 250);
        }
        else if (skill.getSkilltype().equals("1")) {
            this.box.addText("#Y【组合类型】: 辅助类", 250);
        }
        else {
            this.box.addText("#Y【组合类型】: 落宝类", 250);
        }
        String[] msgs = skill.getRemark().split("\\|");
        int lvl = Integer.parseInt(vs[1]);
        int id = Integer.parseInt(skill.getSkillid());
        for (int i = 0; i < msgs.length; ++i) {
            if (msgs[i].equals("目标数")) {
                this.box.addText("#Y【目标数】: " + this.getsum(id, lvl), 250);
            }
            else if (msgs[i].equals("持续回合数")) {
                this.box.addText("#Y【持续回合数】: " + this.getchixu(id, lvl), 250);
            }
            else {
                this.box.addText(msgs[i], 250);
            }
        }
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - this.boxh - 5;
        this.displaymsg();
    }
    
    public int getsum(int id, int lvl) {
        if ((id >= 3001 && id <= 3009) || id == 3023 || id == 3027) {
            return this.getsum2(lvl);
        }
        if (id == 3013 || id == 3014 || id == 3015) {
            return this.getsum3(lvl);
        }
        if (id >= 3016 && id <= 3020) {
            return this.getsum4(lvl);
        }
        if (id == 3028 || id == 3029 || id == 3030) {
            return lvl - 3;
        }
        return lvl;
    }
    
    public int getsum2(int sum) {
        if (sum <= 3) {
            return sum;
        }
        return (sum << 1) - 3;
    }
    
    public int getsum3(int sum) {
        return (sum << 1) - 1;
    }
    
    public int getsum4(int sum) {
        if (sum >= 5) {
            return 10;
        }
        return (sum << 1) - 1;
    }
    
    public int getchixu(int id, int lvl) {
        if (id == 3009) {
            return 1;
        }
        if (id == 3010 || id == 3026 || id == 3022) {
            return 2;
        }
        if (id >= 3016 && id <= 3019) {
            if (lvl > 4) {
                return 3;
            }
            return 2;
        }
        else {
            if (id == 3025 || id == 3023) {
                return 3;
            }
            if (id == 3028) {
                return lvl - 3;
            }
            if (id == 3029) {
                if (lvl > 3) {
                    return 2;
                }
                return 1;
            }
            else {
                return lvl;
            }
        }
    }
    
    public void zsfs(Goodstable goodstable, int x, int y) {
        this.box.removemsg();
        this.box.addText("#W " + goodstable.getGoodsname(), 250);
        String[] v = goodstable.getValue().split("\\|");
        for (int i = 0; i < v.length; ++i) {
            this.box.addText("#Y " + v[i].split("=")[0] + " " + v[i].split("=")[1], 250);
        }
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - this.boxh - 5;
        this.displaymsg();
    }
    
    public void zssxk(Limit limit, int x, int y) {
        if (limit.getType().equals("SVIP")) {
            this.zsVIP(limit, x, y);
            return;
        }
        if (limit.getType().equals("超级六脉化神丸_月") || limit.getType().equals("超级玉枢返虚丸_月")) {
            this.zsVIP1(limit, x, y);
            return;
        }
        this.box.removemsg();
        if (limit.getTime() != 0L) {
            int fenz = TimerUtil.fenzhong(limit.getTime());
            if (fenz > 60) {
                String xiao = "#G剩余的时间#Y" + TimerUtil.xiaoshi(limit.getTime()) + "#G小时";
                this.box.addText("#Y  " + limit.getName() + "   " + xiao, 250);
            }
            else {
                String time = "#G剩余的时间#Y" + TimerUtil.fenzhong(limit.getTime()) + "#G分钟";
                this.box.addText("#Y  " + limit.getName() + "   " + time, 250);
            }
        }
        else if (limit.getType().equals("VIP")) {
            String timex = "#G剩余#Y" + TimerUtil.residueDay(limit.getTime()) + "#G天";
            this.box.addText("#Y  " + limit.getName() + "#r  " + timex, 220);
        }
        else if (limit.getType().equals("JVIP")) {
            String timex = "#G剩余#Y" + TimerUtil.residueDay(limit.getTime()) + "#G天";
            this.box.addText("#Y  " + limit.getName() + "#r  " + timex, 220);
        }
        String v = limit.getValue();
        if (v != null && !v.equals("")) {
            String[] vs = v.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("=");
                if (vss.length == 2) {
                    if (!vss[0].equals("标签") && !vss[0].equals("值") && !vss[0].equals("皮肤") && !vss[0].equals("皮肤不变") && !vss[0].equals("方向") && !vss[0].equals("变身卡类") && !vss[0].equals("lvl") && !vss[0].equals("刮奖") && !vss[0].equals("卡类")) {
                        this.box.addText("#W  " + vss[0] + " " + GoodsMsgJpanel.zffh(vss[1]) + GoodsMsgJpanel.tianjia(vss[0]), 250);
                    }
                }
                else if (vss.length == 1) {
                    this.box.addText("#W  " + vss[0], 250);
                }
            }
        }
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y + 20;
        this.displaymsg();
    }
    
    public void zsVIP1(Limit limit, int x, int y) {
        this.box.removemsg();
        String timex = "#G剩余#Y" + TimerUtil.residueDay(limit.getTime()) + "#G天";
        this.box.addText("#Y  " + limit.getName() + "   " + timex, 220);
        this.box.addText("#W  战斗结束后回复满召唤兽血量#r#W  战斗结束后回复满角色血量", 250);
        String v = limit.getValue();
        if (v != null && !v.equals("")) {
            String[] vs = v.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("=");
                if (vss.length == 2) {
                    if (!vss[0].equals("标签") && !vss[0].equals("值") && !vss[0].equals("皮肤") && !vss[0].equals("皮肤不变") && !vss[0].equals("方向") && !vss[0].equals("变身卡类") && !vss[0].equals("lvl") && !vss[0].equals("刮奖") && !vss[0].equals("卡类")) {
                        this.box.addText("#W  " + vss[0] + " " + GoodsMsgJpanel.zffh(vss[1]) + GoodsMsgJpanel.tianjia(vss[0]), 250);
                    }
                }
                else if (vss.length == 1) {
                    this.box.addText("#W  " + vss[0], 250);
                }
            }
        }
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y + 20;
        this.displaymsg();
    }
    
    public void zsVIP(Limit limit, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y  " + limit.getName(), 250);
        this.box.addText("#Y  以下加成只在PVE有效", 250);
        String v = limit.getValue();
        if (v != null && !v.equals("")) {
            String[] vs = v.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("=");
                if (vss.length == 2) {
                    if (!vss[0].equals("标签") && !vss[0].equals("值") && !vss[0].equals("皮肤") && !vss[0].equals("皮肤不变") && !vss[0].equals("方向") && !vss[0].equals("变身卡类") && !vss[0].equals("lvl") && !vss[0].equals("刮奖") && !vss[0].equals("卡类")) {
                        this.box.addText("#W  " + vss[0] + " " + GoodsMsgJpanel.zffh(vss[1]) + GoodsMsgJpanel.tianjia(vss[0]), 250);
                    }
                }
                else if (vss.length == 1) {
                    this.box.addText("#W  " + vss[0], 250);
                }
            }
        }
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y + 20;
        this.displaymsg();
    }
    
    public void ewts(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y" + text + "", 136);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - 70;
        this.displaymsg();
    }
    
    public void vip(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y  " + text + "   ", 125);
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 10;
        this.boxy = (int)goodx.getY();
        this.displaymsg();
    }
    
    public void vips(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y  " + text + "   ", 300);
        this.boxh = this.box.getHeight();
        this.boxw = this.box.getWidth();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 10;
        this.boxy = (int)goodx.getY() + 80;
        this.displaymsg();
    }
    
    public void showXFD(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#W  " + text, 70);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y + 20;
        this.displaymsg();
    }
    public void showXFD(String text,int x, int y,int size) {
        box.removemsg();
        //box.addText(" #c" + colorStr + msg, boxw, UIUtils.TEXT_FONT);
        box.addText("#W  " + text ,size);
        boxw = box.getWidth();
        boxh = box.getHeight();
        boxx = x - box.getWidth() / 2;
        boxy = y + 20;
        displaymsg();

    }
    public void showchongzhi(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#W  " + text, 130);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y + 20;
        this.displaymsg();
    }
    
    public void showXFD26(String text, String text2, String text3, String text4, String text5, String text6, int x, int y) {
        this.box.removemsg();
        this.box.addText("#c  " + text, 280, UIUtils.TEXT_HY193);
        this.box.addText("#c-------------------" + text2, 280, UIUtils.TEXT_FONT7);
        this.box.addText("#Y  " + text3, 280, UIUtils.TEXT_FONT);
        this.box.addText("#c  " + text4, 280, UIUtils.TEXT_FONT);
        this.box.addText("#c  " + text5, 280, UIUtils.TEXT_FONT);
        this.box.addText("#cEE2C2C  注意：清理的缓存是所有程序临时文件" + text6, 280, UIUtils.TEXT_FONT);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - 35;
        this.displaymsg();
    }
    
    public void showCdan(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y " + text, 100, UIUtils.TEXT_FONT15);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - 38;
        this.displaymsg();
    }
    
    public void showXFDS22(String text, String text2, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y " + text, 100, UIUtils.TEXT_FONT15);
        this.box.addText("#Y " + text2, 100, UIUtils.TEXT_FONT15);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - 60;
        this.displaymsg();
    }
    
    public void showzhaohuans(String msg, String colorStr) {
        this.box.removemsg();
        this.boxw = msg.length() * 12 + 10;
        this.box.addText(" #c" + colorStr + msg, this.boxw, UIUtils.TEXT_FONT78);
        this.boxh = this.box.getHeight();
        this.boxx = ScrenceUntil.Screen_x - 400;
        this.boxy = ScrenceUntil.Screen_y - 720;
        this.displaymsg();
    }
    
    public void showXFDS(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y  " + text, 90, UIUtils.TEXT_FONT86);
        this.box.setForeground(UIUtils.COLOR_SIGN1);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x + 25;
        this.boxy = y - 25;
        this.displaymsg();
    }
    
    public void showSkillName(String text, int x, int y) {
        this.box.removemsg();
        this.boxw = text.length() * 11 + 30;
        this.box.addText("#Y  " + text, this.boxw, UIUtils.TEXT_FONT82);
        this.box.setForeground(UIUtils.COLOR_SIGN1);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x + 25;
        this.boxy = y - 25;
        this.displaymsg();
    }
    
    public void showSZJ(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#W   " + text, 70);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - 35;
        this.displaymsg();
    }
    
    public void showXFD3(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y  " + text, 90);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2 + 50;
        this.boxy = y + 40;
        this.displaymsg();
    }
    
    public void showXFD4(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y " + text, 65);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2 + 50;
        this.boxy = y + 40;
        this.displaymsg();
    }
    
    public void showXFD5(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y  " + text, 75);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2 + 50;
        this.boxy = y + 40;
        this.displaymsg();
    }
    
    public void DiceshowCdan(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y " + text, 355, UIUtils.TEXT_NAME_FONT);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 4;
        this.boxy = y - 20;
        this.displaymsg();
    }
    
    public void showSMWD(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y" + text, 320);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2 + 50;
        this.boxy = y + 30;
        this.displaymsg();
    }
    
    public void zsGemIntensify(int lvl, int x, int y) {
        this.box.removemsg();
        StringBuffer buffer = new StringBuffer();
        buffer.append("#Y");
        buffer.append("五灵共鸣:");
        buffer.append(lvl);
        buffer.append("级");
        this.box.addText(buffer.toString(), 200);
        buffer.setLength(0);
        buffer.append("#W四抗上限 +");
        buffer.append(String.format("%.2f", new Object[] { Double.valueOf(0.4 * (double)lvl) }));
        this.box.addText(buffer.toString(), 200);
        buffer.setLength(0);
        buffer.append("#W装备抗性上限 +");
        buffer.append(String.format("%.2f", new Object[] { Double.valueOf(1.5 * (double)lvl) }));
        this.box.addText(buffer.toString(), 200);
        buffer.setLength(0);
        buffer.append("#WHP +");
        buffer.append(2000 * lvl);
        this.box.addText(buffer.toString(), 200);
        buffer.setLength(0);
        buffer.append("#WMP +");
        buffer.append(1000 * lvl);
        this.box.addText(buffer.toString(), 200);
        if (lvl >= 12) {
            buffer.setLength(0);
            buffer.append("#c00F5FF气贯长虹:");
            this.box.addText(buffer.toString(), 200);
            buffer.setLength(0);
            buffer.append("#c00F5FF涅槃重生,破茧成蝶");
            this.box.addText(buffer.toString(), 200);
            buffer.setLength(0);
            buffer.append("#c00F5FF气贯长虹,笑傲江湖");
            this.box.addText(buffer.toString(), 200);
        }
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y + 20;
        this.displaymsg();
    }
    
    public void texiao(String type) {
        if (this.tx != null) {
            return;
        }
        Sprite mouse = SpriteFactory.Prepare(GetTcpPath.getMouseTcp(type));
        if (mouse != null) {
            this.time = 0L;
            this.tx = mouse;
            this.boxw = ScrenceUntil.Screen_x;
            this.boxh = ScrenceUntil.Screen_y;
            this.boxx = 0;
            this.boxy = 0;
            this.displaymsg();
        }
    }
    
    public void talent(Integer id, int lvl, int type, String outcome, int p) {
        Talent talent = UserMessUntil.getTalent((int)id);
        if (talent == null) {
            return;
        }
        if ((int)id <= 3 && type == 1) {
            return;
        }
        this.box.removemsg();
        this.box.addText(this.gettalentname(talent.getTalentName(), (type == 1) ? lvl : 0), 310);
        if (type == 0) {
            String msg = talent.getText();
            msg = msg.replace("{触发几率}", (double)lvl * talent.getTouch() + "%");
            msg = msg.replace("{要求}", this.getdemand(talent.getDemand(), lvl));
            this.box.addText(msg, 310);
        }
        else {
            this.getvalue(talent, lvl, type, outcome, p);
        }
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX();
        this.boxy = (int)goodx.getY();
        this.displaymsg();
    }
    
    public String gettalentname(String name, int lvl) {
        if (lvl == 0) {
            return "#Y " + name;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("#Y 强化");
        buffer.append(name);
        buffer.append(" ");
        buffer.append(lvl);
        buffer.append("级");
        return buffer.toString();
    }
    
    public String getdemand(String demand, int lvl) {
        if (lvl >= 9) {
            return "天资已达到最高境界,不可强化";
        }
        if (demand == null || demand.equals("")) {
            return "";
        }
        String[] v = demand.split("\\|");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < v.length; ++i) {
            buffer.append(" ");
            String[] vs = v[i].split("=");
            buffer.append(vs[0]);
            buffer.append(" >= ");
            buffer.append(vs[1]);
        }
        buffer.append(" ,达到要求后可以继续强化有几率成功");
        return buffer.toString();
    }
    
    public void getvalue(Talent talent, int lvl, int type, String outcome, int weizhi) {
        String[] v = talent.getValue().split("\\|");
        if (v.length == 2) {
            v = v[1].split("=");
            StringBuffer buffer = new StringBuffer();
            buffer.append(" 发动");
            buffer.append(talent.getTalentName());
            buffer.append("时增加");
            buffer.append(v[0]);
            v = v[1].split("\\+");
            buffer.append(Double.parseDouble(v[0]) + Double.parseDouble(v[1]) * (double)lvl);
            this.box.addText(buffer.toString(), 310);
        }
        if (lvl >= 10) {
            this.box.addText("#G已经达到最高级别了", 310);
        }
        else {
            this.box.addText("#G可以通过点击技能格子消耗琼浆玉液来强化此技能,有几率成功", 310);
        }
        if (outcome == null || outcome.equals("")) {
            this.box.addText("#G还未获取结局,不可强化此技能格子", 310);
            return;
        }
        BabyResult babyResult = UserMessUntil.getBabyResult(outcome);
        int camp = 0;
        if (weizhi == 0) {
            camp = babyResult.getQ1();
        }
        else if (weizhi == 1) {
            camp = babyResult.getQ2();
        }
        else if (weizhi == 2) {
            camp = babyResult.getQ3();
        }
        switch (camp) {
            case 0: {
                this.box.addText("#G此技能格可强化天资:不可强化", 310);
                break;
            }
            case 1: {
                this.box.addText("#G此技能格可强化天资:可强化低级人族天资", 310);
                break;
            }
            case 2: {
                this.box.addText("#G此技能格可强化天资:可强化低级魔族天资", 310);
                break;
            }
            case 3: {
                this.box.addText("#G此技能格可强化天资:可强化低级仙族天资", 310);
                break;
            }
            case 4: {
                this.box.addText("#G此技能格可强化天资:可强化低级鬼族天资", 310);
                break;
            }
            case 5: {
                this.box.addText("#G此技能格可强化天资:可强化全部低级天资", 310);
                break;
            }
            case 6: {
                this.box.addText("#G此技能格可强化天资:可强化高级人族天资", 310);
                break;
            }
            case 7: {
                this.box.addText("#G此技能格可强化天资:可强化高级魔族天资", 310);
                break;
            }
            case 8: {
                this.box.addText("#G此技能格可强化天资:可强化高级仙族天资", 310);
                break;
            }
            case 9: {
                this.box.addText("#G此技能格可强化天资:可强化高级鬼族天资", 310);
                break;
            }
            case 10: {
                this.box.addText("#G此技能格可强化天资:可强化全部高级天资", 310);
                break;
            }
            case 11: {
                this.box.addText("#G此技能格可强化天资:可强化全部天资", 310);
                break;
            }
        }
    }
    
    public void TYC(String name, String msg) {
        this.box.removemsg();
        this.box.addText("#cf8fc70" + name, 310, UIUtils.TEXT_FONT5);
        this.box.addText(msg, 310, UIUtils.TEXT_FONT1);
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 20;
        this.boxy = (int)goodx.getY() - 20;
        this.displaymsg();
    }
    
    public void LX(String name, String msg) {
        this.box.removemsg();
        this.box.addText("#c82aeff " + name, 310, UIUtils.TEXT_FONT33);
        this.box.addText(msg, 310, UIUtils.TEXT_FONT1);
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 50;
        this.boxy = (int)goodx.getY() + 80;
        this.displaymsg();
    }
    
    public void JM(String name, String value) {
        this.box.removemsg();
        this.box.addText("#c82aeff" + name, 310, UIUtils.TEXT_HY88);
        if (value == null || value == "") {
            this.box.addText("【未开启】 ", 310, UIUtils.TEXT_FONT1);
        }
        else {
            this.box.addText("属性加成:#r#c82aeff" + value, 200, UIUtils.TEXT_FONT1);
        }
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 30;
        this.boxy = (int)goodx.getY() + 120;
        this.displaymsg();
    }
    
    public void SM1(Skill skill) {
        this.box.removemsg();
        String msg = skill.getRemark();
        int id = Integer.parseInt(skill.getSkillid());
        if (id <= 22000 || id >= 22035) {
            return;
        }
        this.box.addText(msg, 320);
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX();
        this.boxy = (int)goodx.getY();
        this.displaymsg();
    }
    
    public void SM2(Skill skill) {
        this.box.removemsg();
        String msg = skill.getRemark();
        int id = Integer.parseInt(skill.getSkillid());
        if (id < 23000 || id >= 23010) {
            return;
        }
        this.box.addText(msg, 320);
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX();
        this.boxy = (int)goodx.getY();
        this.displaymsg();
    }
    
    public void SM3(Skill skill) {
        this.box.removemsg();
        String msg = skill.getRemark();
        int id = Integer.parseInt(skill.getSkillid());
        if (id < 9001 || id > 10166) {
            return;
        }
        msg = skillMsgchange(msg, skill);
        this.box.addText(msg, 320);
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX();
        this.boxy = (int)goodx.getY();
        this.displaymsg();
    }
    
    public static String skillMsgchange(String remark, Skill skillAll) {
        int id = Integer.parseInt(skillAll.getSkillid());
        if (id >= 9814 && id <= 10166) {
            if (remark.contains("{公式一}")) {
                remark = remark.replace("{公式一}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())))));
            }
            if (remark.contains("{公式二}")) {
                remark = remark.replace("{公式二}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue1()), Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow1())))));
            }
            if (remark.contains("{公式三}")) {
                remark = remark.replace("{公式三}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue2()), Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow2())))));
            }
            if (remark.contains("{公式四}")) {
                remark = remark.replace("{公式四}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue3()), Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow3())))));
            }
        }
        else if (id == 9412 || id == 9811) {
            remark = remark.replace("{公式三十二}", String.valueOf(Arith.add(Arith.add(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())), (double)Double.valueOf(skillAll.getValue())), 20.0)));
            remark = remark.replace("{公式三十}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())), 125.0)));
            remark = remark.replace("{公式三}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 1.5), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9189) {
            remark = remark.replace("{公式三十一}", String.valueOf(Arith.sub(Arith.add((double)Double.valueOf(skillAll.getValue()), 50.0), Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())))));
            remark = remark.replace("{公式三十}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())), 125.0)));
            remark = remark.replace("{公式三}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 1.5), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9382) {
            remark = remark.replace("{公式三十}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow())), 125.0)));
            remark = remark.replace("{公式三}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 1.5), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9372) {
            remark = remark.replace("{公式二十九}", String.valueOf(Arith.sub(100.0, (Integer.parseInt(skillAll.getSkilllevel()) <= 2) ? Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), 10.0)) : Arith.add(10.0, Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), 5.0)))));
        }
        else if (id == 9370) {
            remark = remark.replace("{公式二十八}", String.valueOf(Arith.add(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), 3000.0), (double)Double.valueOf(skillAll.getSkilllevel())), Arith.mul((double)Double.valueOf(skillAll.getValue()), 1500.0))));
            remark = remark.replace("{公式二十七}", String.valueOf(Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), 3000.0))));
        }
        else if (id == 9365) {
            remark = remark.replace("{公式二十六}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.mul(5.0, (double)Double.valueOf(skillAll.getSkilllevel()))))));
        }
        else if (id == 9352) {
            remark = remark.replace("{公式二十五}", String.valueOf(Arith.add(25.0, Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 2.0), Arith.mul(5.0, (double)Double.valueOf(skillAll.getSkilllevel()))))));
            remark = remark.replace("{公式八}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.div(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 2.0))));
        }
        else if (id == 9328) {
            remark = remark.replace("{公式二十四}", String.valueOf(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), Arith.mul(3.0, (double)Double.valueOf(skillAll.getSkilllevel())))));
            remark = remark.replace("{公式二十三}", String.valueOf(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9326) {
            remark = remark.replace("{公式二十二}", String.valueOf(Arith.add(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getValue()), 2.0), 5.0), Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())))));
        }
        else if (id == 9307) {
            remark = remark.replace("{公式二十一}", String.valueOf(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 5.0), Arith.mul(2.0, (double)Double.valueOf(skillAll.getSkilllevel())))));
        }
        else if (id == 9369) {
            remark = remark.replace("{公式三十三}", String.valueOf(Arith.mul(2.0, (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9271) {
            remark = remark.replace("{公式十九}", String.valueOf(Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), 1000.0))));
        }
        else if (id == 9269) {
            remark = remark.replace("{公式十八}", String.valueOf(Arith.add(Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.mul((Integer.parseInt(skillAll.getSkilllevel()) <= 4) ? ((double)Double.valueOf(skillAll.getSkilllevel())) : Arith.add((double)Double.valueOf(skillAll.getSkilllevel()), 1.0), 500.0)), 13000.0)));
            remark = remark.replace("{公式十七}", String.valueOf(Arith.add(Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.mul((double)Double.valueOf(skillAll.getSkilllevel()), 250.0)), 10000.0)));
        }
        else if (id == 9251) {
            remark = remark.replace("{公式十六}", String.valueOf(Arith.sub(Arith.add((double)Double.valueOf(skillAll.getValue()), (Integer.parseInt(skillAll.getSkilllevel()) > 3) ? Arith.sub((double)Double.valueOf(skillAll.getSkilllevel()), 3.0) : 0.0), 2.0)));
            remark = remark.replace("{公式十五}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), (Integer.parseInt(skillAll.getSkilllevel()) <= 3) ? ((double)Double.valueOf(skillAll.getSkilllevel())) : 3.0)));
        }
        else if (id == 9250) {
            remark = remark.replace("{公式十四}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 6.0)));
        }
        else if (id == 9244) {
            remark = remark.replace("{公式十三}", String.valueOf(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 800.0)));
        }
        else if (id == 9241) {
            remark = remark.replace("{公式十二}", String.valueOf(Arith.div(Arith.mul((double)Double.valueOf(skillAll.getGrow()), Arith.div((double)Double.valueOf(skillAll.getSkilllevel()), 4000.0)), 6.0)));
        }
        else if (id == 9231) {
            remark = remark.replace("{公式十一}", String.valueOf(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 10.0)));
        }
        else if (id == 9227 || id == 9287 || id == 9711) {
            remark = remark.replace("{公式九}", String.valueOf(Arith.sub((double)Double.valueOf(skillAll.getValue()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())))));
        }
        else if (id == 9182) {
            remark = remark.replace("{公式八}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.div(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 2.0))));
        }
        else if (id == 9162 || id == 9265 || id == 9266) {
            remark = remark.replace("{公式七}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 2.0))));
        }
        else if (id == 9152 || id == 9188) {
            remark = remark.replace("{公式六}", String.valueOf(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 500.0)));
            remark = remark.replace("{公式五}", String.valueOf(Arith.mul(Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())), 3.0)));
        }
        else if (id == 9171) {
            remark = remark.replace("{公式四}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getSkilllevel()), (double)Double.valueOf(skillAll.getGrow()))));
        }
        else if (id == 9125) {
            remark = remark.replace("{公式三}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 1.5), (double)Double.valueOf(skillAll.getSkilllevel()))));
        }
        else if (id == 9508) {
            remark = remark.replace("{公式三十四}", String.valueOf(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(600.0, Arith.mul(Double.parseDouble(skillAll.getSkilllevel()), Double.parseDouble(skillAll.getValue()))))));
        }
        else if (id == 9510) {
            remark = remark.replace("{公式三十五}", String.valueOf(Arith.add(60.0, Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(skillAll.getSkilllevel()), 5.0)))));
        }
        else if (id == 9511) {
            remark = remark.replace("{公式三十六}", String.valueOf(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.add(Double.parseDouble(skillAll.getSkilllevel()), 1.0))));
            remark = remark.replace("{公式三十七}", String.valueOf(Arith.mul(10000.0, Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.add(Double.parseDouble(skillAll.getSkilllevel()), 1.0)))));
        }
        else if (id == 9612) {
            remark = remark.replace("{公式三十八}", String.valueOf((Double.parseDouble(skillAll.getSkilllevel()) <= 2.0) ? 1 : ((Double.parseDouble(skillAll.getSkilllevel()) <= 4.0) ? 2 : 3)));
        }
        else if (id == 9191) {
            remark = remark.replace("{公式十}", String.valueOf(Arith.add(Arith.mul(Double.parseDouble(skillAll.getGrow()), Double.parseDouble(skillAll.getSkilllevel())), Arith.add(50.0, Double.parseDouble(skillAll.getValue())))));
        }
        else if (id == 9270) {
            remark = (remark = remark.replace("{公式一}", "上一次的#R90%#W"));
        }
        remark = remark.replace("{公式一}", String.valueOf(Arith.add((double)Double.valueOf(skillAll.getValue()), Arith.mul((double)Double.valueOf(skillAll.getGrow()), (double)Double.valueOf(skillAll.getSkilllevel())))));
        remark = remark.replace("{公式二}", String.valueOf(Arith.mul(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 4.0), 10.0), (double)Double.valueOf(skillAll.getSkilllevel()))));
        remark = remark.replace("{公式二十}", String.valueOf(Arith.mul(Arith.div((double)Double.valueOf(skillAll.getGrow()), 2.0), Arith.mul(5.0, (double)Double.valueOf(skillAll.getSkilllevel())))));
        return remark;
    }
    
    public void SM(Skill skill, double sld, int lvl) {
        this.box.removemsg();
        String msg = skill.getRemark();
        int id = Integer.parseInt(skill.getSkillid());
        if (id >= 1001 && id <= 1100) {
            int level = Integer.parseInt(skill.getSkilllevel());
            double sv = Double.parseDouble(skill.getGrow());
            double mv = Double.parseDouble(skill.getDielectric());
            double value = Double.parseDouble(skill.getValue());
            String type = (id <= 1005) ? "混乱" : ((id <= 1010) ? "封印" : ((id <= 1015) ? "昏睡" : ((id <= 1020) ? "中毒" : ((id <= 1025) ? "震慑" : ((id <= 1030) ? "力量" : ((id <= 1035) ? "抗性" : ((id <= 1040) ? "加速" : ((id <= 1045) ? "风" : ((id <= 1050) ? "雷" : ((id <= 1055) ? "水" : ((id <= 1060) ? "火" : ((id <= 1065) ? "鬼火" : ((id <= 1070) ? "三尸虫" : ((id <= 1075) ? "遗忘" : ((id <= 1080) ? "smmh" : ((id <= 1085) ? "霹雳" : ((id <= 1090) ? "沧波" : ((id <= 1095) ? "甘霖" : "扶摇"))))))))))))))))));
            msg = msg.replace("|个数|", FBUtil.geshu(level, (int)sld, type) + "");
            if (id <= 1015 || (id >= 1071 && id <= 1075)) {
                BigDecimal skillhitrate = new BigDecimal(value + sv * new BigDecimal(Math.pow(sld, 0.3)).setScale(2, 4).doubleValue());
                msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
                msg = msg.replace("|回合|", "7");
            }
            else if (id <= 1020) {
                BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 15.0 * 17.0);
                msg = msg.replace("|伤害|", (level > 3) ? "15" : ((level > 1) ? "12.5" : "10"));
                msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
                msg = msg.replace("|回合|", "3");
            }
            else if (id <= 1040 || (id >= 1076 && id <= 1080)) {
                BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0);
                msg = msg.replace("|回合|", "7");
                msg = msg.replace("|伤害|", skillhitrate.setScale(2, 4).toString());
            }
            else if (id <= 1065 || id >= 1081) {
                BigDecimal skillhitrate = new BigDecimal(value + sv * Math.pow(sld, 0.4) * 1.75 * (double)lvl);
                msg = msg.replace("|伤害|", skillhitrate.intValue() + "");
            }
            else if (id <= 1070) {
                msg = msg.replace("|伤害|", (int)((value * (double)lvl + sld * sv / 1000.0) * 1000.0) + "");
                msg = msg.replace("|几率|", (int)(value * 100.0 + (double)(int)(sld / 250.0)) + "");
            }
            msg = msg.replace("|蓝|", (int)(mv * (sld / 25000.0 + 1.0)) + "");
        }
        else if (id >= 5001 && id <= 5015) {
            msg = "#c8A2BE2" + skill.getSkillname() + "#r#Y【消耗怨气值】" + skill.getDielectric() + "#r#W" + msg;
            int born = ImageMixDeal.userimg.getRoleShow().getTurnAround();
            int lvl2 = AnalysisString.lvlint((int)ImageMixDeal.userimg.getRoleShow().getGrade());
            int bzlvl = RoleLingFa.getRoleLingFa().getFaPJ();
            Lingbao fabao = RoleLingFa.getRoleLingFa().getFabaoByName(skill.getSkillname());
            if (fabao == null) {
                return;
            }
            int qv = RoleLingFa.getQv(fabao.getBaoquality());
            int blvl = fabao.getLingbaolvl().intValue();
            int pz = FBUtil.getFBlvl(born, lvl2, bzlvl, qv, blvl);
            double grow = 0.0;
            double value2 = 0.0;
            if (skill.getGrow() != null && !skill.getGrow().equals("")) {
                grow = Double.parseDouble(skill.getGrow());
            }
            if (skill.getValue() != null && !skill.getGrow().equals("")) {
                value2 = Double.parseDouble(skill.getValue());
            }
            String v1 = UserData.xiaoshu(value2 + (double)pz * grow);
            String v2 = FBUtil.getFBcx(id, blvl) + "";
            String v3 = null;
            String v4 = FBUtil.getFBsum(id, blvl) + "";
            if (id == 4014) {
                v3 = (int)((value2 + (double)pz * grow) * 12500.0) + "";
            }
            else if (id == 4015) {
                v3 = UserData.xiaoshu((value2 + (double)pz * grow) * 2.0 / 3.0);
            }
            msg = msg.replace("{概率}", "#R" + v1 + "#W");
            msg = msg.replace("{回合}", "#G" + v2 + "#W");
            if (v3 != null) {
                msg = msg.replace("{数值}", "#Y" + v3 + "#W");
            }
            msg = msg.replace("{目标}", "#Y" + v4 + "#W");
        }
        else if (id >= 9101 && id <= 9812) {
            msg = skillMsgchange(msg, skill);
            msg = "#cFF8C00" + skill.getSkillname() + "#r#Y【消耗怒气值】" + skill.getDielectric() + "#r#W" + msg;
        }
        else {
            return;
        }
        this.box.addText(msg, 320);
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 10;
        this.boxy = (int)goodx.getY() + 80;
        this.displaymsg();
    }
    
    public void showXF(String msg, String colorStr) {
        this.box.removemsg();
        this.boxw = msg.length() * 8 + 10;
        this.box.addText(" #c" + colorStr + msg, this.boxw, UIUtils.TEXT_FONT78);
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() - 110;
        this.boxy = (int)goodx.getY() + 10;
        this.displaymsg();
    }
    
    public void petshowXF(String msg, String colorStr) {
        this.box.removemsg();
        this.boxw = msg.length() * 8 + 20;
        this.box.addText(" #c" + colorStr + msg, this.boxw, UIUtils.TEXT_FONT78);
        this.boxh = this.box.getHeight();
        this.boxx = ScrenceUntil.Screen_x - 250;
        this.boxy = ScrenceUntil.Screen_y - 720;
        this.displaymsg();
    }
    
    public void showexe(String msg, String colorStr) {
        this.box.removemsg();
        this.boxw = msg.length() * 8 + 5;
        this.box.addText("#c" + colorStr + msg, this.boxw, UIUtils.TEXT_FONT);
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 15;
        this.boxy = (int)goodx.getY() + 100;
        this.displaymsg();
    }
    
    public void battleState(FightingManData manData) {
        List<String> state = manData.getStates();
        if (state.size() == 0 && manData.getAlpha() == 1.0f) {
            return;
        }
        this.box.removemsg();
        StringBuilder s = new StringBuilder();
        for (String str : state) {
            if (s.length() == 0) {
                s.append("#Y");
            }
            else {
                s.append("、");
            }
            if (NumberUtils.isNumber(str) && MsgJapnel.lingxiState.containsKey(str)) {
                s.append((String)MsgJapnel.lingxiState.get(str));
            }
            else {
                s.append(str);
            }
        }
        if (manData.getAlpha() < 1.0f) {
            if (s.length() == 0) {
                s.append("#Y隐身");
            }
            else {
                s.append("、隐身");
            }
        }
        this.box.addText(s.toString(), 150, UIUtils.TEXT_FONT);
        this.boxw = 150;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 30;
        this.boxy = (int)goodx.getY();
        this.displaymsg();
    }
    
    public void Strength(String name, String msg) {
        this.box.removemsg();
        this.box.addText("#cf8fc70" + name, 310, UIUtils.TEXT_FONT5);
        this.box.addText(msg, 310, UIUtils.TEXT_FONT1);
        this.boxw = 320;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX();
        this.boxy = (int)goodx.getY();
        this.displaymsg();
    }
    
    public static String StrengthChange(String remark, Skill skillAll, double level) {
        remark = remark.replace("{公式一}", Arith.mul(Double.parseDouble(skillAll.getGrow()), level) + "");
        return remark;
    }
    
    public void displaymsg() {
        MsgJframe.getJframe().setBounds(this.boxx, this.boxy, this.boxw, this.boxh);
        FormsManagement.showForm(46);
    }
    
    public ChatBox getBox() {
        return this.box;
    }
    
    public void setBox(ChatBox box) {
        this.box = box;
    }
    
    public void rolename(String msg, String colorStr) {
        this.box.removemsg();
        this.boxw = msg.length() * 12 + 10;
        this.box.addText(" #c" + colorStr + msg, this.boxw, UIUtils.TEXT_FONT78);
        this.boxh = this.box.getHeight();
        this.boxx = ScrenceUntil.Screen_x - 300;
        this.boxy = ScrenceUntil.Screen_y - 700;
        this.displaymsg();
    }
    
    public void showXFD2(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#G  " + text, 160);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - 35;
        this.displaymsg();
    }
    
    public void showXFD25(String text, String text2, String text3, String text4, String text5, String text6, int x, int y) {
        this.box.removemsg();
        this.box.addText("#c  " + text, 280, UIUtils.TEXT_HY193);
        this.box.addText("#c-------------------" + text2, 280, UIUtils.TEXT_FONT7);
        this.box.addText("#Y  " + text3, 280, UIUtils.TEXT_FONT);
        this.box.addText("#c  " + text4, 280, UIUtils.TEXT_FONT);
        this.box.addText("#c  " + text5, 280, UIUtils.TEXT_FONT);
        this.box.addText("#cEE2C2C  需要总心意点为:" + text6, 280, UIUtils.TEXT_FONT);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2;
        this.boxy = y - 35;
        this.displaymsg();
    }
    
    public static String SkillLowerLv(String remark, Skill skillAll, int lv) {
        int skillids = 0;
        String skillid = skillAll.getSkillid();
        int n = -1;
        switch (skillid.hashCode()) {
            case 1746997: {
                if (skillid.equals("9196")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1746998: {
                if (skillid.equals("9197")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 1746999: {
                if (skillid.equals("9198")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 1753507: {
                if (skillid.equals("9826")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                skillids = 10;
                break;
            }
            case 1: {
                skillids = 0;
                break;
            }
            case 2: {
                skillids = 53;
                break;
            }
            case 3: {
                skillids = 47;
                break;
            }
        }
        String level = String.valueOf(lv);
        remark = remark.replace("{公式一}", "#R" + Arith.add(Double.parseDouble(skillAll.getValue()), Arith.mul(Double.parseDouble(skillAll.getGrow()), Double.parseDouble(level))) + "#G");
        remark = remark.replace("{公式二}", "#R" + Arith.mul(Arith.mul(Arith.div(Double.parseDouble(skillAll.getGrow()), 4.0), 10.0), Double.parseDouble(level)) + "#G");
        remark = remark.replace("{公式五十四}", "#R" + Arith.add(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(level), 4.0)), 0.0) + "#G");
        remark = remark.replace("{公式五十五}", "#R" + Arith.add(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(level), 10.0)), (double)skillids) + "#G");
        remark = remark.replace("{公式六十}", "#R" + Arith.add(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(level), 1.0)), 0.0) + "#G");
        remark = remark.replace("{公式六十一}", "#R" + Arith.add(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(level), 0.5)), 0.0) + "#G");
        remark = remark.replace("{公式六十三}", "#R" + Arith.add(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(level), 8.0)), 0.0) + "#G");
        remark = remark.replace("{公式六十五}", "#R" + Arith.add(Arith.add(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(level), 5.0)), (double)skillids) + "#G");
        remark = remark.replace("{公式六十六}", "#R" + Arith.add(Arith.add(Double.parseDouble(skillAll.getGrow()), Arith.div(Double.parseDouble(level), 1.0)), (double)skillids) + "#G");
        remark = remark.replace("{公式七十六}", "#R" + Arith.add(Arith.mul(Double.parseDouble(skillAll.getGrow()), Arith.mul(Double.parseDouble(level), 2.0)), (double)skillids) + "#G");
        remark = remark.replace("{公式零零}", "#R" + Arith.add(Double.parseDouble(skillAll.getValue1()), Arith.mul(Double.parseDouble(skillAll.getGrow1()), Double.parseDouble(level))) + "#G");
        remark = remark.replace("{公式零二}", "#R" + Arith.add(Double.parseDouble(skillAll.getValue2()), Arith.mul(Double.parseDouble(skillAll.getGrow2()), Double.parseDouble(level))) + "#G");
        remark = remark.replace("玩家", "#R玩家#G");
        remark = remark.replace("NPC", "#RNPC#G");
        remark = remark.replace("%", "#R%#G");
        return remark;
    }
    
    public void skillmsg(Skill skill) {
        this.box.removemsg();
        this.box.removemsg();
        if (Integer.parseInt(skill.getSkillid()) < 1100) {
            this.SM(skill, 1.0, 200);
        }
        else {
            String msg = skill.getRemark();
            double sv = Double.parseDouble(skill.getGrow());
            long qm = (long)UserMessUntil.getChosePetMes().getFriendliness();
            double value = Double.parseDouble(skill.getValue());
            if (skill.getSkillid().equals("1237")) {
                msg = msg.replace("{0}", String.format("%.2f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
                msg = msg.replace("{1}", String.format("%.2f", new Object[] { Double.valueOf(25.0 + CustomFunction.XS(qm, sv)) }));
            }
            else if (skill.getSkillid().equals("1238") || skill.getSkillid().equals("1240")) {
                msg = msg.replace("{0}", String.format("%.2f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
                msg = msg.replace("{1}", String.format("%.2f", new Object[] { Double.valueOf(Double.parseDouble(skill.getValue1()) + CustomFunction.XS(qm, Double.parseDouble(skill.getGrow1()))) }));
            }
            else if (skill.getSkillid().equals("1241")) {
                msg = msg.replace("{0}", String.format("%.2f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
            }
            else if (skill.getSkillid().equals("1216")) {
                msg = msg.replace("{0}", "2");
                msg = msg.replace("{1}", String.format("%.0f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
                msg = msg.replace("{2}", String.format("10", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
            }
            else if (skill.getSkillid().equals("1256")) {
                msg = msg.replace("{0}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }) + "#G");
                msg = msg.replace("{1}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }) + "#G");
            }
            else if (skill.getSkillid().equals("1257")) {
                msg = msg.replace("{0}", "#R" + String.format(value + (double)(int)CustomFunction.XS(qm, sv) + "#G", new Object[0]));
            }
            else if (skill.getSkillid().equals("1255")) {
                msg = msg.replace("{0}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }) + "#G");
                msg = msg.replace("{1}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }) + "#G");
                msg = msg.replace("{2}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }) + "#G");
            }
            else if (skill.getSkillid().equals("1259")) {
                msg = msg.replace("{0}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }) + "#G");
                msg = msg.replace("{1}", "#R" + String.format("%.1f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }) + "#G");
            }
            else {
                int skillid = Integer.parseInt(skill.getSkillid());
                if (skillid >= 4000 && skillid <= 4022) {
                    if (skillid >= 4022) {
                        if (msg.contains("{守护石1}")) {
                            msg = msg.replace("{守护石1}", "#R" + String.format("%.1f", new Object[] { Double.valueOf((double)((List<Double>)MsgJapnel.listMap.get(Integer.valueOf(skillid - 23))).get(0) / 30.0) }) + "%#G");
                        }
                        if (msg.contains("{守护石2}")) {
                            msg = msg.replace("{守护石2}", "#R" + String.format("%.1f", new Object[] { Double.valueOf((double)((List<Double>)MsgJapnel.listMap.get(Integer.valueOf(skillid - 23))).get(1) / 30.0) }) + "%#G");
                        }
                    }
                    else {
                        if (msg.contains("{守护石1}")) {
                            msg = msg.replace("{守护石1}", "#R" + String.format("%.1f", new Object[] { Double.valueOf((double)((List<Double>)MsgJapnel.listMap.get(Integer.valueOf(skillid))).get(0) / 15.0) }) + "%#G");
                        }
                        if (msg.contains("{守护石2}")) {
                            msg = msg.replace("{守护石2}", "#R" + String.format("%.1f", new Object[] { Double.valueOf((double)((List<Double>)MsgJapnel.listMap.get(Integer.valueOf(skillid))).get(1) / 15.0) }) + "%#G");
                        }
                    }
                }
            }
            String[] v = PetSkillsJpanel.StringReplace(msg).split("\\|");
            for (int i = 0; i < v.length; ++i) {
                String[] v2 = v[i].split("=");
                if (skill.getSkilltype().equals("4") && v2[1].equals("0/999")) {
                    v2[1] = UserMessUntil.getChosePetMes().getTrainNum() + "/999";
                }
                String vg;
                if ((vg = this.gongshi(v, i)) != null) {
                    this.box.addText("#c" + v2[0] + " " + v2[1] + "#c" + v[++i].split("=")[0] + vg + "#r", 320);
                }
                else {
                    this.box.addText("#c" + v2[0] + " " + v2[1] + "#r", 320);
                }
            }
            this.boxw = 320;
            this.boxh = ScrenceUntil.Screen_y;
            Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
            this.boxx = (int)goodx.getX() + 8;
            this.boxy = (int)goodx.getY() + 150;
            this.displaymsg();
        }
    }
    
    public String gongshi(String[] v, int i) {
        if (v.length > i + 1) {
            String[] vs = v[i + 1].split("=");
            if (vs.length > 1 && (vs[1].equals("{公式一}") || vs[1].equals("{公式二}") || vs[1].equals("{公式三}") || vs[1].equals("{公式四}") || vs[1].equals("{公式五}"))) {
                return "1";
            }
        }
        return null;
    }
    
    public void vips1(String msg, String colorStr) {
        this.box.removemsg();
        this.boxw = msg.length() * 14 + 10;
        this.box.addText("#c" + colorStr + msg, this.boxw, UIUtils.TEXT_FONT78);
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() - 25;
        this.boxy = (int)goodx.getY() + 20;
        this.displaymsg();
    }
    
    static {
        (MsgJapnel.lingxiState = new HashMap<>()).put("11006", "#G羊入虎口#Y");
        MsgJapnel.lingxiState.put("11007", "#G势不可挡#Y");
        MsgJapnel.lingxiState.put("11010", "#G化险为夷#Y");
        MsgJapnel.lingxiState.put("11012", "#G冲冠一怒#Y");
        MsgJapnel.lingxiState.put("11013", "#G唇亡齿寒#Y");
        MsgJapnel.lingxiState.put("11014", "#G怒不可遏#Y");
        MsgJapnel.lingxiState.put("11015", "#G晴天霹雳#Y");
        MsgJapnel.lingxiState.put("11017", "#G绝地反击#Y");
        MsgJapnel.lingxiState.put("11018", "#G火冒三丈#Y");
        MsgJapnel.lingxiState.put("11019", "#G攻守兼备#Y");
        MsgJapnel.lingxiState.put("11025", "#G惊涛拍岸#Y");
        MsgJapnel.lingxiState.put("11027", "#G八面玲珑#Y");
        MsgJapnel.lingxiState.put("11029", "#G风荷送香#Y");
        MsgJapnel.lingxiState.put("11031", "#G清风盈袖#Y");
        MsgJapnel.lingxiState.put("11032", "#G月共潮生#Y");
        MsgJapnel.lingxiState.put("11033", "#G节节高升#Y");
        MsgJapnel.lingxiState.put("11034", "#G步步为营#Y");
        MsgJapnel.lingxiState.put("11035", "#G待时而飞#Y");
        MsgJapnel.lingxiState.put("fbYsjl", "银索金铃");
        MsgJapnel.lingxiState.put("fbJjl", "将军令");
        MsgJapnel.lingxiState.put("fbDsc", "大势锤");
        MsgJapnel.lingxiState.put("fbQbllt", "七宝玲珑塔");
        MsgJapnel.lingxiState.put("fbHlz", "黑龙珠");
        MsgJapnel.lingxiState.put("fbYmgs", "幽冥鬼手");
        MsgJapnel.lingxiState.put("fbDsy", "大手印");
        MsgJapnel.lingxiState.put("fbJqb", "绝情鞭");
        MsgJapnel.lingxiState.put("fbQw", "情网");
        MsgJapnel.lingxiState.put("fbBld", "宝莲灯");
        MsgJapnel.lingxiState.put("fbJge", "金箍儿");
        MsgJapnel.lingxiState.put("fbFty", "番天印");
        MsgJapnel.lingxiState.put("fbJljs", "锦襕袈裟");
        MsgJapnel.lingxiState.put("fbBgz", "白骨爪");
        MsgJapnel.lingxiState.put("fbHd", "化蝶");
        MsgJapnel.lingxiState.put("7002", "飞花溅玉");
        MsgJapnel.lingxiState.put("7008", "百害不侵");
        MsgJapnel.lingxiState.put("7015", "饮鸩止渴");
        MsgJapnel.lingxiState.put("7026", "雪上加霜");
        MsgJapnel.lingxiState.put("7033", "蓄势待发");
        MsgJapnel.lingxiState.put("7034", "赤地千里");
        MsgJapnel.lingxiState.put("7035", "梦回前朝");
        MsgJapnel.lingxiState.put("9389", "流风回雪");
        MsgJapnel.lingxiState.put("1231", "天降脱兔");
        MsgJapnel.lingxiState.put("1232", "灵魂封魔");
        MsgJapnel.lingxiState.put("1866", "如虎添翼");
        MsgJapnel.lingxiState.put("1869", "夺魂索命");
        MsgJapnel.lingxiState.put("1873", "福禄双全");
        MsgJapnel.lingxiState.put("1308", "心有灵犀");
        MsgJapnel.lingxiState.put("6018", "以戈止戈");
        MsgJapnel.lingxiState.put("6019", "寸步难行");
        MsgJapnel.lingxiState.put("6020", "玄妙神通");
        MsgJapnel.lingxiState.put("6021", "有的放矢");
        MsgJapnel.lingxiState.put("6022", "万古同悲");
        MsgJapnel.lingxiState.put("6023", "博观约取");
        MsgJapnel.lingxiState.put("6024", "生生不息");
        MsgJapnel.lingxiState.put("6025", "回头是岸");
        MsgJapnel.lingxiState.put("6026", "斩草除根");
        MsgJapnel.lingxiState.put("6027", "平湖秋月");
        MsgJapnel.lingxiState.put("6028", "夕阳箫鼓");
        MsgJapnel.lingxiState.put("6029", "回光返照");
        MsgJapnel.lingxiState.put("6030", "加强加速");
        MsgJapnel.lingxiState.put("6031", "水魔附身");
        MsgJapnel.lingxiState.put("6044", "五行合一");
        MsgJapnel.lingxiState.put("6032", "加强三尸虫");
        MsgJapnel.lingxiState.put("6033", "时过境迁");
        MsgJapnel.lingxiState.put("6034", "忘乎所以");
        MsgJapnel.lingxiState.put("6035", "加强加防");
        MsgJapnel.lingxiState.put("6036", "加强魅惑");
        MsgJapnel.lingxiState.put("6037", "伤筋动骨");
        MsgJapnel.lingxiState.put("6038", "吹箫引凤");
        MsgJapnel.lingxiState.put("6039", "加强加攻");
        MsgJapnel.lingxiState.put("6040", "如鱼得水");
        MsgJapnel.lingxiState.put("6041", "追亡逐北");
        MsgJapnel.lingxiState.put("6042", "龙族震怒");
        MsgJapnel.lingxiState.put("6045", "钻心蚀骨");//新修赤炼
        MsgJapnel.lingxiState.put("1238", "归去来兮");
        MsgJapnel.lingxiState.put("1263", "金身不灭");
        MsgJapnel.listMap = new HashMap<>();
        List<Double> list = new ArrayList<>();
        list.add(Double.valueOf(30.0));
        MsgJapnel.listMap.put(Integer.valueOf(4000), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(15.0));
        MsgJapnel.listMap.put(Integer.valueOf(4001), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(6.0));
        list.add(Double.valueOf(15000.0));
        MsgJapnel.listMap.put(Integer.valueOf(4002), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(4.5));
        list.add(Double.valueOf(3000.0));
        MsgJapnel.listMap.put(Integer.valueOf(4003), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(60.0));
        list.add(Double.valueOf(60.0));
        MsgJapnel.listMap.put(Integer.valueOf(4004), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(30.0));
        list.add(Double.valueOf(45.0));
        MsgJapnel.listMap.put(Integer.valueOf(4005), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(105.0));
        list.add(Double.valueOf(30.0));
        MsgJapnel.listMap.put(Integer.valueOf(4006), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(45.0));
        MsgJapnel.listMap.put(Integer.valueOf(4007), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(15.0));
        MsgJapnel.listMap.put(Integer.valueOf(4008), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(24.0));
        MsgJapnel.listMap.put(Integer.valueOf(4009), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(45.0));
        list.add(Double.valueOf(15.0));
        MsgJapnel.listMap.put(Integer.valueOf(4010), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(45.0));
        list.add(Double.valueOf(16.0));
        MsgJapnel.listMap.put(Integer.valueOf(4011), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(75.0));
        list.add(Double.valueOf(15.0));
        MsgJapnel.listMap.put(Integer.valueOf(4012), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(3.0));
        MsgJapnel.listMap.put(Integer.valueOf(4013), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(60.0));
        list.add(Double.valueOf(15.0));
        MsgJapnel.listMap.put(Integer.valueOf(4014), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(60.0));
        list.add(Double.valueOf(15.0));
        MsgJapnel.listMap.put(Integer.valueOf(4015), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(22.5));
        MsgJapnel.listMap.put(Integer.valueOf(4016), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(75.0));
        list.add(Double.valueOf(15.0));
        MsgJapnel.listMap.put(Integer.valueOf(4017), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(75.0));
        MsgJapnel.listMap.put(Integer.valueOf(4018), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(45.0));
        list.add(Double.valueOf(30.0));
        MsgJapnel.listMap.put(Integer.valueOf(4019), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(45.0));
        list.add(Double.valueOf(30.0));
        MsgJapnel.listMap.put(Integer.valueOf(4020), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(60.0));
        list.add(Double.valueOf(30.0));
        MsgJapnel.listMap.put(Integer.valueOf(4021), new ArrayList(list));
        list = new ArrayList<>();
        list.add(Double.valueOf(9.0));
        list.add(Double.valueOf(9.0));
        MsgJapnel.listMap.put(Integer.valueOf(4022), new ArrayList(list));
    }

    public void showZXY(String text, int x, int y) {
        this.box.removemsg();
        String text1 = text.replaceAll("[^\\u4e00-\\u9fff]", "");
        this.box.addText("#Y " + text, text1.length() * 14);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x - this.box.getWidth() / 2 + 50;
        this.boxy = y + 30;
        this.displaymsg();
    }
}
