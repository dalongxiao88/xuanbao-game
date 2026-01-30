package come.tool.Good;

import java.util.concurrent.ConcurrentHashMap;
import org.come.model.Configure;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import org.come.entity.Mount;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UseMountAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        String[] vs = message.split("\\|");
        Mount mount = AllServiceUtil.getMountService().selectMountsByMID(new BigDecimal(vs[1]));
        if (mount == null) {
            return;
        }
        if (mount.getRoleid().compareTo(loginResult.getRole_id()) != 0) {
            return;
        }
        if (vs[0].equals("DH")) {
            this.DHMount(mount, ctx, loginResult);
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
        if (type != 718L && type != 719L && type != 720L) {
            if (type == 721L) {
                this.addMountValue(mount, good, ctx, loginResult);
            }
            else if (type == 801L) {
                this.addMountExp(mount, good, ctx, loginResult);
            }
            else if (type == 802L) {
                this.addMountProficiency(mount, good, ctx, loginResult);
            }
            else if (type == 2127L || type == 2128L || type == 2129L) {
                if ((int)mount.getMountid() > 7) {
                    if (mount.getMoveGrade() == null) {
                        mount.setMoveGrade(Integer.valueOf(0));
                    }
                    if ((int)mount.getMoveGrade() >= 30) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑已达到最高移动速度等级！"));
                        return;
                    }
                    if (type == 2127L && (int)mount.getMoveGrade() >= 10) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑已达到一级移动速度不可以使用该道具，请兑换更高级的道具！"));
                        return;
                    }
                    if (type == 2128L && (int)mount.getMoveGrade() >= 20) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑已达到二级移动速度不可以使用该道具，请兑换更高级的道具！"));
                        return;
                    }
                    if (type == 2128L && (int)mount.getMoveGrade() < 10) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑未达到一级移动速度不可以使用该道具！"));
                        return;
                    }
                    if (type == 2129L && (int)mount.getMoveGrade() < 20) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑未达到二级移动速度不可以使用该道具！"));
                        return;
                    }
                    this.addMountMove(mount, good, ctx, loginResult);
                }
                else {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前坐骑不属于飞行坐骑无法服用！"));
                    return;
                }
            }
        }
    }
    
    public void DHMount(Mount mount, ChannelHandlerContext ctx, LoginResult login) {
        if ((int)mount.getMountlvl() != 100 || (int)mount.getProficiency() < 100000) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑不符合点化要求"));
            return;
        }
        if (mount.getSid() != null || mount.getOthrersid() != null || mount.getSid3() != null || mount.getSid4() != null || mount.getSid5() != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑还管制着召唤兽"));
            return;
        }
        mount.setMountlvl(Integer.valueOf(101));
        mount.setExp(Integer.valueOf(0));
        mount.setProficiency(Integer.valueOf(0));
        mount.setSpri(Integer.valueOf(mount.getSpri() + 3));
        mount.setPower(Integer.valueOf(mount.getPower() + 3));
        mount.setBone(Integer.valueOf(mount.getBone() + 3));
        AllServiceUtil.getMountService().updateMountRedis(mount);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.setMount(mount);
        assetUpdate.setMsg("点化成功");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
//        AchievemUtil.detectionAchievem(login, "点化坐骑");
        if(mount.getMountid() == 1) {
            AchievemUtil.detectionAchievem(login, "点化一坐骑");
        }
        if(mount.getMountid() == 2) {
            AchievemUtil.detectionAchievem(login, "点化二坐骑");
        }
        if(mount.getMountid() == 3) {
            AchievemUtil.detectionAchievem(login, "点化三坐骑");
        }
        if(mount.getMountid() == 4) {
            AchievemUtil.detectionAchievem(login, "点化四坐骑");
        }
        if(mount.getMountid() == 5) {
            AchievemUtil.detectionAchievem(login, "点化五坐骑");
        }
        if(mount.getMountid() == 6) {
            AchievemUtil.detectionAchievem(login, "点化六坐骑");
        }
        if(mount.getMountid() == 7) {
            AchievemUtil.detectionAchievem(login, "点化七坐骑");
        }
    }
    public static String hanzi(int num){
        if (num == 1) {
            return "一";
        } else if (num == 2) {
            return "二";
        } else if (num == 3) {
            return "三";
        } else if (num == 4) {
            return "四";
        } else if (num == 5) {
            return "五";
        } else if (num == 6) {
            return "六";
        } else if (num == 7) {
            return "七";
        } else {
            return "零";
        }
    }
    public void addMountProficiency(Mount mount, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int up = 100000;
        if (mount.getMountlvl() > 100) {
            ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
            Configure configure = s.get(Integer.valueOf(1));
            up = Integer.parseInt(configure.getZqsld());
        }
        if (mount.getProficiency() >= up) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑 " + mount.getMountname() + "的技能熟练度已达到峰值！！"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        int addvalue = Integer.parseInt(good.getValue().split("=")[1]);
        int proficiency = mount.getProficiency() + addvalue;
        if (proficiency > up) {
            proficiency = up;
        }
        mount.setProficiency(Integer.valueOf(proficiency));
        AllServiceUtil.getMountService().updateMountRedis(mount);
        assetUpdate.setMount(mount);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        if (proficiency >= 5000) {
            AchievemUtil.detectionAchievem(login, hanzi(mount.getMountid())+"座熟练到5000");
        }
        if (proficiency >= 20000) {
            AchievemUtil.detectionAchievem(login, hanzi(mount.getMountid())+"座熟练到20000");
        }
        if (proficiency >= 50000) {
            AchievemUtil.detectionAchievem(login, hanzi(mount.getMountid())+"座熟练到50000");
        }
        if (proficiency >= 100000) {
            AchievemUtil.detectionAchievem(login, hanzi(mount.getMountid())+"座熟练到100000");
        }



    }
    
    public void addMountExp(Mount mount, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int lvl = mount.getMountlvl();
        if (lvl == 100 || lvl >= 300) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑 " + mount.getMountname() + " 已达最高等级100级！！"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        int addexp = Integer.parseInt(good.getValue().split("=")[1]);
        ExpUtil.MountExp(mount, addexp);
        AllServiceUtil.getMountService().updateMountRedis(mount);
        assetUpdate.setMount(mount);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void addMountMove(Mount mount, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int lvl = (int)mount.getMoveGrade();
        if (lvl == 30 || lvl >= 30) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("坐骑 " + mount.getMountname() + " 已达最高移动等级三级！！"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        ExpUtil.MountMove(mount, 1);
        AllServiceUtil.getMountService().updateMountRedis(mount);
        assetUpdate.setMount(mount);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void addMountValue(Mount mount, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(Integer.valueOf(1));
        if (mount.getUseNumber() >= Integer.parseInt(configure.getJgtqwsx())) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("只能使用" + Integer.parseInt(configure.getJgtqwsx()) + "次筋骨提气丹"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        mount.setBone(Integer.valueOf(mount.getBone() + 1));
        mount.setSpri(Integer.valueOf(mount.getSpri() + 1));
        mount.setPower(Integer.valueOf(mount.getPower() + 1));
        mount.setUseNumber(Integer.valueOf(mount.getUseNumber() + 1));
        AllServiceUtil.getMountService().updateMountRedis(mount);
        assetUpdate.setMount(mount);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void mountMissSkills(Mount mount, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
    }
    
    public void useMountSkillCard(Mount mount, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
    }
}
