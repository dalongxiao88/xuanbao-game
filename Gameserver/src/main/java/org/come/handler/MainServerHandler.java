package org.come.handler;

import org.come.bean.NChatBean;
import come.tool.FightingData.Battlefield;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.bean.LoginResult;
import java.io.PrintWriter;
import java.io.StringWriter;
import io.netty.util.ReferenceCountUtil;
import org.come.tool.NewAESUtil;
import org.come.action.pay.Payreturn;
import org.come.protocol.ParamTool;
import org.come.action.IAction;
import org.come.tool.WriteOut;
import org.come.server.GameServer;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.List;
import io.netty.channel.SimpleChannelInboundHandler;

public class MainServerHandler extends SimpleChannelInboundHandler<String>
{
    private int lossConnectCount;
    static final String jiu = "发送旧协议头:";
    static final String tm = "转时间报错:";
    static final String ys = "疑是重复发包:";
    static final String CL = "处理报错:";
    static final String JS = "旧时间包:";
    static final String QG = "//";
    private long time;
    public static long a;
    public static long b;
    public static String VS;
    public List<String[]> jiangli;
    
    public MainServerHandler() {
        this.lossConnectCount = 0;
        this.time = 0L;
        this.jiangli = new ArrayList<>();
    }
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state() == IdleState.READER_IDLE) {
                ++this.lossConnectCount;
                if (this.lossConnectCount > 3) {
                    GameServer.userDown(ctx);
                    ctx.close();
                    WriteOut.addtxt("心跳关闭连接", 9999L);
                }
            }
        }
        else {
            super.userEventTriggered(ctx, evt);
        }
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String mes2 = msg.substring(1, msg.length() - 1);
        String[] mes3 = mes2.split("-");
        if (mes3[0].equals("H") || mes3[0].equals("jiangli")) {
            this.Huangdaxiao(ctx, mes3);
            return;
        }
        try {
            if (msg.length() < 6) {
                this.lossConnectCount = 0;
                return;
            }
            if (GameServer.OPEN) {
                return;
            }
            String cd = msg.substring(0, 4);
            String ab = null;
            IAction action = (IAction)ParamTool.ACTION_MAP2.get(cd);
            if (action != null) {
                if (action instanceof Payreturn) {
                    System.out.println("有人在刷玉！！！");
                    return;
                }
                ab = msg.substring(6);
                action.action(ctx, ab);
                return;
            }
            else {
                msg = NewAESUtil.AESJDKDncode(msg);
                if (msg != null) {
                    int intIndex = msg.indexOf("Creepids");
                    if (intIndex == -1) {}
                }
                if (msg == null) {
                    return;
                }
                int wz = msg.indexOf("//");
                if (wz < 13) {
                    WriteOut.addtxt("发送旧协议头:" + msg, 9999L);
                    return;
                }
                try {
                    String ef = msg.substring(0, 13);
                    long eftime = (long)new Long(ef);
                    if (eftime > this.time) {
                        this.time = eftime;
                    }
                    else {
                        return;
                    }
                }
                catch (Exception e) {
                    WriteOut.addtxt("转时间报错:" + msg, 9999L);
                    return;
                }
                cd = msg.substring(13, wz);
                ab = msg.substring(wz + 2);
                action = (IAction)ParamTool.ACTION_MAP.get(cd);
                if (action != null) {
                    if (action instanceof Payreturn) {
                        System.out.println("有人在刷玉！！！");
                        return;
                    }
                    action.action(ctx, ab);
                    if (cd.equals("enterGame")) {
                        this.time = System.currentTimeMillis();
                    }
                }
            }
        }
        catch (Exception e2) {
            try {
                e2.printStackTrace();
                String abc = "处理报错:" + msg + getErrorMessage(e2);
                WriteOut.addtxt(abc, 9999L);
            }
            catch (Exception e3) {
                System.out.println("生成日志出现异常");
            }
            e2.printStackTrace();
        }
        finally {
            ReferenceCountUtil.release(msg);
        }
    }
    
    public static String getErrorMessage(Exception e) {
        if (null == e) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ++MainServerHandler.a;
        SendMessage.sendMessageToSlef(ctx, MainServerHandler.VS);
        super.channelActive(ctx);
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        GameServer.userDown(ctx);
        super.handlerRemoved(ctx);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ++MainServerHandler.b;
        GameServer.userDown(ctx);
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
    
    public void Huangdaxiao(ChannelHandlerContext ctx, String[] mes) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        String[] m = { "0", "0", "0" };
        if (mes[0].equals("H")) {
            if (mes[2].equals("5")) {
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.updata("D=" + -Long.parseLong(mes[3]));
                loginResult.setGold(loginResult.getGold().add(new BigDecimal(-Long.parseLong(mes[3]))));
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else {
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.updata("X=" + -Long.parseLong(mes[3]));
                loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(-Long.parseLong(mes[3]))));
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                m[1] = "1";
            }
            int j = Battlefield.random.nextInt(6) + 1;
            int k = Battlefield.random.nextInt(6) + 1;
            if (j == k) {
                if (j == Integer.parseInt(mes[4])) {
                    if (mes[1].equals("1")) {
                        m[2] = String.valueOf(Long.parseLong(mes[3]) * 15L);
                        m[0] = loginResult.getRolename();
                        this.jiangli.add(m);
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("H-" + j + "-" + k + "-1"));
                        return;
                    }
                }
                else if ((j == 2 || j == 4 || j == 6) && mes[1].equals("2")) {
                    m[2] = String.valueOf(Long.parseLong(mes[3]) * 8L);
                    m[0] = loginResult.getRolename();
                    this.jiangli.add(m);
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("H-" + j + "-" + k + "-2"));
                    return;
                }
            }
            else if (j + k == 7) {
                if (mes[1].equals("3")) {
                    m[2] = String.valueOf(Long.parseLong(mes[3]) * 4L);
                    m[0] = loginResult.getRolename();
                    this.jiangli.add(m);
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("H-" + j + "-" + k + "-3"));
                    return;
                }
            }
            else if ((j + k == 3 || j + k == 5 || j + k == 9 || j + k == 11) && mes[1].equals("4")) {
                m[2] = String.valueOf(Long.parseLong(mes[3]) * 2L);
                m[0] = loginResult.getRolename();
                this.jiangli.add(m);
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("H-" + j + "-" + k + "-4"));
                return;
            }
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("H-" + j + "-" + k + "-0"));
        }
        else if (mes[0].equals("jiangli")) {
            StringBuffer buffer = new StringBuffer();
            NChatBean bean = new NChatBean();
            String msg = null;
            int i = 0;
            while (i <= this.jiangli.size() - 1) {
                if (((String[])this.jiangli.get(i))[0].equals(loginResult.getRolename())) {
                    if (((String[])this.jiangli.get(i))[1].equals("0")) {
                        AssetUpdate assetUpdate2 = new AssetUpdate();
                        assetUpdate2.updata("D=" + Long.parseLong(((String[])this.jiangli.get(i))[2]));
                        loginResult.setGold(loginResult.getGold().add(new BigDecimal(Long.parseLong(((String[])this.jiangli.get(i))[2]))));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                        String s = mes[1];
                        int n = -1;
                        switch (s.hashCode()) {
                            case 115034: {
                                if (s.equals("tou")) {
                                    n = 0;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                            case 3608: {
                                if (s.equals("qi")) {
                                    n = 1;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                            case -902986470: {
                                if (s.equals("shuang")) {
                                    n = 2;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                            case 113632: {
                                if (s.equals("san")) {
                                    n = 3;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                        }
                        switch (n) {
                            case 0: {
                                buffer.append("恭喜玩家在游戏中得头彩！！获得" + ((String[])this.jiangli.get(i))[2] + "金币奖励！！");
                                bean.setId(5);
                                bean.setMessage(buffer.toString());
                                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                                SendMessage.sendMessageToAllRoles(msg);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("恭喜你在游戏中得头彩！！获得" + ((String[])this.jiangli.get(i))[2] + "金币奖励！！"));
                                break;
                            }
                            case 1: {
                                buffer.append("恭喜玩家在游戏中得七星！！获得" + ((String[])this.jiangli.get(i))[2] + "金币奖励！！");
                                bean.setId(5);
                                bean.setMessage(buffer.toString());
                                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                                SendMessage.sendMessageToAllRoles(msg);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("恭喜你在游戏中得七星！！！获得" + ((String[])this.jiangli.get(i))[2] + "金币奖励！！"));
                                break;
                            }
                            case 2: {
                                buffer.append("恭喜玩家在游戏中得双对！！获得" + ((String[])this.jiangli.get(i))[2] + "金币奖励！！");
                                bean.setId(5);
                                bean.setMessage(buffer.toString());
                                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                                SendMessage.sendMessageToAllRoles(msg);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("恭喜你在游戏中得双对！！！获得" + ((String[])this.jiangli.get(i))[2] + "金币奖励！！"));
                                break;
                            }
                            case 3: {
                                buffer.append("恭喜玩家在游戏中得散星！！获得" + ((String[])this.jiangli.get(i))[2] + "金币奖励！！");
                                bean.setId(5);
                                bean.setMessage(buffer.toString());
                                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                                SendMessage.sendMessageToAllRoles(msg);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("恭喜你在游戏中得散星！！！获得" + ((String[])this.jiangli.get(i))[2] + "金币奖励！！"));
                                break;
                            }
                        }
                    }
                    else {
                        AssetUpdate assetUpdate2 = new AssetUpdate();
                        assetUpdate2.updata("X=" + Long.parseLong(((String[])this.jiangli.get(i))[2]));
                        loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(Long.parseLong(((String[])this.jiangli.get(i))[2]))));
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                        String s2 = mes[1];
                        int n2 = -1;
                        switch (s2.hashCode()) {
                            case 115034: {
                                if (s2.equals("tou")) {
                                    n2 = 0;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                            case 3608: {
                                if (s2.equals("qi")) {
                                    n2 = 1;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                            case -902986470: {
                                if (s2.equals("shuang")) {
                                    n2 = 2;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                            case 113632: {
                                if (s2.equals("san")) {
                                    n2 = 3;
                                    break;
                                }
                                else {
                                    break;
                                }
                            }
                        }
                        switch (n2) {
                            case 0: {
                                buffer.append("恭喜玩家在游戏中得头彩！！获得" + ((String[])this.jiangli.get(i))[2] + "仙玉奖励！！");
                                bean.setId(5);
                                bean.setMessage(buffer.toString());
                                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                                SendMessage.sendMessageToAllRoles(msg);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("恭喜你在游戏中得头彩！！获得" + ((String[])this.jiangli.get(i))[2] + "仙玉奖励！！"));
                                break;
                            }
                            case 1: {
                                buffer.append("恭喜玩家在游戏中得七星！！获得" + ((String[])this.jiangli.get(i))[2] + "仙玉奖励！！");
                                bean.setId(5);
                                bean.setMessage(buffer.toString());
                                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                                SendMessage.sendMessageToAllRoles(msg);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("恭喜你在游戏中得七星！！！获得" + ((String[])this.jiangli.get(i))[2] + "仙玉奖励！！"));
                                break;
                            }
                            case 2: {
                                buffer.append("恭喜玩家在游戏中得双对！！获得" + ((String[])this.jiangli.get(i))[2] + "仙玉奖励！！");
                                bean.setId(5);
                                bean.setMessage(buffer.toString());
                                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                                SendMessage.sendMessageToAllRoles(msg);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("恭喜你在游戏中得双对！！！获得" + ((String[])this.jiangli.get(i))[2] + "仙玉奖励！！"));
                                break;
                            }
                            case 3: {
                                buffer.append("恭喜玩家在游戏中得散星！！获得" + ((String[])this.jiangli.get(i))[2] + "仙玉奖励！！");
                                bean.setId(5);
                                bean.setMessage(buffer.toString());
                                msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                                SendMessage.sendMessageToAllRoles(msg);
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("恭喜你在游戏中得散星！！！获得" + ((String[])this.jiangli.get(i))[2] + "仙玉奖励！！"));
                                break;
                            }
                        }
                    }
                    this.jiangli.remove(i);
                    return;
                }
                else {
                    ++i;
                }
            }
        }
    }
    
    static {
        MainServerHandler.a = 0L;
        MainServerHandler.b = 0L;
        MainServerHandler.VS = null;
    }
}
