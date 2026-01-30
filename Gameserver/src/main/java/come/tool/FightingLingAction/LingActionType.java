package come.tool.FightingLingAction;

public enum LingActionType
{
    Null(null), 
    Directinjury(new Directinjury()), 
    Discharge(new Discharge()), 
    Blood(new Blood()), 
    LuoBao(new LuoBao()), 
    Insane(new Insane()), 
    Retreat(new Retreat()), 
    YinYang(new YinYang()), 
    QIanKunZheTian(new QianKunZheTian()), 
    TianPengZhuanShi(new TianPengZhuanShi()), 
    ZhaoHun(new ZhaoHun()), 
    HuiLing(new HuiLing());
    
    private LingAction action;
    
    private LingActionType(LingAction action) {
        this.action = action;
    }
    
    public LingAction getTarget() {
        return this.action;
    }
    
    public static LingAction getActionById(int actionId) {
        LingActionType[] values = values();
        LingActionType actionType = values[actionId];
        return actionType.getTarget();
    }
}
