package come.tool.Transplant;

import org.come.entity.Friend;
import java.util.List;

public class FriendTransplant
{
    List<Friend> friends;
    
    public FriendTransplant(List<Friend> friends) {
        this.friends = friends;
    }
    
    public List<Friend> getFriends() {
        return this.friends;
    }
    
    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
