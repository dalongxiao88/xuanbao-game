package org.come.action.sale;

import org.come.tool.SplitStringTool;
import org.come.entity.Salegoods;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.SearchGoodsResultBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import java.math.BigDecimal;
import org.come.entity.SalegoodsExample;
import org.come.until.GsonUtil;
import org.come.bean.SearchGoodsBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GoodsSearchAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        SearchGoodsBean bean = (SearchGoodsBean)GsonUtil.getGsonUtil().getgson().fromJson(message, SearchGoodsBean.class);
        SalegoodsExample example = new SalegoodsExample();
        SalegoodsExample.Criteria c = example.createCriteria();
        int dataNum = 15;
        if (bean.getSalename() != null && !"".equals(bean.getSalename())) {
            c.andSalenameLike("%" + bean.getSalename() + "%");
        }
        if (bean.getSaletype() != null && !"".equals(bean.getSaletype())) {
            if ((int)bean.getSaletype() == 1 && (bean.getSalename() == null || "".equals(bean.getSalename()))) {
                c.andUptimeLessThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
                dataNum = 7;
            }
            else {
                c.andSaletypeEqualTo(bean.getSaletype());
            }
            if ((int)bean.getSaletype() != 2) {
                if (bean.getShow() == 0) {
                    c.andUptimeLessThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
                }
                else if (bean.getShow() == 2) {
                    c.andUptimeGreaterThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
                }
            }
        }
        else if (bean.getShow() == 0) {
            c.andUptimeLessThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
            SalegoodsExample example2 = new SalegoodsExample();
            SalegoodsExample.Criteria criteria = example2.createCriteria();
            criteria.andSaletypeEqualTo(Integer.valueOf(2));
            criteria.andFlagEqualTo(Integer.valueOf(2));
            if (bean.getSalename() != null && !"".equals(bean.getSalename())) {
                criteria.andSalenameLike("%" + bean.getSalename() + "%");
            }
            example.or(criteria);
        }
        else if (bean.getShow() == 2) {
            c.andUptimeGreaterThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
            c.andSaletypeNotEqualTo(Integer.valueOf(2));
            c.andSaletypeNotEqualTo(Integer.valueOf(10));
        }
        if (bean.getContiontype() != null && !"".equals(bean.getContiontype())) {
            if ((int)bean.getSaletype() == 2) {
                String[] range = bean.getContiontype().split("-");
                c.andOtheridBetween(new BigDecimal(range[0]), new BigDecimal(range[1]));
            }
            else if ((int)bean.getSaletype() == 3) {
                selectProp(bean.getContiontype(), c);
            }
            else if ((int)bean.getSaletype() == 4) {
                selectPet(bean.getContiontype(), c);
            }
            else if ((int)bean.getSaletype() == 5) {
                selectEquip(bean.getContiontype(), c);
            }
        }
        if (bean.getOrder() == 1) {
            example.setOrderByClause("saleprice");
        }
        else if (bean.getOrder() == 2) {
            example.setOrderByClause("saleprice desc");
        }
        else {
            example.setOrderByClause("uptime desc");
        }
        c.andFlagEqualTo(Integer.valueOf(2));
        BasePageHelper.startPage((int)bean.getPageNum(), dataNum);
        List<Salegoods> list = AllServiceUtil.getSalegoodsService().selectByExample(example);
        PageInfo<Salegoods> pageInfo = new PageInfo(list);
        LoginResult role = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        List<BigDecimal> userCollection = AllServiceUtil.getCollectionService().selectUserCollection(role.getRole_id());
        SearchGoodsResultBean resultBean = new SearchGoodsResultBean();
        resultBean.setSalegoods(pageInfo.getList());
        resultBean.setTotal(pageInfo.getPages());
        resultBean.setCollections(userCollection);
        String msg = Agreement.getAgreement().CBGSearch1Agreement(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
    
    public static void selectProp(String contions, SalegoodsExample.Criteria ctr) {
        String a = "";
        String b = "";
        String c = "";
        String d = "729";
        String e = "54|55|56|57|58|59|60|61";
        String f = "188";
        String qt = "729|54|55|56|57|58|59|60|61|188";
        List<String> types = null;
        if ("1".equals(contions)) {
            types = SplitStringTool.splitString(a);
        }
        else if ("2".equals(contions)) {
            types = SplitStringTool.splitString(b);
        }
        else if ("3".equals(contions)) {
            types = SplitStringTool.splitString(c);
        }
        else if ("4".equals(contions)) {
            types = SplitStringTool.splitString(d);
        }
        else if ("5".equals(contions)) {
            types = SplitStringTool.splitString(e);
        }
        else if ("17".equals(contions)) {
            types = SplitStringTool.splitString(f);
        }
        if ("6".equals(contions)) {
            ctr.andContiontypeNotIn(SplitStringTool.splitString(qt));
        }
        else if (!"0".equals(contions)) {
            ctr.andContiontypeIn(types);
        }
    }
    
    public static void selectPet(String contions, SalegoodsExample.Criteria ctr) {
        String a = "0";
        String b = "1|5";
        String c = "6";
        String d = "2|3|4";
        List<String> types = null;
        if ("7".equals(contions)) {
            types = SplitStringTool.splitString(a);
        }
        else if ("8".equals(contions)) {
            types = SplitStringTool.splitString(b);
        }
        else if ("9".equals(contions)) {
            types = SplitStringTool.splitString(c);
        }
        else if ("10".equals(contions)) {
            types = SplitStringTool.splitString(d);
        }
        if (!"0".equals(contions)) {
            ctr.andContiontypeIn(types);
        }
    }
    
    public static void selectEquip(String contions, SalegoodsExample.Criteria ctr) {
        String a = "602|603|7002|7003|7004|800|6500|6800|6900";
        String b = "6500|6900|6601|6600|6701|6700|6900|6800";
        String c = "7000-7004";
        String d = "609|608|610|607|606|611";
        String e = "611|612";
        List<String> types = null;
        if ("11".equals(contions)) {
            types = SplitStringTool.splitString(a);
        }
        else if ("12".equals(contions)) {
            types = SplitStringTool.splitString(b);
        }
        else if ("13".equals(contions)) {
            types = SplitStringTool.splitString(c);
        }
        else if ("14".equals(contions)) {
            types = SplitStringTool.splitString(d);
        }
        else if ("15".equals(contions)) {
            types = SplitStringTool.splitString(e);
        }
        if (!"0".equals(contions) && !"6".equals(contions)) {
            ctr.andContiontypeIn(types);
        }
        else if ("6".equals(contions)) {
            String f = a + "|" + b + "|" + c + "|" + d + "|" + e;
            ctr.andContiontypeNotIn(SplitStringTool.splitString(f));
        }
    }
}
