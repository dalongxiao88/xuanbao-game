package org.come.serviceImpl;

import java.util.List;
import org.come.entity.Friend;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.FriendMapper;
import org.come.service.IFriendService;

public class FriendServiceImpl implements IFriendService
{
    private FriendMapper friendMapper;
    
    public FriendServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.friendMapper = (FriendMapper)ctx.getBean("friendMapper");
    }
    
    @Override
    public boolean addFriend(Friend friend) {
        boolean isSuccess = this.friendMapper.addFriend(friend);
        return isSuccess;
    }
    
    @Override
    public void deleteFriend(Friend friend) {
        this.friendMapper.deleteFriend(friend);
    }
    
    @Override
    public List<Friend> allFriend() {
        return this.friendMapper.allFriend();
    }
}
