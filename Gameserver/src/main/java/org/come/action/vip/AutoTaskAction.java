package org.come.action.vip;

import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.task.MapMonsterBean;
import org.come.task.MonsterUtil;
import org.come.server.GameServer;
import org.come.model.Boos;
import org.apache.commons.lang.StringUtils;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class AutoTaskAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (StringUtils.isNotBlank(message)) {
            String[] vs = message.split("-");
            try {
                for (int i = 1; i < vs.length; ++i) {
                    Boos boos = (Boos)GameServer.boosesMap.get(vs[i]);
                    if (boos != null) {
                        long mapId = (long)GameServer.getMapIds(boos.getBoosmapname());
                        List<MapMonsterBean> list = MonsterUtil.getList(mapId, Integer.parseInt(boos.getBoosrobot()));
                        if (list != null && list.size() > 0) {
                            MapMonsterBean mapMonsterBean = (MapMonsterBean)list.get(GameServer.random.nextInt(list.size()));
                            StringBuffer buffer = new StringBuffer();
                            buffer.append(mapMonsterBean.getMap());
                            buffer.append("-");
                            buffer.append(mapMonsterBean.getX());
                            buffer.append("-");
                            buffer.append(mapMonsterBean.getY());
                            buffer.append("-");
                            buffer.append(mapMonsterBean.getI());
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getAutoTaskAgreement(buffer.toString()));
                            return;
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getAutoTaskAgreement(vs[0]));
        }
    }
}
