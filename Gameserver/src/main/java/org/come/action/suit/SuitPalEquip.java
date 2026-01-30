package org.come.action.suit;

import org.come.model.Alchemy;
import org.come.bean.UseCardBean;
import org.come.bean.QualityClBean;
import java.util.List;
import come.tool.Role.RoleData;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import org.come.action.monitor.MonitorUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.tool.Goodtype;
import come.tool.Role.RolePool;
import org.come.bean.SuitOperBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;
import come.tool.Calculation.PalEquipQl;
import come.tool.Calculation.PalQl;
import org.come.model.PalEquip;
import come.tool.FightingData.Battlefield;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.entity.Goodstable;

public class SuitPalEquip
{
    static String PALQH;
    static String PALPZ;
    
    public static void PalEquipValue(Goodstable good, long type, int lvl, String[] v, int QH) {
        PalEquip palEquip = GameServer.getPalEquip(type);
        if (lvl < 1) {
            lvl = 1;
        }
        else if (lvl > palEquip.getUpLvl()) {
            lvl = palEquip.getUpLvl();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("等级=");
        buffer.append(lvl);
        if (palEquip.getLimits() != null) {
            String limit = (lvl - 1 < palEquip.getLimits().length) ? palEquip.getLimits()[lvl - 1] : palEquip.getLimits()[palEquip.getLimits().length - 1];
            buffer.append("|");
            buffer.append(limit);
        }
        if (v != null) {
            boolean isV = true;
            boolean isP = false;
            double xs = 0.7;
            for (int i = 1; i < v.length; ++i) {
                if (v[i].startsWith(SuitPalEquip.PALQH)) {
                    buffer.append("|");
                    buffer.append(SuitPalEquip.PALQH);
                    buffer.append("0/");
                    buffer.append(palEquip.getQhs()[lvl - 1]);
                    isV = false;
                }
                else if (v[i].startsWith(SuitPalEquip.PALPZ)) {
                    String[] vs = v[i].split("=");
                    if (vs[1].equals("黄金")) {
                        xs = 1.0;
                    }
                    else if (vs[1].equals("白银")) {
                        xs = 0.85;
                    }
                    buffer.append("|");
                    buffer.append(v[i]);
                    isP = true;
                }
                else if (isV) {
                    if (isP) {
                        try {
                            String[] vs = v[i].split("=");
                            double value = Double.parseDouble(vs[1]);
                            PalQl palQl = palEquip.getPalQl(vs[0], value, xs, lvl, 0);
                            if (palQl != null) {
                                buffer.append("|");
                                buffer.append(palQl.getKey());
                                buffer.append("=");
                                BigDecimal sx = new BigDecimal((palQl.getValue() + (double)lvl * palQl.getSv()) * xs);
                                sx = sx.setScale(1, 4);
                                if (sx.doubleValue() == (double)sx.intValue()) {
                                    buffer.append(sx.intValue());
                                }
                                else {
                                    buffer.append(sx);
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    buffer.append("|");
                    buffer.append(v[i]);
                }
            }
        }
        else {
            int gl = SuitComposeAction.random.nextInt(100);
            double xs2 = 0.7;
            int JC = QH / (palEquip.getQhs()[lvl - 1] / 5);
            if (gl < 4) {
                xs2 = 1.0;
                buffer.append("|品质=黄金");
            }
            else if (gl < 20) {
                xs2 = 0.85;
                buffer.append("|品质=白银");
            }
            else {
                buffer.append("|品质=青铜");
            }
            for (int j = 0; j < palEquip.getQls().length; ++j) {
                PalEquipQl equipQl = palEquip.getQls()[j];
                if (Battlefield.isV(equipQl.getV())) {
                    PalQl palQl2 = equipQl.getPalQl();
                    buffer.append("|");
                    buffer.append(palQl2.getKey());
                    buffer.append("=");
                    BigDecimal sx2 = new BigDecimal((palQl2.getValue() + (double)lvl * palQl2.getSv() + (double)JC * palQl2.getSv() / 5.0) * xs2);
                    sx2 = sx2.setScale(1, 4);
                    if (sx2.doubleValue() == (double)sx2.intValue()) {
                        buffer.append(sx2.intValue());
                    }
                    else {
                        buffer.append(sx2);
                    }
                }
            }
            buffer.append("|");
            buffer.append(SuitPalEquip.PALQH);
            buffer.append(QH);
            buffer.append("/");
            buffer.append(palEquip.getQhs()[lvl - 1]);
        }
        good.setGoodsname(palEquip.getNames()[lvl - 1]);
        good.setSkin(palEquip.getSkins()[lvl - 1]);
        good.setInstruction(palEquip.getIns()[lvl - 1]);
        good.setValue(buffer.toString());
    }
    
    public static void type62(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(100000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (goods.size() != 2 || !Goodtype.isPalEquip(((Goodstable)goods.get(0)).getType()) || ((Goodstable)goods.get(1)).getType() != 500L) {
            return;
        }
        if (((Goodstable)goods.get(0)).getRgid().compareTo(((Goodstable)goods.get(1)).getRgid()) == 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("主副物品一致???"));
            return;
        }
        String[] v = ((Goodstable)goods.get(0)).getValue().split("\\|");
        int lvl = Integer.parseInt(v[0].split("=")[1]);
        int goodlvl = Integer.parseInt(((Goodstable)goods.get(1)).getValue().split("=")[1]);
        int type = 0;
        if (lvl <= 5) {
            if (lvl + 5 == goodlvl) {
                type = 1;
            }
            else if (lvl + 4 == goodlvl) {
                type = 2;
            }
        }
        else if (lvl == 6 && goodlvl == 10) {
            type = 2;
        }
        PalEquip palEquip = GameServer.getPalEquip(((Goodstable)goods.get(0)).getType());
        int qhz = 0;
        int max = palEquip.getQhs()[lvl - 1];
        int i = 2;
        while (i < v.length) {
            if (v[i].startsWith(SuitPalEquip.PALQH)) {
                String[] vs = v[i].substring(SuitPalEquip.PALQH.length()).split("/");
                qhz = Integer.parseInt(vs[0]);
                break;
            }
            else {
                ++i;
            }
        }
        if (type == 1) {
            if (qhz < max) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("契合度未满"));
                return;
            }
            PalEquipValue((Goodstable)goods.get(0), ((Goodstable)goods.get(0)).getType(), lvl + 1, v, 0);
        }
        else if (type == 2) {
            PalEquipValue((Goodstable)goods.get(0), ((Goodstable)goods.get(0)).getType(), lvl, null, qhz);
        }
        else {
            return;
        }
        ((Goodstable)goods.get(1)).goodxh(1);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        SuitComposeAction.saveGoods(goods, false);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("D=-" + money);
        assetUpdate.updata("G" + ((Goodstable)goods.get(1)).getRgid() + "=" + ((Goodstable)goods.get(1)).getUsetime());
        assetUpdate.setGood((Goodstable)goods.get(0));
        assetUpdate.upmsg((type == 1) ? "升级成功" : "重铸成功");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type70(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        int lvl = suitOperBean.getType() - 70;
        UseCardBean cardBean = roleData.getLimit("单人竞技场");
        if (cardBean == null || cardBean.getQls().length < lvl) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还未解锁对应的称谓"));
            return;
        }
        BigDecimal money = new BigDecimal(1000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("金钱不足"));
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 1);
        if (goods == null || goods.size() != 1 || ((Goodstable)goods.get(0)).getType() != 119L || (int)((Goodstable)goods.get(0)).getUsetime() <= 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你没有段位洗练丹"));
            return;
        }
        List<Alchemy> alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get(lvl + "级单人竞技场");
        if (alchemies == null || alchemies.size() == 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有对应的炼化属性"));
            return;
        }
        String value = getOneArena(alchemies, lvl);
        QualityClBean clBean = new QualityClBean();
        clBean.setRgid(loginResult.getRole_id());
        clBean.setType(suitOperBean.getType());
        clBean.setNewAttr(value);
        QualityCPool.getcPool().addExtra(clBean);
        ((Goodstable)goods.get(0)).goodxh(1);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        SuitComposeAction.saveGoods(goods, false);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("D=-" + money);
        assetUpdate.updata("G" + ((Goodstable)goods.get(0)).getRgid() + "=" + ((Goodstable)goods.get(0)).getUsetime());
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static String getOneArena(List<Alchemy> alchemies, int lvl) {
        if (alchemies == null) {
            alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get(lvl + "级单人竞技场");
        }
        if (alchemies == null || alchemies.size() == 0) {
            return "根骨=1";
        }
        Alchemy alchemy = (Alchemy)alchemies.get(Battlefield.random.nextInt(alchemies.size()));
        return SuitComposeAction.lh(2 + Battlefield.random.nextInt(5), alchemy, 0.0);
    }
    
    static {
        SuitPalEquip.PALQH = "契合度=";
        SuitPalEquip.PALPZ = "品质=";
    }
}
