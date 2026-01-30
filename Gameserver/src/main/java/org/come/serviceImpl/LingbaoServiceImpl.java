package org.come.serviceImpl;

import org.come.entity.LingbaoRoleUser;
import java.util.List;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import java.math.BigDecimal;
import org.come.redis.RedisCacheUtil;
import org.come.entity.Lingbao;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.LingbaoMapper;
import org.come.service.ILingbaoService;

public class LingbaoServiceImpl implements ILingbaoService
{
    private LingbaoMapper lingbaoMapper;
    private final Integer limit;
    
    public LingbaoServiceImpl() {
        this.limit = Integer.valueOf(10);
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.lingbaoMapper = (LingbaoMapper)ctx.getBean("lingbaoMapper");
    }
    
    @Override
    public void insertLingbao(Lingbao lingbao) {
        lingbao.setBaoid(RedisCacheUtil.getLingbao_pk());
        lingbao.setSkillsum(Integer.valueOf(0));
        lingbao.setFusum(Integer.valueOf(0));
        lingbao.setLingbaolvl(new BigDecimal(1));
        lingbao.setLingbaoexe(new BigDecimal(0));
        lingbao.setLingbaoqihe(0L);
        RedisControl.insertKeyT(RedisParameterUtil.LINGBAO, lingbao.getBaoid().toString(), lingbao);
        RedisControl.insertListRedis(RedisParameterUtil.LINGBAO, lingbao.getRoleid().toString(), lingbao.getBaoid().toString());
        RedisControl.insertController(RedisParameterUtil.LINGBAO, lingbao.getBaoid().toString(), "1");
    }
    
    @Override
    public List<Lingbao> selectLingbaoByRoleID(BigDecimal roleid) {
        List<Lingbao> lingbaos = RedisControl.getS(RedisParameterUtil.LINGBAO, roleid.toString(), Lingbao.class);
        return lingbaos;
    }
    
    @Override
    public Lingbao selectLingbaoByID(BigDecimal baoid) {
        Lingbao lingbao = (Lingbao)RedisControl.getV(RedisParameterUtil.LINGBAO, baoid.toString(), Lingbao.class);
        return lingbao;
    }
    
    @Override
    public void updateLingbaoIndex(Lingbao lingbao, BigDecimal role_id) {
        BigDecimal yrid = lingbao.getRoleid();
        BigDecimal nrid = role_id;
        lingbao.setRoleid(role_id);
        if (yrid != null && nrid != null && yrid.compareTo(nrid) != 0) {
            RedisControl.deletrValue(RedisParameterUtil.LINGBAO, yrid.toString(), lingbao.getBaoid().toString());
            RedisControl.insertListRedis(RedisParameterUtil.LINGBAO, nrid.toString(), lingbao.getBaoid().toString());
        }
        RedisControl.insertKeyT(RedisParameterUtil.LINGBAO, lingbao.getBaoid().toString(), lingbao);
        RedisControl.insertController(RedisParameterUtil.LINGBAO, lingbao.getBaoid().toString(), "2");
    }
    
    @Override
    public void updateLingbaoRedis(Lingbao lingbao) {
        RedisControl.insertKeyT(RedisParameterUtil.LINGBAO, lingbao.getBaoid().toString(), lingbao);
        RedisControl.insertController(RedisParameterUtil.LINGBAO, lingbao.getBaoid().toString(), "2");
    }
    
    @Override
    public void deleteLingbao(BigDecimal baoid) {
        Lingbao lingbao = (Lingbao)RedisControl.getV(RedisParameterUtil.LINGBAO, baoid.toString(), Lingbao.class);
        if (lingbao != null) {
            RedisControl.deletrValue(RedisParameterUtil.LINGBAO, lingbao.getRoleid().toString(), baoid.toString());
        }
        RedisControl.delForKey(RedisParameterUtil.LINGBAO, baoid.toString());
        RedisControl.insertController(RedisParameterUtil.LINGBAO, baoid.toString(), "3");
    }
    
    @Override
    public List<Lingbao> selectAllLingbao() {
        return this.lingbaoMapper.selectAllLingbao();
    }
    
    @Override
    public BigDecimal selectMaxID() {
        return this.lingbaoMapper.selectMaxID();
    }
    
    @Override
    public void updateLingbaosql(Lingbao lingbao) {
        this.lingbaoMapper.updateLingbao(lingbao);
    }
    
    @Override
    public void deleteLingbaosql(BigDecimal baoid) {
        this.lingbaoMapper.deleteLingbao(baoid);
    }
    
    @Override
    public void insertLingbaosql(Lingbao lingbao) {
        this.lingbaoMapper.insertLingbao(lingbao);
    }
    
    @Override
    public Lingbao selectByPrimaryKey(BigDecimal baoid) {
        Lingbao lingbao2 = (Lingbao)RedisControl.getV(RedisParameterUtil.LINGBAO, baoid.toString(), Lingbao.class);
        return lingbao2;
    }
    
    @Override
    public List<LingbaoRoleUser> selectLingBaoRU(LingbaoRoleUser lru) {
        Integer start = Integer.valueOf(((int)lru.getPageNow() - 1) * (int)this.limit + 1);
        Integer end = Integer.valueOf((int)start + (int)this.limit);
        lru.setStart(start);
        lru.setEnd(end);
        List<LingbaoRoleUser> LingBaoRUList = this.lingbaoMapper.selectLingBaoRU(lru);
        return LingBaoRUList;
    }
    
    @Override
    public Integer selectLingBaoRUCount(LingbaoRoleUser lru) {
        Integer count = this.lingbaoMapper.selectLingBaoRUCount(lru);
        return count;
    }
    
    @Override
    public void deleteLingbaoList(List<BigDecimal> list) {
        this.lingbaoMapper.deleteLingbaoList(list);
    }
    
    @Override
    public void insertLingbaoList(List<Lingbao> list) {
        this.lingbaoMapper.insertLingbaoList(list);
    }
    
    @Override
    public void updateLingbaoList(List<Lingbao> list) {
        this.lingbaoMapper.updateLingbaoList(list);
    }
}
