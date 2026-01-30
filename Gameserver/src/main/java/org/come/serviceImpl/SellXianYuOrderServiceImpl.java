package org.come.serviceImpl;

import org.come.until.GsonUtil;
import org.come.server.GameServer;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;
import java.util.Calendar;
import org.come.redis.RedisCacheUtil;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.bean.SellXianyu;
import java.util.List;
import org.come.entity.SellXianYuOrder;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.SellXianYuOrderMapper;
import org.come.service.ISellXianYuOrderService;

public class SellXianYuOrderServiceImpl implements ISellXianYuOrderService
{
    private SellXianYuOrderMapper sellXianYuOrderMapper;
    
    public SellXianYuOrderServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.sellXianYuOrderMapper = (SellXianYuOrderMapper)ctx.getBean("sellXianYuOrderMapper", SellXianYuOrderMapper.class);
    }
    
    @Override
    public SellXianYuOrder selectOneByID(BigDecimal id) {
        SellXianYuOrder sellxianyuorder = this.sellXianYuOrderMapper.selectByPrimaryKey(id);
        return sellxianyuorder;
    }
    
    @Override
    public List<SellXianYuOrder> selectAll() {
        return null;
    }
    
    @Override
    public List<SellXianYuOrder> selectAllByRoleId(BigDecimal roleId) {
        List<SellXianYuOrder> sellxianyuorders = this.sellXianYuOrderMapper.selectAllByRoleId(roleId);
        return sellxianyuorders;
    }
    
    @Override
    public List<SellXianyu> selectSellListByRoleId(BigDecimal roleId) {
        List<SellXianyu> sellxianyuorders = RedisControl.getS(RedisParameterUtil.SELL, roleId.toString(), SellXianyu.class);
        return sellxianyuorders;
    }
    
    @Override
    public List<SellXianyu> selectSellListNotDeposit() {
        List<SellXianyu> sellxianyus = RedisControl.hgetAll(RedisParameterUtil.SELL, SellXianyu.class);
        ArrayList<SellXianyu> results = new ArrayList<>();
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (sellxianyus != null && sellxianyus.size() > 0) {
            for (SellXianyu sellxianyu : sellxianyus) {
                try {
                    Date expireTime = dateFormat.parse(sellxianyu.getExpireTime());
                    if (expireTime.after(now)) {
                        results.add(sellxianyu);
                    }
                    else {
                        continue;
                    }
                }
                catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return (List)results;
    }
    
    @Override
    public SellXianyu selectSellByIdNotDeposit(BigDecimal id) {
        SellXianyu sellXianyu = (SellXianyu)RedisControl.getV(RedisParameterUtil.SELL, id.toString(), SellXianyu.class);
        return sellXianyu;
    }
    
    @Override
    public int insertOrder(SellXianYuOrder sellXianYuOrder) {
        return this.sellXianYuOrderMapper.insert(sellXianYuOrder);
    }
    
    @Override
    public int updateOrder(SellXianYuOrder sellXianYuOrder) {
        return this.sellXianYuOrderMapper.updateByPrimaryKey(sellXianYuOrder);
    }
    
    public List<SellXianyu> getReidsSellByRoleId(BigDecimal roleid) {
        List<SellXianyu> list = RedisControl.getS(RedisParameterUtil.SELL, roleid.toString(), SellXianyu.class);
        return list;
    }
    
    @Override
    public void addReidsSellxx(SellXianyu sellXianyu) {
        sellXianyu.setId(RedisCacheUtil.getSell_pk());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(5);
        calendar.set(5, day + 7);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdayTime = dateFormat.format(new Date());
        sellXianyu.setAddTime(nowdayTime);
        sellXianyu.setExpireTime(dateFormat.format(calendar.getTime()));
        RedisControl.insertKeyT(RedisParameterUtil.SELL, sellXianyu.getId().toString(), sellXianyu);
        RedisControl.insertListRedis(RedisParameterUtil.SELL, sellXianyu.getRoleId().toString(), sellXianyu.getId().toString());
    }
    
    @Override
    public void updateRedisSellxx(SellXianyu sellXianyu) {
        if (sellXianyu.getXianYuPoint().compareTo(BigDecimal.ZERO) <= 0) {
            RedisControl.deletrValue(RedisParameterUtil.SELL, sellXianyu.getRoleId().toString(), sellXianyu.getId().toString());
            RedisControl.delForKey(RedisParameterUtil.SELL, sellXianyu.getId().toString());
        }
        else {
            RedisControl.insertKeyT(RedisParameterUtil.SELL, sellXianyu.getId().toString(), sellXianyu);
        }
    }
    
    @Override
    public ArrayList<AssetUpdate> downSellXianyu(String id, LoginResult loginResult, ChannelHandlerContext ctx) {
        SellXianyu sellXianyu = this.selectSellByIdNotDeposit(new BigDecimal(id));
        loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() + sellXianyu.getXianYuPoint().longValue()));
        MonitorUtil.getMoney().addX(sellXianyu.getXianYuPoint().longValue(), 4);
        RedisControl.deletrValue(RedisParameterUtil.SELL, sellXianyu.getRoleId().toString(), id);
        RedisControl.delForKey(RedisParameterUtil.SELL, id);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setData("X=" + sellXianyu.getXianYuPoint().longValue());
        String msg = "下架成功,归还" + sellXianyu.getXianYuPoint().longValue() + "仙玉,。";
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(msg));
        ArrayList<AssetUpdate> result = new ArrayList<>();
        result.add(assetUpdate);
        return result;
    }
    
    @Override
    public void calSelfDeposit(ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        List<SellXianyu> cells = this.selectSellListByRoleId(loginResult.getRole_id());
        Date now = new Date();
        if (cells != null && cells.size() > 0) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (SellXianyu sellXianyu : cells) {
                try {
                    Date expireTime = dateFormat.parse(sellXianyu.getExpireTime());
                    if (expireTime.before(now)) {
                        loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() + sellXianyu.getXianYuPoint().longValue()));
                        MonitorUtil.getMoney().addX(sellXianyu.getXianYuPoint().longValue(), 4);
                        RedisControl.deletrValue(RedisParameterUtil.SELL, sellXianyu.getRoleId().toString(), sellXianyu.getId().toString());
                        RedisControl.delForKey(RedisParameterUtil.SELL, sellXianyu.getId().toString());
                        AssetUpdate assetUpdate = new AssetUpdate();
                        assetUpdate.setData("X=" + sellXianyu.getXianYuPoint().longValue());
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        String msg = "你寄售的仙玉已过期，成功归还" + sellXianyu.getXianYuPoint().longValue() + "仙玉。";
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(msg));
                    }
                    else {
                        continue;
                    }
                }
                catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        SellXianYuOrder sellXianYuOrder = new SellXianYuOrder();
        sellXianYuOrder.setSellRoleId(loginResult.getRole_id());
        sellXianYuOrder.setOrderStatus(1);
        List<SellXianYuOrder> sellxianyuorders = this.sellXianYuOrderMapper.selectAllBySellRoleIdAndStatus(sellXianYuOrder);
        if (sellxianyuorders != null && sellxianyuorders.size() > 0) {
            for (SellXianYuOrder sxyo : sellxianyuorders) {
                loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() + sxyo.getTotalPrice().longValue()));
                AssetUpdate assetUpdate = new AssetUpdate();
                String msg = "";
                MonitorUtil.getMoney().addD(sxyo.getTotalPrice().longValue(), 3);
                assetUpdate.setData("D=" + sxyo.getTotalPrice().longValue());
                msg = "有人购买了你的仙玉，你获取" + sxyo.getTotalPrice().longValue() + "大话币。";
                sxyo.setOrderStatus(2);
                this.sellXianYuOrderMapper.updateByPrimaryKey(sxyo);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(msg));
            }
        }
    }
    
    @Override
    public void calDeposit() {
    }
}
