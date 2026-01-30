package org.come.thread;

import come.tool.map.XLPath;
import java.util.Map;
import com.tool.ModerateTask.TaskProgress;
import com.tool.ModerateTask.Task;
import org.come.model.ActiveBase;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.util.HashMap;
import java.util.ArrayList;
import com.tool.ModerateTask.TaskRoleData;
import com.tool.time.Limit;
import org.come.bean.RoleShow;
import org.come.model.Door;
import com.tool.imagemonitor.ScriptEnd;
import com.tool.imagemonitor.CreepsMonitor;
import org.come.until.NpcMenuUntil;
import org.come.action.MapAction;
import org.come.action.NpcMenuAction;
import com.tool.ModerateTask.TaskMixDeal;
import org.come.Frame.NPCJfram;
import org.come.until.FormsManagement;
import org.come.npc.TP;
import org.come.until.UserMessUntil;
import com.tool.imagemonitor.TaskMonitor;
import com.tool.imagemonitor.NpcMonitor;
import org.come.mouslisten.Mouselistener;
import com.tool.imagemonitor.ScriptOpen;
import org.come.until.GoodsListFromServerUntil;
import com.tool.image.ManimgAttribute;
import com.tool.image.ImageMixDeal;
import org.come.until.CutButtonImage;
import com.tool.tcp.SpriteFactory;
import com.tool.PlayerKill.PKSys;
import com.tool.ModerateTask.Hero;
import org.come.until.BabyUntil;
import com.tool.role.RoleData;
import com.tool.time.TimeLimit;
import org.come.Frame.AutoTaskJframe;
import org.come.until.MessagrFlagUntil;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.ZhuFrame;
import org.come.Frame.AntiPluginJframe;
import org.come.until.Util;
import com.tool.time.TimeLiTXT;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.FightingMixDeal;
import org.come.Jpanel.ZhuShouTaskJpanel;
import com.tool.imagemonitor.ScriptTask;
import java.util.List;

public class TimeControlRunnable implements Runnable
{
    private int jg;
    private int sum;
    private int tx;
    private long tick;
    private int dk;
    public static int JBTime;
    private static boolean isScript;
    private static final Object object;
    public static boolean isVip;
    public static boolean isVipLoop;
    private static List<Object> script;
    public static ScriptTask scriptTask;
    private static List<ZhuShouTaskJpanel> autoTaskJpanels;
    private static long count;
    
    public TimeControlRunnable() {
        this.jg = 0;
        this.sum = 0;
        this.tx = 0;
        this.dk = 0;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                ++this.tick;
                long x = System.currentTimeMillis();
                int cha = 25;
                if (FightingMixDeal.State == 0) {
                    this.tx = 6;
                    cha = 150;
                }
                else {
                    ++this.tx;
                    if (Fightingimage.getNum() >= 2 && Fightingimage.getNum() <= 3) {
                        FightingMixDeal.PalyProgress(35L);
                    }
                    else if (Fightingimage.getNum() > 3 && Fightingimage.getNum() <= 6) {
                        FightingMixDeal.PalyProgress(35L);
                    }
                    else if (Fightingimage.getNum() > 6 && Fightingimage.getNum() <= 188) {
                        FightingMixDeal.PalyProgress(35L);
                    }
                    else if (Fightingimage.getNum() > 188 && Fightingimage.getNum() <= 1880) {
                        FightingMixDeal.PalyProgress(60L);
                    }
                    else if (Fightingimage.getNum() > 1880 && Fightingimage.getNum() <= 999999999) {
                        FightingMixDeal.PalyProgress(160L);
                    }
                    else {
                        FightingMixDeal.PalyProgress(24L);
                    }
                }
                if (this.tx > 5) {
                    this.tx = 0;
                    this.CommonUse();
                }
                if (this.tick % 60L == 0L) {
                    TimeLiTXT.update();
                }
                x = System.currentTimeMillis() - x;
                if (x < (long)cha) {
                    Thread.sleep((long)cha - x);
                }
                Util.addtime(cha);
                continue;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void CommonUse() {
        ++this.jg;
        if (TimeControlRunnable.JBTime > 0) {
            TimeControlRunnable.JBTime -= 150;
            if (TimeControlRunnable.JBTime <= 0) {
                AntiPluginJframe.getAntiPluginJframe().getPluginJpanel().overtime();
            }
        }
        if (ZhuFrame.getZhuJpanel().getTeamState() == 1) {
            ZhuJpanel.getGroupBtn().imgchange((ZhuJpanel.getGroupBtn().getImgzhen() + 1) % 2);
        }
        if (MessagrFlagUntil.NotReads.size() > 0) {
            ZhuJpanel.getFriendsBtn().imgchange((ZhuJpanel.getFriendsBtn().getImgzhen() + 1) % 2);
        }
        if (TimeControlRunnable.isScript && this.jg % 4 == 0) {
            this.scriptHandle();
        }
        else if (TimeControlRunnable.isVipLoop && FightingMixDeal.State == 0) {
            if (this.sum % 600 == 0) {
                TimeControlRunnable.count = 0L;
                addAllTask(AutoTaskJframe.getAutoTaskJframe().getAutoTaskJpanel().getAllActive());
            }
            ++this.sum;
        }
        else if (!TimeControlRunnable.isVipLoop && this.sum > 0) {
            this.sum = 0;
        }
        if (this.jg % 30 == 0) {
            TimeLimit.Timeout();
            TimeLiTXT.Timeout();
        }
        else if (this.jg > 500) {
            this.jg = 0;
            if ((int)RoleData.getRoleData().getLoginResult().getHavebaby() > 0) {
                BabyUntil.calculateBaby();
            }
            Hero.getHero().OverTime();
            PKSys.getPkSys().isexpiation();
            SpriteFactory.ResetAndRemove();
            CutButtonImage.removeNPCHeadImg();
            for (int i = ImageMixDeal.npcimglist.size() - 1; i >= 0; --i) {
                ((ManimgAttribute)ImageMixDeal.npcimglist.get(i)).npcmsg();
            }
            GoodsListFromServerUntil.isSendPackRecord();
        }
    }
    
    public void scriptHandle() {
        synchronized (TimeControlRunnable.object) {
            int size = (TimeControlRunnable.script != null) ? TimeControlRunnable.script.size() : 0;
            if (size == 0) {
                TimeControlRunnable.isScript = false;
                return;
            }
            --size;
            Object object = TimeControlRunnable.script.get(size);
            if (object == null) {
                TimeControlRunnable.script.remove(size);
                return;
            }
            if (object instanceof ScriptOpen) {
                ScriptOpen open = (ScriptOpen)object;
                if (open.getType() == 0) {
                    Mouselistener.XL(open.getX() / 20, open.getY() / 20);
                }
                else if (open.getType() == 1) {
                    ManimgAttribute attribute = ImageMixDeal.getNpc(open.getNpc() + "");
                    if (attribute != null) {
                        NpcMonitor.npc(attribute);
                    }
                    else {
                        System.err.println("没有找到NPC");
                        return;
                    }
                }
                else if (open.getType() == 2) {
                    ManimgAttribute attribute = ImageMixDeal.getTask(open.getNpc());
                    if (attribute != null) {
                        TaskMonitor.TaskCreeps(attribute.getTaskdata());
                    }
                }
                else if (open.getType() == 3) {
                    Door door = UserMessUntil.getDoor(open.getDoor() + "");
                    if (door != null) {
                        TP.tp(door, 3);
                    }
                    FormsManagement.HideForm(27);
                }
                else if (open.getType() == 101) {
                    TP.tp(open.getDoor(), open.getX(), open.getY());
                }
                else if (open.getType() == 102) {
                    TaskMixDeal.isoption(open.getNpc(), null, NPCJfram.getNpcJfram().getNpcjpanel().getNpcInfoBean().getNpctable().getNpcway());
                    FormsManagement.HideForm(27);
                }
                else if (open.getType() == 103) {
                    ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.getMenu48())).menuControl("我是来击杀你的");
                    FormsManagement.HideForm(27);
                }
                else if (open.getType() == 104) {
                    ManimgAttribute attribute = ImageMixDeal.getMonster(open.getNpc());
                    if (attribute != null) {
                        CreepsMonitor.Creeps(attribute);
                    }
                }
                else if (open.getType() == 105) {
                    ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.getMenu48())).menuControl("攻击");
                    FormsManagement.HideForm(27);
                }
                else if (open.getType() == 106) {
                    addTask(open.getZhuShouTaskJpanel());
                }
                TimeControlRunnable.script.remove(size);
            }
            else if (object instanceof ScriptEnd) {
                ScriptEnd end = (ScriptEnd)object;
                if (end.getType() == 0) {
                    RoleShow show = ImageMixDeal.userimg.getRoleShow();
                    if (show.getMapid().intValue() == end.getMap() && Math.abs(show.getX() - end.getX()) < 100 && Math.abs(show.getY() - end.getY()) < 100) {
                        TimeControlRunnable.script.remove(size);
                    }
                }
            }
        }
    }
    
    private static int getYeGuaiSum() {
        return TimeControlRunnable.autoTaskJpanels.size();
    }

    public static void sortActiveBases(int id) {
        if (TimeControlRunnable.autoTaskJpanels != null) {
            int index = -1;
            int i = 0;
            while (i < TimeControlRunnable.autoTaskJpanels.size()) {
                if (((ZhuShouTaskJpanel)TimeControlRunnable.autoTaskJpanels.get(i)).getZhuShouBean().getId() == id) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有找到" + ((ZhuShouTaskJpanel)TimeControlRunnable.autoTaskJpanels.get(i)).getZhuShouBean().getaName());
                    index = i;
                    ++TimeControlRunnable.count;
                    break;
                }
                else {
                    ++i;
                }
            }
            if (index != -1) {
                ZhuShouTaskJpanel taskJpanel = (ZhuShouTaskJpanel)TimeControlRunnable.autoTaskJpanels.remove(index);
                TimeControlRunnable.autoTaskJpanels.add(taskJpanel);
            }
        }
    }
    
    public static void addAllTask(List<ZhuShouTaskJpanel> autoTaskJpanels) {
        if (autoTaskJpanels != null) {
            TimeControlRunnable.autoTaskJpanels = autoTaskJpanels;
            TimeControlRunnable.count = 0L;
        }
        if (TimeControlRunnable.autoTaskJpanels == null) {
            return;
        }
        if (TimeControlRunnable.count >= (long)getYeGuaiSum()) {
            return;
        }
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getTroop_id() != null && ImageMixDeal.userimg.getRoleShow().getCaptian() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你不是队长,无法进行操作#76");
            return;
        }
        int vip = 0;
        Limit limit = TimeLimit.getLimits().getLimit("VIP");
        if (limit == null) {
            limit = TimeLimit.getLimits().getLimit("JVIP");
            if (limit == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("月卡或季卡用户才可以使用超级助手");
                return;
            }
        }
        if (TimeControlRunnable.autoTaskJpanels != null) {
            boolean is = true;
            while (is) {
                is = false;
                int i = 0;
                if (i < TimeControlRunnable.autoTaskJpanels.size()) {
                    is = addTask((ZhuShouTaskJpanel)TimeControlRunnable.autoTaskJpanels.get(i));
                    if (is) {
                        TimeControlRunnable.autoTaskJpanels.remove(i);
                    }
                    else if (!TimeControlRunnable.isScript) {
                        TimeControlRunnable.isScript = true;
                    }
                    else {
                        continue;
                    }
                }
            }
        }
        if (TimeControlRunnable.autoTaskJpanels == null || TimeControlRunnable.autoTaskJpanels.size() == 0) {
            removeScript(true);
        }
    }
    
    public static boolean addTask(ZhuShouTaskJpanel autoTaskJpanel) {
        ActiveBase activeBase = autoTaskJpanel.getZhuShouBean();
        if (activeBase.getValue() == 0) {
            int count = TaskRoleData.SumReceive(activeBase.getSid(), 2);
            if (count >= autoTaskJpanel.getNum()) {
                return true;
            }
            String[] vls = activeBase.getGuide().split("-");
            if (vls.length >= 4) {
                addTask(new ScriptTask(vls, activeBase.getSid(), autoTaskJpanel.getNum() - count), true);
            }
        }
        else if (activeBase.getValue() == 1) {
            Task task = Hero.getHero().getSetTask(activeBase.getSid());
            if (task == null) {
                String[] vls = activeBase.getGuide().split("-");
                List<Object> list = new ArrayList<>();
                ScriptOpen openDoor = new ScriptOpen(101);
                openDoor.setDoor(Integer.parseInt(vls[0]));
                openDoor.setX(Integer.parseInt(vls[1]));
                openDoor.setY(Integer.parseInt(vls[2]));
                list.add(0, openDoor);
                ScriptOpen open = new ScriptOpen(1);
                open.setNpc(Integer.parseInt(vls[3]));
                list.add(0, open);
                ScriptOpen lingQv = new ScriptOpen(102);
                lingQv.setNpc(activeBase.getSid());
                list.add(0, lingQv);
                ScriptOpen zhiXing = new ScriptOpen(106);
                zhiXing.setZhuShouTaskJpanel(autoTaskJpanel);
                list.add(0, zhiXing);
                addScript(list);
            }
            if (task != null) {
                TaskProgress progress = task.getTaskProgres();
                if (progress != null) {
                    String value = activeBase.getSkin();
                    String[] boodIds = value.split("\\|");
                    Map<Integer, String> boosIdMap = new HashMap<>();
                    for (int j = 0; j < boodIds.length; ++j) {
                        String[] vs = boodIds[j].split("=");
                        boosIdMap.put(Integer.valueOf(vs[0]), vs[1]);
                    }
                    String sendmes = Agreement.getAgreement().autoTaskAgreement(activeBase.getId() + "-" + (String)boosIdMap.get(Integer.valueOf(progress.getDId())));
                    SendMessageUntil.toServer(sendmes);
                    TimeControlRunnable.isVipLoop = true;
                }
            }
        }
        else {
            int count = TaskRoleData.SumReceive(activeBase.getSid(), 2);
            if (count >= autoTaskJpanel.getNum()) {
                return true;
            }
            String sendmes2 = Agreement.getAgreement().autoTaskAgreement(activeBase.getId() + "-" + activeBase.getGuide());
            SendMessageUntil.toServer(sendmes2);
            TimeControlRunnable.isVipLoop = true;
        }
        return false;
    }
    public static void addTask(ScriptTask scriptTask) {
        synchronized (object) {
            removeScript(true);// 清理之前的脚本纪录
            TimeControlRunnable.scriptTask = null;// 清空之前的自动任务模式
            Task task = Hero.getHero().getSetTask(scriptTask.getTaskSetID());// 根据任务系列id 获取人物身上的任务
//            if (task != null) {// 如果人物身上有这个任务
//                TimeControlRunnable.scriptTask = scriptTask;// set 自动任务模式
////                upTask(task, task.getTaskState());// 调用自动任务模式处理
//            } else {// 如果人物身上没有这个任务
            // 生成脚本 前往领取任务
            RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            List<Object> list = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(),
                    scriptTask.getX(), scriptTask.getY(), scriptTask.getMap());
            if (list == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
                return;
            }
            ScriptOpen open = new ScriptOpen(1);
            open.setNpc(scriptTask.getNpcID());
            list.add(0, open);

            ScriptOpen openTwo = new ScriptOpen(102);
            openTwo.setNpc(scriptTask.getTaskSetID());
            list.add(0, openTwo);

            TimeControlRunnable.scriptTask = scriptTask;
            TimeControlRunnable.addScript(list);
//            }
        }
    }
    public static void addTask(ScriptTask scriptTask,int num) {
        synchronized (object) {
            removeScript(true);// 清理之前的脚本纪录
            TimeControlRunnable.scriptTask = null;// 清空之前的自动任务模式
            Task task = Hero.getHero().getSetTask(scriptTask.getTaskSetID());// 根据任务系列id 获取人物身上的任务
            // 生成脚本 前往领取任务
            RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            List<Object> list = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(),
                    scriptTask.getX(), scriptTask.getY(), scriptTask.getMap());
            if (list == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
                return;
            }
            ScriptOpen open = new ScriptOpen(1);
            open.setNpc(scriptTask.getNpcID());
            list.add(0, open);


            TimeControlRunnable.scriptTask = scriptTask;
            TimeControlRunnable.addScript(list);

        }
    }

    public static void addTask(ScriptTask scriptTask, boolean is) {
        synchronized (TimeControlRunnable.object) {
            TimeControlRunnable.isVip = is;
            removeScript(false);//清理之前的脚本纪录
            TimeControlRunnable.scriptTask = null;//清空之前的自动任务模式
            Task task = Hero.getHero().getSetTask(scriptTask.getTaskSetID());//根据任务系列id 获取人物身上的任务
            if (task != null) {//如果人物身上有这个任务
                TimeControlRunnable.scriptTask = scriptTask;//set 自动任务模式
                upTask(task, task.getTaskState(), TimeControlRunnable.isVip);//调用自动任务模式处理
            }
            else {//如果人物身上没有这个任务
                //生成脚本 前往领取任务
                RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                List<Object> list;
                if (TimeControlRunnable.isVip) {
                    list = new ArrayList<>();
                    ScriptOpen openDoor = new ScriptOpen(101);
                    openDoor.setDoor(scriptTask.getMap());
                    openDoor.setX(scriptTask.getX());
                    openDoor.setY(scriptTask.getY());
                    list.add(0, openDoor);
                }
                else {
                    list = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(), scriptTask.getX(), scriptTask.getY(), scriptTask.getMap());
                }
                if (list == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
                    return;
                }
                ScriptOpen open = new ScriptOpen(1);
                open.setNpc(scriptTask.getNpcID());
                list.add(0, open);
                ScriptOpen openTwo = new ScriptOpen(102);
                openTwo.setNpc(scriptTask.getTaskSetID());
                list.add(0, openTwo);
                TimeControlRunnable.scriptTask = scriptTask;
                addScript(list);
            }
        }
    }
    
    public static void upTask(Task task, int state, boolean is) {
        synchronized (TimeControlRunnable.object) {
            if (is && TimeControlRunnable.autoTaskJpanels == null) {
                return; //自动任务模式为空 或者 自动任务模式系列id 不同 退出
            }
            if (TimeControlRunnable.scriptTask == null || TimeControlRunnable.scriptTask.getTaskSetID() != task.getTaskData().getTaskSetID()) {
                return; //状态是应超时取消任务  或者 取消任务   清空自动任务模式  退出
            }
            if (state == 9 || state == 8) {
                TimeControlRunnable.scriptTask = null;
                return;
            }
            //如果你是队员  清空自动任务模式  退出
            if (ImageMixDeal.userimg.getTeams() == null) {
                TimeControlRunnable.scriptTask = null;
                return;
            }
            if (state == 4) {//完成任务
                //回归接下一个任务  生成脚本
                List<Object> list = new ArrayList<>();
                ScriptOpen openDoor = new ScriptOpen(101);
                openDoor.setDoor(TimeControlRunnable.scriptTask.getMap());
                openDoor.setX(TimeControlRunnable.scriptTask.getX());
                openDoor.setY(TimeControlRunnable.scriptTask.getY());
                list.add(0, openDoor);
                ScriptEnd endDoor = new ScriptEnd(0, openDoor.getDoor(), openDoor.getX(), openDoor.getY());
                list.add(0, endDoor);
                list.add(null);
                list.add(null);
                if (TimeControlRunnable.scriptTask.upSum() <= 0) {
                    TimeControlRunnable.scriptTask = null;
                    if (is) {
                        addScript(list);
                    }
                    else {
                        addAllTask(null);
                    }
                    return;
                }
                else {
                    ScriptOpen open = new ScriptOpen(1);
                    open.setNpc(TimeControlRunnable.scriptTask.getNpcID());
                    list.add(0, open);
                    ScriptOpen openTwo = new ScriptOpen(102);
                    openTwo.setNpc(TimeControlRunnable.scriptTask.getTaskSetID());
                    list.add(0, openTwo);
                    addScript(list);
                }
            }
            else {
                TaskProgress progress = task.getTaskProgress();
                if (progress != null && progress.getMap() != 0) {
                    RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                    List<Object> list2;
                    if (is) {
                        list2 = new ArrayList<>();
                        ScriptOpen openDoor2 = new ScriptOpen(101);
                        openDoor2.setDoor(progress.getMap());
                        openDoor2.setX(progress.getX());
                        openDoor2.setY(progress.getY());
                        list2.add(0, openDoor2);
                    }
                    else {
                        list2 = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(), progress.getX(), progress.getY(), progress.getMap());
                    }
                    if (list2 == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
                        return;
                    }
                    ScriptOpen open = new ScriptOpen((progress.getType() == 1) ? 2 : 1);
                    open.setNpc(progress.getDId());
                    list2.add(0, open);
                    if (progress.getType() == 1 || progress.getType() == 2) {
                        ScriptOpen openTwo = new ScriptOpen(103);
                        openTwo.setNpc(progress.getDId());
                        list2.add(0, openTwo);
                    }
                    addScript(list2);
                }
            }
        }
    }
    
    public static void addScript(List<Object> list) {
        synchronized (TimeControlRunnable.object) {
            TimeControlRunnable.script = list;
            TimeControlRunnable.isScript = true;
            TimeControlRunnable.count = 0L;
        }
    }

    public static void xunlu(final int mapd, int x, int Y) {
        synchronized (TimeControlRunnable.object) {
            removeScript(true);
            TimeControlRunnable.scriptTask = null;
            final RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            final List<Object> list = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(),
                    x, Y, mapd);
            if (list == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
                return;
            } else {
                final ScriptOpen open = new ScriptOpen(1);
                // open.setNpc(scriptTask.getNpcID());
                // list.add(0, open);
                final ScriptOpen openTwo = new ScriptOpen(102);
                // openTwo.setNpc(scriptTask.getTaskSetID());
                // list.add(0, openTwo);
                // TimeControlRunnable.scriptTask = scriptTask;
                addScript(list);
            }
        }
    }
    public static void removeScript(boolean is) {
        synchronized (TimeControlRunnable.object) {
            TimeControlRunnable.script = null;
            TimeControlRunnable.isScript = false;
            TimeControlRunnable.isVipLoop = false;
            TimeControlRunnable.count = 0L;
            if (is) {
                TimeControlRunnable.autoTaskJpanels = null;
                AutoTaskJframe.getAutoTaskJframe().getAutoTaskJpanel().getZhiXingBtn().setStart(false);
            }
        }
    }
    
    static {
        TimeControlRunnable.JBTime = 0;
        TimeControlRunnable.isScript = false;
        object = new Object();
        TimeControlRunnable.count = 0L;
    }
}
