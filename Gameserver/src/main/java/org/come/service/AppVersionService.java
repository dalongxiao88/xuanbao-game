package org.come.service;

import org.come.extInterBean.AreaNumRecord;
import java.math.BigDecimal;
import org.come.extInterBean.ShopBuyRecordResultBean;
import org.come.extInterBean.ShopBuyRecordReqBean;
import org.come.extInterBean.ShopBuyTypeResult;
import org.come.extInterBean.Goodsrecord2;
import org.come.entity.AppVersion;
import java.util.List;

public interface AppVersionService
{
    List<AppVersion> selectVersionUrl(String p0, String p1);
    
    List<AppVersion> selectVersionBean(String p0);
    
    String selectPhoneVersion();
    
    int deleteUserByCondition();
    
    List<Goodsrecord2> selectGoodsRecordByPage(String p0, Integer p1);
    
    int updatePhoneVersion(String p0);
    
    List<Goodsrecord2> trackGoods(int p0, int p1, int p2);
    
    List<ShopBuyTypeResult> selectShopBuyType();
    
    List<ShopBuyRecordResultBean> selectShopBuyRecord(ShopBuyRecordReqBean p0);
    
    String selectAtbyRoleid(BigDecimal p0);
    
    int numRecordInsert(AreaNumRecord p0);
    
    List<AreaNumRecord> weekRecordQuery(int p0);
    
    List<AreaNumRecord> selectRecordByPage(String p0, String p1);
    
    int hequupdate(String p0, String p1);
    
    String selectAtid(String p0, String p1);
    
    List<String> hequSelectUserName();
    
    List<String> hequSelectRoleName();
    
    int hequupdateUserName(String p0, String p1);
    
    int hequupdateRoleName(String p0, String p1);
    
    int insertGoodsRecord(List<Goodsrecord2> p0);
    
    BigDecimal selectSequence();
}
