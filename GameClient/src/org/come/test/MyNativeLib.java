package org.come.test;

import com.sun.jna.Native;
import com.sun.jna.Library;

public interface MyNativeLib extends Library
{
    public static final MyNativeLib INSTANCE = (MyNativeLib)Native.loadLibrary("QianNiao", MyNativeLib.class);
    
    void Start_QianNiao();
}
