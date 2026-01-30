package come.tool.FightingEffect;

import java.util.Iterator;
import com.tool.tcp.NewPart;
import org.come.Frame.ZhuFrame;
import come.tool.Fighting.FightingMove2;
import come.tool.Fighting.ShadowMode;
import com.tool.tcp.SpriteFactory;
import org.come.until.ScrenceUntil;
import com.tool.tcp.GetTcpPath;
import come.tool.Fighting.SkillSpell;
import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingManData;
import java.io.File;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.FightingState;
import come.tool.Fighting.FightingMixDeal;

public enum EffectType
{
    Null(null), 
    Move(new MoveEffect()), 
    Attack(new AttackEffect()), 
    Pet(new Pet()), 
    Escape(new EscapeEffect()), 
    PetCatch(new PetCatch()), 
    MoveSkill(new MoveSkillEffect());
    
    private Effect action;
    
    private EffectType(Effect action) {
        this.action = action;
    }
    
    public Effect getTarget() {
        return this.action;
    }
    
    public static Effect getEffectById(int effectId) {
        EffectType[] values = values();
        EffectType effectType = values[effectId];
        return effectType.getTarget();
    }
    
    public static int getdir(int dir, int camp) {
        if (camp == FightingMixDeal.camp || (camp == 1 && FightingMixDeal.camp == -1)) {
            if (dir == 7) {
                dir = 3;
            }
            else if (dir == 3) {
                dir = 7;
            }
            else if (dir == 2) {
                dir = 6;
            }
            else if (dir == 6) {
                dir = 2;
            }
            else if (dir == 5) {
                dir = 1;
            }
            else if (dir == 1) {
                dir = 5;
            }
        }
        return dir;
    }
    
    public static String getMusicName(FightingState state) {
        int index = FightingMixDeal.CurrentData(state.getCamp(), state.getMan());
        Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(index);
        FightingManData fightingManData = fightingimage.getFightingManData();
        if (fightingManData != null && state.getSkillsy() != null && fightingManData.getSpeciesid() != null) {
            String mnamme = "";
            String skillsy = state.getSkillsy();
            int n = -1;
            switch (skillsy.hashCode()) {
                case 103315: {
                    if (skillsy.equals("hit")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 103655853: {
                    if (skillsy.equals("magic")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case -1335637322: {
                    if (skillsy.equals("defend")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 99456: {
                    if (skillsy.equals("die")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case -1407259064: {
                    if (skillsy.equals("attack")) {
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
                    mnamme = fightingManData.getSpeciesid().toString() + state.getSkillsy();
                    break;
                }
                case 1: {
                    mnamme = fightingManData.getSpeciesid().toString() + state.getSkillsy();
                    break;
                }
                case 2: {
                    mnamme = fightingManData.getSpeciesid().toString() + state.getSkillsy();
                    break;
                }
                case 3: {
                    mnamme = fightingManData.getSpeciesid().toString() + state.getSkillsy();
                    break;
                }
                case 4: {
                    mnamme = fightingManData.getSpeciesid().toString() + state.getSkillsy();
                    break;
                }
                default: {
                    mnamme = fightingManData.getSpeciesid().toString() + state.getSkillsy();
                    break;
                }
            }
            File file = new File("resource/music/" + mnamme + ".mp3");
            if (file.exists()) {
                return mnamme;
            }
            return state.getSkillsy();
        }
        else if (fightingManData != null && state.getStartState() != null && state.getStartState().equals("法术攻击") && state.getEndState() != null && fightingManData.getSpeciesid() != null) {
            String mnamme = fightingManData.getSpeciesid().toString() + state.getEndState();
            File file = new File("resource/music/" + mnamme + ".mp3");
            if (file.exists()) {
                return mnamme;
            }
            return state.getSkillsy();
        }
        else {
            return state.getSkillsy();
        }
    }
    
    public static String getExtMusicName(FightingState state) {
        int index = FightingMixDeal.CurrentData(state.getCamp(), state.getMan());
        Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(index);
        FightingManData fightingManData = fightingimage.getFightingManData();
        if (fightingManData != null && state.getExtsy() != null && fightingManData.getSpeciesid() != null) {
            String mnamme = "";
            String extsy = state.getExtsy();
            int n = -1;
            switch (extsy.hashCode()) {
                case 99456: {
                    if (extsy.equals("die")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    mnamme = fightingManData.getSpeciesid().toString() + state.getExtsy();
                    break;
                }
                default: {
                    mnamme = fightingManData.getSpeciesid().toString() + state.getExtsy();
                    break;
                }
            }
            File file = new File("resource/music/" + mnamme + ".mp3");
            if (file.exists()) {
                return mnamme;
            }
            return state.getExtsy();
        }
        else {
            return state.getExtsy();
        }
    }
    
    public static StateProgress getProgress(FightingState State, int path) {
        StateProgress progress = new StateProgress();
        progress.setSkillName(State.getSkillsy());
        progress.setMan(path);
        progress.setData2(State.getStartState());
        progress.setData(State.getProcessState());
        progress.setBuff(State.getBuff());
        if (State.getHp_Change() != null) {
            progress.setHp_Change(State.getHp_Change().intValue());
        }
        if (State.getMp_Change() != null) {
            progress.setMp_Change(State.getMp_Change().intValue());
        }
        if (State.getYq_c() != null) {
            progress.setYq_Change(State.getYq_c().intValue());
        }
        if (State.getNq_c() != null) {
            progress.setNq_Change(State.getNq_c().intValue());
        }
        progress.setAddchixu(State.getEndState_1());
        if (State.getSp() != null) {
            progress.setSp(State.getSp());
        }
        progress.setDeletechixu(State.getEndState_2());
        progress.setAddchixu1(State.getState_3());
        progress.setMusic(getMusicName(State));
        progress.setExtSound(getExtMusicName(State));
        progress.setText(State.getText());
        Fightingimage image = (Fightingimage)FightingMixDeal.CurrentData.get(path);
        if (State.getUp() != null && !State.getUp().equals("")) {
            String[] vs = State.getUp().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].startsWith("HP")) {
                    image.getFightingManData().setHp_Total(Integer.parseInt(vs[i].split("=")[1]));
                }
                else if (vs[i].startsWith("MP")) {
                    image.getFightingManData().setMp_Total(Integer.parseInt(vs[i].split("=")[1]));
                }
            }
        }
        if (State.getSkin() != null && !State.getSkin().equals("")) {
            image.getFightingManData().setModel(State.getSkin());
            NewPart part = image.getFightingManData().getPart();
            if (image.getFightingManData().getHp_Current() <= 0) {
                part.setAct(8);
            }
            else {
                part.setAct(7);
            }
            image.setPart(part);
        }
        if (State.getSkillskin() != null && !State.getSkillskin().equals("4000")) {
            SkillSpell skill = new SkillSpell();
            String skin = State.getSkillskin();
            int skinID = 0;
            try {
                if (skin.charAt(0) >= '0' && skin.charAt(0) <= '9') {
                    skinID = Integer.parseInt(skin);
                }
            }
            catch (Exception ex) {}
            if (GetTcpPath.STRTMPXJ != null) {
                if (GetTcpPath.STRTMPXJ.equals("1")) {
                    if (GetTcpPath.STRTMP != null && GetTcpPath.STRTMP.equals("1")) {
                        if (skin.equals("1005")) {
                            skin = "10051";
                            skinID = 100511;
                        }
                        if (skin.equals("1010")) {
                            skin = "10101";
                            skinID = 101011;
                        }
                        if (skin.equals("1015")) {
                            skin = "10151";
                            skinID = 101511;
                        }
                        if (skin.equals("1020")) {
                            skin = "10201";
                            skinID = 102011;
                        }
                        if (skin.equals("1025")) {
                            skin = "10251";
                            skinID = 102511;
                        }
                        if (skin.equals("1030")) {
                            skin = "10301";
                            skinID = 103011;
                        }
                        if (skin.equals("1035")) {
                            skin = "10351";
                            skinID = 103511;
                        }
                        if (skin.equals("1040")) {
                            skin = "10401";
                            skinID = 104011;
                        }
                        if (skin.equals("1045")) {
                            skin = "10451";
                            skinID = 104511;
                        }
                        if (skin.equals("1050")) {
                            skin = "10501";
                            skinID = 105011;
                        }
                        if (skin.equals("1055")) {
                            skin = "10551";
                            skinID = 105511;
                        }
                        if (skin.equals("1060")) {
                            skin = "10601";
                            skinID = 106011;
                        }
                        if (skin.equals("1065")) {
                            skin = "10651";
                            skinID = 106511;
                        }
                        if (skin.equals("1070")) {
                            skin = "10701";
                            skinID = 107011;
                        }
                        if (skin.equals("1075")) {
                            skin = "10751";
                            skinID = 107511;
                        }
                        if (skin.equals("1080")) {
                            skin = "10801";
                            skinID = 108011;
                        }
                        if (skin.equals("1085")) {
                            skin = "10851";
                            skinID = 108511;
                        }
                        if (skin.equals("1090")) {
                            skin = "10901";
                            skinID = 109011;
                        }
                        if (skin.equals("1095")) {
                            skin = "10951";
                            skinID = 109511;
                        }
                        if (skin.equals("1100")) {
                            skin = "11001";
                            skinID = 110011;
                        }
                    }
                    if (skinID == 1830 || skinID == 1867 || (skinID >= 3036 && skinID <= 3044)) {
                        if (State.getCamp() == FightingMixDeal.camp) {
                            skill.setX(ScrenceUntil.Screen_x / 4 * 3);
                            skill.setDir(1);
                        }
                        else {
                            skill.setX(ScrenceUntil.Screen_x / 4 * 1);
                        }
                        skill.setY(ScrenceUntil.Screen_y / 2);
                        skill.setSkinpath(1);
                    }
                    else if (skinID == 6020 ||skinID == 80000) {//反隐特效唯一
                        if (State.getCamp() == FightingMixDeal.camp) {
                            skill.setX(ScrenceUntil.Screen_x / 4 * 3);
                            skill.setDir(1);
                        }
                        else {
                            skill.setX(ScrenceUntil.Screen_x / 4 * 1);
                        }
                        skill.setY(ScrenceUntil.Screen_y / 2);
                        skill.setSkinpath(1);
                    }
                    else if (skinID == 10751 || skinID == 10701 || skinID == 10651 || skinID == 10352 || skinID == 10851 || skinID == 10952 || skinID == 11001 || skinID == 1234 || skinID == 1236 || skinID == 7012 || skinID == 10051 || skinID == 10101 || skinID == 10151 || skinID == 10201 || skinID == 10251 || skinID == 10302 || skinID == 10402 || skinID == 10451 || skinID == 10501 || skinID == 10551 || skinID == 10601 || skinID == 10802 || skinID == 10901 || skinID == 11276 || skinID == 11274 || skinID == 11280 || skinID == 11279 || skinID == 11278 || skinID == 11270 || skinID == 11268 || skin.equals("捕捉")) {
                        if (!oldFull(skinID, skill)) {
                            skill.setX(ScrenceUntil.Screen_x / 2);
                            skill.setY(ScrenceUntil.Screen_y / 2);
                            skill.setSkinpath(1);
                        }
                    }
                    else if (skinID == 4001 || skinID == 4002 || skinID == 4003 || skinID == 4004) {
                        int dir = getdir(3, image.getFightingManData().getCamp());
                        NewPart part2 = SpriteFactory.createPart(skin, -1, 0, null);
                        ShadowMode shadowMode = new ShadowMode(part2, (dir == 3) ? (image.getX() + 70) : (image.getX() - 70), (dir == 3) ? (image.getY() + 30) : (image.getY() - 30), null, (dir == 3) ? 1 : 0);
                        skill.setShadowMode(shadowMode);
                    }
                    else if (skinID == 1297) {
                        skill.setX(image.getX());
                        skill.setY(image.getY());
                        skill.setDir(0);
                        Fightingimage image2 = null;
                        for (Fightingimage currentDatum : FightingMixDeal.CurrentData) {
                            if (currentDatum.getFightingManData().getCamp() == 0 && currentDatum.getFightingManData().getMan() == (int)State.getSglxTag()) {
                                image2 = currentDatum;
                                break;
                            }
                        }
                        if (image2 != null) {
                            String s = FightingMove2.calculateRelativePosition(image.getX(), image.getY(), image2.getX(), image2.getY());
                            if (s.equals("上右")) {
                                skill.setX(image.getX() - 20);
                                skill.setY(image.getY() - 60);
                                skill.setDir(11);
                            }
                            else if (s.equals("下左") || s.equals("上左")) {
                                if (s.equals("下左")) {
                                    skill.setDir(1);
                                }
                                else {
                                    skill.setX(image.getX() - 120);
                                    skill.setY(image.getY() - 90);
                                    skill.setDir(0);
                                }
                            }
                            else if (s.equals("下右")) {
                                if (State.getMan() == 2) {
                                    skill.setDir(1);
                                }
                                else {
                                    skill.setDir(3);
                                }
                            }
                        }
                    }
                    else {
                        skill.setX(image.getX());
                        skill.setY(image.getY());
                        skill.setSkinpath(0);
                    }
                    skill.setSkillid(GetTcpPath.getSkillTcp(skin));
                    progress.setSpell(skill);
                }
                else {
                    if (GetTcpPath.STRTMP != null && GetTcpPath.STRTMP.equals("1")) {
                        if (skin.equals("1005")) {
                            skin = "10051";
                            skinID = 10051;
                        }
                        if (skin.equals("1010")) {
                            skin = "10101";
                            skinID = 10101;
                        }
                        if (skin.equals("1015")) {
                            skin = "10151";
                            skinID = 10151;
                        }
                        if (skin.equals("1020")) {
                            skin = "10201";
                            skinID = 10201;
                        }
                        if (skin.equals("1025")) {
                            skin = "10251";
                            skinID = 10251;
                        }
                        if (skin.equals("1030")) {
                            skin = "10301";
                            skinID = 10301;
                        }
                        if (skin.equals("1035")) {
                            skin = "10351";
                            skinID = 10351;
                        }
                        if (skin.equals("1040")) {
                            skin = "10401";
                            skinID = 10401;
                        }
                        if (skin.equals("1045")) {
                            skin = "10451";
                            skinID = 10451;
                        }
                        if (skin.equals("1050")) {
                            skin = "10501";
                            skinID = 10501;
                        }
                        if (skin.equals("1055")) {
                            skin = "10551";
                            skinID = 10551;
                        }
                        if (skin.equals("1060")) {
                            skin = "10601";
                            skinID = 10601;
                        }
                        if (skin.equals("1065")) {
                            skin = "10651";
                            skinID = 10651;
                        }
                        if (skin.equals("1070")) {
                            skin = "10701";
                            skinID = 10701;
                        }
                        if (skin.equals("1075")) {
                            skin = "10751";
                            skinID = 10751;
                        }
                        if (skin.equals("1080")) {
                            skin = "10801";
                            skinID = 10801;
                        }
                    }
                    if (skinID == 1830 || skinID == 1867 || skinID == 1090 || (skinID >= 3036 && skinID <= 3044)) {
                        if (State.getCamp() == FightingMixDeal.camp) {
                            skill.setX(ScrenceUntil.Screen_x / 4 * 3);
                            skill.setDir(1);
                        }
                        else {
                            skill.setX(ScrenceUntil.Screen_x / 4 * 1);
                        }
                        skill.setY(ScrenceUntil.Screen_y / 2);
                        skill.setSkinpath(1);
                    }
                    else if (skinID == 6020 ||skinID == 80000) {//反隐特效唯一
                        if (State.getCamp() == FightingMixDeal.camp) {
                            skill.setX(ScrenceUntil.Screen_x / 4 * 3);
                            skill.setDir(1);
                        }
                        else {
                            skill.setX(ScrenceUntil.Screen_x / 4 * 1);
                        }
                        skill.setY(ScrenceUntil.Screen_y / 2);
                        skill.setSkinpath(1);
                    }
                    else if (skinID == 1075 || skinID == 1070 || skinID == 1065 || skinID == 1035 || skinID == 1085 || skinID == 1095 || skinID == 1100 || skinID == 1234 || skinID == 1236 || skinID == 7012 || skinID == 1005 || skinID == 1010 || skinID == 1015 || skinID == 1020 || skinID == 1025 || skinID == 1030 || skinID == 1035 || skinID == 1040 || skinID == 1045 || skinID == 1050 || skinID == 1055 || skinID == 1060 || skinID == 1065 || skinID == 1070 || skinID == 1075 || skinID == 1080 || skinID == 11276 || skinID == 11274 || skinID == 11280 || skinID == 11279 || skinID == 11278 || skinID == 11270 || skinID == 11268 || skin.equals("捕捉")) {
                        if (!oldFull(skinID, skill)) {
                            skill.setX(ScrenceUntil.Screen_x / 2);
                            skill.setY(ScrenceUntil.Screen_y / 2);
                            skill.setSkinpath(1);
                        }
                    }
                    else if (skinID == 4001 || skinID == 4002 || skinID == 4003 || skinID == 4004) {
                        int dir = getdir(3, image.getFightingManData().getCamp());
                        NewPart part2 = SpriteFactory.createPart(skin, -1, 0, null);
                        ShadowMode shadowMode = new ShadowMode(part2, (dir == 3) ? (image.getX() + 70) : (image.getX() - 70), (dir == 3) ? (image.getY() + 30) : (image.getY() - 30), null, (dir == 3) ? 1 : 0);
                        skill.setShadowMode(shadowMode);
                    }
                    else if (skinID == 1297) {
                        skill.setX(image.getX());
                        skill.setY(image.getY());
                        skill.setDir(0);
                        Fightingimage image2 = null;
                        for (Fightingimage currentDatum : FightingMixDeal.CurrentData) {
                            if (currentDatum.getFightingManData().getCamp() == 0 && currentDatum.getFightingManData().getMan() == (int)State.getSglxTag()) {
                                image2 = currentDatum;
                                break;
                            }
                        }
                        if (image2 != null) {
                            String s = FightingMove2.calculateRelativePosition(image.getX(), image.getY(), image2.getX(), image2.getY());
                            if (s.equals("上右")) {
                                skill.setX(image.getX() - 20);
                                skill.setY(image.getY() - 60);
                                skill.setDir(11);
                            }
                            else if (s.equals("下左") || s.equals("上左")) {
                                if (s.equals("下左")) {
                                    skill.setDir(1);
                                }
                                else {
                                    skill.setX(image.getX() - 120);
                                    skill.setY(image.getY() - 90);
                                    skill.setDir(0);
                                }
                            }
                            else if (s.equals("下右")) {
                                if (State.getMan() == 2) {
                                    skill.setDir(1);
                                }
                                else {
                                    skill.setDir(3);
                                }
                            }
                        }
                    }
                    else {
                        skill.setX(image.getX());
                        skill.setY(image.getY());
                        skill.setSkinpath(0);
                    }
                }
                skill.setSkillid(GetTcpPath.getSkillTcp(skin));
                progress.setSpell(skill);
            }
        }
        if (State.getMsg() != null && State.getCamp() == FightingMixDeal.camp && FightingMixDeal.myman() == State.getMan()) {
            ZhuFrame.getZhuJpanel().addPrompt2(State.getMsg());
        }
        return progress;
    }
    
    private static boolean oldFull(int skinID, SkillSpell skill) {
        boolean fal = false;
        if (GetTcpPath.STRTMPXJ != null) {
            if (GetTcpPath.STRTMPXJ.equals("1")) {
                if (1050 == skinID) {
                    fal = true;
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                }
                if (1055 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1045 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1015 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1010 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1005 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1020 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1030 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1040 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1025 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
                if (1035 == skinID) {
                    skill.setX(ScrenceUntil.Screen_x / 2);
                    skill.setY(ScrenceUntil.Screen_y / 2);
                    fal = true;
                }
            }
        }
        else {
            if (1050 == skinID) {
                fal = true;
                skill.setX(ScrenceUntil.Screen_x / 2 - 160);
                skill.setY(ScrenceUntil.Screen_y / 2 - 110);
            }
            if (1055 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2);
                skill.setY(ScrenceUntil.Screen_y / 2);
                fal = true;
            }
            if (1045 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2 - 145);
                skill.setY(ScrenceUntil.Screen_y / 2 - 120);
                fal = true;
            }
            if (1015 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2 - 110);
                skill.setY(ScrenceUntil.Screen_y / 2 - 140);
                fal = true;
            }
            if (1010 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2 - 110);
                skill.setY(ScrenceUntil.Screen_y / 2 - 100);
                fal = true;
            }
            if (1005 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2);
                skill.setY(ScrenceUntil.Screen_y / 2);
                fal = true;
            }
            if (1020 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2 - 170);
                skill.setY(ScrenceUntil.Screen_y / 2 - 140);
                fal = true;
            }
            if (1030 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2 - 125);
                skill.setY(ScrenceUntil.Screen_y / 2 - 115);
                fal = true;
            }
            if (1040 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2 - 165);
                skill.setY(ScrenceUntil.Screen_y / 2 - 130);
                fal = true;
            }
            if (1025 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2 - 160);
                skill.setY(ScrenceUntil.Screen_y / 2 - 110);
                fal = true;
            }
            if (1035 == skinID) {
                skill.setX(ScrenceUntil.Screen_x / 2);
                skill.setY(ScrenceUntil.Screen_y / 2);
                fal = true;
            }
        }
        return fal;
    }
}
