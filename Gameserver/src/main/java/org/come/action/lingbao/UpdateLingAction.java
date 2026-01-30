package org.come.action.lingbao;

import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import org.come.until.AchievemUtil;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Lingbao;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UpdateLingAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        Lingbao lingbao = (Lingbao)GsonUtil.getGsonUtil().getgson().fromJson(message, Lingbao.class);
        Lingbao lingbaoRedis = AllServiceUtil.getLingbaoService().selectLingbaoByID(lingbao.getBaoid());
        if (lingbaoRedis == null || loginResult.getRole_id().compareTo(lingbaoRedis.getRoleid()) != 0) {
            return;
        }
        if (lingbao.getOperation() == null || lingbao.getOperation().equals("")) {
            Integer yss = Integer.valueOf(lingbaoRedis.getEquipment());
            Integer nss = Integer.valueOf(lingbao.getEquipment());
            int ylvl = lingbaoRedis.getLingbaolvl().intValue();
            int nlvl = lingbao.getLingbaolvl().intValue();
            if (nlvl - ylvl == 1 && ylvl % 30 == 0) {
                lingbaoRedis.setLingbaolvl(lingbao.getLingbaolvl());
                if (lingbaoRedis.getLingbaoexe().compareTo(lingbao.getLingbaoexe()) > 0) {
                    lingbaoRedis.setLingbaoexe(lingbao.getLingbaoexe());
                }
            }
            lingbaoRedis.setBaoname(lingbao.getBaoname());
            lingbaoRedis.setSkillsum(lingbao.getSkillsum());
            lingbaoRedis.setFusum(lingbao.getFusum());
            lingbaoRedis.setSkills(lingbao.getSkills());
            lingbaoRedis.setKangxing(lingbao.getKangxing());
            if (lingbao.getGoodlock() == 0) {
                lingbaoRedis.setFushis(lingbao.getFushis());
            }
            lingbaoRedis.setEquipment(lingbao.getEquipment());
            lingbaoRedis.setGoodlock(lingbao.getGoodlock());
            AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbaoRedis);
            if (nss != null && nss != yss) {
                RoleData data = RolePool.getRoleData(lingbao.getRoleid());
                if (data != null) {
                    data.CLing(lingbao.getBaoid(), lingbao.getBaotype(), (int)nss == 1);
                }
            }
            AchievemUtil.detectionAchievem(loginResult,"拥有一个灵宝");
        }
        else if (lingbao.getOperation().equals("删除")) {
            if (lingbao.getGoodlock() == 1) {
                return;
            }
            AllServiceUtil.getLingbaoService().deleteLingbao(lingbao.getBaoid());
        }
    }
}
