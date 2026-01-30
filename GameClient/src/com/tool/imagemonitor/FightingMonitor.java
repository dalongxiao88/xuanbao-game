package com.tool.imagemonitor;

import org.come.entity.Goodstable;
import java.util.List;
import come.tool.Fighting.FightingState;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import java.util.ArrayList;
import org.come.Frame.ZhuFrame;
import org.come.until.MessagrFlagUntil;
import org.come.Frame.MsgJframe5;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import come.tool.Fighting.FightingEvents;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.Fightingimage;
import org.come.bean.FightOperation;

public class FightingMonitor
{
    public static FightOperation RoleOperation;
    public static FightOperation PetOperation;
    public static int mousestate;
    public static String mousesname;
    
    public static void Fighting(Fightingimage fightingimage) {
        int man = FightingMixDeal.myman();
        if (FightingMixDeal.State == 2) {
            man += 5;
        }
        if (FightingMonitor.mousestate == 0) {
            if (DecisionAttack(man, fightingimage)) {
                FightOperation operation = getOperation();
                operation.Record(fightingimage.getFightingManData().getCamp(), fightingimage.getFightingManData().getMan(), FightingMonitor.mousestate, null);
                execution(operation);
            }
        }
        else if (FightingMonitor.mousestate == 1) {
            if (FightingMonitor.mousesname.equals("一矢中的") && (ziji(man, fightingimage) || fightingimage.getFightingManData().getMan() > 4)) {
                return;
            }
            if (FightingMonitor.mousesname.equals("移形换影") && (ziji(man, fightingimage) || fightingimage.getFightingManData().getMan() > 4)) {
                return;
            }
            if (DecisionSpell(FightingMonitor.mousesname, man, fightingimage)) {
                FightOperation operation = getOperation();
                operation.Record(fightingimage.getFightingManData().getCamp(), fightingimage.getFightingManData().getMan(), FightingMonitor.mousestate, FightingMonitor.mousesname);
                execution(operation);
            }
        }
        else if (FightingMonitor.mousestate == 2) {
            FightOperation operation = getOperation();
            operation.Record(fightingimage.getFightingManData().getCamp(), fightingimage.getFightingManData().getMan(), FightingMonitor.mousestate, FightingMonitor.mousesname);
            execution(operation);
        }
        else if (FightingMonitor.mousestate == 3) {
            if (youfang(fightingimage) && !ziji(man, fightingimage)) {
                FightOperation operation = getOperation();
                operation.Record(fightingimage.getFightingManData().getCamp(), fightingimage.getFightingManData().getMan(), FightingMonitor.mousestate, null);
                execution(operation);
            }
        }
        else if (FightingMonitor.mousestate == 4 && (FightingMixDeal.BattleType == 0 || FightingMixDeal.BattleType == 1 || FightingMixDeal.BattleType == 2) && !youfang(fightingimage)) {
            FightOperation operation = getOperation();
            operation.Record(fightingimage.getFightingManData().getCamp(), fightingimage.getFightingManData().getMan(), FightingMonitor.mousestate, null);
            execution(operation);
        }
    }
    
    public static boolean youfang(Fightingimage fightingimage) {
        return fightingimage.getFightingManData().getCamp() == FightingMixDeal.camp;
    }
    
    public static boolean yinshen(Fightingimage fightingimage) {
        return fightingimage.getFightingManData().getAlpha() == 1.0f;
    }
    
    public static boolean qiankunzhetian(Fightingimage fightingimage) {
        if (fightingimage.getFightingManData().getStates().size() == 0) {
            return false;
        }
        int i = fightingimage.getFightingManData().getStates().size() - 1;
        if (i >= 0) {
            ((String)fightingimage.getFightingManData().getStates().get(i)).equals("乾坤遮天");
            return true;
        }
        return false;
    }
    
    public static boolean ziji(int man, Fightingimage fightingimage) {
        return fightingimage.getFightingManData().getCamp() == FightingMixDeal.camp && fightingimage.getFightingManData().getMan() == man;
    }
    
    public static boolean DecisionAttack(int man, Fightingimage fightingimage) {
        return !ziji(man, fightingimage);
    }
    
    public static boolean DecisionSpell(String skillname, int man, Fightingimage fightingimage) {
        if (skillname.equals("移花接木") || skillname.equals("大手印") || skillname.equals("荆棘束身") || skillname.equals("覆雨") || skillname.equals("御龙") || skillname.equals("引火烧身") || skillname.equals("银索金铃")) {
            return true;
        }
        if (skillname.equals("素手") && ziji(man ,fightingimage)) {
            return false;
        }
        if (skillname.equals("幽怜魅影") ||
                skillname.equals("醉生梦死") ||
                skillname.equals("一曲销魂") ||
                skillname.equals("秦丝冰雾") ||
                skillname.equals("倩女幽魂")
        ) {
            //幽怜魅影
            //醉生梦死
            //一曲销魂
            //秦丝冰雾
            //倩女幽魂
            return true;
        }
        if (Effect(skillname)) {
            return youfang(fightingimage);
        }
        return !youfang(fightingimage);
    }
    
    public static boolean Effect(String skillname) {
        return skillname.equals("绝境逢生") || skillname.equals("流连忘返") ||  skillname.equals("素手") || skillname.equals("子虚乌有") || skillname.equals("春回大地") || skillname.equals("妙手回春") || skillname.equals("春意盎然") || skillname.equals("忠诚") || skillname.equals("自医") || skillname.equals("遗产") || skillname.equals("隐身") || skillname.equals("仁之木叶神") || skillname.equals("智之水叶神") || skillname.equals("礼之火叶神") || skillname.equals("大隐于朝") || skillname.equals("鬼神莫测") || skillname.equals("神出鬼没") || skillname.equals("作鸟兽散") || skillname.equals("将死") || skillname.equals("妖之魔力") || skillname.equals("力神复苏") || skillname.equals("红袖添香") || skillname.equals("莲步轻舞") || skillname.equals("魔之飞步") || skillname.equals("急速之魔") || skillname.equals("幽怜魅影") || skillname.equals("醉生梦死") || skillname.equals("狮王之怒") || skillname.equals("兽王神力") || skillname.equals("魔神附身") || skillname.equals("楚楚可怜") || skillname.equals("魔神护体") || skillname.equals("含情脉脉") || skillname.equals("魔神飞舞") || skillname.equals("天外飞魔") || skillname.equals("乾坤借速") || skillname.equals("一曲销魂") || skillname.equals("秦丝冰雾") || skillname.equals("倩女幽魂") || skillname.equals("泽披天下") || skillname.equals("将军令") || skillname.equals("大势锤") || skillname.equals("七宝玲珑塔") || skillname.equals("黑龙珠") || skillname.equals("幽冥鬼手") || skillname.equals("绝情鞭") || skillname.equals("宝莲灯") || skillname.equals("番天印") || skillname.equals("锦襕袈裟") || skillname.equals("银索金铃") || skillname.equals("飞花溅玉") || skillname.equals("百害不侵") || skillname.equals("明心见性") || skillname.equals("五蕴炽盛") || skillname.equals("清江映雪") || skillname.equals("回光返照") || skillname.equals("吹箫引凤") || skillname.equals("六根清净") || skillname.equals("因缘际会") || skillname.equals("以直报怨") || skillname.equals("如有神助") || skillname.equals("破釜沉舟") || skillname.equals("弱水三千") || skillname.equals("流风回雪") || skillname.equals("岳镇渊渟") || skillname.equals("穿针引线") || skillname.equals("鸿雁长飞") || skillname.equals("销声匿迹") || skillname.equals("一苇渡江") || skillname.equals("苦海慈航") || skillname.equals("拔山") || skillname.equals("七星贯日") || skillname.equals("御龙") || skillname.equals("覆雨") || skillname.equals("拨云见日") || skillname.equals("引火烧身") || skillname.equals("久旱初雨") || skillname.equals("兴云致雨") || skillname.equals("润物无声") || skillname.equals("沛然莫御") || skillname.equals("泽被万物") || skillname.equals("春暖花开") || skillname.equals("瞒天过海") || skillname.equals("双生两仪盾") || skillname.equals("一矢中的") || skillname.equals("凝神一击") || skillname.equals("藏锋蓄势") || skillname.equals("法魂护体") || skillname.equals("幻影迷踪") || skillname.equals("兽魂俯首") || skillname.equals("刚柔兼备") || skillname.equals("开山裂石") || skillname.equals("戟指怒目") || skillname.equals("气贯长虹") || skillname.equals("恭行天罚") || skillname.equals("偷梁换柱") || skillname.equals("遮天蔽日");
    }
    
    public static void FightingOperation(FightingEvents fightingEvents) {
        if (FightingMixDeal.camp == -1) {
            return;
        }
        fightingEvents.setFightingsum(FightingMixDeal.FightingNumber);
        fightingEvents.setCurrentRound(FightingMixDeal.CurrentRound);
        String sendMes = Agreement.getAgreement().battleroundAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingEvents));
        SendMessageUntil.toServer(sendMes);
        FormsManagement.HideForm(46);
        FormsManagement.HideForm(34);
        FormsManagement.HideForm(631);
        FormsManagement.HideForm(603);
        FormsManagement.HideForm(6);
    }
    
    public static void execution(FightOperation Operation) {
        execution(Operation, false);
    }
    
    public static void execution(FightOperation Operation, boolean is) {
        if (FightingMixDeal.State == 1) {
            if (!is && Operation.getType() != 6 && Operation.getType() != 5 && FightingMixDeal.isLL()) {
                return;
            }
        }
        else if (FightingMixDeal.State != 2) {
            return;
        }
        FightingEvents events = AttackGenerate(Operation);
        if (FightingMixDeal.State == 1) {
            if (Operation.getType() == 1) {
                MsgJframe5.getMsJframe5().getJapnel5().sublime = Operation.getSpell();
            }
            else if (Operation.getType() == 0) {
                MsgJframe5.getMsJframe5().getJapnel5().sublime = FightingMonitor.mousesname;
            }
            else if (Operation.getType() == 5) {
                MsgJframe5.getMsJframe5().getJapnel5().sublime = "防御";
            }
            else if (Operation.getType() == 4) {
                MsgJframe5.getMsJframe5().getJapnel5().sublime = "捕捉";
            }
            else if (Operation.getType() == 6) {
                MsgJframe5.getMsJframe5().getJapnel5().sublime = "逃跑";
            }
            else if (Operation.getType() == 7) {
                MsgJframe5.getMsJframe5().getJapnel5().sublime = "召回";
            }
            else if (Operation.getType() == 2) {
                MsgJframe5.getMsJframe5().getJapnel5().sublime = "物品使用";
            }
            else if (Operation.getType() == 3) {
                MsgJframe5.getMsJframe5().getJapnel5().sublime = "保护";
            }
        }
        else if (Operation.getType() == 1) {
            MsgJframe5.getMsJframe5().getJapnel5().subsumpet = Operation.getSpell();
        }
        else if (Operation.getType() == 0) {
            MsgJframe5.getMsJframe5().getJapnel5().subsumpet = FightingMonitor.mousesname;
        }
        else if (Operation.getType() == 5) {
            MsgJframe5.getMsJframe5().getJapnel5().subsumpet = "防御";
        }
        else if (Operation.getType() == 6) {
            MsgJframe5.getMsJframe5().getJapnel5().subsumpet = "逃跑";
        }
        else if (Operation.getType() == 2) {
            MsgJframe5.getMsJframe5().getJapnel5().subsumpet = "物品使用";
        }
        else if (Operation.getType() == 3) {
            MsgJframe5.getMsJframe5().getJapnel5().subsumpet = "保护";
        }
        FightingOperation(events);
        operateEnd();
    }
    
    public static void operateEnd() {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        FightingMonitor.mousestate = 0;
        FightingMonitor.mousesname = "普通攻击";
        if (FightingMixDeal.State == 1 && FightingMixDeal.MyBeastLifeAndDeath()) {
            FightingMixDeal.changeState(2);
            ZhuFrame.getZhuJpanel().HideBeastBtn();
            ZhuFrame.getZhuJpanel().ShowBeastBtn();
        }
        else {
            FightingMixDeal.changeState(3);
            FightingMixDeal.RoundFighting();
        }
    }
    
    public static FightOperation getOperation() {
        if (FightingMixDeal.State == 1) {
            return FightingMonitor.RoleOperation;
        }
        return FightingMonitor.PetOperation;
    }
    
    public static FightingEvents AttackGenerate(FightOperation operation) {
        FightingEvents fEvents = new FightingEvents();
        List<FightingState> fList = new ArrayList<>();
        int type = 0;
        if (FightingMixDeal.State == 2) {
            type = 1;
        }
        fEvents.setOriginator(FightingMixDeal.FightingState("普通攻击", type));
        switch (operation.getType()) {
            case 1: {
                fEvents.getOriginator().setStartState("技能");
                fEvents.getOriginator().setEndState(operation.getSpell());
                break;
            }
            case 2: {
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(operation.getGood());
                if (goodstable != null && goodstable.getGoodlock() != 1 && ((long)goodstable.getType() == 0L || (long)goodstable.getType() == 1L)) {
                    fEvents.getOriginator().setStartState("药");
                    fEvents.getOriginator().setText(goodstable.getGoodsname());
                    fEvents.getOriginator().setEndState(goodstable.getValue());
                    goodstable.goodxh(1);
                    GoodsMouslisten.gooduse(goodstable, 1);
                    if ((int)goodstable.getUsetime() <= 0) {
                        GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
                        operation.setCamp(-1);
                        operation.setMan(-1);
                        operation.setType(0);
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    operation.setCamp(-1);
                    operation.setMan(-1);
                    break;
                }
            }
            case 3: {
                fEvents.getOriginator().setStartState("保护");
                break;
            }
            case 4: {
                fEvents.getOriginator().setStartState("捕捉");
                break;
            }
            case 5: {
                fEvents.getOriginator().setStartState("防御");
                break;
            }
            case 6: {
                fEvents.getOriginator().setStartState("逃跑");
                break;
            }
        }
        FightingState dqstate = new FightingState(operation.getCamp(), operation.getMan(), null);
        fList.add(dqstate);
        fEvents.setAccepterlist(fList);
        return fEvents;
    }
    
    public static FightingEvents CheHuiFightingEvents() {
        FightingEvents fEvents = new FightingEvents();
        int type = -1;
        if (FightingMixDeal.State == 3) {
            type = (FightingMixDeal.MyBeastLifeAndDeath() ? 1 : 0);
        }
        else if (FightingMixDeal.State == 2) {
            type = 0;
        }
        else if (FightingMixDeal.State == 3) {
            type = 1;
        }
        fEvents.setOriginator(FightingMixDeal.FightingState("普通攻击", type));
        fEvents.setType(Integer.valueOf(type));
        return fEvents;
    }
    
    static {
        FightingMonitor.RoleOperation = new FightOperation();
        FightingMonitor.PetOperation = new FightOperation();
        FightingMonitor.mousestate = 0;
        FightingMonitor.mousesname = "普通攻击";
    }
}
