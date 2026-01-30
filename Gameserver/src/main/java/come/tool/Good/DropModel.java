package come.tool.Good;

import java.util.ArrayList;
import org.come.server.GameServer;
import org.come.entity.Goodstable;
import java.util.List;
import org.come.bean.PathPoint;
import java.math.BigDecimal;

public class DropModel
{
    private BigDecimal exp;
    private BigDecimal expFix;
    private BigDecimal money;
    private BigDecimal moneySteal;
    private BigDecimal codeCard;
    private Integer maxRole;
    private Integer maxPet;
    private Integer maxGood;
    private Integer maxKill;
    private Integer maxDrop;
    private PathPoint[] exps;
    private Integer taskId;
    private Integer taskJL;
    private List<DropType> types;
    private BigDecimal skillId;
    private Integer skillGrow;
    private Boolean isTeam;
    
    public int getExps(int num) {
        if (this.exps == null) {
            return 1;
        }
        for (int i = 0; i < this.exps.length; ++i) {
            if (this.exps[i].getY() >= num) {
                return this.exps[i].getX();
            }
        }
        return 0;
    }
    
    public DropModel(StringBuffer buffer, String[] v) {
        this.isTeam = Boolean.valueOf(false);
        for (int i = 1; i < v.length; ++i) {
            String[] vs = v[i].split("=");
            if (vs.length >= 2) {
                if (vs[0].equals("经验")) {
                    this.exp = new BigDecimal(vs[1]);
                    if (buffer.length() != 0) {
                        buffer.append("#r");
                    }
                    buffer.append("经验:");
                    buffer.append(this.exp);
                }
                else if (vs[0].equals("金钱")) {
                    this.money = new BigDecimal(vs[1]);
                    if (buffer.length() != 0) {
                        buffer.append("#r");
                    }
                    buffer.append("金钱:");
                    buffer.append(this.money);
                }
                else if (vs[0].equals("仙玉")) {
                    this.codeCard = new BigDecimal(vs[1]);
                    if (buffer.length() != 0) {
                        buffer.append("#r");
                    }
                    buffer.append("仙玉:");
                    buffer.append(this.codeCard);
                }
                else {
                    DropType dropType = this.getDropType(vs);
                    if (dropType != null) {
                        if (dropType.getDropType() == 4) {
                            DropGood dropGood = dropType.getDropGood();
                            if (dropGood.getEmpty() <= 0.0 && dropGood.getDraws().length == 1 && dropGood.getDraws()[0].getIds().length == 1 && dropGood.getDraws()[0].getV() >= 100.0) {
                                Goodstable goodstable = (Goodstable)GameServer.getAllGoodsMap().get(new BigDecimal(dropGood.getDraws()[0].getIds()[0]));
                                if (goodstable != null) {
                                    if (buffer.length() != 0) {
                                        buffer.append("#r");
                                    }
                                    buffer.append(goodstable.getGoodsname());
                                    buffer.append("*");
                                    buffer.append(dropGood.getDraws()[0].getSum());
                                }
                            }
                            else if (dropGood.getDraws().length >= 1) {
                                StringBuffer buffer2 = new StringBuffer();
                                buffer2.append("有概率获得");
                                int size = 0;
                            LOOP:
                                for (int j = 0; j < dropGood.getDraws().length; ++j) {
                                    for (int j2 = 0; j2 < dropGood.getDraws()[j].getIds().length; ++j2) {
                                        Goodstable goodstable2 = (Goodstable)GameServer.getAllGoodsMap().get(new BigDecimal(dropGood.getDraws()[j].getIds()[j2]));
                                        if (goodstable2 != null) {
                                            buffer2.append(" ");
                                            buffer2.append(goodstable2.getGoodsname());
                                            if (++size >= 3) {
                                                break LOOP;
                                            }
                                        }
                                    }
                                }
                                buffer2.append("等物品");
                                if (buffer2.length() > 8) {
                                    if (buffer.length() != 0) {
                                        buffer.append("#r");
                                    }
                                    buffer.append(buffer2.toString());
                                }
                            }
                        }
                        else if (dropType.getDropType() == 7) {
                            if (buffer.length() != 0) {
                                buffer.append("#r");
                            }
                            buffer.append("帮贡:");
                        }
                        else if (dropType.getDropType() == 8) {
                            if (buffer.length() != 0) {
                                buffer.append("#r");
                            }
                            buffer.append("师贡:");
                        }
                        else if (dropType.getDropType() == 1) {
                            if (buffer.length() != 0) {
                                buffer.append("#r");
                            }
                            buffer.append(dropType.getKey());
                            buffer.append(":");
                            buffer.append(dropType.getValue());
                        }
                        else {
                            continue;
                        }
                        if (this.types == null) {
                            this.types = new ArrayList<>();
                        }
                        this.types.add(dropType);
                    }
                }
            }
        }
    }
    
    public DropModel(String... v) {
        this.isTeam = Boolean.valueOf(false);
        for (int i = 0; i < v.length; ++i) {
            String[] vs = v[i].split("=");
            if (vs.length == 2) {
                if (vs[0].equals("经验")) {
                    this.exp = new BigDecimal(vs[1]);
                }
                else if (vs[0].equals("固定经验")) {
                    this.expFix = new BigDecimal(vs[1]);
                }
                else if (vs[0].equals("金钱")) {
                    this.money = new BigDecimal(vs[1]);
                }
                else if (vs[0].equals("偷钱")) {
                    this.moneySteal = new BigDecimal(vs[1]);
                }
                else if (vs[0].equals("仙玉")) {
                    this.codeCard = new BigDecimal(vs[1]);
                }
                else if (vs[0].equals("人物最大等级")) {
                    this.maxRole = Integer.valueOf(Integer.parseInt(vs[1]));
                }
                else if (vs[0].equals("召唤兽最大等级")) {
                    this.maxPet = Integer.valueOf(Integer.parseInt(vs[1]));
                }
                else if (vs[0].equals("掉落最大等级")) {
                    this.maxDrop = Integer.valueOf(Integer.parseInt(vs[1]));
                }
                else if (vs[0].equals("最大物品")) {
                    this.maxGood = Integer.valueOf(Integer.parseInt(vs[1]));
                }
                else if (vs[0].equals("最多击杀次数")) {
                    this.maxKill = Integer.valueOf(Integer.parseInt(vs[1]));
                }
                else if (vs[0].equals("最大经验")) {
                    String[] vss = vs[1].split("&");
                    this.exps = new PathPoint[vss.length];
                    for (int j = 0; j < vss.length; ++j) {
                        String[] vsss = vss[j].split("-");
                        this.exps[j] = new PathPoint(Integer.parseInt(vsss[0]), Integer.parseInt(vsss[1]));
                    }
                }
                else if (vs[0].equals("任务")) {
                    String[] vss = vs[1].split("&");
                    if (vss.length == 2) {
                        this.taskId = Integer.valueOf(Integer.parseInt(vss[0]));
                        this.taskJL = Integer.valueOf(Integer.parseInt(vss[1]));
                    }
                }
                else if (vs[0].startsWith("SKILL")) {
                    String sid = vs[0].substring(5, vs[0].length());
                    this.skillId = new BigDecimal(sid);
                    this.skillGrow = Integer.valueOf(Integer.parseInt(vs[1]));
                }
                else {
                    DropType dropType = this.getDropType(vs);
                    if (dropType != null) {
                        if (this.types == null) {
                            this.types = new ArrayList<>();
                        }
                        this.types.add(dropType);
                    }
                }
            }
        }
    }
    
    public DropType getDropType(String[] vs) {
        if (vs[0].equals("称谓")) {
            return new DropType(6, vs[1]);
        }
        if (vs[0].equals("记录")) {
            return new DropType(2, vs[0], Integer.valueOf(Integer.parseInt(vs[1])));
        }
        if (vs[0].equals("放妖")) {
            return new DropType(5, vs[1]);
        }
        if (vs[0].equals("自选")) {
            return new DropType(15,vs[1]);
        }
        if (vs[0].endsWith("积分") || vs[0].equals("比斗奖章") || vs[0].equals("星芒")) {
            return new DropType(1, vs[0], Integer.valueOf(Integer.parseInt(vs[1])));
        }
        if (vs[0].startsWith("击杀")) {
            return new DropType(3, vs[0], Integer.valueOf(Integer.parseInt(vs[1])));
        }
        if (vs[0].startsWith("物品")) {
            return new DropType(4, vs[1]);
        }
        if (vs[0].startsWith("技能")) {
            return new DropType(14, vs[1]);
        }
        if (vs[0].startsWith("帮贡")) {
            return new DropType(7, Integer.valueOf(Integer.parseInt(vs[1])));
        }
        if (vs[0].startsWith("师贡")) {
            return new DropType(8, Integer.valueOf(Integer.parseInt(vs[1])));
        }
        if (vs[0].startsWith("队长")) {
            return new DropType(13, vs[1]);
        }
        return null;
    }
    
    public BigDecimal getExp() {
        return this.exp;
    }
    
    public void setExp(BigDecimal exp) {
        this.exp = exp;
    }
    
    public BigDecimal getExpFix() {
        return this.expFix;
    }
    
    public void setExpFix(BigDecimal expFix) {
        this.expFix = expFix;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public BigDecimal getMoneySteal() {
        return this.moneySteal;
    }
    
    public void setMoneySteal(BigDecimal moneySteal) {
        this.moneySteal = moneySteal;
    }
    
    public BigDecimal getCodeCard() {
        return this.codeCard;
    }
    
    public void setCodeCard(BigDecimal codeCard) {
        this.codeCard = codeCard;
    }
    
    public Integer getMaxRole() {
        return this.maxRole;
    }
    
    public void setMaxRole(Integer maxRole) {
        this.maxRole = maxRole;
    }
    
    public Integer getMaxPet() {
        return this.maxPet;
    }
    
    public void setMaxPet(Integer maxPet) {
        this.maxPet = maxPet;
    }
    
    public Integer getMaxGood() {
        return this.maxGood;
    }
    
    public void setMaxGood(Integer maxGood) {
        this.maxGood = maxGood;
    }
    
    public PathPoint[] getExps() {
        return this.exps;
    }
    
    public void setExps(PathPoint[] exps) {
        this.exps = exps;
    }
    
    public Integer getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    
    public Integer getTaskJL() {
        return this.taskJL;
    }
    
    public void setTaskJL(Integer taskJL) {
        this.taskJL = taskJL;
    }
    
    public List<DropType> getTypes() {
        return this.types;
    }
    
    public void setTypes(List<DropType> types) {
        this.types = types;
    }
    
    public Integer getMaxDrop() {
        return this.maxDrop;
    }
    
    public void setMaxDrop(Integer maxDrop) {
        this.maxDrop = maxDrop;
    }
    
    public Integer getSkillGrow() {
        return this.skillGrow;
    }
    
    public void setSkillGrow(Integer skillGrow) {
        this.skillGrow = skillGrow;
    }
    
    public BigDecimal getSkillId() {
        return this.skillId;
    }
    
    public void setSkillId(BigDecimal skillId) {
        this.skillId = skillId;
    }
    
    public Boolean getTeam() {
        return this.isTeam;
    }
    
    public void setTeam(Boolean team) {
        this.isTeam = team;
    }
    
    public Integer getMaxKill() {
        return this.maxKill;
    }
    
    public void setMaxKill(Integer maxKill) {
        this.maxKill = maxKill;
    }
}
