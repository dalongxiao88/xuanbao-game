package come.tool.Scene.LaborDay;

import come.tool.Good.DropUtil;
import come.tool.Stall.AssetUpdate;
import org.come.action.monitor.MonitorUtil;
import org.come.action.suit.SuitMixdeal;
import org.come.bean.LoginResult;
import org.come.entity.Goodstable;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.CreateTextUtil;
import org.come.until.GsonUtil;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import java.util.Iterator;
import org.come.model.Dorp;
import java.util.Collections;
import java.util.ArrayList;
import org.come.action.suit.SuitComposeAction;
import org.come.server.GameServer;
import java.util.List;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class LaborScene
{
    public static int I;
    public static int MONEY;
    public static int[] XYS;
    public static String[] CBTS;
    public static String CZVALUE;
    public static String CBTVALUE;
    public static Object CZLOCK;
    public static Object DRAWLOCK;
    public static long CZSize;
    public static long CZValue;
    public static LaborScene laborScene;
    private long timeOpen;
    private long timeStop;
    private long timeEnd;
    private ConcurrentHashMap<Long, Long> rankMap;
    private ConcurrentHashMap<BigDecimal, LaborRole> roleMap;
    private transient List<LaborRole> CZRankList;
    private transient List<LaborRole> CJRankList;
    private transient boolean isV;
    
    public static void getCBT() {
        String btjp = "";
        Dorp dorp = GameServer.getDorp("9001");//选10
        String[] dr1 = dorp.getDorpValue().split("\\|");
        int d1 = SuitComposeAction.random.nextInt(dr1.length);
        btjp = btjp + dr1[d1] + "|";
        Dorp dorp2 = GameServer.getDorp("9002");
        String[] dr2 = dorp2.getDorpValue().split("\\|");
        int d2 = SuitComposeAction.random.nextInt(dr2.length);
        btjp = btjp + dr2[d2] + "|";
        Dorp dorp3 = GameServer.getDorp("9003");//选1
        String[] dr3 = dorp3.getDorpValue().split("\\|");
        for (int i = 0; i < 2; ++i) {
            int d3 = SuitComposeAction.random.nextInt(dr3.length);
            btjp = btjp + dr3[d3] + "|";
        }
        Dorp dorp4 = GameServer.getDorp("9004");
        String[] dr4 = dorp4.getDorpValue().split("\\|");
        for (int j = 0; j < 10; ++j) {
            int d4 = SuitComposeAction.random.nextInt(dr4.length);
            btjp = btjp + dr4[d4] + "|";
        }
        String[] sl = btjp.split("\\|");
        List<String> s = new ArrayList<>();
        for (String string : sl) {
            s.add(string);
        }
        Collections.shuffle(s);
        String btjp2 = "";
        for (String string2 : s) {
            btjp2 = btjp2 + string2 + "|";
        }
        btjp2 = btjp2.substring(0, btjp2.length() - 1);
        String[] jp = btjp2.split("\\|");
        StringBuffer buffer = new StringBuffer();
        buffer.append(btjp2);
        LaborScene.CBTS = jp;
        LaborScene.CBTVALUE = buffer.toString();
    }
    
    public static void main(String[] args) {
        long v = 0L;
        for (int i = 0; i < 1000000; ++i) {
            v += (long)awardValue();
        }
        System.out.println(v + ":" + v / 1000000L);
    }
    
    public static int awardValue() {
        int v = SuitComposeAction.random.nextInt(1000);
        if (v <= 4) {
            v = 100000;
        }
        else if (v <= 25) {
            v = 50000;
        }
        else if (v <= 100) {
            v = 30000;
        }
        else if (v <= 250) {
            v = 10000;
        }
        else if (v <= 550) {
            v = 5000;
        }
        else {
            v = 3000;
        }
        return v;
    }
    
    public static int awardValueCBT() {
        String[] st = LaborScene.CBTVALUE.split("\\|");
        int v = SuitComposeAction.random.nextInt(st.length);
        return v;
    }
    
    public static synchronized int awardLocation(int v) {
        ++LaborScene.CZSize;
        LaborScene.CZValue += (long)v;
        int location = -1;
        for (int i = 0; i < LaborScene.XYS.length; ++i) {
            if (LaborScene.XYS[i] == v) {
                if (location == -1) {
                    location = i;
                }
                else if (SuitComposeAction.random.nextInt(11) <= 4) {
                    location = i;
                }
            }
        }
        if (location == -1) {
            return location;
        }
        return location + LaborScene.XYS.length * ((LaborScene.CZSize % 3L == 0L) ? 3 : 2);
    }
    
    public static synchronized int awardLocationCBT(int v) {
        ++LaborScene.CZSize;
        LaborScene.CZValue += (long)v;
        int location = -1;
        for (int i = 0; i < LaborScene.CBTS.length; ++i) {
            if (i == v) {
                if (location == -1) {
                    location = i;
                }
                else if (SuitComposeAction.random.nextInt(11) <= 4) {
                    location = i;
                }
            }
        }
        if (location == -1) {
            return location;
        }
        return location + LaborScene.CBTS.length * ((LaborScene.CZSize % 3L == 0L) ? 3 : 2);
    }
    
    public static void init() {
        String text = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "labor.txt");
        if (text == null || text.equals("")) {
            LaborScene.laborScene = new LaborScene();
            LaborScene.laborScene.timeOpen = 1588262400000L;
            LaborScene.laborScene.timeStop = 1588608000000L;
            LaborScene.laborScene.timeEnd = 1588694400000L;
        }
        else {
            LaborScene.laborScene = (LaborScene)GsonUtil.getGsonUtil().getgson().fromJson(text, LaborScene.class);
        }
        long time = System.currentTimeMillis();
        if (time >= LaborScene.laborScene.timeOpen && time < LaborScene.laborScene.timeStop) {
            LaborScene.I = 1;
        }
        else if (time >= LaborScene.laborScene.timeStop && time < LaborScene.laborScene.timeEnd) {
            LaborScene.I = 2;
        }
        else {
            LaborScene.I = 0;
        }
        LaborScene.laborScene.initLabor();
    }
    
    public static void Save(boolean is) {
        synchronized (LaborScene.laborScene) {
            if (!is && !LaborScene.laborScene.isV) {
                return;
            }
            try {
                byte[] bs = GsonUtil.getGsonUtil().getgson().toJson(LaborScene.laborScene).getBytes();
                CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "labor.txt", bs);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            LaborScene.laborScene.isV = false;
        }
    }
    
    public static void setLaborSceneTime(long time1, long time2, long time3) {
        LaborScene.laborScene.timeOpen = time1;
        LaborScene.laborScene.timeStop = time2;
        LaborScene.laborScene.timeEnd = time3;
    }
    
    public static void upLaborScene(int hour, int minute) {
        long time = System.currentTimeMillis();
        if (LaborScene.I == 1 && hour == 0 && minute == 0) {
            LaborScene.laborScene.resetDay();
        }
        if (LaborScene.I == 0 && time >= LaborScene.laborScene.timeOpen && time < LaborScene.laborScene.timeEnd) {
            LaborScene.I = 1;
            LaborScene.laborScene.initLabor();
        }
        else if (LaborScene.I == 1 && time >= LaborScene.laborScene.timeStop) {
            LaborScene.I = 2;
            LaborScene.laborScene.resetCZ();
        }
        else if (LaborScene.I == 2 && time >= LaborScene.laborScene.timeEnd) {
            LaborScene.I = 0;
            LaborScene.laborScene.initLabor();
        }
        if (hour % 2 == 0 && minute == 45) {
            Save(false);
            System.out.println("活动期间仙玉抽奖次数:" + LaborScene.CZSize + ":获得仙玉数:" + LaborScene.CZValue + "平均值:" + LaborScene.CZValue / LaborScene.CZSize);
        }
    }
    
    public static void addRankValue(int type, int value, LoginResult loginResult) {
        if (LaborScene.I != 1) {
            return;
        }
        synchronized (LaborScene.laborScene) {
            LaborRole laborRole = LaborScene.laborScene.addRole(loginResult);
            if (type == 0) {
                laborRole.setCz(laborRole.getCz() + value);
            }
            else if (type == 1) {
                laborRole.setCj(laborRole.getCj() + value);
            }
            LaborScene.laborScene.resetRank(type, laborRole);
            LaborScene.laborScene.isV = true;
        }
    }
    
    public void initLabor() {
        if (LaborScene.I == 0) {
            this.CZRankList = null;
            this.CJRankList = null;
            this.rankMap = null;
            this.roleMap = null;
            return;
        }
        this.CZRankList = new ArrayList<>();
        this.CJRankList = new ArrayList<>();
        if (this.rankMap == null) {
            this.rankMap = new ConcurrentHashMap<>();
        }
        if (this.roleMap == null) {
            this.roleMap = new ConcurrentHashMap<>();
        }
        for (BigDecimal roleId : this.roleMap.keySet()) {
            LaborRole laborRole = (LaborRole)this.roleMap.get(roleId);
            if (laborRole.getCz() != 0) {
                this.resetRank(0, laborRole);
            }
            if (laborRole.getCj() != 0) {
                this.resetRank(1, laborRole);
            }
        }
    }
    
    public int getRank(int type, LaborRole role) {
        int value = (type == 0) ? role.getCz() : role.getCj();
        if (value == 0) {
            return 0;
        }
        synchronized (LaborScene.laborScene) {
            if (type == 0) {
                if (this.CZRankList.size() != 0 && value < ((LaborRole)this.CZRankList.get(this.CZRankList.size() - 1)).getCz()) {
                    return 0;
                }
                for (int i = 0, length = this.CZRankList.size(); i < length; ++i) {
                    LaborRole laborRole = (LaborRole)this.CZRankList.get(i);
                    if (laborRole.getRoleId().compareTo(role.getRoleId()) == 0) {
                        return i + 1;
                    }
                }
            }
            else if (type == 1) {
                if (this.CJRankList.size() != 0 && value < ((LaborRole)this.CJRankList.get(this.CJRankList.size() - 1)).getCj()) {
                    return 0;
                }
                for (int i = 0, length = this.CJRankList.size(); i < length; ++i) {
                    LaborRole laborRole = (LaborRole)this.CJRankList.get(i);
                    if (laborRole.getRoleId().compareTo(role.getRoleId()) == 0) {
                        return i + 1;
                    }
                }
            }
        }
        return 0;
    }
    
    public void resetRank(int type, LaborRole role) {
        if (type == 0) {
            if (this.CZRankList.size() < 100 || role.getCz() >= ((LaborRole)this.CZRankList.get(this.CZRankList.size() - 1)).getCz()) {
                this.CZRankList.remove(role);
                int i = 0;
                int length = this.CZRankList.size();
                while (i < length) {
                    LaborRole laborRole = (LaborRole)this.CZRankList.get(i);
                    if (role.getCz() > laborRole.getCz()) {
                        this.CZRankList.add(i, role);
                        for (int j = 100; j <= length; ++j) {
                            this.CZRankList.remove(100);
                        }
                        return;
                    }
                    else {
                        ++i;
                    }
                }
                if (this.CZRankList.size() < 100) {
                    this.CZRankList.add(role);
                }
            }
        }
        else if (type == 1 && (this.CJRankList.size() < 100 || role.getCj() >= ((LaborRole)this.CJRankList.get(this.CJRankList.size() - 1)).getCj())) {
            this.CJRankList.remove(role);
            int i = 0;
            int length = this.CJRankList.size();
            while (i < length) {
                LaborRole laborRole = (LaborRole)this.CJRankList.get(i);
                if (role.getCj() > laborRole.getCj()) {
                    this.CJRankList.add(i, role);
                    for (int j = 100; j <= length; ++j) {
                        this.CJRankList.remove(100);
                    }
                    return;
                }
                else {
                    ++i;
                }
            }
            if (this.CJRankList.size() < 100) {
                this.CJRankList.add(role);
            }
        }
    }
    
    public void resetCZ() {
        synchronized (LaborScene.DRAWLOCK) {
            for (int i = 0, length = this.CZRankList.size(); i < length; ++i) {
                LaborRole laborRole = (LaborRole)this.CZRankList.get(i);
                this.rankMap.put(Long.valueOf(0x100000000L | laborRole.getRoleId().longValue()), Long.valueOf((long)i + 1L));
            }
        }
    }
    
    public void resetDay() {
        synchronized (LaborScene.laborScene) {
            synchronized (LaborScene.DRAWLOCK) {
                if (this.rankMap != null) {
                    this.rankMap.clear();
                    for (int i = 0, length = this.CJRankList.size(); i < length; ++i) {
                        LaborRole laborRole = (LaborRole)this.CJRankList.get(i);
                        this.rankMap.put(Long.valueOf(laborRole.getRoleId().longValue()), Long.valueOf((long)i + 1L));
                    }
                }
            }
            if (this.CJRankList != null) {
                this.CJRankList.clear();
            }
            if (this.roleMap != null) {
                Iterator<BigDecimal> it = this.roleMap.keySet().iterator();
                while (it.hasNext()) {
                    BigDecimal roleId = (BigDecimal)it.next();
                    LaborRole laborRole2 = (LaborRole)this.roleMap.get(roleId);
                    laborRole2.setCj(0);
                    if (laborRole2.getCz() == 0) {
                        it.remove();
                    }
                }
            }
        }
    }
    
    public LaborRole addRole(LoginResult loginResult) {
        LaborRole laborRole = (LaborRole)LaborScene.laborScene.roleMap.get(loginResult.getRole_id());
        if (laborRole == null) {
            laborRole = new LaborRole(loginResult);
            loginResult.setLaborRole(laborRole);
            LaborScene.laborScene.roleMap.put(loginResult.getRole_id(), laborRole);
        }
        return laborRole;
    }
    
    public static void bindRole(LoginResult loginResult) {
        if (LaborScene.laborScene.roleMap != null) {
            loginResult.setLaborRole((LaborRole)LaborScene.laborScene.roleMap.get(loginResult.getRole_id()));
        }
    }
    
    public static void clearRoleMapByRoleId(BigDecimal roleId) {
        LaborScene.laborScene.roleMap.remove(roleId);
    }
    
    static {
        LaborScene.MONEY = 100;
        LaborScene.XYS = new int[] { 30000, 3000, 5000, 10000, 50000, 3000, 10000, 100000, 3000, 30000, 5000, 50000, 3000, 5000 };
        LaborScene.CBTS = new String[0];
        LaborScene.CZLOCK = new Object();
        LaborScene.DRAWLOCK = new Object();
        LaborScene.CZSize = 0L;
        LaborScene.CZValue = 0L;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < LaborScene.XYS.length; ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            if (LaborScene.XYS[i] == 100000) {
                buffer.append("WYXY10W");
            }
            else if (LaborScene.XYS[i] == 50000) {
                buffer.append("WYXY5W");
            }
            else if (LaborScene.XYS[i] == 30000) {
                buffer.append("WYXY3W");
            }
            else if (LaborScene.XYS[i] == 10000) {
                buffer.append("WYXY1W");
            }
            else if (LaborScene.XYS[i] == 5000) {
                buffer.append("WYXY5K");
            }
            else {
                buffer.append("WYXY3K");
            }
        }
        LaborScene.CZVALUE = buffer.toString();
    }

    /**前端请求处理*/
    public static String request(LoginResult loginResult,String message){
//		if (I==0) { return Agreement.getAgreement().PromptAgreement("活动未开启"); }
        boolean status = message.contains("14|");
        if(status) {
            String[] fs = message.split("\\|");
            Goodstable good = GameServer.getgoods(new BigDecimal(fs[1]));
            //发送系统提示
            String msg = ":25|"+fs[1]+"|1|#AAFFFO";
            //判断刷物资
            SuitMixdeal.CBTZP(loginResult.getRolename(), good.getGoodsname());
            DropUtil.isDH(msg, loginResult);
        }else {
            int value=Integer.parseInt(message);
            if (value>=0&&value<=2) {//显示界面
                LaborRank laborRank=new LaborRank(value);
                LaborRole laborRole=laborScene.roleMap.get(loginResult.getRole_id());
                if (value==2) {
                    laborRank.setRank(laborRole!=null?laborRole.getCZCJNum():0);
                    laborRank.setValue(CZVALUE);
                }else {
                    laborRank.setRoles(value==0?laborScene.CZRankList:laborScene.CJRankList);
                    laborRank.setRole(laborRole!=null?laborRole:new LaborRole(loginResult));
                    laborRank.setRank(laborScene.getRank(value, laborRank.getRole()));
                }
                return Agreement.getAgreement().laborAgreement(GsonUtil.getGsonUtil().getgson().toJson(laborRank));
            }else if (value==10||value==11) {//10领取充值排行 or 11领取抽奖排行
                if (value==10&&I!=2) { return Agreement.getAgreement().PromptAgreement("还未到结算时间"); }//未到结算时间
                long rankValue=0;
                synchronized (DRAWLOCK){
                    long key=loginResult.getRole_id().longValue();
                    if (value==10) { key=(1L<<32)|key; }
                    Long rank=laborScene.rankMap.get(key);
                    if (rank==null) { return Agreement.getAgreement().PromptAgreement("你未上榜无法领取排名奖励"); }
                    if (rank<=0) { return Agreement.getAgreement().PromptAgreement("你已领取过奖励了"); }
                    rankValue=rank.longValue();
                    if (value==10&&(rankValue<=0||rankValue>100)||value==11&&(rankValue<=0||rankValue>10)) { return Agreement.getAgreement().PromptAgreement("你未上榜无法领取排名奖励"); }
                    laborScene.rankMap.put(key, -rank);
                }
                //发送奖励
                String type=null;
                if (value==10) {
                    if (rankValue==1) {type="8001";}
                    else if (rankValue==2) {type="8002";}
                    else if (rankValue==3) {type="8003";}
                    else if (rankValue>=4&&rankValue<=20) {type="8004";}
                    else if (rankValue>=21&&rankValue<=50) {type="8005";}
                    else if (rankValue>=51&&rankValue<=80) {type="8006";}
                    else if (rankValue>=81) {type="8007";}
                }else {
                    type=(8007+rankValue)+"";
                }
                Dorp dorp = GameServer.getDorp(type);
                String msg=null;
                if (type.equals("8008")) { msg="#R{角色名}#Y在限时神宠活动中,出手阔绰,赢的#R{物品名}#Y神宠得青睐,并愿跟随其征战四方,实在令人羡慕不已!#23"; }
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), msg, 999, 1D, null);
            }else if (value==12) {//仙玉抽奖
                LaborRole laborRole=laborScene.roleMap.get(loginResult.getRole_id());
                synchronized (CZLOCK) {
                    if (laborRole==null||laborRole.getCZCJNum()<=0) { return Agreement.getAgreement().PromptAgreement("你没有抽奖次数了"); }
                    laborRole.setCzcj(laborRole.getCzcj()+1);
                }
                //开始发放奖励
                int xy=awardValue();//仙玉奖励
                AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                assetUpdate.updata("X="+xy);
                loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(xy)));
                MonitorUtil.getMoney().addX(xy, 1);
                LaborRank laborRank=new LaborRank(value);
                laborRank.setRank(awardLocation(xy));
                laborRank.setValue("你抽中了"+xy+"仙玉");
                //通知前端转盘
                SendMessage.sendMessageByRoleName(loginResult.getRolename(),
                        Agreement.getAgreement().laborAgreement(GsonUtil.getGsonUtil().getgson().toJson(laborRank))
                                +Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                //发送系统提示
                SuitMixdeal.XYZP(loginResult.getRolename(), xy);
            }else if (value==13||value==113) {
//                if (value == 113) {
//                    value=13;
//                }
                getCBT();
                //开始发放奖励
                int xy=awardValueCBT();
                AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
//                assetUpdate.updata("X="+xy);
                loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(xy)));
                LaborRank laborRank=new LaborRank(value);
                laborRank.setRank(awardLocationCBT(xy));
                laborRank.setValue1(CBTVALUE);
                laborRank.setValue(xy+"");
                //通知前端转盘
                SendMessage.sendMessageByRoleName(loginResult.getRolename(),
                        Agreement.getAgreement().laborAgreement(GsonUtil.getGsonUtil().getgson().toJson(laborRank))
                                +Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));

            }
        }
        return null;
    }

}
