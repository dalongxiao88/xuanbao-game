package org.come.service;

import org.come.entity.MountRoleUser;
import java.math.BigDecimal;
import org.come.entity.Mount;
import java.util.List;

public interface IMountService
{
    List<Mount> selectAllMounts();
    
    List<Mount> selectMountsByRoleID(BigDecimal p0);
    
    Mount selectMountByRoleIDAndMountID(Mount p0);
    
    Mount selectMountsByMID(BigDecimal p0);
    
    void deleteMountsByMid(BigDecimal p0);
    
    void updateMount(Mount p0);
    
    int updateMountForRid(Mount p0);
    
    void updateMountRedis(Mount p0);
    
    void insertMount(Mount p0);
    
    void deleteMountsByMidsql(BigDecimal p0);
    
    void updateMountsql(Mount p0);
    
    void insertMountsql(Mount p0);
    
    BigDecimal selectMaxID();
    
    List<MountRoleUser> selectMount(MountRoleUser p0);
    
    Integer selectMountCount(MountRoleUser p0);
    
    void deleteMountsByMidList(List<BigDecimal> p0);
    
    void updateMountList(List<Mount> p0);
    
    void insertMountList(List<Mount> p0);
    
    void insertMountSingle(Mount p0);
}
