package come.tool.Good;

import org.come.model.Skill;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import org.come.entity.Lingbao;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UseLingAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        String[] vs = message.split("\\|");
        Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(new BigDecimal(vs[1]));
        if (lingbao == null) {
            return;
        }
        if (lingbao.getRoleid().compareTo(loginResult.getRole_id()) != 0) {
            return;
        }
        Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[0]));
        if (good == null) {
            return;
        }
        if (good.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
            return;
        }
        if ((int)good.getUsetime() <= 0) {
            return;
        }
        long type = good.getType();
        if (type == 190L) {
            lingbaoskill(lingbao, good, ctx, loginResult);
        }
        else if (type == 891L) {
            lingbaoQuality(lingbao, good, ctx, loginResult);
        }
        else if (type == 1002L) {
            baoExp(lingbao, good, ctx, loginResult);
        }
        else if (type == 28955L) {
            baoqh(lingbao, good, ctx, loginResult);
        }
    }
    
    public static void baoqh(Lingbao lingbao, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        lingbao.setLingbaoqihe(lingbao.getLingbaoqihe() + 10000L);
        int addqh = Integer.parseInt(good.getValue().split("=")[1]);
        String msg = ExpUtil.LFqh(lingbao, (long)addqh);
        AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
        assetUpdate.setLingbao(lingbao);
        assetUpdate.setMsg(msg);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void baoExp(Lingbao lingbao, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        int addexp = Integer.parseInt(good.getValue().split("=")[1]);
        String msg = ExpUtil.LFExp(lingbao, (long)addexp);
        AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
        assetUpdate.setLingbao(lingbao);
        assetUpdate.setMsg(msg);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void lingbaoQuality(Lingbao lingbao, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int pz = getQv(lingbao.getBaoquality());
        if (pz == 50) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("已经达到最高品质了"));
            return;
        }
        pz /= 10;
        ++pz;
        int v = 50;
        int sum = 1;
        switch (pz) {
            case 2: {
                v = 17;
                sum = 1;
                break;
            }
            case 3: {
                v = 13;
                sum = 2;
                break;
            }
            case 4: {
                v = 7;
                sum = 4;
                break;
            }
            case 5: {
                v = 3;
                sum = 10;
                break;
            }
        }
        if ((int)good.getUsetime() < sum) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该品质提升需要消耗" + sum + "个灵宝诸天印"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, sum);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        if (DropUtil.isV((double)v)) {
            switch (pz) {
                case 2: {
                    lingbao.setBaoquality("贴身");
                    break;
                }
                case 3: {
                    lingbao.setBaoquality("珍藏");
                    break;
                }
                case 4: {
                    lingbao.setBaoquality("无价");
                    break;
                }
                case 5: {
                    lingbao.setBaoquality("传世");
                    break;
                }
                default: {
                    lingbao.setBaoquality("把玩");
                    break;
                }
            }
            AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
            assetUpdate.setLingbao(lingbao);
            assetUpdate.setMsg("提升成功");
        }
        else {
            assetUpdate.setMsg("提升失败");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void lingbaoskill(Lingbao lingbao, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (lingbao.getBaotype().equals("法宝")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("法宝不能打技能"));
            return;
        }
        String[] v = good.getValue().split("=")[1].split("&");
        String skillname = v[GameServer.random.nextInt(v.length)];
        Skill skill = GameServer.getSkill(skillname);
        if (skill == null) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        String msg = lingbao.skilljihe(skill);
        if (msg == null) {
            AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
            assetUpdate.setLingbao(lingbao);
            assetUpdate.setMsg("学习成功");
        }
        else {
            assetUpdate.setMsg(msg);
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static int getQv(String quality) {
        int n = -1;
        switch (quality.hashCode()) {
            case 811615: {
                if (quality.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (quality.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (quality.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (quality.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (quality.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return 10;
            }
            case 1: {
                return 20;
            }
            case 2: {
                return 30;
            }
            case 3: {
                return 40;
            }
            case 4: {
                return 50;
            }
            default: {
                return 10;
            }
        }
    }
}
