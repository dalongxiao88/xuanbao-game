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
    List<AppVersion> selectVersionUrl(String version, String sign);
    
    List<AppVersion> selectVersionBean(String versionType);
    
    String selectPhoneVersion();
    
    int deleteUserByCondition();
    
    List<Goodsrecord2> selectGoodsRecordByPage(String querySql, Integer pageNum);
    
    int updatePhoneVersion(String phoneVersion);
    
    List<Goodsrecord2> trackGoods(int rgid, int quid, int pageNum);
    
    List<ShopBuyTypeResult> selectShopBuyType();
    
    List<ShopBuyRecordResultBean> selectShopBuyRecord(ShopBuyRecordReqBean requestBean);
    
    String selectAtbyRoleid(BigDecimal roleId);
    
    int numRecordInsert(AreaNumRecord areaNumRecord);
    
    List<AreaNumRecord> weekRecordQuery(int pageNum);
    
    List<AreaNumRecord> selectRecordByPage(String areaName, String day);
    
    int hequupdate(String newAtid, String oldAtid);
    
    String selectAtid(String quid, String atid);
    
    List<String> hequSelectUserName();
    
    List<String> hequSelectRoleName();
    
    int hequupdateUserName(String newName, String oldName);
    
    int hequupdateRoleName(String newName, String oldName);
    
    int insertGoodsRecord(List<Goodsrecord2> goodsRecords);
    
    BigDecimal selectSequence();
}
