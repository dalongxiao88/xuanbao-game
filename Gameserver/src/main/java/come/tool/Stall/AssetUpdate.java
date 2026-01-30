package come.tool.Stall;

import org.come.entity.*;
import org.come.tool.JmSum;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.come.bean.UseCardBean;
import come.tool.Role.PartJade;

import java.util.List;

public class AssetUpdate
{
    public static int NPC;
    public static int MALL;
    public static int INTEGRATION;
    public static int GIVE;
    public static int DEAL;
    public static int STALLBUY;
    public static int STALLGET;
    public static int STALLRET;
    public static int USERGOOD;
    public static int STEALING;
    public static int SUIT;
    public static int CGB;
    public static int CLICK;
    public static int USEGOOD;
    public static int CBGBUY;
    public static int CBGGET;
    public static int LHRETURN;
    public static int SHOUHU;
    private List<Goodstable> goods;
    private List<RoleSummoning> pets;
    private List<Lingbao> lingbaos;
    private List<Mount> mounts;
    private List<Car> cars;
    private List<Baby> babys;
    private List<Fly> flys;
    private List<PartJade> jades;
    private List<Pal> pals;
    private UseCardBean useCard;
    private int type;
    private String msg;
    private String data;
    private String sceneMsg;
    private String resistance;
    private String vip;
    private String task;
    private long I;
    private BigDecimal summoningId;
    private BigDecimal baoId;
    private Integer ttVictory;
    private Integer ttFail;
    private String difficultLevel;
    private static long fag;
    
    public AssetUpdate() {
        this.setI(getFag());
    }
    
    public AssetUpdate(int type) {
        this.type = type;
        this.setI(getFag());
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getData() {
        return this.data;
    }
    
    public void setData(String data) {
        this.data = data;
    }

    private String difficultrecord;
    public void updata(String data) {
        if (data == null || data.equals("")) {
            return;
        }
        if (this.data == null || this.data.equals("")) {
            this.data = data;
        }
        else {
            this.data = this.data + "|" + data;
        }
    }
    
    public void upmsg(String msg) {
        if (msg == null || msg.equals("")) {
            return;
        }
        if (this.msg == null || this.msg.equals("")) {
            this.msg = msg;
        }
        else {
            this.msg = this.msg + "|" + msg;
        }
    }
    
    public List<Goodstable> getGoods() {
        if (this.goods == null) {
            this.goods = new ArrayList<>();
        }
        return this.goods;
    }
    
    public void setGoods(List<Goodstable> goods) {
        this.goods = goods;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public List<RoleSummoning> getPets() {
        if (this.pets == null) {
            this.pets = new ArrayList<>();
        }
        return this.pets;
    }
    
    public void setPets(List<RoleSummoning> pets) {
        this.pets = pets;
    }
    
    public List<Lingbao> getLingbaos() {
        if (this.lingbaos == null) {
            this.lingbaos = new ArrayList<>();
        }
        return this.lingbaos;
    }
    
    public void setLingbaos(List<Lingbao> lingbaos) {
        this.lingbaos = lingbaos;
    }
    
    public List<PartJade> getJades() {
        if (this.jades == null) {
            this.jades = new ArrayList<>();
        }
        return this.jades;
    }
    
    public void setJades(List<PartJade> jades) {
        this.jades = jades;
    }
    
    public List<Mount> getMounts() {
        if (this.mounts == null) {
            this.mounts = new ArrayList<>();
        }
        return this.mounts;
    }
    
    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }
    public List<Car> getCars() {
        if (this.cars == null) {
            this.cars = new ArrayList<>();
        }
        return this.cars;
    }

    public void setCars(List<Car> mounts) {
        this.cars = mounts;
    }
    public void setCar(Car mount) {
        this.getCars().add(mount);
    }
    public List<Fly> getFlys() {
        if (this.flys == null) {
            this.flys = new ArrayList<>();
        }
        return this.flys;
    }
    
    public void setFlys(List<Fly> flys) {
        this.flys = flys;
    }
    
    public List<Baby> getBabys() {
        if (this.babys == null) {
            this.babys = new ArrayList<>();
        }
        return this.babys;
    }
    
    public void setFly(Fly fly) {
        this.getFlys().add(fly);
    }
    
    public void setBabys(List<Baby> babys) {
        this.babys = babys;
    }
    
    public void setGood(Goodstable good) {
        this.getGoods().add(good);
    }
    
    public void setPet(RoleSummoning pet) {
        this.getPets().add(pet);
    }
    
    public void setLingbao(Lingbao lingbao) {
        this.getLingbaos().add(lingbao);
    }
    
    public void setBaby(Baby baby) {
        this.getBabys().add(baby);
    }
    
    public void setMount(Mount mount) {
        this.getMounts().add(mount);
    }
    
    public void setPal(Pal pal) {
        if (this.pals == null) {
            this.pals = new ArrayList<>();
        }
        this.pals.add(pal);
    }
    
    public void setJade(PartJade jade) {
        this.getJades();
        for (int i = 0; i < this.jades.size(); ++i) {
            PartJade partJade = (PartJade)this.jades.get(i);
            if (jade.getSuitid() == partJade.getSuitid() && jade.getPartId() == partJade.getPartId()) {
                this.jades.set(i, jade);
                return;
            }
        }
        this.jades.add(jade);
    }
    
    public UseCardBean getUseCard() {
        return this.useCard;
    }
    
    public void setUseCard(UseCardBean useCard) {
        this.useCard = useCard;
    }
    
    public long getI() {
        return JmSum.MZ(this.I);
    }
    
    public void setI(long i) {
        this.I = JmSum.ZM(i);
    }
    
    public String getSceneMsg() {
        return this.sceneMsg;
    }
    
    public void setSceneMsg(String sceneMsg) {
        this.sceneMsg = sceneMsg;
    }
    
    public Goodstable getGoodSid(BigDecimal sid) {
        if (this.goods == null) {
            return null;
        }
        for (int i = this.goods.size() - 1; i >= 0; --i) {
            if (((Goodstable)this.goods.get(i)).getGoodsid().compareTo(sid) == 0) {
                return (Goodstable)this.goods.get(i);
            }
        }
        return null;
    }
    
    public void reset() {
        this.setI(getFag());
    }
    
    private static synchronized long getFag() {
        return ++AssetUpdate.fag;
    }
    
    public String getVip() {
        return this.vip;
    }
    
    public void setVip(String vip) {
        this.vip = vip;
    }
    
    public String getResistance() {
        return this.resistance;
    }
    
    public void setResistance(String resistance) {
        this.resistance = resistance;
    }
    
    public String getTask() {
        return this.task;
    }
    
    public void setTask(String task) {
        this.task = task;
    }
    
    public BigDecimal getSummoningId() {
        return this.summoningId;
    }
    
    public void setSummoningId(BigDecimal summoningId) {
        this.summoningId = summoningId;
    }
    
    public BigDecimal getBaoId() {
        return this.baoId;
    }
    
    public void setBaoId(BigDecimal baoId) {
        this.baoId = baoId;
    }
    
    public Integer getTtVictory() {
        return this.ttVictory;
    }
    
    public void setTtVictory(Integer ttVictory) {
        this.ttVictory = ttVictory;
    }
    
    public Integer getTtFail() {
        return this.ttFail;
    }
    
    public void setTtFail(Integer ttFail) {
        this.ttFail = ttFail;
    }
    public void setDifficultrecord(String difficultrecord) {
        this.difficultrecord = difficultrecord;
    }
    public String getDifficultrecord() {
        return difficultrecord;
    }
    
    static {
        AssetUpdate.NPC = 0;
        AssetUpdate.MALL = 1;
        AssetUpdate.INTEGRATION = 2;
        AssetUpdate.GIVE = 3;
        AssetUpdate.DEAL = 4;
        AssetUpdate.STALLBUY = 5;
        AssetUpdate.STALLGET = 6;
        AssetUpdate.STALLRET = 7;
        AssetUpdate.USERGOOD = 8;
        AssetUpdate.STEALING = 9;
        AssetUpdate.SUIT = 10;
        AssetUpdate.CGB = 11;
        AssetUpdate.CLICK = 21;
        AssetUpdate.USEGOOD = 25;
        AssetUpdate.CBGBUY = 15;
        AssetUpdate.CBGGET = 16;
        AssetUpdate.LHRETURN = 30;
        AssetUpdate.SHOUHU = 2255;
        AssetUpdate.fag = 0L;
    }
    public String getDifficultLevel() {
        return difficultLevel;
    }

    public void setDifficultLevel(String difficultLevel) {
        this.difficultLevel = difficultLevel;
    }

    private List<XuanBao> xuanBaos;
    public List<XuanBao> getXuanBaos() {
        return this.xuanBaos;
    }

    public void setXuanBaos(List<XuanBao> xuanBaos) {
        this.xuanBaos = xuanBaos;
    }

    public void setXuanBao(XuanBao xuanbao) {
        if (getXuanBaos() == null) this.xuanBaos = new ArrayList<>();
        getXuanBaos().add(xuanbao);
    }
}
