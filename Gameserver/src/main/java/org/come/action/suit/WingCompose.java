package org.come.action.suit;

import come.tool.Good.AddGoodAction;
import org.come.bean.XXGDBean;
import org.apache.commons.lang.StringUtils;
import org.come.action.monitor.MonitorUtil;
import org.come.entity.WingTraining;
import org.come.server.GameServer;
import come.tool.Role.PartJade;
import java.util.List;
import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import come.tool.Role.RolePool;
import org.come.bean.SuitOperBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;

public class WingCompose
{
    public static void type31(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        PartJade jade = suitOperBean.getJade();
        if (jade == null) {
            return;
        }
        BigDecimal money = new BigDecimal(10000000L * (long)jade.getPartId());
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        Goodstable pGood = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal((jade.getSuitid() < 0) ? (4294967296L + (long)jade.getSuitid()) : ((long)jade.getSuitid())));
        if (pGood == null || pGood.getRole_id().compareTo(loginResult.getRole_id()) != 0 || (int)pGood.getUsetime() < jade.getPartId() || jade.getPartId() <= 0) {
            return;
        }
        pGood.goodxh(jade.getPartId());
        goods.add(pGood);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        assetUpdate.updata("D=-" + money);
        Goodstable good = (Goodstable)goods.get(0);
        if (good.getType() != 8888L) {
            return;
        }
        String[] vs = good.getValue().split("\\|");
        long exp = Long.parseLong(vs[2].split("=")[1]);
        int ylvl = getWingLevel(exp);
        int pexp = wingTraining(pGood, jade.getPartId());
        exp += (long)pexp;
        if (exp >= 4000000L) {
            exp = 4000000L;
        }
        if (pexp != 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < 4; ++i) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                if (i == 2) {
                    buffer.append("经验=" + exp);
                }
                else {
                    buffer.append(vs[i]);
                }
            }
            int nlvl = getWingLevel(exp);
            if (ylvl == nlvl) {
                for (int j = 4; j < vs.length; ++j) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(vs[j]);
                }
            }
            else {
                int size = nlvl - ylvl;
                int[] arr = new int[4];
                for (int k = 4; k < vs.length; ++k) {
                    if (vs[k].startsWith("根骨=")) {
                        arr[0] = Integer.parseInt(vs[k].split("=")[1]);
                    }
                    else if (vs[k].startsWith("力量=")) {
                        arr[1] = Integer.parseInt(vs[k].split("=")[1]);
                    }
                    else if (vs[k].startsWith("灵性=")) {
                        arr[2] = Integer.parseInt(vs[k].split("=")[1]);
                    }
                    else if (vs[k].startsWith("敏捷=")) {
                        arr[3] = Integer.parseInt(vs[k].split("=")[1]);
                    }
                }
                addWingQuality(arr, size, SuitComposeAction.random.nextInt(4));
                for (int k = 0; k < arr.length; ++k) {
                    if (arr[k] != 0) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        if (k == 0) {
                            buffer.append("根骨=");
                        }
                        else if (k == 1) {
                            buffer.append("力量=");
                        }
                        else if (k == 2) {
                            buffer.append("灵性=");
                        }
                        else if (k == 3) {
                            buffer.append("敏捷=");
                        }
                        buffer.append(arr[k]);
                    }
                }
                for (int k = 4; k < vs.length; ++k) {
                    if (!vs[k].startsWith("根骨=") && !vs[k].startsWith("力量=") && !vs[k].startsWith("灵性=") && !vs[k].startsWith("敏捷=")) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append(vs[k]);
                    }
                }
            }
            good.setValue(buffer.toString());
            assetUpdate.setGood(good);
        }
        assetUpdate.setMsg("本次培养获得了" + pexp + "点经验");
        SuitComposeAction.saveGoods(goods, pexp == 0);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static int wingTraining(Goodstable pGood, int sum) {
        if (pGood.getType() == 8891L) {
            return Integer.parseInt(pGood.getValue().split("\\|")[0].split("=")[1]) * sum;
        }
        double gl = (double)(SuitComposeAction.random.nextInt(70) + 31) / 100.0;
        WingTraining wingTraining = GameServer.getWingTraining(pGood.getType());
        if (wingTraining == null) {
            int pexp = (int)(0.01 * (double)sum);
            pexp = (int)((double)pexp * gl);
            return pexp;
        }
        int value = 1;
        double base = 0.0;
        try {
            if (wingTraining.getUn() != -1) {
                String[] vs = pGood.getValue().split("\\|");
                if (vs.length > wingTraining.getUn()) {
                    value = Integer.parseInt(vs[wingTraining.getUn()].split("=")[1]);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (value >= wingTraining.getBases().length) {
            base = wingTraining.getBases()[wingTraining.getBases().length - 1] / (double)wingTraining.getBases().length;
        }
        else {
            base = wingTraining.getBases()[value - 1] / (double)value;
        }
        return (int)(base * (double)sum * (double)value / (double)wingTraining.getValue() * gl) + sum;
    }
    
    public static void type32(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        Goodstable good = (Goodstable)goods.get(0);
        if (good.getType() != 8888L) {
            return;
        }
        String[] vs = good.getValue().split("\\|");
        int pz = SuitMixdeal.getPZlvl(vs[0].split("=")[1]);
        int xlvl = Integer.parseInt(vs[1].split("=")[1]);
        if (pz <= 0 || pz > 6 || xlvl >= 15) {
            return;
        }
        if (pz != goods.size() - 1) {
            return;
        }
        String value = (xlvl <= 2) ? "1" : ((xlvl <= 5) ? "2" : ((xlvl <= 8) ? "3" : ((xlvl <= 11) ? "4" : "5")));
        for (int i = 1; i < goods.size(); ++i) {
            Goodstable goodstable = (Goodstable)goods.get(i);
            if (goodstable.getType() != 8889L) {
                return;
            }
            if (!goodstable.getValue().split("=")[1].equals(value)) {
                return;
            }
            goodstable.goodxh(1);
            if ((int)goodstable.getUsetime() < 0) {
                return;
            }
        }
        BigDecimal money = new BigDecimal(Long.parseLong(value) * 10000000L);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        ++xlvl;
        vs[1] = "星级=" + xlvl;
        StringBuffer buffer = new StringBuffer();
        vs[0] = "品质=" + SuitMixdeal.getPZName(pz);
        for (int j = 0; j < vs.length; ++j) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(vs[j]);
        }
        good.setValue(buffer.toString());
        assetUpdate.setGood(good);
        assetUpdate.setMsg("#G升星成功");
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type33(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(10000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        Goodstable good = (Goodstable)goods.get(0);
        if (good.getType() != 8888L) {
            return;
        }
        String[] vs = good.getValue().split("\\|");
        int pz = SuitMixdeal.getPZlvl(vs[0].split("=")[1]);
        int xlvl = Integer.parseInt(vs[1].split("=")[1]);
        if (pz <= 0 || pz >= 6 || xlvl < 15) {
            return;
        }
        int size = (pz <= 3) ? ((int)Math.pow(2.0, (double)(pz - 1))) : ((pz == 4) ? 6 : 10);
        if (size != goods.size() - 1) {
            return;
        }
        for (int i = 1; i < goods.size(); ++i) {
            Goodstable goodstable = (Goodstable)goods.get(i);
            if (goodstable.getType() != 8890L) {
                return;
            }
            goodstable.goodxh(1);
            if ((int)goodstable.getUsetime() < 0) {
                return;
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        boolean is = SuitComposeAction.random.nextInt(100) >= 22 - pz;
        if (is) {
            assetUpdate.setMsg("品质升级失败");
        }
        else {
            ++pz;
            StringBuffer buffer = new StringBuffer();
            vs[0] = "品质=" + SuitMixdeal.getPZName(pz);
            vs[1] = "星级=0";
            for (int j = 0; j < vs.length; ++j) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(vs[j]);
            }
            good.setSkin(good.getSkin().substring(0, good.getSkin().length() - 1) + pz);
            good.setValue(buffer.toString());
            assetUpdate.setGood(good);
            assetUpdate.setMsg("#G品质升级成功");
        }
        SuitComposeAction.saveGoods(goods, is);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type34(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(10000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        Goodstable good = (Goodstable)goods.get(0);
        if (good.getType() != 8888L) {
            return;
        }
        if (1 != goods.size() - 1) {
            return;
        }
        for (int i = 1; i < goods.size(); ++i) {
            Goodstable goodstable = (Goodstable)goods.get(i);
            if (goodstable.getType() != 8892L) {
                return;
            }
            goodstable.goodxh(1);
            if ((int)goodstable.getUsetime() < 0) {
                return;
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        String[] vs = good.getValue().split("\\|");
        long exp = Long.parseLong(vs[2].split("=")[1]);
        int size;
        int lvl = size = getWingLevel(exp);
        int gl = SuitComposeAction.random.nextInt(100);
        if (gl <= 20) {
            size += SuitComposeAction.random.nextInt(9) + 2;
        }
        else if (gl <= 75) {
            size += SuitComposeAction.random.nextInt(6) + 2;
        }
        else {
            size += SuitComposeAction.random.nextInt(4) + 2;
        }
        int[] arr = new int[4];
        addWingQuality(arr, size, SuitComposeAction.random.nextInt(4));
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < 4; ++j) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(vs[j]);
        }
        for (int j = 0; j < arr.length; ++j) {
            if (arr[j] != 0) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                if (j == 0) {
                    buffer.append("根骨=");
                }
                else if (j == 1) {
                    buffer.append("力量=");
                }
                else if (j == 2) {
                    buffer.append("灵性=");
                }
                else if (j == 3) {
                    buffer.append("敏捷=");
                }
                buffer.append(arr[j]);
            }
        }
        for (int j = 4; j < vs.length; ++j) {
            if (!vs[j].startsWith("根骨=") && !vs[j].startsWith("力量=") && !vs[j].startsWith("灵性=") && !vs[j].startsWith("敏捷=")) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(vs[j]);
            }
        }
        good.setValue(buffer.toString());
        assetUpdate.setGood(good);
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type35(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(500000);
        BigDecimal money2 = null;
        int lock = 0;
        PartJade jade = suitOperBean.getJade();
        if (jade != null) {
            lock = jade.getSuitid();
            int size = 0;
            for (int i = 0; i < 6; ++i) {
                if ((int)((double)lock / Math.pow(10.0, (double)i)) % 10 != 0) {
                    ++size;
                }
            }
            if (size != 0) {
                if (size == 1) {
                    money2 = new BigDecimal(10);
                }
                else if (size == 2) {
                    money2 = new BigDecimal(50);
                }
                else {
                    money2 = new BigDecimal(100);
                }
            }
        }
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (money2 != null && loginResult.getCodecard().compareTo(money2) < 0) {
            return;
        }
        Goodstable good = (Goodstable)goods.get(0);
        if (good.getType() != 8888L) {
            return;
        }
        if (3 != goods.size() - 1) {
            return;
        }
        for (int i = 1; i < goods.size(); ++i) {
            Goodstable goodstable = (Goodstable)goods.get(i);
            if (goodstable.getType() != 8893L) {
                return;
            }
            goodstable.goodxh(1);
            if ((int)goodstable.getUsetime() < 0) {
                return;
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        if (money2 != null) {
            loginResult.setCodecard(loginResult.getCodecard().subtract(money2));
            MonitorUtil.getMoney().useX(money2.longValue());
            assetUpdate.updata("X=-" + money2);
        }
        String extra = SuitComposeAction.RefinersV(good, 4, lock);
        String value = SuitComposeAction.newExtra(good.getValue().split("\\|"), 0, extra);
        good.setValue(value);
        assetUpdate.setGood(good);
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type355(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(500000);
        BigDecimal money2 = null;
        int lock = 0;
        PartJade jade = suitOperBean.getJade();
        if (jade != null) {
            lock = jade.getSuitid();
            int size = 0;
            for (int i = 0; i < 6; ++i) {
                if ((int)((double)lock / Math.pow(10.0, (double)i)) % 10 != 0) {
                    ++size;
                }
            }
            if (size != 0) {
                if (size == 1) {
                    money2 = new BigDecimal(10);
                }
                else if (size == 2) {
                    money2 = new BigDecimal(50);
                }
                else {
                    money2 = new BigDecimal(100);
                }
            }
        }
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (money2 != null && loginResult.getCodecard().compareTo(money2) < 0) {
            return;
        }
        if (StringUtils.isBlank(loginResult.getLiangHao())) {
            return;
        }
        if (1 != goods.size()) {
            return;
        }
        for (int j = 0; j < goods.size(); ++j) {
            Goodstable goodstable = (Goodstable)goods.get(j);
            if (goodstable.getType() != 7010L) {
                return;
            }
            if ((int)goodstable.getUsetime() < 10) {
                return;
            }
            goodstable.goodxh(10);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        if (money2 != null) {
            loginResult.setCodecard(loginResult.getCodecard().subtract(money2));
            MonitorUtil.getMoney().useX(money2.longValue());
            assetUpdate.updata("X=-" + money2);
        }
        String extra = "";
        String[] sx = { "", "炼化属性", "炼化属性", "炼化属性", "炼化属性" };
        sx[0] = suitOperBean.getType() - 355 + "";
        String[] split = loginResult.getLianghaoValue().split("@");
        String v = "";
        if (split.length == 5) {
            sx[1] = split[1];
            sx[2] = split[2];
            sx[3] = split[3];
            sx[4] = split[4];
            if (suitOperBean.getType() == 362) {
                v = split[1];
            }
            else if (suitOperBean.getType() == 361) {
                v = split[2];
            }
            else if (suitOperBean.getType() == 363) {
                v = split[3];
            }
            else if (suitOperBean.getType() == 364) {
                v = split[4];
            }
        }
        extra = SuitComposeAction.LHRefinersV(v, 4, lock);
        String value1 = SuitComposeAction.newExtra(v.split("\\|"), 0, extra);
        String[] split2 = value1.split("\\|");
        if (split2.length > 1) {
            value1 = split2[1];
        }
        else {
            value1 = split2[0];
        }
        if (suitOperBean.getType() == 362) {
            sx[1] = value1;
        }
        else if (suitOperBean.getType() == 361) {
            sx[2] = value1;
        }
        else if (suitOperBean.getType() == 363) {
            sx[3] = value1;
        }
        else if (suitOperBean.getType() == 364) {
            sx[4] = value1;
        }
        String value2 = StringUtils.join(sx, "@");
        loginResult.setLianghaoValue(value2);
        assetUpdate.updata("LHLHVALUE=" + value2);
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type36(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(100000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        int value = -1;
        for (int i = 0; i < goods.size(); ++i) {
            Goodstable good = (Goodstable)goods.get(i);
            if (good.getType() == 8889L) {
                if (value == -1) {
                    value = Integer.parseInt(good.getValue().split("=")[1]);
                }
                else if (value != Integer.parseInt(good.getValue().split("=")[1])) {
                    return;
                }
                good.goodxh(1);
                if ((int)good.getUsetime() < 0) {
                    return;
                }
            }
        }
        if (value == -1 || value >= 5) {
            return;
        }
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        SuitComposeAction.saveGoods(goods, false);
        value += 81139;
        BigDecimal sid = new BigDecimal(value);
        XXGDBean bean = new XXGDBean();
        bean.setId(sid.toString());
        bean.setSum(1);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        Goodstable goodstable = GameServer.getGood(sid);
        if (goodstable == null) {
            return;
        }
        AddGoodAction.addGood(assetUpdate, goodstable, loginResult, roleData, bean, assetUpdate.getType());
        assetUpdate.setMsg("#G合成成功");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void addWingQuality(int[] arr, int size, int v) {
        int gl = 0;
        for (int i = 0; i < size; ++i) {
            gl = SuitComposeAction.random.nextInt(4);
            if (gl < 4) {
                int n = gl;
                ++arr[n];
            }
            else {
                ++arr[v];
            }
        }
    }
    
    public static int getWingLevel(long exp) {
        int i = 1;
        while (exp >= (long)(i * i * 100)) {
            if (++i >= 200) {
                return 200;
            }
        }
        return i - 1;
    }
    
    public static long getLevelNowExp(int lvl) {
        return (long)(lvl * lvl * 100);
    }
    
    public static long getLevelLastExp(long exp) {
        int lvl = getWingLevel(exp);
        return exp - getLevelNowExp(lvl);
    }
}
