package come.tool.Scene;

import org.come.entity.RoleTable;
import org.come.until.AllServiceUtil;
import java.util.Map;
import java.util.List;
import org.come.bean.NChatBean;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import org.come.action.reward.DrawnitemsAction;
import java.util.Iterator;
import org.come.server.GameServer;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;
import java.util.Calendar;

public class JieGuaScene implements Runnable
{
    private static boolean isStart;
    private static int second;
    public static int minute;
    public static Calendar rightNow;
    private static JieGuaScene jieGuaScene;
    private static final int MAX = 200000;
    private static final int SECOND1 = 120;
    private static final int SECOND2 = 15;
    private static final int SECOND3 = 15;
    private static final BigDecimal REDOUBLE;
    private int state;
    private long time;
    private int yangId;
    private int yinId;
    private final ConcurrentHashMap<String, String> roleNameMap;
    private final ConcurrentHashMap<String, String> pushnoteMap;
    private String winningInfo;
    
    public static JieGuaScene getJieGuaScene() {
        return JieGuaScene.jieGuaScene;
    }
    
    public static void open() {
        if (!JieGuaScene.isStart) {
            JieGuaScene.isStart = true;
            JieGuaScene.jieGuaScene = new JieGuaScene();
            Thread thread = new Thread(JieGuaScene.jieGuaScene);
            thread.start();
        }
    }
    
    public static void close() {
        if (JieGuaScene.isStart) {
            JieGuaScene.isStart = false;
        }
    }
    
    public static boolean isStart() {
        return JieGuaScene.isStart;
    }
    
    private JieGuaScene() {
        this.state = 0;
        this.winningInfo = "";
        this.roleNameMap = new ConcurrentHashMap<>();
        this.pushnoteMap = new ConcurrentHashMap<>();
    }
    
    @Override
    public void run() {
        while (JieGuaScene.isStart) {
            try {
                JieGuaScene.rightNow = Calendar.getInstance(Locale.CHINA);
                int second = JieGuaScene.rightNow.get(13);
                if (second != JieGuaScene.second) {
                    JieGuaScene.second = second;
                    JieGuaScene.minute = JieGuaScene.rightNow.get(12);
                    if (second % 10 == 0) {
                        int s = JieGuaScene.minute * 60 + second;
                        int state;
                        if (s % 150 < 120) {
                            state = 1;
                        }
                        else if (s % 150 < 135) {
                            state = 2;
                        }
                        else {
                            state = 0;
                        }
                        this.handle(state);
                    }
                    else {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized void pushnote(ChannelHandlerContext ctx, LoginResult loginResult, String value) {
        if (this.state != 1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不是押注时间"));
            return;
        }
        String[] vals = value.split("_");
        int points = Integer.parseInt(vals[1]);
        points = this.getDeductingPoints(ctx, loginResult, vals[0], points);
        if (points > 0) {
            StringBuffer buffer = new StringBuffer();
            if (this.pushnoteMap.get(vals[0]) != null) {
                buffer.append((String)this.pushnoteMap.get(vals[0]));
            }
            if (buffer.length() > 0) {
                buffer.append(",");
            }
            buffer.append(loginResult.getRolename());
            buffer.append("_");
            buffer.append(points);
            this.pushnoteMap.put(vals[0], buffer.toString());
            this.push(3);
        }
    }
    
    public void participate(ChannelHandlerContext ctx, String roleName) {
        this.roleNameMap.put(roleName, "");
        this.push(ctx, this.state);
    }
    
    public void cancelParticipate(String roleName) {
        this.roleNameMap.remove(roleName);
    }
    
    public void push(String roleName, int type) {
        this.push((ChannelHandlerContext)GameServer.getRoleNameMap().get(roleName), type);
    }
    
    private void handle(int state) {
        if (this.state != state) {
            this.state = state;
            System.out.println(state);
            if (state == 1) {
                this.time = System.currentTimeMillis() + 121000L;
            }
            else if (state == 2) {
                this.time = System.currentTimeMillis() + 16000L;
            }
            else {
                this.time = System.currentTimeMillis() + 16000L;
                this.lotteryDrawing();
            }
            this.push(state);
        }
    }
    
    private void push(int type) {
        for (String roleName : this.roleNameMap.keySet()) {
            this.push(roleName, type);
        }
    }
    
    private void push(ChannelHandlerContext ctx, int type) {
        if (ctx != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(type);
            buffer.append("=");
            switch (type) {
                case 0: {
                    buffer.append(this.yangId);
                    buffer.append("_");
                    buffer.append(this.yinId);
                    buffer.append("=");
                    buffer.append(this.getWinningInfo());
                    break;
                }
                case 1: {
                    buffer.append(this.time);
                    buffer.append("=");
                    buffer.append(this.getPushnoteValue());
                    break;
                }
                case 2: {
                    buffer.append(this.time);
                    break;
                }
                case 3: {
                    buffer.append(this.getPushnoteInfo());
                    break;
                }
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().JieGuaAgreement(buffer.toString()));
        }
    }
    
    private String getWinningInfo() {
        return this.winningInfo;
    }
    
    private String getPushnoteInfo() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 8; ++i) {
            if (buffer.length() > 0) {
                buffer.append("|");
            }
            buffer.append(this.getPushnoteInfo(i + ""));
        }
        return buffer.toString();
    }
    
    private String getPushnoteInfo(String type) {
        return (String)this.pushnoteMap.get(type);
    }
    
    private String getPushnoteValue() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 8; ++i) {
            if (buffer.length() > 0) {
                buffer.append("|");
            }
            buffer.append(this.getPushnoteValue(i + ""));
        }
        return buffer.toString();
    }
    
    private long getPushnoteValue(String type) {
        long value = 0L;
        String val = (String)this.pushnoteMap.get(type);
        if (val != null) {
            String[] vs;
            for (String v : vs = val.split(",")) {
                value += Long.parseLong(v.split("_")[1]);
            }
        }
        return value;
    }
    
    private int getDeductingPoints(ChannelHandlerContext ctx, LoginResult loginResult, String type, int points) {
        long pushnoteValue = this.getPushnoteValue(type);
        if (pushnoteValue >= 200000L) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("所需灵力已满"));
            return 0;
        }
        if (pushnoteValue + (long)points > 200000L) {
            points = (int)(200000L - pushnoteValue);
        }
        BigDecimal score = loginResult.getScoretype("解卦灵力");
        if ((long)points > score.longValue()) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("灵力不够了"));
            return 0;
        }
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "解卦灵力=" + points, 3));
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setData("解卦灵力=" + -points);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        return points;
    }
    
    private void lotteryDrawing() {
        this.yangId = GameServer.random.nextInt(8);
        this.yinId = GameServer.random.nextInt(8);
        int fold = (this.yangId == this.yinId) ? 2 : 1;
        StringBuffer buffer = new StringBuffer();
        String info = (String)this.pushnoteMap.get(this.yangId + "");
        List<String> msgs = new ArrayList<>();
        if (StringUtils.isNotBlank(info)) {
            Map<String, BigDecimal> map = new HashMap<>();
            String[] vals = info.split(",");
            for (int i = 0; i < vals.length; ++i) {
                String[] vs = vals[i].split("_");
                BigDecimal points = (BigDecimal)map.get(vs[0]);
                if (points == null) {
                    points = BigDecimal.valueOf(0L);
                }
                BigDecimal bigDecimal = new BigDecimal(vs[1]);
                map.put(vs[0], points.add(bigDecimal));
            }
            for (String roleName : map.keySet()) {
                if (buffer.length() > 0) {
                    buffer.append("#r");
                }
                buffer.append("#G");
                buffer.append(roleName);
                buffer.append("#cBBA54B");
                buffer.append("获得");
                buffer.append("#R");
                BigDecimal lingLi = ((BigDecimal)map.get(roleName)).multiply(BigDecimal.valueOf((long)fold));
                BigDecimal money = lingLi.multiply(JieGuaScene.REDOUBLE);
                buffer.append(money.longValue());
                buffer.append("两");
                String msg = this.systemInfo(roleName, lingLi);
                if (StringUtils.isNotBlank(msg)) {
                    msgs.add(msg);
                }
                this.addGold(roleName, money);
            }
        }
        this.winningInfo = buffer.toString();
        this.pushnoteMap.clear();
        if (msgs.size() > 0) {
            StringBuffer msgBuffer = new StringBuffer();
            msgBuffer.append("#c00ffff");
            msgBuffer.append("五行通天地，八卦定乾坤。");
            msgBuffer.append("恭喜解得吉卦的有缘人，");
            for (int j = 0; j < msgs.size(); ++j) {
                msgBuffer.append((String)msgs.get(j));
            }
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage(msgBuffer.toString());
            SendMessage.sendMessageToAllRoles(Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
        }
    }
    
    private String systemInfo(String roleName, BigDecimal lingLi) {
        StringBuffer buffer = new StringBuffer();
        if (lingLi.compareTo(BigDecimal.valueOf(20000L)) > 0) {
            buffer.append("#R");
            buffer.append(roleName);
            buffer.append("#c00ffff");
            buffer.append("获得");
            buffer.append("#Y");
            buffer.append(lingLi.multiply(JieGuaScene.REDOUBLE).longValue());
            buffer.append("两金钱！");
        }
        return buffer.toString();
    }
    
    private void addGold(String roleName, BigDecimal gold) {
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleName);
        if (ctx != null) {
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.GIVE);
            LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            assetUpdate.updata("D=" + gold.longValue());
            loginResult.setGold(loginResult.getGold().add(gold));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else {
            RoleTable roleInfo = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(roleName);
            if (roleInfo != null) {
                BigDecimal roleGold = roleInfo.getGold();
                if (roleGold == null) {
                    roleGold = BigDecimal.ZERO;
                }
                roleGold = roleGold.add(gold);
                AllServiceUtil.getRoleTableService().updateMoneyRoleID(roleInfo.getRole_id(), roleGold);
            }
        }
    }
    
    static {
        JieGuaScene.second = 0;
        JieGuaScene.minute = 0;
        REDOUBLE = BigDecimal.valueOf(50000L);
        JieGuaScene.rightNow = Calendar.getInstance(Locale.CHINA);
    }
}
