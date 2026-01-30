package org.come.readBean;

import org.come.model.ColorScheme;
import java.util.Map;

public class AllColorScheme
{
    private Map<Integer, ColorScheme> allMap;
    
    public Map<Integer, ColorScheme> getAllMap() {
        return this.allMap;
    }
    
    public void setAllMap(Map<Integer, ColorScheme> allMap) {
        this.allMap = allMap;
    }
}
