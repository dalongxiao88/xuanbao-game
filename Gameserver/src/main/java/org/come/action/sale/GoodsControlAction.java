package org.come.action.sale;

import org.come.entity.Lingbao;
import java.util.Iterator;
import org.come.entity.RoleSummoning;
import java.util.List;
import org.come.entity.Goodstable;
import java.text.DateFormat;
import org.come.entity.Message;
import org.come.entity.Collection;
import org.come.entity.CollectionExample;
import java.util.ArrayList;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Salegoods;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GoodsControlAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        Salegoods goods = (Salegoods)GsonUtil.getGsonUtil().getgson().fromJson(message, Salegoods.class);
        if (goods == null) {
            return;
        }
        Salegoods salegoods = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(goods.getSaleid());
        if (roleInfo.getRole_id().compareTo(salegoods.getRoleid()) != 0) {
            System.out.println("此物品:" + salegoods.getSalename() + ",非本人所有:" + roleInfo.getRolename());
            return;
        }
        Integer selectFlag = AllServiceUtil.getSalegoodsService().selectFlag(salegoods.getSaleid());
        String tip = "";
        if ((int)goods.getFlag() != 5) {
            salegoods.setFlag(goods.getFlag());
            if ((int)goods.getFlag() == 2) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowdayTime = dateFormat.format(new Date());
                Date nowDate = null;
                try {
                    nowDate = dateFormat.parse(nowdayTime);
                    salegoods.setUptime(nowDate);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                tip = salegoods.getSalename() + "上架了";
                NChatBean c = new NChatBean();
                c.setId(5);
                int type = 0;
                if ((int)salegoods.getSaletype() == 2) {
                    type = 5;
                }
                else if ((int)salegoods.getSaletype() == 3) {
                    type = 2;
                }
                else if ((int)salegoods.getSaletype() == 4) {
                    type = 3;
                }
                else if ((int)salegoods.getSaletype() == 5) {
                    type = 2;
                }
                else if ((int)salegoods.getSaletype() == 6) {
                    type = 4;
                }
                c.setMessage("#R藏宝阁公告：#W玩家#G" + roleInfo.getRolename() + "#W将#V{\"type\":" + type + ",\"id\":" + salegoods.getOtherid() + ",\"name\":\"[" + salegoods.getSalename() + "]\",\"color\":\"G\"}#L#W上架了#130");
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(c));
                SendMessage.sendMessageToAllRoles(msg);
            }
            else if ((int)goods.getFlag() == 1) {
                tip = salegoods.getSalename() + "下架了";
            }
            AllServiceUtil.getSalegoodsService().updateByPrimaryKey(salegoods);
            AssetUpdate update = new AssetUpdate();
            update.setMsg(tip);
            update.setType(AssetUpdate.CBGGET);
            update.reset();
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
        }
        else if ((int)goods.getFlag() == 5) {
            tip = salegoods.getSalename() + "已被取回";
            AllServiceUtil.getSalegoodsService().deleteByPrimaryKey(salegoods.getSaleid());
            AssetUpdate update = new AssetUpdate();
            if ((int)salegoods.getSaletype() == 3 || (int)salegoods.getSaletype() == 5) {
                Goodstable goods2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(salegoods.getOtherid());
                AllServiceUtil.getGoodsrecordService().insert(goods2, null, Integer.valueOf(1), Integer.valueOf(13));
                AllServiceUtil.getGoodsTableService().updateGoodsIndex(goods2, goods2.getRole_id().multiply(new BigDecimal(-1)), null, null);
                List<Goodstable> goodstables = new ArrayList<>();
                goodstables.add(goods2);
                update.setGoods(goodstables);
            }
            else if ((int)salegoods.getSaletype() == 4) {
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(salegoods.getOtherid());
                AllServiceUtil.getRoleSummoningService().updateRoleSummoningIndex(pet, roleInfo.getRole_id());
                List<RoleSummoning> pets = new ArrayList<>();
                pets.add(pet);
                update.setPets(pets);
                List<Goodstable> goodstables2 = new ArrayList<>();
                List<BigDecimal> goodses = pet.getGoods();
                if (goodses != null) {
                    for (BigDecimal bigDecimal : goodses) {
                        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(bigDecimal);
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, goodstable.getRole_id().multiply(new BigDecimal(-1)), null, null);
                        goodstables2.add(goodstable);
                    }
                }
                update.setGoods(goodstables2);
            }
            else if ((int)salegoods.getSaletype() == 6) {
                Lingbao lingbao = AllServiceUtil.getLingbaoService().selectByPrimaryKey(salegoods.getOtherid());
                AllServiceUtil.getLingbaoService().updateLingbaoIndex(lingbao, roleInfo.getRole_id());
                List<Lingbao> lingbaos = new ArrayList<>();
                lingbaos.add(lingbao);
                update.setLingbaos(lingbaos);
                List<Goodstable> goodstables2 = new ArrayList<>();
                if (lingbao.getFushis() != null && !"".equals(lingbao.getFushis())) {
                    String[] baos;
                    for (String string : baos = lingbao.getFushis().split("\\|")) {
                        Goodstable goodstable2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(string));
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable2, goodstable2.getRole_id().multiply(new BigDecimal(-1)), null, null);
                        goodstables2.add(goodstable2);
                    }
                }
                update.setGoods(goodstables2);
            }
            else if ((int)salegoods.getSaletype() == 2) {
                update.updata("D=" + salegoods.getOtherid().longValue());
                roleInfo.setGold(roleInfo.getGold().add(salegoods.getOtherid()));
            }
            update.setMsg("1个" + salegoods.getSalename());
            update.setType(AssetUpdate.CGB);
            update.reset();
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
        }
        CollectionExample example = new CollectionExample();
        CollectionExample.Criteria c2 = example.createCriteria();
        c2.andSaleidEqualTo(salegoods.getSaleid());
        List<Collection> list = AllServiceUtil.getCollectionService().selectByExample(example);
        if (list != null && list.size() != 0) {
            for (Collection collection : list) {
                Message message2 = new Message();
                message2.setRoleid(collection.getRoleid());
                message2.setSaleid(collection.getSaleid());
                DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowdayTime2 = dateFormat2.format(new Date());
                Date nowDate2 = null;
                try {
                    nowDate2 = dateFormat2.parse(nowdayTime2);
                    message2.setGettime(nowDate2);
                }
                catch (ParseException e2) {
                    e2.printStackTrace();
                }
                message2.setMescontent(tip);
                AllServiceUtil.getMessageService().insert(message2);
            }
        }
    }
}
