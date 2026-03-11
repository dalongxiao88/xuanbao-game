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
/**
 * AppVersionMapper 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

@MyBatisAnnotation
public interface AppVersionMapper
{
    List<AppVersion> selectVersionUrl(@Param("version") String version, @Param("sign") String sign);
    
    String selectPhoneVersion();
    
    int deleteUserByCondition();
    
    List<Goodsrecord2> selectGoodsRecordByPage(@Param("sql") String querySql, @Param("start") int start, @Param("end") int end);
    
    List<Goodsrecord2> trackGoods(@Param("rgid") int rgid, @Param("quid") int quid, @Param("start") int start, @Param("end") int end);
    
    int updatePhoneVersion(String phoneVersion);
    
    List<ShopBuyTypeResult> selectShopBuyType();
    
    List<ShopBuyRecordResultBean> selectShopBuyRecord(ShopBuyRecordReqBean requestBean);
    
    String selectAtbyRoleid(BigDecimal roleId);
    
    int numRecordInsert(AreaNumRecord areaNumRecord);
    
    List<AreaNumRecord> weekRecordQuery(@Param("start") int start, @Param("end") int end);
    
    List<AreaNumRecord> selectRecordByPage(@Param("areaName") String areaName, @Param("day") String day);
    
    int hequupdate(@Param("newAtid") String newAtid, @Param("oldAtid") String oldAtid);
    
    String selectAtid(@Param("quid") String quid, @Param("atid") String atid);
    
    List<String> hequSelectUserName();
    
    List<String> hequSelectRoleName();
    
    int hequupdateUserName(@Param("newName") String newName, @Param("oldName") String oldName);
    
    int hequupdateRoleName(@Param("newName") String newName, @Param("oldName") String oldName);
    
    int insertGoodsRecord(List<Goodsrecord2> goodsRecords);
    
    BigDecimal selectSequence();
    
    List<AppVersion> selectVersionBean(String versionType);
}

