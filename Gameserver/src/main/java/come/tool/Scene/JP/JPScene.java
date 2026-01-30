package come.tool.Scene.JP;

import org.come.task.MonsterMoveBase;
import org.come.task.MapMonsterBean;
import come.tool.Battle.BattleEnd;
import come.tool.Battle.BattleData;
import java.time.LocalTime;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.come.entity.UserTable;
import java.util.ArrayList;
import com.gl.service.GameService;
import java.math.BigDecimal;
import come.tool.Role.RoleData;
import org.come.bean.LoginResult;
import come.tool.Good.AddGoodAction;
import come.tool.Stall.AssetUpdate;
import org.come.bean.XXGDBean;
import org.come.entity.Goodstable;
import come.tool.Role.RolePool;
import org.come.until.AllServiceUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import org.come.model.AuctionExchange;
import org.come.server.GameServer;
import java.util.HashMap;
import java.util.Map;
import come.tool.Scene.Scene;

public class JPScene implements Scene
{
    private Map<Integer, Integer> auctionExchangeMap;
    private Map<Integer, AuctionSceneInfo> auctionSceneInfoMap;
    
    public JPScene() {
        this.auctionExchangeMap = new HashMap<>();
        this.auctionSceneInfoMap = new HashMap<>();
        JPThread thread = new JPThread(this);
        Thread T1 = new Thread(thread);
        T1.start();
    }
    
    public void open() {
        GameServer.getAllAuctionGoodsExchange().getAllAuctionAuctionExchange().forEach((k, v)/* java.lang.Integer,org.come.model.AuctionExchange, */ -> {
            String s = withinTheTimeFrame(v);
            if (s == null) {
                Integer type = (Integer)this.auctionExchangeMap.get(k);
                if (type == null) {
                    this.auctionExchangeMap.put(Integer.valueOf(v.geteId()), Integer.valueOf(1));
                    AuctionExchange auctionExchange = (AuctionExchange)GameServer.getAllAuctionGoodsExchange().getAllAuctionAuctionExchange().get(k);
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("#R[聚宝阁]#c00FFFF珍宝#G");
                    buffer.append(auctionExchange.getGoodssname());
                    buffer.append("#c00FFFF的竞拍已经开始,喜欢的侠士可以前去看看#90");
                    NChatBean bean = new NChatBean();
                    bean.setId(4);
                    bean.setMessage(buffer.toString());
                    String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    SendMessage.sendMessageToAllRoles(msg);
                }
            }
            return;
        });
    }
    
    public void goodschange(AuctionExchange goodsExchange, ChannelHandlerContext ctx, AuctionSceneInfo auctionSceneInfo) {
        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(auctionSceneInfo.getRole_id());
        if (loginResult == null) {
            return;
        }
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
        AuctionExchange exchange = goodsExchange;
        Goodstable goodss = (exchange != null) ? ((Goodstable)GameServer.getAllGoodsMap().get(exchange.getGid())) : null;
        Goodstable goodstable = GameServer.getGood(exchange.getGid());
        if (goodstable == null) {
            return;
        }
        XXGDBean xxgdBean = new XXGDBean();
        xxgdBean.setId(goodstable.getGoodsid().toString());
        xxgdBean.setSum(1);
        AssetUpdate assetUpdate = new AssetUpdate();
        AddGoodAction.addGood(assetUpdate, goodstable, loginResult, roleData, xxgdBean, 66);
        if (ctx != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("#G[聚宝阁]#R");
        buffer.append(loginResult.getRolename());
        buffer.append("#c00ffff在拍卖场上一掷千金，成功拍得物品#G");
        buffer.append(goodss.getGoodsname());
        buffer.append("#c00ffff,不愧是神豪#28");
        NChatBean bean = new NChatBean();
        bean.setId(4);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public void end() {
        this.auctionExchangeMap.forEach((k, v)/* java.lang.Integer,java.lang.Integer, */ -> {
            if ((int)v == 1 || (int)v == 3) {
                AuctionExchange auctionExchange = (AuctionExchange)GameServer.getAllAuctionGoodsExchange().getAllAuctionAuctionExchange().get(k);
                if (auctionExchange != null) {
                    Boolean withinTheTimeFrame = isWithinTheTimeFrame(auctionExchange);
                    if ((boolean)withinTheTimeFrame || (int)v == 3) {
                        AuctionSceneInfo auctionSceneInfo = (AuctionSceneInfo)this.auctionSceneInfoMap.get(k);
                        if (auctionSceneInfo == null || auctionSceneInfo.getRole_id() == null) {
                            return;
                        }
                        this.auctionExchangeMap.put(k, Integer.valueOf(2));
                        this.goodschange(auctionExchange, null, auctionSceneInfo);
                    }
                }
            }
            return;
        });
    }
    
    public void biddingProcessController(ChannelHandlerContext ctx, String mes) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        String[] v = mes.split("=");
        int item = Integer.parseInt(v[0]);
        Integer type = (Integer)this.auctionExchangeMap.get(Integer.valueOf(item));
        if (type == null || (int)type != 1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#R当前竞拍未开放！"));
            return;
        }
        AuctionExchange auctionExchange = (AuctionExchange)GameServer.getAllAuctionGoodsExchange().getAllAuctionAuctionExchange().get(Integer.valueOf(item));
        AuctionSceneInfo auctionSceneInfo = (AuctionSceneInfo)this.auctionSceneInfoMap.get(Integer.valueOf(item));
        BigDecimal money = new BigDecimal(v[1]);
        if (auctionSceneInfo == null) {
            auctionSceneInfo = new AuctionSceneInfo();
            this.auctionSceneInfoMap.put(Integer.valueOf(item), auctionSceneInfo);
        }
        if (auctionSceneInfo.getRole_id() != null) {
            if (auctionSceneInfo.getRole_id().compareTo(loginResult.getRole_id()) == 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您是当前出价最高！"));
                return;
            }
            if (money.longValue() <= auctionSceneInfo.getMoney().longValue()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("出价必须高于当前最新竞拍价格#24！"));
                return;
            }
        }
        else {
            String[] mesa = auctionExchange.getConsume().split("\\|");
            for (int i = 0; i < mesa.length; ++i) {
                if (mesa[i].startsWith("D")) {
                    String[] mesa2 = mesa[i].split("=");
                    if (mesa2.length > 1) {
                        BigDecimal basePrice = new BigDecimal(mesa2[1]);
                        if (basePrice.longValue() > money.longValue()) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前出价低于最低出价！"));
                            return;
                        }
                    }
                }
            }
            auctionExchange.getConsume();
        }
        Integer moneyType = auctionExchange.getMoneyType();
        AssetUpdate assetUpdate = new AssetUpdate();
        if ((int)moneyType == 1) {
            if (money.longValue() > loginResult.getGold().longValue()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的银两不足以支付竞拍费用！"));
                return;
            }
            assetUpdate.setType(AssetUpdate.USEGOOD);
            assetUpdate.updata("D=-" + money.toString());
            loginResult.setGold(loginResult.getGold().subtract(money));
        }
        else if ((int)moneyType == 2) {
            if (money.longValue() > loginResult.getCodecard().longValue()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的银两不足以支付竞拍费用！"));
                return;
            }
            assetUpdate.setType(AssetUpdate.USEGOOD);
            assetUpdate.updata("X=-" + money.toString());
            loginResult.setCodecard(loginResult.getCodecard().subtract(money));
        }
        assetUpdate.setMsg("出价成功");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        if (auctionSceneInfo.getRole_id() != null && auctionSceneInfo.getRole_id().longValue() != loginResult.getRole_id().longValue() && auctionSceneInfo.getRole_id() != null) {
            assetUpdate = new AssetUpdate();
            RoleData roleData = RolePool.getRoleData(auctionSceneInfo.getRole_id());
            if (roleData != null) {
                ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleData.getLoginResult().getRolename());
                LoginResult loginResult2 = (LoginResult)GameServer.getAllLoginRole().get(channelHandlerContext);
                if ((int)moneyType == 1) {
                    assetUpdate.setType(AssetUpdate.USEGOOD);
                    assetUpdate.updata("D=" + auctionSceneInfo.getMoney());
                    loginResult2.setGold(loginResult2.getGold().add(auctionSceneInfo.getMoney()));
                }
                else if ((int)moneyType == 2) {
                    assetUpdate.setType(AssetUpdate.USEGOOD);
                    assetUpdate.updata("X=" + auctionSceneInfo.getMoney());
                    loginResult2.setCodecard(loginResult2.getCodecard().add(auctionSceneInfo.getMoney()));
                }
                String msg = "#Y[系统消息] #G你竞拍#R" + auctionExchange.getGoodssname() + "#G已经被玩家#Y" + loginResult.getRolename() + "#G已新的价格#Y" + money + "#G成为新的竞拍价格#46";
                new GameService().sendMsgToPlayer(msg, roleData.getLoginResult().getRolename());
                SendMessage.sendMessageByRoleName(loginResult2.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else {
                LoginResult role = AllServiceUtil.getRoleTableService().selectRoleID(auctionSceneInfo.getRole_id());
                if ((int)moneyType == 1) {
                    role.setGold(role.getGold().add(auctionSceneInfo.getMoney()));
                    AllServiceUtil.getRoleTableService().updateRoleWhenExit(role);
                }
                else if ((int)moneyType == 2) {
                    role.setCodecard(role.getCodecard().add(auctionSceneInfo.getMoney()));
                    UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(role.getUser_id());
                    userTable.setCodecard(userTable.getCodecard().add(auctionSceneInfo.getMoney()));
                    AllServiceUtil.getUserTableService().updateUser(userTable);
                }
            }
        }
        auctionSceneInfo.setMoney(money);
        auctionSceneInfo.setRole_id(loginResult.getRole_id());
        List<AuctionLog> auctionLogList = auctionSceneInfo.getAuctionLogList();
        if (auctionLogList == null) {
            auctionLogList = new ArrayList<>();
            auctionSceneInfo.setAuctionLogList(auctionLogList);
        }
        AuctionLog auctionLog = new AuctionLog();
        auctionLog.setRoleName(loginResult.getRolename());
        auctionLog.setTime(this.DateFormattedTime());
        auctionLog.setMoney(money.toString());
        auctionLogList.add(auctionLog);
        String msg2 = Agreement.getAgreement().auctionGoods(GsonUtil.getGsonUtil().getgson().toJson(auctionSceneInfo));
        SendMessage.sendMessageToSlef(ctx, msg2);
    }
    
    public String DateFormattedTime() {
        Date now = new Date();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String formattedTime = sdf.format(now);
        return formattedTime;
    }
    
    public void accident() {
        this.auctionExchangeMap.forEach((k, v)/* java.lang.Integer,java.lang.Integer, */ -> {
            if ((int)v == 1) {
                AuctionExchange auctionExchange = (AuctionExchange)GameServer.getAllAuctionGoodsExchange().getAllAuctionAuctionExchange().get(k);
                Integer moneyType = auctionExchange.getMoneyType();
                AuctionSceneInfo auctionSceneInfo = (AuctionSceneInfo)this.auctionSceneInfoMap.get(k);
                if (auctionSceneInfo != null && auctionSceneInfo.getRole_id() != null) {
                    LoginResult role = AllServiceUtil.getRoleTableService().selectRoleID(auctionSceneInfo.getRole_id());
                    if ((int)moneyType == 1) {
                        role.setGold(role.getGold().add(auctionSceneInfo.getMoney()));
                        AllServiceUtil.getRoleTableService().updateRoleWhenExit(role);
                    }
                    else if ((int)moneyType == 2) {
                        role.setCodecard(role.getCodecard().add(auctionSceneInfo.getMoney()));
                        UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(role.getUser_id());
                        userTable.setCodecard(userTable.getCodecard().add(auctionSceneInfo.getMoney()));
                        AllServiceUtil.getUserTableService().updateUser(userTable);
                    }
                }
            }
            return;
        });
    }
    
    public static String withinTheTimeFrame(AuctionExchange auctionExchange) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(7);
        String[] days = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String today = days[dayOfWeek - 1];
        if (!auctionExchange.getWeek().contains(today)) {
            return "当前物品还未开始竞拍#76!!";
        }
        String time = auctionExchange.getTime();
        String[] v = time.split("-");
        LocalTime startTime = LocalTime.parse(v[0]);
        LocalTime endTime = LocalTime.parse(v[1]);
        LocalTime currentTime = LocalTime.now();
        if (!currentTime.isAfter(startTime) || !currentTime.isBefore(endTime)) {
            return "当前物品还未开始竞拍#76!!";
        }
        return null;
    }
    
    public static Boolean isWithinTheTimeFrame(AuctionExchange auctionExchange) {
        Calendar calendar = Calendar.getInstance();
        String time = auctionExchange.getTime();
        String[] v = time.split("-");
        LocalTime endTime = LocalTime.parse(v[1]);
        LocalTime currentTime = LocalTime.now();
        if (endTime.isBefore(currentTime)) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        return null;
    }
    
    @Override
    public int battleEnd(BattleEnd battleEnd, LoginResult loginResult, MapMonsterBean bean, int v) {
        return 0;
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        return null;
    }
    
    @Override
    public boolean isEnd() {
        return false;
    }
    
    @Override
    public boolean isTime(long time) {
        return false;
    }
    
    @Override
    public String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
        return null;
    }
    
    public Map<Integer, Integer> getAuctionExchangeMap() {
        return this.auctionExchangeMap;
    }
    
    public void setAuctionExchangeMap(Map<Integer, Integer> auctionExchangeMap) {
        this.auctionExchangeMap = auctionExchangeMap;
    }
    
    public Map<Integer, AuctionSceneInfo> getAuctionSceneInfoMap() {
        return this.auctionSceneInfoMap;
    }
    
    public void setAuctionSceneInfoMap(Map<Integer, AuctionSceneInfo> auctionSceneInfoMap) {
        this.auctionSceneInfoMap = auctionSceneInfoMap;
    }
}
