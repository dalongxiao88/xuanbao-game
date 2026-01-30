package come.tool.BangBattle;

import org.come.until.GsonUtil;
import org.come.entity.GangBattle;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;

public class BangFileSystem
{
    private static long WEEK;//一周的时间
    private static long CHA;
    private static BangFileSystem bangFileSystem;
    
    public static BangFileSystem getBangFileSystem() {
        if (BangFileSystem.bangFileSystem == null) {
            BangFileSystem.bangFileSystem = new BangFileSystem();
        }
        return BangFileSystem.bangFileSystem;
    }
    //数据保存
    public void DataSaving(BangBattlePool pool) {
        GangBattle gangBattle = AllServiceUtil.getGangBattleService().selectGangBattle(new BigDecimal(pool.week));
        if (gangBattle == null) {
            gangBattle = new GangBattle();
            gangBattle.setWeek(new BigDecimal(pool.week));
            gangBattle.setValue(GsonUtil.getGsonUtil().getgson().toJson(pool.group));
            AllServiceUtil.getGangBattleService().addGangBattle(gangBattle);
        }
        else {
            gangBattle.setValue(GsonUtil.getGsonUtil().getgson().toJson(pool.group));
            AllServiceUtil.getGangBattleService().updataGangBattle(gangBattle);
        }
    }
    //数据读取
    public String DataReading(BigDecimal week) {
        GangBattle gangBattle = AllServiceUtil.getGangBattleService().selectGangBattle(week);
        if (gangBattle == null) {
            return "";
        }
        if (gangBattle.getValue() == null) {
            return "";
        }
        return gangBattle.getValue();
    }
    
    public long getweek() {
        return (System.currentTimeMillis() + BangFileSystem.CHA) / BangFileSystem.WEEK;
    }
    
    static {
        BangFileSystem.WEEK = 604800000L;
        BangFileSystem.CHA = 288000000L;
    }
}
