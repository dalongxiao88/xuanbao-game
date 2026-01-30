package org.come.action.fight;

import java.util.ArrayList;
import java.util.HashMap;
import come.tool.FightingData.Battlefield;
import org.come.protocol.ParamTool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.Map;
import org.come.action.IAction;

public class PrisonAction implements IAction
{
    static Map<Integer, List<String>> Prisonroom;
    static String GOHOME;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleinfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        roleinfo.setTaskDaily(message);
        String team = roleinfo.getTeamInfo();
        if (team != null && !team.equals("")) {
            if (team.split("\\|")[0].equals(roleinfo.getRolename())) {
                IAction action = (IAction)ParamTool.ACTION_MAP.get("teambreak");
                action.action(ctx, null);
            }
            else {
                IAction action = (IAction)ParamTool.ACTION_MAP.get("teammove");
                action.action(ctx, null);
            }
        }
        String[] v = message.split("\\|");
        int ll = Integer.parseInt(v[0]);
        if (ll == 0) {
            IAction action2 = (IAction)ParamTool.ACTION_MAP.get("changemap");
            action2.action(ctx, PrisonAction.GOHOME);
            return;
        }
        if (ll > 4) {
            ll = 2;
        }
        else if (ll >= 8) {
            ll = 3;
        }
        else {
            ll = 1;
        }
        IAction action2 = (IAction)ParamTool.ACTION_MAP.get("changemap");
        action2.action(ctx, (String)((List<String>)PrisonAction.Prisonroom.get(Integer.valueOf(ll))).get(Battlefield.random.nextInt(((List<String>)PrisonAction.Prisonroom.get(Integer.valueOf(ll))).size())));
    }
    
    static {
        PrisonAction.Prisonroom = new HashMap<>();
        PrisonAction.GOHOME = "{\"mapid\":\"1207\",\"mapx\":4297,\"mapy\":2887}";
        List<String> room1 = new ArrayList<>();
        room1.add("{\"mapid\":\"3312\",\"mapx\":2203,\"mapy\":1138}");
        List<String> room2 = new ArrayList<>();
        room2.add("{\"mapid\":\"3313\",\"mapx\":338,\"mapy\":1214}");
        List<String> room3 = new ArrayList<>();
        room3.add("{\"mapid\":\"3314\",\"mapx\":1410,\"mapy\":1015}");
        PrisonAction.Prisonroom.put(Integer.valueOf(1), room1);
        PrisonAction.Prisonroom.put(Integer.valueOf(2), room2);
        PrisonAction.Prisonroom.put(Integer.valueOf(3), room3);
    }
}
