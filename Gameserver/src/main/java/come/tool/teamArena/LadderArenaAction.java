package come.tool.teamArena;

import java.util.concurrent.ConcurrentHashMap;
import come.tool.newTeam.TeamBean;
import java.util.OptionalInt;
import org.come.service.TtModelService;
import org.come.model.Dorp;
import org.come.task.RefreshMonsterTask;
import java.time.LocalTime;
import org.come.model.Configure;
import come.tool.newTeam.TeamUtil;
import java.util.stream.IntStream;
import java.util.Objects;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Calendar;
import org.come.bean.TtModel;
import java.util.stream.Collectors;
import java.util.List;
import org.come.until.AllServiceUtil;
import org.apache.commons.lang.StringUtils;
import come.tool.Good.DropUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class LadderArenaAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.equals("4") || message.equals("5") || message.equals("6") || message.equals("7")) {
            String[] ttAward = roleInfo.getTTJIANGLI().split("\\|");
            int i = Integer.parseInt(message);
            int SLnum = Integer.parseInt(ttAward[4]);
            if (i == 4) {
                if (SLnum < 3) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的胜利场次不足3场,无法领取奖励"));
                    return;
                }
                if (ttAward[0].equals("1")) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经领取过该奖励,请勿重复领取"));
                    return;
                }
                Dorp dorp = GameServer.getDorp("7004");
                DropUtil.getDrop4(roleInfo, dorp.getDorpValue(), "#c00FFFF【天梯比武】#R{角色名}#c00 FFFF在本周天梯比武中豪取#R3次#c00FFFF胜利,打开天梯宝箱意外的发现了#G{物品名},#c00FFFF真是惊喜连连#90", 25, 1.0, null, "", "", null);
                ttAward[0] = "1";
                String join = StringUtils.join(ttAward, "|");
                roleInfo.setTTJIANGLI(join);
            }
            if (i == 5) {
                if (SLnum < 5) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的胜利场次不足5场,无法领取奖励"));
                    return;
                }
                if (ttAward[1].equals("1")) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经领取过该奖励,请勿重复领取"));
                    return;
                }
                Dorp dorp = GameServer.getDorp("7005");
                DropUtil.getDrop4(roleInfo, dorp.getDorpValue(), "#c00FFFF【天梯比武】#R{角色名}#c00FFFF在本周天梯比武中豪取#R5次#c00FFFF胜利,打开天梯宝箱意外的发现了#G{物品名},#c00FFFF真是惊喜连连#90", 25, 1.0, null, "", "", null);
                ttAward[1] = "1";
                String join = StringUtils.join(ttAward, "|");
                roleInfo.setTTJIANGLI(join);
            }
            int CYnum = Integer.parseInt(ttAward[5]);
            if (i == 6) {
                if (CYnum < 10) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的参与场次不足,无法领取奖励"));
                    return;
                }
                if (ttAward[2].equals("1")) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经领取过该奖励,请勿重复领取"));
                    return;
                }
                Dorp dorp2 = GameServer.getDorp("7006");
                DropUtil.getDrop4(roleInfo, dorp2.getDorpValue(), "#c00FFFF【天梯比武】#R{角色名}#c00FFFF在本周天梯比武中踊跃参与,完成10场对战时打开天梯宝箱意外的发现了#G{物品名},#c00FFFF真是惊喜连连#90", 25, 1.0, null, "", null, null);
                ttAward[2] = "1";
                String join2 = StringUtils.join(ttAward, "|");
                roleInfo.setTTJIANGLI(join2);
            }
            TtModelService ttModelService = AllServiceUtil.getTtModelService();
            List<TtModel> ttConfig = ttModelService.getTtConfig();
            List<TtModel> openTT = (List)ttConfig.stream().filter(item/* org.come.bean.TtModel, */ -> (int)item.getIsOpen() != 0).collect(Collectors.toList());
            TtModel ttModel = (TtModel)openTT.get(0);
            Calendar startInstance = Calendar.getInstance();
            startInstance.setTime(ttModel.getSeasonStartTime());
            Calendar endInstance = Calendar.getInstance();
            endInstance.setTime(ttModel.getSeasonEndTime());
            Calendar currInstance = Calendar.getInstance(Locale.CHINA);
            if (i == 7) {
                if (currInstance.after(endInstance)) {
                    long daysDifference = ChronoUnit.DAYS.between(endInstance.toInstant(), currInstance.toInstant());
                    if (daysDifference <= 3L) {
                        if (ttAward[3].equals("1")) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经领取过该奖励,请勿重复领取"));
                            return;
                        }
                        List<LoginResult> TTpai = (List<LoginResult>)GameServer.allBangList.put(Integer.valueOf(8), AllServiceUtil.getRoleTableService().selectOrderByType(Integer.valueOf(8)));
                        OptionalInt indexOpt = IntStream.range(0, ((List<LoginResult>)Objects.requireNonNull(TTpai)).size()).filter(j/* int, */ -> ((LoginResult)TTpai.get(j)).getRole_id().equals(roleInfo.getRole_id())).findFirst();
                        if (indexOpt.isPresent() && ((int)roleInfo.getTtVictory() != 0 || (int)roleInfo.getTtFail() != 0)) {
                            int index = indexOpt.getAsInt();
                            if (index <= 5) {
                                Dorp dorp3 = GameServer.getDorp("7007");
                                DropUtil.getDrop4(roleInfo, dorp3.getDorpValue(), "#G{角色名}#Y领取了天梯前五名的奖励#90", 25, 1.0, null, "", null, null);
                                ttAward[3] = "1";
                                String join3 = StringUtils.join(ttAward, "|");
                                roleInfo.setTTJIANGLI(join3);
                            }
                            if (index > 5 && index <= 10) {
                                Dorp dorp3 = GameServer.getDorp("7008");
                                DropUtil.getDrop4(roleInfo, dorp3.getDorpValue(), "#G{角色名}#Y领取了天梯六到十名的奖励#90", 25, 1.0, null, "", null, null);
                                ttAward[3] = "1";
                                String join3 = StringUtils.join(ttAward, "|");
                                roleInfo.setTTJIANGLI(join3);
                            }
                            if (index > 10 && index <= 15) {
                                Dorp dorp3 = GameServer.getDorp("7009");
                                DropUtil.getDrop4(roleInfo, dorp3.getDorpValue(), "#G{角色名}#Y领取了天梯十一到十五名的奖励#90", 25, 1.0, null, "", null, null);
                                ttAward[3] = "1";
                                String join3 = StringUtils.join(ttAward, "|");
                                roleInfo.setTTJIANGLI(join3);
                            }
                            if (index > 15 && index <= 40) {
                                Dorp dorp3 = GameServer.getDorp("7010");
                                DropUtil.getDrop4(roleInfo, dorp3.getDorpValue(), "#G{角色名}#Y领取了天梯十六到四十名的奖励#90", 25, 1.0, null, "", null, null);
                                ttAward[3] = "1";
                                String join3 = StringUtils.join(ttAward, "|");
                                roleInfo.setTTJIANGLI(join3);
                            }
                            if (index > 40 && index <= 80) {
                                Dorp dorp3 = GameServer.getDorp("7011");
                                DropUtil.getDrop4(roleInfo, dorp3.getDorpValue(), "#G{角色名}#Y领取了天梯四十一到八十名的奖励#90", 25, 1.0, null, "", null, null);
                                ttAward[3] = "1";
                                String join3 = StringUtils.join(ttAward, "|");
                                roleInfo.setTTJIANGLI(join3);
                            }
                            if (index > 80) {
                                Dorp dorp3 = GameServer.getDorp("7012");
                                DropUtil.getDrop4(roleInfo, dorp3.getDorpValue(), "#G{角色名}#Y领取了天梯参与奖励#90", 25, 1.0, null, "", null, null);
                                ttAward[3] = "1";
                                String join3 = StringUtils.join(ttAward, "|");
                                roleInfo.setTTJIANGLI(join3);
                            }
                        }
                        else {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你没有参加天梯活动,没有奖励"));
                            return;
                        }
                    }
                    else {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("赛季奖励结束已超过3天,无法领取赛季奖励"));
                        return;
                    }
                }
                else {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("赛季还没结束,无法领取赛季奖励"));
                    return;
                }
            }
            return;
        }
        else {
            if (roleInfo == null) {
                return;
            }
            TeamBean bean = TeamUtil.getTeam(roleInfo.getTroop_id());
            if (bean == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还没有队伍无法参与"));
                return;
            }
            ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
            Configure configure = (Configure)s.get(Integer.valueOf(7));
            String ttzq = configure.getZqsld();
            LocalTime ttks = LocalTime.parse(configure.getZqjnsx());
            LocalTime ttjs = LocalTime.parse(configure.getJumpurl());
            LocalTime dateTime = LocalTime.of(RefreshMonsterTask.hour, RefreshMonsterTask.minute, RefreshMonsterTask.second);
            if (!ttzq.contains(RefreshMonsterTask.day-1 + "")) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("天梯还未开放，询问管理员#32"));
                return;
            }
            if (LadderArenaUtil.teamArenaThread == null) {
                TtModelService ttModelService2 = AllServiceUtil.getTtModelService();
                List<TtModel> ttConfig2 = ttModelService2.getTtConfig();
                List<TtModel> openTT2 = (List)ttConfig2.stream().filter(item/* org.come.bean.TtModel, */ -> (int)item.getIsOpen() == 1).collect(Collectors.toList());
                if (openTT2.size() == 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前赛季已经关闭#32"));
                    return;
                }
                String time = configure.getZqjnsx() + " - " + configure.getJumpurl();
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#R开放时间为:" + time));
                return;
            }
            else {
                TtModelService ttModelService2 = AllServiceUtil.getTtModelService();
                List<TtModel> ttConfig2 = ttModelService2.getTtConfig();
                List<TtModel> openTT2 = (List)ttConfig2.stream().filter(item/* org.come.bean.TtModel, */ -> (int)item.getIsOpen() == 1).collect(Collectors.toList());
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(11);
                if (!ttks.isBefore(dateTime) && !dateTime.isBefore(ttjs)) {
                    String time2 = configure.getZqjnsx() + " - " + configure.getJumpurl();
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("匹配通道未开启!!!#R开放时间为:" + time2));
                    return;
                }
                if (message.equals("O")) {
                    if (!bean.isCaptian(roleInfo.getRole_id())) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你不是队长"));
                        return;
                    }
                    LadderArenaUtil.addAffirm(ctx, bean);
                }
                else if (message.equals("A")) {
                    LadderArenaUtil.confirm(bean, roleInfo, true);
                }
                else if (message.equals("D")) {
                    LadderArenaUtil.confirm(bean, roleInfo, false);
                }
                return;
            }
        }
    }
    
    public static void restTTJL(LoginResult loginResult) {
        try {
            if (loginResult.getTTJIANGLI() != null) {
                String[] ttAward = loginResult.getTTJIANGLI().split("\\|");
                for (int i = 0; i < ttAward.length; ++i) {
                    ttAward[i] = ((i == 0 || i == 1 || i == 2 || (i >= 4 && i <= 5)) ? "0" : ttAward[i]);
                    String join = StringUtils.join(ttAward, "|");
                    loginResult.setTTJIANGLI(join);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
