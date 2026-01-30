package org.come.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.come.model.Shop;
import java.util.List;

public class ChongjipackBean
{
    private static final long serialVersionUID = 203745024714917095L;
    private Integer id;
    private Integer packtype;
    private Integer packgradetype;
    private String packgrade;
    private String packgoods;
    private Integer getnumber;
    private String datetime;
    private Integer canpaymoney;
    private String huitime;
    private List<Shop> shops;
    
    public static List<XXGDBean> getGoods(String goodses) {
        String[] split = goodses.split("\\|");
        List<XXGDBean> xxgdBeans = new ArrayList<>();
        for (int i = 0; i < split.length; ++i) {
            String[] split2 = split[i].split("=");
            String[] goods = split2[1].split("&");
            if (goods[0].equals("0")) {
                String[] canGetGoods = goods[1].split("\\$");
                XXGDBean bean = new XXGDBean();
                bean.setId(new BigDecimal(canGetGoods[0]));
                bean.setSum(Integer.parseInt(canGetGoods[1]));
                bean.setTag(Long.parseLong(canGetGoods[2]));
                xxgdBeans.add(bean);
            }
            else {
                String[] canGetGoods = goods[1].split("\\$");
                XXGDBean bean = new XXGDBean();
                bean.setId(new BigDecimal(canGetGoods[0]));
                bean.setSum(Integer.parseInt(canGetGoods[1]));
                xxgdBeans.add(bean);
            }
        }
        return xxgdBeans;
    }
    
    public static List<Shop> getShopList(List<ChongjipackBean> chongjipackBeans) {
        List<Shop> shops = new ArrayList<>();
        for (int i = 0; i < chongjipackBeans.size(); ++i) {
            ChongjipackBean chongjipackBean = (ChongjipackBean)chongjipackBeans.get(i);
            if (chongjipackBean.getShops() != null) {
                shops.addAll(chongjipackBean.getShops());
            }
        }
        return shops;
    }
    
    public static List<XXGDBean> getGoodsImpactGrade(String goodses) {
        String[] split = goodses.split("=");
        List<XXGDBean> xxgdBeans = new ArrayList<>();
        String[] goods = split[1].split("&");
        for (int i = 0; i < goods.length; ++i) {
            String[] canGetGoods = goods[i].split("\\$");
            XXGDBean bean = new XXGDBean();
            bean.setId(new BigDecimal(canGetGoods[0]));
            bean.setSum(Integer.parseInt(canGetGoods[1]));
            xxgdBeans.add(bean);
        }
        return xxgdBeans;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getPacktype() {
        return this.packtype;
    }
    
    public void setPacktype(Integer packtype) {
        this.packtype = packtype;
    }
    
    public Integer getPackgradetype() {
        return this.packgradetype;
    }
    
    public void setPackgradetype(Integer packgradetype) {
        this.packgradetype = packgradetype;
    }
    
    public String getPackgrade() {
        return this.packgrade;
    }
    
    public void setPackgrade(String packgrade) {
        this.packgrade = packgrade;
    }
    
    public String getPackgoods() {
        return this.packgoods;
    }
    
    public void setPackgoods(String packgoods) {
        this.packgoods = packgoods;
    }
    
    public Integer getGetnumber() {
        return this.getnumber;
    }
    
    public void setGetnumber(Integer getnumber) {
        this.getnumber = getnumber;
    }
    
    public String getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    public Integer getCanpaymoney() {
        return this.canpaymoney;
    }
    
    public void setCanpaymoney(Integer canpaymoney) {
        this.canpaymoney = canpaymoney;
    }
    
    public String getHuitime() {
        return this.huitime;
    }
    
    public void setHuitime(String huitime) {
        this.huitime = huitime;
    }
    
    public List<Shop> getShops() {
        return this.shops;
    }
    
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
    
    @Override
    public String toString() {
        return "ChongjipackBean [id=" + this.id + ", packtype=" + this.packtype + ", packgradetype=" + this.packgradetype + ", packgrade=" + this.packgrade + ", packgoods=" + this.packgoods + ", getnumber=" + this.getnumber + ", datetime=" + this.datetime + ", canpaymoney=" + this.canpaymoney + ", huitime=" + this.huitime + "]";
    }
}
