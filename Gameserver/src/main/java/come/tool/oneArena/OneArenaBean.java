package come.tool.oneArena;

import java.util.List;

public class OneArenaBean
{
    private int place;
    private List<OneArenaRole> arenaList;
    private List<OneArenaNotes> notesList;
    private OneArenaNotes notes;
    
    public int getPlace() {
        return this.place;
    }
    
    public void setPlace(int place) {
        this.place = place;
    }
    
    public List<OneArenaRole> getArenaList() {
        return this.arenaList;
    }
    
    public void setArenaList(List<OneArenaRole> arenaList) {
        this.arenaList = arenaList;
    }
    
    public List<OneArenaNotes> getNotesList() {
        return this.notesList;
    }
    
    public void setNotesList(List<OneArenaNotes> notesList) {
        this.notesList = notesList;
    }
    
    public OneArenaNotes getNotes() {
        return this.notes;
    }
    
    public void setNotes(OneArenaNotes notes) {
        this.notes = notes;
    }
}
