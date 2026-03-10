package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.MountRoleUser;
import java.math.BigDecimal;
import org.come.entity.Mount;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface MountMapper
{
    List<Mount> selectAllMounts();
    
    List<Mount> selectMountsByRoleID(BigDecimal roleId);
    
    Mount selectMountByRoleIDAndMountID(Mount mount);
    
    Mount selectMountsByMID(BigDecimal mountId);
    
    void deleteMountsByMid(BigDecimal mountId);
    
    void updateMount(Mount mount);
    
    int updateMountForRid(Mount mount);
    
    int selectMountRole(Mount mount);
    
    void insertMount(Mount mount);
    
    BigDecimal selectMaxID();
    
    List<MountRoleUser> selectMount(@Param("mru") MountRoleUser mountRoleUser);
    
    Integer selectMountCount(@Param("mru") MountRoleUser mountRoleUser);
    
    void deleteMountsByMidList(List<BigDecimal> mountIds);
    
    void updateMountList(List<Mount> mountList);
    
    void insertMountList(List<Mount> mountList);
    
    void insertMountSingle(Mount mount);
}
