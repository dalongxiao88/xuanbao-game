package come.tool.Title;

import java.util.Iterator;
import java.util.ArrayList;
import org.come.entity.Titletable;
import java.util.List;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class TitleUtil
{
    public static String SL;
    public static String LTS;
    private static ConcurrentHashMap<BigDecimal, List<Titletable>> roleMap;
    private static ConcurrentHashMap<String, List<BigDecimal>> titleMap;
    
    public static void addTitle(String title, BigDecimal... Ids) {
        List<BigDecimal> list = (List<BigDecimal>)TitleUtil.titleMap.remove(title);
        if (list != null) {
            for (BigDecimal id : list) {
                ClearTitle(title, id);
            }
            list.clear();
        }
        else {
            list = new ArrayList<>();
            TitleUtil.titleMap.put(title, list);
        }
        if (Ids != null) {
            for (int i = 0; i < Ids.length; ++i) {
                Titletable titletable = new Titletable(Ids[i], title);
                list.add(Ids[i]);
                addTitle(titletable, Ids[i]);
            }
        }
    }
    
    public static void addTitle2(String title, BigDecimal... Ids) {
        List<BigDecimal> list = (List<BigDecimal>)TitleUtil.titleMap.remove(title);
        if (list == null) {
            list = new ArrayList<>();
            TitleUtil.titleMap.put(title, list);
        }
        for (int i = 0; i < Ids.length; ++i) {
            Titletable titletable = new Titletable(Ids[i], title);
            list.add(Ids[i]);
            addTitle(titletable, Ids[i]);
        }
    }
    
    public static void ClearTitle(String title, BigDecimal ID) {
        List<Titletable> list = (List<Titletable>)TitleUtil.roleMap.get(ID);
        if (list != null) {
            int i = list.size() - 1;
            while (i >= 0) {
                Titletable titletable = (Titletable)list.get(i);
                if (titletable.getTitlename().equals(title)) {
                    list.remove(titletable);
                    break;
                }
                else {
                    --i;
                }
            }
        }
    }
    
    public static void addTitle(Titletable title, BigDecimal ID) {
        List<Titletable> list = (List<Titletable>)TitleUtil.roleMap.get(ID);
        if (list == null) {
            list = new ArrayList<>();
            TitleUtil.roleMap.put(ID, list);
        }
        list.add(title);
    }
    
    public static List<Titletable> getTitles(BigDecimal ID, List<Titletable> list) {
        List<Titletable> list2 = (List<Titletable>)TitleUtil.roleMap.get(ID);
        if (list2 == null) {
            return list;
        }
        if (list == null) {
            return list2;
        }
        for (Titletable title : list2) {
            list.add(title);
        }
        return list;
    }
    
    public static boolean isTitle(String title, BigDecimal ID) {
        if (title == null) {
            return true;
        }
        List<BigDecimal> list = (List<BigDecimal>)TitleUtil.titleMap.get(title);
        return list == null || list.contains(ID);
    }
    
    static {
        TitleUtil.SL = "水陆大会·战神";
        TitleUtil.LTS = "擂台霸主";
        TitleUtil.roleMap = new ConcurrentHashMap<>();
        TitleUtil.titleMap = new ConcurrentHashMap<>();
    }
}
