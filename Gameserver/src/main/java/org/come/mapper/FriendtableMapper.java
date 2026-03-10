package org.come.mapper;

import org.come.entity.Friendtable;
import java.util.List;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 好友展示信息数据访问接口。
 */
public interface FriendtableMapper
{
    List<Friendtable> selectFriendsByID(BigDecimal roleId);
    
    List<Friendtable> selectAllFriend();
}
