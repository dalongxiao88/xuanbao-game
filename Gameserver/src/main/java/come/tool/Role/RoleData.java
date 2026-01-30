package come.tool.Role;

import java.util.HashMap;
import org.come.handler.SendMessage;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import io.netty.channel.ChannelHandlerContext;
import org.come.model.Npctable;
import org.come.model.TaskProgress;
import org.come.entity.Record;
import org.come.model.Skill;
import come.tool.newTask.TaskUtil;
import org.come.entity.MountSkill;
import java.util.Iterator;
import org.come.action.sys.enterGameAction;
import org.come.model.Configure;
import org.come.server.GameServer;
import come.tool.Mixdeal.CreepsMixdeal;
import org.come.tool.Goodtype;
import org.apache.commons.lang.StringUtils;
import com.github.pagehelper.util.StringUtil;
import come.tool.Calculation.BaseValue;
import java.util.ArrayList;
import org.come.until.AllServiceUtil;
import org.come.entity.Fly;
import org.come.entity.Mount;
import org.come.entity.Baby;
import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.util.Map;
import come.tool.Calculation.BaseQl;
import come.tool.Calculation.BaseSkill;
import org.come.bean.UseCardBean;
import come.tool.newTask.TaskRecord;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.newTask.Task;
import java.util.List;
import org.come.entity.PackRecord;
import org.come.bean.LoginResult;
import java.math.BigDecimal;

public class RoleData
{
    private BigDecimal roleid;
    private String IP;
    private LoginResult loginResult;
    private PrivateData privateData;
    private PackRecord packRecord;
    private RoleSystem roleSystem;
    private List<Task> tasks;
    private ConcurrentHashMap<Integer, TaskRecord> taskRecordMap;
    private BigDecimal[] goodEquip;
    private ConcurrentHashMap<String, UseCardBean> limitMap;
    private List<BaseSkill> skills;
    private BaseQl[] borns;
    private BaseQl[] xcxls;
    private BaseQl[] dcxls;
    private BaseQl[] bpxls;
    private BaseQl[] jmxls;
    private BaseQl[] xpxls;
    private List<Hang> pets;
    private Hang ls;
    private List<Hang> fs;
    private List<Hang> helpFs;
    private List<Long> helps;
    private List<BigDecimal> ps;
    private int fzlvl;
    private BigDecimal mid;
    private int goodNum;
    private int goodMax;
    private int line;
    private CBGData cbgData;
    public static final Map<Long, Long> valueMap;
    
    public RoleData(LoginResult loginResult, List<Goodstable> goods, List<RoleSummoning> pets2, List<Lingbao> lingbaos, List<Baby> babys, List<Mount> mounts2, List<Fly> flys) {
        this.loginResult = loginResult;
        this.roleid = loginResult.getRole_id();
        this.loginResult.setRoleData(this);
        this.packRecord = AllServiceUtil.getPackRecordService().selectByPrimaryKey(this.roleid);
        if (this.packRecord == null) {
            (this.packRecord = new PackRecord()).setRoleId(this.roleid);
            this.packRecord.setRecord("0-0");
            AllServiceUtil.getPackRecordService().insert(this.packRecord);
        }
        this.roleSystem = new RoleSystem();
        this.roleTransfer(loginResult);
        if (loginResult.getResistance() != null && !loginResult.getResistance().equals("")) {
            String[] v = loginResult.getResistance().split("\\|");
            List<BaseQl> kxlist = new ArrayList<>();
            for (int i = 0; i < v.length; ++i) {
                if (v[i].startsWith("主-")) {
                    String zhu = v[i].substring(2);
                    if (zhu.length() > 0) {
                        BigDecimal achi = loginResult.getAchievement();
                        kxlist.add(new BaseQl(zhu, BaseValue.getBangQuality(achi, true)));
                    }
                }
                if (v[i].startsWith("副-")) {
                    String fu = v[i].substring(2);
                    if (fu.length() > 0) {
                        BigDecimal achi = loginResult.getAchievement();
                        kxlist.add(new BaseQl(fu, BaseValue.getBangQuality(achi, false)));
                    }
                }
                if (v[i].startsWith("X") || v[i].startsWith("D")) {
                    String[] vs = v[i].split("#");
                    vs[0] = vs[0].substring(1);
                    if (v[i].startsWith("X")) {
                        this.xcxls = BaseValue.xls(vs);
                    }
                    else {
                        this.dcxls = BaseValue.xls(vs);
                    }
                }
            }
            if (kxlist.size() > 0) {
                this.bpxls = new BaseQl[kxlist.size()];
                for (int i = 0; i < kxlist.size(); ++i) {
                    this.bpxls[i] = (BaseQl)kxlist.get(i);
                }
            }
        }
        if (StringUtil.isNotEmpty(loginResult.getMeridians())) {
            this.jmxls = loginResult.getBaseQl();
        }
        if (StringUtil.isNotEmpty(loginResult.getXingpans())) {
            this.xpxls = loginResult.getBaseQl1();
        }
        String cb = null;
        this.goodEquip = new BigDecimal[14];
        this.pets = new ArrayList<>();
        this.fs = new ArrayList<>();
        this.helpFs = new ArrayList<>();
        if (loginResult.getAttachPack() > 2) {
            loginResult.setAttachPack(2);
        }
        this.goodMax = 24 + (loginResult.getAttachPack() + ((loginResult.getTurnAround() >= 4) ? 4 : loginResult.getTurnAround())) * 24;
        int s = 0;
        long weaponSkin = 0L;
        String equipments = loginResult.getEquipments();
        List<Goodstable> eqGoods = new ArrayList<>();
        if (StringUtils.isNotBlank(equipments)) {
            String[] split = equipments.split("\\$");
            eqGoods = loginResult.getEquipmentsByIndex(Integer.parseInt(split[0]));
            if (eqGoods == null) {
                eqGoods = goods;
            }
            System.out.println("1");
        }
        else {
            eqGoods = goods;
        }
        for (int j = goods.size() - 1; j >= 0; --j) {
            Goodstable good = goods.get(j);
            if (good.getStatus() == 0 && good.getType() != 8888L && good.getType() != 8003L && !loginResult.isSuitEquipment(good.getRgid())) {
                ++this.goodNum;
            }
            if (good.getStatus() == 1) {
                int type = Goodtype.EquipmentType(good.getType());
                if (type != -1) {
                    AchievemUtil.detectionAchievem(loginResult, "第一次一件装备");
                    if (type == 10 && this.goodEquip[type] != null) {
                        this.goodEquip[11] = good.getRgid();
                    }
                    else {
                        this.goodEquip[type] = good.getRgid();
                    }
          //          AchievemUtil.detectionAchievem(loginResult, "第一次一件装备");
            //        if (type == 10 && this.goodEquip[type] != null) {
            //            this.goodEquip[11] = good.getRgid();
             //       }
              //      else {
              //          this.goodEquip[type] = good.getRgid();


                    if (type == 12) {
                        cb = good.getSkin();
                    }
                    //获取物品皮肤
                    if (type == 0) {
                        weaponSkin = (long)CreepsMixdeal.good(Integer.parseInt(good.getSkin()));
                    }
                    // 身上装备是神兵
                    if (Goodtype.GodEquipment_God(good.getType())) {
                        for (BaseQl ql : good.getEquip().getQls()) {
                            if (ql.getKey().equals("等级") && ql.getValue() == 6.0) {
                                ++s;
                                break;
                            }
                        }
                    }
                    else if (Goodtype.GodEquipment_xian(good.getType())) {
                        for (BaseQl ql : good.getEquip().getQls()) {
                            if (ql.getKey().equals("阶数") && ql.getValue() == 6.0) {
                                ++s;
                                break;
                            }
                        }
                    }
                    else if (Goodtype.GodEquipment_Ding(good.getType())) {
                        for (BaseQl ql : good.getEquip().getQls()) {
                            if (ql.getKey().equals("阶数") && ql.getValue() == 6.0) {
                                ++s;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int j = pets2.size() - 1; j >= 0; --j) {
            RoleSummoning pet = (RoleSummoning)pets2.get(j);
            this.pets.add(new Hang(pet.getSid()));
            if (pet.getInnerGoods() != null && !pet.getInnerGoods().equals("")) {
                boolean is = false;
                String[] innerGoodses = pet.getInnerGoods().split("\\|");
                for (int length = innerGoodses.length, n = 0; n < length; ++n) {
                    String string = innerGoodses[n];
                    BigDecimal id = new BigDecimal(string);
                    Goodstable innerGoods = null;
                    int k = goods.size() - 1;
                    while (k >= 0) {
                        if (((Goodstable)goods.get(k)).getType() == 750L && id.compareTo(((Goodstable)goods.get(k)).getRgid()) == 0) {
                            innerGoods = (Goodstable)goods.get(k);
                            if ((int)innerGoods.getStatus() != 1) {
                                AllServiceUtil.getGoodsTableService().updateGoodsIndex(innerGoods, null, null, Integer.valueOf(1));
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            --k;
                        }
                    }
                    if (innerGoods == null) {
                        StringBuffer buffer = new StringBuffer();
                        for (String string2 : innerGoodses) {
                            if (!string2.equals(string)) {
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append(string);
                            }
                        }
                        pet.setInnerGoods(buffer.toString());
                        is = true;
                    }
                }
                if (is) {
                    AllServiceUtil.getRoleSummoningService().updateRoleSummoning(pet);
                }
            }
        }
        this.helps = new ArrayList<>();
        for (int j = lingbaos.size() - 1; j >= 0; --j) {
            Lingbao lingbao = (Lingbao)lingbaos.get(j);
            if (lingbao.getBaotype().equals("法宝")) {
                this.fzlvl += lingbao.getLingbaolvl().intValue();
                this.fzlvl += BaseValue.getQv(lingbao.getBaoquality());
                if (lingbao.getEquipment() == 1) {
                    this.fs.add(new Hang(lingbao, 1));
                }
            }
            else if (lingbao.getEquipment() == 1) {
                this.ls = new Hang(lingbao, 0);
            }
            else {
                this.helpFs.add(new Hang(lingbao, 0));
            }
        }
        for (int j = mounts2.size() - 1; j >= 0; --j) {
            Mount mount = (Mount)mounts2.get(j);
            if (loginResult.getMount_id() != null && mount.getMountid() == loginResult.getMount_id()) {
                this.mid = mount.getMid();
            }
            ConcurrentHashMap<Integer, Configure> sl = GameServer.getAllConfigure();
            Configure configure = (Configure)sl.get(Integer.valueOf(1));
            if (mount.getMountskill() == null) {
                List<MountSkill> mountskill = new ArrayList<>();
                mount.setMountskill(mountskill);
                AllServiceUtil.getMountService().updateMountRedis(mount);
            }
            else if (mount.getMountskill().size() > Integer.parseInt(configure.getZqjnsx())) {
                if ((int)mount.getMountid() > 7) {
                    if (mount.getMountskill().size() > Integer.parseInt(configure.getZqjnsx()) + 1) {
                        AllServiceUtil.getMountskillService().deleteMountskills(mount.getMid());
                        mount.getMountskill().clear();
                        AllServiceUtil.getMountService().updateMountRedis(mount);
                    }
                }
                else {
                    AllServiceUtil.getMountskillService().deleteMountskills(mount.getMid());
                    mount.getMountskill().clear();
                    AllServiceUtil.getMountService().updateMountRedis(mount);
                }
            }
            if (mount.getSid() != null || mount.getOthrersid() != null || mount.getSid3() != null || mount.getSid4() != null || mount.getSid5() != null) {
                for (int l = this.pets.size() - 1; l >= 0; --l) {
                    ((Hang)this.pets.get(l)).initSid(mount);
                }
            }
        }
        for (int j = flys.size() - 1; j >= 0; --j) {
            Fly fly = (Fly)flys.get(j);
            if (loginResult.getFly_id() != null && fly.getFlytid() == loginResult.getFly_id()) {
                this.mid = fly.getMid();
            }
        }
        this.ps = new ArrayList<>();
        if (loginResult.getPals() != null && !loginResult.getPals().equals("")) {
            String[] vs2 = loginResult.getPals().split("\\|");
            for (int m = 0; m < vs2.length; ++m) {
                this.ps.add(new BigDecimal(vs2[m]));
            }
        }
        String eSkin = null;
        UseCardBean limit = (UseCardBean)this.limitMap.get("童卡");
        if (limit == null) {
            limit = (UseCardBean)this.limitMap.get("变身卡");
        }
        if (limit != null) {
            Double value = limit.getQlKey("皮肤");
            if (value != null) {
                eSkin = value.intValue() + "";
            }
        }
        if (eSkin == null && weaponSkin != 0L) {
            ConcurrentHashMap<Integer, Configure> confi = GameServer.getAllConfigure();
            Configure configure = (Configure)confi.get(Integer.valueOf(1));
            String roletyle = "新";
            if (configure.getNeworold() != null) {
                roletyle = configure.getNeworold();
            }
            if (roletyle.equals("新")) {
                long se = loginResult.getSpecies_id().longValue();
                if ((weaponSkin == 1L && se == 20001L) || (weaponSkin == 2L && se == 20001L) || (weaponSkin == 1L && se == 20002L) || (weaponSkin == 3L && se == 20002L) || (weaponSkin == 4L && se == 20003L) || (weaponSkin == 5L && se == 20003L) || (weaponSkin == 9L && se == 20004L) || (weaponSkin == 8L && se == 20004L) || (weaponSkin == 10L && se == 20005L) || (weaponSkin == 7L && se == 20005L) || (weaponSkin == 10L && se == 20006L) || (weaponSkin == 12L && se == 20006L) || (weaponSkin == 1L && se == 20007L) || (weaponSkin == 5L && se == 20007L) || (weaponSkin == 1L && se == 20008L) || (weaponSkin == 10L && se == 20008L) || (weaponSkin == 2L && se == 20009L) || (weaponSkin == 6L && se == 20009L) || (weaponSkin == 8L && se == 20010L) || (weaponSkin == 1L && se == 20010L) || (weaponSkin == 12L && se == 21001L) || (weaponSkin == 7L && se == 21001L) || (weaponSkin == 10L && se == 21002L) || (weaponSkin == 13L && se == 21002L) || (weaponSkin == 10L && se == 21003L) || (weaponSkin == 12L && se == 21003L) || (weaponSkin == 9L && se == 21004L) || (weaponSkin == 10L && se == 21004L) || (weaponSkin == 7L && se == 21005L) || (weaponSkin == 1L && se == 21005L) || (weaponSkin == 14L && se == 21006L) || (weaponSkin == 8L && se == 21006L) || (weaponSkin == 12L && se == 21007L) || (weaponSkin == 4L && se == 21007L) || (weaponSkin == 10L && se == 21008L) || (weaponSkin == 11L && se == 21008L) || (weaponSkin == 10L && se == 21009L) || (weaponSkin == 4L && se == 21009L) || (weaponSkin == 14L && se == 21010L) || (weaponSkin == 9L && se == 21010L) || (weaponSkin == 12L && se == 2200L) || (weaponSkin == 3L && se == 22001L) || (weaponSkin == 14L && se == 22002L) || (weaponSkin == 1L && se == 22002L) || (weaponSkin == 7L && se == 22003L) || (weaponSkin == 14L && se == 22003L) || (weaponSkin == 10L && se == 22004L) || (weaponSkin == 5L && se == 22004L) || (weaponSkin == 7L && se == 22005L) || (weaponSkin == 16L && se == 22005L) || (weaponSkin == 1L && se == 22006L) || (weaponSkin == 12L && se == 22006L) || (weaponSkin == 12L && se == 22007L) || (weaponSkin == 14L && se == 22007L) || (weaponSkin == 11L && se == 22008L) || (weaponSkin == 16L && se == 22008L) || (weaponSkin == 1L && se == 22009L) || (weaponSkin == 13L && se == 22009L) || (weaponSkin == 16L && se == 22010L) || (weaponSkin == 17L && se == 22010L) || (weaponSkin == 1L && se == 23001L) || (weaponSkin == 10L && se == 23001L) || (weaponSkin == 12L && se == 23002L) || (weaponSkin == 5L && se == 23002L) || (weaponSkin == 13L && se == 23003L) || (weaponSkin == 6L && se == 23003L) || (weaponSkin == 9L && se == 23004L) || (weaponSkin == 8L && se == 23004L) || (weaponSkin == 17L && se == 23005L) || (weaponSkin == 11L && se == 23005L) || (weaponSkin == 11L && se == 23006L) || (weaponSkin == 16L && se == 23006L) || (weaponSkin == 1L && se == 24001L) || (weaponSkin == 6L && se == 24001L) || (weaponSkin == 12L && se == 24002L) || (weaponSkin == 10L && se == 24002L) || (weaponSkin == 18L && se == 24003L) || (weaponSkin == 11L && se == 24003L) || (weaponSkin == 9L && se == 24004L) || (weaponSkin == 3L && se == 24004L) || (weaponSkin == 18L && se == 24005L) || (weaponSkin == 12L && se == 24005L) || (weaponSkin == 1L && se == 24006L) || (weaponSkin == 17L && se == 24006L)) {
                    weaponSkin += 18L;
                }
                long key = makeKey(weaponSkin, se);
                if (RoleData.valueMap.containsKey(Long.valueOf(key))) {
                    eSkin = RoleData.valueMap.get(Long.valueOf(key)) + "";
                }
                else {
                    eSkin = (weaponSkin << 32 | loginResult.getSpecies_id().longValue()) + "";
                    if (s >= 0) {
                        String roleId = LoginResult.getRoleId(loginResult.getLocalname(), loginResult);
                        if (StringUtils.isNotBlank(roleId)) {
                            eSkin = "";
                            eSkin = eSkin + "GW_" + roleId.replace("|", "^");
                        }
                    }
                }
            }
        }
        else if (weaponSkin != 0L) {
            eSkin = (weaponSkin << 32 | loginResult.getSpecies_id().longValue()) + "";
        }
        loginResult.setSkin(enterGameAction.getskin(eSkin, this.packRecord.getPutTX(), loginResult.getTitle(), cb));
        loginResult.setRoleShow(new RoleShow(loginResult));
        UseCardBean sxf = (UseCardBean)this.limitMap.get("sxf");
        if (sxf != null) {
            loginResult.setDivineRune(Boolean.valueOf(true));
            loginResult.getRoleShow().setDivineRune(Boolean.valueOf(true));
        }
    }
    
    public static long makeKey(long w, long se) {
        return w << 32 | (se & 0xFFFFFFFFL);
    }
    
    public void roleTransfer(LoginResult loginResult) {
        synchronized (this) {
            (this.privateData = new PrivateData()).setDBExp(loginResult.getDBExp());
            loginResult.setDBExp(null);
            this.privateData.setTaskComplete(loginResult.getTaskComplete());
            loginResult.setTaskComplete(null);
            this.privateData.setTaskData(loginResult.getTaskData());
            loginResult.setTaskData(null);
            this.privateData.setSkills(loginResult.getSkills());
            loginResult.setSkills(null);
            this.privateData.setBorn(loginResult.getBorn());
            loginResult.setBorn(null);
            this.privateData.setBorn1(loginResult.getBorn1());
            this.privateData.setTimingGood(loginResult.getTimingGood());
            loginResult.setTimingGood(null);
            this.skills = BaseValue.reSkill(this.privateData, loginResult);
            this.borns = BaseValue.reborn(this.privateData.getBorn());
            this.tasks = TaskUtil.initTask(this.privateData.getTaskData());
            this.taskRecordMap = TaskUtil.initTaskRecord(this.privateData.getTaskComplete());
            this.limitMap = this.privateData.initLimit(loginResult.getPaysum().longValue());
            UseCardBean vip = (UseCardBean)this.limitMap.get("VIP");
            if (vip != null) {
                Skill skill1 = GameServer.getSkill("8056");
                if (skill1 != null && this.skills == null) {
                    this.skills = new ArrayList<>();
                }
                this.skills.add(new BaseSkill(8056, 9999, skill1, null));
            }
        }
    }
    
    public void roleRecover(LoginResult loginResult) {
        synchronized (this) {
            if (this.privateData == null) {
                AllServiceUtil.getRecordService().insert(new Record(0, "恢复loginResult数据时对象为空"));
                return;
            }
            loginResult.setDBExp(this.privateData.getDBExp());
            loginResult.setTaskComplete(this.privateData.getTaskComplete());
            loginResult.setSkills(this.privateData.getSkills());
            loginResult.setBorn(this.privateData.getBorn());
            loginResult.setBorn1(this.privateData.getBorn1());
            StringBuffer buffer = new StringBuffer();
            for (int i = this.tasks.size() - 1; i >= 0; --i) {
                Task task = (Task)this.tasks.get(i);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(task.getTaskId());
                buffer.append("=");
                buffer.append(task.getTaskState());
                if (task.getTime() != 0L) {
                    buffer.append("=T");
                    buffer.append(task.getTime() / 1000L);
                }
                TaskUtil.Progress(task, buffer);
            }
            loginResult.setTaskData(buffer.toString());
            buffer.setLength(0);
            for (UseCardBean limit : this.limitMap.values()) {
                if (limit.getType().equals("SVIP")) {
                    continue;
                }
                else {
                    if (buffer.length() != 0) {
                        buffer.append("^");
                    }
                    buffer.append(limit.getName());
                    buffer.append("#");
                    buffer.append(limit.getType());
                    buffer.append("#");
                    buffer.append(limit.getSkin());
                    buffer.append("#");
                    buffer.append(limit.getTime() / 60000L);
                    if (limit.getValue() != null && !limit.getValue().equals("")) {
                        buffer.append("#");
                        buffer.append(limit.getValue());
                    }
                    else {
                        continue;
                    }
                }
            }
            loginResult.setTimingGood(buffer.toString());
            loginResult.setTaskComplete(TaskUtil.toTaskRecord(this.taskRecordMap));
        }
    }
    
    public String removeTasks(int type, String... ids) {
        StringBuffer buffer = null;
        for (int i = 0; i < ids.length; ++i) {
            int taskID = Integer.parseInt(ids[i]);
            int j = this.tasks.size() - 1;
            while (j >= 0) {
                Task task = (Task)this.tasks.get(j);
                if (taskID == task.getTaskId()) {
                    if (buffer == null) {
                        buffer = new StringBuffer();
                    }
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(taskID);
                    buffer.append("=");
                    buffer.append(type);
                    this.tasks.remove(j);
                    break;
                }
                else {
                    --j;
                }
            }
        }
        return (buffer == null) ? null : buffer.toString();
    }
    
    public void removeTask(int taskID) {
        int j = this.tasks.size() - 1;
        while (j >= 0) {
            Task task = (Task)this.tasks.get(j);
            if (taskID == task.getTaskId()) {
                this.tasks.remove(j);
                break;
            }
            else {
                --j;
            }
        }
    }
    
    public void addTask(Task task, boolean is) {
        this.removeTask(task.getTaskId());
        this.tasks.add(is ? task : task.FZ());
    }
    
    public Task getTask(int taskId) {
        for (int i = this.tasks.size() - 1; i >= 0; --i) {
            Task task = (Task)this.tasks.get(i);
            if (taskId == task.getTaskId()) {
                return task;
            }
        }
        return null;
    }
    
    public TaskRecord getTaskRecord(int taskSetId) {
        return (TaskRecord)this.taskRecordMap.get(Integer.valueOf(taskSetId));
    }
    
    public int getTaskLQ(int taskSetId) {
        TaskRecord record = (TaskRecord)this.taskRecordMap.get(Integer.valueOf(taskSetId));
        return (record != null) ? record.getrSum() : 0;
    }
    
    public int getTaskWC(int taskSetId) {
        TaskRecord record = (TaskRecord)this.taskRecordMap.get(Integer.valueOf(taskSetId));
        return (record != null) ? record.getcSum() : 0;
    }
    
    public void addTaskRecord(TaskRecord taskRecord) {
        this.taskRecordMap.put(Integer.valueOf(taskRecord.getTaskId()), taskRecord);
    }
    
    public int addTaskRecordWC(int taskSetId) {
        TaskRecord record = (TaskRecord)this.taskRecordMap.get(Integer.valueOf(taskSetId));
        if (record == null) {
            record = new TaskRecord(taskSetId);
            this.taskRecordMap.put(Integer.valueOf(taskSetId), record);
        }
        record.addCSum(1);
        return record.getcSum();
    }
    
    public boolean isRobotId(int robotId) {
        for (int i = this.tasks.size() - 1; i >= 0; --i) {
            List<TaskProgress> progress = ((Task)this.tasks.get(i)).getProgress();
            for (int j = progress.size() - 1; j >= 0; --j) {
                TaskProgress ps = (TaskProgress)progress.get(j);
                if (ps.getSum() < ps.getMax()) {
                    if (ps.getType() == 0 || ps.getType() == 1) {
                        if (ps.getDId() == robotId) {
                            return false;
                        }
                    }
                    else if (ps.getType() == 2) {
                        Npctable npctable = GameServer.getNpc(ps.getDId() + "");
                        if (npctable != null && npctable.getRobotID() == robotId) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public void addLimit(UseCardBean limit) {
        this.limitMap.put(limit.getType(), limit);
    }
    
    public UseCardBean removeLimit(String type) {
        return (UseCardBean)this.limitMap.remove(type);
    }
    
    public UseCardBean getLimit(String type) {
        return (UseCardBean)this.limitMap.get(type);
    }
    
    public PackRecord getPackRecord() {
        if (this.packRecord.getRecord() == null) {
            this.packRecord.setRecord("0-0");
        }
        return this.packRecord;
    }
    
    public void setPackRecord(PackRecord packRecord) {
        this.packRecord = packRecord;
    }
    
    public LoginResult getLoginResult() {
        return this.loginResult;
    }
    
    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }
    
    public RoleSystem getRoleSystem() {
        return this.roleSystem;
    }
    
    public void setRoleSystem(RoleSystem roleSystem) {
        this.roleSystem = roleSystem;
    }
    
    public PrivateData getPrivateData() {
        return this.privateData;
    }
    
    public void setPrivateData(PrivateData privateData) {
        this.privateData = privateData;
    }
    
    public void upPrivateData(PrivateData privateData) {
        if (!this.privateData.getBorn().equals(privateData.getBorn())) {
            this.privateData.setBorn(privateData.getBorn());
            this.borns = BaseValue.reborn(privateData.getBorn());
        }
        if (!this.privateData.getSkills().equals(privateData.getSkills())) {
            this.privateData.setSkills(privateData.getSkills());
            this.skills = BaseValue.reSkill(this.privateData, this.loginResult);
        }
    }
    
    public BaseQl[] getXls(int type) {
        if (type == 1) {
            return this.xcxls;
        }
        if (type == 40) {
            return this.bpxls;
        }
        if (type == 41) {
            return this.jmxls;
        }
        if (type == 42) {
            return this.xpxls;
        }
        return this.dcxls;
    }
    
    public void setXls(int type, BaseQl[] xls) {
        if (type == 1) {
            this.xcxls = xls;
        }
        else if (type == 40) {
            this.bpxls = xls;
        }
        else if (type == 41) {
            this.jmxls = xls;
        }
        else if (type == 42) {
            this.xpxls = xls;
        }
        else {
            this.dcxls = xls;
        }
    }
    
    public List<BaseSkill> getSkills() {
        return this.skills;
    }
    
    public void setSkills(List<BaseSkill> skills) {
        this.skills = skills;
    }
    
    public BaseQl[] getBorns() {
        return this.borns;
    }
    
    public void setBorns(BaseQl[] borns) {
        this.borns = borns;
    }
    
    public Hang getLs() {
        return this.ls;
    }
    
    public void setLs(Hang ls) {
        this.ls = ls;
    }
    
    public List<Hang> getFs() {
        return this.fs;
    }
    
    public void setFs(List<Hang> fs) {
        this.fs = fs;
    }
    
    public List<Hang> getPets() {
        return this.pets;
    }
    
    public BigDecimal[] getGoodEquip() {
        return this.goodEquip;
    }
    
    public void setGoodEquip(BigDecimal[] goodEquip) {
        this.goodEquip = goodEquip;
    }
    
    public String getIP() {
        return this.IP;
    }
    
    public void setIP(String iP) {
        this.IP = iP;
    }
    
    public void CEquip(BigDecimal rgid, int type, boolean is) {
        if (is) {
            if (type == 10 && this.goodEquip[type] != null) {
                type = 11;
            }
            this.goodEquip[type] = rgid;
        }
        else {
            if (type == 10 && this.goodEquip[type] != null && this.goodEquip[type].compareTo(rgid) != 0) {
                type = 11;
            }
            if (this.goodEquip[type] != null && this.goodEquip[type].compareTo(rgid) == 0) {
                this.goodEquip[type] = null;
            }
        }
    }
    
    public void CPet(BigDecimal sid, boolean is) {
        int i = this.pets.size() - 1;
        while (i >= 0) {
            Hang hang = (Hang)this.pets.get(i);
            if (hang.getId().compareTo(sid) == 0) {
                this.pets.remove(i);
                break;
            }
            else {
                --i;
            }
        }
        if (is) {
            this.pets.add(new Hang(sid));
        }
    }
    
    public void MPet(Mount mount, boolean is) {
        for (int i = this.pets.size() - 1; i >= 0; --i) {
            Hang hang = (Hang)this.pets.get(i);
            if (hang.getMid() != null) {
                if (hang.getMid().compareTo(mount.getMid()) == 0) {
                    if (is) {
                        if (mount.getSid() != null && hang.getId().compareTo(mount.getSid()) == 0) {
                            continue;
                        }
                        else if (mount.getOthrersid() != null && hang.getId().compareTo(mount.getOthrersid()) == 0) {
                            continue;
                        }
                        else if (mount.getSid3() != null && hang.getId().compareTo(mount.getSid3()) == 0) {
                            continue;
                        }
                        else if (mount.getSid4() != null && hang.getId().compareTo(mount.getSid4()) == 0) {
                            continue;
                        }
                        else if (mount.getSid5() != null && hang.getId().compareTo(mount.getSid5()) == 0) {
                            continue;
                        }
                    }
                    hang.setMid(null);
                }
            }
            else if (is) {
                if (mount.getSid() != null && hang.getId().compareTo(mount.getSid()) == 0) {
                    hang.setMid(mount.getMid());
                }
                else if (mount.getOthrersid() != null && hang.getId().compareTo(mount.getOthrersid()) == 0) {
                    hang.setMid(mount.getMid());
                }
                else if (mount.getSid3() != null && hang.getId().compareTo(mount.getSid3()) == 0) {
                    hang.setMid(mount.getMid());
                }
                else if (mount.getSid4() != null && hang.getId().compareTo(mount.getSid4()) == 0) {
                    hang.setMid(mount.getMid());
                }
                else if (mount.getSid5() != null && hang.getId().compareTo(mount.getSid5()) == 0) {
                    hang.setMid(mount.getMid());
                }
            }
        }
    }
    
    public void CLing(BigDecimal baoId, String lx, boolean is) {
        if (lx.equals("法宝")) {
            int i = this.fs.size() - 1;
            while (i >= 0) {
                Hang hang = (Hang)this.fs.get(i);
                if (hang.getId().compareTo(baoId) == 0) {
                    this.fs.remove(i);
                    break;
                }
                else {
                    --i;
                }
            }
            if (is) {
                this.fs.add(new Hang(baoId));
            }
        }
        else if (is) {
            this.ls = new Hang(baoId);
        }
        else if (this.ls != null && this.ls.getId().compareTo(baoId) == 0) {
            this.ls = null;
        }
    }
    
    public BigDecimal getMid() {
        return this.mid;
    }
    
    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
    
    public boolean isGoodFull() {
        return this.goodNum >= this.goodMax;
    }
    
    public void upGoodNum(int num) {
        this.goodNum += num;
    }
    
    public int getGoodNum() {
        return this.goodNum;
    }
    
    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }
    
    public int getGoodMax() {
        return this.goodMax;
    }
    
    public void setGoodMax(int goodMax) {
        this.goodMax = goodMax;
    }
    
    public ConcurrentHashMap<Integer, TaskRecord> getTaskRecordMap() {
        return this.taskRecordMap;
    }
    
    public int PSize() {
        return this.ps.size();
    }
    
    public List<BigDecimal> getPs() {
        return this.ps;
    }
    
    public boolean upLine() {
        synchronized (this) {
            --this.line;
            return this.line <= -3;
        }
    }
    
    public void setLine(int value) {
        synchronized (this) {
            this.line = value;
        }
    }
    
    public CBGData getCbgData() {
        return this.cbgData;
    }
    
    public void setCbgData(CBGData cbgData) {
        this.cbgData = cbgData;
    }
    
    public void upPackNum(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        String msg = good.getGoodsname() + "最多只能使用1次";
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (login.getAttachPack() <= 1) {
            login.setAttachPack(login.getAttachPack() + 1);
            this.goodMax = 24 + (login.getAttachPack() + ((login.getTurnAround() >= 4) ? 3 : login.getTurnAround())) * 24;
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(9));
            AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
            msg = good.getGoodsname() + "#G使用成功";
        }
        assetUpdate.setMsg(msg);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public List<Hang> getHelpFs() {
        return this.helpFs;
    }
    
    public void setHelpFs(List<Hang> helpFs) {
        this.helpFs = helpFs;
    }
    
    public Task getTaskBySetId(int taskSetId) {
        for (int i = this.tasks.size() - 1; i >= 0; --i) {
            Task task = (Task)this.tasks.get(i);
            if (taskSetId == task.getTaskSetId()) {
                return task;
            }
        }
        return null;
    }
    
    static {
        (valueMap = new HashMap<>()).put(Long.valueOf(makeKey(1L, 20011L)), Long.valueOf(4294987307L));
        RoleData.valueMap.put(Long.valueOf(makeKey(7L, 20011L)), Long.valueOf(30064791083L));
        RoleData.valueMap.put(Long.valueOf(makeKey(10L, 20012L)), Long.valueOf(42949692972L));
        RoleData.valueMap.put(Long.valueOf(makeKey(8L, 20012L)), Long.valueOf(34359758380L));
        RoleData.valueMap.put(Long.valueOf(makeKey(12L, 21011L)), Long.valueOf(51539628563L));
        RoleData.valueMap.put(Long.valueOf(makeKey(10L, 21011L)), Long.valueOf(42949693971L));
        RoleData.valueMap.put(Long.valueOf(makeKey(9L, 21012L)), Long.valueOf(38654726676L));
        RoleData.valueMap.put(Long.valueOf(makeKey(10L, 21012L)), Long.valueOf(42949693972L));
        RoleData.valueMap.put(Long.valueOf(makeKey(7L, 22011L)), Long.valueOf(30064793083L));
        RoleData.valueMap.put(Long.valueOf(makeKey(12L, 22011L)), Long.valueOf(51539629563L));
        RoleData.valueMap.put(Long.valueOf(makeKey(11L, 22012L)), Long.valueOf(47244662268L));
        RoleData.valueMap.put(Long.valueOf(makeKey(16L, 22012L)), Long.valueOf(68719498748L));
        RoleData.valueMap.put(Long.valueOf(makeKey(1L, 23007L)), Long.valueOf(4294990303L));
        RoleData.valueMap.put(Long.valueOf(makeKey(10L, 23007L)), Long.valueOf(42949695967L));
        RoleData.valueMap.put(Long.valueOf(makeKey(1L, 23008L)), Long.valueOf(4294990304L));
        RoleData.valueMap.put(Long.valueOf(makeKey(16L, 23008L)), Long.valueOf(68719499744L));
        RoleData.valueMap.put(Long.valueOf(makeKey(1L, 24007L)), Long.valueOf(4294991303L));
        RoleData.valueMap.put(Long.valueOf(makeKey(10L, 24007L)), Long.valueOf(42949696967L));
        RoleData.valueMap.put(Long.valueOf(makeKey(16L, 24008L)), Long.valueOf(68719500744L));
        RoleData.valueMap.put(Long.valueOf(makeKey(17L, 24008L)), Long.valueOf(73014468040L));
    }
}
