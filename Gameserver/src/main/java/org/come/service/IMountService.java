package org.come.service;

import org.come.entity.MountRoleUser;
import java.math.BigDecimal;
import org.come.entity.Mount;
import java.util.List;

public interface IMountService
{
    List<Mount> selectAllMounts();
    
    List<Mount> selectMountsByRoleID(BigDecimal roleId);
    
    Mount selectMountByRoleIDAndMountID(Mount mount);
    
    Mount selectMountsByMID(BigDecimal mountId);
    
    void deleteMountsByMid(BigDecimal mountId);
    
    void updateMount(Mount mount);
    
    int updateMountForRid(Mount mount);
    
    void updateMountRedis(Mount mount);
    
    void insertMount(Mount mount);
    
    void deleteMountsByMidsql(BigDecimal mountId);
    
    void updateMountsql(Mount mount);
    
    void insertMountsql(Mount mount);
    
    BigDecimal selectMaxID();
    
    List<MountRoleUser> selectMount(MountRoleUser mountRoleUser);
    
    Integer selectMountCount(MountRoleUser mountRoleUser);
    
    void deleteMountsByMidList(List<BigDecimal> mountIds);
    
    void updateMountList(List<Mount> mountList);
    
    void insertMountList(List<Mount> mountList);
    
    void insertMountSingle(Mount mount);
}
