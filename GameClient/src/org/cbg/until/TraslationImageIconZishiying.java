package org.cbg.until;

import javax.swing.ImageIcon;

public class TraslationImageIconZishiying
{
    public static ImageIcon xiugaitupainSize(ImageIcon img, int width, int hight) {
        img.setImage(img.getImage().getScaledInstance(width, hight, 1));
        return img;
    }
}
