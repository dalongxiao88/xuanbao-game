package com.main;

import com.updateNew.MyQdModeJFrame;
import org.come.test.Main;
import com.updateNew.MyIsif;

public class DKMain
{
    public static void main(String[] args) throws Exception {
        MyIsif.ifs = "D";
        boolean sign = false;
        for (String arg : args) {
            if (arg.equals("-index")) {
                sign = true;
            }
        }
        if (sign) {
            try {
                Main.main(args);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        else {
            if (MyIsif.ifs.equals("D")) {
                MyQdModeJFrame.ggt(args);
            }
            else {
                MyQdModeJFrame.ggtd(args);
            }
            return;
        }
    }
}
