package come.tool.Scene.SLDH;

import come.tool.Role.RoleShow;

public class SLDHBean
{
    private int JS;
    private int CI;
    private RoleShow[] lastShows;
    
    public int getJS() {
        return this.JS;
    }
    
    public void setJS(int jS) {
        this.JS = jS;
    }
    
    public int getCI() {
        return this.CI;
    }
    
    public void setCI(int cI) {
        this.CI = cI;
    }
    
    public RoleShow[] getLastShows() {
        return this.lastShows;
    }
    
    public void setLastShows(RoleShow[] lastShows) {
        this.lastShows = lastShows;
    }
}
