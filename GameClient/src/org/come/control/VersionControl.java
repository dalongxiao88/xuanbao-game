package org.come.control;

import org.come.test.Main;
import io.netty.channel.ChannelHandlerContext;
import org.come.socket.SendMessageUntil;
import org.come.action.MapAction;
import org.come.socket.GameClient;
import org.come.action.FromServerAction;

public class VersionControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        try {
            if (!mes.startsWith(GameClient.VERSION)) {
                try {
                    MapAction.flagAction.put(SendMessageUntil.accServerKey, Boolean.valueOf(false));
                    if (MapAction.nettyAction.get(SendMessageUntil.accServerKey) != null) {
                        ((ChannelHandlerContext)MapAction.nettyAction.get(SendMessageUntil.accServerKey)).channel().close();
                    }
                    MapAction.nettyAction.put(SendMessageUntil.accServerKey, null);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (Main.frame == null || Main.frame.getLoginJpanel() == null || Main.frame.getLoginJpanel().getLoginView() == null) {
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (Exception ex) {}
                        try {
                            Main.frame.getLoginJpanel().getLoginView().setMsg("版本已过期，请下载最新登录器!");
                        }
                        catch (Exception ex2) {}
                    }
                    try {
                        Main.frame.getLoginJpanel().getAreaView().setMsg("版本已过期，请下载最新登录器!");
                    }
                    catch (Exception ex3) {}
                    Thread.sleep(5000L);
                    System.exit(0);
                }
            }
            else if (!mes.equals(GameClient.VERSION)) {
                mes = mes.substring(GameClient.VERSION.length());
                String[] vs = mes.split("\\|");
                for (int i = 0; i < vs.length; ++i) {
                    if (vs[i].startsWith("L")) {
                        GameClient.lianhua = Integer.parseInt(vs[i].substring(1));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
