package come.tool.handle;

public enum HandleType
{
    Null(null), 
    Player(new HandlePlayer()), 
    pet(new HandlePet()), 
    Wait(new HandleWait()), 
    Battle(new HandleBattle()), 
    Play(new HandlePlay()), 
    End(new HandleEnd());
    
    private Handle handle;
    
    private HandleType(Handle handle) {
        this.handle = handle;
    }
    
    public Handle getTarget() {
        return this.handle;
    }
    
    public static Handle getHandleById(int handletId) {
        HandleType[] values = values();
        HandleType handleType = values[handletId];
        return handleType.getTarget();
    }
}
