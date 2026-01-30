package com.tool.btn;

public class BtnUtil
{
    public static void btnBinding(MoBanBtn[] banBtns, int i) {
        for (int j = 0; j < banBtns.length; ++j) {
            banBtns[j].btnchange(0);
        }
        banBtns[i].btnchange(2);
    }
}
