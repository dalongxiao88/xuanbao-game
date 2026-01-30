package org.come.annex.Tournaments.Other;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.come.until.MessagrFlagUntil;

public class CustomMouseListener extends MouseAdapter {
   @Override
   public void mouseEntered(MouseEvent e) {
      if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1) || MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
         MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
      }
   }

   @Override
   public void mouseExited(MouseEvent e) {
      if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12) || MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
         MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
      }
   }
}
