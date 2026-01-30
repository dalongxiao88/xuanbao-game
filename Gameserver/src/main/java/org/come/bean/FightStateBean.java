package org.come.bean;

public class FightStateBean
{
    private int gameStartRound;
    private int gameoverRound;
    private String playerInearseState;
    private String playerstate;
    
    public String getPlayerInearseState() {
        return this.playerInearseState;
    }
    
    public void setPlayerInearseState(String playerInearseState) {
        this.playerInearseState = playerInearseState;
    }
    
    public String getPlayerstate() {
        return this.playerstate;
    }
    
    public void setPlayerstate(String playerstate) {
        this.playerstate = playerstate;
    }
    
    public int getGameStartRound() {
        return this.gameStartRound;
    }
    
    public void setGameStartRound(int gameStartRound) {
        this.gameStartRound = gameStartRound;
    }
    
    public int getGameoverRound() {
        return this.gameoverRound;
    }
    
    public void setGameoverRound(int gameoverRound) {
        this.gameoverRound = gameoverRound;
    }
}
