package org.come.serviceImpl;

import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import org.come.entity.*;
import org.come.mapper.CarMapper;
import org.come.redis.RedisCacheUtil;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.service.ICarService;
import org.come.until.MybatisUntil;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements ICarService
{
    private CarMapper carMapper;
    private final Integer limit;

    public CarServiceImpl() {
        this.limit = Integer.valueOf(10);
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.carMapper = (CarMapper)ctx.getBean("carMapper");
    }
    
    @Override
    public List<Car> selectMountsByRoleID(BigDecimal roleID) {
        List<Car> mounts = RedisControl.getS(RedisParameterUtil.CAR, roleID.toString(), Car.class);
        return mounts;
    }
    
    @Override
    public Car selectMountsByMID(BigDecimal mid) {
        Car mount = (Car)RedisControl.getV(RedisParameterUtil.CAR, mid.toString(), Car.class);
        return mount;
    }
    
    @Override
    public void deleteMountsByMid(BigDecimal roleID) {
        Car mount = (Car)RedisControl.getV(RedisParameterUtil.CAR, roleID.toString(), Car.class);
        if (mount != null) {
            RedisControl.deletrValue(RedisParameterUtil.CAR, mount.getRoleid().toString(), mount.getMid().toString());
            RedisControl.delForKey(RedisParameterUtil.CAR, mount.getMid().toString());
            RedisControl.insertController(RedisParameterUtil.CAR, mount.getMid().toString(), "3");
            RoleData data = RolePool.getRoleData(mount.getRoleid());
//            if (data != null) {
//                data.MPet(mount, false);
//            }
        }
    }
    
    @Override
    public void updateMount(Car mount) {
        Car mount2 = (Car)RedisControl.getV(RedisParameterUtil.CAR, mount.getMid().toString(), Car.class);
        if (mount2 != null) {
            RedisControl.insertKeyT(RedisParameterUtil.CAR, mount2.getMid().toString(), mount2);
            RedisControl.insertController(RedisParameterUtil.CAR, mount2.getMid().toString(), "2");
        }
    }
    
    @Override
    public void updateMountRedis(Car mount) {
        RedisControl.insertKeyT(RedisParameterUtil.CAR, mount.getMid().toString(), mount);
        RedisControl.insertController(RedisParameterUtil.CAR, mount.getMid().toString(), "2");
    }
    
    @Override
    public void insertMount(Car mount) {
        mount.setMid(RedisCacheUtil.getCar_pk());
        mount.setExp(Integer.valueOf(0));
        mount.setUseNumber(Integer.valueOf(0));
        mount.setProficiency(Integer.valueOf(0));
        List<MountSkill> mountskill = new ArrayList<>();
        mount.setMountskill(mountskill);
        RedisControl.insertKeyT(RedisParameterUtil.CAR, mount.getMid().toString(), mount);
        RedisControl.insertListRedis(RedisParameterUtil.CAR, mount.getRoleid().toString(), mount.getMid().toString());
        RedisControl.insertController(RedisParameterUtil.CAR, mount.getMid().toString(), "1");
    }
    
    @Override
    public List<Car> selectAllMounts() {
        return this.carMapper.selectAllMounts();
    }
    
    @Override
    public BigDecimal selectMaxID() {
        return this.carMapper.selectMaxID();
    }
    
    @Override
    public void deleteMountsByMidsql(BigDecimal roleID) {
        this.carMapper.deleteMountsByMid(roleID);
    }
    
    @Override
    public void updateMountsql(Car mount) {
        this.carMapper.updateMount(mount);
    }
    
    @Override
    public int updateMountForRid(Car mount) {
        int mid = this.carMapper.selectMountRole(mount);
        Car mount2 = (Car)RedisControl.getV(RedisParameterUtil.CAR, mid + "", Car.class);
        mount2.setSpri(mount.getSpri());
        mount2.setPower(mount.getPower());
        mount2.setBone(mount.getBone());
        if (mount2 != null) {
            RedisControl.insertKeyT(RedisParameterUtil.CAR, mount2.getMid().toString(), mount2);
            RedisControl.insertController(RedisParameterUtil.CAR, mount2.getMid().toString(), "2");
        }
        return this.carMapper.updateMountForRid(mount2);
    }
    
    @Override
    public void insertMountsql(Car mount) {
        this.carMapper.insertMount(mount);
    }
    
    @Override
    public List<CarRoleUser> selectMount(CarRoleUser mru) {
        Integer start = Integer.valueOf(((int)mru.getPageNum() - 1) * (int)this.limit + 1);
        Integer end = Integer.valueOf((int)start + (int)this.limit);
        mru.setStart(start);
        mru.setEnd(end);
        List<CarRoleUser> mountList = this.carMapper.selectMount(mru);
        return mountList;
    }
    
    @Override
    public Integer selectMountCount(CarRoleUser mru) {
        Integer mountCount = this.carMapper.selectMountCount(mru);
        return mountCount;
    }
    
    @Override
    public void deleteMountsByMidList(List<BigDecimal> roleID) {
        this.carMapper.deleteMountsByMidList(roleID);
    }
    
    @Override
    public void updateMountList(List<Car> mount) {
        this.carMapper.updateMountList(mount);
    }
    
    @Override
    public void insertMountList(List<Car> mount) {
        this.carMapper.insertMountList(mount);
    }
    
    @Override
    public void insertMountSingle(Car mount) {
        this.carMapper.insertMountSingle(mount);
    }
    
    @Override
    public Car selectMountByRoleIDAndMountID(Car mount) {
        return this.carMapper.selectMountByRoleIDAndMountID(mount);
    }
}
