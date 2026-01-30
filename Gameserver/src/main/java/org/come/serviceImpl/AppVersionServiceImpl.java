package org.come.serviceImpl;

import java.math.BigDecimal;
import org.come.extInterBean.AreaNumRecord;
import org.come.extInterBean.ShopBuyRecordResultBean;
import org.come.extInterBean.ShopBuyRecordReqBean;
import org.come.extInterBean.ShopBuyTypeResult;
import org.come.entity.AppVersion;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.tool.WriteOut;
import org.come.until.GsonUtil;
import org.come.handler.MainServerHandler;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import org.come.extInterBean.Goodsrecord2;
import java.util.List;
import org.come.mapper.AppVersionMapper;
import org.come.service.AppVersionService;

public class AppVersionServiceImpl implements AppVersionService
{
    private AppVersionMapper appVersionMapper;
    private static List<Goodsrecord2> list1;
    private static List<Goodsrecord2> list2;
    private static Object lock;
    private final int limit = 10;
    
    public static void add(Goodsrecord2 e) {
        synchronized (AppVersionServiceImpl.lock) {
            AppVersionServiceImpl.list1.add(e);
        }
    }
    
    public static synchronized void up() {
        List<Goodsrecord2> list = null;
        synchronized (AppVersionServiceImpl.lock) {
            list = AppVersionServiceImpl.list1;
            AppVersionServiceImpl.list1 = new ArrayList<>();
        }
        System.out.println("本次同步物品记录数量:" + list.size());
        while (list.size() != 0) {
            try {
                AppVersionServiceImpl.list2.clear();
                for (int i = 0, length = list.size(); i < length && i < 100; ++i) {
                    AppVersionServiceImpl.list2.add(list.remove(length - i - 1));
                }
                if (AppVersionServiceImpl.list2.size() != 0) {
                    AllServiceUtil.getAppVersionService().insertGoodsRecord(AppVersionServiceImpl.list2);
                }
                else {
                    continue;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                WriteOut.addtxt("同步物品记录报错:" + MainServerHandler.getErrorMessage(e) + ":" + GsonUtil.getGsonUtil().getgson().toJson(AppVersionServiceImpl.list2), 9999L);
            }
        }
    }
    
    public AppVersionServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.appVersionMapper = (AppVersionMapper)ctx.getBean("appVersionMapper");
    }
    
    @Override
    public int insertGoodsRecord(List<Goodsrecord2> goodslist) {
        return this.appVersionMapper.insertGoodsRecord(goodslist);
    }
    
    @Override
    public List<AppVersion> selectVersionUrl(String version, String sign) {
        return this.appVersionMapper.selectVersionUrl(version, sign);
    }
    
    @Override
    public String selectPhoneVersion() {
        return this.appVersionMapper.selectPhoneVersion();
    }
    
    @Override
    public int deleteUserByCondition() {
        return this.appVersionMapper.deleteUserByCondition();
    }
    
    @Override
    public List<Goodsrecord2> selectGoodsRecordByPage(String sql, Integer page) {
        int start = ((int)page - 1) * 10;
        int end = start + 10;
        List<Goodsrecord2> goodsRecord = this.appVersionMapper.selectGoodsRecordByPage(sql, start + 1, end + 1);
        return goodsRecord;
    }
    
    @Override
    public List<Goodsrecord2> trackGoods(int rgid, int quid, int page) {
        int start = (page - 1) * 10;
        int end = start + 10;
        List<Goodsrecord2> trackGoods = this.appVersionMapper.trackGoods(rgid, quid, start + 1, end + 1);
        return trackGoods;
    }
    
    @Override
    public List<ShopBuyTypeResult> selectShopBuyType() {
        return this.appVersionMapper.selectShopBuyType();
    }
    
    @Override
    public List<ShopBuyRecordResultBean> selectShopBuyRecord(ShopBuyRecordReqBean reqBean) {
        int start = ((int)Integer.valueOf(reqBean.getNowPage()) - 1) * 10;
        int end = start + 10;
        reqBean.setStart(start + 1);
        reqBean.setEnd(end + 1);
        List<ShopBuyRecordResultBean> selectShopBuyRecord = this.appVersionMapper.selectShopBuyRecord(reqBean);
        return selectShopBuyRecord;
    }
    
    @Override
    public int updatePhoneVersion(String version) {
        return this.appVersionMapper.updatePhoneVersion(version);
    }
    
    @Override
    public int numRecordInsert(AreaNumRecord areaNumRecord) {
        return this.appVersionMapper.numRecordInsert(areaNumRecord);
    }
    
    @Override
    public String selectAtbyRoleid(BigDecimal roleid) {
        return this.appVersionMapper.selectAtbyRoleid(roleid);
    }
    
    @Override
    public List<AreaNumRecord> weekRecordQuery(int page) {
        int start = (page - 1) * 10;
        int end = start + 10;
        return this.appVersionMapper.weekRecordQuery(start + 1, end + 1);
    }
    
    @Override
    public List<AreaNumRecord> selectRecordByPage(String areaName, String day) {
        return this.appVersionMapper.selectRecordByPage(areaName, day);
    }
    
    @Override
    public int hequupdate(String newAtid, String oldAtid) {
        return this.appVersionMapper.hequupdate(newAtid, oldAtid);
    }
    
    @Override
    public String selectAtid(String quid, String atid) {
        return this.appVersionMapper.selectAtid(quid, atid);
    }
    
    @Override
    public int hequupdateUserName(String newName, String oldName) {
        return this.appVersionMapper.hequupdateUserName(newName, oldName);
    }
    
    @Override
    public int hequupdateRoleName(String newName, String oldName) {
        return this.appVersionMapper.hequupdateRoleName(newName, oldName);
    }
    
    @Override
    public List<String> hequSelectUserName() {
        return this.appVersionMapper.hequSelectUserName();
    }
    
    @Override
    public List<String> hequSelectRoleName() {
        return this.appVersionMapper.hequSelectRoleName();
    }
    
    @Override
    public BigDecimal selectSequence() {
        return this.appVersionMapper.selectSequence();
    }
    
    @Override
    public List<AppVersion> selectVersionBean(String sign) {
        return this.appVersionMapper.selectVersionBean(sign);
    }
    
    static {
        AppVersionServiceImpl.list1 = new ArrayList<>();
        AppVersionServiceImpl.list2 = new ArrayList<>();
        AppVersionServiceImpl.lock = new ArrayList<>();
    }
}
