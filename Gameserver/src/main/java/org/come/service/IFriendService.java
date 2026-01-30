package org.come.service;

import java.util.List;
import org.come.entity.Friend;

public interface IFriendService
{
    boolean addFriend(Friend p0);
    
    void deleteFriend(Friend p0);
    
    List<Friend> allFriend();
}
