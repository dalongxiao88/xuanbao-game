package org.come.serviceImpl;

import java.util.List;
import java.math.BigDecimal;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.redis.RedisCacheUtil;
import org.come.entity.Baby;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.BabyMapper;
import org.come.service.IBabyService;

public class BabyServiceImpl implements IBabyService
{
    private BabyMapper babyMapper;
    
    public BabyServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.babyMapper = (BabyMapper)ctx.getBean("babyMapper");
    }
    
    @Override
    public void createBaby(Baby baby) {
        baby.setBabyid(RedisCacheUtil.getBaby_pk());
        baby.setQizhi(Integer.valueOf(0));
        baby.setNeili(Integer.valueOf(0));
        baby.setZhili(Integer.valueOf(0));
        baby.setNaili(Integer.valueOf(0));
        baby.setMingqi(Integer.valueOf(0));
        baby.setDaode(Integer.valueOf(0));
        baby.setPanni(Integer.valueOf(0));
        baby.setWanxing(Integer.valueOf(0));
        baby.setQingmi(Integer.valueOf(0));
        baby.setXiaoxin(Integer.valueOf(0));
        baby.setWenbao(Integer.valueOf(0));
        baby.setPilao(Integer.valueOf(0));
        baby.setYangyujin(Integer.valueOf(0));
        baby.setBabyage(Integer.valueOf(0));
        baby.setTalents("1=1|2=1|3=1");
        baby.setParts("-1|-1|-1|-1");
        RedisControl.insertKeyT(RedisParameterUtil.BABY, baby.getBabyid().toString(), baby);
        RedisControl.insertController(RedisParameterUtil.BABY, baby.getBabyid().toString(), "1");
        RedisControl.insertListRedis(RedisParameterUtil.BABY, baby.getRoleid().toString(), baby.getBabyid().toString());
    }
    
    @Override
    public List<Baby> selectBabyByRolename(BigDecimal roleid) {
        List<Baby> list = RedisControl.getS(RedisParameterUtil.BABY, roleid.toString(), Baby.class);
        return list;
    }
    
    @Override
    public Baby selectBabyById(BigDecimal babyid) {
        Baby baby = (Baby)RedisControl.getV(RedisParameterUtil.BABY, babyid.toString(), Baby.class);
        return baby;
    }
    
    @Override
    public void updateBaby(Baby baby) {
        Baby baby2 = (Baby)RedisControl.getV(RedisParameterUtil.BABY, baby.getBabyid().toString(), Baby.class);
        if (baby2 != null) {
            if (baby.getBabyname() != null) {
                baby2.setBabyname(baby.getBabyname());
            }
            if (baby.getQizhi() != null) {
                baby2.setQizhi(baby.getQizhi());
            }
            if (baby.getNeili() != null) {
                baby2.setNeili(baby.getNeili());
            }
            if (baby.getZhili() != null) {
                baby2.setZhili(baby.getZhili());
            }
            if (baby.getNaili() != null) {
                baby2.setNaili(baby.getNaili());
            }
            if (baby.getMingqi() != null) {
                baby2.setMingqi(baby.getMingqi());
            }
            if (baby.getDaode() != null) {
                baby2.setDaode(baby.getDaode());
            }
            if (baby.getPanni() != null) {
                baby2.setPanni(baby.getPanni());
            }
            if (baby.getQingmi() != null) {
                baby2.setQingmi(baby.getQingmi());
            }
            if (baby.getXiaoxin() != null) {
                baby2.setXiaoxin(baby.getXiaoxin());
            }
            if (baby.getWenbao() != null) {
                baby2.setWenbao(baby.getWenbao());
            }
            if (baby.getPilao() != null) {
                baby2.setPilao(baby.getPilao());
            }
            if (baby.getYangyujin() != null) {
                baby2.setYangyujin(baby.getYangyujin());
            }
            if (baby.getChildSex() != null) {
                baby2.setChildSex(baby.getChildSex());
            }
            if (baby.getBabyage() != null) {
                baby2.setBabyage(baby.getBabyage());
            }
            if (baby.getOutcome() != null) {
                baby2.setOutcome(baby.getOutcome());
            }
            if (baby.getTalents() != null) {
                baby2.setTalents(baby.getTalents());
            }
            if (baby.getParts() != null) {
                baby2.setParts(baby.getParts());
            }
            RedisControl.insertKeyT(RedisParameterUtil.BABY, baby2.getBabyid().toString(), baby2);
            RedisControl.insertController(RedisParameterUtil.BABY, baby2.getBabyid().toString(), "2");
        }
    }
    
    @Override
    public void updateBabyRedis(Baby baby) {
        RedisControl.insertKeyT(RedisParameterUtil.BABY, baby.getBabyid().toString(), baby);
        RedisControl.insertController(RedisParameterUtil.BABY, baby.getBabyid().toString(), "2");
    }
    
    @Override
    public List<Baby> selectAllBaby() {
        return this.babyMapper.selectAllBaby();
    }
    
    @Override
    public BigDecimal selectMaxID() {
        return this.babyMapper.selectMaxID();
    }
    
    @Override
    public void deleteBaby(Baby baby) {
        this.babyMapper.deleteBaby(baby);
    }
    
    @Override
    public void createBabysql(Baby baby) {
        this.babyMapper.createBaby(baby);
    }
    
    @Override
    public void updateBabysql(Baby baby) {
        this.babyMapper.updateBaby(baby);
    }
    
    @Override
    public void deleteBabysql(Baby baby) {
        this.babyMapper.deleteBaby(baby);
    }
    
    @Override
    public void deleteBabyList(List<BigDecimal> list) {
        this.babyMapper.deleteBabyList(list);
    }
    
    @Override
    public void createBabyList(List<Baby> list) {
        this.babyMapper.createBabyList(list);
    }
    
    @Override
    public void updateBabyList(List<Baby> list) {
        this.babyMapper.updateBabyList(list);
    }
    
    @Override
    public void createBabySingle(Baby baby) {
        this.babyMapper.createBabySingle(baby);
    }
    
    @Override
    public void deleteBabySingle(BigDecimal babyid) {
        Baby rbaby = (Baby)RedisControl.getV(RedisParameterUtil.BABY, babyid.toString(), Baby.class);
        if (rbaby != null) {
            RedisControl.deletrValue(RedisParameterUtil.BABY, rbaby.getRoleid().toString(), rbaby.getBabyid().toString());
            RedisControl.delForKey(RedisParameterUtil.BABY, rbaby.getBabyid().toString());
            RedisControl.insertController(RedisParameterUtil.BABY, rbaby.getBabyid().toString(), "3");
        }
        this.babyMapper.deleteBabySingle(babyid);
    }
}
