package org.come.mapper;

import java.util.List;
import org.come.entity.Friend;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface FriendMapper
{
    boolean addFriend(Friend p0);
    
    void deleteFriend(Friend p0);
    
    List<Friend> allFriend();
}
