package come.tool.activity.WSS;

import org.come.protocol.Agreement;
import java.util.HashMap;
import org.come.tool.WriteOut;
import org.come.handler.MainServerHandler;
import java.util.GregorianCalendar;
import org.come.action.monitor.MonitorUtil;
import io.netty.channel.ChannelHandlerContext;
import com.gl.util.GLUtil;
import org.come.model.Robots;
import come.tool.FightingData.ManData;
import come.tool.FightingData.Battlefield;
import org.come.server.GameServer;
import org.come.model.Monster;
import come.tool.Battle.BattleData;
import org.come.handler.SendMessage;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleMixDeal;
import org.come.bean.LoginResult;
import java.util.Iterator;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.come.entity.BattleRole;
import java.util.List;
import java.util.Map;

public class BattleInfo
{
    private static Map<Integer, List<BattleRole>> battleMap;
    public static String CHECKTS1;
    public static String CHECKTS2;
    public static String CHECKTS3;
    public static String CHECKTS4;
    public static String CHECKTS5;
    public static String CHECKTS6;
    public static final String[] SHIJIAN;

    /**
     * 守护者AI
     */

    //public static final String ROLE_


    //条件=回合-1=法术-1005-25000|条件=回合-5=法术-1075-25000|条件=回合-10=法术-1075-25000|条件=回合-15=法术-1075-25000|条件=回合-20=法术-1075-25000|条件=回合-25=法术-1075-25000|条件=回合-30=法术-1075-25000|条件=回合-35=法术-1075-25000|条件=死亡-5902=救人-5902-90

    //男人混
    //条件=回合-1=法术-1005-25000

    //女人毒
    //条件=回合-1=法术-1020-25000

    //男魔  先速，有速就抽
    //条件=回合-1=法术-1040-25000|条件=携带状态-加速=法术-1025-25000

    //女魔  盘，加血，救人
    //条件=回合-1=法术-1035-25000|加血=10&40|救人=10&40

    //大力魔 先牛，有牛就砍
    //条件=回合-1=法术-1030-25000|条件=携带状态-力量=物理狂暴-999

    //仙  根据忽视，取最高法术      风雷水火
    //条件=回合-1=法术-1045-25000
    //条件=回合-1=法术-1050-25000
    //条件=回合-1=法术-1055-25000
    //条件=回合-1=法术-1060-25000


    //女鬼火
    //条件=回合-1=法术-1065-25000


    //男遗忘
    //条件=回合-1=法术-1075-25000

    //男三尸
    //条件=回合-1=法术-1070-25000

    //龙甘霖
    //条件=回合-1=法术-1095-25000

    //龙霹雳
    //条件=回合-1=法术-1085-25000






    //加血=10&40|救人=10&40


    //}else if (type.equals("减人仙")||type.equals("减魔鬼")||type.equals("庇护")||type.equals("化无")||type.equals("中毒")||type.equals("力量")||type.equals("抗性")||type.equals("加速")) {

    /**
     * 获取对应类型的守护队伍
     * @param wssType
     * @return
     */
    public static List<BattleRole> getBattleRoles(int wssType) {
        if (BattleInfo.battleMap.containsKey(wssType)) {
            return BattleInfo.battleMap.get(wssType);
        }
        return new ArrayList<>();
    }
    /**
     * 获取对应类型的守护队伍
     * @param wssType
     * @return
     */
    public static void setBattleRoles(int wssType, List<BattleRole> battleRoles) {
        BattleInfo.battleMap.put(wssType, battleRoles);
    }
    /**
     * 查找玩家队伍是否有人在守护位
     * @param roleids
     * @return
     */
    public static int getBattleRoleCount(List<BigDecimal> roleids) {
        for (int key : BattleInfo.battleMap.keySet()) {
            List<BattleRole> list = (List<BattleRole>)BattleInfo.battleMap.get(key);
            for (BattleRole role : list) {
                for (BigDecimal roleID : roleids) {
                    if (role.getRoleid().compareTo(roleID) == 0) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    /**
     * 获取对应类型的守护队伍队员名称|&|守护时间
     * @param wssType
     * @return
     */
    public static String getBattleRoleNames(int wssType) {
        List<BattleRole> roles = getBattleRoles(wssType);
        StringBuilder str = new StringBuilder();
        if (roles.size() > 0) {
            for (BattleRole br : roles) {
                if (str.length() > 0) {
                    str.append("、");
                }
                str.append(br.getRolename());
            }
            long ke = (System.currentTimeMillis() - ((BattleRole)roles.get(0)).getStarttime()) / 1000L / 60L / 15L;
            long shi = ke / 4L;
            ke %= 4L;
            str.append("\n他们已经守护了  " + BattleInfo.SHIJIAN[(int)shi] + "时" + BattleInfo.SHIJIAN[(int)ke] + "刻");
        }
        return str.toString();
    }
    
    public static String battle(LoginResult role, int wssType) {
        // 判断当前玩家是否出在守护者位置

        String[] teams = role.getTeam().split("\\|");
        BattleData battleData = BattleMixDeal.initWSSData(teams, wssType, (long)role.getMapid());
        String value = isRole(battleData.getTeam1(), battleData, 1);
        if (value != null) {
            return value;
        }

        /**加载被挑战人的数据*/
        // 查询是否有被挑战人，没有挑战人则加载野怪
        List<BattleRole> roleList = getBattleRoles(wssType);
        if (roleList == null || roleList.size() == 0) {
            loadCreep(battleData, (int)role.getGrade());
        }
        else {
            loadCreep(roleList, battleData, wssType);
        }
        battleData.setCalculator(battleData.getParticipantlist().size());

        // 进战斗之前再校验一次
        if (BattleThreadPool.BattleDatas.get(wssType) != null) {
            SendMessage.sendMessageByRoleName(role.getRolename(), BattleInfo.CHECKTS2);
            return null;
        }
        BattleThreadPool.addPool(battleData);
        return null;
    }

    /**
     * 无人守护时 加载守护者野怪数据
     * @param
     * @param battleData
     * @return
     */
    public static void loadCreep(BattleData battleData, int lvl) {
        List<String> list = createCreep(battleData.getRobots(), 9);
        list.add(0, battleData.getRobots().getRobotboss());
        loadCreep(list, battleData.getRobots().getRobotname(), lvl, battleData, 1.0);
    }

    /** 加载野怪数据 */
    public static void loadCreep(List<String> fightCreeps, String alias, int maxLvl, BattleData battleData, double xs) {
        if (fightCreeps == null || fightCreeps.size() == 0) {
            return;
        }
        for (int i = 0; i < fightCreeps.size(); ++i) {
            Monster monster = (Monster)GameServer.getMonsterMap().get(fightCreeps.get(i));
            if (monster != null) {
                ManData data = new ManData(monster, 0, Battlefield.Fightingpath(0, i), maxLvl);
                if (xs != 1.0) {
                    data.JQ(xs);
                }
                if (i == 0) {
                    if (alias != null && !alias.equals("")) {
                        data.setManname(alias);
                    }
                    battleData.getBattlefield().yename = alias;
                }
                battleData.addFightingdata(data);
            }
        }
    }
    
    public static List<String> createCreep(Robots robots, int num) {
        List<String> fightCreeps = new ArrayList<>();
        if (num <= 0) {
            return fightCreeps;
        }
        List<String> monsterList = robots.getMonsterList();
        if (num > 9) {
            num = 9;
        }
        if (num == monsterList.size() || num == 0) {
            for (int i = 0; i < 9 && i < monsterList.size(); ++i) {
                fightCreeps.add(monsterList.get(i));
            }
        }
        else if (monsterList.size() > num) {//不重复出现
            int ci = 0;
            int size = monsterList.size();
            while (num > 0 && ++ci < 99999) {//避免死循环
                String value = (String)monsterList.get(Battlefield.random.nextInt(size));
                if (!fightCreeps.contains(value)) {
                    fightCreeps.add(value);
                    --num;
                }
            }
        }
        else {
            for (int i = 0; i < num; ++i) {
                fightCreeps.add(monsterList.get(Battlefield.random.nextInt(monsterList.size())));
            }
        }
        return fightCreeps;
    }
    
    public static void loadCreep(List<BattleRole> roles, BattleData battleData, int wssType) {

        // 已经守护了多久(每15分钟=1)
        long ke = (System.currentTimeMillis() - ((BattleRole)roles.get(0)).getStarttime()) / 1000L / 60L / 15L + 1L;

        // 守护者难度系数
        double xs = 0.0;
        switch (wssType) {
            case 1: {
                xs = (double)(8L / ke);
                break;
            }
            case 2: {
                xs = (double)(12L / ke);
                break;
            }
            case 3: {
                xs = (double)(16L / ke);
                break;
            }
            case 4: {
                xs = (double)(20L / ke);
                break;
            }
        }
        for (int i = 0; i < roles.size(); ++i) {
            List<ManData> data = GLUtil.getManData((BattleRole)roles.get(i), xs);
            for (ManData man : data) {
                battleData.addFightingdata(man);
            }
        }
    }
    
    public static String isRole(String[] team, BattleData battleData, int camp) {
        if (team.length > 5) {
            return BattleInfo.CHECKTS6;
        }
        List<BigDecimal> ids = new ArrayList<>();
        int[] lvls = (int[])((battleData.getRobots() != null) ? battleData.getRobots().getLvls() : null);
        for (int i = 0; i < team.length; ++i) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(team[i]);
            LoginResult loginResult = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (loginResult == null) {
                return BattleInfo.CHECKTS3;
            }
            ids.add(loginResult.getRole_id());
            if ((int)loginResult.getFighting() != 0) {
                BattleData battleData2 = (BattleData)BattleThreadPool.BattleDatas.get(loginResult.getFighting());
                if (battleData2 != null) {
                    return BattleInfo.CHECKTS4;
                }
                loginResult.setFighting(0);
            }
            if (battleData.getRobots() != null) {
                int lvl = (int)loginResult.getGrade();
                if (lvls != null) {
                    String value = BattleMixDeal.isLvl(lvl, lvls);
                    if (value != null) {
                        return value;
                    }
                }
                lvl = BattleMixDeal.lvlint(lvl);
                if (battleData.getMaxLvl() < lvl) {
                    battleData.setMaxLvl(lvl);
                }
                int sum = MonitorUtil.getBSumBase(loginResult.getRole_id());
                if (sum > battleData.getMaxSum()) {
                    battleData.setMaxSum(sum);
                }
            }
            battleData.getParticipantlist().add(team[i]);
        }
        if (ids.size() > 0 && getBattleRoleCount(ids) > 0) {
            return BattleInfo.CHECKTS5;
        }
        return null;
    }
    
    public static boolean isOpen() {
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(11);
        return hour >= 20;
    }
    
    public static void end() {
        try {
            for (int wssType = 1; wssType <= 4; ++wssType) {
                // 踢掉现有的战斗
                BattleData bf = (BattleData)BattleThreadPool.BattleDatas.get(wssType);
                if (bf != null) {
                    // 强行结束战斗
                    BattleThreadPool.removeBattleData(bf);
                }
                GLUtil.awardAndEnd(null, wssType);
            }
        }
        catch (Exception e) {
            WriteOut.addtxt("武神山结算报错:" + MainServerHandler.getErrorMessage(e), 9999L);
        }
        finally {
            BattleInfo.battleMap.clear();
        }
    }
    
    static {
        BattleInfo.battleMap = new HashMap<>();
        BattleInfo.CHECKTS1 = Agreement.getAgreement().PromptAgreement("队伍中已有玩家正在战斗");
        BattleInfo.CHECKTS2 = Agreement.getAgreement().PromptAgreement("烛火守护正在被其他玩家挑战中，请等待挑战结束");
        BattleInfo.CHECKTS3 = Agreement.getAgreement().PromptAgreement("你的队员不在线");
        BattleInfo.CHECKTS4 = Agreement.getAgreement().PromptAgreement("你的队员正在战斗中");
        BattleInfo.CHECKTS5 = Agreement.getAgreement().PromptAgreement("你的队员已经在守护某盏烛火了");
        BattleInfo.CHECKTS6 = Agreement.getAgreement().PromptAgreement("队伍数据异常");
        // 阿拉伯数字与大写汉字转换
        SHIJIAN = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
    }
}
