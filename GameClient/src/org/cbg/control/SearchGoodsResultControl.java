package org.cbg.control;

import java.math.BigDecimal;
import org.cbg.entity.Salegoods;
import java.util.List;
import org.cbg.panel.TraslationWantBuyCardJapel;
import org.cbg.until.TraslationTableDahuabiUntil;
import org.cbg.until.TraslationTableUntil;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.GsonUtil;
import org.cbg.bean.SearchGoodsResultBean;
import org.come.action.FromServerAction;

public class SearchGoodsResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        SearchGoodsResultBean sa = (SearchGoodsResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, SearchGoodsResultBean.class);
        if (sa.getCollections() != null) {
            TrslationMainJframe.getTrslationMainJframe().setShoucangWupinList(sa.getCollections());
        }
        TraslationWantBuyCardJapel traslationWantBuyCardJapel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantBuyJpanel().getTraslationWantBuyCardJapel();
        switch (TrslationMainJframe.getTrslationMainJframe().getPanelOpen()) {
            case 0: {
                List<Salegoods> salegoodsList = sa.getSalegoods();
                List<BigDecimal> bigDecimalsList = sa.getCollections();
                traslationWantBuyCardJapel.getTraslationWantBuyShouyeCardJpanel().getTraslationWantBuyShouyeJpanel().getTraslationWantBuyShouyeModelJpanel().setZuixinshangjia(salegoodsList);
                traslationWantBuyCardJapel.getCardLayout().show(traslationWantBuyCardJapel, "shouye");
                break;
            }
            case 1: {
                TraslationTableUntil.setTableModel(traslationWantBuyCardJapel.getTraslationWantBuyShouyeCardJpanel().getTraslationWantBuyShouyeSousuojieguoJpanel().getjScrollPane(), sa.getSalegoods(), sa.getCollections());
                traslationWantBuyCardJapel.getTraslationWantBuyShouyeCardJpanel().getTraslationWantBuyShouyeSousuojieguoJpanel().setPage((sa.getTotal() == 0) ? 1 : sa.getTotal());
                traslationWantBuyCardJapel.getTraslationWantBuyShouyeCardJpanel().getCardLayout().show(traslationWantBuyCardJapel.getTraslationWantBuyShouyeCardJpanel(), "sousuojieguo");
                break;
            }
            case 2: {
                TraslationTableDahuabiUntil.setTableModel(traslationWantBuyCardJapel.getTraslationWantBuyDahuabiJpanel().getjScrollPane(), sa.getSalegoods(), sa.getCollections());
                traslationWantBuyCardJapel.getTraslationWantBuyDahuabiJpanel().setPage((sa.getTotal() == 0) ? 1 : sa.getTotal());
                traslationWantBuyCardJapel.getCardLayout().show(traslationWantBuyCardJapel, "dahuabi");
                break;
            }
            case 3: {
                TraslationTableUntil.setTableModel(traslationWantBuyCardJapel.getTraslationWantBuyDaojuJpanel().getjScrollPane(), sa.getSalegoods(), sa.getCollections());
                traslationWantBuyCardJapel.getTraslationWantBuyDaojuJpanel().setPage((sa.getTotal() == 0) ? 1 : sa.getTotal());
                traslationWantBuyCardJapel.getCardLayout().show(traslationWantBuyCardJapel, "daoju");
                break;
            }
            case 4: {
                TraslationTableUntil.setTableModel(traslationWantBuyCardJapel.getTraslationWantBuyZhaohuanshouJpanel().getjScrollPane(), sa.getSalegoods(), sa.getCollections());
                traslationWantBuyCardJapel.getTraslationWantBuyZhaohuanshouJpanel().setPage((sa.getTotal() == 0) ? 1 : sa.getTotal());
                traslationWantBuyCardJapel.getCardLayout().show(traslationWantBuyCardJapel, "zhaohuanshou");
                break;
            }
            case 5: {
                TraslationTableUntil.setTableModel(traslationWantBuyCardJapel.getTraslationWantBuyZhaungbeiJpanel().getjScrollPane(), sa.getSalegoods(), sa.getCollections());
                traslationWantBuyCardJapel.getTraslationWantBuyZhaungbeiJpanel().setPage((sa.getTotal() == 0) ? 1 : sa.getTotal());
                traslationWantBuyCardJapel.getCardLayout().show(traslationWantBuyCardJapel, "zhuangbei");
                break;
            }
            case 6: {
                TraslationTableUntil.setTableModel(traslationWantBuyCardJapel.getTraslationWantBuyLingbaoJpanel().getjScrollPane(), sa.getSalegoods(), sa.getCollections());
                traslationWantBuyCardJapel.getTraslationWantBuyLingbaoJpanel().setPage((sa.getTotal() == 0) ? 1 : sa.getTotal());
                traslationWantBuyCardJapel.getCardLayout().show(traslationWantBuyCardJapel, "lingbao");
                break;
            }
            case 7: {
                TraslationTableUntil.setTableModel(traslationWantBuyCardJapel.getTraslationWantBuyGongshiqiJpanel().getjScrollPane(), sa.getSalegoods(), sa.getCollections());
                traslationWantBuyCardJapel.getTraslationWantBuyGongshiqiJpanel().setPage((sa.getTotal() == 0) ? 1 : sa.getTotal());
                traslationWantBuyCardJapel.getCardLayout().show(traslationWantBuyCardJapel, "gongshiqi");
                break;
            }
        }
    }
}
