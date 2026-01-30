package come.tool.Fighting;

public class MousePosUtil
{
    public static int mouse_x;
    public static int mouse_y;
    public static int cRound;
    public static int ccamp;
    public static int cman;
    
    public static boolean isOverRole() {
        for (int i = 0; i < FightingMixDeal.CurrentData.size(); ++i) {
            if (((Fightingimage)FightingMixDeal.CurrentData.get(i)).getFightingManData().getType() < 3 && ((Fightingimage)FightingMixDeal.CurrentData.get(i)).isContains(MousePosUtil.mouse_x, MousePosUtil.mouse_y)) {
                return true;
            }
        }
        return false;
    }
    
    public static void setRoundInfo(int round, int camp, int man) {
        if (MousePosUtil.cRound != round || MousePosUtil.cRound != camp || MousePosUtil.cman != man) {
            MousePosUtil.cRound = round;
            MousePosUtil.ccamp = camp;
            MousePosUtil.cman = man;
        }
    }
}
