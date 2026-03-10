package org.come.service;

import java.util.List;
import org.come.entity.Friend;

/**
 * 好友关系服务接口。
 */
public interface IFriendService
{
    boolean addFriend(Friend friend);
    
    void deleteFriend(Friend friend);
    
    List<Friend> allFriend();
}
