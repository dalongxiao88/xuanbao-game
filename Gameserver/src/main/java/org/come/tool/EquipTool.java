package org.come.tool;

import java.util.List;

public class EquipTool
{
    static List<Long> strings;
    
    public static boolean canSuper(long goodstype) {
        return EquipTool.strings.contains(Long.valueOf(goodstype));
    }
    
    public static boolean contains(String[] vs, String key) {
        if (vs != null) {
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }
    //物品叠加
    static {
        EquipTool.strings = SplitStringTool.splitLong("0-1|8|49-52|88|99|66690|100|111|112|118|119|120|123-127|189|190|191|212|492-505|507|513-515|521-524|715|716|721|901-910|2040-2043|2053|2070-2079|2080|2113-2116|7005|7010|801-802|1002|1003|1005|1006|1008|2323|2324|728|556|557|889|888|891|744|8889-8893|2260|60003|7500-7502|7511|28955|915|918|10086|919|667|923|932|935|936|951|938|9002|8004|8003|2118|2120|739|2124|2127|2128|2129|2119|1101-1103|60027-60031|666|701|924|192|1181|66666|810-815|2233|2234|925|2423|2236|115-116|2251|20400|2254|1881|999|921|922|2258|702|703|704|705|706|707|708|709|710|711|722|723|882|2666|1115|1116|10015");
    }
}
