package com.tool.Stall;

import org.come.bean.XuanBao;
import org.come.entity.*;
import org.come.until.JmSum;
import java.math.BigDecimal;

import org.come.bean.UseCardBean;
import org.come.model.Lingbao;

import java.util.ArrayList;
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
    private List<Goodstable> goods;
    private List<RoleSummoning> pets;
    private List<Lingbao> lingbaos;
    private List<Mount> mounts;
    private List<Car> cars;
    private List<Baby> babys;
    private List<PartJade> jades;
    private List<Pal> pals;
    private UseCardBean useCard;
    private int type;
    private String msg;
    private String data;
    private String sceneMsg;
    private long I;
    private String resistance;
    private String vip;
    private String task;
    private List<Fly> flys;
    private BigDecimal summoningId;
    private BigDecimal baoId;
    private Integer ttVictory;
    //天梯负场
    private Integer ttFail;
    private List<XuanBao> xuanBaos;
    public List<XuanBao> getXuanBaos() {
        return this.xuanBaos;
    }

    public void setXuanBaos(List<XuanBao> xuanBaos) {
        this.xuanBaos = xuanBaos;
    }
    public List<Goodstable> getGoods() {
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
        return this.pets;
    }
    
    public void setPets(List<RoleSummoning> pets) {
        this.pets = pets;
    }
    
    public List<Lingbao> getLingbaos() {
        return this.lingbaos;
    }
    
    public void setLingbaos(List<Lingbao> lingbaos) {
        this.lingbaos = lingbaos;
    }
    
    public List<PartJade> getJades() {
        return this.jades;
    }
    
    public void setJades(List<PartJade> jades) {
        this.jades = jades;
    }
    
    public List<Mount> getMounts() {
        return this.mounts;
    }
    
    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }
    
    public List<Baby> getBabys() {
        return this.babys;
    }
    
    public void setBabys(List<Baby> babys) {
        this.babys = babys;
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
    
    public long getI() {
        return JmSum.MZ(this.I);
    }
    
    public void setI(long i) {
        this.I = JmSum.ZM(i);
    }
    
    public UseCardBean getUseCard() {
        return this.useCard;
    }
    
    public void setUseCard(UseCardBean useCard) {
        this.useCard = useCard;
    }
    
    public String getSceneMsg() {
        return this.sceneMsg;
    }
    
    public void setSceneMsg(String sceneMsg) {
        this.sceneMsg = sceneMsg;
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
    public String msg() {
        String typeM = "现金";
        StringBuffer buffer = null;
        switch (this.type) {
            case 0:
            case 1:
            case 2: {
                if (this.msg == null || this.msg.length() == 0) {
                    return null;
                }
                buffer = new StringBuffer();
                buffer.append("");
                buffer.append(this.msg);
                return buffer.toString();
            }
            case 3: {
                buffer = new StringBuffer();
                buffer.append("你收到别人送给你的");
                if (this.msg != null) {
                    buffer.append(this.msg);
                }
                if (this.data != null) {
                    if (buffer.length() != 0) {
                        buffer.append(",");
                    }
                    String[] VS = this.data.split("\\|");
                    for (int i = 0; i < VS.length; ++i) {
                        if (VS[i].startsWith("D")) {
                            buffer.append("金钱");
                            buffer.append(VS[i].split("=")[1]);
                        }
                    }
                }
                return buffer.toString();
            }
            case 5: {
                if (typeM.equals("积分")) {
                    if (this.data == null) {
                        return null;
                    }
                    buffer = new StringBuffer();
                    buffer.append("花费了");
                    String[] VS = this.data.split("\\|");
                    for (int i = 0; i < VS.length; ++i) {
                        if (VS[i].startsWith("C")) {
                            buffer.append(VS[i].split("=")[1]);
                            buffer.append("积分");
                        }
                    }
                    buffer.append("购买了");
                    buffer.append(this.msg);
                    return buffer.toString();
                }
                else {
                    if (this.data == null) {
                        return null;
                    }
                    buffer = new StringBuffer();
                    buffer.append("花费了");
                    String[] VS = this.data.split("\\|");
                    for (int i = 0; i < VS.length; ++i) {
                        if (VS[i].startsWith("D")) {
                            buffer.append(Math.abs(Integer.parseInt(VS[i].split("=")[1])));
                            buffer.append("现金");
                        }
                    }
                    buffer.append("购买了");
                    buffer.append(this.msg);
                    return buffer.toString();
                }
            }
            case 6: {
                if (typeM.equals("积分")) {
                    if (this.data == null) {
                        return null;
                    }
                    buffer = new StringBuffer();
                    buffer.append("你售出");
                    buffer.append(this.msg);
                    buffer.append("获得了");
                    String[] VS2 = this.data.split("\\|");
                    for (int i = 0; i < VS2.length; ++i) {
                        if (VS2[i].startsWith("C")) {
                            buffer.append(VS2[i].split("=")[1]);
                            buffer.append("积分");
                        }
                    }
                    return buffer.toString();
                }
                else {
                    if (this.data == null) {
                        return null;
                    }
                    buffer = new StringBuffer();
                    buffer.append("你售出");
                    buffer.append(this.msg);
                    buffer.append("获得了");
                    String[] VS2 = this.data.split("\\|");
                    for (int i = 0; i < VS2.length; ++i) {
                        if (VS2[i].startsWith("D")) {
                            buffer.append(VS2[i].split("=")[1]);
                            buffer.append("现金");
                        }
                    }
                    return buffer.toString();
                }
            }
            case 8: {
                if (this.data == null) {
                    return null;
                }
                buffer = new StringBuffer();
                buffer.append("获得了");
                String[] VS3 = this.data.split("\\|");
                for (int i = 0; i < VS3.length; ++i) {
                    if (VS3[i].startsWith("D")) {
                        buffer.append(VS3[i].split("=")[1]);
                        buffer.append("金钱");
                    }
                    else if (VS3[i].startsWith("X")) {
                        buffer.append(VS3[i].split("=")[1]);
                        buffer.append("点仙玉");
                    }
                    else if (VS3[i].startsWith("C")) {
                        buffer.append(VS3[i].split("=")[1]);
                        buffer.append("点RMB充值点");
                    }
                    else if (VS3[i].startsWith("S")) {
                        buffer.setLength(0);
                        buffer.append(this.getMsg());
                    }
                    else if (VS3[i].startsWith("P")) {
                        buffer.setLength(0);
                        buffer.append(this.getMsg());
                    }
                    else if (VS3[i].startsWith("Z")) {
                        buffer.setLength(0);
                        buffer.append(this.getMsg());
                    }
                }
                return buffer.toString();
            }
            case 11: {
                buffer = new StringBuffer();
                buffer.append("你取回了");
                buffer.append(this.msg);
                return buffer.toString();
            }
            case 21:
            case 22:
            case 23:
            case 24:
            case 25: {
                return this.msg;
            }
            default: {
                return null;
            }
        }
    }
    
    public String getVip() {
        return this.vip;
    }
    
    public void setVip(String vip) {
        this.vip = vip;
    }
    
    public List<Pal> getPals() {
        return this.pals;
    }
    
    public void setPals(List<Pal> pals) {
        this.pals = pals;
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
    
    public List getFlys() {
        return this.flys;
    }
    
    public void setFlys(List flys) {
        this.flys = flys;
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
    public String getDifficultLevel() {
        return difficultLevel;
    }
    public String getDifficultrecord() {
        return difficultrecord;
    }
    public void setDifficultLevel(String difficultLevel) {
        this.difficultLevel = difficultLevel;
    }
    private String difficultLevel;
    private String difficultrecord;
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
    }
}
