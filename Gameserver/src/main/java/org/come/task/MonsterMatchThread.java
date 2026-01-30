package org.come.task;

import come.tool.Battle.BattleThreadPool;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.protocol.Agreement;

public class MonsterMatchThread extends Thread
{
    public String MSG;
    public String MSGTwo;
    boolean is;
    
    public MonsterMatchThread() {
        this.MSG = Agreement.getAgreement().PromptAgreement("地煞星正在挑选顺眼的玩家…… 请等待#24");
        this.MSGTwo = Agreement.getAgreement().PromptAgreement("上古之魂,正在备战状态，请等待战斗回合#32");
        this.setDaemon(this.is = true);
    }
    
    public void addMatch() {
        synchronized (this) {
            if (this.is) {
                return;
            }
            this.is = true;
            this.notifyAll();
        }
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000L);
                if (MonsterUtil.isMatch()) {
                    for (int i = MonsterUtil.matchList.size() - 1; i >= 0; --i) {
                        MapMonsterBean bean = (MapMonsterBean)MonsterUtil.matchList.get(i);
                        if (bean.getType() != 0 || bean.getMatch() == null) {
                            MonsterUtil.matchList.remove(i);
                        }
                        else {
                            MonsterMatch match = bean.getMatch();
                            match.setCountDown(match.getCountDown() - 1);
                            if (match.getCountDown() <= 0) {
                                while (true) {
                                    String roleName = match.getMatch();
                                    if (roleName == null) {
                                        break;
                                    }
                                    else {
                                        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleName);
                                        if (ctx == null) {
                                            continue;
                                        }
                                        else {
                                            LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                                            if (loginResult == null) {
                                                continue;
                                            }
                                            else if ((long)bean.getMap() != (long)loginResult.getMapid()) {
                                                continue;
                                            }
                                            else if (Math.abs((long)(int)bean.getX() - (long)loginResult.getX()) <= 600L) {
                                                if (Math.abs(bean.getY() - (long)loginResult.getY()) > 600L) {
                                                    continue;
                                                }
                                                else {
                                                    String[] teams = loginResult.getTeam().split("\\|");
                                                    if (!teams[0].equals(loginResult.getRolename())) {
                                                        continue;
                                                    }
                                                    else if (BattleThreadPool.addBattle(loginResult, teams, bean)) {
                                                        bean.getMatch().clearMatch();
                                                        break;
                                                    }
                                                    else {
                                                        continue;
                                                    }
                                                }
                                            }
                                            else {
                                                continue;
                                            }
                                        }
                                    }
                                }
                                if (bean.getMatch() != null) {
                                    bean.getMatch().clearMatch();
                                }
                                MonsterUtil.matchList.remove(i);
                            }
                            else if (match.getCountDown() % 3 == 0) {
                                match.sendMatch();
                            }
                        }
                    }
                }
                else {
                    synchronized (this) {
                        this.is = false;
                        this.wait();
                    }
                }
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
