package org.come.annex.Tournaments.Btn;

import com.tool.btn.MoBanBtn;
import com.tool.tcpimg.UIUtils;
import org.come.annex.Tournaments.Frame.TournamentsJframe;
import org.come.annex.Tournaments.Jpanel.TournamentsJpanel;
import org.come.until.CutButtonImage;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TournamentsNavBtn extends MoBanBtn {
   private int caozuo;
   private TournamentsJpanel tournamentsJpanel;
   private int textOrientation;

   public TournamentsNavBtn(String iconpath, int type, int caozuo, TournamentsJpanel tournamentsJpanel, String text, int textOrientation) {
      super(iconpath, type, UIUtils.COLOR_NAVIGATIONSW);
      this.setText(text);
      this.setFont(UIUtils.HYI_WBJ19S);
      this.caozuo = caozuo;
      this.tournamentsJpanel = tournamentsJpanel;
      this.textOrientation = textOrientation;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setColor(this.getForeground());
      if (this.textOrientation == 1) {
         FontMetrics metrics = g2d.getFontMetrics();
         int x = this.getWidth() / 2 - metrics.getHeight() / 2;
         int y = this.getHeight() / 4 + metrics.getHeight() / 4;

         for (char c : this.getText().toCharArray()) {
            g2d.drawString(String.valueOf(c), x, y);
            y += metrics.getHeight();
         }
      } else {
         FontMetrics metrics = g2d.getFontMetrics();
         int x = this.getWidth() / 2 - metrics.stringWidth(this.getText()) / 2;
         int y = this.getHeight() / 2 + metrics.getHeight() / 4;
         g2d.drawString(this.getText(), x, y);
      }
   }

   public TournamentsNavBtn(String iconpath, int type, int caozuo, TournamentsJpanel tournamentsJpanel) {
      super(iconpath, type);
      this.caozuo = caozuo;
      this.tournamentsJpanel = tournamentsJpanel;
   }

   @Override
   public void chooseyes() {
   }

   @Override
   public void chooseno() {
   }

   @Override
   public void nochoose(MouseEvent e) {
      try {
         if (this.caozuo == 10) {
            this.tournamentsJpanel.getBtnTournamentsScreen1().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e4.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen1().setBounds(660, 15, 45, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen2().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen2().setBounds(660, 122, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen3().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen3().setBounds(660, 229, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen4().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen4().setBounds(660, 336, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen1().setColors(UIUtils.COLOR_NAVIGATIONS);
            this.tournamentsJpanel.getBtnTournamentsScreen2().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen3().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen4().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getTournamentsCardJpanel().getCardLayout().show(this.tournamentsJpanel.getTournamentsCardJpanel(), "tournamentsScreen1");
         } else if (this.caozuo == 11) {
            this.tournamentsJpanel.getBtnTournamentsScreen1().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen1().setBounds(660, 15, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen2().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e4.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen2().setBounds(660, 122, 45, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen3().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen3().setBounds(660, 229, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen4().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen4().setBounds(660, 336, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen1().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen2().setColors(UIUtils.COLOR_NAVIGATIONS);
            this.tournamentsJpanel.getBtnTournamentsScreen3().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen4().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getTournamentsCardJpanel().getCardLayout().show(this.tournamentsJpanel.getTournamentsCardJpanel(), "tournamentsScreen2");
            TournamentsJframe.getTournamentsJpanel().getTournamentsCardJpanel().getTournamentsScreen2Jpanel().initializeDefaultButton();
         } else if (this.caozuo == 12) {
            this.tournamentsJpanel.getBtnTournamentsScreen1().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen1().setBounds(660, 15, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen2().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen2().setBounds(660, 122, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen3().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e4.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen3().setBounds(660, 229, 45, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen4().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen4().setBounds(660, 336, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen1().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen2().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen3().setColors(UIUtils.COLOR_NAVIGATIONS);
            this.tournamentsJpanel.getBtnTournamentsScreen4().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getTournamentsCardJpanel().getCardLayout().show(this.tournamentsJpanel.getTournamentsCardJpanel(), "tournamentsScreen3");
            TournamentsBtn.claimTheDraw(99, "3");
         } else if (this.caozuo == 13) {
            this.tournamentsJpanel.getBtnTournamentsScreen1().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen1().setBounds(660, 15, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen2().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen2().setBounds(660, 122, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen3().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e3.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen3().setBounds(660, 229, 40, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen4().setIcons(CutButtonImage.cuts("inkImg/danxin/p/e4.png"));
            this.tournamentsJpanel.getBtnTournamentsScreen4().setBounds(660, 336, 45, 103);
            this.tournamentsJpanel.getBtnTournamentsScreen1().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen2().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen3().setColors(UIUtils.COLOR_NAVIGATIONSW);
            this.tournamentsJpanel.getBtnTournamentsScreen4().setColors(UIUtils.COLOR_NAVIGATIONS);
            this.tournamentsJpanel.getTournamentsCardJpanel().getCardLayout().show(this.tournamentsJpanel.getTournamentsCardJpanel(), "tournamentsScreen4");
            TournamentsBtn.claimTheDraw(99, "4");
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      super.mouseEntered(e);
   }

   @Override
   public void mouseExited(MouseEvent e) {
      super.mouseExited(e);
   }
}
