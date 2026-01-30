package org.come.mouslisten;

import org.come.until.FormsManagement;
import java.text.ParseException;
import java.util.Calendar;
import org.come.bean.DiceReidsBase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import org.come.bean.PathPoint;
import org.come.entity.RoleSummoning;
import java.math.BigDecimal;
import org.come.bean.LoginResult;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.come.Jpanel.GameJpanel;
import com.tool.role.GetExp;
import org.come.until.AnalysisString;
import org.come.until.UserMessUntil;
import org.come.Frame.MsgJframe;
import com.tool.PanelDisplay.RolePanelShow;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.awt.event.MouseListener;

public class WLLMouslisten implements MouseListener
{
    private int i;
    private Timer timer;
    
    public WLLMouslisten(int i) {
        this.i = i;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {System.out.println("wll released...");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (this.i == 28) {
            String Max = RolePanelShow.accessMaxValue((int)loginResult.getGrade()).toString();
            BigDecimal EXE = RoleData.getRoleData().getLoginResult().getExperience();
            MsgJframe.getJframe().getJapnel().showXF(((this.i % 2 == 0) ? "EXE(经验):" : "") + EXE + "/" + Max, "FFFFFF");
        }
        if (this.i == 30) {
            String Max = RolePanelShow.accessMaxValue((int)loginResult.getGrade()).toString();
            BigDecimal EXE = RoleData.getRoleData().getLoginResult().getExperience();
            MsgJframe.getJframe().getJapnel().showexe(((this.i % 2 == 0) ? "经验值:" : "") + EXE + "/" + Max, "FFFF00");
        }
        if (this.i == 29) {
            RoleSummoning pet = UserMessUntil.getChosePetMes();
            if (UserMessUntil.getChosePetMes() == null) {
                return;
            }
            String max = GetExp.getBBExp(pet.getTurnRount(), AnalysisString.petLvlint((int)pet.getGrade())) + "";
            RoleSummoning rs = UserMessUntil.getPetRgid(RoleData.getRoleData().getLoginResult().getSummoning_id());
            if (rs == null) {
                throw new AssertionError();
            }
            BigDecimal EXES = rs.getExp();
            MsgJframe.getJframe().getJapnel().petshowXF(((this.i % 2 == 0) ? "" : "EXE(经验):") + EXES + "/" + max, "FFFFFF");
        }
        if (this.i == 0) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            String format = df.format(date);
            MsgJframe.getJframe().getJapnel().showXFD("当前时间：" + format, point.getX(), point.getY());
        }
        if (this.i == 1) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showSZJ("藏宝阁", point.getX(), point.getY());
        }
        if (this.i == 2) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showSZJ("典  当", point.getX(), point.getY());
        }
        if (this.i == 3) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showSZJ("取  回", point.getX(), point.getY());
        }
        if (this.i == 4) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFDS("宝宝转生", point.getX(), point.getY());
        }
        if (this.i == 5) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFDS("杂货符文", point.getX(), point.getY());
        }
        if (this.i == 6) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFDS("单人比武", point.getX(), point.getY());
        }
        if (this.i == 7) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFDS("点击缩放", point.getX(), point.getY());
        }
        if (this.i == 8) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("新手礼包", point.getX(), point.getY());
        }
        if (this.i == 9) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFDS("法术切换", point.getX(), point.getY());
        }
        if (this.i == 10) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFDS("每日活动", point.getX(), point.getY());
        }
        if (this.i == 11) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("新手伙伴", point.getX(), point.getY());
        }
        if (this.i == 12) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("游戏攻略", point.getX(), point.getY());
        }
        if (this.i == 13) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("领取双倍", point.getX(), point.getY());
        }
        if (this.i == 14) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("冻结双倍", point.getX(), point.getY());
        }
        if (this.i == 15) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("仙玉商城", point.getX(), point.getY());
        }
        if (this.i == 16) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("冻结双倍", point.getX(), point.getY());
        }
        if (this.i == 17) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("当前整理", point.getX(), point.getY());
        }
        if (this.i == 18) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("全局整理", point.getX(), point.getY());
        }
        if (this.i == 19) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("当前清理", point.getX(), point.getY());
        }
        if (this.i == 20) {
            if (RoleData.getRoleData().getLoginResult().getRolename() == null) {
                return;
            }
            String name = RoleData.getRoleData().getLoginResult().getRolename();
            Integer levl = RoleData.getRoleData().getLoginResult().getGrade();
            if ((int)levl <= 102) {
                MsgJframe.getJframe().getJapnel().rolename(((this.i % 3 == 0) ? "" : "名字:") + name + " 等级:0转" + levl + "级", "FFFFFF");
            }
            else if ((int)levl > 102 && (int)levl <= 210) {
                MsgJframe.getJframe().getJapnel().rolename(((this.i % 3 == 0) ? "" : "名字:") + name + " 等级:1转" + ((int)levl - 102 + 14) + "级", "FFFFFF");
            }
            else if ((int)levl > 210 && (int)levl <= 338) {
                MsgJframe.getJframe().getJapnel().rolename(((this.i % 3 == 0) ? "" : "名字:") + name + " 等级:2转" + ((int)levl - 210 + 14) + "级", "FFFFFF");
            }
            else if ((int)levl > 338 && (int)levl <= 459) {
                MsgJframe.getJframe().getJapnel().rolename(((this.i % 3 == 0) ? "" : "名字:") + name + " 等级:3转" + ((int)levl - 338 + 59) + "级", "FFFFFF");
            }
            else if ((int)levl > 459) {
                MsgJframe.getJframe().getJapnel().rolename(((this.i % 3 == 0) ? "" : "名字:") + name + " 等级:飞升" + ((int)levl - 459 + 139) + "级", "FFFFFF");
            }
        }
        if (this.i == 21) {
            if (UserMessUntil.getChosePetMes() == null) {
                return;
            }
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null) {
                String pet2 = UserMessUntil.getChosePetMes().getSummoningname();
                int pes = (int)UserMessUntil.getChosePetMes().getFaithful();
                MsgJframe.getJframe().getJapnel().showzhaohuans(((this.i % 2 == 0) ? "" : "名字:") + pet2 + " 忠诚:" + pes, "FFFFFF");
            }
        }
        if (this.i == 22) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().vips("亲密度影响召唤兽出现连击、致命、内丹效果及技能效果。召唤兽转生需要消耗亲密度。", point.getX(), point.getY());
        }
        if (this.i == 23) {
            RoleSummoning pet = UserMessUntil.getChosePetMes();
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() == null) {
                return;
            }
            String max2 = GetExp.getBBExp(pet.getTurnRount(), AnalysisString.petLvlint((int)pet.getGrade())) + "";
            RoleSummoning rs2 = UserMessUntil.getPetRgid(RoleData.getRoleData().getLoginResult().getSummoning_id());
            assert rs2 != null;
            BigDecimal EXES2 = rs2.getExp();
            MsgJframe.getJframe().getJapnel().showexe(((this.i % 2 == 0) ? "" : "经验值:") + EXES2 + "/" + max2, "FFFF00");
        }
        if (this.i == 24) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("设置密码", point.getX(), point.getY());
        }
        if (this.i == 25) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("装备加锁", point.getX(), point.getY());
        }
        if (this.i == 26) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("装备解锁", point.getX(), point.getY());
        }
        if (this.i == 27) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("联系管理", point.getX(), point.getY());
        }
        if (this.i == 31) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().vip("幸运奖池卷", point.getX(), point.getY());
        }
        if (this.i == 32) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("ALT+P", point.getX(), point.getY());
        }
        if (this.i == 35) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD("#Y星盘", point.getX(), point.getY());
        }
        if (this.i == 43) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("宝宝(Alt+y)", point.getX(), point.getY());
        }
        if (this.i == 44) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("道具(Alt+e)", point.getX(), point.getY());
        }
        if (this.i == 45) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("组队(Alt+t)", point.getX(), point.getY());
        }
        if (this.i == 46) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("攻击(Alt+a)", point.getX(), point.getY());
        }
        if (this.i == 47) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("  技  能  ", point.getX(), point.getY());
        }
        if (this.i == 48) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFDS22("交易(Alt+x)", "给予(Alt+g)", point.getX(), point.getY());
        }
        if (this.i == 49) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFDS22("#Y 坐驾       坐骑(Alt+r)", "飞行(Alt+c)", point.getX(), point.getY()-28);
        }
        if (this.i == 50) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("任务(Alt+q)", point.getX(), point.getY());
        }
        if (this.i == 51) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("交友(Alt+f)", point.getX(), point.getY());
        }
        if (this.i == 52) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("帮派(Alt+b)", point.getX(), point.getY());
        }
        if (this.i == 53) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("系统(Alt+s)", point.getX(), point.getY());
        }
        if (this.i == 214) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showCdan("功绩千秋(Alt+Z)", point.getX(), point.getY()-20);
        }
        if (this.i == 300) {//商城快捷提示
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD5("商城Alt+p", point.getX(), point.getY());
        }
        if (this.i == 302) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD3("小地图Alt+1", point.getX(), point.getY());
        }
        if (this.i == 303) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD3("世界图Alt+2", point.getX(), point.getY());
        }
        if (this.i == 304) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD4("新手指引", point.getX(), point.getY());
        }
        if (this.i == 305) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD4("排行榜", point.getX(), point.getY());
        }
        if (this.i == 306) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD4("藏宝阁", point.getX(), point.getY());
        }
        if (this.i == 307) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD5("日常任务", point.getX(), point.getY());
        }
        if (this.i == 400) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("腾云", "", "腾云而起，心乘风归", "心猿的速度提升。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 401) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("破云", "", "飞影破云，心啸长歌", "心猿的攻击力提升。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 402) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("踏云", "", "踏云三千，双心一念", "心猿的致命几率提升。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 403) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("圣佑", "", "元神之悟，羁绊之心", "心猿攻击时有几率感悟大圣羁绊之心，得悟空元神相助，对敌方随机多个单位造成一次物理伤害。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 404) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("情心", "", "情心意意，来去相依", "心猿得悟空相助的几率提高。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 405) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("神通", "", "彼造化力，心有神通", "心猿攻击时有几率感悟大圣羁绊之心，化身为悟空，提高自身攻击力，在敌方随机多个单位周围召唤悟空分身，攻击过程中分身有几率对目标造成物理伤害。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 406) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("心义", "", "心尤情义，悟影自现", "心猿化身悟空的几率提高。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 407) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("悟影", "", "心修悟影，亦真亦假", "心猿感悟大圣羁绊之心时，感悟悟空元神和召唤悟空分身的数量增加。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 408) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("追云", "", "追云逐月，心求所竞", "心猿的连击率提升。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 409) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("潜云", "", "云潜于渊，心闻于天", "心猿的忽视防御几率提升。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 410) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("纵云", "", "无纵无云，无心无意", "心猿的忽视防御程度提升。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 411) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("魔威", "", "元神之悟，自由之心", "心猿攻击过程中有几率感悟大圣自由之心，得六耳元神相助，对目标造成一次额外物理伤害。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 412) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("逍心", "", "逍心切切，来去自然", "心猿得六耳元神相助的几率提升。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 413) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("灵通", "", "彼造化力，心有灵通", "心猿攻击时有几率感悟大圣自由之心，化身为六耳，并提高自身连击率，攻击目标时有几率对目标造成重击，伤害提搞，同时对目标周围距离为1的单位造成溅射伤害。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 414) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("遥心", "", "心念逍遥，六识通灵", "心猿化身六耳的几率提高。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 415) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD25("六识", "", "心觉六识，六念无踪", "心猿感悟大圣自由之心时，攻击力、忽视防御几率和程度提升。", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 500) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD26("清理缓存", "", "游戏缓存清理", "如游戏运行卡顿或者游戏运行时间比较长，建议使用清理缓存进行清理。清理完缓存直接关闭命令窗口即可！", " ", "", point.getX() + 30, point.getY() + 60);
        }
        if (this.i == 501) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showSMWD("#G因每个玩家对游戏理解的差异性,或多或少的会体验稍差,特此开放此政策照顾每位玩家的游戏体验！#Y#r一、转区币获得：#W#r1、转区币是玩家在本服的历史赞助转换货币；#r2、当本区体验不满意后,可以联系GM提出转入后续新区的申请；#Y#r二、申请条件：#W#r1、提出申请的玩家名下账号角色真实有效；#r2、该账号下从创建至提出申请日止游戏内贵重的物资道具并未售卖、丢弃或赠送给他人,物资真实有效#R(也就是游戏的道具只能进不能出才能满足转区条件,自己使用了就看成品道具可参考出大概的消耗)；#W#r3、转入新区后也禁止售卖给他人牟利,如发现有售卖牟利现象,GM有权对玩家的账号进行封停删除并不在进行售后服务；#r4、自主自愿原则；#r5、本游戏只能带给各位长期有效的体验,提供一定的娱乐,带来情绪价值,#R不具有保值性,#W普通玩家请理性消费。对于实力雄厚的老板鼓励大家多多打赏赞助以便用于后续游戏运营、开发建设；#r#G本服鼓励玩家线下交易收售他人物资道具,担保交易后续有意想不到的作用！", point.getX(), point.getY());
        }
        if (this.i == 502) {
            this.updateAndDisplayTime();
        }
        if (this.i == 1135) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showXFD2("#G开启封印方法#r使用已提取技能的超级聚魄丹开格子！。", point.getX()+30, point.getY()-20);
        }
        if (this.i == 9999) {
            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            String str = "每日阿三,钟馗抓鬼,天庭#r鬼王,修罗,域外,宝象除魔活#r动分别达到100/200次 #r可进行抽奖！";
            MsgJframe.getJframe().getJapnel().showXFD(str, point.getX(), point.getY(),200); //活动文案
        }
    }
    
    private void startTimer() {
        (this.timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WLLMouslisten.this.updateAndDisplayTime();
            }
        })).start();
    }
    
    private void updateAndDisplayTime() {
        PathPoint point = GameJpanel.getGameJpanel().mousepath();
        int sum = UserMessUntil.getChosePetMes().getDicenum();
        String oldpet = DiceReidsBase.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (oldpet != null) {
                Date date = sdf.parse(oldpet);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(10, 2);
                long startTime = c.getTimeInMillis();
                long endTime = System.currentTimeMillis();
                long Time = startTime - endTime;
                long hours = Time / 3600000L;
                long minutes = Time % 3600000L / 60000L;
                long seconds = Time % 60000L / 1000L;
                String formattedTime = String.format("%01d小时%02d分钟%02d秒", new Object[] { Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds) });
                if (Time > 0L) {
                    MsgJframe.getJframe().getJapnel().DiceshowCdan("冷却倒计时：" + formattedTime + "#r 温馨提示：若点击后未获得高级技能，则再点击#R#r " + (15 - sum) + "次#Y后必获得", point.getX() + 25, point.getY() + 60);
                }
                else {
                    MsgJframe.getJframe().getJapnel().DiceshowCdan("冷却倒计时：0:00:00 #r 温馨提示：若点击后未获得高级技能，则再点击#R#r " + (15 - sum) + "次#Y后必获得", point.getX() + 25, point.getY() + 60);
                }
            }
            else {
                MsgJframe.getJframe().getJapnel().DiceshowCdan("冷却倒计时：0:00:00 #r 温馨提示：若点击后未获得高级技能，则再点击#R#r " + (15 - sum) + "次#Y后必获得", point.getX() + 25, point.getY() + 60);
            }
        }
        catch (ParseException a) {
            throw new RuntimeException(a);
        }
    }
    
    private void stopTimer() {
        if (this.timer != null) {
            this.timer.stop();
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.stopTimer();
        FormsManagement.HideForm(46);
        FormsManagement.HideForm(6222);
    }
}
