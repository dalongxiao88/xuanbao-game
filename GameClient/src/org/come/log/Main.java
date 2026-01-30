package org.come.log;

import org.come.init.CheckUpdate;

public class Main
{
    public static CheckUpdate checkUpdate;
    
    public static void main(String[] args) {
        boolean sign = false;
        for (String arg : args) {
            if (arg.equals("-index")) {
                sign = true;
            }
        }
        if (sign) {
            try {
                org.come.test.Main.main(args);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        else {
            Main.checkUpdate = new CheckUpdate();
            Thread thread = new Thread(Main.checkUpdate);
            thread.start();
            return;
        }
    }
}
