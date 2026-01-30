package org.come.mapper;

import org.come.extInterBean.AreaNumRecord;
import java.math.BigDecimal;
import org.come.extInterBean.ShopBuyRecordResultBean;
import org.come.extInterBean.ShopBuyRecordReqBean;
import org.come.extInterBean.ShopBuyTypeResult;
import org.come.extInterBean.Goodsrecord2;
import org.come.entity.AppVersion;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface AppVersionMapper
{
    List<AppVersion> selectVersionUrl(@Param("version") String p0, @Param("sign") String p1);
    
    String selectPhoneVersion();
    
    int deleteUserByCondition();
    
    List<Goodsrecord2> selectGoodsRecordByPage(@Param("sql") String p0, @Param("start") int p1, @Param("end") int p2);
    
    List<Goodsrecord2> trackGoods(@Param("rgid") int p0, @Param("quid") int p1, @Param("start") int p2, @Param("end") int p3);
    
    int updatePhoneVersion(String p0);
    
    List<ShopBuyTypeResult> selectShopBuyType();
    
    List<ShopBuyRecordResultBean> selectShopBuyRecord(ShopBuyRecordReqBean p0);
    
    String selectAtbyRoleid(BigDecimal p0);
    
    int numRecordInsert(AreaNumRecord p0);
    
    List<AreaNumRecord> weekRecordQuery(@Param("start") int p0, @Param("end") int p1);
    
    List<AreaNumRecord> selectRecordByPage(@Param("areaName") String p0, @Param("day") String p1);
    
    int hequupdate(@Param("newAtid") String p0, @Param("oldAtid") String p1);
    
    String selectAtid(@Param("quid") String p0, @Param("atid") String p1);
    
    List<String> hequSelectUserName();
    
    List<String> hequSelectRoleName();
    
    int hequupdateUserName(@Param("newName") String p0, @Param("oldName") String p1);
    
    int hequupdateRoleName(@Param("newName") String p0, @Param("oldName") String p1);
    
    int insertGoodsRecord(List<Goodsrecord2> p0);
    
    BigDecimal selectSequence();
    
    List<AppVersion> selectVersionBean(String p0);
}
