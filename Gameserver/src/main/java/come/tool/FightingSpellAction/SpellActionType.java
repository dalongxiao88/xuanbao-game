package come.tool.FightingSpellAction;

import come.tool.FightingDataAction.JQRMAction;
import come.tool.FightingDataAction.XMLDAction;

public enum SpellActionType
{
    Null(null), 
    Control(new ControlAction()), 
    TYNum(new TYSkillAction()), 
    TY_HS_SSXX(new TY_HS_SSXXAction()), 
    ZS(new ZSAction()), 
    Gain(new GainAction()), 
    TY_LL_JCYY(new TY_LL_JCYYAction()), 
    TY_MH_RSSQ(new TY_MH_RSSQAction()), 
    SSC(new SSCAction()), 
    TY_FY_ZYCX(new TY_FY_ZYCXAction()), 
    TY_MY(new TY_MYAction()), 
    Hurt(new HurtAction()), 
    TY_L_JFXL(new TY_L_JFXLAction()), 
    BB_SRPZ(new BB_SRPZAction()), 
    BB_ZNSS(new BB_ZNSSAction()), 
    BB_HRYY(new BB_HRYYAction()), 
    BB_HNYG(new BB_HNYGAction()), 
    BB_HYMB(new BB_HYMBAction()), 
    BB_WJ(new BB_WJAction()), 
    BB_HSSF(new BB_HSSFAction()), 
    L_PLCBFY(new L_PLCBFYAction()), 
    L_GL(new L_GLAction()), 
    L_LL(new L_LLAction()), 
    FengMo(new FengMoAction()), 
    FM_QX(new FM_QXAction()), 
    FM_ZJZTAction(new FM_ZJZTAction()), 
    TJ_TLHZAction(new TJ_TLHZAction()), 
    TJ_ZTBRAction(new TJ_ZTBRAction()), 
    TJ_DNTGAction(new TJ_DNTGAction()), 
    TJ_WJGZAction(new TJ_WJGZAction()), 
    TJ_WFCZAction(new TJ_WFCZAction()), 
    TJ_QingxinAction(new TJ_QingxinAction()), 
    TJ_tsyd(new TJ_tsyd()), 
    TJ_jsbm(new TJ_jsbm()), 
    TJ_WJCXAction(new TJ_WJCXAction()), 
    TJ_KSFLAction(new TJ_KSFLAction()), 
    TJ_ZDHLAction(new TJ_ZDHLAction()), 
    TJ_DZQKAction(new TJ_DZQKAction()), 
    TJ_LFAction(new TJ_LFAction()), 
    TJ_SFAction(new TJ_SFAction()), 
    TJ_CFXSAction(new TJ_CFXSAction()), 
    TJ_MJFSAction(new TJ_MJFSAction()), 
    TJ_YYSFAction(new TJ_YYSFAction()), 
    TJ_QHSQAction(new TJ_QHSQAction()), 
    XP_JJSSAction(new XP_JJSSAction()), 
    BB_BCJF(new BB_BCJF()), 
    QHFY(new qhfyAction()), 
    YXHY(new YXHYAction()), 
    XMLD(new XMLDAction()), 
    JQRM(new JQRMAction()), 
    LCDD(new TY_L_LCDDAction()), 
    LFCY(new TY_L_LFCYAction()), 
    CBZL(new TY_L_CBZLAction()),
    //53
    TJ_TTZKAction(new TJ_TTZKAction()),
    //54 斗魂帆
    XB_DouHunFanAction(new XB_DouHunFanAction()),
    //55 伏羲琴
    XB_FuXiQing(new XB_FuxiQingAction()),
    //56 巽尘拂c
    XB_FuChen(new XB_FuChenAction()),
    //57 震霆诏
    XB_LeiTing(new XB_LeiTingAction()),
    //58螭纹佩
    XB_LinWenPei(new XB_LiWenPeiAction())
    ;
    
    private SpellAction action;
    
    private SpellActionType(SpellAction action) {
        this.action = action;
    }
    
    public SpellAction getTarget() {
        return this.action;
    }
    
    public static SpellAction getActionById(int actionId) {
        SpellActionType[] values = values();
        SpellActionType actionType = values[actionId];
        return actionType.getTarget();
    }
}
