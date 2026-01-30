package come.tool.BangBattle;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import come.tool.newGang.GangUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.come.entity.Gang;
import org.come.server.GolemServer;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//记录已经报名的帮派
public class BangBattlePool
{
    private static BangBattlePool BangBattlePool;
    public long week;
    public Group group;
    public List<BangFight> BangFights;
    private BangThread bangThread;
    
    public BangBattlePool() {
        this.BangFights = new ArrayList<>();
    }
    
    public static BangBattlePool getBangBattlePool() {
        if (come.tool.BangBattle.BangBattlePool.BangBattlePool == null) {
            come.tool.BangBattle.BangBattlePool.BangBattlePool = new BangBattlePool();
        }
        if (come.tool.BangBattle.BangBattlePool.BangBattlePool.week != BangFileSystem.getBangFileSystem().getweek()) {
            come.tool.BangBattle.BangBattlePool.BangBattlePool.init();
        }
        return come.tool.BangBattle.BangBattlePool.BangBattlePool;
    }
    /**
     * 初始化
     */
    public void init() {
        BangFileSystem bangFileSystem = BangFileSystem.getBangFileSystem();
        this.week = bangFileSystem.getweek();
        String text = bangFileSystem.DataReading(new BigDecimal(this.week));
        if (text.equals("")) {
            this.group = new Group(null, 0);
        }
        else {
            (this.group = (Group)GsonUtil.getGsonUtil().getgson().fromJson(text, Group.class)).init(0);
        }
        List<Gang> allGang = AllServiceUtil.getGangService().findAllGang();
        for (Gang gang : allGang) {
            List<BangPoints> collect = (List)this.group.getlist().stream().filter(item/* come.tool.BangBattle.BangPoints, */ -> item.getId().compareTo(gang.getGangid()) == 0).collect(Collectors.toList());
            if (collect.size() == 0) {
                this.group.addlist(new BangPoints(gang.getGangid()));
                BangFileSystem.getBangFileSystem().DataSaving(this);
            }
        }
        //分组
        this.bangThread = new BangThread(this);
        Thread t = new Thread(this.bangThread);
        t.start();
    }
    //根据帮派获取沙盘
    public BangFight getBangFight(BigDecimal bang_id) {
        if (bang_id == null) {
            return null;
        }
        for (int i = this.BangFights.size() - 1; i >= 0; --i) {
            if (((BangFight)this.BangFights.get(i)).getMap(bang_id) != null) {
                return (BangFight)this.BangFights.get(i);
            }
        }
        return null;
    }
    //判断现在是否在帮战报名时间内
    public boolean isEnroll() {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(7);//周1   1是周日
        if (weekday == 2) {
            int hour = c.get(11);
            if (hour >= 8) {
                return true;
            }
        }
        else if (weekday == 6) {//周五
            int hour = c.get(11);
            if (hour < 19) {
                return true;
            }
        }
        else if (weekday == 3 || weekday == 4 || weekday == 5) {
            return true;
        }
        return true;
//        return false;
    }
    //获取帮派
    public BangPoints getBangPoints(BigDecimal id) {
        if (id == null) {
            return null;
        }
        for (int i = this.group.getlist().size() - 1; i >= 0; --i) {
            if (((BangPoints)this.group.getlist().get(i)).getId().compareTo(id) == 0) {
                return (BangPoints)this.group.getlist().get(i);
            }
        }
        return null;
    }
    //配对
    public void Match(Group group, int sum) {
        if (group == null) {
            return;
        }
        Group victory = group.victory;
        Group fail = group.fail;
        if (victory == null && fail == null) {
            if (group.getlist().size() > 1) {
                for (int i = (group.getlist().size() - 1) / 2; i >= 0; --i) {
                    BangPoints Left = null;
                    if (i * 2 < group.getlist().size()) {
                        Left = (BangPoints)group.getlist().get(i * 2);
                    }
                    BangPoints Right = null;
                    if (i * 2 + 1 < group.getlist().size()) {
                        Right = (BangPoints)group.getlist().get(i * 2 + 1);
                    }
                    BangFight bangFight = new BangFight(sum, Left, Right);
                    this.BangFights.add(bangFight);
                    if (Right == null || Left == null) {
                        if (Right != null) {
                            this.BangBattleBey(Right, bangFight, 1);
                        }
                        if (Left != null) {
                            this.BangBattleBey(Left, bangFight, 2);
                        }
                    }
                    else {
                        this.groupBroadcastOfTheGame(Left, Right, bangFight);
                    }
                }
            }
        }
        else {
            this.Match(victory, sum + 1);
            this.Match(fail, sum + 1);
        }
    }
    
    private void BangBattleBey(BangPoints bangPoints, BangFight bangFight, int i) {
        Gang gang = GangUtil.getGang(bangPoints.getId());
        // 发起世界喊话
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage("#Y" + gang.getGangname() + "#W在帮派分组中轮空#53直接晋级下一阶段比赛#51");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        bangFight.BangState = 2;
    }
    
    private void groupBroadcastOfTheGame(BangPoints l, BangPoints r, BangFight bangFight) {
        Gang gang1 = GangUtil.getGang(l.getId());
        Gang gang2 = GangUtil.getGang(r.getId());
        // 发起世界喊话
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage("#R甲级帮战：#Y" + gang1.getGangname() + "#W 对战 #Y" + gang2.getGangname() + "#W请大家抓紧时间入场了#1");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public void FightOpenClose() {
        this.BangFights.clear();
        this.Match(this.group, 0);
        for (int i = 0; i < this.BangFights.size(); ++i) {
            ((BangFight)this.BangFights.get(i)).BangState = 1;
        }
        GolemServer.isFighting=true;//机器人帮战
    }
    
    public void WinOrLose(BangFight bangFight) {
    }
    
    public void NewWeek() {
        BangFileSystem bangFileSystem = BangFileSystem.getBangFileSystem();
        bangFileSystem.DataSaving(this);
        ++this.week;
        this.BangFights.clear();
        String text = bangFileSystem.DataReading(new BigDecimal(this.week));
        if (text.equals("")) {
            this.group = new Group(null, 0);
        }
        else {
            (this.group = (Group)GsonUtil.getGsonUtil().getgson().fromJson(text, Group.class)).init(0);
        }
    }
    
    public class Group
    {
        private List<BangPoints> list;
        public transient Group victory;
        public transient Group fail;
        
        public Group(List<BangPoints> list, int sum) {
            this.list = list;
            this.init(sum);
        }
        
        public List<BangPoints> getlist() {
            if (this.list == null) {
                this.list = new ArrayList<>();
            }
            return this.list;
        }
        
        public void addlist(BangPoints bangPoints) {
            if (bangPoints == null) {
                return;
            }
            this.getlist().add(bangPoints);
        }
        
        public void init(int sum) {
            List<BangPoints> vs = new ArrayList<>();
            List<BangPoints> fs = new ArrayList<>();
            for (int i = this.getlist().size() - 1; i >= 0; --i) {
                BangPoints bang = (BangPoints)this.getlist().get(i);
                if (bang.getRecord() == (bang.getRecord() | 1 << sum)) {
                    vs.add(bang);
                }
                else {
                    fs.add(bang);
                }
            }
            if (fs.size() == this.getlist().size()) {
                return;
            }
            this.victory = new Group(vs, sum + 1);
            this.fail = new Group(fs, sum + 1);
        }
        //战斗失败胜利处理
        public void result(BangPoints bang, int sum) {
            Group group = this;
            for (int i = 0; i < sum; ++i) {
                if (bang.getRecord() == (bang.getRecord() | 1 << i)) {
                    if (group.victory == null) {
                        group.victory = new Group(null, i + 1);
                    }
                    group = group.victory;
                }
                else {
                    if (group.fail == null) {
                        group.fail = new Group(null, i + 1);
                    }
                    group = group.fail;
                }
            }
            group.addlist(bang);
        }
    }
}
