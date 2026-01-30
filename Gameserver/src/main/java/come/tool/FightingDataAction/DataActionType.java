package come.tool.FightingDataAction;

import come.tool.FightingSpellAction.QC;

public enum DataActionType
{
    Null(null), 
    PhyAttack(new PhyAttack()), 
    Spell(new Spell()), 
    Escape(new Escape()), 
    Yao(new Yao()), 
    PetZhZh(new PetZhZh()), 
    Lingbao(new Lingbao()), 
    Catch(new Catch()), 
    Confusion(new Confusion()), 
    Heritage(new Heritage()), 
    Mate(new Mate()), 
    Worm(null), 
    MoJindan(new MoJindan()), 
    PetFlee(new PetFlee()), 
    NoSkillOne(new NoSkillOne()), 
    NoSkillTwo(new NoSkillTwo()), 
    ZiCang(new ZiCang()), 
    Nirvana(new Nirvana()), 
    Transfer(new Transfer()), 
    PoisonNeedles(new Huayu()), 
    SplitSoul(new SplitSoul()), 
    Kanglong(new Kanglong()), 
    Fabao(new Fabao()), 
    Huadie(new Huadie()), 
    Falian(null), 
    Stealing(new Stealing()), 
    DieGroup(new DieGroup()), 
    Equal(new Equal()), 
    PXKG(new PXKG()), 
    BB_RHTY(new BB_RHTY()), 
    BB_YAHY(new BB_YAHY()), 
    BB_TDTS(new BB_TDTS()), 
    InitState(new InitState()), 
    BB_NZQK(new BB_NZQK()), 
    LianSheng(new LianSheng()), 
    LingTing(new LingTing()), 
    YiShiZhongDi(new YiShiZhongDi()), 
    FM_PTGJ(new FM_PTGJ()), 
    wjqf(new wjqf()), 
    csa(new csa()), 
    XYLL(new XYLL()), 
    QC(new QC()), 
    LINGBAO_ZH(new LingBaoZhZh()), 
    TJ_XFLZAction(new TJ_XFLZAction()),
    QBNZ(new QBNZ());
    
    private DataAction action;
    
    private DataActionType(DataAction action) {
        this.action = action;
    }
    
    public DataAction getTarget() {
        return this.action;
    }
    
    public static DataAction getActionById(int actionId) {
        DataActionType[] values = values();
        DataActionType actionType = values[actionId];
        return actionType.getTarget();
    }
}
