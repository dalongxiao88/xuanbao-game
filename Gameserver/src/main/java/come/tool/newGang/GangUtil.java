package come.tool.newGang;

import java.util.Iterator;
import io.netty.channel.ChannelHandlerContext;
import org.come.until.AllServiceUtil;
import org.come.entity.Gang;
import java.util.List;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class GangUtil
{
    private static ConcurrentHashMap<BigDecimal, GangDomain> gangMap;
    private static List<Gang> gangs;
    
    public static void init() {
        GangUtil.gangMap = new ConcurrentHashMap<>();
        GangUtil.gangs = AllServiceUtil.getGangService().findAllGang();
        for (int i = 0, length = GangUtil.gangs.size(); i < length; ++i) {
            Gang gang = (Gang)GangUtil.gangs.get(i);
            GangUtil.gangMap.put(gang.getGangid(), new GangDomain(gang));
        }
    }
    
    public static GangDomain getGangDomain(BigDecimal gangId) {
        return (GangDomain)GangUtil.gangMap.get(gangId);
    }
    
    public static Gang getGang(BigDecimal gangId) {
        GangDomain gangDomain = (GangDomain)GangUtil.gangMap.get(gangId);
        return (gangDomain != null) ? gangDomain.getGang() : null;
    }
    
    public static GangDomain addGangDomain(Gang gang, BigDecimal roleId, ChannelHandlerContext ctx) {
        GangDomain domain = new GangDomain(gang);
        GangUtil.gangMap.put(gang.getGangid(), domain);
        domain.upGangRole(roleId, ctx);
        GangUtil.gangs.add(gang);
        return domain;
    }
    
    public static void upGangs(boolean is) {
        for (GangDomain gangDomain : GangUtil.gangMap.values()) {
            if (is) {
                gangDomain.upXY();
            }
            gangDomain.upGang();
        }
    }
    
    public static List<Gang> getGangs() {
        return GangUtil.gangs;
    }
    
    public static void setGangs(List<Gang> gangs) {
        GangUtil.gangs = gangs;
    }
}
