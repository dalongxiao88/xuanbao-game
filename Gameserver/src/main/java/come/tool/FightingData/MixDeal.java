package come.tool.FightingData;

import java.util.Collections;
import come.tool.FightingDataAction.PhyAttack;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.tool.Arith;
import come.tool.FightingSpellAction.SpellActionType;
import com.gl.util.FaMenUtil;
import java.util.Iterator;
import java.util.Arrays;
import org.come.bean.PathPoint;
import java.util.List;
import java.util.ArrayList;

import static come.tool.FightingData.Battlefield.isV;

public class MixDeal
{
    public static ManData ZYJL;
    public static String KAN1;
    public static String KAN2;
    static String FG;
    static String DH;
    
    public static int disposetype(String type) {
        if (type.equals(TypeUtil.PTGJ)) {
            return 1;
        }
        if (type.equals(TypeUtil.JN)) {
            return 2;
        }
        if (type.equals("逃跑")) {
            return 3;
        }
        if (type.equals("捕捉")) {
            return 7;
        }
        if (type.equals("药")) {
            return 4;
        }
        if (type.split("\\&")[0].equals("召唤")) {
            return 5;
        }
        if (type.equals("召回")) {
            return 5;
        }
        if (type.equals("闪现")) {
            return 5;
        }
        return 0;
    }
    
    public static void move(int camp, int man, String type, String path, Battlefield battlefield) {
        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> moves = new ArrayList<>();
        FightingState move = new FightingState();
        move.setCamp(camp);
        move.setMan(man);
        move.setStartState(type);
        move.setEndState(path);
        moves.add(move);
        moveEvents.setAccepterlist(moves);
        battlefield.NewEvents.add(moveEvents);
    }
    
    public static void move(int camp, int man, String type, String path, Battlefield battlefield, FightingEvents moveEvents) {
        List<FightingState> moves = new ArrayList<>();
        FightingState move = new FightingState();
        move.setCamp(camp);
        move.setMan(man);
        move.setStartState(type);
        move.setEndState(path);
        moves.add(move);
        moveEvents.setAccepterlist(moves);
        battlefield.NewEvents.add(moveEvents);
    }
    
    public static List<ManData> getjieshou(FightingEvents fightingEvents, FightingSkill fightingSkill, ManData myData, List<FightingState> Accepterlist, Battlefield battlefield, Boolean b, int ren) {
        return getjieshou(fightingEvents, fightingSkill, myData, Accepterlist, battlefield, Long.valueOf(1L), ren);
    }
    
    public static List<ManData> getjieshou(FightingEvents fightingEvents, FightingSkill fightingSkill, ManData myData, List<FightingState> Accepterlist, Battlefield battlefield) {
        return getjieshou(fightingEvents, fightingSkill, myData, Accepterlist, battlefield, 0);
    }
    
    public static List<ManData> getjieshou(FightingEvents fightingEvents, FightingSkill fightingSkill, ManData myData, List<FightingState> Accepterlist, Battlefield battlefield, Long l, int ren) {
        int sum = 9;
        String type = fightingSkill.getSkilltype();
        if (getItself(type) == 1) {
            List<ManData> datas = new ArrayList<>();
            if (myData != null) {
                datas.add(myData);
            }
            return datas;
        }
        else {
            int MyCamp = myData.getCamp();
            int NoCamp = battlefield.nomy(myData.getCamp());
            int death = gethuo(type);
            int camp = getcamp(type, MyCamp, NoCamp);
            int yin = getyin(type);
            int fengyin = getfengyin(type);
            if (type.equals("五行") && myData.executeBxwj()) {
                death = 1;
                fengyin = 1;
            }
            ManData data = null;
            if (fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() != 0) {
                int path = -1;
                if (death == 0) {
                    path = battlefield.Datapathhuo(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
                }
                else {
                    path = battlefield.Datapath(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
                }
                if (path != -1) {
                    data = (ManData)battlefield.fightingdata.get(path);
                }
            }
            if (sum == 0) {
                sum = fightingSkill.getSkillsum();
            }
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                if (sum > 1 && fightingSkill.getSkillid() >= 1001 && fightingSkill.getSkillid() <= 1100) {
                    AddState addState = myData.xzstate(TypeUtil.TY_F_YYFQ_S);
                    if (addState != null) {
                        --sum;
                    }
                }
            }
            else if (sum > 1 && type.equals("中毒") && myData.getSkillType(TypeUtil.TY_ZD_BYHY) != null) {
                sum -= 2;
            }
            if (fightingSkill.getSkillid() >= 1001 && fightingSkill.getSkillid() <= 1100) {
                if (Accepterlist != null) {
                    ManData child = battlefield.getChild(myData);
                    if (child != null) {
                        String a = null;
                        if (fightingSkill.getSkilltype().equals("震慑") || fightingSkill.getSkilltype().equals("三尸虫")) {
                            a = "强" + fightingSkill.getSkilltype();
                        }
                        else if (fightingSkill.getSkilltype().equals("甘霖")) {
                            a = "甘霖回血";
                        }
                        else if (fightingSkill.getSkilltype().equals("霹雳")) {
                            a = "霹雳连击";
                        }
                        else {
                            a = "忽视" + fightingSkill.getSkilltype();
                        }
                        FightingSkill skill = child.getChildSkill(a);
                        if (skill != null && (fightingSkill.getSkilltype().equals("震慑") || fightingSkill.getSkilllvl() != 4)) {
                            Accepterlist.add(getChildSkill(child, skill.getSkillname()));
                            sum = (int)((double)sum + skill.getSkillhurt());
                            ChangeFighting changeFighting = new ChangeFighting();
                            changeFighting.setChangesum(skill.getSkillcontinued());
                            changeFighting.setChangetype(skill.getSkilltype());
                            changeFighting.setChangevlaue(skill.getSkillgain());
                            myData.ChangeData(changeFighting, new FightingState());
                        }
                    }
                    if (getFS(battlefield, Accepterlist, myData, NoCamp)) {
                        camp = battlefield.nomy(camp);
                    }
                    if (getTGBG(battlefield, Accepterlist, myData, NoCamp)) {
                        camp = battlefield.nomy(camp);
                    }
                }
                if (type.equals("风") || type.equals("雷") || type.equals("水") || type.equals("火") || type.equals("鬼火")) {
                    int hs = 1;
                    FightingSkill skill2 = myData.getSkillType(TypeUtil.TZ_YDFC);
                    if (skill2 != null) {
                        hs = 0;
                    }
                    return get(true, data, death, camp, yin, ren, fengyin, 0, sum, fightingSkill.getCamp(), battlefield, hs);
                }
                else if (type.equals("沧波") || type.equals("扶摇")) {
                    AddState addState = myData.xzstate(TypeUtil.LL);
                    if (addState != null) {
                        sum += 2;
                    }
                }
                else if (type.equals("甘霖")) {
                    AddState addState = myData.xzstate(TypeUtil.LL);
                    if (addState != null) {
                        death = 1;
                    }
                }
            }
            else if (type.equals("解除控制")) {
                FightingSkill skill3 = myData.getSkillType(TypeUtil.BB_E_QHFYCX);
                if (skill3 != null && Battlefield.isV(skill3.getSkillgain())) {
                    ++sum;
                    fightingEvents.getOriginator().setText("强化梵音初晓#2");
                }
            }
            else if (type.equals("魔界内丹")) {
                FightingSkill skill3 = myData.getSkillType(TypeUtil.BB_LSDC);
                if (skill3 != null) {
                    sum += skill3.getSkillsum();
                    fightingEvents.getOriginator().setText("利涉大川#2");
                }
            }
            return get(true, data, death, camp, yin, ren, fengyin, 0, sum, fightingSkill.getCamp(), battlefield, 1);
        }
    }
    
    public static List<ManData> getjieshou(FightingEvents fightingEvents, FightingSkill fightingSkill, ManData myData, List<FightingState> Accepterlist, Battlefield battlefield, int sum) {
        String type = fightingSkill.getSkilltype();
        if (getItself(type) == 1) {
            List<ManData> datas = new ArrayList<>();
            if (myData != null) {
                datas.add(myData);
            }
            return datas;
        }
        else {
            int MyCamp = myData.getCamp();
            int NoCamp = battlefield.nomy(myData.getCamp());
            int death = gethuo(type);
            int camp = getcamp(type, MyCamp, NoCamp);
            int yin = getyin(type);
            int ren = getren(type);
            int fengyin = getfengyin(type);
            if (type.equals("五行") && myData.executeBxwj()) {
                death = 1;
                fengyin = 1;
            }
            ManData data = null;
            if (fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() != 0) {
                int path = -1;
                if (death == 0) {
                    path = battlefield.Datapathhuo(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
                }
                else {
                    path = battlefield.Datapath(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
                }
                if (path != -1) {
                    data = (ManData)battlefield.fightingdata.get(path);
                }
            }
            if (sum == 0) {
                sum = fightingSkill.getSkillsum();
            }
            if (PK_MixDeal.isPK(battlefield.BattleType)) {
                if (sum > 1 && fightingSkill.getSkillid() >= 1001 && fightingSkill.getSkillid() <= 1100) {
                    AddState addState = myData.xzstate(TypeUtil.TY_F_YYFQ_S);
                    if (addState != null) {
                        --sum;
                    }
                }
            }
            else if (sum > 1 && type.equals("中毒") && myData.getSkillType(TypeUtil.TY_ZD_BYHY) != null) {
                sum -= 2;
            }
            if (fightingSkill.getSkillid() >= 1001 && fightingSkill.getSkillid() <= 1100) {
                if (Accepterlist != null) {
                    ManData child = battlefield.getChild(myData);
                    if (child != null) {
                        String a = null;
                        if (fightingSkill.getSkilltype().equals("震慑") || fightingSkill.getSkilltype().equals("三尸虫")) {
                            a = "强" + fightingSkill.getSkilltype();
                        }
                        else if (fightingSkill.getSkilltype().equals("甘霖")) {
                            a = "甘霖回血";
                        }
                        else if (fightingSkill.getSkilltype().equals("霹雳")) {
                            a = "霹雳连击";
                        }
                        else {
                            a = "忽视" + fightingSkill.getSkilltype();
                        }
                        FightingSkill skill = child.getChildSkill(a);
                        if (skill != null && (fightingSkill.getSkilltype().equals("震慑") || fightingSkill.getSkilllvl() != 4)) {
                            Accepterlist.add(getChildSkill(child, skill.getSkillname()));
                            sum = (int)((double)sum + skill.getSkillhurt());
                            ChangeFighting changeFighting = new ChangeFighting();
                            changeFighting.setChangesum(skill.getSkillcontinued());
                            changeFighting.setChangetype(skill.getSkilltype());
                            changeFighting.setChangevlaue(skill.getSkillgain());
                            myData.ChangeData(changeFighting, new FightingState());
                        }
                    }
                    if (getFS(battlefield, Accepterlist, myData, NoCamp)) {
                        camp = battlefield.nomy(camp);
                    }
                    if (getTGBG(battlefield, Accepterlist, myData, NoCamp)) {
                        camp = battlefield.nomy(camp);
                    }
                    if (type.equals("smmh")) {
                        System.out.println(data.getCamp());
                        return getmh(true, data, death, data.getCamp()==1?2:1, yin, ren, fengyin, 0, sum, fightingSkill.getCamp(), battlefield, 1);
                    }
                }
                if (type.equals("风") || type.equals("雷") || type.equals("水") || type.equals("火") || type.equals("鬼火")) {
                    int hs = 1;
                    FightingSkill skill2 = myData.getSkillType(TypeUtil.TZ_YDFC);
                    if (skill2 != null) {
                        hs = 0;
                    }
                    return get(true, data, death, camp, yin, ren, fengyin, 0, sum, fightingSkill.getCamp(), battlefield, hs);
                }
                else {
                    if (type.equals("沧波") || type.equals("扶摇")) {
                        AddState addState = myData.xzstate(TypeUtil.LL);
                        if (addState != null) {
                            sum += 2;
                        }
                    }
                    else if (type.equals("甘霖")) {
                        AddState addState = myData.xzstate(TypeUtil.LL);
                        if (addState != null) {
                            death = 1;
                        }
                    }
                    if (fightingSkill.getSkillid() == 1038) {
                        FightingSkill skill3 = myData.getSkillType(TypeUtil.TY_JS_BGSS);
                        if (skill3 != null && Battlefield.isV(skill3.getSkillhurt())) {
                            ++sum;
                        }
                    }
                }
            }
            else if (type.equals("解除控制")) {
                FightingSkill skill3 = myData.getSkillType(TypeUtil.BB_E_QHFYCX);
                if (skill3 != null && Battlefield.isV(skill3.getSkillgain())) {
                    ++sum;
                    fightingEvents.getOriginator().setText("强化梵音初晓#2");
                }
            }
            else if (type.equals("魔界内丹")) {
                FightingSkill skill3 = myData.getSkillType(TypeUtil.BB_LSDC);
                if (skill3 != null) {
                    sum += skill3.getSkillsum();
                    fightingEvents.getOriginator().setText("利涉大川#2");
                }
            }
            else if (type.equals("凝神一击")) {
                List<ManData> manData = get(true, data, death, camp, yin, 3, fengyin, 0, sum, fightingSkill.getCamp(), battlefield, 1);
                if (manData.size() < sum) {
                    sum -= manData.size();
                    manData.addAll(get(true, data, death, camp, yin, 2, fengyin, 0, sum, fightingSkill.getCamp(), battlefield, 1));
                }
                return manData;
            }
//            else if (type.equals("smmh")) {
//                return get(true, data, death, data.getCamp()==1?2:1, yin, ren, fengyin, 0, sum, fightingSkill.getCamp(), battlefield, 1);
//            }
            List<ManData> dataList = get(true, data, death, camp, yin, ren, fengyin, 0, sum, fightingSkill.getCamp(), battlefield, 1);
            addMainTarget(fightingEvents, dataList, myData, fightingSkill, battlefield, sum);
            return dataList;
        }
    }
    
    public static ManData getjieshou(FightingSkill fightingSkill, ManData myData, List<ManData> Accepterlist, Battlefield battlefield) {
        int death = 0;
        int nocamp = myData.getCamp();
        int yin = 0;
        int ren = 0;
        int fengyin = 0;
        int hs = 1;
        FightingSkill skill = myData.getSkillType(TypeUtil.TZ_YDFC);
        if (skill != null) {
            hs = 0;
        }
        PathPoint point = new PathPoint(-1, -1);
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.zuoyong(death, nocamp, yin, ren, point, fengyin, 0, hs) && !Accepterlist.contains(data)) {
                return data;
            }
        }
        return null;
    }
    
    public static void addMainTarget(FightingEvents fightingEvents, List<ManData> datas, ManData manData, FightingSkill skill, Battlefield battlefield, int size) {
        if (skill.getSkillid() == 1030 || skill.getSkillid() == 1035 || skill.getSkillid() == 1040) {
            FightingSkill TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_JFZL_GP);
            if (TY_RH_DDYS_SS == null) {
                TY_RH_DDYS_SS = manData.getSkillType(TypeUtil.TY_RH_JFZL_SP);
            }
            if (TY_RH_DDYS_SS != null && Battlefield.isV(TY_RH_DDYS_SS.getSkillgain())) {
                int path = battlefield.Datapath(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
                if (path != -1) {
                    ManData data = (ManData)battlefield.fightingdata.get(path);
                    if (!datas.contains(data)) {
                        datas.add(0, data);
                    }
                    if (datas.size() > size) {
                        datas.remove(datas.size() - 1);
                    }
                }
            }
        }
    }
    
    public static List<ManData> get(boolean l, ManData data, int death, int nocamp, int yin, int ren, int fengyin, int yao, int size, int type, Battlefield battlefield, int hs) {
        PathPoint point = new PathPoint(-1, -1);
        List<ManData> datas = new ArrayList<>();
        if (data != null) {
            int camp = data.getCamp();
            int man = data.getMan();
            if (l) {
                if (!data.zuoyong(death, nocamp, yin, ren, point, fengyin, yao, hs) && data.xzstate("无敌") == null) {
                    data = null;
                }
            }
            else {
                data = null;
            }
            point.setX(camp);
            point.setY(man);
        }
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            if (((ManData)battlefield.fightingdata.get(i)).xzstate("无敌") != null) {
                if (Battlefield.currZl == null || Battlefield.currZl.getOriginator() == null || ((ManData)battlefield.fightingdata.get(i)).getCamp() != Battlefield.currZl.getOriginator().getCamp()) {
                    if (data != null) {
                        data = null;
                    }
                }
                else if (((ManData)battlefield.fightingdata.get(i)).zuoyong1(death, nocamp, yin, ren, point, fengyin, yao, hs) && ((ManData)battlefield.fightingdata.get(i)).getCamp() == Battlefield.currZl.getOriginator().getCamp()) {
                    datas.add(battlefield.fightingdata.get(i));
                }
            }
            else if (((ManData)battlefield.fightingdata.get(i)).zuoyong(death, nocamp, yin, ren, point, fengyin, yao, hs)) {
                datas.add(battlefield.fightingdata.get(i));
            }
        }
        if (type == -1) {
            type = Battlefield.random.nextInt(8);
        }
        boolean a = false;
        if (type % 2 == 0) {
            a = true;
        }
        for (int j = 0; j < datas.size(); ++j) {
            for (int k = 1; k < datas.size(); ++k) {
                ManData data2 = (ManData)datas.get(k - 1);
                double value1 = data2.getvalue(type);
                ManData data3 = (ManData)datas.get(k);
                double value2 = data3.getvalue(type);
                if ((a && value1 < value2) || (!a && value1 > value2)) {
                    datas.set(k - 1, data3);
                    datas.set(k, data2);
                }
            }
        }
        for (int j = datas.size() - 1; j >= 0 && datas.size() > size; --j) {
            datas.remove(j);
        }
        if (data != null) {
            if (datas.size() != 0 && datas.size() >= size) {
                datas.remove(datas.size() - 1);
            }
            datas.add(0, data);
        }
        return datas;
    }
    public static List<ManData> getmh(boolean l, ManData data, int death, int nocamp, int yin, int ren, int fengyin, int yao, int size, int type, Battlefield battlefield, int hs) {
        PathPoint point = new PathPoint(-1, -1);
        List<ManData> datas = new ArrayList<>();
        if (data != null) {
            int camp = data.getCamp()==1?2:1;
            int man = data.getMan();

            if (l) {
                if (!data.zuoyong(death, camp, yin, ren, point, fengyin, yao, hs) && data.xzstate("无敌") == null) {
                    data = null;
                }
            }
            else {
                data = null;
            }
            point.setX(camp);
            point.setY(man);
        }
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            if (((ManData)battlefield.fightingdata.get(i)).xzstate("无敌") != null) {
                if (Battlefield.currZl == null || Battlefield.currZl.getOriginator() == null || ((ManData)battlefield.fightingdata.get(i)).getCamp() != Battlefield.currZl.getOriginator().getCamp()) {
                    if (data != null) {
                        data = null;
                    }
                }
                else if (!((ManData)battlefield.fightingdata.get(i)).zuoyong2(death, nocamp==1?2:1, yin, ren, point, fengyin, yao, hs) && ((ManData)battlefield.fightingdata.get(i)).getCamp() == Battlefield.currZl.getOriginator().getCamp()) {
                    datas.add(battlefield.fightingdata.get(i));
                }
            }
            else {
                if (data != null && data.getCamp() != nocamp) {
                    if (!((ManData)battlefield.fightingdata.get(i)).zuoyong0(death, data.getCamp(), yin, ren, point, fengyin, yao, hs)) {
                        datas.add(battlefield.fightingdata.get(i));
                    }
                }else {
                    if (((ManData)battlefield.fightingdata.get(i)).zuoyong0(death, nocamp==1?2:1, yin, ren, point, fengyin, yao, hs)) {
                        datas.add(battlefield.fightingdata.get(i));
                    }
                }
            }

        }
        if (type == -1) {
            type = Battlefield.random.nextInt(8);
        }
        boolean a = false;
        if (type % 2 == 0) {
            a = true;
        }
        for (int j = 0; j < datas.size(); ++j) {
            if (datas.get(j).getType()==3||datas.get(j).getType()==4) {
                datas.remove(datas.get(j));
                j--;
                continue;
            }
            if (data!=null&&datas.get(j).getCamp()!=data.getCamp()) {
                datas.remove(datas.get(j));
                j--;
                continue;
            }
            for (int k = 1; k < datas.size(); ++k) {
                ManData data2 = (ManData)datas.get(k - 1);
                double value1 = data2.getvalue(type);
                ManData data3 = (ManData)datas.get(k);
                double value2 = data3.getvalue(type);
                if ((a && value1 < value2) || (!a && value1 > value2)) {
                    datas.set(k - 1, data3);
                    datas.set(k, data2);
                }
            }
        }
        for (int j = datas.size() - 1; j >= 0 && datas.size() > size; --j) {
            datas.remove(j);
        }
        if (data != null) {
            if (datas.size() != 0 && datas.size() >= size) {
                datas.remove(datas.size() - 1);
            }
            datas.add(0, data);
        }
        return datas;
    }
    public static void ys(ManData data, boolean is, Battlefield battlefield) {
        List<FightingState> States = null;
        AddState addState = data.xzstate("隐身");
        if (addState != null) {
            data.getAddStates().remove(addState);
            FightingState org = new FightingState();
            org.setCamp(data.getCamp());
            org.setMan(data.getMan());
            org.setStartState("代价");
            org.setEndState_2("隐身");
            States = new ArrayList<>();
            States.add(org);
        }
        if (is) {
            FightingSkill skill = data.getSkillType(TypeUtil.TZ_XMST);
            AddState addState1 = data.xzstate("玄瞳鉴");
            if (skill != null || (addState1 != null && addState1.getStateEffect2() > 0.0D)) {
                for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
                    ManData manData = (ManData)battlefield.fightingdata.get(i);
                    if (manData.getStates() == 0 && manData.getCamp() != data.getCamp() && manData.getMan() < 10) {
                        addState = manData.xzstate("隐身");
                        if (addState != null) {
                            manData.getAddStates().remove(addState);
                            FightingState org2 = new FightingState();
                            org2.setCamp(manData.getCamp());
                            org2.setMan(manData.getMan());
                            org2.setSkillskin("6020");
                            org2.setStartState("代价");
                            org2.setEndState_2("隐身");
                            if (States == null) {
                                States = new ArrayList<>();
                            }
                            States.add(org2);
                        }
                    }
                }
            }
        }
        if (States != null) {
            FightingEvents fightingEvents = new FightingEvents();
            fightingEvents.setAccepterlist(States);
            battlefield.NewEvents.add(fightingEvents);
        }
    }
    
    public static boolean isTZ_XMST(int id) {
        return (id >= 1041 && id <= 1065) || (id >= 1081 && id <= 1090) || (id >= 1096 && id <= 1100) || id == 9270 || id == 9286 || id == 9287 || id == 9307 || id == 9372;
    }
    
    public static void zhaohui(ManData manData, FightingState fightingState, Battlefield battlefield) {
        if (manData.getType() != 0 && manData.getType() != 2) {
            int i = 0;
            while (i < battlefield.Events.size()) {
                if (((FightingEvents)battlefield.Events.get(i)).getOriginator().getCamp() == manData.getCamp() && ((FightingEvents)battlefield.Events.get(i)).getOriginator().getMan() == manData.getMan()) {
                    battlefield.Events.remove(i);
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        if (manData.getType() == 1) {
            battlefield.Addbb(null, manData.getCamp(), manData.getMan());
        }
        if (manData.getType() == 3) {
            battlefield.AddLB(null, manData.getCamp(), manData.getMan());
        }
        List<GroupBuff> list = battlefield.ClearBuff(manData);
        if (list != null) {
            for (int j = list.size() - 1; j >= 0; --j) {
                String type = ((GroupBuff)list.get(j)).getBuffType();
                if (type.equals(TypeUtil.YBYT) || type.equals(TypeUtil.BB_MCQH) || type.equals(TypeUtil.BB_SGQX) || type.equals(TypeUtil.BB_TM) || type.equals(TypeUtil.BB_QZ)) {
                    GroupBuff buff = battlefield.addbuff(manData.getCamp(), type);
                    if (buff != null) {
                        list.remove(j);
                    }
                }
            }
            fightingState.setBuff(getBuffStr(list, false));
        }
        if (manData.getSkillType("遗产") != null) {
            first(manData, "遗产", battlefield);
        }
        if (manData.getSkillType(TypeUtil.BB_GQLX) != null) {
            first(manData, TypeUtil.BB_GQLX, battlefield);
        }
        if (manData.getSkillType(TypeUtil.BB_JS) != null) {
            first(manData, TypeUtil.BB_JS, battlefield);
        }
        FightingSkill skill = manData.getSkillType(TypeUtil.BB_TDTS);
        if (skill != null && Battlefield.isV(skill.getSkillhurt())) {
            first(manData, TypeUtil.BB_TDTS, battlefield);
        }
    }
    
    public static void DeathSkill(ManData manData, FightingState fightingState, Battlefield battlefield) {
        if (manData.getType() == 1) {
            ManData data = battlefield.getPetParents(manData);
            if (data.getSkillType("8069") != null) {
                FightingState state = new FightingState();
                FightingEvents moveEvents = new FightingEvents();
                List<FightingState> zls = new ArrayList<>();
                data.addyq(80, state);
                state.setCamp(data.getCamp());
                state.setMan(data.getMan());
                zls.add(state);
                moveEvents.setAccepterlist(zls);
                battlefield.NewEvents.add(moveEvents);
            }
        }
        if (manData.getStates() == 1 && manData.xzstate("归墟") == null) {
            if (manData.getSkillType("复活") != null) {
                first(manData, "复活", battlefield);
                if (manData.getBaohu() != null) {
                    battlefield.bhpd1(manData.getBaohu().getCamp(), manData.getBaohu().getMan());
                    manData.setBaohu(null);
                }
                return;
            }
            else if (battlefield.CurrentRound > 3 && manData.getSkillType(TypeUtil.BB_JR) != null) {
                first(manData, "复活", battlefield);
                if (manData.getBaohu() != null) {
                    battlefield.bhpd1(manData.getBaohu().getCamp(), manData.getBaohu().getMan());
                    manData.setBaohu(null);
                }
                return;
            }
        }
        if (manData.getStates() == 1 && manData.executeJtcl(battlefield)) {
            int path = battlefield.Datapath(manData.getCamp(), manData.getMan() - 5);
            if (path != -1) {
                ManData renData = (ManData)battlefield.fightingdata.get(path);
                first(renData, "闪现&" + manData.getId(), battlefield);
                if (manData.getBaohu() != null) {
                    battlefield.bhpd1(manData.getBaohu().getCamp(), manData.getBaohu().getMan());
                    manData.setBaohu(null);
                }
                return;
            }
        }
        if (manData.getType() == 1 && PK_MixDeal.isPK(battlefield.BattleType)) {
            if (manData.xzstate("扶摇") != null) {
                TY_L_GL_Qylh(manData, battlefield, "扶摇", TypeUtil.TY_L_10158);
                TY_L_GL_Qylh(manData, battlefield, "扶摇", TypeUtil.TY_L_10163);
            }
            else if (manData.xzstate("沧波") != null) {
                TY_L_GL_Qylh(manData, battlefield, "沧波", TypeUtil.TY_L_10148);
                TY_L_GL_Qylh(manData, battlefield, "沧波", TypeUtil.TY_L_10143);
            }
            else if (manData.xzstate("甘霖") != null) {
                TY_L_GL_Qylh(manData, battlefield, "甘霖", TypeUtil.TY_L_10153);
                TY_L_GL_Qylh(manData, battlefield, "甘霖", TypeUtil.TY_L_10143);
                TY_L_GL_Qylh(manData, battlefield, "甘霖", TypeUtil.TY_L_10163);
            }
        }
        int wei = battlefield.Datapath(manData.getCamp(), manData.getMan() - 5);
        int ren = battlefield.Datapath(manData.getCamp(), manData.getMan());
        if (wei != -1) {
            ManData data2 = (ManData)battlefield.fightingdata.get(ren);
            ManData mynData = (ManData)battlefield.fightingdata.get(wei);
            Asw(mynData, data2, battlefield);
            if (battlefield.CurrentRound <= 3) {
                if (mynData.getCamp() == 1 && battlefield.YLFY1 != null) {
                    battlefield.summonOrobability1 += battlefield.YLFY1.getSkillhurt();
                }
                else if (mynData.getCamp() == 2 && battlefield.YLFY2 != null) {
                    battlefield.summonOrobability2 += battlefield.YLFY2.getSkillhurt();
                }
            }
        }
        AddState ywlx = manData.xzstate("6043");
        zhaohui(manData, fightingState, battlefield);
        if (ywlx != null) {
            manData.getAddStates().add(ywlx);
        }
        if (manData.getType() == 0 || manData.getType() == 2) {
            FightingSkill skill = manData.getSkillType(TypeUtil.TJ_FSX);
            FightingSkill skill2 = manData.getSkillType(TypeUtil.TJ_ZQLR);
            AddState addState = manData.xzstate("复活");
            if (addState != null) {
                manData.addAddState("复活", 0.5 * (double)manData.getHp_z(), 0.0, 2);
                return;
            }
            if (skill != null) {
                if (skill2 != null) {
                    if (skill.getSkillgain() + 5.0 > (double)Battlefield.random.nextInt(100)) {
                        skill.setSkillsum(skill.getSkillsum() - 1);
                        if (skill.getSkillsum() <= 0) {
                            manData.getSkills().remove(skill);
                        }
                        manData.addAddState("复活", 0.5 * (double)manData.getHp_z(), 0.0, 999);
                        return;
                    }
                }
                else if (skill.getSkillgain() > (double)Battlefield.random.nextInt(100)) {
                    skill.setSkillsum(skill.getSkillsum() - 1);
                    if (skill.getSkillsum() <= 0) {
                        manData.getSkills().remove(skill);
                    }
                    manData.addAddState("复活", 0.5 * (double)manData.getHp_z(), 0.0, 999);
                    return;
                }
            }
        }
        manData.executeXzst(battlefield);
        manData.executeYcbb(battlefield);
        FightingSkill skill = manData.skillname("碧荷凝露");
        if (skill != null) {
            int my = 0;
            for (ManData data3 : battlefield.fightingdata) {
                if (data3.getStates() == 0 && data3.getType() == 0 && data3.getCamp() == manData.getCamp()) {
                    ++my;
                }
            }
            if (my <= 2) {
                first(manData, "碧荷凝露", battlefield);
            }
        }
        FightingPackage.FM_jshx(null, manData, null, battlefield, new String[] { "鱼龙潜跃", "虎踞龙蟠" });
        if (manData.getStates() == 1 && manData.executeSwbz()) {
            List<ManData> zwdy = battlefield.getZW(manData);
            if (zwdy.size() > 0) {
                FightingEvents moveEvents2 = new FightingEvents();
                List<FightingState> zls2 = new ArrayList<>();
                FightingState ace = new FightingState();
                ace.setCamp(manData.getCamp());
                ace.setMan(manData.getMan());
                ace.setText("啊我要炸了");
                zls2.add(ace);
                for (int j = zwdy.size() - 1; j >= 0; --j) {
                    FightingState ace2 = new FightingState();
                    ace2.setSkillskin("1056");
                    ManData nomyData2 = (ManData)zwdy.get(j);
                    if (nomyData2.getStates() == 0) {
                        ChangeFighting acec1 = new ChangeFighting();
                        int ap = manData.getHp_z() / 5;
                        acec1.setChangehp(-ap);
                        FightingPackage.ChangeProcess(acec1, null, nomyData2, ace2, "火", zls2, battlefield);
                    }
                }
                moveEvents2.setAccepterlist(zls2);
                battlefield.NewEvents.add(moveEvents2);
            }
        }
        Appearance(manData, battlefield);
        if (manData.getCamp() == 1) {
            ++battlefield.MyDeath;
        }
        else {
            ++battlefield.NoDeath;
        }
        if (manData.getType() == 1) {
            if (battlefield.bbDeathPoint == null) {
                battlefield.bbDeathPoint = new PathPoint();
            }
            if (manData.getCamp() == 1) {
                battlefield.bbDeathPoint.setX(battlefield.bbDeathPoint.getX() + 1);
            }
            else {
                battlefield.bbDeathPoint.setY(battlefield.bbDeathPoint.getY() + 1);
            }
        }
        else if (manData.getType() == 2 && manData.getZs() >= 1) {
            if (battlefield.bbDeathPoint == null) {
                battlefield.bbDeathPoint = new PathPoint();
                System.err.println("召唤兽死亡");
                zhaohui(manData, fightingState, battlefield);
            }
            if (manData.getCamp() == 1) {
                battlefield.bbDeathPoint.setX(battlefield.bbDeathPoint.getX() + 1);
            }
            else {
                battlefield.bbDeathPoint.setY(battlefield.bbDeathPoint.getY() + 1);
            }
        }
        else if (manData.getType() == 3) {
            List<String> tfSkills = manData.getTFSkills();
            if (tfSkills != null) {
                for (int i = 0; i < tfSkills.size(); ++i) {
                    String tfSkill = (String)tfSkills.get(i);
                    if (tfSkill.endsWith("归根")) {
                        ManData data4 = battlefield.getLingParents(manData);
                        FightingEvents moveEvents3 = new FightingEvents();
                        battlefield.NewEvents.add(moveEvents3);
                        List<FightingState> Accepterlist = new ArrayList<>();
                        moveEvents3.setAccepterlist(Accepterlist);
                        FightingState Accepter = new FightingState();
                        Accepterlist.add(Accepter);
                        ChangeFighting changeFightinghf = new ChangeFighting();
                        if (tfSkill.startsWith("高级")) {
                            changeFightinghf.setChangehp((int)((double)data4.getHp_z() * 0.66));
                            changeFightinghf.setChangemp((int)((double)data4.getHp_z() * 0.66));
                        }
                        else {
                            changeFightinghf.setChangehp((int)((double)data4.getHp_z() * 0.25));
                            changeFightinghf.setChangemp((int)((double)data4.getHp_z() * 0.25));
                        }
                        data4.ChangeData(changeFightinghf, Accepter);
                    }
                }
            }
        }
        boolean foundSkill = false;
        String[] yjsd = new String[] { TypeUtil.TY_R_YJSD_SH, TypeUtil.TY_R_YJSD_FH, TypeUtil.TY_R_YJSD_FS, TypeUtil.TY_R_YJSD_FD, TypeUtil.TY_R_YJSD_SD };
        int length = yjsd.length;
        int k = 0;
        while (k < length) {
            String vs = yjsd[k];
            FightingSkill TY_RH_NDQB_SS = manData.getSkillType(vs);
            if (TY_RH_NDQB_SS != null) {
                foundSkill = true;
                break;
            }
            else {
                ++k;
            }
        }
        String[] lqqm = new String[] { TypeUtil.TY_R_LQQM_SH, TypeUtil.TY_R_LQQM_FH, TypeUtil.TY_R_LQQM_FS, TypeUtil.TY_R_LQQM_FD, TypeUtil.TY_R_LQQM_DS };
        int length2 = lqqm.length;
        int l = 0;
        while (l < length2) {
            String vs2 = lqqm[l];
            FightingSkill TY_RH_NDQB_SS = manData.getSkillType(vs2);
            if (TY_RH_NDQB_SS != null) {
                foundSkill = true;
                break;
            }
            else {
                ++l;
            }
        }
        String[] skid = { "1041", "1042", "1043", "1044", "1045", "1046", "1047", "1048", "1049", "1050", "1051", "1052", "1053", "1054", "1055", "1056", "1057", "1058", "1059", "1060" };
        List<String> skillList = Arrays.asList(skid);
        FightingSkill TY_RH_NDQB_SS = manData.getSkillType(TypeUtil.TY_X_9940);
        if (TY_RH_NDQB_SS != null && skillList.contains(fightingState.getSkillskin())) {
            manData.addAddState(TypeUtil.TY_X_9940, TY_RH_NDQB_SS.getSkillhurt(), 0.0, 2);
        }
        if (foundSkill) {
            manData.ltd -= 3;
            manData.lts -= 3;
            manData.ltf -= 3;
            manData.lth -= 3;
            manData.lsd -= 3;
            manData.lss -= 3;
            manData.lsf -= 3;
            manData.lsh -= 3;
        }
    }
    
    public static void Asw(ManData myData, ManData data, Battlefield battlefield) {
        if (data.xzstate(TypeUtil.JS) == null || myData.xzstate(TypeUtil.JS) != null || myData.getSkillType(TypeUtil.TY_JS_KMJB) == null || myData.getStates() != 0 || myData.xzstate("隐身") != null || myData.xzstate("封印") != null) {
            return;
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        AddState addState = data.xzstate(TypeUtil.JS);
        ChangeFighting change = new ChangeFighting();
        FightingState state = new FightingState();
        change.setChangetype(addState.getStatename());
        change.setChangevlaue(addState.getStateEffect());
        change.setChangesum(addState.getSurplus());
        myData.ChangeData(change, state);
        state.setStartState(TypeUtil.JN);
        Accepterlist.add(state);
        FightingEvents events = new FightingEvents();
        events.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(events);
    }
    
    public static void TY_L_GL_Qylh(ManData manData, Battlefield battlefield, String type, String skillname) {
        ManData data = battlefield.getPetParents(manData);
        if (data != null) {
            FightingSkill skill = data.getSkillType(skillname);
            if (skill != null && Battlefield.isV(skill.getSkillhurt()) && data.xzstate(type) == null) {
                List<FightingState> Accepterlist = new ArrayList<>();
                AddState addState = manData.xzstate(type);
                ChangeFighting change = new ChangeFighting();
                FightingState state = new FightingState();
                change.setChangetype(addState.getStatename());
                change.setChangevlaue(addState.getStateEffect());
                change.setChangesum(addState.getSurplus());
                data.ChangeData(change, state);
                state.setStartState(TypeUtil.JN);
                Accepterlist.add(state);
                FightingEvents events = new FightingEvents();
                events.setAccepterlist(Accepterlist);
                battlefield.NewEvents.add(events);
            }
        }
    }
    
    public static void Appearance(ManData manData, Battlefield battlefield) {
        int path = battlefield.Datapath(manData.getCamp(), manData.getMan() - 5);
        if (path != -1) {
            ManData renData = (ManData)battlefield.fightingdata.get(path);
            if (manData.getStates() == 1) {
                FightingSkill skill = renData.skillId(9365);
                if (skill != null) {
                    skill.setSkillgain(skill.getSkillgain() + 1.0);
                    if (skill.getSkillgain() <= 5.0) {
                        renData.getQuality().setRolehsgh(renData.getQuality().getRolehsgh() + skill.getSkillhurt());
                    }
                }
            }
            if (renData.getType() == 0 && renData.getAppendSkill(9348) == null && renData.getZy() == 1) {
                AddState xzstate = manData.xzstate("6043");
                if (xzstate != null && Battlefield.isV(xzstate.getStateEffect())) {
                    return;
                }
                for (int i = 0; i < renData.getPets().size(); ++i) {
                    if (((FightingSummon)renData.getPets().get(i)).getPlay() == 0) {
                        int xs = manData.executeBfly(battlefield);
                        xs += manData.executeBdxz(battlefield);
                        int sxCamp = -1;
                        if (renData.getCamp() == 1 && battlefield.summonOrobability1 > 0.0) {
                            xs = (int)((double)xs + battlefield.summonOrobability1);
                            sxCamp = 1;
                        }
                        else if (renData.getCamp() == 2 && battlefield.summonOrobability2 > 0.0) {
                            xs = (int)((double)xs + battlefield.summonOrobability2);
                            sxCamp = 2;
                        }
                        int p = ((FightingSummon)renData.getPets().get(i)).getsx(PK_MixDeal.isBB(battlefield.BattleType) ? 1000.0 : ((double)xs));
                        if (p == 2) {
                            first(renData, "闪现&" + ((FightingSummon)renData.getPets().get(i)).getHang().getId(), battlefield);
                            if (manData.getBaohu() != null) {
                                battlefield.bhpd1(manData.getBaohu().getCamp(), manData.getBaohu().getMan());
                                manData.setBaohu(null);
                            }
                            if (sxCamp == 1) {
                                battlefield.summonOrobability1 /= 2.0;
                            }
                            else if (sxCamp == 2) {
                                battlefield.summonOrobability2 /= 2.0;
                            }
                            return;
                        }
                        else if (p == 1) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public static void Approach(ManData manData, FightingState fightingState, Battlefield battlefield) {
        List<GroupBuff> list = battlefield.addBuff(manData);
        if (list != null) {
            fightingState.setBuff(getBuffStr(list, true));
        }
        if (manData.getType() < 3) {
            manData.before(null, fightingState);
        }
        if (manData.getSkillType(TypeUtil.BB_DT) != null) {
            if (manData.getCamp() == 1) {
                Battlefield.overcamp1sp = Battlefield.overSp;
            }
            else {
                Battlefield.overcamp2sp = Battlefield.overSp;
            }
            first(manData, TypeUtil.BB_DT, battlefield);
        }
        if (manData.getSkillType("击其不意") != null) {
            first(manData, "击其不意", battlefield);
        }
        else if (manData.executeYmjr()) {
            first(manData, "一鸣惊人", battlefield);
        }
        if (manData.getSkillType("灯火阑珊")!=null && isV(manData.getSkillType("灯火阑珊").getSkillgain())) {
            first(manData, "流连忘返",battlefield);
        }
        if (manData.getSkillType(TypeUtil.BB_RHTY) != null) {
            first(manData, TypeUtil.BB_RHTY, battlefield);
        }
        if (PK_MixDeal.isPK(battlefield.BattleType)) {
            if (manData.getSkillType(TypeUtil.BB_YAHY) != null) {
                first(manData, TypeUtil.BB_YAHY, battlefield);
            }
            if (manData.getSkillType(TypeUtil.BB_NZQK) != null) {
                first(manData, TypeUtil.BB_NZQK, battlefield);
            }
        }
    }
    
    public static void first(ManData manData, String type, Battlefield battlefield) {
        FightingEvents moveEvents = new FightingEvents();
        FightingState move = new FightingState();
        moveEvents.setOriginator(move);
        move.setCamp(manData.getCamp());
        move.setMan(manData.getMan());
        move.setStartState(type);
        if (type.equals("击其不意")) {
            move.setStartState("普通攻击");
            if (Battlefield.isV((double)manData.executeFbgs(7, null))) {
                move.setStartState("技能");
                move.setEndState("兵临城下");
            }
            battlefield.erwai.add(0, moveEvents);
        }
        else if (type.equals("振羽惊雷")) {
            MixDeal.ZYJL = manData;
            battlefield.erwai.add(0, moveEvents);
        }
        else if (type.equals("复活") || type.equals(TypeUtil.BB_RHTY) || type.equals(TypeUtil.BB_YAHY) || type.equals(TypeUtil.BB_NZQK)) {
            battlefield.erwai.add(0, moveEvents);
        }
        else if (type.equals("一鸣惊人")) {
            FightingSkill skill = manData.skillIds(new int[] { 1044, 1049, 1054, 1059, 1064 });
            if (skill != null) {
                manData.ymjr = true;
                move.setStartState("技能");
                move.setEndState(skill.getSkillname());
                battlefield.erwai.add(0, moveEvents);
            }
        }
        else if (type.equals("流连忘返")) {
            FightingSkill skill=manData.getSkillType(type);
            if (skill!=null) {
                move.setStartState("技能");
                move.setEndState(skill.getSkillname());
                battlefield.erwai.add(0,moveEvents);
            }
        }
        else if (type.equals(TypeUtil.BB_TDTS)) {
            FightingSkill skill = manData.getSkillType(type);
            if (skill != null && manData.getSkin() != null) {
                move.setEndState(manData.getSkin());
                battlefield.erwai.add(0, moveEvents);
            }
        }
        else {
            if (type.equals("遗产")) {
                try {
                    move.setEndState((double)(manData.getMp() / manData.getMp_z()) + "");
                }
                catch (Exception e) {
                    move.setEndState("1");
                }
            }
            if (type.equals(TypeUtil.BB_GQLX)) {}
            if (type.equals(TypeUtil.BB_JS) || type.equals(TypeUtil.BB_DT)) {
                FightingSkill skill = manData.getSkillType(type);
                if (skill != null) {
                    move.setEndState(skill.getSkillsum() + "");
                }
                move.setEndState_2(manData.getSp() - manData.getSp2() + "");
            }
            battlefield.erwai.add(moveEvents);
        }
    }
    
    public static int getfengyin(String type) {
        if (type.equals("封印") || type.equals("药") || type.equals("解除控制") || type.equals("解除召唤兽控制") || type.equals("作鸟兽散")) {
            return 1;
        }
        return 0;
    }
    
    public static int getItself(String type) {
        if (type.equals(TypeUtil.TZ_FHJY) || type.equals(TypeUtil.TZ_PFCZ) || type.equals(TypeUtil.TZ_HGFZ)) {
            return 1;
        }
        return 0;
    }
    
    public static int getren(String type) {
        if (type.equals("灵宝技能")) {
            return 1;
        }
        if (type.equals("法魂护体")) {
            return 4;
        }
        if (type.equals(TypeUtil.BB_SS)) {}
        return 0;
    }
    
    public static int getyin(String type) {
        if (type.equals("作鸟兽散")) {
            return 1;
        }
        return 0;
    }
    
    public static int getcamp(String type, int mycamp, int nomycamp) {
        if (type.equals("混乱技") || type.equals("药") || type.equals("移花接木") || type.equals(TypeUtil.FB_DSY)|| type.equals("暮眠钟")) {
            return -1;
        }
        if (type.equals(TypeUtil.JS) || type.equals(TypeUtil.KX)
                || type.equals(TypeUtil.LL) || type.equals("甘霖")
                || type.equals("庇护") || type.equals(TypeUtil.MH)
                || type.equals("解除控制") || type.equals("回血技")
                || type.equals("加狂暴") || type.equals("遗产")
                || type.equals("作鸟兽散") || type.equals("隐身技")
                || type.equals(TypeUtil.FB_JJL) || type.equals(TypeUtil.TY_KX_BYJR)
                || type.equals(TypeUtil.FB_DSC) || type.equals(TypeUtil.FB_QBLLT)
                || type.equals(TypeUtil.FB_HLZ) || type.equals(TypeUtil.FB_YMGS)
                || type.equals(TypeUtil.FB_JQB) || type.equals(TypeUtil.FB_BLD)
                || type.equals(TypeUtil.FB_JLJS) || type.equals(TypeUtil.FB_FTY)
                || type.equals(TypeUtil.TZ_CXYF) || type.equals(TypeUtil.TY_MH_RSSQ)
                || type.equals(TypeUtil.BB_E_BLBJ) || type.equals(TypeUtil.BB_CNHK)
                || type.equals(TypeUtil.TZ_MXJX) || type.equals(TypeUtil.TZ_BDBQ)
                || type.equals("凝神一击") || type.equals("幻影迷踪")
                || type.equals("兽魂俯首") || type.equals("刚柔兼备")
                || type.equals("偷梁换柱") || type.equals("遮天蔽日")
                || type.equals("金身不灭") || type.equals("法魂护体")
                || type.equals("冥河纱") || type.equals("玄瞳鉴") || type.equals("振魂鼓")
                || type.equals("沧溟露") || type.equals("夔灵鼓") || type.equals("玄鳞甲")
                || type.equals("引蕴瓶") || type.equals("千丝网")
                || type.equals("藏锋蓄势")|| type.equals("流连忘返")) {
            return nomycamp;
        }
        return mycamp;
    }
    
    public static int gethuo(String type) {
        if (type.equals("回血技") || type.equals(TypeUtil.BB_CNHK)) {
            return 1;
        }
        if (type.equals("作鸟兽散")) {
            return 2;
        }
        return 0;
    }
    
    public static double addition(double kbl, FightingState ace, ManData myData, ManData data, String type) {
        if (type.equals("疾风迅雷")) {
            type = "雷";
        }
        double xs = 100.0;
        if (type.equals("三尸虫")) {
            kbl += myData.getQuality().getRolesskb();
            xs += myData.getQuality().getBsccd();
        }
        else if (type.equals("鬼火")) {
            kbl += myData.getQuality().getRoleghkb() + myData.fskbjl;
            xs += myData.getQuality().getBghcd() + myData.fskbcd;
            AddState addState = data.xzstate(TypeUtil.TY_GH_YJFR);
            if (addState != null) {
                xs += addState.getStateEffect();
            }
        }
        else if (type.equals("风")) {
            kbl += myData.getQuality().getRoleffkb() + myData.fskbjl;
            xs += myData.getQuality().getBffcd() + myData.fskbcd;
        }
        else if (type.equals("火")) {
            kbl += myData.getQuality().getRolehfkb() + myData.fskbjl;
            xs += myData.getQuality().getBhfcd() + myData.fskbcd;
            AddState addState = data.xzstate(TypeUtil.TY_H_HLDX);
            if (addState != null) {
                kbl += addState.getStateEffect();
            }
        }
        else if (type.equals("水")) {
            kbl += myData.getQuality().getRolesfkb() + myData.fskbjl;
            xs += myData.getQuality().getBsfcd() + myData.fskbcd;
        }
        else if (type.equals("雷")) {
            kbl += myData.getQuality().getRolelfkb() + myData.fskbjl;
            xs += myData.getQuality().getBlfcd() + myData.fskbcd;
        }
        if (Battlefield.isV(kbl)) {
            ace.setProcessState("狂暴");
            xs += 50.0;
            xs /= 100.0;
            return xs;
        }
        return 0.0;
    }
    
    public static double additionBj(List<FightingState> list, ManData myData, ManData data, String type) {
        if (type.equals("疾风迅雷")) {
            type = "雷";
        }
        double bjl = myData.getQuality().getFsbj();
        double xs = myData.getQuality().getFsbjcd() + 100.0;
        if (type.equals("三尸虫")) {
            bjl += myData.getQuality().getBjsc();
            xs += myData.getQuality().getBjsccd();
        }
        else if (type.equals("鬼火")) {
            bjl += myData.getQuality().getBjgh();
            xs += myData.getQuality().getBjghcd();
        }
        else if (type.equals("风")) {
            bjl += myData.getQuality().getBjff();
            xs += myData.getQuality().getBjffcd();
        }
        else if (type.equals("火")) {
            bjl += myData.getQuality().getBjhf();
            xs += myData.getQuality().getBjhfcd();
        }
        else if (type.equals("水")) {
            bjl += myData.getQuality().getBjsf();
            xs += myData.getQuality().getBjsfcd();
        }
        else if (type.equals("雷")) {
            bjl += myData.getQuality().getBjlf();
            xs += myData.getQuality().getBjlfcd();
        }
        bjl += myData.getFamencsJv(2, new String[] { "一气呵成" });
        AddState addState = myData.xzstate("凝神一击");
        if (addState != null) {
            int fmId = (int)addState.getStateEffect();
            int fmsld = (int)addState.getStateEffect2();
            bjl += FaMenUtil.getDouble(fmId, fmsld, 3);
        }
        if (Battlefield.isV(bjl)) {
            FightingState fightingState = new FightingState();
            fightingState.setCamp(myData.getCamp());
            fightingState.setMan(myData.getMan());
            fightingState.setSkillskin("暴击");
            list.add(fightingState);
            xs += 50.0;
            xs /= 100.0;
            return xs;
        }
        return 0.0;
    }
    
    public static double addition(double ap, double kbl, FightingState ace, ManData myData, ManData data, String type) {
        double xs = 100.0;
        if (type.equals("三尸虫")) {
            kbl += myData.getQuality().getRolesskb();
            xs += myData.getQuality().getBsccd();
        }
        else if (type.equals("鬼火")) {
            kbl += myData.getQuality().getRoleghkb();
            xs += myData.getQuality().getBghcd();
            AddState addState = data.xzstate(TypeUtil.TY_GH_YJFR);
            if (addState != null) {
                xs += addState.getStateEffect();
            }
        }
        else if (type.equals("风")) {
            kbl += myData.getQuality().getRoleffkb();
            xs += myData.getQuality().getBffcd();
        }
        else if (type.equals("火")) {
            kbl += myData.getQuality().getRolehfkb();
            xs += myData.getQuality().getBhfcd();
            AddState addState = data.xzstate(TypeUtil.TY_H_HLDX);
            if (addState != null) {
                kbl += addState.getStateEffect();
            }
        }
        else if (type.equals("水")) {
            kbl += myData.getQuality().getRolesfkb();
            xs += myData.getQuality().getBsfcd();
        }
        else if (type.equals("雷")) {
            kbl += myData.getQuality().getRolelfkb();
            xs += myData.getQuality().getBlfcd();
        }
        if ((double)(Battlefield.random.nextInt(100) + 1) < kbl) {
            ace.setProcessState("狂暴");
            xs += 50.0;
            xs /= 100.0;
            return (double)(long)(ap * xs);
        }
        return ap;
    }
    
    public static double addition(double kbl, FightingState ace, ManData myData, ManData data, String type, Battlefield battlefield) {
        if (type.equals("疾风迅雷")) {
            type = "雷";
        }
        double xs = 100.0;
        if (type.equals("三尸虫")) {
            kbl += myData.getQuality().getRolesskb();
            xs += myData.getQuality().getBsccd();
        }
        else if (type.equals("鬼火")) {
            kbl += myData.getQuality().getRoleghkb() + myData.fskbjl;
            xs += myData.getQuality().getBghcd() + myData.fskbcd;
            AddState addState = data.xzstate(TypeUtil.TY_GH_YJFR);
            if (addState != null) {
                xs += addState.getStateEffect();
            }
        }
        else if (type.equals("风")) {
            kbl += myData.getQuality().getRoleffkb() + myData.fskbjl;
            xs += myData.getQuality().getBffcd() + myData.fskbcd;
        }
        else if (type.equals("火")) {
            kbl += myData.getQuality().getRolehfkb() + myData.fskbjl;
            xs += myData.getQuality().getBhfcd() + myData.fskbcd;
            AddState addState = data.xzstate(TypeUtil.TY_H_HLDX);
            if (addState != null) {
                kbl += addState.getStateEffect();
            }
        }
        else if (type.equals("水")) {
            kbl += myData.getQuality().getRolesfkb() + myData.fskbjl;
            xs += myData.getQuality().getBsfcd() + myData.fskbcd;
            AddState addState = data.xzstate("寒冷");
            if (addState != null) {
                FightingSkill TY_X_9969 = myData.getSkillType(TypeUtil.TY_X_9969);
                if (TY_X_9969 != null) {
                    kbl += TY_X_9969.getSkillhurt();
                }
            }
        }
        else if (type.equals("雷")) {
            kbl += myData.getQuality().getRolelfkb() + myData.fskbjl;
            xs += myData.getQuality().getBlfcd() + myData.fskbcd;
        }
        if (Battlefield.isV(kbl)) {
            FightingSkill TY_X_9970 = myData.getSkillType(TypeUtil.TY_X_9948);
            if (TY_X_9970 != null && PK_MixDeal.isPK(battlefield.BattleType)) {
                data.addAddState("电感", TY_X_9970.getSkillgain(), 0.0, 2);
            }
            ace.setProcessState("狂暴");
            xs += 50.0;
            xs /= 100.0;
            return xs;
        }
        else {
            return 0.0;
        }
    }
    
    public static double addition(FightingState ace, double ap, ManData mydaData, String type) {
        double kbl = 0.0;
        double kbcd = 0.0;
        if (type.equals("三尸虫")) {
            kbl = mydaData.getQuality().getRolesskb();
            kbcd = mydaData.getQuality().getBsccd() / 100.0;
        }
        else if (type.equals("鬼火")) {
            kbl = mydaData.getQuality().getRoleghkb();
        }
        else if (type.equals("风")) {
            kbl = mydaData.getQuality().getRoleffkb();
        }
        else if (type.equals("火")) {
            kbl = mydaData.getQuality().getRolehfkb();
        }
        else if (type.equals("水")) {
            kbl = mydaData.getQuality().getRolesfkb();
        }
        else if (type.equals("雷")) {
            kbl = mydaData.getQuality().getRolelfkb();
        }
        if ((double)(Battlefield.random.nextInt(100) + 1) < kbl) {
            ace.setProcessState("狂暴");
            return (double)(long)(ap * (1.5 + kbcd));
        }
        return ap;
    }
    
    public static void flee(Battlefield battlefield, int type) {
        boolean is_a = true;
        boolean is_b = true;
        ManData a_bb = null;
        ManData b_bb = null;
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.getType() == 1 && data.getStates() == 0) {
                if (a_bb != null && data.getCamp() == a_bb.getCamp()) {
                    is_a = false;
                }
                else if (b_bb != null && data.getCamp() == b_bb.getCamp()) {
                    is_b = false;
                }
                else if (a_bb == null) {
                    a_bb = data;
                }
                else if (b_bb == null) {
                    b_bb = data;
                }
            }
        }
        if (is_a && a_bb != null && battlefield.Datapathhuo(a_bb.getCamp(), a_bb.getMan() - 5) == -1) {
            FightingSkill skill = a_bb.getSkillType((type == 0) ? "作鸟兽散" : TypeUtil.BB_E_HRYY);
            if (skill != null) {
                SpellActionType.getActionById((type == 0) ? 14 : 15).spellAction(a_bb, skill, null, battlefield);
            }
        }
        if (is_b && b_bb != null && battlefield.Datapathhuo(b_bb.getCamp(), b_bb.getMan() - 5) == -1) {
            FightingSkill skill = b_bb.getSkillType((type == 0) ? "作鸟兽散" : TypeUtil.BB_E_HRYY);
            if (skill != null) {
                SpellActionType.getActionById((type == 0) ? 14 : 15).spellAction(b_bb, skill, null, battlefield);
            }
        }
    }
    
    public static void zhszy(Battlefield battlefield, ManData man) {
        if (man.getStates() != 0) {
            return;
        }
        FightingSkill skill = man.getSkillType(TypeUtil.TJ_GJZY);
        if (skill == null) {
            skill = man.getSkillType(TypeUtil.TJ_DJZY);
            if (skill == null) {
                return;
            }
        }
        if (Battlefield.random.nextInt(100) < skill.getSkilllvl() && man.getZy() == 1 && man.getType() == 0 && man.getAppendSkill(9348) == null) {
            for (int i = 0; i < man.getPets().size(); ++i) {
                FightingSummon fs = (FightingSummon)man.getPets().get(i);
                if (fs.getPlay() == 0) {
                    int p = fs.getsx(0.0);
                    if (p == 2) {
                        first(man, "闪现&" + fs.getHang().getId(), battlefield);
                        return;
                    }
                    if (p == 1) {
                        return;
                    }
                }
            }
        }
    }
    
    public static void lingBaozy(Battlefield battlefield, ManData man) {
        if (man.getStates() != 0) {
            return;
        }
        FightingSkill skill = man.getSkillType(TypeUtil.TJ_GJLB);
        if (skill == null) {
            skill = man.getSkillType(TypeUtil.TJ_DJLB);
            if (skill == null) {
                return;
            }
        }
        if (Battlefield.random.nextInt(100) < skill.getSkilllvl() && man.getLzy() == 1 && man.getType() == 0 && man.getAppendSkill(9348) == null) {
            for (int i = man.getLings().size() - 1; i >= 0; --i) {
                FightingLingbao fightingLingbao = (FightingLingbao)man.getLings().get(i);
                if (fightingLingbao.getPlay() == 0) {
                    int p = 2;
                    if (p == 2) {
                        first(man, "ls&" + ((FightingLingbao)man.getLings().get(i)).getLingbaonData().getId(), battlefield);
                        return;
                    }
                    if (p == 1) {
                        return;
                    }
                }
            }
        }
    }
    
    public static void HNYG(Battlefield battlefield) {
        boolean is_Ba = true;
        boolean is_Bb = true;
        boolean is_Ra = true;
        boolean is_Rb = true;
        ManData a_bb = null;
        ManData b_bb = null;
        ManData a_role = null;
        ManData b_role = null;
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.getStates() == 0) {
                if (data.getType() == 0) {
                    if (a_role != null && data.getCamp() == a_role.getCamp()) {
                        is_Ra = false;
                    }
                    else if (b_role != null && data.getCamp() == b_role.getCamp()) {
                        is_Rb = false;
                    }
                    else if (a_role == null) {
                        a_role = data;
                    }
                    else if (b_role == null) {
                        b_role = data;
                    }
                }
                else if (data.getType() == 1) {
                    if (a_bb != null && data.getCamp() == a_bb.getCamp()) {
                        is_Ba = false;
                    }
                    else if (b_bb != null && data.getCamp() == b_bb.getCamp()) {
                        is_Bb = false;
                    }
                    else if (a_bb == null) {
                        a_bb = data;
                    }
                    else if (b_bb == null) {
                        b_bb = data;
                    }
                }
            }
        }
        if (is_Ba && is_Ra && a_bb != null && a_role != null && a_bb.getMan() - 5 == a_role.getMan()) {
            FightingSkill skill = a_bb.getSkillType(TypeUtil.BB_E_HNYG);
            if (skill != null) {
                SpellActionType.getActionById(16).spellAction(a_bb, skill, null, battlefield);
            }
        }
        if (is_Bb && is_Rb && b_bb != null && b_role != null && b_bb.getMan() - 5 == b_role.getMan()) {
            FightingSkill skill = b_bb.getSkillType(TypeUtil.BB_E_HNYG);
            if (skill != null) {
                SpellActionType.getActionById(16).spellAction(b_bb, skill, null, battlefield);
            }
        }
    }
    
    public static boolean getyx(int id, int type) {
        return PK_MixDeal.isPK(type) || type == 886 || ((id < 3025 || id > 3030) && id != 1828);
    }
    
    public static boolean isgyzl(int camp, Battlefield battlefield) {
        int size = 0;
        int sysum = 0;
        for (int i = 0; i < battlefield.fightingdata.size(); ++i) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.getStates() != 2) {
                ++size;
                if (camp == data.getCamp() && data.getStates() == 0 && data.xzstate("封印") == null && data.xzstate("昏睡") == null && data.xzstate("混乱") == null && data.xzstate("遗忘") == null && data.xzstate("中毒") == null) {
                    ++sysum;
                }
            }
        }
        return (double)sysum / (double)size <= 0.4;
    }
    
    public static FightingEvents sxsmove(ManData data, ManData manData, Battlefield battlefield) {
        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> moves = new ArrayList<>();
        FightingState gjz = new FightingState();
        gjz.setCamp(manData.getCamp());
        gjz.setMan(manData.getMan());
        gjz.setStartState("特效1");
        moves.add(gjz);
        FightingState move = new FightingState();
        move.setCamp(manData.getCamp());
        move.setMan(manData.getMan());
        move.setSkillskin("10");
        move.setStartState("技能移动");
        move.setEndState(data.getCamp() + "|" + data.getMan());
        moves.add(move);
        moveEvents.setAccepterlist(moves);
        battlefield.NewEvents.add(moveEvents);
        FightingEvents events = new FightingEvents();
        List<FightingState> list = new ArrayList<>();
        FightingState gl = new FightingState();
        gl.setCamp(manData.getCamp());
        gl.setMan(manData.getMan());
        gl.setSkillskin("11");
        gl.setStartState("技能移动");
        gl.setEndState(data.getCamp() + "|" + data.getMan());
        list.add(gl);
        events.setAccepterlist(list);
        return events;
    }
    
    public static void lsmove(ManData data, Battlefield battlefield, ManData manData) {
        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> moves = new ArrayList<>();
        FightingState move = new FightingState();
        move.setCamp(manData.getCamp());
        move.setMan(manData.getMan());
        move.setSkillskin("1237");
        move.setStartState("技能移动");
        move.setEndState(data.getCamp() + "|" + data.getMan() + "|3");
        moves.add(move);
        FightingState gw = new FightingState();
        gw.setCamp(data.getCamp());
        gw.setMan(data.getMan());
        gw.setSkillskin("7004");
        gw.setStartState("被攻击");
        moves.add(gw);
        FightingState gjz = new FightingState();
        gjz.setCamp(manData.getCamp());
        gjz.setMan(manData.getMan());
        gjz.setStartState("特效2");
        moves.add(gjz);
        moveEvents.setAccepterlist(moves);
        battlefield.NewEvents.add(moveEvents);
    }
    
    public static void skillmove(List<ManData> datas, Battlefield battlefield, ManData manData, String type) {
        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> moves = new ArrayList<>();
        for (int i = datas.size() - 1; i >= 0; --i) {
            ManData data = (ManData)datas.get(i);
            FightingState move = new FightingState();
            move.setCamp(manData.getCamp());
            move.setMan(manData.getMan());
            move.setSkillskin(type);
            move.setStartState("技能移动");
            move.setEndState(data.getCamp() + "|" + data.getMan() + "|3");
            moves.add(move);
        }
        moveEvents.setAccepterlist(moves);
        battlefield.NewEvents.add(moveEvents);
    }
    
    public static FightingState skillmove(ManData data, ManData manData, String type) {
        return skillmove(data, manData, type, 3);
    }
    
    public static FightingState skillmove(ManData data, ManData manData, String type, int dir) {
        FightingState move = new FightingState();
        move.setCamp(manData.getCamp());
        move.setMan(manData.getMan());
        move.setSkillskin(type);
        move.setStartState("技能移动");
        if (dir == -1) {
            move.setEndState(data.getCamp() + "|" + data.getMan());
        }
        else {
            move.setEndState(data.getCamp() + "|" + data.getMan() + "|" + dir);
        }
        return move;
    }
    
    public static void BB_SRPZ(List<ManData> datas, Battlefield battlefield, ManData manData, String skin, FightingSkill skill) {
        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> moves = new ArrayList<>();
        for (int i = datas.size() - 1; i >= 0; --i) {
            ManData data = (ManData)datas.get(i);
            FightingState move = new FightingState();
            move.setCamp(data.getCamp());
            move.setMan(data.getMan());
            move.setSkillskin(TypeUtil.BB_SRPZ);
            move.setStartState(TypeUtil.JN);
            moves.add(move);
        }
        FightingState move2 = new FightingState();
        move2.setCamp(manData.getCamp());
        move2.setMan(manData.getMan());
        move2.setSkillskin(skin);
        move2.setStartState("法术攻击");
        if (skill != null) {
            manData.daijia(skill, move2, battlefield);
        }
        moves.add(move2);
        moveEvents.setAccepterlist(moves);
        battlefield.NewEvents.add(moveEvents);
    }
    
    public static void BB_DNTG(List<ManData> datas, Battlefield battlefield, ManData manData, String skin, FightingSkill skill) {
        FightingEvents moveEvents = new FightingEvents();
        List<FightingState> moves = new ArrayList<>();
        for (int i = datas.size() - 1; i >= 0; --i) {
            ManData data = (ManData)datas.get(i);
            FightingState move = new FightingState();
            move.setCamp(data.getCamp());
            move.setMan(data.getMan());
            move.setSkillskin("1252");
            move.setStartState(TypeUtil.JN);
            moves.add(move);
        }
        FightingState move2 = new FightingState();
        move2.setCamp(manData.getCamp());
        move2.setMan(manData.getMan());
        move2.setSkillskin(skin);
        move2.setStartState("法术攻击");
        if (skill != null) {
            manData.daijia(skill, move2, battlefield);
        }
        moves.add(move2);
        moveEvents.setAccepterlist(moves);
        battlefield.NewEvents.add(moveEvents);
    }
    
    public static List<ManData> getdaji(int size, int nocamp, FightingEvents fightingEvents, Battlefield battlefield) {
        ManData data = null;
        if (fightingEvents != null && fightingEvents.getAccepterlist() != null && fightingEvents.getAccepterlist().size() != 0) {
            int path = battlefield.Datapathhuo(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
            if (path != -1) {
                data = (ManData)battlefield.fightingdata.get(path);
            }
        }
        List<ManData> datas = get(true, data, 0, nocamp, 0, 0, 0, 0, size, -1, battlefield, 1);
        return datas;
    }
    
    public static FightingState getChildSkill(ManData child, String skillname) {
        FightingState state = new FightingState();
        state.setCamp(child.getCamp());
        state.setMan(child.getMan());
        state.setStartState("法术攻击");
        StringBuffer buffer = new StringBuffer();
        buffer.append(MixDeal.KAN1);
        buffer.append(skillname);
        buffer.append(MixDeal.KAN2);
        state.setText(buffer.toString());
        state.setSkillsy("孩子喊话");
        return state;
    }
    
    public static boolean getFS(Battlefield battlefield, List<FightingState> Accepterlist, ManData manData, int noCamp) {
        GroupBuff groupBuff = battlefield.getBuff(noCamp, TypeUtil.BB_FBSF);
        if (groupBuff != null && Battlefield.isV(groupBuff.getValue())) {
            List<GroupBuff> removeBuffs = battlefield.ClearBuff(groupBuff);
            if (removeBuffs != null) {
                FightingState state = new FightingState();
                state.setCamp(manData.getCamp());
                state.setMan(manData.getMan());
                state.setStartState("代价");
                state.setBuff(getBuffStr(removeBuffs, false));
                state.setSkillskin(groupBuff.getBuffType());
                Accepterlist.add(state);
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public static boolean getTGBG(Battlefield battlefield, List<FightingState> Accepterlist, ManData manData, int noCamp) {
        GroupBuff groupBuff = battlefield.getBuff(noCamp, "23009");
        if (groupBuff != null && Battlefield.isV(groupBuff.getValue())) {
            List<GroupBuff> removeBuffs = battlefield.ClearBuff(groupBuff);
            if (removeBuffs != null) {
                FightingState state = new FightingState();
                state.setCamp(manData.getCamp());
                state.setMan(manData.getMan());
                state.setStartState("代价");
                state.setBuff(getBuffStr(removeBuffs, false));
                state.setSkillskin(groupBuff.getBuffType());
                Accepterlist.add(state);
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public static void clbf(FightingSkill skill, ManData manData, Battlefield battlefield) {
        if (!PK_MixDeal.isPK(battlefield.BattleType)) {
            for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
                ManData data = (ManData)battlefield.fightingdata.get(i);
                if (data.getStates() == 0 && data != manData && (data.getType() == 1 && data.xzstate("封印") == null && data.getCamp() == manData.getCamp())) {
                    FightingSkill fightingSkill = data.getSkillType(TypeUtil.BB_HY);
                    if (fightingSkill != null && Battlefield.isV(fightingSkill.getSkillhitrate())) {
                        List<FightingState> fightingStates = new ArrayList<>();
                        FightingState org = new FightingState();
                        org.setCamp(manData.getCamp());
                        org.setMan(manData.getMan());
                        org.setStartState(TypeUtil.JN);
                        ChangeFighting change = new ChangeFighting();
                        change.setChangemp(manData.getMp_z() / 2);
                        FightingPackage.ChangeProcess(change, null, manData, org, TypeUtil.BB_BF, fightingStates, battlefield);
                        FightingEvents fightingEvents = new FightingEvents();
                        fightingEvents.setAccepterlist(fightingStates);
                        battlefield.NewEvents.add(fightingEvents);
                    }
                }
            }
        }
        else {
            for (int i = battlefield.fightingdata.size() - 1; i >= 0; --i) {
                ManData data = (ManData)battlefield.fightingdata.get(i);
                if (data.getStates() == 0 && data != manData && (data.getType() == 1 && data.xzstate("封印") == null)) {
                    if (data.getCamp() == manData.getCamp()) {
                        FightingSkill fightingSkill = data.getSkillType(TypeUtil.BB_HY);
                        if (fightingSkill != null && Battlefield.isV(fightingSkill.getSkillhitrate())) {
                            List<FightingState> fightingStates = new ArrayList<>();
                            FightingState org = new FightingState();
                            org.setCamp(manData.getCamp());
                            org.setMan(manData.getMan());
                            org.setStartState(TypeUtil.JN);
                            ChangeFighting change = new ChangeFighting();
                            change.setChangemp(manData.getMp_z() / 2);
                            FightingPackage.ChangeProcess(change, null, manData, org, TypeUtil.BB_BF, fightingStates, battlefield);
                            FightingEvents fightingEvents = new FightingEvents();
                            fightingEvents.setAccepterlist(fightingStates);
                            battlefield.NewEvents.add(fightingEvents);
                        }
                    }
                    else {
                        FightingSkill fightingSkill = data.getSkillType(TypeUtil.BB_BF);
                        if (fightingSkill != null && Battlefield.isV(fightingSkill.getSkillhitrate())) {
                            List<FightingState> fightingStates = new ArrayList<>();
                            FightingState org = new FightingState();
                            org.setCamp(manData.getCamp());
                            org.setMan(manData.getMan());
                            org.setSkillskin("1835");
                            org.setStartState(TypeUtil.JN);
                            ChangeFighting change = new ChangeFighting();
                            int hurt = -(int)((double)skill.getSkillblue() * fightingSkill.getSkillhurt() / 100.0);
                            FightingSkill skill2 = manData.skillId(9221);
                            if (skill2 != null) {
                                hurt = (int)((double)hurt * (100.0 - skill2.getSkillhurt()) / 100.0);
                            }
                            change.setChangehp(hurt);
                            FightingPackage.ChangeProcess(change, null, manData, org, TypeUtil.BB_BF, fightingStates, battlefield);
                            FightingEvents fightingEvents2 = new FightingEvents();
                            fightingEvents2.setAccepterlist(fightingStates);
                            battlefield.NewEvents.add(fightingEvents2);
                        }
                    }
                }
            }
        }
    }
    
    public static String getBuffStr(List<GroupBuff> buffs, boolean type) {
        StringBuffer buffer = new StringBuffer();
        if (type) {
            for (int i = buffs.size() - 1; i >= 0; --i) {
                GroupBuff buff = (GroupBuff)buffs.get(i);
                buffer.append(0);
                buffer.append(MixDeal.DH);
                buffer.append(buff.getBuffId());
                buffer.append(MixDeal.DH);
                buffer.append(buff.getCamp());
                buffer.append(MixDeal.DH);
                buffer.append(buff.getBuffType());
                if (i != 0) {
                    buffer.append(MixDeal.FG);
                }
            }
        }
        else {
            for (int i = buffs.size() - 1; i >= 0; --i) {
                GroupBuff buff = (GroupBuff)buffs.get(i);
                buffer.append(1);
                buffer.append(MixDeal.DH);
                buffer.append(buff.getBuffId());
                if (i != 0) {
                    buffer.append(MixDeal.FG);
                }
            }
        }
        return buffer.toString();
    }
    
    public static FightingState DSMY(ManData manData, ManData data, int skillId, Battlefield battlefield) {
        return DSMY(manData, data, null, skillId, battlefield);
    }
    
    public static FightingState DSMY(ManData manData, ManData data, String attackType, int skillId, Battlefield battlefield) {
        int type = SkillType(skillId);
        if (type == 0) {
            return null;
        }
        double xs = getXS1(data.getQihe());
        xs += data.getQuality().getEfsds();
        FightingSkill skillType = data.getSkillType(TypeUtil.BB_E_BLBJ);
        if (skillType != null && data.getType() == 1 && ((skillId >= 1001 && skillId <= 1015) || (skillId >= 1071 && skillId <= 1075))) {
            double v = Arith.div((double)data.getHp(), (double)data.getHp_z());
            if (v >= 0.33) {
                xs += 100.0;
            }
        }
        AddState addState = manData.xzstate("沧波");
        if (addState != null) {
            xs += addState.getStateEffect() / 2.0;
        }
        if (data.getZs() >= 4) {
            FightingSkill skill = data.getAppendSkill(9229);
            if (skill != null) {
                xs += skill.getSkillhurt();
            }
        }
        addState = manData.xzstate(TypeUtil.TY_X_9940);
        if (addState != null) {
            xs += addState.getStateEffect();
        }
        GroupBuff buff = battlefield.getBuff(data.getCamp(), TypeUtil.BB_E_HYMB);
        if (buff != null) {
            xs += buff.getValue();
        }
        xs += data.getds(skillId);
        xs -= data.getMz(skillId) + manData.fsmz;
        addState = manData.xzstate("凝神一击");
        if (addState != null) {
            int fmId = (int)addState.getStateEffect();
            int fmsld = (int)addState.getStateEffect2();
            xs -= FaMenUtil.getDouble(fmId, fmsld, 2);
        }
        addState = manData.xzstate("幻影迷踪");
        if (addState != null && battlefield.getWaitList().contains(data)) {
            int fmId = (int)addState.getStateEffect();
            int fmsld = (int)addState.getStateEffect2();
            xs -= FaMenUtil.getDouble(fmId, fmsld, 2);
        }
        addState = data.xzstate("幻影迷踪");
        if (addState != null && battlefield.getWaitList().contains(data)) {
            int fmId = (int)addState.getStateEffect();
            int fmsld = (int)addState.getStateEffect2();
            xs += FaMenUtil.getDouble(fmId, fmsld, 3);
        }
        addState = manData.xzstate("神不守舍");
        if (addState != null) {
            int fmId = (int)addState.getStateEffect();
            int fmsld = (int)addState.getStateEffect2();
            xs += FaMenUtil.getDouble(fmId, fmsld, 1);
        }
        addState = data.xzstate("幽魂灯");
        if (addState != null) {
            xs -= addState.getStateEffect4();
        }
        addState = manData.xzstate("扑朔迷离");
        if (addState != null && addState.getSurplus() == 0) {
            xs += FaMenUtil.getDouble((int)addState.getStateEffect(), manData.getDffmsld(), 1);
        }
        addState = data.xzstate("妙法莲华");
        if (addState != null) {
            xs -= data.getFamencsJv(2, new String[] { "妙法莲华" });
        }
        addState = data.xzstate("困兽之斗-速");
        if (addState != null) {
            int fmId = (int)addState.getStateEffect();
            int fmsld = (int)addState.getStateEffect2();
            xs += FaMenUtil.getDouble(fmId, fmsld, 2);
        }
        try {
            addState = data.xzstate("加速");
            if (addState != null && data.getType() == 0 && data.getZs() >= 4 && manData.getSkillType("9229") != null && PK_MixDeal.isPK(battlefield.BattleType)) {
                FightingSkill skill2 = manData.getSkillType("9229");
                xs += (double)(1 * skill2.getSkilllvl());
            }
        }
        catch (Exception e) {
            FightingSkill skill3 = manData.getSkillType("9229");
            SendMessage.sendMessageByRoleName(data.getManname(), Agreement.getAgreement().tipAgreement(skill3.getSkillname() + "#W出现异常,联系管理员处理"));
        }
        addState = data.xzstate("刚柔兼备");
        if (addState != null) {
            int fmId = (int)addState.getStateEffect();
            int fmSld = (int)addState.getStateEffect2();
            xs += FaMenUtil.getDouble(fmId, fmSld, 2);
        }
        xs += data.getFamencsJv(2, new String[] { "明镜止水" });
        addState = data.xzstate("气聚神凝");
        if (addState != null) {
            xs += data.getFamencsJv(2, new String[] { "气聚神凝" });
        }
        xs *= 100.0;
        FightingSkill TY_G_10081 = manData.getSkillType(TypeUtil.TY_G_10081);
        if (TY_G_10081 != null && xs < (double)Battlefield.random.nextInt(10000) && PK_MixDeal.isPK(battlefield.BattleType)) {
            xs += TY_G_10081.getSkillhurt();
            manData.getSkills().remove(TY_G_10081);
        }
        boolean isOk = true;
        if (attackType != null && attackType.equals("神力加身")) {
            double dsjv = manData.getFamencsJv(4, new String[] { attackType });
            if ((double)(Battlefield.random.nextInt(10000) + 1) <= dsjv * 100.0) {
                isOk = false;
            }
        }
        if (isOk && xs > (double)Battlefield.random.nextInt(10000)) {
            return initFightingState(data, "躲闪");
        }
        if (type == 1) {
            addState = data.xzstate(TypeUtil.TZ_BDBQ);
            if (addState != null) {
                return initFightingState(data, "免疫");
            }
            addState = data.xzstate(TypeUtil.TY_S_XCWM);
            if (addState != null && Battlefield.isV(addState.getStateEffect2())) {
                return initFightingState(data, "免疫");
            }
            FightingSkill skill4 = data.getAppendSkill(9282);
            if (skill4 != null && Battlefield.isV(skill4.getSkillhurt())) {
                return initFightingState(data, "免疫");
            }
        }
        else if (type == 2) {
            addState = data.xzstate(TypeUtil.TZ_MXJX);
            if (addState != null) {
                return initFightingState(data, "免疫");
            }
        }
        if (skillId >= 1061 && skillId <= 1075) {
            FightingSkill skill4 = data.getAppendSkill(9411);
            if (skill4 != null && Battlefield.isV(skill4.getSkillhurt())) {
                return initFightingState(data, "免疫");
            }
        }
        if (Sepcies_MixDeal.isSex(manData.getSe(), data.getSe()) == 2) {
            double idsv = 0.0;
            FightingSkill skill5 = data.getAppendSkill(9410);
            FightingSkill TY_G_10082 = data.getSkillType(TypeUtil.TY_G_10082);
            if (TY_G_10082 != null) {
                idsv += TY_G_10082.getSkillhurt();
                data.getSkills().remove(TY_G_10082);
            }
            if (skill5 != null && Battlefield.isV(skill5.getSkillhurt() + idsv)) {
                return initFightingState(data, "免疫");
            }
        }
        if ((skillId >= 1001 && skillId <= 1015) || (skillId >= 1071 && skillId <= 1075)) {
            addState = data.xzstate("焕然新生");
            if (addState != null && Battlefield.isV(25.0)) {
                return initFightingState(data, "免疫");
            }
        }
        addState = data.xzstate(TypeUtil.TY_SSC_LFHX);
        if (addState != null) {
            if (type == 2 && addState.getStateEffect2() >= 4.0) {
                return initFightingState(data, "免疫");
            }
            if (type == 1 && addState.getStateEffect2() >= 5.0) {
                return initFightingState(data, "免疫");
            }
        }
        return null;
    }
    
    public static int SkillType(int Id) {
        if ((Id >= 1001 && Id <= 1015) || (Id >= 1071 && Id <= 1075)) {
            return 1;
        }
        if ((Id >= 1016 && Id <= 1025) || (Id >= 1041 && Id <= 1070)) {
            return 2;
        }
        return 0;
    }
    
    public static FightingState initFightingState(ManData data, String type) {
        FightingState fightingState = new FightingState();
        fightingState.setCamp(data.getCamp());
        fightingState.setMan(data.getMan());
        fightingState.setStartState(TypeUtil.JN);
        fightingState.setProcessState(type);
        return fightingState;
    }
    
    public static void rid(ManData data, FightingState state, List<AddState> rAddStates, Battlefield battlefield) {
        for (int i = 0; i < rAddStates.size(); ++i) {
            ((AddState)rAddStates.get(i)).rid(data, state, battlefield);
            ((AddState)rAddStates.get(i)).rid(data, state);
        }
    }
    
    public static void rid(ManData data, FightingState state, List<AddState> rAddStates) {
        for (int i = 0; i < rAddStates.size(); ++i) {
            ((AddState)rAddStates.get(i)).rid(data, state);
        }
    }
    
    public static double getXS(long p) {
        if (p >= 1500L) {
            p = 1500L;
        }
        return 0.04 * (double)p;
    }
    
    public static double getXS1(long p) {
        if (p >= 1500L) {
            p = 1500L;
        }
        return 0.045 * (double)p;
    }
    
    public static int getTYSkillId(int id) {
        if (id == 9151 || id == 9857) {
            return 1005;
        }
        if (id == 9870) {
            return 1010;
        }
        if (id == 9130 || id == 9881) {
            return 1015;
        }
        if (id == 9169 || id == 9170 || id == 9171) {
            return 1020;
        }
        if (id == 9350 || id == 9352 || id == 10055) {
            return 1075;
        }
        if (id == 9189 || id == 9190) {
            return 1025;
        }
        if (id == 9207) {
            return 1030;
        }
        if (id == 9208) {
            return 1029;
        }
        if (id == 9231) {
            return 1039;
        }
        if (id == 9232) {
            return 1040;
        }
        if (id == 9250) {
            return 1034;
        }
        if (id == 9251 || id == 9252 || id == 10047) {
            return 1035;
        }
        if (id == 9286 || id == 9287) {
            return 1055;
        }
        if (id == 9307) {
            return 1045;
        }
        if (id == 9372) {
            return 1065;
        }
        if (id == 9611 || id == 9612) {
            return 1095;
        }
        if (id == 9610) {
            return 1094;
        }
        if (id == 9510 || id == 9512) {
            return 1085;
        }
        if (id == 9710) {
            return 1100;
        }
        if (id == 9711) {
            return 1099;
        }
        if (id == 9811) {
            return 1089;
        }
        if (id == 9812) {
            return 1090;
        }
        if (id == 9111) {
            return 9111;
        }
        return 0;
    }
    
    public static void BB_TJTT(ManData myData, long Zap, int nocamp, FightingSkill skill, Battlefield battlefield) {
        int size = (int)skill.getSkillhurt();
        for (int i = 0; i < 4 && Battlefield.isV((double)(66 - i * 6)); ++i) {
            ++size;
        }
        if (size > 8) {
            size = 8;
        }
        FightingEvents gjEvents = new FightingEvents();
        List<FightingState> zls = new ArrayList<>();
        FightingState gjz = new FightingState();
        gjz.setCamp(myData.getCamp());
        gjz.setMan(myData.getMan());
        gjz.setStartState("法术攻击");
        zls.add(gjz);
        gjEvents.setAccepterlist(zls);
        battlefield.NewEvents.add(gjEvents);
        List<ManData> datas = get(false, myData, 0, nocamp, 0, 0, 0, 0, 10, -1, battlefield, 0);
        gjEvents = new FightingEvents();
        zls = new ArrayList<>();
        gjz = new FightingState();
        gjz.setCamp(myData.getCamp());
        gjz.setMan(myData.getMan());
        gjz.setStartState("特效1");
        gjz.setEndState("3");
        zls.add(gjz);
        if (datas.size() != 0) {
            List<ManData> list = new ArrayList<>();
            int j = 0;
            while (j < size) {
                if (datas.size() == 0) {
                    break;
                }
                else {
                    ManData data = (ManData)datas.get(Battlefield.random.nextInt(datas.size()));
                    if (data.getStates() != 0) {
                        datas.remove(data);
                    }
                    else {
                        list.add(data);
                        int vs = 0;
                        for (int k = list.size() - 1; k >= 0; --k) {
                            if (list.get(k) == data) {
                                ++vs;
                            }
                        }
                        if (vs >= 3) {
                            datas.remove(data);
                        }
                        FightingState ace1 = new FightingState();
                        ChangeFighting acec1 = new ChangeFighting();
                        long ap = (long)PhyAttack.Hurt(Zap, 1, myData, data, TypeUtil.PTGJ, null, zls, null, 0.0, 0.0);
                        acec1.setChangehp((int)(-ap));
                        acec1.setChangevlaue(20.0);
                        acec1.setChangetype(TypeUtil.BB_TJTT);
                        acec1.setChangesum(2);
                        FightingPackage.ChangeProcess(acec1, null, data, ace1, TypeUtil.PTGJ, zls, battlefield);
                    }
                    ++j;
                }
            }
        }
        gjEvents.setAccepterlist(zls);
        battlefield.NewEvents.add(gjEvents);
        move(myData.getCamp(), myData.getMan(), "瞬移", myData.getCamp() + "|" + myData.getMan(), battlefield);
    }
    
    public static void passiveSpellAction(ManData manData, String skillType, List<FightingState> accepterList, Battlefield battlefield, List<ManData> datas, double mzl) {
        FightingSkill skill = null;
        int n = -1;
        switch (skillType.hashCode()) {
            case 39118: {
                if (skillType.equals("风")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 38647: {
                if (skillType.equals("雷")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 27700: {
                if (skillType.equals("水")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 28779: {
                if (skillType.equals("火")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 892762: {
                if (skillType.equals("混乱")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 751503: {
                if (skillType.equals("封印")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 840498: {
                if (skillType.equals("昏睡")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 648005: {
                if (skillType.equals("中毒")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 1223466: {
                if (skillType.equals("震慑")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 1260719: {
                if (skillType.equals("鬼火")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 1170017: {
                if (skillType.equals("遗忘")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 1238746: {
                if (skillType.equals("霹雳")) {
                    n = 11;
                    break;
                }
                else {
                    break;
                }
            }
            case 890139: {
                if (skillType.equals("沧波")) {
                    n = 12;
                    break;
                }
                else {
                    break;
                }
            }
            case 967934: {
                if (skillType.equals("甘霖")) {
                    n = 13;
                    break;
                }
                else {
                    break;
                }
            }
            case 807057: {
                if (skillType.equals("扶摇")) {
                    n = 14;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14: {
                manData.setPhyAttack(true);
                skill = manData.getSkillType("积羽沉舟");
                if (skill != null) {
                    passiveSpellAction(manData, skill, accepterList, battlefield, datas);
                }
                List<ManData> manDataList = battlefield.getBattleRoleDataByCamp(battlefield.nomy(manData.getCamp()));
                int i = 0;
                while (i < manDataList.size()) {
                    ManData data = (ManData)manDataList.get(i);
                    skill = data.getSkillType("扑朔迷离");
                    if (skill != null && mzl >= 0.8) {
                        passiveSpellAction(manData, skill, accepterList, battlefield);
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                break;
            }
        }
    }
    
    public static void passiveSpellAction(ManData manData, FightingSkill skill, List<FightingState> accepterList, Battlefield battlefield) {
        String type = skill.getSkilltype();
        FightingState Accepter = new FightingState();
        ChangeFighting changeFighting = new ChangeFighting();
        changeFighting.setChangetype(type);
        changeFighting.setChangesum(2);
        changeFighting.setChangevlaue((double)skill.getSkillid());
        changeFighting.setChangevlaue((double)(int)manData.getFmsld2());
        FightingPackage.ChangeProcess(changeFighting, null, manData, Accepter, type, accepterList, battlefield);
    }
    
    private static void passiveSpellAction(ManData manData, FightingSkill skill, List<FightingState> accepterList, Battlefield battlefield, List<ManData> datas) {
        if (datas == null) {
            return;
        }
        datas = new ArrayList(datas);
        Collections.shuffle(datas);
        String type = skill.getSkilltype();
        int skillId = skill.getSkillid();
        int fmsld = (int)manData.getFmsld2();
        int mubiaosum = FaMenUtil.getInt(skillId, fmsld, 1);
        int huihesum = FaMenUtil.getInt(skillId, fmsld, 3);
        if (mubiaosum > datas.size()) {
            mubiaosum = datas.size();
        }
        datas = datas.subList(0, mubiaosum);
        for (int i = 0; i < datas.size(); ++i) {
            ManData data = (ManData)datas.get(i);
            if (data.getStates() == 0) {
                FightingState Accepter = new FightingState();
                ChangeFighting changeFighting = new ChangeFighting();
                changeFighting.setChangetype(type);
                changeFighting.setChangesum(huihesum);
                changeFighting.setChangevlaue((double)skillId);
                changeFighting.setChangevlaue((double)fmsld);
                data.setDffmsld(fmsld);
                data.setNrjycz(FaMenUtil.getInt(skillId, fmsld, 2));
                FightingPackage.ChangeProcess(changeFighting, manData, data, Accepter, type, accepterList, battlefield);
            }
        }
    }
    
    static {
        MixDeal.ZYJL = null;
        MixDeal.KAN1 = "看我的#Y";
        MixDeal.KAN2 = "#46";
        MixDeal.FG = "|";
        MixDeal.DH = "=";
    }
}
