package come.tool.FightingSpellAction;

import java.util.List;
import come.tool.FightingData.FightingSummon;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.SummonType;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import com.gl.util.FaMenUtil;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.ManData;

public class FM_ZJZTAction implements SpellAction
{
    @Override
    public void spellAction(ManData manData, FightingSkill skill, FightingEvents events, Battlefield battlefield) {
        int mubiaoSum = 0;
        int huiheSum = 0;
        int skillId = skill.getSkillid();
        int fmsld = (int)manData.getFmsld(skillId);
        if (fmsld == 0) {
            return;
        }
        switch (skillId) {
            case 22002:
            case 22008:
            case 22014:
            case 22020: {
                mubiaoSum = FaMenUtil.getInt(skillId + "", fmsld, 1);
                huiheSum = FaMenUtil.getInt(skillId + "", fmsld, 4);
                break;
            }
            case 22004: {
                mubiaoSum = 1;
                huiheSum = 3;
                break;
            }
            case 22010: {
                mubiaoSum = FaMenUtil.getInt(skillId + "", fmsld, 1);
                huiheSum = FaMenUtil.getInt(skillId + "", fmsld, 2);
                break;
            }
            case 22018: {
                mubiaoSum = 5;
                huiheSum = 3;
                break;
            }
            case 22024: {
                mubiaoSum = FaMenUtil.getInt(skillId + "", fmsld, 1);
                huiheSum = 9999;
                break;
            }
            case 22028: {
                mubiaoSum = 1;
                huiheSum = FaMenUtil.getInt(skillId + "", fmsld, 1);
                break;
            }
            case 22030: {
                mubiaoSum = FaMenUtil.getInt(skillId + "", fmsld, 1);
                huiheSum = 3;
                break;
            }
        }
        List<FightingState> Accepterlist = new ArrayList<>();
        List<ManData> datas;
        if (skillId == 22024) {
            ManData data = null;
            if (events.getAccepterlist() != null && events.getAccepterlist().size() > 0) {
                int path = battlefield.Datapath(((FightingState)events.getAccepterlist().get(0)).getCamp(), ((FightingState)events.getAccepterlist().get(0)).getMan());
                if (path != -1) {
                    data = (ManData)battlefield.fightingdata.get(path);
                    --mubiaoSum;
                }
            }
            datas = SSCAction.minhp(manData.getCamp(), mubiaoSum, battlefield);
            if (data != null) {
                datas.add(data);
            }
        }
        else {
            datas = MixDeal.getjieshou(events, skill, manData, Accepterlist, battlefield, mubiaoSum);
        }
        FightingState Originator = events.getOriginator();
        SummonType.xianzhi(manData, skill);
        Originator.setStartState("法术攻击");
        Originator.setSkillsy(skill.getSkillname());
        String type = skill.getSkilltype();
        for (int j = 0; j < datas.size(); ++j) {
            ManData data2 = (ManData)datas.get(j);
            FightingState Accepter = new FightingState();
            ChangeFighting changeFighting = new ChangeFighting();
            changeFighting.setChangetype(type);
            changeFighting.setChangesum(huiheSum);
            changeFighting.setChangevlaue((double)skillId);
            switch (skillId) {
                case 22008: {
                    if (data2.getMan() == manData.getMan()) {
                        changeFighting.setChangevlaue2((double)(fmsld * 2));
                        break;
                    }
                    else {
                        changeFighting.setChangevlaue2((double)fmsld);
                        break;
                    }
                }
                case 22024: {
                    FightingState fightingState = new FightingState();
                    ChangeFighting changeFightinghf = new ChangeFighting();
                    changeFightinghf.setChangehp(FaMenUtil.getInt(skillId, fmsld, 2));
                    data2.ChangeData(changeFightinghf, fightingState);
                    fightingState.setStartState("药");
                    Accepterlist.add(fightingState);
                    data2.setNrfhht(FaMenUtil.getInt(skillId, fmsld, 3));
                    changeFighting.setChangevlaue2((double)fmsld);
                    break;
                }
                default: {
                    changeFighting.setChangevlaue2((double)fmsld);
                    break;
                }
            }
            FightingPackage.ChangeProcess(changeFighting, manData, data2, Accepter, type, Accepterlist, battlefield);
            data2.initFmSkill();
            switch (data2.fmshfssx()) {
                case 1: {
                    Accepter.setUp("HP", data2.getHp_z());
                    break;
                }
                case 2: {
                    Accepter.setUp("MP", data2.getMp_z());
                    break;
                }
            }
        }
        if (skillId == 22018) {
            List<ManData> dataList = battlefield.getBattleRoleDataByCamp(manData.getCamp());
            for (int i = 0; i < dataList.size(); ++i) {
                ManData data3 = (ManData)dataList.get(i);
                int wei = battlefield.Datapathhuo(data3.getCamp(), data3.getMan() + 5);
                if (wei == -1 && (double)Battlefield.random.nextInt(10000) < FaMenUtil.getDouble(skillId, fmsld, 3) * 100.0) {
                    for (int k = 0; k < data3.getPets().size(); ++k) {
                        FightingSummon fs = (FightingSummon)data3.getPets().get(k);
                        if (fs.getPlay() == 0) {
                            int p = fs.getsx(0.0);
                            if (p == 2) {
                                MixDeal.first(data3, "闪现&" + fs.getHang().getId(), battlefield);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (events.getOriginator() != null) {
            Accepterlist.add(Originator);
        }
        events.setOriginator(null);
        if (Accepterlist.size() != 0) {
            events.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(events);
        }
    }
    
    public static void santiaochong(int skillid, ManData manData, int mubiaosum, Battlefield battlefield, String type) {
        int camp = manData.getCamp();
        FightingEvents fEvents = new FightingEvents();
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState Accepter = new FightingState();
        if (skillid == 22024 && (int)manData.getFmsld("法魂护体") > 0) {
            List<ManData> datas1 = SSCAction.minhp(camp, mubiaosum, battlefield);
            for (int i = 0; i < mubiaosum && i < datas1.size(); ++i) {
                ManData data = (ManData)datas1.get(i);
                int hp = (int)manData.getFmsld("法魂护体") * 22;
                FightingState fightingState = new FightingState();
                ChangeFighting changeFightinghf = new ChangeFighting();
                changeFightinghf.setChangehp(hp);
                data.ChangeData(changeFightinghf, fightingState);
                fightingState.setStartState("药");
                ChangeFighting changeFighting = new ChangeFighting();
                changeFighting.setChangetype("法魂护体");
                changeFighting.setChangevlaue((double)((int)manData.getFmsld("法魂护体") * 14));
                changeFighting.setChangesum(3);
                FightingPackage.ChangeProcess(changeFighting, manData, data, Accepter, type, Accepterlist, battlefield);
                Accepterlist.add(fightingState);
            }
        }
        if (Accepterlist.size() != 0) {
            fEvents.setAccepterlist(Accepterlist);
            battlefield.NewEvents.add(fEvents);
        }
    }
}
