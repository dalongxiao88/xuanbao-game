package org.come.action.reward;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.List;
import org.come.entity.Lingbao;
import org.come.tool.EquipTool;
import org.come.until.SplitLingbaoValue;
import org.come.until.AllServiceUtil;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import org.come.until.GsonUtil;
import org.come.bean.RewardDrawingBean;
import org.come.entity.RewardHall;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.util.Date;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import org.come.action.IAction;

public class DrawnitemsAction implements IAction
{
    private Random random;
    
    public DrawnitemsAction() {
        this.random = new Random();
    }
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo.getDrawing() != null) {
            int date = differentDays(roleInfo.getDrawing(), new Date());
            if (date < 7) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().drawnitemsfailAgreement(date + ""));
                return;
            }
        }
        while (GameServer.rewardList.size() != 0) {
            int a = this.random.nextInt(GameServer.rewardList.size());
            RewardHall rewardHall = (RewardHall)GameServer.rewardList.get(a);
            if (rewardHall.getVersion() != 0) {
                GameServer.rewardList.remove(a);
            }
            else {
                RewardDrawingBean bean = new RewardDrawingBean();
                bean.setRewardHall(rewardHall);
                rewardHall.setVersion(1);
                roleInfo.setDrawing(new Date());
                bean.setRoleName(roleInfo.getRolename());
                SendMessage.sendMessageToAllRoles(Agreement.getAgreement().drawnitemsAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
                Goodstable goodstable = (Goodstable)GsonUtil.getGsonUtil().getgson().fromJson(rewardHall.getGoodstable(), Goodstable.class);
                if (goodstable == null) {
                    return;
                }
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.INTEGRATION);
                assetUpdate.updata("帮派积分=-50");
                roleInfo.setScore(mergeRecordEntry(roleInfo.getScore(), "帮派积分=50", 3));
                assetUpdate.setMsg("1个" + goodstable.getGoodsname());
                goodstable.setRole_id(roleInfo.getRole_id());
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(0));
                long yid = goodstable.getGoodsid().longValue();
                for (int i = 0; i < 1; ++i) {
                    if (i != 0) {
                        goodstable = GameServer.getGood(goodstable.getGoodsid());
                    }
                    goodstable.setRole_id(roleInfo.getRole_id());
                    long sid = goodstable.getGoodsid().longValue();
                    if (sid >= 70001L && sid <= 70030L) {
                        Lingbao lingbao = SplitLingbaoValue.addling(goodstable.getGoodsname(), roleInfo.getRole_id());
                        assetUpdate.setLingbao(lingbao);
                    }
                    else if (sid >= 69001L && sid <= 69015L) {
                        Lingbao lingbao = SplitLingbaoValue.addfa(sid, roleInfo.getRole_id());
                        assetUpdate.setLingbao(lingbao);
                    }
                    else if (EquipTool.canSuper(goodstable.getType())) {
                        int sum = (yid == sid) ? 1 : 1;
                        List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(roleInfo.getRole_id(), goodstable.getGoodsid());
                        if (sameGoodstable.size() != 0) {
                            ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum));
                            AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                            assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                        }
                        else {
                            goodstable.setUsetime(Integer.valueOf(sum));
                            AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                            assetUpdate.setGood(goodstable);
                        }
                        if (yid == sid) {
                            break;
                        }
                    }
                    else {
                        goodstable.setUsetime(Integer.valueOf(1));
                        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                        assetUpdate.setGood(goodstable);
                    }
                }
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                GameServer.rewardList.remove(a);
                AllServiceUtil.getRewardHallMallService().deleteByPrimaryKey(rewardHall.getId());
                return;
            }
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().drawnitemsfailAgreement("0"));
    }
    
    public static int differentDays(Date date1, Date date2) {
        TimeZone zone = TimeZone.getTimeZone("GMT-8:00");
        Calendar cal1 = Calendar.getInstance(zone);
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance(zone);
        cal2.setTime(date2);
        int day1 = cal1.get(6);
        int day2 = cal2.get(6);
        int year1 = cal1.get(1);
        int year2 = cal2.get(1);
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; ++i) {
                if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
                    timeDistance += 366;
                }
                else {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        }
        else {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }
    
    /**
     * 语义化别名：按指定策略合并或替换记录串中的条目。
     *
     * 该方法主要被积分、属性串、成就记录等“key=value”型文本记录复用。
     */
    public static String mergeRecordEntry(String source, String entry, int mergeType) {
        return mergeRecordEntryInternal(source, entry, mergeType);
    }

    /**
     * 记录串合并的内部实现。
     */
    private static String mergeRecordEntryInternal(String source, String entry, int mergeType) {
        boolean shouldAppendOriginalEntry = true;
        boolean appendEntryAfterScan = false;
        if (mergeType == 11 || mergeType == 2 || mergeType == 3 || mergeType == 5) {
            appendEntryAfterScan = true;
        }
        List<String> mergedEntries = new ArrayList<>();
        if (source == null) {
            source = "";
        }
        String[] sourceEntries = source.split("\\|");
        for (int i = 0; i < sourceEntries.length; ++i) {
            if (mergeType == 0) {
                if (!sourceEntries[i].equals(entry)) {
                    mergedEntries.add(sourceEntries[i]);
                }
                else {
                    shouldAppendOriginalEntry = false;
                }
            }
            else {
                String[] currentEntry = sourceEntries[i].split("=");
                String[] incomingEntry = entry.split("=");
                if (currentEntry[0].equals(incomingEntry[0])) {
                    if (mergeType == 1) {
                        if (mergeType == 11) {
                            appendEntryAfterScan = false;
                        }
                        mergedEntries.add(entry);
                    }
                    else if (mergeType == 2) {
                        appendEntryAfterScan = false;
                        double originalValue = Double.parseDouble(currentEntry[1]);
                        double deltaValue = Double.parseDouble(incomingEntry[1]);
                        originalValue += deltaValue;
                        if (originalValue % 1.0 == 0.0) {
                            mergedEntries.add(currentEntry[0] + "=" + (int)originalValue);
                        }
                        else {
                            mergedEntries.add(currentEntry[0] + "=" + originalValue);
                        }
                    }
                    else if (mergeType == 3) {
                        appendEntryAfterScan = false;
                        double originalValue = Double.parseDouble(currentEntry[1]);
                        double deltaValue = Double.parseDouble(incomingEntry[1]);
                        originalValue -= deltaValue;
                        if (originalValue % 1.0 == 0.0) {
                            mergedEntries.add(currentEntry[0] + "=" + (int)originalValue);
                        }
                        else {
                            mergedEntries.add(currentEntry[0] + "=" + originalValue);
                        }
                    }
                    else if (mergeType == 5) {
                        appendEntryAfterScan = false;
                        double originalValue = Double.parseDouble(currentEntry[1]);
                        double incomingValue = Double.parseDouble(incomingEntry[1]);
                        if (incomingValue > originalValue) {
                            originalValue = incomingValue;
                        }
                        if (originalValue % 1.0 == 0.0) {
                            mergedEntries.add(currentEntry[0] + "=" + (int)originalValue);
                        }
                        else {
                            mergedEntries.add(currentEntry[0] + "=" + originalValue);
                        }
                    }
                }
                else {
                    mergedEntries.add(sourceEntries[i]);
                }
            }
        }
        if (shouldAppendOriginalEntry && mergeType == 0) {
            mergedEntries.add(entry);
        }
        if (appendEntryAfterScan) {
            mergedEntries.add(entry);
        }
        StringBuffer mergedText = new StringBuffer();
        for (int j = 0; j < mergedEntries.size(); ++j) {
            if (!mergedText.toString().equals("")) {
                mergedText.append("|" + mergedEntries.get(j));
            }
            else {
                mergedText.append(mergedEntries.get(j));
            }
        }
        return mergedText.toString();
    }
}
