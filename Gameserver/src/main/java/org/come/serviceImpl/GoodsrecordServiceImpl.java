package org.come.serviceImpl;

import java.text.SimpleDateFormat;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.gl.model.Param;
import com.google.gson.Gson;
import com.github.pagehelper.BasePageHelper;
import com.google.gson.GsonBuilder;
import com.github.pagehelper.PageInfo;
import org.come.redis.RedisCacheUtil;
import org.come.extInterBean.Goodsrecord2;
import org.come.until.AllServiceUtil;
import java.util.Date;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.entity.GoodsrecordExample;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import java.util.List;
import org.come.entity.Goodsrecord;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.GoodsrecordMapper;
import org.come.service.IGoodsrecordService;

public class GoodsrecordServiceImpl implements IGoodsrecordService
{
    private GoodsrecordMapper goodsrecordMapper;
    
    public GoodsrecordServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.goodsrecordMapper = (GoodsrecordMapper)ctx.getBean("goodsrecordMapper", GoodsrecordMapper.class);
    }
    
    @Override
    public int insertGoodsrecordRoel(Goodsrecord goodsrecord) {
        return this.goodsrecordMapper.insertGoodsrecordRoel(goodsrecord);
    }
    
    @Override
    public List<Goodsrecord> selectGoodsrecordList(Goodsrecord goodsrecord) {
        List<Goodsrecord> mount2 = RedisControl.getS(RedisParameterUtil.GOODS_RECORD, "", Goodsrecord.class);
        return mount2;
    }
    
    @Override
    public int countByExample(GoodsrecordExample example) {
        return 0;
    }
    
    @Override
    public int deleteByExample(GoodsrecordExample example) {
        return 0;
    }
    
    @Override
    public int deleteByPrimaryKey(BigDecimal grid) {
        return 0;
    }
    
    @Override
    public int insertGoodsRecordNew(Goodstable goodstable, BigDecimal roleId, BigDecimal otherrole, Integer goodsnum, Integer recordtype, String aName, String bName) {
        try {
            Goodsrecord goodsrecord = new Goodsrecord();
            goodsrecord.setRoleid(roleId);
            goodsrecord.setOtherrole(otherrole);
            goodsrecord.setRecordtime(new Date());
            goodsrecord.setRolename(aName);
            goodsrecord.setOthername(bName);
            goodsrecord.setRecordtype(recordtype);
            goodsrecord.setGoodsnum(((int)recordtype == 2) ? goodsnum : goodstable.getUsetime());
            goodsrecord.setGoods(goodstable.getGoodsid() + "," + goodstable.getGoodsname());
            AllServiceUtil.getGoodsrecordService().insertGoodsrecordRoel(goodsrecord);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public int insert(Goodstable goodstable, BigDecimal otherrole, Integer goodsnum, Integer recordtype) {
        try {
            Goodsrecord2 goodsrecord = new Goodsrecord2();
            goodsrecord.setGrid(Long.valueOf(RedisCacheUtil.getRecord_pk()));
            goodsrecord.setRoleid(goodstable.getRole_id());
            goodsrecord.setOtherrole(otherrole);
            goodsrecord.setRecordtype(recordtype);
            goodsrecord.setGoodsnum(goodsnum);
            setGoodsRecordValue(goodstable, goodsrecord);
            AppVersionServiceImpl.add(goodsrecord);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public int insertGoodsrecord(BigDecimal roleID, BigDecimal otherID, int type, BigDecimal id, String name, String value, int num) {
        try {
            Goodsrecord2 goodsrecord = new Goodsrecord2();
            goodsrecord.setGrid(Long.valueOf(RedisCacheUtil.getRecord_pk()));
            goodsrecord.setRoleid(roleID);
            goodsrecord.setOtherrole(otherID);
            goodsrecord.setRecordtype(Integer.valueOf(type));
            goodsrecord.setGoodsnum(Integer.valueOf(num));
            goodsrecord.setGoodsname(name);
            goodsrecord.setRgid(id);
            goodsrecord.setValue(value);
            AppVersionServiceImpl.add(goodsrecord);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public int insertSelective(Goodsrecord record) {
        this.goodsrecordMapper.insert(record);
        return 0;
    }
    
    @Override
    public List<Goodsrecord> selectByExample(GoodsrecordExample example) {
        return this.goodsrecordMapper.selectByExample(example);
    }
    
    @Override
    public Goodsrecord selectByPrimaryKey(Integer grid) {
        return this.goodsrecordMapper.selectByPrimaryKey(grid);
    }
    
    @Override
    public int updateByExampleSelective(Goodsrecord record, GoodsrecordExample example) {
        return 0;
    }
    
    @Override
    public int updateByExample(Goodsrecord record, GoodsrecordExample example) {
        return 0;
    }
    
    @Override
    public int updateByPrimaryKeySelective(Goodsrecord record) {
        return 0;
    }
    
    @Override
    public int updateByPrimaryKey(Goodsrecord record) {
        return 0;
    }
    
    @Override
    public PageInfo<Goodsrecord> selectGoodsRecord(Integer pageNum, String condition) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Goodsrecord goodsrecord = (Goodsrecord)gson.fromJson(condition, Goodsrecord.class);
        BasePageHelper.startPage((int)pageNum, 8);
        List<Goodsrecord> list = this.goodsrecordMapper.selectGoodsRecord(goodsrecord);
        PageInfo<Goodsrecord> pageinfo = new PageInfo(list);
        return pageinfo;
    }
    
    public static void setGoodsRecordValue(Goodstable goodstable, Goodsrecord2 goodsrecord) {
        goodsrecord.setGoodsname(goodstable.getGoodsname());
        goodsrecord.setValue(goodstable.getValue());
        goodsrecord.setUsetime(goodstable.getUsetime() + "");
        goodsrecord.setGoodsid(goodstable.getGoodsid());
        goodsrecord.setSkin(goodstable.getSkin());
        goodsrecord.setType(Long.valueOf(goodstable.getType()));
        if (goodstable.getQuality() == null) {
            goodsrecord.setQuality(Long.valueOf(0L));
        }
        else {
            goodsrecord.setQuality(Long.valueOf((long)goodstable.getQuality()));
        }
        goodsrecord.setRgid(goodstable.getRgid());
        if (goodstable.getStatus() == null) {
            goodsrecord.setStatus(Integer.valueOf(0));
        }
        else {
            goodsrecord.setStatus(Integer.valueOf((int)goodstable.getStatus()));
        }
        goodsrecord.setGoodlock(Integer.valueOf(0));
    }
    
    @Override
    public PageInfo<Goodsrecord> selectGoodsRecordNew(Param param) {
        BasePageHelper.startPage(param.getPageNum(), param.getPageSize());
        Goodsrecord goodsrecord = new Goodsrecord();
        goodsrecord.setRecordtype(param.getType());
        if (CharSequenceUtil.isNotBlank(param.getRoleName())) {
            goodsrecord.setRolename(param.getRoleName());
        }
        if (ObjectUtil.isNotEmpty(param.getRoleId())) {
            goodsrecord.setRoleid(new BigDecimal((int)param.getRoleId()));
        }
        List<Goodsrecord> list = this.goodsrecordMapper.selectGoodsRecord(goodsrecord);
        list.forEach(g/* org.come.entity.Goodsrecord, */ -> g.setRecordtimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(g.getRecordtime())));
        PageInfo<Goodsrecord> pageinfo = new PageInfo(list);
        return pageinfo;
    }
    @Override
    public List<Goodsrecord> selectGoodsRecordByType(Integer type) {
        return goodsrecordMapper.selectGoodsRecordByType(type);
    }
}
