package org.come.service;

import org.come.entity.Friendtable;
import java.util.List;
import java.math.BigDecimal;

public interface IFriendtableService
{
    List<Friendtable> selectFriendsByID(BigDecimal p0);
    
    List<Friendtable> selectAllFriend();
}
