package org.come.serviceImpl;

import org.come.entity.Friendtable;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.FriendtableMapper;
import org.come.service.IFriendtableService;

public class FriendtableServiceImpl implements IFriendtableService
{
    private FriendtableMapper friendtableMapper;
    
    public FriendtableServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.friendtableMapper = (FriendtableMapper)ctx.getBean("friendtableMapper");
    }
    
    @Override
    public List<Friendtable> selectFriendsByID(BigDecimal roleid) {
        List<Friendtable> friendtables = this.friendtableMapper.selectFriendsByID(roleid);
        return friendtables;
    }
    
    @Override
    public List<Friendtable> selectAllFriend() {
        return this.friendtableMapper.selectAllFriend();
    }
}
