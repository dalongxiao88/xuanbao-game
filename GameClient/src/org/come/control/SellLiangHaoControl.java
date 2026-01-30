package org.come.control;

import java.util.Iterator;
import java.util.List;
import org.come.entity.SellLiangHaoBase;
import org.come.entity.SellLianghaoAuc;
import org.come.Frame.Teststatejframe;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.bean.MyLiangHaoAucBean;
import org.come.Frame.GetLiangHaoJframe;
import org.come.until.GsonUtil;
import org.come.bean.SearchSellLiangHaoResultBean;
import org.come.action.FromServerAction;

public class SellLiangHaoControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.startsWith("SELLLIST")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                SearchSellLiangHaoResultBean sa = (SearchSellLiangHaoResultBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SearchSellLiangHaoResultBean.class);
                GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoOneJpanel().setSlb(sa);
                if (sa.getXjDate() != null) {
                    GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoThreeJpanel().setXjDate(sa.getXjDate());
                }
            }
        }
        else if (mes.startsWith("AUCLIST")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                SearchSellLiangHaoResultBean sa = (SearchSellLiangHaoResultBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SearchSellLiangHaoResultBean.class);
                GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().setAlb(sa);
                if (sa.getXjDate() != null) {
                    GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoThreeJpanel().setXjDate(sa.getXjDate());
                }
            }
        }
        else if (mes.startsWith("MYAUCLIST")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                MyLiangHaoAucBean sa2 = (MyLiangHaoAucBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], MyLiangHaoAucBean.class);
                GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().setMyLiangHaoAucBean(sa2);
            }
        }
        else if (mes.startsWith("DROPLIANGHAO")) {
            String[] result = mes.split("\\|");
            if (result.length > 1) {
                RoleData.getRoleData().getLoginResult().setLianghaoexpire(null);
                RoleData.getRoleData().getLoginResult().setLiangHao(null);
                RoleData.getRoleData().getLoginResult().setLianghaotype(null);
                RoleData.getRoleData().getLoginResult().setContinueprice(null);
                ZhuFrame.getZhuJpanel().addPrompt2("你捐赠了靓号：" + result[1]);
                Teststatejframe.getTeststatejframe().getTeststateJpanel().getLabappeleid().setText(String.valueOf(RoleData.getRoleData().getLoginResult().getRole_id()));
            }
        }
        else if (mes.startsWith("ADDLHTIME")) {
            String[] result = mes.split("\\|");
            if (result.length > 1) {
                RoleData.getRoleData().getLoginResult().setLianghaoexpire(result[1]);
            }
        }
        else if (mes.startsWith("CHANGETYPE")) {
            String[] result = mes.split("\\|");
            if (result.length > 1) {
                RoleData.getRoleData().getLoginResult().setLianghaotype(Integer.valueOf(result[1]));
            }
        }
        else if (mes.startsWith("EXPLIANGHAO")) {
            String[] result = mes.split("\\|");
            if (result.length > 1) {
                RoleData.getRoleData().getLoginResult().setLianghaoexpire(null);
                RoleData.getRoleData().getLoginResult().setLiangHao(null);
                RoleData.getRoleData().getLoginResult().setLianghaotype(null);
                RoleData.getRoleData().getLoginResult().setContinueprice(null);
                ZhuFrame.getZhuJpanel().addPrompt2("你的靓号" + result[1] + "已过期");
            }
        }
        else if (mes.startsWith("DELAUC")) {
            String[] result = mes.split("\\|");
            if (result.length > 1) {
                SellLianghaoAuc sellLianghaoAuc = (SellLianghaoAuc)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SellLianghaoAuc.class);
                List<SellLianghaoAuc> slhs = GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().getMyLiangHaoAucBean().getSellLianghaoAucs();
                if (slhs != null && slhs.size() > 0) {
                    for (SellLianghaoAuc item : slhs) {
                        if (item.getId().equals(sellLianghaoAuc.getId())) {
                            item.setStatus(sellLianghaoAuc.getStatus());
                        }
                    }
                }
                List<SellLiangHaoBase> lhlist = GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().getnLhList();
                if (lhlist != null && lhlist.size() > 0) {
                    for (SellLiangHaoBase item2 : lhlist) {
                        if (item2.getLianghao().equals(sellLianghaoAuc.getLianghao()) && item2.getAucPrice() == sellLianghaoAuc.getAucPoint().intValue()) {
                            item2.setAucPrice(0);
                        }
                    }
                }
            }
        }
        else if (mes.startsWith("BUYLIANGHAO")) {
            String[] result = mes.split("\\|");
            if (result.length > 1) {
                SellLiangHaoBase sellLiangHaoBase = (SellLiangHaoBase)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SellLiangHaoBase.class);
                SearchSellLiangHaoResultBean ssb = GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoOneJpanel().getSlb();
                if (ssb.getSellLiangHaos() != null && ssb.getSellLiangHaos().size() > 0) {
                    for (SellLiangHaoBase item3 : ssb.getSellLiangHaos()) {
                        if (sellLiangHaoBase.getLianghao().equals(item3.getLianghao())) {
                            ssb.getSellLiangHaos().remove(item3);
                            return;
                        }
                    }
                }
            }
        }
        else if (mes.startsWith("BUYAUC")) {
            String[] result = mes.split("\\|");
            if (result.length > 1) {
                SellLianghaoAuc sellLianghaoAuc = (SellLianghaoAuc)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SellLianghaoAuc.class);
                List<SellLiangHaoBase> lhlist2 = GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().getnLhList();
                List<SellLianghaoAuc> myaucs = GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().getMyLiangHaoAucBean().getSellLianghaoAucs();
                if (lhlist2 != null && lhlist2.size() > 0) {
                    for (SellLiangHaoBase item2 : lhlist2) {
                        if (item2.getLianghao().equals(sellLianghaoAuc.getLianghao())) {
                            item2.setAucPrice(sellLianghaoAuc.getAucPoint().intValue());
                        }
                    }
                }
                if (myaucs != null && myaucs.size() > 0) {
                    for (SellLianghaoAuc item4 : myaucs) {
                        if (item4.getLianghao().equals(sellLianghaoAuc.getLianghao())) {
                            item4.setAucPoint(sellLianghaoAuc.getAucPoint());
                            item4.setOriginalprice(sellLianghaoAuc.getOriginalprice());
                            item4.setStatus(sellLianghaoAuc.getStatus());
                            item4.setBuyRoleId(sellLianghaoAuc.getBuyRoleId());
                            item4.setAucEndTime(sellLianghaoAuc.getAucEndTime());
                            item4.setId(sellLianghaoAuc.getId());
                        }
                    }
                }
            }
        }
    }
}
