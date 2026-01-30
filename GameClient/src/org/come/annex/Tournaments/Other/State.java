package org.come.annex.Tournaments.Other;

import com.tool.btn.MoBanBtn;
import java.awt.event.MouseEvent;

public class State extends MoBanBtn {
   private int state = 1;

   public State(String iconpath, int type) {
      super(iconpath, type);
   }

   @Override
   public void chooseyes() {
   }

   @Override
   public void chooseno() {
   }

   @Override
   public void nochoose(MouseEvent e) {
   }

   public int getState() {
      return this.state;
   }

   public void setState(int newState) {
      this.state = newState;
   }

   public void toggleState() {
      if (this.state == 1) {
         this.state = 2;
      } else {
         this.state = 1;
      }
   }
}
