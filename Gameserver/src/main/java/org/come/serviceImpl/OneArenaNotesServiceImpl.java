package org.come.serviceImpl;

import java.util.List;
import java.math.BigDecimal;
import org.come.redis.RedisCacheUtil;
import come.tool.oneArena.OneArenaNotes;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.OneArenaNotesMapper;
import org.come.service.OneArenaNotesService;

public class OneArenaNotesServiceImpl implements OneArenaNotesService
{
    private OneArenaNotesMapper oneArenaNotesMapper;
    
    public OneArenaNotesServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.oneArenaNotesMapper = (OneArenaNotesMapper)ctx.getBean("oneArenaNotesMapper");
    }
    
    @Override
    public int insertOneArenaNotes(OneArenaNotes notes) {
        notes.setId(RedisCacheUtil.getOneAreanNotes_pk().longValue());
        return this.oneArenaNotesMapper.insertOneArenaNotes(notes);
    }
    
    @Override
    public BigDecimal selectMaxID(String time) {
        return this.oneArenaNotesMapper.selectMaxID(time);
    }
    
    @Override
    public List<OneArenaNotes> selectRole(BigDecimal roleId, BigDecimal min) {
        return this.oneArenaNotesMapper.selectRole(roleId, min);
    }
}
