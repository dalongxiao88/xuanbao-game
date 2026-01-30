package org.come.tool;

import java.util.HashMap;
import java.util.Arrays;
import java.util.Iterator;
import come.tool.FightingData.AddState;
import come.tool.FightingData.FightingPackage;
import come.tool.FightingData.ChangeFighting;
import come.tool.FightingData.MixDeal;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingState;
import come.tool.FightingData.ManData;
import org.come.entity.RoleSummoning;
import java.util.Map;
import java.util.List;

public class TalentTool
{
    private static final List<String> TALENT_PET_TYPE;
    private static final Map<String, Integer> PET_TALENT_MAX_LVL;
    
    public static String isAllowReadTalent(RoleSummoning pet) {
        if (!TalentTool.PET_TALENT_MAX_LVL.keySet().contains(pet.getSsn())) {
            return "该召唤兽无法学习天赋";
        }
        Integer maxLvl = (Integer)TalentTool.PET_TALENT_MAX_LVL.get(pet.getSsn());
        int talentLvl = pet.getTalentLvl();
        if (talentLvl >= (int)maxLvl) {
            return "该召唤兽天赋已到最大阶数";
        }
        return null;
    }
    
    public static int getPetTalentLvl(RoleSummoning pet) {
        if (!TalentTool.PET_TALENT_MAX_LVL.keySet().contains(pet.getSsn())) {
            return 0;
        }
        Integer maxLvl = (Integer)TalentTool.PET_TALENT_MAX_LVL.get(pet.getSsn());
        int talentLvl = pet.getTalentLvl();
        if (talentLvl > (int)maxLvl) {
            talentLvl = (int)maxLvl;
        }
        return talentLvl;
    }
    
    public static void triggerTalentSkill(ManData manData, List<FightingState> accepterList, Battlefield battlefield) {
        if (manData.getType() == 1 && manData.getTalentLvl() > 0) {
            List<ManData> datas = MixDeal.get(false, null, 0, manData.getCamp(), 1, 2, 0, 0, 5, -1, battlefield, 1);
            for (int i = 0; i < datas.size(); ++i) {
                double probability = manData.getTalentProbability((ManData)datas.get(i));
                if (getState((ManData)datas.get(i)) == null && Battlefield.isV(probability)) {
                    ChangeFighting changeFighting = new ChangeFighting();
                    changeFighting.setChangetype(getTalentName(manData.getTalentLvl()));
                    changeFighting.setChangesum(1);
                    FightingState Accepter = new FightingState();
                    FightingPackage.ChangeProcess(changeFighting, manData, (ManData)datas.get(i), Accepter, "天赋", accepterList, battlefield);
                    return;
                }
            }
        }
    }
    
    public static AddState getState(ManData manData) {
        return getStateByLvl(manData, 1);
    }
    
    public static AddState getStateByLvl(ManData manData, int lvl) {
        List<AddState> states = manData.getAddStates();
        if (states != null && states.size() != 0) {
            for (AddState state : states) {
                if (state.getStatename().endsWith("天赋") && getTalentLvl(state.getStatename()) >= lvl) {
                    return state;
                }
            }
        }
        return null;
    }
    
    public static String getTalentName(int lvl) {
        String name = "天赋";
        switch (lvl) {
            case 1: {
                name = "一阶天赋";
                break;
            }
            case 2: {
                name = "二阶天赋";
                break;
            }
            case 3: {
                name = "三阶天赋";
                break;
            }
            case 4: {
                name = "四阶天赋";
                break;
            }
            case 5: {
                name = "五阶天赋";
                break;
            }
        }
        return name;
    }
    
    public static int getTalentLvl(String name) {
        int lvl = 0;
        int n = -1;
        switch (name.hashCode()) {
            case 632564728: {
                if (name.equals("一阶天赋")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 636735468: {
                if (name.equals("二阶天赋")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 632832847: {
                if (name.equals("三阶天赋")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 700100925: {
                if (name.equals("四阶天赋")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 636973796: {
                if (name.equals("五阶天赋")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                lvl = 1;
                break;
            }
            case 1: {
                lvl = 2;
                break;
            }
            case 2: {
                lvl = 3;
                break;
            }
            case 3: {
                lvl = 4;
                break;
            }
            case 4: {
                lvl = 5;
                break;
            }
        }
        return lvl;
    }
    
    static {
        TALENT_PET_TYPE = Arrays.asList(new String[] { "1" });
        (PET_TALENT_MAX_LVL = new HashMap<>()).put("1", Integer.valueOf(4));
        TalentTool.PET_TALENT_MAX_LVL.put("5", Integer.valueOf(4));
        TalentTool.PET_TALENT_MAX_LVL.put("6", Integer.valueOf(5));
        TalentTool.PET_TALENT_MAX_LVL.put("2", Integer.valueOf(5));
        TalentTool.PET_TALENT_MAX_LVL.put("3", Integer.valueOf(5));
        TalentTool.PET_TALENT_MAX_LVL.put("4", Integer.valueOf(5));
    }
}
