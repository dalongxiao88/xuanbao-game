package org.come.action.suit;

import java.util.HashMap;
import java.text.DecimalFormat;
import org.come.model.Newequip;
import java.util.Iterator;
import org.come.model.WitchComposeAttr;
import org.come.action.buy.AddGoodUtil;
import org.come.tool.Goodtype;
import java.util.List;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.entity.Goodstable;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.bean.NpcComposeBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Map;
import java.util.Random;
import org.come.action.IAction;

public class WitchComposeAction implements IAction
{
    private static Random random;
    static Map<String, String> map;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        NpcComposeBean npcComposeBean = (NpcComposeBean)GsonUtil.getGsonUtil().getgson().fromJson(message, NpcComposeBean.class);
        List<BigDecimal> goodlist = npcComposeBean.getGoodstables();
        List<BigDecimal> goodstables = new ArrayList<>();
        for (int i = 0; i < goodlist.size(); ++i) {
            if (!goodstables.contains(goodlist.get(i))) {
                goodstables.add(goodlist.get(i));
            }
        }
        Goodstable[] goods = new Goodstable[goodstables.size()];
        if (goods.length < 4) {
            return;
        }
        for (int j = 0; j < goodstables.size(); ++j) {
            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)goodstables.get(j));
            if (good == null || (int)good.getUsetime() <= 0 || good.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                return;
            }
            goods[j] = good;
        }
        int zblvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        boolean up = false;
        if (zblvl >= 10 && zblvl <= 13) {
            if (kslvl != 8 && kslvl != 9) {
                return;
            }
            if (kslvl == 9) {
                up = true;
            }
        }
        else if (zblvl == 14) {
            if (kslvl != 9 && kslvl != 10) {
                return;
            }
            if (kslvl == 10) {
                up = true;
            }
        }
        else if (zblvl == 15) {
            if (kslvl != 10 && kslvl != 11) {
                return;
            }
            if (kslvl == 11) {
                up = true;
            }
        }
        else if (zblvl == 16) {
            if (kslvl != 11) {
                return;
            }
        }
        else {
            return;
        }
        zblvl += (up ? 1 : 0);
        int wzcl = 1;
        String typeStr = getEquipType(Long.valueOf(goods[0].getType()));
        if (typeStr != "武器" && typeStr != "衣服" && typeStr != "帽子") {
            if (typeStr == "鞋子" || typeStr == "项链") {}
        }
        for (int k = 0; k < goods.length; ++k) {
            if (k != 0 && k != 3) {
                goods[k].goodxh(1);
            }
            else if (k == 3) {
                goods[k].goodxh(wzcl);
            }
            else {
                goods[k].setUsetime(Integer.valueOf(1));
            }
        }
        String type = npcComposeBean.getComposetype();
        if (type.equals("巫铸")) {
            this.Compose_4(goods, ctx, loginResult);
        }
    }
    
    public static int Numerical(String vlaue) {
        if (vlaue.split("\\=").length == 1) {
            return 0;
        }
        return Integer.parseInt(vlaue.split("\\=")[1]);
    }
    
    public void Compose_4(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.OrdinaryEquipment(goods[0].getType())) {
            return;
        }
        if (!Goodtype.Ore(goods[1].getType())) {
            return;
        }
        int goodid = goods[0].getGoodsid().intValue();
        int zblvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        Goodstable good = null;
        if (zblvl < 10) {
            BigDecimal id = new BigDecimal(goodid - zblvl + kslvl + 1);
            good = GameServer.getGood(id);
            BigDecimal nsid = good.getGoodsid();
            good.setRgid(goods[0].getRgid());
            good.setRole_id(login.getRole_id());
            good.setQuality(goods[0].getQuality());
            good.setGoodsid(goods[0].getGoodsid());
            good.setStatus(goods[0].getStatus());
            AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
            goods[0] = null;
        }
        else {
            boolean up = false;
            if (zblvl >= 10 && zblvl <= 13) {
                if (kslvl != 8 && kslvl != 9) {
                    return;
                }
                if (kslvl == 9) {
                    up = true;
                }
            }
            else if (zblvl == 14) {
                if (kslvl != 9 && kslvl != 10) {
                    return;
                }
                if (kslvl == 10) {
                    up = true;
                }
            }
            else if (zblvl == 15) {
                if (kslvl != 10 && kslvl != 11) {
                    return;
                }
                if (kslvl == 11) {
                    up = true;
                }
            }
            else if (zblvl == 16) {
                if (kslvl != 11) {
                    return;
                }
            }
            else {
                return;
            }
            if (up) {
                ++goodid;
            }
            BigDecimal id2 = new BigDecimal(goodid);
            good = GameServer.getGood(id2);
            BigDecimal nsid2 = good.getGoodsid();
            good.setRgid(goods[0].getRgid());
            good.setRole_id(login.getRole_id());
            String race = this.getRaceByGood(goods);
            String wuzhustr = this.getWitchAttr(good, up, race);
            good.setValue(good.getValue() + this.getNewequip(good, race) + wuzhustr);
            good.setQuality(goods[0].getQuality());
            good.setGoodsid(goods[0].getGoodsid());
            good.setStatus(goods[0].getStatus());
            AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid2, null);
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
            goods[0] = null;
        }
        this.updata(goods);
        AddGoodUtil.addGood(ctx, good);
        System.out.println();
    }
    
    public String getRaceByGood(Goodstable[] goods) {
        if (goods.length >= 3 && goods[3] != null) {
            if (goods[3].getType() == 815L) {
                return "大力";
            }
            if (goods[3].getType() == 810L) {
                return "人族";
            }
            if (goods[3].getType() == 812L) {
                return "鬼族";
            }
            if (goods[3].getType() == 813L) {
                return "仙族";
            }
            if (goods[3].getType() == 811L) {
                return "魔族";
            }
            if (goods[3].getType() == 814L) {
                return "龙族";
            }
            return "";
        }
        else {
            return "";
        }
    }
    
    public static String getAttribute(String decorateType, int i) {
        String typees = "";
        if (decorateType.indexOf("|") != -1) {
            String[] decorateTypes = decorateType.split("\\|");
            int[] a = randomArray(0, decorateTypes.length - 1, i);
            for (int j = 0; j < a.length; ++j) {
                typees = typees + "|" + decorateTypes[a[j]];
            }
            return typees;
        }
        else {
            return "|" + decorateType;
        }
    }
    
    public String getWitchAttr(Goodstable goods, boolean up, String raceName) {
        Map<String, List<WitchComposeAttr>> witchAttrs = GameServer.getAllSwitchAttr();
        String buwei = getEquipType(Long.valueOf(goods.getType()));
        int zblvl = Integer.parseInt(goods.getValue().split("\\|")[0].split("=")[1]);
        List<WitchComposeAttr> allAttrs = new ArrayList<>();
        List<WitchComposeAttr> results = new ArrayList<>();
        List<WitchComposeAttr> raceAttrs = (List<WitchComposeAttr>)witchAttrs.get(raceName);
        List<WitchComposeAttr> posAttrs = (List<WitchComposeAttr>)witchAttrs.get("通用");
        if (posAttrs != null && posAttrs.size() > 0) {
            allAttrs.addAll(posAttrs);
        }
        if (raceAttrs != null && raceAttrs.size() > 0) {
            allAttrs.addAll(raceAttrs);
        }
        if (allAttrs != null && allAttrs.size() > 0) {
            for (WitchComposeAttr witchcomposeattr : allAttrs) {
                if (witchcomposeattr.getPosition().equals(buwei) && witchcomposeattr.getLvl() == zblvl) {
                    results.add(witchcomposeattr);
                }
            }
        }
        if (results.size() >= 1) {
            int index = WitchComposeAction.random.nextInt(results.size());
            WitchComposeAttr calAttr = (WitchComposeAttr)results.get(index);
            String val = String.format("%.2f", new Object[] { Double.valueOf(Math.random() * (calAttr.getMax() - calAttr.getMin()) + calAttr.getMin()) });
            return "|巫铸属性&" + calAttr.getAttr() + "=" + val;
        }
        return "";
    }
    
    public static String getEquipType(Long type) {
        String goodsType = null;
        if ((long)type == 601L || (long)type == 600L) {
            goodsType = "帽子";
        }
        else if ((long)type == 603L) {
            goodsType = "项链";
        }
        else if ((long)type == 605L || (long)type == 604L) {
            goodsType = "衣服";
        }
        else if ((long)type == 800L) {
            goodsType = "武器";
        }
        else if ((long)type == 602L) {
            goodsType = "鞋子";
        }
        else if ((long)type == 612L) {
            goodsType = "护身符";
        }
        return goodsType;
    }
    
    public String getNewequip(Goodstable goods, String raceName) {
        Map<String, List<Newequip>> sameNewequipMap = GameServer.getWitchNewequipMap();
        Long type = Long.valueOf(goods.getType());
        String value = goods.getValue();
        String[] goodsInfo = value.split("\\|");
        String grade = goodsInfo[0].split("=")[1];
        String goodsType = getEquipType(type);
        List<Newequip> hatList = (List<Newequip>)sameNewequipMap.get(goodsType + grade + raceName);
        List<Newequip> nomalList = (List<Newequip>)sameNewequipMap.get(goodsType + grade + "通用");
        if (hatList == null) {
            hatList = new ArrayList<>();
        }
        if (nomalList != null) {
            hatList.addAll(nomalList);
        }
        Newequip newequip = null;
        if (hatList.size() != 0) {
            newequip = (Newequip)hatList.get(WitchComposeAction.random.nextInt(hatList.size()));
            String types = this.getEquipRequire(newequip.getEquiptype());
            String[] vs = new String[12];
            List<String> vss = new ArrayList<>();
            for (int i = 0; i < vs.length; ++i) {
                String v = null;
                switch (i) {
                    case 0: {
                        v = newequip.getEquipfq();
                        break;
                    }
                    case 1: {
                        v = newequip.getEquipfskb();
                        break;
                    }
                    case 2: {
                        v = newequip.getEquipkwl();
                        break;
                    }
                    case 3: {
                        v = newequip.getEquipwla();
                        break;
                    }
                    case 4: {
                        v = newequip.getEquipkrf();
                        break;
                    }
                    case 5: {
                        v = newequip.getEquipqsc();
                        break;
                    }
                    case 6: {
                        v = newequip.getEquipksc();
                        break;
                    }
                    case 7: {
                        v = newequip.getEquipkxf();
                        break;
                    }
                    case 8: {
                        v = newequip.getEquipfsh();
                        break;
                    }
                    case 9: {
                        v = newequip.getEquipwle();
                        break;
                    }
                    case 10: {
                        v = newequip.getEquipqhp();
                        break;
                    }
                    case 11: {
                        v = newequip.getEquipqmp();
                        break;
                    }
                }
                if (v != null && !v.equals("")) {
                    String[] attrStrings = v.split("\\|");
                    if (attrStrings.length != 1 && WitchComposeAction.random.nextInt(100) <= Integer.parseInt(attrStrings[0])) {
                        String val = "0";
                        if (attrStrings[1].startsWith("-") || attrStrings[1].indexOf(".") > 0) {
                            val = randomDoubleNum(attrStrings[1], true);
                        }
                        else {
                            String[] num = attrStrings[1].split("-");
                            int min = (int)Math.round(Double.parseDouble(num[0]) * 1.2);
                            int max = Integer.parseInt(num[1]);
                            if (min >= max) {
                                min = max;
                            }
                            int[] a = randomArray(min, max, 1);
                            val = String.valueOf(a[0]);
                        }
                        String[] attrbutes = attrStrings[2].split("&");
                        String attrs = attrbutes[WitchComposeAction.random.nextInt(attrbutes.length)];
                        int k = 0;
                        while (vss.contains(attrs) || (WitchComposeAction.map.get(attrs) != null && vss.contains(WitchComposeAction.map.get(attrs)))) {
                            attrs = attrbutes[WitchComposeAction.random.nextInt(attrbutes.length)];
                            if (++k > 10000) {
                                break;
                            }
                        }
                        vss.add(attrs);
                        if (val == null) {
                            System.out.println(GsonUtil.getGsonUtil().getgson().toJson(newequip));
                        }
                        vs[i] = attrs + "=" + val;
                    }
                }
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append(types);
            for (int j = 0; j < vs.length; ++j) {
                if (vs[j] != null && !vs[j].equals("")) {
                    buffer.append("|");
                    buffer.append(vs[j]);
                }
            }
            return buffer.toString();
        }
        else {
            System.err.println("打造装备报错" + goodsType + grade);
            return "";
        }
    }
    
    public String getEquipRequire(String equipTypes) {
        String[] types = equipTypes.split("\\|");
        String type = new String("|");
        if (types.length != 1) {
            String[] requString = types[1].split("&");
            int[] a = randomArray(0, requString.length - 1, Integer.parseInt(types[0]));
            for (int j = 0; j < a.length; ++j) {
                type += requString[a[j]];
            }
            return type;
        }
        else {
            return type;
        }
    }
    
    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;
        if (max < min || n > len) {
            return null;
        }
        int[] source = new int[len];
        for (int i = min; i < min + len; ++i) {
            source[i - min] = i;
        }
        int[] result = new int[n];
        int index = 0;
        for (int j = 0; j < result.length; ++j) {
            index = Math.abs(WitchComposeAction.random.nextInt() % len--);
            result[j] = source[index];
            source[index] = source[len];
        }
        return result;
    }
    
    public static String randomDoubleNum(String strs, boolean type) {
        if (strs.startsWith("-")) {
            String[] num = strs.substring(1, strs.length()).split("-");
            if (num[0].indexOf(".") > 0 || num[1].indexOf(".") > 0) {
                Double min = Double.valueOf(type ? (Double.parseDouble(num[0]) * 1.2) : Double.parseDouble(num[0]));
                Double max = Double.valueOf(Double.parseDouble(num[1]));
                if ((double)min >= (double)max) {
                    min = max;
                }
                double randomNum = Math.random() * ((double)max - (double)min) + (double)min;
                DecimalFormat df = new DecimalFormat("#.#");
                return "-" + df.format(randomNum);
            }
            else {
                int min2 = (int)Math.round(Double.parseDouble(num[0]) * (type ? 1.2 : 1.0));
                int max2 = Integer.parseInt(num[1]);
                if (min2 >= max2) {
                    min2 = max2;
                }
                int[] a = randomArray(min2, max2, 1);
                return "-" + String.valueOf(a[0]);
            }
        }
        else {
            String[] num = strs.split("-");
            if (num[0].indexOf(".") > 0 || num[1].indexOf(".") > 0) {
                Double min = Double.valueOf(type ? (Double.parseDouble(num[0]) * 1.2) : Double.parseDouble(num[0]));
                Double max = Double.valueOf(Double.parseDouble(num[1]));
                if ((double)min >= (double)max) {
                    min = max;
                }
                double randomNum = Math.random() * ((double)max - (double)min) + (double)min;
                DecimalFormat df = new DecimalFormat("#.#");
                return df.format(randomNum);
            }
            else {
                int min2 = (int)Math.round(Double.parseDouble(num[0]) * 1.2);
                int max2 = Integer.parseInt(num[1]);
                if (min2 >= max2) {
                    min2 = max2;
                }
                int[] a = randomArray(min2, max2, 1);
                return String.valueOf(a[0]);
            }
        }
    }
    
    public void updata(Goodstable[] goods) {
        for (int i = 0; i < goods.length; ++i) {
            if (goods[i] != null) {
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goods[i]);
                if (i == 0) {
                    AllServiceUtil.getGoodsrecordService().insert(goods[i], null, Integer.valueOf(1), Integer.valueOf(8));
                }
                else {
                    long gType = goods[i].getType();
                    if (gType != 212L && (gType < 497L || gType > 500L) && gType != 505L && gType != 915L && gType != 923L) {
                        AllServiceUtil.getGoodsrecordService().insert(goods[i], null, Integer.valueOf(1), Integer.valueOf(6));
                    }
                }
            }
        }
    }
    
    static {
        WitchComposeAction.random = new Random();
        WitchComposeAction.map = new HashMap<>();
    }
}
