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
                roleInfo.setScore(Splice(roleInfo.getScore(), "帮派积分=50", 3));
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
    
    public static String Splice(String v, String b, int type) {
        boolean s = true;
        boolean s2 = false;
        if (type == 11 || type == 2 || type == 3 || type == 5) {
            s2 = true;
        }
        List<String> jihe = new ArrayList<>();
        if (v == null) {
            v = "";
        }
        String[] vs = v.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (type == 0) {
                if (!vs[i].equals(b)) {
                    jihe.add(vs[i]);
                }
                else {
                    s = false;
                }
            }
            else {
                String[] vs2 = vs[i].split("=");
                String[] vs3 = b.split("=");
                if (vs2[0].equals(vs3[0])) {
                    if (type == 1) {
                        if (type == 11) {
                            s2 = false;
                        }
                        jihe.add(b);
                    }
                    else if (type == 2) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        x1 += x2;
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                    else if (type == 3) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        x1 -= x2;
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                    else if (type == 5) {
                        s2 = false;
                        double x1 = Double.parseDouble(vs2[1]);
                        double x2 = Double.parseDouble(vs3[1]);
                        if (x2 > x1) {
                            x1 = x2;
                        }
                        if (x1 % 1.0 == 0.0) {
                            jihe.add(vs2[0] + "=" + (int)x1);
                        }
                        else {
                            jihe.add(vs2[0] + "=" + x1);
                        }
                    }
                }
                else {
                    jihe.add(vs[i]);
                }
            }
        }
        if (s && type == 0) {
            jihe.add(b);
        }
        if (s2) {
            jihe.add(b);
        }
        StringBuffer genggai = new StringBuffer();
        for (int j = 0; j < jihe.size(); ++j) {
            if (!genggai.toString().equals("")) {
                genggai.append("|" + (String)jihe.get(j));
            }
            else {
                genggai.append((String)jihe.get(j));
            }
        }
        return genggai.toString();
    }
}
