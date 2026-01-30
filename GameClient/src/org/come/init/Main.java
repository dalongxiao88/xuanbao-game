package org.come.init;

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
                com.main.Main.main(args);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        else {
            System.err.println(1);
            System.out.println(2);
            Main.checkUpdate = new CheckUpdate();
            Thread thread = new Thread(Main.checkUpdate);
            thread.start();
            return;
        }
    }
}
