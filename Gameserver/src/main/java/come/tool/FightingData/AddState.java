package come.tool.FightingData;

import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import java.util.stream.Collectors;
import java.util.List;
/**
 * 附加状态
 * @author Administrator
 *
 */
public class AddState
{
    private String statename;//附加状态名
    private int type;//法术类型 0表示增益 1表示减益
    private double stateEffect;//状态效果
    private double stateEffect2;//状态效果2
    private double stateEffect3;//状态效果3
    private int Surplus;//持续的回合数
    private int Ongoing;//已持续的回合数
    private List<FightingSkill> skills;//附加技能
    private List<FightingSkill> skills2;//附加技能
    private int manId;// 施法人id
    
    public AddState() {
        this.type = -1;
    }

    //判断是否持续到头了
    public boolean isEnd() {
        ++this.Ongoing;
        --this.Surplus;
        return this.Surplus <= 0;
    }

    public AddState(String statename, int surplus, double... value) {
        this.statename = statename;
        this.Surplus = surplus;
        if (value.length > 0) {
            this.stateEffect = value[0];
        }
        if (value.length > 1) {
            this.stateEffect2 = value[1];
        }
        if (value.length > 2) {
            this.stateEffect3 = value[2];
        }
        if (value.length > 3) {
            this.stateEffect4 = value[3];
        }
        if (value.length > 4) {
            this.stateEffect5 = value[4];
        }
        if (value.length > 5) {
            this.stateEffect6 = value[5];
        }
        if (value.length > 6) {
            this.stateEffect7 = value[6];
        }
    }
    public AddState(String statename, double stateEffect, int surplus) {
        this.statename = statename;
        if (statename.equals(TypeUtil.LL) || statename.equals(TypeUtil.JS) || statename.equals(TypeUtil.KX) || statename.equals(TypeUtil.MH)) {
            this.type = 0;
        }
        else {
            this.type = 1;
        }
        this.stateEffect = stateEffect;
        this.Surplus = surplus;
    }
    
    public AddState(String statename, double stateEffect, double stateEffect2, int surplus) {
        this.statename = statename;
        this.stateEffect = stateEffect;
        this.stateEffect2 = stateEffect2;
        this.Surplus = surplus;
    }
    //	9104|风刀霜剑|被封印的目标在三回合摆脱封印，则仙法鬼火抗性降低（2%*等级），持续2回合。（仅在与玩家之间战斗有效）
//	9106|白发苍颜|被封印的目标解除封印时，损失最大血量的（1%*等级）。（仅在与NPC之间战斗有效。）
//	9107|春寒料峭|被封印的目标如果在三回合内摆脱封印状态，则损失最大血量的（1%*等级）。（仅在与玩家之间战斗有效。）
//	9109|浩气清英|被封印的目标解除封印状态后当回合内对我方单位造成的所有伤害降低（5%*等级）。（仅在与NPC之间战斗有效）
//	9112|霁月霜天|被封印的目标解除封印状态后有（4%*等级）的几率进入混乱状态，持续1回合。（仅在与NPC之间战斗有效。）
//	9125|残酒慷妆|被昏睡的目标摆脱昏睡状态时（非物理，法术攻击解除）有（4%*等级）几率扣除最大法量的（1.5%*等级）。（仅在与玩家之间战斗有效。）
//	9128|渔舟唱晚|被昏睡的目标如果在下回合自动解除昏睡（非物理，法术攻击解除），则有（5%+1%*等级）陷入封印状态1回合。（仅在与玩家之间战斗有效。
//	9147|杜鹃啼血|被混乱的目标如果在三回合内摆脱混乱状态，则损失最大血量的（1%*等级）。（仅在与玩家之间战斗有效。）
//	9351|梦断魂劳|被遗忘的人物摆脱遗忘后,有20%率进虚弱状态,虚弱状态下的单位受到的伤害增加（10%+2%*等级）,持续2回合。(仅在与玩家之间战斗有效
    /**摆脱状态的处理*/
    public void rid(ManData data, FightingState state) {
        if ("扶摇".equals(this.statename)) {
            data.UP(state, 0, this.stateEffect / 100.0);
        }
        else if (TypeUtil.SH_4010.equals(this.statename) && data.getStates() == 0) {
            data.UP(state, 0, -this.stateEffect);
        }
        else if (TypeUtil.L_LL.equals(this.statename)) {
            data.UPModel(state, null);
            data.UP(state, 0, -this.stateEffect / 100.0);
        }
        else if (TypeUtil.TY_L_GL_PYGQ.equals(this.statename)) {
            data.UP(state, 0, -this.stateEffect / 100.0);
        }
        else if (this.statename.equals(TypeUtil.LB_TTZS)) {
            state.setSkin(data.getSkin());
            state.setText("我胡汉三又回来了#28");
        }
        if (this.skills == null) {
            return;
        }
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            FightingSkill skill = (FightingSkill)this.skills.get(i);
            if (skill.getSkillid() == 9104) {
                //9104|风刀霜剑|被封印的目标在三回合摆脱封印，则仙法鬼火抗性降低（2%*等级），持续2回合。（仅在与玩家之间战斗有效）
                if (this.Ongoing < 3) {
                    data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
                }
            }
            else if (skill.getSkillid() == 9106) {
                //9106|白发苍颜|被封印的目标解除封印时，损失最大血量的（1%*等级）。（仅在与NPC之间战斗有效。）
                ChangeFighting change = new ChangeFighting();
                change.setChangehp(-(int)((double)data.getHp_z() * skill.getSkillhurt() / 100.0));
                data.ChangeData(change, state);
            }
            else if (skill.getSkillid() == 9107) {
                //9107|春寒料峭|被封印的目标如果在三回合内摆脱封印状态，则损失最大血量的（1%*等级）。（仅在与玩家之间战斗有效。）
                if (this.Ongoing < 3) {
                    ChangeFighting change = new ChangeFighting();
                    change.setChangehp(-(int)((double)data.getHp_z() * skill.getSkillhurt() / 100.0));
                    data.ChangeData(change, state);
                }
            }
            else if (skill.getSkillid() == 9109) {
                //9109|浩气清英|被封印的目标解除封印状态后当回合内对我方单位造成的所有伤害降低（5%*等级）。（仅在与NPC之间战斗有效）
                data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
            }
            else if (skill.getSkillid() == 9112) {
                //9112|霁月霜天|被封印的目标解除封印状态后有（4%*等级）的几率进入混乱状态，持续1回合。（仅在与NPC之间战斗有效。）
                state.setEndState_1("混乱");
                data.addAddState("混乱", skill.getSkillhurt(), 0.0, 1);
            }
            else if (skill.getSkillid() == 9125) {
                //9125|残酒慷妆|被昏睡的目标摆脱昏睡状态时（非物理，法术攻击解除）有（4%*等级）几率扣除最大法量的（1.5%*等级）。（仅在与玩家之间战斗有效。）
                ChangeFighting change = new ChangeFighting();
                change.setChangemp(-(int)((double)data.getMp_z() * skill.getSkillhurt() * 1.5 / 4.0 / 100.0));
                data.ChangeData(change, state);
            }
            else if (skill.getSkillid() == 9128) {
                //9128|渔舟唱晚|被昏睡的目标如果在下回合自动解除昏睡（非物理，法术攻击解除），则有（5%+1%*等级）陷入封印状态1回合。（仅在与玩家之间战斗有效。
                if (this.Ongoing < 1) {
                    state.setEndState_1("封印");
                    data.addAddState("封印", skill.getSkillhurt(), 0.0, 1);
                }
            }
            else if (skill.getSkillid() == 9147) {
                //9147|杜鹃啼血|被混乱的目标如果在三回合内摆脱混乱状态，则损失最大血量的（1%*等级）。（仅在与玩家之间战斗有效。）
                if (this.Ongoing < 3) {
                    ChangeFighting change = new ChangeFighting();
                    change.setChangehp(-(int)((double)data.getHp_z() * skill.getSkillhurt() / 100.0));
                    data.ChangeData(change, state);
                }
            }
            else if (skill.getSkillid() == 9856) {
                //9856|宁心入梦|被混单位自动脱混，{1*}%几率进入昏睡状态，持续1回合(仅在与玩家之间战斗有效)
                if (Battlefield.isV(skill.getSkillhurt())) {
                    state.setEndState_1("昏睡");
                    data.addAddState("昏睡", 0.0, 0.0, 2);
                }
            }
            else if (skill.getSkillid() == 9351) {
                //9351|梦断魂劳|被遗忘的人物摆脱遗忘后,有20%率进虚弱状态,虚弱状态下的单位受到的伤害增加（10%+2%*等级）,持续2回合。(仅在与玩家之间战斗有效
                data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
            }
            else if (skill.getSkillid() == 9866) {
                //9866|冰天雪窑|敌方被冰单位超过存活单位的70%时，剩余未被冰单位承受伤害增加{公式一}%(仅在与NPC之间战斗有效)
                data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
            }
            else if (skill.getSkillid() == 9285) {
                //9285|仙云护体|销声匿迹护盾消失时如果人物没有死亡,则随机解除自身的一个异常状态,并提高自身（1%*等级）点冰混睡忘抗性,持续2回合。(仅在与NPC之间战斗有效)
                data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
                data.RemoveNegativeState(state);
            }
        }
    }
    
    public void rid(ManData data, FightingState state, Battlefield battlefield) {
        if ("扶摇".equals(this.statename)) {
            data.UP(state, 0, this.stateEffect / 100.0);
        }
        else if (TypeUtil.L_LL.equals(this.statename)) {
            data.UPModel(state, null);
            data.UP(state, 0, -this.stateEffect / 100.0);
        }
        else if (TypeUtil.TY_L_GL_PYGQ.equals(this.statename)) {
            data.UP(state, 0, -this.stateEffect / 100.0);
        }
        else if (this.statename.equals(TypeUtil.LB_TTZS)) {
            if (data.getType() == 0) {
                state.setSkin(data.getModel());
            }
            else {
                state.setSkin(data.getSkin());
            }
            state.setText("我胡汉三又回来了#28");
        }
        else if (this.statename.equals("无敌")) {
            GroupBuff qkzt = battlefield.getBuff(data.getCamp(), TypeUtil.LB_QKZT);
            if (qkzt != null) {
                List<ManData> nomydata = MixDeal.get(false, null, 0, battlefield.nomy(data.getCamp()), 1, 1, 0, 0, 15, data.getCamp(), battlefield, 1);
                List<ManData> manDatas = (List)nomydata.stream().filter(item/* come.tool.FightingData.ManData, */ -> item.getId() == qkzt.getData().getId()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(manDatas)) {
                    battlefield.buffs.remove(qkzt);
                    List<GroupBuff> removeBuffs = new ArrayList<>();
                    removeBuffs.add(qkzt);
                    FightingEvents hhe = new FightingEvents();
                    List<FightingState> hhs = new ArrayList<>();
                    FightingState fs = new FightingState();
                    fs.setCamp(data.getCamp());
                    fs.setMan(data.getMan());
                    fs.setStartState("代价");
                    fs.setBuff(MixDeal.getBuffStr(removeBuffs, false));
                    hhs.add(fs);
                    hhe.setAccepterlist(hhs);
                    battlefield.NewEvents.add(hhe);
                }
            }
        }
        if (this.skills == null) {
            return;
        }
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            FightingSkill skill = (FightingSkill)this.skills.get(i);
            if (skill.getSkillid() == 9104) {
                //9104|风刀霜剑|被封印的目标在三回合摆脱封印，则仙法鬼火抗性降低（2%*等级），持续2回合。（仅在与玩家之间战斗有效）
                if (this.Ongoing < 3) {
                    data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
                }
            }
            else if (skill.getSkillid() == 9106) {
                //9106|白发苍颜|被封印的目标解除封印时，损失最大血量的（1%*等级）。（仅在与NPC之间战斗有效。）
                ChangeFighting change = new ChangeFighting();
                change.setChangehp(-(int)((double)data.getHp_z() * skill.getSkillhurt() / 100.0));
                data.ChangeData(change, state);
            }
            else if (skill.getSkillid() == 9107) {
                //9107|春寒料峭|被封印的目标如果在三回合内摆脱封印状态，则损失最大血量的（1%*等级）。（仅在与玩家之间战斗有效。）
                if (this.Ongoing < 3) {
                    ChangeFighting change = new ChangeFighting();
                    change.setChangehp(-(int)((double)data.getHp_z() * skill.getSkillhurt() / 100.0));
                    data.ChangeData(change, state);
                }
            }
            else if (skill.getSkillid() == 9109) {
                //9109|浩气清英|被封印的目标解除封印状态后当回合内对我方单位造成的所有伤害降低（5%*等级）。（仅在与NPC之间战斗有效）
                data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
            }
            else if (skill.getSkillid() == 9112) {
                //9112|霁月霜天|被封印的目标解除封印状态后有（4%*等级）的几率进入混乱状态，持续1回合。（仅在与NPC之间战斗有效。）
                state.setEndState_1("混乱");
                data.addAddState("混乱", skill.getSkillhurt(), 0.0, 1);
            }
            else if (skill.getSkillid() == 9125) {
                //9125|残酒慷妆|被昏睡的目标摆脱昏睡状态时（非物理，法术攻击解除）有（4%*等级）几率扣除最大法量的（1.5%*等级）。（仅在与玩家之间战斗有效。）
                ChangeFighting change = new ChangeFighting();
                change.setChangemp(-(int)((double)data.getMp_z() * skill.getSkillhurt() * 1.5 / 4.0 / 100.0));
                data.ChangeData(change, state);
            }
            else if (skill.getSkillid() == 9128) {
                //9128|渔舟唱晚|被昏睡的目标如果在下回合自动解除昏睡（非物理，法术攻击解除），则有（5%+1%*等级）陷入封印状态1回合。（仅在与玩家之间战斗有效。
                if (this.Ongoing < 1) {
                    state.setEndState_1("封印");
                    data.addAddState("封印", skill.getSkillhurt(), 0.0, 1);
                }
            }
            else if (skill.getSkillid() == 9147) {
                //9147|杜鹃啼血|被混乱的目标如果在三回合内摆脱混乱状态，则损失最大血量的（1%*等级）。（仅在与玩家之间战斗有效。）
                if (this.Ongoing < 3) {
                    ChangeFighting change = new ChangeFighting();
                    change.setChangehp(-(int)((double)data.getHp_z() * skill.getSkillhurt() / 100.0));
                    data.ChangeData(change, state);
                }
            }
            else if (skill.getSkillid() == 9351) {
                //9351|梦断魂劳|被遗忘的人物摆脱遗忘后,有20%率进虚弱状态,虚弱状态下的单位受到的伤害增加（10%+2%*等级）,持续2回合。(仅在与玩家之间战斗有效
                data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
            }
            else if (skill.getSkillid() == 9285) {
                //9285|仙云护体|销声匿迹护盾消失时如果人物没有死亡,则随机解除自身的一个异常状态,并提高自身（1%*等级）点冰混睡忘抗性,持续2回合。(仅在与NPC之间战斗有效)
                data.addAddState(skill.getSkilltype(), skill.getSkillhurt(), 0.0, 2);
                data.RemoveNegativeState(state);
            }
        }
    }
    
    public double getStateEffect2() {
        return this.stateEffect2;
    }
    
    public void setStateEffect2(double stateEffect2) {
        this.stateEffect2 = stateEffect2;
    }
    
    public double getStateEffect3() {
        return this.stateEffect3;
    }
    
    public void setStateEffect3(double stateEffect3) {
        this.stateEffect3 = stateEffect3;
    }
    
    public String getStatename() {
        if (this.statename == null) {
            this.statename = "";
        }
        return this.statename;
    }
    
    public void setStatename(String statename) {
        this.statename = statename;
    }
    
    public int getSurplus() {
        return this.Surplus;
    }
    
    public void setSurplus(int surplus) {
        this.Surplus = surplus;
    }
    
    public double getStateEffect() {
        return this.stateEffect;
    }
    
    public void setStateEffect(double stateEffect) {
        this.Ongoing = 0;
        this.stateEffect = stateEffect;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getOngoing() {
        return this.Ongoing;
    }
    
    public void setOngoing(int ongoing) {
        this.Ongoing = ongoing;
    }
    
    public List<FightingSkill> getSkills() {
        return this.skills;
    }
    
    public void setSkills(List<FightingSkill> skills) {
        this.skills = skills;
    }
    
    public void setSkill(FightingSkill skill) {
        if (skill == null) {
            return;
        }
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        this.skills.add(skill);
    }
    
    public FightingSkill getSkill(int id) {
        if (this.skills == null) {
            return null;
        }
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            if (((FightingSkill)this.skills.get(i)).getSkillid() == id) {
                return (FightingSkill)this.skills.get(i);
            }
        }
        return null;
    }
    
    public List<FightingSkill> getSkills(List<Integer> ids) {
        List<FightingSkill> skills = new ArrayList<>();
        if (this.skills == null) {
            return skills;
        }
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            if (ids.contains(((FightingSkill) this.skills.get(i)).getSkillid())) {
                skills.add(this.skills.get(i));
            }
        }
        return skills;
    }
    
    public int getManId() {
        return this.manId;
    }
    
    public void setManId(int manId) {
        this.manId = manId;
    }
    
    public List<FightingSkill> getSkills2() {
        return this.skills2;
    }
    
    public void setSkills2(List<FightingSkill> skills2) {
        this.skills2 = skills2;
    }
    private double stateEffect4;
    private double stateEffect5;
    private double stateEffect6;
    private double stateEffect7;
    public double getStateEffect4() {
        return this.stateEffect4;
    }

    public void setStateEffect4(double stateEffect4) {
        this.stateEffect4 = stateEffect4;
    }

    public double getStateEffect5() {
        return this.stateEffect5;
    }

    public void setStateEffect5(double stateEffect5) {
        this.stateEffect5 = stateEffect5;
    }

    public double getStateEffect6() {
        return this.stateEffect6;
    }

    public void setStateEffect6(double stateEffect6) {
        this.stateEffect6 = stateEffect6;
    }

    public double getStateEffect7() {
        return this.stateEffect7;
    }

    public void setStateEffect7(double stateEffect7) {
        this.stateEffect7 = stateEffect7;
    }
}
