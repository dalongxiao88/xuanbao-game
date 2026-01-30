package org.come.annex.Tournaments.Frame;

import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.come.annex.Tournaments.Jpanel.TournamentsResultsJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;
import org.come.until.ScrenceUntil;

public class TournamentsResultsJframe extends JInternalFrame implements MouseListener {
   private static TournamentsResultsJpanel tournamentsResultsJpanel;
   private int first_x;
   private int first_y;

   public static TournamentsResultsJframe getTournamentsResultsJframe() {
      return (TournamentsResultsJframe)FormsManagement.getInternalForm(1148).getFrame();
   }

   public TournamentsResultsJframe() throws Exception {
      tournamentsResultsJpanel = new TournamentsResultsJpanel();
      this.getContentPane().add(tournamentsResultsJpanel);
      this.setBorder(BorderFactory.createEmptyBorder());
      ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
      int x = (ScrenceUntil.Screen_x - 800) / 2;
      int y = (ScrenceUntil.Screen_y - 515) / 2;
      this.setBounds(x, y, 800, 515);
      this.pack();
      this.setBackground(UIUtils.Color_BACK);
      this.setDefaultCloseOperation(3);
      this.setVisible(false);
      this.addMouseListener(this);
      this.addMouseMotionListener(
         new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
               if (TournamentsResultsJframe.this.isVisible()) {
                  int xx = e.getX() - TournamentsResultsJframe.this.first_x;
                  int yx = e.getY() - TournamentsResultsJframe.this.first_y;
                  TournamentsResultsJframe.this.setBounds(
                     xx + TournamentsResultsJframe.this.getX(),
                     yx + TournamentsResultsJframe.this.getY(),
                     TournamentsResultsJframe.this.getWidth(),
                     TournamentsResultsJframe.this.getHeight()
                  );
               }
            }
         }
      );
   }

   @Override
   public void mouseClicked(MouseEvent e) {
   }

   @Override
   public void mousePressed(MouseEvent e) {
      Music.addyinxiao("关闭窗口.mp3");
      if (e.isMetaDown()) {
         FormsManagement.HideForm(1148);
      } else {
         FormsManagement.Switchinglevel(1148);
      }

      this.first_x = e.getX();
      this.first_y = e.getY();
   }

   @Override
   public void mouseReleased(MouseEvent e) {
   }

   @Override
   public void mouseEntered(MouseEvent e) {
   }

   @Override
   public void mouseExited(MouseEvent e) {
   }

   public static TournamentsResultsJpanel getTournamentsResultsJpanel() {
      return tournamentsResultsJpanel;
   }

   public static void setTournamentsResultsJpanel(TournamentsResultsJpanel tournamentsResultsJpanel) {
      TournamentsResultsJframe.tournamentsResultsJpanel = TournamentsResultsJframe.tournamentsResultsJpanel;
   }

   public int getFirst_x() {
      return this.first_x;
   }

   public void setFirst_x(int first_x) {
      this.first_x = first_x;
   }

   public int getFirst_y() {
      return this.first_y;
   }

   public void setFirst_y(int first_y) {
      this.first_y = first_y;
   }
}
