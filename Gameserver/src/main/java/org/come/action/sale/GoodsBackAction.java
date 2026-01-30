package org.come.action.sale;

import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import java.util.List;
import org.come.entity.Goodstable;
import org.come.entity.Salegoods;
import java.util.Iterator;
import org.come.entity.Roleorder;
import org.come.entity.RoleorderExample;
import org.come.action.chat.FriendChatAction;
import org.come.bean.ChatBean;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.ArrayList;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.until.GsonUtil;
import org.come.bean.GoodsBackBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GoodsBackAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        GoodsBackBean backBean = (GoodsBackBean)GsonUtil.getGsonUtil().getgson().fromJson(message, GoodsBackBean.class);
        for (BigDecimal saleid : backBean.getIds()) {
            Salegoods salegoods = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(saleid);
            AssetUpdate update = new AssetUpdate();
            if ((int)salegoods.getSaletype() == 3 || (int)salegoods.getSaletype() == 5) {
                Goodstable goods2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(salegoods.getOtherid());
                AllServiceUtil.getGoodsrecordService().insert(goods2, null, Integer.valueOf(1), Integer.valueOf(14));
                AllServiceUtil.getGoodsTableService().updateGoodsIndex(goods2, roleInfo.getRole_id(), null, null);
                List<Goodstable> goodstables = new ArrayList<>();
                goodstables.add(goods2);
                update.setGoods(goodstables);
            }
            else if ((int)salegoods.getSaletype() == 4) {
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(salegoods.getOtherid());
                pet.setFriendliness(Long.valueOf(0L));
                AllServiceUtil.getRoleSummoningService().updateRoleSummoningIndex(pet, roleInfo.getRole_id());
                List<RoleSummoning> pets = new ArrayList<>();
                pets.add(pet);
                update.setPets(pets);
                List<Goodstable> goodstables2 = new ArrayList<>();
                List<BigDecimal> goodses = pet.getGoods();
                if (goodses != null) {
                    for (BigDecimal bigDecimal : goodses) {
                        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(bigDecimal);
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, roleInfo.getRole_id(), null, null);
                        goodstables2.add(goodstable);
                    }
                }
                update.setGoods(goodstables2);
            }
            else if ((int)salegoods.getSaletype() == 6) {
                Lingbao lingbao = AllServiceUtil.getLingbaoService().selectByPrimaryKey(salegoods.getOtherid());
                lingbao.setLingbaoqihe(0L);
                AllServiceUtil.getLingbaoService().updateLingbaoIndex(lingbao, roleInfo.getRole_id());
                List<Lingbao> lingbaos = new ArrayList<>();
                lingbaos.add(lingbao);
                update.setLingbaos(lingbaos);
                List<Goodstable> goodstables2 = new ArrayList<>();
                if (lingbao.getFushis() != null && !"".equals(lingbao.getFushis())) {
                    String[] baos;
                    for (String string : baos = lingbao.getFushis().split("\\|")) {
                        Goodstable goodstable2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(string));
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable2, roleInfo.getRole_id(), null, null);
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
            c.setMessage("#R藏宝阁公告：某人#Y刚刚以实惠的价格购买了#G藏宝阁：#V{\"type\":" + type + ",\"id\":" + salegoods.getOtherid() + ",\"name\":\"[" + salegoods.getSalename() + "]\",\"color\":\"G\"}#L-￥" + salegoods.getSaleprice());
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(c));
            SendMessage.sendMessageToAllRoles(msg);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
            ChatBean bc = new ChatBean();
            bc.setRolename("大话精灵");
            bc.setFriendName(roleInfo.getRolename());
            bc.setMessage("成功购买藏宝阁" + salegoods.getSalename() + "商品");
            bc.setTime(System.currentTimeMillis());
            FriendChatAction.useServerFriendToRoleForClient(bc);
            LoginResult sr = AllServiceUtil.getRoleTableService().selectRoleID(salegoods.getRoleid());
            if (sr == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().tipAgreement("没有该玩家"));
            }
            else {
                ChatBean sc = new ChatBean();
                sc.setRolename("大话精灵");
                sc.setFriendName(sr.getRolename());
                sc.setMessage("藏宝阁" + salegoods.getSalename() + "商品成功售出");
                sc.setTime(System.currentTimeMillis());
                FriendChatAction.useServerFriendToRoleForClient(sc);
            }
        }
        RoleorderExample example = new RoleorderExample();
        RoleorderExample.Criteria c2 = example.createCriteria();
        c2.andRoleidEqualTo(roleInfo.getRole_id());
        c2.andSaleidIn(backBean.getIds());
        Roleorder record = new Roleorder();
        record.setStatus(Integer.valueOf(4));
        AllServiceUtil.getRoleorderService().updateByExampleSelective(record, example);
    }
}
