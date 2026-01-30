package come.tool.Good;

import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import java.util.Iterator;
import java.util.List;
import org.come.entity.RoleTable;
import org.come.until.GsonUtil;
import come.tool.Role.RolePool;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.Fly;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UseFlyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        String[] vs = message.split("\\|");
        Fly fly = AllServiceUtil.getFlyService().selectFlysByMID(new BigDecimal(vs[1]));
        if (fly == null) {
            return;
        }
        if (vs[0].equals("flyxh")) {
            if (loginResult.getTroop_id() == null) {
                this.editFlyfuel(fly, ctx, loginResult);
            }
            else {
                this.editFlyfuel(fly, ctx, loginResult);
                String[] team = loginResult.getTeamInfo().split("\\|");
                boolean hasInvalidFuel = true;
                for (int i = 0; i < team.length && hasInvalidFuel; ++i) {
                    RoleTable userTable = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(team[i]);
                    List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(userTable.getRole_id());
                    List<LoginResult> loginResults = GameServer.gameServer.getUserRole(userTable.getUser_id());
                    for (Fly fly2 : flys) {
                        if (fly2.getFlytid().equals(((LoginResult)loginResults.get(0)).getFly_id()) && (long)fly2.getFuel() <= 0L) {
                            hasInvalidFuel = false;
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(team[i] + "飞行器燃料不足"));
                            break;
                        }
                    }
                }
                if (!hasInvalidFuel) {
                    for (int i = 0; i < team.length; ++i) {
                        RoleTable userTable = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(team[i]);
                        List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(userTable.getRole_id());
                        List<LoginResult> loginResults = GameServer.gameServer.getUserRole(userTable.getUser_id());
                        for (int j = 0; j < flys.size(); ++j) {
                            ((LoginResult)loginResults.get(0)).setFly_id(null);
                            RolePool.getRoleData(loginResult.getRole_id()).setMid(null);
                            ((LoginResult)loginResults.get(0)).setFlyskin(null);
                        }
                        SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(((LoginResult)loginResults.get(0)).getRoleShow())));
                    }
                }
            }
            return;
        }
        else {
            if (fly.getRoleid().compareTo(loginResult.getRole_id()) != 0) {
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
            if (type == 1101L) {
                this.addFlyExp(fly, good, ctx, loginResult);
            }
            else if (type == 1102L) {
                this.addFlystate(fly, good, ctx, loginResult);
            }
            else if (type == 1103L) {
                this.addFlyfuel(fly, good, ctx, loginResult);
            }
            return;
        }
    }
    
    public void editFlyfuel(Fly fly, ChannelHandlerContext ctx, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        fly.setFuel(Long.valueOf((long)fly.getFuel() - 10L));
        AllServiceUtil.getFlyService().updateFlyRedis(fly);
        assetUpdate.setFly(fly);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void addFlyExp(Fly fly, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int lvl = (int)fly.getFlylvl();
        if (lvl == 100) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("飞行器 " + fly.getFlyname() + " 已达最高等级100级！！"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        int addexp = Integer.parseInt(good.getValue().split("=")[1]);
        ExpUtil.FlyExp(fly, addexp);
        AllServiceUtil.getFlyService().updateFlyRedis(fly);
        assetUpdate.setFly(fly);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void addFlyfuel(Fly fly, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        int addfuel = Integer.parseInt(good.getValue().split("=")[1]);
        fly.setFuel(Long.valueOf((long)fly.getFuel() + (long)addfuel));
        AllServiceUtil.getFlyService().updateFlyRedis(fly);
        assetUpdate.setFly(fly);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    private void addFlystate(Fly fly, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int lvl = (int)fly.getFlylvl();
        int stat = (int)fly.getFlystate();
        if (stat >= 5) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("飞行器已经是最高阶"));
            return;
        }
        if (lvl != 100) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("飞行器等级不足"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        fly.setFlylvl(Integer.valueOf(0));
        fly.setFlystate(Integer.valueOf((int)fly.getFlystate() + 1));
        fly.setSkin(Integer.valueOf((int)fly.getSkin() + 1));
        fly.setExp(Integer.valueOf(0));
        String name = this.ChangeFlyName(fly.getFlyname());
        fly.setFlyname(name);
        AllServiceUtil.getFlyService().updateFlyRedis(fly);
        assetUpdate.setFly(fly);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        if (login.getFly_id()!=null&&fly.getMid().compareTo(RolePool.getRoleData(login.getRole_id()).getMid())==0) {
            login.setFly_id(fly.getFlytid());
            RolePool.getRoleData(login.getRole_id()).setMid(fly.getMid());
            login.setFlyskin(String.valueOf(fly.getSkin()));
            SendMessage.sendMessageToMapRoles(login.getMapid(),Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(login.getRoleShow())));

        }
    }
    
    public String ChangeFlyName(String name) {
        String[] vs = { "香叶扇", "寒露扇", "雕龙玉扇", "春秋乾坤扇", "火凰焚天扇", "富贵锦", "无妄锦", "玲珑素锦", "遮霞闭月锦", "叠彩狮王锦", "奔云燕", "冰霓蝶", "妙音彩鱼", "紫蜈追梦筝", "飞龙流珠席", "净心荷", "定魂莲", "画水尘莲", "碧影琉璃台", "金玉宝莲台", "轻鸿羽", "藏虹羽", "百灵风羽", "青澜牵星羽", "流冥翠羽", "筋斗云", "旋霜云", "万象星云", "电闪雷鸣云", "五色祥云" };
        if (name.equals("火凰焚天扇") || name.equals("叠彩狮王锦") || name.equals("飞龙流珠席") || name.equals("金玉宝莲台") || name.equals("流冥翠羽") || name.equals("五色祥云")) {
            return name;
        }
        for (int i = 0; i <= vs.length; ++i) {
            if (name.equals(vs[i])) {
                return vs[i + 1];
            }
        }
        return name;
    }
}
