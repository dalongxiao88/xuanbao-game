package org.come.annex.Tournaments.Frame;

import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.come.annex.Tournaments.Jpanel.TournamentsJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;
import org.come.until.ScrenceUntil;

public class TournamentsJframe extends JInternalFrame implements MouseListener {
   private static TournamentsJpanel tournamentsJpanel;
   private int first_x;
   private int first_y;

   public static TournamentsJframe getTournamentsJframe() {
      return (TournamentsJframe)FormsManagement.getInternalForm(1146).getFrame();
   }

   public TournamentsJframe() throws Exception {
      tournamentsJpanel = new TournamentsJpanel();
      this.getContentPane().add(tournamentsJpanel);
      this.setBorder(BorderFactory.createEmptyBorder());
      ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
      int x = (ScrenceUntil.Screen_x - 669 + 40) / 2;
      int y = (ScrenceUntil.Screen_y - 474) / 2;
      this.setBounds(x, y, 709, 474);
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
               if (TournamentsJframe.this.isVisible()) {
                  int xx = e.getX() - TournamentsJframe.this.first_x;
                  int yx = e.getY() - TournamentsJframe.this.first_y;
                  TournamentsJframe.this.setBounds(
                     xx + TournamentsJframe.this.getX(),
                     yx + TournamentsJframe.this.getY(),
                     TournamentsJframe.this.getWidth(),
                     TournamentsJframe.this.getHeight()
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
         FormsManagement.HideForm(1146);
      } else {
         FormsManagement.Switchinglevel(1146);
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

   public static TournamentsJpanel getTournamentsJpanel() {
      return tournamentsJpanel;
   }

   public static void setTournamentsJpanel(TournamentsJpanel tournamentsJpanel) {
      TournamentsJframe.tournamentsJpanel = TournamentsJframe.tournamentsJpanel;
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
