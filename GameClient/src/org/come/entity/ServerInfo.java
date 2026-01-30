package org.come.entity;

import java.util.List;

public class ServerInfo
{
    private String WinTitle;
    private List<Server> Server;
    
    public void setWinTitle(String WinTitle) {
        this.WinTitle = WinTitle;
    }
    
    public String getWinTitle() {
        return this.WinTitle;
    }
    
    public void setServer(List<Server> Server) {
        this.Server = Server;
    }
    
    public List<Server> getServer() {
        return this.Server;
    }
}
