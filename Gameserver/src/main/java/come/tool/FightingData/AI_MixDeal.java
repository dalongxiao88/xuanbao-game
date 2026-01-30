package come.tool.FightingData;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import java.util.List;

public class AI_MixDeal
{
    /**封装的ai条件判断*/
    public static boolean isAIFull(List<AICondition> list, Battlefield battlefield, ManData data, AI ai) {
        if (list == null) {
            return true;
        }
         for (int i = list.size() - 1; i >= 0; i--) {
            AICondition point = list.get(i);
            if (point.getX() == AI.AI_BILITY) {
                if (point.getY() < Battlefield.random.nextInt(100)) {
                    return false;
                }
            } else if (point.getX() == AI.AI_ROUND) {
                if (StringUtils.isNotBlank(point.getSy()) && point.getSy().startsWith("X")) {
                    String[] v = point.getSy().substring(1).split("=");
                    int v1 = Integer.parseInt(v[0]);
                    int v2 = Integer.parseInt(v[1]);
                    int i1 = battlefield.CurrentRound % v1;
                    if(i1 == v2) {
                        return true;
                    } else return  false;
                } else if (battlefield.CurrentRound < point.getY()) {
                    return false;
                }
            } else if (point.getX() == AI.AI_ZD_ROUND) {
                if (battlefield.CurrentRound == point.getY()) {
                    return true;
                } else return false;
            } else if (point.getX() == AI.AI_DEATH) {
                ManData data2 = battlefield.getMonster(point.getY());
                if (data2 != null && data2.getStates() == 0) {
                    return false;
                }
            } else if (point.getX() == AI.AI_NODEATH) {
                ManData data2 = battlefield.getMonster(point.getY());
                if (data2 != null && !(data2.getStates() == 0)) {
                    return false;
                }
            } else if (point.getX() == AI.AI_SKILL) {
                if (battlefield.JLSkills.get(point.getY()) == null) {
                    return false;
                }
            } else if (point.getX() == AI.AI_BEARSKILL) {
                ManData data2 = data;
                if (point.getY() != 0) {
                    data2 = battlefield.getMonster(point.getY());
                }
                if (data2 == null || !data2.isBear(point.getSy())) {
                    return false;
                }
            } else if (point.getX() == AI.AI_ZDBEARSKILL) {
                ManData data2 = data;
                if (point.getY() != 0) {
                    data2 = battlefield.getMonster(point.getY());
                }
                for (FightingEvents newEvent : battlefield.NewEvents) {
                    List<FightingState> accepterlist = newEvent.getAccepterlist();
                    for (FightingState fightingState : accepterlist) {
                        if (StringUtils.isNotBlank(fightingState.getStartState()) && fightingState.getStartState().equals("法术攻击")
                                && fightingState.getCamp() != data2.getCamp() && fightingState.getEndState().equals(point.getSy())) {
                            ai.setState(point.getSy());
                            return true;
                        }
                    }
                }
                return false;

            } else if (point.getX() == AI.AI_CARRYSTATE) {


                ManData data2 = data;
                if (point.getY() != 0) {
                    data2 = battlefield.getMonster(point.getY());
                }
                if (data2 == null || data2.xzstate(point.getSy()) == null) {
                    return false;
                }
            } else if (point.getX() == AI.AI_TYPE_ZH) {

                if (battlefield.CurrentRound == point.getY() || (point.getB() && battlefield.CurrentRound == point.getY() + 1)) {
                    return true;
                } else return false;
            }
        }
        return true;
    }
    //指令ai判断
    public static AI AI_ZL(Battlefield battlefield, ManData data) {
        if (data.getAis() == null) {
            return null;
        }
        if (data.getStates() != 0) {
            return null;
        }
        for (int i = data.getAis().size() - 1; i >= 0; --i) {
            AI ai = (AI)data.getAis().get(i);
            if (ai.isAI(1) && isAIFull(ai.getAiConditions(), battlefield, data, ai)) {
                 return ai;
            }
        }
        return null;
    }
    //回合开始判断
    public static void AI_KS(Battlefield battlefield) {
        for (int i = battlefield.fightingdata.size() - 1; i >= 0; i--) {
            ManData data = battlefield.fightingdata.get(i);
            if (data.getType() != 2 || data.getAis() == null) {
                continue;
            }
            if (data.getStates() != 0) {
                continue;
            }
            for (int j = data.getAis().size() - 1; j >= 0; j--) {
                AI ai = data.getAis().get(j);
                if (ai.isAI(2)) {
                    if (isAIFull(ai.getAiConditions(), battlefield, data, ai)) {
                        int type = 2003;
                        if (type == AI.AI_TYPE_VIOLENT_PHY) {//物理狂暴 3分2追
                            if (data.xzstate("物理狂暴") == null) {
                                data.setSp(data.getSp2() + 600);
                                data.getZDSKILL(1831, 50000000);
                                data.getZDSKILL(1833, 50000000);
                                data.addAddState("物理狂暴", 0, 0, ai.getMan());
                            }
                        } else if (type == AI.AI_TYPE_VIOLENT_SKILL) {//法术狂暴
                            if (data.xzstate("仙法狂暴") == null) {
                                data.setSp(data.getSp2() + 600);
                                data.getQuality().kuangbao(1);
                                data.addAddState("仙法狂暴", 0, 0, ai.getMan());
                            }
                        } else if (type == AI.AI_TYPE_STATE_START) {//回合开始附加状态
                            if (ai != null && ai.getType() == AI.AI_FBBZ) {
                                FightingSkill skill = data.getSkillId(ai.getMan());
                                if (skill != null) {
                                    data.getSkills().remove(skill);
                                    skill.setSkillhurt(ai.getValue());
                                    data.getSkills().add(skill);
                                }

                            } else if (ai != null && ai.getType() == AI.AI_PCYS) {
                                List<FightingState> States = null;
                                GroupBuff jjys = battlefield.getBuff(data.getCamp(), "JJYS");
                                if (jjys == null)
                                    battlefield.addBuff(new GroupBuff(99999999, "JJYS", data, 99999));
                                for (int k = battlefield.fightingdata.size() - 1; k >= 0; k--) {
                                    ManData manData = battlefield.fightingdata.get(k);
                                    if (manData.getStates() == 0 && manData.getCamp() != data.getCamp() && manData.getMan() < 10) {
                                        AddState addState = manData.xzstate("隐身");
                                        if (addState != null) {
                                            manData.getAddStates().remove(addState);
                                            FightingState org = new FightingState();
                                            org.setCamp(manData.getCamp());
                                            org.setMan(manData.getMan());
                                            org.setStartState("代价");
                                            org.setEndState_2("隐身");
                                            if (States == null) {
                                                States = new ArrayList<>();
                                                FightingState org1 = new FightingState();
                                                org1.setCamp(data.getCamp());
                                                org1.setMan(data.getMan());
                                                org1.setStartState("代价");
                                                org1.setText("扫除一切迷雾#24");
                                                States.add(org1);
                                            }
                                            States.add(org);
                                        }
                                    }
                                }
                                if (States != null) {

                                    FightingEvents fightingEvents = new FightingEvents();
                                    fightingEvents.setAccepterlist(States);
                                    battlefield.NewEvents.add(fightingEvents);
                                }
                            } else if (ai != null && ai.getType() == AI.AI_SUBSTATE) {
                                if (data.getStates() == 0 && ai.getMan() == 1509) {
                                    for (ManData fightingdatum : battlefield.fightingdata) {
                                        AddState npsub = fightingdatum.xzstate("npsub");
                                        if (fightingdatum.getType() == 1 && npsub == null) {
                                            AddState addState = new AddState();
                                            addState.setStatename("npsub");
                                            addState.setType(1);
                                            addState.setStateEffect(ai.getValue());
                                            addState.setSurplus(99999);
                                            fightingdatum.getAddStates().add(addState);
                                        }

                                    }
                                } else if (ai.getMan() == 1806) {
                                    for (ManData fightingdatum : battlefield.fightingdata) {
                                        AddState npsub = fightingdatum.xzstate("ssjv");
                                        if (fightingdatum.getType() == 1 && npsub == null) {
                                            AddState addState = new AddState();
                                            addState.setStatename("ssjv");
                                            addState.setType(1);
                                            addState.setStateEffect(ai.getValue());
                                            addState.setSurplus(99999);
                                            fightingdatum.getAddStates().add(addState);
                                        }

                                    }
                                } else if (ai.getMan() == 99999) {
                                    for (ManData fightingdatum : battlefield.fightingdata) {
                                        AddState npsub = fightingdatum.xzstate("xzzh");
                                        if (fightingdatum.getType() == 0 && npsub == null) {
                                            AddState addState = new AddState();
                                            addState.setStatename("xzzh");
                                            addState.setType(1);
                                            addState.setStateEffect(ai.getValue());
//                                            addState.setStateEffect(data.getMan());
                                            addState.setStateEffect2(0d);
                                            addState.setSurplus(99999);
                                            fightingdatum.getAddStates().add(addState);
                                        }

                                    }
//                                    data.getAis().remove(j);
                                }
                            } else {
                                if (data.addAddState(ai.getState(), 0, 0, ai.getValue())) {
                                    FightingEvents fightingEvents = new FightingEvents();
                                    FightingState org = new FightingState();
                                    org.setCamp(data.getCamp());
                                    org.setMan(data.getMan());
                                    org.setStartState("代价");
                                    org.setEndState_1(ai.getState());
                                    List<FightingState> States = new ArrayList<>();
                                    States.add(org);
                                    fightingEvents.setAccepterlist(States);
                                    battlefield.NewEvents.add(fightingEvents);
                                }
                                ai.setMan(ai.getMan() - 1);
                                if (ai.getMan() <= 0) {
                                    data.getAis().remove(j);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**出手结束附加状态*/
    public static void AI_End(Battlefield battlefield, ManData data) {
        if (data == null) {
            return;
        }
        if (data.getAis() == null) {
            return;
        }
        if (data.getStates() != 0) {
            return;
        }
        int i = data.getAis().size() - 1;
        while (i >= 0) {
            AI ai = (AI)data.getAis().get(i);
            if (ai.isAI(3) && isAIFull(ai.getAiConditions(), battlefield, data, ai)) {
                if (data.addAddState(ai.getState(), 0.0, 0.0, ai.getValue())) {
                    FightingEvents fightingEvents = new FightingEvents();
                    FightingState org = new FightingState();
                    org.setCamp(data.getCamp());
                    org.setMan(data.getMan());
                    org.setStartState("代价");
                    org.setEndState_1(ai.getState());
                    List<FightingState> States = new ArrayList<>();
                    States.add(org);
                    fightingEvents.setAccepterlist(States);
                    battlefield.NewEvents.add(fightingEvents);
                }
                ai.setMan(ai.getMan() - 1);
                if (ai.getMan() <= 0) {
                    data.getAis().remove(i);
                }
                return;
            }
            else {
                --i;
            }
        }
    }
    /**死亡处理*/
    public static void AI_Die(Battlefield battlefield, ManData data) {
        if (data.getAis() == null) {
            return;
        }
        if (data.getStates() != 0) {
            return;
        }
        for (int i = data.getAis().size() - 1; i >= 0; --i) {
            AI ai = (AI)data.getAis().get(i);
            if (ai.isAI(4) && isAIFull(ai.getAiConditions(), battlefield, data, ai)) {
                return;
            }
        }
    }
}
