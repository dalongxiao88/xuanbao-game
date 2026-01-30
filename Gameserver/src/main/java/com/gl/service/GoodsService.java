package com.gl.service;

import come.tool.Role.PartJade;
import org.come.entity.Lingbao;
import come.tool.Role.RoleData;
import org.come.entity.RoleTable;
import org.come.tool.EquipTool;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.action.monitor.MonitorUtil;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import org.come.entity.RandomNum;
import java.util.ArrayList;
import org.come.until.SplitLingbaoValue;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Record;
import org.come.bean.XXGDBean;
import come.tool.Role.RolePool;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.entity.Goodstable;
import org.come.entity.Goodsexchange;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.BasePageHelper;
import org.come.until.AllServiceUtil;
import org.apache.commons.lang.math.NumberUtils;
import com.github.pagehelper.util.StringUtil;
import org.come.entity.GoodsexchangeExample;
import org.come.bean.BackGoodsExchange;
import com.gl.model.Param;

public class GoodsService
{
    private static final int PageSize = 15;
    
    public BackGoodsExchange getExchange(Param param) {
        String type = param.getValue1();
        String value = param.getValue2();
        int pageNum = param.getPageNum();
        int size = param.getPageSize();
        if (size < 20) {
            size = 15;
        }
        GoodsexchangeExample example = new GoodsexchangeExample();
        if (StringUtil.isNotEmpty(type)) {
            if ("1".equals(type)) {
                example.setGoodsguid(value);
            }
            else if ("2".equals(type) && StringUtil.isNotEmpty(value) && NumberUtils.isDigits(value)) {
                example.setGoodsid(value);
            }
        }
        // 查询推广码总数
        int total = AllServiceUtil.getGoodsExchangeService().countByExample(example);
        //总页数
        int page = total / size;
        if (total % size > 0) {
            ++page;
        }
        example.setOrderByClause("GOODSID ASC");
        // 分页查询
        BasePageHelper.startPage(pageNum, size);
        List<Goodsexchange> list = AllServiceUtil.getGoodsExchangeService().selectByExample(example);
        PageInfo<Goodsexchange> pageInfo = new PageInfo(list);
        // 进行物品名称实例化
        for (Goodsexchange change : list) {
            change.setGoodsid(((Goodstable)GameServer.getAllGoodsMap().get(new BigDecimal(change.getGoodsid()))).getGoodsname());
        }
        BackGoodsExchange goods = new BackGoodsExchange();
        goods.setList(pageInfo.getList());
        goods.setPages(page);
        goods.setPageNum(pageNum);
        goods.setTotal((long)total);
        return goods;
    }
    //生成推广礼包
    public boolean createExchange(Param param) {
        String goodsId = param.getValue1();
        String sum = param.getValue2();
        if (StringUtil.isNotEmpty(goodsId) && StringUtil.isNotEmpty(sum) && NumberUtils.isDigits(goodsId) && NumberUtils.isDigits(sum) && GameServer.getAllGoodsMap().containsKey(new BigDecimal(goodsId))) {
            for (int count = (int)Integer.valueOf(sum), i = 0; i < count; ++i) {
                Goodsexchange record = new Goodsexchange();
                record.setGoodsguid(UUID.randomUUID().toString().toUpperCase());
                record.setGoodsid(goodsId);
                record.setFlag(Integer.valueOf(0));
                AllServiceUtil.getGoodsExchangeService().insert(record);
            }
            return true;
        }
        else {
            return false;
        }
    }
    //返回所有物品列表
    public Map<String, String> goodsMap() {
        ConcurrentHashMap<BigDecimal, Goodstable> map = GameServer.getAllGoodsMap();
        Map<String, String> goodsMap = new ConcurrentHashMap<>();
        map.forEach((k, v)/* java.math.BigDecimal,org.come.entity.Goodstable, */ -> goodsMap.put(v.getGoodsname() + "(" + v.getGoodsid() + ")", v.getGoodsid().longValue() + ""));
        return goodsMap;
    }
    //给玩家发送物品
    public boolean sendGoods(Param param) {
        String roleName = param.getValue1();
        String goodsId = param.getValue2();
        String num = param.getValue3();
        RoleTable userTable = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(roleName);
        if (userTable != null) {
            RoleData roleData = RolePool.getRoleData(userTable.getRole_id());
            XXGDBean xxgdBean = new XXGDBean();
            xxgdBean.setId(goodsId);
            xxgdBean.setSum(Integer.parseInt(num));
            // 获得购买的物品的ID查找excel表，获得物品信息
            BigDecimal id = new BigDecimal(xxgdBean.getId());
            Goodstable goodstable = GameServer.getGood(id);
            if (goodstable == null) {
                return false;
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append("刷物资接口物品id:");
            buffer.append(goodsId);
            buffer.append(",");
            buffer.append(xxgdBean.getSum() + "个" + goodstable.getGoodsname());
            buffer.append(",接收人:");
            buffer.append(userTable.getRole_id());
            buffer.append("_");
            buffer.append(roleName);
            AllServiceUtil.getRecordService().insert(new Record(4, buffer.toString()));
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setMsg("获得" + xxgdBean.getSum() + "个" + goodstable.getGoodsname());
            // 添加记录
            goodstable.setRole_id(userTable.getRole_id());
            long yid = id.longValue();
            for (int i = 0; i < xxgdBean.getSum(); ++i) {
                if (i != 0) {
                    goodstable = GameServer.getGood(id);
                }
                goodstable.setRole_id(userTable.getRole_id());
                long sid = goodstable.getGoodsid().longValue();
                if (sid >= 70001L && sid <= 70030L) {
                    Lingbao lingbao = SplitLingbaoValue.addling(goodstable.getGoodsname(), userTable.getRole_id());
                    assetUpdate.setLingbao(lingbao);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, 1, -3);//物品+对方id+物品数量+记录类型
                }
                else if (goodstable.getType() == 1001L) {
                    assetUpdate.setType(AssetUpdate.USERGOOD);

                    String[] s = goodstable.getValue().split("=")[1].split("&");
                    List<RandomNum> randomNums = new ArrayList<>();
                    for (String string : s) {
                        String[] m = string.split("\\$");
                        RandomNum randomNum = new RandomNum();
                        randomNum.setProbability(Integer.parseInt(m[0]));
                        String[] n = m[1].split("-");
                        randomNum.setMin(Integer.parseInt(n[0]));
                        randomNum.setMax(Integer.parseInt(n[1]));
                        randomNums.add(randomNum);
                    }
                    Collections.sort(randomNums, new Comparator<RandomNum>() {
                        @Override
                        public int compare(RandomNum p1, RandomNum p2) {
                            return Integer.compare(p1.getProbability(), p2.getProbability());
                        }
                    });
                    Boolean b = false;
                    BigDecimal number = BigDecimal.ZERO;
                    for (RandomNum randomNum2 : randomNums) {
                        if (randomNum2.getProbability() > new Random().nextInt(100)) {
                            b = true;
                            int money = randomNum2.getMin() + new Random().nextInt(randomNum2.getMax() - randomNum2.getMin());
                            number = new BigDecimal(money + "");
                            break;
                        }
                    }
                    if (number.longValue() == 0L) {
                        int min = ((RandomNum)randomNums.get(randomNums.size() - 1)).getMin();
                        number = new BigDecimal(min + "");
                    }
                    ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleName);
                    if (goodstable.getValue().startsWith("金钱")) {
                        if (channelHandlerContext != null) {
                            LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(channelHandlerContext);
                            loginResult.setGold(loginResult.getGold().add(number));
                            MonitorUtil.getMoney().addD(number.longValue(), 3);
                            assetUpdate.updata("D=" + number.longValue());
                            assetUpdate.setMsg("您获得了#R" + number.toString() + "#Y两银子");
                            SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        }
                    }
                    else if (goodstable.getValue().startsWith("仙玉") && channelHandlerContext != null) {
                        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(channelHandlerContext);
                        loginResult.setCodecard(loginResult.getCodecard().add(number));
                        MonitorUtil.getMoney().addX(number.longValue(), 3);
                        assetUpdate.updata("X=" + number.longValue());
                        assetUpdate.setMsg("您获得了#R" + number.toString() + "#Y仙玉");
                        SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }
                }
                else if (sid >= 69001L && sid <= 69015L) {
                    Lingbao lingbao = SplitLingbaoValue.addfa(sid, userTable.getRole_id());
                    assetUpdate.setLingbao(lingbao);
                    if (i == 0) {
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, xxgdBean.getSum(), -3);
                    }
                }
                else if (goodstable.getType() == 825L) {// 是玉符
                    if (!goodstable.getValue().equals("")) {
                        String[] v = goodstable.getValue().split("\\|");
                        int suitid = Integer.parseInt(v[0]);
                        int part = Integer.parseInt(v[1]);
                        int pz = Integer.parseInt(v[2]);
                        PartJade jade = roleData.getPackRecord().setPartJade(suitid, part, pz, 1);
                        assetUpdate.setJade(jade);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, 1, -3);
                    }
                }
                else if (EquipTool.canSuper(goodstable.getType())) {// 可叠加
                    int sum = (yid == sid) ? xxgdBean.getSum() : 1;
                    // 判断该角色是否拥有这件物品
                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(userTable.getRole_id(), goodstable.getGoodsid());
                    if (sameGoodstable.size() != 0) {
                        // 修改使用次数
                        int uses = (int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum;
                        ((Goodstable)sameGoodstable.get(0)).setUsetime(uses);
                        // 修改数据库
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                        assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, xxgdBean.getSum(), -3);
                    }
                    else {
                        goodstable.setUsetime(sum);
                        // 插入数据库
                        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                        assetUpdate.setGood(goodstable);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, xxgdBean.getSum(), -3);
                    }
                    if (yid == sid) {
                        break;
                    }
                }
                else {
                    goodstable.setUsetime(1);
                    AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                    assetUpdate.setGood(goodstable);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, 1, -3);
                }
            }
            //玩家在线
            if (GameServer.getRoleNameMap().get(roleName) != null) {
                SendMessage.sendMessageToSlef((ChannelHandlerContext)GameServer.getRoleNameMap().get(roleName), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            String message = "#Y[系统消息] #G你获得了#R" + xxgdBean.getSum() + "#G个#Y" + goodstable.getGoodsname();
            new GameService().sendMsgToPlayer(message, roleName);
            return true;
        }
        else {
            return false;
        }
    }
}
