package org.come.task;

import org.come.handler.SendMessage;
import org.come.server.GameServer;
import java.util.ArrayList;
import java.util.List;

public class MonsterMatch
{
    private int count;
    private int countDown;
    private List<String> matchs;
    private String TS;
    
    public MonsterMatch() {
        this.count = 10;
        this.countDown = 10;
        this.matchs = new ArrayList<>();
    }
    
    public MonsterMatch(int countDown) {
        this.count = countDown;
        this.countDown = countDown;
        this.matchs = new ArrayList<>();
    }
    
    public int getCountDown() {
        return this.countDown;
    }
    
    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }
    
    public String getMatch() {
        synchronized (this) {
            int size = this.matchs.size();
            if (size == 0) {
                return null;
            }
            return (String)this.matchs.remove(GameServer.random.nextInt(size));
        }
    }
    
    public boolean addMatch(String name) {
        synchronized (this) {
            if (this.matchs.contains(name)) {
                return false;
            }
            this.matchs.add(name);
            return true;
        }
    }
    
    public void clearMatch() {
        synchronized (this) {
            this.countDown = this.count;
            this.matchs.clear();
        }
    }
    
    public void sendMatch() {
        if (this.TS != null) {
            for (int i = this.matchs.size() - 1; i >= 0; --i) {
                SendMessage.sendMessageByRoleName((String)this.matchs.get(i), this.TS);
            }
        }
    }
    
    public String getTS() {
        return this.TS;
    }
    
    public void setTS(String tS) {
        this.TS = tS;
    }
}
