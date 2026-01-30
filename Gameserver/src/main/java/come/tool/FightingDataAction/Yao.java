package come.tool.FightingDataAction;

import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingSkill;
import java.util.List;
import come.tool.Mixdeal.AnalysisString;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.TypeUtil;
import java.util.ArrayList;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Yao implements DataAction
{
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        int camp = -1;
        int man = -1;
        if (fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() != 0) {
            camp = ((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp();
            man = ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan();
        }
        type = fightingEvents.getOriginator().getEndState();
        int nocamp = battlefield.nomy(manData.getCamp());
        int path = battlefield.Datapath(camp, man);
        ManData mdata = null;
        if (path != -1) {
            mdata = (ManData)battlefield.fightingdata.get(path);
            nocamp = battlefield.nomy(mdata.getCamp());
        }
        int fengyin = 0;
        int size = 1;
        if (type.equals("清除异常状态")) {
            fengyin = 1;
        }
        else if (type.equals("破隐")) {
            size = 10;
        }
        else if (type.equals("惊天雷")) {
            size = 5;
        }
        else if (type.equals("愿上钩")) {
            size = 10;
        }
        else if (type.equals("谛听符")) {
            size = 3;
        }
        List<ManData> datas = MixDeal.get(true, mdata, 1, nocamp, 1, 0, fengyin, 0, size, 0, battlefield, 1);
        FightingEvents EVE = new FightingEvents();
        List<FightingState> Accepterlist = new ArrayList<>();
        if (type.equals("隐身")) {
            for (int i = datas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)datas.get(i);
                data.addAddState(type, 0.0, 0.0, 3);
                FightingState yaostate = new FightingState();
                yaostate.setStartState(TypeUtil.JN);
                yaostate.setCamp(data.getCamp());
                yaostate.setMan(data.getMan());
                yaostate.setEndState_1(type);
                Accepterlist.add(yaostate);
            }
        }
        else if (type.equals("破隐")) {
            for (int i = datas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)datas.get(i);
                if (data.deleteState("隐身")) {
                    FightingState yaostate = new FightingState();
                    yaostate.setStartState(TypeUtil.JN);
                    yaostate.setSkillskin("80000");//火眼特效
                    yaostate.setCamp(data.getCamp());
                    yaostate.setMan(data.getMan());
                    yaostate.setEndState_2("隐身");
                    Accepterlist.add(yaostate);
                }
            }
        }
        else if (type.equals("清除异常状态")) {
            for (int i = datas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)datas.get(i);
                if (50 > Battlefield.random.nextInt(100) && data.deleteState(type)) {
                    FightingState yaostate = new FightingState();
                    yaostate.setStartState(TypeUtil.JN);
                    yaostate.setSkillskin("18499");//解药特效
                    yaostate.setCamp(data.getCamp());
                    yaostate.setMan(data.getMan());
                    yaostate.setEndState_2(type);
                    Accepterlist.add(yaostate);
                }
            }
        }
        else if (type.equals("惊天雷")) {
            for (int i = datas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)datas.get(i);
                if (data.getType() == 2 && (data.getId() == 5391 || data.getId() == 5392 || data.getId() == 5393)) {
                    ChangeFighting changeFighting = new ChangeFighting();
                    changeFighting.setChangehp(-100000);
                    FightingState yaostate2 = new FightingState();
                    yaostate2.setStartState(TypeUtil.JN);
                    data.ChangeData(changeFighting, yaostate2);
                    yaostate2.setSkillskin("1056");
                    Accepterlist.add(yaostate2);
                }
            }
        }
        else if (type.equals("愿上钩") || type.equals("弥天网")) {
            for (int i = datas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)datas.get(i);
                if (data.getType() == 2 && (data.getId() == 5386 || data.getId() == 5387 || data.getId() == 5388 || data.getId() == 5389 || data.getId() == 5390 || data.getId() == 5391 || data.getId() == 5392 || data.getId() == 5393)) {
                    ChangeFighting changeFighting = new ChangeFighting();
                    changeFighting.setChangehp(-50000);
                    FightingState yaostate2 = new FightingState();
                    yaostate2.setStartState(TypeUtil.JN);
                    data.ChangeData(changeFighting, yaostate2);
                    Accepterlist.add(yaostate2);
                }
            }
        }
        else if (type.equals("谛听符")) {
            for (int i = datas.size() - 1; i >= 0; --i) {
                ManData data = (ManData)datas.get(i);
                if (data.getType() == 2 && data.getId() == 5420) {
                    ChangeFighting changeFighting = new ChangeFighting();
                    changeFighting.setChangehp(-50000);
                    changeFighting.setChangetype("混乱");
                    changeFighting.setChangevlaue(2.0);
                    FightingState yaostate2 = new FightingState();
                    yaostate2.setStartState(TypeUtil.JN);
                    data.ChangeData(changeFighting, yaostate2);
                    Accepterlist.add(yaostate2);
                }
            }
        }
        else {
            int[] yao = AnalysisString.yao(fightingEvents.getOriginator().getEndState());
            if (manData.getSkillType(TypeUtil.TJ_SN) != null) {
                if (yao[2] != 0) {
                    int n = 2;
                    yao[n] += 10;
                }
                if (yao[3] != 0) {
                    int n2 = 3;
                    yao[n2] += 10;
                }
            }
            FightingSkill skill = manData.getSkillType(TypeUtil.TJ_FBYG);
            for (int j = datas.size() - 1; j >= 0; --j) {
                ManData data2 = (ManData)datas.get(j);
                if (data2.getStates() != 1 || yao[0] != 0 || yao[2] != 0) {
                    ChangeFighting changeFighting2 = battlefield.Typeyao(data2, yao);
                    FightingState yaostate3 = new FightingState();
                    yaostate3.setStartState("药");
                    data2.ChangeData(changeFighting2, yaostate3);
                    Accepterlist.add(yaostate3);
                    AddState addState = data2.xzstate(TypeUtil.TZ_CXYF);
                    if (addState != null) {
                        int[] yao2 = (int[])yao.clone();
                        yao2[1] = 0;
                        yao2[yao2[3] = 0] = (int)((double)yao2[0] * addState.getStateEffect() / 100.0);
                        yao2[2] = (int)((double)yao2[2] * addState.getStateEffect() / 100.0);
                        if (yao2[0] == 0 && yao2[2] == 0) {
                            continue;
                        }
                        else {
                            List<ManData> datas2 = MixDeal.get(false, mdata, 1, nocamp, 1, 3, 1, 1, 5, 0, battlefield, 1);
                            for (int k = 0; k < datas2.size(); ++k) {
                                ManData data3 = (ManData)datas.get(k);
                                changeFighting2 = battlefield.Typeyao(data3, yao);
                                FightingState yaostate4 = new FightingState();
                                yaostate4.setStartState("药");
                                data3.ChangeData(changeFighting2, yaostate4);
                                Accepterlist.add(yaostate4);
                            }
                            yaostate3.setEndState_2(addState.getStatename());
                            data2.getAddStates().remove(addState);
                        }
                    }
                    if (skill != null) {
                        changeFighting2.setChangehp((int)((double)changeFighting2.getChangehp() * 0.2));
                        changeFighting2.setChangemp((int)((double)changeFighting2.getChangemp() * 0.2));
                        FightingState myao = new FightingState();
                        myao.setStartState("药");
                        manData.ChangeData(changeFighting2, myao);
                        Accepterlist.add(myao);
                    }
                }
            }
            int[] tempYao = { yao[0], 0, yao[2], 0 };
            if ((yao[0] > 0 || yao[2] > 0) && manData.executeSxtg()) {
                List<ManData> ds = MixDeal.get(true, mdata, 2, nocamp, 1, 0, fengyin, 0, size, 0, battlefield, 1);
                if (ds.size() > 0) {
                    ManData data4 = (ManData)ds.get(0);
                    int p = battlefield.Datapath(data4.getCamp(), data4.getMan() + 5);
                    if (p != -1) {
                        ChangeFighting changeFighting3 = battlefield.Typeyao(data4, tempYao);
                        FightingState yaostate5 = new FightingState();
                        yaostate5.setStartState("药");
                        data4.ChangeData(changeFighting3, yaostate5);
                        Accepterlist.add(yaostate5);
                        FightingState say = new FightingState();
                        say.setCamp(manData.getCamp());
                        say.setMan(manData.getMan());
                        say.setText("看我的上下天光");
                        Accepterlist.add(say);
                    }
                }
            }
            int[] tempYao2 = { 0, yao[1], 0, yao[3] };
            if ((yao[1] > 0 || yao[3] > 0) && manData.executeZyfy()) {
                List<ManData> ds2 = MixDeal.get(true, mdata, 2, nocamp, 1, 0, fengyin, 0, size, 0, battlefield, 1);
                if (ds2.size() > 0) {
                    ManData data5 = (ManData)ds2.get(0);
                    int p2 = battlefield.Datapath(data5.getCamp(), data5.getMan() + 5);
                    if (p2 != -1) {
                        ChangeFighting changeFighting4 = battlefield.Typeyao(data5, tempYao2);
                        FightingState yaostate6 = new FightingState();
                        yaostate6.setStartState("药");
                        data5.ChangeData(changeFighting4, yaostate6);
                        Accepterlist.add(yaostate6);
                        FightingState say2 = new FightingState();
                        say2.setCamp(manData.getCamp());
                        say2.setMan(manData.getMan());
                        say2.setText("看我的左右逢源");
                        Accepterlist.add(say2);
                    }
                }
            }
        }
        if (Accepterlist.size() != 0) {
            EVE.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(EVE);
        }
    }
}
