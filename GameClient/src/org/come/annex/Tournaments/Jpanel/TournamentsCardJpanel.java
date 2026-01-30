package org.come.annex.Tournaments.Jpanel;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

public class TournamentsCardJpanel extends JPanel {
   private CardLayout cardLayout;
   private TournamentsScreen1Jpanel tournamentsScreen1Jpanel;
   private TournamentsScreen2Jpanel tournamentsScreen2Jpanel;
   private TournamentsScreen3Jpanel tournamentsScreen3Jpanel;
   private TournamentsScreen4Jpanel tournamentsScreen4Jpanel;

   public TournamentsCardJpanel() {
      this.setPreferredSize(new Dimension(639, 444));
      this.setOpaque(false);
      this.cardLayout = new CardLayout();
      this.setLayout(this.cardLayout);
      this.tournamentsScreen1Jpanel = new TournamentsScreen1Jpanel(this);
      this.add(this.tournamentsScreen1Jpanel, "tournamentsScreen1");
      this.tournamentsScreen2Jpanel = new TournamentsScreen2Jpanel(this);
      this.add(this.tournamentsScreen2Jpanel, "tournamentsScreen2");
      this.tournamentsScreen3Jpanel = new TournamentsScreen3Jpanel(this);
      this.add(this.tournamentsScreen3Jpanel, "tournamentsScreen3");
      this.tournamentsScreen4Jpanel = new TournamentsScreen4Jpanel(this);
      this.add(this.tournamentsScreen4Jpanel, "tournamentsScreen4");
   }

   public CardLayout getCardLayout() {
      return this.cardLayout;
   }

   public void setCardLayout(CardLayout cardLayout) {
      this.cardLayout = cardLayout;
   }

   public TournamentsScreen1Jpanel getTournamentsScreen1Jpanel() {
      return this.tournamentsScreen1Jpanel;
   }

   public void setTournamentsScreen1Jpanel(TournamentsScreen1Jpanel tournamentsScreen1Jpanel) {
      this.tournamentsScreen1Jpanel = tournamentsScreen1Jpanel;
   }

   public TournamentsScreen2Jpanel getTournamentsScreen2Jpanel() {
      return this.tournamentsScreen2Jpanel;
   }

   public void setTournamentsScreen2Jpanel(TournamentsScreen2Jpanel tournamentsScreen2Jpanel) {
      this.tournamentsScreen2Jpanel = tournamentsScreen2Jpanel;
   }

   public TournamentsScreen3Jpanel getTournamentsScreen3Jpanel() {
      return this.tournamentsScreen3Jpanel;
   }

   public void setTournamentsScreen3Jpanel(TournamentsScreen3Jpanel tournamentsScreen3Jpanel) {
      this.tournamentsScreen3Jpanel = tournamentsScreen3Jpanel;
   }

   public TournamentsScreen4Jpanel getTournamentsScreen4Jpanel() {
      return this.tournamentsScreen4Jpanel;
   }

   public void setTournamentsScreen4Jpanel(TournamentsScreen4Jpanel tournamentsScreen4Jpanel) {
      this.tournamentsScreen4Jpanel = tournamentsScreen4Jpanel;
   }
}
