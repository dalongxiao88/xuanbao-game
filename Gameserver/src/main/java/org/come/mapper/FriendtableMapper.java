package org.come.mapper;

import org.come.entity.Friendtable;
import java.util.List;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface FriendtableMapper
{
    List<Friendtable> selectFriendsByID(BigDecimal p0);
    
    List<Friendtable> selectAllFriend();
}
