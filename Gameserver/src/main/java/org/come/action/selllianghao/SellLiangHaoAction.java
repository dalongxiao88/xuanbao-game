package org.come.action.selllianghao;

import com.github.pagehelper.PageInfo;
import org.come.bean.SearchSellLiangHaoResultBean;
import java.util.Random;
import java.util.Map;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;
import org.come.bean.MyLiangHaoAucBean;
import org.come.model.LiangHaoInfo;
import java.util.Calendar;
import org.come.entity.RoleTable;
import org.come.action.monitor.MonitorUtil;
import come.tool.Role.RolePool;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import org.come.entity.SellLianghaoAuc;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.regex.Pattern;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import java.util.Arrays;
import org.come.model.SellLiangHaoBase;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SellLiangHaoAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.startsWith("AUCLIST")) {
            List<SellLiangHaoBase> list = null;
            String param = null;
            list = GameServer.getUpselllianghaos();
            param = "AUCLIST|";
            String[] msg = message.split("\\|");
            if (msg.length > 0) {
                if (msg.length > 1 && StringUtils.isNotEmpty(msg[1])) {
                    List<SellLiangHaoBase> list2 = new ArrayList<>();
                    if (list != null && list.size() > 0) {
                        for (SellLiangHaoBase item : list) {
                            if (item.getLianghao().contains(msg[1])) {
                                list2.add(item);
                            }
                        }
                    }
                    this.sendLiangHaoList(this.updateAuc(list2), ctx, 1, param);
                }
                else {
                    this.sendLiangHaoList(this.updateAuc(list), ctx, 1, param);
                }
            }
        }
        else if (message.startsWith("SELLLIST")) {
            List<String> blacklist = Arrays.asList(new String[] { "123456", "654321" });
            List<String> clhs = AllServiceUtil.getRoleTableService().allLiangHao();
            String param2 = "SELLLIST|";
            String[] msg2 = message.split("\\|");
            if (msg2.length > 0) {
                if (msg2.length > 1 && StringUtils.isNotEmpty(msg2[1])) {
                    AssetUpdate assetUpdate = new AssetUpdate();
                    if (msg2[1].contains(" ")) {
                        assetUpdate.setMsg("不能输入空格！");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                    if (Pattern.matches(".*(\\d)\\1{4}.*", msg2[1])) {
                        assetUpdate.setMsg("不能输入5A靓号！");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                    List<SellLiangHaoBase> results = this.createRandomListParam(msg2[1], clhs);
                    if (results == null || results.size() <= 0) {
                        assetUpdate.setMsg("未找到符合条件的靓号！");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                    this.sendLiangHaoList(results, ctx, 1, param2);
                }
                else {
                    List<SellLiangHaoBase> results2 = this.createRandomList(clhs);
                    this.sendLiangHaoList(results2, ctx, 1, param2);
                }
            }
        }
        else if (message.startsWith("BUYAUC")) {
            String[] msg3 = message.split("\\|");
            if (msg3.length > 1) {
                SellLiangHaoBase sellLiangHaoBase = (SellLiangHaoBase)GsonUtil.getGsonUtil().getgson().fromJson(msg3[1], SellLiangHaoBase.class);
                AssetUpdate assetUpdate2 = new AssetUpdate();
                if (loginResult.getCodecard().intValue() < sellLiangHaoBase.getAucPrice()) {
                    assetUpdate2.setMsg("你的仙玉不足！");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    return;
                }
                List<SellLianghaoAuc> alreadys = AllServiceUtil.getSellLianghaoAucService().selectAllByDateAndRoleIdLhAndStatus(loginResult.getRole_id(), sellLiangHaoBase.getAucEndTime(), sellLiangHaoBase.getLianghao(), Short.valueOf((short)1));
                if (alreadys != null && alreadys.size() > 0) {
                    assetUpdate2.setMsg("你已经出价！");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    return;
                }
                LocalDate expDate = LocalDate.parse(sellLiangHaoBase.getAucEndTime());
                LocalDate nowDate = LocalDate.now();
                long daysBetween = ChronoUnit.DAYS.between(nowDate, expDate);
                if (daysBetween <= 0L) {
                    assetUpdate2.setMsg("活动已经结束！");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    return;
                }
                LocalDate startDate = LocalDate.parse(sellLiangHaoBase.getAucStartTime());
                long sBetween = ChronoUnit.DAYS.between(nowDate, startDate);
                if (sBetween > 0L) {
                    assetUpdate2.setMsg("活动还没有开始！");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    return;
                }
                SellLianghaoAuc param3 = new SellLianghaoAuc();
                param3.setAucPoint(BigDecimal.valueOf((long)sellLiangHaoBase.getAucPrice()));
                param3.setLianghao(sellLiangHaoBase.getLianghao());
                param3.setStatus(Short.valueOf((short)1));
                List<SellLianghaoAuc> check = AllServiceUtil.getSellLianghaoAucService().selectByPrice(param3);
                if (check != null && check.size() > 0) {
                    assetUpdate2.setMsg("已经有人出价更高的价格！请重新打开界面显示最新价格。");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String todayStr = sdf.format(new Date());
                List<SellLianghaoAuc> results3 = AllServiceUtil.getSellLianghaoAucService().selectAllByDateAndLhAndStatus(sellLiangHaoBase.getAucEndTime(), sellLiangHaoBase.getLianghao(), Short.valueOf((short)1));
                if (results3 != null && results3.size() > 0) {
                    for (SellLianghaoAuc item2 : results3) {
                        if (item2.getBuyRoleId().compareTo(loginResult.getRole_id()) == 0) {
                            continue;
                        }
                        else {
                            LoginResult login = RolePool.getLoginResult(item2.getBuyRoleId());
                            ChannelHandlerContext tCtx = (login != null) ? ((ChannelHandlerContext)GameServer.getRoleNameMap().get(login.getRolename())) : null;
                            if (tCtx != null) {
                                AssetUpdate assetUpdate3 = new AssetUpdate();
                                assetUpdate3.setType(AssetUpdate.LHRETURN);
                                assetUpdate3.updata("X=" + item2.getAucPoint().longValue());
                                MonitorUtil.getMoney().addX(item2.getAucPoint().longValue(), 888);
                                login.setCodecard(((login.getCodecard() != null) ? login.getCodecard() : BigDecimal.ZERO).add(item2.getAucPoint()));
                                assetUpdate3.setMsg("你竞价靓号：" + item2.getLianghao() + "失败，已经退回" + item2.getAucPoint() + "仙玉");
                                SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
                                SellLianghaoAuc updateStatus = new SellLianghaoAuc();
                                updateStatus.setId(item2.getId());
                                updateStatus.setStatus(Short.valueOf((short)3));
                                AllServiceUtil.getSellLianghaoAucService().updateStauts(updateStatus);
                            }
                            else {
                                SellLianghaoAuc updateStatus2 = new SellLianghaoAuc();
                                updateStatus2.setId(item2.getId());
                                updateStatus2.setStatus(Short.valueOf((short)5));
                                AllServiceUtil.getSellLianghaoAucService().updateStauts(updateStatus2);
                            }
                        }
                    }
                }
                loginResult.setCodecard(loginResult.getCodecard().subtract(new BigDecimal(sellLiangHaoBase.getAucPrice())));
                MonitorUtil.getMoney().useX((long)sellLiangHaoBase.getAucPrice());
                assetUpdate2.setData("X=" + -sellLiangHaoBase.getAucPrice());
                assetUpdate2.setMsg("你已经出价购买靓号：" + sellLiangHaoBase.getLianghao() + "。暂扣" + sellLiangHaoBase.getAucPrice() + "仙玉。");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String nowdayTime = dateFormat.format(new Date());
                List<SellLianghaoAuc> myauc = AllServiceUtil.getSellLianghaoAucService().selectAllByDateAndRoleIdLh(loginResult.getRole_id(), sellLiangHaoBase.getAucEndTime(), sellLiangHaoBase.getLianghao());
                if (myauc != null && myauc.size() > 0) {
                    if (myauc.size() > 1) {
                        for (int i = 0; i < myauc.size(); ++i) {
                            if (i == 0 && myauc.get(i) != null) {
                                ((SellLianghaoAuc)myauc.get(i)).setStatus(Short.valueOf((short)1));
                                ((SellLianghaoAuc)myauc.get(i)).setAucPoint(BigDecimal.valueOf((long)sellLiangHaoBase.getAucPrice()));
                                ((SellLianghaoAuc)myauc.get(i)).setBuyTime(nowdayTime);
                                AllServiceUtil.getSellLianghaoAucService().updateByPrimaryKeySelective((SellLianghaoAuc)myauc.get(i));
                                String msg4 = Agreement.getAgreement().searchSellLiangHao("BUYAUC|" + GsonUtil.getGsonUtil().getgson().toJson(myauc.get(0)));
                                SendMessage.sendMessageToSlef(ctx, msg4);
                            }
                            else {
                                AllServiceUtil.getSellLianghaoAucService().deleteByPrimaryKey(((SellLianghaoAuc)myauc.get(i)).getId());
                            }
                        }
                    }
                    else {
                        ((SellLianghaoAuc)myauc.get(0)).setStatus(Short.valueOf((short)1));
                        ((SellLianghaoAuc)myauc.get(0)).setAucPoint(BigDecimal.valueOf((long)sellLiangHaoBase.getAucPrice()));
                        ((SellLianghaoAuc)myauc.get(0)).setBuyTime(nowdayTime);
                        AllServiceUtil.getSellLianghaoAucService().updateByPrimaryKeySelective((SellLianghaoAuc)myauc.get(0));
                        String msg5 = Agreement.getAgreement().searchSellLiangHao("BUYAUC|" + GsonUtil.getGsonUtil().getgson().toJson(myauc.get(0)));
                        SendMessage.sendMessageToSlef(ctx, msg5);
                    }
                }
                else {
                    SellLianghaoAuc sellLianghaoAuc = new SellLianghaoAuc();
                    sellLianghaoAuc.setBuyRoleId(loginResult.getRole_id());
                    sellLianghaoAuc.setLianghao(sellLiangHaoBase.getLianghao());
                    sellLianghaoAuc.setAucEndTime(sellLiangHaoBase.getAucEndTime());
                    sellLianghaoAuc.setAucPoint(BigDecimal.valueOf((long)sellLiangHaoBase.getAucPrice()));
                    sellLianghaoAuc.setOriginalprice(Integer.valueOf(sellLiangHaoBase.getPrice()));
                    sellLianghaoAuc.setStatus(Short.valueOf((short)1));
                    sellLianghaoAuc.setBuyTime(nowdayTime);
                    AllServiceUtil.getSellLianghaoAucService().insertOrder(sellLianghaoAuc);
                    String msg4 = Agreement.getAgreement().searchSellLiangHao("BUYAUC|" + GsonUtil.getGsonUtil().getgson().toJson(sellLianghaoAuc));
                    SendMessage.sendMessageToSlef(ctx, msg4);
                }
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
            }
        }
        else if (message.startsWith("BUYLIANGHAO")) {
            String[] msg3 = message.split("\\|");
            if (msg3.length >= 2) {
                SellLiangHaoBase sellLiangHaoBase = (SellLiangHaoBase)GsonUtil.getGsonUtil().getgson().fromJson(msg3[1], SellLiangHaoBase.class);
                AssetUpdate assetUpdate2 = new AssetUpdate();
                if (loginResult.getCodecard().intValue() < sellLiangHaoBase.getPrice()) {
                    assetUpdate2.setMsg("你的仙玉不足！");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    return;
                }
                loginResult.setCodecard(loginResult.getCodecard().subtract(new BigDecimal(sellLiangHaoBase.getPrice())));
                MonitorUtil.getMoney().useX((long)sellLiangHaoBase.getPrice());
                assetUpdate2.setData("X=" + -sellLiangHaoBase.getPrice());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你购买了靓号：" + sellLiangHaoBase.getLianghao() + ",扣除了" + sellLiangHaoBase.getPrice() + "仙玉。"));
                RoleTable roleTable = new RoleTable();
                roleTable.setLiangHao(sellLiangHaoBase.getLianghao());
                roleTable.setRole_id(loginResult.getRole_id());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.add(6, 30);
                Date nextMonth = calendar.getTime();
                String expireStr = sdf2.format(nextMonth);
                roleTable.setLianghaoexpire(expireStr);
                roleTable.setContinueprice(Integer.valueOf(sellLiangHaoBase.getPrice()));
                if (loginResult.getLianghaotype() == null) {
                    roleTable.setLianghaotype(Integer.valueOf(5));
                    loginResult.setLianghaotype(Integer.valueOf(5));
                }
                else {
                    roleTable.setLianghaotype(loginResult.getLianghaotype());
                }
                AllServiceUtil.getRoleTableService().getLiangHao(roleTable);
                loginResult.setLiangHao(sellLiangHaoBase.getLianghao());
                loginResult.setLianghaoexpire(expireStr);
                loginResult.setContinueprice(Integer.valueOf(sellLiangHaoBase.getPrice()));
                String msg6 = Agreement.getAgreement().searchSellLiangHao("BUYLIANGHAO|" + GsonUtil.getGsonUtil().getgson().toJson(sellLiangHaoBase));
                SendMessage.sendMessageToSlef(ctx, msg6);
                AssetUpdate assetUpdate4 = new AssetUpdate();
                LiangHaoInfo liangHaoInfo = new LiangHaoInfo();
                liangHaoInfo.setLianghao(sellLiangHaoBase.getLianghao());
                liangHaoInfo.setType((int)roleTable.getLianghaotype());
                liangHaoInfo.setExpTime(roleTable.getLianghaoexpire());
                liangHaoInfo.setContinueprice(Integer.valueOf(sellLiangHaoBase.getPrice()));
                assetUpdate4.updata("LH=" + GsonUtil.getGsonUtil().getgson().toJson(liangHaoInfo));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate4)));
            }
        }
        else if (message.startsWith("MYAUCLIST")) {
            loginResult.getRole_id();
            List<SellLianghaoAuc> result = AllServiceUtil.getSellLianghaoAucService().selectAllByRoleId(loginResult.getRole_id());
            MyLiangHaoAucBean myLiangHaoAucBean = new MyLiangHaoAucBean();
            myLiangHaoAucBean.setSellLianghaoAucs(result);
            String msg7 = Agreement.getAgreement().searchSellLiangHao("MYAUCLIST|" + GsonUtil.getGsonUtil().getgson().toJson(myLiangHaoAucBean));
            SendMessage.sendMessageToSlef(ctx, msg7);
        }
        else if (message.startsWith("XJLIANGHAO")) {
            String[] msg3 = message.split("\\|");
            if (msg3.length >= 2) {
                SellLianghaoAuc sellLianghaoAuc2 = (SellLianghaoAuc)GsonUtil.getGsonUtil().getgson().fromJson(msg3[1], SellLianghaoAuc.class);
                AssetUpdate assetUpdate2 = new AssetUpdate();
                if (loginResult.getCodecard().longValue() < (long)(int)sellLianghaoAuc2.getOriginalprice()) {
                    assetUpdate2.setMsg("你的仙玉不足！");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    return;
                }
                Map<String, String> xjDate = GameServer.getXjDate();
                if (xjDate != null && xjDate.get("end") != null) {
                    LocalDate expDate = LocalDate.parse((CharSequence)xjDate.get("end"));
                    LocalDate nowDate = LocalDate.now();
                    long daysBetween = ChronoUnit.DAYS.between(nowDate, expDate);
                    if (daysBetween <= 0L) {
                        assetUpdate2.setMsg("未到靓号信笺投放时间！");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                        return;
                    }
                    LocalDate startDate = LocalDate.parse((CharSequence)xjDate.get("start"));
                    long sBetween = ChronoUnit.DAYS.between(nowDate, startDate);
                    if (sBetween > 0L) {
                        assetUpdate2.setMsg("活动还没有开始！");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                        return;
                    }
                    String xjLiangHao = sellLianghaoAuc2.getLianghao();
                    RoleTable roleTablep = new RoleTable();
                    roleTablep.setLiangHao(xjLiangHao);
                    List<RoleTable> clhs2 = AllServiceUtil.getRoleTableService().getRoleTaleByLiangHao(roleTablep);
                    if (clhs2 != null && clhs2.size() > 0) {
                        assetUpdate2.setMsg("靓号" + xjLiangHao + "已经被占用！");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                        return;
                    }
                    List<SellLiangHaoBase> auclist = GameServer.getUpselllianghaos();
                    if (auclist != null && auclist.size() > 0) {
                        for (SellLiangHaoBase item3 : auclist) {
                            if (item3.getLianghao().equals(xjLiangHao)) {
                                assetUpdate2.setMsg("靓号" + xjLiangHao + "已经被占用！");
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                                return;
                            }
                        }
                    }
                    RoleTable roleTable2 = new RoleTable();
                    roleTable2.setLiangHao(xjLiangHao);
                    roleTable2.setRole_id(loginResult.getRole_id());
                    SimpleDateFormat sdf3 = new SimpleDateFormat("yyy-MM-dd");
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.add(6, 30);
                    Date nextMonth2 = calendar2.getTime();
                    String expireStr2 = sdf3.format(nextMonth2);
                    roleTable2.setLianghaoexpire(expireStr2);
                    roleTable2.setContinueprice(sellLianghaoAuc2.getOriginalprice());
                    if (loginResult.getLianghaotype() == null) {
                        roleTable2.setLianghaotype(Integer.valueOf(5));
                    }
                    else {
                        roleTable2.setLianghaotype(loginResult.getLianghaotype());
                    }
                    AllServiceUtil.getRoleTableService().getLiangHao(roleTable2);
                    loginResult.setLiangHao(xjLiangHao);
                    AssetUpdate assetUpdate5 = new AssetUpdate();
                    LiangHaoInfo liangHaoInfo2 = new LiangHaoInfo();
                    liangHaoInfo2.setLianghao(xjLiangHao);
                    liangHaoInfo2.setType((int)roleTable2.getLianghaotype());
                    liangHaoInfo2.setExpTime(roleTable2.getLianghaoexpire());
                    assetUpdate5.updata("LH=" + GsonUtil.getGsonUtil().getgson().toJson(liangHaoInfo2));
                    assetUpdate5.updata("LH=" + GsonUtil.getGsonUtil().getgson().toJson(liangHaoInfo2));
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate5)));
                    loginResult.setCodecard(loginResult.getCodecard().subtract(new BigDecimal((int)sellLianghaoAuc2.getOriginalprice())));
                    MonitorUtil.getMoney().useX((long)(int)sellLianghaoAuc2.getOriginalprice());
                    assetUpdate2.setData("X=" + -(int)sellLianghaoAuc2.getOriginalprice());
                    assetUpdate2.setMsg("你已经购买了靓号：" + xjLiangHao + "。扣除" + sellLianghaoAuc2.getOriginalprice() + "仙玉。");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                }
                else {
                    assetUpdate2.setMsg("活动已经结束！");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    return;
                }
            }
        }
        else if (message.startsWith("CHANGETYPE")) {
            String[] msg3 = message.split("\\|");
            if (msg3.length >= 2) {
                SellLiangHaoBase sellLiangHaoBase = (SellLiangHaoBase)GsonUtil.getGsonUtil().getgson().fromJson(msg3[1], SellLiangHaoBase.class);
                if ((int)loginResult.getLianghaotype() == sellLiangHaoBase.getType()) {
                    return;
                }
                long jg = (long)sellLiangHaoBase.getAucPrice();
                if (jg <= 0L) {
                    return;
                }
                AssetUpdate assetUpdate6 = new AssetUpdate();
                long gold = loginResult.getCodecard().longValue() - jg;
                if (gold < 0L) {
                    assetUpdate6.setMsg("染色失败，染色需扣除" + String.valueOf(jg) + "仙玉，但是你没有足够的仙玉");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate6)));
                }
                else {
                    loginResult.setCodecard(new BigDecimal(gold));
                    MonitorUtil.getMoney().useX(jg);
                    assetUpdate6.setMsg("染色成功扣除仙玉：" + String.valueOf(jg));
                    assetUpdate6.setData("X=" + -jg);
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate6)));
                    RoleTable roleTable3 = new RoleTable();
                    roleTable3.setRole_id(loginResult.getRole_id());
                    roleTable3.setLianghaotype(Integer.valueOf(sellLiangHaoBase.getType()));
                    AllServiceUtil.getRoleTableService().updateRoleLiangHaoType(roleTable3);
                    loginResult.setLianghaotype(roleTable3.getLianghaotype());
                    String msg6 = Agreement.getAgreement().searchSellLiangHao("CHANGETYPE|" + sellLiangHaoBase.getType());
                    SendMessage.sendMessageToSlef(ctx, msg6);
                }
            }
        }
        else if (message.startsWith("ADDLHTIME")) {
            String[] msg3 = message.split("\\|");
            if (msg3.length >= 1) {
                AssetUpdate assetUpdate7 = new AssetUpdate();
                int month = (int)Integer.valueOf(msg3[1]);
                long money = 888L;
                if (loginResult.getContinueprice() != null) {
                    long gold = loginResult.getCodecard().longValue() - money;
                    if (gold < 0L) {
                        assetUpdate7.setMsg("续费失败，续费需扣除" + String.valueOf(money) + "仙玉，但是你没有足够的仙玉。");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate7)));
                    }
                    else if (StringUtils.isNotBlank(loginResult.getLiangHao())) {
                        loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - money));
                        MonitorUtil.getMoney().useX(money);
                        assetUpdate7.setData("X=" + -money);
                        assetUpdate7.setMsg("你已经消费" + money + "仙玉,给你的靓号续费1个月时间。");
                        String expDate2 = loginResult.getLianghaoexpire();
                        RoleTable roleTable2 = new RoleTable();
                        roleTable2.setRole_id(loginResult.getRole_id());
                        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = dateFormat2.parse(expDate2);
                            Calendar calendar3 = Calendar.getInstance();
                            calendar3.setTime(date);
                            calendar3.add(6, 30);
                            String strDate = dateFormat2.format(calendar3.getTime());
                            roleTable2.setLianghaoexpire(strDate);
                            loginResult.setLianghaoexpire(strDate);
                            AllServiceUtil.getRoleTableService().addLiangHaoExp(roleTable2);
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate7)));
                            String msg8 = Agreement.getAgreement().searchSellLiangHao("ADDLHTIME|" + strDate);
                            SendMessage.sendMessageToSlef(ctx, msg8);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        else if (message.startsWith("DROPLIANGHAO")) {
            if (StringUtils.isNotBlank(loginResult.getLiangHao())) {
                String liangHao = loginResult.getLiangHao();
                RoleTable roleTable4 = new RoleTable();
                roleTable4.setRole_id(loginResult.getRole_id());
                roleTable4.setLianghaotype(null);
                roleTable4.setLiangHao(null);
                roleTable4.setLianghaoexpire(null);
                roleTable4.setContinueprice(null);
                AllServiceUtil.getRoleTableService().dropLiangHao(roleTable4);
                loginResult.setLiangHao(null);
                loginResult.setLianghaotype(null);
                loginResult.setLianghaoexpire(null);
                loginResult.setContinueprice(null);
                String msg7 = Agreement.getAgreement().searchSellLiangHao("DROPLIANGHAO|" + liangHao);
                SendMessage.sendMessageToSlef(ctx, msg7);
            }
        }
        else if (message.startsWith("DELAUC")) {
            String[] msg3 = message.split("\\|");
            if (msg3.length >= 1) {
                AssetUpdate assetUpdate7 = new AssetUpdate();
                SellLianghaoAuc sellLianghaoAuc3 = (SellLianghaoAuc)GsonUtil.getGsonUtil().getgson().fromJson(msg3[1], SellLianghaoAuc.class);
                loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(sellLianghaoAuc3.getAucPoint().intValue())));
                MonitorUtil.getMoney().addX((long)sellLianghaoAuc3.getAucPoint().intValue(), 888);
                assetUpdate7.setData("X=" + sellLianghaoAuc3.getAucPoint());
                assetUpdate7.setMsg("你放弃了竞拍靓号：" + sellLianghaoAuc3.getLianghao() + "。退还" + sellLianghaoAuc3.getAucPoint() + "点仙玉。");
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate7)));
                SellLianghaoAuc updateStatus3 = new SellLianghaoAuc();
                updateStatus3.setId(sellLianghaoAuc3.getId());
                updateStatus3.setStatus(Short.valueOf((short)4));
                AllServiceUtil.getSellLianghaoAucService().updateStauts(updateStatus3);
                sellLianghaoAuc3.setStatus(Short.valueOf((short)4));
                String msg9 = Agreement.getAgreement().searchSellLiangHao("DELAUC|" + GsonUtil.getGsonUtil().getgson().toJson(sellLianghaoAuc3));
                SendMessage.sendMessageToSlef(ctx, msg9);
            }
        }
    }
    
    public List<SellLiangHaoBase> createRandomListParam(String lhPart, List<String> clhs) {
        List<String> blacklist = Arrays.asList(new String[] { "123456", "654321" });
        List<SellLiangHaoBase> results = new ArrayList<>();
        int bk = 10000;
        if (lhPart.length() > 6) {
            return results;
        }
        if (lhPart.length() == 6 && !results.contains(lhPart) && !clhs.contains(lhPart) && !blacklist.contains(lhPart) && !Pattern.matches(".*(\\d)\\1{4}.*", lhPart)) {
            SellLiangHaoBase item = new SellLiangHaoBase();
            item.setLianghao(lhPart);
            item.setType(2);
            item.setPrice(2800);
            results.add(item);
            return results;
        }
        if (lhPart.length() == 6 && results.contains(lhPart) && clhs.contains(lhPart) && blacklist.contains(lhPart) && Pattern.matches(".*(\\d)\\1{4}.*", lhPart)) {
            return results;
        }
        if (lhPart.contains(" ")) {
            return results;
        }
        do {
            int index = new Random().nextInt(6 - lhPart.length() + 1);
            int head = 0;
            int end = 0;
            if (index > 0) {
                String fix = "";
                for (int i = 1; i < index; ++i) {
                    fix += "0";
                }
                head = new Random().nextInt((int)Integer.valueOf("9" + fix)) + (int)Integer.valueOf("1" + fix);
            }
            if (index + lhPart.length() < 6) {
                String endfix = "";
                for (int endNum = 6 - (index + lhPart.length()), j = 1; j < endNum; ++j) {
                    endfix += "0";
                }
                end = new Random().nextInt((int)Integer.valueOf("9" + endfix)) + (int)Integer.valueOf("1" + endfix);
            }
            String str = ((head == 0) ? "" : String.valueOf(head)) + lhPart + ((end == 0) ? "" : String.valueOf(end));
            if (!results.contains(str) && !clhs.contains(str) && !blacklist.contains(str) && !Pattern.matches(".*(\\d)\\1{4}.*", str)) {
                SellLiangHaoBase item2 = new SellLiangHaoBase();
                item2.setLianghao(str);
                item2.setType(2);
                item2.setPrice(2800);
                results.add(item2);
            }
            else {
                --bk;
            }
        } while (results.size() < 16 && bk > 1000);
        return results;
    }
    
    public List<SellLiangHaoBase> createRandomList(List<String> clhs) {
        List<String> blacklist = Arrays.asList(new String[] { "123456", "654321" });
        List<SellLiangHaoBase> results = new ArrayList<>();
        do {
            String randomNum = String.valueOf(generateRandomNumber());
            if (!results.contains(randomNum) && !clhs.contains(randomNum) && !blacklist.contains(randomNum) && !Pattern.matches(".*(\\d)\\1{4}.*", randomNum) && !randomNum.contains(" ")) {
                SellLiangHaoBase item = new SellLiangHaoBase();
                item.setLianghao(randomNum);
                item.setType(2);
                item.setPrice(2800);
                results.add(item);
            }
        } while (results.size() < 16);
        return results;
    }
    
    public void sendLiangHaoList(List<SellLiangHaoBase> list, ChannelHandlerContext ctx, int type, String param) {
        SearchSellLiangHaoResultBean resultBean2 = new SearchSellLiangHaoResultBean();
        resultBean2.setXjDate(GameServer.getXjDate());
        PageInfo<SellLiangHaoBase> pageInfo2 = new PageInfo(list);
        resultBean2.setSellLiangHaos(pageInfo2.getList());
        resultBean2.setTotal(pageInfo2.getPages());
        String msg2 = Agreement.getAgreement().searchSellLiangHao(param + GsonUtil.getGsonUtil().getgson().toJson(resultBean2));
        if (type == 2) {
            SendMessage.sendMessageToAllRoles(msg2);
        }
        else if (type == 1) {
            SendMessage.sendMessageToSlef(ctx, msg2);
        }
    }
    
    public List<SellLiangHaoBase> updateAuc(List<SellLiangHaoBase> res) {
        if (res != null && res.size() > 0) {
            List<SellLianghaoAuc> list = AllServiceUtil.getSellLianghaoAucService().selectAllByStatus(Short.valueOf((short)1));
            if (list != null && list.size() > 0) {
                for (SellLianghaoAuc oitem : list) {
                    for (SellLiangHaoBase item : res) {
                        if (item.getLianghao().equals(oitem.getLianghao())) {
                            item.setAucPrice(oitem.getAucPoint().intValue());
                        }
                    }
                }
            }
            return res;
        }
        else {
            return res;
        }
    }
    
    public static void main(String[] args) {
    }
    
    private static int generateRandomNumber() {
        return new Random().nextInt(900000) + 100000;
    }
}
