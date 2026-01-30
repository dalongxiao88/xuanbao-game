package come.tool.Good;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.come.bean.*;
import org.come.entity.*;
import org.come.model.*;
import org.come.readUtil.ReadPoolUtil;
import org.come.tool.EquipTool;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.FightingForesee;
import come.tool.FightingData.Sepcies_MixDeal;
import come.tool.Battle.BattleMixDeal;
import org.come.action.summoning.SummonPetAction;
import org.come.action.monitor.MonitorLimit;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import java.util.HashSet;
import java.util.Set;

import org.come.action.zxpack.ZxpackNineAction;
import come.tool.Role.RoleData;
import java.util.Iterator;
import come.tool.FightingData.Battlefield;
import org.come.readUtil.ReadMapUtil;
import org.come.tool.Goodtype;
import org.come.action.reward.DrawnitemsAction;
import come.tool.Role.RolePool;
import org.come.action.buy.AddGoodUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import org.come.handler.SendMessage;
import org.come.tool.WriteOut;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import java.util.Map;
import java.util.List;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;
import org.come.action.IAction;

public class UseRoleAction implements IAction
{
    public static Random random;
    public static ConcurrentHashMap<BigDecimal, ConcurrentHashMap<String, List<Npctable>>> zhNpc;
    public static Map<ChannelHandlerContext, List<Goodstable>> lottery;
    public static Map<ChannelHandlerContext, List<Goodstable>> lottery1;
    private static final String[] NUMS;
    //八十一难礼盒物品
    public static String[] lbidText = {"77215","77215","77212","77215","77215","77207","77215",
            "77215","77211","77203","77203","77203",
            "77203","77203","77203","77203","77203",
            "77203","77204","77211","77204","77204",
            "77213","77204","77204","77212","77204",
            "77205","77214","77205","77205","77205",
            "77205","77202","77202","77214","77214",
            "77212","77206","77206","77206","77206",
            "77207","77207","77214","77207","77207",
            "77208","77208","77208","77208","77208",
            "77209","77209","77212","77209","77209",
            "77210","77210","77210","77210","77210",
            "77211","77211","77211","77212","77212",
            "77213","77213","77212","77211","77213",
            "77207","77207","77214","77214","77214",
            "77202","77202","77202","77202"};
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        int number222=0;  int aaa=0;
        // 检查字符串是否不为空且包含 "%" 符号
        if (message != null && !message.isEmpty() && message.contains("!")) {
            // 找到 "%" 符号的位置
            int percentIndex = message.indexOf('!');

            // 提取 "%" 符号后面的部分
            String numberPart = message.substring(percentIndex + 1).trim();
            number222 = Integer.parseInt(numberPart);

            aaa=9;
        }else {
            aaa=0;

        }
        if(message.split("=")[0].equals("我要浇水")) {//祝福树
            List<Goodstable> list = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), new BigDecimal("8188004"));
            Goodstable goodstable = null;
            if(list!=null && list.size()>0) {
                goodstable = list.get(0);
            }else {
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#c00FFFF背包空空如也#4哪来的#R圣水#c00FFFF#24快去找观音姐姐问问!#129"));
                return;
            }
            if(goodstable!=null && goodstable.getUsetime()>0) {
                ConcurrentHashMap<String, Npctable> map = GameServer.getNpcMap();
                Npctable npctable = map.get(99+""+loginResult.getRole_id().toString());
                if(npctable.getNum().equals("1")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W您已经浇过水了，请去除虫吧!"));
                    return;
                }else if(npctable.getNum().equals("2")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W您已经浇过水了，请去施肥吧!"));
                    return;
                }else if(npctable.getNum().equals("3")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W您已经浇过水了!"));
                    return;
                }else if(npctable.getNum().equals("0")) {
                    npctable.setNum("1");
                }

                goodstable.goodxh(1);
                AssetUpdate assetUpdate=new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USEGOOD);
                UsePetAction.useGood(goodstable,1);
                assetUpdate.updata("G"+goodstable.getRgid()+"="+goodstable.getUsetime());
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));

                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#Y正在浇水..."));
                npctable.setSkin("700009");
                updateNpc(npctable,loginResult.getMapid().toString(),ctx);
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#R恭喜你：#W浇灌了#Y吉祥树#W，又长高了不少!"));
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W快去找镇元大仙请教除虫秘方吧!"));
                //浇完水自动领取任务
                addTask(loginResult, 4293);
            }
            return;
        }else if(message.split("=")[0].equals("我要除虫")) {//祝福树
            List<Goodstable> list = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), new BigDecimal("8188005"));
            Goodstable goodstable = null;
            if(list!=null && list.size()>0) {
                goodstable = list.get(0);
            }else {
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W背包里没有除虫的道具!"));
                return;
            }
            if(goodstable!=null && goodstable.getUsetime()>0) {
                ConcurrentHashMap<String, Npctable> map = GameServer.getNpcMap();
                Npctable npctable = map.get(99+""+loginResult.getRole_id().toString());
                if(npctable.getNum().equals("1")) {
                    npctable.setNum("2");
                }else if(npctable.getNum().equals("2")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W您已经除过虫了，请去施肥吧!"));
                    return;
                }else if(npctable.getNum().equals("3")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W您已经除过虫了!"));
                    return;
                }else if(npctable.getNum().equals("0")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W请先浇水再除虫!"));
                    return;
                }
                goodstable.goodxh(1);
                AssetUpdate assetUpdate=new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USEGOOD);
                UsePetAction.useGood(goodstable,1);
                assetUpdate.updata("G"+goodstable.getRgid()+"="+goodstable.getUsetime());
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));

                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#Y正在除虫..."));
                npctable.setSkin("700010");
                updateNpc(npctable,loginResult.getMapid().toString(),ctx);
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#R恭喜你：#W给吉祥树除虫成功，又长高了不少!#28"));
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W快去找太上老君要点仙丹当肥料吧!"));
                addTask(loginResult, 4458);
            }
            return;
        }else if(message.split("=")[0].equals("我要施肥")) {//祝福树
            List<Goodstable> list = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), new BigDecimal("8188006"));
            Goodstable goodstable = null;
            if(list!=null && list.size()>0) {
                goodstable = list.get(0);
            }else {
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W背包里没有施肥的道具!"));
                return;
            }
            if(goodstable!=null && goodstable.getUsetime()>0) {
                ConcurrentHashMap<String, Npctable> map = GameServer.getNpcMap();
                Npctable npctable = map.get(99+""+loginResult.getRole_id().toString());
                if(npctable.getNum().equals("1")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W请先除虫!"));
                    return;
                }else if(npctable.getNum().equals("2")) {
                    npctable.setNum("3");
                }else if(npctable.getNum().equals("3")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W您已经施过肥了!"));
                    return;
                }else if(npctable.getNum().equals("0")) {
                    SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W请先浇水!"));
                    return;
                }
                goodstable.goodxh(1);
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#Y正在施肥..."));
                AssetUpdate assetUpdate=new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USEGOOD);
                UsePetAction.useGood(goodstable,1);
                assetUpdate.updata("G"+goodstable.getRgid()+"="+goodstable.getUsetime());

                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));

                String[] pfId = {"700015","700011","700015","700012","700015","700015"};
                npctable.setSkin(pfId[Battlefield.random.nextInt(6)]);
                updateNpc(npctable,loginResult.getMapid().toString(),ctx);
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#R恭喜你：#W给吉祥树施肥成功，终于长成了参天大树!#29"));
                if(npctable.getSkin().equals("700015")) {
                    //种树奖励设置  普通
                    Dorp dorp=GameServer.getDorp("2510");
                    if (dorp!=null) {
                        DropUtil.getDrop(loginResult,dorp.getDorpValue(),"吉祥树", 22,1D,null);
                    }
                }else {
                    //种树奖励设置 高级
                    Dorp dorp=GameServer.getDorp("2511");
                    if (dorp!=null) {
                        DropUtil.getDrop(loginResult,dorp.getDorpValue(),"吉祥树", 22,1D,null);
                    }
                    NChatBean bean=new NChatBean();
                    bean.setId(5);
                    bean.setMessage("#R恭喜玩家#G【"+loginResult.getRolename()+"】#Y在新年种树活动中种出了千年一遇的，金色摇钱树，大家快去沾沾福气吧！！！");
                    String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    SendMessage.sendMessageToAllRoles(msg);
                    bean.setId(9);
                    bean.setMessage("#R恭喜玩家#G【"+loginResult.getRolename()+"】#Y在新年种树活动中种出了千年一遇的，金色摇钱树，大家快去沾沾福气吧！！！");
                    msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    SendMessage.sendMessageToAllRoles(msg);
                }
            }
            return;
        }else if(message.split("=")[0].equals("我来沾沾福气")) {
            ConcurrentHashMap<String, Npctable> map = GameServer.getNpcMap();
            Npctable npctable = map.get(message.split("=")[1]);
            if(StringUtils.isEmpty(npctable.getRoleIdName())) {
                npctable.setRoleIdName("迪丽热巴滴");
            }
            if(!npctable.getRoleIdName().contains(loginResult.getRole_id().toString()+loginResult.getRolename().toString())) {
                npctable.setRoleIdName(npctable.getRoleIdName()+"|"+loginResult.getRole_id().toString()+loginResult.getRolename().toString());
                map.put(npctable.getNpcid(), npctable);
                GameServer.setNpcMap(map);
                //种树答题奖励设置
                Dorp dorp=GameServer.getDorp("9240");
                if (dorp!=null) {
                    DropUtil.getDrop(loginResult,dorp.getDorpValue(),"吉祥树", 22,1D,null);
                }
            }else {
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#W您已沾过福气了，换一颗树试试吧!"));
            }
            return;
        }else if(message.split("=")[0].equals("八十一难领取")) {
            String goodId = lbidText[Integer.parseInt(message.split("=")[1])-1];
            if(StringUtils.isNotEmpty(goodId)) {
                Dorp dorp= new Dorp();
                dorp.setDorpValue("物品=0&"+goodId+"$1$100");
                if (dorp!=null) {
                    DropUtil.getDrop(loginResult,dorp.getDorpValue(),"八十一难挑战", 22,1D,null);
                }
            }
            AssetUpdate assetUpdate=new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            String msg = "";
            if(StringUtils.isNotEmpty(loginResult.getDifficultrecord())) {
                msg = loginResult.getDifficultrecord();
            }
            loginResult.setDifficultrecord(msg+"|"+message.split("=")[1]);
            assetUpdate.setDifficultrecord(loginResult.getDifficultrecord());
            SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            return;
        }
        int percentIndex = message.indexOf('!');

        // 如果 "%" 符号存在，截取 "%" 符号之前的部分
        if (percentIndex != -1) {
            message = message.substring(0, percentIndex);
        }

        if(message!=null) {
            if(message.contains("刷你麻痹自选")) {
                String goodId = message.split("=")[1].split("\\|")[Integer.parseInt(message.split("=")[2])];
                Goodstable goods = new Goodstable();
                goods.setGoodsid(new BigDecimal(goodId));
                sendZXDJ(loginResult, goods, 1);
                return;
            }
        }


        String[] v2s = message.split("\\|");//////////////////////////




        int path = message.indexOf("|");
        Goodstable goodstable = null;
        if (path == -1) {
            goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(message));
        }
        else {
            goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(message.substring(0, path)));
            message = message.substring(path + 1);
        }
        if (goodstable == null) {
            return;
        }
        if (goodstable.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
            return;
        }
        if ((int)goodstable.getUsetime() <= 0) {
            return;
        }
        long type = goodstable.getType();
        if (type != 2250L) {
            if( aaa==9) {
                System.out.println("number222：" + number222);System.out.println("goodstable.getUsetime()：" + goodstable.getUsetime());
                if(number222>=goodstable.getUsetime()) {

                    number222=goodstable.getUsetime();

                    //	SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                    //	return ;
                }


                goodstable.setUsetime(goodstable.getUsetime()- number222);


            }else {

                goodstable.setUsetime(goodstable.getUsetime()-1);
            }
        }
        if (type == 60001L || type == 60002L) {
            this.Novice(goodstable, ctx, loginResult);
            return;
        }
        if (type == 121L || type == 122L) {
            if (type == 121L) {
                unSilence(goodstable, message, ctx, loginResult);
            }
            else {
                unSeal(goodstable, message, ctx, loginResult);
            }
            return;
        }
        else {//添加记录
            AllServiceUtil.getGoodsrecordService().insert(goodstable, null, 1, 9);
            AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
            if (type == 100L || type == 2126L) {//大话币使用卡
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USERGOOD);
                String[] vs = goodstable.getValue().split("=");
                BigDecimal m = new BigDecimal(vs[1]);
                if(type == 100 && aaa==9) {	//	System.out.println("aaa=999：" + message);
                    BigDecimal num1= new BigDecimal(number222);
                    if(number222>goodstable.getUsetime()) {
                        //	SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                        //	return ;
                    }

                    m = num1.multiply(m);


                }else {
                    m = new BigDecimal(vs[1]);
                }
                assetUpdate.updata("D=" + m.longValue());
                loginResult.setGold(loginResult.getGold().add(m));
                MonitorUtil.getMoney().addD(m.longValue(), 3);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            if (type == 1001L) {//大话币使用卡
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USERGOOD);
                String[] s = goodstable.getValue().split("=")[1].split("&");
                List<RandomNum> randomNums = new ArrayList<>();
                for (String string : s) {
                    String[] i = string.split("\\$");
                    RandomNum randomNum = new RandomNum();
                    randomNum.setProbability(Integer.parseInt(i[0]));
                    String[] n = i[1].split("-");
                    randomNum.setMin(Integer.parseInt(n[0]));
                    randomNum.setMax(Integer.parseInt(n[1]));
                    randomNums.add(randomNum);
                }
                // 根据年龄字段进行排序
                Collections.sort(randomNums, new Comparator<RandomNum>() {
                    @Override
                    public int compare(RandomNum p1, RandomNum p2) {
                        return Integer.compare(p1.getProbability(), p2.getProbability());
                    }
                });
                Boolean b = Boolean.FALSE;
                BigDecimal number = BigDecimal.ZERO;
                for (RandomNum randomNum2 : randomNums) {
                    if (randomNum2.getProbability() > new Random().nextInt(100)) {
                        b = Boolean.valueOf(true);
                        int money = randomNum2.getMin() + new Random().nextInt(randomNum2.getMax() - randomNum2.getMin());
                        number = new BigDecimal(money + "");
                        break;
                    }
                }
                if (number.longValue() == 0L) {
                    int min = ((RandomNum)randomNums.get(randomNums.size() - 1)).getMin();
                    number = new BigDecimal(min + "");
                }
                if (goodstable.getValue().startsWith("金钱")) {
                    loginResult.setGold(loginResult.getGold().add(number));
                    MonitorUtil.getMoney().addD(number.longValue(), 3);
                    assetUpdate.updata("D=" + number.longValue());
                    assetUpdate.setMsg("您获得了#R" + number.toString() + "#Y两银子");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                }
                else if (goodstable.getValue().startsWith("仙玉")) {
                    loginResult.setCodecard(loginResult.getCodecard().add(number));
                    MonitorUtil.getMoney().addX(number.longValue(), 3);
                    assetUpdate.updata("X=" + number.longValue());
                    assetUpdate.setMsg("您获得了#R" + number.toString() + "#Y两银子");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                }
            }
            else if (type == 888L) {
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USERGOOD);
                String[] vs = goodstable.getValue().split("=");
                BigDecimal m = new BigDecimal(vs[1]);
                if( aaa==9) {	//	System.out.println("aaa=999：" + message);
                    BigDecimal num1= new BigDecimal(number222);
                    if(number222>goodstable.getUsetime()) {
                        //	SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                        //return ;
                    }

                    m = num1.multiply(m);


                }else {
                    m = new BigDecimal(vs[1]);
                }
                assetUpdate.updata("X=" + m.longValue());
                loginResult.setCodecard(loginResult.getCodecard().add(m));
                MonitorUtil.getMoney().addX(m.longValue(), 2);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else if (type == 7005L) {
                String[] v = goodstable.getValue().split("\\|");
                String[] v2 = v[0].split("=")[1].split(" ");
                BigDecimal id = new BigDecimal(v2[UseRoleAction.random.nextInt(v2.length)]);
                String js = v[1].split("=")[1];
                Goodstable good = GameServer.getGood(id);
                List<String> xianqis = (List<String>)GameServer.getXianqiTypeValue().get(id + "|" + js);
                int[] a = randomArray(0, xianqis.size() - 1, 1);
                good.setValue((String)xianqis.get(a[0]));
                good.setRole_id(loginResult.getRole_id());
                good.setQuality(goodstable.getQuality());
                AllServiceUtil.getGoodsTableService().insertGoods(good);
                AddGoodUtil.addGood(ctx, good);
                AllServiceUtil.getGoodsrecordService().insert(good, null, good.getUsetime(), Integer.valueOf(3));
            }
            else if (type == 2041L) {
                if(aaa==9) {		System.out.println("aaa=999：" + message);

                    if(number222>goodstable.getUsetime()) {
                        //	SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                        //	return ;
                    }

                    DropUtil.getDrop(loginResult, goodstable.getValue(),null,22,1D*number222,null);

                }else {
                    DropUtil.getDrop(loginResult, goodstable.getValue(),null,22,1D,null);
                }

//                DropUtil.getDrop(loginResult, goodstable.getValue(), null, 22, 1.0, null);
            }
            else if (type == 502L) {
                this.PetCard(goodstable, ctx, loginResult);
            }
            else if (type == 717L) {
                this.MountCard(goodstable, ctx, loginResult);
            }
            else if (type == 7171L) {
                this.CarCard(goodstable, ctx, loginResult);
            }
            else if (type == 996L) {
                RoleData data = RolePool.getLineRoleData(loginResult.getRole_id());
                loginResult.addEquipments(data);
            }
            else if (type == 2235L) {
                this.FlyCard(goodstable, ctx, loginResult);
            }
            else if (type == 66666L) {
                UseMixdeal.divineWalk(goodstable, ctx, loginResult);
            }
            else if (type == 882L) {
                String[] v = goodstable.getValue().split("=");
                String name = v[1];
                ColorScheme colorScheme = null;
                if (name.equals("随机")) {
                    colorScheme = (ColorScheme)GameServer.getAllListColor().get(new Random().nextInt(GameServer.getAllListColor().size()));
                }
                else {
                    colorScheme = GameServer.getColor(name);
                }
                BigDecimal scoretype = loginResult.getScoretype("光武颜色=");
                if (scoretype.intValue() == 0) {
                    loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "光武颜色=1", 2));
                }
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "光武颜色=" + colorScheme.getId(), 1));
                AssetUpdate assetUpdate2 = new AssetUpdate();
                assetUpdate2.setType(AssetUpdate.USEGOOD);
                assetUpdate2.setMsg("幻色成功#R" + colorScheme.getName());
                assetUpdate2.updata("光武颜色=" + colorScheme.getId());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
            }
            else if (type == 118L || type == 1181L) {
                Dorp dorp = GameServer.getDorp(goodstable.getValue().split("=")[1]);
                if (dorp == null) {
                    return;
                }
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), goodstable.getGoodsname(), 22, 1.0, null);
            }
            else if (type == 2051L || type == 2052L || type == 2057L || type == 1007L) {
                Dorp dorp = GameServer.getDorp(type + "");
                if (dorp == null) {
                    return;
                }
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), goodstable.getGoodsname(), 22, 1.0, null);
            }
            else if (type == 2525L) {
                this.ChallengeCard(goodstable, ctx, loginResult);
            }
            else if (Goodtype.TimingGood(type)) {
                UseMixdeal.TimingGood(goodstable, ctx, loginResult);
            }
            else if (Goodtype.Medicine(type)) {
                UseMixdeal.Medicine(goodstable, ctx, loginResult);
            }
            else if (Goodtype.BlueBack(type)) {
                UseMixdeal.BlueBack(goodstable, ctx, loginResult);
            }
            else if (type == 112L) {
                UseMixdeal.baseCard(goodstable, ctx, loginResult);
            }
            else if (type == 113L || type == 88L || type == 99L || type == 111L) {
                UseMixdeal.qtCard(goodstable, ctx, loginResult);
            }
            else if (type == 1006L) {
                hz(goodstable, ctx, loginResult);
            }
            else if (type == 6699L) {
                this.uscExpandCard(goodstable, ctx, loginResult);
            }
            else if (type == 66668L || type == 66669L) {
                UseMixdeal.vipSss(goodstable, ctx, loginResult);
            }
            else if (type == 66778L || type == 66779L) {
                UseMixdeal.vipJjj(goodstable, ctx, loginResult);
            }
            else if (type == 924L) {
                UseMixdeal.Potion(goodstable, ctx, loginResult);
            }
            else if (type == 9002L) {
                if (loginResult.getFmsld() == null) {
                    loginResult.setFmsld(Integer.valueOf(0));
                }
                if ((int)loginResult.getFmsld() > 10000) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("法门熟练度已满"));
                    return;
                }
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USERGOOD);
                loginResult.setFmsld(Integer.valueOf((int)loginResult.getFmsld() + 20));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else if (type == 2124L) {
                Dorp dorp = GameServer.getDorp(goodstable.getValue().split("=")[1]);
                if (dorp == null) {
                    return;
                }
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), goodstable.getGoodsname(), 22, 1.0, null);
            }
            else if (type == 2288L) {
                String[] v = goodstable.getValue().split("\\|");
                Npctable copyNpc = null;
                if (v.length == 1) {
                    Npctable npc = GameServer.getNpc(v[0]);
                    npc.setMap(loginResult.getMapid().intValue());
                    npc.setTx(loginResult.getX().toString());
                    npc.setTy(loginResult.getY().toString());
                    copyNpc = (Npctable)GsonUtil.getGsonUtil().getgson().fromJson(GsonUtil.getGsonUtil().getgson().toJson(npc), Npctable.class);
                    copyNpc.setExTime(Long.valueOf(System.currentTimeMillis()));
                }
                else if (v.length > 1) {
                    Npctable npc = GameServer.getNpc(v[new Random().nextInt(v.length)]);
                    copyNpc = (Npctable)GsonUtil.getGsonUtil().getgson().fromJson(GsonUtil.getGsonUtil().getgson().toJson(npc), Npctable.class);
                    npc.setMap(loginResult.getMapid().intValue());
                    npc.setTx(loginResult.getX().toString());
                    npc.setTy(loginResult.getY().toString());
                    copyNpc.setExTime(Long.valueOf(System.currentTimeMillis()));
                }
                String[] teams = loginResult.getTeam().split("\\|");
                LoginResult[] logs = new LoginResult[teams.length];
                for (int j = 0; j < teams.length; ++j) {
                    LoginResult login = null;
                    if (j == 0) {
                        login = loginResult;
                    }
                    else {
                        ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[j]);
                        if (ctx2 != null) {
                            login = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
                        }
                    }
                    if (login == null) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(teams[j] + "处于异常状态"));
                        return;
                    }
                    logs[j] = login;
                }
                for (LoginResult log : logs) {
                    ConcurrentHashMap<String, List<Npctable>> stringNpctableConcurrentHashMap = (ConcurrentHashMap<String, List<Npctable>>)UseRoleAction.zhNpc.get(log.getRole_id());
                    if (stringNpctableConcurrentHashMap == null) {
                        stringNpctableConcurrentHashMap = new ConcurrentHashMap<>();
                        UseRoleAction.zhNpc.put(log.getRole_id(), stringNpctableConcurrentHashMap);
                    }
                    List<Npctable> npctables = (List<Npctable>)stringNpctableConcurrentHashMap.get(copyNpc.getMap() + "");
                    if (npctables == null) {
                        npctables = new ArrayList<>();
                    }
                    npctables.add(copyNpc);
                    stringNpctableConcurrentHashMap.put(log.getMapid() + "", npctables);
                    ChannelHandlerContext ctx3 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(log.getRolename());
                    if (ctx3 != null) {
                        AllNpcBean txtNpc1 = ReadMapUtil.createTxtNpc1(copyNpc);
                        String json = GsonUtil.getGsonUtil().getgson().toJson(txtNpc1);
                        SendMessage.sendMessageByRoleName(log.getRolename(), Agreement.getAgreement().addTimeNpcAgreement(json));
                    }
                }
            }
            else if (type == 935L) {
                LoginResult login2 = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
                AssetUpdate assetUpdate3 = new AssetUpdate();
                assetUpdate3.setType(AssetUpdate.USERGOOD);
                String[] vs2 = goodstable.getValue().split("=");
                BigDecimal addC = new BigDecimal(vs2[1]);
                assetUpdate3.updata("C=" + addC.longValue());
                assert login2 != null;
                login2.setPaysum(login2.getPaysum().add(addC));
                login2.setMoney(Integer.valueOf(((login2.getMoney() != null) ? ((int)login2.getMoney()) : 0) + addC.intValue()));
                MonitorUtil.getMoney().addC((long)addC.intValue());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
            }
            else if (type == 936L) {
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USERGOOD);
                String[] vs = goodstable.getValue().split("=");
                BigDecimal m = new BigDecimal(vs[1]);
                assetUpdate.updata("S=" + m.longValue());
                assetUpdate.updata("G" + goodstable.getRgid() + "=" + goodstable.getUsetime());
                assetUpdate.setMsg("使用获得" + m.longValue() + "师贡");
                loginResult.setSavegold(loginResult.getSavegold().add(new BigDecimal(m.longValue())));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else if (type == 999L) {
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USERGOOD);
                String[] vs = goodstable.getValue().split("=");
                BigDecimal addZ = new BigDecimal(vs[1]);
                assetUpdate.updata("Z=" + addZ.longValue());
                assetUpdate.setMsg("使用获得" + addZ + "转区币");
                loginResult.setTransfergold(loginResult.getTransfergold().add(addZ));
                MonitorUtil.getMoney().addZ(addZ.longValue());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else if (type == 951L) {
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USERGOOD);
                String[] vs = goodstable.getValue().split("=");
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
                BigDecimal m = new BigDecimal(vs[1]);
                assetUpdate.setType(AssetUpdate.USEGOOD);
                assetUpdate.updata(vs[0] + "=" + m.longValue());
                assetUpdate.updata("G" + goodstable.getRgid() + "=" + goodstable.getUsetime());
                assetUpdate.setMsg("使用获得" + m + "积分");
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), vs[0] + "=" + vs[1], 2));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else if (type == 2120L) {
                ConcurrentHashMap<Integer, Configure> s2 = GameServer.getAllConfigure();
                Configure configure = (Configure)s2.get(Integer.valueOf(1));
                if (loginResult.getGradeincrease() != null && (int)loginResult.getGradeincrease() >= Integer.parseInt(configure.getRwdjsx())) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("等级突破丹最大可服用#G" + configure.getRwdjsx() + "#Y次！#R您已达到上限！"));
                    return;
                }
                if (loginResult.getGradeincrease() == null) {
                    loginResult.setGradeincrease(Integer.valueOf(0));
                }
                loginResult.setGradeincrease(Integer.valueOf((int)loginResult.getGradeincrease() + 1));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你使用了【等级突破丹】等级上限#G+1"));
            }
            else if (type == 2121L) {
                dkRecharge(goodstable, message, ctx, loginResult);
            }
            else if (type == 2233L) {
                addExtPoint(goodstable, message, ctx, loginResult);
            }
            else if (type == 2250L) {
                getLiangHao(goodstable, message, ctx, loginResult);
            }
            else if (type == 2251L) {
                getLottery(goodstable, message, ctx, loginResult);
            }
            else if (type == 2254L) {
                getLotterytwo(goodstable, message, ctx, loginResult);
            }
            else if (type == 2255L) {
                int r = Battlefield.random.nextInt(20) + 5;
                loginResult.setShouhu(loginResult.getShouhu() + r);
                AssetUpdate assetUpdate3 = new AssetUpdate();
                assetUpdate3.setType(AssetUpdate.SHOUHU);
                assetUpdate3.setMsg("#Y分解获得守护之尘+" + r + "#53");
                assetUpdate3.setData(r + "");
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
                UsePetAction.useGood(goodstable, 1);
            }
            else if (type == 60027) {
                String[] vs = goodstable.getValue().split("=");
                if(StringUtils.isNotEmpty(vs[1])) {
                    Dorp dorp=GameServer.getDorp(vs[1]);
                    if (dorp!=null) {
                        DropUtil.getDrop(loginResult,dorp.getDorpValue(),"烟花爆竹", 22,1D,null);
                    }
                }
                AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                assetUpdate.updata("TYHBZHWXY ("+Battlefield.random.nextInt(36)+")");
                SendMessage.sendMessageToMapRoles(loginResult.getMapid(),Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));


                return;
            }
            else if (type == 60028) {
                //种树 祝福树
                Long mapId = loginResult.getMapid();
                Long X = loginResult.getX();
                Long Y = loginResult.getY();
                Npctable npctable = new Npctable();
                npctable.setNpcid(99+""+loginResult.getRole_id().toString());
                npctable.setDir("1");
                npctable.setRoleId(loginResult.getRole_id().toString());
                npctable.setNum("0");
                npctable.setSkin("700013");
                npctable.setTx(X+"");
                npctable.setTy(Y+"");
                npctable.setNpctype("99129");
                npctable.setNpcname(loginResult.getRolename()+"的祝福树");
                SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("在小小的花园里面挖呀挖呀挖...#159"));
                addNpc(npctable,mapId.toString(),ctx);
                //种完树自动领取任务
                addTask(loginResult, 4290);
            }
            else if (type == 2260L) {
                getZxpack(goodstable, message, ctx, loginResult);
            }
            else if (type == 2261L || type == 2262L) {
                if (loginResult.getLowOrHihtpack() != 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已开通礼包，无法再次开通！"));
                    return;
                }
                this.addLowOrHihtpack(ctx, loginResult, (int)type - 2260);
            }
            return;
        }
    }
    
    private void addLowOrHihtpack(ChannelHandlerContext ctx, LoginResult loginResult, int lvl) {
        loginResult.setLowOrHihtpack(lvl);
        ApplyPayBean applyPayBean = new ApplyPayBean();
        applyPayBean.setAddM(BigDecimal.valueOf(0L));
        applyPayBean.setLowOrHihtpack(new BigDecimal(lvl));
        applyPayBean.setMsg((lvl == 1) ? "开通了小资冲级礼包" : "开通了土豪冲级礼包");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().applyPay(GsonUtil.getGsonUtil().getgson().toJson(applyPayBean)));
    }
    
    public static void getZxpack(Goodstable good, String msg, ChannelHandlerContext ctx, LoginResult login) {
        ZxpackNineAction.valuse = good.getValue();
        String[] v = good.getValue().split("\\|");
        List<Goodstable> result = new ArrayList<>();
        for (String s : v) {
            Goodstable goodstable = GameServer.getgoods(new BigDecimal(s));
            if (goodstable == null) {
                System.out.println("ID:" + s + "在item不存在");
            }
            else {
                result.add(goodstable);
            }
        }
        ZxpackBean zxpackbean = new ZxpackBean();
        zxpackbean.setList(result);
        String returnmsg = Agreement.getAgreement().getZxpackAgreement("GETZX=" + GsonUtil.getGsonUtil().getgson().toJson(zxpackbean));
        SendMessage.sendMessageToSlef(ctx, returnmsg);
    }
    
    public static void getLottery(Goodstable good, String msg, ChannelHandlerContext ctx, LoginResult login) {
        List<LotteryItemBase> lotterytimes = GameServer.getLotteryitems();
        List<LotteryItemBase> temps = new ArrayList(lotterytimes);
        List<Goodstable> result = new ArrayList<>();
        Random random = new Random();
        while (result.size() < 6) {
            int idx = random.nextInt(temps.size() - 1);
            if (temps.get(idx) != null && temps.get(idx).getId() != null && random.nextInt(100) <= ((LotteryItemBase)temps.get(idx)).getRate()) {
                Goodstable goodstable = GameServer.getgoods(temps.get(idx).getId());
                goodstable.setQuality((long) temps.get(idx).getQuality());
                goodstable.setValue("");
                result.add(goodstable);
                temps.remove(idx);
            }
        }
        UseRoleAction.lottery.put(ctx, result);
        LotteryBean lotterybean = new LotteryBean();
        lotterybean.setList(result);
        String returnmsg = Agreement.getAgreement().getLotteryAgreement("GETITEMS|" + GsonUtil.getGsonUtil().getgson().toJson(lotterybean));
        SendMessage.sendMessageToSlef(ctx, returnmsg);
    }
    public void addNpc(Npctable npctable,String mapId,ChannelHandlerContext ctx) {
        ConcurrentHashMap<String, Npctable> map = GameServer.getNpcMap();
        map.remove(npctable.getNpcid());
        map.put(npctable.getNpcid(), npctable);
        GameServer.setNpcMap(map);
        String msg= ReadMapUtil.createTxtNpc(map);//生成txt
        ReadPoolUtil.text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "")+"GetTXT\\npc.txt", msg);
        SendMessage.sendMessageToAllRolesUpdateText(Agreement.getAgreement().updateTxtAgreement("npc.txt"+"="+"种树进度设置="+npctable.getNpcid()+"="+npctable.getNum()+"="+npctable.getSkin()));
        try {
            Thread.sleep(1000);
            ConcurrentHashMap<String, Gamemap> allMapBean = GameServer.getGameMap();
            Gamemap gamemap = allMapBean.get(mapId);
            gamemap.setMapnpc(gamemap.getMapnpc()+"|"+npctable.getNpcid());
            allMapBean.put(mapId, gamemap);
            msg=ReadMapUtil.createTxtMap(allMapBean);//生成txt
            ReadPoolUtil.text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "")+ "GetTXT\\map.txt", msg);
            SendMessage.sendMessageToAllRolesUpdateText(Agreement.getAgreement().updateTxtAgreement("map.txt"));
            SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().PromptAgreement("#R恭喜你：#W种下了#Y吉祥树#W的小树苗，记得来浇水哦!"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }
    public void updateNpc(Npctable npctable,String mapId,ChannelHandlerContext ctx) {
        ConcurrentHashMap<String, Npctable> map = GameServer.getNpcMap();
        map.remove(npctable.getNpcid());
        System.err.println("浇水=="+npctable.getNum());
        map.put(npctable.getNpcid(), npctable);
        GameServer.setNpcMap(map);
        String msg = ReadMapUtil.createTxtNpc(map);//生成txt
        ReadPoolUtil.text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "")+"GetTXT\\npc.txt", msg);
        SendMessage.sendMessageToAllRolesUpdateText(Agreement.getAgreement().updateTxtAgreement("npc.txt"+"="+"种树进度设置="+npctable.getNpcid()+"="+npctable.getNum()+"="+npctable.getSkin()));

        try {
            Thread.sleep(1000);
            ConcurrentHashMap<String, Gamemap> allMapBean = GameServer.getGameMap();
            msg=ReadMapUtil.createTxtMap(allMapBean);//生成txt
            ReadPoolUtil.text(GameServer.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "")+ "GetTXT\\map.txt", msg);
            SendMessage.sendMessageToAllRolesUpdateText(Agreement.getAgreement().updateTxtAgreement("map.txt"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return;
    }
    
    public static void getLotterytwo(Goodstable good, String msg, ChannelHandlerContext ctx, LoginResult login) {
        List<LotteryItemBasetwo> lotterytimestwo = GameServer.getLotteryitemstwo();
        List<LotteryItemBasetwo> temps = new ArrayList(lotterytimestwo);
        List<Goodstable> result = new ArrayList<>();
        Random random = new Random();
        while (result.size() < 6) {
            int idx = random.nextInt(temps.size() - 1);
            if (temps.get(idx) != null && temps.get(idx).getId() != null && random.nextInt(100) <= ((LotteryItemBasetwo)temps.get(idx)).getRate()) {
                Goodstable goodstable = GameServer.getgoods(temps.get(idx).getId());
                goodstable.setQuality((long) temps.get(idx).getQuality());
                goodstable.setValue("");
                result.add(goodstable);
                temps.remove(idx);
            }
        }
        LotteryBean lotterybean = new LotteryBean();
        lotterybean.setList(result);
        UseRoleAction.lottery1.put(ctx, result);
        String returnmsg = Agreement.getAgreement().getLotteryAgreement("GETITEMSTWO|" + GsonUtil.getGsonUtil().getgson().toJson(lotterybean));
        SendMessage.sendMessageToSlef(ctx, returnmsg);
    }
    
    public static Set<Integer> generateRandomNumbers(int n, int max) {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < n) {
            int num = random.nextInt(max) + 1;
            set.add(Integer.valueOf(num));
        }
        return set;
    }

    public static boolean sendZXDJ(LoginResult loginResult,Goodstable goodstable,int num){
        String ab = ":25|"+goodstable.getGoodsid()+"|1|#AAFFFO";
        ChannelHandlerContext ctx=GameServer.getRoleNameMap().get(loginResult.getRolename());
        XXGDBean bean=new XXGDBean();
        bean.setId(goodstable.getGoodsid()+"");
        bean.setSum(1);
        RoleData roleData=RolePool.getRoleData(loginResult.getRole_id());
        AssetUpdate assetUpdate=new AssetUpdate(25);
        BigDecimal id=new BigDecimal(bean.getId());
        goodstable = GameServer.getGood(id);
        AddGoodAction.addGood(assetUpdate,goodstable,loginResult,roleData,bean,assetUpdate.getType());
        SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        String msg="dh:"+ab+(loginResult!=null?loginResult.getRole_id():null);
        WriteOut.addtxt(msg,9999);
        return true;
    }
    public static void addTask(LoginResult loginResult,int taskId) {
        TaskData taskData = GameServer.getTaskData(taskId);
        ExpUtil.addTaskCw(taskData, loginResult);
    }
    public static void getLiangHao(Goodstable good, String msg, ChannelHandlerContext ctx, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (good != null) {
            String[] valus = good.getValue().split("=");
            if (valus.length >= 2) {
                String value = valus[1];
                if (value.equals("随机")) {
                    List<LiangHaoBase> nlhs = new ArrayList<>();
                    List<LiangHaoBase> lhs = GameServer.getLianghaos();
                    List<String> clhs = AllServiceUtil.getRoleTableService().allLiangHao();
                    if (lhs != null && lhs.size() > 0) {
                        for (LiangHaoBase item : lhs) {
                            if (!clhs.contains(item.getLianghao())) {
                                nlhs.add(item);
                            }
                        }
                    }
                    if (nlhs != null && nlhs.size() > 0) {
                        Random random = new Random();
                        int idx = random.nextInt(nlhs.size());
                        LiangHaoBase myLiangHao = (LiangHaoBase)nlhs.get(idx);
                        RoleTable roleTable = new RoleTable();
                        roleTable.setLiangHao(myLiangHao.getLianghao());
                        roleTable.setRole_id(login.getRole_id());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(6, 30);
                        Date nextMonth = calendar.getTime();
                        String expireStr = sdf.format(nextMonth);
                        roleTable.setLianghaoexpire(expireStr);
                        roleTable.setContinueprice(Integer.valueOf(6));
                        if (login.getLianghaotype() == null) {
                            roleTable.setLianghaotype(Integer.valueOf(5));
                        }
                        else {
                            roleTable.setLianghaotype(login.getLianghaotype());
                        }
                        AllServiceUtil.getRoleTableService().updateRoleLiangHao(roleTable);
                        UsePetAction.useGood(good, 1);
                        LiangHaoInfo liangHaoInfo = new LiangHaoInfo();
                        liangHaoInfo.setLianghao(roleTable.getLiangHao());
                        liangHaoInfo.setType((int)roleTable.getLianghaotype());
                        liangHaoInfo.setExpTime(roleTable.getLianghaoexpire());
                        assetUpdate.updata("LH=" + GsonUtil.getGsonUtil().getgson().toJson(liangHaoInfo));
                        assetUpdate.setMsg("你使用了" + good.getGoodsname() + "，获得了靓号：" + roleTable.getLiangHao());
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                    else {
                        assetUpdate.setMsg("此靓号已经被其他人占用，无法获得！！！");
                        assetUpdate.updata("LH=");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                }
                else {
                    RoleTable roleTable2 = new RoleTable();
                    roleTable2.setLiangHao(value);
                    List<RoleTable> rlist = AllServiceUtil.getRoleTableService().getRoleTaleByLiangHao(roleTable2);
                    if (rlist != null && rlist.size() > 0) {
                        assetUpdate.setMsg("此靓号已经被其他人占用，无法获得！！！");
                        assetUpdate.updata("LH=");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                    else {
                        roleTable2.setRole_id(login.getRole_id());
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyy-MM-dd");
                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.add(6, 30);
                        Date nextMonth2 = calendar2.getTime();
                        String expireStr2 = sdf2.format(nextMonth2);
                        roleTable2.setLianghaoexpire(expireStr2);
                        roleTable2.setContinueprice(Integer.valueOf(6));
                        if (login.getLianghaotype() == null) {
                            roleTable2.setLianghaotype(Integer.valueOf(5));
                        }
                        else {
                            roleTable2.setLianghaotype(login.getLianghaotype());
                        }
                        AllServiceUtil.getRoleTableService().updateRoleLiangHao(roleTable2);
                        UsePetAction.useGood(good, 1);
                        assetUpdate.setMsg("你使用了" + good.getGoodsname() + "，获得了靓号：" + roleTable2.getLiangHao());
                        LiangHaoInfo liangHaoInfo2 = new LiangHaoInfo();
                        liangHaoInfo2.setLianghao(roleTable2.getLiangHao());
                        liangHaoInfo2.setType((int)roleTable2.getLianghaotype());
                        liangHaoInfo2.setExpTime(roleTable2.getLianghaoexpire());
                        assetUpdate.updata("LH=" + GsonUtil.getGsonUtil().getgson().toJson(liangHaoInfo2));
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                }
            }
            else {
                return;
            }
        }
    }
    
    public static void addExtPoint(Goodstable good, String msg, ChannelHandlerContext ctx, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (good != null && good.getValue() != null) {
            String[] valus = good.getValue().split("=");
            if (valus.length >= 2) {
                int value = (int)Integer.valueOf(valus[1]);
                RoleTable roleTable = new RoleTable();
                roleTable.setRole_id(login.getRole_id());
                roleTable.setExtPoint(BigDecimal.valueOf((long)value));
                assetUpdate.updata("EP=" + BigDecimal.valueOf((long)value));
                BigDecimal nextPoint = (login.getExtPoint() != null) ? login.getExtPoint().add(roleTable.getExtPoint()) : roleTable.getExtPoint();
                login.setExtPoint(nextPoint);
                AllServiceUtil.getRoleTableService().updateRoleExtPoint(roleTable);
                assetUpdate.setMsg("你使用了" + good.getGoodsname() + "，获得了" + value + "点属性");
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else {
                return;
            }
        }
    }
    
    public static void unSeal(Goodstable good, String msg, ChannelHandlerContext ctx, LoginResult login) {
        if (!MonitorLimit.unSeal(msg, "物品:" + good.getRgid())) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("账号:" + msg + "没有被封号,不需要使用该道具"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setMsg("你已帮账号:" + msg + "解除封号状态");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void dkRecharge(Goodstable good, String msg, ChannelHandlerContext ctx, LoginResult login) {
        String num = good.getValue().split("\\|")[0].split("=")[1];
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleID(new BigDecimal(msg));
        roleInfo.setGameTimeRemaining(Integer.valueOf((int)roleInfo.getGameTimeRemaining() + Integer.parseInt(num)));
        AllServiceUtil.getRoleTableService().updateRoleWhenExit(roleInfo);
        assetUpdate.setMsg("你已帮#GID:【" + msg + "】名字：【" + roleInfo.getRolename() + "】#Y充值#G" + num + "#Y游戏点");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void unSilence(Goodstable good, String msg, ChannelHandlerContext ctx, LoginResult login) {
        if (!msg.matches("[0-9]+")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("请输入数字"));
            return;
        }
        if (!MonitorLimit.unSilence(new BigDecimal(msg), "物品:" + good.getRgid())) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("玩家id:" + msg + "没有被禁言,不需要使用该道具"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        UsePetAction.useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setMsg("你已帮玩家ID:" + msg + "解除禁言");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void hz(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        login.setSkill_id(Integer.valueOf(Integer.parseInt(good.getValue())));
        SendMessage.sendMessageToMapRoles(login.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(login.getRoleShow())));
    }
    
    public void PetCard(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        RoleSummoning pet = GameServer.getPet(new BigDecimal(good.getValue()));
        if (pet == null) {
            return;
        }
        pet.setBasishp(pet.getHp());
        pet.setBasismp(pet.getMp());
        pet.setFaithful(Integer.valueOf(100));
        pet.setGrade(Integer.valueOf(0));
        pet.setTurnRount(0);
        pet.setBone(Integer.valueOf(0));
        pet.setSpir(Integer.valueOf(0));
        pet.setPower(Integer.valueOf(0));
        pet.setSpeed(Integer.valueOf(0));
        pet.setCalm(Integer.valueOf(0));
        pet.setDragon(0);
        pet.setSpdragon(0);
        pet.setAlchemynum(0);
        pet.setExp(new BigDecimal(0));
        pet.setOpenSeal(Integer.valueOf(1));
        pet.setRoleid(login.getRole_id());
        Long c = Long.valueOf(new Date().getTime());
        c = Long.valueOf((long)c + Long.parseLong(Integer.parseInt(pet.getSurplusTimes()) * 60 + "000"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date((long)c);
        String res = simpleDateFormat.format(date);
        pet.setSurplusTimes(res);
        if (pet.getSsn() == null || pet.getSsn().equals("0")) {}
        String yb = pet.getResistance();
        if (yb == null || yb.equals("")) {
            int p;
            int p2;
            for (p = UseRoleAction.random.nextInt(SummonPetAction.kxs.length), p2 = UseRoleAction.random.nextInt(SummonPetAction.kxs.length); p2 == p; p2 = UseRoleAction.random.nextInt(SummonPetAction.kxs.length)) {}
            pet.setResistance(SummonPetAction.kxs[p] + "|" + SummonPetAction.kxs[p2]);
        }
        AllServiceUtil.getRoleSummoningService().insertRoleSummoning(pet);
        if (pet.getQuality() != null && pet.getQuality().equals("1")) {
            pet.setTurnRount(BattleMixDeal.petTurnRount((int)login.getGrade()));
            pet.setGrade(login.getGrade());
            int g = BattleMixDeal.petLvlint((int)login.getGrade());
            pet.setBone(pet.getBone() + g);
            pet.setSpir(pet.getSpir() + g);
            pet.setPower(pet.getPower() + g);
            pet.setSpeed(pet.getSpeed() + g);
            pet.setBasishp(pet.getHp());
            pet.setBasismp(pet.getMp());
            pet.setFriendliness(Long.valueOf(20000000L));
            if (pet.getTurnRount() > 0) {
                String cz = "0." + pet.getTurnRount();
                pet.setGrowlevel(Double.parseDouble(pet.getGrowlevel()) + Double.parseDouble(cz) + "");
            }
            if (pet.getTurnRount() >= 4) {
                pet.setCalm(Integer.valueOf((int)pet.getCalm() + (int)login.getRoleShow().getGrade()));
            }
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USERGOOD);
        assetUpdate.setPet(pet);
        assetUpdate.updata("P" + good.getRgid() + "=" + good.getUsetime());
        if (pet.getQuality() != null) {
            if (pet.getQuality().equals("1")) {
                assetUpdate.setMsg("您获得了一只【" + pet.getSummoningname() + "】！");
            }
            else {
                assetUpdate.setMsg("您获得了一只" + pet.getSummoningname() + "！");
            }
            if(pet.getSsn().equals("2")||pet.getSsn().equals("3")||pet.getSsn().equals("4")) {
                AchievemUtil.detectionAchievem(login, "神兽");
        }
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    /**坐骑卡使用*/
    public void MountCard(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int lvl = Integer.parseInt(good.getValue()) / 100;
        Mount mount = GameServer.getMount(Sepcies_MixDeal.getRace(login.getSpecies_id()), lvl);
        if (mount != null) {//获取这只坐骑
            List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(login.getRole_id());
            if (mounts != null) {
                for (int i = mounts.size() - 1; i >= 0; --i) {
                    Mount mount2 = mounts.get(i);
                    if (mount2.getMountid() == lvl) {
                        SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().PromptAgreement("#R你已经拥有" + intToChineseCapital(lvl) + "阶坐骑！"));
                        return;
                    }
                }
            }
            mount.setRoleid(login.getRole_id());
            AllServiceUtil.getMountService().insertMount(mount);
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USERGOOD);
            assetUpdate.setMount(mount);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            if(mount.getMountid() == 1) {
                AchievemUtil.detectionAchievem(login, "获得一坐骑");
            }else if(mount.getMountid() == 2) {
                AchievemUtil.detectionAchievem(login, "获得二坐骑");
            }else if(mount.getMountid() == 3) {
                AchievemUtil.detectionAchievem(login, "获得三坐骑");
            }else if(mount.getMountid() == 4) {
                AchievemUtil.detectionAchievem(login, "获得四坐骑");
            }else if(mount.getMountid() == 5) {
                AchievemUtil.detectionAchievem(login, "获得五坐骑");
            }else if(mount.getMountid() == 6) {
                AchievemUtil.detectionAchievem(login, "获得六坐骑");
            }
        }
    }
    /**坐驾卡使用*/
    public void CarCard(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int lvl = Integer.parseInt(good.getValue()) / 100;
        Car car = GameServer.getCar(login.getSpecies_id().intValue(), lvl);
        if (car != null) {//获取这只坐骑
            List<Car> cars = AllServiceUtil.getCarService().selectMountsByRoleID(login.getRole_id());
            if (cars != null) {
                for (int i = cars.size() - 1; i >= 0; --i) {
                    Car car2 = cars.get(i);
                    if (car2.getMountid() == lvl) {
                        SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().PromptAgreement("#R你已经拥有" + intToChineseCapital(lvl) + "阶座驾！"));
                        return;
                    }
                }
            }
            car.setRoleid(login.getRole_id());
            AllServiceUtil.getCarService().insertMount(car);
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USERGOOD);
            assetUpdate.setCar(car);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    public static String intToChineseCapital(int lvl) {
        if (lvl < 0 || lvl > 9) {
            throw new IllegalArgumentException("输入的数字必须在0-9之间");
        }
        return UseRoleAction.NUMS[lvl];
    }
    
    public void FlyCard(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        String[] vs = good.getValue().split("\\=");
        String name = vs[0];
        int id = 0;
        if (name.equals("香叶扇")) {
            id = 1;
        }
        if (name.equals("富贵锦")) {
            id = 2;
        }
        if (name.equals("奔云燕")) {
            id = 3;
        }
        if (name.equals("净心荷")) {
            id = 4;
        }
        if (name.equals("轻鸿羽")) {
            id = 5;
        }
        if (name.equals("筋斗云")) {
            id = 6;
        }
        Fly fly = GameServer.getFly(Sepcies_MixDeal.getRace(login.getSpecies_id()), id);
        if (name != null) {
            fly.setSkin(Integer.valueOf(vs[1]));
            fly.setRoleid(login.getRole_id());
            fly.setFlyname(name);
            fly.setExp(Integer.valueOf(0));
            fly.setFlystate(Integer.valueOf(1));
            AllServiceUtil.getFlyService().insertFly(fly);
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USERGOOD);
            assetUpdate.setFly(fly);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            AchievemUtil.detectionAchievem(login, "拥有一件飞行器");
        }
    }
    
    public void ChallengeCard(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        String robotID = good.getValue().split("=")[1];
        FightingForesee fightingForesee = new FightingForesee();
        fightingForesee.setRobotid(robotID);
        fightingForesee.setYidui(login.getTeam());
        fightingForesee.setType(1);
        BattleThreadPool.addBattle(ctx, fightingForesee);
    }

    public void uscExpandCard(Goodstable goodstable, ChannelHandlerContext ctx, LoginResult loginResult) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        roleData.upPackNum(goodstable, ctx, loginResult);
    }
    
    public void Novice(Goodstable goodstable, ChannelHandlerContext ctx, LoginResult loginResult) {
        AssetUpdate assetUpdate = null;
        try {
            RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
            boolean is = false;
            boolean isNovice = false;
            if (goodstable.getType() == 60001L) {
                is = true;
            }
            String[] v = goodstable.getValue().split("\\|");
            int i = 0;
            while (i < v.length) {
                if (v[i].startsWith("物品")) {
                    v = v[i].split("=")[1].split("\\&");
                    for (int j = 0; j < v.length; ++j) {
                        String[] v2 = v[j].split("\\$");
                        BigDecimal id = new BigDecimal(v2[0]);
                        int sum = Integer.parseInt(v2[1]);
                        Goodstable good = GameServer.getGood(id);
                        if (good != null) {
                            good.setRole_id(loginResult.getRole_id());
                            if ((good.getType() == 60001L || good.getType() == 60002L) && !isNovice) {
                                isNovice = true;
                                goodstable.setGoodsname(good.getGoodsname());
                                goodstable.setUsetime(Integer.valueOf(1));
                                goodstable.setValue(good.getValue());
                                goodstable.setType(good.getType());
                                goodstable.setInstruction(good.getInstruction());
                                if (assetUpdate == null) {
                                    assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                                }
                                assetUpdate.setGood(goodstable);
                            }
                            else {
                                long sid = goodstable.getGoodsid().longValue();
                                if ((sid >= 70001L && sid <= 70030L) || (sid >= 69001L && sid <= 69015L) || goodstable.getType() == 825L || goodstable.getType() == -1L) {
                                    if (assetUpdate == null) {
                                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                                    }
                                    XXGDBean xxgdBean = new XXGDBean();
                                    xxgdBean.setId(id.toString());
                                    xxgdBean.setSum(sum);
                                    AddGoodAction.addGood(assetUpdate, goodstable, loginResult, roleData, xxgdBean, AssetUpdate.USEGOOD);
                                }
                                else if (EquipTool.canSuper(good.getType())) {
                                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), good.getGoodsid());
                                    if (sameGoodstable.size() != 0) {
                                        ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum));
                                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                                        good = (Goodstable)sameGoodstable.get(0);
                                    }
                                    else {
                                        good.setUsetime(Integer.valueOf(sum));
                                        AllServiceUtil.getGoodsTableService().insertGoods(good);
                                    }
                                    AddGoodUtil.addGood(ctx, good);
                                    AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(sum), Integer.valueOf(3));
                                }
                                else {
                                    for (int k = 0; k < sum; ++k) {
                                        AllServiceUtil.getGoodsTableService().insertGoods(good);
                                        AddGoodUtil.addGood(ctx, good);
                                        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(3));
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        if (assetUpdate != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;
        if (max < min || n > len) {
            return null;
        }
        int[] source = new int[len];
        for (int i = min; i < min + len; ++i) {
            source[i - min] = i;
        }
        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int j = 0; j < result.length; ++j) {
            index = Math.abs(rd.nextInt() % len--);
            result[j] = source[index];
            source[index] = source[len];
        }
        return result;
    }
    
    static {
        UseRoleAction.random = new Random();
        UseRoleAction.zhNpc = new ConcurrentHashMap<>();
        UseRoleAction.lottery = new HashMap<>();
        UseRoleAction.lottery1 = new HashMap<>();
        NUMS = new String[] { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
    }
}
