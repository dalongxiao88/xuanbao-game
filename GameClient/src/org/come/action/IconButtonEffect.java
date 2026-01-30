package org.come.action;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IconButtonEffect extends MouseAdapter {
   private JComponent component;

   public IconButtonEffect(JComponent component) {
      this.component = component;
   }

   @Override
   public void mousePressed(MouseEvent e) {
      this.component.setLocation(this.component.getX() + 1, this.component.getY() + 2);
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      this.component.setLocation(this.component.getX() - 1, this.component.getY() - 2);
   }
}
