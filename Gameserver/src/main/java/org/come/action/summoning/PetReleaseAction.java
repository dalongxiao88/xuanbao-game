package org.come.action.summoning;

import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import org.come.tool.EquipTool;
import come.tool.Stall.AssetUpdate;
import java.util.Random;
import org.come.bean.LoginResult;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.come.entity.RoleSummoning;
import org.come.model.PetExchange;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PetReleaseAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal summoningId = new BigDecimal(message);
        if (!SummonPetAction.validateSummoning(ctx, summoningId)) {
            return;
        }
        RoleSummoning roleSummoning = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(message));
        AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySid(new BigDecimal(message));
        ConcurrentHashMap<Integer, PetExchange> allpetexchange = GameServer.getAllPetExchange();
        if (allpetexchange != null) {
            for (Integer key : allpetexchange.keySet()) {
                PetExchange petExchange = (PetExchange)allpetexchange.get(key);
                if (petExchange.getType() != null && petExchange.getType().equals("召唤兽分解")) {
                    petExchange.getConsume();
                    petExchange.getPid();
                    if (petExchange.getPid() != null) {
                        if (petExchange.getPid().split("-").length > 1) {
                            if (new BigDecimal(roleSummoning.getSummoningid()).compareTo(new BigDecimal(petExchange.getPid().split("-")[0])) >= 0 && new BigDecimal(roleSummoning.getSummoningid()).compareTo(new BigDecimal(petExchange.getPid().split("-")[1])) <= 0) {
                                this.sendGodds(petExchange, ctx);
                            }
                            else {
                                continue;
                            }
                        }
                        else if (petExchange.getPid().split("\\|").length > 1) {
                            String[] ids;
                            for (String id : ids = petExchange.getPid().split("\\|")) {
                                if (roleSummoning.getSummoningid().equals(id)) {
                                    this.sendGodds(petExchange, ctx);
                                }
                            }
                        }
                        else if (roleSummoning.getSummoningid().equals(petExchange.getPid())) {
                            this.sendGodds(petExchange, ctx);
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        continue;
                    }
                }
            }
        }
    }
    
    public void sendGodds(PetExchange petExchange, ChannelHandlerContext ctx) {
        if (petExchange.getConsume() != null) {
            String[] goodsinfo = petExchange.getConsume().split("=");
            if (goodsinfo.length >= 4) {
                BigDecimal goodsId = new BigDecimal(goodsinfo[1]);
                LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                int min = (int)Integer.valueOf(goodsinfo[2]);
                int max = (int)Integer.valueOf(goodsinfo[3]);
                Random random = new Random();
                int num = random.nextInt(max - min + 1) + min;
                Goodstable goodstable = GameServer.getGood(goodsId);
                AssetUpdate assetUpdate = new AssetUpdate();
                goodstable.setRole_id(loginResult.getRole_id());
                if (EquipTool.canSuper(goodstable.getType())) {
                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodstable.getGoodsid());
                    if (sameGoodstable.size() != 0) {
                        int uses = (int)((Goodstable)sameGoodstable.get(0)).getUsetime() + num;
                        ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf(uses));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                        assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                    }
                    else {
                        goodstable.setUsetime(Integer.valueOf(num));
                        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                        assetUpdate.setGood(goodstable);
                    }
                    assetUpdate.setMsg("你分解了召唤兽，获得了" + num + "个" + goodstable.getGoodsname());
                }
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
        }
    }
}
