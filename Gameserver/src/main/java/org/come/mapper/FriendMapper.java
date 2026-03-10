package org.come.mapper;

import java.util.List;
import org.come.entity.Friend;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 好友关系数据访问接口。
 */
public interface FriendMapper
{
    boolean addFriend(Friend friend);
    
    void deleteFriend(Friend friend);
    
    List<Friend> allFriend();
}
