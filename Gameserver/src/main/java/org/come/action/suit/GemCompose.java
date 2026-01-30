package org.come.action.suit;

import come.tool.FightingData.Battlefield;
import org.come.entity.RoleSummoning;
import come.tool.Role.PartJade;
import come.tool.Role.RoleData;
import org.come.action.summoning.SummonPetAction;
import come.tool.Good.UseRoleAction;
import come.tool.Good.AddGoodAction;
import org.come.bean.XXGDBean;
import come.tool.Role.RolePool;
import org.come.bean.QualityClBean;
import org.come.model.GemBase;
import org.come.model.Gem;
import java.util.List;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import come.tool.Stall.AssetUpdate;
import org.come.server.GameServer;
import org.come.action.monitor.MonitorUtil;
import org.come.tool.Goodtype;
import org.come.entity.Goodstable;
import org.come.handler.SendMessage;
import java.math.BigDecimal;
import org.come.bean.SuitOperBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;

public class GemCompose
{
    public static void type17(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        BigDecimal money = new BigDecimal(500000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        int isT = SuitComposeAction.isTrans(goods, 0);
        if (isT == -1) {
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS5);
            return;
        }
        Goodstable good1 = (Goodstable)goods.get(0);
        Goodstable good2 = (Goodstable)goods.get(1);
        int lvl1 = 0;
        int lvl2 = 0;
        long quality = (long)good1.getQuality();
        String sType1 = null;
        String sType2 = null;
        if (good1.getType() == 744L) {
            lvl1 = 1;
        }
        else if (Goodtype.baoshi(good1.getType())) {
            String[] vs = good1.getValue().split("\\|");
            lvl1 = Integer.parseInt(vs[0].split("=")[1]);
            sType1 = vs[1].split("=")[0];
        }
        else {
            return;
        }
        if ((long)good2.getQuality() % 2L == 1L) {
            quality = (long)good2.getQuality();
        }
        if (good2.getType() == 744L) {
            lvl2 = 1;
        }
        else if (Goodtype.baoshi(good2.getType())) {
            String[] vs = good2.getValue().split("\\|");
            lvl2 = Integer.parseInt(vs[0].split("=")[1]);
            sType2 = vs[1].split("=")[0];
        }
        else {
            return;
        }
        if (lvl1 != lvl2 || lvl1 >= 10) {
            return;
        }
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        if (sType1 == null || sType2 == null || good1.getType() != good2.getType() || !sType1.equals(sType2)) {
            sType1 = null;
            sType2 = null;
        }
        int isUP = isUP(lvl1);
        if (isUP == 2 && good1.getType() == good2.getType()) {
            isUP = 1;
        }
        Goodstable good3 = null;
        boolean is = true;
        if (isUP == 1) {
            ++lvl1;
            for (int i = 0; i < goods.size(); ++i) {
                if (i != 0 || ((Goodstable)goods.get(i)).getType() == 744L) {
                    ((Goodstable)goods.get(i)).goodxh(1);
                }
            }
            Gem gem = getGem(good1, good2);
            GemBase base = gem.getGemBase(sType1);
            Goodstable goodstable = GameServer.getGood(new BigDecimal(gem.getBid()));
            good3 = (Goodstable)goods.get(0);
            if (good3.getType() == 744L) {
                is = false;
                good3 = goodstable;
                good3.setRole_id(loginResult.getRole_id());
            }
            good3.setType(goodstable.getType());
            good3.setGoodsname(goodstable.getGoodsname());
            good3.setInstruction(goodstable.getInstruction());
            if (lvl1 >= 7) {
                good3.setSkin(Integer.parseInt(goodstable.getSkin()) + 2 + "");
            }
            else if (lvl1 >= 4) {
                good3.setSkin(Integer.parseInt(goodstable.getSkin()) + 1 + "");
            }
            else {
                good3.setSkin(goodstable.getSkin());
            }
            good3.setValue(base.getGemValue(lvl1, 95 + SuitComposeAction.random.nextInt(9)));
            good3.setQuality(Long.valueOf(quality));
            if (lvl1 >= 7) {
                SuitMixdeal.Gem(loginResult.getRolename(), good3.getGoodsname(), lvl1);
            }
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS3);
        }
        else if (isUP == 2) {
            ++lvl1;
            is = false;
            for (int i = 0; i < goods.size(); ++i) {
                ((Goodstable)goods.get(i)).goodxh(1);
            }
            good3 = GameServer.getGood(new BigDecimal(81096));
            good3.setRole_id(loginResult.getRole_id());
            good3.setValue("等级=" + lvl1);
            if (lvl1 >= 7) {
                good3.setSkin(Integer.parseInt(good3.getSkin()) + 1 + "");
            }
            good3.setQuality(Long.valueOf(quality));
            if (lvl1 >= 7) {
                SuitMixdeal.Gem(loginResult.getRolename(), good3.getGoodsname(), lvl1);
            }
        }
        else {
            good3 = (Goodstable)goods.get(0);
            for (int i = 1; i < goods.size(); ++i) {
                ((Goodstable)goods.get(i)).goodxh(1);
            }
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS4);
        }
        SuitComposeAction.saveGoods(goods, is);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("D=-500000");
        for (int j = is ? 1 : 0; j < goods.size(); ++j) {
            assetUpdate.updata("G" + ((Goodstable)goods.get(j)).getRgid() + "=" + ((Goodstable)goods.get(j)).getUsetime());
        }
        if (isUP != 0) {
            if (good3.getRgid() == null) {
                AllServiceUtil.getGoodsTableService().insertGoods(good3);
            }
            else {
                AllServiceUtil.getGoodsTableService().updateGoodRedis(good3);
            }
            assetUpdate.setGood(good3);
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static int isUP(int lvl) {
        if (lvl <= 3) {
            return 1;
        }
        if (SuitComposeAction.random.nextInt(6) == 0) {
            return 0;
        }
        if (SuitComposeAction.random.nextInt(10) == 0 && lvl >= 8) {
            return 2;
        }
        return 1;
    }
    
    public static Gem getGem(Goodstable good1, Goodstable good2) {
        String name = good1.getGoodsname();
        if (good1.getType() == 744L || good2.getType() == 744L) {
            name = null;
        }
        else if (good1.getType() != good2.getType()) {
            name = null;
        }
        return GameServer.getGem(name);
    }
    
    public static void type18(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        int isT = SuitComposeAction.isTrans(goods, 0);
        if (isT == -1) {
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS5);
            return;
        }
        Goodstable good1 = (Goodstable)goods.get(0);
        Goodstable good2 = (Goodstable)goods.get(1);
        int lvl1 = 0;
        int lvl2 = 0;
        int G = 0;
        if (Goodtype.baoshi(good1.getType())) {
            String[] vs = good1.getValue().split("\\|");
            lvl1 = Integer.parseInt(vs[0].split("=")[1]);
            G = Integer.parseInt(vs[2].split("=")[1]);
            if (good2.getType() == 744L) {
                lvl2 = 1;
            }
            else if (Goodtype.baoshi(good2.getType())) {
                vs = good2.getValue().split("\\|");
                lvl2 = Integer.parseInt(vs[0].split("=")[1]);
            }
            else {
                return;
            }

            if (lvl1 - 4 != lvl2) {
                if ((lvl1 != 10 && lvl2!=10)) {
                    return;
                }
            }
            if ((lvl1 == 10 && lvl2==10)) {
               G = Battlefield.random.nextInt(13)+108;
            }
            Gem gem = SuitMixdeal.getGemType((long)suitOperBean.getJade().getSuitid());
            GemBase base = gem.getGemBase(null);
            Goodstable goodstable = GameServer.getGood(new BigDecimal(gem.getBid()));
            good1.setType(goodstable.getType());
            good1.setGoodsname(goodstable.getGoodsname());
            good1.setInstruction(goodstable.getInstruction());
            if (lvl1 >= 7) {
                good1.setSkin(Integer.parseInt(goodstable.getSkin()) + 2 + "");
            }
            else if (lvl1 >= 4) {
                good1.setSkin(Integer.parseInt(goodstable.getSkin()) + 1 + "");
            }
            else {
                good1.setSkin(goodstable.getSkin());
            }
            good1.setValue(base.getGemValue(lvl1, G));
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            for (int i = 1; i < goods.size(); ++i) {
                ((Goodstable)goods.get(i)).goodxh(1);
                assetUpdate.updata("G" + ((Goodstable)goods.get(i)).getRgid() + "=" + ((Goodstable)goods.get(i)).getUsetime());
            }
            assetUpdate.setGood(good1);
            SuitComposeAction.saveGoods(goods, false);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            return;
        }
        else {
            return;
        }
    }
    //	1|仅奇异石需要鉴定
//	2|需要消耗一颗比奇异石等级低3级的宝石
//	3|鉴定100%成功
//	4|鉴定后，种类和属性随机
//	5|鉴定后的宝石价值较高
    /**19:宝石鉴定*/
    public static void type19(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        int isT = SuitComposeAction.isTrans(goods, 0);
        if (isT == -1) {
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS5);
            return;
        }
        Goodstable good1 = (Goodstable)goods.get(0);
        Goodstable good2 = (Goodstable)goods.get(1);
        int lvl1 = 0;
        int lvl2 = 0;
        if (good1.getType() == 770L) {
            String[] vs = good1.getValue().split("\\|");
            lvl1 = Integer.parseInt(vs[0].split("=")[1]);
            if (good2.getType() == 744L) {
                lvl2 = 1;
            }
            else if (Goodtype.baoshi(good2.getType())) {
                vs = good2.getValue().split("\\|");
                lvl2 = Integer.parseInt(vs[0].split("=")[1]);
            }
            else {
                return;
            }
            if (lvl1 - 3 != lvl2) {
                return;
            }

            Gem gem = GameServer.getGem(null);
            GemBase base = gem.getGemBase(null);
            Goodstable goodstable = GameServer.getGood(new BigDecimal(gem.getBid()));
            good1.setType(goodstable.getType());
            good1.setGoodsname(goodstable.getGoodsname());
            good1.setInstruction(goodstable.getInstruction());
            if (lvl1 >= 7) {
                good1.setSkin(Integer.parseInt(goodstable.getSkin()) + 2 + "");
            }
            else if (lvl1 >= 4) {
                good1.setSkin(Integer.parseInt(goodstable.getSkin()) + 1 + "");
            }
            else {
                good1.setSkin(goodstable.getSkin());
            }
            good1.setValue(base.getGemValue(lvl1, 100 + SuitComposeAction.random.nextInt(21)));
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            for (int i = 1; i < goods.size(); ++i) {
                ((Goodstable)goods.get(i)).goodxh(1);
                assetUpdate.updata("G" + ((Goodstable)goods.get(i)).getRgid() + "=" + ((Goodstable)goods.get(i)).getUsetime());
            }
            assetUpdate.setGood(good1);
            SuitComposeAction.saveGoods(goods, false);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            return;
        }
        else {
            return;
        }
    }
    
    public static void type20(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        Goodstable good1 = (Goodstable)goods.get(0);
        Goodstable good2 = (Goodstable)goods.get(1);
        int lvl = 0;
        if (Goodtype.baoshi(good2.getType())) {
            String[] vs = good2.getValue().split("\\|");
            lvl = Integer.parseInt(vs[0].split("=")[1]);
            if (goods.size() == 2) {
                BigDecimal money = new BigDecimal(3200000 * lvl);
                if (loginResult.getGold().compareTo(money) < 0) {
                    return;
                }
                loginResult.setGold(loginResult.getGold().subtract(money));
                MonitorUtil.getMoney().useD(money.longValue());
                String[] vs2 = good1.getValue().split("\\|");
                String extra = SuitComposeAction.getExtra(vs2, SuitComposeAction.Extras[4]);
                if (extra == null) {
                    return;
                }
                QualityClBean clBean = new QualityClBean();
                clBean.setRgid(good1.getRgid());
                clBean.setType(5);
                StringBuffer buffer = new StringBuffer();
                buffer.append("宝石属性");
                String[] extras = extra.split("&");
                for (int i = 1; i < extras.length; ++i) {
                    if (!extras[i].equals(good2.getRgid().toString())) {
                        buffer.append("&");
                        buffer.append(extras[i]);
                    }
                }
                clBean.setNewAttr(buffer.toString());
                if (clBean.getNewAttr().equals("宝石属性")) {
                    clBean.setNewAttr(null);
                }
                good1.setValue(SuitComposeAction.newExtra(vs2, 4, clBean.getNewAttr()));
                good2.setStatus(Integer.valueOf(0));
                SuitComposeAction.saveGoods(goods, false);
                SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
            }
            else {
                if (good1 == null||good2 ==null) {
                    return;
                }
                //81097	赤焰石	746	746	2	等级=1	镶嵌部位：武器
                //81098	芙蓉石	749	749	2	等级=1	镶嵌部位：项链、帽子
                //81099	寒山石	752	752	2	等级=1	镶嵌部位：鞋子
                //81100	孔雀石	755	755	2	等级=1	镶嵌部位：武器
                //81101	琉璃石	758	758	2	等级=1	镶嵌部位：鞋子
                //81102	落星石	761	761	2	等级=1	镶嵌部位：衣服、帽子
                //81103	沐阳石	764	764	2	等级=1	镶嵌部位：衣服、项链
                //81104	紫烟石	767	767	2	等级=1	镶嵌部位：武器
                if (Goodtype.EquipmentType(good1.getType())==5){
                    if (good2.getType()!=752
                            &&good2.getType()!=758
                    ) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("此宝石不能用于打造这个装备（重上后显示宝石）"));
                        return;
                    }
                }
                if (Goodtype.EquipmentType(good1.getType())==3){
                    if (good2.getType()!=761
                            &&good2.getType()!=764
                    ) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("此宝石不能用于打造这个装备（重上后显示宝石）"));
                        return;
                    }
                }
                if (Goodtype.EquipmentType(good1.getType())==2){
                    if (good2.getType()!=764
                            &&good2.getType()!=749
                    ) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("此宝石不能用于打造这个装备（重上后显示宝石）"));
                        return;
                    }
                }
                if (Goodtype.EquipmentType(good1.getType())==1){
                    if (good2.getType()!=761
                            &&good2.getType()!=749
                    ) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("此宝石不能用于打造这个装备（重上后显示宝石）"));
                        return;
                    }
                }
                if (Goodtype.EquipmentType(good1.getType())==0){
                    if (good2.getType()!=746
                            &&good2.getType()!=755
                            &&good2.getType()!=767
                    ) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("此宝石不能用于打造这个装备（重上后显示宝石）"));
                        return;
                    }
                }
                if (((lvl <= 4) ? 3 : (lvl - 1)) != goods.size()) {
                    return;
                }
                BigDecimal money = new BigDecimal(5000000 + lvl * 1000000);
                if (loginResult.getGold().compareTo(money) < 0) {
                    return;
                }
                String[] vs2 = good1.getValue().split("\\|");
                String extra = SuitComposeAction.getExtra(vs2, SuitComposeAction.Extras[4]);
                QualityClBean clBean = new QualityClBean();
                clBean.setRgid(good1.getRgid());
                clBean.setType(5);
                if (extra == null || extra.equals("")) {
                    clBean.setNewAttr("宝石属性&" + good2.getRgid());
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("宝石属性");
                    String[] extras = extra.split("&");
                    int i = 1;
                    while (i < extras.length) {
                        if (!extras[i].equals(good2.getRgid().toString())) {
                            Goodstable good3 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(extras[i]));
                            if (good3 == null || good3.getType() == good2.getType()) {
                                return;
                            }
                            buffer.append("&");
                            buffer.append(extras[i]);
                            ++i;
                        }
                        else {
                            return;
                        }
                    }
                    buffer.append("&");
                    buffer.append(good2.getRgid().toString());
                    clBean.setNewAttr(buffer.toString());
                }
                loginResult.setGold(loginResult.getGold().subtract(money));
                MonitorUtil.getMoney().useD(money.longValue());
                good1.setValue(SuitComposeAction.newExtra(vs2, 4, clBean.getNewAttr()));
                good2.setStatus(Integer.valueOf(1));
                for (int j = 2; j < goods.size(); ++j) {
                    ((Goodstable)goods.get(j)).goodxh(1);
                }
                SuitComposeAction.saveGoods(goods, false);
                SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
            }
            return;
        }
        else {
            return;
        }
    }
    
    public static void type21(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        for (int i = 0; i < goods.size(); ++i) {
            ((Goodstable)goods.get(i)).goodxh(1);
            if ((int)((Goodstable)goods.get(i)).getUsetime() < 0) {
                return;
            }
        }
        PartJade jade = suitOperBean.getJade();
        if (jade.getSuitid() == 0) {
            if (jade.getPartId() == 170) {
                BigDecimal id = new BigDecimal(171);
                if (goods.size() != 3) {
                    return;
                }
                for (int j = 0; j < goods.size(); ++j) {
                    if (((Goodstable)goods.get(j)).getGoodsid().compareTo(id) != 0) {
                        return;
                    }
                }
            }
            else if (jade.getPartId() == 80060) {
                BigDecimal id = new BigDecimal(80059);
                if (goods.size() != 5) {
                    return;
                }
                for (int j = 0; j < goods.size(); ++j) {
                    if (((Goodstable)goods.get(j)).getGoodsid().compareTo(id) != 0) {
                        return;
                    }
                }
            }
            else if (jade.getPartId() == 80046) {
                BigDecimal id = new BigDecimal(80047);
                if (goods.size() != 1) {
                    return;
                }
                for (int j = 0; j < goods.size(); ++j) {
                    if (((Goodstable)goods.get(j)).getGoodsid().compareTo(id) != 0) {
                        return;
                    }
                }
            }
            else if (jade.getPartId() == 92381) {
                BigDecimal id = new BigDecimal(188);
                if (goods.size() != 3) {
                    return;
                }
                for (int j = 0; j < goods.size(); ++j) {
                    if (((Goodstable)goods.get(j)).getGoodsid().compareTo(id) != 0) {
                        return;
                    }
                }
            }
            else if (jade.getPartId() == 92383) {
                BigDecimal id = new BigDecimal(772);
                if (goods.size() != 3) {
                    return;
                }
                for (int j = 0; j < goods.size(); ++j) {
                    if (((Goodstable)goods.get(j)).getGoodsid().compareTo(id) != 0) {
                        return;
                    }
                }
            }
            else if (jade.getPartId() == 80165) {
                BigDecimal id = new BigDecimal(80164);
                if (goods.size() != 10) {
                    return;
                }
                for (int j = 0; j < goods.size(); ++j) {
                    if (((Goodstable)goods.get(j)).getGoodsid().compareTo(id) != 0) {
                        return;
                    }
                }
            }
            else {
                return;
            }
        }
        else if (jade.getSuitid() == 1) {
            if (jade.getPartId() >= 200097 && jade.getPartId() <= 200101) {
                if (goods.size() != 10) {
                    return;
                }
                for (int k = 0; k < goods.size(); ++k) {
                    if (((Goodstable)goods.get(k)).getGoodsid().compareTo(new BigDecimal(901 + k)) != 0) {
                        return;
                    }
                }
            }
            else if (jade.getPartId() == 200096) {
                if (goods.size() != 4) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(174)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(175)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(178)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200095) {
                if (goods.size() != 4) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(174)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(175)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200094) {
                if (goods.size() != 4) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(175)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(176)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(178)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200093) {
                if (goods.size() != 4) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(176)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(178)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200092) {
                if (goods.size() != 4) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(175)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(176)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200135) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(176)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(178)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(182)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200147) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(178)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200137) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(176)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(182)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200145) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(174)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(174)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(183)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200132) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(174)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(175)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(182)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(183)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200133) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(174)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(176)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(183)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200134) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200146) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(175)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(176)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(182)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(182)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200136) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(174)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(176)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(177)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(179)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(182)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(183)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200144) {
                if (goods.size() != 6) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(175)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(1)).getGoodsid().compareTo(new BigDecimal(178)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(2)).getGoodsid().compareTo(new BigDecimal(180)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(3)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(4)).getGoodsid().compareTo(new BigDecimal(181)) != 0) {
                    return;
                }
                if (((Goodstable)goods.get(5)).getGoodsid().compareTo(new BigDecimal(182)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200157) {
                if (goods.size() != 1) {
                    return;
                }
                if (((Goodstable)goods.get(0)).getGoodsid().compareTo(new BigDecimal(80047)) != 0) {
                    return;
                }
            }
            else if (jade.getPartId() == 200116 || jade.getPartId() == 200117 || jade.getPartId() == 200123) {
                int size = 0;
                if (jade.getPartId() == 200116) {
                    size = 588;
                }
                else if (jade.getPartId() == 200117) {
                    size = 488;
                }
                else {
                    size = 468;
                }
                if (goods.size() != size) {
                    return;
                }
                BigDecimal id2 = new BigDecimal(80167);
                for (int l = 0; l < goods.size(); ++l) {
                    if (((Goodstable)goods.get(l)).getGoodsid().compareTo(id2) != 0) {
                        return;
                    }
                }
            }
            else if (jade.getPartId() == 200124 || jade.getPartId() == 200138 || jade.getPartId() == 200141 || jade.getPartId() == 200142 || jade.getPartId() == 200140 || jade.getPartId() == 200143 || jade.getPartId() == 200158) {
                int size = 0;
                if (jade.getPartId() == 200124 || jade.getPartId() == 200138 || jade.getPartId() == 200141) {
                    size = 188;
                }
                else if (jade.getPartId() == 200142 || jade.getPartId() == 200140) {
                    size = 288;
                }
                else if (jade.getPartId() == 200143 || jade.getPartId() == 200158) {
                    size = 388;
                }
                if (goods.size() != size) {
                    return;
                }
                BigDecimal id2 = new BigDecimal(80168);
                for (int l = 0; l < goods.size(); ++l) {
                    if (((Goodstable)goods.get(l)).getGoodsid().compareTo(id2) != 0) {
                        return;
                    }
                }
            }
            else {
                return;
            }
        }
        else {
            return;
        }
        SuitComposeAction.saveGoods(goods, false);
        if (jade.getSuitid() == 0) {
            XXGDBean bean = new XXGDBean();
            bean.setId(jade.getPartId() + "");
            bean.setSum(1);
            BigDecimal id2 = new BigDecimal(bean.getId());
            Goodstable goodstable = GameServer.getGood(id2);
            if (id2.longValue() < 0L && roleData.getPackRecord().isTX(-id2.longValue() + "")) {
                return;
            }
            if (goodstable == null) {
                return;
            }
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setMsg(bean.getSum() + "个" + goodstable.getGoodsname());
            AddGoodAction.addGood(assetUpdate, goodstable, loginResult, roleData, bean, 21);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (jade.getSuitid() == 1) {
            RoleSummoning pet = GameServer.getPet(new BigDecimal(jade.getPartId()));
            if (pet == null) {
                return;
            }
            pet.setBasishp(pet.getHp());
            pet.setBasismp(pet.getMp());
            pet.setFaithful(Integer.valueOf(100));
            pet.setGrade(Integer.valueOf(0));
            pet.setTurnRount(0);
            pet.setBone(Integer.valueOf(0));
            pet.setSpir(Integer.valueOf(0));
            pet.setPower(Integer.valueOf(0));
            pet.setSpeed(Integer.valueOf(0));
            pet.setCalm(Integer.valueOf(0));
            pet.setDragon(0);
            pet.setSpdragon(0);
            pet.setAlchemynum(0);
            pet.setExp(new BigDecimal(0));
            pet.setOpenSeal(Integer.valueOf(1));
            pet.setOpenql(Integer.valueOf(0));
            pet.setRoleid(loginResult.getRole_id());
            if (pet.getSsn() == null || pet.getSsn().equals("0")) {}
            String yb = pet.getResistance();
            if (yb == null || yb.equals("")) {
                int p;
                int p2;
                for (p = UseRoleAction.random.nextInt(SummonPetAction.kxs.length), p2 = UseRoleAction.random.nextInt(SummonPetAction.kxs.length); p2 == p; p2 = UseRoleAction.random.nextInt(SummonPetAction.kxs.length)) {}
                pet.setResistance(SummonPetAction.kxs[p] + "|" + SummonPetAction.kxs[p2]);
            }
            AllServiceUtil.getRoleSummoningService().insertRoleSummoning(pet);
            AssetUpdate assetUpdate2 = new AssetUpdate();
            assetUpdate2.setType(AssetUpdate.USERGOOD);
            assetUpdate2.setPet(pet);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
        }
    }
}
