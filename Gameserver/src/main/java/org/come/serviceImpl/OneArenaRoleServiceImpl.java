package org.come.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import come.tool.oneArena.OneArenaRole;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.OneArenaRoleMapper;
import org.come.service.OneArenaRoleService;

public class OneArenaRoleServiceImpl implements OneArenaRoleService
{
    private OneArenaRoleMapper oneArenaRoleMapper;
    
    public OneArenaRoleServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.oneArenaRoleMapper = (OneArenaRoleMapper)ctx.getBean("oneArenaRoleMapper");
    }
    
    @Override
    public int insertOneArenaRole(OneArenaRole role) {
        return this.oneArenaRoleMapper.insertOneArenaRole(role);
    }
    
    @Override
    public List<OneArenaRole> selectRankRoles(List<Integer> list) {
        return this.oneArenaRoleMapper.selectRankRoles(list);
    }
    
    @Override
    public OneArenaRole selectRole(BigDecimal roleID) {
        return this.oneArenaRoleMapper.selectRole(roleID);
    }
    
    @Override
    public int updateDayReset() {
        return this.oneArenaRoleMapper.updateDayReset();
    }
    
    @Override
    public int updateRankRole(BigDecimal roleID, int rank, String skin, String name, int lvl) {
        return this.oneArenaRoleMapper.updateRankRole(roleID, rank, skin, name, lvl);
    }
    
    @Override
    public int selectRank(BigDecimal roleID) {
        Integer selectRank = this.oneArenaRoleMapper.selectRank(roleID);
        return (selectRank == null) ? 0 : ((int)selectRank);
    }
    
    @Override
    public int selectRankPast(BigDecimal roleID) {
        Integer selectRankPast = this.oneArenaRoleMapper.selectRankPast(roleID);
        return (selectRankPast == null) ? 0 : ((int)selectRankPast);
    }
    
    @Override
    public int updateDayResetRole(BigDecimal roleID) {
        return this.oneArenaRoleMapper.updateDayResetRole(roleID);
    }
}
