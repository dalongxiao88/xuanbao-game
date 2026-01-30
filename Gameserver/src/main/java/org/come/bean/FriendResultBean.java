package org.come.bean;

import org.come.entity.Friendtable;
import java.util.List;

public class FriendResultBean
{
    private List<Friendtable> friendtables;
    
    public List<Friendtable> getFriendtables() {
        return this.friendtables;
    }
    
    public void setFriendtables(List<Friendtable> friendtables) {
        this.friendtables = friendtables;
    }
}
