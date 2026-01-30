package org.come.action.wqx;

import java.util.Iterator;
import org.come.task.MapMonsterBean;
import come.tool.Battle.BattleEnd;
import come.tool.Battle.BattleData;
import org.come.task.MonsterMoveBase;
import java.util.Map;
import org.come.model.Dorp;
import come.tool.Good.DropUtil;
import java.util.Random;
import org.come.bean.NPCDialog;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.Calendar;
import java.util.Locale;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import come.tool.Scene.Scene;

public class WenQuXingScene implements Scene
{
    private int I;
    public long time;
    private long endTime;
    private KJThread kjThread;
    private int day;
    private KJexamQuestions kJexamQuestions;
    private Integer xsCount;
    private Integer xsErrorCount;
    private Integer ssCount;
    private Integer ssErrorCount;
    public static long JG;
    public List<KJRole> list;
    private LinkedHashMap<BigDecimal, KJRole> examineeRoles;
    private Boolean b;
    
    public WenQuXingScene() {
        this.xsCount = Integer.valueOf(0);
        this.xsErrorCount = Integer.valueOf(10);
        this.ssCount = Integer.valueOf(20);
        this.ssErrorCount = Integer.valueOf(0);
        this.list = new ArrayList<>();
        this.examineeRoles = new LinkedHashMap<>();
        this.b = Boolean.valueOf(true);
        Calendar rightNow = Calendar.getInstance(Locale.CHINA);
        this.day = rightNow.get(7);
        this.I = 2;
        this.kjThread = new KJThread(this);
        Thread T1 = new Thread(this.kjThread);
        T1.start();
        this.endTime = System.currentTimeMillis() + 7200000L;
    }
    
    @Override
    public boolean isEnd() {
        this.I = 2;
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage("#W本次答题活动已经结束!");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        return false;
    }
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 999; ++i) {
            System.out.println(System.currentTimeMillis());
            Thread.sleep(1000L);
        }
    }
    
    public void provincialTest(KJexamQuestions k, ChannelHandlerContext ctx, KJRole kjRole, LoginResult roleInfo) {
    }
    
    public Boolean verificationDifficulty(KJexamQuestions kJexamQuestions) {
        ConcurrentHashMap<Integer, KJexamQuestions> getAllKJexamQuestions = GameServer.getGetAllKJexamQuestions();
        KJexamQuestions dbKJexamQuestions = (KJexamQuestions)getAllKJexamQuestions.get(kJexamQuestions.getId());
        Boolean b;
        if (StringUtils.isBlank(kJexamQuestions.getAnswer()) || (StringUtils.isNotBlank(kJexamQuestions.getAnswer()) && !kJexamQuestions.getAnswer().equals(dbKJexamQuestions.getAnswer()))) {
            b = Boolean.valueOf(false);
        }
        else {
            b = Boolean.valueOf(true);
        }
        return b;
    }
    
    public NPCDialog selectQuestion(int type) {
        ConcurrentHashMap<Integer, KJexamQuestions> getAllKJexamQuestions = GameServer.getGetAllKJexamQuestions();
        int i = new Random().nextInt(getAllKJexamQuestions.size());
        if (i == 0) {
            ++i;
        }
        System.out.println(i);
        KJexamQuestions kJexamQuestions = (KJexamQuestions)getAllKJexamQuestions.get(Integer.valueOf(i));
        return null;
    }
    
    public void init() {
        NChatBean bean = new NChatBean();
        bean.setId(4);
        bean.setMessage("科举活动开始了#46");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public void sendReward(Boolean b, ChannelHandlerContext ctx, KJRole kjRole, LoginResult roleInfo) {
        Dorp sl = GameServer.getDorp("9898");
        Dorp sb = GameServer.getDorp("9899");
        DropUtil.getDrop4(roleInfo, ((boolean)b) ? sl.getDorpValue() : sb.getDorpValue(), "#c00FFFF天道酬勤,厚积薄发，恭喜#R{角色名}#c00FFFF在答题活动中，才思敏捷，获得了星君之厚赏#G{物品名}", 25, 1.0, null, "", "", "");
    }
    
    public void open() {
    }
    
    public void initKjRole(KJRole kjRole) {
        kjRole.setExamProcess(1);
        kjRole.setMistake(0);
        kjRole.setCorrect(0);
        kjRole.setStartTime(Long.valueOf(0L));
    }
    
    public int getI() {
        return this.I;
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        return moveMap;
    }
    
    @Override
    public boolean isTime(long time) {
        return true;
    }
    
    @Override
    public String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
        return null;
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        return null;
    }
    
    @Override
    public int battleEnd(BattleEnd battleEnd, LoginResult loginResult, MapMonsterBean bean, int v) {
        return 0;
    }
    
    public LinkedHashMap<BigDecimal, KJRole> getExamineeRoles() {
        return this.examineeRoles;
    }
    
    public void setExamineeRoles(LinkedHashMap<BigDecimal, KJRole> examineeRoles) {
        this.examineeRoles = examineeRoles;
    }
    
    public void ClearingQuestions() {
        this.kjThread.b = Boolean.valueOf(true);
        this.examineeRoles.forEach((k, v)/* java.math.BigDecimal,org.come.action.wqx.KJRole, */ -> {
            if (v.getCorrect() == 1) {
                ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(v.getRoleName());
                if (channelHandlerContext != null && v.getCorrect() == 1 && (int)this.ssErrorCount <= 7) {
                    this.ssErrorCount = Integer.valueOf((int)this.ssErrorCount + 1);
                    this.list.add(v);
                    this.sendReward(Boolean.valueOf(false), channelHandlerContext, v, (LoginResult)GameServer.getAllLoginRole().get(channelHandlerContext));
                }
            }
            return;
        });
        if (this.list.size() > 0) {
            NChatBean bean = new NChatBean();
            bean.setId(5);
            String s = "";
            for (KJRole kjRole : this.list) {
                s = s + kjRole.getRoleName() + "，";
            }
            String roleNames = s.substring(0, s.length() - 1);
            bean.setMessage("#W文曲星君非常欣赏#G" + roleNames + ",#W恭喜他们获得本次答题活动中的幸运参与奖。");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
        }
        this.isEnd();
    }
    
    private void initParam() {
        this.list = new ArrayList<>();
        this.kJexamQuestions = null;
        this.examineeRoles = new LinkedHashMap<>();
        this.b = Boolean.valueOf(true);
        this.xsCount = Integer.valueOf(0);
        this.xsErrorCount = Integer.valueOf(10);
        this.ssCount = Integer.valueOf(20);
        this.ssErrorCount = Integer.valueOf(0);
        this.time = System.currentTimeMillis();
    }
    
    public void verification(ChannelHandlerContext context, String answer) {
        if ((int)this.xsCount >= (int)this.xsErrorCount) {
            this.I = 2;
            this.kJexamQuestions = null;
            this.examineeRoles = new LinkedHashMap<>();
            this.isEnd();
        }
        if (this.kJexamQuestions != null) {
            LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(context);
            if (loginResult.getRolename() == null) {
                return;
            }
            if (this.examineeRoles.get(loginResult.getRole_id()) != null) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("你已经参与过当前答题环节#76"));
                return;
            }
            KJRole kjRole = new KJRole(loginResult.getRole_id(), loginResult.getRolename());
            this.examineeRoles.put(kjRole.getRoleId(), kjRole);
            if (this.kJexamQuestions.getAnswer().equals(answer)) {
                if ((boolean)this.b) {
                    NChatBean bean = new NChatBean();
                    bean.setId(5);
                    bean.setMessage("#Y" + kjRole.getRoleName() + "#G智冠三界,快人快语.第一个解答了文曲星君的谜题。恭喜他获得了本次答题活动中的头奖。");
                    String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    SendMessage.sendMessageToAllRoles(msg);
                    this.sendReward(Boolean.valueOf(true), context, kjRole, loginResult);
                    this.b = Boolean.valueOf(false);
                }
                kjRole.setCorrect(1);
                this.xsCount = Integer.valueOf((int)this.xsCount + 1);
                if ((int)this.xsCount >= (int)this.xsErrorCount) {
                    this.I = 2;
                }
            }
            else {
                kjRole.setCorrect(0);
            }
        }
    }
    
    public void sendTiMU() {
        this.initParam();
        this.I = 1;
        ConcurrentHashMap<Integer, KJexamQuestions> getAllKJexamQuestions = GameServer.getGetAllKJexamQuestions();
        int i = new Random().nextInt(getAllKJexamQuestions.size());
        if (i == 0) {
            ++i;
        }
        this.kJexamQuestions = (KJexamQuestions)getAllKJexamQuestions.get(Integer.valueOf(i));
        NChatBean bean = new NChatBean();
        bean.setId(3);
        bean.setMessage("#G文曲星君题戏三界：#G" + (StringUtils.isNotBlank(this.kJexamQuestions.getTimuType()) ? this.kJexamQuestions.getTimuType() : "") + "#G" + this.kJexamQuestions.getTopicText());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        KJThread1 kjThread1 = new KJThread1(this, Long.valueOf(this.time));
        Thread thread = new Thread(kjThread1);
        thread.start();
    }
    
    static {
        WenQuXingScene.JG = 5000L;
    }
}
