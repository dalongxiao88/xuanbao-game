package come.tool.Good;

import come.tool.Calculation.BaseLimit;
import org.come.action.suit.SuitComposeAction;
import org.come.model.PalData;
import org.come.action.monitor.MonitorUtil;
import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import java.util.List;
import org.come.entity.Baby;
import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import org.come.entity.Pal;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import org.come.tool.Goodtype;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UsePalAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        String[] vs = message.split("\\|");
        int type = 0;
        if (vs[0].startsWith("P")) {
            vs[0] = vs[0].substring(1);
            type = 1;
        }
        else {
            if (vs[0].startsWith("C")) {
                this.palJH(Integer.parseInt(vs[0].substring(1)), ctx, loginResult);
                return;
            }
            if (vs[1].equals("pet")) {
                type = 2;
            }
            else if (vs[1].equals("getPet")) {
                type = 3;
            }
            else if (vs[1].equals("lingbao")) {
                type = 4;
            }
            else if (vs[1].equals("getLingbao")) {
                type = 5;
            }
            else if (vs[1].equals("baby")) {
                type = 6;
            }
            else if (vs[1].equals("getBaby")) {
                type = 7;
            }
            else if (vs[1].equals("addSkillAI")) {
                type = 8;
            }
            else if (vs[1].equals("deleteSkillAI")) {
                type = 9;
            }
        }
        Pal pal = AllServiceUtil.getPalService().selectPalByID(new BigDecimal(vs[0]));
        if (pal == null) {
            return;
        }
        if (pal.getRoleId().compareTo(loginResult.getRole_id()) != 0) {
            return;
        }
        if (type == 0) {
            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[1]));
            if (good == null) {
                return;
            }
            if (good.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                return;
            }
            if ((int)good.getUsetime() <= 0) {
                return;
            }
            if (good.getType() == 7500L || good.getType() == 7501L) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该道具已失效"));
                return;
            }
            if (good.getType() == 7502L) {
                this.palJJGood(pal, good, ctx, loginResult);
            }
            else if (Goodtype.isPalEquip(good.getType())) {
                this.palEquipGood(pal, good, ctx, loginResult, vs.length == 2);
            }
        }
        else if (type == 1) {
            this.palCZ(pal.getId(), loginResult, (vs.length == 2) ? Integer.parseInt(vs[1]) : 3);
        }
        else if (type == 2) {
            RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(vs[2]));
            pal.setSummoning(pet);
            AllServiceUtil.getPalService().updatePal(pal);
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.setPal(pal);
            assetUpdate.setMsg("#R提示：#Y召唤兽【" + pet.getSummoningname() + "】已分配到伙伴！");
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (type == 3) {
            RoleSummoning pet = pal.getSummoning();
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            AllServiceUtil.getRoleSummoningService().updateRoleSummoningIndex(pet, loginResult.getRole_id());
            assetUpdate.setPet(pet);
            assetUpdate.setMsg("#R提示：#Y召唤兽【" + pet.getSummoningname() + "】已取回！");
            pal.setSummoning(null);
            AllServiceUtil.getPalService().updatePal(pal);
            assetUpdate.setPal(pal);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (type == 4) {
            Lingbao lingbao = AllServiceUtil.getLingbaoService().selectByPrimaryKey(new BigDecimal(vs[2]));
            int sj = (int)((Math.random() * 9.0 + 1.0) * 1.0E8);
            lingbao.setRoleid(new BigDecimal(sj));
            pal.setLingbao(lingbao);
            AllServiceUtil.getPalService().updatePal(pal);
            AssetUpdate assetUpdate2 = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate2.setPal(pal);
            assetUpdate2.setMsg("#R提示：#Y灵宝【" + lingbao.getBaoname() + "】已分配到伙伴！");
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
        }
        else if (type == 5) {
            Lingbao lingbao = pal.getLingbao();
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            AllServiceUtil.getLingbaoService().updateLingbaoIndex(lingbao, loginResult.getRole_id());
            assetUpdate.setLingbao(lingbao);
            assetUpdate.setMsg("#R提示：#Y灵宝【" + lingbao.getBaoname() + "】已取回！");
            pal.setLingbao(null);
            AllServiceUtil.getPalService().updatePal(pal);
            assetUpdate.setPal(pal);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (type == 6) {
            Baby baby = AllServiceUtil.getBabyService().selectBabyById(new BigDecimal(vs[2]));
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            assetUpdate.setMsg("#R提示：#Y熊孩子【" + baby.getBabyname() + "】已寄养给伙伴！");
            pal.setBaby(baby);
            baby.setState("1");
            AllServiceUtil.getBabyService().updateBabyRedis(baby);
            assetUpdate.setBaby(baby);
            assetUpdate.setPal(pal);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (type == 7) {
            Baby baby = pal.getBaby();
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            assetUpdate.setMsg("#R提示：#Y孩子【" + baby.getBabyname() + "】已领回！");
            pal.setBaby(null);
            baby.setState("0");
            AllServiceUtil.getBabyService().updateBabyRedis(baby);
            assetUpdate.setBaby(baby);
            assetUpdate.setPal(pal);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (type == 8) {
            AssetUpdate assetUpdate3 = new AssetUpdate(AssetUpdate.USEGOOD);
            List<String> palSkillAI = new ArrayList<>();
            for (String v : vs[2].split(",")) {
                palSkillAI.add(v);
            }
            pal.setPalSkillAI(palSkillAI);
            assetUpdate3.setPal(pal);
            AllServiceUtil.getPalService().updatePal(pal);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
        }
        else if (type == 9) {
            AssetUpdate assetUpdate3 = new AssetUpdate(AssetUpdate.USEGOOD);
            pal.setPalSkillAI(null);
            assetUpdate3.setPal(pal);
            AllServiceUtil.getPalService().updatePal(pal);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
        }
    }
    
    public void palCZ(BigDecimal id, LoginResult login, int p) {
        RoleData data = RolePool.getRoleData(login.getRole_id());
        if (p == -1) {
            data.getPs().remove(id);
        }
        else if (p >= data.getPs().size()) {
            data.getPs().remove(id);
            data.getPs().add(id);
        }
        else {
            BigDecimal idTwo = (BigDecimal)data.getPs().get(p);
            int path = data.getPs().indexOf(id);
            data.getPs().set(p, id);
            if (path == -1) {
                if (data.getPs().size() < 4) {
                    data.getPs().add(idTwo);
                }
            }
            else {
                data.getPs().set(path, idTwo);
            }
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < data.getPs().size(); ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(data.getPs().get(i));
        }
        login.setPals((buffer.length() != 0) ? buffer.toString() : null);
    }
    
    public void palJH(int pid, ChannelHandlerContext ctx, LoginResult login) {
        PalData palData = GameServer.getPalData(pid);
        if (palData == null) {
            return;
        }
        List<Pal> pals = AllServiceUtil.getPalService().selectPalByRoleID(login.getRole_id());
        for (int i = 0; i < pals.size(); ++i) {
            if (((Pal)pals.get(i)).getpId() == palData.getPalId()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经激活该伙伴了"));
                return;
            }
        }
        StringBuffer buffer = new StringBuffer();
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        if (palData.getXh() != null) {
            if (palData.getXh().startsWith("D")) {
                long xh = Long.parseLong(palData.getXh().substring(1));
                if (xh > login.getGold().longValue()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你金钱不足" + xh));
                    return;
                }
                assetUpdate.updata("D=-" + xh);
                login.setGold(login.getGold().add(new BigDecimal(-xh)));
                MonitorUtil.getMoney().useD(xh);
                buffer.append("你花费");
                buffer.append(xh);
                buffer.append("金钱获得");
            }
            else if (palData.getXh().startsWith("X")) {
                long xh = Long.parseLong(palData.getXh().substring(1));
                if (xh > login.getCodecard().longValue()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你仙玉不足" + xh));
                    return;
                }
                assetUpdate.updata("X=-" + xh);
                login.setCodecard(login.getCodecard().add(new BigDecimal(-xh)));
                MonitorUtil.getMoney().useX(xh);
                buffer.append("你花费");
                buffer.append(xh);
                buffer.append("仙玉获得");
            }
        }
        else {
            buffer.append("你通过免费激活获得");
        }
        buffer.append(palData.getName());
        assetUpdate.setMsg(buffer.toString());
        Pal pal = new Pal();
        pal.setpId(palData.getPalId());
        pal.setRoleId(login.getRole_id());
        AllServiceUtil.getPalService().insertPal(pal);
        assetUpdate.setPal(pal);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void palUPGood(Pal pal, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        PalData palData = GameServer.getPalData(pal.getpId());
        if (palData == null) {
            return;
        }
        int lvl = pal.getLvl();
        long exp = pal.getExp();
        long maxExp = ExpUtil.palExp(lvl);
        int sum = (lvl == 60) ? 10 : ((lvl == 100) ? 20 : ((lvl == 140) ? 40 : ((lvl == 180) ? 80 : 0)));
        if (sum == 0 || maxExp > exp) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还未达到等级上限"));
            return;
        }
        if (sum > (int)good.getUsetime()) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("物品数量不足" + sum));
            return;
        }
        for (exp -= maxExp, maxExp = ExpUtil.palExp(++lvl); exp > maxExp && lvl != 60 && lvl != 100 && lvl != 140 && lvl != 180 && lvl != 300; exp -= maxExp, maxExp = ExpUtil.palExp(++lvl)) {}
        pal.setExp(exp);
        pal.setLvl(lvl);
        good.goodxh(sum);
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        AllServiceUtil.getPalService().updatePal(pal);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.upmsg("你的伙伴:#R" + palData.getName() + "#Y成功突破等级上限");
        assetUpdate.setPal(pal);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void palExpGood(Pal pal, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        PalData palData = GameServer.getPalData(pal.getpId());
        if (palData == null) {
            return;
        }
        int lvl = pal.getLvl();
        long exp = pal.getExp();
        long maxExp = ExpUtil.palExp(lvl);
        if ((lvl == 60 || lvl == 100 || lvl == 140 || lvl == 180) && exp >= maxExp) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("伙伴已经达到等级上限,请先去突破"));
            return;
        }
        if (lvl >= 300) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("已达升级上限"));
            return;
        }
        long addExp = Long.parseLong(good.getValue().split("=")[1]);
        for (exp += addExp; exp > maxExp && lvl != 60 && lvl != 100 && lvl != 140 && lvl != 180 && lvl != 300; exp -= maxExp, maxExp = ExpUtil.palExp(++lvl)) {}
        pal.setExp(exp);
        pal.setLvl(lvl);
        good.goodxh(1);
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        AllServiceUtil.getPalService().updatePal(pal);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.upmsg("你的伙伴:#R" + palData.getName() + "#Y获得#R" + addExp + "#Y经验");
        assetUpdate.setPal(pal);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void palJJGood(Pal pal, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        PalData palData = GameServer.getPalData(pal.getpId());
        if (palData == null) {
            return;
        }
        double grow = pal.getGrow();
        if (grow >= 2.5) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的伙伴已经达到最高品质"));
            return;
        }
        double nGrow = 1.0;
        int gl = SuitComposeAction.random.nextInt(1000);
        if (gl < 10) {
            nGrow = 2.5;
        }
        else if (gl < 25) {
            nGrow = 2.0;
        }
        else if (gl < 160) {
            nGrow = 1.5;
        }
        good.goodxh(1);
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        if (grow != nGrow) {
            pal.setGrow(nGrow);
            AllServiceUtil.getPalService().updatePal(pal);
            assetUpdate.setPal(pal);
        }
        assetUpdate.upmsg("你的伙伴:#R" + palData.getName() + "#Y品质变更为" + ((nGrow == 1.0) ? "#G资质平平" : ((nGrow == 1.5) ? "#B出类拔萃" : ((nGrow == 2.0) ? "#c800080神通广大" : ((nGrow == 2.5) ? "#cFF7F00万中无一" : "")))));
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void palEquipGood(Pal pal, Goodstable good, ChannelHandlerContext ctx, LoginResult login, boolean isEquip) {
        PalData palData = GameServer.getPalData(pal.getpId());
        if (palData == null) {
            return;
        }
        if ((int)good.getStatus() != (isEquip ? 0 : 1)) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的装备状态异常"));
            return;
        }
        if (isEquip) {
            BaseLimit baseLimit = good.getEquip().getBaseLimit();
            if (baseLimit != null && baseLimit.getSex() != 2 && palData.getSex() != baseLimit.getSex()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的伙伴性别不对"));
                return;
            }
        }
        else {
            RoleData roleData = RolePool.getRoleData(login.getRole_id());
            if (roleData.isGoodFull()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("背包已满,先清空背包"));
                return;
            }
        }
        int path = palEquipPath(good.getType());
        if (path == -1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("非伙伴装备"));
            return;
        }
        StringBuffer buffer = new StringBuffer();
        boolean is = isEquip;
        BigDecimal oldRgid = null;
        if (pal.getParts() != null && !pal.getParts().equals("")) {
            String[] vs = pal.getParts().split("\\|");
            String qz = path + "=";
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].startsWith(qz)) {
                    oldRgid = new BigDecimal(vs[i].substring(qz.length()));
                    if (!isEquip && oldRgid.compareTo(good.getRgid()) == 0) {
                        is = true;
                    }
                }
                else {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(vs[i]);
                }
            }
        }
        if (!is) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的伙伴装备状态异常"));
            return;
        }
        Goodstable goodTwo = null;
        if (isEquip) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(path);
            buffer.append("=");
            buffer.append(good.getRgid());
            if (oldRgid != null) {
                goodTwo = AllServiceUtil.getGoodsTableService().getGoodsByRgID(oldRgid);
            }
        }
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, null, Integer.valueOf(isEquip ? 1 : 0));
        if (goodTwo != null) {
            AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodTwo, null, null, Integer.valueOf(0));
        }
        pal.setParts((buffer.length() != 0) ? buffer.toString() : null);
        AllServiceUtil.getPalService().updatePal(pal);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setPal(pal);
        assetUpdate.setGood(good);
        if (goodTwo != null) {
            assetUpdate.setGood(goodTwo);
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static int palEquipPath(long type) {
        if (type == 7503L) {
            return 0;
        }
        if (type == 7504L || type == 7508L) {
            return 1;
        }
        if (type == 7505L || type == 7509L) {
            return 2;
        }
        if (type == 7506L) {
            return 3;
        }
        if (type == 7507L) {
            return 4;
        }
        return -1;
    }
}
