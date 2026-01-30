package org.come.serviceImpl;

import org.come.entity.MountRoleUser;
import org.come.entity.MountSkill;
import java.util.ArrayList;
import org.come.redis.RedisCacheUtil;
import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.entity.Mount;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.MountMapper;
import org.come.service.IMountService;

public class MountServiceImpl implements IMountService
{
    private MountMapper mountMapper;
    private final Integer limit;
    
    public MountServiceImpl() {
        this.limit = Integer.valueOf(10);
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.mountMapper = (MountMapper)ctx.getBean("mountMapper");
    }
    
    @Override
    public List<Mount> selectMountsByRoleID(BigDecimal roleID) {
        List<Mount> mounts = RedisControl.getS(RedisParameterUtil.MOUNT, roleID.toString(), Mount.class);
        return mounts;
    }
    
    @Override
    public Mount selectMountsByMID(BigDecimal mid) {
        Mount mount = (Mount)RedisControl.getV(RedisParameterUtil.MOUNT, mid.toString(), Mount.class);
        return mount;
    }
    
    @Override
    public void deleteMountsByMid(BigDecimal roleID) {
        Mount mount = (Mount)RedisControl.getV(RedisParameterUtil.MOUNT, roleID.toString(), Mount.class);
        if (mount != null) {
            RedisControl.deletrValue(RedisParameterUtil.MOUNT, mount.getRoleid().toString(), mount.getMid().toString());
            RedisControl.delForKey(RedisParameterUtil.MOUNT, mount.getMid().toString());
            RedisControl.insertController(RedisParameterUtil.MOUNT, mount.getMid().toString(), "3");
            RoleData data = RolePool.getRoleData(mount.getRoleid());
            if (data != null) {
                data.MPet(mount, false);
            }
        }
    }
    
    @Override
    public void updateMount(Mount mount) {
        Mount mount2 = (Mount)RedisControl.getV(RedisParameterUtil.MOUNT, mount.getMid().toString(), Mount.class);
        if (mount2 != null) {
            RedisControl.insertKeyT(RedisParameterUtil.MOUNT, mount2.getMid().toString(), mount2);
            RedisControl.insertController(RedisParameterUtil.MOUNT, mount2.getMid().toString(), "2");
        }
    }
    
    @Override
    public void updateMountRedis(Mount mount) {
        RedisControl.insertKeyT(RedisParameterUtil.MOUNT, mount.getMid().toString(), mount);
        RedisControl.insertController(RedisParameterUtil.MOUNT, mount.getMid().toString(), "2");
    }
    
    @Override
    public void insertMount(Mount mount) {
        mount.setMid(RedisCacheUtil.getMount_pk());
        mount.setExp(Integer.valueOf(0));
        mount.setUseNumber(Integer.valueOf(0));
        mount.setProficiency(Integer.valueOf(0));
        List<MountSkill> mountskill = new ArrayList<>();
        mount.setMountskill(mountskill);
        RedisControl.insertKeyT(RedisParameterUtil.MOUNT, mount.getMid().toString(), mount);
        RedisControl.insertListRedis(RedisParameterUtil.MOUNT, mount.getRoleid().toString(), mount.getMid().toString());
        RedisControl.insertController(RedisParameterUtil.MOUNT, mount.getMid().toString(), "1");
    }
    
    @Override
    public List<Mount> selectAllMounts() {
        return this.mountMapper.selectAllMounts();
    }
    
    @Override
    public BigDecimal selectMaxID() {
        return this.mountMapper.selectMaxID();
    }
    
    @Override
    public void deleteMountsByMidsql(BigDecimal roleID) {
        this.mountMapper.deleteMountsByMid(roleID);
    }
    
    @Override
    public void updateMountsql(Mount mount) {
        this.mountMapper.updateMount(mount);
    }
    
    @Override
    public int updateMountForRid(Mount mount) {
        int mid = this.mountMapper.selectMountRole(mount);
        Mount mount2 = (Mount)RedisControl.getV(RedisParameterUtil.MOUNT, mid + "", Mount.class);
        mount2.setSpri(mount.getSpri());
        mount2.setPower(mount.getPower());
        mount2.setBone(mount.getBone());
        if (mount2 != null) {
            RedisControl.insertKeyT(RedisParameterUtil.MOUNT, mount2.getMid().toString(), mount2);
            RedisControl.insertController(RedisParameterUtil.MOUNT, mount2.getMid().toString(), "2");
        }
        return this.mountMapper.updateMountForRid(mount2);
    }
    
    @Override
    public void insertMountsql(Mount mount) {
        this.mountMapper.insertMount(mount);
    }
    
    @Override
    public List<MountRoleUser> selectMount(MountRoleUser mru) {
        Integer start = Integer.valueOf(((int)mru.getPageNum() - 1) * (int)this.limit + 1);
        Integer end = Integer.valueOf((int)start + (int)this.limit);
        mru.setStart(start);
        mru.setEnd(end);
        List<MountRoleUser> mountList = this.mountMapper.selectMount(mru);
        return mountList;
    }
    
    @Override
    public Integer selectMountCount(MountRoleUser mru) {
        Integer mountCount = this.mountMapper.selectMountCount(mru);
        return mountCount;
    }
    
    @Override
    public void deleteMountsByMidList(List<BigDecimal> roleID) {
        this.mountMapper.deleteMountsByMidList(roleID);
    }
    
    @Override
    public void updateMountList(List<Mount> mount) {
        this.mountMapper.updateMountList(mount);
    }
    
    @Override
    public void insertMountList(List<Mount> mount) {
        this.mountMapper.insertMountList(mount);
    }
    
    @Override
    public void insertMountSingle(Mount mount) {
        this.mountMapper.insertMountSingle(mount);
    }
    
    @Override
    public Mount selectMountByRoleIDAndMountID(Mount mount) {
        return this.mountMapper.selectMountByRoleIDAndMountID(mount);
    }
}
