package org.come.service;

import org.come.entity.Friendtable;
import java.util.List;
import java.math.BigDecimal;

/**
 * 好友展示信息服务接口。
 */
public interface IFriendtableService
{
    List<Friendtable> selectFriendsByID(BigDecimal roleId);
    
    List<Friendtable> selectAllFriend();
}
