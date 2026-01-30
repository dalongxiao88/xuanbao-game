package org.come.until;

import java.awt.Toolkit;
import java.awt.Image;

public class flyunit
{
    public static Image image;
    
    static {
        flyunit.image = Toolkit.getDefaultToolkit().getImage("resource\\map\\111111.png");
    }
}
