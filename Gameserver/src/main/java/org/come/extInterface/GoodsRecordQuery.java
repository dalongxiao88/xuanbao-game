package org.come.extInterface;

import java.math.BigDecimal;
import java.util.List;
import org.come.entity.RoleTable;
import org.come.extInterBean.GodsRecordResultModel;
import org.come.extInterBean.Goodsrecord2;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.extInterBean.GoodsRecordQueryReqBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GoodsRecordQuery implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        GoodsRecordQueryReqBean request = (GoodsRecordQueryReqBean)GsonUtil.getGsonUtil().getgson().fromJson(message, GoodsRecordQueryReqBean.class);
        String nowPage = request.getNowPage();
        String gDState = request.getgDState();
        String gDRoleName = request.getgDRoleName();
        String gDOtherName = request.getgDOtherName();
        String gDGoodsName = request.getgDGoodsName();
        String gDTime = request.getgDTime();
        String rgid = request.getRgid();
        String quid = request.getQuid();
        String page = request.getPage();
        String gDType = request.getgDType();
        String goodsRecordres = "";
        if ("goodsrecord".equals(gDType)) {
            goodsRecordres = this.goodsRecordQuery(nowPage, gDState, gDRoleName, gDOtherName, gDGoodsName, gDTime);
        }
        else if ("trackGoods".equals(gDType)) {
            goodsRecordres = this.trackGoods(rgid, quid, page);
        }
        String msg = Agreement.getAgreement().goodsRecordQueryAgreement(goodsRecordres);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
    
    public String goodsRecordQuery(String nowPage, String goodsDetailState, String goodsDetailRoleName, String goodsDetailOtherName, String goodsDetailGoodsName, String goodsDetailTime) {
        String result = "";
        if (nowPage == null || goodsDetailState == null || goodsDetailRoleName == null || goodsDetailOtherName == null || goodsDetailGoodsName == null || goodsDetailTime == null) {
            return result;
        }
        String sql = "";
        if (!"".equals(goodsDetailRoleName)) {
            RoleTable role = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(goodsDetailRoleName);
            if (role == null) {
                sql += " AND ROLEID = 0 ";
            }
            else {
                sql = sql + " AND ROLEID = " + role.getRole_id();
            }
        }
        if (!"".equals(goodsDetailOtherName)) {
            RoleTable role = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(goodsDetailOtherName);
            if (role == null) {
                sql += " AND OTHERROLE = 0 ";
            }
            else {
                sql = sql + " AND OTHERROLE = " + role.getRole_id();
            }
        }
        if (!"".equals(goodsDetailState)) {
            sql = sql + " AND RECORDTYPE = " + goodsDetailState;
        }
        if (!"".equals(goodsDetailGoodsName)) {
            sql = sql + " AND GOODSNAME = '" + goodsDetailGoodsName + "'";
        }
        if (!"".equals(goodsDetailTime)) {
            sql = sql + " AND RECORDTIME between to_timestamp('" + goodsDetailTime + " 00:00:0.000000000','yyyy-mm-dd hh24:mi:ss.ff9') and to_timestamp('" + goodsDetailTime + " 23:59:59.000000000','yyyy-mm-dd hh24:mi:ss.ff9')";
        }
        List<Goodsrecord2> goodsRecord = AllServiceUtil.getAppVersionService().selectGoodsRecordByPage(sql, Integer.valueOf(nowPage));
        for (int i = 0; i < goodsRecord.size(); ++i) {
            BigDecimal myRole = ((Goodsrecord2)goodsRecord.get(i)).getRoleid();
            if (myRole != null) {
                RoleTable role2 = AllServiceUtil.getRoleTableService().selectGang(myRole);
                if (role2 != null) {
                    ((Goodsrecord2)goodsRecord.get(i)).setRoleName(role2.getRolename());
                }
                else {
                    ((Goodsrecord2)goodsRecord.get(i)).setRoleName("");
                }
            }
            BigDecimal otherRole = ((Goodsrecord2)goodsRecord.get(i)).getOtherrole();
            if (otherRole != null && !"0".equals(otherRole)) {
                RoleTable role3 = AllServiceUtil.getRoleTableService().selectGang(otherRole);
                if (role3 != null) {
                    ((Goodsrecord2)goodsRecord.get(i)).setOtherRole(role3.getRolename());
                }
                else {
                    ((Goodsrecord2)goodsRecord.get(i)).setOtherRole("");
                }
            }
        }
        GodsRecordResultModel godsRec = new GodsRecordResultModel(goodsRecord, (int)Integer.valueOf(nowPage), 0);
        String godsRecJson = GsonUtil.getGsonUtil().getgson().toJson(godsRec);
        return godsRecJson;
    }
    
    public String trackGoods(String rgid, String quid, String page) {
        String result = "";
        if (rgid == null || "".equals(rgid) || quid == null || "".equals(quid) || page == null || "".equals(page)) {
            return result;
        }
        int rgidi = (int)Integer.valueOf(rgid);
        int quidi = (int)Integer.valueOf(quid);
        int pagei = (int)Integer.valueOf(page);
        List<Goodsrecord2> trackGoods = AllServiceUtil.getAppVersionService().trackGoods(rgidi, quidi, pagei);
        for (int i = 0; i < trackGoods.size(); ++i) {
            BigDecimal myRole = ((Goodsrecord2)trackGoods.get(i)).getRoleid();
            Integer sid = ((Goodsrecord2)trackGoods.get(i)).getSid();
            if (myRole != null) {
                RoleTable role = AllServiceUtil.getRoleTableService().selectGang(myRole);
                if (role != null) {
                    ((Goodsrecord2)trackGoods.get(i)).setRoleName(role.getRolename());
                }
                else {
                    ((Goodsrecord2)trackGoods.get(i)).setRoleName("");
                }
            }
            BigDecimal otherRole = ((Goodsrecord2)trackGoods.get(i)).getOtherrole();
            if (otherRole != null && !"0".equals(otherRole)) {
                RoleTable role2 = AllServiceUtil.getRoleTableService().selectGang(otherRole);
                if (role2 != null) {
                    ((Goodsrecord2)trackGoods.get(i)).setOtherRole(role2.getRolename());
                }
                else {
                    ((Goodsrecord2)trackGoods.get(i)).setOtherRole("");
                }
            }
        }
        GodsRecordResultModel model = new GodsRecordResultModel(trackGoods, pagei, 0);
        String godsRecoJson = GsonUtil.getGsonUtil().getgson().toJson(model);
        return godsRecoJson;
    }
}
