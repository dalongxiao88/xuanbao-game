package come.tool.FightingData;

import java.util.ArrayList;
import java.util.List;

public class AI
{
    public static int AI_BILITY;// 概率触发
    public static int AI_DEATH; // 死亡触发
    public static int AI_ROUND; // 回合触发
    public static int AI_ZD_ROUND; // 特殊回合触发
    public static int AI_SKILL; // 技能触发
    public static int AI_BEARSKILL;
    public static int AI_ZDBEARSKILL;
    public static int AI_CARRYSTATE;
    public static int AI_TYPE_FY;
    public static int AI_TYPE_TP;
    public static int AI_TYPE_HELP;
    public static int AI_TYPE_SKILL;
    public static int AI_TYPE_AUTOMATE;
    public static int AI_TYPE_ZH;
    public static int AI_TYPE_MF;
    public static int AI_TYPE_BS;
    public static int AI_TYPE_SWBS;
    public static int AI_NODEATH;
    public static int AI_SUBSTATE;
    public static int AI_TYPE_VIOLENT_PHY;
    public static int AI_TYPE_VIOLENT_SKILL;
    public static int AI_TYPE_STATE_START;
    public static int AI_TYPE_STATE_END;
    public static int AI_TYPE_FLASH;
    public static int AI_ZDGJ=1011;//指定攻击
    public static int AI_FBBZ=2012;//法宝100%
    public static int AI_PCYS=2013;//破除隐身

    //条件=死亡-1000&死亡-1001=逃跑(救人-1001)(狂暴)
    //类型
    private int type;
    //目标id
    private int man;
    //数值
    private int value;
    //状态名
    private String state;
    //触发条件
    private List<AICondition> aiConditions;
    private List<AIAutomate> aiAutomates;

    public AI(String type, String[] vs) {
        this.type = this.initType(type);
        this.aiAutomates = new ArrayList<>();
        for (int i = 1; i < vs.length; ++i) {
            String[] v = vs[i].split("-");
            int size = Integer.parseInt(v[1]);
            this.value += size;
            AIAutomate automate = new AIAutomate(v[0], v);
            for (int j = 0; j < size; ++j) {
                this.aiAutomates.add(automate);
            }
        }
    }
    
    public AI(String type, int man, int value, List<AICondition> aiConditions) {
        this.type = this.initType(type);
        this.man = man;
        this.value = value;
        this.aiConditions = aiConditions;
    }
    public AI(String type, int man, int value, List<AICondition> aiConditions,String state) {
        this.type = this.initType(type);
        this.man = man;
        this.state = state;
        this.value = value;
        this.aiConditions = aiConditions;
    }

    public AI(String type, String state, int sum, List<AICondition> aiConditions, int value) {
        this.type = this.initType(type);
        this.state = state;
        this.man = sum;
        this.aiConditions = aiConditions;
        this.value = value;
    }
    //判断是否触发概率ai
    public boolean isai(int round) {
        for (int i = this.aiConditions.size() - 1; i >= 0; --i) {
            AICondition point = (AICondition)this.aiConditions.get(i);
            if (point.getX() == AI.AI_BILITY) {
                if (point.getY() < Battlefield.random.nextInt(100)) {
                    return false;
                }
            }
            else if (point.getX() == AI.AI_ROUND) {
                if (round != point.getY()) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
    /**类型划分*/
    public int initType(String type) {
        switch (type) {
            case "逃跑":return AI_TYPE_TP;
            case "救人":
            case "加血":return AI_TYPE_HELP;
            case "法术":return AI_TYPE_SKILL;
            case "物理狂暴":return AI_TYPE_VIOLENT_PHY;
            case "仙法狂暴":return AI_TYPE_VIOLENT_SKILL;
            case "回合状态":return AI_TYPE_STATE_START;
            case "出手状态":return AI_TYPE_STATE_END;
            case "模仿技能":return AI_TYPE_MF;
            case "闪现":return AI_TYPE_FLASH;
            case "召唤":return AI_TYPE_ZH;
            case "指令":return AI_TYPE_AUTOMATE;
            case "变身":return AI_TYPE_BS;
            case "死亡变身":return AI_TYPE_SWBS;
            case "未死亡":return AI_NODEATH;
            case "降低状态":return AI_SUBSTATE;
            case "指定攻击":return AI_ZDGJ;
            case "法宝必中":return AI_FBBZ;
            case "破除隐身":return AI_PCYS;

        }
        return AI_TYPE_FY;//未知类型防御
    }
    
    public boolean isAI(int type) {
        if (this.type > 1000 && this.type < 2000) {
            return type == 1;
        }
        if (this.type > 2000 && this.type < 3000) {
            return type == 2;
        }
        if (this.type > 3000 && this.type < 4000) {
            return type == 3;
        }
        return this.type > 4000 && this.type < 5000 && type == 4;
    }
    
    public AIAutomate getAiAutomate(int BattleType) {
        if (this.aiAutomates == null || this.aiAutomates.size() == 0) {
            return null;
        }
        BattleType %= this.value;
        if (BattleType >= this.aiAutomates.size()) {
            BattleType = this.aiAutomates.size() - 1;
        }
        return this.aiAutomates.get(BattleType);
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getMan() {
        return this.man;
    }
    
    public void setMan(int man) {
        this.man = man;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public List<AICondition> getAiConditions() {
        return this.aiConditions;
    }
    
    public void setAiConditions(List<AICondition> aiConditions) {
        this.aiConditions = aiConditions;
    }
    
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public List<AIAutomate> getAiAutomates() {
        return this.aiAutomates;
    }
    
    public void setAiAutomates(List<AIAutomate> aiAutomates) {
        this.aiAutomates = aiAutomates;
    }
    
    static {
        AI.AI_BILITY = 0;//概率
        AI.AI_DEATH = 1;//死亡
        AI.AI_ROUND = 2;//回合
        AI.AI_ZD_ROUND = 6;
        AI.AI_SKILL = 3;//法术
        AI.AI_BEARSKILL = 4;//承受的法术
        AI.AI_ZDBEARSKILL = 7;
        AI.AI_CARRYSTATE = 5;//携带状态
        //出手指令判断
        AI.AI_TYPE_FY = 1001;//防御
        AI.AI_TYPE_TP = 1002;//逃跑
        AI.AI_TYPE_HELP = 1003;//加血
        AI.AI_TYPE_SKILL = 1004;//指定法术
        AI.AI_TYPE_AUTOMATE = 1005;//重复指令顺序 自动化操作

        AI.AI_TYPE_ZH = 1006;//召唤
        AI.AI_TYPE_MF = 1007;//模仿技能
        AI.AI_TYPE_BS = 1008;
        AI.AI_TYPE_SWBS = 1009;
        AI.AI_NODEATH = 1010;
        AI.AI_SUBSTATE = 1011;
        //回合开始判断
        AI.AI_TYPE_VIOLENT_PHY = 2001;//物理狂暴 3分2追
        AI.AI_TYPE_VIOLENT_SKILL = 2002;//法术狂暴
        AI.AI_TYPE_STATE_START = 2003;//回合开始附加状态
        AI.AI_TYPE_STATE_END = 3001;//出手结束附加状态
        AI.AI_TYPE_FLASH = 4001;//闪现
    }
}
