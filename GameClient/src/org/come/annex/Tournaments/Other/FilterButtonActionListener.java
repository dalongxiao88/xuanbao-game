package org.come.annex.Tournaments.Other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.come.annex.Tournaments.Jpanel.TournamentsScreen2Jpanel;

public class FilterButtonActionListener implements ActionListener {
   private int index;
   TournamentsScreen2Jpanel tournamentsScreen2Jpanel;

   public FilterButtonActionListener(int index, TournamentsScreen2Jpanel tournamentsScreen2Jpanel) {
      this.index = index;
      this.tournamentsScreen2Jpanel = tournamentsScreen2Jpanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      this.tournamentsScreen2Jpanel.setSelectedButton(this.index);
   }
}
