package come.tool.Fighting;

import com.tool.tcp.NewPart;
import org.come.until.Article;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import java.awt.Graphics;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;

public class FightingManData
{
    static String[] fbs;
    static List<String> drawDown;
    static List<String> lingxistate;
    static boolean lingxidraw;
    private int id;
    private int Camp;
    private int man;
    private int type;
    private String State_1;
    private String manname;
    private int hp_Total;
    private int hp_Current;
    private int mp_Total;
    private int mp_Current;
    private boolean B;
    private int yqz;
    private int nqz;
    private int xy;
    private float alpha;
    private int zs;
    private String msg;
    private String model;
    private Integer W;
    private List<String> States;
    private List<String> aaa;
    private BigDecimal speciesid;
    
    public FightingManData() {
        this.alpha = 1.0f;
        this.model = "img/角色/鬼族/祭剑魂";
        this.States = new ArrayList<>();
    }
    
    public BigDecimal getSpeciesid() {
        return this.speciesid;
    }
    
    public void setSpeciesid(BigDecimal speciesid) {
        this.speciesid = speciesid;
    }
    
    public void initState() {
        if (this.States == null) {
            return;
        }
        for (int i = this.States.size() - 1; i >= 0; --i) {
            String type = (String)this.States.get(i);
            if (type.startsWith("tj") || type.startsWith("mj") || type.startsWith("xl") || type.startsWith("rj")) {
                if (this.aaa == null) {
                    this.aaa = new ArrayList<>();
                }
                this.aaa.add(type);
                this.States.remove(i);
            }
            else if (type.startsWith("含情脉脉") || type.startsWith("乾坤借速") || type.startsWith("魔神附身") || type.startsWith("高级回灵")) {
                this.States.remove(i);
            }
        }
    }
    
    public boolean isstate(String type) {
        if (type.equals("金") || type.equals("木") || type.equals("水") || type.equals("土") || type.equals("火") || type.equals("1889") || type.equals("消除五行") || type.equals("乾坤遮天") || type.equals("乾坤破阵")) {
            this.deletestate("清除五行");
            return true;
        }
        if (type.equals("遗忘") || type.equals("封印") || type.equals("昏睡") || type.equals("混乱")) {
            if (type.equals("封印")) {
                this.deletestate("中毒");
            }
            this.deletestate("封印");
            this.deletestate("昏睡");
            this.deletestate("混乱");
            this.deletestate("遗忘");
            return true;
        }else if (type.equals("分辉")) {
            deletestate("分辉");
            deletestate("1286");
            deletestate("12861");
            return true;
        } else {
            if (type.equals("9371")) {
                this.deletestate("9371");
                return true;
            }
            if (type.equals("骨盾") || type.equals("减人仙")||type.equals("流连忘返")  || type.equals("减魔鬼") || type.equals("庇护") || type.equals("中毒") || type.equals("力量") || type.equals("抗性") || type.equals("加狂暴") || type.equals("加速") || type.equals("归墟") || type.equals("毒针轻刺") || type.equals("回魂咒") || type.equals("分身") || type.equals("化羽") || type.equals("阴阳逆转") || type.equals("smmh") || type.equals("smmhd") || type.equals("减速") || type.equals("天蓬转世") || type.equals("乾坤遮天") || type.equals("赤芒") || type.equals("青峰") || type.equals("金石")) {
                return true;
            }
            if (type.equals("风水") || type.equals("雷火") || type.equals("鬼力") || type.equals("玉净散")) {
                this.deletestate("风水");
                this.deletestate("雷火");
                this.deletestate("鬼力");
                this.deletestate("玉净散");
                return true;
            }
            if (type.equals("6029")) {
                String[] values = { "6029", "遗忘", "封印", "中毒", "昏睡", "混乱", "fbJge", "fbQw" };
                this.RemoveAbnormal(values);
                return true;
            }
            if (type.equals("7009")) {
                this.deletestate("昏睡");
                this.deletestate("混乱");
                this.deletestate("遗忘");
                this.deletestate("7009");
                return true;
            }
            if (type.equals("1237") || type.equals("1237A") || type.equals("1237B")) {
                String[] values = { "1237", "1237A", "1237B" };
                this.RemoveAbnormal(values);
                return true;
            }
            if (type.equals("1238") || type.equals("9111")) {
                this.deletestate("1238");
                return true;
            }
            if (type.equals("7027")) {
                this.deletestate("7027");
                return true;
            }
            if (type.equals("7028")) {
                this.deletestate("7028");
                return true;
            }
            if (type.equals("1221")) {
                this.deletestate("1221");
                return true;
            }
            if (type.equals("9307S")) {
                this.deletestate("9307S");
                return true;
            }
            if (type.startsWith("LBFD")) {
                this.deletestate(type);
                return true;
            }
            if (type.endsWith("天赋")) {
                this.deletestate(type);
                return true;
            }
            if (type.equals("步步相逼") || type.equals("焕然新生") || type.equals("紫燕翻飞") || type.equals("一矢中的") || type.equals("一飞冲天") || type.equals("展翅欲飞") || type.equals("化险为夷") || type.equals("风荷送香") || type.equals("战意") || type.equals("1241") || type.equals("破甲")) {
                this.deletestate(type);
                return true;
            }
            for (int i = 0; i < FightingManData.fbs.length; ++i) {
                if (FightingManData.fbs[i].equals(type)) {
                    this.deletestate(type);
                    return true;
                }
            }
            return false;
        }
    }
    
    public void addstate(String typess) {
        if (typess == null) {
            return;
        }
        String[] types = typess.split("\\|");
        for (int i = 0; i < types.length; ++i) {
            String type = types[i];
            if (type.equals("隐身")) {
                this.alpha = 0.3f;
            }
            else if (!this.States.contains(type) && this.isstate(type)) {
                for (int k = this.States.size() - 1; k >= 0; --k) {
                    if (((String)this.States.get(k)).equals("金石") || ((String)this.States.get(k)).equals("赤芒") || ((String)this.States.get(k)).equals("青峰")) {
                        this.States.remove(k);
                    }
                }
                this.States.add(type);
            }
        }
    }
    
    public void deletestate(String typess) {
        if (typess == null) {
            return;
        }
        String[] types = typess.split("\\|");
        for (int i = 0; i < types.length; ++i) {
            String type = types[i];
            if (type.equals("清除状态")) {
                String[] values = { "减人仙", "减魔鬼", "庇护", "遗忘", "封印", "中毒", "昏睡", "混乱", "金", "木", "水", "火", "土", "1876", "力量", "抗性", "加速", "smmh","smmhd", "减速", "加狂暴", "1889", "消除五行" ,"流连忘返" };
                this.RemoveAbnormal(values);
            }
            else if (type.equals("清除异常状态")) {
                String[] values = { "遗忘", "封印", "中毒", "昏睡", "混乱", "fbJge", "fbQw", "1232", "1869", "bbss" };
                this.RemoveAbnormal(values);
            }
            else if (type.equals("清除五行")) {
                String[] values = { "金", "木", "水", "火", "土", "1876" };
                this.RemoveAbnormal(values);
            }
            else if (type.equals("非控制减益")) {
                String[] values = { "减速", "减人仙", "减魔鬼", "中毒" };
                this.RemoveAbnormal(values);
            }
            else if (type.equals("隐身") || type.equals("无敌")) {
                this.alpha = 1.0f;
            }
            else if (type.equals("清除灵宝发动")) {
                String[] values = { "LBFD", "LBFD2" };
                this.RemoveAbnormal(values);
            }
            else if (type.equals("1238")) {
                String[] values = { "1238" };
                this.RemoveAbnormal(values);
            }
            else {
                this.States.remove(type);
            }
        }
    }
    
    public void RemoveAbnormal(String[] values) {
        for (int j = 0; j < values.length; ++j) {
            this.States.remove(values[j]);
        }
    }
    
    public void draw1(Graphics g, long time, int x, int y) {
        FightingManData.lingxidraw = false;
        for (int i = 0; i < this.States.size(); ++i) {
            String type = (String)this.States.get(i);
            if (!type.contains("抗封印") && !type.contains("妙法莲华") && !type.contains("9362") && !type.contains("免疫物理") && !type.contains("抗普通攻击") && !type.contains("8074") && !type.contains("血蛊护盾") && !type.contains("灵识") && !type.contains("无敌") && !type.contains("1308") && !FightingManData.drawDown.contains(type)) {
                if (FightingManData.lingxistate.contains(type)) {
                    if (!FightingManData.lingxidraw) {
                        continue;
                    }
                    else {
                        FightingManData.lingxidraw = false;
                        type = (String)FightingManData.lingxistate.get(0);
                    }
                }
                int x2 = 0;
                int y2 = 0;
                if (type.equals("分身")) {
                    x2 = -20;
                    y2 = -20;
                }
                Sprite State = SpriteFactory.Prepare(GetTcpPath.getBuffTcp(type));
                if (State != null) {
                    State.updateToTime(time, 0);
                    State.draw(g, x + x2, y + y2);
                }
            }
        }
    }
    
    public void draw2(Graphics g, long time, int x, int y) {
        for (int i = 0; i < this.States.size(); ++i) {
            String type = (String)this.States.get(i);
            if (FightingManData.drawDown.contains(type)) {
                Sprite State = SpriteFactory.Prepare(GetTcpPath.getBuffTcp(type));
                if (State != null) {
                    State.updateToTime(time, 0);
                    State.draw(g, x, y);
                }
            }
        }
        if (this.aaa != null && this.aaa.size() != 0) {
            try {
                Sprite State2 = SpriteFactory.Prepare(GetTcpPath.getBuffTcp((String)this.aaa.get(FightingMixDeal.CurrentRound % this.aaa.size())));
                if (State2 != null) {
                    State2.updateToTime(time, 0);
                    State2.draw(g, x, y);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void chang(int hp, int mp, int yq, int nq,int xy) {
        int myman = FightingMixDeal.myman();
        if (hp != 0) {
            this.hp_Current = Article.xiugai(this.hp_Total, this.hp_Current, hp);
            if (FightingMixDeal.camp == this.Camp && (myman == this.man || myman + 5 == this.man)) {
                Article.fightingarticlehp(this, this.hp_Current);
            }
        }
        if (mp != 0) {
            this.mp_Current = Article.xiugai(this.mp_Total, this.mp_Current, mp);
            if (FightingMixDeal.camp == this.Camp && (myman == this.man || myman + 5 == this.man)) {
                Article.fightingarticlemp(this, this.mp_Current);
            }
        }
        if (yq != 0) {
            this.yqz += yq;
            if (FightingMixDeal.camp == this.Camp && myman == this.man) {
                FightingMixDeal.yqz = this.yqz + "";
            }
        }
        if (nq != 0) {
            this.nqz += nq;
            if (FightingMixDeal.camp == this.Camp && myman == this.man) {
                FightingMixDeal.nqz = this.nqz + "";
            }
        }
        if (xy != 0) {
            this.xy += xy;
            if (FightingMixDeal.camp == this.Camp && myman == this.man) {
                FightingMixDeal.xyz = String.valueOf(Math.min(20, this.xy));
            }
        }
    }
    
    public boolean ztcz(String type) {
        for (int i = 0; i < this.States.size(); ++i) {
            if (((String)this.States.get(i)).equals(type)) {
                return true;
            }
        }
        return false;
    }
    
    public List<TypeState> cxxx(String type) {
        List<TypeState> xx = new ArrayList<>();
        String[] v = this.State_1.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals(type)) {
                String[] v3 = v2[1].split("\\_");
                for (int j = 0; j < v3.length; ++j) {
                    String[] v4 = v3[j].split("\\-");
                    TypeState state = new TypeState();
                    state.setType(v4[0]);
                    state.setState(Integer.parseInt(v4[1]));
                    xx.add(state);
                }
            }
        }
        return xx;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getState_1() {
        return this.State_1;
    }
    
    public void setState_1(String state_1) {
        this.State_1 = state_1;
    }
    
    public String getManname() {
        return this.manname;
    }
    
    public void setManname(String manname) {
        this.manname = manname;
    }
    
    public int getCamp() {
        return this.Camp;
    }
    
    public void setCamp(int camp) {
        this.Camp = camp;
    }
    
    public int getMan() {
        return this.man;
    }
    
    public void setMan(int man) {
        this.man = man;
    }
    
    public int getHp_Total() {
        return this.hp_Total;
    }
    
    public void setHp_Total(int hp_Total) {
        this.hp_Total = hp_Total;
    }
    
    public int getHp_Current() {
        return this.hp_Current;
    }
    
    public void setHp_Current(int hp_Current) {
        this.hp_Current = hp_Current;
    }
    
    public int getMp_Total() {
        return this.mp_Total;
    }
    
    public void setMp_Total(int mp_Total) {
        this.mp_Total = mp_Total;
    }
    
    public int getMp_Current() {
        return this.mp_Current;
    }
    
    public void setMp_Current(int mp_Current) {
        this.mp_Current = mp_Current;
    }
    
    public List<String> getStates() {
        return this.States;
    }
    
    public void setB(boolean B) {
        this.B = B;
    }
    
    public boolean getB() {
        return this.B;
    }
    
    public void setStates(List<String> states) {
        this.States = states;
    }
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getZs() {
        return this.zs;
    }
    
    public void setZs(int zs) {
        this.zs = zs;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public int getYqz() {
        return this.yqz;
    }
    
    public void setYqz(int yqz) {
        this.yqz = yqz;
    }
    
    public int getNqz() {
        return this.nqz;
    }
    
    public void setNqz(int nqz) {
        this.nqz = nqz;
    }
    
    public Integer getW() {
        return this.W;
    }
    
    public void setW(Integer w) {
        this.W = w;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    public int getXy() {
        return this.xy;
    }

    public void setXy(int xy) {
        this.xy = xy;
    }
    public NewPart getPart() {
        String[] v = this.model.split("\\&");
        NewPart parts = null;
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith("W")) {
                this.W = Integer.valueOf(Integer.parseInt(v[i].substring(1)));
            }
            else {
                String[] vs = v[i].split("\\_");
                String skin = vs[0];
                int lvl = 0;
                int act = -2;
                String color = null;
                if (vs.length == 1) {
                    lvl = 1;
                    act = 7;
                }
                else {
                    if (vs.length > 1) {
                        lvl = Integer.parseInt(vs[1]);
                    }
                    if (vs.length > 2) {
                        act = Integer.parseInt(vs[2]);
                    }
                    if (vs.length > 3) {
                        color = vs[3];
                    }
                }
                if (skin.contains("#")) {
                    String[] v2 = skin.split("#");
                    skin = v2[0];
                    color = v2[1];
                }
                NewPart part = null;
                try {
                    part = SpriteFactory.createPart(skin, act, lvl, color);
                }
                catch (Exception e) {
                    System.out.println(this.manname);
                }
                if (parts == null) {
                    parts = part;
                }
                else {
                    parts = parts.addPart(part);
                }
            }
        }
        return parts;
    }
    
    static {
        FightingManData.fbs = new String[] { "fbYsjl", "fbJjl", "fbDsc", "fbQbllt", "fbHlz", "fbYmgs", "fbDsy", "fbJqb", "fbQw", "fbBld", "fbJge", "fbFty", "fbJljs", "fbBgz", "fbHd", "bbss", "6018", "6019", "6022", "6024", "6025", "6027", "6028", "7002", "7008", "7015", "7026", "7033", "7034", "7035", "9389",
                "沧波", "扶摇", "甘霖", "1231", "1232", "1866", "1869", "1873", "1308", "1254", "1260", "1263", "天蓬转世",
                "以静制动", "清心静气", "失魂落魄", "利刃加身", "兽魂俯首", "凝神一击", "刚柔兼备", "幻影迷踪", "虎踞龙蟠", "气吞山河",
                "明镜止水", "气聚神凝", "无坚不摧", "血蛊佑身", "鱼龙潜跃", "法魂护体", "扑朔迷离", "神不守舍", "神龙摆尾", "行气如虹",
                "积羽沉舟", "荆棘束身", "23004", "知耻后勇" ,"分辉"};
        (FightingManData.drawDown = new ArrayList<>()).add("金");
        FightingManData.drawDown.add("木");
        FightingManData.drawDown.add("土");
        FightingManData.drawDown.add("水");
        FightingManData.drawDown.add("火");
        FightingManData.drawDown.add("1869");
        FightingManData.drawDown.add("1876");
        FightingManData.drawDown.add("1889");
        FightingManData.drawDown.add("消除五行");
        FightingManData.drawDown.add("一矢中的");
        FightingManData.drawDown.add("fbJge");
        (FightingManData.lingxistate = new ArrayList<>()).add("11006");
        FightingManData.lingxistate.add("11007");
        FightingManData.lingxistate.add("11010");
        FightingManData.lingxistate.add("11012");
        FightingManData.lingxistate.add("11013");
        FightingManData.lingxistate.add("11014");
        FightingManData.lingxistate.add("11015");
        FightingManData.lingxistate.add("11017");
        FightingManData.lingxistate.add("11018");
        FightingManData.lingxistate.add("11019");
        FightingManData.lingxistate.add("11025");
        FightingManData.lingxistate.add("11027");
        FightingManData.lingxistate.add("11029");
        FightingManData.lingxistate.add("11031");
        FightingManData.lingxistate.add("11032");
        FightingManData.lingxistate.add("11033");
        FightingManData.lingxistate.add("11034");
        FightingManData.lingxistate.add("11035");
    }
}
