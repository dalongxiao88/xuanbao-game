package org.come.action.lingbao;

import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;
import org.come.bean.LoginResult;
import org.come.entity.XuanBao;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;


public class UpdateXuanAction implements IAction {
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = GameServer.getAllLoginRole().get(ctx);
        XuanBao xuanbao = GsonUtil.getGsonUtil().getgson().fromJson(message, XuanBao.class);
        XuanBao xuanbaoRedis = AllServiceUtil.getXuanBaoService().selectLingbaoByID(xuanbao.getBid());
        if (xuanbaoRedis == null || loginResult.getRole_id().compareTo(xuanbaoRedis.getRoleid()) != 0) {
            return;
        }
        if (xuanbao.getOperation() == null || xuanbao.getOperation().equals("")) {// 修改玄宝
            int ylvl = xuanbaoRedis.getLvl();
            int nlvl = xuanbao.getLvl();
            if (nlvl - ylvl == 1 && ylvl % 30 == 0) {
                xuanbaoRedis.setLvl(xuanbao.getLvl());
            }
            xuanbaoRedis.setNum(xuanbao.getNum());
            xuanbaoRedis.setFushi(xuanbao.getFushi());
            xuanbaoRedis.setEquipment(xuanbao.getEquipment());
            AllServiceUtil.getXuanBaoService().updateLingbaoRedis(xuanbaoRedis);
        } else if (xuanbao.getOperation().equals("删除")) {// 删除玄宝
            AllServiceUtil.getXuanBaoService().deleteLingbao(xuanbao.getBid());
        }
    }
}
