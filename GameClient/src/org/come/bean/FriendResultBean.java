package org.come.bean;

import org.come.entity.Friendtable;
import java.util.List;

/**
 * 好友列表返回对象。
 */
public class FriendResultBean
{
    private List<Friendtable> friendtables;
    
    public List<Friendtable> getFriendtables() {
        return this.friendtables;
    }

    /** 语义化别名：好友展示列表。 */
    public List<Friendtable> getFriendTables() {
        return this.friendtables;
    }
    
    public void setFriendtables(List<Friendtable> friendtables) {
        this.friendtables = friendtables;
    }

    public void setFriendTables(List<Friendtable> friendTables) {
        this.friendtables = friendTables;
    }
}
