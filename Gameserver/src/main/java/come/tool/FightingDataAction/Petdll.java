package come.tool.FightingDataAction;

import com.sun.jna.Native;
import com.sun.jna.Library;

public class Petdll
{
    public interface Mypetdll extends Library
    {
        public static final Mypetdll mypetdll = (Mypetdll)Native.load("Mydll", Mypetdll.class);
        
        String bianshen(int p0, int p1);
    }
}
